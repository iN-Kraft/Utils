package dev.datlag.inkraft.fetcher.context

import android.content.Context
import androidx.annotation.StringRes
import dev.datlag.inkraft.fetcher.Fetcher
import kotlin.properties.ReadOnlyProperty

class StringFetcher internal constructor(
    private val context: Context
) : Fetcher.Standard<Int, String> {
    override operator fun get(@StringRes resource: Int): String = context.getString(resource)
    operator fun get(@StringRes resource: Int, vararg formatArgs: Any): String = context.getString(resource, *formatArgs)

    override fun lazy(@StringRes resource: Int): ReadOnlyProperty<Any?, String> = ReadOnlyProperty { _, _ ->
        get(resource)
    }
    fun lazy(@StringRes resource: Int, vararg formatArgs: Any): ReadOnlyProperty<Any?, String> = ReadOnlyProperty { _, _ ->
        get(resource, formatArgs = formatArgs)
    }
}