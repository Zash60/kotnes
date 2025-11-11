package com.zash60.kotnes.core.cpu
import com.zash60.kotnes.core.apu.Apu
import com.zash60.kotnes.core.cpu.Cpu
import com.zash60.kotnes.core.ext.update
import com.zash60.kotnes.core.ext.isSetUInt
import com.zash60.kotnes.core.ext.isSetUByte
import com.zash60.kotnes.core.ext.extract
import com.zash60.kotnes.core.ext.toUnsignedInt

import com.zash60.kotnes.core.ext.toInt
class CpuRegister {
    var a = 0 // accumulator
    var x = 0 // index register
    var y = 0 // index register
    var sp = 0xFD // stack pointer
    var n = false // status negative
    var v = false // status overflow
    var r = true // status reserved
    var b = false // status break
    var d = false // status decimal
    var i = true // status interrupt
    var z = false // status zero
    var c = false // status carry
    val status
        get() =
            (n.toInt() shl 7) or
                    (v.toInt() shl 6) or
                    (r.toInt() shl 5) or
                    (b.toInt() shl 4) or
                    (d.toInt() shl 3) or
                    (i.toInt() shl 2) or
                    (z.toInt() shl 1) or
                    c.toInt()
    var pc = 0 // program counter
}
