package dev.datlag.inkraft

import dev.datlag.inkraft.Constants.DOUBLE_QUOTATION
import dev.datlag.inkraft.Constants.SINGLE_QUOTATION

/**
 * Wraps this character in double quotes (`"`).
 *
 * Example: `c` becomes `"c"`.
 */
val Char.doubleQuoted
    get() = quote(DOUBLE_QUOTATION)

/**
 * Wraps this character in single quotes (`'`).
 *
 * Example: `c` becomes `'c'`.
 */
val Char.singleQuoted
    get() = quote(SINGLE_QUOTATION)

/**
 * Wraps this [CharSequence] in double quotes (`"`) and escapes special characters.
 *
 * This handles escaping for backslashes, newlines, carriage returns, tabs, and double quotes.
 */
val CharSequence.doubleQuoted
    get() = quote(DOUBLE_QUOTATION)

/**
 * Wraps this [CharSequence] in single quotes (`'`) and escapes special characters.
 *
 * This handles escaping for backslashes, newlines, carriage returns, tabs, and double quotes.
 */
val CharSequence.singleQuoted
    get() = quote(SINGLE_QUOTATION)

/**
 * Wraps this character with the specified [start] and [end] characters.
 *
 * @param start The character to prepend. Defaults to [DOUBLE_QUOTATION].
 * @param end The character to append. Defaults to [start].
 * @return A string containing the wrapped character.
 */
fun Char.quote(start: Char = DOUBLE_QUOTATION, end: Char = start) = buildString {
    append(start)
    append(this@quote)
    append(end)
}

/**
 * Wraps this [CharSequence] with the specified [start] and [end] characters and escapes content.
 *
 * Special characters within the sequence are escaped to ensure valid string representation:
 * - `\` becomes `\\`
 * - `\n` becomes `\n` (literal)
 * - `\r` becomes `\r` (literal)
 * - `\t` becomes `\t` (literal)
 * - `"` becomes `\"`
 *
 * @param start The character to prepend. Defaults to [DOUBLE_QUOTATION].
 * @param end The character to append. Defaults to [start].
 * @return A string containing the wrapped and escaped sequence.
 */
fun CharSequence.quote(start: Char = DOUBLE_QUOTATION, end: Char = start) = buildString {
    append(start)
    for (char in this@quote) {
        when (char) {
            '\\' -> append("\\\\")
            '\n' -> append("\\n")
            '\r' -> append("\\r")
            '\t' -> append("\\t")
            '\"' -> append("\\\"")
            else -> append(char)
        }
    }
    append(end)
}