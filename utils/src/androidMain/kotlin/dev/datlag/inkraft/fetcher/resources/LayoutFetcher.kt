package dev.datlag.inkraft.fetcher.resources

import android.content.res.XmlResourceParser
import androidx.annotation.LayoutRes
import dev.datlag.inkraft.fetcher.Fetcher

class LayoutFetcher internal constructor() : Fetcher.Standard<Int, XmlResourceParser> {
    override operator fun get(@LayoutRes resource: Int): XmlResourceParser = context.resources.getLayout(resource)
}