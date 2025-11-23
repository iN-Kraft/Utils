package dev.datlag.inkraft

import java.nio.file.Path
import kotlin.io.path.exists
import kotlin.io.path.Path

actual object INKraft {
    actual data object Platform {
        internal val os by lazy {
            OperatingSystem.matching()
        }

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
        actual val isAndroidJVM: Boolean = false

        @JvmField
        actual val isWasmWASI: Boolean = false

        @JvmField
        actual val isDesktopJVM: Boolean = true

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

        @JvmStatic
        actual val isLinux: Boolean by lazy {
            isLinuxNative || os?.isLinux ?: false
        }

        @JvmStatic
        actual val isWindows: Boolean by lazy {
            isWindowsNative || os?.isWindows ?: false
        }

        @JvmStatic
        actual val isMacOS: Boolean by lazy {
            isMacOSNative || os?.isMacOS ?: false
        }
    }

    data object Directories {
        private const val PROPERTY_USER_HOME = "user.home"
        private const val ENV_USER_HOME = "HOME"
        private const val PROPERTY_JAVA_HOME = "java.home"
        private const val ENV_JAVA_HOME = "JAVA_HOME"
        private const val PATH_JAVA_SDK_MAN = ".sdkman/candidates/java/current"
        private const val PATH_JAVA_DEFAULT_RUNTIME = "/usr/lib/jvm/default-runtime"
        private const val PATH_JAVA_FALLBACK_RUNTIME = "/usr/lib/jvm/java"
        private const val PROPERTY_TEMP_DIR = "java.io.tmpdir"
        private const val ENV_TEMP_DIR = "TEMP"

        @JvmStatic
        val Home: Path? by lazy {
            resolveDirectory(systemProperty(PROPERTY_USER_HOME))
                ?: resolveDirectory(systemEnv(ENV_USER_HOME))
        }

        @JvmStatic
        val JavaHome: Path? by lazy {
            resolveDirectory(systemProperty(PROPERTY_JAVA_HOME))
                ?: resolveDirectory(systemEnv(ENV_JAVA_HOME))
                ?: Home?.resolve(PATH_JAVA_SDK_MAN)?.ifExists()
                ?: resolveDirectory(PATH_JAVA_DEFAULT_RUNTIME)
                ?: resolveDirectory(PATH_JAVA_FALLBACK_RUNTIME)
        }

        @JvmStatic
        val Temp: Path? by lazy {
            resolveDirectory(systemProperty(PROPERTY_TEMP_DIR))
                ?: resolveDirectory(systemEnv(ENV_TEMP_DIR))
        }

        private fun resolveDirectory(path: String?): Path? {
            if (path.isNullOrBlank()) {
                return null
            }

            return Path(path).ifExists()
        }

        private fun Path.ifExists(): Path? = if (this.exists()) {
            this
        } else {
            null
        }
    }
}