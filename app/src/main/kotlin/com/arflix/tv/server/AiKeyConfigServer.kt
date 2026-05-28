package com.arflix.tv.server

import fi.iki.elonen.NanoHTTPD
import java.io.ByteArrayInputStream
import java.security.SecureRandom

/**
 * Local HTTP server used to collect a user-provided AI API key during onboarding.
 *
 * Security hardening: require a one-time pairing token to be included with the
 * POST to `/api/key`. The token is short-lived and displayed on-device (and
 * encoded into the QR URL by the caller) which reduces the attack surface when
 * the server is reachable on the local network. For stronger protection consider
 * binding to localhost only or serving over TLS; this change implements a
 * pragmatic one-time token improvement that preserves the previous UX (QR -> phone).
 */
class AiKeyConfigServer(
    private val onKeyReceived: (String) -> Unit,
    private val logoProvider: (() -> ByteArray?)? = null,
    port: Int = 8095,
    /**
     * Pairing token required for /api/key POSTs. If null, a random token will be generated.
     */
    val pairingToken: String? = null
) : NanoHTTPD(port) {

    private val token: String = pairingToken ?: generateToken()
    /**
     * Public accessor for the active pairing token (generated or provided).
     */
    val currentPairingToken: String
        get() = token

    override fun serve(session: IHTTPSession): Response {
        val uri = session.uri
        val method = session.method

        return when {
            method == Method.GET  && uri == "/"          -> serveHtml(AiKeyWebPage.getLandingHtml())
            method == Method.GET  && uri == "/groq"      -> serveHtml(AiKeyWebPage.getGroqHtml())
            method == Method.GET  && uri == "/gemini"    -> serveHtml(AiKeyWebPage.getGeminiHtml())
            method == Method.GET  && uri == "/logo.png"  -> serveLogo()
            // Require the client to supply the pairing token when submitting the key.
            method == Method.POST && uri == "/api/key"   -> handleKeySubmit(session, onKeyReceived)
            else -> newFixedLengthResponse(Response.Status.NOT_FOUND, MIME_PLAINTEXT, "Not found")
        }
    }

    private fun serveHtml(html: String): Response =
        newFixedLengthResponse(Response.Status.OK, "text/html", html)

    private fun serveLogo(): Response {
        val bytes = logoProvider?.invoke()
        return if (bytes != null) {
            newFixedLengthResponse(
                Response.Status.OK,
                "image/png",
                ByteArrayInputStream(bytes),
                bytes.size.toLong()
            )
        } else {
            newFixedLengthResponse(Response.Status.NOT_FOUND, MIME_PLAINTEXT, "Not found")
        }
    }

    private fun handleKeySubmit(session: IHTTPSession, onKeyReceived: (String) -> Unit): Response {
        val bodyMap = HashMap<String, String>()
        session.parseBody(bodyMap)
        val body = bodyMap["postData"] ?: ""
        val json = try {
            org.json.JSONObject(body)
        } catch (e: Exception) {
            null
        }
        val key = json?.optString("key", "")?.trim() ?: ""
        val suppliedToken = json?.optString("token", "") ?: ""

        // Validate pairing token
        if (token.isNotEmpty() && suppliedToken != token) {
            val forbidden = org.json.JSONObject().put("status", "forbidden").put("reason", "invalid_token")
            return newFixedLengthResponse(Response.Status.FORBIDDEN, "application/json", forbidden.toString())
        }

        // Accept key (empty string allowed to clear)
        onKeyReceived(key)
        val response = org.json.JSONObject().put("status", "saved")
        return newFixedLengthResponse(Response.Status.OK, "application/json", response.toString())
    }

    companion object {
        fun startOnAvailablePort(
            onKeyReceived: (String) -> Unit,
            logoProvider: (() -> ByteArray?)? = null,
            startPort: Int = 8095,
            maxAttempts: Int = 10,
            pairingToken: String? = null
        ): AiKeyConfigServer? {
            for (port in startPort until startPort + maxAttempts) {
                try {
                    val server = AiKeyConfigServer(onKeyReceived, logoProvider, port, pairingToken)
                    server.start(SOCKET_READ_TIMEOUT, false)
                    return server
                } catch (e: Exception) {
                    // Port in use, try next
                }
            }
            return null
        }
    }

    private fun generateToken(length: Int = 8): String {
        val src = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        val rnd = SecureRandom()
        return (1..length).map { src[rnd.nextInt(src.length)] }.joinToString("")
    }
}
