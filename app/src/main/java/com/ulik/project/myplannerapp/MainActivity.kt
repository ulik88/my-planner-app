package com.ulik.project.myplannerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
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









//        val host: NavHostFragment = supportFragmentManager
//            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return

        // Set up Action Bar
//        val navController = host.navController
//        setupBottomNavMenu(navController)
//    }
//    private fun setupBottomNavMenu(navController: NavController) {
////        // TODO STEP 9.3 - Use NavigationUI to set up Bottom Nav
//        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
//        bottomNav?.setupWithNavController(navController)
//        // TODO END STEP 9.3

        val fragmentTasks = TasksFragment()
        val fragmentSharedTask = SharedTaskFragment()
        val fragmentFavorite = FavoriteFragment()

        setCurrentFragment(fragmentTasks)

        bottom_nav_view.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.homeFragment -> setCurrentFragment(fragmentTasks)
                R.id.favFragment -> setCurrentFragment(fragmentFavorite)
                R.id.groupFragment -> setCurrentFragment(fragmentSharedTask)
            }
           true
        }

        bottom_nav_view.getOrCreateBadge(R.id.favFragment).apply {
            number = 3
            isVisible = true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction(). apply {
            replace(R.id.nav_host_fragment, fragment)
                .commit()
    }
}