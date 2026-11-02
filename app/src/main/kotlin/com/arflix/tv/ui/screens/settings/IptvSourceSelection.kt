package com.arflix.tv.ui.screens.settings

internal enum class IptvSourceSection { PLAYLISTS, PORTALS }

internal data class IptvSourceSelection(
    val section: IptvSourceSection = IptvSourceSection.PLAYLISTS,
    val ids: Set<String> = emptySet()
) {
    val isActive: Boolean get() = ids.isNotEmpty()

    fun contains(section: IptvSourceSection, id: String): Boolean = this.section == section && id in ids

    fun toggle(section: IptvSourceSection, id: String): IptvSourceSelection =
        if (this.section != section) IptvSourceSelection(section, setOf(id))
        else copy(ids = if (id in ids) ids - id else ids + id)

    fun retain(playlistIds: Set<String>, portalIds: Set<String>): IptvSourceSelection =
        copy(ids = ids.intersect(if (section == IptvSourceSection.PLAYLISTS) playlistIds else portalIds))
}
