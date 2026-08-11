package com.arflix.tv.data.repository.sync

/**
 * The remote sync backend a profile is connected to. Mutually exclusive per
 * profile: a profile uses Trakt, MDBList, or neither (local/Supabase only).
 */
enum class SyncProvider {
    NONE,
    TRAKT,
    MDBLIST,
    SIMKL;

    companion object {
        fun fromStorage(value: String?): SyncProvider = when (value?.lowercase()) {
            "trakt" -> TRAKT
            "mdblist" -> MDBLIST
            "simkl" -> SIMKL
            else -> NONE
        }
    }

    fun toStorage(): String = name.lowercase()
}
