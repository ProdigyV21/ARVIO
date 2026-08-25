package com.arflix.tv.util;

import androidx.compose.foundation.c;
import androidx.compose.runtime.ComposerKt;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.extractor.ts.TsExtractor;
import com.arflix.tv.BuildConfig;
import com.arflix.tv.data.api.ArmMappingEntry;
import com.arflix.tv.data.api.StreamApi;
import com.arflix.tv.data.api.TmdbApi;
import com.google.android.gms.cast.MediaError;
import d7.d;
import f7.j;
import io.ktor.http.LinkHeader;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import ka.k0;
import ka.m0;
import ka.x0;
import kotlin.Metadata;
import kotlin.collections.h0;
import kotlin.collections.r;
import kotlin.collections.z;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import q7.n;
import ua.e;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Singleton
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0006\b\u0007\u0018\u0000 d2\u00020\u0001:\u0002edB\u0019\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J3\u0010\u000f\u001a\u00020\u000e2\b\u0010\t\u001a\u0004\u0018\u00010\b2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0014\u001a\u0004\u0018\u00010\u00112\u0006\u0010\t\u001a\u00020\bH\u0080@¢\u0006\u0004\b\u0012\u0010\u0013JH\u0010\u001a\u001a\u00020\f2\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\u0015\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\f2\b\u0010\u0018\u001a\u0004\u0018\u00010\b2\b\u0010\u0019\u001a\u0004\u0018\u00010\bH\u0086@¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001c\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u001c\u0010\u001dJ\u0015\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u001e\u0010\u001fJ*\u0010 \u001a\u0004\u0018\u00010\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\bH\u0082@¢\u0006\u0004\b \u0010!J%\u0010$\u001a\u00020\f2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0\n2\u0006\u0010\u0019\u001a\u00020\bH\u0002¢\u0006\u0004\b$\u0010%J&\u0010&\u001a\u00020\f2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0\n2\u0006\u0010\u0019\u001a\u00020\bH\u0082@¢\u0006\u0004\b&\u0010'J*\u0010(\u001a\u0004\u0018\u00010\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\bH\u0082@¢\u0006\u0004\b(\u0010!J*\u0010)\u001a\u0004\u0018\u00010\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\bH\u0082@¢\u0006\u0004\b)\u0010!J \u0010+\u001a\n\u0012\u0004\u0012\u00020*\u0018\u00010\n2\u0006\u0010\t\u001a\u00020\bH\u0082@¢\u0006\u0004\b+\u0010\u0013J4\u0010,\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0015\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\bH\u0082@¢\u0006\u0004\b,\u0010-J\u001a\u0010.\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0015\u001a\u00020\bH\u0082@¢\u0006\u0004\b.\u0010\u0013J4\u0010/\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0016\u001a\u00020\f2\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\bH\u0082@¢\u0006\u0004\b/\u00100J\u001a\u00101\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0016\u001a\u00020\fH\u0082@¢\u0006\u0004\b1\u00102J>\u00105\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b042\u0006\u00103\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\bH\u0082@¢\u0006\u0004\b5\u0010-J6\u00108\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u0001042\u0006\u00106\u001a\u00020\b2\u0006\u00107\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\bH\u0082@¢\u0006\u0004\b8\u0010!J.\u00109\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u0001042\u0006\u00106\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\bH\u0082@¢\u0006\u0004\b9\u0010:J\u0018\u0010;\u001a\u00020\u000e2\u0006\u00103\u001a\u00020\bH\u0082@¢\u0006\u0004\b;\u0010\u0013J\u001a\u0010<\u001a\u0004\u0018\u00010\b2\u0006\u00103\u001a\u00020\bH\u0082@¢\u0006\u0004\b<\u0010\u0013J\u001a\u0010=\u001a\u0004\u0018\u00010\b2\u0006\u00103\u001a\u00020\bH\u0082@¢\u0006\u0004\b=\u0010\u0013J$\u0010>\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0018\u001a\u00020\bH\u0082@¢\u0006\u0004\b>\u0010?J(\u0010C\u001a\u00020B2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010@\u001a\u00020\b2\u0006\u0010A\u001a\u00020\bH\u0082@¢\u0006\u0004\bC\u0010!J'\u0010G\u001a\u00020B\"\u0004\b\u0000\u0010D2\u0010\u0010F\u001a\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030EH\u0002¢\u0006\u0004\bG\u0010HR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010IR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010JR\u0014\u0010K\u001a\u00020\f8\u0002X\u0082D¢\u0006\u0006\n\u0004\bK\u0010LR\u0014\u0010N\u001a\u00020M8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bN\u0010OR\u0014\u0010P\u001a\u00020\b8\u0002X\u0082D¢\u0006\u0006\n\u0004\bP\u0010QR \u0010R\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0E8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bR\u0010SR \u0010T\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\b0E8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bT\u0010SR \u0010U\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0E8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bU\u0010SR \u0010V\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\b0E8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bV\u0010SR\"\u0010W\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b0E8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bW\u0010SR \u0010X\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000e0E8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bX\u0010SR&\u0010Y\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0\n0E8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bY\u0010SR&\u0010[\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020B0Z0E8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b[\u0010SR \u0010\\\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0E8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\\\u0010SR,\u0010^\u001a\u001a\u0012\u0004\u0012\u00020\b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0]0]8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b^\u0010SR2\u0010_\u001a \u0012\u0004\u0012\u00020\b\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\n0]0]8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b_\u0010SR\u001a\u0010a\u001a\b\u0012\u0004\u0012\u00020\b0`8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\ba\u0010bR,\u0010c\u001a\u001a\u0012\u0004\u0012\u00020\b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0]0]8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bc\u0010S¨\u0006f"}, d2 = {"Lcom/arflix/tv/util/AnimeMapper;", "", "Lcom/arflix/tv/data/api/StreamApi;", "streamApi", "Lcom/arflix/tv/data/api/TmdbApi;", "tmdbApi", "<init>", "(Lcom/arflix/tv/data/api/StreamApi;Lcom/arflix/tv/data/api/TmdbApi;)V", "", "tmdbId", "", "genreIds", "", "originalLanguage", "", "isAnimeContent", "(Ljava/lang/Integer;Ljava/util/List;Ljava/lang/String;)Z", "Lcom/arflix/tv/util/AnimeSeasonStructure;", "resolveAnimeSeasonStructure$app_playRelease", "(ILd7/d;)Ljava/lang/Object;", "resolveAnimeSeasonStructure", "tvdbId", LinkHeader.Parameters.Title, "imdbId", "season", "episode", "resolveAnimeEpisodeQuery", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ld7/d;)Ljava/lang/Object;", "getKitsuId", "(I)Ljava/lang/Integer;", "isAnime", "(I)Z", "resolveTier1PerSeason", "(IIILd7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/util/AnimeMapper$SeasonSegment;", "segments", "resolveSegmentedEpisode", "(Ljava/util/List;I)Ljava/lang/String;", "resolveDynamicSegmentedEpisode", "(Ljava/util/List;ILd7/d;)Ljava/lang/Object;", "resolveTier2Absolute", "resolveTierArm", "Lcom/arflix/tv/data/api/ArmMappingEntry;", "fetchArmMapping", "resolveTier3Tvdb", "(ILjava/lang/Integer;IILd7/d;)Ljava/lang/Object;", "resolveKitsuIdFromTvdb", "resolveTier4TitleSearch", "(Ljava/lang/String;Ljava/lang/Integer;IILd7/d;)Ljava/lang/Object;", "searchKitsuByTitle", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "kitsuId", "Lx6/x;", "resolveValidatedEpisode", "startKitsuId", "targetSeason", "walkSequelsToSeason", "walkSequelsForEpisode", "(IILd7/d;)Ljava/lang/Object;", "hasKitsuSequel", "getKitsuSequelId", "getKitsuEpisodeCount", "calculateTmdbSeasonOffset", "(Ljava/lang/Integer;ILd7/d;)Ljava/lang/Object;", "startSeason", "endSeason", "Lx6/t0;", "ensureSeasonEpisodeCountsCached", "K", "", "cache", "evictIfNeeded", "(Ljava/util/Map;)V", "Lcom/arflix/tv/data/api/StreamApi;", "Lcom/arflix/tv/data/api/TmdbApi;", "TAG", "Ljava/lang/String;", "Lua/a;", "cacheMutex", "Lua/a;", "MAX_CACHE_SIZE", "I", "tvdbToKitsuCache", "Ljava/util/Map;", "titleToKitsuCache", "episodeCountCache", "tmdbSeasonEpCountCache", "sequelCache", "hasSequelCache", "armTmdbCache", "Lka/r;", "inFlightRequests", "tmdbToKitsuMap", "", "perSeasonKitsuMap", "seasonSegments", "", "absoluteNumberingAnime", "Ljava/util/Set;", "animeSeasonOffsets", "Companion", "SeasonSegment", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AnimeMapper {
    private final StreamApi streamApi;
    private final TmdbApi tmdbApi;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final Set<Integer> staticTmdbIds = r.p0(new Integer[]{37854, 46260, 31910, 1429, 65930, 85937, 95479, 114410, 202250, 30984, 13916, 31911, 62085, 1104, 1043, 12609, 68727, 60574, 45782, 46298, 69122, 101280, 92320, 71448, 67133, 73223, 127532, 209867, 203737, 127064, 135157, 210232, 225439});
    private final String TAG = "AnimeMapper";
    private final ua.a cacheMutex = e.a();
    private final int MAX_CACHE_SIZE = 500;
    private final Map<Integer, Integer> tvdbToKitsuCache = new LinkedHashMap();
    private final Map<String, Integer> titleToKitsuCache = new LinkedHashMap();
    private final Map<Integer, Integer> episodeCountCache = new LinkedHashMap();
    private final Map<String, Integer> tmdbSeasonEpCountCache = new LinkedHashMap();
    private final Map<Integer, Integer> sequelCache = new LinkedHashMap();
    private final Map<Integer, Boolean> hasSequelCache = new LinkedHashMap();
    private final Map<Integer, List<ArmMappingEntry>> armTmdbCache = new LinkedHashMap();
    private final Map<Integer, ka.r<t0>> inFlightRequests = new LinkedHashMap();
    private final Map<Integer, Integer> tmdbToKitsuMap = h0.u0(new x(37854, 12), new x(46260, 40), new x(31910, 1555), new x(1429, 7442), new x(65930, 11469), new x(85937, 38000), new x(95479, 42765), new x(114410, 43806), new x(202250, 45398), new x(30984, 6), new x(13916, 1376), new x(31911, 4595), new x(62085, 5646), new x(1104, 1415), new x(1043, 1), new x(12609, 214), new x(68727, 12243), new x(60574, 8271), new x(45782, 6589), new x(46298, 6448), new x(69122, 11696), new x(101280, 40046), new x(92320, 41312), new x(71448, 11209), new x(67133, 9965), new x(73223, 13932), new x(127532, 47058), new x(209867, 46474), new x(203737, 47997), new x(127064, 44973), new x(135157, 44196), new x(210232, 48269), new x(225439, 46300), new x(154526, 45713));
    private final Map<Integer, Map<Integer, Integer>> perSeasonKitsuMap = h0.t0(new x(95479, h0.t0(new x(1, 42765), new x(2, 45857), new x(3, 48363))), new x(1429, h0.t0(new x(1, 7442), new x(2, 12960), new x(3, 13569), new x(4, 43469))), new x(85937, h0.t0(new x(1, 38000), new x(2, 44979), new x(3, 46567), new x(4, 48513))), new x(65930, h0.t0(new x(1, 11469), new x(2, 12469), new x(3, 13881), new x(4, 41524), new x(5, 43108), new x(6, 45904), new x(7, 48058))), new x(101280, h0.t0(new x(1, 40046), new x(2, 46262))), new x(69122, h0.t0(new x(1, 11696), new x(2, 13735), new x(3, 45702))), new x(203737, h0.t0(new x(1, 47997), new x(2, 48572))), new x(127532, h0.t0(new x(1, 47058), new x(2, 48693))), new x(202250, h0.t0(new x(1, 45398), new x(2, 46873), new x(3, 48939))), new x(127064, h0.t0(new x(1, 44973), new x(2, 47245))), new x(209867, h0.t0(new x(1, 46474), new x(2, 49240))), new x(210232, h0.t0(new x(1, 48269), new x(2, 49425))), new x(225439, h0.t0(new x(1, 46300), new x(2, 48994))), new x(92320, h0.t0(new x(1, 41312), new x(2, 42220))), new x(71448, h0.t0(new x(1, 11209), new x(2, 42198), new x(3, 47235))), new x(67133, h0.t0(new x(1, 9965), new x(2, 13237), new x(3, 41174), new x(4, 44529))), new x(60574, h0.t0(new x(1, 8271), new x(2, 9135), new x(3, 13929), new x(4, 41359))), new x(45782, h0.t0(new x(1, 6589), new x(2, 8174), new x(3, 13893), new x(4, 42213))), new x(62085, h0.t0(new x(1, 5646), new x(2, 10788))), new x(1104, h0.t0(new x(1, 1415), new x(2, 2634))), new x(154526, h0.t0(new x(1, 45713), new x(2, 48337), new x(3, 49441))));
    private final Map<Integer, Map<Integer, List<SeasonSegment>>> seasonSegments = h0.t0(new x(1429, Collections.singletonMap(3, t7.a.E(new SeasonSegment(13569, 12), new SeasonSegment(41370, 10)))), new x(71448, Collections.singletonMap(2, t7.a.E(new SeasonSegment(42198, 13), new SeasonSegment(43247, 12)))), new x(45782, Collections.singletonMap(4, t7.a.E(new SeasonSegment(42213, 12), new SeasonSegment(42927, 11)))), new x(202250, Collections.singletonMap(1, t7.a.E(new SeasonSegment(45398, 12), new SeasonSegment(45619, 13)))), new x(154526, Collections.singletonMap(1, t7.a.E(new SeasonSegment(45713, 0), new SeasonSegment(48337, 0), new SeasonSegment(49441, 0)))));
    private final Set<Integer> absoluteNumberingAnime = r.p0(new Integer[]{37854, 46260, 31910, 12609, 68727, 46298, 73223, 30984, 13916, 31911});
    private final Map<Integer, Map<Integer, Integer>> animeSeasonOffsets = h0.t0(new x(46298, h0.t0(new x(1, 0), new x(2, 26), new x(3, 57), new x(4, 87), new x(5, 112), new x(6, 131))), new x(37854, h0.t0(new x(1, 0), new x(2, 61), new x(3, 77), new x(4, 91), new x(5, Integer.valueOf(TsExtractor.TS_STREAM_TYPE_HDMV_DTS)), new x(6, 143), new x(7, 195), new x(8, Integer.valueOf(ComposerKt.referenceKey)), new x(9, 325), new x(10, 336), new x(11, 381), new x(12, 405), new x(13, 407), new x(14, Integer.valueOf(MediaError.DetailedErrorCode.DASH_MANIFEST_NO_PERIODS)), new x(15, 458), new x(16, 491), new x(17, 516), new x(18, 522), new x(19, 574), new x(20, 628), new x(21, 746), new x(22, 891))), new x(46260, h0.t0(new x(1, 0), new x(2, 35), new x(3, 100), new x(4, 141), new x(5, 183))), new x(31910, h0.t0(new x(1, 0), new x(2, 32), new x(3, 53), new x(4, 71), new x(5, 89), new x(6, 112), new x(7, 126), new x(8, 143), new x(9, 175), new x(10, 197), new x(11, 222), new x(12, 243), new x(13, 261), new x(14, 283), new x(15, 300), new x(16, Integer.valueOf(MediaError.DetailedErrorCode.DASH_NETWORK)), new x(17, 349), new x(18, 375), new x(19, 394), new x(20, 432), new x(21, 459))), new x(12609, h0.t0(new x(1, 0), new x(2, 39), new x(3, 74), new x(4, 108), new x(5, Integer.valueOf(TsExtractor.TS_STREAM_TYPE_DTS_UHD)), new x(6, 165), new x(7, 200), new x(8, 220), new x(9, 254))), new x(68727, h0.t0(new x(1, 0), new x(2, 28), new x(3, 46), new x(4, 77), new x(5, 91))), new x(73223, h0.t0(new x(1, 0), new x(2, 51), new x(3, Integer.valueOf(MediaError.DetailedErrorCode.MEDIA_NETWORK)), new x(4, 154))), new x(30984, h0.t0(new x(1, 0), new x(2, 20), new x(3, 41), new x(4, 63), new x(5, 91), new x(6, 109), new x(7, 132), new x(8, 152), new x(9, 167), new x(10, 190), new x(11, Integer.valueOf(ComposerKt.referenceKey)), new x(12, 230), new x(13, 266), new x(14, Integer.valueOf(BuildConfig.VERSION_CODE)), new x(15, 342), new x(16, 366))), new x(13916, Collections.singletonMap(1, 0)), new x(31911, Collections.singletonMap(1, 0)));

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J3\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007¢\u0006\u0002\u0010\fR\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/arflix/tv/util/AnimeMapper$Companion;", "", "<init>", "()V", "isAnimeContentStatic", "", "tmdbId", "", "genreIds", "", "originalLanguage", "", "(Ljava/lang/Integer;Ljava/util/List;Ljava/lang/String;)Z", "staticTmdbIds", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ boolean isAnimeContentStatic$default(Companion companion, Integer num, List list, String str, int i10, Object obj) {
            if ((i10 & 2) != 0) {
                list = z.f19728i;
            }
            if ((i10 & 4) != 0) {
                str = null;
            }
            return companion.isAnimeContentStatic(num, list, str);
        }

        @n
        public final boolean isAnimeContentStatic(Integer tmdbId, List<Integer> genreIds, String originalLanguage) {
            if (tmdbId == null || !AnimeMapper.staticTmdbIds.contains(tmdbId)) {
                return genreIds.contains(16) && p.a(originalLanguage != null ? originalLanguage.toLowerCase(Locale.ROOT) : null, "ja");
            }
            return true;
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/arflix/tv/util/AnimeMapper$SeasonSegment;", "", "kitsuId", "", "episodeCount", "<init>", "(II)V", "getKitsuId", "()I", "getEpisodeCount", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SeasonSegment {
        private final int episodeCount;
        private final int kitsuId;

        public SeasonSegment(int i10, int i11) {
            this.kitsuId = i10;
            this.episodeCount = i11;
        }

        public static /* synthetic */ SeasonSegment copy$default(SeasonSegment seasonSegment, int i10, int i11, int i12, Object obj) {
            if ((i12 & 1) != 0) {
                i10 = seasonSegment.kitsuId;
            }
            if ((i12 & 2) != 0) {
                i11 = seasonSegment.episodeCount;
            }
            return seasonSegment.copy(i10, i11);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getKitsuId() {
            return this.kitsuId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getEpisodeCount() {
            return this.episodeCount;
        }

        public final SeasonSegment copy(int kitsuId, int episodeCount) {
            return new SeasonSegment(kitsuId, episodeCount);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SeasonSegment)) {
                return false;
            }
            SeasonSegment seasonSegment = (SeasonSegment) other;
            return this.kitsuId == seasonSegment.kitsuId && this.episodeCount == seasonSegment.episodeCount;
        }

        public final int getEpisodeCount() {
            return this.episodeCount;
        }

        public final int getKitsuId() {
            return this.kitsuId;
        }

        public int hashCode() {
            return (this.kitsuId * 31) + this.episodeCount;
        }

        public String toString() {
            return c.s("SeasonSegment(kitsuId=", this.kitsuId, ", episodeCount=", this.episodeCount, ")");
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.util.AnimeMapper$calculateTmdbSeasonOffset$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.util.AnimeMapper", f = "AnimeMapper.kt", l = {AnalyticsListener.EVENT_DROPPED_SEEKS_WHILE_SCRUBBING, 1142}, m = "calculateTmdbSeasonOffset", v = 2)
    public static final class AnonymousClass1 extends f7.c {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AnimeMapper.this.calculateTmdbSeasonOffset(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.util.AnimeMapper$ensureSeasonEpisodeCountsCached$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.util.AnimeMapper", f = "AnimeMapper.kt", l = {1142, 1075, 1080, 1152, 1162}, m = "ensureSeasonEpisodeCountsCached", v = 2)
    public static final class C16421 extends f7.c {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C16421(d<? super C16421> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AnimeMapper.this.ensureSeasonEpisodeCountsCached(0, 0, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.util.AnimeMapper$fetchArmMapping$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.util.AnimeMapper", f = "AnimeMapper.kt", l = {692, 1145}, m = "fetchArmMapping", v = 2)
    public static final class C16431 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C16431(d<? super C16431> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AnimeMapper.this.fetchArmMapping(0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.util.AnimeMapper$getKitsuEpisodeCount$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.util.AnimeMapper", f = "AnimeMapper.kt", l = {1142, AnalyticsListener.EVENT_AUDIO_UNDERRUN, 1152}, m = "getKitsuEpisodeCount", v = 2)
    public static final class C16441 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C16441(d<? super C16441> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AnimeMapper.this.getKitsuEpisodeCount(0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.util.AnimeMapper$getKitsuSequelId$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.util.AnimeMapper", f = "AnimeMapper.kt", l = {1142, 962, 974, 1156, 1166}, m = "getKitsuSequelId", v = 2)
    public static final class C16451 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public C16451(d<? super C16451> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AnimeMapper.this.getKitsuSequelId(0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.util.AnimeMapper$hasKitsuSequel$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.util.AnimeMapper", f = "AnimeMapper.kt", l = {1142, 943, 1152}, m = "hasKitsuSequel", v = 2)
    public static final class C16461 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C16461(d<? super C16461> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AnimeMapper.this.hasKitsuSequel(0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.util.AnimeMapper$resolveAnimeEpisodeQuery$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.util.AnimeMapper", f = "AnimeMapper.kt", l = {472, 481, 489, 497, 505, 518}, m = "resolveAnimeEpisodeQuery", v = 2)
    public static final class C16471 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        public C16471(d<? super C16471> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AnimeMapper.this.resolveAnimeEpisodeQuery(null, null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.util.AnimeMapper$resolveDynamicSegmentedEpisode$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.util.AnimeMapper", f = "AnimeMapper.kt", l = {584}, m = "resolveDynamicSegmentedEpisode", v = 2)
    public static final class C16481 extends f7.c {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C16481(d<? super C16481> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AnimeMapper.this.resolveDynamicSegmentedEpisode(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.util.AnimeMapper$resolveKitsuIdFromTvdb$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.util.AnimeMapper", f = "AnimeMapper.kt", l = {732, 1144}, m = "resolveKitsuIdFromTvdb", v = 2)
    public static final class C16491 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public C16491(d<? super C16491> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AnimeMapper.this.resolveKitsuIdFromTvdb(0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.util.AnimeMapper$resolveTier2Absolute$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.util.AnimeMapper", f = "AnimeMapper.kt", l = {634, 1142}, m = "resolveTier2Absolute", v = 2)
    public static final class C16501 extends f7.c {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
        int I$5;
        int I$6;
        int I$7;
        int I$8;
        int I$9;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public C16501(d<? super C16501> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AnimeMapper.this.resolveTier2Absolute(0, 0, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.util.AnimeMapper$resolveTier3Tvdb$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Ljava/lang/String;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.util.AnimeMapper$resolveTier3Tvdb$2", f = "AnimeMapper.kt", l = {1142, 714, 718}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends j implements r7.p<k0, d<? super String>, Object> {
        final /* synthetic */ int $episode;
        final /* synthetic */ int $season;
        final /* synthetic */ Integer $tmdbId;
        final /* synthetic */ int $tvdbId;
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(int i10, Integer num, int i11, int i12, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$tvdbId = i10;
            this.$tmdbId = num;
            this.$season = i11;
            this.$episode = i12;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return AnimeMapper.this.new AnonymousClass2(this.$tvdbId, this.$tmdbId, this.$season, this.$episode, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:32:0x009d, code lost:
        
            if (r14 == r6) goto L33;
         */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0086 A[Catch: Exception -> 0x00c1, TryCatch #1 {Exception -> 0x00c1, blocks: (B:8:0x001a, B:34:0x00a0, B:13:0x002b, B:29:0x0082, B:31:0x0086, B:16:0x0039, B:24:0x006c, B:26:0x0071, B:37:0x00bc, B:38:0x00c0, B:19:0x0040, B:23:0x005d), top: B:43:0x000a, inners: #0 }] */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r14) {
            /*
                r13 = this;
                java.lang.String r0 = "kitsu:"
                int r1 = r13.label
                r2 = 3
                r3 = 2
                r4 = 1
                r5 = 0
                e7.a r6 = e7.a.f15033i
                if (r1 == 0) goto L3d
                if (r1 == r4) goto L2f
                if (r1 == r3) goto L27
                if (r1 != r2) goto L1f
                java.lang.Object r1 = r13.L$1
                java.lang.Integer r1 = (java.lang.Integer) r1
                java.lang.Object r1 = r13.L$0
                java.lang.Integer r1 = (java.lang.Integer) r1
                k2.c.G(r14)     // Catch: java.lang.Exception -> Lc1
                goto La0
            L1f:
                java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r14.<init>(r0)
                throw r14
            L27:
                java.lang.Object r1 = r13.L$0
                java.lang.Integer r1 = (java.lang.Integer) r1
                k2.c.G(r14)     // Catch: java.lang.Exception -> Lc1
                goto L82
            L2f:
                int r1 = r13.I$0
                java.lang.Object r4 = r13.L$1
                com.arflix.tv.util.AnimeMapper r4 = (com.arflix.tv.util.AnimeMapper) r4
                java.lang.Object r7 = r13.L$0
                ua.a r7 = (ua.a) r7
                k2.c.G(r14)     // Catch: java.lang.Exception -> Lc1
                goto L5d
            L3d:
                k2.c.G(r14)
                com.arflix.tv.util.AnimeMapper r14 = com.arflix.tv.util.AnimeMapper.this     // Catch: java.lang.Exception -> Lc1
                ua.a r7 = com.arflix.tv.util.AnimeMapper.access$getCacheMutex$p(r14)     // Catch: java.lang.Exception -> Lc1
                com.arflix.tv.util.AnimeMapper r14 = com.arflix.tv.util.AnimeMapper.this     // Catch: java.lang.Exception -> Lc1
                int r1 = r13.$tvdbId     // Catch: java.lang.Exception -> Lc1
                r13.L$0 = r7     // Catch: java.lang.Exception -> Lc1
                r13.L$1 = r14     // Catch: java.lang.Exception -> Lc1
                r13.I$0 = r1     // Catch: java.lang.Exception -> Lc1
                r8 = 0
                r13.I$1 = r8     // Catch: java.lang.Exception -> Lc1
                r13.label = r4     // Catch: java.lang.Exception -> Lc1
                java.lang.Object r4 = r7.c(r13)     // Catch: java.lang.Exception -> Lc1
                if (r4 != r6) goto L5c
                goto L9f
            L5c:
                r4 = r14
            L5d:
                java.util.Map r14 = com.arflix.tv.util.AnimeMapper.access$getTvdbToKitsuCache$p(r4)     // Catch: java.lang.Throwable -> Lbb
                java.lang.Integer r4 = new java.lang.Integer     // Catch: java.lang.Throwable -> Lbb
                r4.<init>(r1)     // Catch: java.lang.Throwable -> Lbb
                java.lang.Object r14 = r14.get(r4)     // Catch: java.lang.Throwable -> Lbb
                java.lang.Integer r14 = (java.lang.Integer) r14     // Catch: java.lang.Throwable -> Lbb
                r7.b(r5)     // Catch: java.lang.Exception -> Lc1
                if (r14 != 0) goto L84
                com.arflix.tv.util.AnimeMapper r14 = com.arflix.tv.util.AnimeMapper.this     // Catch: java.lang.Exception -> Lc1
                int r1 = r13.$tvdbId     // Catch: java.lang.Exception -> Lc1
                r13.L$0 = r5     // Catch: java.lang.Exception -> Lc1
                r13.L$1 = r5     // Catch: java.lang.Exception -> Lc1
                r13.label = r3     // Catch: java.lang.Exception -> Lc1
                java.lang.Object r14 = com.arflix.tv.util.AnimeMapper.access$resolveKitsuIdFromTvdb(r14, r1, r13)     // Catch: java.lang.Exception -> Lc1
                if (r14 != r6) goto L82
                goto L9f
            L82:
                java.lang.Integer r14 = (java.lang.Integer) r14     // Catch: java.lang.Exception -> Lc1
            L84:
                if (r14 == 0) goto Lc1
                com.arflix.tv.util.AnimeMapper r7 = com.arflix.tv.util.AnimeMapper.this     // Catch: java.lang.Exception -> Lc1
                int r8 = r14.intValue()     // Catch: java.lang.Exception -> Lc1
                java.lang.Integer r9 = r13.$tmdbId     // Catch: java.lang.Exception -> Lc1
                int r10 = r13.$season     // Catch: java.lang.Exception -> Lc1
                int r11 = r13.$episode     // Catch: java.lang.Exception -> Lc1
                r13.L$0 = r5     // Catch: java.lang.Exception -> Lc1
                r13.L$1 = r5     // Catch: java.lang.Exception -> Lc1
                r13.label = r2     // Catch: java.lang.Exception -> Lc1
                r12 = r13
                java.lang.Object r14 = com.arflix.tv.util.AnimeMapper.access$resolveValidatedEpisode(r7, r8, r9, r10, r11, r12)     // Catch: java.lang.Exception -> Lc1
                if (r14 != r6) goto La0
            L9f:
                return r6
            La0:
                x6.x r14 = (x6.x) r14     // Catch: java.lang.Exception -> Lc1
                java.lang.Object r1 = r14.f22608i     // Catch: java.lang.Exception -> Lc1
                java.lang.Object r14 = r14.f22609l     // Catch: java.lang.Exception -> Lc1
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lc1
                r2.<init>(r0)     // Catch: java.lang.Exception -> Lc1
                r2.append(r1)     // Catch: java.lang.Exception -> Lc1
                java.lang.String r0 = ":"
                r2.append(r0)     // Catch: java.lang.Exception -> Lc1
                r2.append(r14)     // Catch: java.lang.Exception -> Lc1
                java.lang.String r14 = r2.toString()     // Catch: java.lang.Exception -> Lc1
                return r14
            Lbb:
                r0 = move-exception
                r14 = r0
                r7.b(r5)     // Catch: java.lang.Exception -> Lc1
                throw r14     // Catch: java.lang.Exception -> Lc1
            Lc1:
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.util.AnimeMapper.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super String> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.util.AnimeMapper$resolveTier4TitleSearch$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Ljava/lang/String;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.util.AnimeMapper$resolveTier4TitleSearch$2", f = "AnimeMapper.kt", l = {1142, 755, 759}, m = "invokeSuspend", v = 2)
    public static final class C16512 extends j implements r7.p<k0, d<? super String>, Object> {
        final /* synthetic */ int $episode;
        final /* synthetic */ int $season;
        final /* synthetic */ String $title;
        final /* synthetic */ Integer $tmdbId;
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        final /* synthetic */ AnimeMapper this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C16512(String str, AnimeMapper animeMapper, Integer num, int i10, int i11, d<? super C16512> dVar) {
            super(2, dVar);
            this.$title = str;
            this.this$0 = animeMapper;
            this.$tmdbId = num;
            this.$season = i10;
            this.$episode = i11;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new C16512(this.$title, this.this$0, this.$tmdbId, this.$season, this.$episode, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:32:0x00b4, code lost:
        
            if (r14 == r6) goto L33;
         */
        /* JADX WARN: Removed duplicated region for block: B:31:0x009b A[Catch: Exception -> 0x00d8, TryCatch #1 {Exception -> 0x00d8, blocks: (B:8:0x001e, B:34:0x00b7, B:13:0x0033, B:29:0x0097, B:31:0x009b, B:16:0x0043, B:24:0x007f, B:26:0x0084, B:37:0x00d3, B:38:0x00d7, B:19:0x004a, B:23:0x0075), top: B:43:0x000a, inners: #0 }] */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r14) {
            /*
                Method dump skipped, instruction units count: 217
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.util.AnimeMapper.C16512.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super String> dVar) {
            return ((C16512) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.util.AnimeMapper$resolveTierArm$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Ljava/lang/String;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.util.AnimeMapper$resolveTierArm$2", f = "AnimeMapper.kt", l = {1142, 668, 677}, m = "invokeSuspend", v = 2)
    public static final class C16522 extends j implements r7.p<k0, d<? super String>, Object> {
        final /* synthetic */ int $episode;
        final /* synthetic */ int $season;
        final /* synthetic */ int $tmdbId;
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.util.AnimeMapper$resolveTierArm$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", "", "kitsuId"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @f7.e(c = "com.arflix.tv.util.AnimeMapper$resolveTierArm$2$1", f = "AnimeMapper.kt", l = {678}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass1 extends j implements r7.p<Integer, d<? super Integer>, Object> {
            /* synthetic */ int I$0;
            int label;
            final /* synthetic */ AnimeMapper this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(AnimeMapper animeMapper, d<? super AnonymousClass1> dVar) {
                super(2, dVar);
                this.this$0 = animeMapper;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, dVar);
                anonymousClass1.I$0 = ((Number) obj).intValue();
                return anonymousClass1;
            }

            public final Object invoke(int i10, d<? super Integer> dVar) {
                return ((AnonymousClass1) create(Integer.valueOf(i10), dVar)).invokeSuspend(t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.I$0;
                int i11 = this.label;
                if (i11 != 0) {
                    if (i11 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    return obj;
                }
                k2.c.G(obj);
                AnimeMapper animeMapper = this.this$0;
                this.I$0 = i10;
                this.label = 1;
                Object kitsuEpisodeCount = animeMapper.getKitsuEpisodeCount(i10, this);
                e7.a aVar = e7.a.f15033i;
                return kitsuEpisodeCount == aVar ? aVar : kitsuEpisodeCount;
            }

            @Override // r7.p
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke(((Number) obj).intValue(), (d<? super Integer>) obj2);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C16522(int i10, int i11, int i12, d<? super C16522> dVar) {
            super(2, dVar);
            this.$tmdbId = i10;
            this.$season = i11;
            this.$episode = i12;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return AnimeMapper.this.new C16522(this.$tmdbId, this.$season, this.$episode, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:38:0x00b7, code lost:
        
            if (r9 == r5) goto L39;
         */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0088 A[Catch: Exception -> 0x00c2, TryCatch #1 {Exception -> 0x00c2, blocks: (B:8:0x001c, B:40:0x00ba, B:13:0x002d, B:29:0x0084, B:31:0x0088, B:34:0x008f, B:37:0x00a0, B:16:0x003b, B:24:0x006e, B:26:0x0073, B:43:0x00be, B:44:0x00c1, B:19:0x0042, B:23:0x005f), top: B:49:0x0008, inners: #0 }] */
        /* JADX WARN: Removed duplicated region for block: B:36:0x009f  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x00a0 A[Catch: Exception -> 0x00c2, TryCatch #1 {Exception -> 0x00c2, blocks: (B:8:0x001c, B:40:0x00ba, B:13:0x002d, B:29:0x0084, B:31:0x0088, B:34:0x008f, B:37:0x00a0, B:16:0x003b, B:24:0x006e, B:26:0x0073, B:43:0x00be, B:44:0x00c1, B:19:0x0042, B:23:0x005f), top: B:49:0x0008, inners: #0 }] */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                r8 = this;
                int r0 = r8.label
                r1 = 3
                r2 = 2
                r3 = 1
                r4 = 0
                e7.a r5 = e7.a.f15033i
                if (r0 == 0) goto L3f
                if (r0 == r3) goto L31
                if (r0 == r2) goto L29
                if (r0 != r1) goto L21
                java.lang.Object r0 = r8.L$2
                com.arflix.tv.util.ArmSeasonKitsuCandidates r0 = (com.arflix.tv.util.ArmSeasonKitsuCandidates) r0
                java.lang.Object r0 = r8.L$1
                java.util.List r0 = (java.util.List) r0
                java.lang.Object r0 = r8.L$0
                java.util.List r0 = (java.util.List) r0
                k2.c.G(r9)     // Catch: java.lang.Exception -> Lc2
                goto Lba
            L21:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L29:
                java.lang.Object r0 = r8.L$0
                java.util.List r0 = (java.util.List) r0
                k2.c.G(r9)     // Catch: java.lang.Exception -> Lc2
                goto L84
            L31:
                int r0 = r8.I$0
                java.lang.Object r3 = r8.L$1
                com.arflix.tv.util.AnimeMapper r3 = (com.arflix.tv.util.AnimeMapper) r3
                java.lang.Object r6 = r8.L$0
                ua.a r6 = (ua.a) r6
                k2.c.G(r9)     // Catch: java.lang.Exception -> Lc2
                goto L5f
            L3f:
                k2.c.G(r9)
                com.arflix.tv.util.AnimeMapper r9 = com.arflix.tv.util.AnimeMapper.this     // Catch: java.lang.Exception -> Lc2
                ua.a r6 = com.arflix.tv.util.AnimeMapper.access$getCacheMutex$p(r9)     // Catch: java.lang.Exception -> Lc2
                com.arflix.tv.util.AnimeMapper r9 = com.arflix.tv.util.AnimeMapper.this     // Catch: java.lang.Exception -> Lc2
                int r0 = r8.$tmdbId     // Catch: java.lang.Exception -> Lc2
                r8.L$0 = r6     // Catch: java.lang.Exception -> Lc2
                r8.L$1 = r9     // Catch: java.lang.Exception -> Lc2
                r8.I$0 = r0     // Catch: java.lang.Exception -> Lc2
                r7 = 0
                r8.I$1 = r7     // Catch: java.lang.Exception -> Lc2
                r8.label = r3     // Catch: java.lang.Exception -> Lc2
                java.lang.Object r3 = r6.c(r8)     // Catch: java.lang.Exception -> Lc2
                if (r3 != r5) goto L5e
                goto Lb9
            L5e:
                r3 = r9
            L5f:
                java.util.Map r9 = com.arflix.tv.util.AnimeMapper.access$getArmTmdbCache$p(r3)     // Catch: java.lang.Throwable -> Lbd
                java.lang.Integer r3 = new java.lang.Integer     // Catch: java.lang.Throwable -> Lbd
                r3.<init>(r0)     // Catch: java.lang.Throwable -> Lbd
                java.lang.Object r9 = r9.get(r3)     // Catch: java.lang.Throwable -> Lbd
                java.util.List r9 = (java.util.List) r9     // Catch: java.lang.Throwable -> Lbd
                r6.b(r4)     // Catch: java.lang.Exception -> Lc2
                if (r9 != 0) goto L86
                com.arflix.tv.util.AnimeMapper r9 = com.arflix.tv.util.AnimeMapper.this     // Catch: java.lang.Exception -> Lc2
                int r0 = r8.$tmdbId     // Catch: java.lang.Exception -> Lc2
                r8.L$0 = r4     // Catch: java.lang.Exception -> Lc2
                r8.L$1 = r4     // Catch: java.lang.Exception -> Lc2
                r8.label = r2     // Catch: java.lang.Exception -> Lc2
                java.lang.Object r9 = com.arflix.tv.util.AnimeMapper.access$fetchArmMapping(r9, r0, r8)     // Catch: java.lang.Exception -> Lc2
                if (r9 != r5) goto L84
                goto Lb9
            L84:
                java.util.List r9 = (java.util.List) r9     // Catch: java.lang.Exception -> Lc2
            L86:
                if (r9 == 0) goto Lc2
                boolean r0 = r9.isEmpty()     // Catch: java.lang.Exception -> Lc2
                if (r0 == 0) goto L8f
                goto Lc2
            L8f:
                int r0 = r8.$season     // Catch: java.lang.Exception -> Lc2
                com.arflix.tv.util.ArmSeasonKitsuCandidates r9 = com.arflix.tv.util.AnimeMapperKt.armSeasonKitsuCandidates(r9, r0)     // Catch: java.lang.Exception -> Lc2
                java.util.List r0 = r9.getKitsuIds()     // Catch: java.lang.Exception -> Lc2
                boolean r0 = r0.isEmpty()     // Catch: java.lang.Exception -> Lc2
                if (r0 == 0) goto La0
                goto Lc2
            La0:
                int r0 = r8.$season     // Catch: java.lang.Exception -> Lc2
                int r2 = r8.$episode     // Catch: java.lang.Exception -> Lc2
                com.arflix.tv.util.AnimeMapper$resolveTierArm$2$1 r3 = new com.arflix.tv.util.AnimeMapper$resolveTierArm$2$1     // Catch: java.lang.Exception -> Lc2
                com.arflix.tv.util.AnimeMapper r6 = com.arflix.tv.util.AnimeMapper.this     // Catch: java.lang.Exception -> Lc2
                r3.<init>(r6, r4)     // Catch: java.lang.Exception -> Lc2
                r8.L$0 = r4     // Catch: java.lang.Exception -> Lc2
                r8.L$1 = r4     // Catch: java.lang.Exception -> Lc2
                r8.L$2 = r4     // Catch: java.lang.Exception -> Lc2
                r8.label = r1     // Catch: java.lang.Exception -> Lc2
                java.lang.Object r9 = com.arflix.tv.util.AnimeMapperKt.armEpisodeQueryFromSeasonCandidates(r9, r0, r2, r3, r8)     // Catch: java.lang.Exception -> Lc2
                if (r9 != r5) goto Lba
            Lb9:
                return r5
            Lba:
                java.lang.String r9 = (java.lang.String) r9     // Catch: java.lang.Exception -> Lc2
                return r9
            Lbd:
                r9 = move-exception
                r6.b(r4)     // Catch: java.lang.Exception -> Lc2
                throw r9     // Catch: java.lang.Exception -> Lc2
            Lc2:
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.util.AnimeMapper.C16522.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super String> dVar) {
            return ((C16522) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.util.AnimeMapper$resolveValidatedEpisode$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.util.AnimeMapper", f = "AnimeMapper.kt", l = {812, 813, 831, 847, 855, 859}, m = "resolveValidatedEpisode", v = 2)
    public static final class C16531 extends f7.c {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        Object L$0;
        Object L$1;
        Object L$2;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C16531(d<? super C16531> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AnimeMapper.this.resolveValidatedEpisode(0, null, 0, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.util.AnimeMapper$searchKitsuByTitle$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.util.AnimeMapper", f = "AnimeMapper.kt", l = {775, 1144}, m = "searchKitsuByTitle", v = 2)
    public static final class C16541 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        int label;
        /* synthetic */ Object result;

        public C16541(d<? super C16541> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AnimeMapper.this.searchKitsuByTitle(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.util.AnimeMapper$walkSequelsForEpisode$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.util.AnimeMapper", f = "AnimeMapper.kt", l = {913, 924}, m = "walkSequelsForEpisode", v = 2)
    public static final class C16551 extends f7.c {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
        int I$5;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C16551(d<? super C16551> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AnimeMapper.this.walkSequelsForEpisode(0, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.util.AnimeMapper$walkSequelsToSeason$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.util.AnimeMapper", f = "AnimeMapper.kt", l = {882, 885, 892}, m = "walkSequelsToSeason", v = 2)
    public static final class C16561 extends f7.c {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
        int I$5;
        int I$6;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C16561(d<? super C16561> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AnimeMapper.this.walkSequelsToSeason(0, 0, 0, this);
        }
    }

    @Inject
    public AnimeMapper(StreamApi streamApi, TmdbApi tmdbApi) {
        this.streamApi = streamApi;
        this.tmdbApi = tmdbApi;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00a1, code lost:
    
        if (r6.c(r0) == r5) goto L28;
     */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x00a1 -> B:29:0x00a4). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object calculateTmdbSeasonOffset(java.lang.Integer r11, int r12, d7.d<? super java.lang.Integer> r13) {
        /*
            Method dump skipped, instruction units count: 203
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.util.AnimeMapper.calculateTmdbSeasonOffset(java.lang.Integer, int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0155, code lost:
    
        if (((ka.r) r3).o(r7) == r6) goto L31;
     */
    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:112:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:114:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01b3 A[Catch: all -> 0x01de, LOOP:0: B:64:0x01ad->B:66:0x01b3, LOOP_END, TryCatch #6 {all -> 0x01de, blocks: (B:63:0x01a0, B:64:0x01ad, B:66:0x01b3, B:69:0x01e0, B:71:0x01ef), top: B:107:0x01a0, outer: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01ef A[Catch: all -> 0x01de, TRY_LEAVE, TryCatch #6 {all -> 0x01de, blocks: (B:63:0x01a0, B:64:0x01ad, B:66:0x01b3, B:69:0x01e0, B:71:0x01ef), top: B:107:0x01a0, outer: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x022f A[Catch: all -> 0x0233, TRY_LEAVE, TryCatch #2 {all -> 0x0233, blocks: (B:84:0x0220, B:86:0x022f), top: B:102:0x0220 }] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object ensureSeasonEpisodeCountsCached(int r18, int r19, int r20, d7.d<? super x6.t0> r21) {
        /*
            Method dump skipped, instruction units count: 578
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.util.AnimeMapper.ensureSeasonEpisodeCountsCached(int, int, int, d7.d):java.lang.Object");
    }

    private final <K> void evictIfNeeded(Map<K, ?> cache) {
        if (cache.size() >= this.MAX_CACHE_SIZE) {
            Iterator it = kotlin.collections.x.X0(cache.keySet(), cache.size() / 5).iterator();
            while (it.hasNext()) {
                cache.remove(it.next());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00ac, code lost:
    
        if (r0.c(r1) == r6) goto L34;
     */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00ce A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00cf A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object fetchArmMapping(int r8, d7.d<? super java.util.List<com.arflix.tv.data.api.ArmMappingEntry>> r9) {
        /*
            Method dump skipped, instruction units count: 208
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.util.AnimeMapper.fetchArmMapping(int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00db, code lost:
    
        if (r0.c(r1) == r8) goto L45;
     */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00c7 A[Catch: Exception -> 0x00f7, TRY_LEAVE, TryCatch #2 {Exception -> 0x00f7, blocks: (B:14:0x003f, B:47:0x00ed, B:50:0x00f2, B:51:0x00f5, B:19:0x0052, B:36:0x00b0, B:38:0x00b8, B:40:0x00be, B:43:0x00c7, B:33:0x0095, B:46:0x00de), top: B:60:0x0025, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00f6 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getKitsuEpisodeCount(int r10, d7.d<? super java.lang.Integer> r11) {
        /*
            Method dump skipped, instruction units count: 252
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.util.AnimeMapper.getKitsuEpisodeCount(int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x01ee, code lost:
    
        if (r1.c(r0) != r11) goto L136;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x023a, code lost:
    
        if (r1.c(r0) != r11) goto L132;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00f6 A[Catch: Exception -> 0x0224, TryCatch #3 {Exception -> 0x0224, blocks: (B:19:0x0062, B:114:0x0216, B:116:0x0220, B:117:0x0223, B:106:0x01d8, B:25:0x0088, B:42:0x00ee, B:44:0x00f6, B:45:0x00fa, B:47:0x0100, B:49:0x010d, B:51:0x0113, B:55:0x011b, B:57:0x011f, B:59:0x0126, B:61:0x012c, B:63:0x0132, B:65:0x0138, B:67:0x013e, B:69:0x0144, B:71:0x0148, B:73:0x014e, B:39:0x00cd, B:109:0x01f1, B:113:0x020f), top: B:134:0x0029, inners: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x018c A[Catch: Exception -> 0x01d7, TryCatch #2 {Exception -> 0x01d7, blocks: (B:22:0x007d, B:78:0x0184, B:80:0x018c, B:81:0x0190, B:83:0x0196, B:85:0x01a3, B:87:0x01a9, B:91:0x01b1, B:94:0x01b7, B:96:0x01bd, B:98:0x01c3, B:100:0x01c9, B:102:0x01cf, B:104:0x01d5, B:75:0x0158), top: B:134:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01b4  */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v12, types: [java.lang.Object, kotlin.jvm.internal.k0] */
    /* JADX WARN: Type inference failed for: r1v14, types: [kotlin.jvm.internal.k0] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v29 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v30 */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v2, types: [kotlin.jvm.internal.k0] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getKitsuSequelId(int r14, d7.d<? super java.lang.Integer> r15) {
        /*
            Method dump skipped, instruction units count: 608
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.util.AnimeMapper.getKitsuSequelId(int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00a5 A[Catch: all -> 0x00aa, TryCatch #1 {all -> 0x00aa, blocks: (B:32:0x0096, B:34:0x00a5, B:37:0x00ac), top: B:47:0x0096 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object hasKitsuSequel(int r9, d7.d<? super java.lang.Boolean> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.arflix.tv.util.AnimeMapper.C16461
            if (r0 == 0) goto L13
            r0 = r10
            com.arflix.tv.util.AnimeMapper$hasKitsuSequel$1 r0 = (com.arflix.tv.util.AnimeMapper.C16461) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.util.AnimeMapper$hasKitsuSequel$1 r0 = new com.arflix.tv.util.AnimeMapper$hasKitsuSequel$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            int r1 = r0.label
            r2 = 3
            r3 = 2
            r4 = 1
            r5 = 0
            r6 = 0
            e7.a r7 = e7.a.f15033i
            if (r1 == 0) goto L4d
            if (r1 == r4) goto L43
            if (r1 == r3) goto L3d
            if (r1 != r2) goto L35
            int r9 = r0.I$0
            java.lang.Object r0 = r0.L$0
            ua.a r0 = (ua.a) r0
            k2.c.G(r10)
            goto L96
        L35:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L3d:
            int r9 = r0.I$0
            k2.c.G(r10)
            goto L84
        L43:
            int r9 = r0.I$0
            java.lang.Object r1 = r0.L$0
            ua.a r1 = (ua.a) r1
            k2.c.G(r10)
            goto L61
        L4d:
            k2.c.G(r10)
            ua.a r1 = r8.cacheMutex
            r0.L$0 = r1
            r0.I$0 = r9
            r0.I$1 = r5
            r0.label = r4
            java.lang.Object r10 = r1.c(r0)
            if (r10 != r7) goto L61
            goto L94
        L61:
            java.util.Map<java.lang.Integer, java.lang.Boolean> r10 = r8.hasSequelCache     // Catch: java.lang.Throwable -> Lb8
            java.lang.Integer r4 = new java.lang.Integer     // Catch: java.lang.Throwable -> Lb8
            r4.<init>(r9)     // Catch: java.lang.Throwable -> Lb8
            java.lang.Object r10 = r10.get(r4)     // Catch: java.lang.Throwable -> Lb8
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch: java.lang.Throwable -> Lb8
            if (r10 == 0) goto L74
            r1.b(r6)
            return r10
        L74:
            r1.b(r6)
            r0.L$0 = r6
            r0.I$0 = r9
            r0.label = r3
            java.lang.Object r10 = r8.getKitsuSequelId(r9, r0)
            if (r10 != r7) goto L84
            goto L94
        L84:
            ua.a r10 = r8.cacheMutex
            r0.L$0 = r10
            r0.I$0 = r9
            r0.I$1 = r5
            r0.label = r2
            java.lang.Object r0 = r10.c(r0)
            if (r0 != r7) goto L95
        L94:
            return r7
        L95:
            r0 = r10
        L96:
            java.util.Map<java.lang.Integer, java.lang.Boolean> r10 = r8.hasSequelCache     // Catch: java.lang.Throwable -> Laa
            java.lang.Integer r1 = new java.lang.Integer     // Catch: java.lang.Throwable -> Laa
            r1.<init>(r9)     // Catch: java.lang.Throwable -> Laa
            java.lang.Object r9 = r10.get(r1)     // Catch: java.lang.Throwable -> Laa
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch: java.lang.Throwable -> Laa
            if (r9 == 0) goto Lac
            boolean r5 = r9.booleanValue()     // Catch: java.lang.Throwable -> Laa
            goto Lac
        Laa:
            r9 = move-exception
            goto Lb4
        Lac:
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r5)     // Catch: java.lang.Throwable -> Laa
            r0.b(r6)
            return r9
        Lb4:
            r0.b(r6)
            throw r9
        Lb8:
            r9 = move-exception
            r1.b(r6)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.util.AnimeMapper.hasKitsuSequel(int, d7.d):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ boolean isAnimeContent$default(AnimeMapper animeMapper, Integer num, List list, String str, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            list = z.f19728i;
        }
        if ((i10 & 4) != 0) {
            str = null;
        }
        return animeMapper.isAnimeContent(num, list, str);
    }

    @n
    public static final boolean isAnimeContentStatic(Integer num, List<Integer> list, String str) {
        return INSTANCE.isAnimeContentStatic(num, list, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x007a -> B:21:0x0081). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveDynamicSegmentedEpisode(java.util.List<com.arflix.tv.util.AnimeMapper.SeasonSegment> r12, int r13, d7.d<? super java.lang.String> r14) {
        /*
            Method dump skipped, instruction units count: 206
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.util.AnimeMapper.resolveDynamicSegmentedEpisode(java.util.List, int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveKitsuIdFromTvdb(int r8, d7.d<? super java.lang.Integer> r9) {
        /*
            Method dump skipped, instruction units count: 271
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.util.AnimeMapper.resolveKitsuIdFromTvdb(int, d7.d):java.lang.Object");
    }

    private final String resolveSegmentedEpisode(List<SeasonSegment> segments, int episode) {
        for (SeasonSegment seasonSegment : segments) {
            if (episode <= seasonSegment.getEpisodeCount()) {
                return c.n(seasonSegment.getKitsuId(), episode, "kitsu:", ":");
            }
            episode -= seasonSegment.getEpisodeCount();
        }
        return c.n(((SeasonSegment) kotlin.collections.x.w0(segments)).getKitsuId(), episode, "kitsu:", ":");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object resolveTier1PerSeason(int i10, int i11, int i12, d<? super String> dVar) {
        Integer num;
        List<SeasonSegment> list;
        Map<Integer, List<SeasonSegment>> map = this.seasonSegments.get(new Integer(i10));
        if (map == null || (list = map.get(new Integer(i11))) == null) {
            Map<Integer, Integer> map2 = this.perSeasonKitsuMap.get(new Integer(i10));
            if (map2 == null || (num = map2.get(new Integer(i11))) == null) {
                return null;
            }
            return c.n(num.intValue(), i12, "kitsu:", ":");
        }
        if (!list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (((SeasonSegment) it.next()).getEpisodeCount() == 0) {
                    Object objResolveDynamicSegmentedEpisode = resolveDynamicSegmentedEpisode(list, i12, dVar);
                    return objResolveDynamicSegmentedEpisode == e7.a.f15033i ? objResolveDynamicSegmentedEpisode : (String) objResolveDynamicSegmentedEpisode;
                }
            }
        }
        return resolveSegmentedEpisode(list, i12);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:62:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001b  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x0179 -> B:57:0x017e). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveTier2Absolute(int r20, int r21, int r22, d7.d<? super java.lang.String> r23) {
        /*
            Method dump skipped, instruction units count: 435
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.util.AnimeMapper.resolveTier2Absolute(int, int, int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object resolveTier3Tvdb(int i10, Integer num, int i11, int i12, d<? super String> dVar) {
        return m0.y(x0.f19655d, new AnonymousClass2(i10, num, i11, i12, null), dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object resolveTier4TitleSearch(String str, Integer num, int i10, int i11, d<? super String> dVar) {
        return m0.y(x0.f19655d, new C16512(str, this, num, i10, i11, null), dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object resolveTierArm(int i10, int i11, int i12, d<? super String> dVar) {
        return m0.y(x0.f19655d, new C16522(i10, i11, i12, null), dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x010d, code lost:
    
        if (r14 == r4) goto L73;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0146 A[PHI: r1 r5 r6 r10 r11 r12 r13
      0x0146: PHI (r1v8 int) = (r1v4 int), (r1v9 int) binds: [B:35:0x00f6, B:40:0x0113] A[DONT_GENERATE, DONT_INLINE]
      0x0146: PHI (r5v4 java.lang.Integer) = (r5v2 java.lang.Integer), (r5v5 java.lang.Integer) binds: [B:35:0x00f6, B:40:0x0113] A[DONT_GENERATE, DONT_INLINE]
      0x0146: PHI (r6v3 java.lang.Integer) = (r6v1 java.lang.Integer), (r6v4 java.lang.Integer) binds: [B:35:0x00f6, B:40:0x0113] A[DONT_GENERATE, DONT_INLINE]
      0x0146: PHI (r10v7 int) = (r10v4 int), (r10v9 int) binds: [B:35:0x00f6, B:40:0x0113] A[DONT_GENERATE, DONT_INLINE]
      0x0146: PHI (r11v6 boolean) = (r11v4 boolean), (r11v10 boolean) binds: [B:35:0x00f6, B:40:0x0113] A[DONT_GENERATE, DONT_INLINE]
      0x0146: PHI (r12v7 int) = (r12v4 int), (r12v9 int) binds: [B:35:0x00f6, B:40:0x0113] A[DONT_GENERATE, DONT_INLINE]
      0x0146: PHI (r13v8 int) = (r13v4 int), (r13v9 int) binds: [B:35:0x00f6, B:40:0x0113] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0171 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01a0 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveValidatedEpisode(int r10, java.lang.Integer r11, int r12, int r13, d7.d<? super x6.x> r14) {
        /*
            Method dump skipped, instruction units count: 510
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.util.AnimeMapper.resolveValidatedEpisode(int, java.lang.Integer, int, int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object searchKitsuByTitle(java.lang.String r11, d7.d<? super java.lang.Integer> r12) {
        /*
            Method dump skipped, instruction units count: 392
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.util.AnimeMapper.searchKitsuByTitle(java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00cb, code lost:
    
        if (r14 == r5) goto L33;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00ed A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x00cb -> B:13:0x003a). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object walkSequelsForEpisode(int r12, int r13, d7.d<? super x6.x> r14) {
        /*
            Method dump skipped, instruction units count: 238
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.util.AnimeMapper.walkSequelsForEpisode(int, int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:20:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00c3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00f7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0107 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:42:0x00ec -> B:43:0x00f3). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object walkSequelsToSeason(int r11, int r12, int r13, d7.d<? super x6.x> r14) {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.util.AnimeMapper.walkSequelsToSeason(int, int, int, d7.d):java.lang.Object");
    }

    public final Integer getKitsuId(int tmdbId) {
        return this.tmdbToKitsuMap.get(Integer.valueOf(tmdbId));
    }

    public final boolean isAnime(int tmdbId) {
        return this.tmdbToKitsuMap.containsKey(Integer.valueOf(tmdbId));
    }

    public final boolean isAnimeContent(Integer tmdbId, List<Integer> genreIds, String originalLanguage) {
        if (tmdbId == null || !this.tmdbToKitsuMap.containsKey(tmdbId)) {
            return genreIds.contains(16) && p.a(originalLanguage != null ? originalLanguage.toLowerCase(Locale.ROOT) : null, "ja");
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x0184, code lost:
    
        if (r5 == r11) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x01bc, code lost:
    
        if (r5 == r11) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x01f9, code lost:
    
        if (r5 == r11) goto L73;
     */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0128 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x015d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x018d A[PHI: r1 r2 r3 r4 r7 r12
      0x018d: PHI (r1v11 java.lang.Integer) = (r1v7 java.lang.Integer), (r1v12 java.lang.Integer) binds: [B:42:0x0163, B:47:0x018a] A[DONT_GENERATE, DONT_INLINE]
      0x018d: PHI (r2v12 java.lang.Integer) = (r2v8 java.lang.Integer), (r2v13 java.lang.Integer) binds: [B:42:0x0163, B:47:0x018a] A[DONT_GENERATE, DONT_INLINE]
      0x018d: PHI (r3v12 java.lang.String) = (r3v8 java.lang.String), (r3v13 java.lang.String) binds: [B:42:0x0163, B:47:0x018a] A[DONT_GENERATE, DONT_INLINE]
      0x018d: PHI (r4v12 java.lang.String) = (r4v8 java.lang.String), (r4v13 java.lang.String) binds: [B:42:0x0163, B:47:0x018a] A[DONT_GENERATE, DONT_INLINE]
      0x018d: PHI (r7v11 java.lang.Integer) = (r7v7 java.lang.Integer), (r7v15 java.lang.Integer) binds: [B:42:0x0163, B:47:0x018a] A[DONT_GENERATE, DONT_INLINE]
      0x018d: PHI (r12v11 java.lang.Integer) = (r12v7 java.lang.Integer), (r12v12 java.lang.Integer) binds: [B:42:0x0163, B:47:0x018a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0203  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveAnimeEpisodeQuery(java.lang.Integer r17, java.lang.Integer r18, java.lang.String r19, java.lang.String r20, java.lang.Integer r21, java.lang.Integer r22, d7.d<? super java.lang.String> r23) {
        /*
            Method dump skipped, instruction units count: 670
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.util.AnimeMapper.resolveAnimeEpisodeQuery(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, d7.d):java.lang.Object");
    }

    public final Object resolveAnimeSeasonStructure$app_playRelease(int i10, d<? super AnimeSeasonStructure> dVar) {
        return m0.y(x0.f19655d, new AnimeMapper$resolveAnimeSeasonStructure$2(this, i10, null), dVar);
    }
}
