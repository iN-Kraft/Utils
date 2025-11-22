package dev.datlag.inkraft

import kotlin.js.JsName

@JsName("WeakRef")
internal external class JsWeakRef<T : Any>(target: T) {
    fun deref(): T?
}