package com.arflix.tv.data.repository

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

/**
 * The three entry points that ask for the Stalker channel list used to download
 * it once each — measured 3 x 29 MB in 17 seconds on a portal with ~21k
 * channels. These tests pin down the sharing that turns that into one download.
 */
class StalkerChannelListLoaderTest {

    private var clock = 0L

    private fun TestScope.downloadScope(): CoroutineScope =
        CoroutineScope(SupervisorJob() + StandardTestDispatcher(testScheduler))

    private fun loader(
        scope: CoroutineScope,
        freshnessWindowMs: Long = 1_000L,
        isReusable: (String) -> Boolean = { true }
    ) = StalkerChannelListLoader(
        freshnessWindowMs = freshnessWindowMs,
        scope = scope,
        nowMs = { clock },
        isReusable = isReusable
    )

    @Test
    fun reusesTheListInsideTheFreshnessWindow() = runTest {
        val scope = downloadScope()
        val loader = loader(scope)
        var downloads = 0
        val fetch: suspend () -> String = { downloads++; "channels" }

        assertThat(loader.load("portal-a", fetch = fetch)).isEqualTo("channels")
        clock = 999
        assertThat(loader.load("portal-a", fetch = fetch)).isEqualTo("channels")

        assertThat(downloads).isEqualTo(1)
        scope.cancel()
    }

    @Test
    fun downloadsAgainOnceTheFreshnessWindowHasPassed() = runTest {
        val scope = downloadScope()
        val loader = loader(scope)
        var downloads = 0
        val fetch: suspend () -> String = { downloads++; "channels" }

        loader.load("portal-a", fetch = fetch)
        clock = 1_000
        loader.load("portal-a", fetch = fetch)

        assertThat(downloads).isEqualTo(2)
        scope.cancel()
    }

    @Test
    fun concurrentCallersShareOneDownload() = runTest {
        val scope = downloadScope()
        val loader = loader(scope)
        var downloads = 0
        val fetch: suspend () -> String = { downloads++; delay(2_000); "channels" }

        val startupPrefetch = async { loader.load("portal-a", fetch = fetch) }
        val snapshotLoad = async { loader.load("portal-a", fetch = fetch) }
        val guideBackfill = async { loader.load("portal-a", fetch = fetch) }
        advanceUntilIdle()

        assertThat(startupPrefetch.await()).isEqualTo("channels")
        assertThat(snapshotLoad.await()).isEqualTo("channels")
        assertThat(guideBackfill.await()).isEqualTo("channels")
        assertThat(downloads).isEqualTo(1)
        scope.cancel()
    }

    @Test
    fun aCallerThatGivesUpDoesNotCancelTheSharedDownload() = runTest {
        // The startup prefetch times out after 25 s; the snapshot load waiting
        // on the same download must still get its channels.
        val scope = downloadScope()
        val loader = loader(scope)
        var downloads = 0
        val fetch: suspend () -> String = { downloads++; delay(5_000); "channels" }

        val givesUp = launch { loader.load("portal-a", fetch = fetch) }
        advanceTimeBy(1_000)
        givesUp.cancel()
        advanceUntilIdle()

        assertThat(loader.load("portal-a", fetch = fetch)).isEqualTo("channels")
        assertThat(downloads).isEqualTo(1)
        scope.cancel()
    }

    @Test
    fun everyPortalKeepsItsOwnList() = runTest {
        // One entry per portal: a second portal neither reuses nor replaces the
        // first one's list, and asking the first one again costs nothing.
        val scope = downloadScope()
        val loader = loader(scope)
        val downloaded = mutableListOf<String>()
        val fetchA: suspend () -> String = { downloaded += "a"; "channels-a" }
        val fetchB: suspend () -> String = { downloaded += "b"; "channels-b" }

        assertThat(loader.load("portal-a", fetch = fetchA)).isEqualTo("channels-a")
        assertThat(loader.load("portal-b", fetch = fetchB)).isEqualTo("channels-b")
        assertThat(loader.load("portal-a", fetch = fetchA)).isEqualTo("channels-a")

        assertThat(downloaded).containsExactly("a", "b").inOrder()
        scope.cancel()
    }

