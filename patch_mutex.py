import re

file_path = "app/src/main/kotlin/com/arflix/tv/data/repository/TraktSyncService.kt"
with open(file_path, "r") as f:
    content = f.read()

search_mutex = "    private val _syncEvents = MutableSharedFlow<SyncStatus>(extraBufferCapacity = 1)\n    val syncEvents: SharedFlow<SyncStatus> = _syncEvents.asSharedFlow()"
replace_mutex = search_mutex + "\n\n    private val supabaseAuthMutex = Mutex()"

if search_mutex in content:
    content = content.replace(search_mutex, replace_mutex)
    with open(file_path, "w") as f:
        f.write(content)
    print("Mutex added.")
else:
    print("Could not find mutex injection point.")
