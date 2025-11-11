package com.zash60.kotnes

import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
// IMPORT CORRIGIDO PARA APONTAR PARA A LOCALIZAÇÃO CORRETA DA UI
import com.zash60.kotnes.core.ui.*
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

                // Atualiza o bitmap com os novos pixels
                bitmap.setPixels(pixelBuffer, 0, SCREEN_WIDTH, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT)

                // Envia o bitmap atualizado para a UI
                screenBitmap.value = bitmap.asImageBitmap()

                // Aprox. 60 FPS
                delay(16)
            }
        }
    }
}
