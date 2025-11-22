package dev.datlag.inkraft.fetcher

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

abstract class FetcherHolder internal constructor() {

    internal abstract val context: Context

    val string: StringFetcher by lazy { StringFetcher(context) }
    val plural: PluralFetcher by lazy { PluralFetcher(context) }
    val text: TextFetcher by lazy { TextFetcher(context) }

    val bool: BoolFetcher by lazy { BoolFetcher(context) }
    val dimension: DimensionFetcher by lazy { DimensionFetcher(context) }
    val float: FloatFetcher by lazy { FloatFetcher(context) }
    val fraction: FractionFetcher by lazy { FractionFetcher(context) }
    val integer: IntegerFetcher by lazy { IntegerFetcher(context) }

    val color: ColorFetcher by lazy { ColorFetcher(context) }
    val colorStateList: ColorStateListFetcher by lazy { ColorStateListFetcher(context) }

    val drawable: DrawableFetcher by lazy { DrawableFetcher(context) }

    val animation: AnimationFetcher by lazy { AnimationFetcher(context) }
    val interpolator: InterpolatorFetcher by lazy { InterpolatorFetcher(context) }

    val intArray: IntArrayFetcher by lazy { IntArrayFetcher(context) }
    val stringArray: StringArrayFetcher by lazy { StringArrayFetcher(context) }
    val textArray: TextArrayFetcher by lazy { TextArrayFetcher(context) }
    val typedArray: TypedArrayFetcher by lazy { TypedArrayFetcher(context) }

    val raw: RawFetcher by lazy { RawFetcher(context) }
    val xml: XmlFetcher by lazy { XmlFetcher(context) }
    val font: FontFetcher by lazy { FontFetcher(context) }
    val layout: LayoutFetcher by lazy { LayoutFetcher(context) }
}