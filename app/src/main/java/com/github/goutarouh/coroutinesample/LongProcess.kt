package com.github.goutarouh.coroutinesample

import kotlinx.coroutines.delay

suspend fun process(i: Int, p: () -> Unit = {}) {
    println("start$i")
    delay(10L * i)
    p.invoke()
    println("end$i")
}