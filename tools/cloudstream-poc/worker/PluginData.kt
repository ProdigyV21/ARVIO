package com.lagradost.cloudstream3.plugins

/** ABI-only metadata model required by StreamPlay's optional settings code. No plugin installation API. */
data class PluginData(val internalName: String, val url: String?, val isOnline: Boolean, val filePath: String, val version: Int)
