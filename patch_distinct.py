import re

with open('app/src/main/kotlin/com/arflix/tv/data/repository/IptvRepository.kt', 'r') as f:
    content = f.read()

# Fix Distinct
old_distinct = """        val toFetch = prioritized.take(2000)
        System.err.println("[EPG] Xtream short EPG: fetching ${toFetch.size}/${xtreamChannels.size} channels")
        if (toFetch.isEmpty()) return null

        val errorCount = java.util.concurrent.atomic.AtomicInteger(0)
        val fetchedCount = java.util.concurrent.atomic.AtomicInteger(0)
        val total = toFetch.size
        val streamIds = toFetch.mapNotNull { resolveXtreamStreamId(it) }"""

new_distinct = """        val streamIds = prioritized.mapNotNull { resolveXtreamStreamId(it) }.distinct().take(2000)
        System.err.println("[EPG] Xtream short EPG: fetching ${streamIds.size}/${xtreamChannels.size} unique streams")
        if (streamIds.isEmpty()) return null

        val errorCount = java.util.concurrent.atomic.AtomicInteger(0)
        val fetchedCount = java.util.concurrent.atomic.AtomicInteger(0)
        val total = streamIds.size"""

content = content.replace(old_distinct, new_distinct)

with open('app/src/main/kotlin/com/arflix/tv/data/repository/IptvRepository.kt', 'w') as f:
    f.write(content)
