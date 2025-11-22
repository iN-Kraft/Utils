package dev.datlag.inkraft.fetcher.resources

import androidx.annotation.DimenRes
import androidx.core.content.res.ResourcesCompat
import dev.datlag.inkraft.fetcher.Fetcher

class FloatFetcher internal constructor() : Fetcher.Standard<Int, Float> {
    override operator fun get(@DimenRes resource: Int): Float = ResourcesCompat.getFloat(context.resources, resource)
}