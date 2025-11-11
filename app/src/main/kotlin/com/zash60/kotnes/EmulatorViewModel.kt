package com.zash60.kotnes

import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zash60.kotnes.ui.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EmulatorViewModel : ViewModel() {
    val screenBitmap = mutableStateOf(createEmptyBitmap())

    private val bitmap = Bitmap.createBitmap(SCREEN_WIDTH, SCREEN_HEIGHT, Bitmap.Config.ARGB_8888)
    private val pixelBuffer = IntArray(SCREEN_WIDTH * SCREEN_HEIGHT)

    fun start() {
        viewModelScope.launch(Dispatchers.Default) {
            while (true) {
                // Gera "ruído" aleatório para mostrar que a tela está funcionando
                for (i in pixelBuffer.indices) {
                    pixelBuffer[i] = (0xFF000000.toInt() or (0..0xFFFFFF).random())
                }

                bitmap.setPixels(pixelBuffer, 0, SCREEN_WIDTH, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT)

                screenBitmap.value = bitmap.asImageBitmap()

                delay(16)
            }
        }
    }
}
