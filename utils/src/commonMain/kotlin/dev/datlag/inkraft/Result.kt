package dev.datlag.inkraft

import kotlinx.coroutines.CancellationException as CoroutineCancelException
import kotlin.coroutines.cancellation.CancellationException

/**
 * Calls the specified function [block] and returns its encapsulated result if invocation was successful,
 * catching any [Throwable] exception that was thrown from the [block] function.
 *
 * **Important:** Unlike the standard [runCatching], this function rethrows [CancellationException].
 * This ensures that parent coroutines can cancel the execution flow
 * properly without the cancellation signal being swallowed as a failure result.
 *
 * @param block The function to execute.
 * @return A [Result] object that encapsulates the result of execution.
 */
inline fun <T> suspendCatching(block: () -> T): Result<T> = try {
    Result.success(block())
} catch (e: Throwable) {
    if (e is CancellationException || e is CoroutineCancelException) {
        throw e
    }

    Result.failure(e)
}

/**
 * Returns the result of [transform] applied to the encapsulated value if this instance represents
 * [success][Result.isSuccess], catching any [Throwable] exception that was thrown from the [transform] function.
 *
 * The [transform] block is executed using [suspendCatching], meaning cancellation exceptions
 * will be rethrown rather than caught.
 *
 * @param transform The function to apply to the value.
 * @return A [Result] containing the transformed value, the original failure, or a new failure from the transform.
 */
inline fun <R, T> Result<T>.mapSuspendCatching(transform: (value: T) -> R): Result<R> {
    return when {
        isSuccess -> suspendCatching { transform(getOrNull() ?: return Result.failure(exceptionOrNull() ?: IllegalStateException("Called [mapSuspendCatching] without value to transform"))) }
        else -> Result.failure(exceptionOrNull() ?: IllegalStateException("Called [mapSuspendCatching] without parent exception"))
    }
}

/**
 * Returns the result of [transform] applied to the encapsulated [Throwable] exception if this instance
 * represents [failure][Result.isFailure], catching any [Throwable] exception that was thrown from the [transform] function.
 *
 * The [transform] block is executed using [suspendCatching], meaning cancellation exceptions
 * will be rethrown rather than caught.
 *
 * @param transform The function to recover the failure with.
 * @return A [Result] containing the recovered value, the original success value, or a new failure from the recovery attempt.
 */
inline fun <R, T : R> Result<T>.recoverSuspendCatching(transform: (exception: Throwable) -> R): Result<R> {
    return when (val exception = exceptionOrNull()) {
        null -> this
        else -> suspendCatching { transform(exception) }
    }
}

/**
 * Enables Go-style destructuring for the [Result] class to access the **value**.
 *
 * This functions as the first component in a destructuring declaration: `val (value, error) = result`.
 *
 * @return The encapsulated value if the result is a success, or `null` if the result is a failure.
 * @see component2
 */
operator fun <T> Result<T>.component1(): T? = getOrNull()

/**
 * Enables Go-style destructuring for the [Result] class to access the **error**.
 *
 * This functions as the second component in a destructuring declaration: `val (value, error) = result`.
 *
 * @return The encapsulated [Throwable] exception if the result is a failure, or `null` if the result is a success.
 *
 * @sample
 * // Usage example:
 * val (data, err) = runCatching {
 * // logic that may throw
 * }
 *
 * if (err != null) {
 * // Handle error
 * return
 * }
 *
 * // Handle data (Note: data is nullable T?)
 * println(data)
 * @see component1
 */
operator fun <T> Result<T>.component2(): Throwable? = exceptionOrNull()