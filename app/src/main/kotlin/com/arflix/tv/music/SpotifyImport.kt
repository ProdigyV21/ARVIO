package com.arflix.tv.music

import com.google.gson.JsonParser
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import java.io.ByteArrayInputStream
import java.text.Normalizer
import java.util.zip.ZipInputStream

/** A song as a Spotify export lists it: names only, nothing playable. */
data class ImportedTrack(
    val title: String,
    val artist: String,
    val album: String = "",
    val durationMs: Long? = null,
)

/** A playlist from an export; [liked] marks the user's Liked Songs. */
data class ImportedPlaylist(
    val name: String,
    val tracks: List<ImportedTrack>,
    val liked: Boolean = false,
)

/**
 * Reads a user's own Spotify library from files they exported themselves, so no Spotify login
 * or developer app is needed:
 *
 * - Exportify CSV files (one per playlist; "Liked Songs" too), columns "Track Name",
 *   "Artist Name(s)", "Album Name", "Duration (ms)" or "Track Duration (ms)".
 * - Spotify's own "Download your data" export: `YourLibrary.json` (liked songs in `tracks`) and
 *   `Playlist*.json` (`playlists[].items[].track`), loose or still in the zip.
 *
 * The songs are then looked up in the user's own sources by [match]; ARVIO plays nothing from Spotify.
 */
object SpotifyImport {

    fun parse(fileName: String, bytes: ByteArray): List<ImportedPlaylist> {
        val name = fileName.substringAfterLast('/').substringAfterLast('\\')
        return when {
            name.endsWith(".zip", ignoreCase = true) -> parseZip(bytes)
            name.endsWith(".json", ignoreCase = true) -> parseJson(name, String(bytes, Charsets.UTF_8))
            else -> listOfNotNull(parseCsv(name, String(bytes, Charsets.UTF_8)))
        }
    }

    private fun parseZip(bytes: ByteArray): List<ImportedPlaylist> {
        val out = mutableListOf<ImportedPlaylist>()
        ZipInputStream(ByteArrayInputStream(bytes)).use { zip ->
            while (true) {
                val entry = zip.nextEntry ?: break
                val entryName = entry.name.substringAfterLast('/')
                if (!entry.isDirectory && (entryName.endsWith(".json", true) || entryName.endsWith(".csv", true))) {
                    val content = zip.readBytes()
                    if (entryName.endsWith(".csv", true)) parseCsv(entryName, String(content, Charsets.UTF_8))?.let(out::add)
                    else out += parseJson(entryName, String(content, Charsets.UTF_8))
                }
            }
        }
        return out
    }

    internal fun parseJson(fileName: String, text: String): List<ImportedPlaylist> {
        val root = runCatching { JsonParser.parseString(text) }.getOrNull()?.takeIf { it.isJsonObject }?.asJsonObject
            ?: return emptyList()
        val out = mutableListOf<ImportedPlaylist>()
        if (fileName.startsWith("YourLibrary", ignoreCase = true) || root.has("tracks")) {
            val liked = root.list("tracks").mapNotNull { o ->
                ImportedTrack(o.str("track") ?: return@mapNotNull null, o.str("artist").orEmpty(), o.str("album").orEmpty())
            }
            if (liked.isNotEmpty()) out += ImportedPlaylist(LIKED_NAME, liked, liked = true)
        }
        root.list("playlists").forEach { playlist ->
            val tracks = playlist.list("items").mapNotNull { item ->
                val t = item.obj("track")
                ImportedTrack(t.str("trackName") ?: return@mapNotNull null, t.str("artistName").orEmpty(), t.str("albumName").orEmpty())
            }
            if (tracks.isNotEmpty()) out += ImportedPlaylist(playlist.str("name") ?: "Spotify", tracks)
        }
        return out
    }

    internal fun parseCsv(fileName: String, text: String): ImportedPlaylist? {
        val rows = csvRows(text.removePrefix("﻿"))
        if (rows.size < 2) return null
        val header = rows.first().map { it.trim().lowercase() }
        fun col(vararg names: String) = names.firstNotNullOfOrNull { n -> header.indexOf(n).takeIf { it >= 0 } }
        val title = col("track name") ?: return null
        val artist = col("artist name(s)", "artist name", "artist")
        val album = col("album name", "album")
        val duration = col("duration (ms)", "track duration (ms)")
        val tracks = rows.drop(1).mapNotNull { row ->
            val t = row.getOrNull(title)?.trim().orEmpty()
            if (t.isEmpty()) return@mapNotNull null
            ImportedTrack(
                title = t,
                // Exportify separates several artists with commas.
                artist = artist?.let { row.getOrNull(it) }?.split(',')?.firstOrNull()?.trim().orEmpty(),
                album = album?.let { row.getOrNull(it) }?.trim().orEmpty(),
                durationMs = duration?.let { row.getOrNull(it) }?.trim()?.toLongOrNull(),
            )
        }
        if (tracks.isEmpty()) return null
        val base = fileName.substringBeforeLast('.').replace('_', ' ').trim()
        val liked = base.equals("liked songs", ignoreCase = true) || base.equals("liked", ignoreCase = true)
        return ImportedPlaylist(if (liked) LIKED_NAME else base.ifEmpty { "Spotify" }, tracks, liked)
    }