    @Test
    fun aPortalThatFailedIsRetriedWhileTheOthersKeepTheirLists() = runTest {
        // ⭐ Prodigy's review of #677: with two portals configured, one that is
        // down must not hide behind one that answered. Its empty answer is not
        // remembered, so the next load asks it again — and the healthy portal
        // is not downloaded a second time for it.
        val scope = downloadScope()
        val loader = loader(scope, isReusable = { it.isNotEmpty() })
        val downloaded = mutableListOf<String>()
        var portalBIsUp = false
        val fetchA: suspend () -> String = { downloaded += "a"; "channels-a" }
        val fetchB: suspend () -> String = {
            downloaded += "b"
            if (portalBIsUp) "channels-b" else ""
        }

        loader.load("portal-a", fetch = fetchA)
        assertThat(loader.load("portal-b", fetch = fetchB)).isEmpty()

        portalBIsUp = true
        loader.load("portal-a", fetch = fetchA)
        assertThat(loader.load("portal-b", fetch = fetchB)).isEqualTo("channels-b")

        assertThat(downloaded).containsExactly("a", "b", "b").inOrder()
        scope.cancel()
    }

    @Test
    fun twoForcedCallersAtOnceShareOneDownload() = runTest {
        // Measured on device: two entry points reacted to one added playlist and
        // pulled the full channel list twice in the same second. Asking for
        // fresh data must mean "not the old list", not "a second portal
        // session".
        val scope = downloadScope()
        val loader = loader(scope)
        var downloads = 0
        val fetch: suspend () -> String = { downloads++; delay(2_000); "channels" }

        clock = 5_000
        val first = async { loader.load("portal-a", freshSinceMs = 5_000, fetch = fetch) }
        val second = async { loader.load("portal-a", freshSinceMs = 5_000, fetch = fetch) }
        advanceUntilIdle()

        assertThat(first.await()).isEqualTo("channels")
        assertThat(second.await()).isEqualTo("channels")
        assertThat(downloads).isEqualTo(1)
        scope.cancel()
    }

    @Test
    fun aForcedCallerTakesTheListThatArrivedWhileItWaitedItsTurn() = runTest {
        // ⭐ The bug measured on 09.09.: a playlist toggle wakes two entry
        // points, the repository lock serializes them, so the second never
        // meets a *running* download — it meets one that just finished. Both
        // asked at 5_000, so a list downloaded at 5_400 is newer than the
        // request and answers it. Skipping every remembered list instead cost a
        // second full 27.67 MB download.
        val scope = downloadScope()
        val loader = loader(scope, freshnessWindowMs = 300_000L)
        var downloads = 0
        val fetch: suspend () -> String = { downloads++; clock = 5_400; "channels" }

        clock = 5_000
        val askedAtMs = clock
        assertThat(loader.load("portal-a", freshSinceMs = askedAtMs, fetch = fetch)).isEqualTo("channels")
        assertThat(loader.load("portal-a", freshSinceMs = askedAtMs, fetch = fetch)).isEqualTo("channels")

        assertThat(downloads).isEqualTo(1)
        scope.cancel()
    }

    @Test
    fun aForcedCallerDoesNotTakeAListFromBeforeItAsked() = runTest {
        // The counterpart: pressing "Refresh IPTV" must reach the portal even
        // though the stored list is still well inside the freshness window.
        val scope = downloadScope()
        val loader = loader(scope)
        var downloads = 0
        val fetch: suspend () -> String = { downloads++; "channels-$downloads" }

        assertThat(loader.load("portal-a", fetch = fetch)).isEqualTo("channels-1")
        assertThat(loader.load("portal-a", fetch = fetch)).isEqualTo("channels-1")
        clock = 500
        assertThat(loader.load("portal-a", freshSinceMs = 500, fetch = fetch)).isEqualTo("channels-2")

        assertThat(downloads).isEqualTo(2)
        scope.cancel()
    }

