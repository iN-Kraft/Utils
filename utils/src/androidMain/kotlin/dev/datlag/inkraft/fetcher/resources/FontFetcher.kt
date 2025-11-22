package dev.datlag.inkraft.fetcher.resources

import android.content.Context
import android.graphics.Typeface
import android.os.Handler
import androidx.annotation.FontRes
import androidx.core.content.res.ResourcesCompat
import dev.datlag.inkraft.fetcher.Fetcher

class FontFetcher internal constructor(
    private val context: Context
) : Fetcher.Standard<Int, Typeface?> {
    override operator fun get(@FontRes resource: Int): Typeface? = ResourcesCompat.getFont(context, resource)

    operator fun get(
        @FontRes resource: Int,
        fontCallback: ResourcesCompat.FontCallback,
        handler: Handler?
    ): Unit = ResourcesCompat.getFont(context, resource, fontCallback, handler)

    operator fun get(
        @FontRes resource: Int,
        fontCallback: ResourcesCompat.FontCallback
    ) = get(resource, fontCallback, handler = null)
}