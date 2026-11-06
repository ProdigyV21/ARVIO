package com.arflix.tv.cast

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class DlnaRendererTest {
    private val description = """<?xml version="1.0"?>
<root xmlns="urn:schemas-upnp-org:device-1-0">
  <device>
    <deviceType>urn:schemas-upnp-org:device:MediaRenderer:1</deviceType>
    <friendlyName>Living Room TV</friendlyName>
    <manufacturer>Sony</manufacturer>
    <modelName>KD-55X</modelName>
    <UDN>uuid:1234</UDN>
    <serviceList>
      <service>
        <serviceType>urn:schemas-upnp-org:service:RenderingControl:1</serviceType>
        <controlURL>/upnp/control/RenderingControl</controlURL>
      </service>
      <service>
        <serviceType>urn:schemas-upnp-org:service:AVTransport:1</serviceType>
        <controlURL>upnp/control/AVTransport</controlURL>
      </service>
    </serviceList>
  </device>
</root>"""

    @Test fun parsesRendererDescription() {
        val d = DlnaDiscovery.parseDescription("http://192.168.1.20:52323/dmr.xml", description)
        assertNotNull(d)
        assertEquals("Living Room TV", d!!.friendlyName)
        assertEquals("uuid:1234", d.id)
        assertEquals("http://192.168.1.20:52323/upnp/control/AVTransport", d.avTransportControlUrl)
        assertEquals("http://192.168.1.20:52323/upnp/control/RenderingControl", d.renderingControlUrl)
    }

    @Test fun readsSsdpLocationHeader() {
        val resp = "HTTP/1.1 200 OK\r\nCACHE-CONTROL: max-age=1800\r\nLocation: http://10.0.0.5:1400/desc.xml\r\nST: x\r\n\r\n"
        assertEquals("http://10.0.0.5:1400/desc.xml", DlnaDiscovery.headerValue(resp, "LOCATION"))
    }

    @Test fun timeFormatRoundTrips() {
        assertEquals("01:02:03", DlnaController.formatTime(3_723_000L))
        assertEquals(3_723_000L, DlnaController.parseTime("1:02:03"))
        assertEquals(3_723_500L, DlnaController.parseTime("01:02:03.500"))
        assertEquals(0L, DlnaController.parseTime("NOT_IMPLEMENTED"))
    }

    @Test fun parsesSoapTagsAndEscapesMetadata() {
        val xml = "<s:Envelope><s:Body><u:GetPositionInfoResponse><RelTime>00:10:00</RelTime>" +
            "<TrackDuration>01:30:00</TrackDuration></u:GetPositionInfoResponse></s:Body></s:Envelope>"
        assertEquals("00:10:00", DlnaController.tag(xml, "RelTime"))
        val didl = DlnaController.didl("http://a/b?x=1&y=2", "Tom & Jerry", "video/mp4")
        assertTrue(didl.contains("x=1&amp;y=2"))
        assertTrue(didl.contains("Tom &amp; Jerry"))
    }
}
