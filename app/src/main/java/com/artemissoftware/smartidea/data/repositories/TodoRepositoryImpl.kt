package com.artemissoftware.smartidea.data.repositories

import com.artemissoftware.smartidea.data.database.dao.TodoDao
import com.artemissoftware.smartidea.data.database.entities.TodoEntity
import com.artemissoftware.smartidea.domain.repositories.TodoRepository
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl (
    private val dao: TodoDao
): TodoRepository {

    override suspend fun insertTodo(todo: TodoEntity) {
        dao.insertTodo(todo)
    }

    override suspend fun deleteTodo(todo: TodoEntity) {
        dao.deleteTodo(todo)
    }

    override suspend fun getTodoById(id: Int): TodoEntity? {
        return dao.getTodoById(id)
    }

    override fun getTodos(): Flow<List<TodoEntity>> {
        return dao.getTodos()
    }
}