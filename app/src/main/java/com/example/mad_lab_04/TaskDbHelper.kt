package com.example.mad_lab_04
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

class TaskDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val SQL_CREATE_TASKS_TABLE = """
            CREATE TABLE ${TaskContract.TaskEntry.TABLE_NAME} (
                ${TaskContract.TaskEntry.COLUMN_ID} TEXT PRIMARY KEY,
                ${TaskContract.TaskEntry.COLUMN_NAME} TEXT,
                ${TaskContract.TaskEntry.COLUMN_DESC} TEXT,
                ${TaskContract.TaskEntry.COLUMN_DUE_TIME} TEXT,
                ${TaskContract.TaskEntry.COLUMN_COMP_DATE} TEXT
            )
        """.trimIndent()
        db.execSQL(SQL_CREATE_TASKS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Handle database upgrades
    }

    fun insertTask(taskItem: TaskItem) {
        val db = writableDatabase

        val values = ContentValues().apply {
            put(TaskContract.TaskEntry.COLUMN_ID, taskItem.id.toString())
            put(TaskContract.TaskEntry.COLUMN_NAME, taskItem.name)
            put(TaskContract.TaskEntry.COLUMN_DESC, taskItem.desc)
            put(TaskContract.TaskEntry.COLUMN_DUE_TIME, taskItem.dueTime.toString())
            put(TaskContract.TaskEntry.COLUMN_COMP_DATE, taskItem.compDate.toString())
        }

        db.insert(TaskContract.TaskEntry.TABLE_NAME, null, values)
    }

    fun getAllTasks(): List<TaskItem> {
        val tasks = mutableListOf<TaskItem>()
        val db = readableDatabase

        val projection = arrayOf(
            TaskContract.TaskEntry.COLUMN_ID,
            TaskContract.TaskEntry.COLUMN_NAME,
            TaskContract.TaskEntry.COLUMN_DESC,
            TaskContract.TaskEntry.COLUMN_DUE_TIME,
            TaskContract.TaskEntry.COLUMN_COMP_DATE
        )

        val cursor: Cursor = db.query(
            TaskContract.TaskEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor) {
            while (moveToNext()) {
                val taskId = getString(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_ID))
                val name = getString(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_NAME))
                val desc = getString(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_DESC))
                val dueTime = LocalTime.parse(getString(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_DUE_TIME)))
                val compDate = LocalDate.parse(getString(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_COMP_DATE)))

                val taskItem = TaskItem()
                taskItem.id = UUID.fromString(taskId)
                taskItem.name = name
                taskItem.desc = desc
                taskItem.dueTime = dueTime
                taskItem.compDate = compDate

                tasks.add(taskItem)
            }
        }

        return tasks
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "tasks.db"
    }
}

