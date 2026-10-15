package com.genai.mynotesapp.data.room_database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskItem)

    @Update
    suspend fun update(task: TaskItem)

    @Delete
    suspend fun delete(task: TaskItem)

    @Query("SELECT * FROM tasks ORDER BY id ASC")
    fun getAllTask(): Flow<List<TaskItem>>

}