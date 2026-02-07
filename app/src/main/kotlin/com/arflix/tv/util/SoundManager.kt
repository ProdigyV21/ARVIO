package com.arflix.tv.util

import android.content.Context
import android.media.AudioManager
import android.view.SoundEffectConstants
import android.view.View
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Manages UI sound effects for navigation
 * Uses Android's built-in sound effect system for consistent TV experience
 */
@Singleton
class SoundManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val audioManager: AudioManager? = context.getSystemService(Context.AUDIO_SERVICE) as? AudioManager
    private var isEnabled = true

    /**
     * Play navigation move sound (arrow key press)
     * Uses system navigation sound
     */
    fun playMove(view: View?) {
        if (!isEnabled) return
        try {
            view?.playSoundEffect(SoundEffectConstants.NAVIGATION_DOWN)
        } catch (_: Exception) {
            // Silently handle errors
        }
    }

    /**
     * Play navigation up sound
     */
    fun playMoveUp(view: View?) {
        if (!isEnabled) return
        try {
            view?.playSoundEffect(SoundEffectConstants.NAVIGATION_UP)
        } catch (_: Exception) {
            // Silently handle errors
        }
    }

    /**
     * Play navigation left sound
     */
    fun playMoveLeft(view: View?) {
        if (!isEnabled) return
        try {
            view?.playSoundEffect(SoundEffectConstants.NAVIGATION_LEFT)
        } catch (_: Exception) {
            // Silently handle errors
        }
    }

    /**
     * Play navigation right sound
     */
    fun playMoveRight(view: View?) {
        if (!isEnabled) return
        try {
            view?.playSoundEffect(SoundEffectConstants.NAVIGATION_RIGHT)
        } catch (_: Exception) {
            // Silently handle errors
        }
    }

    /**
     * Play selection sound (enter/OK button)
     * Uses system click sound
     */
    fun playSelect(view: View?) {
        if (!isEnabled) return
        try {
            view?.playSoundEffect(SoundEffectConstants.CLICK)
        } catch (_: Exception) {
            // Silently handle errors
        }
    }

    /**
     * Play back sound (back button)
     */
    fun playBack(view: View?) {
        if (!isEnabled) return
        try {
            view?.playSoundEffect(SoundEffectConstants.NAVIGATION_UP)
        } catch (_: Exception) {
            // Silently handle errors
        }
    }

    /**
     * Play sound effect without a view (uses AudioManager)
     */
    fun playSoundEffect(effectType: Int) {
        if (!isEnabled) return
        try {
            audioManager?.playSoundEffect(effectType, 1.0f)
        } catch (_: Exception) {
            // Silently handle errors
        }
    }

    /**
     * Enable/disable sound effects
     */
    fun setEnabled(enabled: Boolean) {
        isEnabled = enabled
    }

    /**
     * Check if sound effects are enabled
     */
    fun isEnabled(): Boolean = isEnabled
}
