package com.ulik.project.myplannerapp.presenter

import com.ulik.project.myplannerapp.data.model.Task

interface TasksPresenter {

    suspend fun showTaskSavedSuccessfuly(tasks:List<Task>)
    suspend fun showError(message: String)
    suspend fun showLoading(state: Boolean)
    suspend fun taskDeletedSuccesfully()
    suspend fun showLoadedTask(tasks: List<Task>)
//    suspend fun showTaskSaredSuccessfuly(task: List<Task>)
}