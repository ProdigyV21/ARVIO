import re

with open('app/src/main/kotlin/com/arflix/tv/data/repository/IptvRepository.kt', 'r') as f:
    content = f.read()

# Fix refreshEpgForChannels
old_refresh = """            var errors = 0
            val allListings = fetchXtreamEpgListingsAsync(creds, streamIds) { _, hadError ->
                if (hadError) errors++
            }

            System.err.println("[EPG-Refresh] Done: ${allListings.size} listings, $errors errors")"""
new_refresh = """            val errorCount = java.util.concurrent.atomic.AtomicInteger(0)
            val allListings = fetchXtreamEpgListingsAsync(creds, streamIds) { _, hadError ->
                if (hadError) errorCount.incrementAndGet()
            }
            val errors = errorCount.get()

            System.err.println("[EPG-Refresh] Done: ${allListings.size} listings, $errors errors")"""
content = content.replace(old_refresh, new_refresh)

# Fix fetchXtreamShortEpg
old_fetch = """        var errors = 0
        var fetched = 0
        val total = toFetch.size
        val streamIds = toFetch.mapNotNull { resolveXtreamStreamId(it) }

        val allListings = fetchXtreamEpgListingsAsync(creds, streamIds) { _, hadError ->
            fetched++
            if (hadError) errors++
            if (fetched % 50 == 0) {
                val pct = (90 + ((fetched.toLong() * 8L) / total.toLong())).toInt().coerceIn(90, 98)
                onProgress(IptvLoadProgress("Loading EPG... $fetched/$total channels", pct))
            }
        }

        System.err.println("[EPG] Xtream short EPG done: ${allListings.size} listings, $fetched fetched, $errors errors")"""

new_fetch = """        val errorCount = java.util.concurrent.atomic.AtomicInteger(0)
        val fetchedCount = java.util.concurrent.atomic.AtomicInteger(0)
        val total = toFetch.size
        val streamIds = toFetch.mapNotNull { resolveXtreamStreamId(it) }

        val allListings = fetchXtreamEpgListingsAsync(creds, streamIds) { _, hadError ->
            val done = fetchedCount.incrementAndGet()
            if (hadError) errorCount.incrementAndGet()
            if (done % 50 == 0) {
                val pct = (90 + ((done.toLong() * 8L) / total.toLong())).toInt().coerceIn(90, 98)
                onProgress(IptvLoadProgress("Loading EPG... $done/$total channels", pct))
            }
        }
        val errors = errorCount.get()
        val fetched = fetchedCount.get()

        System.err.println("[EPG] Xtream short EPG done: ${allListings.size} listings, $fetched fetched, $errors errors")"""

content = content.replace(old_fetch, new_fetch)

# Also let's fix the missing closing brace in the docstring if that's what caused the compilation error earlier
content = content.replace("    /**\n     * Build IptvNowNext map from Xtream EPG listings.", "    /**\n     * Build IptvNowNext map from Xtream EPG listings.\n     */")

with open('app/src/main/kotlin/com/arflix/tv/data/repository/IptvRepository.kt', 'w') as f:
    f.write(content)
