package dev.datlag.inkraft

actual fun systemProperty(key: String): String? = suspendCatching {
    System.getProperty(key).ifBlank { null }
}.getOrNull()

actual fun systemEnv(key: String): String? = suspendCatching {
    System.getenv(key).ifBlank { null }
}.getOrNull()