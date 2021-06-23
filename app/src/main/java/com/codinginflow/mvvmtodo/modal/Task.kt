package com.codinginflow.mvvmtodo.modal

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat


@Entity(tableName = "task_table")
@Parcelize
data class Task (
    val title : String,
    val completed : Boolean,
    val important : Boolean,
    val description : String,
    val created : Long = System.currentTimeMillis(),

    @PrimaryKey(autoGenerate = true)
        val id : int = 0
        ) : Parcelable{

    val createdDateFormatted : String
    get() = DateFormat.getDateInstance().format(created)
}