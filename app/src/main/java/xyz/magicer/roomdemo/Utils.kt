package xyz.magicer.roomdemo

import kotlin.random.Random

fun random(): Int {
    return Random(System.currentTimeMillis()).nextInt(100)
}