package dev.datlag.inkraft

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

expect class WeakReference<T : Any>(value: T? = null) : ReadWriteProperty<Any?, T?> {
    fun getOrThrow(): T
    fun getOrNull(): T?
    fun set(value: T)
    fun clear()

    override operator fun getValue(thisRef: Any?, property: KProperty<*>): T?
    override operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?)
}