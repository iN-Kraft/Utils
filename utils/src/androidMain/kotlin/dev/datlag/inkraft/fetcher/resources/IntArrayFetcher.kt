package dev.datlag.inkraft.fetcher.resources

import androidx.annotation.ArrayRes
import dev.datlag.inkraft.fetcher.Fetcher

class IntArrayFetcher internal constructor() : Fetcher.Standard<Int, IntArray> {
    override operator fun get(@ArrayRes resource: Int): IntArray = context.resources.getIntArray(resource)
}