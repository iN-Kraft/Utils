package dev.datlag.inkraft

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toKString
import platform.posix.__system_property_get
import platform.posix.getenv
import sysprop.PROP_VALUE_MAX

@OptIn(ExperimentalForeignApi::class)
actual fun systemProperty(key: String): String? = memScoped {
    val buffer = allocArray<ByteVar>(PROP_VALUE_MAX)
    val len = __system_property_get(key, buffer)

    if (len > 0) {
        buffer.toKString().ifBlank { null }
    } else {
        null
    }
}

@OptIn(ExperimentalForeignApi::class)
actual fun systemEnv(key: String): String? {
    return getenv(key)?.toKString()?.ifBlank { null }
}