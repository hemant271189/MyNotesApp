package com.genai.mynotesapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.genai.mynotesapp.data.room_database.TaskItem
import com.genai.mynotesapp.ui.theme.darkGrey
import com.genai.mynotesapp.ui.theme.grey


@Composable
fun ToDoItem(
    item: TaskItem,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onCheckChanged: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (item.isDone) Color.LightGray else Color.White
        ),
        border = BorderStroke(1.dp, grey)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { onCheckChanged(!item.isDone) },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = if (item.isDone)
                        Icons.Default.CheckCircle
                    else
                        Icons.Default.RadioButtonUnchecked,
                    contentDescription = if (item.isDone) "Mark incomplete" else "Mark complete",
                    tint = if (item.isDone) darkGrey.copy(0.5f) else grey
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = item.taskName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = if (item.isDone) grey else darkGrey,
                textDecoration = if (item.isDone) TextDecoration.LineThrough else null,
                modifier = Modifier.weight(1f)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onEditClick) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Task",
                        tint = grey,
                        modifier = Modifier.size(24.dp)
                    )
                }

                IconButton(onClick = onDeleteClick) {
                    Icon(
                        imageVector = Icons.Default.DeleteOutline,
                        contentDescription = "Delete Task",
                        tint = grey,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ToDoItemPreview() {
    ToDoItem(
        item = TaskItem(id = 1, taskName = "Sample Task", isDone = false),
        onEditClick = {},
        onDeleteClick = {},
        onCheckChanged = {}
    )
}

