package dev.datlag.inkraft

/**
 * Combine any [Iterable] of the same type to one [MutableList].
 *
 * @return a [MutableList] of all provided items in iterables.
 */
fun <T> mutableListFrom(vararg list: Iterable<T>): MutableList<T> {
    return mutableListOf<T>().apply {
        list.forEach(::addAll)
    }
}

/**
 * Combine any [Iterable] of the same type to one [List].
 *
 * @return a [List] of all provided items in iterables.
 */
fun <T> listFrom(vararg list: Iterable<T>): List<T> = mutableListFrom(*list).toList()

/**
 * Combine any [Iterable] of the same type to one [MutableSet].
 *
 * @return a [MutableSet] of all provided items in iterables.
 */
fun <T> mutableSetFrom(vararg list: Iterable<T>): MutableSet<T> {
    return mutableSetOf<T>().apply {
        list.forEach(::addAll)
    }
}

/**
 * Combine any [Iterable] of the same type to one [Set].
 *
 * @return a [Set] of all provided items in iterables.
 */
fun <T> setFrom(vararg list: Iterable<T>): Set<T> = mutableSetFrom(*list).toSet()

