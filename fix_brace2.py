import re

with open('app/src/main/kotlin/com/arflix/tv/data/repository/IptvRepository.kt', 'r') as f:
    content = f.read()

# Since withTimeoutOrNull returns T? and our signature returns List<XtreamEpgListing>,
# we must apply the elvis operator properly, e.g. at the end of withTimeoutOrNull

old_fetch = """    ): List<XtreamEpgListing> = withTimeoutOrNull(timeoutMillis) {
        withContext(Dispatchers.IO.limitedParallelism(20)) {
        val sampleLogged = java.util.concurrent.atomic.AtomicBoolean(false)
        streamIds.map { sid ->
            async {
                var hadError = false
                val url = "${creds.baseUrl}/player_api.php?username=${creds.username}" +
                    "&password=${creds.password}&action=get_short_epg&stream_id=$sid&limit=12"
                var listings: List<XtreamEpgListing>? = null
                try {
                    var resp: XtreamEpgResponse? = requestJson(url, XtreamEpgResponse::class.java)
                    listings = resp?.epgListings
                    if (listings.isNullOrEmpty()) {
                        val fallbackUrl = "${creds.baseUrl}/player_api.php?username=${creds.username}" +
                            "&password=${creds.password}&action=get_short_epg&stream_id=$sid"
                        resp = requestJson(fallbackUrl, XtreamEpgResponse::class.java)
                        listings = resp?.epgListings
                    }
                    if (!listings.isNullOrEmpty() && sampleLogged.compareAndSet(false, true)) {
                        val sample = listings.first()
                        System.err.println("[EPG] Sample response for stream_id=$sid: channelId=${sample.channelId} epgId=${sample.epgId} streamId=${sample.streamId} start=${sample.start} startTs=${sample.startTimestamp} title=${sample.title?.take(40)}")
                    }
                } catch (_: Exception) { hadError = true }
                onStreamProcessed(sid, hadError)
                listings ?: emptyList()
            }
        }.awaitAll().flatten()
    } ?: emptyList()
    }"""

new_fetch = """    ): List<XtreamEpgListing> {
        val result = withTimeoutOrNull(timeoutMillis) {
            withContext(Dispatchers.IO.limitedParallelism(20)) {
                val sampleLogged = java.util.concurrent.atomic.AtomicBoolean(false)
                streamIds.map { sid ->
                    async {
                        var hadError = false
                        val url = "${creds.baseUrl}/player_api.php?username=${creds.username}" +
                            "&password=${creds.password}&action=get_short_epg&stream_id=$sid&limit=12"
                        var listings: List<XtreamEpgListing>? = null
                        try {
                            var resp: XtreamEpgResponse? = requestJson(url, XtreamEpgResponse::class.java)
                            listings = resp?.epgListings
                            if (listings.isNullOrEmpty()) {
                                val fallbackUrl = "${creds.baseUrl}/player_api.php?username=${creds.username}" +
                                    "&password=${creds.password}&action=get_short_epg&stream_id=$sid"
                                resp = requestJson(fallbackUrl, XtreamEpgResponse::class.java)
                                listings = resp?.epgListings
                            }
                            if (!listings.isNullOrEmpty() && sampleLogged.compareAndSet(false, true)) {
                                val sample = listings.first()
                                System.err.println("[EPG] Sample response for stream_id=$sid: channelId=${sample.channelId} epgId=${sample.epgId} streamId=${sample.streamId} start=${sample.start} startTs=${sample.startTimestamp} title=${sample.title?.take(40)}")
                            }
                        } catch (_: Exception) { hadError = true }
                        onStreamProcessed(sid, hadError)
                        listings ?: emptyList()
                    }
                }.awaitAll().flatten()
            }
        }
        return result ?: emptyList()
    }"""

content = content.replace(old_fetch, new_fetch)

with open('app/src/main/kotlin/com/arflix/tv/data/repository/IptvRepository.kt', 'w') as f:
    f.write(content)
