package dev.datlag.inkraft

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * A cross-platform wrapper for a weak reference.
 *
 * A weak reference allows the referenced object to be garbage collected if it is not reachable
 * by any other strong references. This is useful for caches or preventing memory leaks in
 * parent-child relationships.
 *
 * This class implements [ReadWriteProperty], allowing it to be used as a property delegate:
 * ```kotlin
 * var myObject: MyType? by WeakReference(initialValue)
 * ```
 *
 * @param T The type of the object being referenced.
 * @param value The initial value to hold strongly referenced, or null.
 */
expect class WeakReference<T : Any>(value: T? = null) : ReadWriteProperty<Any?, T?> {

    /**
     * Returns the referenced object if it is still present, otherwise throws an exception.
     *
     * **Warning:** Because garbage collection is non-deterministic, relying on this method
     * is generally unsafe unless you are certain a strong reference exists elsewhere.
     *
     * @throws NullPointerException or [IllegalStateException] if the reference has been collected or cleared.
     * @return The referenced object.
     */
    fun getOrThrow(): T

    /**
     * Returns the referenced object if it has not been garbage collected, or `null` if it has.
     *
     * @return The object or `null`.
     */
    fun getOrNull(): T?

    /**
     * Updates the held reference to the new [value].
     *
     * @param value The new object to reference weakly.
     */
    fun set(value: T)

    /**
     * Clears the reference, effectively setting the held value to `null`.
     */
    fun clear()

    /**
     * Delegate getter. Returns the value via [getOrNull].
     */
    override operator fun getValue(thisRef: Any?, property: KProperty<*>): T?

    /**
     * Delegate setter. Updates the value or clears it if [value] is null.
     *
     * @param value The new value to set, or null to clear the reference.
     */
    override operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?)
}