package dev.datlag.inkraft.fetcher.resources

import android.content.Context
import androidx.annotation.ArrayRes
import dev.datlag.inkraft.fetcher.Fetcher
import kotlin.properties.ReadOnlyProperty

class StringArrayFetcher internal constructor(
    private val context: Context
) : Fetcher.Standard<Int, Array<out String>> {
    override operator fun get(@ArrayRes resource: Int): Array<out String> = context.resources.getStringArray(resource)

    override fun lazy(@ArrayRes resource: Int): ReadOnlyProperty<Any?, Array<out String>> = ReadOnlyProperty { _, _ ->
        get(resource)
    }
}