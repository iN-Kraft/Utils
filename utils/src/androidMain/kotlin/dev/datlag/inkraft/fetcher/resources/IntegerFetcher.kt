package dev.datlag.inkraft.fetcher.resources

import androidx.annotation.IntegerRes
import dev.datlag.inkraft.fetcher.Fetcher

class IntegerFetcher internal constructor() : Fetcher.Standard<Int, Int> {
    override operator fun get(@IntegerRes resource: Int): Int = context.resources.getInteger(resource)
}