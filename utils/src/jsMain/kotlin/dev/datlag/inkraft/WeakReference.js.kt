package dev.datlag.inkraft

import kotlin.reflect.KProperty

actual class WeakReference<T : Any> actual constructor(value: T?) {

    private var value: JsWeakRef<T>? = value?.let { JsWeakRef(it) }

    actual fun getOrThrow(): T {
        return value?.deref() ?: throw IllegalStateException("Weak reference is null or collected")
    }

    actual fun getOrNull(): T? {
        return value?.deref()
    }

    actual fun set(value: T) {
        this.value = JsWeakRef(value)
    }

    actual fun clear() {
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