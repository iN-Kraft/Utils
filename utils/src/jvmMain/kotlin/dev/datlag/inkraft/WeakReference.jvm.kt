package dev.datlag.inkraft

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import java.lang.ref.WeakReference as JavaWeak

actual class WeakReference<T : Any> actual constructor(value: T?) : ReadWriteProperty<Any?, T?> {

    @Volatile
    private var value: JavaWeak<T>? = value?.let { JavaWeak(it) }

    actual fun getOrThrow(): T {
        return value?.get() ?: throw IllegalStateException("Weak reference is null or collected")
    }

    actual fun getOrNull(): T? {
        return value?.get()
    }

    actual fun set(value: T) {
        this.value = JavaWeak(value)
    }

    actual fun clear() {
        value?.clear()
        value = null
    }

    actual override operator fun getValue(thisRef: Any?, property: KProperty<*>): T? {
        return getOrNull()
    }

    actual override operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        if (value != null) {
            set(value)
        } else {
            clear()
        }
    }
}