package com.ulik.project.myplannerapp.domain

import com.ulik.project.myplannerapp.data.TaskRepository
import com.ulik.project.myplannerapp.data.model.Task
import com.ulik.project.myplannerapp.presenter.TasksPresnter
import com.ulik.project.myplannerapp.utilities.Result
import kotlinx.coroutines.delay

class MainDomain(
    val taskRepository: TaskRepository,
    val tasksPresenter: TasksPresnter

   ) {

    suspend fun createTask(task: Task){
        tasksPresenter.showLoading(true)
        var result = taskRepository.saveTask(task)
        when (result) {
            is Result.Success -> {
                tasksPresenter.showTaskSavedSuccessfuly(result.data)
            }
            is Result.Error -> {
                tasksPresenter.showError(result.exception.toString())
            }
        }
        delay(1000)
        tasksPresenter.showLoading(false)
    }
}