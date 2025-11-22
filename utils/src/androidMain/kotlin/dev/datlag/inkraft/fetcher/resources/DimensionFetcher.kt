package dev.datlag.inkraft.fetcher.resources

import android.content.Context
import androidx.annotation.DimenRes
import dev.datlag.inkraft.fetcher.Fetcher

class DimensionFetcher internal constructor(
    private val context: Context
) : Fetcher.Standard<Int, Float> {
    override operator fun get(@DimenRes resource: Int): Float = context.resources.getDimension(resource)
}