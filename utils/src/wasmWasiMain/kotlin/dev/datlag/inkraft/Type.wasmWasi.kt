package dev.datlag.inkraft

import kotlin.reflect.KClass

actual infix fun <T : Any> KClass<T>.typeOf(base: KClass<*>): Boolean {
    return this == base
}