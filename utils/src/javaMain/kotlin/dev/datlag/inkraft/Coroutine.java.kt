package dev.datlag.inkraft

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Returns a [CoroutineDispatcher] backed by Java Virtual Threads (Project Loom), or `null` if not available.
 *
 * **Availability:**
 * - **JVM:** Returns a valid dispatcher if running on Java 21+ (or compatible preview versions).
 * - **Android:** Currently returns `null` as Virtual Threads are not supported in the standard Android SDK.
 * - **Other Targets:** Returns `null`.
 *
 * Use this dispatcher for blocking I/O tasks where high concurrency is required without the overhead of OS-level threads.
 *
 * Example usage:
 * ```kotlin
 * val dispatcher = Dispatchers.Virtual ?: Dispatchers.IO
 * withContext(dispatcher) {
 * // High-concurrency blocking work
 * }
 * ```
 */
expect val Dispatchers.Virtual: CoroutineDispatcher?