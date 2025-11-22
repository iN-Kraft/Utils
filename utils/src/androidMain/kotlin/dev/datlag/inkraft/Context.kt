package dev.datlag.inkraft

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build

fun PackageManager.isTelevision(): Boolean {
    val leanbackOnly = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        this.hasSystemFeature(PackageManager.FEATURE_LEANBACK_ONLY)
    } else {
        false
    }

    return leanbackOnly
            || this.hasSystemFeature(PackageManager.FEATURE_TELEVISION)
            || this.hasSystemFeature(PackageManager.FEATURE_LEANBACK)
}

fun Configuration.isTelevision(): Boolean {
    return (this.uiMode and Configuration.UI_MODE_TYPE_MASK) == Configuration.UI_MODE_TYPE_TELEVISION
}

fun Context.isTelevision(): Boolean {
    return this.packageManager.isTelevision() || this.resources.configuration?.isTelevision() ?: false
}

fun PackageManager.isWatch(): Boolean {
    return this.hasSystemFeature(PackageManager.FEATURE_WATCH)
}

fun Configuration.isWatch(): Boolean {
    return (this.uiMode and Configuration.UI_MODE_TYPE_MASK) == Configuration.UI_MODE_TYPE_WATCH
}

fun Context.isWatch(): Boolean {
    return this.packageManager.isWatch() || this.resources.configuration?.isWatch() ?: false
}