import re

with open('app/src/main/kotlin/com/arflix/tv/data/repository/IptvRepository.kt', 'r') as f:
    content = f.read()

# Since making requestJson suspend breaks non-suspend callers,
# let's instead provide a dedicated suspend requestJson function or just make the caller suspend

# The comment suggests "modify requestJson (and its usage here) to perform the OkHttp request via a suspendCancellableCoroutine..."
# We did modify requestJson, but we found it's called by fetchXtreamLiveChannels, which is a normal fun.
# If we look at where fetchXtreamLiveChannels is called (line 1176), we can just make fetchXtreamLiveChannels suspend!

old_live = """    private fun fetchXtreamLiveChannels(
        creds: XtreamCredentials,
        onProgress: (IptvLoadProgress) -> Unit
    ): List<IptvChannel> {"""
new_live = """    private suspend fun fetchXtreamLiveChannels(
        creds: XtreamCredentials,
        onProgress: (IptvLoadProgress) -> Unit
    ): List<IptvChannel> {"""

content = content.replace(old_live, new_live)

with open('app/src/main/kotlin/com/arflix/tv/data/repository/IptvRepository.kt', 'w') as f:
    f.write(content)
