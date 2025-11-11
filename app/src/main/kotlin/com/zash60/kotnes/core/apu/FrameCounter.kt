package com.zash60.kotnes.core.apu

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
  

class FrameCounter(
    private val onQuarterFrame: () -> Any,
    private val onHalfFrame: () -> Any,
) {
    private var value = 0
    private var register: UByte = 0u
    private var accumulativeCycles = 0
    fun tick() {
        if (++accumulativeCycles < STEP_CYCLES) return
        accumulativeCycles = 0
        val mode = register.isSetUByte(7u)
        if (mode) {
            runFiveStepSequence()
        } else {
            runFourStepSequence()
        }
    }
    fun updateRegister(data: UByte) {
        register = data
    private fun runFourStepSequence() {
        onQuarterFrame()
        when (value) {
            1, 3 -> onHalfFrame()
        if (value == 3) {
            value = 0
            value++
    private fun runFiveStepSequence() {
            0, 1, 2, 4 -> onQuarterFrame()
            1, 4 -> onHalfFrame()
        if (value == 4) {
    companion object {
        private const val STEP_CYCLES = Cpu.CPU_HZ / Apu.APU_HZ
}
