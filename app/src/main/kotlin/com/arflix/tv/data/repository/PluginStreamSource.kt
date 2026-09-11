package com.arflix.tv.data.repository

import com.arflix.tv.data.model.ProxyHeaders
import com.arflix.tv.data.model.StreamBehaviorHints
import com.arflix.tv.data.model.StreamSource
import com.arflix.tv.domain.model.LocalScraperResult

internal fun LocalScraperResult.toStreamSource(): StreamSource = StreamSource(
    source = title,
    addonName = provider ?: name ?: "Plugin",
    addonId = "plugin_${provider?.lowercase()?.replace(" ", "_") ?: "unknown"}",
    quality = quality ?: "Unknown",
    size = size ?: "",
    url = url,
    infoHash = infoHash,
    behaviorHints = headers?.let { StreamBehaviorHints(notWebReady = false, proxyHeaders = ProxyHeaders(request = it)) }
)
