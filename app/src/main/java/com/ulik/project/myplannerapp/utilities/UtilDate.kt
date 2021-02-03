package com.ulik.project.myplannerapp.utilities

import com.ulik.project.myplannerapp.data.model.Task
import java.text.SimpleDateFormat

fun formatDate(task: Task): String {
    val pattern = "MM-dd-yyyy"
    val simpleDateFormat = SimpleDateFormat(pattern)
    val date: String = simpleDateFormat.format(task.createdDate)
    return date
}