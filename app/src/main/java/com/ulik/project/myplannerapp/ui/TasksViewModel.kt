package com.ulik.project.myplannerapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ulik.project.myplannerapp.data.model.Task
import com.ulik.project.myplannerapp.domain.MainDomain
import com.ulik.project.myplannerapp.presenter.TaskPresenterState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class TasksViewModel(
    private val domain: MainDomain,
    private val tasksPresenterState: TaskPresenterState
) : ViewModel(), TaskPresenterState by tasksPresenterState {

    fun saveTask(description: String, title: String) {

        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                domain.createTask(Task(title = title, description = description))
            }
        }
    }
}