package dev.datlag.inkraft.fetcher.resources

import androidx.annotation.DimenRes
import dev.datlag.inkraft.fetcher.Fetcher

class DimensionFetcher internal constructor() : Fetcher.Standard<Int, Float> {
    override operator fun get(@DimenRes resource: Int): Float = context.resources.getDimension(resource)
}