package com.zash60.kotnes.core.cartridge
import com.zash60.kotnes.core.apu.Apu
import com.zash60.kotnes.core.cpu.Cpu
import com.zash60.kotnes.core.ext.update
import com.zash60.kotnes.core.ext.isSetUInt
import com.zash60.kotnes.core.ext.isSetUByte
import com.zash60.kotnes.core.ext.extract
import com.zash60.kotnes.core.ext.toInt

import com.zash60.kotnes.core.ext.toUnsignedInt
class Rom(
    private val data: ByteArray,
) {
    val size get() = data.size
    fun read(addr: Int) = data[addr].toUnsignedInt()
}
