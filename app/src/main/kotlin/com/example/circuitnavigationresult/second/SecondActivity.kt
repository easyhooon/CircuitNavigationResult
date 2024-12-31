package com.example.circuitnavigationresult.second

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.circuitnavigationresult.ui.theme.CircuitNavigationResultTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondActivity : ComponentActivity() {
    companion object {
        const val KEY_TEXT: String = "text"
    }

    private val initialText by lazy { intent.getStringExtra(KEY_TEXT)!! }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CircuitNavigationResultTheme {
                val navController = rememberNavController()
                Scaffold { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                    ) {
                        composable("home") { entry ->
                            val text = entry.savedStateHandle.get<String>("text") ?: initialText
                            Column(
                                modifier = Modifier.fillMaxSize(),
                            ) {
                                Text(text = text)
                                Button(
                                    onClick = {
                                        navController.navigate("input")
                                    },
                                ) {
                                    Text(text = "Go to input")
                                }
                            }
                        }
                        composable("input") {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                            ) {
                                var text by remember { mutableStateOf(initialText) }
                                OutlinedTextField(
                                    value = text,
                                    onValueChange = { text = it },
                                    modifier = Modifier.width(300.dp),
                                )
                                Button(
                                    onClick = {
                                        navController.previousBackStackEntry
                                            ?.savedStateHandle
                                            ?.set("text", text)
                                        navController.popBackStack()
                                    },
                                ) {
                                    Text(text = "Apply")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
