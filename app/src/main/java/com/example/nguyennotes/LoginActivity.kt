package com.example.nguyennotes

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.nguyennotes.ui.theme.NguyenNotesTheme
import com.example.nguyennotes.ui.theme.Primary
import kotlinx.coroutines.launch

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NguyenNotesTheme {
                LoginScreen()
            }
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun LoginScreen(){
        val scaffoldState = rememberScaffoldState()
        var txtUsername by remember { mutableStateOf("") }
        val scope = rememberCoroutineScope()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Login",
                            style = MaterialTheme.typography.body1,
                    ) },

                    backgroundColor = Primary
                )
            },
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp)
            ) {
                TextField(
                    value = txtUsername,
                    onValueChange = {
                        txtUsername = it
                    },
                    label = { Text(text = "Enter your name") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {
                    if (txtUsername.trim().isEmpty()) {
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                "Please enter your name", duration = SnackbarDuration.Short
                            )
                        }
                    } else {
                        startActivity(MainActivity.intentFor(this@LoginActivity, txtUsername))
                        finish()
                    }
                }) {
                    Text(text = "Let notes begin")
                }
            }
        }
    }
}