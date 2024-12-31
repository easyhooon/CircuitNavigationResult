package com.example.circuitnavigationresult.second

import android.content.Context
import android.content.Intent
import androidx.annotation.UiContext
import com.slack.circuitx.android.AndroidScreen
import com.slack.circuitx.android.AndroidScreenStarter
import kotlinx.parcelize.Parcelize

@Parcelize
@JvmInline
value class SecondScreen(val text: String) : AndroidScreen {
    companion object {
        fun buildAndroidStarter(@UiContext context: Context): AndroidScreenStarter =
            AndroidScreenStarter { screen ->
                if (screen is SecondScreen) {
                    val intent = Intent(context, SecondActivity::class.java)
                    context.startActivity(intent.putExtra(SecondActivity.KEY_TEXT, screen.text))
                    true
                } else {
                    false
                }
            }
    }
}
