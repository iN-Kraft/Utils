package dev.datlag.inkraft

import kotlin.reflect.KClass

actual infix fun <T : Any> KClass<T>.typeOf(base: KClass<*>): Boolean {
    return this == base || this.js == base.js || run {
        val jsClass = this.js.asDynamic()
        val baseClass = base.js.asDynamic()

        js("baseClass.prototype.isPrototypeOf(jsClass.prototype)")
    }
}