package com.zash60.kotnes.core

import com.zash60.kotnes.core.apu.*
import com.zash60.kotnes.core.cartridge.Cartridge
import com.zash60.kotnes.core.cartridge.Rom
import com.zash60.kotnes.core.cpu.Cpu
import com.zash60.kotnes.core.cpu.CpuBus
import com.zash60.kotnes.core.dma.Dma
import com.zash60.kotnes.core.ext.toUnsignedInt
import com.zash60.kotnes.core.interrupts.Interrupts
import com.zash60.kotnes.core.pad.KeyEvent
import com.zash60.kotnes.core.pad.Pad
import com.zash60.kotnes.core.ppu.Canvas
import com.zash60.kotnes.core.ppu.Ppu
import com.zash60.kotnes.core.ram.Ram
import kotlin.math.round

class Emulator(
    cartridge: Cartridge,
    canvas: Canvas,
    keyEvent: KeyEvent,
) {
    private val interrupts = Interrupts()
    private val chrRam = Ram(0x4000).apply {
        cartridge.character.forEachIndexed { idx, data ->
            write(idx, data.toUnsignedInt())
        }
    }

    private val ppu = Ppu(
        chrRam = chrRam,
        canvas = canvas,
        interrupts = interrupts,
        isHorizontalMirror = cartridge.isHorizontalMirror
    )

    private val apu = Apu(
        pulse1 = PulseChannel(
            envelopeGenerator = EnvelopeGenerator(),
            lengthCounter = LengthCounter(),
            isChannelOne = true,
        ),
        pulse2 = PulseChannel(
            envelopeGenerator = EnvelopeGenerator(),
            lengthCounter = LengthCounter(),
            isChannelOne = false,
        ),
        triangle = TriangleChannel(
            lengthCounter = LengthCounter(),
        ),
        noise = NoiseChannel(
            envelopeGenerator = EnvelopeGenerator(),
            lengthCounter = LengthCounter(),
        )
        // A linha 'speaker = Speaker()' foi removida daqui
    )
    private val wRam = Ram(0x2048)
    private val prgRom = Rom(cartridge.program)
    private val dma = Dma(ppu, wRam)
    private val pad = Pad(keyEvent)

    private val cpuBus = CpuBus(
        ppu = ppu,
        apu = apu,
        ram = wRam,
        rom = prgRom,
        dma = dma,
        pad = pad
    )

    private val cpu = Cpu(
        bus = cpuBus,
        interrupts = interrupts
    )

    private var sleepMargin = 0L

    fun start() {
        while (true) {
            val frameStartNs = System.nanoTime()
            stepFrame()
            val frameEndNs = System.nanoTime()

            val sleepTimeNs = (FRAME_NS - (frameEndNs - frameStartNs)) + sleepMargin
            val sleepTimeMs = sleepTimeNs / 1000_000
            val sleepStartNs = System.nanoTime()
            if (sleepTimeMs > 0) {
                Thread.sleep(sleepTimeMs)
            }
            val sleepEnd = System.nanoTime()
            sleepMargin = sleepTimeNs - (sleepEnd - sleepStartNs)
        }
    }

    private fun stepFrame() {
        while (true) {
            var cpuCycle = 0
            if (dma.isProcessing) {
                dma.run()
                cpuCycle = 514
            }
            cpuCycle += cpu.run()
            apu.run(cpuCycle)
            if (ppu.run(cpuCycle * 3)) {
                break
            }
        }
        // apu.flush() // Chamada comentada
    }

    companion object {
        private val FRAME_NS = round(1.0 / 60 * 1000_000_000).toInt() // 60fps
    }
}
