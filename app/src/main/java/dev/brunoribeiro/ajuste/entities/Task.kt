package dev.brunoribeiro.ajuste.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "actual_tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)val room_id: Int? = null,
    @ColumnInfo(name = "title")val title: String?
)
