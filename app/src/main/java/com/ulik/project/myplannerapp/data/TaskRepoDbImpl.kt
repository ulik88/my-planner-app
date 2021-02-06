package com.ulik.project.myplannerapp.data

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ulik.project.myplannerapp.data.localDataSource.TasksDao
import com.ulik.project.myplannerapp.data.model.Task
import com.ulik.project.myplannerapp.utilities.Result
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class TaskRepoDbImpl(val dao: TasksDao) : TaskRepository {
    private val TAG: String = "TaskRepoDbImpl"
    private val db = Firebase.firestore

    override suspend fun getTasks(): Result<List<Task>> {
        return Result.Success(dao.getTasks())
    }

    override suspend fun saveTask(task: Task): Result<List<Task>> {
        dao.saveTask(task)
        return Result.Success(dao.getTasks())
    }

    override suspend fun updateTask(task: Task): Result<List<Task>> {
        dao.updateTask(task)
        return Result.Success(dao.getTasks())
    }

    override suspend fun deleteTask(task: Task): Result<Unit> {
        return Result.Success(dao.deleteTask(task))
    }

    override suspend fun shareTaskToGroup(task: Task): Result<List<Task>> {

        lateinit var result: Result<List<Task>>

        db.collection("cities").document(task.id)
            .set(task)
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot successfully written!")
                result = Result.Success(emptyList())

            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error writing document", e)
                result = Result.Error(Exception())
            }
            .await()

        if (result is Result.Success){
            updateTask(task)
            result = getTasks()
        }

        return result
    }

    override suspend fun getSharedTasks(): Result<List<Task>> {
        lateinit var result: Result<List<Task>>

        db.collection("cities").get()
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot successfully read!")
                result = Result.Success(it.toObjects(Task::class.java))

            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error reading document", e)
                result = Result.Error(Exception())
            }
            .await()
        return result
    }

}