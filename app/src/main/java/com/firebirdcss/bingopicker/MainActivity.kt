package com.firebirdcss.bingopicker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.firebirdcss.bingopicker.ui.screen.StartScreen
import com.firebirdcss.bingopicker.ui.theme.AppTheme

/**
 * MAIN_ACTIVITY: This is the main activity of the app.
 * This is where the app begins its execution.
 */
class MainActivity : ComponentActivity() {
    /**
     * OVERRIDE: This function is an override of the one
     * provided by the extending of the ComponentActivity class.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StartScreen()
                }
            }
        }
    }
}