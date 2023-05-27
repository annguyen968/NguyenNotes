package com.example.nguyennotes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.nguyennotes.model.Note
import com.example.nguyennotes.navigation.SetupNavGraph
import com.example.nguyennotes.ui.theme.NguyenNotesTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

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
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        username = intent.getStringExtra("USER_NAME") ?: ""

        setContent {
            NguyenNotesTheme() {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }


}
