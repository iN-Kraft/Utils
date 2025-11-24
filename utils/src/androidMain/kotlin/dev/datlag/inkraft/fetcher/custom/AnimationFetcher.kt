package dev.datlag.inkraft.fetcher.custom

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes
import dev.datlag.inkraft.fetcher.Fetcher
import kotlin.properties.ReadOnlyProperty

class AnimationFetcher internal constructor(
    private val context: Context
) : Fetcher.Standard<Int, Animation?> {
    override operator fun get(@AnimRes resource: Int): Animation? = AnimationUtils.loadAnimation(context, resource)

    override fun lazy(@AnimRes resource: Int): ReadOnlyProperty<Any?, Animation?> = ReadOnlyProperty { _, _ ->
        get(resource)
    }
}