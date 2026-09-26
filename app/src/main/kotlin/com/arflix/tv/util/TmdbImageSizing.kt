package com.arflix.tv.util

import kotlin.math.ceil

/**
 * Picks the TMDB image size a load site actually needs.
 *
 * Stored URLs keep the size they were built with (backdrops are `original`, 1–2 MB each); only
 * the load sites rewrite them, so the data model, the cloud state and the Home cache stay as
 * they are. The rule is the smallest official TMDB width that still covers the slot, `original`
 * only above the largest one — the idea comes from Moviebase's `TmdbImageSize`
 * (MoviebaseApp/tmdb-kotlin, Apache 2.0).
 *
 * Only `image.tmdb.org/t/p/<width or original>/…` is touched. Addon posters, Plex/Jellyfin art,
 * channel logos and any other host come back unchanged, and a URL is never rewritten to a
 * larger size than the one it already carries.
 */
object TmdbImageSizing {

    /** Official TMDB widths per image type; [aspect] is width / height of the source image. */
    enum class Kind(internal val widths: IntArray, internal val aspect: Float) {
        /** Backdrops and episode stills (both 16:9). */
        BACKDROP(intArrayOf(300, 780, 1280), 16f / 9f),
        POSTER(intArrayOf(92, 154, 185, 342, 500, 780), 2f / 3f),
    }

    /**
     * Kind of the artwork a card slot shows. Without a poster, `MediaItem.image` carries the
     * backdrop file (`MediaRepository.toMediaItem`), so a portrait slot can hold a 16:9 image.
     * A landscape slot is sized as a backdrop either way: a poster there is cropped by its width.
     */
    fun cardArtworkKind(imageUrl: String, backdropUrl: String?, landscapeSlot: Boolean): Kind {
        if (landscapeSlot) return Kind.BACKDROP
        val isBackdropFile = backdropUrl != null &&
            imageUrl.substringAfterLast('/') == backdropUrl.substringAfterLast('/')
        return if (isBackdropFile) Kind.BACKDROP else Kind.POSTER
    }

    private val tmdbImageUrl = Regex("""^(https?://image\.tmdb\.org/t/p/)(original|w\d+)(/.+)$""")

    /**
     * Returns [url] with the smallest [kind] size that fills a [slotWidthPx] × [slotHeightPx] slot
     * drawn with a crop scale. Pass `0` for [slotHeightPx] when only the width is known.
     */
    fun forSlot(url: String, slotWidthPx: Int, slotHeightPx: Int, kind: Kind): String {
        if (slotWidthPx <= 0) return url
        val match = tmdbImageUrl.matchEntire(url) ?: return url
        val (prefix, currentSize, path) = match.destructured

        // A crop fills the slot on its tighter axis, so a wide image in a tall slot needs more width.
        val neededWidth = maxOf(slotWidthPx, ceil(slotHeightPx.coerceAtLeast(0) * kind.aspect).toInt())
        val chosenWidth = kind.widths.firstOrNull { it >= neededWidth }
        val currentWidth = currentSize.removePrefix("w").toIntOrNull()

        // Never ask for more than the stored URL already carries.
        if (currentWidth != null && (chosenWidth == null || chosenWidth >= currentWidth)) return url
        return if (chosenWidth == null) url else "${prefix}w$chosenWidth$path"
    }
}
