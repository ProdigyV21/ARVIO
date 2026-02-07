package com.arflix.tv.data.model

import java.util.UUID

/**
 * User profile - each profile has independent settings, Trakt, addons, etc.
 */
data class Profile(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val avatarColor: Long = ProfileColors.random(),
    val isKidsProfile: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val lastUsedAt: Long = System.currentTimeMillis()
)

/**
 * Predefined profile avatar colors (Netflix-style)
 */
object ProfileColors {
    val colors = listOf(
        0xFFE50914L, // Netflix Red
        0xFF1DB954L, // Green
        0xFF3B82F6L, // Blue
        0xFFF59E0BL, // Orange
        0xFF8B5CF6L, // Purple
        0xFFEC4899L, // Pink
        0xFF14B8A6L, // Teal
        0xFF6366F1L  // Indigo
    )

    fun random(): Long = colors.random()

    fun getByIndex(index: Int): Long = colors[index % colors.size]
}
