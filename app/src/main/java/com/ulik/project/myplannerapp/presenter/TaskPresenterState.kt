package com.ulik.project.myplannerapp.presenter

import androidx.lifecycle.LiveData
import com.ulik.project.myplannerapp.data.model.Task
import com.ulik.project.myplannerapp.utilities.Event

interface TaskPresenterState {
    val loadingEvent: LiveData<Event<Boolean>>
    val showErrorEvent: LiveData<Event<String>>
    val showTaskSaveSuccesfuly:LiveData<Event<List<Task>>>
    val updateAdapter:LiveData<Event<List<Task>>>
    val navigateToTasksFragment:LiveData<Event<List<Task>>>
    val deleteTask: LiveData<Event<Unit>>
    val showLoadedTaskEvent: LiveData<Event<List<Task>>>
    val showTaskSharedSuccessfulyEvent: LiveData<Event<List<Task>>>
    val showTaskSharedEvent: LiveData<Event<List<Task>>>
}