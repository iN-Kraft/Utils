package dev.datlag.inkraft.fetcher.custom

import android.content.Context
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator
import androidx.annotation.AnimRes
import androidx.annotation.InterpolatorRes
import dev.datlag.inkraft.fetcher.Fetcher

class InterpolatorFetcher internal constructor(
    private val context: Context
) : Fetcher.Standard<Int, Interpolator> {
    override operator fun get(@AnimRes @InterpolatorRes resource: Int): Interpolator = AnimationUtils.loadInterpolator(context, resource)
}