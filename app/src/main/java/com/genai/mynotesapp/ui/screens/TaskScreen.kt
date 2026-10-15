package com.genai.mynotesapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.genai.mynotesapp.data.room_database.TaskItem
import com.genai.mynotesapp.ui.theme.darkGrey
import com.genai.mynotesapp.ui.theme.grey
import com.genai.mynotesapp.viewModel.TaskViewModel


@Composable
fun ToDoScreen(viewModel: TaskViewModel) {

    val tasks by viewModel.allTask.collectAsState()
    var taskToEdit by remember { mutableStateOf<TaskItem?>(null) }
    var showEditDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    taskToEdit = null
                    showEditDialog = true
                },
                shape = RoundedCornerShape(20.dp),
                containerColor = darkGrey,
                contentColor = Color.White,
                elevation = FloatingActionButtonDefaults.elevation(8.dp)
            )
            {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
                Text(
                    text = "New task",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = "My Tasks",
                modifier = Modifier.padding(top = 32.dp),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = darkGrey
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "${tasks.filter { !it.isDone }.size} remaining today",
                color = darkGrey
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (tasks.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                )
                {
                    Text(
                        text = "No tasks for today! Add a new one above",
                        color = grey
                    )
                }

            } else {

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 80.dp)
                )
                {
                    items(items = tasks, key = { it.id })
                    {task->
                          ToDoItem(
                              item= task,
                              onEditClick = {
                                  taskToEdit = task
                                  showEditDialog = true
                              },
                              onDeleteClick = {
                                  viewModel.deleteTask(task)
                              },
                              onCheckChanged = {
                                  viewModel.updateTask(task.copy(isDone = it))

                              }
                          )
                    }

                }
            }
        }
    }

    if(showEditDialog){
       TaskEditorDialog(
           task = taskToEdit,
           onSave = { newName->
               if(taskToEdit == null){
                   viewModel.addTask(TaskItem(taskName = newName, isDone = false))
               }else{
                   taskToEdit?.let {currentTask->
                       viewModel.updateTask(currentTask.copy(taskName = newName))
                   }
               }
               showEditDialog = false
               taskToEdit = null

           },
           onCancel = {
               showEditDialog = false
               taskToEdit = null
           }

       )
    }

}