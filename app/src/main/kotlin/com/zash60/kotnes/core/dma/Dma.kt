package com.zash60.kotnes.core.dma

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
  

class Dma(
    private val ppu: Ppu,
    private val ram: Ram,
) {
    var isProcessing = false
    var ramAddr = 0
    fun run() {
        if (!isProcessing) return
        for (i in 0 until 0x100) {
            ppu.transferSprite(i, ram.read(ramAddr + i))
        }
        isProcessing = false
    }
    fun write(data: Int) {
        ramAddr = data shl 8
        isProcessing = true
}
