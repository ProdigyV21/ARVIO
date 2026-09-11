package com.arflix.tv.data.repository

import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import org.json.JSONArray
import org.json.JSONObject

internal object IptvCloudFields {
    private val fields = setOf("m3uUrl", "epgUrl", "playlists", "stalkerPortals", "stalkerPortalUrl",
        "stalkerMacAddress", "favoriteGroups", "favoriteChannels", "hiddenGroups", "lockedGroups",
        "groupOrder", "groupOrderSchema", "sortOrder")
    private val timestampKey = stringPreferencesKey("cloud_sync_field_ts")
    private val baselineKey = stringPreferencesKey("cloud_sync_field_base")

    fun keys(root: JSONObject): List<String> = buildList {
        root.optJSONObject("iptvByProfile")?.let { profiles ->
            for (id in profiles.keys()) {
                val profile = profiles.optJSONObject(id) ?: continue
                fields.filter { profile.has(it) }.forEach { add("i:$id:$it") }
            }
        }
    }

    fun value(root: JSONObject, key: String): Any? {
        val path = key.removePrefix("i:")
        return root.optJSONObject("iptvByProfile")?.optJSONObject(path.substringBefore(':'))
            ?.opt(path.substringAfter(':'))
    }

    fun merge(base: JSONObject, other: JSONObject): Set<String> {
        val baseTs = base.optJSONObject("fieldUpdatedAt") ?: JSONObject()
        val otherTs = other.optJSONObject("fieldUpdatedAt") ?: JSONObject()
        val won = LinkedHashSet<String>()
        for (key in keys(other)) {
            val timestamp = otherTs.optLong(key, 0)
            if (timestamp <= baseTs.optLong(key, 0)) continue
            val path = key.removePrefix("i:")
            val profiles = base.optJSONObject("iptvByProfile") ?: JSONObject().also { base.put("iptvByProfile", it) }
            val id = path.substringBefore(':')
            val profile = profiles.optJSONObject(id) ?: JSONObject().also { profiles.put(id, it) }
            profile.put(path.substringAfter(':'), value(other, key))
            baseTs.put(key, timestamp)
            won += key
        }
        base.put("fieldUpdatedAt", baseTs)
        return won
    }

    fun stamp(prefs: MutablePreferences, profileId: String, values: JSONObject, now: Long = System.currentTimeMillis()) {
        val timestamps = parse(prefs[timestampKey])
        val baseline = parse(prefs[baselineKey])
        for (field in values.keys()) {
            require(field in fields)
            val key = "i:$profileId:$field"
            timestamps.put(key, maxOf(now, timestamps.optLong(key, 0) + 1))
            baseline.put(key, values.get(field).toString())
        }
        prefs[timestampKey] = timestamps.toString()
        prefs[baselineKey] = baseline.toString()
    }

    fun hasNewerLocalChange(prefs: MutablePreferences, profileId: String, incoming: JSONObject): Boolean {
        val local = parse(prefs[timestampKey])
        return fields.any { field ->
            val key = "i:$profileId:$field"
            local.optLong(key, 0) > incoming.optLong(key, 0)
        }
    }

    /** Keep a deletion made while a cloud snapshot was being assembled. */
    fun reconcileSnapshot(root: JSONObject, timestamps: JSONObject, baseline: JSONObject, captured: JSONObject) {
        for (key in keys(root)) {
            if (timestamps.optLong(key, 0) <= captured.optLong(key, 0) || !baseline.has(key)) continue
            val text = baseline.getString(key)
            val latest: Any = when (value(root, key)) {
                is JSONArray -> JSONArray(text)
                is JSONObject -> JSONObject(text)
                is Number -> text.toLong()
                is Boolean -> text.toBooleanStrict()
                else -> text
            }
            val path = key.removePrefix("i:")
            root.getJSONObject("iptvByProfile").getJSONObject(path.substringBefore(':'))
                .put(path.substringAfter(':'), latest)
        }
    }

    private fun parse(raw: String?): JSONObject = try {
        if (raw.isNullOrBlank()) JSONObject() else JSONObject(raw)
    } catch (_: org.json.JSONException) { JSONObject() }
}
