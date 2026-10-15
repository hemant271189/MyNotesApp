package com.genai.mynotesapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.genai.mynotesapp.data.room_database.TaskItem
import com.genai.mynotesapp.ui.theme.darkGrey
import com.genai.mynotesapp.ui.theme.grey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskEditorDialog(
                     task: TaskItem?,
                     onSave:(String) -> Unit,
                     onCancel:() -> Unit) {

    var taskName by remember { mutableStateOf(task?.taskName ?: "") }

    ModalBottomSheet(
        onDismissRequest = onCancel,
        containerColor = Color.White,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .navigationBarsPadding()
        ) {
            Text(
                text = if(task == null)
                    " Create new Task "
                else
                    "Update Task",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = taskName,
                onValueChange = { taskName = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = "Enter task name") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = darkGrey,
                    unfocusedBorderColor = grey
                ),
                shape = RoundedCornerShape(16.dp)

            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { onSave(taskName.trim())},
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(grey),
                enabled = taskName.isNotBlank()
            )
            {
                Text(
                    text = "Save Task",
                    fontSize = 16.sp
                )

            }
        }
    }
}