package com.example.circuitnavigationresult.input

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.PopResult
import com.slack.circuit.runtime.screen.Screen
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.parcelize.Parcelize

@Parcelize
data object InputScreen : Screen {
    data class State(
        val text: String = "",
        val eventSink: (Event) -> Unit,
    ) : CircuitUiState

    @Parcelize
    data class Result(val text: String) : PopResult

    sealed interface Event : CircuitUiEvent {
        data class OnTextChanged(val text: String) : Event
        data object OnApplyClicked : Event
    }
}

@CircuitInject(InputScreen::class, ActivityRetainedComponent::class)
@Composable
fun Input(
    state: InputScreen.State,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        OutlinedTextField(
            value = state.text,
            onValueChange = {
                state.eventSink(InputScreen.Event.OnTextChanged(it))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        )
        Button(
            onClick = {
                state.eventSink(InputScreen.Event.OnApplyClicked)
            },
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp),
        ) {
            Text(text = "Apply")
        }
    }
}
