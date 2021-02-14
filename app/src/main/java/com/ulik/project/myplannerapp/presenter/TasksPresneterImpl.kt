package com.ulik.project.myplannerapp.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ulik.project.myplannerapp.data.model.Task
import com.ulik.project.myplannerapp.utilities.Event

class TasksPresneterImpl : TaskPresenterState,TasksPresenter{
    private val _loadingEvent = MutableLiveData<Event<Boolean>>()
    override val loadingEvent: LiveData<Event<Boolean>> = _loadingEvent
    override suspend fun showLoading(state: Boolean) {
        _loadingEvent.postValue(Event(state))
    }

    private val _showErrorEvent = MutableLiveData<Event<String>>()
    override val showErrorEvent: LiveData<Event<String>> = _showErrorEvent
    override suspend fun showError(message: String) {
        _showErrorEvent.postValue(Event(message))
    }

    val _updateAdapter: MutableLiveData<Event<List<Task>>> = MutableLiveData<Event<List<Task>>>()
    override val updateAdapter: LiveData<Event<List<Task>>> =_updateAdapter

    private val _showTaskSavedSuccesfuly = MutableLiveData<Event<List<Task>>>()
    override val showTaskSaveSuccesfuly: LiveData<Event<List<Task>>> = _showTaskSavedSuccesfuly


    private val _navigateToTasksFragment: MutableLiveData<Event<List<Task>>> =
        _showTaskSavedSuccesfuly
    override val navigateToTasksFragment: LiveData<Event<List<Task>>> = _navigateToTasksFragment

    override suspend fun showTaskSavedSuccessfuly(tasks:List<Task>) {
        _showTaskSavedSuccesfuly.postValue(Event(tasks))
        _updateAdapter.postValue(Event(tasks))
        _navigateToTasksFragment.postValue(Event(tasks))
    }

    private val _showLoadedTaskEvent = MutableLiveData<Event<List<Task>>>()
    override val showLoadedTaskEvent: LiveData<Event<List<Task>>> = _showLoadedTaskEvent
    override suspend fun showLoadedTask(tasks: List<Task>) {
        _showLoadedTaskEvent.postValue(Event(tasks))
    }

    private val _showTaskSharedSuccessfulyEvent = MutableLiveData<Event<List<Task>>>()
    override val showTaskSharedSuccessfulyEvent: LiveData<Event<List<Task>>> =
        _showTaskSharedSuccessfulyEvent
    override suspend fun showTaskSharedSuccessfuly(data: List<Task>) {
        _showTaskSharedSuccessfulyEvent.postValue(Event(data))
    }

    private val _showTaskSharedEvent = MutableLiveData<Event<List<Task>>>()
    override val showTaskSharedEvent: LiveData<Event<List<Task>>> =
        _showTaskSharedEvent
    override suspend fun showSharedTasks(tasks: List<Task>) {
        _showTaskSharedEvent.postValue(Event(tasks))
    }

    private val _showTaskSharedFavoriteEvent = MutableLiveData<Event<List<Task>>>()
    override val showTaskSharedFavoriteEvent: LiveData<Event<List<Task>>> =
        _showTaskSharedFavoriteEvent
    override suspend fun showSharedFavoriteTasks(data: List<Task>){
        _showTaskSharedFavoriteEvent.postValue(Event(data))
    }


    private val _deleteTask = MutableLiveData<Event<Unit>>()
    override val deleteTask: LiveData<Event<Unit>> = _deleteTask
    override suspend fun taskDeletedSuccesfully() {
        _deleteTask.postValue(Event(Unit))
    }

    private val _notifyUserEvent = MutableLiveData<Event<String>>()
    override val notifyUserEvent: LiveData<Event<String>> = _notifyUserEvent
    override suspend fun notifyUser(s: String) {
        _notifyUserEvent.postValue(Event(s))
    }
}