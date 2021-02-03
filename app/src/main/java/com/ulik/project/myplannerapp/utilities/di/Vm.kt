package com.ulik.project.myplannerapp.utilities.di

import com.ulik.project.myplannerapp.data.TaskRepoDbIml
import com.ulik.project.myplannerapp.data.TaskRepository
import com.ulik.project.myplannerapp.data.localDataSource.DataBase
import com.ulik.project.myplannerapp.domain.MainUseCase
import com.ulik.project.myplannerapp.presenter.TaskPresenterState
import com.ulik.project.myplannerapp.presenter.TasksPresneterImpl
import com.ulik.project.myplannerapp.presenter.TasksPresenter
import com.ulik.project.myplannerapp.ui.TasksViewModel
import org.koin.android.ext.koin.androidContext
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
        TaskRepoDbIml(dao = get())
    }
}

val presenterModule = module{
    single<TasksPresenter>{
        TasksPresneterImpl()
    }
    single {
        get<TasksPresenter>() as TaskPresenterState
    }
}

val dao = module {
    single {
        get<DataBase>().taskDao()
    }
    single<DataBase> {
        DataBase.getInstance(androidContext())
    }
}

