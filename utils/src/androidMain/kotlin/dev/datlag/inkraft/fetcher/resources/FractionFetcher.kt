package dev.datlag.inkraft.fetcher.resources

import android.content.Context
import androidx.annotation.FractionRes
import dev.datlag.inkraft.fetcher.Fetcher
import kotlin.properties.ReadOnlyProperty

class FractionFetcher internal constructor(
    private val context: Context
) : Fetcher.Custom<Int, Float> {
    operator fun get(
        @FractionRes resource: Int,
        base: Int,
        pbase: Int
    ): Float = context.resources.getFraction(resource, base, pbase)

    fun lazy(
        @FractionRes resource: Int,
        base: Int,
        pbase: Int
    ): ReadOnlyProperty<Any?, Float> = ReadOnlyProperty { _, _ ->
        get(resource, base, pbase)
    }
}