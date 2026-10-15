package com.genai.mynotesapp.repository

import com.genai.mynotesapp.data.room_database.TaskDao
import com.genai.mynotesapp.data.room_database.TaskItem
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val dao: TaskDao) {

     fun getAllTask(): Flow<List<TaskItem>>{
         return dao.getAllTask()
     }

    suspend fun insert(task: TaskItem) = dao.insertTask(task)

    suspend fun update(task: TaskItem) = dao.update(task)

    suspend fun delete(task: TaskItem) = dao.delete(task)
}