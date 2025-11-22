package dev.datlag.inkraft.fetcher.resources

import androidx.annotation.FractionRes
import dev.datlag.inkraft.fetcher.Fetcher

class FractionFetcher internal constructor() : Fetcher.Custom<Int, Float> {

    operator fun get(
        @FractionRes fraction: Int,
        base: Int,
        pbase: Int
    ): Float = context.resources.getFraction(fraction, base, pbase)
}