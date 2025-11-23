package dev.datlag.inkraft

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

private val virtualDispatcher: CoroutineDispatcher? by lazy {
    suspendCatching {
        Executors.newVirtualThreadPerTaskExecutor().asCoroutineDispatcher()
    }.getOrNull() ?: suspendCatching {
        (Executors::class.java.getMethod("newVirtualThreadPerTaskExecutor").invoke(null) as? ExecutorService)?.asCoroutineDispatcher()
    }.getOrNull()
}

actual val Dispatchers.Virtual: CoroutineDispatcher?
    get() = virtualDispatcher