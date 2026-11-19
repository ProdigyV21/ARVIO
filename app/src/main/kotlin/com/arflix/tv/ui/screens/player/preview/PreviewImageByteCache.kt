package com.arflix.tv.ui.screens.player.preview

/** Compressed sheets only; source-scoped and bounded independently of decoded frame memory. */
internal class PreviewImageByteCache(private val limit: Int = 8 * 1024 * 1024) {
    data class Key(val url: String, val offset: Long? = null, val length: Int? = null)
    private val entries = LinkedHashMap<Key, ByteArray>(4, .75f, true)
    private var bytes = 0

    fun get(key: Key): ByteArray? = entries[key]

    fun put(key: Key, value: ByteArray) {
        entries.remove(key)?.let { bytes -= it.size }
        if (value.size > limit) return
        entries[key] = value
        bytes += value.size
        val iterator = entries.entries.iterator()
        while (bytes > limit && iterator.hasNext()) {
            bytes -= iterator.next().value.size
            iterator.remove()
        }
    }

    fun clear() { entries.clear(); bytes = 0 }
}
