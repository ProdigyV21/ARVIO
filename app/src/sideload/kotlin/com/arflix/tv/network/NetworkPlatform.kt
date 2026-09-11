package com.arflix.tv.network

import android.content.Context
import okhttp3.OkHttp

internal fun initializeNetworkPlatform(context: Context) {
    // Cloudstream brings OkHttp 5. Initialize before Hilt or DNS can load its assets.
    OkHttp.initialize(context)
}
