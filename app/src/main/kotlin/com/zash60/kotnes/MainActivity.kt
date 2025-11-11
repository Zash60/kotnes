package com.zash60.kotnes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.zash60.kotnes.ui.EmulatorView

class MainActivity : ComponentActivity() {
    private val viewModel: EmulatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.start()

        setContent {
            EmulatorView(bitmap = viewModel.screenBitmap.value)
        }
    }
}
