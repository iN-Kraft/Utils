package dev.datlag.inkraft.fetcher.resources

import androidx.annotation.BoolRes
import dev.datlag.inkraft.fetcher.Fetcher

class BoolFetcher internal constructor() : Fetcher.Standard<Int, Boolean> {

    override operator fun get(@BoolRes resource: Int): Boolean = context.resources.getBoolean(resource)
}