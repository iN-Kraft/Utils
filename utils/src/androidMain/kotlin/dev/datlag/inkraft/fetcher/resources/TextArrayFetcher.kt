package dev.datlag.inkraft.fetcher.resources

import androidx.annotation.ArrayRes
import dev.datlag.inkraft.fetcher.Fetcher

class TextArrayFetcher internal constructor() : Fetcher.Standard<Int, Array<out CharSequence>> {
    override operator fun get(@ArrayRes resource: Int): Array<out CharSequence> = context.resources.getTextArray(resource)
}