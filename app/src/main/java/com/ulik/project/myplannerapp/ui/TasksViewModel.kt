package com.ulik.project.myplannerapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ulik.project.myplannerapp.data.model.Task
import com.ulik.project.myplannerapp.domain.MainUseCase
import com.ulik.project.myplannerapp.presenter.TaskPresenterState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class TasksViewModel(
        private val useCase: MainUseCase,
        private val tasksPresenterState: TaskPresenterState
) : ViewModel(), TaskPresenterState by tasksPresenterState {
    val tasks = mutableListOf<Task>()

    init {
//        tasksPresenterState.shoTaskSaveSuccesfuly.observeForever( Observer {
//            tasks.clear()
//            tasks.addAll(it)
//        })
    }

    fun saveTask(description: String, title: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                useCase.saveTask(Task(title = title, description = description))
            }
        }
    }

    fun remove(position: Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                useCase.deleteTask(tasks[position])
                tasks.removeAt(position)
            }
        }
    }
}