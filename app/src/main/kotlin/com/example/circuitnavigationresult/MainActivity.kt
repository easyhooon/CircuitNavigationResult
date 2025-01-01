package com.example.circuitnavigationresult

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import com.example.circuitnavigationresult.home.HomeScreen
import com.example.circuitnavigationresult.second.SecondScreen
import com.example.circuitnavigationresult.ui.theme.CircuitNavigationResultTheme
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.slack.circuit.overlay.ContentWithOverlays
import com.slack.circuitx.android.rememberAndroidScreenAwareNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var circuit: Circuit

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CircuitNavigationResultTheme {
                setContent {
                    CircuitNavigationResultTheme {
                        val backStack = rememberSaveableBackStack(root = HomeScreen)
                        val navigator = rememberAndroidScreenAwareNavigator(
                            delegate = rememberCircuitNavigator(backStack),
                            starter = SecondScreen.buildAndroidStarter(this),
                        )

                        CircuitCompositionLocals(circuit) {
                            Scaffold(
                                topBar = {
                                     TopAppBar(
                                         title = { Text(text = "MainActivity") },
                                     )
                                },
                            ) { innerPadding ->
                                ContentWithOverlays {
                                    NavigableCircuitContent(
                                        navigator = navigator,
                                        backStack = backStack,
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(innerPadding),
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