    /** RFC 4180 CSV: quoted fields may hold commas, newlines and doubled quotes. */
    internal fun csvRows(text: String): List<List<String>> {
        val rows = mutableListOf<List<String>>()
        var row = mutableListOf<String>()
        val field = StringBuilder()
        var quoted = false
        var i = 0
        while (i < text.length) {
            val c = text[i]
            when {
                quoted && c == '"' && text.getOrNull(i + 1) == '"' -> { field.append('"'); i++ }
                c == '"' -> quoted = !quoted
                !quoted && c == ',' -> { row += field.toString(); field.clear() }
                !quoted && (c == '\n' || c == '\r') -> {
                    if (c == '\r' && text.getOrNull(i + 1) == '\n') i++
                    row += field.toString(); field.clear()
                    if (row.any { it.isNotEmpty() }) rows += row
                    row = mutableListOf()
                }
                else -> field.append(c)
            }
            i++
        }
        row += field.toString()
        if (row.any { it.isNotEmpty() }) rows += row
        return rows
    }

    /** Lower case, no accents, no brackets ("(feat. X)", "- Remastered 2011") and no punctuation. */
    internal fun normalize(text: String): String {
        val plain = Normalizer.normalize(text.lowercase(), Normalizer.Form.NFD).replace(Regex("\\p{Mn}+"), "")
        return plain
            .replace(Regex("[(\\[].*?[)\\]]"), " ")
            .replace(Regex(" - .*(remaster|version|live|edit|mix|mono|stereo).*$"), " ")
            .replace(Regex("[^\\p{L}\\p{N}]+"), " ")
            .trim()
    }

    /** How well a found track fits the wanted one, 0..1; below [MATCH_THRESHOLD] it is not the same song. */
    internal fun score(wanted: ImportedTrack, found: MusicTrack): Double {
        val wantTitle = normalize(wanted.title)
        val gotTitle = normalize(found.title)
        val title = when {
            wantTitle.isEmpty() || gotTitle.isEmpty() -> 0.0
            wantTitle == gotTitle -> 1.0
            gotTitle.contains(wantTitle) || wantTitle.contains(gotTitle) -> 0.75
            else -> 0.0
        }
        if (title == 0.0) return 0.0
        val wantArtist = normalize(wanted.artist)
        val gotArtist = normalize(found.artist)
        val artist = when {
            wantArtist.isEmpty() -> 0.5
            gotArtist == wantArtist -> 1.0
            gotArtist.isNotEmpty() && (gotArtist.contains(wantArtist) || wantArtist.contains(gotArtist)) -> 0.8
            // Addons often fold the artist into the title ("Artist - Song").
            normalize(found.title).contains(wantArtist) -> 0.7
            else -> 0.0
        }
        val wantSec = wanted.durationMs?.div(1000)
        val duration = when {
            wantSec == null || found.durationSec <= 0 -> 0.5
            kotlin.math.abs(wantSec - found.durationSec) <= 5 -> 1.0
            kotlin.math.abs(wantSec - found.durationSec) <= 20 -> 0.5
            else -> 0.0
        }
        // A song named exactly the same beats a remix or live take that only differs in brackets.
        val exact = if (wanted.title.trim().equals(found.title.trim(), ignoreCase = true)) 0.05 else 0.0
        return title * 0.5 + artist * 0.35 + duration * 0.15 + exact
    }

    /**
     * Looks every song up in [source] and keeps the best fit; songs nobody has are left out.
     * Reports progress as (done, total).
     */
    suspend fun match(
        source: MusicSource,
        tracks: List<ImportedTrack>,
        onProgress: (Int, Int) -> Unit = { _, _ -> },
    ): List<MusicTrack?> = coroutineScope {
        val gate = Semaphore(4)
        var done = 0
        tracks.map { wanted ->
            async {
                gate.withPermit {
                    val query = listOf(wanted.artist, wanted.title).filter { it.isNotBlank() }.joinToString(" ")
                    val found = runCatching { source.search(query).tracks }.getOrDefault(emptyList())
                    val best = found.maxByOrNull { score(wanted, it) }?.takeIf { score(wanted, it) >= MATCH_THRESHOLD }
                    synchronized(this@SpotifyImport) { done++; onProgress(done, tracks.size) }
                    best
                }
            }
        }.map { it.await() }
    }

    const val LIKED_NAME = "Liked Songs"
    const val MATCH_THRESHOLD = 0.7
}
