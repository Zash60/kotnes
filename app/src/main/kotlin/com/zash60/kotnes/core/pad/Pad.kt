package com.zash60.kotnes.core.pad

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
  

class Pad(
    keyEvent: KeyEvent,
) {
    private var isSet = false
    private var index = 0
    private val registers = Array(8, init = { false })
    private val buffer = Array(8, init = { false })
    init {
        keyEvent.listen(object : KeyEventListener {
            override fun onKeyDown(key: Key) {
                buffer[key.keyCode] = true
            }
            override fun onKeyUp(key: Key) {
                buffer[key.keyCode] = false
        })
    }
    // TODO: fix a bug (use nestest ROM)
    fun read() = registers[index++].toInt()
    fun write(data: Int) {
        if (data != 0) {
            isSet = true
        } else if (isSet && data == 0) {
            isSet = false
            index = 0
            buffer.forEachIndexed { idx, b -> registers[idx] = b }
        }
}
