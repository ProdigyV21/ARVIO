package com.arflix.tv.data.repository

/**
 * Tells whether another device saved the account document, from the server's revision alone.
 *
 * The revision changes on every save and reading it downloads nothing. The first revision seen only
 * becomes the baseline, and a missing revision (offline, older server) is never a change.
 */
internal class RemoteRevisionTracker {
    @Volatile private var baseline: String? = null

    /** Records [revision] and returns true when it differs from a baseline that was already known. */
    @Synchronized
    fun observe(revision: String?): Boolean {
        if (revision.isNullOrBlank()) return false
        val previous = baseline
        baseline = revision
        return previous != null && previous != revision
    }

    /** Sets the baseline after this device pulled, so its own pull is not reported as a change. */
    @Synchronized
    fun rebase(revision: String?) {
        if (!revision.isNullOrBlank()) baseline = revision
    }

    @Synchronized
    fun reset() {
        baseline = null
    }
}
