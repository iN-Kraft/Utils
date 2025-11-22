package dev.datlag.inkraft

expect class WeakReference<T : Any>(value: T? = null) {
    fun getOrThrow(): T
    fun getOrNull(): T?
    fun set(value: T)
    fun clear()
}