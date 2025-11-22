package dev.datlag.inkraft.fetcher.resources

import android.content.Context
import androidx.annotation.ArrayRes
import dev.datlag.inkraft.fetcher.Fetcher

class TextArrayFetcher internal constructor(
    private val context: Context
) : Fetcher.Standard<Int, Array<out CharSequence>> {
    override operator fun get(@ArrayRes resource: Int): Array<out CharSequence> = context.resources.getTextArray(resource)
}