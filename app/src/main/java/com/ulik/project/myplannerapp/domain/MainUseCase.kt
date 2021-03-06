package com.ulik.project.myplannerapp.domain

import com.ulik.project.myplannerapp.data.TaskRepository
import com.ulik.project.myplannerapp.data.model.Task
import com.ulik.project.myplannerapp.presenter.TasksPresenter
import com.ulik.project.myplannerapp.utilities.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect

class MainUseCase(
    val taskRepository: TaskRepository,
    val tasksPresenter: TasksPresenter

   ) {

    suspend fun saveTask(task: Task){
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

    suspend fun updateTask(task: Task) {
        var update = taskRepository.updateTask(task)
        when(update){
            is Result.Success ->{
                tasksPresenter.showTaskSavedSuccessfuly(update.data)
            }

            is Result.Error ->{
                tasksPresenter.showError(update.exception.toString())
            }
        }
    }

    suspend fun shareTaskToGroup(task: Task){
        var update = taskRepository.shareTaskToGroup(task)
        when (update){
            is Result.Success -> {
                tasksPresenter.showTaskSavedSuccessfuly(update.data)
//                tasksPresenter.showTaskSharedSuccessfuly(update.data)
            }

            is Result.Error -> {
                tasksPresenter.showError(update.exception.toString())
            }
        }
    }

    suspend fun getSharedTasks(){
        var sharedTasks = taskRepository.getSharedTasks()

        when (sharedTasks){
            is Result.Success -> {
                tasksPresenter.showSharedTasks(sharedTasks.data)
            }
            is Result.Error -> {
                tasksPresenter.showError(sharedTasks.exception.toString())
            }
        }


    }
    suspend fun observerForChanges(){
        taskRepository.observeForChanges().collect {
            getSharedTasks()
            tasksPresenter.notifyUser(it)
        }
    }

    suspend fun deleteTask(task: Task){
//        tasksPresenter.taskDeletedSuccesfully()

        var delete = taskRepository.deleteTask(task)
        when(delete){
            is Result.Success -> {
                tasksPresenter.taskDeletedSuccesfully()
            }
            is Result.Error ->{
                tasksPresenter.showError(delete.exception.toString())
            }
        }
    }

    suspend fun getAllTasks() {
        tasksPresenter.showLoading(true)
        val result = taskRepository.getTasks()
        when (result) {
            is Result.Success -> {
                tasksPresenter.showLoadedTask(result.data)
            }
            is Result.Error -> {
                tasksPresenter.showError(result.exception.toString())
            }
        }
        tasksPresenter.showLoading(false)
    }

    suspend fun getAllFavoriteTasks() {
        tasksPresenter.showLoading(true)
        val result = taskRepository.getTasks()
        when (result) {
            is Result.Success -> {

                tasksPresenter.showSharedFavoriteTasks(result.data.filter {
                    it.isFavorite
                })
            }
            is Result.Error -> {
                tasksPresenter.showError(result.exception.toString())
            }
        }
        tasksPresenter.showLoading(false)
    }

}