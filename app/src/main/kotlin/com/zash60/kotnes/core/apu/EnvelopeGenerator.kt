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
  


class EnvelopeGenerator {
    // ... (cole o resto do código original)
}
    var output: UByte = 0u
        private set
    private var register: UByte = 0u
    private val volume get() = register.extract(range = 0..3)
    private val useConstantVolume get() = register.isSetUByte(4u) // otherwise, use decay
    private val isLoop get() = register.isSetUByte(5u)
    private var startFlag = false
    private var dividerCounter: UByte = 0u
    private var decayLevelCounter: UByte = 0u
    fun tick() {
        if (startFlag) {
            startFlag = false
            resetDecayLevelCounter()
            reloadDivider()
        } else {
            if (dividerCounter > 0u) {
                dividerCounter--
            } else {
                reloadDivider()
                tickDecayLevelCounter()
            }
        }
        output = if (useConstantVolume) volume else decayLevelCounter
    }
    fun setStartFlag() {
        startFlag = true
    fun updateRegister(register: UByte) {
        this.register = register
    private fun reloadDivider() {
        dividerCounter = volume
    private fun tickDecayLevelCounter() {
        if (decayLevelCounter > 0u) {
            decayLevelCounter--
        } else if (isLoop) {
    private fun resetDecayLevelCounter() {
        decayLevelCounter = 15u
}
