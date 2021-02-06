package com.ulik.project.myplannerapp.data.localDataSource

import androidx.room.*
import com.ulik.project.myplannerapp.data.model.Task

@Dao
interface TasksDao {

    @Query("SELECT * FROM Task") //Where isFavorite true
    suspend fun getTasks(): List<Task>

    @Insert
    suspend fun saveTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

}
