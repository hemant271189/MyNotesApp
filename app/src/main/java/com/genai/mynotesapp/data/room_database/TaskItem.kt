package com.genai.mynotesapp.data.room_database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val taskName: String,
    val isDone: Boolean = false
)
