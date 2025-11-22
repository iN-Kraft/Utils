package dev.datlag.inkraft.fetcher.context

import android.content.Context
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import dev.datlag.inkraft.fetcher.Fetcher

class TextFetcher internal constructor(
    private val context: Context
) : Fetcher.Standard<Int, CharSequence> {

    override operator fun get(@StringRes resource: Int): CharSequence = context.getText(resource)

    operator fun get(@StringRes resource: Int, def: CharSequence): CharSequence = context.resources.getText(resource, def)

    operator fun get(@PluralsRes resource: Int, quantity: Int): CharSequence = context.resources.getQuantityText(resource, quantity)
}