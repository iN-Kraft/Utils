package dev.datlag.inkraft.fetcher.resources

import android.content.Context
import androidx.annotation.ArrayRes
import dev.datlag.inkraft.fetcher.Fetcher
import kotlin.properties.ReadOnlyProperty

class IntArrayFetcher internal constructor(
    private val context: Context
) : Fetcher.Standard<Int, IntArray> {
    override operator fun get(@ArrayRes resource: Int): IntArray = context.resources.getIntArray(resource)

    override fun lazy(@ArrayRes resource: Int): ReadOnlyProperty<Any?, IntArray> = ReadOnlyProperty { _, _ ->
        get(resource)
    }
}