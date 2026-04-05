import re

with open('app/src/main/kotlin/com/arflix/tv/data/repository/IptvRepository.kt', 'r') as f:
    content = f.read()

# Add withTimeoutOrNull to imports if missing
if "import kotlinx.coroutines.withTimeoutOrNull" not in content:
    content = content.replace("import kotlinx.coroutines.withContext", "import kotlinx.coroutines.withContext\nimport kotlinx.coroutines.withTimeoutOrNull")

old_func = """    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    private suspend fun fetchXtreamEpgListingsAsync(
        creds: XtreamCredentials,
        streamIds: List<Int>,
        onStreamProcessed: (Int, Boolean) -> Unit = { _, _ -> }
    ): List<XtreamEpgListing> = withContext(Dispatchers.IO.limitedParallelism(20)) {"""

new_func = """    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    private suspend fun fetchXtreamEpgListingsAsync(
        creds: XtreamCredentials,
        streamIds: List<Int>,
        timeoutMillis: Long = 60_000L,
        onStreamProcessed: (Int, Boolean) -> Unit = { _, _ -> }
    ): List<XtreamEpgListing> = withTimeoutOrNull(timeoutMillis) {
        withContext(Dispatchers.IO.limitedParallelism(20)) {"""

content = content.replace(old_func, new_func)

old_end = """        }.awaitAll().flatten()
    }"""

new_end = """        }.awaitAll().flatten()
    } ?: emptyList()"""

content = content.replace(old_end, new_end)

with open('app/src/main/kotlin/com/arflix/tv/data/repository/IptvRepository.kt', 'w') as f:
    f.write(content)
