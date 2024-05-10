package com.example.mad_lab_04

import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

class TaskItem {
    lateinit var name:String
    lateinit var desc:String
    lateinit var dueTime:LocalTime
    lateinit var compDate:LocalDate
    var id:UUID = UUID.randomUUID()
}