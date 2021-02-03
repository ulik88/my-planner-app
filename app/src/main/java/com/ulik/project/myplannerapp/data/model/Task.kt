package com.ulik.project.myplannerapp.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity
@Parcelize
data class Task(
    var title: String = "",
    var description:String = "",
    var isCompleted: Boolean = false,
    var isFavorite:Boolean = false,
    var createdDate: Date = Date(),
    var isShared: Boolean = false,

    @PrimaryKey
    var id: String = UUID.randomUUID().toString()

) : Parcelable