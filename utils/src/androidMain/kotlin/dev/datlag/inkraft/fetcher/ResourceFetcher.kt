package dev.datlag.inkraft.fetcher

import android.content.Context
import android.content.res.Resources
import android.os.Handler
import android.util.TypedValue
import androidx.annotation.AnimRes
import androidx.annotation.ArrayRes
import androidx.annotation.BoolRes
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.annotation.FractionRes
import androidx.annotation.IntegerRes
import androidx.annotation.InterpolatorRes
import androidx.annotation.LayoutRes
import androidx.annotation.PluralsRes
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.annotation.XmlRes
import androidx.core.content.res.ResourcesCompat
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

/**
 * An abstract facade that simplifies access to Android application resources.
 *
 * This class provides a unified API to retrieve Strings, Colors, Drawables, Dimensions,
 * and other resource types without repeatedly accessing `context.resources`.
 *
 * **Access Patterns:**
 * Resources can be accessed in three ways:
 * 1. **Direct Method:** `ink.string(R.string.id)`
 * 2. **Fetcher Operator:** `ink.string[R.string.id]`
 * 3. **Lazy Delegate:** `val title by ink.string.lazy(R.string.id)`
 */
abstract class ResourceFetcher internal constructor() {

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

    fun string(@StringRes resource: Int) = string[resource]
    fun string(@StringRes resource: Int, vararg formatArgs: Any) = string.get(resource, formatArgs = formatArgs)
    fun plural(@PluralsRes resource: Int, quantity: Int) = plural[resource, quantity]
    fun plural(@PluralsRes resource: Int, quantity: Int, vararg formatArgs: Any) = plural.get(resource, quantity, formatArgs = formatArgs)
    fun text(@StringRes resource: Int) = text[resource]
    fun text(@StringRes resource: Int, def: CharSequence) = text[resource, def]
    fun text(@PluralsRes resource: Int, quantity: Int) = text[resource, quantity]

    fun bool(@BoolRes resource: Int) = bool[resource]
    fun dimension(@DimenRes resource: Int) = dimension[resource]
    fun float(@DimenRes resource: Int) = float[resource]
    fun fraction(@FractionRes resource: Int, base: Int, pbase: Int) = fraction[resource, base, pbase]
    fun integer(@IntegerRes resource: Int) = integer[resource]

    fun color(@ColorRes resource: Int) = color[resource]
    fun colorStateList(@ColorRes resource: Int) = colorStateList[resource]

    fun drawable(@DrawableRes resource: Int) = drawable[resource]
    fun drawable(@DrawableRes resource: Int, theme: Resources.Theme?) = drawable[resource, theme]

    fun animation(@AnimRes resource: Int) = animation[resource]
    fun interpolator(@AnimRes @InterpolatorRes resource: Int) = interpolator[resource]

    fun intArray(@ArrayRes resource: Int) = intArray[resource]
    fun stringArray(@ArrayRes resource: Int) = stringArray[resource]
    fun textArray(@ArrayRes resource: Int) = textArray[resource]
    fun typedArray(@ArrayRes resource: Int) = typedArray[resource]

    fun raw(@RawRes resource: Int) = raw[resource]
    fun raw(@RawRes resource: Int, value: TypedValue) = raw[resource, value]
    fun xml(@XmlRes resource: Int) = xml[resource]
    fun font(@FontRes resource: Int) = font[resource]
    @JvmOverloads
    fun font(@FontRes resource: Int, fontCallback: ResourcesCompat.FontCallback, handler: Handler? = null) = font[resource, fontCallback, handler]
    fun layout(@LayoutRes resource: Int) = layout[resource]
}