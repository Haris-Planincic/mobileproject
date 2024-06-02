package com.example.accountamate.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.accountamate.NewNoteDialog
import com.example.accountamate.Note
import com.example.accountamate.NotesList
import com.example.accountamate.database.DBHelper
import com.example.accountamate.ui.theme.Green400

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Notes() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red),
        contentAlignment = Alignment.Center
    ) {

        val context = LocalContext.current
        val notesList = remember { mutableStateListOf<Note>() }
        val showDialog = remember { mutableStateOf(false) }
        val title = remember { mutableStateOf("") }
        val description = remember { mutableStateOf("") }
        val counter = remember { mutableStateOf(0) } // Counter state

        LaunchedEffect(Unit) {
            val dbHelper = DBHelper(context)
            val savedNotes = dbHelper.fetchAllNotes()
            notesList.addAll(savedNotes)
            counter.value = savedNotes.size // Initialize counter with the number of saved notes
        }

        Scaffold(
            topBar = { TopAppBar(title = { Text("My Notes") }) },
            content = {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 25.dp)
                ) {
                    Text(text = "List of Notes")
                    Spacer(modifier = Modifier.height(16.dp))
                    NotesList(notesList)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            title.value = ""
                            description.value = ""
                            showDialog.value = true
                        },
                        modifier = Modifier.align(Alignment.End),
                        colors = ButtonDefaults.buttonColors(Green400)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "+ Add New ")
                        Text(text = "Add New Note")
                    }
                }
            }
        )

        if (showDialog.value) {
            NewNoteDialog(
                title = title.value,
                onTitleChange = { title.value = it },
                description = description.value,
                onDescriptionChange = { description.value = it },
                onSaveClick = {
                    if (title.value.isNotBlank() && description.value.isNotBlank()) {
                        val newNote = Note(title.value, description.value)
                        notesList.add(newNote)
                        val dbHelper = DBHelper(context)
                        dbHelper.addNote(newNote)
                        counter.value++ // Increment counter
                        showDialog.value = false
                    }
                },
                onCancelClick = { showDialog.value = false }
            )
        }
    }
}

fun saveNoteToSharedPreferences(context: Context, note: Note) {
    val dbHelper = DBHelper(context)
    dbHelper.addNote(note)
}

@Composable
@Preview
fun NotesScreenPreview() {
    Notes()
}
