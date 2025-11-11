package com.zash60.kotnes.core.cartridge

import com.zash60.kotnes.core.ext.read
import com.zash60.kotnes.core.ext.readAsHex
import com.zash60.kotnes.core.ext.readAsInt
import java.io.File

class Cartridge(rom: File) {
    val program: ByteArray
    val character: ByteArray
    val isHorizontalMirror: Boolean

    init {
        val romData = rom.inputStream()
        val magicBytes = romData.readAsHex(4)
        if (magicBytes != MAGIC_BYTES) {
            throw IllegalArgumentException("The file is not iNES file.")
        }
        val prgPage = romData.readAsInt(1)
        val chrPage = romData.readAsInt(1)
        val prgSize = prgPage * PRG_PAGE_SIZE
        val chrSize = chrPage * CHR_PAGE_SIZE
        isHorizontalMirror = romData.readAsInt(1) == 0
        val readHeaderBytes = 7
        // dump rest header
        romData.read(HEADER_SIZE - readHeaderBytes)
        program = romData.read(prgSize)
        character = romData.read(chrSize)
    }

    companion object {
        const val MAGIC_BYTES = "4E45531A"

        const val HEADER_SIZE = 0x10
        const val PRG_PAGE_SIZE = 0x4000
        const val CHR_PAGE_SIZE = 0x2000
    }
}
