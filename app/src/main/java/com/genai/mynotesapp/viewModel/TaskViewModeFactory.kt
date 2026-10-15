package com.genai.mynotesapp.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TaskViewModeFactory(private val application : Application) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            return TaskViewModel(application) as T
        }else{
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}