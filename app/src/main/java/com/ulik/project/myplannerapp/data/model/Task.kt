package com.ulik.project.myplannerapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*
@Parcelize
data class Task(
    var title: String,
    var description:String,
    var isCompleted: Boolean = false,
    var isFavorite:Boolean = false,
    var id: String = UUID.randomUUID().toString()

) : Parcelable