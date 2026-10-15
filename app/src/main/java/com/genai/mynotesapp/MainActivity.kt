package com.genai.mynotesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.genai.mynotesapp.ui.screens.ToDoScreen
import com.genai.mynotesapp.ui.theme.MyNotesAppTheme
import com.genai.mynotesapp.viewModel.TaskViewModeFactory
import com.genai.mynotesapp.viewModel.TaskViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: TaskViewModel by viewModels {
        TaskViewModeFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyNotesAppTheme {
                ToDoScreen(viewModel)
            }
        }
    }
}

