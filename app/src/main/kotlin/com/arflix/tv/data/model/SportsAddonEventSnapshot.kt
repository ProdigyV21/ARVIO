package com.arflix.tv.data.model

/** On-disk copy of the sports add-on schedule (Gson; kept from R8 with the other data.model classes). */
data class SportsAddonEventSnapshot(
    val installations: List<String> = emptyList(),
    val savedAt: Long = 0L,
    val events: List<SportsAddonEvent> = emptyList(),
)
