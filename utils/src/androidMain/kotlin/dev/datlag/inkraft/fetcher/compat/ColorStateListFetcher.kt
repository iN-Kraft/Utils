package dev.datlag.inkraft.fetcher.compat

import android.content.res.ColorStateList
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import dev.datlag.inkraft.fetcher.Fetcher

class ColorStateListFetcher internal constructor() : Fetcher.Standard<Int, ColorStateList?> {

    override operator fun get(@ColorRes resource: Int): ColorStateList? = ContextCompat.getColorStateList(context, resource)
}