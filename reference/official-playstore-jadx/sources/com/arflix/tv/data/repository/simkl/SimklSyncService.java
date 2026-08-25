package com.arflix.tv.data.repository.simkl;

import androidx.media3.extractor.ts.TsExtractor;
import com.arflix.tv.data.api.SimklAllItemsResponse;
import com.arflix.tv.data.api.SimklApi;
import com.arflix.tv.data.api.SimklHistoryMovieItem;
import com.arflix.tv.data.api.SimklHistoryShowItem;
import com.arflix.tv.data.api.SimklIds;
import com.arflix.tv.data.api.SimklMovieRef;
import com.arflix.tv.data.api.SimklNextToWatchInfo;
import com.arflix.tv.data.api.SimklPlaybackEpisode;
import com.arflix.tv.data.api.SimklPlaybackItem;
import com.arflix.tv.data.api.SimklShowRef;
import com.arflix.tv.data.api.TmdbApi;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.repository.ContinueWatchingItem;
import com.arflix.tv.data.repository.g;
import com.arflix.tv.util.Constants;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import d7.d;
import f7.c;
import f7.j;
import io.ktor.http.LinkHeader;
import j$.util.concurrent.ConcurrentHashMap;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;
import javax.inject.Singleton;
import ka.k0;
import ka.l0;
import ka.m0;
import ka.p0;
import kotlin.Metadata;
import kotlin.collections.n0;
import kotlin.collections.s;
import kotlin.collections.z;
import kotlin.jvm.internal.p;
import kotlin.text.k;
import kotlin.text.m;
import kotlin.text.o;
import kotlin.text.u;
import ua.e;
import ua.i;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000Ü\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010#\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u000b\b\u0007\u0018\u0000  \u00012\u00020\u0001:\b \u0001¡\u0001¢\u0001£\u0001B!\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u001a\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000b\u001a\u00020\nH\u0086@¢\u0006\u0004\b\r\u0010\u000eJ\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0086@¢\u0006\u0004\b\u0011\u0010\u0012J\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u000fH\u0086@¢\u0006\u0004\b\u0014\u0010\u0012J\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0086@¢\u0006\u0004\b\u0017\u0010\u0012J(\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0018\u001a\u00020\u00132\b\b\u0002\u0010\u0019\u001a\u00020\nH\u0086@¢\u0006\u0004\b\u001a\u0010\u001bJ \u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00152\b\b\u0002\u0010\u0019\u001a\u00020\nH\u0086@¢\u0006\u0004\b\u001d\u0010\u000eJ*\u0010\"\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u00102\b\b\u0002\u0010!\u001a\u00020\nH\u0086@¢\u0006\u0004\b\"\u0010#J*\u0010$\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u00102\b\b\u0002\u0010!\u001a\u00020\nH\u0086@¢\u0006\u0004\b$\u0010#J8\u0010'\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u00102\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u0010H\u0086@¢\u0006\u0004\b'\u0010(J8\u0010)\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u00102\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u0010H\u0086@¢\u0006\u0004\b)\u0010(J6\u0010-\u001a\u00020\n2\u0006\u0010*\u001a\u00020\u00102\u0006\u0010%\u001a\u00020\u00102\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00100\u00152\u0006\u0010,\u001a\u00020\nH\u0086@¢\u0006\u0004\b-\u0010.J8\u0010/\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u00102\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u0010H\u0086@¢\u0006\u0004\b/\u0010(J'\u00100\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u00102\u0006\u0010%\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\u0010H\u0002¢\u0006\u0004\b0\u00101J\u0017\u00102\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u0010H\u0002¢\u0006\u0004\b2\u00103JB\u0010:\u001a\b\u0012\u0004\u0012\u00028\u000009\"\u0004\b\u0000\u001042\u0006\u00105\u001a\u00020\u00132\u001c\u00108\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u000007\u0012\u0006\u0012\u0004\u0018\u00010\u000106H\u0082@¢\u0006\u0004\b:\u0010;J\u0018\u0010>\u001a\u00020=2\u0006\u0010<\u001a\u00020\u0013H\u0082@¢\u0006\u0004\b>\u0010?J\u001f\u0010D\u001a\u00020C2\u0006\u0010@\u001a\u00020\u00132\u0006\u0010B\u001a\u00020AH\u0002¢\u0006\u0004\bD\u0010EJ5\u0010K\u001a\u00020\f2\u0006\u0010F\u001a\u00020C2\u0006\u0010G\u001a\u00020C2\u0006\u0010H\u001a\u00020C2\f\u0010J\u001a\b\u0012\u0004\u0012\u00020I0\u0015H\u0002¢\u0006\u0004\bK\u0010LJ\u000f\u0010M\u001a\u00020\fH\u0002¢\u0006\u0004\bM\u0010NJ\u0017\u0010P\u001a\u00020\f2\u0006\u0010O\u001a\u00020CH\u0002¢\u0006\u0004\bP\u0010QJ\u0017\u0010R\u001a\u00020\f2\u0006\u0010O\u001a\u00020CH\u0002¢\u0006\u0004\bR\u0010QJ5\u0010V\u001a\u00020\f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00132\u0012\u0010T\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00100S2\u0006\u0010U\u001a\u00020\u0016H\u0002¢\u0006\u0004\bV\u0010WJ\u001b\u0010Z\u001a\u0004\u0018\u00010Y2\b\u0010X\u001a\u0004\u0018\u00010\u0013H\u0002¢\u0006\u0004\bZ\u0010[J\u001d\u0010]\u001a\u00020\f2\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020I0\u0015H\u0002¢\u0006\u0004\b]\u0010^J!\u0010a\u001a\u0004\u0018\u00010\u00132\u0006\u0010`\u001a\u00020_2\u0006\u0010\u001f\u001a\u00020\u001eH\u0002¢\u0006\u0004\ba\u0010bJ9\u0010e\u001a\u0004\u0018\u00010\u00132\u0006\u0010`\u001a\u00020_2\u0006\u0010\u001f\u001a\u00020\u001e2\n\b\u0002\u0010c\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010d\u001a\u0004\u0018\u00010\u0010H\u0002¢\u0006\u0004\be\u0010fJ9\u0010g\u001a\u0004\u0018\u00010\u00102\u0006\u0010`\u001a\u00020_2\u0006\u0010\u001f\u001a\u00020\u001e2\n\b\u0002\u0010c\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010d\u001a\u0004\u0018\u00010\u0010H\u0002¢\u0006\u0004\bg\u0010hJ6\u0010i\u001a\u00020\f2\u0006\u0010F\u001a\u00020C2\u0006\u0010G\u001a\u00020C2\u0006\u0010H\u001a\u00020C2\f\u0010J\u001a\b\u0012\u0004\u0012\u00020I0\u0015H\u0082@¢\u0006\u0004\bi\u0010jJ\u001a\u0010m\u001a\u0004\u0018\u00010\u00102\u0006\u0010l\u001a\u00020kH\u0082@¢\u0006\u0004\bm\u0010nJ\"\u0010p\u001a\u0004\u0018\u00010\u00102\u0006\u0010l\u001a\u00020k2\u0006\u0010o\u001a\u00020\nH\u0082@¢\u0006\u0004\bp\u0010qJ\u0017\u0010r\u001a\u00020\u00132\u0006\u0010X\u001a\u00020\u0013H\u0002¢\u0006\u0004\br\u0010sJ\u0019\u0010u\u001a\u00020t2\b\u0010X\u001a\u0004\u0018\u00010\u0013H\u0002¢\u0006\u0004\bu\u0010vR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010wR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010xR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010yR\u0014\u0010{\u001a\u00020z8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b{\u0010|R\u0014\u0010~\u001a\u00020}8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b~\u0010\u007fR\u001b\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u00108\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0080\u0001\u0010\u0081\u0001R\u0019\u0010\u0082\u0001\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0082\u0001\u0010\u0083\u0001R\u001b\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u00138\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0084\u0001\u0010\u0085\u0001R\u0019\u0010\u0086\u0001\u001a\u00020t8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0086\u0001\u0010\u0087\u0001R\u0019\u0010\u0088\u0001\u001a\u00020t8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0088\u0001\u0010\u0087\u0001R\u001b\u0010\u0089\u0001\u001a\u0004\u0018\u00010C8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0089\u0001\u0010\u008a\u0001R\u001b\u0010\u008b\u0001\u001a\u0004\u0018\u00010C8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u008b\u0001\u0010\u008a\u0001R\u001b\u0010\u008c\u0001\u001a\u0004\u0018\u00010C8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u008c\u0001\u0010\u008a\u0001R!\u0010\u008d\u0001\u001a\n\u0012\u0004\u0012\u00020I\u0018\u00010\u00158\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u008d\u0001\u0010\u008e\u0001R\u001e\u0010\u0090\u0001\u001a\t\u0012\u0004\u0012\u00020\u00100\u008f\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0090\u0001\u0010\u0091\u0001R\u001e\u0010\u0092\u0001\u001a\t\u0012\u0004\u0012\u00020\u00130\u008f\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0092\u0001\u0010\u0091\u0001R0\u0010\u0094\u0001\u001a\u001b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00100S\u0012\u0004\u0012\u00020\u00160\u0093\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0094\u0001\u0010\u0095\u0001R0\u0010\u0096\u0001\u001a\u001b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00100S\u0012\u0004\u0012\u00020\u001c0\u0093\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0096\u0001\u0010\u0095\u0001RZ\u0010\u0099\u0001\u001aE\u0012\u0004\u0012\u00020\u0013\u0012:\u00128\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00100S\u0012\u0004\u0012\u00020\u00160\u0097\u0001j\u001b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00100S\u0012\u0004\u0012\u00020\u0016`\u0098\u00010\u0093\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0099\u0001\u0010\u0095\u0001R$\u0010\u009b\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00100\u009a\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u009b\u0001\u0010\u009c\u0001R\u0017\u0010\u009f\u0001\u001a\u00020\u00138BX\u0082\u0004¢\u0006\b\u001a\u0006\b\u009d\u0001\u0010\u009e\u0001¨\u0006¤\u0001"}, d2 = {"Lcom/arflix/tv/data/repository/simkl/SimklSyncService;", "", "Lcom/arflix/tv/data/api/SimklApi;", "simklApi", "Lcom/arflix/tv/data/repository/simkl/SimklAuthManager;", "authManager", "Lcom/arflix/tv/data/api/TmdbApi;", "tmdbApi", "<init>", "(Lcom/arflix/tv/data/api/SimklApi;Lcom/arflix/tv/data/repository/simkl/SimklAuthManager;Lcom/arflix/tv/data/api/TmdbApi;)V", "", "force", "Lx6/t0;", "syncIfNeeded", "(ZLd7/d;)Ljava/lang/Object;", "", "", "getWatchedMovies", "(Ld7/d;)Ljava/lang/Object;", "", "getWatchedEpisodes", "", "Lcom/arflix/tv/data/model/MediaItem;", "getWatchlistItems", "status", "forceRefresh", "getLibraryItems", "(Ljava/lang/String;ZLd7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/repository/ContinueWatchingItem;", "getContinueWatching", "Lcom/arflix/tv/data/model/MediaType;", "mediaType", "tmdbId", "isAnime", "addToWatchlist", "(Lcom/arflix/tv/data/model/MediaType;IZLd7/d;)Ljava/lang/Object;", "removeFromWatchlist", "season", "episode", "markWatched", "(Lcom/arflix/tv/data/model/MediaType;ILjava/lang/Integer;Ljava/lang/Integer;Ld7/d;)Ljava/lang/Object;", "markUnwatched", "showTmdbId", "episodes", "watched", "markSeasonWatched", "(IILjava/util/List;ZLd7/d;)Ljava/lang/Object;", "dismissContinueWatching", "episodeKey", "(III)Ljava/lang/String;", "episodePrefix", "(I)Ljava/lang/String;", "T", "label", "Lkotlin/Function1;", "Ld7/d;", "block", "Lcom/arflix/tv/data/repository/simkl/SimklSyncService$SnapshotFetch;", "fetchSnapshotPart", "(Ljava/lang/String;Lr7/l;Ld7/d;)Ljava/lang/Object;", "authHeader", "Lcom/arflix/tv/data/repository/simkl/SimklSyncService$SnapshotRefreshOutcome;", "refreshSnapshot", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", LinkHeader.Parameters.Type, "Lcom/google/gson/JsonElement;", "payload", "Lcom/arflix/tv/data/api/SimklAllItemsResponse;", "decodeAllItems", "(Ljava/lang/String;Lcom/google/gson/JsonElement;)Lcom/arflix/tv/data/api/SimklAllItemsResponse;", "movies", "shows", "anime", "Lcom/arflix/tv/data/api/SimklPlaybackItem;", "playback", "rebuildCaches", "(Lcom/arflix/tv/data/api/SimklAllItemsResponse;Lcom/arflix/tv/data/api/SimklAllItemsResponse;Lcom/arflix/tv/data/api/SimklAllItemsResponse;Ljava/util/List;)V", "clearCachedState", "()V", "response", "processMoviesResponse", "(Lcom/arflix/tv/data/api/SimklAllItemsResponse;)V", "processShowsResponse", "Lx6/x;", "key", "item", "cacheLibraryItem", "(Ljava/lang/String;Lx6/x;Lcom/arflix/tv/data/model/MediaItem;)V", "value", "Lcom/arflix/tv/data/api/SimklNextToWatchInfo;", "parseNextToWatch", "(Ljava/lang/String;)Lcom/arflix/tv/data/api/SimklNextToWatchInfo;", "items", "processPlayback", "(Ljava/util/List;)V", "Lcom/arflix/tv/data/api/SimklIds;", "ids", "externalKey", "(Lcom/arflix/tv/data/api/SimklIds;Lcom/arflix/tv/data/model/MediaType;)Ljava/lang/String;", LinkHeader.Parameters.Title, "year", "resolutionKey", "(Lcom/arflix/tv/data/api/SimklIds;Lcom/arflix/tv/data/model/MediaType;Ljava/lang/String;Ljava/lang/Integer;)Ljava/lang/String;", "resolvedTmdbId", "(Lcom/arflix/tv/data/api/SimklIds;Lcom/arflix/tv/data/model/MediaType;Ljava/lang/String;Ljava/lang/Integer;)Ljava/lang/Integer;", "resolveMissingTmdbIds", "(Lcom/arflix/tv/data/api/SimklAllItemsResponse;Lcom/arflix/tv/data/api/SimklAllItemsResponse;Lcom/arflix/tv/data/api/SimklAllItemsResponse;Ljava/util/List;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/repository/simkl/SimklSyncService$TmdbResolutionCandidate;", "candidate", "resolveCandidate", "(Lcom/arflix/tv/data/repository/simkl/SimklSyncService$TmdbResolutionCandidate;Ld7/d;)Ljava/lang/Object;", "constrainYear", "searchTmdbCandidate", "(Lcom/arflix/tv/data/repository/simkl/SimklSyncService$TmdbResolutionCandidate;ZLd7/d;)Ljava/lang/Object;", "normalizeTitle", "(Ljava/lang/String;)Ljava/lang/String;", "", "parseTimestamp", "(Ljava/lang/String;)J", "Lcom/arflix/tv/data/api/SimklApi;", "Lcom/arflix/tv/data/repository/simkl/SimklAuthManager;", "Lcom/arflix/tv/data/api/TmdbApi;", "Lcom/google/gson/Gson;", "gson", "Lcom/google/gson/Gson;", "Lua/a;", "syncMutex", "Lua/a;", "activeTokenScope", "Ljava/lang/Integer;", "hasInitialSnapshot", "Z", "lastActivityTimestamp", "Ljava/lang/String;", "lastActivityCheckTime", "J", "lastSyncAttemptTime", "snapshotMovies", "Lcom/arflix/tv/data/api/SimklAllItemsResponse;", "snapshotShows", "snapshotAnime", "snapshotPlayback", "Ljava/util/List;", "", "cachedWatchedMovies", "Ljava/util/Set;", "cachedWatchedEpisodes", "", "cachedWatchlist", "Ljava/util/Map;", "cachedContinueWatching", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "cachedLibraryItems", "j$/util/concurrent/ConcurrentHashMap", "resolvedExternalIds", "Lj$/util/concurrent/ConcurrentHashMap;", "getClientId", "()Ljava/lang/String;", "clientId", "Companion", "SnapshotFetch", "SnapshotRefreshOutcome", "TmdbResolutionCandidate", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SimklSyncService {
    private static final long FAILED_SYNC_BACKOFF_MS = 60000;
    private static final long SNAPSHOT_TTL_MS = 900000;
    private Integer activeTokenScope;
    private final SimklAuthManager authManager;
    private boolean hasInitialSnapshot;
    private long lastActivityCheckTime;
    private String lastActivityTimestamp;
    private long lastSyncAttemptTime;
    private final SimklApi simklApi;
    private SimklAllItemsResponse snapshotAnime;
    private SimklAllItemsResponse snapshotMovies;
    private List<SimklPlaybackItem> snapshotPlayback;
    private SimklAllItemsResponse snapshotShows;
    private final TmdbApi tmdbApi;
    public static final int $stable = 8;
    private static final m DIACRITICS_REGEX = new m("\\p{M}+");
    private static final m NON_ALPHA_NUM_REGEX = new m("[^a-z0-9]+");
    private final Gson gson = new Gson();
    private final ua.a syncMutex = e.a();
    private final Set<Integer> cachedWatchedMovies = new LinkedHashSet();
    private final Set<String> cachedWatchedEpisodes = new LinkedHashSet();
    private final Map<x, MediaItem> cachedWatchlist = new LinkedHashMap();
    private final Map<x, ContinueWatchingItem> cachedContinueWatching = new LinkedHashMap();
    private final Map<String, LinkedHashMap<x, MediaItem>> cachedLibraryItems = new LinkedHashMap();
    private final ConcurrentHashMap<String, Integer> resolvedExternalIds = new ConcurrentHashMap<>();

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0019\u0012\b\u0010\u0003\u001a\u0004\u0018\u00018\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\r\u001a\u0004\u0018\u00018\u0000HÆ\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J*\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00018\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0015\u0010\u0003\u001a\u0004\u0018\u00018\u0000¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/arflix/tv/data/repository/simkl/SimklSyncService$SnapshotFetch;", "T", "", "value", "succeeded", "", "<init>", "(Ljava/lang/Object;Z)V", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getSucceeded", "()Z", "component1", "component2", "copy", "(Ljava/lang/Object;Z)Lcom/arflix/tv/data/repository/simkl/SimklSyncService$SnapshotFetch;", "equals", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SnapshotFetch<T> {
        private final boolean succeeded;
        private final T value;

        public SnapshotFetch(T t2, boolean z) {
            this.value = t2;
            this.succeeded = z;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SnapshotFetch copy$default(SnapshotFetch snapshotFetch, Object obj, boolean z, int i10, Object obj2) {
            if ((i10 & 1) != 0) {
                obj = snapshotFetch.value;
            }
            if ((i10 & 2) != 0) {
                z = snapshotFetch.succeeded;
            }
            return snapshotFetch.copy(obj, z);
        }

        public final T component1() {
            return this.value;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getSucceeded() {
            return this.succeeded;
        }

        public final SnapshotFetch<T> copy(T value, boolean succeeded) {
            return new SnapshotFetch<>(value, succeeded);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SnapshotFetch)) {
                return false;
            }
            SnapshotFetch snapshotFetch = (SnapshotFetch) other;
            return p.a(this.value, snapshotFetch.value) && this.succeeded == snapshotFetch.succeeded;
        }

        public final boolean getSucceeded() {
            return this.succeeded;
        }

        public final T getValue() {
            return this.value;
        }

        public int hashCode() {
            T t2 = this.value;
            return ((t2 == null ? 0 : t2.hashCode()) * 31) + (this.succeeded ? 1231 : 1237);
        }

        public String toString() {
            return "SnapshotFetch(value=" + this.value + ", succeeded=" + this.succeeded + ")";
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/arflix/tv/data/repository/simkl/SimklSyncService$SnapshotRefreshOutcome;", "", "hasUsableSnapshot", "", "complete", "<init>", "(ZZ)V", "getHasUsableSnapshot", "()Z", "getComplete", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SnapshotRefreshOutcome {
        private final boolean complete;
        private final boolean hasUsableSnapshot;

        public SnapshotRefreshOutcome(boolean z, boolean z5) {
            this.hasUsableSnapshot = z;
            this.complete = z5;
        }

        public static /* synthetic */ SnapshotRefreshOutcome copy$default(SnapshotRefreshOutcome snapshotRefreshOutcome, boolean z, boolean z5, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                z = snapshotRefreshOutcome.hasUsableSnapshot;
            }
            if ((i10 & 2) != 0) {
                z5 = snapshotRefreshOutcome.complete;
            }
            return snapshotRefreshOutcome.copy(z, z5);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getHasUsableSnapshot() {
            return this.hasUsableSnapshot;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getComplete() {
            return this.complete;
        }

        public final SnapshotRefreshOutcome copy(boolean hasUsableSnapshot, boolean complete) {
            return new SnapshotRefreshOutcome(hasUsableSnapshot, complete);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SnapshotRefreshOutcome)) {
                return false;
            }
            SnapshotRefreshOutcome snapshotRefreshOutcome = (SnapshotRefreshOutcome) other;
            return this.hasUsableSnapshot == snapshotRefreshOutcome.hasUsableSnapshot && this.complete == snapshotRefreshOutcome.complete;
        }

        public final boolean getComplete() {
            return this.complete;
        }

        public final boolean getHasUsableSnapshot() {
            return this.hasUsableSnapshot;
        }

        public int hashCode() {
            return ((this.hasUsableSnapshot ? 1231 : 1237) * 31) + (this.complete ? 1231 : 1237);
        }

        public String toString() {
            return "SnapshotRefreshOutcome(hasUsableSnapshot=" + this.hasUsableSnapshot + ", complete=" + this.complete + ")";
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0013J:\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u0010\u001aJ\u0014\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001e\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\u001f\u001a\u00020\u0007HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013¨\u0006 "}, d2 = {"Lcom/arflix/tv/data/repository/simkl/SimklSyncService$TmdbResolutionCandidate;", "", "mediaType", "Lcom/arflix/tv/data/model/MediaType;", "ids", "Lcom/arflix/tv/data/api/SimklIds;", LinkHeader.Parameters.Title, "", "year", "", "<init>", "(Lcom/arflix/tv/data/model/MediaType;Lcom/arflix/tv/data/api/SimklIds;Ljava/lang/String;Ljava/lang/Integer;)V", "getMediaType", "()Lcom/arflix/tv/data/model/MediaType;", "getIds", "()Lcom/arflix/tv/data/api/SimklIds;", "getTitle", "()Ljava/lang/String;", "getYear", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "component4", "copy", "(Lcom/arflix/tv/data/model/MediaType;Lcom/arflix/tv/data/api/SimklIds;Ljava/lang/String;Ljava/lang/Integer;)Lcom/arflix/tv/data/repository/simkl/SimklSyncService$TmdbResolutionCandidate;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class TmdbResolutionCandidate {
        private final SimklIds ids;
        private final MediaType mediaType;
        private final String title;
        private final Integer year;

        public TmdbResolutionCandidate(MediaType mediaType, SimklIds simklIds, String str, Integer num) {
            this.mediaType = mediaType;
            this.ids = simklIds;
            this.title = str;
            this.year = num;
        }

        public static /* synthetic */ TmdbResolutionCandidate copy$default(TmdbResolutionCandidate tmdbResolutionCandidate, MediaType mediaType, SimklIds simklIds, String str, Integer num, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                mediaType = tmdbResolutionCandidate.mediaType;
            }
            if ((i10 & 2) != 0) {
                simklIds = tmdbResolutionCandidate.ids;
            }
            if ((i10 & 4) != 0) {
                str = tmdbResolutionCandidate.title;
            }
            if ((i10 & 8) != 0) {
                num = tmdbResolutionCandidate.year;
            }
            return tmdbResolutionCandidate.copy(mediaType, simklIds, str, num);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final MediaType getMediaType() {
            return this.mediaType;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final SimklIds getIds() {
            return this.ids;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Integer getYear() {
            return this.year;
        }

        public final TmdbResolutionCandidate copy(MediaType mediaType, SimklIds ids, String title, Integer year) {
            return new TmdbResolutionCandidate(mediaType, ids, title, year);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TmdbResolutionCandidate)) {
                return false;
            }
            TmdbResolutionCandidate tmdbResolutionCandidate = (TmdbResolutionCandidate) other;
            return this.mediaType == tmdbResolutionCandidate.mediaType && p.a(this.ids, tmdbResolutionCandidate.ids) && p.a(this.title, tmdbResolutionCandidate.title) && p.a(this.year, tmdbResolutionCandidate.year);
        }

        public final SimklIds getIds() {
            return this.ids;
        }

        public final MediaType getMediaType() {
            return this.mediaType;
        }

        public final String getTitle() {
            return this.title;
        }

        public final Integer getYear() {
            return this.year;
        }

        public int hashCode() {
            int iHashCode = (this.ids.hashCode() + (this.mediaType.hashCode() * 31)) * 31;
            String str = this.title;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            Integer num = this.year;
            return iHashCode2 + (num != null ? num.hashCode() : 0);
        }

        public String toString() {
            return "TmdbResolutionCandidate(mediaType=" + this.mediaType + ", ids=" + this.ids + ", title=" + this.title + ", year=" + this.year + ")";
        }
    }

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MediaType.values().length];
            try {
                iArr[MediaType.MOVIE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[MediaType.TV.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.simkl.SimklSyncService$addToWatchlist$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.simkl.SimklSyncService", f = "SimklSyncService.kt", l = {611, 627}, m = "addToWatchlist", v = 2)
    public static final class AnonymousClass1 extends c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklSyncService.this.addToWatchlist(null, 0, false, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.simkl.SimklSyncService$dismissContinueWatching$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.simkl.SimklSyncService", f = "SimklSyncService.kt", l = {826, 829, 843}, m = "dismissContinueWatching", v = 2)
    public static final class C12491 extends c {
        int I$0;
        int I$1;
        int I$2;
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        public C12491(d<? super C12491> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklSyncService.this.dismissContinueWatching(null, 0, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.simkl.SimklSyncService$fetchSnapshotPart$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.simkl.SimklSyncService", f = "SimklSyncService.kt", l = {143}, m = "fetchSnapshotPart", v = 2)
    public static final class C12501<T> extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C12501(d<? super C12501> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklSyncService.this.fetchSnapshotPart(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.simkl.SimklSyncService$getContinueWatching$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.simkl.SimklSyncService", f = "SimklSyncService.kt", l = {604}, m = "getContinueWatching", v = 2)
    public static final class C12511 extends c {
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C12511(d<? super C12511> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklSyncService.this.getContinueWatching(false, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.simkl.SimklSyncService$getLibraryItems$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.simkl.SimklSyncService", f = "SimklSyncService.kt", l = {599}, m = "getLibraryItems", v = 2)
    public static final class C12521 extends c {
        Object L$0;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C12521(d<? super C12521> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklSyncService.this.getLibraryItems(null, false, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.simkl.SimklSyncService$getWatchedEpisodes$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.simkl.SimklSyncService", f = "SimklSyncService.kt", l = {589}, m = "getWatchedEpisodes", v = 2)
    public static final class C12531 extends c {
        int label;
        /* synthetic */ Object result;

        public C12531(d<? super C12531> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklSyncService.this.getWatchedEpisodes(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.simkl.SimklSyncService$getWatchedMovies$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.simkl.SimklSyncService", f = "SimklSyncService.kt", l = {584}, m = "getWatchedMovies", v = 2)
    public static final class C12541 extends c {
        int label;
        /* synthetic */ Object result;

        public C12541(d<? super C12541> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklSyncService.this.getWatchedMovies(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.simkl.SimklSyncService$getWatchlistItems$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.simkl.SimklSyncService", f = "SimklSyncService.kt", l = {594}, m = "getWatchlistItems", v = 2)
    public static final class C12551 extends c {
        int label;
        /* synthetic */ Object result;

        public C12551(d<? super C12551> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklSyncService.this.getWatchlistItems(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.simkl.SimklSyncService$markSeasonWatched$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.simkl.SimklSyncService", f = "SimklSyncService.kt", l = {776, 793, 795}, m = "markSeasonWatched", v = 2)
    public static final class C12561 extends c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C12561(d<? super C12561> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklSyncService.this.markSeasonWatched(0, 0, null, false, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.simkl.SimklSyncService$markUnwatched$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.simkl.SimklSyncService", f = "SimklSyncService.kt", l = {733, 750}, m = "markUnwatched", v = 2)
    public static final class C12571 extends c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public C12571(d<? super C12571> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklSyncService.this.markUnwatched(null, 0, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.simkl.SimklSyncService$markWatched$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.simkl.SimklSyncService", f = "SimklSyncService.kt", l = {696, 713}, m = "markWatched", v = 2)
    public static final class C12581 extends c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public C12581(d<? super C12581> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklSyncService.this.markWatched(null, 0, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.simkl.SimklSyncService$refreshSnapshot$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/repository/simkl/SimklSyncService$SnapshotRefreshOutcome;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/repository/simkl/SimklSyncService$SnapshotRefreshOutcome;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.simkl.SimklSyncService$refreshSnapshot$2", f = "SimklSyncService.kt", l = {TsExtractor.TS_STREAM_TYPE_AC4, 173, 174, 175, 190}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends j implements r7.p<k0, d<? super SnapshotRefreshOutcome>, Object> {
        final /* synthetic */ String $authHeader;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(String str, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$authHeader = str;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass2 anonymousClass2 = SimklSyncService.this.new AnonymousClass2(this.$authHeader, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        /* JADX WARN: Removed duplicated region for block: B:25:0x0155  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x0176  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0181  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0192  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x01a3  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x01b4  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x01c7  */
        /* JADX WARN: Removed duplicated region for block: B:45:0x01d6  */
        /* JADX WARN: Removed duplicated region for block: B:48:0x01df  */
        /* JADX WARN: Removed duplicated region for block: B:51:0x01f5  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x020b  */
        /* JADX WARN: Removed duplicated region for block: B:65:0x0230  */
        /* JADX WARN: Removed duplicated region for block: B:67:0x0233  */
        /* JADX WARN: Removed duplicated region for block: B:74:0x0271  */
        /* JADX WARN: Removed duplicated region for block: B:75:0x0273  */
        /* JADX WARN: Removed duplicated region for block: B:78:0x027a  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r21) {
            /*
                Method dump skipped, instruction units count: 651
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.simkl.SimklSyncService.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super SnapshotRefreshOutcome> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.simkl.SimklSyncService$removeFromWatchlist$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.simkl.SimklSyncService", f = "SimklSyncService.kt", l = {643, 668, 678}, m = "removeFromWatchlist", v = 2)
    public static final class C12591 extends c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C12591(d<? super C12591> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklSyncService.this.removeFromWatchlist(null, 0, false, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.simkl.SimklSyncService$resolveCandidate$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.simkl.SimklSyncService", f = "SimklSyncService.kt", l = {482, 487, 508, 509}, m = "resolveCandidate", v = 2)
    public static final class C12601 extends c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C12601(d<? super C12601> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklSyncService.this.resolveCandidate(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.simkl.SimklSyncService$resolveMissingTmdbIds$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.simkl.SimklSyncService$resolveMissingTmdbIds$2", f = "SimklSyncService.kt", l = {476}, m = "invokeSuspend", v = 2)
    public static final class C12612 extends j implements r7.p<k0, d<? super t0>, Object> {
        final /* synthetic */ SimklAllItemsResponse $anime;
        final /* synthetic */ SimklAllItemsResponse $movies;
        final /* synthetic */ List<SimklPlaybackItem> $playback;
        final /* synthetic */ SimklAllItemsResponse $shows;
        int I$0;
        int I$1;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        final /* synthetic */ SimklSyncService this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C12612(SimklAllItemsResponse simklAllItemsResponse, SimklAllItemsResponse simklAllItemsResponse2, SimklAllItemsResponse simklAllItemsResponse3, List<SimklPlaybackItem> list, SimklSyncService simklSyncService, d<? super C12612> dVar) {
            super(2, dVar);
            this.$movies = simklAllItemsResponse;
            this.$shows = simklAllItemsResponse2;
            this.$anime = simklAllItemsResponse3;
            this.$playback = list;
            this.this$0 = simklSyncService;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C12612 c12612 = new C12612(this.$movies, this.$shows, this.$anime, this.$playback, this.this$0, dVar);
            c12612.L$0 = obj;
            return c12612;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Iterator it;
            int i10;
            String strResolutionKey;
            k0 k0Var = (k0) this.L$0;
            int i11 = this.label;
            if (i11 == 0) {
                k2.c.G(obj);
                SimklAllItemsResponse simklAllItemsResponse = this.$movies;
                SimklAllItemsResponse simklAllItemsResponse2 = this.$shows;
                SimklAllItemsResponse simklAllItemsResponse3 = this.$anime;
                List<SimklPlaybackItem> list = this.$playback;
                z6.c cVarN = t7.a.n();
                Iterable movies = simklAllItemsResponse.getMovies();
                List<SimklHistoryShowItem> list2 = z.f19728i;
                if (movies == null) {
                    movies = list2;
                }
                Iterator it2 = movies.iterator();
                while (it2.hasNext()) {
                    SimklMovieRef movie = ((SimklHistoryMovieItem) it2.next()).getMovie();
                    TmdbResolutionCandidate tmdbResolutionCandidate = movie != null ? new TmdbResolutionCandidate(MediaType.MOVIE, movie.getIds(), movie.getTitle(), movie.getYear()) : null;
                    if (tmdbResolutionCandidate != null) {
                        cVarN.add(tmdbResolutionCandidate);
                    }
                }
                List<SimklHistoryShowItem> shows = simklAllItemsResponse2.getShows();
                if (shows == null) {
                    shows = list2;
                }
                List<SimklHistoryShowItem> anime = simklAllItemsResponse2.getAnime();
                if (anime == null) {
                    anime = list2;
                }
                ArrayList arrayListI0 = kotlin.collections.x.I0(shows, anime);
                List<SimklHistoryShowItem> shows2 = simklAllItemsResponse3.getShows();
                if (shows2 == null) {
                    shows2 = list2;
                }
                ArrayList arrayListI02 = kotlin.collections.x.I0(arrayListI0, shows2);
                List<SimklHistoryShowItem> anime2 = simklAllItemsResponse3.getAnime();
                if (anime2 != null) {
                    list2 = anime2;
                }
                Iterator it3 = kotlin.collections.x.I0(arrayListI02, list2).iterator();
                while (it3.hasNext()) {
                    SimklShowRef show = ((SimklHistoryShowItem) it3.next()).getShow();
                    TmdbResolutionCandidate tmdbResolutionCandidate2 = show != null ? new TmdbResolutionCandidate(MediaType.TV, show.getIds(), show.getTitle(), show.getYear()) : null;
                    if (tmdbResolutionCandidate2 != null) {
                        cVarN.add(tmdbResolutionCandidate2);
                    }
                }
                for (SimklPlaybackItem simklPlaybackItem : list) {
                    SimklMovieRef movie2 = simklPlaybackItem.getMovie();
                    if (movie2 != null) {
                        cVarN.add(new TmdbResolutionCandidate(MediaType.MOVIE, movie2.getIds(), movie2.getTitle(), movie2.getYear()));
                    }
                    SimklShowRef show2 = simklPlaybackItem.getShow();
                    if (show2 == null) {
                        show2 = simklPlaybackItem.getAnime();
                    }
                    if (show2 != null) {
                        cVarN.add(new TmdbResolutionCandidate(MediaType.TV, show2.getIds(), show2.getTitle(), show2.getYear()));
                    }
                }
                z6.c cVarE = t7.a.e(cVarN);
                SimklSyncService simklSyncService = this.this$0;
                ArrayList arrayList = new ArrayList();
                ListIterator listIterator = cVarE.listIterator(0);
                while (true) {
                    z6.a aVar = (z6.a) listIterator;
                    if (!aVar.hasNext()) {
                        break;
                    }
                    Object next = aVar.next();
                    TmdbResolutionCandidate tmdbResolutionCandidate3 = (TmdbResolutionCandidate) next;
                    if (tmdbResolutionCandidate3.getIds().getTmdb() == null && (strResolutionKey = simklSyncService.resolutionKey(tmdbResolutionCandidate3.getIds(), tmdbResolutionCandidate3.getMediaType(), tmdbResolutionCandidate3.getTitle(), tmdbResolutionCandidate3.getYear())) != null && (!simklSyncService.resolvedExternalIds.containsKey(strResolutionKey))) {
                        arrayList.add(next);
                    }
                }
                SimklSyncService simklSyncService2 = this.this$0;
                HashSet hashSet = new HashSet();
                ArrayList arrayList2 = new ArrayList();
                for (Object obj2 : arrayList) {
                    TmdbResolutionCandidate tmdbResolutionCandidate4 = (TmdbResolutionCandidate) obj2;
                    if (hashSet.add(simklSyncService2.resolutionKey(tmdbResolutionCandidate4.getIds(), tmdbResolutionCandidate4.getMediaType(), tmdbResolutionCandidate4.getTitle(), tmdbResolutionCandidate4.getYear()))) {
                        arrayList2.add(obj2);
                    }
                }
                i iVarA = ua.j.a(6);
                SimklSyncService simklSyncService3 = this.this$0;
                ArrayList arrayList3 = new ArrayList(s.U(arrayList2, 10));
                Iterator it4 = arrayList2.iterator();
                while (it4.hasNext()) {
                    arrayList3.add(m0.e(3, null, k0Var, new SimklSyncService$resolveMissingTmdbIds$2$1$1(iVarA, simklSyncService3, (TmdbResolutionCandidate) it4.next(), null)));
                }
                it = arrayList3.iterator();
                i10 = 0;
            } else {
                if (i11 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                i10 = this.I$0;
                it = (Iterator) this.L$4;
                k2.c.G(obj);
            }
            while (it.hasNext()) {
                p0 p0Var = (p0) it.next();
                this.L$0 = null;
                this.L$1 = null;
                this.L$2 = null;
                this.L$3 = null;
                this.L$4 = it;
                this.L$5 = null;
                this.L$6 = null;
                this.I$0 = i10;
                this.I$1 = 0;
                this.label = 1;
                Object objO = p0Var.o(this);
                e7.a aVar2 = e7.a.f15033i;
                if (objO == aVar2) {
                    return aVar2;
                }
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((C12612) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.simkl.SimklSyncService$searchTmdbCandidate$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.simkl.SimklSyncService", f = "SimklSyncService.kt", l = {521, 528}, m = "searchTmdbCandidate", v = 2)
    public static final class C12621 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C12621(d<? super C12621> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklSyncService.this.searchTmdbCandidate(null, false, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.simkl.SimklSyncService$syncIfNeeded$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.simkl.SimklSyncService", f = "SimklSyncService.kt", l = {864, 84, 106, 114}, m = "syncIfNeeded", v = 2)
    public static final class C12631 extends c {
        int I$0;
        int I$1;
        int I$2;
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C12631(d<? super C12631> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklSyncService.this.syncIfNeeded(false, this);
        }
    }

    @Inject
    public SimklSyncService(SimklApi simklApi, SimklAuthManager simklAuthManager, TmdbApi tmdbApi) {
        this.simklApi = simklApi;
        this.authManager = simklAuthManager;
        this.tmdbApi = tmdbApi;
    }

    public static /* synthetic */ Object addToWatchlist$default(SimklSyncService simklSyncService, MediaType mediaType, int i10, boolean z, d dVar, int i11, Object obj) {
        if ((i11 & 4) != 0) {
            z = false;
        }
        return simklSyncService.addToWatchlist(mediaType, i10, z, dVar);
    }

    private final void cacheLibraryItem(String status, x key, MediaItem item) {
        String string;
        if (status == null || (string = o.L0(status).toString()) == null) {
            return;
        }
        String lowerCase = string.toLowerCase(Locale.ROOT);
        if (o.h0(lowerCase)) {
            lowerCase = null;
        }
        if (lowerCase == null) {
            return;
        }
        Map<String, LinkedHashMap<x, MediaItem>> map = this.cachedLibraryItems;
        LinkedHashMap<x, MediaItem> linkedHashMap = map.get(lowerCase);
        if (linkedHashMap == null) {
            linkedHashMap = new LinkedHashMap<>();
            map.put(lowerCase, linkedHashMap);
        }
        linkedHashMap.put(key, item);
    }

    private final void clearCachedState() {
        this.activeTokenScope = null;
        this.hasInitialSnapshot = false;
        this.lastActivityTimestamp = null;
        this.lastActivityCheckTime = 0L;
        this.lastSyncAttemptTime = 0L;
        this.snapshotMovies = null;
        this.snapshotShows = null;
        this.snapshotAnime = null;
        this.snapshotPlayback = null;
        this.cachedWatchedMovies.clear();
        this.cachedWatchedEpisodes.clear();
        this.cachedWatchlist.clear();
        this.cachedContinueWatching.clear();
        this.cachedLibraryItems.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SimklAllItemsResponse decodeAllItems(String type, JsonElement payload) {
        if (payload.isJsonObject()) {
            return (SimklAllItemsResponse) this.gson.fromJson(payload, SimklAllItemsResponse.class);
        }
        if (!payload.isJsonArray()) {
            throw new IllegalStateException(a0.c.l("Unexpected Simkl ", type, " library response"));
        }
        int iHashCode = type.hashCode();
        if (iHashCode != -1068259517) {
            if (iHashCode != 92962932) {
                if (iHashCode == 109413654 && type.equals("shows")) {
                    JsonArray asJsonArray = payload.getAsJsonArray();
                    ArrayList arrayList = new ArrayList(s.U(asJsonArray, 10));
                    Iterator<JsonElement> it = asJsonArray.iterator();
                    while (it.hasNext()) {
                        arrayList.add((SimklHistoryShowItem) this.gson.fromJson(it.next(), SimklHistoryShowItem.class));
                    }
                    return new SimklAllItemsResponse(null, arrayList, null, 5, null);
                }
            } else if (type.equals("anime")) {
                JsonArray asJsonArray2 = payload.getAsJsonArray();
                ArrayList arrayList2 = new ArrayList(s.U(asJsonArray2, 10));
                Iterator<JsonElement> it2 = asJsonArray2.iterator();
                while (it2.hasNext()) {
                    arrayList2.add((SimklHistoryShowItem) this.gson.fromJson(it2.next(), SimklHistoryShowItem.class));
                }
                return new SimklAllItemsResponse(null, null, arrayList2, 3, null);
            }
        } else if (type.equals("movies")) {
            JsonArray asJsonArray3 = payload.getAsJsonArray();
            ArrayList arrayList3 = new ArrayList(s.U(asJsonArray3, 10));
            Iterator<JsonElement> it3 = asJsonArray3.iterator();
            while (it3.hasNext()) {
                arrayList3.add((SimklHistoryMovieItem) this.gson.fromJson(it3.next(), SimklHistoryMovieItem.class));
            }
            return new SimklAllItemsResponse(arrayList3, null, null, 6, null);
        }
        throw new IllegalArgumentException("Unsupported Simkl library type: ".concat(type));
    }

    public static /* synthetic */ Object dismissContinueWatching$default(SimklSyncService simklSyncService, MediaType mediaType, int i10, Integer num, Integer num2, d dVar, int i11, Object obj) {
        if ((i11 & 4) != 0) {
            num = null;
        }
        if ((i11 & 8) != 0) {
            num2 = null;
        }
        return simklSyncService.dismissContinueWatching(mediaType, i10, num, num2, dVar);
    }

    private final String episodeKey(int tmdbId, int season, int episode) {
        StringBuilder sbV = androidx.compose.foundation.c.v("show_tmdb:", tmdbId, ":", season, ":");
        sbV.append(episode);
        return sbV.toString();
    }

    private final String episodePrefix(int tmdbId) {
        return androidx.compose.foundation.c.o(tmdbId, "show_tmdb:", ":");
    }

    private final String externalKey(SimklIds ids, MediaType mediaType) {
        String imdb = ids.getImdb();
        if (imdb != null && !o.h0(imdb)) {
            return androidx.compose.foundation.c.t(mediaType.name(), ":imdb:", ids.getImdb());
        }
        String tvdb = ids.getTvdb();
        if (tvdb == null || o.h0(tvdb)) {
            return null;
        }
        return androidx.compose.foundation.c.t(mediaType.name(), ":tvdb:", ids.getTvdb());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final <T> java.lang.Object fetchSnapshotPart(java.lang.String r10, r7.l<? super d7.d<? super T>, ? extends java.lang.Object> r11, d7.d<? super com.arflix.tv.data.repository.simkl.SimklSyncService.SnapshotFetch<T>> r12) {
        /*
            r9 = this;
            boolean r0 = r12 instanceof com.arflix.tv.data.repository.simkl.SimklSyncService.C12501
            if (r0 == 0) goto L13
            r0 = r12
            com.arflix.tv.data.repository.simkl.SimklSyncService$fetchSnapshotPart$1 r0 = (com.arflix.tv.data.repository.simkl.SimklSyncService.C12501) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.simkl.SimklSyncService$fetchSnapshotPart$1 r0 = new com.arflix.tv.data.repository.simkl.SimklSyncService$fetchSnapshotPart$1
            r0.<init>(r12)
        L18:
            java.lang.Object r12 = r0.result
            int r1 = r0.label
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L3c
            if (r1 != r3) goto L34
            java.lang.Object r10 = r0.L$1
            r7.l r10 = (r7.l) r10
            java.lang.Object r10 = r0.L$0
            java.lang.String r10 = (java.lang.String) r10
            k2.c.G(r12)     // Catch: java.lang.Exception -> L2e java.util.concurrent.CancellationException -> L31
            goto L4e
        L2e:
            r0 = move-exception
            r11 = r0
            goto L54
        L31:
            r0 = move-exception
            r10 = r0
            goto L6f
        L34:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L3c:
            k2.c.G(r12)
            r0.L$0 = r10     // Catch: java.lang.Exception -> L2e java.util.concurrent.CancellationException -> L31
            r0.L$1 = r2     // Catch: java.lang.Exception -> L2e java.util.concurrent.CancellationException -> L31
            r0.label = r3     // Catch: java.lang.Exception -> L2e java.util.concurrent.CancellationException -> L31
            java.lang.Object r12 = r11.invoke(r0)     // Catch: java.lang.Exception -> L2e java.util.concurrent.CancellationException -> L31
            e7.a r11 = e7.a.f15033i
            if (r12 != r11) goto L4e
            return r11
        L4e:
            com.arflix.tv.data.repository.simkl.SimklSyncService$SnapshotFetch r11 = new com.arflix.tv.data.repository.simkl.SimklSyncService$SnapshotFetch     // Catch: java.lang.Exception -> L2e java.util.concurrent.CancellationException -> L31
            r11.<init>(r12, r3)     // Catch: java.lang.Exception -> L2e java.util.concurrent.CancellationException -> L31
            return r11
        L54:
            com.arflix.tv.util.AppLogger r3 = com.arflix.tv.util.AppLogger.INSTANCE
            java.lang.String r11 = r11.getMessage()
            java.lang.String r12 = " sync failed: "
            java.lang.String r5 = androidx.compose.foundation.c.t(r10, r12, r11)
            r7 = 4
            r8 = 0
            java.lang.String r4 = "SimklSyncService"
            r6 = 0
            com.arflix.tv.util.AppLogger.e$default(r3, r4, r5, r6, r7, r8)
            com.arflix.tv.data.repository.simkl.SimklSyncService$SnapshotFetch r10 = new com.arflix.tv.data.repository.simkl.SimklSyncService$SnapshotFetch
            r11 = 0
            r10.<init>(r2, r11)
            return r10
        L6f:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.simkl.SimklSyncService.fetchSnapshotPart(java.lang.String, r7.l, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getClientId() {
        return Constants.INSTANCE.getSIMKL_CLIENT_ID();
    }

    public static /* synthetic */ Object getContinueWatching$default(SimklSyncService simklSyncService, boolean z, d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        return simklSyncService.getContinueWatching(z, dVar);
    }

    public static /* synthetic */ Object getLibraryItems$default(SimklSyncService simklSyncService, String str, boolean z, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = false;
        }
        return simklSyncService.getLibraryItems(str, z, dVar);
    }

    public static /* synthetic */ Object markUnwatched$default(SimklSyncService simklSyncService, MediaType mediaType, int i10, Integer num, Integer num2, d dVar, int i11, Object obj) {
        if ((i11 & 4) != 0) {
            num = null;
        }
        if ((i11 & 8) != 0) {
            num2 = null;
        }
        return simklSyncService.markUnwatched(mediaType, i10, num, num2, dVar);
    }

    public static /* synthetic */ Object markWatched$default(SimklSyncService simklSyncService, MediaType mediaType, int i10, Integer num, Integer num2, d dVar, int i11, Object obj) {
        if ((i11 & 4) != 0) {
            num = null;
        }
        if ((i11 & 8) != 0) {
            num2 = null;
        }
        return simklSyncService.markWatched(mediaType, i10, num, num2, dVar);
    }

    private final String normalizeTitle(String value) {
        return g.o(NON_ALPHA_NUM_REGEX, u.O(DIACRITICS_REGEX.f("", Normalizer.normalize(value, Normalizer.Form.NFD)).toLowerCase(Locale.US), "&", " and ", false), " ");
    }

    private final SimklNextToWatchInfo parseNextToWatch(String value) {
        String string;
        String upperCase = (value == null || (string = o.L0(value).toString()) == null) ? null : string.toUpperCase(Locale.ROOT);
        if (upperCase == null) {
            upperCase = "";
        }
        if (!o.h0(upperCase)) {
            Matcher matcher = Pattern.compile("^(?:S(\\d+))?E(\\d+)$").matcher(upperCase);
            k kVar = !matcher.matches() ? null : new k(matcher, upperCase);
            if (kVar != null) {
                Integer numR = u.R((String) ((n0) kVar.b()).get(1));
                int iIntValue = numR != null ? numR.intValue() : 1;
                Integer numR2 = u.R((String) ((n0) kVar.b()).get(2));
                if (numR2 != null) {
                    return new SimklNextToWatchInfo(null, Integer.valueOf(iIntValue), numR2, null, 9, null);
                }
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final long parseTimestamp(java.lang.String r4) {
        /*
            r3 = this;
            r0 = 0
            if (r4 == 0) goto L19
            j$.time.Instant r4 = j$.time.Instant.parse(r4)     // Catch: java.lang.Throwable -> L12
            if (r4 == 0) goto L19
            long r1 = r4.toEpochMilli()     // Catch: java.lang.Throwable -> L12
            java.lang.Long r4 = java.lang.Long.valueOf(r1)     // Catch: java.lang.Throwable -> L12
            goto L1a
        L12:
            r4 = move-exception
            x6.c0 r1 = new x6.c0
            r1.<init>(r4)
            goto L1b
        L19:
            r4 = r0
        L1a:
            r1 = r4
        L1b:
            boolean r4 = r1 instanceof x6.c0
            if (r4 == 0) goto L21
            goto L22
        L21:
            r0 = r1
        L22:
            java.lang.Long r0 = (java.lang.Long) r0
            if (r0 == 0) goto L2b
            long r0 = r0.longValue()
            goto L2d
        L2b:
            r0 = 0
        L2d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.simkl.SimklSyncService.parseTimestamp(java.lang.String):long");
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void processMoviesResponse(com.arflix.tv.data.api.SimklAllItemsResponse r55) {
        /*
            Method dump skipped, instruction units count: 406
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.simkl.SimklSyncService.processMoviesResponse(com.arflix.tv.data.api.SimklAllItemsResponse):void");
    }

    private final void processPlayback(List<SimklPlaybackItem> items) {
        Integer season;
        Integer number;
        for (SimklPlaybackItem simklPlaybackItem : items) {
            SimklMovieRef movie = simklPlaybackItem.getMovie();
            if (movie != null) {
                SimklIds ids = movie.getIds();
                MediaType mediaType = MediaType.MOVIE;
                Integer numResolvedTmdbId = resolvedTmdbId(ids, mediaType, movie.getTitle(), movie.getYear());
                if (numResolvedTmdbId != null) {
                    int iIntValue = numResolvedTmdbId.intValue();
                    int iN = qb.d.n((int) simklPlaybackItem.getProgress(), 0, 100);
                    Integer runtime = movie.getRuntime();
                    long jIntValue = runtime != null ? ((long) runtime.intValue()) * 60 : 0L;
                    Map<x, ContinueWatchingItem> map = this.cachedContinueWatching;
                    x xVar = new x(mediaType, numResolvedTmdbId);
                    String title = movie.getTitle();
                    String str = title == null ? "" : title;
                    long j10 = (((long) iN) * jIntValue) / 100;
                    Integer year = movie.getYear();
                    String strValueOf = year != null ? String.valueOf(year.intValue()) : null;
                    map.put(xVar, new ContinueWatchingItem(iIntValue, str, mediaType, iN, j10, jIntValue, null, null, null, null, null, null, null, null, null, null, strValueOf == null ? "" : strValueOf, null, false, null, null, null, null, null, parseTimestamp(simklPlaybackItem.getPausedAt()), 0, 0, 117374912, null));
                }
            } else {
                SimklShowRef show = simklPlaybackItem.getShow();
                if (show != null || (show = simklPlaybackItem.getAnime()) != null) {
                    SimklIds ids2 = show.getIds();
                    MediaType mediaType2 = MediaType.TV;
                    Integer numResolvedTmdbId2 = resolvedTmdbId(ids2, mediaType2, show.getTitle(), show.getYear());
                    if (numResolvedTmdbId2 != null) {
                        int iIntValue2 = numResolvedTmdbId2.intValue();
                        SimklPlaybackEpisode episode = simklPlaybackItem.getEpisode();
                        if (episode != null && (season = episode.getSeason()) != null && (number = simklPlaybackItem.getEpisode().getNumber()) != null) {
                            int iIntValue3 = number.intValue();
                            int iN2 = qb.d.n((int) simklPlaybackItem.getProgress(), 0, 100);
                            Integer runtime2 = show.getRuntime();
                            long jIntValue2 = runtime2 != null ? ((long) runtime2.intValue()) * 60 : 0L;
                            Map<x, ContinueWatchingItem> map2 = this.cachedContinueWatching;
                            x xVar2 = new x(mediaType2, numResolvedTmdbId2);
                            String title2 = show.getTitle();
                            String str2 = title2 == null ? "" : title2;
                            long j11 = (((long) iN2) * jIntValue2) / 100;
                            String title3 = simklPlaybackItem.getEpisode().getTitle();
                            if (title3 == null) {
                                title3 = a0.c.i(iIntValue3, "Episode ");
                            }
                            String str3 = title3;
                            Integer year2 = show.getYear();
                            String strValueOf2 = year2 != null ? String.valueOf(year2.intValue()) : null;
                            map2.put(xVar2, new ContinueWatchingItem(iIntValue2, str2, mediaType2, iN2, j11, jIntValue2, season, number, null, null, str3, null, null, null, null, null, strValueOf2 == null ? "" : strValueOf2, null, false, null, null, null, null, null, parseTimestamp(simklPlaybackItem.getPausedAt()), 0, 0, 117373696, null));
                        }
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0289  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x02ba  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x02c9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void processShowsResponse(com.arflix.tv.data.api.SimklAllItemsResponse r58) {
        /*
            Method dump skipped, instruction units count: 774
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.simkl.SimklSyncService.processShowsResponse(com.arflix.tv.data.api.SimklAllItemsResponse):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void rebuildCaches(SimklAllItemsResponse movies, SimklAllItemsResponse shows, SimklAllItemsResponse anime, List<SimklPlaybackItem> playback) {
        this.cachedWatchedMovies.clear();
        this.cachedWatchedEpisodes.clear();
        this.cachedWatchlist.clear();
        this.cachedContinueWatching.clear();
        this.cachedLibraryItems.clear();
        processMoviesResponse(movies);
        processShowsResponse(shows);
        processShowsResponse(anime);
        processPlayback(playback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object refreshSnapshot(String str, d<? super SnapshotRefreshOutcome> dVar) {
        return l0.c(new AnonymousClass2(str, null), dVar);
    }

    public static /* synthetic */ Object removeFromWatchlist$default(SimklSyncService simklSyncService, MediaType mediaType, int i10, boolean z, d dVar, int i11, Object obj) {
        if ((i11 & 4) != 0) {
            z = false;
        }
        return simklSyncService.removeFromWatchlist(mediaType, i10, z, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String resolutionKey(SimklIds ids, MediaType mediaType, String title, Integer year) {
        String str;
        String strExternalKey = externalKey(ids, mediaType);
        if (strExternalKey != null) {
            return strExternalKey;
        }
        Long simkl = ids.getSimkl();
        if (simkl != null) {
            long jLongValue = simkl.longValue();
            str = mediaType.name() + ":simkl:" + jLongValue;
        } else {
            str = null;
        }
        if (str != null) {
            return str;
        }
        if (title == null) {
            title = "";
        }
        String strNormalizeTitle = normalizeTitle(title);
        if (o.h0(strNormalizeTitle)) {
            strNormalizeTitle = null;
        }
        if (strNormalizeTitle == null) {
            return null;
        }
        return mediaType.name() + ":title:" + strNormalizeTitle + ":" + (year != null ? year.intValue() : 0);
    }

    public static /* synthetic */ String resolutionKey$default(SimklSyncService simklSyncService, SimklIds simklIds, MediaType mediaType, String str, Integer num, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            str = null;
        }
        if ((i10 & 8) != 0) {
            num = null;
        }
        return simklSyncService.resolutionKey(simklIds, mediaType, str, num);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x01cd, code lost:
    
        if (r13 == r7) goto L110;
     */
    /* JADX WARN: Removed duplicated region for block: B:106:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0179 A[Catch: CancellationException -> 0x0060, Exception -> 0x0180, TRY_LEAVE, TryCatch #2 {CancellationException -> 0x0060, Exception -> 0x0180, blocks: (B:19:0x005b, B:44:0x00d0, B:46:0x00d4, B:49:0x00de, B:51:0x00e4, B:63:0x011b, B:65:0x011f, B:86:0x0179, B:54:0x00f0, B:57:0x00fb, B:58:0x0102, B:61:0x0115, B:68:0x012d, B:70:0x0133, B:82:0x016a, B:84:0x016e, B:73:0x013f, B:76:0x014a, B:77:0x0151, B:80:0x0164, B:24:0x0067, B:35:0x009d, B:27:0x006e, B:29:0x0078, B:32:0x007f, B:36:0x00a0, B:38:0x00aa, B:41:0x00b1), top: B:116:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0183 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0184  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveCandidate(com.arflix.tv.data.repository.simkl.SimklSyncService.TmdbResolutionCandidate r12, d7.d<? super java.lang.Integer> r13) {
        /*
            Method dump skipped, instruction units count: 471
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.simkl.SimklSyncService.resolveCandidate(com.arflix.tv.data.repository.simkl.SimklSyncService$TmdbResolutionCandidate, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object resolveMissingTmdbIds(SimklAllItemsResponse simklAllItemsResponse, SimklAllItemsResponse simklAllItemsResponse2, SimklAllItemsResponse simklAllItemsResponse3, List<SimklPlaybackItem> list, d<? super t0> dVar) {
        Object objC = l0.c(new C12612(simklAllItemsResponse, simklAllItemsResponse2, simklAllItemsResponse3, list, this, null), dVar);
        return objC == e7.a.f15033i ? objC : t0.f22605a;
    }

    private final Integer resolvedTmdbId(SimklIds ids, MediaType mediaType, String title, Integer year) {
        Integer tmdb = ids.getTmdb();
        if (tmdb != null) {
            return tmdb;
        }
        String strResolutionKey = resolutionKey(ids, mediaType, title, year);
        if (strResolutionKey != null) {
            return this.resolvedExternalIds.get(strResolutionKey);
        }
        return null;
    }

    public static /* synthetic */ Integer resolvedTmdbId$default(SimklSyncService simklSyncService, SimklIds simklIds, MediaType mediaType, String str, Integer num, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            str = null;
        }
        if ((i10 & 8) != 0) {
            num = null;
        }
        return simklSyncService.resolvedTmdbId(simklIds, mediaType, str, num);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00ea, code lost:
    
        if (r3 == r4) goto L51;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object searchTmdbCandidate(com.arflix.tv.data.repository.simkl.SimklSyncService.TmdbResolutionCandidate r17, boolean r18, d7.d<? super java.lang.Integer> r19) {
        /*
            Method dump skipped, instruction units count: 640
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.simkl.SimklSyncService.searchTmdbCandidate(com.arflix.tv.data.repository.simkl.SimklSyncService$TmdbResolutionCandidate, boolean, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object syncIfNeeded$default(SimklSyncService simklSyncService, boolean z, d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        return simklSyncService.syncIfNeeded(z, dVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0136 A[Catch: Exception -> 0x0045, TryCatch #0 {Exception -> 0x0045, blocks: (B:13:0x003f, B:37:0x012c, B:39:0x0136, B:40:0x01a3, B:33:0x010f), top: B:45:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x01a3 A[Catch: Exception -> 0x0045, TRY_LEAVE, TryCatch #0 {Exception -> 0x0045, blocks: (B:13:0x003f, B:37:0x012c, B:39:0x0136, B:40:0x01a3, B:33:0x010f), top: B:45:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object addToWatchlist(com.arflix.tv.data.model.MediaType r57, int r58, boolean r59, d7.d<? super java.lang.Boolean> r60) {
        /*
            Method dump skipped, instruction units count: 481
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.simkl.SimklSyncService.addToWatchlist(com.arflix.tv.data.model.MediaType, int, boolean, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x021e A[Catch: CancellationException -> 0x0063, Exception -> 0x0217, TRY_LEAVE, TryCatch #1 {Exception -> 0x0217, blocks: (B:96:0x0206, B:104:0x021e, B:92:0x01ee), top: B:112:0x0206 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0108 A[Catch: Exception -> 0x005f, CancellationException -> 0x0063, TryCatch #0 {Exception -> 0x005f, blocks: (B:14:0x0056, B:88:0x01bc, B:90:0x01c2, B:23:0x0084, B:39:0x00f7, B:40:0x0102, B:42:0x0108, B:44:0x0113, B:46:0x0119, B:55:0x014b, B:77:0x0188, B:60:0x0155, B:64:0x015e, B:67:0x0169, B:70:0x0171, B:73:0x017c, B:48:0x012c, B:50:0x0132, B:52:0x0138, B:78:0x018c, B:79:0x0195, B:81:0x019b, B:83:0x01a7, B:84:0x01ab, B:87:0x01b5, B:35:0x00d1), top: B:110:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x019b A[Catch: Exception -> 0x005f, CancellationException -> 0x0063, TryCatch #0 {Exception -> 0x005f, blocks: (B:14:0x0056, B:88:0x01bc, B:90:0x01c2, B:23:0x0084, B:39:0x00f7, B:40:0x0102, B:42:0x0108, B:44:0x0113, B:46:0x0119, B:55:0x014b, B:77:0x0188, B:60:0x0155, B:64:0x015e, B:67:0x0169, B:70:0x0171, B:73:0x017c, B:48:0x012c, B:50:0x0132, B:52:0x0138, B:78:0x018c, B:79:0x0195, B:81:0x019b, B:83:0x01a7, B:84:0x01ab, B:87:0x01b5, B:35:0x00d1), top: B:110:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01b5 A[Catch: Exception -> 0x005f, CancellationException -> 0x0063, TryCatch #0 {Exception -> 0x005f, blocks: (B:14:0x0056, B:88:0x01bc, B:90:0x01c2, B:23:0x0084, B:39:0x00f7, B:40:0x0102, B:42:0x0108, B:44:0x0113, B:46:0x0119, B:55:0x014b, B:77:0x0188, B:60:0x0155, B:64:0x015e, B:67:0x0169, B:70:0x0171, B:73:0x017c, B:48:0x012c, B:50:0x0132, B:52:0x0138, B:78:0x018c, B:79:0x0195, B:81:0x019b, B:83:0x01a7, B:84:0x01ab, B:87:0x01b5, B:35:0x00d1), top: B:110:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01c2 A[Catch: Exception -> 0x005f, CancellationException -> 0x0063, TRY_LEAVE, TryCatch #0 {Exception -> 0x005f, blocks: (B:14:0x0056, B:88:0x01bc, B:90:0x01c2, B:23:0x0084, B:39:0x00f7, B:40:0x0102, B:42:0x0108, B:44:0x0113, B:46:0x0119, B:55:0x014b, B:77:0x0188, B:60:0x0155, B:64:0x015e, B:67:0x0169, B:70:0x0171, B:73:0x017c, B:48:0x012c, B:50:0x0132, B:52:0x0138, B:78:0x018c, B:79:0x0195, B:81:0x019b, B:83:0x01a7, B:84:0x01ab, B:87:0x01b5, B:35:0x00d1), top: B:110:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0213  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:95:0x0202 -> B:112:0x0206). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object dismissContinueWatching(com.arflix.tv.data.model.MediaType r25, int r26, java.lang.Integer r27, java.lang.Integer r28, d7.d<? super java.lang.Boolean> r29) {
        /*
            Method dump skipped, instruction units count: 590
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.simkl.SimklSyncService.dismissContinueWatching(com.arflix.tv.data.model.MediaType, int, java.lang.Integer, java.lang.Integer, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getContinueWatching(boolean r5, d7.d<? super java.util.List<com.arflix.tv.data.repository.ContinueWatchingItem>> r6) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.arflix.tv.data.repository.simkl.SimklSyncService.C12511
            if (r0 == 0) goto L13
            r0 = r6
            com.arflix.tv.data.repository.simkl.SimklSyncService$getContinueWatching$1 r0 = (com.arflix.tv.data.repository.simkl.SimklSyncService.C12511) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.simkl.SimklSyncService$getContinueWatching$1 r0 = new com.arflix.tv.data.repository.simkl.SimklSyncService$getContinueWatching$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r6)
            goto L3d
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2d:
            k2.c.G(r6)
            r0.Z$0 = r5
            r0.label = r2
            java.lang.Object r5 = r4.syncIfNeeded(r5, r0)
            e7.a r6 = e7.a.f15033i
            if (r5 != r6) goto L3d
            return r6
        L3d:
            java.util.Map<x6.x, com.arflix.tv.data.repository.ContinueWatchingItem> r5 = r4.cachedContinueWatching
            java.util.Collection r5 = r5.values()
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.Iterator r5 = r5.iterator()
        L4e:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L67
            java.lang.Object r0 = r5.next()
            r1 = r0
            com.arflix.tv.data.repository.ContinueWatchingItem r1 = (com.arflix.tv.data.repository.ContinueWatchingItem) r1
            int r1 = r1.getProgress()
            r2 = 95
            if (r1 >= r2) goto L4e
            r6.add(r0)
            goto L4e
        L67:
            com.arflix.tv.data.repository.simkl.SimklSyncService$getContinueWatching$$inlined$sortedByDescending$1 r5 = new com.arflix.tv.data.repository.simkl.SimklSyncService$getContinueWatching$$inlined$sortedByDescending$1
            r5.<init>()
            java.util.List r5 = kotlin.collections.x.W0(r6, r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.simkl.SimklSyncService.getContinueWatching(boolean, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getLibraryItems(java.lang.String r5, boolean r6, d7.d<? super java.util.List<com.arflix.tv.data.model.MediaItem>> r7) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r7 instanceof com.arflix.tv.data.repository.simkl.SimklSyncService.C12521
            if (r0 == 0) goto L13
            r0 = r7
            com.arflix.tv.data.repository.simkl.SimklSyncService$getLibraryItems$1 r0 = (com.arflix.tv.data.repository.simkl.SimklSyncService.C12521) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.simkl.SimklSyncService$getLibraryItems$1 r0 = new com.arflix.tv.data.repository.simkl.SimklSyncService$getLibraryItems$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r5 = r0.L$0
            java.lang.String r5 = (java.lang.String) r5
            k2.c.G(r7)
            goto L43
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            k2.c.G(r7)
            r0.L$0 = r5
            r0.Z$0 = r6
            r0.label = r2
            java.lang.Object r6 = r4.syncIfNeeded(r6, r0)
            e7.a r7 = e7.a.f15033i
            if (r6 != r7) goto L43
            return r7
        L43:
            java.util.Map<java.lang.String, java.util.LinkedHashMap<x6.x, com.arflix.tv.data.model.MediaItem>> r6 = r4.cachedLibraryItems
            java.lang.CharSequence r5 = kotlin.text.o.L0(r5)
            java.lang.String r5 = r5.toString()
            java.util.Locale r7 = java.util.Locale.ROOT
            java.lang.String r5 = r5.toLowerCase(r7)
            java.lang.Object r5 = r6.get(r5)
            java.util.LinkedHashMap r5 = (java.util.LinkedHashMap) r5
            if (r5 == 0) goto L68
            java.util.Collection r5 = r5.values()
            if (r5 == 0) goto L68
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.List r5 = kotlin.collections.x.c1(r5)
            goto L69
        L68:
            r5 = 0
        L69:
            if (r5 != 0) goto L6d
            kotlin.collections.z r5 = kotlin.collections.z.f19728i
        L6d:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.simkl.SimklSyncService.getLibraryItems(java.lang.String, boolean, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getWatchedEpisodes(d7.d<? super java.util.Set<java.lang.String>> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.arflix.tv.data.repository.simkl.SimklSyncService.C12531
            if (r0 == 0) goto L13
            r0 = r5
            com.arflix.tv.data.repository.simkl.SimklSyncService$getWatchedEpisodes$1 r0 = (com.arflix.tv.data.repository.simkl.SimklSyncService.C12531) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.simkl.SimklSyncService$getWatchedEpisodes$1 r0 = new com.arflix.tv.data.repository.simkl.SimklSyncService$getWatchedEpisodes$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r5)
            goto L3d
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2d:
            k2.c.G(r5)
            r0.label = r2
            r5 = 0
            r1 = 0
            java.lang.Object r5 = syncIfNeeded$default(r4, r5, r0, r2, r1)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L3d
            return r0
        L3d:
            java.util.Set<java.lang.String> r5 = r4.cachedWatchedEpisodes
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.Set r5 = kotlin.collections.x.g1(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.simkl.SimklSyncService.getWatchedEpisodes(d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getWatchedMovies(d7.d<? super java.util.Set<java.lang.Integer>> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.arflix.tv.data.repository.simkl.SimklSyncService.C12541
            if (r0 == 0) goto L13
            r0 = r5
            com.arflix.tv.data.repository.simkl.SimklSyncService$getWatchedMovies$1 r0 = (com.arflix.tv.data.repository.simkl.SimklSyncService.C12541) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.simkl.SimklSyncService$getWatchedMovies$1 r0 = new com.arflix.tv.data.repository.simkl.SimklSyncService$getWatchedMovies$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r5)
            goto L3d
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2d:
            k2.c.G(r5)
            r0.label = r2
            r5 = 0
            r1 = 0
            java.lang.Object r5 = syncIfNeeded$default(r4, r5, r0, r2, r1)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L3d
            return r0
        L3d:
            java.util.Set<java.lang.Integer> r5 = r4.cachedWatchedMovies
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.Set r5 = kotlin.collections.x.g1(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.simkl.SimklSyncService.getWatchedMovies(d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getWatchlistItems(d7.d<? super java.util.List<com.arflix.tv.data.model.MediaItem>> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.arflix.tv.data.repository.simkl.SimklSyncService.C12551
            if (r0 == 0) goto L13
            r0 = r5
            com.arflix.tv.data.repository.simkl.SimklSyncService$getWatchlistItems$1 r0 = (com.arflix.tv.data.repository.simkl.SimklSyncService.C12551) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.simkl.SimklSyncService$getWatchlistItems$1 r0 = new com.arflix.tv.data.repository.simkl.SimklSyncService$getWatchlistItems$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r5)
            goto L3d
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2d:
            k2.c.G(r5)
            r0.label = r2
            r5 = 0
            r1 = 0
            java.lang.Object r5 = syncIfNeeded$default(r4, r5, r0, r2, r1)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L3d
            return r0
        L3d:
            java.util.Map<x6.x, com.arflix.tv.data.model.MediaItem> r5 = r4.cachedWatchlist
            java.util.Collection r5 = r5.values()
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.List r5 = kotlin.collections.x.c1(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.simkl.SimklSyncService.getWatchlistItems(d7.d):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:57:0x019f A[Catch: Exception -> 0x004b, CancellationException -> 0x004e, TryCatch #2 {CancellationException -> 0x004e, Exception -> 0x004b, blocks: (B:15:0x0046, B:54:0x0195, B:55:0x0197, B:57:0x019f, B:58:0x01a3, B:60:0x01a9, B:62:0x01b9, B:63:0x01bf, B:64:0x01c5, B:65:0x01dc, B:69:0x01eb, B:24:0x006f, B:49:0x016d, B:45:0x0143, B:50:0x0170), top: B:75:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01dc A[Catch: Exception -> 0x004b, CancellationException -> 0x004e, TryCatch #2 {CancellationException -> 0x004e, Exception -> 0x004b, blocks: (B:15:0x0046, B:54:0x0195, B:55:0x0197, B:57:0x019f, B:58:0x01a3, B:60:0x01a9, B:62:0x01b9, B:63:0x01bf, B:64:0x01c5, B:65:0x01dc, B:69:0x01eb, B:24:0x006f, B:49:0x016d, B:45:0x0143, B:50:0x0170), top: B:75:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object markSeasonWatched(int r26, int r27, java.util.List<java.lang.Integer> r28, boolean r29, d7.d<? super java.lang.Boolean> r30) {
        /*
            Method dump skipped, instruction units count: 553
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.simkl.SimklSyncService.markSeasonWatched(int, int, java.util.List, boolean, d7.d):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:43:0x016e A[Catch: Exception -> 0x004b, TryCatch #0 {Exception -> 0x004b, blocks: (B:13:0x0046, B:41:0x0164, B:43:0x016e, B:45:0x0172, B:49:0x0192, B:48:0x0181, B:50:0x0198, B:37:0x0143), top: B:56:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0198 A[Catch: Exception -> 0x004b, TRY_LEAVE, TryCatch #0 {Exception -> 0x004b, blocks: (B:13:0x0046, B:41:0x0164, B:43:0x016e, B:45:0x0172, B:49:0x0192, B:48:0x0181, B:50:0x0198, B:37:0x0143), top: B:56:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object markUnwatched(com.arflix.tv.data.model.MediaType r27, int r28, java.lang.Integer r29, java.lang.Integer r30, d7.d<? super java.lang.Boolean> r31) {
        /*
            Method dump skipped, instruction units count: 474
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.simkl.SimklSyncService.markUnwatched(com.arflix.tv.data.model.MediaType, int, java.lang.Integer, java.lang.Integer, d7.d):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0174 A[Catch: Exception -> 0x004d, TryCatch #0 {Exception -> 0x004d, blocks: (B:14:0x0048, B:42:0x016a, B:44:0x0174, B:46:0x0178, B:50:0x0198, B:49:0x0187, B:51:0x019e, B:38:0x0140), top: B:57:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x019e A[Catch: Exception -> 0x004d, TRY_LEAVE, TryCatch #0 {Exception -> 0x004d, blocks: (B:14:0x0048, B:42:0x016a, B:44:0x0174, B:46:0x0178, B:50:0x0198, B:49:0x0187, B:51:0x019e, B:38:0x0140), top: B:57:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object markWatched(com.arflix.tv.data.model.MediaType r29, int r30, java.lang.Integer r31, java.lang.Integer r32, d7.d<? super java.lang.Boolean> r33) {
        /*
            Method dump skipped, instruction units count: 480
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.simkl.SimklSyncService.markWatched(com.arflix.tv.data.model.MediaType, int, java.lang.Integer, java.lang.Integer, d7.d):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0275 A[Catch: Exception -> 0x0045, TryCatch #0 {Exception -> 0x0045, blocks: (B:14:0x0040, B:70:0x026b, B:71:0x026d, B:73:0x0275, B:74:0x028a, B:21:0x0062, B:59:0x018e, B:47:0x00e1, B:50:0x00e7, B:55:0x016e, B:52:0x0116, B:54:0x0142, B:60:0x0192, B:62:0x0196, B:66:0x024c, B:64:0x01d4, B:65:0x0210), top: B:80:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x028a A[Catch: Exception -> 0x0045, TRY_LEAVE, TryCatch #0 {Exception -> 0x0045, blocks: (B:14:0x0040, B:70:0x026b, B:71:0x026d, B:73:0x0275, B:74:0x028a, B:21:0x0062, B:59:0x018e, B:47:0x00e1, B:50:0x00e7, B:55:0x016e, B:52:0x0116, B:54:0x0142, B:60:0x0192, B:62:0x0196, B:66:0x024c, B:64:0x01d4, B:65:0x0210), top: B:80:0x0026 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Type inference failed for: r11v11 */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r11v8, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object removeFromWatchlist(com.arflix.tv.data.model.MediaType r25, int r26, boolean r27, d7.d<? super java.lang.Boolean> r28) {
        /*
            Method dump skipped, instruction units count: 719
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.simkl.SimklSyncService.removeFromWatchlist(com.arflix.tv.data.model.MediaType, int, boolean, d7.d):java.lang.Object");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:0|2|(2:4|(1:6)(1:7))(0)|8|126|(1:(4:(7:(1:(10:14|131|15|111|(1:113)(1:114)|115|117|121|122|123)(2:23|24))(10:25|132|26|27|78|(3:80|(1:82)(1:87)|(3:89|(1:91)(1:92)|(2:94|(2:96|97)(1:98))))|99|(5:106|117|121|122|123)|107|(1:135)(8:110|111|(0)(0)|115|117|121|122|123))|116|115|117|121|122|123)(10:34|128|35|36|48|(1:119)(5:53|(1:58)|61|(1:68)(2:65|(1:67))|(4:73|130|74|(2:76|134)(7:77|78|(0)|99|(3:101|103|106)|107|(0)(0)))(1:72))|117|121|122|123)|18|124|125)(1:39))(4:40|(1:43)|42|109)|127|44|(3:46|42|109)(7:47|48|(3:50|52|119)(0)|117|121|122|123)|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0106, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0107, code lost:
    
        r5 = r14;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:110:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0200 A[Catch: all -> 0x0052, Exception -> 0x0056, CancellationException -> 0x0059, TryCatch #1 {all -> 0x0052, blocks: (B:15:0x004d, B:111:0x01f2, B:113:0x0200, B:121:0x022d, B:114:0x0205, B:116:0x020b, B:118:0x0228), top: B:126:0x002d }] */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0205 A[Catch: all -> 0x0052, Exception -> 0x0056, CancellationException -> 0x0059, TRY_LEAVE, TryCatch #1 {all -> 0x0052, blocks: (B:15:0x004d, B:111:0x01f2, B:113:0x0200, B:121:0x022d, B:114:0x0205, B:116:0x020b, B:118:0x0228), top: B:126:0x002d }] */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0229 A[Catch: all -> 0x0106, TRY_ENTER, TRY_LEAVE, TryCatch #3 {all -> 0x0106, blocks: (B:78:0x0166, B:80:0x016e, B:82:0x0174, B:89:0x0184, B:91:0x018a, B:94:0x0192, B:96:0x0198, B:101:0x01a3, B:103:0x01a7, B:106:0x01b0, B:107:0x01c9, B:48:0x00e0, B:50:0x00e4, B:53:0x00ec, B:58:0x00fb, B:61:0x010a, B:63:0x0114, B:65:0x0118, B:70:0x012b, B:73:0x0138, B:74:0x013a, B:56:0x00f5, B:119:0x0229, B:44:0x00c9), top: B:127:0x00c9 }] */
    /* JADX WARN: Removed duplicated region for block: B:135:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001d  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x016e A[Catch: all -> 0x0106, Exception -> 0x0179, CancellationException -> 0x017d, TryCatch #3 {all -> 0x0106, blocks: (B:78:0x0166, B:80:0x016e, B:82:0x0174, B:89:0x0184, B:91:0x018a, B:94:0x0192, B:96:0x0198, B:101:0x01a3, B:103:0x01a7, B:106:0x01b0, B:107:0x01c9, B:48:0x00e0, B:50:0x00e4, B:53:0x00ec, B:58:0x00fb, B:61:0x010a, B:63:0x0114, B:65:0x0118, B:70:0x012b, B:73:0x0138, B:74:0x013a, B:56:0x00f5, B:119:0x0229, B:44:0x00c9), top: B:127:0x00c9 }] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Object, ua.a] */
    /* JADX WARN: Type inference failed for: r11v2, types: [com.arflix.tv.data.repository.simkl.SimklAuthManager] */
    /* JADX WARN: Type inference failed for: r12v1, types: [com.arflix.tv.data.api.SimklApi] */
    /* JADX WARN: Type inference failed for: r14v0 */
    /* JADX WARN: Type inference failed for: r14v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r14v11 */
    /* JADX WARN: Type inference failed for: r14v12 */
    /* JADX WARN: Type inference failed for: r14v13 */
    /* JADX WARN: Type inference failed for: r14v14 */
    /* JADX WARN: Type inference failed for: r14v15 */
    /* JADX WARN: Type inference failed for: r14v16 */
    /* JADX WARN: Type inference failed for: r14v17 */
    /* JADX WARN: Type inference failed for: r14v18 */
    /* JADX WARN: Type inference failed for: r14v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r14v3, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r14v4 */
    /* JADX WARN: Type inference failed for: r14v5 */
    /* JADX WARN: Type inference failed for: r14v8 */
    /* JADX WARN: Type inference failed for: r21v0, types: [com.arflix.tv.data.repository.simkl.SimklSyncService] */
    /* JADX WARN: Type inference failed for: r5v10, types: [ua.a] */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v18, types: [ua.a] */
    /* JADX WARN: Type inference failed for: r5v2, types: [com.arflix.tv.data.repository.simkl.SimklSyncService$syncIfNeeded$1, d7.d] */
    /* JADX WARN: Type inference failed for: r5v21 */
    /* JADX WARN: Type inference failed for: r5v22 */
    /* JADX WARN: Type inference failed for: r5v23 */
    /* JADX WARN: Type inference failed for: r5v24 */
    /* JADX WARN: Type inference failed for: r5v25 */
    /* JADX WARN: Type inference failed for: r5v26 */
    /* JADX WARN: Type inference failed for: r5v3, types: [ua.a] */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v9 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object syncIfNeeded(boolean r22, d7.d<? super x6.t0> r23) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 568
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.simkl.SimklSyncService.syncIfNeeded(boolean, d7.d):java.lang.Object");
    }
}
