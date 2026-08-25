package com.arflix.tv.data.repository;

import androidx.compose.runtime.ComposerKt;
import androidx.fragment.app.a2;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.arflix.tv.data.api.StreamApi;
import com.arflix.tv.data.api.StremioMetaPreview;
import com.arflix.tv.data.api.StremioStream;
import com.arflix.tv.data.model.Addon;
import com.arflix.tv.data.model.AddonCatalog;
import com.arflix.tv.data.model.AddonManifest;
import com.arflix.tv.data.model.CatalogConfig;
import com.arflix.tv.data.model.CatalogKind;
import com.arflix.tv.data.model.CatalogSourceType;
import com.arflix.tv.data.model.Category;
import com.arflix.tv.data.model.CollectionGroupKind;
import com.arflix.tv.data.model.CollectionSourceConfig;
import com.arflix.tv.data.model.CollectionSourceKind;
import com.arflix.tv.data.model.CollectionTileShape;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.model.SportsAddonCapabilities;
import com.arflix.tv.data.model.StreamSource;
import com.arflix.tv.data.repository.MediaRepository;
import com.google.android.gms.cast.MediaTrack;
import com.google.common.util.concurrent.r0;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import ka.x0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u000b\b\u0007\u0018\u0000 x2\u00020\u0001:\u0004xyz{B\u0019\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0004\b\n\u0010\u000bJ0\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\b2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0086@¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0012\u001a\u00020\u000e¢\u0006\u0004\b\u0014\u0010\u0015J(\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0016H\u0086@¢\u0006\u0004\b\u001a\u0010\u001bJ/\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0004\b\u001e\u0010\u001fJ\u0019\u0010!\u001a\u0004\u0018\u00010\u000e2\b\u0010 \u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b!\u0010\"J\"\u0010%\u001a\u0004\u0018\u00010$2\u0006\u0010 \u001a\u00020\u000e2\u0006\u0010#\u001a\u00020\u000eH\u0086@¢\u0006\u0004\b%\u0010&J*\u0010'\u001a\u0004\u0018\u00010\t2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0082@¢\u0006\u0004\b'\u0010\u0011J\u0015\u0010(\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002¢\u0006\u0004\b(\u0010\u000bJ/\u0010-\u001a\u00020\t2\b\b\u0002\u0010*\u001a\u00020)2\u0014\b\u0002\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0+H\u0002¢\u0006\u0004\b-\u0010.J*\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0+2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\bH\u0082@¢\u0006\u0004\b/\u00100J>\u00104\u001a\b\u0012\u0004\u0012\u0002030\b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u00101\u001a\u00020\u00162\u0006\u00102\u001a\u00020)H\u0082@¢\u0006\u0004\b4\u00105J&\u0010:\u001a\b\u0012\u0004\u0012\u0002090\b2\u0006\u00106\u001a\u00020\f2\u0006\u00108\u001a\u000207H\u0082@¢\u0006\u0004\b:\u0010;J;\u0010@\u001a\u0002032\u0006\u0010<\u001a\u00020\u000e2\u0006\u0010#\u001a\u00020\u000e2\u0006\u0010=\u001a\u00020\u000e2\u0006\u0010>\u001a\u00020\u000e2\n\b\u0002\u0010?\u001a\u0004\u0018\u00010\u000eH\u0002¢\u0006\u0004\b@\u0010AJ'\u0010B\u001a\b\u0012\u0004\u0012\u0002070\b2\u0006\u00106\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0002¢\u0006\u0004\bB\u0010CJ%\u0010D\u001a\u0004\u0018\u000103*\u0002092\u0006\u00106\u001a\u00020\f2\u0006\u00108\u001a\u000207H\u0002¢\u0006\u0004\bD\u0010EJ%\u0010I\u001a\u0004\u0018\u00010H*\u00020F2\u0006\u00106\u001a\u00020\f2\u0006\u0010G\u001a\u00020\u000eH\u0002¢\u0006\u0004\bI\u0010JJ\u001b\u0010L\u001a\u00020)*\u00020F2\u0006\u0010K\u001a\u00020\u000eH\u0002¢\u0006\u0004\bL\u0010MJ\u0017\u0010N\u001a\u00020)2\u0006\u0010K\u001a\u00020\u000eH\u0002¢\u0006\u0004\bN\u0010OJ\u0017\u0010Q\u001a\u00020)2\u0006\u0010P\u001a\u000209H\u0002¢\u0006\u0004\bQ\u0010RJ\u001b\u0010U\u001a\u00020)*\u0002072\u0006\u0010T\u001a\u00020SH\u0002¢\u0006\u0004\bU\u0010VJ\u0013\u0010W\u001a\u00020)*\u000207H\u0002¢\u0006\u0004\bW\u0010XJ\u001f\u0010Y\u001a\b\u0012\u0004\u0012\u00020\f0\b*\b\u0012\u0004\u0012\u00020\f0\bH\u0002¢\u0006\u0004\bY\u0010ZJ\u0017\u0010\\\u001a\u00020\u000e2\u0006\u0010[\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\\\u0010\"J\u0013\u0010]\u001a\u00020\u000e*\u000207H\u0002¢\u0006\u0004\b]\u0010^J'\u0010b\u001a\u00020\u000e2\u0006\u0010_\u001a\u00020\u000e2\u0006\u0010`\u001a\u00020\u000e2\u0006\u0010a\u001a\u00020\u000eH\u0002¢\u0006\u0004\bb\u0010cJ\u0019\u0010e\u001a\u0004\u0018\u00010d2\u0006\u0010 \u001a\u00020\u000eH\u0002¢\u0006\u0004\be\u0010fJ\u0019\u0010g\u001a\u0004\u0018\u00010\u000e2\u0006\u00106\u001a\u00020\fH\u0002¢\u0006\u0004\bg\u0010hJ\u0017\u0010i\u001a\u00020\u000e2\u0006\u0010[\u001a\u00020\u000eH\u0002¢\u0006\u0004\bi\u0010\"J\u0017\u0010j\u001a\u00020\u000e2\u0006\u0010[\u001a\u00020\u000eH\u0002¢\u0006\u0004\bj\u0010\"J\u0017\u0010k\u001a\u00020\u000e2\u0006\u0010[\u001a\u00020\u000eH\u0002¢\u0006\u0004\bk\u0010\"JK\u0010n\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0+2.\u0010m\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0018\u00010+0l\"\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0018\u00010+H\u0002¢\u0006\u0004\bn\u0010oJ\u0017\u0010r\u001a\u00020q2\u0006\u0010p\u001a\u00020\u000eH\u0002¢\u0006\u0004\br\u0010sR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010tR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010uR\u001a\u0010v\u001a\b\u0012\u0004\u0012\u00020S0\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bv\u0010w¨\u0006|"}, d2 = {"Lcom/arflix/tv/data/repository/SportsRepository;", "", "Lcom/arflix/tv/data/repository/StreamRepository;", "streamRepository", "Lcom/arflix/tv/data/api/StreamApi;", "streamApi", "<init>", "(Lcom/arflix/tv/data/repository/StreamRepository;Lcom/arflix/tv/data/api/StreamApi;)V", "", "Lcom/arflix/tv/data/model/Category;", "defaultHomeRows", "()Ljava/util/List;", "Lcom/arflix/tv/data/model/Addon;", "addons", "", "selectedSportId", "buildHomeRows", "(Ljava/util/List;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "catalogId", "Lcom/arflix/tv/data/model/CatalogConfig;", "sportsCollectionCatalog", "(Ljava/lang/String;)Lcom/arflix/tv/data/model/CatalogConfig;", "", "offset", "limit", "Lcom/arflix/tv/data/repository/MediaRepository$CategoryPageResult;", "loadSportsCollectionPage", "(Ljava/lang/String;IILd7/d;)Ljava/lang/Object;", "categories", "sportsRows", "mergeSportsRows", "(Ljava/util/List;Ljava/util/List;)Ljava/util/List;", "status", "selectedSportIdFromStatus", "(Ljava/lang/String;)Ljava/lang/String;", LinkHeader.Parameters.Title, "Lcom/arflix/tv/data/repository/SportsRepository$SportsPlayback;", "resolvePlayback", "(Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "loadPopularLiveTvCategory", "buildLockedRows", "", "locked", "", "artworkOverrides", "sportsCategoryRow", "(ZLjava/util/Map;)Lcom/arflix/tv/data/model/Category;", "loadSportsCategoryArtworkOverrides", "(Ljava/util/List;Ld7/d;)Ljava/lang/Object;", "maxItems", "liveOnly", "Lcom/arflix/tv/data/model/MediaItem;", "loadSportItems", "(Ljava/util/List;Ljava/lang/String;IZLd7/d;)Ljava/lang/Object;", "addon", "Lcom/arflix/tv/data/model/AddonCatalog;", "catalog", "Lcom/arflix/tv/data/api/StremioMetaPreview;", "loadCatalogMetas", "(Lcom/arflix/tv/data/model/Addon;Lcom/arflix/tv/data/model/AddonCatalog;Ld7/d;)Ljava/lang/Object;", "key", MediaTrack.ROLE_SUBTITLE, "overview", "badge", "placeholderItem", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/arflix/tv/data/model/MediaItem;", "candidateCatalogs", "(Lcom/arflix/tv/data/model/Addon;Ljava/lang/String;)Ljava/util/List;", "toMediaItem", "(Lcom/arflix/tv/data/api/StremioMetaPreview;Lcom/arflix/tv/data/model/Addon;Lcom/arflix/tv/data/model/AddonCatalog;)Lcom/arflix/tv/data/model/MediaItem;", "Lcom/arflix/tv/data/api/StremioStream;", "fallbackTitle", "Lcom/arflix/tv/data/model/StreamSource;", "toStreamSource", "(Lcom/arflix/tv/data/api/StremioStream;Lcom/arflix/tv/data/model/Addon;Ljava/lang/String;)Lcom/arflix/tv/data/model/StreamSource;", "rawUrl", "isPlayableSportsStream", "(Lcom/arflix/tv/data/api/StremioStream;Ljava/lang/String;)Z", "isNativePlayableSportsUrl", "(Ljava/lang/String;)Z", "meta", "isCurrentlyLive", "(Lcom/arflix/tv/data/api/StremioMetaPreview;)Z", "Lcom/arflix/tv/data/repository/SportsRepository$SportsCategoryDef;", "sport", "matchesSport", "(Lcom/arflix/tv/data/model/AddonCatalog;Lcom/arflix/tv/data/repository/SportsRepository$SportsCategoryDef;)Z", "isPopularLiveCatalog", "(Lcom/arflix/tv/data/model/AddonCatalog;)Z", "prioritizedSportsAddons", "(Ljava/util/List;)Ljava/util/List;", "value", "cleanSportsDescription", "text", "(Lcom/arflix/tv/data/model/AddonCatalog;)Ljava/lang/String;", "addonId", LinkHeader.Parameters.Type, "eventId", "eventStatus", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "Lcom/arflix/tv/data/repository/SportsRepository$ParsedEventStatus;", "parseEventStatus", "(Ljava/lang/String;)Lcom/arflix/tv/data/repository/SportsRepository$ParsedEventStatus;", "addonBaseUrl", "(Lcom/arflix/tv/data/model/Addon;)Ljava/lang/String;", "encodePathSegment", "encodeStatusPart", "decodeStatusPart", "", "maps", "mergeHeaders", "([Ljava/util/Map;)Ljava/util/Map;", "sizeStr", "", "parseSizeToBytes", "(Ljava/lang/String;)J", "Lcom/arflix/tv/data/repository/StreamRepository;", "Lcom/arflix/tv/data/api/StreamApi;", "sportsCategories", "Ljava/util/List;", "Companion", "SportsPlayback", "SportsCategoryDef", "ParsedEventStatus", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SportsRepository {
    private static final long CATEGORY_ARTWORK_TIMEOUT_MS = 1500;
    private static final int MAX_CATALOGS_PER_LOAD = 3;
    private static final int MAX_EVENT_ITEMS = 24;
    private final List<SportsCategoryDef> sportsCategories;
    private final StreamApi streamApi;
    private final StreamRepository streamRepository;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = StreamRepository.$stable;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/arflix/tv/data/repository/SportsRepository$Companion;", "", "<init>", "()V", "MAX_EVENT_ITEMS", "", "MAX_CATALOGS_PER_LOAD", "CATEGORY_ARTWORK_TIMEOUT_MS", "", "drawable", "", ContentDisposition.Parameters.Name, "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String drawable(String name) {
            return androidx.compose.material3.d.C("android.resource://com.arvio.tv/drawable/", name);
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/data/repository/SportsRepository$ParsedEventStatus;", "", "addonId", "", LinkHeader.Parameters.Type, "eventId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAddonId", "()Ljava/lang/String;", "getType", "getEventId", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class ParsedEventStatus {
        private final String addonId;
        private final String eventId;
        private final String type;

        public ParsedEventStatus(String str, String str2, String str3) {
            this.addonId = str;
            this.type = str2;
            this.eventId = str3;
        }

        public static /* synthetic */ ParsedEventStatus copy$default(ParsedEventStatus parsedEventStatus, String str, String str2, String str3, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = parsedEventStatus.addonId;
            }
            if ((i10 & 2) != 0) {
                str2 = parsedEventStatus.type;
            }
            if ((i10 & 4) != 0) {
                str3 = parsedEventStatus.eventId;
            }
            return parsedEventStatus.copy(str, str2, str3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getAddonId() {
            return this.addonId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getEventId() {
            return this.eventId;
        }

        public final ParsedEventStatus copy(String addonId, String type, String eventId) {
            return new ParsedEventStatus(addonId, type, eventId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ParsedEventStatus)) {
                return false;
            }
            ParsedEventStatus parsedEventStatus = (ParsedEventStatus) other;
            return kotlin.jvm.internal.p.a(this.addonId, parsedEventStatus.addonId) && kotlin.jvm.internal.p.a(this.type, parsedEventStatus.type) && kotlin.jvm.internal.p.a(this.eventId, parsedEventStatus.eventId);
        }

        public final String getAddonId() {
            return this.addonId;
        }

        public final String getEventId() {
            return this.eventId;
        }

        public final String getType() {
            return this.type;
        }

        public int hashCode() {
            return this.eventId.hashCode() + androidx.compose.foundation.c.c(this.addonId.hashCode() * 31, 31, this.type);
        }

        public String toString() {
            String str = this.addonId;
            String str2 = this.type;
            return a0.c.p(a2.r("ParsedEventStatus(addonId=", str, ", type=", str2, ", eventId="), this.eventId, ")");
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003JG\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u001e"}, d2 = {"Lcom/arflix/tv/data/repository/SportsRepository$SportsCategoryDef;", "", TtmlNode.ATTR_ID, "", LinkHeader.Parameters.Title, "catalogIds", "", "keywords", "artworkUrl", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Set;Ljava/util/Set;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getTitle", "getCatalogIds", "()Ljava/util/Set;", "getKeywords", "getArtworkUrl", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SportsCategoryDef {
        private final String artworkUrl;
        private final Set<String> catalogIds;
        private final String id;
        private final Set<String> keywords;
        private final String title;

        public SportsCategoryDef(String str, String str2, Set<String> set, Set<String> set2, String str3) {
            this.id = str;
            this.title = str2;
            this.catalogIds = set;
            this.keywords = set2;
            this.artworkUrl = str3;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SportsCategoryDef copy$default(SportsCategoryDef sportsCategoryDef, String str, String str2, Set set, Set set2, String str3, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = sportsCategoryDef.id;
            }
            if ((i10 & 2) != 0) {
                str2 = sportsCategoryDef.title;
            }
            if ((i10 & 4) != 0) {
                set = sportsCategoryDef.catalogIds;
            }
            if ((i10 & 8) != 0) {
                set2 = sportsCategoryDef.keywords;
            }
            if ((i10 & 16) != 0) {
                str3 = sportsCategoryDef.artworkUrl;
            }
            String str4 = str3;
            Set set3 = set;
            return sportsCategoryDef.copy(str, str2, set3, set2, str4);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        public final Set<String> component3() {
            return this.catalogIds;
        }

        public final Set<String> component4() {
            return this.keywords;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getArtworkUrl() {
            return this.artworkUrl;
        }

        public final SportsCategoryDef copy(String id, String title, Set<String> catalogIds, Set<String> keywords, String artworkUrl) {
            return new SportsCategoryDef(id, title, catalogIds, keywords, artworkUrl);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SportsCategoryDef)) {
                return false;
            }
            SportsCategoryDef sportsCategoryDef = (SportsCategoryDef) other;
            return kotlin.jvm.internal.p.a(this.id, sportsCategoryDef.id) && kotlin.jvm.internal.p.a(this.title, sportsCategoryDef.title) && kotlin.jvm.internal.p.a(this.catalogIds, sportsCategoryDef.catalogIds) && kotlin.jvm.internal.p.a(this.keywords, sportsCategoryDef.keywords) && kotlin.jvm.internal.p.a(this.artworkUrl, sportsCategoryDef.artworkUrl);
        }

        public final String getArtworkUrl() {
            return this.artworkUrl;
        }

        public final Set<String> getCatalogIds() {
            return this.catalogIds;
        }

        public final String getId() {
            return this.id;
        }

        public final Set<String> getKeywords() {
            return this.keywords;
        }

        public final String getTitle() {
            return this.title;
        }

        public int hashCode() {
            return this.artworkUrl.hashCode() + ((this.keywords.hashCode() + ((this.catalogIds.hashCode() + androidx.compose.foundation.c.c(this.id.hashCode() * 31, 31, this.title)) * 31)) * 31);
        }

        public String toString() {
            String str = this.id;
            String str2 = this.title;
            Set<String> set = this.catalogIds;
            Set<String> set2 = this.keywords;
            String str3 = this.artworkUrl;
            StringBuilder sbR = a2.r("SportsCategoryDef(id=", str, ", title=", str2, ", catalogIds=");
            sbR.append(set);
            sbR.append(", keywords=");
            sbR.append(set2);
            sbR.append(", artworkUrl=");
            return a0.c.p(sbR, str3, ")");
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J;\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e¨\u0006\u001d"}, d2 = {"Lcom/arflix/tv/data/repository/SportsRepository$SportsPlayback;", "", "mediaId", "", LinkHeader.Parameters.Title, "", "streamUrl", "addonId", "sourceName", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getMediaId", "()I", "getTitle", "()Ljava/lang/String;", "getStreamUrl", "getAddonId", "getSourceName", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SportsPlayback {
        public static final int $stable = 0;
        private final String addonId;
        private final int mediaId;
        private final String sourceName;
        private final String streamUrl;
        private final String title;

        public SportsPlayback(int i10, String str, String str2, String str3, String str4) {
            this.mediaId = i10;
            this.title = str;
            this.streamUrl = str2;
            this.addonId = str3;
            this.sourceName = str4;
        }

        public static /* synthetic */ SportsPlayback copy$default(SportsPlayback sportsPlayback, int i10, String str, String str2, String str3, String str4, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                i10 = sportsPlayback.mediaId;
            }
            if ((i11 & 2) != 0) {
                str = sportsPlayback.title;
            }
            if ((i11 & 4) != 0) {
                str2 = sportsPlayback.streamUrl;
            }
            if ((i11 & 8) != 0) {
                str3 = sportsPlayback.addonId;
            }
            if ((i11 & 16) != 0) {
                str4 = sportsPlayback.sourceName;
            }
            String str5 = str4;
            String str6 = str2;
            return sportsPlayback.copy(i10, str, str6, str3, str5);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getMediaId() {
            return this.mediaId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getStreamUrl() {
            return this.streamUrl;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getAddonId() {
            return this.addonId;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getSourceName() {
            return this.sourceName;
        }

        public final SportsPlayback copy(int mediaId, String title, String streamUrl, String addonId, String sourceName) {
            return new SportsPlayback(mediaId, title, streamUrl, addonId, sourceName);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SportsPlayback)) {
                return false;
            }
            SportsPlayback sportsPlayback = (SportsPlayback) other;
            return this.mediaId == sportsPlayback.mediaId && kotlin.jvm.internal.p.a(this.title, sportsPlayback.title) && kotlin.jvm.internal.p.a(this.streamUrl, sportsPlayback.streamUrl) && kotlin.jvm.internal.p.a(this.addonId, sportsPlayback.addonId) && kotlin.jvm.internal.p.a(this.sourceName, sportsPlayback.sourceName);
        }

        public final String getAddonId() {
            return this.addonId;
        }

        public final int getMediaId() {
            return this.mediaId;
        }

        public final String getSourceName() {
            return this.sourceName;
        }

        public final String getStreamUrl() {
            return this.streamUrl;
        }

        public final String getTitle() {
            return this.title;
        }

        public int hashCode() {
            return this.sourceName.hashCode() + androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(this.mediaId * 31, 31, this.title), 31, this.streamUrl), 31, this.addonId);
        }

        public String toString() {
            int i10 = this.mediaId;
            String str = this.title;
            String str2 = this.streamUrl;
            String str3 = this.addonId;
            String str4 = this.sourceName;
            StringBuilder sbO = a2.o("SportsPlayback(mediaId=", i10, ", title=", str, ", streamUrl=");
            y.a.i(sbO, str2, ", addonId=", str3, ", sourceName=");
            return a0.c.p(sbO, str4, ")");
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.SportsRepository$buildHomeRows$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/Category;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.SportsRepository$buildHomeRows$2", f = "SportsRepository.kt", l = {105, 106}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends Category>>, Object> {
        final /* synthetic */ List<Addon> $addons;
        final /* synthetic */ String $selectedSportId;
        Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(List<Addon> list, String str, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$addons = list;
            this.$selectedSportId = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return SportsRepository.this.new AnonymousClass2(this.$addons, this.$selectedSportId, dVar);
        }

        /* JADX WARN: Removed duplicated region for block: B:38:0x00a4  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r13) throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 213
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.SportsRepository.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super List<Category>> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.SportsRepository$loadCatalogMetas$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.SportsRepository", f = "SportsRepository.kt", l = {353}, m = "loadCatalogMetas", v = 2)
    public static final class AnonymousClass1 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SportsRepository.this.loadCatalogMetas(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.SportsRepository$loadPopularLiveTvCategory$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.SportsRepository", f = "SportsRepository.kt", l = {236}, m = "loadPopularLiveTvCategory", v = 2)
    public static final class C09711 extends f7.c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C09711(d7.d<? super C09711> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SportsRepository.this.loadPopularLiveTvCategory(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.SportsRepository$loadSportItems$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.SportsRepository", f = "SportsRepository.kt", l = {335}, m = "loadSportItems", v = 2)
    public static final class C09721 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C09721(d7.d<? super C09721> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SportsRepository.this.loadSportItems(null, null, 0, false, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.SportsRepository$loadSportsCategoryArtworkOverrides$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "", "<anonymous>", "(Lka/k0;)Ljava/util/Map;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.SportsRepository$loadSportsCategoryArtworkOverrides$2", f = "SportsRepository.kt", l = {319}, m = "invokeSuspend", v = 2)
    public static final class C09732 extends f7.j implements r7.p<ka.k0, d7.d<? super Map<String, ? extends String>>, Object> {
        final /* synthetic */ List<Addon> $addons;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ SportsRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C09732(List<Addon> list, SportsRepository sportsRepository, d7.d<? super C09732> dVar) {
            super(2, dVar);
            this.$addons = list;
            this.this$0 = sportsRepository;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C09732 c09732 = new C09732(this.$addons, this.this$0, dVar);
            c09732.L$0 = obj;
            return c09732;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            ka.k0 k0Var = (ka.k0) this.L$0;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                Addon addon = (Addon) kotlin.collections.x.o0(this.$addons);
                if (addon == null) {
                    return kotlin.collections.a0.f19683i;
                }
                List list = this.this$0.sportsCategories;
                SportsRepository sportsRepository = this.this$0;
                ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(ka.m0.e(2, x0.f19655d, k0Var, new SportsRepository$loadSportsCategoryArtworkOverrides$2$1$1(sportsRepository, addon, (SportsCategoryDef) it.next(), null)));
                }
                this.L$0 = null;
                this.L$1 = null;
                this.label = 1;
                obj = ka.m0.f(arrayList, this);
                e7.a aVar = e7.a.f15033i;
                if (obj == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return kotlin.collections.h0.A0(kotlin.collections.x.k0((Iterable) obj));
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super Map<String, String>> dVar) {
            return ((C09732) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.SportsRepository$loadSportsCollectionPage$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/repository/MediaRepository$CategoryPageResult;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/repository/MediaRepository$CategoryPageResult;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.SportsRepository$loadSportsCollectionPage$2", f = "SportsRepository.kt", l = {154, 164}, m = "invokeSuspend", v = 2)
    public static final class C09742 extends f7.j implements r7.p<ka.k0, d7.d<? super MediaRepository.CategoryPageResult>, Object> {
        final /* synthetic */ String $catalogId;
        final /* synthetic */ int $limit;
        final /* synthetic */ int $offset;
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ SportsRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C09742(String str, SportsRepository sportsRepository, int i10, int i11, d7.d<? super C09742> dVar) {
            super(2, dVar);
            this.$catalogId = str;
            this.this$0 = sportsRepository;
            this.$limit = i10;
            this.$offset = i11;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new C09742(this.$catalogId, this.this$0, this.$limit, this.$offset, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:41:0x00c0, code lost:
        
            if (r13 == r5) goto L42;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r13) throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 274
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.SportsRepository.C09742.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super MediaRepository.CategoryPageResult> dVar) {
            return ((C09742) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.SportsRepository$resolvePlayback$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/repository/SportsRepository$SportsPlayback;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/repository/SportsRepository$SportsPlayback;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.SportsRepository$resolvePlayback$2", f = "SportsRepository.kt", l = {194, ComposerKt.providerMapsKey, 221}, m = "invokeSuspend", v = 2)
    public static final class C09752 extends f7.j implements r7.p<ka.k0, d7.d<? super SportsPlayback>, Object> {
        final /* synthetic */ String $status;
        final /* synthetic */ String $title;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C09752(String str, String str2, d7.d<? super C09752> dVar) {
            super(2, dVar);
            this.$status = str;
            this.$title = str2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final StreamSource invokeSuspend$lambda$1(SportsRepository sportsRepository, Addon addon, String str, StremioStream stremioStream) {
            return sportsRepository.toStreamSource(stremioStream, addon, str);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return SportsRepository.this.new C09752(this.$status, this.$title, dVar);
        }

        /* JADX WARN: Removed duplicated region for block: B:51:0x0114  */
        /* JADX WARN: Removed duplicated region for block: B:56:0x0162  */
        /* JADX WARN: Removed duplicated region for block: B:65:0x017e  */
        /* JADX WARN: Removed duplicated region for block: B:72:0x01a0  */
        /* JADX WARN: Removed duplicated region for block: B:75:0x01a7  */
        /* JADX WARN: Removed duplicated region for block: B:91:0x0176 A[SYNTHETIC] */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r15) {
            /*
                Method dump skipped, instruction units count: 488
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.SportsRepository.C09752.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super SportsPlayback> dVar) {
            return ((C09752) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    @Inject
    public SportsRepository(StreamRepository streamRepository, StreamApi streamApi) {
        this.streamRepository = streamRepository;
        this.streamApi = streamApi;
        Set setSingleton = Collections.singleton("sports_basketball");
        Set setP0 = kotlin.collections.r.p0(new String[]{"basketball", "nba", "wnba"});
        Companion companion = INSTANCE;
        this.sportsCategories = t7.a.E(new SportsCategoryDef("basketball", "Basketball", setSingleton, setP0, companion.drawable("sports_card_basketball")), new SportsCategoryDef("football", "Football", Collections.singleton("sports_football"), kotlin.collections.r.p0(new String[]{"football", "soccer"}), companion.drawable("sports_card_football")), new SportsCategoryDef("american-football", "American Football", Collections.singleton("sports_american_football"), kotlin.collections.r.p0(new String[]{"american football", "nfl"}), companion.drawable("sports_card_american_football")), new SportsCategoryDef("tennis", "Tennis", Collections.singleton("sports_tennis"), kotlin.collections.r.p0(new String[]{"tennis", "atp", "wta"}), companion.drawable("sports_card_tennis")), new SportsCategoryDef("motor-sports", "Motor Sports", Collections.singleton("sports_motor_sports"), kotlin.collections.r.p0(new String[]{"motorsport", "motor sports", "motor_sports", "motor-sports", "formula", "f1", "racing", "motogp", "nascar"}), companion.drawable("sports_card_motor_sports")), new SportsCategoryDef("rugby", "Rugby", Collections.singleton("sports_rugby"), Collections.singleton("rugby"), companion.drawable("sports_card_rugby")), new SportsCategoryDef("hockey", "Hockey", Collections.singleton("sports_hockey"), kotlin.collections.r.p0(new String[]{"hockey", "nhl"}), companion.drawable("sports_card_hockey")), new SportsCategoryDef("baseball", "Baseball", Collections.singleton("sports_baseball"), kotlin.collections.r.p0(new String[]{"baseball", "mlb"}), companion.drawable("sports_card_baseball")), new SportsCategoryDef("fight", "Fight", Collections.singleton("sports_fight"), kotlin.collections.r.p0(new String[]{"boxing", "ufc", "mma", "combat", "fight"}), companion.drawable("sports_card_fight")), new SportsCategoryDef("golf", "Golf", Collections.singleton("sports_golf"), Collections.singleton("golf"), companion.drawable("sports_card_golf")), new SportsCategoryDef("cricket", "Cricket", Collections.singleton("sports_cricket"), Collections.singleton("cricket"), companion.drawable("sports_card_cricket")), new SportsCategoryDef("darts", "Darts", Collections.singleton("sports_darts"), Collections.singleton("darts"), companion.drawable("sports_card_darts")), new SportsCategoryDef("billiards", "Billiards", Collections.singleton("sports_billiards"), kotlin.collections.r.p0(new String[]{"billiards", "snooker", "pool"}), companion.drawable("sports_card_billiards")), new SportsCategoryDef("afl", "AFL", Collections.singleton("sports_afl"), kotlin.collections.r.p0(new String[]{"afl", "aussie rules", "australian football"}), companion.drawable("sports_card_afl")), new SportsCategoryDef("other", "Other", Collections.singleton("sports_other"), Collections.singleton("other"), companion.drawable("sports_card_other")));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x001f A[PHI: r0
      0x001f: PHI (r0v1 java.lang.String) = (r0v0 java.lang.String), (r0v4 java.lang.String) binds: [B:3:0x0007, B:8:0x001c] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String addonBaseUrl(com.arflix.tv.data.model.Addon r4) {
        /*
            r3 = this;
            java.lang.String r0 = r4.getTransportUrl()
            java.lang.String r1 = "/"
            r2 = 0
            if (r0 != 0) goto L1f
            java.lang.String r4 = r4.getUrl()
            if (r4 == 0) goto L1b
            java.lang.String r0 = "/manifest.json"
            java.lang.String r4 = kotlin.text.o.s0(r4, r0)
            java.lang.String r4 = kotlin.text.o.s0(r4, r1)
            r0 = r4
            goto L1c
        L1b:
            r0 = r2
        L1c:
            if (r0 != 0) goto L1f
            goto L32
        L1f:
            java.lang.CharSequence r4 = kotlin.text.o.L0(r0)
            java.lang.String r4 = r4.toString()
            java.lang.String r4 = kotlin.text.o.s0(r4, r1)
            boolean r0 = kotlin.text.o.h0(r4)
            if (r0 != 0) goto L32
            return r4
        L32:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.SportsRepository.addonBaseUrl(com.arflix.tv.data.model.Addon):java.lang.String");
    }

    public static /* synthetic */ Object buildHomeRows$default(SportsRepository sportsRepository, List list, String str, d7.d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            str = null;
        }
        return sportsRepository.buildHomeRows(list, str, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Category> buildLockedRows() {
        return t7.a.E(sportsCategoryRow$default(this, true, null, 2, null), new Category(SportsAddonCapabilities.POPULAR_LIVE_TV_ROW_ID, "Popular Live Sports", Collections.singletonList(placeholderItem("locked", "Add Sports Live TV Addon", "Required for playback", "Install a sports live TV addon before ARVIO can show or play live sports events.", "LOCKED"))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<AddonCatalog> candidateCatalogs(Addon addon, String selectedSportId) {
        SportsCategoryDef sportsCategoryDef;
        Object next;
        if (selectedSportId != null) {
            Iterator<T> it = this.sportsCategories.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (kotlin.jvm.internal.p.a(((SportsCategoryDef) next).getId(), selectedSportId)) {
                    break;
                }
            }
            sportsCategoryDef = (SportsCategoryDef) next;
        } else {
            sportsCategoryDef = null;
        }
        AddonManifest manifest = addon.getManifest();
        List<AddonCatalog> catalogs = manifest != null ? manifest.getCatalogs() : null;
        List<AddonCatalog> list = kotlin.collections.z.f19728i;
        if (catalogs == null) {
            catalogs = list;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : catalogs) {
            AddonCatalog addonCatalog = (AddonCatalog) obj;
            if (sportsCategoryDef != null ? matchesSport(addonCatalog, sportsCategoryDef) : isPopularLiveCatalog(addonCatalog)) {
                arrayList.add(obj);
            }
        }
        final Comparator comparator = new Comparator() { // from class: com.arflix.tv.data.repository.SportsRepository$candidateCatalogs$$inlined$compareByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                AddonCatalog addonCatalog2 = (AddonCatalog) t10;
                AddonCatalog addonCatalog3 = (AddonCatalog) t2;
                return r0.e(Boolean.valueOf(kotlin.text.o.T(this.this$0.text(addonCatalog2), "popular", false) || kotlin.text.o.T(this.this$0.text(addonCatalog2), "live", false)), Boolean.valueOf(kotlin.text.o.T(this.this$0.text(addonCatalog3), "popular", false) || kotlin.text.o.T(this.this$0.text(addonCatalog3), "live", false)));
            }
        };
        List<AddonCatalog> listW0 = kotlin.collections.x.W0(arrayList, new Comparator() { // from class: com.arflix.tv.data.repository.SportsRepository$candidateCatalogs$$inlined$thenBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                int iCompare = comparator.compare(t2, t10);
                if (iCompare != 0) {
                    return iCompare;
                }
                AddonCatalog addonCatalog2 = (AddonCatalog) t2;
                String name = addonCatalog2.getName();
                if (kotlin.text.o.h0(name)) {
                    name = addonCatalog2.getId();
                }
                AddonCatalog addonCatalog3 = (AddonCatalog) t10;
                String name2 = addonCatalog3.getName();
                if (kotlin.text.o.h0(name2)) {
                    name2 = addonCatalog3.getId();
                }
                return r0.e(name, name2);
            }
        });
        if (!listW0.isEmpty()) {
            return listW0;
        }
        AddonManifest manifest2 = addon.getManifest();
        List<AddonCatalog> catalogs2 = manifest2 != null ? manifest2.getCatalogs() : null;
        if (catalogs2 != null) {
            list = catalogs2;
        }
        SportsAddonCapabilities sportsAddonCapabilities = SportsAddonCapabilities.INSTANCE;
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : list) {
            if (sportsAddonCapabilities.isSportsCatalog((AddonCatalog) obj2)) {
                arrayList2.add(obj2);
            }
        }
        return arrayList2;
    }

    private final String cleanSportsDescription(String value) {
        List listN0 = kotlin.text.o.n0(value);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listN0) {
            String lowerCase = ((String) obj).toLowerCase(Locale.US);
            if (!kotlin.text.o.T(lowerCase, " utc", false) || (!kotlin.text.o.T(lowerCase, " jan ", false) && !kotlin.text.o.T(lowerCase, " feb ", false) && !kotlin.text.o.T(lowerCase, " mar ", false) && !kotlin.text.o.T(lowerCase, " apr ", false) && !kotlin.text.o.T(lowerCase, " may ", false) && !kotlin.text.o.T(lowerCase, " jun ", false) && !kotlin.text.o.T(lowerCase, " jul ", false) && !kotlin.text.o.T(lowerCase, " aug ", false) && !kotlin.text.o.T(lowerCase, " sep ", false) && !kotlin.text.o.T(lowerCase, " oct ", false) && !kotlin.text.o.T(lowerCase, " nov ", false) && !kotlin.text.o.T(lowerCase, " dec ", false))) {
                arrayList.add(obj);
            }
        }
        return kotlin.text.o.L0(kotlin.collections.x.u0(arrayList, "\n", null, null, null, 62)).toString();
    }

    private final String decodeStatusPart(String value) {
        return URLDecoder.decode(value, "UTF-8");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String encodePathSegment(String value) {
        return kotlin.text.u.O(URLEncoder.encode(value, "UTF-8"), "+", "%20", false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String encodeStatusPart(String value) {
        return kotlin.text.u.O(URLEncoder.encode(value, "UTF-8"), "+", "%20", false);
    }

    private final String eventStatus(String addonId, String type, String eventId) {
        return androidx.compose.material3.d.C(SportsAddonCapabilities.SPORTS_EVENT_STATUS_PREFIX, kotlin.collections.x.u0(t7.a.E(addonId, type, eventId), "|", null, null, new d0(this, 5), 30));
    }

    private final boolean isCurrentlyLive(StremioMetaPreview meta) {
        return kotlin.text.o.T(kotlin.collections.x.u0(kotlin.collections.x.k0(t7.a.E(meta.getReleaseInfo(), meta.getDescription())), " ", null, null, null, 62).toLowerCase(Locale.US), "live", false);
    }

    private final boolean isNativePlayableSportsUrl(String rawUrl) {
        String lowerCase = kotlin.text.o.L0(rawUrl).toString().toLowerCase(Locale.US);
        if (kotlin.text.u.P(lowerCase, "http://", false) || kotlin.text.u.P(lowerCase, "https://", false)) {
            if (kotlin.text.o.T(lowerCase, ".m3u8", false) || kotlin.text.o.T(lowerCase, "/hls", false) || kotlin.text.o.T(lowerCase, "format=hls", false)) {
                return true;
            }
            if (kotlin.text.o.T(lowerCase, "highfly", false) && kotlin.text.o.T(lowerCase, "/playlist/", false)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00a4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final boolean isPlayableSportsStream(com.arflix.tv.data.api.StremioStream r10, java.lang.String r11) {
        /*
            r9 = this;
            java.lang.CharSequence r0 = kotlin.text.o.L0(r11)
            java.lang.String r0 = r0.toString()
            java.util.Locale r1 = java.util.Locale.US
            java.lang.String r0 = r0.toLowerCase(r1)
            java.lang.String r2 = r10.getName()
            java.lang.String r3 = r10.getTitle()
            java.lang.String r4 = r10.getDescription()
            java.lang.String[] r2 = new java.lang.String[]{r2, r3, r4, r11}
            java.util.List r2 = t7.a.E(r2)
            java.util.ArrayList r3 = kotlin.collections.x.k0(r2)
            r7 = 0
            r8 = 62
            java.lang.String r4 = " "
            r5 = 0
            r6 = 0
            java.lang.String r2 = kotlin.collections.x.u0(r3, r4, r5, r6, r7, r8)
            java.lang.String r1 = r2.toLowerCase(r1)
            int r2 = r0.hashCode()
            r3 = 0
            switch(r2) {
                case -1632447086: goto L59;
                case -760432549: goto L50;
                case -247021602: goto L47;
                case 1244095381: goto L3e;
                default: goto L3d;
            }
        L3d:
            goto L61
        L3e:
            java.lang.String r2 = "http://www.google.com"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto Lb0
            goto L61
        L47:
            java.lang.String r2 = "http://google.com"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L61
            goto Lb0
        L50:
            java.lang.String r2 = "https://google.com"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto Lb0
            goto L61
        L59:
            java.lang.String r2 = "https://www.google.com"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto Lb0
        L61:
            java.lang.String r0 = "stream has ended"
            java.lang.String r2 = "not available"
            java.lang.String r4 = "upgrade to watch"
            java.lang.String r5 = "premium"
            java.lang.String r6 = "unavailable"
            java.lang.String[] r0 = new java.lang.String[]{r4, r5, r6, r0, r2}
            java.util.List r0 = t7.a.E(r0)
            boolean r2 = r0.isEmpty()
            if (r2 == 0) goto L7c
            goto L93
        L7c:
            java.util.Iterator r0 = r0.iterator()
        L80:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L93
            java.lang.Object r2 = r0.next()
            java.lang.String r2 = (java.lang.String) r2
            boolean r2 = kotlin.text.o.T(r1, r2, r3)
            if (r2 == 0) goto L80
            goto Lb0
        L93:
            com.arflix.tv.data.api.StreamBehaviorHints r10 = r10.getBehaviorHints()
            if (r10 == 0) goto La4
            java.lang.Boolean r10 = r10.getNotWebReady()
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            boolean r10 = kotlin.jvm.internal.p.a(r10, r0)
            goto La5
        La4:
            r10 = r3
        La5:
            if (r10 == 0) goto Lae
            boolean r10 = r9.isNativePlayableSportsUrl(r11)
            if (r10 != 0) goto Lae
            goto Lb0
        Lae:
            r10 = 1
            return r10
        Lb0:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.SportsRepository.isPlayableSportsStream(com.arflix.tv.data.api.StremioStream, java.lang.String):boolean");
    }

    private final boolean isPopularLiveCatalog(AddonCatalog addonCatalog) {
        String strText = text(addonCatalog);
        return kotlin.text.o.T(strText, "live", false) || kotlin.text.o.T(strText, "popular", false) || kotlin.text.u.L(addonCatalog.getId(), "sports_live", true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadCatalogMetas(com.arflix.tv.data.model.Addon r13, com.arflix.tv.data.model.AddonCatalog r14, d7.d<? super java.util.List<com.arflix.tv.data.api.StremioMetaPreview>> r15) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 269
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.SportsRepository.loadCatalogMetas(com.arflix.tv.data.model.Addon, com.arflix.tv.data.model.AddonCatalog, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadPopularLiveTvCategory(java.util.List<com.arflix.tv.data.model.Addon> r9, java.lang.String r10, d7.d<? super com.arflix.tv.data.model.Category> r11) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 201
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.SportsRepository.loadPopularLiveTvCategory(java.util.List, java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0062 A[PHI: r9 r10 r11 r12 r13
      0x0062: PHI (r9v4 java.util.Iterator<com.arflix.tv.data.model.Addon>) = (r9v1 java.util.Iterator<com.arflix.tv.data.model.Addon>), (r9v7 java.util.Iterator<com.arflix.tv.data.model.Addon>) binds: [B:18:0x0059, B:36:0x00ea] A[DONT_GENERATE, DONT_INLINE]
      0x0062: PHI (r10v1 java.lang.String) = (r10v0 java.lang.String), (r10v4 java.lang.String) binds: [B:18:0x0059, B:36:0x00ea] A[DONT_GENERATE, DONT_INLINE]
      0x0062: PHI (r11v2 int) = (r11v0 int), (r11v3 int) binds: [B:18:0x0059, B:36:0x00ea] A[DONT_GENERATE, DONT_INLINE]
      0x0062: PHI (r12v1 boolean) = (r12v0 boolean), (r12v3 boolean) binds: [B:18:0x0059, B:36:0x00ea] A[DONT_GENERATE, DONT_INLINE]
      0x0062: PHI (r13v4 java.util.List) = (r13v2 java.util.List), (r13v5 java.util.List) binds: [B:18:0x0059, B:36:0x00ea] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0082 A[PHI: r1 r3 r4 r5 r9 r10 r12
      0x0082: PHI (r1v5 com.arflix.tv.data.model.Addon) = (r1v3 com.arflix.tv.data.model.Addon), (r1v6 com.arflix.tv.data.model.Addon) binds: [B:21:0x0068, B:34:0x00df] A[DONT_GENERATE, DONT_INLINE]
      0x0082: PHI (r3v5 java.util.Iterator<com.arflix.tv.data.model.Addon>) = (r3v3 java.util.Iterator<com.arflix.tv.data.model.Addon>), (r3v6 java.util.Iterator<com.arflix.tv.data.model.Addon>) binds: [B:21:0x0068, B:34:0x00df] A[DONT_GENERATE, DONT_INLINE]
      0x0082: PHI (r4v4 java.util.List) = (r4v2 java.util.List), (r4v5 java.util.List) binds: [B:21:0x0068, B:34:0x00df] A[DONT_GENERATE, DONT_INLINE]
      0x0082: PHI (r5v2 java.lang.String) = (r5v0 java.lang.String), (r5v3 java.lang.String) binds: [B:21:0x0068, B:34:0x00df] A[DONT_GENERATE, DONT_INLINE]
      0x0082: PHI (r9v8 boolean) = (r9v5 boolean), (r9v9 boolean) binds: [B:21:0x0068, B:34:0x00df] A[DONT_GENERATE, DONT_INLINE]
      0x0082: PHI (r10v5 int) = (r10v2 int), (r10v6 int) binds: [B:21:0x0068, B:34:0x00df] A[DONT_GENERATE, DONT_INLINE]
      0x0082: PHI (r12v4 java.util.Iterator) = (r12v2 java.util.Iterator), (r12v5 java.util.Iterator) binds: [B:21:0x0068, B:34:0x00df] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0068 -> B:22:0x0082). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x00ab -> B:27:0x00ae). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadSportItems(java.util.List<com.arflix.tv.data.model.Addon> r9, java.lang.String r10, int r11, boolean r12, d7.d<? super java.util.List<com.arflix.tv.data.model.MediaItem>> r13) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 241
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.SportsRepository.loadSportItems(java.util.List, java.lang.String, int, boolean, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean loadSportItems$lambda$0(boolean z, SportsRepository sportsRepository, StremioMetaPreview stremioMetaPreview) {
        return !z || sportsRepository.isCurrentlyLive(stremioMetaPreview);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object loadSportsCategoryArtworkOverrides(List<Addon> list, d7.d<? super Map<String, String>> dVar) {
        return ka.l0.c(new C09732(list, this, null), dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final boolean matchesSport(com.arflix.tv.data.model.AddonCatalog r6, com.arflix.tv.data.repository.SportsRepository.SportsCategoryDef r7) {
        /*
            r5 = this;
            java.lang.String r0 = r5.text(r6)
            java.util.Set r1 = r7.getCatalogIds()
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            boolean r2 = r1 instanceof java.util.Collection
            r3 = 1
            if (r2 == 0) goto L19
            r2 = r1
            java.util.Collection r2 = (java.util.Collection) r2
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L19
            goto L34
        L19:
            java.util.Iterator r1 = r1.iterator()
        L1d:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L34
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r4 = r6.getId()
            boolean r2 = kotlin.text.u.L(r2, r4, r3)
            if (r2 == 0) goto L1d
            goto L74
        L34:
            java.lang.String r6 = r7.getId()
            java.lang.String r1 = "football"
            boolean r6 = kotlin.jvm.internal.p.a(r6, r1)
            r1 = 0
            if (r6 == 0) goto L4a
            java.lang.String r6 = "american football"
            boolean r6 = kotlin.text.o.T(r0, r6, r1)
            if (r6 == 0) goto L4a
            goto L75
        L4a:
            java.util.Set r6 = r7.getKeywords()
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            boolean r7 = r6 instanceof java.util.Collection
            if (r7 == 0) goto L5e
            r7 = r6
            java.util.Collection r7 = (java.util.Collection) r7
            boolean r7 = r7.isEmpty()
            if (r7 == 0) goto L5e
            goto L75
        L5e:
            java.util.Iterator r6 = r6.iterator()
        L62:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L75
            java.lang.Object r7 = r6.next()
            java.lang.String r7 = (java.lang.String) r7
            boolean r7 = kotlin.text.o.T(r0, r7, r1)
            if (r7 == 0) goto L62
        L74:
            return r3
        L75:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.SportsRepository.matchesSport(com.arflix.tv.data.model.AddonCatalog, com.arflix.tv.data.repository.SportsRepository$SportsCategoryDef):boolean");
    }

    private final Map<String, String> mergeHeaders(Map<String, String>... maps) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map<String, String> map : maps) {
            if (map == null) {
                map = kotlin.collections.a0.f19683i;
            }
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (!kotlin.text.o.h0(key) && !kotlin.text.o.h0(value)) {
                    linkedHashMap.put(key, value);
                }
            }
        }
        return linkedHashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ParsedEventStatus parseEventStatus(String status) {
        List listY0 = kotlin.text.o.y0(kotlin.text.o.r0(status, SportsAddonCapabilities.SPORTS_EVENT_STATUS_PREFIX), new String[]{"|"}, 0, 6);
        if (listY0.size() != 3) {
            return null;
        }
        return new ParsedEventStatus(decodeStatusPart((String) listY0.get(0)), decodeStatusPart((String) listY0.get(1)), decodeStatusPart((String) listY0.get(2)));
    }

    private final long parseSizeToBytes(String sizeStr) {
        kotlin.text.k kVarB;
        Double dG;
        double d4;
        if (kotlin.text.o.h0(sizeStr) || (kVarB = kotlin.text.m.b(SportsRepoRegexes.INSTANCE.getSIZE_REGEX(), kotlin.text.u.O(sizeStr, ",", ".", false))) == null || (dG = kotlin.text.t.G((String) ((kotlin.collections.n0) kVarB.b()).get(1))) == null) {
            return 0L;
        }
        double dDoubleValue = dG.doubleValue();
        String upperCase = ((String) ((kotlin.collections.n0) kVarB.b()).get(2)).toUpperCase(Locale.US);
        int iHashCode = upperCase.hashCode();
        if (iHashCode != 2267) {
            if (iHashCode == 2391) {
                if (!upperCase.equals("KB")) {
                    return 0L;
                }
                d4 = 1024;
                return (long) (dDoubleValue * d4);
            }
            if (iHashCode == 2453) {
                if (!upperCase.equals("MB")) {
                    return 0L;
                }
                d4 = 1024;
                dDoubleValue *= d4;
                return (long) (dDoubleValue * d4);
            }
            if (iHashCode != 2670 || !upperCase.equals("TB")) {
                return 0L;
            }
            d4 = 1024;
            dDoubleValue *= d4;
        } else {
            if (!upperCase.equals("GB")) {
                return 0L;
            }
            d4 = 1024;
        }
        dDoubleValue *= d4;
        dDoubleValue *= d4;
        return (long) (dDoubleValue * d4);
    }

    private final MediaItem placeholderItem(String key, String title, String subtitle, String overview, String badge) {
        return new MediaItem(SportsAddonCapabilities.INSTANCE.sportsSyntheticId(SportsAddonCapabilities.SPORTS_LOCKED_STATUS_PREFIX + key), title, subtitle, overview, null, null, null, null, null, null, MediaType.TV, null, null, 0, false, null, badge, null, null, null, false, null, null, null, null, null, androidx.compose.material3.d.C(SportsAddonCapabilities.SPORTS_LOCKED_STATUS_PREFIX, key), null, null, false, null, 0.0f, 0L, 0, false, null, false, false, null, null, null, null, -67175440, AnalyticsListener.EVENT_DRM_KEYS_LOADED, null);
    }

    public static /* synthetic */ MediaItem placeholderItem$default(SportsRepository sportsRepository, String str, String str2, String str3, String str4, String str5, int i10, Object obj) {
        if ((i10 & 16) != 0) {
            str5 = null;
        }
        return sportsRepository.placeholderItem(str, str2, str3, str4, str5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Addon> prioritizedSportsAddons(List<Addon> list) {
        final Comparator comparator = new Comparator() { // from class: com.arflix.tv.data.repository.SportsRepository$prioritizedSportsAddons$$inlined$compareBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                Integer num = 1;
                Addon addon = (Addon) t2;
                String strU0 = kotlin.collections.x.u0(kotlin.collections.x.k0(t7.a.E(addon.getId(), addon.getName(), addon.getUrl(), addon.getTransportUrl())), " ", null, null, null, 62);
                Locale locale = Locale.US;
                String lowerCase = strU0.toLowerCase(locale);
                Integer num2 = (kotlin.text.o.T(lowerCase, "sports.fly", false) || kotlin.text.o.T(lowerCase, "highfly", false)) ? 0 : kotlin.text.o.T(lowerCase, SportsAddonCapabilities.SPORTS_CATEGORY_ROW_ID, false) ? num : 2;
                Addon addon2 = (Addon) t10;
                String lowerCase2 = kotlin.collections.x.u0(kotlin.collections.x.k0(t7.a.E(addon2.getId(), addon2.getName(), addon2.getUrl(), addon2.getTransportUrl())), " ", null, null, null, 62).toLowerCase(locale);
                if (kotlin.text.o.T(lowerCase2, "sports.fly", false) || kotlin.text.o.T(lowerCase2, "highfly", false)) {
                    num = 0;
                } else if (!kotlin.text.o.T(lowerCase2, SportsAddonCapabilities.SPORTS_CATEGORY_ROW_ID, false)) {
                    num = 2;
                }
                return r0.e(num2, num);
            }
        };
        return kotlin.collections.x.W0(list, new Comparator() { // from class: com.arflix.tv.data.repository.SportsRepository$prioritizedSportsAddons$$inlined$thenBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                int iCompare = comparator.compare(t2, t10);
                if (iCompare != 0) {
                    return iCompare;
                }
                Addon addon = (Addon) t2;
                String name = addon.getName();
                if (kotlin.text.o.h0(name)) {
                    name = addon.getId();
                }
                Addon addon2 = (Addon) t10;
                String name2 = addon2.getName();
                if (kotlin.text.o.h0(name2)) {
                    name2 = addon2.getId();
                }
                return r0.e(name, name2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Category sportsCategoryRow(boolean locked, Map<String, String> artworkOverrides) {
        List<SportsCategoryDef> list = this.sportsCategories;
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
        for (SportsCategoryDef sportsCategoryDef : list) {
            String strC = locked ? androidx.compose.material3.d.C(SportsAddonCapabilities.SPORTS_LOCKED_STATUS_PREFIX, sportsCategoryDef.getId()) : androidx.compose.material3.d.C("collection:", SportsAddonCapabilities.INSTANCE.sportsCollectionCatalogId(sportsCategoryDef.getId()));
            String artworkUrl = artworkOverrides.get(sportsCategoryDef.getId());
            if (artworkUrl == null) {
                artworkUrl = sportsCategoryDef.getArtworkUrl();
            }
            String str = artworkUrl;
            arrayList.add(new MediaItem(SportsAddonCapabilities.INSTANCE.sportsSyntheticId(strC), sportsCategoryDef.getTitle(), "Sports", a0.c.l("Browse ", sportsCategoryDef.getTitle().toLowerCase(Locale.US), " events from your installed sports live TV addon."), null, null, null, null, null, null, MediaType.TV, str, str, 0, false, null, "SPORT", null, null, null, false, null, null, null, null, null, strC, locked ? null : CollectionGroupKind.SERVICE, locked ? null : CollectionTileShape.LANDSCAPE, false, null, 0.0f, 0L, 0, false, null, false, false, null, null, null, null, -469834768, AnalyticsListener.EVENT_DRM_KEYS_LOADED, null));
        }
        return new Category(SportsAddonCapabilities.SPORTS_CATEGORY_ROW_ID, "Sports", arrayList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Category sportsCategoryRow$default(SportsRepository sportsRepository, boolean z, Map map, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        if ((i10 & 2) != 0) {
            map = kotlin.collections.a0.f19683i;
        }
        return sportsRepository.sportsCategoryRow(z, map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String text(AddonCatalog addonCatalog) {
        String type = addonCatalog.getType();
        String id = addonCatalog.getId();
        String name = addonCatalog.getName();
        List<String> genres = addonCatalog.getGenres();
        if (genres == null) {
            genres = kotlin.collections.z.f19728i;
        }
        return kotlin.collections.x.u0(t7.a.E(type, id, name, kotlin.collections.x.u0(genres, " ", null, null, null, 62)), " ", null, null, null, 62).toLowerCase(Locale.US);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final com.arflix.tv.data.model.MediaItem toMediaItem(com.arflix.tv.data.api.StremioMetaPreview r53, com.arflix.tv.data.model.Addon r54, com.arflix.tv.data.model.AddonCatalog r55) {
        /*
            Method dump skipped, instruction units count: 289
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.SportsRepository.toMediaItem(com.arflix.tv.data.api.StremioMetaPreview, com.arflix.tv.data.model.Addon, com.arflix.tv.data.model.AddonCatalog):com.arflix.tv.data.model.MediaItem");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0112  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.arflix.tv.data.model.StreamSource toStreamSource(com.arflix.tv.data.api.StremioStream r33, com.arflix.tv.data.model.Addon r34, java.lang.String r35) {
        /*
            Method dump skipped, instruction units count: 385
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.SportsRepository.toStreamSource(com.arflix.tv.data.api.StremioStream, com.arflix.tv.data.model.Addon, java.lang.String):com.arflix.tv.data.model.StreamSource");
    }

    public final Object buildHomeRows(List<Addon> list, String str, d7.d<? super List<Category>> dVar) {
        return ka.m0.y(x0.f19655d, new AnonymousClass2(list, str, null), dVar);
    }

    public final List<Category> defaultHomeRows() {
        return buildLockedRows();
    }

    public final Object loadSportsCollectionPage(String str, int i10, int i11, d7.d<? super MediaRepository.CategoryPageResult> dVar) {
        return ka.m0.y(x0.f19655d, new C09742(str, this, i11, i10, null), dVar);
    }

    public final List<Category> mergeSportsRows(List<Category> categories, List<Category> sportsRows) {
        if (sportsRows.isEmpty()) {
            return categories;
        }
        int iQ0 = kotlin.collections.i0.q0(kotlin.collections.s.U(sportsRows, 10));
        if (iQ0 < 16) {
            iQ0 = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iQ0);
        for (Object obj : sportsRows) {
            linkedHashMap.put(((Category) obj).getId(), obj);
        }
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(categories, 10));
        for (Category category : categories) {
            Category category2 = (Category) linkedHashMap.get(category.getId());
            if (category2 != null) {
                category = category2;
            }
            arrayList.add(category);
        }
        return arrayList;
    }

    public final Object resolvePlayback(String str, String str2, d7.d<? super SportsPlayback> dVar) {
        return ka.m0.y(x0.f19655d, new C09752(str, str2, null), dVar);
    }

    public final String selectedSportIdFromStatus(String status) {
        if (!SportsAddonCapabilities.INSTANCE.isSportsCategoryStatus(status) || status == null) {
            return null;
        }
        String strR0 = kotlin.text.o.r0(status, SportsAddonCapabilities.SPORTS_STATUS_PREFIX);
        if (kotlin.text.o.h0(strR0)) {
            return null;
        }
        return strR0;
    }

    public final CatalogConfig sportsCollectionCatalog(String catalogId) {
        Object next;
        String strSportIdFromCollectionCatalogId = SportsAddonCapabilities.INSTANCE.sportIdFromCollectionCatalogId(catalogId);
        if (strSportIdFromCollectionCatalogId == null) {
            return null;
        }
        Iterator<T> it = this.sportsCategories.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (kotlin.jvm.internal.p.a(((SportsCategoryDef) next).getId(), strSportIdFromCollectionCatalogId)) {
                break;
            }
        }
        SportsCategoryDef sportsCategoryDef = (SportsCategoryDef) next;
        if (sportsCategoryDef == null) {
            return null;
        }
        return new CatalogConfig(catalogId, sportsCategoryDef.getTitle(), CatalogSourceType.PREINSTALLED, null, null, true, null, null, null, null, CatalogKind.COLLECTION, CollectionGroupKind.SERVICE, a0.c.l("Live and upcoming ", sportsCategoryDef.getTitle().toLowerCase(Locale.US), " events from your installed sports live TV addon."), null, null, null, null, null, null, CollectionTileShape.LANDSCAPE, false, Collections.singletonList(new CollectionSourceConfig(CollectionSourceKind.CURATED_IDS, "tv", null, null, null, null, null, null, null, null, null, null, kotlin.collections.z.f19728i, null, 12284, null)), null, null, null, 30925784, null);
    }
}
