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
        delay(1000)
        taskList.add(task)
        return Result.Success(taskList)
    }

    override suspend fun updateTask(task: Task): Result<List<Task>> {
        taskList.forEach {

            if (it.id == task.id){
                taskList.remove(it)
                taskList.add(task)
                return Result.Success(taskList.reversed())
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

    override suspend fun shareTaskToGroup(task: Task): Result<List<Task>> {
        TODO("Not yet implemented")
    }

    override suspend fun getSharedTasks(): Result<List<Task>> {
        TODO("Not yet implemented")
    }


//    override suspend fun getSharedFavoriteTasks(): Result<List<Task>> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun shareFavoriteTaskToGroup(task: Task): Result<List<Task>> {
//        taskList.forEach {
//
//            if (it.id == task.id){
//                taskList.remove(it)
//                taskList.add(task)
//                return Result.Success(taskList.reversed())
//            }
//        }
//        return Result.Error(Exception("task not found"))
//    }
}