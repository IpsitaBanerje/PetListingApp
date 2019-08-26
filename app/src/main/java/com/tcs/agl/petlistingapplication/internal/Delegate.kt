package com.tcs.agl.petlistingapplication.internal

import kotlinx.coroutines.*

/**
 * [Lazy] block that implements [CoroutineScope]
 */
fun <T> lazyAndDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>>{
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}