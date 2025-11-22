package dev.datlag.inkraft.fetcher.resources

import androidx.annotation.ArrayRes
import dev.datlag.inkraft.fetcher.Fetcher

class StringArrayFetcher internal constructor() : Fetcher.Standard<Int, Array<out String>> {
    override operator fun get(@ArrayRes resource: Int): Array<out String> = context.resources.getStringArray(resource)
}