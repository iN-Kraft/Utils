package dev.datlag.inkraft.fetcher

import android.content.Context
import dev.datlag.inkraft.iNKraft

internal sealed interface Fetcher<in R, out O> {

    val context: Context
        get() = iNKraft.getContext()

    interface Standard<in R, out O> : Fetcher<R, O> {
        operator fun get(resource: R): O
    }

    interface Custom<in R, out O> : Fetcher<R, O>
}