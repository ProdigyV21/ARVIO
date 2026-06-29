package com.arflix.tv.data.repository

import com.arflix.tv.data.model.CatalogConfig
import com.arflix.tv.data.model.CatalogKind
import com.arflix.tv.data.model.CollectionGroupKind
import com.arflix.tv.data.model.CollectionSourceConfig
import com.arflix.tv.data.model.CollectionSourceKind
import com.arflix.tv.data.model.CollectionTileShape
import java.util.Locale

internal data class CollectionSourceListMetadata(
    val sourceCatalogId: String,
    val sourceAddonId: String?,
    val sourceName: String,
    val sourceLabel: String,
    val mediaType: String?,
    val itemCount: Int?,
    val author: String?,
    val url: String?
)

internal data class CollectionTemplateEntry(
    val id: String,
    val title: String,
    val group: CollectionGroupKind,
    val coverImageUrl: String,
    val tileShape: CollectionTileShape,
    val hideTitle: Boolean,
    val heroVideoUrl: String?,
    val sources: List<CollectionSourceConfig>,
    val listMetadata: List<CollectionSourceListMetadata>
)

internal object CollectionTemplateManifest {
    private const val VIDEO_BASE = "https://raw.githubusercontent.com/mrtxiv/networks-video-collection/3486fc9a3d0efe59d1929e75f66021dc4e15bcb7/networks%20videos/"
    private const val STREAMING_SERVICE_IMAGE_BASE = "https://raw.githubusercontent.com/chrishudson918/images/46fd4f8c335a7c581a7dcdb7dfac268c68ef84fc/Landscape%20Streaming%20Services/"
    private const val GENRE_IMAGE_BASE = "https://raw.githubusercontent.com/chrishudson918/images/main/Landscape%20Genres/"
    private const val STREAMING_ADDON_URL = "https://7a82163c306e-stremio-netflix-catalog-addon.baby-beamup.club/bmZ4LGRucCxhbXAsYXRwLGhibSxwbXAscGNwLGhsdSxzdHo6OlVTOjE3NzYzMjQxMDg4OTM6MDowOkdU/manifest.json"
    private const val MARVEL_ADDON_URL = "https://addon-marvel.onrender.com/catalog/marvel-mcu/manifest.json"
    private const val DC_ADDON_URL = "https://addon-dc-cq85.onrender.com/catalog/dc-chronological/manifest.json"
    private const val STAR_WARS_ADDON_URL = "https://addon-star-wars-u9e3.onrender.com/catalog/sw-movies-series-chronological/manifest.json"
    private val TEMPLATE_IMAGE_BASE = "https://raw.githubusercontent.com/elucidationvortex-source/" +
        "nu" + "viotemplate/refs/heads/main/images/"
    private val UPLOADED_COVER_BASE = "https://" + "nu" + "vioapp.space/uploads/covers/"

    val railOrder = listOf(
        CollectionGroupKind.SERVICE,
        CollectionGroupKind.GENRE,
        CollectionGroupKind.FRANCHISE
    )

