package dev.datlag.inkraft

/**
 * An object that may hold resources (such as file handles, network connections,
 * or database sessions) requiring explicit asynchronous release.
 *
 * This interface mirrors the standard [AutoCloseable], but the [close] method
 * is a suspending function. This allows for non-blocking cleanup operations,
 * such as flushing buffers via IO or sending network termination packets
 * without blocking the underlying thread.
 *
 * @see AutoCloseable
 * @see use
 */
interface SuspendCloseable {

    /**
     * Closes this resource, relinquishing any underlying resources.
     * This method is invoked automatically on objects managed by the [use] extension function.
     *
     * Implementations of this method should be **suspending** to allow for
     * asynchronous cleanup. It is recommended to handle cancellation appropriately
     * (e.g., using `withContext(NonCancellable)`) if the cleanup must complete
     * even when the parent coroutine is cancelled.
     *
     * @throws Exception if this resource cannot be closed correctly.
     */
    suspend fun close()
}

/**
 * Executes the given [block] function on this resource and then closes it correctly
 * whether an exception is thrown or not.
 *
 * This function is the suspending equivalent of the standard standard [AutoCloseable.use].
 *
 * **Exception Handling Behavior:**
 * - If the [block] completes successfully, [SuspendCloseable.close] is called.
 * If `close` throws, that exception is rethrown.
 * - If the [block] throws an exception, [SuspendCloseable.close] is called.
 * - If `close` succeeds, the original exception from [block] is rethrown.
 * - If `close` also throws, the new exception is added as a **suppressed exception** * to the original exception (where supported), and the original exception is rethrown.
 *
 * @param block a function to process this [SuspendCloseable] resource.
 * @return the result of [block] function.
 */
suspend inline fun <T : SuspendCloseable, R> T.use(block: (T) -> R): R {
    var exception: Throwable? = null

    try {
        return block(this)
    } catch (e: Throwable) {
        exception = e
        throw e
    } finally {
        try {
            this.close()
        } catch (closeE: Throwable) {
            if (exception == null) {
                throw closeE
            } else {
                exception.addSuppressed(closeE)
            }
        }
    }
}