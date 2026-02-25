package com.arflix.tv.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CloudStreamExtensionManifest(
    val name: String = "",
    val description: String = "",
    val version: Int = 1,
    val pluginLists: List<String> = emptyList()
) : Serializable

data class CloudStreamPluginList(
    val plugins: List<CloudStreamPluginEntry> = emptyList(),
    @SerializedName("all_plugins", alternate = ["allPlugins"])
    val allPlugins: List<CloudStreamPluginEntry> = emptyList()
) : Serializable

data class CloudStreamPluginEntry(
    val name: String = "",
    val url: String = "",
    val version: Int = 1,
    val authors: List<String> = emptyList(),
    @SerializedName("tvTypes", alternate = ["tv_types"])
    val tvTypes: List<String> = emptyList(),
    val language: String = "",
    val description: String = "",
    @SerializedName("iconUrl", alternate = ["icon_url"])
    val iconUrl: String? = null
) : Serializable

data class CloudStreamSearchResponse(
    val results: List<CloudStreamSearchResult> = emptyList(),
    @SerializedName("hasNextPage", alternate = ["has_next_page"])
    val hasNextPage: Boolean = false
) : Serializable

data class CloudStreamSearchResult(
    val name: String = "",
    val url: String = "",
    @SerializedName("posterUrl", alternate = ["poster_url", "poster"])
    val posterUrl: String? = null,
    val year: Int? = null,
    val type: String? = null,
    val id: String? = null,
    val quality: String? = null,
    val rating: Float? = null
) : Serializable

data class CloudStreamStreamSource(
    val url: String = "",
    val name: String = "",
    val quality: String? = null,
    val headers: Map<String, String>? = null,
    val referer: String? = null,
    @SerializedName("subtitleUrls", alternate = ["subtitle_urls"])
    val subtitleUrls: List<String> = emptyList()
) : Serializable

data class CloudStreamExtension(
    val repositoryUrl: String,
    val manifest: CloudStreamExtensionManifest,
    val plugins: List<CloudStreamPluginEntry>,
    val enabled: Boolean = true
) : Serializable
