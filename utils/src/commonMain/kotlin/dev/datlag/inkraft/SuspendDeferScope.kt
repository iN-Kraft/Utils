package dev.datlag.inkraft

import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.withContext

/**
 * A coroutine-aware scope that manages a stack of **suspending** cleanup actions to be executed
 * in Last-In-First-Out (LIFO) order.
 *
 * This class implements the "Go-style defer" pattern for **asynchronous** code. It is designed
 * to be used via the [using] helper function inside coroutines.
 *
 * **Key Features:**
 * - **Cancellation Safety:** Cleanup actions are always executed in the [NonCancellable] context.
 * This guarantees that resources are released even if the parent coroutine is cancelled
 * (e.g., user navigates away or a timeout occurs).
 * - **Exception Handling:** Handles multiple failures by throwing the first exception and
 * attaching subsequent failures as suppressed exceptions.
 *
 * @see using
 * @see SuspendCloseable
 */
class SuspendDeferScope : SuspendCloseable {

    private val actions = ArrayDeque<suspend () -> Unit>()

    /**
     * Schedules a **suspending** block of code to be executed when this scope closes.
     *
     * @param action The suspending cleanup logic (e.g., `api.logout()`, `db.closeAsync()`).
     */
    fun defer(action: suspend () -> Unit) {
        actions.addLast(action)
    }

    /**
     * Registers a standard blocking [AutoCloseable] to be closed when the scope ends.
     *
     * **⚠️ Performance Warning:**
     * Since [AutoCloseable.close] is a blocking method, using it here will block the
     * coroutine dispatcher during cleanup.
     * - If you are on `Dispatchers.Main`, this could freeze the UI.
     * - Ideally, wrap blocking resources in a `withContext(Dispatchers.IO) { }` block
     * inside a manual [defer] call.
     *
     * @return The receiver object (for fluent chaining).
     */
    fun <T : AutoCloseable> T.deferClose(): T {
        defer { close() }
        return this
    }

    /**
     * Registers a [SuspendCloseable] to be closed asynchronously.
     *
     * **Behavior:**
     * - This function uses the non-blocking [SuspendCloseable.close] method.
     *
     * @return The receiver object (for fluent chaining).
     */
    fun <T : SuspendCloseable> T.deferClose(): T {
        defer { close() }
        return this
    }

    /**
     * Executes all registered deferred actions in LIFO order within a [NonCancellable] context.
     *
     * This method is called automatically by the [using] function.
     *
     * @throws Throwable If any action fails.
     */
    override suspend fun close() {
        var currentError: Throwable? = null

        withContext(NonCancellable) {
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
        }

        if (currentError != null) throw currentError
    }
}

/**
 * Executing a block of code within a **suspending** defer scope.
 *
 * This function is the primary entry point for "Go-style" resource management in Coroutines.
 * It creates a [SuspendDeferScope] that allows you to register cleanup actions (using `defer`)
 * which are guaranteed to run when the block exits.
 *
 * **Behavior:**
 * - **LIFO Order:** Deferred actions are executed in reverse order of registration.
 * - **Cancellation Safety:** Cleanup actions are run in a [NonCancellable] context.
 * - **Exception Safety:** If the block throws an exception, cleanup actions are still executed.
 * If cleanup actions also fail, their exceptions are suppressed and attached to the original exception.
 *
 * @param block The suspending code block to execute. The receiver is [SuspendDeferScope].
 * @return The result of the [block].
 * @see SuspendDeferScope
 * @see SuspendDeferScope.defer
 */
suspend inline fun <T> using(block: SuspendDeferScope.() -> T): T {
    return SuspendDeferScope().use(block)
}

/**
 * Helper: Combines [using] and [suspendCatching] for Result.
 */
suspend inline fun <T> usingResult(block: SuspendDeferScope.() -> T): Result<T> = suspendCatching {
    using(block)
}