package com.arflix.tv.ui.screens.tv.live

import androidx.media3.common.C
import androidx.media3.common.Player
import androidx.media3.common.TrackSelectionOverride
import androidx.media3.common.TrackSelectionParameters
import androidx.media3.common.Tracks
import com.arflix.tv.util.normalizeLanguageCode
import java.util.Locale

/**
 * Subtitle and audio choices on live channels. Channels often carry their subtitles inside the
 * stream (DVB bitmap subtitles in MPEG-TS, one track per language) and more than one audio
 * language. Nothing on the Live TV screen used to choose between them, so subtitles only showed
 * when the device's system captions happened to be on in the same language, and the audio was
 * whatever the stream listed first.
 */

/** "off" (subtitles only), or a track's normalized language (or its position when it has none). */
internal const val LIVE_SUBTITLES_OFF = "off"

internal data class LiveTrackOption(
    val key: String,
    val label: String,
    val selected: Boolean,
)

/** What a channel starts with, from the profile's "Default subtitle" setting. */
internal sealed interface LiveSubtitleDefault {
    /** "Off", or nothing set: no subtitles until the viewer picks one. */
    data object Off : LiveSubtitleDefault

    /** "Forced": only tracks the stream marks as forced (foreign-language parts). */
    data object ForcedOnly : LiveSubtitleDefault

    /** A language: its track when the channel carries one. */
    data class Language(val code: String) : LiveSubtitleDefault
}

/**
 * The profile's "Default subtitle" setting for live channels. Blank and "Off" both mean off, as in
 * the VOD player; a language the app cannot place is treated as off too, rather than letting the
 * player pick whatever the device's caption settings would.
 */
internal fun liveSubtitleDefault(preference: String?): LiveSubtitleDefault {
    val value = preference?.trim().orEmpty()
    val lower = value.lowercase(Locale.ROOT)
    return when {
        value.isBlank() || lower in DISABLED_PREFERENCES -> LiveSubtitleDefault.Off
        lower == "forced" -> LiveSubtitleDefault.ForcedOnly
        else -> languageFromPreference(value)?.let { LiveSubtitleDefault.Language(it) } ?: LiveSubtitleDefault.Off
    }
}

/** The profile's "Default audio language" setting, or null for "Auto (Original)". */
internal fun audioPreferenceLanguage(preference: String?): String? {
    val value = preference?.trim().orEmpty()
    if (value.isBlank() || value.lowercase(Locale.ROOT).startsWith("auto") ||
        value.lowercase(Locale.ROOT).startsWith("original")
    ) return null
    return languageFromPreference(value)
}

/** The subtitle choices for what is playing: "Off" first, then one per language or track. */
internal fun liveSubtitleOptions(tracks: Tracks, offLabel: String, displayLocale: Locale = Locale.getDefault()) =
    liveTrackOptions(tracks, C.TRACK_TYPE_TEXT, offLabel, displayLocale)

/** The picker key of the "AI subtitles" entry (Live TV AI subtitles). */
internal const val LIVE_SUBTITLES_AI = "ai"

/**
 * [streamOptions] (from [liveSubtitleOptions]) plus an "AI subtitles" entry after the stream's own
 * tracks, when the feature is on. A channel without subtitle tracks gets "Off" and "AI subtitles".
 * While AI subtitles are on for the channel, that entry is the selected one.
 */
internal fun withAiSubtitlesOption(
    streamOptions: List<LiveTrackOption>,
    offLabel: String,
    aiLabel: String,
    aiAvailable: Boolean,
    aiSelected: Boolean,
): List<LiveTrackOption> {
    if (!aiAvailable) return streamOptions
    val base = streamOptions.ifEmpty { listOf(LiveTrackOption(LIVE_SUBTITLES_OFF, offLabel, selected = true)) }
    val withSelection = if (aiSelected) base.map { it.copy(selected = false) } else base
    return withSelection + LiveTrackOption(LIVE_SUBTITLES_AI, aiLabel, selected = aiSelected)
}

/**
 * The subtitle track AI subtitles read text from: a text track (not a bitmap one, which carries
 * no text), English first, never one already in [avoidLanguage] (that one needs no AI). Null when
 * the channel has none; it is then subtitled from its audio.
 */
internal fun liveCaptionSourceKey(tracks: Tracks, avoidLanguage: String?): String? {
    val textTracks = typedTracks(tracks, C.TRACK_TYPE_TEXT).filter { track ->
        !isBitmapSubtitle(track.group.getTrackFormat(track.trackIndex)) &&
            (avoidLanguage == null || track.language != avoidLanguage)
    }
    return (textTracks.firstOrNull { it.language == "en" } ?: textTracks.firstOrNull())?.key
}

