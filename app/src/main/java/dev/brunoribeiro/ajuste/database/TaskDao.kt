package dev.brunoribeiro.ajuste.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import dev.brunoribeiro.ajuste.entities.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM actual_tasks")
    fun getAll(): List<Task>

    @Insert
    fun insert(task: Task)

    @Delete
    fun delete(task: Task)
}