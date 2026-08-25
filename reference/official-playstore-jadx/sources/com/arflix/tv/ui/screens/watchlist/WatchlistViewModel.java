package com.arflix.tv.ui.screens.watchlist;

import android.content.Context;
import androidx.lifecycle.d1;
import androidx.lifecycle.z0;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import com.arflix.tv.BuildConfig;
import com.arflix.tv.data.model.CatalogConfig;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.repository.CatalogRepository;
import com.arflix.tv.data.repository.CloudSyncRepository;
import com.arflix.tv.data.repository.HomeServerCatalogCandidate;
import com.arflix.tv.data.repository.HomeServerConnection;
import com.arflix.tv.data.repository.HomeServerKind;
import com.arflix.tv.data.repository.HomeServerLibrarySort;
import com.arflix.tv.data.repository.HomeServerRepository;
import com.arflix.tv.data.repository.MediaRepository;
import com.arflix.tv.data.repository.ProfileManager;
import com.arflix.tv.data.repository.TraktRepository;
import com.arflix.tv.data.repository.WatchHistoryRepository;
import com.arflix.tv.data.repository.WatchlistRepository;
import com.arflix.tv.data.repository.simkl.SimklAuthManager;
import com.arflix.tv.data.repository.simkl.SimklSyncService;
import com.arflix.tv.data.repository.sync.RemoteSyncManager;
import com.arflix.tv.ui.screens.watchlist.WatchlistSourceItem;
import com.arflix.tv.ui.screens.watchlist.WatchlistViewModel;
import com.arflix.tv.util.AppLogger;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.MediaError;
import com.google.common.util.concurrent.r0;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import javax.inject.Inject;
import ka.k0;
import ka.l0;
import ka.m0;
import ka.s0;
import ka.v1;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.a0;
import kotlin.collections.h0;
import kotlin.collections.i0;
import kotlin.collections.r;
import kotlin.collections.s;
import kotlin.collections.z;
import kotlin.text.u;
import na.h1;
import na.j1;
import na.n0;
import na.q0;
import na.y0;
import r7.p;
import r7.q;
import x6.c0;
import x6.d0;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000ø\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010#\n\u0002\b\u0004\b\u0007\u0018\u0000 ¨\u00012\u00020\u0001:\u0002¨\u0001Bk\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001f\u001a\u00020\u001e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c¢\u0006\u0004\b\u001f\u0010 J\u0015\u0010#\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020!¢\u0006\u0004\b#\u0010$J\u0015\u0010'\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020%¢\u0006\u0004\b'\u0010(J\u0015\u0010*\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020!¢\u0006\u0004\b*\u0010$J\r\u0010+\u001a\u00020\u001e¢\u0006\u0004\b+\u0010,J\r\u0010-\u001a\u00020\u001e¢\u0006\u0004\b-\u0010,J\u0015\u0010/\u001a\u00020\u001e2\u0006\u0010.\u001a\u00020!¢\u0006\u0004\b/\u0010$J\u001d\u00103\u001a\u00020\u001e2\u0006\u00101\u001a\u0002002\u0006\u00102\u001a\u000200¢\u0006\u0004\b3\u00104J\r\u00105\u001a\u00020\u001e¢\u0006\u0004\b5\u0010,J\u0015\u00108\u001a\u00020\u001e2\u0006\u00107\u001a\u000206¢\u0006\u0004\b8\u00109J\r\u0010:\u001a\u00020\u001e¢\u0006\u0004\b:\u0010,J\r\u0010;\u001a\u00020\u001e¢\u0006\u0004\b;\u0010,J\u0015\u0010<\u001a\u00020\u001e2\u0006\u00107\u001a\u000206¢\u0006\u0004\b<\u00109J\r\u0010=\u001a\u00020\u001e¢\u0006\u0004\b=\u0010,J9\u0010A\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020!0?2\u0006\u0010>\u001a\u00020!2\u0014\b\u0002\u0010@\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020!0?H\u0002¢\u0006\u0004\bA\u0010BJ\u001f\u0010D\u001a\b\u0012\u0004\u0012\u0002060C*\b\u0012\u0004\u0012\u0002060CH\u0002¢\u0006\u0004\bD\u0010EJ\u0019\u0010G\u001a\u00020F*\b\u0012\u0004\u0012\u0002060CH\u0002¢\u0006\u0004\bG\u0010HJ\u000f\u0010I\u001a\u00020\u001eH\u0002¢\u0006\u0004\bI\u0010,J\u000f\u0010J\u001a\u00020\u001eH\u0002¢\u0006\u0004\bJ\u0010,J\u000f\u0010K\u001a\u00020\u001eH\u0002¢\u0006\u0004\bK\u0010,J\u0018\u0010M\u001a\u00020\u001e2\u0006\u0010L\u001a\u00020FH\u0082@¢\u0006\u0004\bM\u0010NJ+\u0010R\u001a\u00020\u001e2\f\u0010O\u001a\b\u0012\u0004\u0012\u00020\u001c0C2\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020P0CH\u0002¢\u0006\u0004\bR\u0010SJ\u0017\u0010V\u001a\u00020!2\u0006\u0010U\u001a\u00020TH\u0002¢\u0006\u0004\bV\u0010WJ\u0019\u0010Y\u001a\u00020\u001e2\b\b\u0002\u0010X\u001a\u00020FH\u0002¢\u0006\u0004\bY\u0010ZJ3\u0010^\u001a\u00020\u001e2\u0010\b\u0002\u0010\\\u001a\n\u0012\u0004\u0012\u00020[\u0018\u00010C2\u0010\b\u0002\u0010]\u001a\n\u0012\u0004\u0012\u00020P\u0018\u00010CH\u0002¢\u0006\u0004\b^\u0010SJ\u0019\u0010`\u001a\u00020\u001e2\b\b\u0002\u0010_\u001a\u00020FH\u0002¢\u0006\u0004\b`\u0010ZJ$\u0010b\u001a\b\u0012\u0004\u0012\u0002060C2\f\u0010a\u001a\b\u0012\u0004\u0012\u0002060CH\u0082@¢\u0006\u0004\bb\u0010cJ \u0010d\u001a\b\u0012\u0004\u0012\u0002060C*\b\u0012\u0004\u0012\u0002060CH\u0082@¢\u0006\u0004\bd\u0010cJ\u001d\u0010e\u001a\u00020\u001e2\f\u0010a\u001a\b\u0012\u0004\u0012\u0002060CH\u0002¢\u0006\u0004\be\u0010fJ\u000f\u0010g\u001a\u00020\u001eH\u0002¢\u0006\u0004\bg\u0010,J\u000f\u0010h\u001a\u00020\u001eH\u0002¢\u0006\u0004\bh\u0010,J\u0018\u0010j\u001a\u00020\u001e2\u0006\u0010i\u001a\u00020!H\u0082@¢\u0006\u0004\bj\u0010kJ\u0010\u0010l\u001a\u00020FH\u0082@¢\u0006\u0004\bl\u0010mR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010nR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010oR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010pR\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010qR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010rR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010sR\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010tR\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010uR\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010vR\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010wR\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010xR\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010yR\u001a\u0010|\u001a\b\u0012\u0004\u0012\u00020{0z8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b|\u0010}R \u0010\u007f\u001a\b\u0012\u0004\u0012\u00020{0~8\u0006¢\u0006\u000f\n\u0005\b\u007f\u0010\u0080\u0001\u001a\u0006\b\u0081\u0001\u0010\u0082\u0001R(\u0010\u0083\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020!0?0z8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0083\u0001\u0010}R.\u0010\u0084\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020!0?0~8\u0006¢\u0006\u0010\n\u0006\b\u0084\u0001\u0010\u0080\u0001\u001a\u0006\b\u0085\u0001\u0010\u0082\u0001R\u001c\u0010\u0086\u0001\u001a\b\u0012\u0004\u0012\u00020T0z8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0086\u0001\u0010}R\"\u0010\u0087\u0001\u001a\b\u0012\u0004\u0012\u00020T0~8\u0006¢\u0006\u0010\n\u0006\b\u0087\u0001\u0010\u0080\u0001\u001a\u0006\b\u0088\u0001\u0010\u0082\u0001R[\u0010\u008c\u0001\u001aF\u0012\u0004\u0012\u00020!\u0012\u0017\u0012\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u0002060C\u0012\u0004\u0012\u00020F0\u008a\u00010\u0089\u0001j\"\u0012\u0004\u0012\u00020!\u0012\u0017\u0012\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u0002060C\u0012\u0004\u0012\u00020F0\u008a\u0001`\u008b\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u008c\u0001\u0010\u008d\u0001R\u001c\u0010\u008f\u0001\u001a\u0005\u0018\u00010\u008e\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u008f\u0001\u0010\u0090\u0001R\u001c\u0010\u0091\u0001\u001a\u0005\u0018\u00010\u008e\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0091\u0001\u0010\u0090\u0001R\u0019\u0010\u0092\u0001\u001a\u0002008\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0092\u0001\u0010\u0093\u0001RA\u0010\u0094\u0001\u001a,\u0012\u0004\u0012\u00020!\u0012\n\u0012\b\u0012\u0004\u0012\u0002060C0\u0089\u0001j\u0015\u0012\u0004\u0012\u00020!\u0012\n\u0012\b\u0012\u0004\u0012\u0002060C`\u008b\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0094\u0001\u0010\u008d\u0001R%\u0010\u0097\u0001\u001a\u0010\u0012\u0004\u0012\u00020!\u0012\u0005\u0012\u00030\u0096\u00010\u0095\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0097\u0001\u0010\u0098\u0001R\u001f\u0010\u0099\u0001\u001a\b\u0012\u0004\u0012\u00020[0C8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0099\u0001\u0010\u009a\u0001R\u001f\u0010\u009b\u0001\u001a\b\u0012\u0004\u0012\u00020P0C8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u009b\u0001\u0010\u009a\u0001R \u0010\u009d\u0001\u001a\t\u0012\u0005\u0012\u00030\u009c\u00010C8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u009d\u0001\u0010\u009a\u0001R\u001c\u0010\u009e\u0001\u001a\u0005\u0018\u00010\u008e\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u009e\u0001\u0010\u0090\u0001R\u001c\u0010\u009f\u0001\u001a\u0005\u0018\u00010\u008e\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u009f\u0001\u0010\u0090\u0001R\u0019\u0010 \u0001\u001a\u00020F8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b \u0001\u0010¡\u0001R\u0019\u0010¢\u0001\u001a\u00020F8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¢\u0001\u0010¡\u0001R\u0019\u0010£\u0001\u001a\u00020F8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b£\u0001\u0010¡\u0001R\u0019\u0010¤\u0001\u001a\u00020F8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¤\u0001\u0010¡\u0001R\u001e\u0010¦\u0001\u001a\t\u0012\u0004\u0012\u00020!0¥\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¦\u0001\u0010§\u0001¨\u0006©\u0001"}, d2 = {"Lcom/arflix/tv/ui/screens/watchlist/WatchlistViewModel;", "Landroidx/lifecycle/d1;", "Landroid/content/Context;", "context", "Lcom/arflix/tv/data/repository/WatchlistRepository;", "watchlistRepository", "Lcom/arflix/tv/data/repository/CloudSyncRepository;", "cloudSyncRepository", "Lcom/arflix/tv/data/repository/TraktRepository;", "traktRepository", "Lcom/arflix/tv/data/repository/sync/RemoteSyncManager;", "remoteSyncManager", "Lcom/arflix/tv/data/repository/MediaRepository;", "mediaRepository", "Lcom/arflix/tv/data/repository/HomeServerRepository;", "homeServerRepository", "Lcom/arflix/tv/data/repository/CatalogRepository;", "catalogRepository", "Lcom/arflix/tv/data/repository/WatchHistoryRepository;", "watchHistoryRepository", "Lcom/arflix/tv/data/repository/simkl/SimklAuthManager;", "simklAuthManager", "Lcom/arflix/tv/data/repository/simkl/SimklSyncService;", "simklSyncService", "Lcom/arflix/tv/data/repository/ProfileManager;", "profileManager", "<init>", "(Landroid/content/Context;Lcom/arflix/tv/data/repository/WatchlistRepository;Lcom/arflix/tv/data/repository/CloudSyncRepository;Lcom/arflix/tv/data/repository/TraktRepository;Lcom/arflix/tv/data/repository/sync/RemoteSyncManager;Lcom/arflix/tv/data/repository/MediaRepository;Lcom/arflix/tv/data/repository/HomeServerRepository;Lcom/arflix/tv/data/repository/CatalogRepository;Lcom/arflix/tv/data/repository/WatchHistoryRepository;Lcom/arflix/tv/data/repository/simkl/SimklAuthManager;Lcom/arflix/tv/data/repository/simkl/SimklSyncService;Lcom/arflix/tv/data/repository/ProfileManager;)V", "Lcom/arflix/tv/data/repository/HomeServerKind;", "provider", "Lx6/t0;", "selectLibraryProvider", "(Lcom/arflix/tv/data/repository/HomeServerKind;)V", "", "sourceRef", "selectLibrary", "(Ljava/lang/String;)V", "Lcom/arflix/tv/data/repository/HomeServerLibrarySort;", "sort", "setLibrarySort", "(Lcom/arflix/tv/data/repository/HomeServerLibrarySort;)V", "query", "setLibrarySearch", "refreshLibrary", "()V", "loadMoreLibrary", "sourceId", "selectSource", "", "sectionIndex", "itemIndex", "saveFocusState", "(II)V", "loadMoreActiveSource", "Lcom/arflix/tv/data/model/MediaItem;", "item", "ensureLogo", "(Lcom/arflix/tv/data/model/MediaItem;)V", "refresh", "refreshAfterResume", "removeFromWatchlist", "dismissToast", "phase", "", "extra", "watchlistDiagnosticContext", "(Ljava/lang/String;Ljava/util/Map;)Ljava/util/Map;", "", "watchlistDisplayOrder", "(Ljava/util/List;)Ljava/util/List;", "", "needsArtworkEnrichment", "(Ljava/util/List;)Z", "observeWatchlistChanges", "observeCatalogsAndHomeServers", "observeTrackerLibraries", "traktConnected", "refreshTrackerLibraries", "(ZLd7/d;)Ljava/lang/Object;", "usableProviders", "Lcom/arflix/tv/data/repository/HomeServerCatalogCandidate;", "candidates", "updateHomeLibraryState", "(Ljava/util/List;Ljava/util/List;)V", "Lcom/arflix/tv/ui/screens/watchlist/HomeLibraryUiState;", "state", "libraryCacheKey", "(Lcom/arflix/tv/ui/screens/watchlist/HomeLibraryUiState;)Ljava/lang/String;", "force", "loadLibraryFirstPage", "(Z)V", "Lcom/arflix/tv/data/model/CatalogConfig;", "catalogs", "homeServerCandidates", "updateAvailableSources", "forceRefresh", "loadActiveSourceItems", "items", "hydrateTrackerItems", "(Ljava/util/List;Ld7/d;)Ljava/lang/Object;", "enrichWithPlaybackProgress", "fetchLogos", "(Ljava/util/List;)V", "loadWatchlistInstant", "enrichLocalWatchlistInBackground", "message", "showLocalWatchlistOrError", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "syncTraktWatchlistSuspend", "(Ld7/d;)Ljava/lang/Object;", "Landroid/content/Context;", "Lcom/arflix/tv/data/repository/WatchlistRepository;", "Lcom/arflix/tv/data/repository/CloudSyncRepository;", "Lcom/arflix/tv/data/repository/TraktRepository;", "Lcom/arflix/tv/data/repository/sync/RemoteSyncManager;", "Lcom/arflix/tv/data/repository/MediaRepository;", "Lcom/arflix/tv/data/repository/HomeServerRepository;", "Lcom/arflix/tv/data/repository/CatalogRepository;", "Lcom/arflix/tv/data/repository/WatchHistoryRepository;", "Lcom/arflix/tv/data/repository/simkl/SimklAuthManager;", "Lcom/arflix/tv/data/repository/simkl/SimklSyncService;", "Lcom/arflix/tv/data/repository/ProfileManager;", "Lna/q0;", "Lcom/arflix/tv/ui/screens/watchlist/WatchlistUiState;", "_uiState", "Lna/q0;", "Lna/h1;", "uiState", "Lna/h1;", "getUiState", "()Lna/h1;", "_logoUrls", "logoUrls", "getLogoUrls", "_libraryState", "libraryState", "getLibraryState", "Ljava/util/LinkedHashMap;", "Lx6/x;", "Lkotlin/collections/LinkedHashMap;", "libraryCache", "Ljava/util/LinkedHashMap;", "Lka/v1;", "libraryLoadJob", "Lka/v1;", "librarySearchJob", "libraryRequestId", "I", "sourceItemsCache", "", "Lcom/arflix/tv/ui/screens/watchlist/SourcePageState;", "sourcePageStates", "Ljava/util/Map;", "currentCatalogs", "Ljava/util/List;", "currentHomeServerCandidates", "Lcom/arflix/tv/ui/screens/watchlist/WatchlistSourceItem$TrackerList;", "currentTrackerLists", "sourceLoadJob", "sourceLoadMoreJob", "traktSyncInFlight", "Z", "initialLoadComplete", "enrichmentInFlight", "enrichmentRequested", "", "logoRequestsInFlight", "Ljava/util/Set;", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class WatchlistViewModel extends d1 {
    private static final int LIBRARY_CACHE_ENTRY_LIMIT = 12;
    private static final int LIBRARY_LOGO_INITIAL_PREFETCH = 12;
    private static final int LIBRARY_PAGE_SIZE = 60;
    private static final int TRACKER_LIST_ITEM_LIMIT = 240;
    private static final String TRAKT_WATCHLIST_KEY = "__watchlist__";
    private final q0<HomeLibraryUiState> _libraryState;
    private final q0<Map<String, String>> _logoUrls;
    private final q0<WatchlistUiState> _uiState;
    private final CatalogRepository catalogRepository;
    private final CloudSyncRepository cloudSyncRepository;
    private final Context context;
    private List<CatalogConfig> currentCatalogs;
    private List<HomeServerCatalogCandidate> currentHomeServerCandidates;
    private List<WatchlistSourceItem.TrackerList> currentTrackerLists;
    private boolean enrichmentInFlight;
    private boolean enrichmentRequested;
    private final HomeServerRepository homeServerRepository;
    private boolean initialLoadComplete;
    private final LinkedHashMap<String, x> libraryCache;
    private v1 libraryLoadJob;
    private int libraryRequestId;
    private v1 librarySearchJob;
    private final h1<HomeLibraryUiState> libraryState;
    private final Set<String> logoRequestsInFlight;
    private final h1<Map<String, String>> logoUrls;
    private final MediaRepository mediaRepository;
    private final ProfileManager profileManager;
    private final RemoteSyncManager remoteSyncManager;
    private final SimklAuthManager simklAuthManager;
    private final SimklSyncService simklSyncService;
    private final LinkedHashMap<String, List<MediaItem>> sourceItemsCache;
    private v1 sourceLoadJob;
    private v1 sourceLoadMoreJob;
    private final Map<String, SourcePageState> sourcePageStates;
    private final TraktRepository traktRepository;
    private boolean traktSyncInFlight;
    private final h1<WatchlistUiState> uiState;
    private final WatchHistoryRepository watchHistoryRepository;
    private final WatchlistRepository watchlistRepository;
    public static final int $stable = 8;
    private static final List<x> SIMKL_LIBRARY_LISTS = t7.a.E(new x("watching", "Watching"), new x("plantowatch", "Plan to watch"), new x("completed", "Completed"), new x("hold", "On hold"), new x("dropped", "Dropped"));
    private static final Set<String> BROWSABLE_LIBRARY_TYPES = r.p0(new String[]{"", "movie", "movies", "show", "shows", "series", "tvshows", "mixed"});

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[HomeServerKind.values().length];
            try {
                iArr[HomeServerKind.PLEX.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[HomeServerKind.JELLYFIN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[HomeServerKind.EMBY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[HomeServerKind.UNKNOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$enrichLocalWatchlistInBackground$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$enrichLocalWatchlistInBackground$1", f = "WatchlistViewModel.kt", l = {AnalyticsListener.EVENT_PLAYER_RELEASED, AnalyticsListener.EVENT_PLAYER_RELEASED, 1050, 1050}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return WatchlistViewModel.this.new AnonymousClass1(dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:69:0x01f1, code lost:
        
            if (r0 != r8) goto L71;
         */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0072  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x0074  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0087  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x00a7 A[Catch: all -> 0x0021, Exception -> 0x0046, TryCatch #1 {Exception -> 0x0046, blocks: (B:18:0x0040, B:33:0x0089, B:35:0x00a7, B:37:0x00ba, B:38:0x00d6, B:40:0x00dc, B:42:0x00eb, B:43:0x00ef, B:44:0x00f8, B:46:0x00fe, B:48:0x010d, B:50:0x0112, B:51:0x0136, B:53:0x0148, B:54:0x0178, B:26:0x005b, B:30:0x0075, B:23:0x0051), top: B:90:0x000c, outer: #0 }] */
        /* JADX WARN: Removed duplicated region for block: B:57:0x0187  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x0085 -> B:33:0x0089). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r27) {
            /*
                Method dump skipped, instruction units count: 626
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$enrichWithPlaybackProgress$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistViewModel", f = "WatchlistViewModel.kt", l = {855}, m = "enrichWithPlaybackProgress", v = 2)
    public static final class C16231 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C16231(d7.d<? super C16231> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WatchlistViewModel.this.enrichWithPlaybackProgress(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$fetchLogos$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$fetchLogos$1", f = "WatchlistViewModel.kt", l = {897}, m = "invokeSuspend", v = 2)
    public static final class C16241 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ List<MediaItem> $items;
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ WatchlistViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C16241(List<MediaItem> list, WatchlistViewModel watchlistViewModel, d7.d<? super C16241> dVar) {
            super(2, dVar);
            this.$items = list;
            this.this$0 = watchlistViewModel;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new C16241(this.$items, this.this$0, dVar);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0, types: [int] */
        /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Iterable] */
        /* JADX WARN: Type inference failed for: r0v16 */
        /* JADX WARN: Type inference failed for: r0v17 */
        /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Iterable] */
        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            ?? r02 = this.label;
            t0 t0Var = t0.f22605a;
            try {
                if (r02 == 0) {
                    k2.c.G(obj);
                    List<MediaItem> list = this.$items;
                    HashSet hashSet = new HashSet();
                    ArrayList arrayList = new ArrayList();
                    for (Object obj2 : list) {
                        if (hashSet.add(WatchlistViewModelKt.watchlistLogoKey((MediaItem) obj2))) {
                            arrayList.add(obj2);
                        }
                    }
                    WatchlistViewModel watchlistViewModel = this.this$0;
                    ArrayList arrayList2 = new ArrayList();
                    for (Object obj3 : arrayList) {
                        String strWatchlistLogoKey = WatchlistViewModelKt.watchlistLogoKey((MediaItem) obj3);
                        if (!((Map) watchlistViewModel._logoUrls.getValue()).containsKey(strWatchlistLogoKey) && watchlistViewModel.logoRequestsInFlight.add(strWatchlistLogoKey)) {
                            arrayList2.add(obj3);
                        }
                    }
                    if (!arrayList2.isEmpty()) {
                        WatchlistViewModel$fetchLogos$1$resolved$1 watchlistViewModel$fetchLogos$1$resolved$1 = new WatchlistViewModel$fetchLogos$1$resolved$1(arrayList2, ua.j.a(5), this.this$0, null);
                        this.L$0 = arrayList2;
                        this.L$1 = null;
                        this.label = 1;
                        obj = l0.c(watchlistViewModel$fetchLogos$1$resolved$1, this);
                        e7.a aVar = e7.a.f15033i;
                        r02 = arrayList2;
                        if (obj == aVar) {
                            return aVar;
                        }
                    }
                    return t0Var;
                }
                if (r02 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                List list2 = (List) this.L$0;
                k2.c.G(obj);
                r02 = list2;
                List<x> list3 = (List) obj;
                ArrayList arrayList3 = new ArrayList();
                for (x xVar : list3) {
                    String str = (String) xVar.f22608i;
                    String str2 = (String) xVar.f22609l;
                    x xVar2 = str2 != null ? new x(str, str2) : null;
                    if (xVar2 != null) {
                        arrayList3.add(xVar2);
                    }
                }
                Map mapA0 = h0.A0(arrayList3);
                if (!mapA0.isEmpty()) {
                    this.this$0._logoUrls.setValue(h0.v0((Map) this.this$0._logoUrls.getValue(), mapA0));
                }
                return t0Var;
            } finally {
                WatchlistViewModel watchlistViewModel2 = this.this$0;
                Iterator it = r02.iterator();
                while (it.hasNext()) {
                    watchlistViewModel2.logoRequestsInFlight.remove(WatchlistViewModelKt.watchlistLogoKey((MediaItem) it.next()));
                }
            }
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16241) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$hydrateTrackerItems$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/MediaItem;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$hydrateTrackerItems$2", f = "WatchlistViewModel.kt", l = {794}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements p<k0, d7.d<? super List<? extends MediaItem>>, Object> {
        final /* synthetic */ List<MediaItem> $items;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ WatchlistViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(List<MediaItem> list, WatchlistViewModel watchlistViewModel, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$items = list;
            this.this$0 = watchlistViewModel;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$items, this.this$0, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            k0 k0Var = (k0) this.L$0;
            int i10 = this.label;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return obj;
            }
            k2.c.G(obj);
            ua.i iVarA = ua.j.a(6);
            List listX0 = kotlin.collections.x.X0(this.$items, 240);
            WatchlistViewModel watchlistViewModel = this.this$0;
            ArrayList arrayList = new ArrayList(s.U(listX0, 10));
            int i11 = 0;
            for (Object obj2 : listX0) {
                int i12 = i11 + 1;
                if (i11 < 0) {
                    t7.a.Q();
                    throw null;
                }
                arrayList.add(m0.e(3, null, k0Var, new WatchlistViewModel$hydrateTrackerItems$2$1$1(iVarA, (MediaItem) obj2, watchlistViewModel, i11, null)));
                i11 = i12;
            }
            this.L$0 = null;
            this.L$1 = null;
            this.label = 1;
            Object objF = m0.f(arrayList, this);
            e7.a aVar = e7.a.f15033i;
            return objF == aVar ? aVar : objF;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super List<MediaItem>> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$loadActiveSourceItems$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$loadActiveSourceItems$3", f = "WatchlistViewModel.kt", l = {642, 642}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass3 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ String $cacheKey;
        Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(String str, d7.d<? super AnonymousClass3> dVar) {
            super(2, dVar);
            this.$cacheKey = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return WatchlistViewModel.this.new AnonymousClass3(this.$cacheKey, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:15:0x0053, code lost:
        
            if (r1 == r4) goto L16;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r20) {
            /*
                Method dump skipped, instruction units count: 257
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel.AnonymousClass3.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass3) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$loadActiveSourceItems$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$loadActiveSourceItems$4", f = "WatchlistViewModel.kt", l = {665, 667}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass4 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ WatchlistSourceItem $activeSource;
        final /* synthetic */ String $cacheKey;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(WatchlistSourceItem watchlistSourceItem, String str, d7.d<? super AnonymousClass4> dVar) {
            super(2, dVar);
            this.$activeSource = watchlistSourceItem;
            this.$cacheKey = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass4 anonymousClass4 = WatchlistViewModel.this.new AnonymousClass4(this.$activeSource, this.$cacheKey, dVar);
            anonymousClass4.L$0 = obj;
            return anonymousClass4;
        }

        /* JADX WARN: Removed duplicated region for block: B:34:0x00c0  */
        /* JADX WARN: Removed duplicated region for block: B:53:0x0167  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r22) {
            /*
                Method dump skipped, instruction units count: 397
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel.AnonymousClass4.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass4) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$loadActiveSourceItems$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$loadActiveSourceItems$5", f = "WatchlistViewModel.kt", l = {700, 702, 705, 710, 712}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass5 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ WatchlistSourceItem $activeSource;
        final /* synthetic */ String $cacheKey;
        final /* synthetic */ boolean $forceRefresh;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        final /* synthetic */ WatchlistViewModel this$0;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$loadActiveSourceItems$5$WhenMappings */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[TrackerLibraryProvider.values().length];
                try {
                    iArr[TrackerLibraryProvider.TRAKT.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[TrackerLibraryProvider.SIMKL.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass5(WatchlistSourceItem watchlistSourceItem, WatchlistViewModel watchlistViewModel, boolean z, String str, d7.d<? super AnonymousClass5> dVar) {
            super(2, dVar);
            this.$activeSource = watchlistSourceItem;
            this.this$0 = watchlistViewModel;
            this.$forceRefresh = z;
            this.$cacheKey = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass5 anonymousClass5 = new AnonymousClass5(this.$activeSource, this.this$0, this.$forceRefresh, this.$cacheKey, dVar);
            anonymousClass5.L$0 = obj;
            return anonymousClass5;
        }

        /* JADX WARN: Removed duplicated region for block: B:57:0x0134  */
        /* JADX WARN: Removed duplicated region for block: B:63:0x0173  */
        /* JADX WARN: Removed duplicated region for block: B:78:0x01eb  */
        /* JADX WARN: Removed duplicated region for block: B:82:0x021a  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r22) {
            /*
                Method dump skipped, instruction units count: 576
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel.AnonymousClass5.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass5) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$loadActiveSourceItems$6, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$loadActiveSourceItems$6", f = "WatchlistViewModel.kt", l = {741, 748}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass6 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ WatchlistSourceItem $activeSource;
        final /* synthetic */ String $cacheKey;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass6(WatchlistSourceItem watchlistSourceItem, String str, d7.d<? super AnonymousClass6> dVar) {
            super(2, dVar);
            this.$activeSource = watchlistSourceItem;
            this.$cacheKey = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass6 anonymousClass6 = WatchlistViewModel.this.new AnonymousClass6(this.$activeSource, this.$cacheKey, dVar);
            anonymousClass6.L$0 = obj;
            return anonymousClass6;
        }

        /* JADX WARN: Code restructure failed: missing block: B:24:0x009e, code lost:
        
            if (r5 == r14) goto L25;
         */
        /* JADX WARN: Removed duplicated region for block: B:59:0x01b5  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r30) {
            /*
                Method dump skipped, instruction units count: 478
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel.AnonymousClass6.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass6) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$loadLibraryFirstPage$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$loadLibraryFirstPage$1", f = "WatchlistViewModel.kt", l = {468, 477}, m = "invokeSuspend", v = 2)
    public static final class C16251 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ String $cacheKey;
        final /* synthetic */ x $cached;
        final /* synthetic */ int $requestId;
        final /* synthetic */ HomeLibraryUiState $snapshot;
        final /* synthetic */ String $sourceRef;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C16251(String str, HomeLibraryUiState homeLibraryUiState, int i10, String str2, x xVar, d7.d<? super C16251> dVar) {
            super(2, dVar);
            this.$sourceRef = str;
            this.$snapshot = homeLibraryUiState;
            this.$requestId = i10;
            this.$cacheKey = str2;
            this.$cached = xVar;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C16251 c16251 = WatchlistViewModel.this.new C16251(this.$sourceRef, this.$snapshot, this.$requestId, this.$cacheKey, this.$cached, dVar);
            c16251.L$0 = obj;
            return c16251;
        }

        /* JADX WARN: Code restructure failed: missing block: B:26:0x009d, code lost:
        
            if (r0 == r13) goto L27;
         */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0134  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x0139  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r28) {
            /*
                Method dump skipped, instruction units count: 378
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel.C16251.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16251) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$loadMoreActiveSource$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$loadMoreActiveSource$1", f = "WatchlistViewModel.kt", l = {811, 823}, m = "invokeSuspend", v = 2)
    public static final class C16261 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ WatchlistSourceItem.HomeServer $activeSource;
        final /* synthetic */ String $cacheKey;
        final /* synthetic */ List<MediaItem> $currentItems;
        final /* synthetic */ SourcePageState $pageState;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C16261(WatchlistSourceItem.HomeServer homeServer, SourcePageState sourcePageState, List<MediaItem> list, String str, d7.d<? super C16261> dVar) {
            super(2, dVar);
            this.$activeSource = homeServer;
            this.$pageState = sourcePageState;
            this.$currentItems = list;
            this.$cacheKey = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C16261 c16261 = WatchlistViewModel.this.new C16261(this.$activeSource, this.$pageState, this.$currentItems, this.$cacheKey, dVar);
            c16261.L$0 = obj;
            return c16261;
        }

        /* JADX WARN: Removed duplicated region for block: B:42:0x0184  */
        /* JADX WARN: Removed duplicated region for block: B:45:0x01b0  */
        /* JADX WARN: Removed duplicated region for block: B:66:0x024f  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r33) {
            /*
                Method dump skipped, instruction units count: 689
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel.C16261.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16261) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$loadMoreLibrary$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$loadMoreLibrary$1", f = "WatchlistViewModel.kt", l = {515, 530}, m = "invokeSuspend", v = 2)
    public static final class C16271 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ String $cacheKey;
        final /* synthetic */ int $requestId;
        final /* synthetic */ HomeLibraryUiState $snapshot;
        final /* synthetic */ String $sourceRef;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C16271(String str, HomeLibraryUiState homeLibraryUiState, int i10, String str2, d7.d<? super C16271> dVar) {
            super(2, dVar);
            this.$sourceRef = str;
            this.$snapshot = homeLibraryUiState;
            this.$requestId = i10;
            this.$cacheKey = str2;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C16271 c16271 = WatchlistViewModel.this.new C16271(this.$sourceRef, this.$snapshot, this.$requestId, this.$cacheKey, dVar);
            c16271.L$0 = obj;
            return c16271;
        }

        /* JADX WARN: Code restructure failed: missing block: B:38:0x0162, code lost:
        
            if (r5 == r13) goto L39;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r23) {
            /*
                Method dump skipped, instruction units count: 487
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel.C16271.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16271) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$loadWatchlistInstant$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$loadWatchlistInstant$1", f = "WatchlistViewModel.kt", l = {922, 922, 936, 945, 945, 959, 961, 961, 976, 976, 989, 994, 994}, m = "invokeSuspend", v = 2)
    public static final class C16281 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        boolean Z$0;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$loadWatchlistInstant$1$3, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "Lx6/d0;", "Lcom/arflix/tv/data/repository/CloudSyncRepository$RestoreResult;", "<anonymous>", "(Lka/k0;)Lx6/d0;"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$loadWatchlistInstant$1$3", f = "WatchlistViewModel.kt", l = {937}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass3 extends f7.j implements p<k0, d7.d<? super d0>, Object> {
            int I$0;
            private /* synthetic */ Object L$0;
            Object L$1;
            int label;
            final /* synthetic */ WatchlistViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(WatchlistViewModel watchlistViewModel, d7.d<? super AnonymousClass3> dVar) {
                super(2, dVar);
                this.this$0 = watchlistViewModel;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.this$0, dVar);
                anonymousClass3.L$0 = obj;
                return anonymousClass3;
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                Object c0Var;
                int i10 = this.label;
                try {
                    if (i10 == 0) {
                        k2.c.G(obj);
                        CloudSyncRepository cloudSyncRepository = this.this$0.cloudSyncRepository;
                        this.L$0 = null;
                        this.L$1 = null;
                        this.I$0 = 0;
                        this.label = 1;
                        obj = CloudSyncRepository.pullFromCloud$default(cloudSyncRepository, false, this, 1, null);
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
                    c0Var = (CloudSyncRepository.RestoreResult) obj;
                } catch (Throwable th) {
                    c0Var = new c0(th);
                }
                WatchlistViewModel watchlistViewModel = this.this$0;
                Throwable thA = d0.a(c0Var);
                if (thA != null) {
                    AppLogger.INSTANCE.recordException(thA, WatchlistViewModel.watchlistDiagnosticContext$default(watchlistViewModel, "startup_cloud_pull", null, 2, null));
                }
                return new d0(c0Var);
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d7.d<? super d0> dVar) {
                return ((AnonymousClass3) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        public C16281(d7.d<? super C16281> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C16281 c16281 = WatchlistViewModel.this.new C16281(dVar);
            c16281.L$0 = obj;
            return c16281;
        }

        /* JADX WARN: Code restructure failed: missing block: B:158:0x053d, code lost:
        
            if (r0 != r7) goto L160;
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x0210, code lost:
        
            if (r0 != r7) goto L59;
         */
        /* JADX WARN: Code restructure failed: missing block: B:93:0x0324, code lost:
        
            if (r0 != r7) goto L95;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:117:0x03de  */
        /* JADX WARN: Removed duplicated region for block: B:124:0x0417 A[PHI: r0 r2
          0x0417: PHI (r0v18 boolean) = (r0v17 boolean), (r0v58 boolean) binds: [B:122:0x0413, B:9:0x0056] A[DONT_GENERATE, DONT_INLINE]
          0x0417: PHI (r2v22 java.lang.Object) = (r2v21 java.lang.Object), (r2v92 java.lang.Object) binds: [B:122:0x0413, B:9:0x0056] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:126:0x0442  */
        /* JADX WARN: Removed duplicated region for block: B:148:0x04f2 A[PHI: r0 r2
          0x04f2: PHI (r0v19 boolean) = (r0v18 boolean), (r0v59 boolean) binds: [B:146:0x04ef, B:8:0x0045] A[DONT_GENERATE, DONT_INLINE]
          0x04f2: PHI (r2v29 java.lang.Object) = (r2v26 java.lang.Object), (r2v97 java.lang.Object) binds: [B:146:0x04ef, B:8:0x0045] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:150:0x04f6  */
        /* JADX WARN: Removed duplicated region for block: B:151:0x04fb  */
        /* JADX WARN: Removed duplicated region for block: B:153:0x04fe  */
        /* JADX WARN: Removed duplicated region for block: B:180:0x063d  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x011a A[PHI: r2
          0x011a: PHI (r2v60 java.lang.Object) = (r2v57 java.lang.Object), (r2v65 java.lang.Object) binds: [B:28:0x0116, B:21:0x00d4] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0122  */
        /* JADX WARN: Removed duplicated region for block: B:49:0x01c9  */
        /* JADX WARN: Removed duplicated region for block: B:55:0x01fa  */
        /* JADX WARN: Removed duplicated region for block: B:76:0x02bc  */
        /* JADX WARN: Removed duplicated region for block: B:79:0x02d3  */
        /* JADX WARN: Removed duplicated region for block: B:85:0x02e7  */
        /* JADX WARN: Removed duplicated region for block: B:88:0x02f0  */
        /* JADX WARN: Type inference failed for: r0v21 */
        /* JADX WARN: Type inference failed for: r0v22, types: [int] */
        /* JADX WARN: Type inference failed for: r0v67 */
        /* JADX WARN: Type inference failed for: r4v22 */
        /* JADX WARN: Type inference failed for: r4v23, types: [int] */
        /* JADX WARN: Type inference failed for: r4v43 */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r26) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 1636
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel.C16281.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16281) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$observeCatalogsAndHomeServers$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$observeCatalogsAndHomeServers$1", f = "WatchlistViewModel.kt", l = {274}, m = "invokeSuspend", v = 2)
    public static final class C16291 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
        int label;

        public C16291(d7.d<? super C16291> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return WatchlistViewModel.this.new C16291(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                na.j<List<CatalogConfig>> jVarObserveCatalogs = WatchlistViewModel.this.catalogRepository.observeCatalogs();
                final WatchlistViewModel watchlistViewModel = WatchlistViewModel.this;
                na.k kVar = new na.k() { // from class: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel.observeCatalogsAndHomeServers.1.1
                    @Override // na.k
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, d7.d dVar) {
                        return emit((List<CatalogConfig>) obj2, (d7.d<? super t0>) dVar);
                    }

                    public final Object emit(List<CatalogConfig> list, d7.d<? super t0> dVar) {
                        WatchlistViewModel.updateAvailableSources$default(watchlistViewModel, list, null, 2, null);
                        return t0.f22605a;
                    }
                };
                this.label = 1;
                Object objCollect = jVarObserveCatalogs.collect(kVar, this);
                e7.a aVar = e7.a.f15033i;
                if (objCollect == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16291) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$observeCatalogsAndHomeServers$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$observeCatalogsAndHomeServers$2", f = "WatchlistViewModel.kt", l = {279}, m = "invokeSuspend", v = 2)
    public static final class C16302 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$observeCatalogsAndHomeServers$2$1, reason: invalid class name */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final class AnonymousClass1<T> implements na.k {
            final /* synthetic */ k0 $$this$launch;
            final /* synthetic */ WatchlistViewModel this$0;

            public AnonymousClass1(k0 k0Var, WatchlistViewModel watchlistViewModel) {
                this.$$this$launch = k0Var;
                this.this$0 = watchlistViewModel;
            }

            @Override // na.k
            public /* bridge */ /* synthetic */ Object emit(Object obj, d7.d dVar) {
                return emit((List<HomeServerConnection>) obj, (d7.d<? super t0>) dVar);
            }

            /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object emit(java.util.List<com.arflix.tv.data.repository.HomeServerConnection> r8, d7.d<? super x6.t0> r9) {
                /*
                    Method dump skipped, instruction units count: 243
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel.C16302.AnonymousClass1.emit(java.util.List, d7.d):java.lang.Object");
            }
        }

        public C16302(d7.d<? super C16302> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C16302 c16302 = WatchlistViewModel.this.new C16302(dVar);
            c16302.L$0 = obj;
            return c16302;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            k0 k0Var = (k0) this.L$0;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                na.j<List<HomeServerConnection>> connections = WatchlistViewModel.this.homeServerRepository.getConnections();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(k0Var, WatchlistViewModel.this);
                this.L$0 = null;
                this.label = 1;
                Object objCollect = connections.collect(anonymousClass1, this);
                e7.a aVar = e7.a.f15033i;
                if (objCollect == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16302) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$observeTrackerLibraries$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$observeTrackerLibraries$1", f = "WatchlistViewModel.kt", l = {297}, m = "invokeSuspend", v = 2)
    public static final class C16311 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$observeTrackerLibraries$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"", "profileId", "", "traktConnected", "Lx6/x;", "<anonymous>", "(Ljava/lang/String;Z)Lx6/x;"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$observeTrackerLibraries$1$1", f = "WatchlistViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C02191 extends f7.j implements q<String, Boolean, d7.d<? super x>, Object> {
            /* synthetic */ Object L$0;
            /* synthetic */ boolean Z$0;
            int label;

            public C02191(d7.d<? super C02191> dVar) {
                super(3, dVar);
            }

            @Override // r7.q
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                return invoke((String) obj, ((Boolean) obj2).booleanValue(), (d7.d<? super x>) obj3);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                String str = (String) this.L$0;
                boolean z = this.Z$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return new x(str, Boolean.valueOf(z));
            }

            public final Object invoke(String str, boolean z, d7.d<? super x> dVar) {
                C02191 c02191 = new C02191(dVar);
                c02191.L$0 = str;
                c02191.Z$0 = z;
                return c02191.invokeSuspend(t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$observeTrackerLibraries$1$2, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u00042\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lx6/x;", "", "", "<destruct>", "Lx6/t0;", "<anonymous>", "(Lx6/x;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$observeTrackerLibraries$1$2", f = "WatchlistViewModel.kt", l = {304}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass2 extends f7.j implements p<x, d7.d<? super t0>, Object> {
            /* synthetic */ Object L$0;
            boolean Z$0;
            int label;
            final /* synthetic */ WatchlistViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(WatchlistViewModel watchlistViewModel, d7.d<? super AnonymousClass2> dVar) {
                super(2, dVar);
                this.this$0 = watchlistViewModel;
            }

            private static final boolean invokeSuspend$lambda$0(String str) {
                return u.P(str, "tracker_", false);
            }

            private static final boolean invokeSuspend$lambda$1(String str) {
                return u.P(str, "tracker_", false);
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0, dVar);
                anonymousClass2.L$0 = obj;
                return anonymousClass2;
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                x xVar = (x) this.L$0;
                int i10 = this.label;
                if (i10 == 0) {
                    k2.c.G(obj);
                    boolean zBooleanValue = ((Boolean) xVar.f22609l).booleanValue();
                    v1 v1Var = this.this$0.sourceLoadJob;
                    if (v1Var != null) {
                        v1Var.cancel((CancellationException) null);
                    }
                    v1 v1Var2 = this.this$0.sourceLoadMoreJob;
                    if (v1Var2 != null) {
                        v1Var2.cancel((CancellationException) null);
                    }
                    Iterator it = this.this$0.sourceItemsCache.keySet().iterator();
                    while (it.hasNext()) {
                        if (invokeSuspend$lambda$0((String) it.next())) {
                            it.remove();
                        }
                    }
                    Iterator it2 = this.this$0.sourcePageStates.keySet().iterator();
                    while (it2.hasNext()) {
                        if (invokeSuspend$lambda$1((String) it2.next())) {
                            it2.remove();
                        }
                    }
                    this.this$0.currentTrackerLists = z.f19728i;
                    WatchlistViewModel.updateAvailableSources$default(this.this$0, null, null, 3, null);
                    WatchlistViewModel watchlistViewModel = this.this$0;
                    this.L$0 = null;
                    this.Z$0 = zBooleanValue;
                    this.label = 1;
                    Object objRefreshTrackerLibraries = watchlistViewModel.refreshTrackerLibraries(zBooleanValue, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objRefreshTrackerLibraries == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                }
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(x xVar, d7.d<? super t0> dVar) {
                return ((AnonymousClass2) create(xVar, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        public C16311(d7.d<? super C16311> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return WatchlistViewModel.this.new C16311(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                na.j jVarH = y0.h(new n0(WatchlistViewModel.this.profileManager.getActiveProfileId(), WatchlistViewModel.this.traktRepository.isAuthenticated(), new C02191(null)));
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(WatchlistViewModel.this, null);
                this.label = 1;
                Object objG = y0.g(jVarH, anonymousClass2, this);
                e7.a aVar = e7.a.f15033i;
                if (objG == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16311) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$observeWatchlistChanges$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$observeWatchlistChanges$1", f = "WatchlistViewModel.kt", l = {255}, m = "invokeSuspend", v = 2)
    public static final class C16321 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$observeWatchlistChanges$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final class C02201<T> implements na.k {
            final /* synthetic */ WatchlistViewModel this$0;

            public C02201(WatchlistViewModel watchlistViewModel) {
                this.this$0 = watchlistViewModel;
            }

            @Override // na.k
            public /* bridge */ /* synthetic */ Object emit(Object obj, d7.d dVar) {
                return emit((List<MediaItem>) obj, (d7.d<? super t0>) dVar);
            }

            /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object emit(java.util.List<com.arflix.tv.data.model.MediaItem> r21, d7.d<? super x6.t0> r22) {
                /*
                    Method dump skipped, instruction units count: 250
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel.C16321.C02201.emit(java.util.List, d7.d):java.lang.Object");
            }
        }

        public C16321(d7.d<? super C16321> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return WatchlistViewModel.this.new C16321(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                h1<List<MediaItem>> watchlistItems = WatchlistViewModel.this.watchlistRepository.getWatchlistItems();
                C02201 c02201 = new C02201(WatchlistViewModel.this);
                this.label = 1;
                Object objCollect = watchlistItems.collect(c02201, this);
                e7.a aVar = e7.a.f15033i;
                if (objCollect == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            throw new KotlinNothingValueException();
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16321) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$refresh$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$refresh$1", f = "WatchlistViewModel.kt", l = {1072, 1074, 1079, 1079, 1087}, m = "invokeSuspend", v = 2)
    public static final class C16331 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
        int I$0;
        int I$1;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        boolean Z$0;
        int label;

        public C16331(d7.d<? super C16331> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C16331 c16331 = WatchlistViewModel.this.new C16331(dVar);
            c16331.L$0 = obj;
            return c16331;
        }

        /* JADX WARN: Code restructure failed: missing block: B:75:0x0200, code lost:
        
            if (r0.showLocalWatchlistOrError(r3, r26) == r9) goto L76;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:49:0x0119 A[Catch: Exception -> 0x0022, TryCatch #2 {Exception -> 0x0022, blocks: (B:9:0x001d, B:15:0x002d, B:60:0x015d, B:61:0x018a, B:63:0x0190, B:65:0x019f, B:66:0x01a3, B:67:0x01ac, B:69:0x01b2, B:71:0x01c1, B:72:0x01c5, B:18:0x0042, B:57:0x0141, B:21:0x004f, B:47:0x0115, B:49:0x0119, B:53:0x0122, B:74:0x01e3, B:77:0x0203, B:39:0x00eb, B:42:0x00f2, B:44:0x00fa, B:38:0x00e5, B:29:0x00c4), top: B:89:0x0011 }] */
        /* JADX WARN: Removed duplicated region for block: B:52:0x0120 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:59:0x015b  */
        /* JADX WARN: Removed duplicated region for block: B:63:0x0190 A[Catch: Exception -> 0x0022, TryCatch #2 {Exception -> 0x0022, blocks: (B:9:0x001d, B:15:0x002d, B:60:0x015d, B:61:0x018a, B:63:0x0190, B:65:0x019f, B:66:0x01a3, B:67:0x01ac, B:69:0x01b2, B:71:0x01c1, B:72:0x01c5, B:18:0x0042, B:57:0x0141, B:21:0x004f, B:47:0x0115, B:49:0x0119, B:53:0x0122, B:74:0x01e3, B:77:0x0203, B:39:0x00eb, B:42:0x00f2, B:44:0x00fa, B:38:0x00e5, B:29:0x00c4), top: B:89:0x0011 }] */
        /* JADX WARN: Removed duplicated region for block: B:69:0x01b2 A[Catch: Exception -> 0x0022, TryCatch #2 {Exception -> 0x0022, blocks: (B:9:0x001d, B:15:0x002d, B:60:0x015d, B:61:0x018a, B:63:0x0190, B:65:0x019f, B:66:0x01a3, B:67:0x01ac, B:69:0x01b2, B:71:0x01c1, B:72:0x01c5, B:18:0x0042, B:57:0x0141, B:21:0x004f, B:47:0x0115, B:49:0x0119, B:53:0x0122, B:74:0x01e3, B:77:0x0203, B:39:0x00eb, B:42:0x00f2, B:44:0x00fa, B:38:0x00e5, B:29:0x00c4), top: B:89:0x0011 }] */
        /* JADX WARN: Removed duplicated region for block: B:74:0x01e3 A[Catch: Exception -> 0x0022, TryCatch #2 {Exception -> 0x0022, blocks: (B:9:0x001d, B:15:0x002d, B:60:0x015d, B:61:0x018a, B:63:0x0190, B:65:0x019f, B:66:0x01a3, B:67:0x01ac, B:69:0x01b2, B:71:0x01c1, B:72:0x01c5, B:18:0x0042, B:57:0x0141, B:21:0x004f, B:47:0x0115, B:49:0x0119, B:53:0x0122, B:74:0x01e3, B:77:0x0203, B:39:0x00eb, B:42:0x00f2, B:44:0x00fa, B:38:0x00e5, B:29:0x00c4), top: B:89:0x0011 }] */
        /* JADX WARN: Removed duplicated region for block: B:77:0x0203 A[Catch: Exception -> 0x0022, TRY_LEAVE, TryCatch #2 {Exception -> 0x0022, blocks: (B:9:0x001d, B:15:0x002d, B:60:0x015d, B:61:0x018a, B:63:0x0190, B:65:0x019f, B:66:0x01a3, B:67:0x01ac, B:69:0x01b2, B:71:0x01c1, B:72:0x01c5, B:18:0x0042, B:57:0x0141, B:21:0x004f, B:47:0x0115, B:49:0x0119, B:53:0x0122, B:74:0x01e3, B:77:0x0203, B:39:0x00eb, B:42:0x00f2, B:44:0x00fa, B:38:0x00e5, B:29:0x00c4), top: B:89:0x0011 }] */
        /* JADX WARN: Type inference failed for: r0v33 */
        /* JADX WARN: Type inference failed for: r0v35, types: [int] */
        /* JADX WARN: Type inference failed for: r0v48 */
        /* JADX WARN: Type inference failed for: r5v0 */
        /* JADX WARN: Type inference failed for: r5v12 */
        /* JADX WARN: Type inference failed for: r5v13 */
        /* JADX WARN: Type inference failed for: r5v2, types: [int] */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r27) throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 596
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel.C16331.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16331) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$refreshAfterResume$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$refreshAfterResume$1", f = "WatchlistViewModel.kt", l = {1112, 1114, 1116, 1116}, m = "invokeSuspend", v = 2)
    public static final class C16341 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        boolean Z$0;
        int label;

        public C16341(d7.d<? super C16341> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C16341 c16341 = WatchlistViewModel.this.new C16341(dVar);
            c16341.L$0 = obj;
            return c16341;
        }

        /* JADX WARN: Code restructure failed: missing block: B:47:0x00f9, code lost:
        
            if (r0 != r9) goto L49;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00ae  */
        /* JADX WARN: Removed duplicated region for block: B:45:0x00df  */
        /* JADX WARN: Type inference failed for: r3v0 */
        /* JADX WARN: Type inference failed for: r3v1, types: [int] */
        /* JADX WARN: Type inference failed for: r3v8 */
        /* JADX WARN: Type inference failed for: r7v7 */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r21) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 365
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel.C16341.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16341) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$refreshTrackerLibraries$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistViewModel", f = "WatchlistViewModel.kt", l = {BuildConfig.VERSION_CODE, MediaError.DetailedErrorCode.HLS_NETWORK_NO_KEY_RESPONSE}, m = "refreshTrackerLibraries", v = 2)
    public static final class C16351 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        boolean Z$0;
        boolean Z$1;
        int label;
        /* synthetic */ Object result;

        public C16351(d7.d<? super C16351> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WatchlistViewModel.this.refreshTrackerLibraries(false, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$removeFromWatchlist$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$removeFromWatchlist$1", f = "WatchlistViewModel.kt", l = {1146, 1150, 1154, 1167}, m = "invokeSuspend", v = 2)
    public static final class C16361 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ MediaItem $item;
        int I$0;
        int I$1;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        boolean Z$0;
        int label;
        final /* synthetic */ WatchlistViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C16361(MediaItem mediaItem, WatchlistViewModel watchlistViewModel, d7.d<? super C16361> dVar) {
            super(2, dVar);
            this.$item = mediaItem;
            this.this$0 = watchlistViewModel;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C16361 c16361 = new C16361(this.$item, this.this$0, dVar);
            c16361.L$0 = obj;
            return c16361;
        }

        /* JADX WARN: Code restructure failed: missing block: B:82:0x0201, code lost:
        
            if (r0 != r9) goto L84;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:53:0x00f2  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x00f3 A[Catch: Exception -> 0x004a, TryCatch #1 {Exception -> 0x004a, blocks: (B:87:0x020f, B:89:0x0217, B:86:0x020a, B:16:0x0045, B:60:0x012a, B:61:0x0146, B:63:0x014c, B:65:0x015d, B:69:0x016a, B:70:0x016e, B:71:0x017d, B:73:0x0183, B:75:0x0194, B:79:0x01a1, B:80:0x01a5, B:21:0x0051, B:51:0x00ea, B:56:0x0105, B:54:0x00f3, B:55:0x0104, B:36:0x0089, B:39:0x0090, B:41:0x00a0, B:43:0x00ae, B:48:0x00c6, B:35:0x0083, B:29:0x0067, B:24:0x005c, B:33:0x007d, B:30:0x0069, B:9:0x002b, B:84:0x0204, B:81:0x01e6), top: B:97:0x0011, inners: #0, #2 }] */
        /* JADX WARN: Removed duplicated region for block: B:58:0x0127  */
        /* JADX WARN: Removed duplicated region for block: B:59:0x0129  */
        /* JADX WARN: Removed duplicated region for block: B:63:0x014c A[Catch: Exception -> 0x004a, TryCatch #1 {Exception -> 0x004a, blocks: (B:87:0x020f, B:89:0x0217, B:86:0x020a, B:16:0x0045, B:60:0x012a, B:61:0x0146, B:63:0x014c, B:65:0x015d, B:69:0x016a, B:70:0x016e, B:71:0x017d, B:73:0x0183, B:75:0x0194, B:79:0x01a1, B:80:0x01a5, B:21:0x0051, B:51:0x00ea, B:56:0x0105, B:54:0x00f3, B:55:0x0104, B:36:0x0089, B:39:0x0090, B:41:0x00a0, B:43:0x00ae, B:48:0x00c6, B:35:0x0083, B:29:0x0067, B:24:0x005c, B:33:0x007d, B:30:0x0069, B:9:0x002b, B:84:0x0204, B:81:0x01e6), top: B:97:0x0011, inners: #0, #2 }] */
        /* JADX WARN: Removed duplicated region for block: B:73:0x0183 A[Catch: Exception -> 0x004a, TryCatch #1 {Exception -> 0x004a, blocks: (B:87:0x020f, B:89:0x0217, B:86:0x020a, B:16:0x0045, B:60:0x012a, B:61:0x0146, B:63:0x014c, B:65:0x015d, B:69:0x016a, B:70:0x016e, B:71:0x017d, B:73:0x0183, B:75:0x0194, B:79:0x01a1, B:80:0x01a5, B:21:0x0051, B:51:0x00ea, B:56:0x0105, B:54:0x00f3, B:55:0x0104, B:36:0x0089, B:39:0x0090, B:41:0x00a0, B:43:0x00ae, B:48:0x00c6, B:35:0x0083, B:29:0x0067, B:24:0x005c, B:33:0x007d, B:30:0x0069, B:9:0x002b, B:84:0x0204, B:81:0x01e6), top: B:97:0x0011, inners: #0, #2 }] */
        /* JADX WARN: Type inference failed for: r0v13 */
        /* JADX WARN: Type inference failed for: r0v14, types: [boolean, int] */
        /* JADX WARN: Type inference failed for: r0v21 */
        /* JADX WARN: Type inference failed for: r0v24 */
        /* JADX WARN: Type inference failed for: r0v56 */
        /* JADX WARN: Type inference failed for: r0v57 */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r26) throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 645
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel.C16361.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16361) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$setLibrarySearch$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$setLibrarySearch$1", f = "WatchlistViewModel.kt", l = {MediaError.DetailedErrorCode.SMOOTH_MANIFEST}, m = "invokeSuspend", v = 2)
    public static final class C16371 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
        int label;

        public C16371(d7.d<? super C16371> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return WatchlistViewModel.this.new C16371(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                this.label = 1;
                Object objA = s0.a(300L, this);
                e7.a aVar = e7.a.f15033i;
                if (objA == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            WatchlistViewModel.loadLibraryFirstPage$default(WatchlistViewModel.this, false, 1, null);
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16371) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$showLocalWatchlistOrError$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistViewModel", f = "WatchlistViewModel.kt", l = {1128, 1128}, m = "showLocalWatchlistOrError", v = 2)
    public static final class C16381 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C16381(d7.d<? super C16381> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WatchlistViewModel.this.showLocalWatchlistOrError(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$syncTraktWatchlistSuspend$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistViewModel", f = "WatchlistViewModel.kt", l = {1196, 1205, 1206, 1206, 1216, 1219, 1220, 1233, 1233}, m = "syncTraktWatchlistSuspend", v = 2)
    public static final class C16391 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public C16391(d7.d<? super C16391> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WatchlistViewModel.this.syncTraktWatchlistSuspend(this);
        }
    }

    @Inject
    public WatchlistViewModel(@ApplicationContext Context context, WatchlistRepository watchlistRepository, CloudSyncRepository cloudSyncRepository, TraktRepository traktRepository, RemoteSyncManager remoteSyncManager, MediaRepository mediaRepository, HomeServerRepository homeServerRepository, CatalogRepository catalogRepository, WatchHistoryRepository watchHistoryRepository, SimklAuthManager simklAuthManager, SimklSyncService simklSyncService, ProfileManager profileManager) {
        this.context = context;
        this.watchlistRepository = watchlistRepository;
        this.cloudSyncRepository = cloudSyncRepository;
        this.traktRepository = traktRepository;
        this.remoteSyncManager = remoteSyncManager;
        this.mediaRepository = mediaRepository;
        this.homeServerRepository = homeServerRepository;
        this.catalogRepository = catalogRepository;
        this.watchHistoryRepository = watchHistoryRepository;
        this.simklAuthManager = simklAuthManager;
        this.simklSyncService = simklSyncService;
        this.profileManager = profileManager;
        j1 j1VarB = y0.b(new WatchlistUiState(null, null, false, null, null, null, null, null, 0, 0, false, false, 4095, null));
        this._uiState = j1VarB;
        this.uiState = y0.e(j1VarB);
        j1 j1VarB2 = y0.b(a0.f19683i);
        this._logoUrls = j1VarB2;
        this.logoUrls = y0.e(j1VarB2);
        List list = null;
        boolean z = false;
        boolean z5 = false;
        HomeServerLibrarySort homeServerLibrarySort = null;
        String str = null;
        String str2 = null;
        j1 j1VarB3 = y0.b(new HomeLibraryUiState(null, list, null, null, null, z, z5, false, homeServerLibrarySort, str, str2, 2047, null));
        this._libraryState = j1VarB3;
        this.libraryState = y0.e(j1VarB3);
        this.libraryCache = new LinkedHashMap<>();
        this.sourceItemsCache = new LinkedHashMap<>();
        this.sourcePageStates = new LinkedHashMap();
        z zVar = z.f19728i;
        this.currentCatalogs = zVar;
        this.currentHomeServerCandidates = zVar;
        this.currentTrackerLists = zVar;
        this.logoRequestsInFlight = new LinkedHashSet();
        observeWatchlistChanges();
        observeCatalogsAndHomeServers();
        observeTrackerLibraries();
        loadWatchlistInstant();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void enrichLocalWatchlistInBackground() {
        if (this.enrichmentInFlight) {
            this.enrichmentRequested = true;
        } else {
            this.enrichmentInFlight = true;
            m0.p(z0.h(this), null, 0, new AnonymousClass1(null), 3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object enrichWithPlaybackProgress(java.util.List<com.arflix.tv.data.model.MediaItem> r57, d7.d<? super java.util.List<com.arflix.tv.data.model.MediaItem>> r58) {
        /*
            Method dump skipped, instruction units count: 376
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel.enrichWithPlaybackProgress(java.util.List, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void fetchLogos(List<MediaItem> items) {
        m0.p(z0.h(this), null, 0, new C16241(items, this, null), 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object hydrateTrackerItems(List<MediaItem> list, d7.d<? super List<MediaItem>> dVar) {
        return l0.c(new AnonymousClass2(list, this, null), dVar);
    }

    private final String libraryCacheKey(HomeLibraryUiState state) {
        String selectedSourceRef = state.getSelectedSourceRef();
        if (selectedSourceRef == null) {
            selectedSourceRef = "";
        }
        return kotlin.collections.x.u0(t7.a.E(selectedSourceRef, state.getSort().name(), kotlin.text.o.L0(state.getSearchQuery()).toString().toLowerCase(Locale.ROOT)), "|", null, null, null, 62);
    }

    private final void loadActiveSourceItems(boolean forceRefresh) {
        SourcePageState sourcePageState;
        WatchlistSourceItem selectedSource = ((WatchlistUiState) this._uiState.getValue()).getSelectedSource();
        String id = selectedSource.getId();
        List<MediaItem> list = this.sourceItemsCache.get(id);
        if (forceRefresh) {
            v1 v1Var = this.sourceLoadMoreJob;
            if (v1Var != null) {
                v1Var.cancel((CancellationException) null);
            }
            Map<String, SourcePageState> map = this.sourcePageStates;
            SourcePageState sourcePageState2 = map.get(id);
            if (sourcePageState2 == null || (sourcePageState = SourcePageState.copy$default(sourcePageState2, false, false, 0, 5, null)) == null) {
                sourcePageState = new SourcePageState(false, false, 0, 7, null);
            }
            map.put(id, sourcePageState);
        }
        if (list != null && !forceRefresh) {
            SourcePageState sourcePageState3 = this.sourcePageStates.get(id);
            if (sourcePageState3 == null) {
                sourcePageState3 = new SourcePageState(false, false, 0, 7, null);
            }
            q0<WatchlistUiState> q0Var = this._uiState;
            WatchlistUiState watchlistUiState = (WatchlistUiState) q0Var.getValue();
            ArrayList arrayList = new ArrayList();
            for (Object obj : list) {
                if (((MediaItem) obj).getMediaType() == MediaType.MOVIE) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = new ArrayList();
            for (Object obj2 : list) {
                if (((MediaItem) obj2).getMediaType() == MediaType.TV) {
                    arrayList2.add(obj2);
                }
            }
            q0Var.setValue(WatchlistUiState.copy$default(watchlistUiState, null, null, false, arrayList, arrayList2, null, null, null, 0, 0, sourcePageState3.getHasMore(), false, 963, null));
            fetchLogos(list);
            return;
        }
        if (selectedSource instanceof WatchlistSourceItem.MyWatchlist) {
            q0<WatchlistUiState> q0Var2 = this._uiState;
            q0Var2.setValue(WatchlistUiState.copy$default((WatchlistUiState) q0Var2.getValue(), null, null, list == null, null, null, null, null, null, 0, 0, false, false, 987, null));
            this.sourceLoadJob = m0.p(z0.h(this), null, 0, new AnonymousClass3(id, null), 3);
            return;
        }
        if (selectedSource instanceof WatchlistSourceItem.Catalog) {
            q0<WatchlistUiState> q0Var3 = this._uiState;
            q0Var3.setValue(WatchlistUiState.copy$default((WatchlistUiState) q0Var3.getValue(), null, null, list == null, null, null, null, null, null, 0, 0, false, false, 987, null));
            this.sourceLoadJob = m0.p(z0.h(this), null, 0, new AnonymousClass4(selectedSource, id, null), 3);
        } else if (selectedSource instanceof WatchlistSourceItem.TrackerList) {
            q0<WatchlistUiState> q0Var4 = this._uiState;
            q0Var4.setValue(WatchlistUiState.copy$default((WatchlistUiState) q0Var4.getValue(), null, null, list == null, null, null, null, null, null, 0, 0, false, false, 987, null));
            this.sourceLoadJob = m0.p(z0.h(this), null, 0, new AnonymousClass5(selectedSource, this, forceRefresh, id, null), 3);
        } else {
            if (!(selectedSource instanceof WatchlistSourceItem.HomeServer)) {
                throw new NoWhenBranchMatchedException();
            }
            q0<WatchlistUiState> q0Var5 = this._uiState;
            q0Var5.setValue(WatchlistUiState.copy$default((WatchlistUiState) q0Var5.getValue(), null, null, list == null, null, null, null, null, null, 0, 0, false, false, 2011, null));
            this.sourceLoadJob = m0.p(z0.h(this), null, 0, new AnonymousClass6(selectedSource, id, null), 3);
        }
    }

    public static /* synthetic */ void loadActiveSourceItems$default(WatchlistViewModel watchlistViewModel, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        watchlistViewModel.loadActiveSourceItems(z);
    }

    private final void loadLibraryFirstPage(boolean force) {
        String str;
        String str2;
        x xVar;
        HomeLibraryUiState homeLibraryUiState = (HomeLibraryUiState) this._libraryState.getValue();
        String selectedSourceRef = homeLibraryUiState.getSelectedSourceRef();
        if (selectedSourceRef == null) {
            return;
        }
        String strLibraryCacheKey = libraryCacheKey(homeLibraryUiState);
        x xVar2 = this.libraryCache.get(strLibraryCacheKey);
        if (xVar2 == null || force) {
            str = selectedSourceRef;
            str2 = strLibraryCacheKey;
            xVar = xVar2;
            this._libraryState.setValue(HomeLibraryUiState.copy$default(homeLibraryUiState, null, null, null, null, null, true, false, false, null, null, null, 927, null));
        } else {
            str2 = strLibraryCacheKey;
            xVar = xVar2;
            str = selectedSourceRef;
            this._libraryState.setValue(HomeLibraryUiState.copy$default(homeLibraryUiState, null, null, null, null, (List) xVar2.f22608i, false, false, ((Boolean) xVar2.f22609l).booleanValue(), null, null, null, 783, null));
        }
        int i10 = this.libraryRequestId + 1;
        this.libraryRequestId = i10;
        v1 v1Var = this.libraryLoadJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.libraryLoadJob = m0.p(z0.h(this), null, 0, new C16251(str, homeLibraryUiState, i10, str2, xVar, null), 3);
    }

    public static /* synthetic */ void loadLibraryFirstPage$default(WatchlistViewModel watchlistViewModel, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        watchlistViewModel.loadLibraryFirstPage(z);
    }

    private final void loadWatchlistInstant() {
        this.sourceLoadJob = m0.p(z0.h(this), null, 0, new C16281(null), 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean needsArtworkEnrichment(List<MediaItem> list) {
        String backdrop;
        if (list != null && list.isEmpty()) {
            return false;
        }
        for (MediaItem mediaItem : list) {
            if (kotlin.text.o.h0(mediaItem.getImage()) && ((backdrop = mediaItem.getBackdrop()) == null || kotlin.text.o.h0(backdrop))) {
                return true;
            }
        }
        return false;
    }

    private final void observeCatalogsAndHomeServers() {
        m0.p(z0.h(this), null, 0, new C16291(null), 3);
        m0.p(z0.h(this), null, 0, new C16302(null), 3);
    }

    private final void observeTrackerLibraries() {
        m0.p(z0.h(this), null, 0, new C16311(null), 3);
    }

    private final void observeWatchlistChanges() {
        m0.p(z0.h(this), null, 0, new C16321(null), 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object refreshTrackerLibraries(boolean r8, d7.d<? super x6.t0> r9) {
        /*
            Method dump skipped, instruction units count: 298
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel.refreshTrackerLibraries(boolean, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object showLocalWatchlistOrError(java.lang.String r21, d7.d<? super x6.t0> r22) {
        /*
            Method dump skipped, instruction units count: 283
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel.showLocalWatchlistOrError(java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x0334, code lost:
    
        if (r0 != r8) goto L125;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0218, code lost:
    
        if (com.arflix.tv.data.repository.CloudSyncRepository.m6121pushToCloudgIAlus$default(r0, false, r2, 1, null) != r8) goto L153;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x028e A[Catch: all -> 0x003e, Exception -> 0x0041, TryCatch #2 {Exception -> 0x0041, blocks: (B:13:0x0039, B:125:0x0337, B:127:0x035a, B:128:0x036c, B:130:0x0372, B:132:0x0381, B:133:0x0385, B:134:0x038e, B:136:0x0394, B:138:0x03a3, B:139:0x03a7, B:20:0x0056, B:122:0x031c, B:23:0x0063, B:101:0x026b, B:103:0x028e, B:104:0x02a0, B:106:0x02a6, B:108:0x02b5, B:109:0x02b9, B:110:0x02c2, B:112:0x02c8, B:114:0x02d7, B:115:0x02db, B:117:0x02fb, B:26:0x007a, B:96:0x0248, B:98:0x0252, B:32:0x00ab, B:70:0x016f, B:72:0x0192, B:73:0x01a4, B:75:0x01aa, B:77:0x01b9, B:78:0x01bd, B:79:0x01c6, B:81:0x01cc, B:83:0x01db, B:85:0x01e0, B:35:0x00c6, B:67:0x0152, B:38:0x00d9, B:63:0x0137, B:39:0x00dd, B:49:0x00fb, B:51:0x00ff, B:54:0x0107, B:56:0x010d, B:57:0x010f, B:59:0x0119, B:90:0x021e, B:92:0x022a, B:118:0x0300, B:46:0x00ef), top: B:154:0x0026, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x010d A[Catch: all -> 0x003e, Exception -> 0x0041, TryCatch #2 {Exception -> 0x0041, blocks: (B:13:0x0039, B:125:0x0337, B:127:0x035a, B:128:0x036c, B:130:0x0372, B:132:0x0381, B:133:0x0385, B:134:0x038e, B:136:0x0394, B:138:0x03a3, B:139:0x03a7, B:20:0x0056, B:122:0x031c, B:23:0x0063, B:101:0x026b, B:103:0x028e, B:104:0x02a0, B:106:0x02a6, B:108:0x02b5, B:109:0x02b9, B:110:0x02c2, B:112:0x02c8, B:114:0x02d7, B:115:0x02db, B:117:0x02fb, B:26:0x007a, B:96:0x0248, B:98:0x0252, B:32:0x00ab, B:70:0x016f, B:72:0x0192, B:73:0x01a4, B:75:0x01aa, B:77:0x01b9, B:78:0x01bd, B:79:0x01c6, B:81:0x01cc, B:83:0x01db, B:85:0x01e0, B:35:0x00c6, B:67:0x0152, B:38:0x00d9, B:63:0x0137, B:39:0x00dd, B:49:0x00fb, B:51:0x00ff, B:54:0x0107, B:56:0x010d, B:57:0x010f, B:59:0x0119, B:90:0x021e, B:92:0x022a, B:118:0x0300, B:46:0x00ef), top: B:154:0x0026, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0119 A[Catch: all -> 0x003e, Exception -> 0x0041, TryCatch #2 {Exception -> 0x0041, blocks: (B:13:0x0039, B:125:0x0337, B:127:0x035a, B:128:0x036c, B:130:0x0372, B:132:0x0381, B:133:0x0385, B:134:0x038e, B:136:0x0394, B:138:0x03a3, B:139:0x03a7, B:20:0x0056, B:122:0x031c, B:23:0x0063, B:101:0x026b, B:103:0x028e, B:104:0x02a0, B:106:0x02a6, B:108:0x02b5, B:109:0x02b9, B:110:0x02c2, B:112:0x02c8, B:114:0x02d7, B:115:0x02db, B:117:0x02fb, B:26:0x007a, B:96:0x0248, B:98:0x0252, B:32:0x00ab, B:70:0x016f, B:72:0x0192, B:73:0x01a4, B:75:0x01aa, B:77:0x01b9, B:78:0x01bd, B:79:0x01c6, B:81:0x01cc, B:83:0x01db, B:85:0x01e0, B:35:0x00c6, B:67:0x0152, B:38:0x00d9, B:63:0x0137, B:39:0x00dd, B:49:0x00fb, B:51:0x00ff, B:54:0x0107, B:56:0x010d, B:57:0x010f, B:59:0x0119, B:90:0x021e, B:92:0x022a, B:118:0x0300, B:46:0x00ef), top: B:154:0x0026, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x016f A[Catch: all -> 0x003e, Exception -> 0x0041, PHI: r0 r3
      0x016f: PHI (r0v20 java.lang.Object) = (r0v19 java.lang.Object), (r0v1 java.lang.Object) binds: [B:68:0x016b, B:32:0x00ab] A[DONT_GENERATE, DONT_INLINE]
      0x016f: PHI (r3v22 int) = (r3v20 int), (r3v24 int) binds: [B:68:0x016b, B:32:0x00ab] A[DONT_GENERATE, DONT_INLINE], TryCatch #2 {Exception -> 0x0041, blocks: (B:13:0x0039, B:125:0x0337, B:127:0x035a, B:128:0x036c, B:130:0x0372, B:132:0x0381, B:133:0x0385, B:134:0x038e, B:136:0x0394, B:138:0x03a3, B:139:0x03a7, B:20:0x0056, B:122:0x031c, B:23:0x0063, B:101:0x026b, B:103:0x028e, B:104:0x02a0, B:106:0x02a6, B:108:0x02b5, B:109:0x02b9, B:110:0x02c2, B:112:0x02c8, B:114:0x02d7, B:115:0x02db, B:117:0x02fb, B:26:0x007a, B:96:0x0248, B:98:0x0252, B:32:0x00ab, B:70:0x016f, B:72:0x0192, B:73:0x01a4, B:75:0x01aa, B:77:0x01b9, B:78:0x01bd, B:79:0x01c6, B:81:0x01cc, B:83:0x01db, B:85:0x01e0, B:35:0x00c6, B:67:0x0152, B:38:0x00d9, B:63:0x0137, B:39:0x00dd, B:49:0x00fb, B:51:0x00ff, B:54:0x0107, B:56:0x010d, B:57:0x010f, B:59:0x0119, B:90:0x021e, B:92:0x022a, B:118:0x0300, B:46:0x00ef), top: B:154:0x0026, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0192 A[Catch: all -> 0x003e, Exception -> 0x0041, TryCatch #2 {Exception -> 0x0041, blocks: (B:13:0x0039, B:125:0x0337, B:127:0x035a, B:128:0x036c, B:130:0x0372, B:132:0x0381, B:133:0x0385, B:134:0x038e, B:136:0x0394, B:138:0x03a3, B:139:0x03a7, B:20:0x0056, B:122:0x031c, B:23:0x0063, B:101:0x026b, B:103:0x028e, B:104:0x02a0, B:106:0x02a6, B:108:0x02b5, B:109:0x02b9, B:110:0x02c2, B:112:0x02c8, B:114:0x02d7, B:115:0x02db, B:117:0x02fb, B:26:0x007a, B:96:0x0248, B:98:0x0252, B:32:0x00ab, B:70:0x016f, B:72:0x0192, B:73:0x01a4, B:75:0x01aa, B:77:0x01b9, B:78:0x01bd, B:79:0x01c6, B:81:0x01cc, B:83:0x01db, B:85:0x01e0, B:35:0x00c6, B:67:0x0152, B:38:0x00d9, B:63:0x0137, B:39:0x00dd, B:49:0x00fb, B:51:0x00ff, B:54:0x0107, B:56:0x010d, B:57:0x010f, B:59:0x0119, B:90:0x021e, B:92:0x022a, B:118:0x0300, B:46:0x00ef), top: B:154:0x0026, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x021c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object syncTraktWatchlistSuspend(d7.d<? super java.lang.Boolean> r28) {
        /*
            Method dump skipped, instruction units count: 1022
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel.syncTraktWatchlistSuspend(d7.d):java.lang.Object");
    }

    private final void updateAvailableSources(List<CatalogConfig> catalogs, List<HomeServerCatalogCandidate> homeServerCandidates) {
        boolean z;
        if (catalogs != null) {
            this.currentCatalogs = catalogs;
        }
        if (homeServerCandidates != null) {
            this.currentHomeServerCandidates = homeServerCandidates;
        }
        List<WatchlistSourceItem> listBuildWatchlistSources = WatchlistViewModelKt.buildWatchlistSources(this.currentCatalogs, this.currentHomeServerCandidates, this.currentTrackerLists);
        List<WatchlistSourceItem> sources = ((WatchlistUiState) this._uiState.getValue()).getSources();
        int iQ0 = i0.q0(s.U(sources, 10));
        if (iQ0 < 16) {
            iQ0 = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iQ0);
        for (Object obj : sources) {
            linkedHashMap.put(((WatchlistSourceItem) obj).getId(), obj);
        }
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = listBuildWatchlistSources.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            WatchlistSourceItem watchlistSourceItem = (WatchlistSourceItem) it.next();
            String id = kotlin.jvm.internal.p.a(linkedHashMap.get(watchlistSourceItem.getId()), watchlistSourceItem) ? null : watchlistSourceItem.getId();
            if (id != null) {
                arrayList.add(id);
            }
        }
        Set<String> setG1 = kotlin.collections.x.g1(arrayList);
        for (String str : setG1) {
            this.sourceItemsCache.remove(str);
            this.sourcePageStates.remove(str);
        }
        String selectedSourceId = ((WatchlistUiState) this._uiState.getValue()).getSelectedSourceId();
        if (listBuildWatchlistSources.isEmpty()) {
            z = false;
        } else {
            Iterator<T> it2 = listBuildWatchlistSources.iterator();
            while (it2.hasNext()) {
                if (kotlin.jvm.internal.p.a(((WatchlistSourceItem) it2.next()).getId(), selectedSourceId)) {
                    z = true;
                    break;
                }
            }
            z = false;
        }
        String id2 = z ? selectedSourceId : WatchlistSourceItem.MyWatchlist.INSTANCE.getId();
        q0<WatchlistUiState> q0Var = this._uiState;
        String str2 = id2;
        boolean z5 = z;
        q0Var.setValue(WatchlistUiState.copy$default((WatchlistUiState) q0Var.getValue(), listBuildWatchlistSources, str2, false, null, null, null, null, null, 0, 0, false, false, 4092, null));
        if ((z5 || kotlin.jvm.internal.p.a(selectedSourceId, WatchlistSourceItem.MyWatchlist.INSTANCE.getId())) && !setG1.contains(str2)) {
            return;
        }
        loadActiveSourceItems$default(this, false, 1, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void updateAvailableSources$default(WatchlistViewModel watchlistViewModel, List list, List list2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            list = null;
        }
        if ((i10 & 2) != 0) {
            list2 = null;
        }
        watchlistViewModel.updateAvailableSources(list, list2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateHomeLibraryState(List<? extends HomeServerKind> usableProviders, List<HomeServerCatalogCandidate> candidates) {
        String str;
        List listW0 = kotlin.collections.x.W0(kotlin.collections.x.g0(usableProviders), new Comparator() { // from class: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$updateHomeLibraryState$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                int i10;
                int[] iArr = WatchlistViewModel.WhenMappings.$EnumSwitchMapping$0;
                int i11 = iArr[((HomeServerKind) t2).ordinal()];
                int i12 = 0;
                if (i11 == 1) {
                    i10 = 0;
                } else if (i11 == 2) {
                    i10 = 1;
                } else if (i11 == 3) {
                    i10 = 2;
                } else {
                    if (i11 != 4) {
                        throw new NoWhenBranchMatchedException();
                    }
                    i10 = 3;
                }
                Integer numValueOf = Integer.valueOf(i10);
                int i13 = iArr[((HomeServerKind) t10).ordinal()];
                if (i13 != 1) {
                    if (i13 == 2) {
                        i12 = 1;
                    } else if (i13 == 3) {
                        i12 = 2;
                    } else {
                        if (i13 != 4) {
                            throw new NoWhenBranchMatchedException();
                        }
                        i12 = 3;
                    }
                }
                return r0.e(numValueOf, Integer.valueOf(i12));
            }
        });
        HomeLibraryUiState homeLibraryUiState = (HomeLibraryUiState) this._libraryState.getValue();
        HomeServerKind selectedProvider = homeLibraryUiState.getSelectedProvider();
        HomeServerKind homeServerKind = (selectedProvider == null || !listW0.contains(selectedProvider)) ? null : selectedProvider;
        String selectedSourceRef = homeLibraryUiState.getSelectedSourceRef();
        if (selectedSourceRef == null || (candidates != null && candidates.isEmpty())) {
            str = null;
        } else {
            for (HomeServerCatalogCandidate homeServerCatalogCandidate : candidates) {
                if (kotlin.jvm.internal.p.a(homeServerCatalogCandidate.getSourceRef(), selectedSourceRef) && homeServerCatalogCandidate.getServerKind() == homeServerKind) {
                    str = selectedSourceRef;
                    break;
                }
            }
            str = null;
        }
        this._libraryState.setValue(HomeLibraryUiState.copy$default(homeLibraryUiState, listW0, candidates, homeServerKind, str, homeServerKind == null ? z.f19728i : homeLibraryUiState.getItems(), false, false, false, null, null, null, CastStatusCodes.DEVICE_CONNECTION_SUSPENDED, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Map<String, String> watchlistDiagnosticContext(String phase, Map<String, String> extra) {
        LinkedHashMap linkedHashMapU0 = h0.u0(new x("error_area", "Watchlist"), new x("watchlist_phase", phase), new x("visible_count", String.valueOf(((WatchlistUiState) this._uiState.getValue()).getAllItems().size())));
        linkedHashMapU0.putAll(extra);
        return linkedHashMapU0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Map watchlistDiagnosticContext$default(WatchlistViewModel watchlistViewModel, String str, Map map, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            map = a0.f19683i;
        }
        return watchlistViewModel.watchlistDiagnosticContext(str, map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<MediaItem> watchlistDisplayOrder(List<MediaItem> list) {
        final Comparator comparator = new Comparator() { // from class: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$watchlistDisplayOrder$$inlined$compareBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                return r0.e(Integer.valueOf(((MediaItem) t2).getSourceOrder()), Integer.valueOf(((MediaItem) t10).getSourceOrder()));
            }
        };
        return kotlin.collections.x.W0(list, new Comparator() { // from class: com.arflix.tv.ui.screens.watchlist.WatchlistViewModel$watchlistDisplayOrder$$inlined$thenByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                int iCompare = comparator.compare(t2, t10);
                return iCompare != 0 ? iCompare : r0.e(Long.valueOf(((MediaItem) t10).getAddedAt()), Long.valueOf(((MediaItem) t2).getAddedAt()));
            }
        });
    }

    public final void dismissToast() {
        q0<WatchlistUiState> q0Var = this._uiState;
        q0Var.setValue(WatchlistUiState.copy$default((WatchlistUiState) q0Var.getValue(), null, null, false, null, null, null, null, null, 0, 0, false, false, 4031, null));
    }

    public final void ensureLogo(MediaItem item) {
        fetchLogos(Collections.singletonList(item));
    }

    public final h1<HomeLibraryUiState> getLibraryState() {
        return this.libraryState;
    }

    public final h1<Map<String, String>> getLogoUrls() {
        return this.logoUrls;
    }

    public final h1<WatchlistUiState> getUiState() {
        return this.uiState;
    }

    public final void loadMoreActiveSource() {
        String id;
        SourcePageState sourcePageState;
        WatchlistSourceItem selectedSource = ((WatchlistUiState) this._uiState.getValue()).getSelectedSource();
        WatchlistSourceItem.HomeServer homeServer = selectedSource instanceof WatchlistSourceItem.HomeServer ? (WatchlistSourceItem.HomeServer) selectedSource : null;
        if (homeServer == null || (sourcePageState = this.sourcePageStates.get((id = homeServer.getId()))) == null) {
            return;
        }
        List<MediaItem> list = this.sourceItemsCache.get(id);
        if (list == null) {
            list = z.f19728i;
        }
        List<MediaItem> list2 = list;
        if (!sourcePageState.getHasMore() || sourcePageState.isLoadingMore() || list2.isEmpty()) {
            return;
        }
        this.sourcePageStates.put(id, SourcePageState.copy$default(sourcePageState, false, true, 0, 5, null));
        if (kotlin.jvm.internal.p.a(((WatchlistUiState) this._uiState.getValue()).getSelectedSourceId(), id)) {
            q0<WatchlistUiState> q0Var = this._uiState;
            q0Var.setValue(WatchlistUiState.copy$default((WatchlistUiState) q0Var.getValue(), null, null, false, null, null, null, null, null, 0, 0, false, true, 2047, null));
        }
        v1 v1Var = this.sourceLoadMoreJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.sourceLoadMoreJob = m0.p(z0.h(this), null, 0, new C16261(homeServer, sourcePageState, list2, id, null), 3);
    }

    public final void loadMoreLibrary() {
        HomeLibraryUiState homeLibraryUiState = (HomeLibraryUiState) this._libraryState.getValue();
        String selectedSourceRef = homeLibraryUiState.getSelectedSourceRef();
        if (selectedSourceRef == null || !homeLibraryUiState.getHasMore() || homeLibraryUiState.isLoading() || homeLibraryUiState.isLoadingMore()) {
            return;
        }
        int i10 = this.libraryRequestId;
        String strLibraryCacheKey = libraryCacheKey(homeLibraryUiState);
        this._libraryState.setValue(HomeLibraryUiState.copy$default(homeLibraryUiState, null, null, null, null, null, false, true, false, null, null, null, 1983, null));
        m0.p(z0.h(this), null, 0, new C16271(selectedSourceRef, homeLibraryUiState, i10, strLibraryCacheKey, null), 3);
    }

    public final void refresh() {
        if (kotlin.jvm.internal.p.a(((WatchlistUiState) this._uiState.getValue()).getSelectedSourceId(), WatchlistSourceItem.MyWatchlist.INSTANCE.getId())) {
            m0.p(z0.h(this), null, 0, new C16331(null), 3);
        } else {
            loadActiveSourceItems(true);
        }
    }

    public final void refreshAfterResume() {
        if (this.initialLoadComplete) {
            if (kotlin.jvm.internal.p.a(((WatchlistUiState) this._uiState.getValue()).getSelectedSourceId(), WatchlistSourceItem.MyWatchlist.INSTANCE.getId())) {
                m0.p(z0.h(this), null, 0, new C16341(null), 3);
            } else {
                loadActiveSourceItems(true);
            }
        }
    }

    public final void refreshLibrary() {
        loadLibraryFirstPage(true);
    }

    public final void removeFromWatchlist(MediaItem item) {
        if (kotlin.jvm.internal.p.a(((WatchlistUiState) this._uiState.getValue()).getSelectedSourceId(), WatchlistSourceItem.MyWatchlist.INSTANCE.getId())) {
            m0.p(z0.h(this), null, 0, new C16361(item, this, null), 3);
        }
    }

    public final void saveFocusState(int sectionIndex, int itemIndex) {
        q0<WatchlistUiState> q0Var = this._uiState;
        q0Var.setValue(WatchlistUiState.copy$default((WatchlistUiState) q0Var.getValue(), null, null, false, null, null, null, null, null, sectionIndex, itemIndex, false, false, 3327, null));
    }

    public final void selectLibrary(String sourceRef) {
        if (kotlin.jvm.internal.p.a(((HomeLibraryUiState) this._libraryState.getValue()).getSelectedSourceRef(), sourceRef)) {
            return;
        }
        v1 v1Var = this.librarySearchJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        q0<HomeLibraryUiState> q0Var = this._libraryState;
        q0Var.setValue(HomeLibraryUiState.copy$default((HomeLibraryUiState) q0Var.getValue(), null, null, null, sourceRef, z.f19728i, false, false, false, null, null, null, 935, null));
        loadLibraryFirstPage$default(this, false, 1, null);
    }

    public final void selectLibraryProvider(HomeServerKind provider) {
        Object next;
        HomeLibraryUiState homeLibraryUiState = (HomeLibraryUiState) this._libraryState.getValue();
        if (homeLibraryUiState.getSelectedProvider() == provider) {
            return;
        }
        v1 v1Var = this.libraryLoadJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        v1 v1Var2 = this.librarySearchJob;
        if (v1Var2 != null) {
            v1Var2.cancel((CancellationException) null);
        }
        z zVar = z.f19728i;
        if (provider == null) {
            this._libraryState.setValue(HomeLibraryUiState.copy$default(homeLibraryUiState, null, null, null, null, zVar, false, false, false, null, null, null, 899, null));
            return;
        }
        Iterator<T> it = homeLibraryUiState.getLibraries().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (((HomeServerCatalogCandidate) next).getServerKind() == provider) {
                    break;
                }
            }
        }
        HomeServerCatalogCandidate homeServerCatalogCandidate = (HomeServerCatalogCandidate) next;
        this._libraryState.setValue(HomeLibraryUiState.copy$default(homeLibraryUiState, null, null, provider, homeServerCatalogCandidate != null ? homeServerCatalogCandidate.getSourceRef() : null, zVar, homeServerCatalogCandidate != null, false, false, null, null, null, 899, null));
        if (homeServerCatalogCandidate != null) {
            loadLibraryFirstPage$default(this, false, 1, null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [kotlin.collections.z] */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v3, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r11v0 */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v3, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r6v2, types: [java.util.List] */
    public final void selectSource(String sourceId) {
        ?? arrayList;
        if (kotlin.jvm.internal.p.a(((WatchlistUiState) this._uiState.getValue()).getSelectedSourceId(), sourceId)) {
            return;
        }
        v1 v1Var = this.sourceLoadJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        v1 v1Var2 = this.sourceLoadMoreJob;
        if (v1Var2 != null) {
            v1Var2.cancel((CancellationException) null);
        }
        List<MediaItem> list = this.sourceItemsCache.get(sourceId);
        SourcePageState sourcePageState = this.sourcePageStates.get(sourceId);
        if (sourcePageState == null) {
            sourcePageState = new SourcePageState(false, false, 0, 7, null);
        }
        q0<WatchlistUiState> q0Var = this._uiState;
        WatchlistUiState watchlistUiState = (WatchlistUiState) q0Var.getValue();
        SourcePageState sourcePageState2 = sourcePageState;
        boolean z = list == null;
        ?? arrayList2 = z.f19728i;
        if (list != null) {
            arrayList = new ArrayList();
            for (Object obj : list) {
                if (((MediaItem) obj).getMediaType() == MediaType.MOVIE) {
                    arrayList.add(obj);
                }
            }
        } else {
            arrayList = arrayList2;
        }
        if (list != null) {
            arrayList2 = new ArrayList();
            for (Object obj2 : list) {
                if (((MediaItem) obj2).getMediaType() == MediaType.TV) {
                    arrayList2.add(obj2);
                }
            }
        }
        q0Var.setValue(WatchlistUiState.copy$default(watchlistUiState, null, sourceId, z, arrayList, arrayList2, null, null, null, 0, 0, sourcePageState2.getHasMore(), false, 193, null));
        loadActiveSourceItems$default(this, false, 1, null);
    }

    public final void setLibrarySearch(String query) {
        if (kotlin.jvm.internal.p.a(((HomeLibraryUiState) this._libraryState.getValue()).getSearchQuery(), query)) {
            return;
        }
        this.libraryRequestId++;
        v1 v1Var = this.libraryLoadJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        q0<HomeLibraryUiState> q0Var = this._libraryState;
        q0Var.setValue(HomeLibraryUiState.copy$default((HomeLibraryUiState) q0Var.getValue(), null, null, null, null, null, false, false, false, null, query, null, 1535, null));
        v1 v1Var2 = this.librarySearchJob;
        if (v1Var2 != null) {
            v1Var2.cancel((CancellationException) null);
        }
        this.librarySearchJob = m0.p(z0.h(this), null, 0, new C16371(null), 3);
    }

    public final void setLibrarySort(HomeServerLibrarySort sort) {
        if (((HomeLibraryUiState) this._libraryState.getValue()).getSort() == sort) {
            return;
        }
        q0<HomeLibraryUiState> q0Var = this._libraryState;
        q0Var.setValue(HomeLibraryUiState.copy$default((HomeLibraryUiState) q0Var.getValue(), null, null, null, null, null, false, false, false, sort, null, null, 767, null));
        loadLibraryFirstPage$default(this, false, 1, null);
    }
}
