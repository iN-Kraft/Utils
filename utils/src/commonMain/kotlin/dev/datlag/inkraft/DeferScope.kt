package dev.datlag.inkraft

/**
 * A scope that manages a stack of cleanup actions to be executed in Last-In-First-Out (LIFO) order.
 *
 * This class implements the "Go-style defer" pattern for **blocking** code. It is designed to be used
 * primarily via the [usingBlocking] helper function, but since it implements [AutoCloseable],
 * it can also be used with the standard Kotlin [use] function.
 *
 * **Execution Behavior:**
 * - Actions are executed in the reverse order of their registration (Stack behavior).
 * - Cleanup guarantees: All registered actions are attempted, even if previous actions throw exceptions.
 * - Exception handling: If multiple actions fail, the first exception is thrown, and subsequent
 * exceptions are attached as **suppressed** exceptions (on platforms that support it).
 *
 * @see usingBlocking
 * @see AutoCloseable
 */
class DeferScope : AutoCloseable {

    private val actions = ArrayDeque<() -> Unit>()

    /**
     * Schedules a block of code to be executed when this scope closes.
     *
     * The [action] will be pushed onto the cleanup stack and executed after the main
     * block of code finishes (or throws).
     *
     * @param action The cleanup logic to execute.
     */
    fun defer(action: () -> Unit) {
        actions.addLast(action)
    }

    /**
     * Registers this [AutoCloseable] resource to be closed automatically when the scope ends.
     *
     * This is a convenience extension equivalent to calling `defer { this.close() }`.
     *
     * @return The receiver object (for fluent chaining).
     * @sample
     * // Usage:
     * val file = FileInputStream("data.txt").deferClose()
     */
    fun <T : AutoCloseable> T.deferClose(): T {
        defer { this.close() }
        return this
    }

    /**
     * Executes all registered deferred actions in LIFO order.
     *
     * This method is called automatically by [usingBlocking] or the standard [use] function.
     * You rarely need to call this manually unless managing the scope's lifecycle yourself.
     *
     * @throws Throwable If any deferred action fails. If multiple fail, the first
     * exception is thrown with others added as suppressed.
     */
    override fun close() {
        var currentError: Throwable? = null

        while (actions.isNotEmpty()) {
            val action = actions.removeLast()

            try {
                action()
            } catch (e: Throwable) {
                if (currentError == null) {
                    currentError = e
                } else {
                    currentError.addSuppressed(e)
                }
            }
        }

        if (currentError != null) throw currentError
    }
}

/**
 * Executes a block of code within a **blocking** defer scope.
 *
 * Use this function in synchronous contexts (like `main()`, scripts, or non-coroutine legacy code)
 * where you need "Go-style" resource management.
 *
 * **⚠️ Blocking Warning:**
 * This function blocks the current thread until the [block] and all deferred cleanup actions
 * are complete. Do not use this inside a coroutine if you can avoid it; use [using] instead.
 *
 * **Behavior:**
 * - **LIFO Order:** Deferred actions are executed in reverse order of registration.
 * - **Standard AutoCloseable:** This is essentially a wrapper around the standard Kotlin
 * `DeferScope().use { ... }` pattern but provides a cleaner syntax.
 *
 * @param block The blocking code block to execute. The receiver is [DeferScope].
 * @return The result of the [block].
 * @see DeferScope
 * @see DeferScope.defer
 */
inline fun <T> usingBlocking(block: DeferScope.() -> T): T {
    return DeferScope().use(block)
}

/**
 * Helper: Combines [usingBlocking] and [suspendCatching] for Result.
 */
inline fun <T> usingBlockingResult(block: DeferScope.() -> T): Result<T> = suspendCatching {
    usingBlocking(block)
}