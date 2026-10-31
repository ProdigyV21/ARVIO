package com.arflix.tv.cast

import android.content.Context
import android.net.wifi.WifiManager
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.w3c.dom.Element
import java.io.ByteArrayInputStream
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.HttpURLConnection
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.SocketTimeoutException
import java.net.URL
import javax.xml.parsers.DocumentBuilderFactory

/**
 * Minimal DLNA / UPnP AV MediaRenderer support so the cast button also
 * reaches smart TVs, TV boxes and projectors on the same Wi-Fi that are not Google Cast
 * devices (or phones without Google Play services).
 */
data class DlnaDevice(
    val id: String,
    val friendlyName: String,
    val manufacturer: String,
    val modelName: String,
    val avTransportControlUrl: String,
    val renderingControlUrl: String?,
)

object DlnaDiscovery {
    private const val TAG = "DlnaDiscovery"
    private const val SSDP_ADDR = "239.255.255.250"
    private const val SSDP_PORT = 1900
    private val SEARCH_TARGETS = listOf(
        "urn:schemas-upnp-org:device:MediaRenderer:1",
        "urn:schemas-upnp-org:service:AVTransport:1",
    )

    /**
     * Sends SSDP M-SEARCH requests and reports each renderer as soon as its description has
     * been read. Blocking; call from Dispatchers.IO.
     */
    fun discover(context: Context, timeoutMs: Long = 5_000L, onFound: (DlnaDevice) -> Unit) {
        val wifi = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as? WifiManager
        val lock = try {
            wifi?.createMulticastLock("arvio-dlna")?.apply { setReferenceCounted(false); acquire() }
        } catch (t: Throwable) { null }
        val seenLocations = HashSet<String>()
        val seenIds = HashSet<String>()
        var socket: DatagramSocket? = null
        try {
            socket = DatagramSocket(null).apply {
                reuseAddress = true
                bind(InetSocketAddress(0))
                soTimeout = 400
            }
            val group = InetAddress.getByName(SSDP_ADDR)
            fun search() {
                for (st in SEARCH_TARGETS) {
                    val msg = "M-SEARCH * HTTP/1.1\r\n" +
                        "HOST: $SSDP_ADDR:$SSDP_PORT\r\n" +
                        "MAN: \"ssdp:discover\"\r\n" +
                        "MX: 2\r\n" +
                        "ST: $st\r\n" +
                        "USER-AGENT: Android UPnP/1.0 ARVIO/1.0\r\n\r\n"
                    val bytes = msg.toByteArray()
                    runCatching { socket.send(DatagramPacket(bytes, bytes.size, group, SSDP_PORT)) }
                }
            }
            search()
            val deadline = System.currentTimeMillis() + timeoutMs
            var resent = 0
            val buf = ByteArray(4096)
            while (System.currentTimeMillis() < deadline && !Thread.currentThread().isInterrupted) {
                val elapsed = timeoutMs - (deadline - System.currentTimeMillis())
                if (resent < 2 && elapsed > (resent + 1) * 1_200L) { search(); resent++ }
                val packet = DatagramPacket(buf, buf.size)
                try {
                    socket.receive(packet)
                } catch (_: SocketTimeoutException) {
                    continue
                }
                val text = String(packet.data, 0, packet.length)
                val location = headerValue(text, "LOCATION") ?: continue
                if (!seenLocations.add(location)) continue
                val device = runCatching { readDescription(location) }
                    .onFailure { Log.w(TAG, "description failed for $location: ${it.message}") }
                    .getOrNull() ?: continue
                if (seenIds.add(device.id)) onFound(device)
            }
        } catch (t: Throwable) {
            Log.w(TAG, "SSDP discovery failed", t)
        } finally {
            runCatching { socket?.close() }
            runCatching { lock?.release() }
        }
    }

    internal fun headerValue(response: String, name: String): String? =
        response.lineSequence()
            .firstOrNull { it.substringBefore(':').trim().equals(name, ignoreCase = true) }
            ?.substringAfter(':')?.trim()?.takeIf { it.isNotEmpty() }

