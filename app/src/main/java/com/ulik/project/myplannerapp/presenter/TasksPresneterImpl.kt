package com.ulik.project.myplannerapp.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ulik.project.myplannerapp.data.model.Task
import com.ulik.project.myplannerapp.utilities.Event

class TasksPresneterImpl : TaskPresenterState,TasksPresnter{
    private val _loadingEvent = MutableLiveData<Event<Boolean>>()
    override val loadingEvent: LiveData<Event<Boolean>> = _loadingEvent
    override suspend fun showLoading(state: Boolean) {
        _loadingEvent.postValue(Event(state))
    }

    private val _deleteTask = MutableLiveData<Event<List<Task>>>()
    override val deleteTask: LiveData<Event<List<Task>>> = _deleteTask
    override suspend fun showDeletedTask(tasks: List<Task>) {
        _deleteTask.postValue(Event(tasks))
    }

    private val _showErrorEvent = MutableLiveData<Event<String>>()
    override val showErrorEvent: LiveData<Event<String>> = _showErrorEvent
    override suspend fun showError(message: String) {
        _showErrorEvent.postValue(Event(message))
    }

    private val _shoTaskSaveSuccesfuly = MutableLiveData<Event<List<Task>>>()
    override val shoTaskSaveSuccesfuly: LiveData<Event<List<Task>>> = _shoTaskSaveSuccesfuly
    override suspend fun showTaskSavedSuccessfuly(tasks:List<Task>) {
        _shoTaskSaveSuccesfuly.postValue(Event(tasks))
    }

}