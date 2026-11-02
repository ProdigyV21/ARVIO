package com.arflix.tv.data.repository

import android.os.Build
import android.os.Bundle
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.datastore.preferences.core.booleanPreferencesKey
import com.arflix.tv.BuildConfig
import com.arflix.tv.di.PartnerTestAccess
import com.arflix.tv.util.settingsDataStore
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import org.junit.Assert.*
import org.junit.Assume.assumeTrue
import org.junit.Test
import org.junit.runner.RunWith

/** Opt-in real-account test. Refuses physical devices and any non-QA signed-in account. */
@RunWith(AndroidJUnit4::class)
class PartnerLiveDeviceTest {
    @Test fun assignedCatalogsAndXtreamReachTheCustomer() = runBlocking {
        val args = InstrumentationRegistry.getArguments()
        assumeTrue(args.getString("partnerLive") == "true")
        assertTrue("Use the Partner-enabled test build", BuildConfig.PARTNER_MANAGEMENT_ENABLED)
        assertTrue("Emulator only", Build.FINGERPRINT.contains("generic") || Build.MODEL.contains("sdk"))
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val context = instrumentation.targetContext
        val access = EntryPointAccessors.fromApplication(context, PartnerTestAccess::class.java)
        val auth = access.auth()
        auth.checkAuthState()
        val account = auth.authState.value as? AuthState.Authenticated
        val email = args.getString("qaEmail") ?: error("QA email required")
        assertEquals("Only the designated QA account is allowed", "arvio.app@gmail.com", email)
        assertTrue("Refusing to replace another account", account == null || account.email == email)
        if (account == null) {
            val password = args.getString("qaPassword") ?: error("Supply the QA password at runtime")
            assertTrue("QA sign-in failed", auth.signIn(email, password).isSuccess)
        }
        val partner = access.partner()
        val worker = launch(Dispatchers.Default) { partner.runForegroundSession() }
        try {
            val delivery = withTimeout(90_000) { partner.deliveries.first { it != null } }!!
            assertTrue("Expected assigned IPTV", delivery.playlists.isNotEmpty())
            val expectedTitles = args.getString("expectedTitles", "QA - Harry Potter|QA - Star Wars").split('|')
            val catalogs = access.catalogs().observeCatalogs().first()
            assertEquals(expectedTitles, catalogs.map { it.title })
            val personal = access.catalogs().getPersonalCatalogs()
            assertTrue("Managed catalogs leaked into personal storage", personal.none { PartnerCatalogs.isManaged(it.id) })
            assertTrue("Managed playlist missing from effective configuration",
                access.iptv().observeConfig().first().playlists.any { PartnerCatalogs.isManaged(it.id) })
            assertTrue("Managed playlist leaked into personal storage",
                access.iptv().observePersonalConfig().first().playlists.none { PartnerCatalogs.isManaged(it.id) })
            val persisted = context.settingsDataStore.data.first().asMap().values.joinToString()
            assertFalse("Provider URL leaked into personal settings", persisted.contains(delivery.playlists.first().m3uUrl))
            val profile = access.profiles().getProfileIdSync()
            val effective = access.preferences().data.first()
            assertEquals(true, effective[booleanPreferencesKey("profile_${profile}_watchlist_on_home")])
            assertEquals(false, effective[booleanPreferencesKey("profile_${profile}_trailer_auto_play")])
            for (catalog in catalogs) {
                val row = withTimeout(60_000) { access.media().loadCustomCatalog(catalog, maxItems = 4) }
                assertTrue("Assigned list did not load: ${catalog.title}", row?.items?.isNotEmpty() == true)
                instrumentation.sendStatus(0, Bundle().apply {
                    putString("stream", "PARTNER_LIST title=${catalog.title} items=${row!!.items.size}\n")
                })
            }
            withTimeout(20_000) { partner.acknowledgedRevision.first { it >= delivery.revision } }
            instrumentation.sendStatus(0, Bundle().apply {
                putString("stream", "PARTNER_DELIVERY revision=${delivery.revision} lists=${catalogs.size} playlists=${delivery.playlists.size} personalStorageClean=true\n")
            })
            if (args.getString("checkIptv") == "true") {
                val snapshot = withTimeout(180_000) { access.iptv().loadSnapshot(allowNetworkEpgFetch = false) }
                assertTrue("No provider channels returned", snapshot.channels.isNotEmpty())
                instrumentation.sendStatus(0, Bundle().apply {
                    putString("stream", "PARTNER_IPTV channels=${snapshot.channels.size}\n")
                })
            }
        } finally { worker.cancelAndJoin() }
    }

    @Test fun publishedChangesReachRunningCustomer() = runBlocking {
        val args = InstrumentationRegistry.getArguments()
        assumeTrue(args.getString("partnerUpdate") == "true")
        assertTrue(BuildConfig.PARTNER_MANAGEMENT_ENABLED)
        assertTrue("Emulator only", Build.FINGERPRINT.contains("generic") || Build.MODEL.contains("sdk"))
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val access = EntryPointAccessors.fromApplication(instrumentation.targetContext, PartnerTestAccess::class.java)
        access.auth().checkAuthState()
        assertEquals("arvio.app@gmail.com", (access.auth().authState.value as? AuthState.Authenticated)?.email)
        val partner = access.partner()
        val worker = launch(Dispatchers.Default) { partner.runForegroundSession() }
        try {
            val initial = withTimeout(90_000) { partner.deliveries.first { it != null } }!!
            val target = args.getString("targetRevision")!!.toInt()
            assertTrue("Start this test before publishing", initial.revision < target)
            instrumentation.sendStatus(0, Bundle().apply {
                putString("stream", "PARTNER_WAITING initialRevision=${initial.revision} targetRevision=$target\n")
            })
            val updated = withTimeout(240_000) { partner.deliveries.first { it != null && it.revision >= target } }!!
            val catalogs = access.catalogs().observeCatalogs().first()
            assertEquals(args.getString("expectedTitles")!!.split('|'), catalogs.map { it.title })
            assertTrue("Old catalog remained after publication", catalogs.none { it.title == "QA - Harry Potter" })
            for (catalog in catalogs) {
                assertTrue("Updated list did not load", access.media().loadCustomCatalog(catalog, maxItems = 4)?.items?.isNotEmpty() == true)
            }
            withTimeout(20_000) { partner.acknowledgedRevision.first { it >= updated.revision } }
            instrumentation.sendStatus(0, Bundle().apply {
                putString("stream", "PARTNER_UPDATED revision=${updated.revision} lists=${catalogs.size} withoutRestart=true\n")
            })
        } finally { worker.cancelAndJoin() }
    }
}
