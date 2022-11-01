package com.artemissoftware.smartidea.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.artemissoftware.smartidea.data.database.dao.TodoDao
import com.artemissoftware.smartidea.data.database.entities.TodoEntity

@Database(
    entities = [TodoEntity::class],
    version = 1
)
abstract class TodoDatabase: RoomDatabase() {

    abstract val dao: TodoDao
}