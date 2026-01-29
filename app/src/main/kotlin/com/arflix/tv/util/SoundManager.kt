package com.arflix.tv.util

import android.content.Context
import android.media.AudioManager
import android.util.Log
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
    private val TAG = "SoundManager"
    private val audioManager: AudioManager? = context.getSystemService(Context.AUDIO_SERVICE) as? AudioManager
    private var isEnabled = true

    init {
        Log.d(TAG, "Sound manager initialized")
    }

    /**
     * Play navigation move sound (arrow key press)
     * Uses system navigation sound
     */
    fun playMove(view: View?) {
        if (!isEnabled) return
        try {
            view?.playSoundEffect(SoundEffectConstants.NAVIGATION_DOWN)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to play move sound", e)
        }
    }

    /**
     * Play navigation up sound
     */
    fun playMoveUp(view: View?) {
        if (!isEnabled) return
        try {
            view?.playSoundEffect(SoundEffectConstants.NAVIGATION_UP)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to play move up sound", e)
        }
    }

    /**
     * Play navigation left sound
     */
    fun playMoveLeft(view: View?) {
        if (!isEnabled) return
        try {
            view?.playSoundEffect(SoundEffectConstants.NAVIGATION_LEFT)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to play move left sound", e)
        }
    }

    /**
     * Play navigation right sound
     */
    fun playMoveRight(view: View?) {
        if (!isEnabled) return
        try {
            view?.playSoundEffect(SoundEffectConstants.NAVIGATION_RIGHT)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to play move right sound", e)
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
        } catch (e: Exception) {
            Log.e(TAG, "Failed to play select sound", e)
        }
    }

    /**
     * Play back sound (back button)
     */
    fun playBack(view: View?) {
        if (!isEnabled) return
        try {
            view?.playSoundEffect(SoundEffectConstants.NAVIGATION_UP)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to play back sound", e)
        }
    }

    /**
     * Play sound effect without a view (uses AudioManager)
     */
    fun playSoundEffect(effectType: Int) {
        if (!isEnabled) return
        try {
            audioManager?.playSoundEffect(effectType, 1.0f)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to play sound effect", e)
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
