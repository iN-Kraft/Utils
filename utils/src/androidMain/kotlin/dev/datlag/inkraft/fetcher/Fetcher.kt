package dev.datlag.inkraft.fetcher

internal sealed interface Fetcher<in R, out O> {
    interface Standard<in R, out O> : Fetcher<R, O> {
        operator fun get(resource: R): O
    }

    interface Custom<in R, out O> : Fetcher<R, O>
}