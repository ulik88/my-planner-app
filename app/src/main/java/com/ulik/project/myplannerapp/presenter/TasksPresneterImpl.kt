package com.ulik.project.myplannerapp.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ulik.project.myplannerapp.data.model.Task

class TasksPresneterImpl : TaskPresenterState,TasksPresnter{

    private val _loadingEvent = MutableLiveData<Boolean>()
    override val loadingEvent: LiveData<Boolean> = _loadingEvent
    override suspend fun showLoading(state: Boolean) {
        _loadingEvent.postValue(state)
    }

    private val _showErrorEvent = MutableLiveData<String>()
    override val showErrorEvent: LiveData<String> = _showErrorEvent
    override suspend fun showError(message: String) {
        _showErrorEvent.postValue(message)
    }

    private val _shoTaskSaveSuccesfuly = MutableLiveData<List<Task>>()
    override val shoTaskSaveSuccesfuly: LiveData<List<Task>> = _shoTaskSaveSuccesfuly
    override suspend fun showTaskSavedSuccessfuly(tasks:List<Task>) {
        _shoTaskSaveSuccesfuly.postValue(tasks)
    }
}