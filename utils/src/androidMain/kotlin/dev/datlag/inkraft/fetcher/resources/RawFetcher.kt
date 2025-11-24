package dev.datlag.inkraft.fetcher.resources

import android.content.Context
import android.util.TypedValue
import androidx.annotation.RawRes
import dev.datlag.inkraft.fetcher.Fetcher
import java.io.InputStream
import kotlin.properties.ReadOnlyProperty

class RawFetcher internal constructor(
    private val context: Context
) : Fetcher.Standard<Int, InputStream> {
    override operator fun get(@RawRes resource: Int): InputStream = context.resources.openRawResource(resource)
    operator fun get(@RawRes resource: Int, value: TypedValue): InputStream = context.resources.openRawResource(resource, value)

    override fun lazy(@RawRes resource: Int): ReadOnlyProperty<Any?, InputStream> = ReadOnlyProperty { _, _ ->
        get(resource)
    }
    fun lazy(@RawRes resource: Int, value: TypedValue): ReadOnlyProperty<Any?, InputStream> = ReadOnlyProperty { _, _ ->
        get(resource, value)
    }
}