    private fun readDescription(location: String): DlnaDevice? {
        val conn = (URL(location).openConnection() as HttpURLConnection).apply {
            connectTimeout = 2_500; readTimeout = 2_500
        }
        val xml = try { conn.inputStream.use { it.readBytes() } } finally { conn.disconnect() }
        return parseDescription(location, String(xml, Charsets.UTF_8))
    }

    internal fun parseDescription(location: String, xml: String): DlnaDevice? {
        val factory = DocumentBuilderFactory.newInstance().apply { isNamespaceAware = false }
        val doc = factory.newDocumentBuilder().parse(ByteArrayInputStream(xml.toByteArray(Charsets.UTF_8)))
        val root = doc.documentElement
        val base = childText(root, "URLBase")?.takeIf { it.isNotBlank() } ?: location
        val devices = root.getElementsByTagName("device")
        for (i in 0 until devices.length) {
            val dev = devices.item(i) as? Element ?: continue
            var avt: String? = null
            var rc: String? = null
            val serviceList = dev.getElementsByTagName("service")
            for (j in 0 until serviceList.length) {
                val svc = serviceList.item(j) as? Element ?: continue
                // Only services that belong directly to this device.
                if (svc.parentNode?.parentNode != dev) continue
                val type = childText(svc, "serviceType") ?: continue
                val control = childText(svc, "controlURL") ?: continue
                when {
                    type.contains(":AVTransport:", ignoreCase = true) -> avt = resolve(base, control)
                    type.contains(":RenderingControl:", ignoreCase = true) -> rc = resolve(base, control)
                }
            }
            if (avt != null) {
                return DlnaDevice(
                    id = childText(dev, "UDN") ?: location,
                    friendlyName = childText(dev, "friendlyName")?.takeIf { it.isNotBlank() } ?: "DLNA TV",
                    manufacturer = childText(dev, "manufacturer").orEmpty(),
                    modelName = childText(dev, "modelName").orEmpty(),
                    avTransportControlUrl = avt,
                    renderingControlUrl = rc,
                )
            }
        }
        return null
    }

    private fun childText(parent: Element, tag: String): String? {
        val nodes = parent.childNodes
        for (i in 0 until nodes.length) {
            val n = nodes.item(i)
            if (n is Element && (n.tagName == tag || n.tagName.substringAfter(':') == tag)) return n.textContent?.trim()
        }
        return null
    }

    internal fun resolve(base: String, path: String): String =
        if (path.startsWith("http://") || path.startsWith("https://")) path
        else URL(URL(base), path).toString()
}

/** Talks to one renderer's AVTransport service over SOAP. All calls block; use Dispatchers.IO. */
class DlnaController(val device: DlnaDevice) {
    data class Position(val positionMs: Long, val durationMs: Long)

    suspend fun load(url: String, title: String, mimeType: String): Boolean = withContext(Dispatchers.IO) {
        runCatching { soap("Stop", "") }
        val meta = didl(url, title, mimeType)
        val withMeta = runCatching {
            soap("SetAVTransportURI", "<CurrentURI>${esc(url)}</CurrentURI><CurrentURIMetaData>${esc(meta)}</CurrentURIMetaData>")
        }
        if (withMeta.isFailure) {
            // Some renderers reject DIDL-Lite they do not understand; retry without metadata.
            soap("SetAVTransportURI", "<CurrentURI>${esc(url)}</CurrentURI><CurrentURIMetaData></CurrentURIMetaData>")
        }
        runCatching { soap("Play", "<Speed>1</Speed>") }.isSuccess
    }

    suspend fun play() = withContext(Dispatchers.IO) { runCatching { soap("Play", "<Speed>1</Speed>") }.isSuccess }
    suspend fun pause() = withContext(Dispatchers.IO) { runCatching { soap("Pause", "") }.isSuccess }
    suspend fun stop() = withContext(Dispatchers.IO) { runCatching { soap("Stop", "") }.isSuccess }

