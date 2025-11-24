package dev.datlag.inkraft

import kotlin.reflect.KProperty

expect class WeakReference<T : Any>(value: T? = null) {
    fun getOrThrow(): T
    fun getOrNull(): T?
    fun set(value: T)
    fun clear()

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T?
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?)
}