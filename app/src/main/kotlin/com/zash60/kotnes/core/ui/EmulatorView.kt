package com.zash60.kotnes.core.ui

  import com.zash60.kotnes.core.apu.*
  import com.zash60.kotnes.core.cartridge.*
  import com.zash60.kotnes.core.cpu.*
  import com.zash60.kotnes.core.dma.*
  import com.zash60.kotnes.core.exception.*
  import com.zash60.kotnes.core.ext.*
  import com.zash60.kotnes.core.interrupts.*
  import com.zash60.kotnes.core.pad.*
  import com.zash60.kotnes.core.ppu.*
  import com.zash60.kotnes.core.ram.*
  import com.zash60.kotnes.core.util.*
  import java.io.File
  import java.math.BigInteger
  import kotlin.math.pow
  import kotlin.math.round
  

// Resolução do NES
const val SCREEN_WIDTH = 256
const val SCREEN_HEIGHT = 240
@Composable
fun EmulatorView(bitmap: ImageBitmap) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawImage(bitmap)
    }
}
fun createEmptyBitmap(): ImageBitmap {
    return Bitmap.createBitmap(SCREEN_WIDTH, SCREEN_HEIGHT, Bitmap.Config.ARGB_8888).asImageBitmap()
