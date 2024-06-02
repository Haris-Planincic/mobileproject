package com.example.accountamate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.AlertDialog
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.accountamate.ui.theme.Green400
import com.example.accountamate.ui.theme.Green600
import com.example.accountamate.ui.theme.Red500

@Composable
fun NewNoteDialog(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCancelClick,
        title = { Text(text = "New Note") },
        text = {
            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = onTitleChange,
                    label = { Text(text = "Title") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Green600,
                        unfocusedBorderColor = Green400
                    )

                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = onDescriptionChange,
                    label = { Text(text = "Description") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Green600,
                        unfocusedBorderColor = Green400
                    )
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onSaveClick,colors = ButtonDefaults.textButtonColors(contentColor = Green400)) {
                Text(text = "Save")
                Icon(Icons.Default.Add, contentDescription = "SAVE")
            }
        },
        dismissButton = {
            TextButton(onClick = onCancelClick,colors = ButtonDefaults.textButtonColors(contentColor = Red500)) {
                Text(text = "Cancel")
                Icon(Icons.Default.Clear, contentDescription = "Cancel")

            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun NewNoteDialogPreview() {
    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }

    NewNoteDialog(
        title = title.value,
        onTitleChange = { title.value = it },
        description = description.value,
        onDescriptionChange = { description.value = it },
        onSaveClick = { /* Handle save */ },
        onCancelClick = { /* Handle cancel */ }
    )
}