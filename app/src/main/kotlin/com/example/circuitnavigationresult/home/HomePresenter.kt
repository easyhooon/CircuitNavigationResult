package com.example.circuitnavigationresult.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.circuitnavigationresult.input.InputScreen
import com.example.circuitnavigationresult.second.SecondScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.foundation.rememberAnsweringNavigator
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent

class HomePresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
) : Presenter<HomeScreen.State> {

    @Composable
    override fun present(): HomeScreen.State {
        var text by remember { mutableStateOf("") }
        val inputNavigator = rememberAnsweringNavigator<InputScreen.Result>(navigator) { result ->
            text = result.text
        }

        return HomeScreen.State(
            text = text,
        ) { event ->
            when (event) {
                is HomeScreen.Event.NavigateToInput -> inputNavigator.goTo(InputScreen)
                is HomeScreen.Event.NavigateToSecondActivity -> navigator.goTo(SecondScreen("second"))
            }
        }
    }

    @CircuitInject(HomeScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    fun interface Factory {
        fun create(navigator: Navigator): HomePresenter
    }
}
