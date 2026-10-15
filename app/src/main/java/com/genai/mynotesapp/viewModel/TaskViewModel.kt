package com.genai.mynotesapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.genai.mynotesapp.data.room_database.TaskDatabase
import com.genai.mynotesapp.data.room_database.TaskItem
import com.genai.mynotesapp.repository.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = TaskDatabase.getDataBase(application).taskDao()
    private val repository = TaskRepository(dao)

    val allTask:StateFlow<List<TaskItem>>  = repository.getAllTask()
        .stateIn(viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList())

    fun addTask(task:TaskItem){
        viewModelScope.launch {
            repository.insert(task)
        }
    }

    fun updateTask(task:TaskItem){
        viewModelScope.launch {
            repository.update(task)
        }
    }

    fun deleteTask(task:TaskItem){
        viewModelScope.launch {
            repository.delete(task)
        }
    }

}