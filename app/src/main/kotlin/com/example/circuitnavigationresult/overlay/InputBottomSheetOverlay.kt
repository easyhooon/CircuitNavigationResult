package com.example.circuitnavigationresult.overlay

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.slack.circuit.overlay.Overlay
import com.slack.circuitx.overlays.BottomSheetOverlay

@Suppress("FunctionName")
@OptIn(ExperimentalMaterial3Api::class)
fun InputBottomSheetOverlay(): Overlay<String> =
    BottomSheetOverlay(
        model = Unit,
        skipPartiallyExpandedState = true,
        onDismiss = { "" },
        content = { model, navigator ->
            var text by remember { mutableStateOf("") }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 48.dp),
            ) {
                OutlinedTextField(
                    value = text,
                    onValueChange = {
                        text = it
                    },
                    modifier = Modifier.width(300.dp),
                )
                Button(
                    onClick = {
                        navigator.finish(text)
                    },
                ) {
                    Text(text = "Apply")
                }
            }
        },
    )
