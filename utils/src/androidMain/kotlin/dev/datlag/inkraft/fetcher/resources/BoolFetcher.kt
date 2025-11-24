package dev.datlag.inkraft.fetcher.resources

import android.content.Context
import androidx.annotation.BoolRes
import dev.datlag.inkraft.fetcher.Fetcher
import kotlin.properties.ReadOnlyProperty

class BoolFetcher internal constructor(
    private val context: Context
) : Fetcher.Standard<Int, Boolean> {
    override operator fun get(@BoolRes resource: Int): Boolean = context.resources.getBoolean(resource)

    override fun lazy(@BoolRes resource: Int): ReadOnlyProperty<Any?, Boolean> = ReadOnlyProperty { _, _ ->
        get(resource)
    }
}