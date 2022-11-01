package com.artemissoftware.smartidea.screens.todolist

import androidx.lifecycle.ViewModel
import com.artemissoftware.smartidea.domain.repositories.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.artemissoftware.smartidea.data.database.entities.TodoEntity
import com.artemissoftware.smartidea.domain.UiEvent
import com.artemissoftware.smartidea.navigation.Routes
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: TodoRepository
): ViewModel() {

    val todos = repository.getTodos()

    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedTodo: TodoEntity? = null

    fun onEvent(event: TodoListEvent) {

        when(event) {
            is TodoListEvent.OnTodoClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO + "?todoId=${event.todo.id}"))
            }
            is TodoListEvent.OnAddTodoClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO))
            }
            is TodoListEvent.OnUndoDeleteClick -> {
                undoDeleteTodo()
            }
            is TodoListEvent.OnDeleteTodoClick -> {
                removeTodo(event)
            }
            is TodoListEvent.OnDoneChange -> {
                insertDoneTodo(event)
            }
        }
    }

    private fun undoDeleteTodo() {
        deletedTodo?.let { todo ->
            viewModelScope.launch {
                repository.insertTodo(todo)
            }
        }
    }

    private fun insertDoneTodo(event: TodoListEvent.OnDoneChange) {
        viewModelScope.launch {
            repository.insertTodo(
                event.todo.copy(
                    isDone = event.isDone
                )
            )
        }
    }

    private fun removeTodo(event: TodoListEvent.OnDeleteTodoClick) {
        viewModelScope.launch {
            deletedTodo = event.todo
            repository.deleteTodo(event.todo)
            sendUiEvent(
                UiEvent.ShowSnackbar(
                    message = "Todo deleted",
                    action = "Undo"
                )
            )
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}