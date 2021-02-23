package dev.brunoribeiro.ajuste.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Delete
import dev.brunoribeiro.ajuste.database.AppDatabase
import dev.brunoribeiro.ajuste.entities.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class ServiceRepository {
    abstract val database: AppDatabase



    private val _allTasks = MutableLiveData<MutableList<Task>>()
    val allTasks: LiveData<MutableList<Task>>
        get() = _allTasks

    suspend fun getTasks() {
        withContext(Dispatchers.IO) {
            _allTasks.postValue(database.TaskDatabaseDao.getAll())
        }
    }

    suspend fun insertTask(task: Task) {
        withContext(Dispatchers.IO) {
            database.TaskDatabaseDao.insert(task)
        }
    }

    suspend fun deleteTask(task: Task) {
        withContext(Dispatchers.IO) {
            database.TaskDatabaseDao.delete(task)
        }
    }

    suspend fun updateTask(check: Boolean, task: Task) {
        withContext(Dispatchers.IO) {
            database.TaskDatabaseDao.update(check, task.room_id!!)
        }
    }





    companion object {
        private var INSTANCE: ServiceRepository? = null

        fun getInstance(context: Context): ServiceRepository {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = object : ServiceRepository() {
                        override val database: AppDatabase
                            get() = AppDatabase.getInstance(context)
                    }
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}