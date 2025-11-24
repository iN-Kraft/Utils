package dev.datlag.inkraft.fetcher.custom

import android.content.Context
import androidx.annotation.PluralsRes
import dev.datlag.inkraft.fetcher.Fetcher
import kotlin.properties.ReadOnlyProperty

class PluralFetcher internal constructor(
    private val context: Context
) : Fetcher.Custom<Int, String> {
    operator fun get(
        @PluralsRes resource: Int,
        quantity: Int
    ): String = context.resources.getQuantityString(resource, quantity)
    operator fun get(
        @PluralsRes resource: Int,
        quantity: Int,
        vararg formatArgs: Any
    ): String = context.resources.getQuantityString(resource, quantity, *formatArgs)

    fun lazy(
        @PluralsRes resource: Int,
        quantity: Int
    ): ReadOnlyProperty<Any?, String> = ReadOnlyProperty { _, _ ->
        get(resource, quantity)
    }
    fun lazy(
        @PluralsRes resource: Int,
        quantity: Int,
        vararg formatArgs: Any
    ): ReadOnlyProperty<Any?, String> = ReadOnlyProperty { _, _ ->
        get(resource, quantity, formatArgs = formatArgs)
    }
}