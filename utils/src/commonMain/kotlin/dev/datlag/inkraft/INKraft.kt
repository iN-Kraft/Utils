package dev.datlag.inkraft

/**
 * The central entry point for INKraft utilities and platform-specific configurations.
 */
expect class INKraft {

    /**
     * Provides runtime environment information and target detection.
     *
     * This object allows for branching logic based on the specific target (e.g., [isAndroidJVM])
     * or broader platform families (e.g., [isApple], [isWeb]).
     */
    object Platform {
        /** True if running on an iOS device or simulator. */
        val isIOS: Boolean

        /** True if running on tvOS. */
        val isTVOS: Boolean

        /** True if running on watchOS. */
        val isWatchOS: Boolean

        /** True if running on the native macOS target (Kotlin/Native). */
        val isMacOSNative: Boolean

        /**
         * True if running on any Apple platform.
         *
         * Aggregates [isIOS], [isTVOS], [isWatchOS], and [isMacOSNative].
         */
        val isApple: Boolean

        /** True if running on the native Linux target. */
        val isLinuxNative: Boolean

        /** True if running on the native Windows target (MinGW). */
        val isWindowsNative: Boolean

        /** True if running on the Android NDK (Native) target. */
        val isAndroidNative: Boolean

        /** True if running on a JavaScript target. */
        val isJs: Boolean

        /** True if running on WebAssembly (JS) target in the browser. */
        val isWasmJS: Boolean

        /** True if running on the standard Android JVM target. */
        val isAndroidJVM: Boolean

        /** True if running on WebAssembly via WASI. */
        val isWasmWASI: Boolean

        /** True if running on a standard JVM (Desktop/Server) excluding Android. */
        val isDesktopJVM: Boolean

        /**
         * True if running in a web environment.
         *
         * Aggregates [isJs] and [isWasmJS].
         */
        val isWeb: Boolean

        /**
         * True if running on a native target (Kotlin/Native).
         *
         * This excludes JVM, JS, and Wasm targets.
         */
        val isNative: Boolean

        /**
         * True if running on the Android OS.
         *
         * Aggregates [isAndroidJVM] and [isAndroidNative].
         */
        val isAndroid: Boolean

        /**
         * True if running on any WebAssembly target.
         *
         * Aggregates [isWasmJS] and [isWasmWASI].
         */
        val isWasm: Boolean

        /**
         * True if running on the Java Virtual Machine.
         *
         * Aggregates [isDesktopJVM] and [isAndroidJVM].
         */
        val isJVM: Boolean

        /**
         * True if the operating system is Linux-based.
         *
         * This may include [isLinuxNative] and JVM/Android running on Linux kernels.
         */
        val isLinux: Boolean

        /**
         * True if the operating system is Windows.
         *
         * This includes [isWindowsNative] and JVM running on Windows.
         */
        val isWindows: Boolean

        /**
         * True if the operating system is macOS.
         *
         * This includes [isMacOSNative] and JVM running on macOS.
         */
        val isMacOS: Boolean
    }

}