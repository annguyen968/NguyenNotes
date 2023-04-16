package com.example.nguyennotes

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.google.firebase.FirebaseApp
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    companion object {
        fun intentFor(context: Context, username: String): Intent =
            Intent(context, MainActivity::class.java).apply {
                putExtra("USER_NAME", username)
            }
    }

    private var username: String = ""
    private var list = mutableListOf<Note>()
    private val database = Firebase.database.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        username = intent.getStringExtra("USER_NAME") ?: ""
        setContent {
            NoteScreen()
        }
    }

    private fun addNoteToDatabase(title: String, description: String, username: String) {
        database.child(username).push().setValue(Note(title, description))
    }

    private fun getNoteFromDatabase(username: String) {
        database.child(username).get().addOnSuccessListener {
            for (item in it.children) {
                val note = item.getValue(Note::class.java)
                if (note != null) {
                    list.add(note)
                }
            }
        }.addOnCanceledListener {
            println("Error")
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun NoteScreen() {
        val scaffoldState = rememberScaffoldState()
        var isLoading by remember { mutableStateOf(false) }
        var isListVisible by remember { mutableStateOf(false) }
        val txtTitle = remember { mutableStateOf(TextFieldValue()) }
        val txtDes = remember { mutableStateOf(TextFieldValue()) }
        val scope = rememberCoroutineScope()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = { Text(text = "Note") },
                    backgroundColor = MaterialTheme.colors.primary
                )
            }
        ) {
            if (isLoading) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                TextField(
                    value = txtTitle.value,
                    onValueChange = { txtTitle.value = it },
                    label = { Text(text = "Title") },
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = txtDes.value,
                    onValueChange = { txtDes.value = it },
                    label = { Text(text = "Description") },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        if (txtTitle.value.text.trim().isNotEmpty() && txtDes.value.text.trim()
                                .isNotEmpty()
                        ) {
                            isLoading = true
                            addNoteToDatabase(txtTitle.value.text, txtDes.value.text, username)
                            scope.launch {
                                delay(2000L)
                                txtTitle.value = TextFieldValue()
                                txtDes.value = TextFieldValue()
                                isLoading = false
                                scope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        "Add note successfully",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            }
                        } else {
                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    "Please enter title and description",
                                    duration = SnackbarDuration.Short
                                )
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Add note")
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        isLoading = true
                        clearDataList(list)
                        if (!isListVisible) {
                            getNoteFromDatabase(username)
                            scope.launch {
                                delay(3000L)
                                isLoading = false
                            }
                        } else {
                            isLoading = false
                        }
                        isListVisible = !isListVisible
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (isListVisible) {
                        Text("Hide all my notes")
                    } else {
                        Text("Show all my notes")
                    }
                }

                LazyColumn {
                    items(list) { item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .border(
                                    BorderStroke(
                                        2.dp,
                                        androidx.compose.ui.graphics.Color.Blue
                                    )
                                )
                        ) {
                            Column {
                                Text(
                                    text = item.title,
                                    modifier = Modifier.padding(16.dp),
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = item.description,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun clearDataList(list: MutableList<Note>) {
        if (list.isNotEmpty()) {
            list.clear()
        }
    }
}
