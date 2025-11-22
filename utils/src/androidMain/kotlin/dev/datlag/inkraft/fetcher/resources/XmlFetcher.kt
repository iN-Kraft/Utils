package dev.datlag.inkraft.fetcher.resources

import android.content.Context
import android.content.res.XmlResourceParser
import androidx.annotation.XmlRes
import dev.datlag.inkraft.fetcher.Fetcher

class XmlFetcher internal constructor(
    private val context: Context
) : Fetcher.Standard<Int, XmlResourceParser> {
    override operator fun get(@XmlRes resource: Int): XmlResourceParser = context.resources.getXml(resource)
}