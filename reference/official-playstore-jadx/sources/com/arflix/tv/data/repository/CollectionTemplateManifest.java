package com.arflix.tv.data.repository;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.arflix.tv.data.model.CatalogConfig;
import com.arflix.tv.data.model.CatalogKind;
import com.arflix.tv.data.model.CollectionGroupKind;
import com.arflix.tv.data.model.CollectionSourceConfig;
import com.arflix.tv.data.model.CollectionSourceKind;
import com.arflix.tv.data.model.CollectionTileShape;
import com.arflix.tv.ui.theme.AnimationConstants;
import com.google.android.gms.cast.MediaError;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0013\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u00142\b\u0010\u001c\u001a\u0004\u0018\u00010\u0005J\u0016\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u000f2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0005J\u000e\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u0010J\u000e\u0010!\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u0010J\u000e\u0010\"\u001a\u00020#2\u0006\u0010 \u001a\u00020\u0010J\u000e\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020&J\u0014\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f2\u0006\u0010(\u001a\u00020\u0014J\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00050\u000fJ\u000e\u0010*\u001a\u00020\u00052\u0006\u0010(\u001a\u00020\u0014JV\u0010(\u001a\u00020\u00142\u0006\u0010+\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u00102\u0006\u0010,\u001a\u00020\u00052\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020#2\b\u00100\u001a\u0004\u0018\u00010\u00052\f\u00101\u001a\b\u0012\u0004\u0012\u0002020\u000f2\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u001e0\u000fH\u0002JW\u00104\u001a\u00020\u001e2\u0006\u00105\u001a\u00020\u00052\b\u00106\u001a\u0004\u0018\u00010\u00052\u0006\u00107\u001a\u00020\u00052\u0006\u00108\u001a\u00020\u00052\b\u00109\u001a\u0004\u0018\u00010\u00052\b\u0010:\u001a\u0004\u0018\u00010;2\b\u0010<\u001a\u0004\u0018\u00010\u00052\b\u0010=\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0002\u0010>J \u0010?\u001a\u0002022\u0006\u0010@\u001a\u00020\u00052\u0006\u0010A\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u0005H\u0002J\u0010\u0010B\u001a\u0002022\u0006\u0010C\u001a\u00020\u0005H\u0002J\u0010\u0010D\u001a\u0002022\u0006\u0010E\u001a\u00020;H\u0002J\u0018\u0010F\u001a\u0002022\u0006\u0010A\u001a\u00020\u00052\u0006\u0010G\u001a\u00020;H\u0002J\u0018\u0010H\u001a\u0002022\u0006\u0010A\u001a\u00020\u00052\u0006\u0010I\u001a\u00020;H\u0002J\u0018\u0010J\u001a\u0002022\u0006\u0010A\u001a\u00020\u00052\u0006\u0010K\u001a\u00020;H\u0002J\u0010\u0010L\u001a\u00020\u00052\u0006\u0010M\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u001a\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00140\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00100\u0019X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006N"}, d2 = {"Lcom/arflix/tv/data/repository/CollectionTemplateManifest;", "", "<init>", "()V", "VIDEO_BASE", "", "STREAMING_SERVICE_IMAGE_BASE", "GENRE_IMAGE_BASE", "STREAMING_ADDON_URL", "MARVEL_ADDON_URL", "DC_ADDON_URL", "STAR_WARS_ADDON_URL", "TEMPLATE_IMAGE_BASE", "UPLOADED_COVER_BASE", "railOrder", "", "Lcom/arflix/tv/data/model/CollectionGroupKind;", "getRailOrder", "()Ljava/util/List;", "entries", "Lcom/arflix/tv/data/repository/CollectionTemplateEntry;", "getEntries", "entriesById", "", "entryIds", "", "validRailGroups", "entryForCatalog", "catalogId", "listMetadataFor", "Lcom/arflix/tv/data/repository/CollectionSourceListMetadata;", "railCatalogId", "group", "railTitle", "hasEntriesFor", "", "isValidCollectionConfig", "config", "Lcom/arflix/tv/data/model/CatalogConfig;", "requiredAddonUrlsFor", "entry", "autoInstalledAddonUrls", "descriptionFor", LinkHeader.Parameters.Title, "coverImageUrl", "tileShape", "Lcom/arflix/tv/data/model/CollectionTileShape;", "hideTitle", "heroVideoUrl", "sources", "Lcom/arflix/tv/data/model/CollectionSourceConfig;", "listMetadata", TtmlNode.TAG_METADATA, "sourceCatalogId", "sourceAddonId", "sourceName", "sourceLabel", "mediaType", "itemCount", "", "author", "url", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)Lcom/arflix/tv/data/repository/CollectionSourceListMetadata;", "source", "addonId", LinkHeader.Parameters.Type, "mdblistSource", "slug", "tmdbCollectionSource", "collectionId", "tmdbGenreSource", "genreId", "tmdbKeywordSource", "keywordId", "watchProviderSource", "providerId", "slugify", "value", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CollectionTemplateManifest {
    public static final int $stable = 0;
    private static final String DC_ADDON_URL = "https://addon-dc-cq85.onrender.com/catalog/dc-chronological/manifest.json";
    private static final String GENRE_IMAGE_BASE = "https://raw.githubusercontent.com/chrishudson918/images/main/Landscape%20Genres/";
    public static final CollectionTemplateManifest INSTANCE;
    private static final String MARVEL_ADDON_URL = "https://addon-marvel.onrender.com/catalog/marvel-mcu/manifest.json";
    private static final String STAR_WARS_ADDON_URL = "https://addon-star-wars-u9e3.onrender.com/catalog/sw-movies-series-chronological/manifest.json";
    private static final String STREAMING_ADDON_URL = "https://7a82163c306e-stremio-netflix-catalog-addon.baby-beamup.club/bmZ4LGRucCxhbXAsYXRwLGhibSxwbXAscGNwLGhsdSxzdHo6OlVTOjE3NzYzMjQxMDg4OTM6MDowOkdU/manifest.json";
    private static final String STREAMING_SERVICE_IMAGE_BASE = "https://raw.githubusercontent.com/chrishudson918/images/46fd4f8c335a7c581a7dcdb7dfac268c68ef84fc/Landscape%20Streaming%20Services/";
    private static final String TEMPLATE_IMAGE_BASE;
    private static final String UPLOADED_COVER_BASE;
    private static final String VIDEO_BASE = "https://raw.githubusercontent.com/mrtxiv/networks-video-collection/3486fc9a3d0efe59d1929e75f66021dc4e15bcb7/networks%20videos/";
    private static final List<CollectionTemplateEntry> entries;
    private static final Map<String, CollectionTemplateEntry> entriesById;
    private static final Set<String> entryIds;
    private static final List<CollectionGroupKind> railOrder;
    private static final Set<CollectionGroupKind> validRailGroups;

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[CollectionGroupKind.values().length];
            try {
                iArr[CollectionGroupKind.FEATURED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CollectionGroupKind.SERVICE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CollectionGroupKind.GENRE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[CollectionGroupKind.DECADE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[CollectionGroupKind.FRANCHISE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[CollectionGroupKind.NETWORK.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[CatalogKind.values().length];
            try {
                iArr2[CatalogKind.COLLECTION.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[CatalogKind.COLLECTION_RAIL.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    static {
        CollectionTemplateManifest collectionTemplateManifest = new CollectionTemplateManifest();
        INSTANCE = collectionTemplateManifest;
        TEMPLATE_IMAGE_BASE = "https://raw.githubusercontent.com/elucidationvortex-source/nuviotemplate/refs/heads/main/images/";
        UPLOADED_COVER_BASE = "https://nuvioapp.space/uploads/covers/";
        CollectionGroupKind collectionGroupKind = CollectionGroupKind.SERVICE;
        CollectionGroupKind collectionGroupKind2 = CollectionGroupKind.GENRE;
        CollectionGroupKind collectionGroupKind3 = CollectionGroupKind.FRANCHISE;
        railOrder = t7.a.E(collectionGroupKind, collectionGroupKind2, collectionGroupKind3);
        CollectionGroupKind collectionGroupKind4 = CollectionGroupKind.FEATURED;
        CollectionTileShape collectionTileShape = CollectionTileShape.LANDSCAPE;
        CollectionTemplateEntry collectionTemplateEntryEntry = collectionTemplateManifest.entry("Latest Movies", collectionGroupKind4, "https://raw.githubusercontent.com/elucidationvortex-source/nuviotemplate/refs/heads/main/images/Latest-Movies.jpg", collectionTileShape, true, null, t7.a.D(collectionTemplateManifest.source("aio-metadata", "movie", "mdblist.86934")), t7.a.D(collectionTemplateManifest.metadata("mdblist.86934", "aio-metadata", "Latest Digital Release", "MDBLIST", "movie", 498, "snoak", "https://mdblist.com/lists/snoak/latest-movies-digital-release")));
        CollectionTemplateEntry collectionTemplateEntryEntry2 = collectionTemplateManifest.entry("Latest Shows", collectionGroupKind4, "https://raw.githubusercontent.com/elucidationvortex-source/nuviotemplate/refs/heads/main/images/Latest-Shows.jpg", collectionTileShape, true, null, t7.a.D(collectionTemplateManifest.source("aio-metadata", "series", "mdblist.86710")), t7.a.D(collectionTemplateManifest.metadata("mdblist.86710", "aio-metadata", "Latest Airing", "MDBLIST", "series", 200, "snoak", "https://mdblist.com/lists/snoak/latest-tv-shows")));
        List<CollectionSourceConfig> listD = t7.a.D(collectionTemplateManifest.source("aio-metadata", "movie", "mdblist.87667"));
        Integer numValueOf = Integer.valueOf(AnimationConstants.DURATION_IMAGE_CROSSFADE);
        CollectionTemplateEntry collectionTemplateEntryEntry3 = collectionTemplateManifest.entry("Trending Movies", collectionGroupKind4, "https://raw.githubusercontent.com/elucidationvortex-source/nuviotemplate/refs/heads/main/images/Trending-Movies.jpg", collectionTileShape, true, null, listD, t7.a.D(collectionTemplateManifest.metadata("mdblist.87667", "aio-metadata", "Trending Movies", "MDBLIST", "movie", numValueOf, "snoak", "https://mdblist.com/lists/snoak/trending-movies")));
        CollectionTemplateEntry collectionTemplateEntryEntry4 = collectionTemplateManifest.entry("Trending Shows", collectionGroupKind4, "https://raw.githubusercontent.com/elucidationvortex-source/nuviotemplate/refs/heads/main/images/Trending-Shows.jpg", collectionTileShape, true, null, t7.a.D(collectionTemplateManifest.source("aio-metadata", "series", "mdblist.88434")), t7.a.D(collectionTemplateManifest.metadata("mdblist.88434", "aio-metadata", "Trending Shows", "MDBLIST", "series", numValueOf, "snoak", "https://mdblist.com/lists/snoak/trakt-s-trending-shows")));
        CollectionTemplateEntry collectionTemplateEntryEntry5 = collectionTemplateManifest.entry("Netflix", collectionGroupKind, "https://raw.githubusercontent.com/chrishudson918/images/46fd4f8c335a7c581a7dcdb7dfac268c68ef84fc/Landscape%20Streaming%20Services/netflix.jpegli.jpg", collectionTileShape, true, "https://raw.githubusercontent.com/mrtxiv/networks-video-collection/3486fc9a3d0efe59d1929e75f66021dc4e15bcb7/networks%20videos/netflix.mp4", t7.a.E(collectionTemplateManifest.source("aio-metadata", "movie", "streaming.nfx"), collectionTemplateManifest.source("aio-metadata", "series", "streaming.nfx"), collectionTemplateManifest.watchProviderSource("movie", 8), collectionTemplateManifest.watchProviderSource("series", 8)), t7.a.E(collectionTemplateManifest.metadata("streaming.nfx", "aio-metadata", "Netflix", "AIO", "movie", null, null, null), collectionTemplateManifest.metadata("streaming.nfx", "aio-metadata", "Netflix", "AIO", "series", null, null, null)));
        CollectionTemplateEntry collectionTemplateEntryEntry6 = collectionTemplateManifest.entry("Disney+", collectionGroupKind, "https://raw.githubusercontent.com/chrishudson918/images/46fd4f8c335a7c581a7dcdb7dfac268c68ef84fc/Landscape%20Streaming%20Services/disney.jpegli.jpg", collectionTileShape, true, "https://raw.githubusercontent.com/mrtxiv/networks-video-collection/3486fc9a3d0efe59d1929e75f66021dc4e15bcb7/networks%20videos/disneyplus.mp4", t7.a.E(collectionTemplateManifest.source("aio-metadata", "movie", "streaming.dnp"), collectionTemplateManifest.source("aio-metadata", "series", "streaming.dnp"), collectionTemplateManifest.watchProviderSource("movie", 337), collectionTemplateManifest.watchProviderSource("series", 337)), t7.a.E(collectionTemplateManifest.metadata("streaming.dnp", "aio-metadata", "Disney+", "AIO", "movie", null, null, null), collectionTemplateManifest.metadata("streaming.dnp", "aio-metadata", "Disney+", "AIO", "series", null, null, null)));
        CollectionTemplateEntry collectionTemplateEntryEntry7 = collectionTemplateManifest.entry("Apple TV+", collectionGroupKind, "https://raw.githubusercontent.com/chrishudson918/images/46fd4f8c335a7c581a7dcdb7dfac268c68ef84fc/Landscape%20Streaming%20Services/apple.jpegli.jpg", collectionTileShape, true, "https://raw.githubusercontent.com/mrtxiv/networks-video-collection/3486fc9a3d0efe59d1929e75f66021dc4e15bcb7/networks%20videos/appletv.mp4", t7.a.E(collectionTemplateManifest.source("aio-metadata", "movie", "streaming.atp"), collectionTemplateManifest.source("aio-metadata", "series", "streaming.atp"), collectionTemplateManifest.watchProviderSource("movie", 350), collectionTemplateManifest.watchProviderSource("series", 350)), t7.a.E(collectionTemplateManifest.metadata("streaming.atp", "aio-metadata", "Apple TV+", "AIO", "movie", null, null, null), collectionTemplateManifest.metadata("streaming.atp", "aio-metadata", "Apple TV+", "AIO", "series", null, null, null)));
        CollectionTemplateEntry collectionTemplateEntryEntry8 = collectionTemplateManifest.entry("Prime Video", collectionGroupKind, "https://raw.githubusercontent.com/chrishudson918/images/46fd4f8c335a7c581a7dcdb7dfac268c68ef84fc/Landscape%20Streaming%20Services/prime.jpegli.jpg", collectionTileShape, true, "https://raw.githubusercontent.com/mrtxiv/networks-video-collection/3486fc9a3d0efe59d1929e75f66021dc4e15bcb7/networks%20videos/amazonprime.mp4", t7.a.E(collectionTemplateManifest.source("aio-metadata", "movie", "streaming.amp"), collectionTemplateManifest.source("aio-metadata", "series", "streaming.amp"), collectionTemplateManifest.watchProviderSource("movie", 9), collectionTemplateManifest.watchProviderSource("series", 9)), t7.a.E(collectionTemplateManifest.metadata("streaming.amp", "aio-metadata", "Prime Video", "AIO", "movie", null, null, null), collectionTemplateManifest.metadata("streaming.amp", "aio-metadata", "Prime Video", "AIO", "series", null, null, null)));
        CollectionTemplateEntry collectionTemplateEntryEntry9 = collectionTemplateManifest.entry("HBO Max", collectionGroupKind, "https://raw.githubusercontent.com/chrishudson918/images/46fd4f8c335a7c581a7dcdb7dfac268c68ef84fc/Landscape%20Streaming%20Services/hbo.jpegli.jpg", collectionTileShape, true, "https://raw.githubusercontent.com/mrtxiv/networks-video-collection/3486fc9a3d0efe59d1929e75f66021dc4e15bcb7/networks%20videos/hbomax.mp4", t7.a.E(collectionTemplateManifest.source("aio-metadata", "movie", "streaming.hbm"), collectionTemplateManifest.source("aio-metadata", "series", "streaming.hbm"), collectionTemplateManifest.watchProviderSource("movie", 1899), collectionTemplateManifest.watchProviderSource("series", 1899)), t7.a.E(collectionTemplateManifest.metadata("streaming.hbm", "aio-metadata", "HBO Max", "AIO", "movie", null, null, null), collectionTemplateManifest.metadata("streaming.hbm", "aio-metadata", "HBO Max", "AIO", "series", null, null, null)));
        CollectionTemplateEntry collectionTemplateEntryEntry10 = collectionTemplateManifest.entry("Hulu", collectionGroupKind, "https://raw.githubusercontent.com/chrishudson918/images/46fd4f8c335a7c581a7dcdb7dfac268c68ef84fc/Landscape%20Streaming%20Services/hulu.jpegli.jpg", collectionTileShape, true, "https://raw.githubusercontent.com/mrtxiv/networks-video-collection/3486fc9a3d0efe59d1929e75f66021dc4e15bcb7/networks%20videos/hulu.mp4", t7.a.E(collectionTemplateManifest.source("aio-metadata", "movie", "streaming.hlu"), collectionTemplateManifest.source("aio-metadata", "series", "streaming.hlu"), collectionTemplateManifest.watchProviderSource("movie", 15), collectionTemplateManifest.watchProviderSource("series", 15)), t7.a.E(collectionTemplateManifest.metadata("streaming.hlu", "aio-metadata", "Hulu", "AIO", "movie", null, null, null), collectionTemplateManifest.metadata("streaming.hlu", "aio-metadata", "Hulu", "AIO", "series", null, null, null)));
        CollectionTemplateEntry collectionTemplateEntryEntry11 = collectionTemplateManifest.entry("Paramount+", collectionGroupKind, "https://raw.githubusercontent.com/chrishudson918/images/46fd4f8c335a7c581a7dcdb7dfac268c68ef84fc/Landscape%20Streaming%20Services/paramount.jpegli.jpg", collectionTileShape, true, "https://raw.githubusercontent.com/mrtxiv/networks-video-collection/3486fc9a3d0efe59d1929e75f66021dc4e15bcb7/networks%20videos/paramount.mp4", t7.a.E(collectionTemplateManifest.source("aio-metadata", "movie", "streaming.pmp"), collectionTemplateManifest.source("aio-metadata", "series", "streaming.pmp"), collectionTemplateManifest.watchProviderSource("movie", 2303), collectionTemplateManifest.watchProviderSource("series", 2303), collectionTemplateManifest.watchProviderSource("movie", 2616), collectionTemplateManifest.watchProviderSource("series", 2616)), t7.a.E(collectionTemplateManifest.metadata("mdblist.86762", "aio-metadata", "Paramount+ Movies", "MDBLIST", "movie", null, null, null), collectionTemplateManifest.metadata("mdblist.86761", "aio-metadata", "Paramount+ Series", "MDBLIST", "series", null, null, null)));
        CollectionTemplateEntry collectionTemplateEntryEntry12 = collectionTemplateManifest.entry("Peacock", collectionGroupKind, "https://raw.githubusercontent.com/chrishudson918/images/46fd4f8c335a7c581a7dcdb7dfac268c68ef84fc/Landscape%20Streaming%20Services/peacock.jpegli.jpg", collectionTileShape, true, null, t7.a.E(collectionTemplateManifest.source("aio-metadata", "movie", "streaming.pcp"), collectionTemplateManifest.source("aio-metadata", "series", "streaming.pcp"), collectionTemplateManifest.watchProviderSource("movie", 386), collectionTemplateManifest.watchProviderSource("series", 386), collectionTemplateManifest.watchProviderSource("movie", 387), collectionTemplateManifest.watchProviderSource("series", 387)), t7.a.E(collectionTemplateManifest.metadata("streaming.pcp", "aio-metadata", "Peacock", "AIO", "movie", null, null, null), collectionTemplateManifest.metadata("streaming.pcp", "aio-metadata", "Peacock", "AIO", "series", null, null, null)));
        CollectionTemplateEntry collectionTemplateEntryEntry13 = collectionTemplateManifest.entry("Starz", collectionGroupKind, "https://raw.githubusercontent.com/elucidationvortex-source/nuviotemplate/refs/heads/main/images/Starz.jpg", collectionTileShape, true, null, t7.a.E(collectionTemplateManifest.source("aio-metadata", "movie", "streaming.sta"), collectionTemplateManifest.source("aio-metadata", "series", "streaming.sta"), collectionTemplateManifest.watchProviderSource("movie", 43), collectionTemplateManifest.watchProviderSource("series", 43)), t7.a.E(collectionTemplateManifest.metadata("streaming.sta", "aio-metadata", "Starz", "AIO", "movie", null, null, null), collectionTemplateManifest.metadata("streaming.sta", "aio-metadata", "Starz", "AIO", "series", null, null, null)));
        CollectionTemplateEntry collectionTemplateEntryEntry14 = collectionTemplateManifest.entry("Shudder", collectionGroupKind, "https://raw.githubusercontent.com/chrishudson918/images/46fd4f8c335a7c581a7dcdb7dfac268c68ef84fc/Landscape%20Streaming%20Services/Shudder.jpegli.jpg", collectionTileShape, true, null, t7.a.E(collectionTemplateManifest.source("aio-metadata", "movie", "mdblist.8862"), collectionTemplateManifest.source("aio-metadata", "series", "mdblist.8861"), collectionTemplateManifest.watchProviderSource("movie", 99), collectionTemplateManifest.watchProviderSource("series", 99)), t7.a.E(collectionTemplateManifest.metadata("mdblist.8862", "aio-metadata", "Shudder Movies", "MDBLIST", "movie", 814, "sig1878", "https://mdblist.com/lists/sig1878/movies-shudder"), collectionTemplateManifest.metadata("mdblist.8861", "aio-metadata", "Shudder Series", "MDBLIST", "series", 64, "sig1878", "https://mdblist.com/lists/sig1878/tv-shudder")));
        CollectionTemplateEntry collectionTemplateEntryEntry15 = collectionTemplateManifest.entry("MGM+", collectionGroupKind, "https://nuvioapp.space/uploads/covers/886dd8ac-2c7f-4ad6-9fe9-dab115ca7c00.jpg", collectionTileShape, true, null, t7.a.E(collectionTemplateManifest.source("aio-metadata", "movie", "mdblist.48305"), collectionTemplateManifest.source("aio-metadata", "series", "mdblist.48306"), collectionTemplateManifest.watchProviderSource("movie", 34), collectionTemplateManifest.watchProviderSource("series", 34)), t7.a.E(collectionTemplateManifest.metadata("mdblist.48305", "aio-metadata", "MGM+ Movies", "MDBLIST", "movie", null, null, null), collectionTemplateManifest.metadata("mdblist.48306", "aio-metadata", "MGM+ Series", "MDBLIST", "series", null, null, null)));
        CollectionTemplateEntry collectionTemplateEntryEntry16 = collectionTemplateManifest.entry("Discovery+", collectionGroupKind, "https://raw.githubusercontent.com/chrishudson918/images/46fd4f8c335a7c581a7dcdb7dfac268c68ef84fc/Landscape%20Streaming%20Services/discovery.jpegli.jpg", collectionTileShape, true, null, t7.a.E(collectionTemplateManifest.source("aio-metadata", "movie", "streaming.dpe"), collectionTemplateManifest.source("aio-metadata", "series", "streaming.dpe"), collectionTemplateManifest.watchProviderSource("movie", 520), collectionTemplateManifest.watchProviderSource("series", 520)), t7.a.E(collectionTemplateManifest.metadata("streaming.dpe", "aio-metadata", "Discovery+", "AIO", "movie", null, null, null), collectionTemplateManifest.metadata("streaming.dpe", "aio-metadata", "Discovery+", "AIO", "series", null, null, null)));
        CollectionTemplateEntry collectionTemplateEntryEntry17 = collectionTemplateManifest.entry("Crunchyroll", collectionGroupKind, "https://mir-s3-cdn-cf.behance.net/project_modules/fs_webp/380e75223389683.67f7c1dc0669a.png", collectionTileShape, true, "https://raw.githubusercontent.com/mrtxiv/networks-video-collection/3486fc9a3d0efe59d1929e75f66021dc4e15bcb7/networks%20videos/crunchyroll.mp4", t7.a.E(collectionTemplateManifest.source("aio-metadata", "movie", "streaming.cru_movie"), collectionTemplateManifest.source("aio-metadata", "series", "streaming.cru_series"), collectionTemplateManifest.watchProviderSource("movie", 283), collectionTemplateManifest.watchProviderSource("series", 283)), t7.a.E(collectionTemplateManifest.metadata("streaming.cru_movie", "aio-metadata", "Crunchyroll Movies", "AIO", "movie", null, null, null), collectionTemplateManifest.metadata("streaming.cru_series", "aio-metadata", "Crunchyroll Series", "AIO", "series", null, null, null)));
        CollectionTemplateEntry collectionTemplateEntryEntry18 = collectionTemplateManifest.entry("Adult Swim", CollectionGroupKind.NETWORK, "https://raw.githubusercontent.com/elucidationvortex-source/nuviotemplate/refs/heads/main/images/adult-swim.jpg", collectionTileShape, true, null, t7.a.D(collectionTemplateManifest.source("aio-metadata", "series", "mdblist.59779")), t7.a.D(collectionTemplateManifest.metadata("mdblist.59779", "aio-metadata", "Adult Swim", "MDBLIST", "series", 156, "ppboy", "https://mdblist.com/lists/ppboy/adult-swim")));
        CollectionTemplateEntry collectionTemplateEntryEntry19 = collectionTemplateManifest.entry("Action", collectionGroupKind2, "https://raw.githubusercontent.com/chrishudson918/images/main/Landscape%20Genres/ACTION.jpegli.jpg", collectionTileShape, true, null, t7.a.E(collectionTemplateManifest.source("aio-metadata", "movie", "mdblist.91211"), collectionTemplateManifest.source("aio-metadata", "series", "mdblist.91213"), collectionTemplateManifest.tmdbGenreSource("movie", 28), collectionTemplateManifest.tmdbGenreSource("series", 10759)), t7.a.E(collectionTemplateManifest.metadata("mdblist.91211", "aio-metadata", "Popular Action Movies", "MDBLIST", "movie", 500, "snoak", "https://mdblist.com/lists/snoak/action-movies"), collectionTemplateManifest.metadata("mdblist.91213", "aio-metadata", "Popular Action Shows", "MDBLIST", "series", 500, "snoak", "https://mdblist.com/lists/snoak/action-shows")));
        CollectionTemplateEntry collectionTemplateEntryEntry20 = collectionTemplateManifest.entry("Comedy", collectionGroupKind2, "https://raw.githubusercontent.com/chrishudson918/images/main/Landscape%20Genres/COMEDY.jpegli.jpg", collectionTileShape, true, null, t7.a.E(collectionTemplateManifest.source("aio-metadata", "movie", "mdblist.91223"), collectionTemplateManifest.source("aio-metadata", "series", "mdblist.91224"), collectionTemplateManifest.tmdbGenreSource("movie", 35), collectionTemplateManifest.tmdbGenreSource("series", 35)), t7.a.E(collectionTemplateManifest.metadata("mdblist.91223", "aio-metadata", "Popular Comedy Movies", "MDBLIST", "movie", 500, "snoak", "https://mdblist.com/lists/snoak/comedy-movies"), collectionTemplateManifest.metadata("mdblist.91224", "aio-metadata", "Popular Comedy Shows", "MDBLIST", "series", 500, "snoak", "https://mdblist.com/lists/snoak/comedy-shows")));
        CollectionTemplateEntry collectionTemplateEntryEntry21 = collectionTemplateManifest.entry("Sci-Fi", collectionGroupKind2, "https://raw.githubusercontent.com/chrishudson918/images/main/Landscape%20Genres/SCI%20FI.jpegli.jpg", collectionTileShape, true, null, t7.a.E(collectionTemplateManifest.source("aio-metadata", "movie", "mdblist.91220"), collectionTemplateManifest.source("aio-metadata", "series", "mdblist.91221"), collectionTemplateManifest.tmdbGenreSource("movie", 878), collectionTemplateManifest.tmdbGenreSource("series", 10765)), t7.a.E(collectionTemplateManifest.metadata("mdblist.91220", "aio-metadata", "Popular Sci-Fi Movies", "MDBLIST", "movie", 500, "snoak", "https://mdblist.com/lists/snoak/science-fiction-movies"), collectionTemplateManifest.metadata("mdblist.91221", "aio-metadata", "Popular Sci-Fi Shows", "MDBLIST", "series", 500, "snoak", "https://mdblist.com/lists/snoak/science-fiction-shows")));
        CollectionTemplateEntry collectionTemplateEntryEntry22 = collectionTemplateManifest.entry("Thriller", collectionGroupKind2, "https://raw.githubusercontent.com/chrishudson918/images/main/Landscape%20Genres/THRILLER.jpegli.jpg", collectionTileShape, true, null, t7.a.E(collectionTemplateManifest.source("aio-metadata", "movie", "mdblist.91893"), collectionTemplateManifest.source("aio-metadata", "series", "mdblist.91894"), collectionTemplateManifest.tmdbGenreSource("movie", 53), collectionTemplateManifest.tmdbKeywordSource("series", 316362)), t7.a.E(collectionTemplateManifest.metadata("mdblist.91893", "aio-metadata", "Popular Thriller Movies", "MDBLIST", "movie", 500, "snoak", "https://mdblist.com/lists/snoak/thriller-movies"), collectionTemplateManifest.metadata("mdblist.91894", "aio-metadata", "Popular Thriller Shows", "MDBLIST", "series", 500, "snoak", "https://mdblist.com/lists/snoak/thriller-shows")));
        CollectionTemplateEntry collectionTemplateEntryEntry23 = collectionTemplateManifest.entry("Drama", collectionGroupKind2, "https://raw.githubusercontent.com/chrishudson918/images/main/Landscape%20Genres/DRAMA.jpegli.jpg", collectionTileShape, true, null, t7.a.E(collectionTemplateManifest.source("aio-metadata", "movie", "mdblist.91296"), collectionTemplateManifest.source("aio-metadata", "series", "mdblist.91297"), collectionTemplateManifest.tmdbGenreSource("movie", 18), collectionTemplateManifest.tmdbGenreSource("series", 18)), t7.a.E(collectionTemplateManifest.metadata("mdblist.91296", "aio-metadata", "Popular Drama Movies", "MDBLIST", "movie", 500, "snoak", "https://mdblist.com/lists/snoak/drama-movies"), collectionTemplateManifest.metadata("mdblist.91297", "aio-metadata", "Popular Drama Shows", "MDBLIST", "series", 500, "snoak", "https://mdblist.com/lists/snoak/drama-shows")));
        CollectionTemplateEntry collectionTemplateEntryEntry24 = collectionTemplateManifest.entry("Horror", collectionGroupKind2, "https://raw.githubusercontent.com/chrishudson918/images/main/Landscape%20Genres/HORROR.jpegli.jpg", collectionTileShape, true, null, t7.a.E(collectionTemplateManifest.source("aio-metadata", "movie", "mdblist.91215"), collectionTemplateManifest.source("aio-metadata", "series", "mdblist.91217"), collectionTemplateManifest.source("aio-metadata", "movie", "mdblist.102554"), collectionTemplateManifest.tmdbGenreSource("movie", 27), collectionTemplateManifest.tmdbKeywordSource("series", 315058)), t7.a.E(collectionTemplateManifest.metadata("mdblist.91215", "aio-metadata", "Popular Horror Movies", "MDBLIST", "movie", 500, "snoak", "https://mdblist.com/lists/snoak/horror-movies"), collectionTemplateManifest.metadata("mdblist.91217", "aio-metadata", "Popular Horror Shows", "MDBLIST", "series", 451, "snoak", "https://mdblist.com/lists/snoak/horror-shows"), collectionTemplateManifest.metadata("mdblist.102554", "aio-metadata", "Horror", "ADDON", "movie", null, null, null)));
        CollectionTemplateEntry collectionTemplateEntryEntry25 = collectionTemplateManifest.entry("Documentary", collectionGroupKind2, "https://raw.githubusercontent.com/chrishudson918/images/main/Landscape%20Genres/DOC.jpegli.jpg", collectionTileShape, true, null, t7.a.E(collectionTemplateManifest.source("aio-metadata", "movie", "mdblist.128051"), collectionTemplateManifest.source("aio-metadata", "series", "mdblist.128052"), collectionTemplateManifest.tmdbGenreSource("movie", 99), collectionTemplateManifest.tmdbGenreSource("series", 99)), t7.a.E(collectionTemplateManifest.metadata("mdblist.128051", "aio-metadata", "Popular Documentary Movies", "MDBLIST", "movie", 500, "snoak", "https://mdblist.com/lists/snoak/popular-documentary-movies"), collectionTemplateManifest.metadata("mdblist.128052", "aio-metadata", "Popular Documentary Shows", "MDBLIST", "series", 500, "snoak", "https://mdblist.com/lists/snoak/popular-documentary-shows")));
        CollectionTemplateEntry collectionTemplateEntryEntry26 = collectionTemplateManifest.entry("Romance", collectionGroupKind2, "https://raw.githubusercontent.com/chrishudson918/images/main/Landscape%20Genres/ROMANCE.jpegli.jpg", collectionTileShape, true, null, t7.a.E(collectionTemplateManifest.source("aio-metadata", "movie", "mdblist.128262"), collectionTemplateManifest.source("aio-metadata", "series", "mdblist.128265"), collectionTemplateManifest.tmdbGenreSource("movie", 10749), collectionTemplateManifest.tmdbKeywordSource("series", 9840)), t7.a.E(collectionTemplateManifest.metadata("mdblist.128262", "aio-metadata", "Popular Romance Movies", "MDBLIST", "movie", 500, "snoak", "https://mdblist.com/lists/snoak/popular-romance-movies"), collectionTemplateManifest.metadata("mdblist.128265", "aio-metadata", "Popular Romance Shows", "MDBLIST", "series", 200, "snoak", "https://mdblist.com/lists/snoak/popular-romance-shows")));
        CollectionTemplateEntry collectionTemplateEntryEntry27 = collectionTemplateManifest.entry("Animation", collectionGroupKind2, "https://raw.githubusercontent.com/chrishudson918/images/main/Landscape%20Genres/ANIMATION.jpegli.jpg", collectionTileShape, true, null, t7.a.E(collectionTemplateManifest.source("aio-metadata", "movie", "mdblist.121922"), collectionTemplateManifest.source("aio-metadata", "series", "mdblist.121921"), collectionTemplateManifest.tmdbGenreSource("movie", 16), collectionTemplateManifest.tmdbGenreSource("series", 16)), t7.a.E(collectionTemplateManifest.metadata("mdblist.121922", "aio-metadata", "Popular Animated Movies", "MDBLIST", "movie", 500, "snoak", "https://mdblist.com/lists/snoak/animationanime-movies"), collectionTemplateManifest.metadata("mdblist.121921", "aio-metadata", "Popular Animated Shows", "MDBLIST", "series", 500, "snoak", "https://mdblist.com/lists/snoak/animationanime-shows")));
        CollectionTemplateEntry collectionTemplateEntryEntry28 = collectionTemplateManifest.entry("Family", collectionGroupKind2, "https://raw.githubusercontent.com/chrishudson918/images/main/Landscape%20Genres/KIDS%20AND%20FAMILY.jpegli.jpg", collectionTileShape, true, null, t7.a.E(collectionTemplateManifest.source("aio-metadata", "movie", "mdblist.43249"), collectionTemplateManifest.source("aio-metadata", "series", "mdblist.43251"), collectionTemplateManifest.tmdbGenreSource("movie", 10751), collectionTemplateManifest.tmdbGenreSource("series", 10751)), t7.a.E(collectionTemplateManifest.metadata("mdblist.43249", "aio-metadata", "Popular Family Movies", "MDBLIST", "movie", 434, "familytv133", "https://mdblist.com/lists/familytv133/family-kids-english-movies-rated-g-pg"), collectionTemplateManifest.metadata("mdblist.43251", "aio-metadata", "Popular Family Shows", "MDBLIST", "series", 277, "familytv133", "https://mdblist.com/lists/familytv133/family-kids-english-tv-showsrated-g-pg")));
        List<CollectionSourceConfig> listE = t7.a.E(collectionTemplateManifest.tmdbGenreSource("movie", 14), collectionTemplateManifest.tmdbGenreSource("series", 10765));
        kotlin.collections.z zVar = kotlin.collections.z.f19728i;
        CollectionTemplateEntry collectionTemplateEntryEntry29 = collectionTemplateManifest.entry("Fantasy", collectionGroupKind2, "https://raw.githubusercontent.com/itsrenoria/fusion-starter-kit/refs/heads/main/resources/widgets/genres/wide/dannyrutledge/fantasy-wide.png", collectionTileShape, true, null, listE, zVar);
        CollectionTemplateEntry collectionTemplateEntryEntry30 = collectionTemplateManifest.entry("Adventure", collectionGroupKind2, "https://raw.githubusercontent.com/itsrenoria/fusion-starter-kit/refs/heads/main/resources/widgets/genres/wide/dannyrutledge/adventure-wide.png", collectionTileShape, true, null, t7.a.E(collectionTemplateManifest.tmdbGenreSource("movie", 12), collectionTemplateManifest.tmdbGenreSource("series", 10759)), zVar);
        CollectionTemplateEntry collectionTemplateEntryEntry31 = collectionTemplateManifest.entry("Superhero", collectionGroupKind2, "https://raw.githubusercontent.com/itsrenoria/fusion-starter-kit/refs/heads/main/resources/widgets/genres/wide/dannyrutledge/superheroes-wide.png", collectionTileShape, true, null, t7.a.E(collectionTemplateManifest.tmdbKeywordSource("movie", 9715), collectionTemplateManifest.tmdbKeywordSource("series", 9715)), zVar);
        CollectionTemplateEntry collectionTemplateEntryEntry32 = collectionTemplateManifest.entry("War & Military", collectionGroupKind2, "https://raw.githubusercontent.com/itsrenoria/fusion-starter-kit/refs/heads/main/resources/widgets/genres/wide/dannyrutledge/war-stories-wide.png", collectionTileShape, true, null, t7.a.E(collectionTemplateManifest.tmdbGenreSource("movie", 10752), collectionTemplateManifest.tmdbGenreSource("series", 10768)), zVar);
        CollectionGroupKind collectionGroupKind5 = CollectionGroupKind.DECADE;
        List<CollectionTemplateEntry> listE2 = t7.a.E(collectionTemplateEntryEntry, collectionTemplateEntryEntry2, collectionTemplateEntryEntry3, collectionTemplateEntryEntry4, collectionTemplateEntryEntry5, collectionTemplateEntryEntry6, collectionTemplateEntryEntry7, collectionTemplateEntryEntry8, collectionTemplateEntryEntry9, collectionTemplateEntryEntry10, collectionTemplateEntryEntry11, collectionTemplateEntryEntry12, collectionTemplateEntryEntry13, collectionTemplateEntryEntry14, collectionTemplateEntryEntry15, collectionTemplateEntryEntry16, collectionTemplateEntryEntry17, collectionTemplateEntryEntry18, collectionTemplateEntryEntry19, collectionTemplateEntryEntry20, collectionTemplateEntryEntry21, collectionTemplateEntryEntry22, collectionTemplateEntryEntry23, collectionTemplateEntryEntry24, collectionTemplateEntryEntry25, collectionTemplateEntryEntry26, collectionTemplateEntryEntry27, collectionTemplateEntryEntry28, collectionTemplateEntryEntry29, collectionTemplateEntryEntry30, collectionTemplateEntryEntry31, collectionTemplateEntryEntry32, collectionTemplateManifest.entry("20's Movies", collectionGroupKind5, "https://raw.githubusercontent.com/elucidationvortex-source/nuviotemplate/refs/heads/main/images/20snew.jpg", collectionTileShape, true, null, t7.a.D(collectionTemplateManifest.source("aio-metadata", "movie", "mdblist.91304")), t7.a.D(collectionTemplateManifest.metadata("mdblist.91304", "aio-metadata", "Popular 2020s Movies", "MDBLIST", "movie", 179, "snoak", "https://mdblist.com/lists/snoak/top-2020s-movies"))), collectionTemplateManifest.entry("10's Movies", collectionGroupKind5, "https://raw.githubusercontent.com/elucidationvortex-source/nuviotemplate/refs/heads/main/images/10snew.jpg", collectionTileShape, true, null, t7.a.D(collectionTemplateManifest.source("aio-metadata", "movie", "mdblist.91303")), t7.a.D(collectionTemplateManifest.metadata("mdblist.91303", "aio-metadata", "Popular 2010s Movies", "MDBLIST", "movie", 372, "snoak", "https://mdblist.com/lists/snoak/top-2010s-movies"))), collectionTemplateManifest.entry("00's Movies", collectionGroupKind5, "https://raw.githubusercontent.com/elucidationvortex-source/nuviotemplate/refs/heads/main/images/00snew.jpg", collectionTileShape, true, null, t7.a.D(collectionTemplateManifest.source("aio-metadata", "movie", "mdblist.91302")), t7.a.D(collectionTemplateManifest.metadata("mdblist.91302", "aio-metadata", "Popular 2000s Movies", "MDBLIST", "movie", 448, "snoak", "https://mdblist.com/lists/snoak/top-2000s-movies"))), collectionTemplateManifest.entry("90's Movies", collectionGroupKind5, "https://raw.githubusercontent.com/elucidationvortex-source/nuviotemplate/refs/heads/main/images/90snew.jpg", collectionTileShape, true, null, t7.a.D(collectionTemplateManifest.source("aio-metadata", "movie", "mdblist.91300")), t7.a.D(collectionTemplateManifest.metadata("mdblist.91300", "aio-metadata", "Popular 1990s Movies", "MDBLIST", "movie", Integer.valueOf(MediaError.DetailedErrorCode.HLS_NETWORK_KEY_LOAD), "snoak", "https://mdblist.com/lists/snoak/top-1990s-movies"))), collectionTemplateManifest.entry("80's Movies", collectionGroupKind5, "https://raw.githubusercontent.com/elucidationvortex-source/nuviotemplate/refs/heads/main/images/80snew.jpg", collectionTileShape, true, null, t7.a.D(collectionTemplateManifest.source("aio-metadata", "movie", "mdblist.91301")), t7.a.D(collectionTemplateManifest.metadata("mdblist.91301", "aio-metadata", "Popular 1980s Movies", "MDBLIST", "movie", 220, "snoak", "https://mdblist.com/lists/snoak/top-1980s-movies"))), collectionTemplateManifest.entry("70's Movies", collectionGroupKind5, "https://raw.githubusercontent.com/elucidationvortex-source/nuviotemplate/refs/heads/main/images/70snew.png", collectionTileShape, true, null, t7.a.D(collectionTemplateManifest.source("aio-metadata", "movie", "mdblist.127962")), t7.a.D(collectionTemplateManifest.metadata("mdblist.127962", "aio-metadata", "Popular 1970s Movies", "MDBLIST", "movie", 159, "snoak", "https://mdblist.com/lists/snoak/popular-1970s-movies"))), collectionTemplateManifest.entry("60's Movies", collectionGroupKind5, "https://raw.githubusercontent.com/elucidationvortex-source/nuviotemplate/refs/heads/main/images/60snew.png", collectionTileShape, true, null, t7.a.D(collectionTemplateManifest.source("aio-metadata", "movie", "mdblist.144321")), t7.a.D(collectionTemplateManifest.metadata("mdblist.144321", "aio-metadata", "Popular 1960s Movies", "MDBLIST", "movie", 117, "snoak", "https://mdblist.com/lists/snoak/popular-1960s-movies"))), collectionTemplateManifest.entry("Marvel", collectionGroupKind3, "https://raw.githubusercontent.com/elucidationvortex-source/nuviotemplate/refs/heads/main/images/Marvel.jpg", collectionTileShape, true, null, t7.a.D(collectionTemplateManifest.source("com.joaogonp.marveladdon.custom.marvel-mcu", "Marvel", "marvel-mcu")), t7.a.D(collectionTemplateManifest.metadata("marvel-mcu", "com.joaogonp.marveladdon.custom.marvel-mcu", "Marvel", "ADDON", "marvel", null, null, null))), collectionTemplateManifest.entry("DC Universe", collectionGroupKind3, "https://raw.githubusercontent.com/elucidationvortex-source/nuviotemplate/refs/heads/main/images/DC.jpg", collectionTileShape, true, null, t7.a.D(collectionTemplateManifest.source("com.tapframe.dcaddon.custom.dc-chronological", "DC", "dc-chronological")), t7.a.D(collectionTemplateManifest.metadata("dc-chronological", "com.tapframe.dcaddon.custom.dc-chronological", "DC Universe", "ADDON", "dc", null, null, null))), collectionTemplateManifest.entry("Star Wars", collectionGroupKind3, "https://raw.githubusercontent.com/elucidationvortex-source/nuviotemplate/refs/heads/main/images/Star-Wars.jpg", collectionTileShape, true, null, t7.a.D(collectionTemplateManifest.source("com.starwars.addon.custom.sw-movies-series-chronological", "StarWars", "sw-movies-series-chronological")), t7.a.D(collectionTemplateManifest.metadata("sw-movies-series-chronological", "com.starwars.addon.custom.sw-movies-series-chronological", "Star Wars", "ADDON", "starwars", null, null, null))), collectionTemplateManifest.entry("James Bond", collectionGroupKind3, "https://raw.githubusercontent.com/elucidationvortex-source/nuviotemplate/refs/heads/main/images/007.jpg", collectionTileShape, true, null, t7.a.D(collectionTemplateManifest.source("aio-metadata", "movie", "mdblist.7947")), t7.a.D(collectionTemplateManifest.metadata("mdblist.7947", "aio-metadata", "James Bond Collection", "MDBLIST", "movie", 27, "hdlists", "https://mdblist.com/lists/hdlists/james-bond-movies"))), collectionTemplateManifest.entry("Harry Potter", collectionGroupKind3, "https://raw.githubusercontent.com/elucidationvortex-source/nuviotemplate/refs/heads/main/images/Harry-Potter.jpg", collectionTileShape, true, null, t7.a.D(collectionTemplateManifest.source("aio-metadata", "movie", "mdblist.102972")), t7.a.D(collectionTemplateManifest.metadata("mdblist.102972", "aio-metadata", "Harry Potter Collection", "MDBLIST", "movie", 11, "thebirdod", "https://mdblist.com/lists/thebirdod/harry-potter-collection"))), collectionTemplateManifest.entry("Alien vs Predator", collectionGroupKind3, "https://raw.githubusercontent.com/elucidationvortex-source/nuviotemplate/refs/heads/main/images/avp.jpg", collectionTileShape, true, null, t7.a.E(collectionTemplateManifest.source("aio-metadata", TtmlNode.COMBINE_ALL, "mdblist.101434"), collectionTemplateManifest.tmdbCollectionSource(8091), collectionTemplateManifest.tmdbCollectionSource(399), collectionTemplateManifest.tmdbCollectionSource(115762)), t7.a.D(collectionTemplateManifest.metadata("mdblist.101434", "aio-metadata", "Alien vs Predator Collection", "MDBLIST", TtmlNode.COMBINE_ALL, 17, "exoduso", "https://mdblist.com/lists/exoduso/predator-franchise"))), collectionTemplateManifest.entry("Pirates of the Caribbean", collectionGroupKind3, "https://raw.githubusercontent.com/elucidationvortex-source/nuviotemplate/refs/heads/main/images/pirates.jpg", collectionTileShape, true, null, t7.a.E(collectionTemplateManifest.source("aio-metadata", "movie", "mdblist.82145"), collectionTemplateManifest.tmdbCollectionSource(295)), t7.a.D(collectionTemplateManifest.metadata("mdblist.82145", "aio-metadata", "Pirates of the Caribbean Collection", "MDBLIST", "movie", 5, "aaron713", "https://mdblist.com/lists/aaron713/pirates-of-the-caribbean-collection"))), collectionTemplateManifest.entry("Terminator", collectionGroupKind3, "https://raw.githubusercontent.com/elucidationvortex-source/nuviotemplate/refs/heads/main/images/Terminator.jpg", collectionTileShape, true, null, t7.a.E(collectionTemplateManifest.source("aio-metadata", TtmlNode.COMBINE_ALL, "mdblist.125458"), collectionTemplateManifest.tmdbCollectionSource(528)), t7.a.D(collectionTemplateManifest.metadata("mdblist.125458", "aio-metadata", "The Terminator Collection", "MDBLIST", TtmlNode.COMBINE_ALL, 9, "andyhawks", "https://mdblist.com/lists/andyhawks/universe-the-terminator"))), collectionTemplateManifest.entry("Mission Impossible", collectionGroupKind3, "https://raw.githubusercontent.com/elucidationvortex-source/nuviotemplate/refs/heads/main/images/mission-impossible.jpg", collectionTileShape, true, null, t7.a.D(collectionTemplateManifest.source("aio-metadata", "movie", "mdblist.42716")), t7.a.D(collectionTemplateManifest.metadata("mdblist.42716", "aio-metadata", "Mission Impossible Collection", "MDBLIST", "movie", 8, "nammel", "https://mdblist.com/lists/nammel/mission-impossible-saga"))), collectionTemplateManifest.entry("Jurassic Park", collectionGroupKind3, "https://raw.githubusercontent.com/elucidationvortex-source/nuviotemplate/refs/heads/main/images/jurrasic-park.jpg", collectionTileShape, true, null, t7.a.D(collectionTemplateManifest.source("aio-metadata", TtmlNode.COMBINE_ALL, "mdblist.120197")), t7.a.D(collectionTemplateManifest.metadata("mdblist.120197", "aio-metadata", "Jurassic Park Collection", "MDBLIST", TtmlNode.COMBINE_ALL, 12, "purple_smurf", "https://mdblist.com/lists/purple_smurf/jurassic-park"))), collectionTemplateManifest.entry("The Matrix", collectionGroupKind3, "https://raw.githubusercontent.com/elucidationvortex-source/nuviotemplate/refs/heads/main/images/matrix.jpg", collectionTileShape, true, null, t7.a.E(collectionTemplateManifest.source("aio-metadata", "movie", "mdblist.125142"), collectionTemplateManifest.tmdbCollectionSource(2344)), t7.a.D(collectionTemplateManifest.metadata("mdblist.125142", "aio-metadata", "The Matrix Collection", "MDBLIST", "movie", 5, "andyhawks", "https://mdblist.com/lists/andyhawks/universe-the-matrix"))), collectionTemplateManifest.entry("Lord of the Rings", collectionGroupKind3, "https://raw.githubusercontent.com/elucidationvortex-source/nuviotemplate/refs/heads/main/images/lotr.jpg", collectionTileShape, true, null, t7.a.D(collectionTemplateManifest.source("aio-metadata", "movie", "mdblist.94304")), t7.a.D(collectionTemplateManifest.metadata("mdblist.94304", "aio-metadata", "Lord of the Rings and Hobbit Collection", "MDBLIST", "movie", 6, "spudhead15", "https://mdblist.com/lists/spudhead15/lord-of-the-rings-and-hobbit-collection"))), collectionTemplateManifest.entry("X-Men", collectionGroupKind3, "https://i.postimg.cc/RC2Ny8Ds/X-Men.jpg", collectionTileShape, true, null, zVar, zVar), collectionTemplateManifest.entry("Hunger Games", collectionGroupKind3, "https://i.postimg.cc/FzfKsZ29/Hunger-Games.jpg", collectionTileShape, true, null, zVar, zVar), collectionTemplateManifest.entry("Avatar", collectionGroupKind3, "https://i.postimg.cc/nLSV4nhT/AVATAR.jpg", collectionTileShape, true, null, zVar, zVar), collectionTemplateManifest.entry("Dune", collectionGroupKind3, "https://i.postimg.cc/HnrT6frm/Dune.jpg", collectionTileShape, true, null, zVar, zVar), collectionTemplateManifest.entry("Indiana Jones", collectionGroupKind3, "https://i.postimg.cc/tCbrtFwS/Indiana-Jo.jpg", collectionTileShape, true, null, zVar, zVar), collectionTemplateManifest.entry("The Godfather", collectionGroupKind3, "https://i.postimg.cc/X7YwbzbT/The-Godfather.jpg", collectionTileShape, true, null, zVar, zVar), collectionTemplateManifest.entry("John Wick", collectionGroupKind3, "https://i.postimg.cc/W14q7rtM/JW.jpg", collectionTileShape, true, null, zVar, zVar), collectionTemplateManifest.entry("Transformers", collectionGroupKind3, "https://i.postimg.cc/CLw3Lyhx/Transformers.jpg", collectionTileShape, true, null, zVar, zVar));
        entries = listE2;
        LinkedHashMap linkedHashMap = new LinkedHashMap(qb.d.i(kotlin.collections.i0.q0(kotlin.collections.s.U(listE2, 10)), 16));
        for (Object obj : listE2) {
            linkedHashMap.put(((CollectionTemplateEntry) obj).getId(), obj);
        }
        entriesById = linkedHashMap;
        entryIds = linkedHashMap.keySet();
        validRailGroups = kotlin.collections.x.g1(railOrder);
    }

    private CollectionTemplateManifest() {
    }

    private final CollectionTemplateEntry entry(String title, CollectionGroupKind group, String coverImageUrl, CollectionTileShape tileShape, boolean hideTitle, String heroVideoUrl, List<CollectionSourceConfig> sources, List<CollectionSourceListMetadata> listMetadata) {
        return new CollectionTemplateEntry(androidx.compose.material3.d.n("collection_", group.name().toLowerCase(Locale.US), "_", slugify(title)), title, group, coverImageUrl, tileShape, hideTitle, heroVideoUrl, sources, listMetadata);
    }

    private final CollectionSourceConfig mdblistSource(String slug) {
        return new CollectionSourceConfig(CollectionSourceKind.MDBLIST_PUBLIC, null, null, null, null, null, null, null, null, null, null, null, null, slug, 8190, null);
    }

    private final CollectionSourceListMetadata metadata(String sourceCatalogId, String sourceAddonId, String sourceName, String sourceLabel, String mediaType, Integer itemCount, String author, String url) {
        return new CollectionSourceListMetadata(sourceCatalogId, sourceAddonId, sourceName, sourceLabel, mediaType, itemCount, author, url);
    }

    private final String slugify(String value) {
        return kotlin.text.o.M0(CollectionManifestRegexes.INSTANCE.getSLUGIFY_NON_ALPHA_NUM_REGEX().f("_", kotlin.text.u.O(kotlin.text.u.O(kotlin.text.u.O(value.toLowerCase(Locale.US), "+", "plus", false), "&", "and", false), "'", "", false)), '_');
    }

    private final CollectionSourceConfig source(String addonId, String type, String catalogId) {
        return new CollectionSourceConfig(CollectionSourceKind.ADDON_CATALOG, null, addonId, type, catalogId, null, null, null, null, null, null, null, null, null, 16354, null);
    }

    private final CollectionSourceConfig tmdbCollectionSource(int collectionId) {
        return new CollectionSourceConfig(CollectionSourceKind.TMDB_COLLECTION, null, null, null, null, null, null, Integer.valueOf(collectionId), null, null, null, null, null, null, 16254, null);
    }

    private final CollectionSourceConfig tmdbGenreSource(String type, int genreId) {
        return new CollectionSourceConfig(CollectionSourceKind.TMDB_GENRE, type, null, null, null, Integer.valueOf(genreId), null, null, null, null, null, "popularity.desc", null, null, 14300, null);
    }

    private final CollectionSourceConfig tmdbKeywordSource(String type, int keywordId) {
        return new CollectionSourceConfig(CollectionSourceKind.TMDB_KEYWORD, type, null, null, null, null, null, null, Integer.valueOf(keywordId), null, null, "popularity.desc", null, null, 14076, null);
    }

    private final CollectionSourceConfig watchProviderSource(String type, int providerId) {
        return new CollectionSourceConfig(CollectionSourceKind.TMDB_WATCH_PROVIDER, type, null, null, null, null, null, null, null, Integer.valueOf(providerId), "US", "popularity.desc", null, null, 12796, null);
    }

    public final List<String> autoInstalledAddonUrls() {
        return t7.a.E(STREAMING_ADDON_URL, MARVEL_ADDON_URL, DC_ADDON_URL, STAR_WARS_ADDON_URL);
    }

    public final String descriptionFor(CollectionTemplateEntry entry) {
        switch (WhenMappings.$EnumSwitchMapping$0[entry.getGroup().ordinal()]) {
            case 1:
                return "Curated spotlight picks sourced from the latest public lists.";
            case 2:
                return "Browse movies and series grouped by streaming service.";
            case 3:
                return "A themed mix of movies and series built around a single genre mood.";
            case 4:
                return "Explore movies collected around a specific decade.";
            case 5:
                return "A franchise timeline or universe collection gathered from dedicated lists.";
            case 6:
                return "Shows and movies grouped around a specific network or brand.";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final CollectionTemplateEntry entryForCatalog(String catalogId) {
        if (catalogId != null) {
            return entriesById.get(catalogId);
        }
        return null;
    }

    public final List<CollectionTemplateEntry> getEntries() {
        return entries;
    }

    public final List<CollectionGroupKind> getRailOrder() {
        return railOrder;
    }

    public final boolean hasEntriesFor(CollectionGroupKind group) {
        List<CollectionTemplateEntry> list = entries;
        if (list != null && list.isEmpty()) {
            return false;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (((CollectionTemplateEntry) it.next()).getGroup() == group) {
                return true;
            }
        }
        return false;
    }

    public final boolean isValidCollectionConfig(CatalogConfig config) {
        int i10 = WhenMappings.$EnumSwitchMapping$1[config.getKind().ordinal()];
        if (i10 == 1) {
            return entryIds.contains(config.getId());
        }
        if (i10 != 2) {
            return true;
        }
        CollectionGroupKind collectionGroup = config.getCollectionGroup();
        return collectionGroup != null && validRailGroups.contains(collectionGroup) && hasEntriesFor(collectionGroup);
    }

    public final List<CollectionSourceListMetadata> listMetadataFor(String catalogId) {
        CollectionTemplateEntry collectionTemplateEntryEntryForCatalog = entryForCatalog(catalogId);
        List<CollectionSourceListMetadata> listMetadata = collectionTemplateEntryEntryForCatalog != null ? collectionTemplateEntryEntryForCatalog.getListMetadata() : null;
        return listMetadata == null ? kotlin.collections.z.f19728i : listMetadata;
    }

    public final String railCatalogId(CollectionGroupKind group) {
        return "collection_rail_".concat(group.name().toLowerCase(Locale.US));
    }

    public final String railTitle(CollectionGroupKind group) {
        switch (WhenMappings.$EnumSwitchMapping$0[group.ordinal()]) {
            case 1:
                return "Featured";
            case 2:
                return "Services";
            case 3:
                return "Genres";
            case 4:
                return "Decades";
            case 5:
                return "Franchises";
            case 6:
                return "Networks";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final List<String> requiredAddonUrlsFor(CollectionTemplateEntry entry) {
        List<CollectionSourceConfig> sources = entry.getSources();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = sources.iterator();
        while (it.hasNext()) {
            String addonId = ((CollectionSourceConfig) it.next()).getAddonId();
            if (addonId != null) {
                arrayList.add(addonId);
            }
        }
        Set setG1 = kotlin.collections.x.g1(arrayList);
        z6.c cVarN = t7.a.n();
        if (setG1.contains("pw.ers.netflix-catalog")) {
            cVarN.add(STREAMING_ADDON_URL);
        }
        if (setG1.contains("com.joaogonp.marveladdon.custom.marvel-mcu")) {
            cVarN.add(MARVEL_ADDON_URL);
        }
        if (setG1.contains("com.btmv.addon.dcuniverse.custom.dc-chronological")) {
            cVarN.add(DC_ADDON_URL);
        }
        if (setG1.contains("com.starwars.addon.custom.sw-movies-series-chronological")) {
            cVarN.add(STAR_WARS_ADDON_URL);
        }
        return t7.a.e(cVarN);
    }
}
