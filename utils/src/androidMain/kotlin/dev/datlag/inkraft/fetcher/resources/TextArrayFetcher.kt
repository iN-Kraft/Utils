package dev.datlag.inkraft.fetcher.resources

import android.content.Context
import androidx.annotation.ArrayRes
import dev.datlag.inkraft.fetcher.Fetcher
import kotlin.properties.ReadOnlyProperty

class TextArrayFetcher internal constructor(
    private val context: Context
) : Fetcher.Standard<Int, Array<out CharSequence>> {
    override operator fun get(@ArrayRes resource: Int): Array<out CharSequence> = context.resources.getTextArray(resource)

    override fun lazy(@ArrayRes resource: Int): ReadOnlyProperty<Any?, Array<out CharSequence>> = ReadOnlyProperty { _, _ ->
        get(resource)
    }
}