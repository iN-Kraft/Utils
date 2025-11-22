package dev.datlag.inkraft

import kotlin.reflect.KClass

expect infix fun <T : Any> KClass<T>.typeOf(base: KClass<*>): Boolean

infix fun <T : Any> Any?.typeOf(base: KClass<T>): Boolean {
    return base.isInstance(this)
}

infix fun <T : Any> KClass<T>.typeOf(value: Any?): Boolean {
    return value.typeOf(this)
}