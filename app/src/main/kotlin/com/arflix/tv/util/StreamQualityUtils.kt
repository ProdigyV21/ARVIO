package com.arflix.tv.util

import com.arflix.tv.data.model.StreamSource

/**
 * Centralized quality detection utility for streams.
 * 
 * Strategy: Check filename first (more reliable), then fall back to provider metadata.
 * This ensures accurate quality badges even when providers have incorrect metadata.
 */

// Shared regex patterns - word-boundary prevents false positives like "DS4K", "PSEUDO4K"
private val REGEX_4K = Regex("""\b(4K|2160p|UHD|ULTRA)\b""", RegexOption.IGNORE_CASE)
private val REGEX_1080 = Regex("""\b(1080p|FHD|FULLHD)\b""", RegexOption.IGNORE_CASE)
private val REGEX_720 = Regex("""\b(720p|HD)\b""", RegexOption.IGNORE_CASE)
private val REGEX_480 = Regex("""\b(480p|SD)\b""", RegexOption.IGNORE_CASE)

/**
 * Extracts quality from a filename/torrent name string.
 * Uses word-boundary regex to avoid false positives.
 */
fun extractQualityFromFilename(filename: String): String? {
    if (filename.isBlank()) return null
    
    return when {
        REGEX_4K.containsMatchIn(filename) -> "4K"
        REGEX_1080.containsMatchIn(filename) -> "1080p"
        REGEX_720.containsMatchIn(filename) -> "720p"
        REGEX_480.containsMatchIn(filename) -> "480p"
        else -> null
    }
}

/**
 * Extracts quality from a StreamSource using hybrid approach:
 * 1. Check filename from behaviorHints.filename (Stremio metadata)
 * 2. Check source field (torrent/file name)
 * 3. Fall back to provider's quality field
 * 
 * Returns: Best guess quality string (e.g., "4K", "1080p", "720p", or original quality field)
 */
fun StreamSource.extractQuality(): String {
    // Priority 1: Stremio behavior hints filename
    behaviorHints?.filename?.let { filenameFromHints ->
        extractQualityFromFilename(filenameFromHints)?.let { return it }
    }
    
    // Priority 2: Source field (torrent or direct filename)
    extractQualityFromFilename(source)?.let { return it }
    
    // Priority 3: Fall back to provider's metadata
    return quality
}

/**
 * Gets quality score for sorting/filtering.
 * Higher scores = higher quality.
 */
fun getQualityScore(qualityString: String): Int = when {
    REGEX_4K.containsMatchIn(qualityString) -> 4
    REGEX_1080.containsMatchIn(qualityString) -> 3
    REGEX_720.containsMatchIn(qualityString) -> 2
    REGEX_480.containsMatchIn(qualityString) -> 1
    else -> 0
}

/**
 * Gets quality score from a StreamSource using hybrid detection.
 * Convenience function that combines extractQuality() + getQualityScore().
 */
fun StreamSource.getQualityScore(): Int = getQualityScore(extractQuality())

/**
 * Check if quality string matches a specific tier.
 */
fun isQuality4K(qualityString: String): Boolean = REGEX_4K.containsMatchIn(qualityString)
fun isQuality1080(qualityString: String): Boolean = REGEX_1080.containsMatchIn(qualityString)
fun isQuality720(qualityString: String): Boolean = REGEX_720.containsMatchIn(qualityString)
fun isQuality480(qualityString: String): Boolean = REGEX_480.containsMatchIn(qualityString)