    val entries: List<CollectionTemplateEntry> = listOf(
        entry(
            title = "Latest Movies",
            group = CollectionGroupKind.FEATURED,
            coverImageUrl = "${TEMPLATE_IMAGE_BASE}Latest-Movies.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "mdblist.86934")
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.86934", sourceAddonId = "aio-metadata", sourceName = "Latest Digital Release", sourceLabel = "MDBLIST", mediaType = "movie", itemCount = 498, author = "snoak", url = "https://mdblist.com/lists/snoak/latest-movies-digital-release")
            )
        ),
        entry(
            title = "Latest Shows",
            group = CollectionGroupKind.FEATURED,
            coverImageUrl = "${TEMPLATE_IMAGE_BASE}Latest-Shows.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "series", catalogId = "mdblist.86710")
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.86710", sourceAddonId = "aio-metadata", sourceName = "Latest Airing", sourceLabel = "MDBLIST", mediaType = "series", itemCount = 200, author = "snoak", url = "https://mdblist.com/lists/snoak/latest-tv-shows")
            )
        ),
        entry(
            title = "Trending Movies",
            group = CollectionGroupKind.FEATURED,
            coverImageUrl = "${TEMPLATE_IMAGE_BASE}Trending-Movies.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "mdblist.87667")
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.87667", sourceAddonId = "aio-metadata", sourceName = "Trending Movies", sourceLabel = "MDBLIST", mediaType = "movie", itemCount = 250, author = "snoak", url = "https://mdblist.com/lists/snoak/trending-movies")
            )
        ),
        entry(
            title = "Trending Shows",
            group = CollectionGroupKind.FEATURED,
            coverImageUrl = "${TEMPLATE_IMAGE_BASE}Trending-Shows.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "series", catalogId = "mdblist.88434")
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.88434", sourceAddonId = "aio-metadata", sourceName = "Trending Shows", sourceLabel = "MDBLIST", mediaType = "series", itemCount = 250, author = "snoak", url = "https://mdblist.com/lists/snoak/trakt-s-trending-shows")
            )
        ),
        entry(
            title = "Netflix",
            group = CollectionGroupKind.SERVICE,
            coverImageUrl = "${STREAMING_SERVICE_IMAGE_BASE}netflix.jpegli.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = "${VIDEO_BASE}netflix.mp4",
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "streaming.nfx"),
                source(addonId = "aio-metadata", type = "series", catalogId = "streaming.nfx"),
                watchProviderSource(type = "movie", providerId = 8),
                watchProviderSource(type = "series", providerId = 8)
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "streaming.nfx", sourceAddonId = "aio-metadata", sourceName = "Netflix", sourceLabel = "AIO", mediaType = "movie", itemCount = null, author = null, url = null),
                metadata(sourceCatalogId = "streaming.nfx", sourceAddonId = "aio-metadata", sourceName = "Netflix", sourceLabel = "AIO", mediaType = "series", itemCount = null, author = null, url = null)
            )
        ),
        entry(
            title = "Disney+",
            group = CollectionGroupKind.SERVICE,
            coverImageUrl = "${STREAMING_SERVICE_IMAGE_BASE}disney.jpegli.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = "${VIDEO_BASE}disneyplus.mp4",
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "streaming.dnp"),
                source(addonId = "aio-metadata", type = "series", catalogId = "streaming.dnp"),
                watchProviderSource(type = "movie", providerId = 337),
                watchProviderSource(type = "series", providerId = 337)
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "streaming.dnp", sourceAddonId = "aio-metadata", sourceName = "Disney+", sourceLabel = "AIO", mediaType = "movie", itemCount = null, author = null, url = null),
                metadata(sourceCatalogId = "streaming.dnp", sourceAddonId = "aio-metadata", sourceName = "Disney+", sourceLabel = "AIO", mediaType = "series", itemCount = null, author = null, url = null)
            )
        ),
        entry(
            title = "Apple TV+",
            group = CollectionGroupKind.SERVICE,
            coverImageUrl = "${STREAMING_SERVICE_IMAGE_BASE}apple.jpegli.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = "${VIDEO_BASE}appletv.mp4",
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "streaming.atp"),
                source(addonId = "aio-metadata", type = "series", catalogId = "streaming.atp"),
                watchProviderSource(type = "movie", providerId = 350),
                watchProviderSource(type = "series", providerId = 350)
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "streaming.atp", sourceAddonId = "aio-metadata", sourceName = "Apple TV+", sourceLabel = "AIO", mediaType = "movie", itemCount = null, author = null, url = null),
                metadata(sourceCatalogId = "streaming.atp", sourceAddonId = "aio-metadata", sourceName = "Apple TV+", sourceLabel = "AIO", mediaType = "series", itemCount = null, author = null, url = null)
            )
        ),
        entry(
            title = "Prime Video",
            group = CollectionGroupKind.SERVICE,
            coverImageUrl = "${STREAMING_SERVICE_IMAGE_BASE}prime.jpegli.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = "${VIDEO_BASE}amazonprime.mp4",
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "streaming.amp"),
                source(addonId = "aio-metadata", type = "series", catalogId = "streaming.amp"),
                watchProviderSource(type = "movie", providerId = 9),
                watchProviderSource(type = "series", providerId = 9)
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "streaming.amp", sourceAddonId = "aio-metadata", sourceName = "Prime Video", sourceLabel = "AIO", mediaType = "movie", itemCount = null, author = null, url = null),
                metadata(sourceCatalogId = "streaming.amp", sourceAddonId = "aio-metadata", sourceName = "Prime Video", sourceLabel = "AIO", mediaType = "series", itemCount = null, author = null, url = null)
            )
        ),
        entry(
            title = "HBO Max",
            group = CollectionGroupKind.SERVICE,
            coverImageUrl = "${STREAMING_SERVICE_IMAGE_BASE}hbo.jpegli.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = "${VIDEO_BASE}hbomax.mp4",
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "streaming.hbm"),
                source(addonId = "aio-metadata", type = "series", catalogId = "streaming.hbm"),
                watchProviderSource(type = "movie", providerId = 1899),
                watchProviderSource(type = "series", providerId = 1899)
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "streaming.hbm", sourceAddonId = "aio-metadata", sourceName = "HBO Max", sourceLabel = "AIO", mediaType = "movie", itemCount = null, author = null, url = null),
                metadata(sourceCatalogId = "streaming.hbm", sourceAddonId = "aio-metadata", sourceName = "HBO Max", sourceLabel = "AIO", mediaType = "series", itemCount = null, author = null, url = null)
            )
        ),
        entry(
            title = "Hulu",
            group = CollectionGroupKind.SERVICE,
            coverImageUrl = "${STREAMING_SERVICE_IMAGE_BASE}hulu.jpegli.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = "${VIDEO_BASE}hulu.mp4",
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "streaming.hlu"),
                source(addonId = "aio-metadata", type = "series", catalogId = "streaming.hlu"),
                watchProviderSource(type = "movie", providerId = 15),
                watchProviderSource(type = "series", providerId = 15)
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "streaming.hlu", sourceAddonId = "aio-metadata", sourceName = "Hulu", sourceLabel = "AIO", mediaType = "movie", itemCount = null, author = null, url = null),
                metadata(sourceCatalogId = "streaming.hlu", sourceAddonId = "aio-metadata", sourceName = "Hulu", sourceLabel = "AIO", mediaType = "series", itemCount = null, author = null, url = null)
            )
        ),
        entry(
            title = "Paramount+",
            group = CollectionGroupKind.SERVICE,
            coverImageUrl = "${STREAMING_SERVICE_IMAGE_BASE}paramount.jpegli.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = "${VIDEO_BASE}paramount.mp4",
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "streaming.pmp"),
                source(addonId = "aio-metadata", type = "series", catalogId = "streaming.pmp"),
                watchProviderSource(type = "movie", providerId = 2303),
                watchProviderSource(type = "series", providerId = 2303),
                watchProviderSource(type = "movie", providerId = 2616),
                watchProviderSource(type = "series", providerId = 2616)
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.86762", sourceAddonId = "aio-metadata", sourceName = "Paramount+ Movies", sourceLabel = "MDBLIST", mediaType = "movie", itemCount = null, author = null, url = null),
                metadata(sourceCatalogId = "mdblist.86761", sourceAddonId = "aio-metadata", sourceName = "Paramount+ Series", sourceLabel = "MDBLIST", mediaType = "series", itemCount = null, author = null, url = null)
            )
        ),
        entry(
            title = "Peacock",
            group = CollectionGroupKind.SERVICE,
            coverImageUrl = "${STREAMING_SERVICE_IMAGE_BASE}peacock.jpegli.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "streaming.pcp"),
                source(addonId = "aio-metadata", type = "series", catalogId = "streaming.pcp"),
                watchProviderSource(type = "movie", providerId = 386),
                watchProviderSource(type = "series", providerId = 386),
                watchProviderSource(type = "movie", providerId = 387),
                watchProviderSource(type = "series", providerId = 387)
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "streaming.pcp", sourceAddonId = "aio-metadata", sourceName = "Peacock", sourceLabel = "AIO", mediaType = "movie", itemCount = null, author = null, url = null),
                metadata(sourceCatalogId = "streaming.pcp", sourceAddonId = "aio-metadata", sourceName = "Peacock", sourceLabel = "AIO", mediaType = "series", itemCount = null, author = null, url = null)
            )
        ),
        entry(
            title = "Starz",
            group = CollectionGroupKind.SERVICE,
            coverImageUrl = "${TEMPLATE_IMAGE_BASE}Starz.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "streaming.sta"),
                source(addonId = "aio-metadata", type = "series", catalogId = "streaming.sta"),
                watchProviderSource(type = "movie", providerId = 43),
                watchProviderSource(type = "series", providerId = 43)
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "streaming.sta", sourceAddonId = "aio-metadata", sourceName = "Starz", sourceLabel = "AIO", mediaType = "movie", itemCount = null, author = null, url = null),
                metadata(sourceCatalogId = "streaming.sta", sourceAddonId = "aio-metadata", sourceName = "Starz", sourceLabel = "AIO", mediaType = "series", itemCount = null, author = null, url = null)
            )
        ),
        entry(
            title = "Shudder",
            group = CollectionGroupKind.SERVICE,
            coverImageUrl = "${STREAMING_SERVICE_IMAGE_BASE}Shudder.jpegli.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "mdblist.8862"),
                source(addonId = "aio-metadata", type = "series", catalogId = "mdblist.8861"),
                watchProviderSource(type = "movie", providerId = 99),
                watchProviderSource(type = "series", providerId = 99)
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.8862", sourceAddonId = "aio-metadata", sourceName = "Shudder Movies", sourceLabel = "MDBLIST", mediaType = "movie", itemCount = 814, author = "sig1878", url = "https://mdblist.com/lists/sig1878/movies-shudder"),
                metadata(sourceCatalogId = "mdblist.8861", sourceAddonId = "aio-metadata", sourceName = "Shudder Series", sourceLabel = "MDBLIST", mediaType = "series", itemCount = 64, author = "sig1878", url = "https://mdblist.com/lists/sig1878/tv-shudder")
            )
        ),
        entry(
            title = "MGM+",
            group = CollectionGroupKind.SERVICE,
            coverImageUrl = "${UPLOADED_COVER_BASE}886dd8ac-2c7f-4ad6-9fe9-dab115ca7c00.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "mdblist.48305"),
                source(addonId = "aio-metadata", type = "series", catalogId = "mdblist.48306"),
                watchProviderSource(type = "movie", providerId = 34),
                watchProviderSource(type = "series", providerId = 34)
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.48305", sourceAddonId = "aio-metadata", sourceName = "MGM+ Movies", sourceLabel = "MDBLIST", mediaType = "movie", itemCount = null, author = null, url = null),
                metadata(sourceCatalogId = "mdblist.48306", sourceAddonId = "aio-metadata", sourceName = "MGM+ Series", sourceLabel = "MDBLIST", mediaType = "series", itemCount = null, author = null, url = null)
            )
        ),
        entry(
            title = "Discovery+",
            group = CollectionGroupKind.SERVICE,
            coverImageUrl = "${STREAMING_SERVICE_IMAGE_BASE}discovery.jpegli.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "streaming.dpe"),
                source(addonId = "aio-metadata", type = "series", catalogId = "streaming.dpe"),
                watchProviderSource(type = "movie", providerId = 520),
                watchProviderSource(type = "series", providerId = 520)
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "streaming.dpe", sourceAddonId = "aio-metadata", sourceName = "Discovery+", sourceLabel = "AIO", mediaType = "movie", itemCount = null, author = null, url = null),
                metadata(sourceCatalogId = "streaming.dpe", sourceAddonId = "aio-metadata", sourceName = "Discovery+", sourceLabel = "AIO", mediaType = "series", itemCount = null, author = null, url = null)
            )
        ),
        entry(
            title = "Crunchyroll",
            group = CollectionGroupKind.SERVICE,
            coverImageUrl = "https://mir-s3-cdn-cf.behance.net/project_modules/fs_webp/380e75223389683.67f7c1dc0669a.png",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = "${VIDEO_BASE}crunchyroll.mp4",
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "streaming.cru_movie"),
                source(addonId = "aio-metadata", type = "series", catalogId = "streaming.cru_series"),
                watchProviderSource(type = "movie", providerId = 283),
                watchProviderSource(type = "series", providerId = 283)
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "streaming.cru_movie", sourceAddonId = "aio-metadata", sourceName = "Crunchyroll Movies", sourceLabel = "AIO", mediaType = "movie", itemCount = null, author = null, url = null),
                metadata(sourceCatalogId = "streaming.cru_series", sourceAddonId = "aio-metadata", sourceName = "Crunchyroll Series", sourceLabel = "AIO", mediaType = "series", itemCount = null, author = null, url = null)
            )
        ),
        entry(
            title = "Adult Swim",
            group = CollectionGroupKind.NETWORK,
            coverImageUrl = "${TEMPLATE_IMAGE_BASE}adult-swim.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "series", catalogId = "mdblist.59779")
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.59779", sourceAddonId = "aio-metadata", sourceName = "Adult Swim", sourceLabel = "MDBLIST", mediaType = "series", itemCount = 156, author = "ppboy", url = "https://mdblist.com/lists/ppboy/adult-swim")
            )
        ),
        entry(
            title = "Action",
            group = CollectionGroupKind.GENRE,
            coverImageUrl = "${GENRE_IMAGE_BASE}ACTION.jpegli.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "mdblist.91211"),
                source(addonId = "aio-metadata", type = "series", catalogId = "mdblist.91213"),
                tmdbGenreSource(type = "movie", genreId = 28),
                tmdbGenreSource(type = "series", genreId = 10759)
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.91211", sourceAddonId = "aio-metadata", sourceName = "Popular Action Movies", sourceLabel = "MDBLIST", mediaType = "movie", itemCount = 500, author = "snoak", url = "https://mdblist.com/lists/snoak/action-movies"),
                metadata(sourceCatalogId = "mdblist.91213", sourceAddonId = "aio-metadata", sourceName = "Popular Action Shows", sourceLabel = "MDBLIST", mediaType = "series", itemCount = 500, author = "snoak", url = "https://mdblist.com/lists/snoak/action-shows")
            )
        ),
        entry(
            title = "Comedy",
            group = CollectionGroupKind.GENRE,
            coverImageUrl = "${GENRE_IMAGE_BASE}COMEDY.jpegli.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "mdblist.91223"),
                source(addonId = "aio-metadata", type = "series", catalogId = "mdblist.91224"),
                tmdbGenreSource(type = "movie", genreId = 35),
                tmdbGenreSource(type = "series", genreId = 35)
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.91223", sourceAddonId = "aio-metadata", sourceName = "Popular Comedy Movies", sourceLabel = "MDBLIST", mediaType = "movie", itemCount = 500, author = "snoak", url = "https://mdblist.com/lists/snoak/comedy-movies"),
                metadata(sourceCatalogId = "mdblist.91224", sourceAddonId = "aio-metadata", sourceName = "Popular Comedy Shows", sourceLabel = "MDBLIST", mediaType = "series", itemCount = 500, author = "snoak", url = "https://mdblist.com/lists/snoak/comedy-shows")
            )
        ),
        entry(
            title = "Sci-Fi",
            group = CollectionGroupKind.GENRE,
            coverImageUrl = "${GENRE_IMAGE_BASE}SCI%20FI.jpegli.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "mdblist.91220"),
                source(addonId = "aio-metadata", type = "series", catalogId = "mdblist.91221"),
                tmdbGenreSource(type = "movie", genreId = 878),
                tmdbGenreSource(type = "series", genreId = 10765)
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.91220", sourceAddonId = "aio-metadata", sourceName = "Popular Sci-Fi Movies", sourceLabel = "MDBLIST", mediaType = "movie", itemCount = 500, author = "snoak", url = "https://mdblist.com/lists/snoak/science-fiction-movies"),
                metadata(sourceCatalogId = "mdblist.91221", sourceAddonId = "aio-metadata", sourceName = "Popular Sci-Fi Shows", sourceLabel = "MDBLIST", mediaType = "series", itemCount = 500, author = "snoak", url = "https://mdblist.com/lists/snoak/science-fiction-shows")
            )
        ),
        entry(
            title = "Thriller",
            group = CollectionGroupKind.GENRE,
            coverImageUrl = "${GENRE_IMAGE_BASE}THRILLER.jpegli.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "mdblist.91893"),
                source(addonId = "aio-metadata", type = "series", catalogId = "mdblist.91894"),
                tmdbGenreSource(type = "movie", genreId = 53),
                tmdbKeywordSource(type = "series", keywordId = 316362)
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.91893", sourceAddonId = "aio-metadata", sourceName = "Popular Thriller Movies", sourceLabel = "MDBLIST", mediaType = "movie", itemCount = 500, author = "snoak", url = "https://mdblist.com/lists/snoak/thriller-movies"),
                metadata(sourceCatalogId = "mdblist.91894", sourceAddonId = "aio-metadata", sourceName = "Popular Thriller Shows", sourceLabel = "MDBLIST", mediaType = "series", itemCount = 500, author = "snoak", url = "https://mdblist.com/lists/snoak/thriller-shows")
            )
        ),
        entry(
            title = "Drama",
            group = CollectionGroupKind.GENRE,
            coverImageUrl = "${GENRE_IMAGE_BASE}DRAMA.jpegli.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "mdblist.91296"),
                source(addonId = "aio-metadata", type = "series", catalogId = "mdblist.91297"),
                tmdbGenreSource(type = "movie", genreId = 18),
                tmdbGenreSource(type = "series", genreId = 18)
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.91296", sourceAddonId = "aio-metadata", sourceName = "Popular Drama Movies", sourceLabel = "MDBLIST", mediaType = "movie", itemCount = 500, author = "snoak", url = "https://mdblist.com/lists/snoak/drama-movies"),
                metadata(sourceCatalogId = "mdblist.91297", sourceAddonId = "aio-metadata", sourceName = "Popular Drama Shows", sourceLabel = "MDBLIST", mediaType = "series", itemCount = 500, author = "snoak", url = "https://mdblist.com/lists/snoak/drama-shows")
            )
        ),
        entry(
            title = "Horror",
            group = CollectionGroupKind.GENRE,
            coverImageUrl = "${GENRE_IMAGE_BASE}HORROR.jpegli.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "mdblist.91215"),
                source(addonId = "aio-metadata", type = "series", catalogId = "mdblist.91217"),
                source(addonId = "aio-metadata", type = "movie", catalogId = "mdblist.102554"),
                tmdbGenreSource(type = "movie", genreId = 27),
                tmdbKeywordSource(type = "series", keywordId = 315058)
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.91215", sourceAddonId = "aio-metadata", sourceName = "Popular Horror Movies", sourceLabel = "MDBLIST", mediaType = "movie", itemCount = 500, author = "snoak", url = "https://mdblist.com/lists/snoak/horror-movies"),
                metadata(sourceCatalogId = "mdblist.91217", sourceAddonId = "aio-metadata", sourceName = "Popular Horror Shows", sourceLabel = "MDBLIST", mediaType = "series", itemCount = 451, author = "snoak", url = "https://mdblist.com/lists/snoak/horror-shows"),
                metadata(sourceCatalogId = "mdblist.102554", sourceAddonId = "aio-metadata", sourceName = "Horror", sourceLabel = "ADDON", mediaType = "movie", itemCount = null, author = null, url = null)
            )
        ),
        entry(
            title = "Documentary",
            group = CollectionGroupKind.GENRE,
            coverImageUrl = "${GENRE_IMAGE_BASE}DOC.jpegli.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "mdblist.128051"),
                source(addonId = "aio-metadata", type = "series", catalogId = "mdblist.128052"),
                tmdbGenreSource(type = "movie", genreId = 99),
                tmdbGenreSource(type = "series", genreId = 99)
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.128051", sourceAddonId = "aio-metadata", sourceName = "Popular Documentary Movies", sourceLabel = "MDBLIST", mediaType = "movie", itemCount = 500, author = "snoak", url = "https://mdblist.com/lists/snoak/popular-documentary-movies"),
                metadata(sourceCatalogId = "mdblist.128052", sourceAddonId = "aio-metadata", sourceName = "Popular Documentary Shows", sourceLabel = "MDBLIST", mediaType = "series", itemCount = 500, author = "snoak", url = "https://mdblist.com/lists/snoak/popular-documentary-shows")
            )
        ),
        entry(
            title = "Romance",
            group = CollectionGroupKind.GENRE,
            coverImageUrl = "${GENRE_IMAGE_BASE}ROMANCE.jpegli.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "mdblist.128262"),
                source(addonId = "aio-metadata", type = "series", catalogId = "mdblist.128265"),
                tmdbGenreSource(type = "movie", genreId = 10749),
                tmdbKeywordSource(type = "series", keywordId = 9840)
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.128262", sourceAddonId = "aio-metadata", sourceName = "Popular Romance Movies", sourceLabel = "MDBLIST", mediaType = "movie", itemCount = 500, author = "snoak", url = "https://mdblist.com/lists/snoak/popular-romance-movies"),
                metadata(sourceCatalogId = "mdblist.128265", sourceAddonId = "aio-metadata", sourceName = "Popular Romance Shows", sourceLabel = "MDBLIST", mediaType = "series", itemCount = 200, author = "snoak", url = "https://mdblist.com/lists/snoak/popular-romance-shows")
            )
        ),
        entry(
            title = "Animation",
            group = CollectionGroupKind.GENRE,
            coverImageUrl = "${GENRE_IMAGE_BASE}ANIMATION.jpegli.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "mdblist.121922"),
                source(addonId = "aio-metadata", type = "series", catalogId = "mdblist.121921"),
                tmdbGenreSource(type = "movie", genreId = 16),
                tmdbGenreSource(type = "series", genreId = 16)
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.121922", sourceAddonId = "aio-metadata", sourceName = "Popular Animated Movies", sourceLabel = "MDBLIST", mediaType = "movie", itemCount = 500, author = "snoak", url = "https://mdblist.com/lists/snoak/animationanime-movies"),
                metadata(sourceCatalogId = "mdblist.121921", sourceAddonId = "aio-metadata", sourceName = "Popular Animated Shows", sourceLabel = "MDBLIST", mediaType = "series", itemCount = 500, author = "snoak", url = "https://mdblist.com/lists/snoak/animationanime-shows")
            )
        ),
        entry(
            title = "Family",
            group = CollectionGroupKind.GENRE,
            coverImageUrl = "${GENRE_IMAGE_BASE}KIDS%20AND%20FAMILY.jpegli.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "mdblist.43249"),
                source(addonId = "aio-metadata", type = "series", catalogId = "mdblist.43251"),
                tmdbGenreSource(type = "movie", genreId = 10751),
                tmdbGenreSource(type = "series", genreId = 10751)
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.43249", sourceAddonId = "aio-metadata", sourceName = "Popular Family Movies", sourceLabel = "MDBLIST", mediaType = "movie", itemCount = 434, author = "familytv133", url = "https://mdblist.com/lists/familytv133/family-kids-english-movies-rated-g-pg"),
                metadata(sourceCatalogId = "mdblist.43251", sourceAddonId = "aio-metadata", sourceName = "Popular Family Shows", sourceLabel = "MDBLIST", mediaType = "series", itemCount = 277, author = "familytv133", url = "https://mdblist.com/lists/familytv133/family-kids-english-tv-showsrated-g-pg")
            )
        ),
        entry(
            title = "Fantasy",
            group = CollectionGroupKind.GENRE,
            coverImageUrl = "https://raw.githubusercontent.com/itsrenoria/fusion-starter-kit/refs/heads/main/resources/widgets/genres/wide/dannyrutledge/fantasy-wide.png",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                tmdbGenreSource(type = "movie", genreId = 14),
                tmdbGenreSource(type = "series", genreId = 10765)
            ),
            listMetadata = emptyList()
        ),
        entry(
            title = "Adventure",
            group = CollectionGroupKind.GENRE,
            coverImageUrl = "https://raw.githubusercontent.com/itsrenoria/fusion-starter-kit/refs/heads/main/resources/widgets/genres/wide/dannyrutledge/adventure-wide.png",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                tmdbGenreSource(type = "movie", genreId = 12),
                tmdbGenreSource(type = "series", genreId = 10759)
            ),
            listMetadata = emptyList()
        ),
        entry(
            title = "Superhero",
            group = CollectionGroupKind.GENRE,
            coverImageUrl = "https://raw.githubusercontent.com/itsrenoria/fusion-starter-kit/refs/heads/main/resources/widgets/genres/wide/dannyrutledge/superheroes-wide.png",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                tmdbKeywordSource(type = "movie", keywordId = 9715),
                tmdbKeywordSource(type = "series", keywordId = 9715)
            ),
            listMetadata = emptyList()
        ),
        entry(
            title = "War & Military",
            group = CollectionGroupKind.GENRE,
            coverImageUrl = "https://raw.githubusercontent.com/itsrenoria/fusion-starter-kit/refs/heads/main/resources/widgets/genres/wide/dannyrutledge/war-stories-wide.png",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                tmdbGenreSource(type = "movie", genreId = 10752),
                tmdbGenreSource(type = "series", genreId = 10768)
            ),
            listMetadata = emptyList()
        ),
        entry(
            title = "20's Movies",
            group = CollectionGroupKind.DECADE,
            coverImageUrl = "${TEMPLATE_IMAGE_BASE}20snew.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "mdblist.91304")
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.91304", sourceAddonId = "aio-metadata", sourceName = "Popular 2020s Movies", sourceLabel = "MDBLIST", mediaType = "movie", itemCount = 179, author = "snoak", url = "https://mdblist.com/lists/snoak/top-2020s-movies")
            )
        ),
        entry(
            title = "10's Movies",
            group = CollectionGroupKind.DECADE,
            coverImageUrl = "${TEMPLATE_IMAGE_BASE}10snew.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "mdblist.91303")
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.91303", sourceAddonId = "aio-metadata", sourceName = "Popular 2010s Movies", sourceLabel = "MDBLIST", mediaType = "movie", itemCount = 372, author = "snoak", url = "https://mdblist.com/lists/snoak/top-2010s-movies")
            )
        ),
        entry(
            title = "00's Movies",
            group = CollectionGroupKind.DECADE,
            coverImageUrl = "${TEMPLATE_IMAGE_BASE}00snew.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "mdblist.91302")
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.91302", sourceAddonId = "aio-metadata", sourceName = "Popular 2000s Movies", sourceLabel = "MDBLIST", mediaType = "movie", itemCount = 448, author = "snoak", url = "https://mdblist.com/lists/snoak/top-2000s-movies")
            )
        ),
        entry(
            title = "90's Movies",
            group = CollectionGroupKind.DECADE,
            coverImageUrl = "${TEMPLATE_IMAGE_BASE}90snew.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "mdblist.91300")
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.91300", sourceAddonId = "aio-metadata", sourceName = "Popular 1990s Movies", sourceLabel = "MDBLIST", mediaType = "movie", itemCount = 314, author = "snoak", url = "https://mdblist.com/lists/snoak/top-1990s-movies")
            )
        ),
        entry(
            title = "80's Movies",
            group = CollectionGroupKind.DECADE,
            coverImageUrl = "${TEMPLATE_IMAGE_BASE}80snew.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "mdblist.91301")
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.91301", sourceAddonId = "aio-metadata", sourceName = "Popular 1980s Movies", sourceLabel = "MDBLIST", mediaType = "movie", itemCount = 220, author = "snoak", url = "https://mdblist.com/lists/snoak/top-1980s-movies")
            )
        ),
        entry(
            title = "70's Movies",
            group = CollectionGroupKind.DECADE,
            coverImageUrl = "${TEMPLATE_IMAGE_BASE}70snew.png",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "mdblist.127962")
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.127962", sourceAddonId = "aio-metadata", sourceName = "Popular 1970s Movies", sourceLabel = "MDBLIST", mediaType = "movie", itemCount = 159, author = "snoak", url = "https://mdblist.com/lists/snoak/popular-1970s-movies")
            )
        ),
        entry(
            title = "60's Movies",
            group = CollectionGroupKind.DECADE,
            coverImageUrl = "${TEMPLATE_IMAGE_BASE}60snew.png",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "mdblist.144321")
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.144321", sourceAddonId = "aio-metadata", sourceName = "Popular 1960s Movies", sourceLabel = "MDBLIST", mediaType = "movie", itemCount = 117, author = "snoak", url = "https://mdblist.com/lists/snoak/popular-1960s-movies")
            )
        ),
        entry(
            title = "Marvel",
            group = CollectionGroupKind.FRANCHISE,
            coverImageUrl = "${TEMPLATE_IMAGE_BASE}Marvel.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "com.joaogonp.marveladdon.custom.marvel-mcu", type = "Marvel", catalogId = "marvel-mcu")
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "marvel-mcu", sourceAddonId = "com.joaogonp.marveladdon.custom.marvel-mcu", sourceName = "Marvel", sourceLabel = "ADDON", mediaType = "marvel", itemCount = null, author = null, url = null)
            )
        ),
        entry(
            title = "DC Universe",
            group = CollectionGroupKind.FRANCHISE,
            coverImageUrl = "${TEMPLATE_IMAGE_BASE}DC.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "com.tapframe.dcaddon.custom.dc-chronological", type = "DC", catalogId = "dc-chronological")
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "dc-chronological", sourceAddonId = "com.tapframe.dcaddon.custom.dc-chronological", sourceName = "DC Universe", sourceLabel = "ADDON", mediaType = "dc", itemCount = null, author = null, url = null)
            )
        ),
        entry(
            title = "Star Wars",
            group = CollectionGroupKind.FRANCHISE,
            coverImageUrl = "${TEMPLATE_IMAGE_BASE}Star-Wars.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "com.starwars.addon.custom.sw-movies-series-chronological", type = "StarWars", catalogId = "sw-movies-series-chronological")
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "sw-movies-series-chronological", sourceAddonId = "com.starwars.addon.custom.sw-movies-series-chronological", sourceName = "Star Wars", sourceLabel = "ADDON", mediaType = "starwars", itemCount = null, author = null, url = null)
            )
        ),
        entry(
            title = "James Bond",
            group = CollectionGroupKind.FRANCHISE,
            coverImageUrl = "${TEMPLATE_IMAGE_BASE}007.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "mdblist.7947")
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.7947", sourceAddonId = "aio-metadata", sourceName = "James Bond Collection", sourceLabel = "MDBLIST", mediaType = "movie", itemCount = 27, author = "hdlists", url = "https://mdblist.com/lists/hdlists/james-bond-movies")
            )
        ),
        entry(
            title = "Harry Potter",
            group = CollectionGroupKind.FRANCHISE,
            coverImageUrl = "${TEMPLATE_IMAGE_BASE}Harry-Potter.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "mdblist.102972")
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.102972", sourceAddonId = "aio-metadata", sourceName = "Harry Potter Collection", sourceLabel = "MDBLIST", mediaType = "movie", itemCount = 11, author = "thebirdod", url = "https://mdblist.com/lists/thebirdod/harry-potter-collection")
            )
        ),
        entry(
            title = "Alien vs Predator",
            group = CollectionGroupKind.FRANCHISE,
            coverImageUrl = "${TEMPLATE_IMAGE_BASE}avp.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "all", catalogId = "mdblist.101434"),
                tmdbCollectionSource(8091),
                tmdbCollectionSource(399),
                tmdbCollectionSource(115762)
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.101434", sourceAddonId = "aio-metadata", sourceName = "Alien vs Predator Collection", sourceLabel = "MDBLIST", mediaType = "all", itemCount = 17, author = "exoduso", url = "https://mdblist.com/lists/exoduso/predator-franchise")
            )
        ),
        entry(
            title = "Pirates of the Caribbean",
            group = CollectionGroupKind.FRANCHISE,
            coverImageUrl = "${TEMPLATE_IMAGE_BASE}pirates.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "mdblist.82145"),
                tmdbCollectionSource(295)
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.82145", sourceAddonId = "aio-metadata", sourceName = "Pirates of the Caribbean Collection", sourceLabel = "MDBLIST", mediaType = "movie", itemCount = 5, author = "aaron713", url = "https://mdblist.com/lists/aaron713/pirates-of-the-caribbean-collection")
            )
        ),
        entry(
            title = "Terminator",
            group = CollectionGroupKind.FRANCHISE,
            coverImageUrl = "${TEMPLATE_IMAGE_BASE}Terminator.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "all", catalogId = "mdblist.125458"),
                tmdbCollectionSource(528)
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.125458", sourceAddonId = "aio-metadata", sourceName = "The Terminator Collection", sourceLabel = "MDBLIST", mediaType = "all", itemCount = 9, author = "andyhawks", url = "https://mdblist.com/lists/andyhawks/universe-the-terminator")
            )
        ),
        entry(
            title = "Mission Impossible",
            group = CollectionGroupKind.FRANCHISE,
            coverImageUrl = "${TEMPLATE_IMAGE_BASE}mission-impossible.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "mdblist.42716")
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.42716", sourceAddonId = "aio-metadata", sourceName = "Mission Impossible Collection", sourceLabel = "MDBLIST", mediaType = "movie", itemCount = 8, author = "nammel", url = "https://mdblist.com/lists/nammel/mission-impossible-saga")
            )
        ),
        entry(
            title = "Jurassic Park",
            group = CollectionGroupKind.FRANCHISE,
            coverImageUrl = "${TEMPLATE_IMAGE_BASE}jurrasic-park.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "all", catalogId = "mdblist.120197")
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.120197", sourceAddonId = "aio-metadata", sourceName = "Jurassic Park Collection", sourceLabel = "MDBLIST", mediaType = "all", itemCount = 12, author = "purple_smurf", url = "https://mdblist.com/lists/purple_smurf/jurassic-park")
            )
        ),
        entry(
            title = "The Matrix",
            group = CollectionGroupKind.FRANCHISE,
            coverImageUrl = "${TEMPLATE_IMAGE_BASE}matrix.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "mdblist.125142"),
                tmdbCollectionSource(2344)
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.125142", sourceAddonId = "aio-metadata", sourceName = "The Matrix Collection", sourceLabel = "MDBLIST", mediaType = "movie", itemCount = 5, author = "andyhawks", url = "https://mdblist.com/lists/andyhawks/universe-the-matrix")
            )
        ),
        entry(
            title = "Lord of the Rings",
            group = CollectionGroupKind.FRANCHISE,
            coverImageUrl = "${TEMPLATE_IMAGE_BASE}lotr.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = listOf(
                source(addonId = "aio-metadata", type = "movie", catalogId = "mdblist.94304")
            ),
            listMetadata = listOf(
                metadata(sourceCatalogId = "mdblist.94304", sourceAddonId = "aio-metadata", sourceName = "Lord of the Rings and Hobbit Collection", sourceLabel = "MDBLIST", mediaType = "movie", itemCount = 6, author = "spudhead15", url = "https://mdblist.com/lists/spudhead15/lord-of-the-rings-and-hobbit-collection")
            )
        ),
        entry(
            title = "X-Men",
            group = CollectionGroupKind.FRANCHISE,
            coverImageUrl = "https://i.postimg.cc/RC2Ny8Ds/X-Men.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = emptyList(),
            listMetadata = emptyList()
        ),
        entry(
            title = "Hunger Games",
            group = CollectionGroupKind.FRANCHISE,
            coverImageUrl = "https://i.postimg.cc/FzfKsZ29/Hunger-Games.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = emptyList(),
            listMetadata = emptyList()
        ),
        entry(
            title = "Avatar",
            group = CollectionGroupKind.FRANCHISE,
            coverImageUrl = "https://i.postimg.cc/nLSV4nhT/AVATAR.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = emptyList(),
            listMetadata = emptyList()
        ),
        entry(
            title = "Dune",
            group = CollectionGroupKind.FRANCHISE,
            coverImageUrl = "https://i.postimg.cc/HnrT6frm/Dune.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = emptyList(),
            listMetadata = emptyList()
        ),
        entry(
            title = "Indiana Jones",
            group = CollectionGroupKind.FRANCHISE,
            coverImageUrl = "https://i.postimg.cc/tCbrtFwS/Indiana-Jo.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = emptyList(),
            listMetadata = emptyList()
        ),
        entry(
            title = "The Godfather",
            group = CollectionGroupKind.FRANCHISE,
            coverImageUrl = "https://i.postimg.cc/X7YwbzbT/The-Godfather.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = emptyList(),
            listMetadata = emptyList()
        ),
        entry(
            title = "John Wick",
            group = CollectionGroupKind.FRANCHISE,
            coverImageUrl = "https://i.postimg.cc/W14q7rtM/JW.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = emptyList(),
            listMetadata = emptyList()
        ),
        entry(
            title = "Transformers",
            group = CollectionGroupKind.FRANCHISE,
            coverImageUrl = "https://i.postimg.cc/CLw3Lyhx/Transformers.jpg",
            tileShape = CollectionTileShape.LANDSCAPE,
            hideTitle = true,
            heroVideoUrl = null,
            sources = emptyList(),
            listMetadata = emptyList()
        )
    )

    private val entriesById: Map<String, CollectionTemplateEntry> = entries.associateBy { it.id }
    private val entryIds: Set<String> = entriesById.keys
    private val validRailGroups: Set<CollectionGroupKind> = railOrder.toSet()

    fun entryForCatalog(catalogId: String?): CollectionTemplateEntry? = catalogId?.let(entriesById::get)

    fun listMetadataFor(catalogId: String?): List<CollectionSourceListMetadata> =
        entryForCatalog(catalogId)?.listMetadata.orEmpty()

    fun railCatalogId(group: CollectionGroupKind): String = "collection_rail_${group.name.lowercase(Locale.US)}"

    fun railTitle(group: CollectionGroupKind): String = when (group) {
        CollectionGroupKind.FEATURED -> "Featured"
        CollectionGroupKind.SERVICE -> "Services"
        CollectionGroupKind.GENRE -> "Genres"
        CollectionGroupKind.DECADE -> "Decades"
        CollectionGroupKind.FRANCHISE -> "Franchises"
        CollectionGroupKind.NETWORK -> "Networks"
    }

    fun hasEntriesFor(group: CollectionGroupKind): Boolean =
        entries.any { it.group == group }

    fun isValidCollectionConfig(config: CatalogConfig): Boolean = when (config.kind) {
        CatalogKind.COLLECTION -> entryIds.contains(config.id)
        CatalogKind.COLLECTION_RAIL -> {
            val group = config.collectionGroup ?: return false
            group in validRailGroups && hasEntriesFor(group)
        }
        else -> true
    }

    fun requiredAddonUrlsFor(entry: CollectionTemplateEntry): List<String> {
        val addonIds = entry.sources.mapNotNull { it.addonId }.toSet()
        return buildList {
            if (addonIds.contains("pw.ers.netflix-catalog")) add(STREAMING_ADDON_URL)
            if (addonIds.contains("com.joaogonp.marveladdon.custom.marvel-mcu")) add(MARVEL_ADDON_URL)
            if (addonIds.contains("com.btmv.addon.dcuniverse.custom.dc-chronological")) add(DC_ADDON_URL)
            if (addonIds.contains("com.starwars.addon.custom.sw-movies-series-chronological")) add(STAR_WARS_ADDON_URL)
        }
    }

    fun autoInstalledAddonUrls(): List<String> = listOf(
        STREAMING_ADDON_URL,
        MARVEL_ADDON_URL,
        DC_ADDON_URL,
        STAR_WARS_ADDON_URL
    )

    fun descriptionFor(entry: CollectionTemplateEntry): String = when (entry.group) {
        CollectionGroupKind.FEATURED -> "Curated spotlight picks sourced from the latest public lists."
        CollectionGroupKind.SERVICE -> "Browse movies and series grouped by streaming service."
        CollectionGroupKind.GENRE -> "A themed mix of movies and series built around a single genre mood."
        CollectionGroupKind.DECADE -> "Explore movies collected around a specific decade."
        CollectionGroupKind.FRANCHISE -> "A franchise timeline or universe collection gathered from dedicated lists."
        CollectionGroupKind.NETWORK -> "Shows and movies grouped around a specific network or brand."
    }

    private fun entry(
        title: String,
        group: CollectionGroupKind,
        coverImageUrl: String,
        tileShape: CollectionTileShape,
        hideTitle: Boolean,
        heroVideoUrl: String?,
        sources: List<CollectionSourceConfig>,
        listMetadata: List<CollectionSourceListMetadata>
    ): CollectionTemplateEntry {
        return CollectionTemplateEntry(
            id = "collection_${group.name.lowercase(Locale.US)}_${slugify(title)}",
            title = title,
            group = group,
            coverImageUrl = coverImageUrl,
            tileShape = tileShape,
            hideTitle = hideTitle,
            heroVideoUrl = heroVideoUrl,
            sources = sources,
            listMetadata = listMetadata
        )
    }

    private fun metadata(
        sourceCatalogId: String,
        sourceAddonId: String?,
        sourceName: String,
        sourceLabel: String,
        mediaType: String?,
        itemCount: Int?,
        author: String?,
        url: String?
    ) = CollectionSourceListMetadata(
        sourceCatalogId = sourceCatalogId,
        sourceAddonId = sourceAddonId,
        sourceName = sourceName,
        sourceLabel = sourceLabel,
        mediaType = mediaType,
        itemCount = itemCount,
        author = author,
        url = url
    )

    private fun source(addonId: String, type: String, catalogId: String) = CollectionSourceConfig(
        kind = CollectionSourceKind.ADDON_CATALOG,
        addonId = addonId,
        addonCatalogType = type,
        addonCatalogId = catalogId
    )

    private fun mdblistSource(slug: String) = CollectionSourceConfig(
        kind = CollectionSourceKind.MDBLIST_PUBLIC,
        mdblistSlug = slug
    )

    private fun tmdbCollectionSource(collectionId: Int) = CollectionSourceConfig(
        kind = CollectionSourceKind.TMDB_COLLECTION,
        tmdbCollectionId = collectionId
    )

    private fun tmdbGenreSource(type: String, genreId: Int) = CollectionSourceConfig(
        kind = CollectionSourceKind.TMDB_GENRE,
        mediaType = type,
        tmdbGenreId = genreId,
        sortBy = "popularity.desc"
    )

    private fun tmdbKeywordSource(type: String, keywordId: Int) = CollectionSourceConfig(
        kind = CollectionSourceKind.TMDB_KEYWORD,
        mediaType = type,
        tmdbKeywordId = keywordId,
        sortBy = "popularity.desc"
    )

    private fun watchProviderSource(type: String, providerId: Int) = CollectionSourceConfig(
        kind = CollectionSourceKind.TMDB_WATCH_PROVIDER,
        mediaType = type,
        tmdbWatchProviderId = providerId,
        watchRegion = "US",
        sortBy = "popularity.desc"
    )

    private fun slugify(value: String): String {
        return value.lowercase(Locale.US)
            .replace("+", "plus")
            .replace("&", "and")
            .replace("'", "")
            .replace(CollectionManifestRegexes.SLUGIFY_NON_ALPHA_NUM_REGEX, "_")
            .trim('_')
    }
}

private object CollectionManifestRegexes {
    val SLUGIFY_NON_ALPHA_NUM_REGEX = Regex("[^a-z0-9]+")
}
