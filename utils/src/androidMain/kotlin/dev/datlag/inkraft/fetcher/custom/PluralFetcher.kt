package dev.datlag.inkraft.fetcher.custom

import androidx.annotation.PluralsRes
import dev.datlag.inkraft.fetcher.Fetcher

class PluralFetcher internal constructor() : Fetcher.Custom<Int, String> {

    operator fun get(
        @PluralsRes resource: Int,
        quantity: Int
    ): String = context.resources.getQuantityString(resource, quantity)

    operator fun get(
        @PluralsRes resource: Int,
        quantity: Int,
        vararg formatArgs: Any
    ): String = context.resources.getQuantityString(resource, quantity, *formatArgs)

}