package com.arflix.tv.data.repository

import com.arflix.tv.network.IptvAccountUsage
import com.arflix.tv.network.IptvProviderAccount
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class IptvStreamPolicyTest {
    private val xtream = IptvProviderAccount.xtream("http://panel.example", "user", "pass")!!
    private val otherXtream = IptvProviderAccount.xtream("http://panel.example", "guide", "pass")!!
    private val stalker = IptvProviderAccount.stalker("http://portal.example/c", "00:1A:79:00:00:01")!!

    private data class Shape(val limit: Int, val homePreview: Boolean, val matroskaIndex: Boolean, val panelCheck: Boolean)

    private fun shape(setting: IptvConnectionLimit, allowance: Int?, usage: IptvAccountUsage? = null) =
        iptvStreamPolicy(xtream, setting, allowance, usage)!!
            .let { Shape(it.effectiveLimit, it.homePreviewAllowed, it.matroskaIndexAllowed, it.panelCheckAllowed) }

    private fun info(
        max: Int? = 3,
        status: String? = IptvAccountInfo.STATUS_ACTIVE,
        fingerprint: String? = "fp",
    ) = IptvAccountInfo(sourceFingerprint = fingerprint, status = status, maxConnections = max)

    @Test fun autoFollowsTheLatestAnswerCapsAtTwoAndAlwaysLeavesThePreviewToItsOwnCheck() {
        val two = Shape(2, homePreview = true, matroskaIndex = true, panelCheck = true)
        val one = Shape(1, homePreview = true, matroskaIndex = false, panelCheck = true)
        val expected = mapOf(
            IptvAccountUsage(1, 3) to two,
            IptvAccountUsage(0, 10) to two,
            IptvAccountUsage(2, 3) to one,
            IptvAccountUsage(3, 3) to one,
            IptvAccountUsage(5, 3) to one,
            null to one,
        )
        expected.forEach { (usage, shape) -> assertEquals("usage $usage", shape, shape(IptvConnectionLimit.AUTO, 3, usage)) }
        assertEquals(
            "an answer without a reliable allowance is not trusted",
            Shape(1, homePreview = true, matroskaIndex = false, panelCheck = false),
            shape(IptvConnectionLimit.AUTO, null, IptvAccountUsage(0, 10))
        )
    }

    @Test fun anExplicitValueIsCappedByAReliableAllowanceAndChecksOnlyWithOne() {
        assertEquals(Shape(2, homePreview = true, matroskaIndex = true, panelCheck = true), shape(IptvConnectionLimit.THREE, 2))
        assertEquals(Shape(1, homePreview = false, matroskaIndex = false, panelCheck = true), shape(IptvConnectionLimit.THREE, 1))
        assertEquals(Shape(1, homePreview = false, matroskaIndex = false, panelCheck = true), shape(IptvConnectionLimit.ONE, 5))
        assertEquals(Shape(3, homePreview = true, matroskaIndex = true, panelCheck = false), shape(IptvConnectionLimit.THREE, null))
        assertEquals(Shape(1, homePreview = false, matroskaIndex = false, panelCheck = false), shape(IptvConnectionLimit.ONE, null))
        assertEquals("only Auto reads the answer", Shape(1, homePreview = false, matroskaIndex = false, panelCheck = true), shape(IptvConnectionLimit.ONE, 5, IptvAccountUsage(0, 5)))
    }

    @Test fun noLimitOrNoAccountHasNoPolicy() {
        assertNull(iptvStreamPolicy(xtream, IptvConnectionLimit.NO_LIMIT, 3))
        assertNull(iptvStreamPolicy(null, IptvConnectionLimit.AUTO, 3))
        assertNull(iptvStreamPolicy(null, IptvConnectionLimit.ONE, null))
    }

    @Test fun strictestExplicitWinsAndExplicitBeatsAuto() {
        val auto = IptvConnectionLimit.AUTO
        val none = IptvConnectionLimit.NO_LIMIT
        assertEquals(IptvConnectionLimit.ONE, strictestIptvConnectionLimit(listOf(IptvConnectionLimit.THREE, IptvConnectionLimit.ONE, auto)))
        assertEquals(IptvConnectionLimit.TWO, strictestIptvConnectionLimit(listOf(none, IptvConnectionLimit.TWO)))
        assertEquals(none, strictestIptvConnectionLimit(listOf(auto, none)))
        assertEquals(auto, strictestIptvConnectionLimit(listOf(auto, auto)))
        assertEquals(auto, strictestIptvConnectionLimit(emptyList()))
    }

    @Test fun allowanceIsReliableOnlyWhenEveryConditionHolds() {
        assertEquals(3, reliableIptvAllowance(info(), "fp", xtream, xtream))
        assertNull("stored info is for other credentials", reliableIptvAllowance(info(fingerprint = "old"), "fp", xtream, xtream))
        assertNull("no stored fingerprint", reliableIptvAllowance(info(fingerprint = null), "fp", xtream, xtream))
        assertNull("expired", reliableIptvAllowance(info(status = IptvAccountInfo.STATUS_EXPIRED), "fp", xtream, xtream))
        assertNull("unavailable", reliableIptvAllowance(info(status = IptvAccountInfo.STATUS_UNAVAILABLE), "fp", xtream, xtream))
        assertNull("not reported", reliableIptvAllowance(info(max = null), "fp", xtream, xtream))
        assertNull("zero", reliableIptvAllowance(info(max = 0), "fp", xtream, xtream))
        assertNull("nothing stored", reliableIptvAllowance(null, "fp", xtream, xtream))
        assertNull("streams use the EPG login", reliableIptvAllowance(info(), "fp", xtream, otherXtream))
        assertNull("stalker", reliableIptvAllowance(info(), "fp", stalker, stalker))
    }
}
