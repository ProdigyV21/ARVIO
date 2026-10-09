package com.arflix.tv.ui.components

import android.content.Intent
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.webkit.RenderProcessGoneDetail
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi

/** The embedded-player library uses WebChromeClient, but no renderer recovery client. */
internal fun installTrailerWebViewRecovery(root: View, onRendererGone: () -> Unit) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return
    if (root is WebView) installRecoveryClient(root, onRendererGone)
    else if (root is ViewGroup) for (index in 0 until root.childCount) {
        installTrailerWebViewRecovery(root.getChildAt(index), onRendererGone)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun installRecoveryClient(webView: WebView, onRendererGone: () -> Unit) {
    webView.webViewClient = object : WebViewClient() {
        override fun onRenderProcessGone(view: WebView, detail: RenderProcessGoneDetail): Boolean {
            // Android kills the host app unless every affected WebView handles
            // this callback. A dead renderer cannot be reused or played again.
            (view.parent as? ViewGroup)?.removeView(view)
            view.destroy()
            onRendererGone()
            return true
        }

        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            // Keep user-selected YouTube/ad links opening externally, as they
            // do with the default WebView client. Embedded resource loads stay intact.
            if (!request.isForMainFrame || !request.hasGesture() ||
                request.url.scheme !in setOf("http", "https")
            ) return false
            return runCatching {
                view.context.startActivity(Intent(Intent.ACTION_VIEW, request.url).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                true
            }.getOrDefault(false)
        }
    }
}
