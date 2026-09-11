package com.arflix.tv.ui.screens.tv.live

/** Keeps a menu-opening hold (and short synthetic repeats) out of the menu actions. */
internal class GuideSelectKeyGuard {
    var holdHandled = false
    private var currentDownTime = 0L
    private var currentEventTime = 0L
    private var blockedDownTime: Long? = null
    private var blockSyntheticBurst = false
    private var lastBlockedEventTime = 0L

    fun blockCurrentPress(includeSyntheticBurst: Boolean) {
        blockedDownTime = currentDownTime
        lastBlockedEventTime = currentEventTime
        blockSyntheticBurst = includeSyntheticBurst
    }

    fun consume(downTime: Long, eventTime: Long, isDown: Boolean, repeatCount: Int): Boolean {
        currentDownTime = downTime
        currentEventTime = eventTime
        val blocked = blockedDownTime ?: return false
        val samePress = downTime == blocked
        // Some remotes emit separate DOWN/UP pairs roughly 33ms apart during a hold.
        // Do not extend this into a 250ms dead zone for genuine menu clicks.
        val burstRepeat = blockSyntheticBurst && eventTime - lastBlockedEventTime in 0..80L
        if (samePress || burstRepeat || !isDown || repeatCount > 0) {
            blockedDownTime = downTime
            lastBlockedEventTime = eventTime
            if (!isDown && !blockSyntheticBurst) blockedDownTime = null
            return true
        }
        blockedDownTime = null
        blockSyntheticBurst = false
        return false
    }
}
