package dev.datlag.inkraft.fetcher.resources

import android.content.Context
import androidx.annotation.FractionRes
import dev.datlag.inkraft.fetcher.Fetcher

class FractionFetcher internal constructor(
    private val context: Context
) : Fetcher.Custom<Int, Float> {

    operator fun get(
        @FractionRes fraction: Int,
        base: Int,
        pbase: Int
    ): Float = context.resources.getFraction(fraction, base, pbase)
}