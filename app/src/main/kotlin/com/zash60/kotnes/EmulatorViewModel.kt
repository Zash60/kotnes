package com.zash60.kotnes

import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
// IMPORTANTE: Mude o import abaixo para o caminho correto do seu PPU
// import com.zash60.kotnes.core.ppu.PPU
import com.zash60.kotnes.ui.SCREEN_HEIGHT
import com.zash60.kotnes.ui.SCREEN_WIDTH
import com.zash60.kotnes.ui.createEmptyBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EmulatorViewModel : ViewModel() {
    val screenBitmap = mutableStateOf(createEmptyBitmap())

    private val bitmap = Bitmap.createBitmap(SCREEN_WIDTH, SCREEN_HEIGHT, Bitmap.Config.ARGB_8888)
    private val pixelBuffer = IntArray(SCREEN_WIDTH * SCREEN_HEIGHT)

    // --- INTEGRAÇÃO DO EMULADOR AQUI ---
    // PASSO 1: Descomente e instancie seu emulador quando tiver movido os arquivos
    // private val emulator = Emulator()

    fun start() {
        // Inicia o game loop em uma thread de background
        viewModelScope.launch(Dispatchers.Default) {
            while (true) {
                // PASSO 2: Substitua este bloco pela lógica do seu emulador
                // A lógica deve:
                // 1. Rodar um frame completo do emulador (ex: emulator.runFrame())
                // 2. Pedir ao PPU o array de pixels do frame (ex: val pixels = emulator.ppu.getPixels())
                // 3. Copiar esses pixels para o `pixelBuffer`

                // --- INÍCIO DO CÓDIGO DE EXEMPLO (SUBSTITUIR DEPOIS) ---
                // Gera "ruído" aleatório para mostrar que a tela está funcionando
                for (i in pixelBuffer.indices) {
                    pixelBuffer[i] = (0xFF000000.toInt() or (0..0xFFFFFF).random())
                }
                // --- FIM DO CÓDIGO DE EXEMPLO ---

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
