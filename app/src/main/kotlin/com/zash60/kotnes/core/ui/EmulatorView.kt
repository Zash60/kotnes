package com.zash60.kotnes.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import android.graphics.Bitmap

// Resolução do NES
const val SCREEN_WIDTH = 256
const val SCREEN_HEIGHT = 240

@Composable
fun EmulatorView(bitmap: ImageBitmap) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawImage(bitmap)
    }
}

fun createEmptyBitmap(): ImageBitmap {
    return Bitmap.createBitmap(SCREEN_WIDTH, SCREEN_HEIGHT, Bitmap.Config.ARGB_8888).asImageBitmap()
}
