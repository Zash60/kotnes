package com.zash60.kotnes.core.ext
import com.zash60.kotnes.core.apu.Apu
import com.zash60.kotnes.core.cpu.Cpu
import com.zash60.kotnes.core.ext.update
import com.zash60.kotnes.core.ext.isSetUInt
import com.zash60.kotnes.core.ext.isSetUByte
import com.zash60.kotnes.core.ext.extract
import com.zash60.kotnes.core.ext.toUnsignedInt
import com.zash60.kotnes.core.ext.toInt

fun Byte.toUnsignedInt() = this.toInt() and 0xFF