    @Test
    fun anEditedPortalIsDownloadedAgainUnderItsNewKey() = runTest {
        // The key carries the portal's URL and MAC, so editing either of them
        // makes a different portal as far as this loader is concerned. Nothing
        // else discards a list: everything the app calls "invalidate" for (a
        // playlist, an EPG URL, a profile) leaves the portals alone.
        val scope = downloadScope()
        val loader = loader(scope)
        var downloads = 0
        val fetch: suspend () -> String = { downloads++; "channels" }

        loader.load("portal-a-old-mac", fetch = fetch)
        loader.load("portal-a-new-mac", fetch = fetch)

        assertThat(downloads).isEqualTo(2)
        scope.cancel()
    }

    @Test
    fun retainOnlyReleasesTheListsOfPortalsThatAreGone() = runTest {
        // Removing a portal means nobody calls load() for it again, so its list
        // and session would be held for the rest of the app run. Portals that
        // are still configured must not pay for that.
        val scope = downloadScope()
        val loader = loader(scope)
        val downloaded = mutableListOf<String>()
        val fetchA: suspend () -> String = { downloaded += "a"; "channels-a" }
        val fetchB: suspend () -> String = { downloaded += "b"; "channels-b" }

        loader.load("portal-a", fetch = fetchA)
        loader.load("portal-b", fetch = fetchB)

        // Portal B removed, A still configured: only B is dropped.
        loader.retainOnly(setOf("portal-a"))
        loader.load("portal-a", fetch = fetchA)
        loader.load("portal-b", fetch = fetchB)
        assertThat(downloaded).containsExactly("a", "b", "b").inOrder()

        // The last portal removed: everything goes.
        loader.retainOnly(emptySet())
        loader.load("portal-a", fetch = fetchA)
        assertThat(downloaded).containsExactly("a", "b", "b", "a").inOrder()
        scope.cancel()
    }

    @Test
    fun aDetachedDownloadCannotOverwriteTheOneThatReplacedIt() = runTest {
        // The portal is removed and re-added while a download is still running
        // — a portal edited back to its old URL and MAC does exactly this. The
        // detached download finishes last here; its result must not land.
        val scope = downloadScope()
        val loader = loader(scope)
        var downloads = 0
        val fetch: suspend () -> String = {
            val attempt = ++downloads
            // The first download is the slow one, so it lands after the second.
            delay(if (attempt == 1) 4_000 else 1_000)
            "channels-$attempt"
        }

        val detached = async { loader.load("portal-a", fetch = fetch) }
        advanceTimeBy(500)
        loader.retainOnly(emptySet())
        val replacement = async { loader.load("portal-a", fetch = fetch) }
        advanceUntilIdle()

        assertThat(detached.await()).isEqualTo("channels-1")
        assertThat(replacement.await()).isEqualTo("channels-2")
        assertThat(loader.load("portal-a", fetch = fetch)).isEqualTo("channels-2")
        assertThat(downloads).isEqualTo(2)
        scope.cancel()
    }

    @Test
    fun anEmptyResultIsNotRemembered() = runTest {
        // A failed handshake returns an empty list; remembering it would keep
        // the portal dark for the whole freshness window.
        val scope = downloadScope()
        val loader = loader(scope, isReusable = { it.isNotEmpty() })
        var downloads = 0
        val fetch: suspend () -> String = { downloads++; if (downloads == 1) "" else "channels" }

        assertThat(loader.load("portal-a", fetch = fetch)).isEmpty()
        assertThat(loader.load("portal-a", fetch = fetch)).isEqualTo("channels")
        assertThat(loader.load("portal-a", fetch = fetch)).isEqualTo("channels")

        assertThat(downloads).isEqualTo(2)
        scope.cancel()
    }

    @Test
    fun aFailedDownloadReachesTheCallerAndIsNotRemembered() = runTest {
        val scope = downloadScope()
        val loader = loader(scope)
        var downloads = 0
        val fetch: suspend () -> String = {
            downloads++
            if (downloads == 1) throw IllegalStateException("portal unreachable") else "channels"
        }

        val failure = runCatching { loader.load("portal-a", fetch = fetch) }.exceptionOrNull()
        assertThat(failure).isInstanceOf(IllegalStateException::class.java)
        assertThat(loader.load("portal-a", fetch = fetch)).isEqualTo("channels")

        assertThat(downloads).isEqualTo(2)
        scope.cancel()
    }
}
