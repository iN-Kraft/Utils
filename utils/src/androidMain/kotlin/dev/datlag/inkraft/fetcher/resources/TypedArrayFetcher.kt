package dev.datlag.inkraft.fetcher.resources

import android.content.res.TypedArray
import androidx.annotation.ArrayRes
import dev.datlag.inkraft.fetcher.Fetcher

class TypedArrayFetcher internal constructor() : Fetcher.Standard<Int, TypedArray> {
    override operator fun get(@ArrayRes resource: Int): TypedArray = context.resources.obtainTypedArray(resource)
}