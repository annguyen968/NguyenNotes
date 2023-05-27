package com.example.nguyennotes.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nguyennotes.navigation.BottomNav
import com.example.nguyennotes.viewmodel.NoteViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: NoteViewModel = NoteViewModel(),

) {
    val scaffoldState = rememberScaffoldState()
    var isLoading by remember { mutableStateOf(false) }
    var isListVisible by remember { mutableStateOf(false) }
    val txtTitle = remember { mutableStateOf(TextFieldValue()) }
    val txtDes = remember { mutableStateOf(TextFieldValue()) }
    val scope = rememberCoroutineScope()
    val listNote by viewModel.listNote.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),

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
                        viewModel.addNoteToDatabase(
                            txtTitle.value.text,
                            txtDes.value.text,
                            "username"
                        )
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
                    viewModel.clearDataList()
                    if (!isListVisible) {
                        viewModel.getNoteFromDatabase("username")
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
                items(listNote) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .border(
                                BorderStroke(
                                    2.dp,
                                    Color.Blue
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