/**
 * The channel's own subtitles to show while AI subtitles are on but cannot run (no or rejected
 * key, audio that cannot be decoded, quota or connection pause): [preferredLanguage] first, then
 * English, then any track (picture subtitles included: these are only shown). Null when the
 * channel has none.
 */
internal fun liveAiFallbackSubtitleKey(tracks: Tracks, preferredLanguage: String?): String? {
    val all = typedTracks(tracks, C.TRACK_TYPE_TEXT)
    return (all.firstOrNull { preferredLanguage != null && it.language == preferredLanguage }
        ?: all.firstOrNull { it.language == "en" }
        ?: all.firstOrNull())?.key
}

/**
 * True for picture subtitles (DVB, PGS, VobSub), which carry no text. Subtitles the player parses
 * during extraction arrive as `application/x-media3-cues`, with the original type kept in codecs.
 */
internal fun isBitmapSubtitle(format: androidx.media3.common.Format): Boolean =
    format.sampleMimeType in BITMAP_SUBTITLE_MIMES || format.codecs in BITMAP_SUBTITLE_MIMES

/** The key of the subtitle track selected now, or null when subtitles are off. */
internal fun liveSelectedSubtitleKey(tracks: Tracks): String? =
    typedTracks(tracks, C.TRACK_TYPE_TEXT).firstOrNull { it.selected }?.key

private val BITMAP_SUBTITLE_MIMES = setOf(
    androidx.media3.common.MimeTypes.APPLICATION_DVBSUBS,
    androidx.media3.common.MimeTypes.APPLICATION_PGS,
    androidx.media3.common.MimeTypes.APPLICATION_VOBSUB,
)

/** The audio choices for what is playing, one per language or track. */
internal fun liveAudioOptions(tracks: Tracks, displayLocale: Locale = Locale.getDefault()) =
    liveTrackOptions(tracks, C.TRACK_TYPE_AUDIO, offLabel = null, displayLocale)

/** Applies a choice from [liveSubtitleOptions]; false when that track is not in this stream. */
internal fun applyLiveSubtitleChoice(player: Player, key: String): Boolean =
    applyLiveTrackChoice(player, C.TRACK_TYPE_TEXT, key)

/** Applies a choice from [liveAudioOptions]; false when that track is not in this stream. */
internal fun applyLiveAudioChoice(player: Player, key: String): Boolean =
    applyLiveTrackChoice(player, C.TRACK_TYPE_AUDIO, key)

/** Back to the profile's default subtitle choice, dropping any choice made on the previous channel. */
internal fun resetLiveSubtitleChoice(player: Player, default: LiveSubtitleDefault) {
    player.trackSelectionParameters = player.trackSelectionParameters.withLiveSubtitleDefault(default)
}

/**
 * Text-track parameters for [default]. Every case sets the text preferences explicitly: the
 * player starts from the device's caption settings (their language and caption roles), which
 * would otherwise turn subtitles on with the profile set to Off, or pick a language it did not ask for.
 */
internal fun TrackSelectionParameters.withLiveSubtitleDefault(default: LiveSubtitleDefault): TrackSelectionParameters =
    buildUpon()
        .clearOverridesOfType(C.TRACK_TYPE_TEXT)
        .setPreferredTextRoleFlags(0)
        .setSelectUndeterminedTextLanguage(false)
        .apply {
            when (default) {
                LiveSubtitleDefault.Off -> setTrackTypeDisabled(C.TRACK_TYPE_TEXT, true)
                    .setPreferredTextLanguages()
                    .setIgnoredTextSelectionFlags(0)
                // Without a preferred language only forced and default-flagged tracks are picked;
                // ignoring the default flag leaves the forced ones.
                LiveSubtitleDefault.ForcedOnly -> setTrackTypeDisabled(C.TRACK_TYPE_TEXT, false)
                    .setPreferredTextLanguages()
                    .setIgnoredTextSelectionFlags(C.SELECTION_FLAG_DEFAULT)
                is LiveSubtitleDefault.Language -> setTrackTypeDisabled(C.TRACK_TYPE_TEXT, false)
                    .setPreferredTextLanguages(default.code)
                    .setIgnoredTextSelectionFlags(0)
            }
        }
        .build()

/** Back to automatic audio selection: the preferred language, if the profile has one. */
internal fun resetLiveAudioChoice(player: Player, preferredLanguage: String?) {
    player.trackSelectionParameters = player.trackSelectionParameters.buildUpon()
        .clearOverridesOfType(C.TRACK_TYPE_AUDIO)
        .apply { if (preferredLanguage != null) setPreferredAudioLanguages(preferredLanguage) }
        .build()
}

