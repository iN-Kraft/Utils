package dev.datlag.inkraft

import kotlin.reflect.KClass

/**
 * Checks if this class represents the same type as, or a subtype of, the [base] class.
 *
 * **Platform Behavior Warning:**
 * This function behaves inconsistently across platforms due to runtime limitations:
 * - **JVM / Android:** Performs a full subclass check (`this.isSubclassOf(base)`).
 * - **JS:** Uses a prototype check, which may not work reliably for all classes or interfaces.
 * - **Native / Common:** Often strictly checks for equality (`this == base`) and **does not** detect inheritance.
 *
 * @param base The class to check against.
 * @return `true` if the types match according to the platform-specific rules, `false` otherwise.
 */
expect infix fun <T : Any> KClass<T>.typeOf(base: KClass<*>): Boolean

/**
 * Checks if this object instance is an instance of the [base] class.
 *
 * This is a functional wrapper around [KClass.isInstance].
 *
 * @param base The class to check type against.
 * @return `true` if this object is an instance of [base], `false` otherwise.
 */
infix fun <T : Any> Any?.typeOf(base: KClass<T>): Boolean {
    return base.isInstance(this)
}

/**
 * Checks if the provided [value] is an instance of this class.
 *
 * This is a convenience infix function effectively reversing the syntax of [Any?.typeOf].
 *
 * @param value The object instance to check.
 * @return `true` if [value] is an instance of this class.
 */
infix fun <T : Any> KClass<T>.typeOf(value: Any?): Boolean {
    return value.typeOf(this)
}