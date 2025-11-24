package dev.datlag.inkraft.fetcher.compat

import android.content.Context
import android.content.res.ColorStateList
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import dev.datlag.inkraft.fetcher.Fetcher
import kotlin.properties.ReadOnlyProperty

class ColorStateListFetcher internal constructor(
    private val context: Context
) : Fetcher.Standard<Int, ColorStateList?> {
    override operator fun get(@ColorRes resource: Int): ColorStateList? = ContextCompat.getColorStateList(context, resource)

    override fun lazy(@ColorRes resource: Int): ReadOnlyProperty<Any?, ColorStateList?> = ReadOnlyProperty { _, _ ->
        get(resource)
    }
}