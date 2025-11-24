package dev.datlag.inkraft.fetcher.resources

import android.content.Context
import android.content.res.TypedArray
import androidx.annotation.ArrayRes
import dev.datlag.inkraft.fetcher.Fetcher
import kotlin.properties.ReadOnlyProperty

class TypedArrayFetcher internal constructor(
    private val context: Context
) : Fetcher.Standard<Int, TypedArray> {
    override operator fun get(@ArrayRes resource: Int): TypedArray = context.resources.obtainTypedArray(resource)

    override fun lazy(@ArrayRes resource: Int): ReadOnlyProperty<Any?, TypedArray> = ReadOnlyProperty { _, _ ->
        get(resource)
    }
}