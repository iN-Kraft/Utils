package dev.datlag.inkraft.fetcher.compat

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import dev.datlag.inkraft.fetcher.Fetcher
import kotlin.properties.ReadOnlyProperty

class DrawableFetcher internal constructor(
    private val context: Context
) : Fetcher.Standard<Int, Drawable?> {
    override operator fun get(@DrawableRes resource: Int): Drawable? = ContextCompat.getDrawable(context, resource)
    operator fun get(@DrawableRes resource: Int, theme: Resources.Theme?): Drawable? = context.resources.getDrawable(resource, theme)

    override fun lazy(@DrawableRes resource: Int): ReadOnlyProperty<Any?, Drawable?> = ReadOnlyProperty { _, _ ->
        get(resource)
    }
    fun lazy(@DrawableRes resource: Int, theme: Resources.Theme?): ReadOnlyProperty<Any?, Drawable?> = ReadOnlyProperty { _, _ ->
        get(resource, theme)
    }
}