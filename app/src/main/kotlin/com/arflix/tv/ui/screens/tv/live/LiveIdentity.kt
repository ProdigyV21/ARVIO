package com.arflix.tv.ui.screens.tv.live

/**
 * Compose compares remember/LaunchedEffect keys and stable parameters with equals().
 * When a background pass publishes a new list that is equal to the old one, the
 * runtime keeps the *old* key and so compares the two lists element by element on
 * every later recomposition. With sports events holding thousands of channels this
 * was a third of the main thread while scrolling the guide. Publishing the previous
 * instance when the content did not change keeps those comparisons at `===`.
 *
 * Call this off the main thread: the equality check itself is the expensive part.
 */
internal fun <T> List<T>.reuseIfEqual(previous: List<T>?): List<T> =
    if (previous != null && previous !== this && previous == this) previous else this

/** A key that changes only when the wrapped object is replaced, never compared deeply. */
internal class ByReference<T>(val value: T) {
    override fun equals(other: Any?): Boolean = other is ByReference<*> && other.value === value
    override fun hashCode(): Int = System.identityHashCode(value)
}
