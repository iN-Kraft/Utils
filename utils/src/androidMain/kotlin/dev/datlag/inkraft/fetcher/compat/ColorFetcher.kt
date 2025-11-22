package dev.datlag.inkraft.fetcher.compat

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import dev.datlag.inkraft.fetcher.Fetcher

class ColorFetcher internal constructor(
    private val context: Context
) : Fetcher.Standard<Int, Int> {
    override operator fun get(@ColorRes resource: Int): Int = ContextCompat.getColor(context, resource)
}