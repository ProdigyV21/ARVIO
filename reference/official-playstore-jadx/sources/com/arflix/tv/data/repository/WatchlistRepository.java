package com.arflix.tv.data.repository;

import android.content.Context;
import androidx.fragment.app.a2;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.arflix.tv.R;
import com.arflix.tv.data.api.TmdbApi;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.MediaType;
import com.google.android.gms.cast.MediaError;
import com.google.common.util.concurrent.r0;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import ka.x0;
import kotlin.Metadata;
import na.h1;
import na.j1;
import na.q0;
import na.y0;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B+\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u0013\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0086@¢\u0006\u0004\b\u0010\u0010\u0011J \u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0014H\u0086@¢\u0006\u0004\b\u0017\u0010\u0018J,\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\rH\u0086@¢\u0006\u0004\b\u001b\u0010\u001cJ \u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0014H\u0086@¢\u0006\u0004\b\u001d\u0010\u0018J\u0016\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0086@¢\u0006\u0004\b\u001e\u0010\u0011J\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0086@¢\u0006\u0004\b\u001f\u0010\u0011J\u001e\u0010!\u001a\u00020\u001a2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0086@¢\u0006\u0004\b!\u0010\"J\r\u0010#\u001a\u00020\u001a¢\u0006\u0004\b#\u0010$J\u001e\u0010(\u001a\b\u0012\u0004\u0012\u00020'0\f2\u0006\u0010&\u001a\u00020%H\u0086@¢\u0006\u0004\b(\u0010)J&\u0010+\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020%2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020'0\fH\u0086@¢\u0006\u0004\b+\u0010,J\u0015\u0010.\u001a\b\u0012\u0004\u0012\u00020%0-H\u0002¢\u0006\u0004\b.\u0010/J\u001d\u00100\u001a\b\u0012\u0004\u0012\u00020%0-2\u0006\u0010&\u001a\u00020%H\u0002¢\u0006\u0004\b0\u00101J\u001f\u00102\u001a\u00020%2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0014H\u0002¢\u0006\u0004\b2\u00103J\u0010\u00104\u001a\u00020\u001aH\u0082@¢\u0006\u0004\b4\u0010\u0011J\u0016\u00105\u001a\b\u0012\u0004\u0012\u00020'0\fH\u0082@¢\u0006\u0004\b5\u0010\u0011J\u001e\u00107\u001a\u00020\u001a2\f\u00106\u001a\b\u0012\u0004\u0012\u00020'0\fH\u0082@¢\u0006\u0004\b7\u0010\"J$\u00109\u001a\b\u0012\u0004\u0012\u00020'0\f2\f\u00108\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0082@¢\u0006\u0004\b9\u0010\"J\u001f\u0010;\u001a\b\u0012\u0004\u0012\u00020'0\f2\b\u0010:\u001a\u0004\u0018\u00010%H\u0002¢\u0006\u0004\b;\u0010<J\u001a\u0010>\u001a\u0004\u0018\u00010\r2\u0006\u0010=\u001a\u00020'H\u0082@¢\u0006\u0004\b>\u0010?J0\u0010D\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010@\u001a\u00020%2\u0006\u0010B\u001a\u00020A2\u0006\u0010C\u001a\u00020\u0014H\u0082@¢\u0006\u0004\bD\u0010EJ0\u0010F\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010@\u001a\u00020%2\u0006\u0010B\u001a\u00020A2\u0006\u0010C\u001a\u00020\u0014H\u0082@¢\u0006\u0004\bF\u0010EJ\u0017\u0010H\u001a\u00020%2\u0006\u0010G\u001a\u00020\u0014H\u0002¢\u0006\u0004\bH\u0010IJ\u0013\u0010J\u001a\u00020\r*\u00020'H\u0002¢\u0006\u0004\bJ\u0010KJ\u001f\u0010L\u001a\b\u0012\u0004\u0012\u00020\r0\f*\b\u0012\u0004\u0012\u00020\r0\fH\u0002¢\u0006\u0004\bL\u0010MR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010NR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010OR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010PR\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010QR\u0014\u0010S\u001a\u00020R8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bS\u0010TR\u001a\u0010V\u001a\b\u0012\u0004\u0012\u00020%0U8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bV\u0010WR\u001a\u0010Y\u001a\b\u0012\u0004\u0012\u00020\r0X8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bY\u0010ZR \u0010\\\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0[8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\\\u0010]R#\u0010_\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0^8\u0006¢\u0006\f\n\u0004\b_\u0010`\u001a\u0004\b\u001e\u0010aR\u0016\u0010b\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bb\u0010cR\u0014\u0010e\u001a\u00020d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\be\u0010fR\u0014\u0010h\u001a\u00020g8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bh\u0010i¨\u0006j"}, d2 = {"Lcom/arflix/tv/data/repository/WatchlistRepository;", "", "Landroid/content/Context;", "context", "Lcom/arflix/tv/data/repository/ProfileManager;", "profileManager", "Lcom/arflix/tv/data/api/TmdbApi;", "tmdbApi", "Lcom/arflix/tv/data/repository/CloudSyncInvalidationBus;", "invalidationBus", "<init>", "(Landroid/content/Context;Lcom/arflix/tv/data/repository/ProfileManager;Lcom/arflix/tv/data/api/TmdbApi;Lcom/arflix/tv/data/repository/CloudSyncInvalidationBus;)V", "", "Lcom/arflix/tv/data/model/MediaItem;", "getCachedItems", "()Ljava/util/List;", "getLocalWatchlistItems", "(Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/model/MediaType;", "mediaType", "", "tmdbId", "", "isInWatchlist", "(Lcom/arflix/tv/data/model/MediaType;ILd7/d;)Ljava/lang/Object;", "mediaItem", "Lx6/t0;", "addToWatchlist", "(Lcom/arflix/tv/data/model/MediaType;ILcom/arflix/tv/data/model/MediaItem;Ld7/d;)Ljava/lang/Object;", "removeFromWatchlist", "getWatchlistItems", "refreshWatchlistItems", "traktItems", "syncFromTraktOrder", "(Ljava/util/List;Ld7/d;)Ljava/lang/Object;", "clearWatchlistCache", "()V", "", "profileId", "Lcom/arflix/tv/data/repository/LocalWatchlistItem;", "exportWatchlistForProfile", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "cloudItems", "importWatchlistForProfile", "(Ljava/lang/String;Ljava/util/List;Ld7/d;)Ljava/lang/Object;", "Lc1/e;", "watchlistKey", "()Lc1/e;", "watchlistKeyFor", "(Ljava/lang/String;)Lc1/e;", "cacheKey", "(Lcom/arflix/tv/data/model/MediaType;I)Ljava/lang/String;", "loadKeyCacheQuick", "loadWatchlistRaw", "items", "saveWatchlist", "enrichedItems", "persistEnrichedArtwork", "json", "parseWatchlistItems", "(Ljava/lang/String;)Ljava/util/List;", "item", "enrichWatchlistItem", "(Lcom/arflix/tv/data/repository/LocalWatchlistItem;Ld7/d;)Ljava/lang/Object;", "apiKey", "", "addedAt", "sourceOrder", "enrichTvShow", "(ILjava/lang/String;JILd7/d;)Ljava/lang/Object;", "enrichMovie", "runtime", "formatRuntime", "(I)Ljava/lang/String;", "toBasicMediaItem", "(Lcom/arflix/tv/data/repository/LocalWatchlistItem;)Lcom/arflix/tv/data/model/MediaItem;", "toTraktOrder", "(Ljava/util/List;)Ljava/util/List;", "Landroid/content/Context;", "Lcom/arflix/tv/data/repository/ProfileManager;", "Lcom/arflix/tv/data/api/TmdbApi;", "Lcom/arflix/tv/data/repository/CloudSyncInvalidationBus;", "Lcom/google/gson/Gson;", "gson", "Lcom/google/gson/Gson;", "", "keyCache", "Ljava/util/Set;", "", "itemsCache", "Ljava/util/List;", "Lna/q0;", "_watchlistItems", "Lna/q0;", "Lna/h1;", "watchlistItems", "Lna/h1;", "()Lna/h1;", "cacheLoaded", "Z", "Lua/a;", "cacheMutex", "Lua/a;", "Lua/f;", "tmdbSemaphore", "Lua/f;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class WatchlistRepository {
    public static final int $stable = 8;
    private final q0<List<MediaItem>> _watchlistItems;
    private boolean cacheLoaded;
    private final ua.a cacheMutex;
    private final Context context;
    private final CloudSyncInvalidationBus invalidationBus;
    private final ProfileManager profileManager;
    private final TmdbApi tmdbApi;
    private final ua.f tmdbSemaphore;
    private final h1<List<MediaItem>> watchlistItems;
    private final Gson gson = new Gson();
    private final Set<String> keyCache = new LinkedHashSet();
    private final List<MediaItem> itemsCache = new ArrayList();

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.WatchlistRepository$addToWatchlist$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.WatchlistRepository", f = "WatchlistRepository.kt", l = {182, 191, 672}, m = "addToWatchlist", v = 2)
    public static final class AnonymousClass1 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WatchlistRepository.this.addToWatchlist(null, 0, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.WatchlistRepository$enrichMovie$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.WatchlistRepository", f = "WatchlistRepository.kt", l = {618}, m = "enrichMovie", v = 2)
    public static final class C12261 extends f7.c {
        int I$0;
        int I$1;
        long J$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C12261(d7.d<? super C12261> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WatchlistRepository.this.enrichMovie(0, null, 0L, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.WatchlistRepository$enrichTvShow$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.WatchlistRepository", f = "WatchlistRepository.kt", l = {599}, m = "enrichTvShow", v = 2)
    public static final class C12271 extends f7.c {
        int I$0;
        int I$1;
        long J$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C12271(d7.d<? super C12271> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WatchlistRepository.this.enrichTvShow(0, null, 0L, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.WatchlistRepository$enrichWatchlistItem$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.WatchlistRepository", f = "WatchlistRepository.kt", l = {582, 584}, m = "enrichWatchlistItem", v = 2)
    public static final class C12281 extends f7.c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C12281(d7.d<? super C12281> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WatchlistRepository.this.enrichWatchlistItem(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.WatchlistRepository$exportWatchlistForProfile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.WatchlistRepository", f = "WatchlistRepository.kt", l = {377}, m = "exportWatchlistForProfile", v = 2)
    public static final class C12291 extends f7.c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C12291(d7.d<? super C12291> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WatchlistRepository.this.exportWatchlistForProfile(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.WatchlistRepository$getLocalWatchlistItems$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/MediaItem;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.WatchlistRepository$getLocalWatchlistItems$2", f = "WatchlistRepository.kt", l = {113, 676}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends MediaItem>>, Object> {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        public AnonymousClass2(d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return WatchlistRepository.this.new AnonymousClass2(dVar);
        }

        /* JADX WARN: Removed duplicated region for block: B:28:0x00b7 A[Catch: all -> 0x00d1, LOOP:0: B:26:0x00b1->B:28:0x00b7, LOOP_END, TryCatch #0 {all -> 0x00d1, blocks: (B:25:0x0098, B:26:0x00b1, B:28:0x00b7, B:31:0x00d3), top: B:36:0x0098 }] */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                Method dump skipped, instruction units count: 229
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.WatchlistRepository.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super List<MediaItem>> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.WatchlistRepository$getWatchlistItems$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/MediaItem;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.WatchlistRepository$getWatchlistItems$2", f = "WatchlistRepository.kt", l = {239, 672, 686, 263, 273, 707}, m = "invokeSuspend", v = 2)
    public static final class C12302 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends MediaItem>>, Object> {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;

        public C12302(d7.d<? super C12302> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return WatchlistRepository.this.new C12302(dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:22:0x00ce, code lost:
        
            if (r2.c(r58) == r6) goto L72;
         */
        /* JADX WARN: Finally extract failed */
        /* JADX WARN: Removed duplicated region for block: B:21:0x00b7  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x00f5  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0154 A[Catch: all -> 0x016e, LOOP:3: B:38:0x014e->B:40:0x0154, LOOP_END, TryCatch #2 {all -> 0x016e, blocks: (B:37:0x0135, B:38:0x014e, B:40:0x0154, B:43:0x0171), top: B:91:0x0135 }] */
        /* JADX WARN: Removed duplicated region for block: B:47:0x0198 A[PHI: r0
          0x0198: PHI (r0v27 java.lang.Object) = (r0v24 java.lang.Object), (r0v33 java.lang.Object) binds: [B:45:0x0194, B:8:0x004e] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:50:0x01ad A[PHI: r0 r7
          0x01ad: PHI (r0v34 java.util.List) = (r0v28 java.util.List), (r0v40 java.util.List) binds: [B:48:0x01a9, B:7:0x003b] A[DONT_GENERATE, DONT_INLINE]
          0x01ad: PHI (r7v10 java.lang.Object) = (r7v9 java.lang.Object), (r7v19 java.lang.Object) binds: [B:48:0x01a9, B:7:0x003b] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:52:0x01bd  */
        /* JADX WARN: Removed duplicated region for block: B:56:0x01cd A[LOOP:1: B:54:0x01c7->B:56:0x01cd, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:60:0x01f9  */
        /* JADX WARN: Removed duplicated region for block: B:73:0x02af  */
        /* JADX WARN: Removed duplicated region for block: B:77:0x02cf A[Catch: all -> 0x02e9, LOOP:0: B:75:0x02c9->B:77:0x02cf, LOOP_END, TryCatch #1 {all -> 0x02e9, blocks: (B:74:0x02b0, B:75:0x02c9, B:77:0x02cf, B:80:0x02eb), top: B:89:0x02b0 }] */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r59) {
            /*
                Method dump skipped, instruction units count: 788
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.WatchlistRepository.C12302.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super List<MediaItem>> dVar) {
            return ((C12302) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.WatchlistRepository$importWatchlistForProfile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.WatchlistRepository", f = "WatchlistRepository.kt", l = {403, 461}, m = "importWatchlistForProfile", v = 2)
    public static final class C12311 extends f7.c {
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

        public C12311(d7.d<? super C12311> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WatchlistRepository.this.importWatchlistForProfile(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.WatchlistRepository$importWatchlistForProfile$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.WatchlistRepository$importWatchlistForProfile$4", f = "WatchlistRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass4 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
        final /* synthetic */ String $json;
        final /* synthetic */ String $safeProfileId;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(String str, String str2, d7.d<? super AnonymousClass4> dVar) {
            super(2, dVar);
            this.$safeProfileId = str;
            this.$json = str2;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass4 anonymousClass4 = WatchlistRepository.this.new AnonymousClass4(this.$safeProfileId, this.$json, dVar);
            anonymousClass4.L$0 = obj;
            return anonymousClass4;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((AnonymousClass4) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            bVar.d(WatchlistRepository.this.watchlistKeyFor(this.$safeProfileId), this.$json);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.WatchlistRepository$isInWatchlist$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.WatchlistRepository", f = "WatchlistRepository.kt", l = {133}, m = "isInWatchlist", v = 2)
    public static final class C12321 extends f7.c {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C12321(d7.d<? super C12321> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WatchlistRepository.this.isInWatchlist(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.WatchlistRepository$loadKeyCacheQuick$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.WatchlistRepository", f = "WatchlistRepository.kt", l = {143, 672}, m = "loadKeyCacheQuick", v = 2)
    public static final class C12331 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C12331(d7.d<? super C12331> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WatchlistRepository.this.loadKeyCacheQuick(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.WatchlistRepository$loadWatchlistRaw$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.WatchlistRepository", f = "WatchlistRepository.kt", l = {487}, m = "loadWatchlistRaw", v = 2)
    public static final class C12341 extends f7.c {
        int label;
        /* synthetic */ Object result;

        public C12341(d7.d<? super C12341> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WatchlistRepository.this.loadWatchlistRaw(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.WatchlistRepository$persistEnrichedArtwork$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.WatchlistRepository", f = "WatchlistRepository.kt", l = {525, 534}, m = "persistEnrichedArtwork", v = 2)
    public static final class C12351 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C12351(d7.d<? super C12351> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WatchlistRepository.this.persistEnrichedArtwork(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.WatchlistRepository$persistEnrichedArtwork$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.WatchlistRepository$persistEnrichedArtwork$2", f = "WatchlistRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C12362 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
        final /* synthetic */ Map<String, MediaItem> $enrichedByKey;
        final /* synthetic */ c1.e $key;
        final /* synthetic */ kotlin.jvm.internal.k0 $storedItems;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C12362(c1.e eVar, kotlin.jvm.internal.k0 k0Var, Map<String, MediaItem> map, d7.d<? super C12362> dVar) {
            super(2, dVar);
            this.$key = eVar;
            this.$storedItems = k0Var;
            this.$enrichedByKey = map;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C12362 c12362 = WatchlistRepository.this.new C12362(this.$key, this.$storedItems, this.$enrichedByKey, dVar);
            c12362.L$0 = obj;
            return c12362;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((C12362) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            LocalWatchlistItem localWatchlistItemCopy$default;
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            List<LocalWatchlistItem> watchlistItems = WatchlistRepository.this.parseWatchlistItems((String) bVar.f7368a.get(this.$key));
            Map<String, MediaItem> map = this.$enrichedByKey;
            ArrayList arrayList = new ArrayList(kotlin.collections.s.U(watchlistItems, 10));
            for (LocalWatchlistItem localWatchlistItem : watchlistItems) {
                MediaItem mediaItem = map.get(localWatchlistItem.getMediaType() + ":" + localWatchlistItem.getTmdbId());
                if (mediaItem == null) {
                    localWatchlistItemCopy$default = LocalWatchlistItem.copy$default(localWatchlistItem, 0, null, null, WatchlistRepositoryKt.normalizeWatchlistArtworkUrl(localWatchlistItem.getPosterPath(), false), WatchlistRepositoryKt.normalizeWatchlistArtworkUrl(localWatchlistItem.getBackdropPath(), true), 0L, 0, MediaError.DetailedErrorCode.MEDIA_NETWORK, null);
                } else {
                    String title = mediaItem.getTitle();
                    if (kotlin.text.o.h0(title)) {
                        title = localWatchlistItem.getTitle();
                    }
                    String strNormalizeWatchlistArtworkUrl = WatchlistRepositoryKt.normalizeWatchlistArtworkUrl(mediaItem.getImage(), false);
                    if (strNormalizeWatchlistArtworkUrl == null) {
                        strNormalizeWatchlistArtworkUrl = WatchlistRepositoryKt.normalizeWatchlistArtworkUrl(localWatchlistItem.getPosterPath(), false);
                    }
                    String strNormalizeWatchlistArtworkUrl2 = WatchlistRepositoryKt.normalizeWatchlistArtworkUrl(mediaItem.getBackdrop(), true);
                    if (strNormalizeWatchlistArtworkUrl2 == null) {
                        strNormalizeWatchlistArtworkUrl2 = WatchlistRepositoryKt.normalizeWatchlistArtworkUrl(localWatchlistItem.getBackdropPath(), true);
                    }
                    localWatchlistItemCopy$default = LocalWatchlistItem.copy$default(localWatchlistItem, 0, null, title, strNormalizeWatchlistArtworkUrl, strNormalizeWatchlistArtworkUrl2, 0L, 0, 99, null);
                }
                arrayList.add(localWatchlistItemCopy$default);
            }
            if (!arrayList.equals(watchlistItems)) {
                bVar.d(this.$key, WatchlistRepository.this.gson.toJson(arrayList));
            }
            this.$storedItems.f19746i = arrayList;
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.WatchlistRepository$refreshWatchlistItems$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/MediaItem;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.WatchlistRepository$refreshWatchlistItems$2", f = "WatchlistRepository.kt", l = {672, 306}, m = "invokeSuspend", v = 2)
    public static final class C12372 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends MediaItem>>, Object> {
        int I$0;
        Object L$0;
        Object L$1;
        int label;

        public C12372(d7.d<? super C12372> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return WatchlistRepository.this.new C12372(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            WatchlistRepository watchlistRepository;
            ua.a aVar;
            int i10 = this.label;
            e7.a aVar2 = e7.a.f15033i;
            try {
                if (i10 == 0) {
                    k2.c.G(obj);
                    ua.a aVar3 = WatchlistRepository.this.cacheMutex;
                    watchlistRepository = WatchlistRepository.this;
                    this.L$0 = aVar3;
                    this.L$1 = watchlistRepository;
                    this.I$0 = 0;
                    this.label = 1;
                    if (aVar3.c(this) != aVar2) {
                        aVar = aVar3;
                    }
                }
                if (i10 != 1) {
                    if (i10 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    return obj;
                }
                watchlistRepository = (WatchlistRepository) this.L$1;
                aVar = (ua.a) this.L$0;
                k2.c.G(obj);
                watchlistRepository.itemsCache.clear();
                aVar.b(null);
                WatchlistRepository watchlistRepository2 = WatchlistRepository.this;
                this.L$0 = null;
                this.L$1 = null;
                this.label = 2;
                Object watchlistItems = watchlistRepository2.getWatchlistItems(this);
                return watchlistItems == aVar2 ? aVar2 : watchlistItems;
            } catch (Throwable th) {
                aVar.b(null);
                throw th;
            }
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super List<MediaItem>> dVar) {
            return ((C12372) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.WatchlistRepository$removeFromWatchlist$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.WatchlistRepository", f = "WatchlistRepository.kt", l = {213, 219, 672}, m = "removeFromWatchlist", v = 2)
    public static final class C12381 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public C12381(d7.d<? super C12381> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WatchlistRepository.this.removeFromWatchlist(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.WatchlistRepository$saveWatchlist$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.WatchlistRepository", f = "WatchlistRepository.kt", l = {514}, m = "saveWatchlist", v = 2)
    public static final class C12391 extends f7.c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C12391(d7.d<? super C12391> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WatchlistRepository.this.saveWatchlist(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.WatchlistRepository$saveWatchlist$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.WatchlistRepository$saveWatchlist$2", f = "WatchlistRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C12402 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
        final /* synthetic */ String $json;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C12402(String str, d7.d<? super C12402> dVar) {
            super(2, dVar);
            this.$json = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C12402 c12402 = WatchlistRepository.this.new C12402(this.$json, dVar);
            c12402.L$0 = obj;
            return c12402;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((C12402) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            bVar.d(WatchlistRepository.this.watchlistKey(), this.$json);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.WatchlistRepository$syncFromTraktOrder$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.WatchlistRepository$syncFromTraktOrder$2", f = "WatchlistRepository.kt", l = {MediaError.DetailedErrorCode.HLS_SEGMENT_PARSING, 349, 679}, m = "invokeSuspend", v = 2)
    public static final class C12412 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        final /* synthetic */ List<MediaItem> $traktItems;
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C12412(List<MediaItem> list, d7.d<? super C12412> dVar) {
            super(2, dVar);
            this.$traktItems = list;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return WatchlistRepository.this.new C12412(this.$traktItems, dVar);
        }

        /* JADX WARN: Removed duplicated region for block: B:59:0x01d5  */
        /* JADX WARN: Removed duplicated region for block: B:63:0x01f0 A[Catch: all -> 0x0203, TryCatch #0 {all -> 0x0203, blocks: (B:60:0x01d8, B:61:0x01ea, B:63:0x01f0, B:65:0x0200, B:69:0x0208, B:68:0x0206, B:70:0x0218, B:71:0x022b, B:73:0x0231, B:74:0x023f), top: B:79:0x01d8 }] */
        /* JADX WARN: Removed duplicated region for block: B:73:0x0231 A[Catch: all -> 0x0203, LOOP:1: B:71:0x022b->B:73:0x0231, LOOP_END, TryCatch #0 {all -> 0x0203, blocks: (B:60:0x01d8, B:61:0x01ea, B:63:0x01f0, B:65:0x0200, B:69:0x0208, B:68:0x0206, B:70:0x0218, B:71:0x022b, B:73:0x0231, B:74:0x023f), top: B:79:0x01d8 }] */
        /* JADX WARN: Removed duplicated region for block: B:89:? A[RETURN, SYNTHETIC] */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r28) {
            /*
                Method dump skipped, instruction units count: 592
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.WatchlistRepository.C12412.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C12412) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    @Inject
    public WatchlistRepository(@ApplicationContext Context context, ProfileManager profileManager, TmdbApi tmdbApi, CloudSyncInvalidationBus cloudSyncInvalidationBus) {
        this.context = context;
        this.profileManager = profileManager;
        this.tmdbApi = tmdbApi;
        this.invalidationBus = cloudSyncInvalidationBus;
        j1 j1VarB = y0.b(kotlin.collections.z.f19728i);
        this._watchlistItems = j1VarB;
        this.watchlistItems = y0.e(j1VarB);
        this.cacheMutex = ua.e.a();
        this.tmdbSemaphore = ua.j.a(5);
    }

    public static /* synthetic */ Object addToWatchlist$default(WatchlistRepository watchlistRepository, MediaType mediaType, int i10, MediaItem mediaItem, d7.d dVar, int i11, Object obj) {
        if ((i11 & 4) != 0) {
            mediaItem = null;
        }
        return watchlistRepository.addToWatchlist(mediaType, i10, mediaItem, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean addToWatchlist$lambda$0(int i10, LocalWatchlistItem localWatchlistItem, LocalWatchlistItem localWatchlistItem2) {
        return localWatchlistItem2.getTmdbId() == i10 && kotlin.jvm.internal.p.a(localWatchlistItem2.getMediaType(), localWatchlistItem.getMediaType());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean addToWatchlist$lambda$1$0(int i10, MediaType mediaType, MediaItem mediaItem) {
        return mediaItem.getId() == i10 && mediaItem.getMediaType() == mediaType;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String cacheKey(MediaType mediaType, int tmdbId) {
        return a2.j(tmdbId, mediaType.name().toLowerCase(Locale.ROOT), ":");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object enrichMovie(int r60, java.lang.String r61, long r62, int r64, d7.d<? super com.arflix.tv.data.model.MediaItem> r65) {
        /*
            Method dump skipped, instruction units count: 317
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.WatchlistRepository.enrichMovie(int, java.lang.String, long, int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object enrichTvShow(int r60, java.lang.String r61, long r62, int r64, d7.d<? super com.arflix.tv.data.model.MediaItem> r65) {
        /*
            Method dump skipped, instruction units count: 327
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.WatchlistRepository.enrichTvShow(int, java.lang.String, long, int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00a1, code lost:
    
        if (r11 == r8) goto L39;
     */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object enrichWatchlistItem(com.arflix.tv.data.repository.LocalWatchlistItem r10, d7.d<? super com.arflix.tv.data.model.MediaItem> r11) {
        /*
            Method dump skipped, instruction units count: 215
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.WatchlistRepository.enrichWatchlistItem(com.arflix.tv.data.repository.LocalWatchlistItem, d7.d):java.lang.Object");
    }

    private final String formatRuntime(int runtime) {
        int i10 = runtime / 60;
        int i11 = runtime % 60;
        if (i10 <= 0) {
            return a2.i(i11, CmcdData.OBJECT_TYPE_MANIFEST);
        }
        return i10 + "h " + i11 + CmcdData.OBJECT_TYPE_MANIFEST;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0075 A[Catch: all -> 0x008b, TryCatch #0 {all -> 0x008b, blocks: (B:32:0x0066, B:33:0x006f, B:35:0x0075, B:37:0x0088, B:41:0x008f, B:40:0x008d, B:42:0x009d), top: B:50:0x0066, outer: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadKeyCacheQuick(d7.d<? super x6.t0> r9) {
        /*
            Method dump skipped, instruction units count: 205
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.WatchlistRepository.loadKeyCacheQuick(d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadWatchlistRaw(d7.d<? super java.util.List<com.arflix.tv.data.repository.LocalWatchlistItem>> r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof com.arflix.tv.data.repository.WatchlistRepository.C12341
            if (r0 == 0) goto L13
            r0 = r9
            com.arflix.tv.data.repository.WatchlistRepository$loadWatchlistRaw$1 r0 = (com.arflix.tv.data.repository.WatchlistRepository.C12341) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.WatchlistRepository$loadWatchlistRaw$1 r0 = new com.arflix.tv.data.repository.WatchlistRepository$loadWatchlistRaw$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 0
            r3 = 1
            kotlin.collections.z r4 = kotlin.collections.z.f19728i
            if (r1 == 0) goto L35
            if (r1 != r3) goto L2d
            k2.c.G(r9)     // Catch: java.lang.Exception -> L28 java.util.concurrent.CancellationException -> L2a
            goto L4d
        L28:
            r9 = move-exception
            goto L8a
        L2a:
            r9 = move-exception
            goto Lae
        L2d:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L35:
            k2.c.G(r9)
            android.content.Context r9 = r8.context     // Catch: java.lang.Exception -> L28 java.util.concurrent.CancellationException -> L2a
            z0.g r9 = com.arflix.tv.util.DataStoresKt.getTraktDataStore(r9)     // Catch: java.lang.Exception -> L28 java.util.concurrent.CancellationException -> L2a
            na.j r9 = r9.getData()     // Catch: java.lang.Exception -> L28 java.util.concurrent.CancellationException -> L2a
            r0.label = r3     // Catch: java.lang.Exception -> L28 java.util.concurrent.CancellationException -> L2a
            java.lang.Object r9 = na.y0.l(r9, r0)     // Catch: java.lang.Exception -> L28 java.util.concurrent.CancellationException -> L2a
            e7.a r0 = e7.a.f15033i
            if (r9 != r0) goto L4d
            return r0
        L4d:
            c1.g r9 = (c1.g) r9     // Catch: java.lang.Exception -> L28 java.util.concurrent.CancellationException -> L2a
            c1.e r0 = r8.watchlistKey()     // Catch: java.lang.Exception -> L28 java.util.concurrent.CancellationException -> L2a
            c1.b r9 = (c1.b) r9     // Catch: java.lang.Exception -> L28 java.util.concurrent.CancellationException -> L2a
            java.util.LinkedHashMap r9 = r9.f7368a     // Catch: java.lang.Exception -> L28 java.util.concurrent.CancellationException -> L2a
            java.lang.Object r9 = r9.get(r0)     // Catch: java.lang.Exception -> L28 java.util.concurrent.CancellationException -> L2a
            java.lang.String r9 = (java.lang.String) r9     // Catch: java.lang.Exception -> L28 java.util.concurrent.CancellationException -> L2a
            if (r9 != 0) goto L60
            return r4
        L60:
            java.lang.Class<java.util.List> r0 = java.util.List.class
            java.lang.reflect.Type[] r1 = new java.lang.reflect.Type[r3]     // Catch: java.lang.Exception -> L28 java.util.concurrent.CancellationException -> L2a
            java.lang.Class<com.arflix.tv.data.repository.LocalWatchlistItem> r5 = com.arflix.tv.data.repository.LocalWatchlistItem.class
            r1[r2] = r5     // Catch: java.lang.Exception -> L28 java.util.concurrent.CancellationException -> L2a
            com.google.gson.reflect.TypeToken r0 = com.google.gson.reflect.TypeToken.getParameterized(r0, r1)     // Catch: java.lang.Exception -> L28 java.util.concurrent.CancellationException -> L2a
            java.lang.reflect.Type r0 = r0.getType()     // Catch: java.lang.Exception -> L28 java.util.concurrent.CancellationException -> L2a
            com.google.gson.Gson r1 = r8.gson     // Catch: java.lang.Exception -> L28 java.util.concurrent.CancellationException -> L2a
            java.lang.Object r9 = r1.fromJson(r9, r0)     // Catch: java.lang.Exception -> L28 java.util.concurrent.CancellationException -> L2a
            java.util.List r9 = (java.util.List) r9     // Catch: java.lang.Exception -> L28 java.util.concurrent.CancellationException -> L2a
            if (r9 != 0) goto L7b
            r9 = r4
        L7b:
            com.arflix.tv.data.repository.WatchlistRepository$loadWatchlistRaw$$inlined$compareBy$1 r0 = new com.arflix.tv.data.repository.WatchlistRepository$loadWatchlistRaw$$inlined$compareBy$1     // Catch: java.lang.Exception -> L28 java.util.concurrent.CancellationException -> L2a
            r0.<init>()     // Catch: java.lang.Exception -> L28 java.util.concurrent.CancellationException -> L2a
            com.arflix.tv.data.repository.WatchlistRepository$loadWatchlistRaw$$inlined$thenByDescending$1 r1 = new com.arflix.tv.data.repository.WatchlistRepository$loadWatchlistRaw$$inlined$thenByDescending$1     // Catch: java.lang.Exception -> L28 java.util.concurrent.CancellationException -> L2a
            r1.<init>()     // Catch: java.lang.Exception -> L28 java.util.concurrent.CancellationException -> L2a
            java.util.List r9 = kotlin.collections.x.W0(r9, r1)     // Catch: java.lang.Exception -> L28 java.util.concurrent.CancellationException -> L2a
            return r9
        L8a:
            com.arflix.tv.util.AppLogger r0 = com.arflix.tv.util.AppLogger.INSTANCE
            x6.x r1 = new x6.x
            java.lang.String r5 = "error_area"
            java.lang.String r6 = "WatchlistRepository"
            r1.<init>(r5, r6)
            x6.x r5 = new x6.x
            java.lang.String r6 = "watchlist_phase"
            java.lang.String r7 = "load_raw"
            r5.<init>(r6, r7)
            r6 = 2
            x6.x[] r6 = new x6.x[r6]
            r6[r2] = r1
            r6[r3] = r5
            java.util.Map r1 = kotlin.collections.h0.t0(r6)
            r0.recordException(r9, r1)
            return r4
        Lae:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.WatchlistRepository.loadWatchlistRaw(d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<LocalWatchlistItem> parseWatchlistItems(String json) {
        Object c0Var;
        kotlin.collections.z zVar = kotlin.collections.z.f19728i;
        if (json == null || kotlin.text.o.h0(json)) {
            return zVar;
        }
        try {
            List list = (List) this.gson.fromJson(json, TypeToken.getParameterized(List.class, LocalWatchlistItem.class).getType());
            c0Var = list;
            if (list == null) {
                c0Var = zVar;
            }
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        Object obj = zVar;
        if (!(c0Var instanceof x6.c0)) {
            obj = c0Var;
        }
        return (List) obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object persistEnrichedArtwork(java.util.List<com.arflix.tv.data.model.MediaItem> r12, d7.d<? super java.util.List<com.arflix.tv.data.repository.LocalWatchlistItem>> r13) {
        /*
            Method dump skipped, instruction units count: 240
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.WatchlistRepository.persistEnrichedArtwork(java.util.List, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean removeFromWatchlist$lambda$0(int i10, String str, LocalWatchlistItem localWatchlistItem) {
        return localWatchlistItem.getTmdbId() == i10 && kotlin.jvm.internal.p.a(localWatchlistItem.getMediaType(), str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean removeFromWatchlist$lambda$1$0(int i10, MediaType mediaType, MediaItem mediaItem) {
        return mediaItem.getId() == i10 && mediaItem.getMediaType() == mediaType;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object saveWatchlist(java.util.List<com.arflix.tv.data.repository.LocalWatchlistItem> r5, d7.d<? super x6.t0> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.arflix.tv.data.repository.WatchlistRepository.C12391
            if (r0 == 0) goto L13
            r0 = r6
            com.arflix.tv.data.repository.WatchlistRepository$saveWatchlist$1 r0 = (com.arflix.tv.data.repository.WatchlistRepository.C12391) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.WatchlistRepository$saveWatchlist$1 r0 = new com.arflix.tv.data.repository.WatchlistRepository$saveWatchlist$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L35
            if (r1 != r2) goto L2d
            java.lang.Object r5 = r0.L$1
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r5 = r0.L$0
            java.util.List r5 = (java.util.List) r5
            k2.c.G(r6)
            goto L59
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L35:
            k2.c.G(r6)
            com.google.gson.Gson r6 = r4.gson
            java.lang.String r5 = r6.toJson(r5)
            android.content.Context r6 = r4.context
            z0.g r6 = com.arflix.tv.util.DataStoresKt.getTraktDataStore(r6)
            com.arflix.tv.data.repository.WatchlistRepository$saveWatchlist$2 r1 = new com.arflix.tv.data.repository.WatchlistRepository$saveWatchlist$2
            r3 = 0
            r1.<init>(r5, r3)
            r0.L$0 = r3
            r0.L$1 = r3
            r0.label = r2
            java.lang.Object r5 = androidx.work.impl.t.o(r6, r1, r0)
            e7.a r6 = e7.a.f15033i
            if (r5 != r6) goto L59
            return r6
        L59:
            com.arflix.tv.data.repository.CloudSyncInvalidationBus r5 = r4.invalidationBus
            com.arflix.tv.data.repository.CloudSyncScope r6 = com.arflix.tv.data.repository.CloudSyncScope.WATCHLIST
            com.arflix.tv.data.repository.ProfileManager r0 = r4.profileManager
            java.lang.String r0 = r0.getProfileIdSync()
            java.lang.String r1 = "save watchlist"
            r5.markDirty(r6, r0, r1)
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.WatchlistRepository.saveWatchlist(java.util.List, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MediaItem toBasicMediaItem(LocalWatchlistItem localWatchlistItem) {
        Context context;
        int i10;
        MediaType mediaType = kotlin.jvm.internal.p.a(localWatchlistItem.getMediaType(), "tv") ? MediaType.TV : MediaType.MOVIE;
        int tmdbId = localWatchlistItem.getTmdbId();
        String title = localWatchlistItem.getTitle();
        if (mediaType == MediaType.TV) {
            context = this.context;
            i10 = R.string.component_label_tv_series;
        } else {
            context = this.context;
            i10 = R.string.movie;
        }
        String string = context.getString(i10);
        String strNormalizeWatchlistArtworkUrl = WatchlistRepositoryKt.normalizeWatchlistArtworkUrl(localWatchlistItem.getPosterPath(), false);
        if (strNormalizeWatchlistArtworkUrl == null) {
            strNormalizeWatchlistArtworkUrl = "";
        }
        return new MediaItem(tmdbId, title, string, "", "", null, null, null, null, null, mediaType, strNormalizeWatchlistArtworkUrl, WatchlistRepositoryKt.normalizeWatchlistArtworkUrl(localWatchlistItem.getBackdropPath(), true), 0, false, null, null, null, null, null, false, null, null, null, null, null, null, null, null, false, null, 0.0f, localWatchlistItem.getAddedAt(), localWatchlistItem.getSourceOrder(), false, null, false, false, null, null, null, null, -7200, AnalyticsListener.EVENT_VIDEO_DISABLED, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<MediaItem> toTraktOrder(List<MediaItem> list) {
        final Comparator comparator = new Comparator() { // from class: com.arflix.tv.data.repository.WatchlistRepository$toTraktOrder$$inlined$compareBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                return r0.e(Integer.valueOf(((MediaItem) t2).getSourceOrder()), Integer.valueOf(((MediaItem) t10).getSourceOrder()));
            }
        };
        return kotlin.collections.x.W0(list, new Comparator() { // from class: com.arflix.tv.data.repository.WatchlistRepository$toTraktOrder$$inlined$thenByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                int iCompare = comparator.compare(t2, t10);
                return iCompare != 0 ? iCompare : r0.e(Long.valueOf(((MediaItem) t10).getAddedAt()), Long.valueOf(((MediaItem) t2).getAddedAt()));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e watchlistKey() {
        return this.profileManager.profileStringKey("local_watchlist_v1");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e watchlistKeyFor(String profileId) {
        return this.profileManager.profileStringKeyFor(profileId, "local_watchlist_v1");
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x014b A[Catch: all -> 0x015c, TryCatch #0 {all -> 0x015c, blocks: (B:46:0x0139, B:48:0x014b, B:51:0x015e), top: B:56:0x0139 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object addToWatchlist(com.arflix.tv.data.model.MediaType r25, int r26, com.arflix.tv.data.model.MediaItem r27, d7.d<? super x6.t0> r28) {
        /*
            Method dump skipped, instruction units count: 362
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.WatchlistRepository.addToWatchlist(com.arflix.tv.data.model.MediaType, int, com.arflix.tv.data.model.MediaItem, d7.d):java.lang.Object");
    }

    public final void clearWatchlistCache() {
        this.keyCache.clear();
        this.itemsCache.clear();
        this._watchlistItems.setValue(kotlin.collections.z.f19728i);
        this.cacheLoaded = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object exportWatchlistForProfile(java.lang.String r8, d7.d<? super java.util.List<com.arflix.tv.data.repository.LocalWatchlistItem>> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof com.arflix.tv.data.repository.WatchlistRepository.C12291
            if (r0 == 0) goto L13
            r0 = r9
            com.arflix.tv.data.repository.WatchlistRepository$exportWatchlistForProfile$1 r0 = (com.arflix.tv.data.repository.WatchlistRepository.C12291) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.WatchlistRepository$exportWatchlistForProfile$1 r0 = new com.arflix.tv.data.repository.WatchlistRepository$exportWatchlistForProfile$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 0
            r3 = 1
            kotlin.collections.z r4 = kotlin.collections.z.f19728i
            if (r1 == 0) goto L3d
            if (r1 != r3) goto L35
            java.lang.Object r8 = r0.L$1
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r0 = r0.L$0
            java.lang.String r0 = (java.lang.String) r0
            k2.c.G(r9)     // Catch: java.lang.Exception -> L30 java.util.concurrent.CancellationException -> L32
            goto L63
        L30:
            r8 = move-exception
            goto L92
        L32:
            r8 = move-exception
            goto Lb6
        L35:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L3d:
            java.lang.String r8 = androidx.fragment.app.a2.l(r9, r8)
            boolean r9 = kotlin.text.o.h0(r8)
            if (r9 == 0) goto L49
            java.lang.String r8 = "default"
        L49:
            android.content.Context r9 = r7.context     // Catch: java.lang.Exception -> L30 java.util.concurrent.CancellationException -> L32
            z0.g r9 = com.arflix.tv.util.DataStoresKt.getTraktDataStore(r9)     // Catch: java.lang.Exception -> L30 java.util.concurrent.CancellationException -> L32
            na.j r9 = r9.getData()     // Catch: java.lang.Exception -> L30 java.util.concurrent.CancellationException -> L32
            r1 = 0
            r0.L$0 = r1     // Catch: java.lang.Exception -> L30 java.util.concurrent.CancellationException -> L32
            r0.L$1 = r8     // Catch: java.lang.Exception -> L30 java.util.concurrent.CancellationException -> L32
            r0.label = r3     // Catch: java.lang.Exception -> L30 java.util.concurrent.CancellationException -> L32
            java.lang.Object r9 = na.y0.l(r9, r0)     // Catch: java.lang.Exception -> L30 java.util.concurrent.CancellationException -> L32
            e7.a r0 = e7.a.f15033i
            if (r9 != r0) goto L63
            return r0
        L63:
            c1.g r9 = (c1.g) r9     // Catch: java.lang.Exception -> L30 java.util.concurrent.CancellationException -> L32
            c1.e r8 = r7.watchlistKeyFor(r8)     // Catch: java.lang.Exception -> L30 java.util.concurrent.CancellationException -> L32
            c1.b r9 = (c1.b) r9     // Catch: java.lang.Exception -> L30 java.util.concurrent.CancellationException -> L32
            java.util.LinkedHashMap r9 = r9.f7368a     // Catch: java.lang.Exception -> L30 java.util.concurrent.CancellationException -> L32
            java.lang.Object r8 = r9.get(r8)     // Catch: java.lang.Exception -> L30 java.util.concurrent.CancellationException -> L32
            java.lang.String r8 = (java.lang.String) r8     // Catch: java.lang.Exception -> L30 java.util.concurrent.CancellationException -> L32
            if (r8 != 0) goto L76
            goto L90
        L76:
            java.lang.Class<java.util.List> r9 = java.util.List.class
            java.lang.reflect.Type[] r0 = new java.lang.reflect.Type[r3]     // Catch: java.lang.Exception -> L30 java.util.concurrent.CancellationException -> L32
            java.lang.Class<com.arflix.tv.data.repository.LocalWatchlistItem> r1 = com.arflix.tv.data.repository.LocalWatchlistItem.class
            r0[r2] = r1     // Catch: java.lang.Exception -> L30 java.util.concurrent.CancellationException -> L32
            com.google.gson.reflect.TypeToken r9 = com.google.gson.reflect.TypeToken.getParameterized(r9, r0)     // Catch: java.lang.Exception -> L30 java.util.concurrent.CancellationException -> L32
            java.lang.reflect.Type r9 = r9.getType()     // Catch: java.lang.Exception -> L30 java.util.concurrent.CancellationException -> L32
            com.google.gson.Gson r0 = r7.gson     // Catch: java.lang.Exception -> L30 java.util.concurrent.CancellationException -> L32
            java.lang.Object r8 = r0.fromJson(r8, r9)     // Catch: java.lang.Exception -> L30 java.util.concurrent.CancellationException -> L32
            java.util.List r8 = (java.util.List) r8     // Catch: java.lang.Exception -> L30 java.util.concurrent.CancellationException -> L32
            if (r8 != 0) goto L91
        L90:
            return r4
        L91:
            return r8
        L92:
            com.arflix.tv.util.AppLogger r9 = com.arflix.tv.util.AppLogger.INSTANCE
            x6.x r0 = new x6.x
            java.lang.String r1 = "error_area"
            java.lang.String r5 = "WatchlistRepository"
            r0.<init>(r1, r5)
            x6.x r1 = new x6.x
            java.lang.String r5 = "watchlist_phase"
            java.lang.String r6 = "export_profile"
            r1.<init>(r5, r6)
            r5 = 2
            x6.x[] r5 = new x6.x[r5]
            r5[r2] = r0
            r5[r3] = r1
            java.util.Map r0 = kotlin.collections.h0.t0(r5)
            r9.recordException(r8, r0)
            return r4
        Lb6:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.WatchlistRepository.exportWatchlistForProfile(java.lang.String, d7.d):java.lang.Object");
    }

    public final List<MediaItem> getCachedItems() {
        return kotlin.collections.x.c1(this.itemsCache);
    }

    public final Object getLocalWatchlistItems(d7.d<? super List<MediaItem>> dVar) {
        return ka.m0.y(x0.f19655d, new AnonymousClass2(null), dVar);
    }

    public final Object getWatchlistItems(d7.d<? super List<MediaItem>> dVar) {
        return ka.m0.y(x0.f19655d, new C12302(null), dVar);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(5:(1:(1:(7:12|79|13|14|60|(1:62)|66)(2:19|20))(2:21|22))(5:28|(1:30)|31|32|(2:34|59)(1:35))|86|56|83|57) */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x019d, code lost:
    
        if (androidx.work.impl.t.o(r4, r5, r2) == r14) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x01b9, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Type inference failed for: r0v30, types: [com.arflix.tv.data.repository.CloudSyncInvalidationBus] */
    /* JADX WARN: Type inference failed for: r22v0, types: [com.arflix.tv.data.repository.WatchlistRepository] */
    /* JADX WARN: Type inference failed for: r25v0, types: [d7.d, d7.d<? super x6.t0>] */
    /* JADX WARN: Type inference failed for: r25v1 */
    /* JADX WARN: Type inference failed for: r25v10 */
    /* JADX WARN: Type inference failed for: r25v2 */
    /* JADX WARN: Type inference failed for: r25v3 */
    /* JADX WARN: Type inference failed for: r25v4 */
    /* JADX WARN: Type inference failed for: r25v5 */
    /* JADX WARN: Type inference failed for: r25v6 */
    /* JADX WARN: Type inference failed for: r25v7 */
    /* JADX WARN: Type inference failed for: r25v8 */
    /* JADX WARN: Type inference failed for: r25v9 */
    /* JADX WARN: Type inference failed for: r3v0, types: [int] */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v12, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v17, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v33, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v36 */
    /* JADX WARN: Type inference failed for: r3v37 */
    /* JADX WARN: Type inference failed for: r3v38 */
    /* JADX WARN: Type inference failed for: r3v39 */
    /* JADX WARN: Type inference failed for: r3v8, types: [java.lang.Object, java.lang.String] */
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
    public final java.lang.Object importWatchlistForProfile(java.lang.String r23, java.util.List<com.arflix.tv.data.repository.LocalWatchlistItem> r24, d7.d<? super x6.t0> r25) {
        /*
            Method dump skipped, instruction units count: 563
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.WatchlistRepository.importWatchlistForProfile(java.lang.String, java.util.List, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object isInWatchlist(com.arflix.tv.data.model.MediaType r5, int r6, d7.d<? super java.lang.Boolean> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof com.arflix.tv.data.repository.WatchlistRepository.C12321
            if (r0 == 0) goto L13
            r0 = r7
            com.arflix.tv.data.repository.WatchlistRepository$isInWatchlist$1 r0 = (com.arflix.tv.data.repository.WatchlistRepository.C12321) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.WatchlistRepository$isInWatchlist$1 r0 = new com.arflix.tv.data.repository.WatchlistRepository$isInWatchlist$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L33
            if (r1 != r2) goto L2b
            int r6 = r0.I$0
            java.lang.Object r5 = r0.L$0
            com.arflix.tv.data.model.MediaType r5 = (com.arflix.tv.data.model.MediaType) r5
            k2.c.G(r7)
            goto L49
        L2b:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L33:
            k2.c.G(r7)
            boolean r7 = r4.cacheLoaded
            if (r7 != 0) goto L49
            r0.L$0 = r5
            r0.I$0 = r6
            r0.label = r2
            java.lang.Object r7 = r4.loadKeyCacheQuick(r0)
            e7.a r0 = e7.a.f15033i
            if (r7 != r0) goto L49
            return r0
        L49:
            java.util.Set<java.lang.String> r7 = r4.keyCache
            java.lang.String r5 = r4.cacheKey(r5, r6)
            boolean r5 = r7.contains(r5)
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.WatchlistRepository.isInWatchlist(com.arflix.tv.data.model.MediaType, int, d7.d):java.lang.Object");
    }

    public final Object refreshWatchlistItems(d7.d<? super List<MediaItem>> dVar) {
        return ka.m0.y(x0.f19655d, new C12372(null), dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object removeFromWatchlist(com.arflix.tv.data.model.MediaType r11, int r12, d7.d<? super x6.t0> r13) {
        /*
            Method dump skipped, instruction units count: 260
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.WatchlistRepository.removeFromWatchlist(com.arflix.tv.data.model.MediaType, int, d7.d):java.lang.Object");
    }

    public final Object syncFromTraktOrder(List<MediaItem> list, d7.d<? super t0> dVar) throws Throwable {
        Object objY = ka.m0.y(x0.f19655d, new C12412(list, null), dVar);
        return objY == e7.a.f15033i ? objY : t0.f22605a;
    }

    public final h1<List<MediaItem>> getWatchlistItems() {
        return this.watchlistItems;
    }
}
