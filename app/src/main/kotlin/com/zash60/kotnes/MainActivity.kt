package com.zash60.kotnes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
// IMPORT CORRIGIDO PARA APONTAR PARA A LOCALIZAÇÃO CORRETA DA UI
import com.zash60.kotnes.core.ui.EmulatorView

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
