package dev.datlag.inkraft.fetcher.resources

import android.content.Context
import androidx.annotation.IntegerRes
import dev.datlag.inkraft.fetcher.Fetcher

class IntegerFetcher internal constructor(
    private val context: Context
) : Fetcher.Standard<Int, Int> {
    override operator fun get(@IntegerRes resource: Int): Int = context.resources.getInteger(resource)
}