package com.example.circuitnavigationresult.input

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent

class InputPresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
) : Presenter<InputScreen.State> {

    @Composable
    override fun present(): InputScreen.State {
        var text by remember { mutableStateOf("") }
        return InputScreen.State(
            text = text,
        ) { event ->
            when (event) {
                is InputScreen.Event.OnTextChanged -> {
                    text = event.text
                }
                is InputScreen.Event.OnApplyClicked -> {
                    navigator.pop(InputScreen.Result(text))
                }
            }
        }
    }

    @CircuitInject(InputScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    fun interface Factory {
        fun create(navigator: Navigator): InputPresenter
    }
}
