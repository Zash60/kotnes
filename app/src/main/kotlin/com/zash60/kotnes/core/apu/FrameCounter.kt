package com.zash60.kotnes.core.apu
import com.zash60.kotnes.core.apu.Apu
import com.zash60.kotnes.core.ext.update
import com.zash60.kotnes.core.ext.isSetUInt
import com.zash60.kotnes.core.ext.isSetUByte
import com.zash60.kotnes.core.ext.extract
import com.zash60.kotnes.core.ext.toUnsignedInt
import com.zash60.kotnes.core.ext.toInt

import com.zash60.kotnes.core.cpu.Cpu
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
