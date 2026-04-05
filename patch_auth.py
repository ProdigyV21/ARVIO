import re

file_path = "app/src/main/kotlin/com/arflix/tv/data/repository/TraktSyncService.kt"
with open(file_path, "r") as f:
    content = f.read()

# 1. Add Mutex property to TraktSyncService
search_mutex = "    private val traktSyncMutex = Mutex()"
replace_mutex = "    private val traktSyncMutex = Mutex()\n    private val supabaseAuthMutex = Mutex()"

if search_mutex in content:
    content = content.replace(search_mutex, replace_mutex)
    print("Mutex added.")
else:
    print("Could not find mutex injection point.")

# 2. Update executeSupabaseCall to use Mutex
search_auth = """    private suspend fun <T> executeSupabaseCall(
        operation: String,
        block: suspend (String) -> T
    ): T {
        // Try getting auth, force-refresh if initial attempt fails
        var auth = getSupabaseAuth()
        if (auth == null) {
            val refreshed = authRepository.refreshAccessToken()
            auth = if (!refreshed.isNullOrBlank()) "Bearer $refreshed" else null
        }
        if (auth == null) throw IllegalStateException("Supabase auth failed")
        return try {
            block(auth)
        } catch (e: HttpException) {
            if (e.code() == 401) {
                val refreshed = authRepository.refreshAccessToken()
                if (!refreshed.isNullOrBlank()) {
                    return block("Bearer $refreshed")
                }
            }
            throw e
        }
    }"""

replace_auth = """    private suspend fun <T> executeSupabaseCall(
        operation: String,
        block: suspend (String) -> T
    ): T {
        // Try getting auth, force-refresh if initial attempt fails
        var auth = getSupabaseAuth()
        if (auth == null) {
            val refreshed = supabaseAuthMutex.withLock {
                authRepository.refreshAccessToken()
            }
            auth = if (!refreshed.isNullOrBlank()) "Bearer $refreshed" else null
        }
        if (auth == null) throw IllegalStateException("Supabase auth failed")
        return try {
            block(auth)
        } catch (e: HttpException) {
            if (e.code() == 401) {
                val refreshed = supabaseAuthMutex.withLock {
                    authRepository.refreshAccessToken()
                }
                if (!refreshed.isNullOrBlank()) {
                    return block("Bearer $refreshed")
                }
            }
            throw e
        }
    }"""

if search_auth in content:
    content = content.replace(search_auth, replace_auth)
    print("Auth patched.")
else:
    print("Could not find auth injection point.")

with open(file_path, "w") as f:
    f.write(content)
