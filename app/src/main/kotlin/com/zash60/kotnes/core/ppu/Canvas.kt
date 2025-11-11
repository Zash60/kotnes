package com.zash60.kotnes.core.ppu

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
  

interface Canvas {
    fun bulkDrawDots(data: List<RenderingData>) {
        data.forEach { drawDot(it.x, it.y, it.r, it.g, it.b) }
    }
    fun drawDot(x: Int, y: Int, r: Int, g: Int, b: Int)
    fun rendered()
    class RenderingData(
        val x: Int,
        val y: Int,
        val r: Int,
        val g: Int,
        val b: Int,
    )
}
