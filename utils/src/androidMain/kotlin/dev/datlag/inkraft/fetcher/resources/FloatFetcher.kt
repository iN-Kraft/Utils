package dev.datlag.inkraft.fetcher.resources

import android.content.Context
import androidx.annotation.DimenRes
import androidx.core.content.res.ResourcesCompat
import dev.datlag.inkraft.fetcher.Fetcher
import kotlin.properties.ReadOnlyProperty

class FloatFetcher internal constructor(
    private val context: Context
) : Fetcher.Standard<Int, Float> {
    override operator fun get(@DimenRes resource: Int): Float = ResourcesCompat.getFloat(context.resources, resource)

    override fun lazy(@DimenRes resource: Int): ReadOnlyProperty<Any?, Float> = ReadOnlyProperty { _, _ ->
        get(resource)
    }
}