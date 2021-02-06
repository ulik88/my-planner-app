package com.ulik.project.myplannerapp.data

import com.ulik.project.myplannerapp.data.model.Task
import com.ulik.project.myplannerapp.utilities.Result

interface TaskRepository{
    suspend fun getTasks():Result<List<Task>>
    suspend fun saveTask(task: Task):Result<List<Task>>
    suspend fun updateTask(task: Task):Result<List<Task>>
    suspend fun deleteTask(task: Task):Result<Unit>
    suspend fun shareTaskToGroup(task: Task): Result<List<Task>>
    suspend fun getSharedTasks(): Result<List<Task>>
//    suspend fun getSharedFavoriteTasks(): Result<List<Task>>
//    suspend fun shareFavoriteTaskToGroup(task: Task): Result<List<Task>>
}