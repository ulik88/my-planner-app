package com.ulik.project.myplannerapp.data

import com.ulik.project.myplannerapp.data.model.Task
import com.ulik.project.myplannerapp.utilities.Result
import kotlinx.coroutines.delay
import java.lang.Exception

class TaskRepoImplement : TaskRepository {

    val taskList = mutableListOf<Task>()

    override suspend fun getTasks(): Result<List<Task>> {
        return Result.Success(taskList)
    }

    override suspend fun saveTask(task: Task): Result<List<Task>> {
        delay(3000)
        taskList.add(task)
        return Result.Success(taskList)
    }

    override suspend fun updateTask(task: Task): Result<Unit> {
        taskList.forEach {

            if (it.id == task.id){
                taskList.remove(it)
                taskList.add(task)
                return Result.Success(Unit)
            }
        }
        return Result.Error(Exception("task not found"))
    }

    override suspend fun deleteTask(task: Task): Result<Unit> {
        taskList.forEach {

            if (it.id == task.id){
                taskList.remove(it)
                return Result.Success(Unit)
            }
        }
        return Result.Error(Exception("task not found"))
    }
}