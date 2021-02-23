package dev.brunoribeiro.ajuste.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "actual_tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id")val room_id: Int? = null,
    @ColumnInfo(name = "title")val title: String?,
    @ColumnInfo(name = "type")val type: Int?,
    @ColumnInfo(name = "check_value")var checked: Boolean? = false
){
    companion object{
        val URGENTE = 1
        val INTERMEDIARIO = 2
        val NORMAL = 3
    }
}
