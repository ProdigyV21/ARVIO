package com.arflix.tv.data.repository

import com.arflix.tv.data.api.StremioStream
import com.arflix.tv.data.model.StreamSource
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

/**
 * A message an addon sent in place of sources, e.g. AIOStreams' "[❌] Comet — Invalid API key".
 * Shown in the source list instead of being dropped, so "no sources" comes with its reason.
 */
data class AddonNotice(
    val addonId: String,
    val addonName: String,
    val title: String,
    val message: String
)

/**
 * Collects [AddonNotice]s for one source search. Put it in the search's coroutine context; the
 * addon fetches below it record into it without threading a list through every call.
 */
internal class AddonNoticeSink : AbstractCoroutineContextElement(Key) {
    companion object Key : CoroutineContext.Key<AddonNoticeSink>

    private val notices = ConcurrentLinkedQueue<AddonNotice>()

    fun add(notice: AddonNotice) {
        notices.add(notice)
    }

    fun snapshot(): List<AddonNotice> = notices.toList().distinct()
}

private const val AIOSTREAMS_INFO_URL = "https://github.com/Viren070/AIOStreams"

/**
 * AIOStreams reports failures as link-less streams pointing at its GitHub page, named "[❌] <what>"
 * with the reason in the description. Its statistics rows use the same link without the ❌, and
 * are left out.
 */
internal fun aioStreamsNotices(
    streams: List<StremioStream>,
    addonId: String,
    addonName: String
): List<AddonNotice> = streams.mapNotNull { stream ->
    if (stream.url != null || stream.infoHash != null || stream.ytId != null) return@mapNotNull null
    if (stream.externalUrl?.trim()?.trimEnd('/')?.equals(AIOSTREAMS_INFO_URL, ignoreCase = true) != true) {
        return@mapNotNull null
    }
    val name = stream.name?.trim().orEmpty()
    if (!name.contains("❌")) return@mapNotNull null
    val title = name.replace("[❌]", "").replace("❌", "").trim().ifBlank { addonName }
    val message = (stream.description ?: stream.title)?.trim().orEmpty()
    AddonNotice(addonId = addonId, addonName = addonName, title = title, message = message)
}

private const val ADDON_NOTICE_ROW_URL = "arvio-addon-notice://"

/**
 * A notice as a display-only row under its addon's sources, like the Telegram search row: listed
 * with its reason after the real sources, never counted, played or pre-warmed.
 */
internal fun addonNoticeRow(index: Int, notice: AddonNotice): StreamSource = StreamSource(
    source = "⚠️ ${notice.title}",
    addonName = notice.addonName,
    addonId = notice.addonId,
    quality = "",
    size = "",
    url = "$ADDON_NOTICE_ROW_URL$index",
    description = notice.message.ifBlank { null }
)

internal fun isAddonNoticeRow(stream: StreamSource): Boolean =
    stream.url?.startsWith(ADDON_NOTICE_ROW_URL) == true
