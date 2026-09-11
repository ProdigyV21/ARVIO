package com.arflix.tv.ui.screens.tv.live

import com.arflix.tv.data.model.IptvChannel

/**
 * Startup decisions for the Live TV screen, kept out of the composable so they
 * can be unit tested. The screen itself is a single very large @Composable, so
 * behaviour expressed inline there can only be verified on a device — these
 * rules are the parts users actually complained about, so they live here.
 */
object LiveTvStartup {

    enum class GuideBackAction { OPEN_CATEGORIES, EXIT_TV }

    /**
     * Which channel Live TV should open on.
     *
     * Order: an explicit request (deep link, "continue watching" action) wins;
     * otherwise resume the channel the session last recorded. The session
     * already persisted `lastChannelId` but nothing consumed it on entry, so
     * Live TV always reopened at the top of the list.
     *
     * A remembered id that is no longer in the playlists is ignored, so a
     * removed channel can't pin the screen to something that cannot be shown.
     */
    fun resumeChannelId(
        explicitChannelId: String?,
        lastChannelId: String?,
        availableChannelIds: Set<String>,
    ): String? {
        explicitChannelId?.takeIf { it.isNotBlank() }?.let { return it }
        val remembered = lastChannelId?.trim().orEmpty()
        if (remembered.isEmpty()) return null
        // An empty channel set means the list hasn't loaded yet — keep the
        // remembered id so it can be honoured once channels arrive, rather than
        // discarding it and defaulting to the top of the list.
        if (availableChannelIds.isEmpty()) return remembered
        return remembered.takeIf { it in availableChannelIds }
    }

    /**
     * Resolves the first playable channel after the filtered guide has loaded.
     * Explicit navigation wins, followed by the persisted session channel,
     * then a favorite and finally provider order.
     */
    fun chooseStartupChannelId(
        availableChannelIds: Set<String>,
        firstAvailableChannelId: String?,
        explicitChannelId: String?,
        sessionLastChannelId: String?,
        hasOpenedBefore: Boolean,
        favoriteChannelIds: List<String>,
        isFullyLoaded: Boolean,
        resolvedSessionChannelId: String? = null,
    ): String? {
        // Prefer the in-window match so the guide can also scroll to it.
        explicitChannelId
            ?.takeIf { it in availableChannelIds }
            ?.let { return it }
        // Still honour it when it is NOT in availableChannelIds. A caller-supplied channel
        // (Home's Favorite TV row, launcher deep links) is an instruction, not a hint, and
        // on a large playlist it is routinely outside the currently paged category window.
        // Falling through from here returned firstAvailableChannelId — a completely
        // unrelated channel — which is what made picking a favourite on Home tune channel
        // #1. LiveTvScreen hydrates an out-of-window channel by id from the store, so
        // returning it here is safe.
        if (explicitChannelId != null) return explicitChannelId

        if (hasOpenedBefore) {
            // A validated SQLite lookup is authoritative even when the saved
            // channel is not among the first 144 rows of the selected category.
            resolvedSessionChannelId?.takeIf { it.isNotBlank() && it == sessionLastChannelId }
                ?.let { return it }
            sessionLastChannelId
                ?.takeIf { it.isNotBlank() && it in availableChannelIds }
                ?.let { return it }
            if (!sessionLastChannelId.isNullOrBlank() && !isFullyLoaded) return null
        }

        favoriteChannelIds.firstOrNull { it in availableChannelIds }?.let { return it }
        if (favoriteChannelIds.isNotEmpty() && !isFullyLoaded) return null
        return firstAvailableChannelId
    }

    fun resumeCategoryId(lastGroupName: String?, availableCategoryIds: Set<String>): String {
        val remembered = lastGroupName?.trim().orEmpty()
        return remembered.takeIf { it.isNotBlank() && (it == "all" || it in availableCategoryIds) }
            ?: "all"
    }

    fun guideBackAction(isTouchDevice: Boolean, categoryDrawerOpen: Boolean): GuideBackAction =
        if (!isTouchDevice && !categoryDrawerOpen) GuideBackAction.OPEN_CATEGORIES else GuideBackAction.EXIT_TV

    fun anchoredWindowOffset(channelIndex: Int, visibleRowsBeforeAnchor: Int): Int =
        if (channelIndex < 0) 0 else (channelIndex - visibleRowsBeforeAnchor.coerceAtLeast(0)).coerceAtLeast(0)

    /**
     * Whether the sidebar may claim D-pad focus right now.
     *
     * While channels are still loading the list recomposes underneath the
     * focused item, Compose drops focus, and the focus effect used to grab it
     * back — which is what made the selector jump in unrelated directions when
     * a user pressed a direction key during load. Touch devices never take
     * this focus at all.
     */
    fun shouldClaimSidebarFocus(
        isTouchDevice: Boolean,
        isCategoryZoneActive: Boolean,
        channelsLoaded: Boolean,
    ): Boolean = !isTouchDevice && isCategoryZoneActive && channelsLoaded

    /**
     * Whether the channel-search field should be focused.
     *
     * The signal seeds at 0 so opening Live TV does not slam focus into the
     * search box; only an explicit user action (which bumps the signal) does.
     */
    fun shouldFocusSearch(focusSearchSignal: Int): Boolean = focusSearchSignal > 0

    /**
     * Whether the channel-search row may take D-pad focus.
     *
     * Search is the first focusable row in the sidebar, so while the playlist
     * is still loading Compose parks the selector there by default — and
     * "down" from search selects the first category, which does not exist yet,
     * so every key press is swallowed and the selector looks frozen. Keeping
     * search out of the focus order until there is a category to move to sends
     * that initial focus straight to the category list instead.
     */
    fun searchIsReachable(categoryCount: Int): Boolean = categoryCount > 0

    /**
     * Where the selector lands when Live TV opens.
     *
     * This used to be the search row on purpose ("Default IPTV entry is the
     * playlist/category rail, focused on Search"), which is exactly what users
     * reported as broken: the selector opened inside the search box, and since
     * "down" from search selects the first category — which does not exist
     * until the playlist has parsed — it stayed stuck there through the entire
     * load. The categories are the useful landing spot; search is one press up.
     */
    enum class EntryFocus { CATEGORY_LIST, NONE }

    fun entryFocus(isTouchDevice: Boolean, hasChannels: Boolean): EntryFocus = when {
        isTouchDevice -> EntryFocus.NONE
        !hasChannels -> EntryFocus.NONE
        else -> EntryFocus.CATEGORY_LIST
    }

    /**
     * How long the sidebar keeps re-claiming the selector after Live TV opens.
     *
     * The mini player attaches its video surface shortly after the screen
     * appears; that takes the platform focus, and Compose then falls back to
     * the first focusable row — the search box. Roughly two seconds of retries
     * covers stream start-up on a slow TV without fighting the user afterwards.
     */
    const val INITIAL_FOCUS_ATTEMPTS: Int = 25
    const val INITIAL_FOCUS_RETRY_MS: Long = 80L

    /** Ids of the channels currently available, for [resumeChannelId]. */
    fun channelIds(channels: List<IptvChannel>): Set<String> =
        channels.mapTo(LinkedHashSet(channels.size)) { it.id }
}
