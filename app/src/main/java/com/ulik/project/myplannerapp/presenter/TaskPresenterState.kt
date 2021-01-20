package com.ulik.project.myplannerapp.presenter

import androidx.lifecycle.LiveData
import com.ulik.project.myplannerapp.data.model.Task

interface TaskPresenterState {
    val loadingEvent: LiveData<Boolean>
    val showErrorEvent: LiveData<String>
    val shoTaskSaveSuccesfuly: LiveData<List<Task>>
}