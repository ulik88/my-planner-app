package com.ulik.project.myplannerapp.presenter

import com.ulik.project.myplannerapp.data.model.Task

interface TasksPresnter {

    suspend fun showTaskSavedSuccessfuly(tasks:List<Task>)
    suspend fun showError(message: String)
    suspend fun showLoading(state: Boolean)
}