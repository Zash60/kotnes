package com.zash60.kotnes.core.ext
import com.zash60.kotnes.core.apu.Apu
import com.zash60.kotnes.core.cpu.Cpu
import com.zash60.kotnes.core.ext.update
import com.zash60.kotnes.core.ext.isSetUInt
import com.zash60.kotnes.core.ext.isSetUByte
import com.zash60.kotnes.core.ext.extract
import com.zash60.kotnes.core.ext.toUnsignedInt
import com.zash60.kotnes.core.ext.toInt

import java.io.FileInputStream
import java.math.BigInteger
fun FileInputStream.read(len: Int) =
    ByteArray(len).apply { read(this) }
fun FileInputStream.readAsHex(len: Int) =
    read(len).toHex()
fun FileInputStream.readAsInt(len: Int) =
    BigInteger(read(len).toHex(), 16).toInt()
