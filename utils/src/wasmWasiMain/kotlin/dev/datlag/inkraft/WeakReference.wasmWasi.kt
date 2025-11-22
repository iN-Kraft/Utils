package dev.datlag.inkraft

actual class WeakReference<T : Any> actual constructor(value: T?) {

    // Not yet supported, may cause memory leaks when not cleared correctly
    private var value: T? = value

    actual fun getOrThrow(): T {
        return value ?: throw IllegalStateException("Reference is null")
    }

    actual fun getOrNull(): T? {
        return value
    }

    actual fun set(value: T) {
        this.value = value
    }

    actual fun clear() {
        value = null
    }
}