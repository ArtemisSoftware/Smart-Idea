package com.artemissoftware.smartidea.screens.todolist.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artemissoftware.smartidea.data.database.entities.TodoEntity
import com.artemissoftware.smartidea.screens.todolist.TodoListEvent
import com.artemissoftware.smartidea.ui.theme.TextNewRodinFont

@Composable
fun TodoItem (
    todo: TodoEntity,
    onEvent: (TodoListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    style = TextNewRodinFont,
                    text = todo.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = {
                    onEvent(TodoListEvent.OnDeleteTodoClick(todo))
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete"
                    )
                }
            }
            todo.description?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(style = TextNewRodinFont, text = it)
            }
        }
        Checkbox(
            checked = todo.isDone,
            onCheckedChange = { isChecked ->
                onEvent(TodoListEvent.OnDoneChange(todo, isChecked))
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    TodoItem (
        todo = TodoEntity(title = "Title", description = "description", isDone = true),
        onEvent = {},
        modifier = Modifier
    )
}