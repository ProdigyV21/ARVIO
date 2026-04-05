import re

file_path = "app/src/main/kotlin/com/arflix/tv/data/repository/TraktSyncService.kt"
with open(file_path, "r") as f:
    content = f.read()

search_catch = """                            executeSupabaseCall("delete stale playback") { auth ->
                                supabaseApi.deleteWatchHistory(
                                    auth = auth,
                                    userId = "eq.$userId",
                                    showTmdbId = record.showTmdbId?.let { "eq.$it" },
                                    mediaType = "eq.${record.mediaType}",
                                    season = record.season?.let { "eq.$it" },
                                    episode = record.episode?.let { "eq.$it" },
                                    source = "eq.${profileHistorySource("trakt")}"
                                )
                            }
                        } catch (e: Exception) {
                        }"""

replace_catch = """                            executeSupabaseCall("delete stale playback") { auth ->
                                supabaseApi.deleteWatchHistory(
                                    auth = auth,
                                    userId = "eq.$userId",
                                    showTmdbId = record.showTmdbId?.let { "eq.$it" },
                                    mediaType = "eq.${record.mediaType}",
                                    season = record.season?.let { "eq.$it" },
                                    episode = record.episode?.let { "eq.$it" },
                                    source = "eq.${profileHistorySource("trakt")}"
                                )
                            }
                        } catch (e: Exception) {
                            if (e is kotlinx.coroutines.CancellationException) throw e
                        }"""

if search_catch in content:
    content = content.replace(search_catch, replace_catch)
    with open(file_path, "w") as f:
        f.write(content)
    print("CancellationException catch block patched.")
else:
    print("Could not find cancellation catch block injection point.")
