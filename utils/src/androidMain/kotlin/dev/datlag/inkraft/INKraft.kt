package dev.datlag.inkraft

import android.content.Context
import dev.datlag.inkraft.fetcher.ResourceFetcher

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

    companion object : ResourceFetcher() {
        private val contextInstance = WeakReference<Context>(null)

        override val context: Context
            get() = contextInstance.getOrNull() ?: throw IllegalStateException("Cleared context, should not happen! Are you providing a proper applicationContext?")

        @JvmStatic
        fun init(context: Context) {
            if (contextInstance.getOrNull() == null) {
                contextInstance.set(context.applicationContext ?: context)
            }
        }

        @JvmStatic
        fun isTelevision(): Boolean {
            return context.isTelevision()
        }

        @JvmStatic
        fun isWatch(): Boolean {
            return context.isWatch()
        }
    }
}