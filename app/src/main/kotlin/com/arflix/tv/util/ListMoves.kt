package com.arflix.tv.util

/** This list with the element at [from] taken out and put back in at [to]. */
fun <T> List<T>.withItemMoved(from: Int, to: Int): List<T> {
    if (from !in indices || to !in indices || from == to) return this
    val result = toMutableList()
    result.add(to, result.removeAt(from))
    return result
}
