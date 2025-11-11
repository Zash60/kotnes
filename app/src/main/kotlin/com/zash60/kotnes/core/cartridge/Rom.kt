package com.zash60.kotnes.core.cartridge

import com.zash60.kotnes.core.ext.toUnsignedInt

class Rom(
    private val data: ByteArray,
) {
    val size get() = data.size

    fun read(addr: Int) = data[addr].toUnsignedInt()
}
