package com.ulik.project.myplannerapp.data.model

import java.util.*

class Task(
    var title: String,
    var description:String,
    var isCompleted: Boolean = false,
    var isFavorite:Boolean = false,
    var id: String = UUID.randomUUID().toString()

)