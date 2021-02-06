package com.ulik.project.myplannerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ulik.project.myplannerapp.ui.FavoriteFragment
import com.ulik.project.myplannerapp.ui.SharedTaskFragment
import com.ulik.project.myplannerapp.ui.TasksFragment
import com.ulik.project.myplannerapp.ui.TasksViewModel
import kotlinx.android.synthetic.main.content_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    val tasksViewModel: TasksViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)



        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.tasksFragment,
                R.id.sharedTaskFragment,
                R.id.favoriteFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottom_nav_view.setupWithNavController(navController)
    }
}