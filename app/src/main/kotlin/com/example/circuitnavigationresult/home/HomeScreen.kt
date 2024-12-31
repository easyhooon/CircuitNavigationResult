package com.example.circuitnavigationresult.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.circuitnavigationresult.overlay.InputBottomSheetOverlay
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.overlay.LocalOverlayHost
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize

@Parcelize
data object HomeScreen : Screen {
    data class State(
        val text: String = "",
        val eventSink: (Event) -> Unit,
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent {
        data object NavigateToInput : Event
        data object NavigateToSecondActivity : Event
        data class UpdateTextByOverlay(val text: String) : Event
    }
}

@CircuitInject(HomeScreen::class, ActivityRetainedComponent::class)
@Composable
fun Home(
    state: HomeScreen.State,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()
    val overlay = LocalOverlayHost.current

    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        if (state.text.isNotEmpty()) {
            Text(text = state.text)
        }
        Button(
            onClick = {
                state.eventSink(HomeScreen.Event.NavigateToInput)
            },
        ) {
            Text(text = "Go to input")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                state.eventSink(HomeScreen.Event.NavigateToSecondActivity)
            },
        ) {
            Text(text = "Go to second activity")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                scope.launch {
                    val result = overlay.show(InputBottomSheetOverlay())
                    state.eventSink(HomeScreen.Event.UpdateTextByOverlay(result))
                }
            },
        ) {
            Text(text = "Show input bottomSheet")
        }
    }
}
