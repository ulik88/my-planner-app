package com.ulik.project.myplannerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ulik.project.myplannerapp.ui.TasksViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    val tasksViewModel: TasksViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)
    }
}