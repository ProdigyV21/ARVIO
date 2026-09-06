package com.arflix.tv.data.repository

import kotlinx.coroutines.CancellationException
import org.json.JSONObject

/** Main cannot edit plugins, but must not erase backups still used by extension-capable builds. */
internal fun preserveLegacyPluginBackup(localPayload: String, remotePayload: Result<String?>): Result<String> {
    return try {
        val remote = remotePayload.getOrThrow()?.takeIf { it.isNotBlank() }
            ?: return Result.success(localPayload)
        val remoteRoot = JSONObject(remote)
        val localRoot = JSONObject(localPayload)
        for (key in listOf("pluginRepositories", "pluginScrapers", "pluginsEnabled")) {
            if (remoteRoot.has(key)) localRoot.put(key, remoteRoot.get(key))
        }
        Result.success(localRoot.toString())
    } catch (error: CancellationException) {
        throw error
    } catch (error: Exception) {
        Result.failure(error)
    }
}