    suspend fun seek(positionMs: Long) = withContext(Dispatchers.IO) {
        runCatching { soap("Seek", "<Unit>REL_TIME</Unit><Target>${formatTime(positionMs)}</Target>") }.isSuccess
    }

    suspend fun position(): Position? = withContext(Dispatchers.IO) {
        runCatching {
            val xml = soap("GetPositionInfo", "")
            Position(
                positionMs = parseTime(tag(xml, "RelTime")),
                durationMs = parseTime(tag(xml, "TrackDuration")),
            )
        }.getOrNull()
    }

    /** PLAYING, PAUSED_PLAYBACK, STOPPED, TRANSITIONING, NO_MEDIA_PRESENT … */
    suspend fun transportState(): String? = withContext(Dispatchers.IO) {
        runCatching { tag(soap("GetTransportInfo", ""), "CurrentTransportState") }.getOrNull()
    }

    private fun soap(action: String, args: String): String {
        val body = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
            "<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\" s:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">" +
            "<s:Body><u:$action xmlns:u=\"$SERVICE\"><InstanceID>0</InstanceID>$args</u:$action></s:Body></s:Envelope>"
        val conn = (URL(device.avTransportControlUrl).openConnection() as HttpURLConnection).apply {
            requestMethod = "POST"
            connectTimeout = 3_000
            readTimeout = 6_000
            doOutput = true
            setRequestProperty("Content-Type", "text/xml; charset=\"utf-8\"")
            setRequestProperty("SOAPACTION", "\"$SERVICE#$action\"")
        }
        try {
            conn.outputStream.use { it.write(body.toByteArray(Charsets.UTF_8)) }
            val code = conn.responseCode
            val stream = if (code in 200..299) conn.inputStream else conn.errorStream
            val text = stream?.use { String(it.readBytes(), Charsets.UTF_8) }.orEmpty()
            if (code !in 200..299) throw IllegalStateException("$action HTTP $code ${tag(text, "errorDescription")}")
            return text
        } finally {
            conn.disconnect()
        }
    }

    companion object {
        private const val SERVICE = "urn:schemas-upnp-org:service:AVTransport:1"

        internal fun didl(url: String, title: String, mimeType: String): String =
            "<DIDL-Lite xmlns=\"urn:schemas-upnp-org:metadata-1-0/DIDL-Lite/\" " +
                "xmlns:dc=\"http://purl.org/dc/elements/1.1/\" xmlns:upnp=\"urn:schemas-upnp-org:metadata-1-0/upnp/\">" +
                "<item id=\"1\" parentID=\"0\" restricted=\"1\"><dc:title>${esc(title)}</dc:title>" +
                "<upnp:class>object.item.videoItem</upnp:class>" +
                "<res protocolInfo=\"http-get:*:$mimeType:*\">${esc(url)}</res></item></DIDL-Lite>"

        internal fun esc(s: String): String = s
            .replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
            .replace("\"", "&quot;").replace("'", "&apos;")

        internal fun tag(xml: String, name: String): String? {
            val m = Regex("<(?:\\w+:)?$name(?:\\s[^>]*)?>([^<]*)</(?:\\w+:)?$name>").find(xml) ?: return null
            return m.groupValues[1].trim()
        }

        internal fun formatTime(ms: Long): String {
            val t = ms.coerceAtLeast(0L) / 1000L
            return "%02d:%02d:%02d".format(t / 3600, (t % 3600) / 60, t % 60)
        }

        internal fun parseTime(value: String?): Long {
            if (value.isNullOrBlank() || value.startsWith("NOT_IMPLEMENTED")) return 0L
            val main = value.substringBefore('.')
            val parts = main.split(':').mapNotNull { it.trim().toLongOrNull() }
            if (parts.isEmpty()) return 0L
            var sec = 0L
            for (p in parts) sec = sec * 60 + p
            val frac = value.substringAfter('.', "").takeWhile { it.isDigit() }.take(3).padEnd(3, '0')
            return sec * 1000L + (frac.toLongOrNull() ?: 0L)
        }
    }
}
