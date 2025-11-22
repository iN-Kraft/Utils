package dev.datlag.inkraft.fetcher.custom

import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes
import dev.datlag.inkraft.fetcher.Fetcher

class AnimationFetcher internal constructor() : Fetcher.Standard<Int, Animation?> {
    override operator fun get(@AnimRes resource: Int): Animation? = AnimationUtils.loadAnimation(context, resource)
}