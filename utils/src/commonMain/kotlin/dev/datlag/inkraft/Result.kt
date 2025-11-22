package dev.datlag.inkraft

import kotlinx.coroutines.CancellationException as CoroutineCancelException
import kotlin.coroutines.cancellation.CancellationException

inline fun <T> suspendCatching(block: () -> T): Result<T> = try {
    Result.success(block())
} catch (e: Throwable) {
    if (e is CancellationException || e is CoroutineCancelException) {
        throw e
    }

    Result.failure(e)
}

inline fun <R, T> Result<T>.mapSuspendCatching(transform: (value: T) -> R): Result<R> {
    return when {
        isSuccess -> suspendCatching { transform(getOrNull() ?: return Result.failure(exceptionOrNull() ?: IllegalStateException("Called [mapSuspendCatching] without value to transform"))) }
        else -> Result.failure(exceptionOrNull() ?: IllegalStateException("Called [mapSuspendCatching] without parent exception"))
    }
}

inline fun <R, T : R> Result<T>.recoverSuspendCatching(transform: (exception: Throwable) -> R): Result<R> {
    return when (val exception = exceptionOrNull()) {
        null -> this
        else -> suspendCatching { transform(exception) }
    }
}