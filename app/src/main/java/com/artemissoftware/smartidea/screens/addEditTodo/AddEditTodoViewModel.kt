package com.artemissoftware.smartidea.screens.addEditTodo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.smartidea.data.database.entities.TodoEntity
import com.artemissoftware.smartidea.domain.UiEvent
import com.artemissoftware.smartidea.domain.repositories.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val repository: TodoRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var todo by mutableStateOf<TodoEntity?>(null)
        private set

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val todoId = savedStateHandle.get<Int>("todoId")!!
        if(todoId != -1) {
            getTodo(todoId)
        }
    }



    fun onEvent(event: AddEditTodoEvent) {
        when(event) {
            is AddEditTodoEvent.OnTitleChange -> {
                title = event.title
            }
            is AddEditTodoEvent.OnDescriptionChange -> {
                description = event.description
            }
            is AddEditTodoEvent.OnSaveTodoClick -> {
                saveTodo()
            }
        }
    }

    private fun saveTodo() {
        viewModelScope.launch {
            if (title.isBlank()) {
                sendUiEvent(
                    UiEvent.ShowSnackbar(
                        message = "The title can't be empty"
                    )
                )
                return@launch
            }
            repository.insertTodo(
                TodoEntity(
                    title = title,
                    description = description,
                    isDone = todo?.isDone ?: false,
                    id = todo?.id
                )
            )
            sendUiEvent(UiEvent.PopBackStack)
        }
    }


    private fun getTodo(todoId: Int) {
        viewModelScope.launch {
            repository.getTodoById(todoId)?.let { todo ->
                title = todo.title
                description = todo.description ?: ""
                this@AddEditTodoViewModel.todo = todo
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}