private fun liveTrackOptions(tracks: Tracks, type: Int, offLabel: String?, displayLocale: Locale): List<LiveTrackOption> {
    val list = typedTracks(tracks, type)
    if (list.isEmpty()) return emptyList()
    val options = mutableListOf<LiveTrackOption>()
    if (offLabel != null) {
        options += LiveTrackOption(LIVE_SUBTITLES_OFF, offLabel, selected = list.none { it.selected })
    }
    list.distinctBy { it.key }.forEach { track ->
        options += LiveTrackOption(track.key, track.label(displayLocale), selected = list.any { it.key == track.key && it.selected })
    }
    return options
}

private fun applyLiveTrackChoice(player: Player, type: Int, key: String): Boolean {
    val builder = player.trackSelectionParameters.buildUpon().clearOverridesOfType(type)
    if (key == LIVE_SUBTITLES_OFF && type == C.TRACK_TYPE_TEXT) {
        player.trackSelectionParameters = builder.setTrackTypeDisabled(type, true).build()
        return true
    }
    val track = typedTracks(player.currentTracks, type).firstOrNull { it.key == key } ?: return false
    player.trackSelectionParameters = builder
        .setTrackTypeDisabled(type, false)
        .addOverride(TrackSelectionOverride(track.group.mediaTrackGroup, track.trackIndex))
        .build()
    return true
}

private class LiveTrack(
    val group: Tracks.Group,
    val trackIndex: Int,
    val key: String,
    val language: String?,
    val name: String?,
    val selected: Boolean,
    val position: Int,
) {
    fun label(displayLocale: Locale): String {
        val languageName = language
            ?.let { Locale.forLanguageTag(it).getDisplayLanguage(displayLocale) }
            ?.takeIf { it.isNotBlank() && !it.equals(language, ignoreCase = true) }
            ?.replaceFirstChar { it.titlecase(displayLocale) }
        return listOfNotNull(languageName ?: language, name?.takeIf { it.isNotBlank() && it != languageName })
            .joinToString(" · ")
            .ifBlank { "#$position" }
    }
}

private fun typedTracks(tracks: Tracks, type: Int): List<LiveTrack> = buildList {
    var position = 0
    tracks.groups.filter { it.type == type }.forEach { group ->
        for (i in 0 until group.length) {
            position++
            if (!group.isTrackSupported(i)) continue
            val format = group.getTrackFormat(i)
            val language = format.language?.takeIf { it.isNotBlank() && it != C.LANGUAGE_UNDETERMINED }
                ?.let(::normalizeLanguage)
            add(LiveTrack(group, i, language ?: "track$position", language, format.label, group.isTrackSelected(i), position))
        }
    }
}

/**
 * A settings value ("Hebrew", "Portuguese (Brazil)", "pt-BR", "heb") as a player language code,
 * using the app's shared mapping ([normalizeLanguageCode], as the VOD player does) and the
 * platform's language names for the rest. Null when it is not a language.
 */
private fun languageFromPreference(value: String): String? {
    val mapped = normalizeLanguageCode(value)
    if (LANGUAGE_TAG.matches(mapped)) return normalizeLanguage(mapped)
    val byName = Locale.getAvailableLocales().firstOrNull {
        it.getDisplayLanguage(Locale.ENGLISH).equals(value, ignoreCase = true)
    } ?: return null
    return normalizeLanguage(byName.language)
}

/**
 * Track language codes in the form [liveSubtitleOptions] keys them by: ISO 639-2 and legacy codes
 * to two letters ("heb", "iw" -> "he"), regional tags kept ("pt-BR" -> "pt-br").
 */
private fun normalizeLanguage(code: String): String {
    val lower = code.lowercase(Locale.ROOT).replace('_', '-')
    val primary = lower.substringBefore('-')
    val region = lower.substringAfter('-', missingDelimiterValue = "")
    val twoLetter = when {
        primary == "iw" || primary == "heb" -> "he"
        primary.length == 3 -> Locale.getISOLanguages().firstOrNull { Locale(it).isO3Language == primary } ?: primary
        else -> primary
    }
    return if (region.isEmpty()) twoLetter else "$twoLetter-$region"
}

private val LANGUAGE_TAG = Regex("[a-z]{2,3}(-[a-z0-9]{2,8})*")

private val DISABLED_PREFERENCES = setOf("off", "none", "no subtitles", "disabled")
