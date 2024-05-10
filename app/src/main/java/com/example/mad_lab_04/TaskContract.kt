package com.example.mad_lab_04
import android.provider.BaseColumns

object TaskContract {
    object TaskEntry : BaseColumns {
        const val TABLE_NAME = "tasks"
        const val COLUMN_NAME = "name"
        const val COLUMN_DESC = "description"
        const val COLUMN_DUE_TIME = "due_time"
        const val COLUMN_COMP_DATE = "completion_date"
        const val COLUMN_ID = "id"
    }
}
