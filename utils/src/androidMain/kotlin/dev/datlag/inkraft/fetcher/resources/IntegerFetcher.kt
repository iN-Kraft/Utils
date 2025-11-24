package dev.datlag.inkraft.fetcher.resources

import android.content.Context
import androidx.annotation.IntegerRes
import dev.datlag.inkraft.fetcher.Fetcher
import kotlin.properties.ReadOnlyProperty

class IntegerFetcher internal constructor(
    private val context: Context
) : Fetcher.Standard<Int, Int> {
    override operator fun get(@IntegerRes resource: Int): Int = context.resources.getInteger(resource)

    override fun lazy(@IntegerRes resource: Int): ReadOnlyProperty<Any?, Int> = ReadOnlyProperty { _, _ ->
        get(resource)
    }
}