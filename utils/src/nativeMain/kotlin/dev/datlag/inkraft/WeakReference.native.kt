package dev.datlag.inkraft

import kotlin.concurrent.Volatile
import kotlin.experimental.ExperimentalNativeApi
import kotlin.reflect.KProperty
import kotlin.native.ref.WeakReference as NativeWeak

@OptIn(ExperimentalNativeApi::class)
actual class WeakReference<T : Any> actual constructor(value: T?) {

    @Volatile
    private var value: NativeWeak<T>? = value?.let { NativeWeak(it) }

    actual fun getOrThrow(): T {
        return value?.get() ?: throw IllegalStateException("Weak reference is null or collected")
    }

    actual fun getOrNull(): T? {
        return value?.get()
    }

    actual fun set(value: T) {
        this.value = NativeWeak(value)
    }

    actual fun clear() {
        value?.clear()
        value = null
    }

    actual operator fun getValue(thisRef: Any?, property: KProperty<*>): T? {
        return getOrNull()
    }

    actual operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        if (value != null) {
            set(value)
        } else {
            clear()
        }
    }
}