package com.github.goutarouh.coroutinesample

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

suspend fun main() = runBlocking {
    println("start1")
    supervisorScope {
        launch {
            println("start2")
            coroutineScope {
                launch {
                    process(3)
                }
                launch {
                    process(4)
                }
            }
            println("end2")
        }
        launch {
            println("start5")
            delay(500)
            println("end5")
        }
    }
    println("end1")
    delay(3000)
}