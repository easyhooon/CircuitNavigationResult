package com.example.circuitnavigationresult.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.parcelize.Parcelize

@Parcelize
data object HomeScreen : Screen {
    data class State(
        val text: String = "",
        val eventSink: (Event) -> Unit,
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent {
        data object NavigateToInput : Event
    }
}

@CircuitInject(HomeScreen::class, ActivityRetainedComponent::class)
@Composable
fun Home(
    state: HomeScreen.State,
    modifier: Modifier = Modifier,
) {
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
    }
}
