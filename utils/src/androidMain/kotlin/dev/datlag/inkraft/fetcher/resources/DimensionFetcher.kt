package dev.datlag.inkraft.fetcher.resources

import android.content.Context
import androidx.annotation.DimenRes
import dev.datlag.inkraft.fetcher.Fetcher
import kotlin.properties.ReadOnlyProperty

class DimensionFetcher internal constructor(
    private val context: Context
) : Fetcher.Standard<Int, Float> {
    override operator fun get(@DimenRes resource: Int): Float = context.resources.getDimension(resource)

    override fun lazy(@DimenRes resource: Int): ReadOnlyProperty<Any?, Float> = ReadOnlyProperty { _, _ ->
        get(resource)
    }
}