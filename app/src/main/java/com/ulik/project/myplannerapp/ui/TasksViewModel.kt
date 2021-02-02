package com.ulik.project.myplannerapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ulik.project.myplannerapp.data.model.Task
import com.ulik.project.myplannerapp.domain.MainUseCase
import com.ulik.project.myplannerapp.presenter.TaskPresenterState
import com.ulik.project.myplannerapp.utilities.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class TasksViewModel(
        private val useCase: MainUseCase,
        private val tasksPresenterState: TaskPresenterState
) : ViewModel(), TaskPresenterState by tasksPresenterState {
    val tasks = mutableListOf<Task>()

    init {

        viewModelScope.launch {
            withContext(Dispatchers.IO){
                useCase.getAllTasks()
            }
        }
    }

    fun saveTask(description: String, title: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                useCase.saveTask(Task(title = title, description = description))
            }
        }
    }

    fun taskUpdate(task: Task){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                useCase.updateTask(task)
            }
        }
    }

    fun remove(task: Task){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                useCase.deleteTask(task)
//                tasks.removeAt(position)
            }
        }
    }

    private val _openTaskDetails = MutableLiveData<Event<Task>>() // Works here by ViewModel only
    val openTaskDetails: LiveData<Event<Task>> = _openTaskDetails  // wird vom Fragment beobachtet
    fun showTaskDetails(task: Task) {
        _openTaskDetails.value= Event(task)   //InFlow, value of LiveData
    }
}