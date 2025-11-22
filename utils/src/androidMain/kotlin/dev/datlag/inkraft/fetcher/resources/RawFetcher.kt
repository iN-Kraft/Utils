package dev.datlag.inkraft.fetcher.resources

import android.content.Context
import android.util.TypedValue
import androidx.annotation.RawRes
import dev.datlag.inkraft.fetcher.Fetcher
import java.io.InputStream

class RawFetcher internal constructor(
    private val context: Context
) : Fetcher.Standard<Int, InputStream> {
    override operator fun get(@RawRes resource: Int): InputStream = context.resources.openRawResource(resource)
    operator fun get(@RawRes resource: Int, value: TypedValue): InputStream = context.resources.openRawResource(resource, value)
}