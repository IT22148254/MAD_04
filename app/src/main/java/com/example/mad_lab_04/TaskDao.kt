package com.example.mad_lab_04
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {

    @Insert
    fun insert(task: Task)

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): List<Task>
}

