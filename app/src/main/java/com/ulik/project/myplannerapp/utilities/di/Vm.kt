package com.ulik.project.myplannerapp.utilities.di

import com.ulik.project.myplannerapp.data.TaskRepoImplement
import com.ulik.project.myplannerapp.data.TaskRepository
import com.ulik.project.myplannerapp.domain.MainUseCase
import com.ulik.project.myplannerapp.presenter.TaskPresenterState
import com.ulik.project.myplannerapp.presenter.TasksPresneterImpl
import com.ulik.project.myplannerapp.presenter.TasksPresnter
import com.ulik.project.myplannerapp.ui.TasksViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {
    viewModel {
        TasksViewModel(
            useCase = get(),
            tasksPresenterState = get()
        )
    }
}

val domainModule = module {
    single {
        MainUseCase(
            taskRepository = get(),
            tasksPresenter = get()
        )
    }
}

val repoModule = module {
    single<TaskRepository> {
        TaskRepoImplement()
    }
}

val presenterModule = module{
    single<TasksPresnter>{
        TasksPresneterImpl()
    }
    single {
        get<TasksPresnter>() as TaskPresenterState
    }
}

