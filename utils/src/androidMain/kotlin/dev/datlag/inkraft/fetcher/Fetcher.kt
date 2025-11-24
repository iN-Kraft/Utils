package dev.datlag.inkraft.fetcher

import kotlin.properties.ReadOnlyProperty

internal sealed interface Fetcher<in R, out O> {
    interface Standard<in R, out O> : Fetcher<R, O> {
        operator fun get(resource: R): O
        fun lazy(resource: R): ReadOnlyProperty<Any?, O>
    }

    interface Custom<in R, out O> : Fetcher<R, O>
}