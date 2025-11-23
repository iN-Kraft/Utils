package dev.datlag.inkraft

expect class INKraft {

    object Platform {
        val isIOS: Boolean
        val isTVOS: Boolean
        val isWatchOS: Boolean
        val isMacOSNative: Boolean
        val isApple: Boolean
        val isLinuxNative: Boolean
        val isWindowsNative: Boolean
        val isAndroidNative: Boolean
        val isJs: Boolean
        val isWasmJS: Boolean
        val isAndroidJVM: Boolean
        val isWasmWASI: Boolean
        val isDesktopJVM: Boolean
        val isWeb: Boolean
        val isNative: Boolean
        val isAndroid: Boolean
        val isWasm: Boolean
        val isJVM: Boolean
        val isLinux: Boolean
        val isWindows: Boolean
        val isMacOS: Boolean
    }

}