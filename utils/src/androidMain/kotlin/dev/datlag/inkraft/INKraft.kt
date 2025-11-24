package dev.datlag.inkraft

import android.content.Context
import dev.datlag.inkraft.fetcher.ResourceFetcher

/**
 * Android implementation of the INKraft utility entry point.
 *
 * Extends [ResourceFetcher] to provide direct, clean access to Android resources
 * scoped to the provided [context].
 *
 * Example:
 * ```kotlin
 * val ink = INKraft(context)
 *
 * // Access resources
 * val color = ink.color[R.color.primary]
 * val text = ink.string(R.string.hello)
 * ```
 *
 * @param context The Android Context used to resolve resources.
 */
actual class INKraft(override val context: Context) : ResourceFetcher() {

    init {
        init(context)
    }

    actual data object Platform {

        @JvmField
        actual val isIOS: Boolean = false

        @JvmField
        actual val isTVOS: Boolean = false

        @JvmField
        actual val isWatchOS: Boolean = false

        @JvmField
        actual val isMacOSNative: Boolean = false

        @JvmField
        actual val isApple: Boolean = isIOS || isTVOS || isWatchOS || isMacOSNative

        @JvmField
        actual val isLinuxNative: Boolean = false

        @JvmField
        actual val isWindowsNative: Boolean = false

        @JvmField
        actual val isAndroidNative: Boolean = false

        @JvmField
        actual val isJs: Boolean = false

        @JvmField
        actual val isWasmJS: Boolean = false

        @JvmField
        actual val isAndroidJVM: Boolean = true

        @JvmField
        actual val isWasmWASI: Boolean = false

        @JvmField
        actual val isDesktopJVM: Boolean = false

        @JvmField
        actual val isWeb: Boolean = isJs || isWasmJS

        @JvmField
        actual val isNative: Boolean = isApple || isLinuxNative || isWindowsNative || isAndroidNative

        @JvmField
        actual val isAndroid: Boolean = isAndroidJVM || isAndroidNative

        @JvmField
        actual val isWasm: Boolean = isWasmJS || isWasmWASI

        @JvmField
        actual val isJVM: Boolean = isDesktopJVM || isAndroidJVM

        @JvmField
        actual val isLinux: Boolean = isLinuxNative

        @JvmField
        actual val isWindows: Boolean = isWindowsNative

        @JvmField
        actual val isMacOS: Boolean = isMacOSNative
    }

    /**
     * Singleton entry point for INKraft utilities.
     *
     * This companion object allows static-like access to resources without creating an instance,
     * provided that [init] has been called previously.
     *
     * Example:
     * ```kotlin
     * // In Application.onCreate()
     * INKraft.init(this)
     *
     * // Anywhere else
     * val color = INKraft.color[R.color.my_color]
     * ```
     */
    companion object : ResourceFetcher() {
        private val contextInstance = WeakReference<Context>(null)

        override val context: Context
            get() = contextInstance.getOrNull() ?: throw IllegalStateException("Cleared context, should not happen! Are you providing a proper applicationContext?")

        /**
         * Initializes the global INKraft singleton with the provided [context].
         *
         * This should typically be called in your `Application.onCreate()`.
         * It holds a [WeakReference] to the `applicationContext` to prevent memory leaks,
         * ensuring that Activity contexts are not accidentally held statically.
         *
         * @param context The context to extract the application context from.
         */
        @JvmStatic
        fun init(context: Context) {
            if (contextInstance.getOrNull() == null) {
                contextInstance.set(context.applicationContext ?: context)
            }
        }

        /**
         * Checks if the current device is a Television.
         *
         * Delegates to the internal context extension.
         */
        @JvmStatic
        fun isTelevision(): Boolean {
            return context.isTelevision()
        }

        /**
         * Checks if the current device is a Watch (Wear OS).
         *
         * Delegates to the internal context extension.
         */
        @JvmStatic
        fun isWatch(): Boolean {
            return context.isWatch()
        }
    }
}