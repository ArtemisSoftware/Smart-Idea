package com.artemissoftware.smartidea.domain.repositories

import com.artemissoftware.smartidea.data.database.entities.TodoEntity
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    suspend fun insertTodo(todo: TodoEntity)

    suspend fun deleteTodo(todo: TodoEntity)

    suspend fun getTodoById(id: Int): TodoEntity?

    fun getTodos(): Flow<List<TodoEntity>>
}