# Utils

> [!IMPORTANT]
> Read the full documentation on the iNKraft website.  
> https://datlag.dev

A Kotlin Multiplatform library providing essential helpers, a unified `WeakReference`, and a comprehensive `Platform` object for easy target detection.

## 🎯 Supported Targets

The following targets are supported:

| Platform           | Targets                                  |
|:-------------------|:-----------------------------------------|
| **JVM & Android**  | `jvm`, `android`                         |
| **Apple**          | `ios`, `macos`, `tvos`, `watchos`        |
| **Web**            | `js`, `wasmJs`                           |
| **Native & Other** | `androidNative`, `linux`, `mingw`, `wasmWasi`                         |

## ✨ Features

Utils streamlines cross-platform development with common tools and extensions.

### 🌍 Platform Inspection

Utils provides a singleton `Platform` object that allows you to check the current runtime environment with simple boolean properties.
It resolves complex hierarchies (e.g., `isApple`, `isNative`, `isWeb`) automatically.

```kotlin
if (INKraft.Platform.isAndroid) {
    // Android specific logic
} else if (INKraft.Platform.isWasm) {
    // WebAssembly specific logic
}
```

### 🧠 Memory Management

Includes a common `WeakReference` class implementation for Kotlin Multiplatform, allowing you to hold references to objects without preventing them from being reclaimed by the garbage collector on supported targets.

### 🛠️ Handy Extensions

A collection of useful extension functions to reduce boilerplate code, such as:

- `CharSequence.quote()`: Wraps text in quotes.
- And other common string and utility helpers.
