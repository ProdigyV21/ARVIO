package com.arflix.tv.data.telegram

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.arflix.tv.data.repository.CloudSyncInvalidationBus
import com.arflix.tv.data.repository.CloudSyncScope
import com.arflix.tv.util.settingsDataStore
import com.arflix.tv.util.telegramDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import org.drinkless.tdlib.TdApi
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

data class TelegramChat(
    val id: Long,
    val title: String,
    val type: String,
    val memberCount: Int,
    val isChannel: Boolean
)

data class TelegramVideoMessage(
    val messageId: Long,
    val chatId: Long,
    val fileName: String,
    val fileId: Int,
    val fileSize: Long,
    val duration: Int,
    val mimeType: String,
    val caption: String
)

@Singleton
class TelegramRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val client: TelegramClient,
    private val proxy: TelegramStreamingProxy,
    private val invalidationBus: CloudSyncInvalidationBus
) {
    companion object {
        private const val TAG = "TelegramRepository"
        private const val SEARCH_REQUEST_TIMEOUT_MS = 20_000L
        private val KEY_EXCLUDED_CHATS = stringPreferencesKey("excluded_chat_ids")
        /**
         * In the main settings store, not [telegramDataStore]: it is cloud-synced across devices
         * (CloudSyncRepository "telegramSearchOnClickOnly"), and cloud sync only reads that store.
         */
        val KEY_SEARCH_ON_CLICK_ONLY = booleanPreferencesKey("telegram_search_on_click_only")

        fun sessionMarker(context: Context) = File(context.filesDir, "tdlib_session_ok")

        fun wipeTdlibFiles(context: Context) {
            sessionMarker(context).delete()
            File(context.filesDir, "tdlib").deleteRecursively()
            File(context.filesDir, "tdlib_files").deleteRecursively()
            Log.d(TAG, "TDLib database wiped")
        }
    }

    val authState = client.authState

    init {
        proxy.start()
        val hasValidSession = sessionMarker(context).exists()
        if (!hasValidSession) {
            // Wipe any partial database left by a previous crashed session
            // so TDLib doesn't try to open a corrupted database on next Connect.
            File(context.filesDir, "tdlib").deleteRecursively()
            File(context.filesDir, "tdlib_files").deleteRecursively()
        } else {
            // Purge leftover video stream files from previous session on launch
            clearCache()
            client.initialize()
        }
    }

    fun isAuthenticated(): Boolean = client.authState.value is TelegramAuthState.Ready

    fun startAuth() = client.initialize()
    fun requestQrCode() = client.requestQrCode()
    fun submitPhone(phone: String) = client.submitPhone(phone)
    fun submitCode(code: String) = client.submitCode(code)
    fun submitPassword(password: String) = client.submitPassword(password)

    fun disconnect() {
        client.reset()
        wipeTdlibFiles(context)
    }

    fun getCacheSize(): Long {
        val dir = File(context.filesDir, "tdlib_files")
        return if (dir.exists()) dir.walkBottomUp().filter { it.isFile }.sumOf { it.length() } else 0L
    }

    fun clearCache() {
        File(context.filesDir, "tdlib_files").listFiles()?.forEach { it.deleteRecursively() }
    }

    suspend fun getChats(limit: Int = 200): List<TelegramChat> {
        val result = client.sendRequest(TdApi.GetChats().also { it.limit = limit })
        val chats = (result as? TdApi.Chats) ?: return emptyList()
        val out = mutableListOf<TelegramChat>()

        for (chatId in chats.chatIds) {
            val chat = client.sendRequest(TdApi.GetChat(chatId)) as? TdApi.Chat ?: continue
            when (val type = chat.type) {
                is TdApi.ChatTypePrivate, is TdApi.ChatTypeSecret -> continue
                is TdApi.ChatTypeSupergroup -> out.add(
                    TelegramChat(
                        id = chatId,
                        title = chat.title,
                        type = "chatTypeSupergroup",
                        memberCount = 0,
                        isChannel = type.isChannel
                    )
                )
                is TdApi.ChatTypeBasicGroup -> out.add(
                    TelegramChat(
                        id = chatId,
                        title = chat.title,
                        type = "chatTypeBasicGroup",
                        memberCount = 0,
                        isChannel = false
                    )
                )
            }
        }

        Log.d(TAG, "Loaded ${out.size} chats")
        return out
    }

    /** Search a single chat for video files matching the query. */
    suspend fun searchVideoMessagesInChat(
        chatId: Long,
        query: String,
        limit: Int = 20
    ): List<TelegramVideoMessage> {
        val filters = listOf(
            TdApi.SearchMessagesFilterDocument(),
            TdApi.SearchMessagesFilterVideo()
        )
        val seen = mutableSetOf<Pair<String, Long>>()
        val results = mutableListOf<TelegramVideoMessage>()

        for (filter in filters) {
            val result = client.sendRequest(TdApi.SearchChatMessages().also { req ->
                req.chatId = chatId
                req.query = query
                req.fromMessageId = 0
                req.offset = 0
                req.limit = limit
                req.filter = filter
            })
            val found = (result as? TdApi.FoundChatMessages) ?: continue

            for (msg in found.messages) {
                when (val content = msg.content) {
                    is TdApi.MessageDocument -> {
                        val mime = content.document.mimeType
                        if (!mime.startsWith("video/") && mime != "application/x-matroska") continue
                        val key = content.document.fileName to content.document.document.size
                        if (seen.add(key)) results.add(TelegramVideoMessage(
                            messageId = msg.id, chatId = msg.chatId,
                            fileName = content.document.fileName, fileId = content.document.document.id,
                            fileSize = content.document.document.size, duration = 0,
                            mimeType = mime, caption = content.caption.text
                        ))
                    }
                    is TdApi.MessageVideo -> {
                        val key = content.video.fileName to content.video.video.size
                        if (seen.add(key)) results.add(TelegramVideoMessage(
                            messageId = msg.id, chatId = msg.chatId,
                            fileName = content.video.fileName, fileId = content.video.video.id,
                            fileSize = content.video.video.size, duration = content.video.duration,
                            mimeType = content.video.mimeType, caption = content.caption.text
                        ))
                    }
                    else -> continue
                }
            }
        }
        return results
    }

    /**
     * Searches globally across all chats (equivalent to Telethon's iter_messages(None, ...)) and
     * keeps the video files.
     *
     * ONE request, with no type filter; videos and video documents are picked out below. It used to
     * be two per phrase (Document, then Video), and global search is exactly what Telegram rate-
     * limits: one episode lookup sent 26 of them, most of which TDLib then held back until the
     * caller gave up (Special Ops S1E1, Sept 2026: 8 of 9 requests unanswered after 10s).
     */
    suspend fun searchVideoMessages(
        query: String,
        limit: Int = 50
    ): List<TelegramVideoMessage> {
        val filters = listOf<TdApi.SearchMessagesFilter?>(null)
        val seen = mutableSetOf<Pair<String, Long>>() // dedupe by (fileName, fileSize)
        val results = mutableListOf<TelegramVideoMessage>()

        for (filter in filters) {
            // Global search answers in 1–9s (Special Ops S1E1, Sept 2026: "פרק 1" 7.9s,
            // "lioness s01e01" 8.9s); the default 10s cut those off on a slower second try.
            val result = client.sendRequest(TdApi.SearchMessages().also { req ->
                req.chatList = null  // null = search all chats (like Telethon's iter_messages(None))
                req.query = query
                req.offset = ""
                req.limit = limit
                req.filter = filter
            }, timeoutMs = SEARCH_REQUEST_TIMEOUT_MS)
            val found = (result as? TdApi.FoundMessages) ?: continue

            for (msg in found.messages) {
                when (val content = msg.content) {
                    is TdApi.MessageDocument -> {
                        val mime = content.document.mimeType
                        if (!mime.startsWith("video/") && mime != "application/x-matroska") continue
                        val key = content.document.fileName to content.document.document.size
                        if (seen.add(key)) {
                            results.add(TelegramVideoMessage(
                                messageId = msg.id,
                                chatId = msg.chatId,
                                fileName = content.document.fileName,
                                fileId = content.document.document.id,
                                fileSize = content.document.document.size,
                                duration = 0,
                                mimeType = mime,
                                caption = content.caption.text
                            ))
                        }
                    }
                    is TdApi.MessageVideo -> {
                        val key = content.video.fileName to content.video.video.size
                        if (seen.add(key)) {
                            results.add(TelegramVideoMessage(
                                messageId = msg.id,
                                chatId = msg.chatId,
                                fileName = content.video.fileName,
                                fileId = content.video.video.id,
                                fileSize = content.video.video.size,
                                duration = content.video.duration,
                                mimeType = content.video.mimeType,
                                caption = content.caption.text
                            ))
                        }
                    }
                    else -> continue
                }
            }
        }

        return results
    }

    fun getStreamUrl(fileId: Int): String = proxy.getUrl(fileId)

    /**
     * "Only search Telegram when clicking the Telegram source" (Telegram settings, on by default).
     * Every source list used to search Telegram by itself — opening a show, pre-selecting an
     * episode, the player's own list — and global search is what Telegram rate-limits, so the
     * lookups nobody looked at used up the ones the user wanted. When on, a source list shows only
     * what an earlier search found and offers the search as a row the user selects.
     */
    val searchOnClickOnly: Flow<Boolean> =
        context.settingsDataStore.data.map { prefs -> prefs[KEY_SEARCH_ON_CLICK_ONLY] ?: true }

    suspend fun setSearchOnClickOnly(enabled: Boolean) {
        context.settingsDataStore.edit { prefs -> prefs[KEY_SEARCH_ON_CLICK_ONLY] = enabled }
        invalidationBus.markDirty(CloudSyncScope.PROFILE_SETTINGS, reason = "telegram search on click")
    }

    fun getExcludedChatIds(): Flow<Set<Long>> =
        context.telegramDataStore.data.map { prefs ->
            prefs[KEY_EXCLUDED_CHATS]
                ?.split(",")
                ?.mapNotNull { it.toLongOrNull() }
                ?.toSet()
                ?: emptySet()
        }

    suspend fun setExcludedChatIds(ids: Set<Long>) {
        context.telegramDataStore.edit { prefs ->
            prefs[KEY_EXCLUDED_CHATS] = ids.joinToString(",")
        }
    }

    suspend fun toggleChatExclusion(chatId: Long, exclude: Boolean) {
        val current = getExcludedChatIds().first().toMutableSet()
        if (exclude) current.add(chatId) else current.remove(chatId)
        setExcludedChatIds(current)
    }
}
