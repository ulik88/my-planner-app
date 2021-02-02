package com.ulik.project.myplannerapp.data.localDataSource

import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.Room
import androidx.room.Database
import com.ulik.project.myplannerapp.data.model.Task
//import com.ulik.project.myplannerapp.utilities.Converters

@Database(
    entities =  [
        Task::class
    ],
    version = 1,
)

abstract class DataBase : RoomDatabase() {
    abstract fun taskDao(): TasksDao

    companion object{
        private var INSTANCE: DataBase? = null

        private val lock = Any()

        fun getInstance(context: Context): DataBase {
            synchronized(lock) {
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DataBase::class.java, "DataBase.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return INSTANCE!!
            }
        }
    }
}
