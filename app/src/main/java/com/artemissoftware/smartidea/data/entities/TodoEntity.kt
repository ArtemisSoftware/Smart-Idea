package com.artemissoftware.smartidea.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoEntity(
    val title: String,
    val description: String?,
    val isDone: Boolean,
    @PrimaryKey val id: Int? = null
)