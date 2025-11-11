package com.zash60.kotnes.core.cpu

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
  

class CpuBus(
    private val ppu: Ppu,
    private val apu: Apu,
    private val ram: Ram,
    private val rom: Rom,
    private val dma: Dma,
    private val pad: Pad,
) {
    fun read(addr: Int) =
        when {
            addr < 0x2000 -> ram.read(addr % 0x800)
            addr < 0x4000 -> ppu.read((addr - 0x2000) % 8)
            addr == 0x4015 -> apu.read(0x15)
            addr == 0x4016 -> pad.read()
            addr >= 0xC000 -> {
                val offset = -if (rom.size <= 0x4000) 0xC000 else 0x8000
                rom.read(addr + offset)
            }
            addr >= 0x8000 -> rom.read(addr - 0x8000)
            else -> 0
        }
    fun write(addr: Int, data: Int) {
            addr < 0x2000 -> ram.write(addr % 0x800, data)
            addr < 0x4000 -> ppu.write((addr - 0x2000) % 8, data)
            addr == 0x4014 -> dma.write(data)
            addr == 0x4016 -> pad.write(data)
            addr < 0x4020 -> apu.write(addr - 0x4000, data.toUByte())
    }
}
