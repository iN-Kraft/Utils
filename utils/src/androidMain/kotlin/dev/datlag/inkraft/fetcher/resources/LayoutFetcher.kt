package dev.datlag.inkraft.fetcher.resources

import android.content.Context
import android.content.res.XmlResourceParser
import androidx.annotation.LayoutRes
import dev.datlag.inkraft.fetcher.Fetcher
import kotlin.properties.ReadOnlyProperty

class LayoutFetcher internal constructor(
    private val context: Context
) : Fetcher.Standard<Int, XmlResourceParser> {
    override operator fun get(@LayoutRes resource: Int): XmlResourceParser = context.resources.getLayout(resource)

    override fun lazy(@LayoutRes resource: Int): ReadOnlyProperty<Any?, XmlResourceParser> = ReadOnlyProperty { _, _ ->
        get(resource)
    }
}