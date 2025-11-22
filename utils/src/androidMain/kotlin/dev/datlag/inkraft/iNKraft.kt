package dev.datlag.inkraft

import android.content.Context
import dev.datlag.inkraft.fetcher.compat.ColorFetcher
import dev.datlag.inkraft.fetcher.compat.ColorStateListFetcher
import dev.datlag.inkraft.fetcher.compat.DrawableFetcher
import dev.datlag.inkraft.fetcher.context.StringFetcher
import dev.datlag.inkraft.fetcher.context.TextFetcher
import dev.datlag.inkraft.fetcher.custom.AnimationFetcher
import dev.datlag.inkraft.fetcher.custom.InterpolatorFetcher
import dev.datlag.inkraft.fetcher.custom.PluralFetcher
import dev.datlag.inkraft.fetcher.resources.BoolFetcher
import dev.datlag.inkraft.fetcher.resources.DimensionFetcher
import dev.datlag.inkraft.fetcher.resources.FloatFetcher
import dev.datlag.inkraft.fetcher.resources.FontFetcher
import dev.datlag.inkraft.fetcher.resources.FractionFetcher
import dev.datlag.inkraft.fetcher.resources.IntArrayFetcher
import dev.datlag.inkraft.fetcher.resources.IntegerFetcher
import dev.datlag.inkraft.fetcher.resources.LayoutFetcher
import dev.datlag.inkraft.fetcher.resources.RawFetcher
import dev.datlag.inkraft.fetcher.resources.StringArrayFetcher
import dev.datlag.inkraft.fetcher.resources.TextArrayFetcher
import dev.datlag.inkraft.fetcher.resources.TypedArrayFetcher
import dev.datlag.inkraft.fetcher.resources.XmlFetcher

object iNKraft {

    private val context = WeakReference<Context>(null)

    val string: StringFetcher by lazy { StringFetcher() }
    val plural: PluralFetcher by lazy { PluralFetcher() }
    val text: TextFetcher by lazy { TextFetcher() }

    val bool: BoolFetcher by lazy { BoolFetcher() }
    val dimension: DimensionFetcher by lazy { DimensionFetcher() }
    val float: FloatFetcher by lazy { FloatFetcher() }
    val fraction: FractionFetcher by lazy { FractionFetcher() }
    val integer: IntegerFetcher by lazy { IntegerFetcher() }

    val color: ColorFetcher by lazy { ColorFetcher() }
    val colorStateList: ColorStateListFetcher by lazy { ColorStateListFetcher() }

    val drawable: DrawableFetcher by lazy { DrawableFetcher() }

    val animation: AnimationFetcher by lazy { AnimationFetcher() }
    val interpolator: InterpolatorFetcher by lazy { InterpolatorFetcher() }

    val intArray: IntArrayFetcher by lazy { IntArrayFetcher() }
    val stringArray: StringArrayFetcher by lazy { StringArrayFetcher() }
    val textArray: TextArrayFetcher by lazy { TextArrayFetcher() }
    val typedArray: TypedArrayFetcher by lazy { TypedArrayFetcher() }

    val raw: RawFetcher by lazy { RawFetcher() }
    val xml: XmlFetcher by lazy { XmlFetcher() }
    val font: FontFetcher by lazy { FontFetcher() }
    val layout: LayoutFetcher by lazy { LayoutFetcher() }

    fun init(applicationContext: Context) = apply {
        context.set(applicationContext.applicationContext ?: applicationContext)
    }

    fun isTelevision(): Boolean {
        return getContext().isTelevision()
    }

    fun isWatch(): Boolean {
        return getContext().isWatch()
    }

    internal fun getContext(): Context {
        return context.getOrNull() ?: throw IllegalStateException("Cleared context, should not happen! Are you providing a proper applicationContext?")
    }
}