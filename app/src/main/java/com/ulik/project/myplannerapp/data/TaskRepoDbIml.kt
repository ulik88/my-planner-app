package com.ulik.project.myplannerapp.data

import com.ulik.project.myplannerapp.data.localDataSource.TasksDao
import com.ulik.project.myplannerapp.data.model.Task
import com.ulik.project.myplannerapp.utilities.Result

class TaskRepoDbIml(val dao: TasksDao) : TaskRepository {
    override suspend fun getTasks(): Result<List<Task>> {
        return Result.Success(dao.getTasks())
    }

    override suspend fun saveTask(task: Task): Result<List<Task>> {
        dao.saveTask(task)
        return Result.Success(dao.getTasks())
    }

    override suspend fun updateTask(task: Task): Result<List<Task>> {
        dao.updateTask(task)
        return Result.Success(dao.getTasks())
    }

    override suspend fun deleteTask(task: Task): Result<Unit> {
        return Result.Success(dao.deleteTask(task))
    }
}