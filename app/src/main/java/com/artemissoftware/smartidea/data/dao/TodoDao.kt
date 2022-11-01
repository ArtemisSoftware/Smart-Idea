package com.artemissoftware.smartidea.data.dao

import androidx.room.Dao
import androidx.room.*
import com.artemissoftware.smartidea.data.entities.TodoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: TodoEntity)

    @Delete
    suspend fun deleteTodo(todo: TodoEntity)

    @Query("SELECT * FROM todoentity WHERE id = :id")
    suspend fun getTodoById(id: Int): TodoEntity?

    @Query("SELECT * FROM todoentity")
    fun getTodos(): Flow<List<TodoEntity>>
}