package com.arflix.tv.network

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class IptvProviderAccountTest {
    private fun xtream(base: String, user: String = "user", pass: String = "secret-pass") =
        IptvProviderAccount.xtream(base, user, pass)
    private fun stalker(portal: String, mac: String = "00:1A:79:AA:BB:CC") = IptvProviderAccount.stalker(portal, mac)
    private fun m3u(url: String) = IptvProviderAccount.m3u(url)

    @Test fun duplicatesOfOneAccountMerge() {
        val merging = listOf(
            "http and https" to (xtream("http://panel.test") to xtream("https://panel.test")),
            "ports 80 and 443" to (xtream("http://panel.test:80") to xtream("https://panel.test:443")),
            "host case" to (xtream("http://PANEL.test") to xtream("http://panel.test")),
            "get.php and player_api" to (xtream("http://panel.test/get.php") to xtream("http://panel.test/player_api.php")),
            "stalker /c" to (stalker("http://portal.test/c") to stalker("http://portal.test/c/")),
            "stalker root and /c" to (stalker("http://portal.test") to stalker("http://portal.test/c")),
            "stalker_portal /c" to (stalker("http://portal.test/stalker_portal/c") to stalker("https://portal.test/c/")),
            "stalker api path" to (stalker("http://portal.test/server/load.php") to stalker("http://portal.test/portal.php")),
            "stalker kept path" to (stalker("http://portal.test/tv/c") to stalker("http://portal.test/tv")),
            "stalker without scheme" to (stalker("portal.test:8080/c") to stalker("http://portal.test:8080/c")),
            "mac case" to (stalker("http://portal.test/c", "00:1a:79:aa:bb:cc") to stalker("http://portal.test/c")),
            "mac dashes" to (stalker("http://portal.test/c", "00-1A-79-AA-BB-CC") to stalker("http://portal.test/c")),
            "m3u scheme" to (m3u("http://list.test/a.m3u?token=1") to m3u("https://list.test/a.m3u?token=1")),
            "m3u query order" to (m3u("http://list.test/a.m3u?a=1&b=2") to m3u("http://list.test/a.m3u?b=2&a=1")),
            "m3u fragment" to (m3u("http://list.test/a.m3u#x") to m3u("http://list.test/a.m3u")),
        )
        merging.forEach { (name, pair) ->
            assertNotNull(name, pair.first)
            assertEquals(name, pair.first, pair.second)
            assertEquals(name, pair.first.hashCode(), pair.second.hashCode())
        }
    }

    @Test fun differentAccountsNeverMerge() {
        val splitting = listOf(
            "username" to (xtream("http://panel.test", user = "alice") to xtream("http://panel.test", user = "carol")),
            "username case" to (xtream("http://panel.test", user = "Bob") to xtream("http://panel.test", user = "bob")),
            "password" to (xtream("http://panel.test", pass = "one") to xtream("http://panel.test", pass = "two")),
            "port" to (xtream("http://panel.test:8080") to xtream("http://panel.test:8081")),
            "default and custom port" to (xtream("http://panel.test") to xtream("http://panel.test:8080")),
            "host" to (xtream("http://panel.test") to xtream("http://other.test")),
            "same mac on another portal" to (stalker("http://portal.test/c") to stalker("http://other.test/c")),
            "same mac on another portal path" to (stalker("http://portal.test/a/c") to stalker("http://portal.test/b/c")),
            "another mac" to (stalker("http://portal.test/c") to stalker("http://portal.test/c", "00:1A:79:AA:BB:CD")),
            "m3u token" to (m3u("http://list.test/a.m3u?token=1") to m3u("http://list.test/a.m3u?token=2")),
            "m3u path case" to (m3u("http://list.test/A.m3u") to m3u("http://list.test/a.m3u")),
            "m3u port" to (m3u("http://list.test:81/a.m3u") to m3u("http://list.test:82/a.m3u")),
            "numeric tail lists" to (m3u("http://list.test/u1/p1/100.m3u8") to m3u("http://list.test/u2/p2/100.m3u8")),
            "kinds" to (xtream("http://panel.test") to m3u("http://panel.test/get.php?username=user&password=secret-pass")),
        )
        splitting.forEach { (name, pair) ->
            assertNotNull(name, pair.first)
            assertNotNull(name, pair.second)
            assertNotEquals(name, pair.first, pair.second)
        }
    }

    @Test fun textFormsCarryNoCredential() {
        listOf(
            xtream("http://secret-host.test", "secret-user", "secret-pass"),
            stalker("http://secret-host.test/c", "AA:BB:CC:DD:EE:FF"),
            m3u("http://secret-host.test/a.m3u?token=secret-token"),
        ).forEach { account ->
            val text = account.toString()
            assertEquals("IptvProviderAccount(redacted)", text)
            assertFalse(text.contains("secret"))
        }
    }
}
