package com.zash60.kotnes.core.pad

interface KeyEvent {
    fun listen(listener: KeyEventListener)
}

interface KeyEventListener {
    fun onKeyDown(key: Key)
    fun onKeyUp(key: Key)
}
