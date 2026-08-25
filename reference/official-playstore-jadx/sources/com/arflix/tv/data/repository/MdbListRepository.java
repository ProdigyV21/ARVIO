package com.arflix.tv.data.repository;

import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.extractor.ts.PsExtractor;
import androidx.media3.extractor.ts.TsExtractor;
import com.arflix.tv.data.api.MdbEpisodeInfo;
import com.arflix.tv.data.api.MdbIds;
import com.arflix.tv.data.api.MdbIdsItem;
import com.arflix.tv.data.api.MdbListApi;
import com.arflix.tv.data.api.MdbMovieInfo;
import com.arflix.tv.data.api.MdbPlaybackItem;
import com.arflix.tv.data.api.MdbShowInfo;
import com.arflix.tv.data.api.MdbWatchedBody;
import com.arflix.tv.data.api.MdbWatchedEpisodeRef;
import com.arflix.tv.data.api.MdbWatchedSeasonRef;
import com.arflix.tv.data.api.MdbWatchedShowRef;
import com.arflix.tv.data.api.MdbWatchlistItem;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.repository.sync.RemoteWatchlistResult;
import com.arflix.tv.data.repository.sync.SyncProviderStore;
import com.arflix.tv.ui.theme.AnimationConstants;
import com.google.android.gms.cast.MediaError;
import io.ktor.http.LinkHeader;
import j$.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import javax.inject.Inject;
import javax.inject.Singleton;
import ka.x0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0086@¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\nH\u0086@¢\u0006\u0004\b\r\u0010\u000eJ\u0012\u0010\u000f\u001a\u0004\u0018\u00010\bH\u0086@¢\u0006\u0004\b\u000f\u0010\u000eJ \u0010\u0014\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0086@¢\u0006\u0004\b\u0014\u0010\u0015J \u0010\u0016\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0086@¢\u0006\u0004\b\u0016\u0010\u0015J\u0010\u0010\u0018\u001a\u00020\u0017H\u0086@¢\u0006\u0004\b\u0018\u0010\u000eJD\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u001a2\b\u0010\u001c\u001a\u0004\u0018\u00010\u00122\b\u0010\u001d\u001a\u0004\u0018\u00010\u0012H\u0086@¢\u0006\u0004\b\u001f\u0010 J4\u0010!\u001a\u00020\u001e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\b\u0010\u001c\u001a\u0004\u0018\u00010\u00122\b\u0010\u001d\u001a\u0004\u0018\u00010\u0012H\u0086@¢\u0006\u0004\b!\u0010\"J\u0018\u0010#\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0012H\u0086@¢\u0006\u0004\b#\u0010$J\u0018\u0010%\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0012H\u0086@¢\u0006\u0004\b%\u0010$J(\u0010'\u001a\u00020\n2\u0006\u0010&\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u0012H\u0086@¢\u0006\u0004\b'\u0010(J(\u0010)\u001a\u00020\n2\u0006\u0010&\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u0012H\u0086@¢\u0006\u0004\b)\u0010(J.\u0010,\u001a\u00020\n2\u0006\u0010&\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u00122\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00120*H\u0086@¢\u0006\u0004\b,\u0010-J\u0016\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00120.H\u0086@¢\u0006\u0004\b/\u0010\u000eJ\u0016\u00101\u001a\b\u0012\u0004\u0012\u0002000*H\u0086@¢\u0006\u0004\b1\u0010\u000eJ\u0016\u00102\u001a\b\u0012\u0004\u0012\u00020\b0.H\u0086@¢\u0006\u0004\b2\u0010\u000eJ\u0016\u00104\u001a\b\u0012\u0004\u0012\u0002030*H\u0086@¢\u0006\u0004\b4\u0010\u000eJ\u0012\u00105\u001a\u0004\u0018\u00010\bH\u0082@¢\u0006\u0004\b5\u0010\u000eJ(\u00106\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\bH\u0082@¢\u0006\u0004\b6\u00107J\u001e\u00109\u001a\b\u0012\u0004\u0012\u0002080*2\u0006\u0010\t\u001a\u00020\bH\u0082@¢\u0006\u0004\b9\u0010\fJ!\u0010=\u001a\u0004\u0018\u00010<2\u0006\u0010:\u001a\u0002082\u0006\u0010;\u001a\u00020\u0012H\u0002¢\u0006\u0004\b=\u0010>J'\u0010@\u001a\u00020?2\u0006\u0010&\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u0012H\u0002¢\u0006\u0004\b@\u0010AJ4\u0010E\u001a\u00020\n2\"\u0010D\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010C\u0012\u0006\u0012\u0004\u0018\u00010\u00010BH\u0082@¢\u0006\u0004\bE\u0010FJ\u0019\u0010H\u001a\u0004\u0018\u0001032\u0006\u0010:\u001a\u00020GH\u0002¢\u0006\u0004\bH\u0010IJ\u0019\u0010L\u001a\u00020K2\b\u0010J\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0004\bL\u0010MR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010NR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010OR\u0014\u0010P\u001a\u00020\b8\u0002X\u0082D¢\u0006\u0006\n\u0004\bP\u0010Q¨\u0006R"}, d2 = {"Lcom/arflix/tv/data/repository/MdbListRepository;", "", "Lcom/arflix/tv/data/api/MdbListApi;", "api", "Lcom/arflix/tv/data/repository/sync/SyncProviderStore;", "store", "<init>", "(Lcom/arflix/tv/data/api/MdbListApi;Lcom/arflix/tv/data/repository/sync/SyncProviderStore;)V", "", "apiKey", "", "validateKey", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "isConnected", "(Ld7/d;)Ljava/lang/Object;", "fetchUsername", "Lcom/arflix/tv/data/model/MediaType;", "mediaType", "", "tmdbId", "addToWatchlist", "(Lcom/arflix/tv/data/model/MediaType;ILd7/d;)Ljava/lang/Object;", "removeFromWatchlist", "Lcom/arflix/tv/data/repository/sync/RemoteWatchlistResult;", "getWatchlist", "action", "", "progress", "season", "episode", "Lx6/t0;", "scrobble", "(Ljava/lang/String;Lcom/arflix/tv/data/model/MediaType;IFLjava/lang/Integer;Ljava/lang/Integer;Ld7/d;)Ljava/lang/Object;", "clearPlayback", "(Lcom/arflix/tv/data/model/MediaType;ILjava/lang/Integer;Ljava/lang/Integer;Ld7/d;)Ljava/lang/Object;", "markMovieWatched", "(ILd7/d;)Ljava/lang/Object;", "markMovieUnwatched", "showTmdbId", "markEpisodeWatched", "(IIILd7/d;)Ljava/lang/Object;", "markEpisodeUnwatched", "", "episodes", "markSeasonWatched", "(IILjava/util/List;Ld7/d;)Ljava/lang/Object;", "", "getWatchedMovies", "Lcom/arflix/tv/data/repository/MdbShowWatchedProgress;", "getWatchedShowsProgress", "getWatchedEpisodes", "Lcom/arflix/tv/data/repository/ContinueWatchingItem;", "getContinueWatching", "key", "modifyWatchlist", "(Lcom/arflix/tv/data/model/MediaType;ILjava/lang/String;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/api/MdbWatchlistItem;", "fetchAllWatchlistItems", "item", "sourceOrder", "Lcom/arflix/tv/data/model/MediaItem;", "mapWatchlistItem", "(Lcom/arflix/tv/data/api/MdbWatchlistItem;I)Lcom/arflix/tv/data/model/MediaItem;", "Lcom/arflix/tv/data/api/MdbWatchedBody;", "episodeBody", "(III)Lcom/arflix/tv/data/api/MdbWatchedBody;", "Lkotlin/Function2;", "Ld7/d;", "block", "watchedCall", "(Lr7/p;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/api/MdbPlaybackItem;", "mapPlaybackItem", "(Lcom/arflix/tv/data/api/MdbPlaybackItem;)Lcom/arflix/tv/data/repository/ContinueWatchingItem;", "iso", "", "parseIsoMillis", "(Ljava/lang/String;)J", "Lcom/arflix/tv/data/api/MdbListApi;", "Lcom/arflix/tv/data/repository/sync/SyncProviderStore;", "TAG", "Ljava/lang/String;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class MdbListRepository {
    public static final int $stable = SyncProviderStore.$stable;
    private final String TAG = "MdbListRepository";
    private final MdbListApi api;
    private final SyncProviderStore store;

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MdbListRepository$clearPlayback$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.MdbListRepository$clearPlayback$2", f = "MdbListRepository.kt", l = {199, 212}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        final /* synthetic */ Integer $episode;
        final /* synthetic */ MediaType $mediaType;
        final /* synthetic */ Integer $season;
        final /* synthetic */ int $tmdbId;
        Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(MediaType mediaType, int i10, Integer num, Integer num2, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$mediaType = mediaType;
            this.$tmdbId = i10;
            this.$season = num;
            this.$episode = num2;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return MdbListRepository.this.new AnonymousClass2(this.$mediaType, this.$tmdbId, this.$season, this.$episode, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:30:0x00bd, code lost:
        
            if (r3.scrobbleClear(r0, r7, r18) == r5) goto L31;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r19) throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 212
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MdbListRepository.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MdbListRepository$fetchAllWatchlistItems$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MdbListRepository", f = "MdbListRepository.kt", l = {TsExtractor.TS_STREAM_TYPE_DTS}, m = "fetchAllWatchlistItems", v = 2)
    public static final class AnonymousClass1 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MdbListRepository.this.fetchAllWatchlistItems(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MdbListRepository$fetchUsername$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Ljava/lang/String;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.MdbListRepository$fetchUsername$2", f = "MdbListRepository.kt", l = {AnimationConstants.STAGGER_SECTION, 81}, m = "invokeSuspend", v = 2)
    public static final class C08682 extends f7.j implements r7.p<ka.k0, d7.d<? super String>, Object> {
        Object L$0;
        int label;

        public C08682(d7.d<? super C08682> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return MdbListRepository.this.new C08682(dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:23:0x0044, code lost:
        
            if (r6 == r4) goto L24;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r6) {
            /*
                r5 = this;
                int r0 = r5.label
                r1 = 2
                r2 = 1
                r3 = 0
                e7.a r4 = e7.a.f15033i
                if (r0 == 0) goto L23
                if (r0 == r2) goto L1f
                if (r0 != r1) goto L17
                java.lang.Object r0 = r5.L$0
                java.lang.String r0 = (java.lang.String) r0
                k2.c.G(r6)     // Catch: java.util.concurrent.CancellationException -> L15 java.lang.Exception -> L4e
                goto L47
            L15:
                r6 = move-exception
                goto L4f
            L17:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            L1f:
                k2.c.G(r6)     // Catch: java.util.concurrent.CancellationException -> L15 java.lang.Exception -> L4e
                goto L31
            L23:
                k2.c.G(r6)
                com.arflix.tv.data.repository.MdbListRepository r6 = com.arflix.tv.data.repository.MdbListRepository.this     // Catch: java.util.concurrent.CancellationException -> L15 java.lang.Exception -> L4e
                r5.label = r2     // Catch: java.util.concurrent.CancellationException -> L15 java.lang.Exception -> L4e
                java.lang.Object r6 = com.arflix.tv.data.repository.MdbListRepository.access$key(r6, r5)     // Catch: java.util.concurrent.CancellationException -> L15 java.lang.Exception -> L4e
                if (r6 != r4) goto L31
                goto L46
            L31:
                java.lang.String r6 = (java.lang.String) r6     // Catch: java.util.concurrent.CancellationException -> L15 java.lang.Exception -> L4e
                if (r6 != 0) goto L36
                return r3
            L36:
                com.arflix.tv.data.repository.MdbListRepository r0 = com.arflix.tv.data.repository.MdbListRepository.this     // Catch: java.util.concurrent.CancellationException -> L15 java.lang.Exception -> L4e
                com.arflix.tv.data.api.MdbListApi r0 = com.arflix.tv.data.repository.MdbListRepository.access$getApi$p(r0)     // Catch: java.util.concurrent.CancellationException -> L15 java.lang.Exception -> L4e
                r5.L$0 = r3     // Catch: java.util.concurrent.CancellationException -> L15 java.lang.Exception -> L4e
                r5.label = r1     // Catch: java.util.concurrent.CancellationException -> L15 java.lang.Exception -> L4e
                java.lang.Object r6 = r0.getUser(r6, r5)     // Catch: java.util.concurrent.CancellationException -> L15 java.lang.Exception -> L4e
                if (r6 != r4) goto L47
            L46:
                return r4
            L47:
                com.arflix.tv.data.api.MdbUser r6 = (com.arflix.tv.data.api.MdbUser) r6     // Catch: java.util.concurrent.CancellationException -> L15 java.lang.Exception -> L4e
                java.lang.String r6 = r6.getUsername()     // Catch: java.util.concurrent.CancellationException -> L15 java.lang.Exception -> L4e
                return r6
            L4e:
                return r3
            L4f:
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MdbListRepository.C08682.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super String> dVar) {
            return ((C08682) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MdbListRepository$getContinueWatching$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/repository/ContinueWatchingItem;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.MdbListRepository$getContinueWatching$2", f = "MdbListRepository.kt", l = {376, 378}, m = "invokeSuspend", v = 2)
    public static final class C08692 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends ContinueWatchingItem>>, Object> {
        Object L$0;
        int label;

        public C08692(d7.d<? super C08692> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return MdbListRepository.this.new C08692(dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:21:0x0046, code lost:
        
            if (r6 == r4) goto L22;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r6) throws java.lang.Exception {
            /*
                r5 = this;
                int r0 = r5.label
                kotlin.collections.z r1 = kotlin.collections.z.f19728i
                r2 = 2
                r3 = 1
                e7.a r4 = e7.a.f15033i
                if (r0 == 0) goto L24
                if (r0 == r3) goto L20
                if (r0 != r2) goto L18
                java.lang.Object r0 = r5.L$0
                java.lang.String r0 = (java.lang.String) r0
                k2.c.G(r6)     // Catch: java.lang.Exception -> L16
                goto L49
            L16:
                r6 = move-exception
                goto L7c
            L18:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            L20:
                k2.c.G(r6)
                goto L32
            L24:
                k2.c.G(r6)
                com.arflix.tv.data.repository.MdbListRepository r6 = com.arflix.tv.data.repository.MdbListRepository.this
                r5.label = r3
                java.lang.Object r6 = com.arflix.tv.data.repository.MdbListRepository.access$key(r6, r5)
                if (r6 != r4) goto L32
                goto L48
            L32:
                java.lang.String r6 = (java.lang.String) r6
                if (r6 != 0) goto L37
                return r1
            L37:
                com.arflix.tv.data.repository.MdbListRepository r0 = com.arflix.tv.data.repository.MdbListRepository.this     // Catch: java.lang.Exception -> L16
                com.arflix.tv.data.api.MdbListApi r0 = com.arflix.tv.data.repository.MdbListRepository.access$getApi$p(r0)     // Catch: java.lang.Exception -> L16
                r3 = 0
                r5.L$0 = r3     // Catch: java.lang.Exception -> L16
                r5.label = r2     // Catch: java.lang.Exception -> L16
                java.lang.Object r6 = r0.getPlayback(r6, r5)     // Catch: java.lang.Exception -> L16
                if (r6 != r4) goto L49
            L48:
                return r4
            L49:
                java.lang.Iterable r6 = (java.lang.Iterable) r6     // Catch: java.lang.Exception -> L16
                com.arflix.tv.data.repository.MdbListRepository r0 = com.arflix.tv.data.repository.MdbListRepository.this     // Catch: java.lang.Exception -> L16
                java.util.ArrayList r2 = new java.util.ArrayList     // Catch: java.lang.Exception -> L16
                r2.<init>()     // Catch: java.lang.Exception -> L16
                java.util.Iterator r6 = r6.iterator()     // Catch: java.lang.Exception -> L16
            L56:
                boolean r3 = r6.hasNext()     // Catch: java.lang.Exception -> L16
                if (r3 == 0) goto L6c
                java.lang.Object r3 = r6.next()     // Catch: java.lang.Exception -> L16
                com.arflix.tv.data.api.MdbPlaybackItem r3 = (com.arflix.tv.data.api.MdbPlaybackItem) r3     // Catch: java.lang.Exception -> L16
                com.arflix.tv.data.repository.ContinueWatchingItem r3 = com.arflix.tv.data.repository.MdbListRepository.access$mapPlaybackItem(r0, r3)     // Catch: java.lang.Exception -> L16
                if (r3 == 0) goto L56
                r2.add(r3)     // Catch: java.lang.Exception -> L16
                goto L56
            L6c:
                com.arflix.tv.data.repository.MdbListRepository$getContinueWatching$2$invokeSuspend$$inlined$sortedByDescending$1 r6 = new com.arflix.tv.data.repository.MdbListRepository$getContinueWatching$2$invokeSuspend$$inlined$sortedByDescending$1     // Catch: java.lang.Exception -> L16
                r6.<init>()     // Catch: java.lang.Exception -> L16
                java.util.List r6 = kotlin.collections.x.W0(r2, r6)     // Catch: java.lang.Exception -> L16
                r0 = 50
                java.util.List r6 = kotlin.collections.x.X0(r6, r0)     // Catch: java.lang.Exception -> L16
                return r6
            L7c:
                boolean r0 = r6 instanceof java.util.concurrent.CancellationException
                if (r0 != 0) goto L8e
                com.arflix.tv.util.AppLogger r0 = com.arflix.tv.util.AppLogger.INSTANCE
                com.arflix.tv.data.repository.MdbListRepository r2 = com.arflix.tv.data.repository.MdbListRepository.this
                java.lang.String r2 = com.arflix.tv.data.repository.MdbListRepository.access$getTAG$p(r2)
                java.lang.String r3 = "continue watching fetch failed"
                r0.e(r2, r3, r6)
                return r1
            L8e:
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MdbListRepository.C08692.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super List<ContinueWatchingItem>> dVar) {
            return ((C08692) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MdbListRepository$getWatchedEpisodes$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "", "<anonymous>", "(Lka/k0;)Ljava/util/Set;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.MdbListRepository$getWatchedEpisodes$2", f = "MdbListRepository.kt", l = {349, 355}, m = "invokeSuspend", v = 2)
    public static final class C08702 extends f7.j implements r7.p<ka.k0, d7.d<? super Set<? extends String>>, Object> {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;

        public C08702(d7.d<? super C08702> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return MdbListRepository.this.new C08702(dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:15:0x0040, code lost:
        
            if (r2 == r6) goto L23;
         */
        /* JADX WARN: Code restructure failed: missing block: B:22:0x006e, code lost:
        
            if (r10 != r6) goto L24;
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x0070, code lost:
        
            return r6;
         */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x006e -> B:24:0x0071). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r18) throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 243
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MdbListRepository.C08702.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super Set<String>> dVar) {
            return ((C08702) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MdbListRepository$getWatchedMovies$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "", "<anonymous>", "(Lka/k0;)Ljava/util/Set;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.MdbListRepository$getWatchedMovies$2", f = "MdbListRepository.kt", l = {280, 286}, m = "invokeSuspend", v = 2)
    public static final class C08712 extends f7.j implements r7.p<ka.k0, d7.d<? super Set<? extends Integer>>, Object> {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;

        public C08712(d7.d<? super C08712> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return MdbListRepository.this.new C08712(dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:15:0x0038, code lost:
        
            if (r12 == r4) goto L23;
         */
        /* JADX WARN: Code restructure failed: missing block: B:22:0x0060, code lost:
        
            if (r12 != r4) goto L24;
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x0062, code lost:
        
            return r4;
         */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0060 -> B:24:0x0063). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r12) throws java.lang.Exception {
            /*
                r11 = this;
                int r0 = r11.label
                kotlin.collections.b0 r1 = kotlin.collections.b0.f19686i
                r2 = 2
                r3 = 1
                e7.a r4 = e7.a.f15033i
                if (r0 == 0) goto L2d
                if (r0 == r3) goto L29
                if (r0 != r2) goto L21
                int r0 = r11.I$1
                int r5 = r11.I$0
                java.lang.Object r6 = r11.L$1
                java.util.Set r6 = (java.util.Set) r6
                java.lang.Object r7 = r11.L$0
                java.lang.String r7 = (java.lang.String) r7
                k2.c.G(r12)     // Catch: java.lang.Exception -> L1e
                goto L63
            L1e:
                r12 = move-exception
                goto La9
            L21:
                java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r12.<init>(r0)
                throw r12
            L29:
                k2.c.G(r12)
                goto L3b
            L2d:
                k2.c.G(r12)
                com.arflix.tv.data.repository.MdbListRepository r12 = com.arflix.tv.data.repository.MdbListRepository.this
                r11.label = r3
                java.lang.Object r12 = com.arflix.tv.data.repository.MdbListRepository.access$key(r12, r11)
                if (r12 != r4) goto L3b
                goto L62
            L3b:
                java.lang.String r12 = (java.lang.String) r12
                if (r12 != 0) goto L40
                return r1
            L40:
                java.util.LinkedHashSet r0 = new java.util.LinkedHashSet     // Catch: java.lang.Exception -> L1e
                r0.<init>()     // Catch: java.lang.Exception -> L1e
                r5 = 0
                r6 = 1000(0x3e8, float:1.401E-42)
                r7 = r6
                r6 = r0
                r0 = r7
                r7 = r12
            L4c:
                com.arflix.tv.data.repository.MdbListRepository r12 = com.arflix.tv.data.repository.MdbListRepository.this     // Catch: java.lang.Exception -> L1e
                com.arflix.tv.data.api.MdbListApi r12 = com.arflix.tv.data.repository.MdbListRepository.access$getApi$p(r12)     // Catch: java.lang.Exception -> L1e
                r11.L$0 = r7     // Catch: java.lang.Exception -> L1e
                r11.L$1 = r6     // Catch: java.lang.Exception -> L1e
                r11.I$0 = r5     // Catch: java.lang.Exception -> L1e
                r11.I$1 = r0     // Catch: java.lang.Exception -> L1e
                r11.label = r2     // Catch: java.lang.Exception -> L1e
                java.lang.Object r12 = r12.getWatched(r7, r0, r5, r11)     // Catch: java.lang.Exception -> L1e
                if (r12 != r4) goto L63
            L62:
                return r4
            L63:
                com.arflix.tv.data.api.MdbWatchedResponse r12 = (com.arflix.tv.data.api.MdbWatchedResponse) r12     // Catch: java.lang.Exception -> L1e
                java.util.List r8 = r12.getMovies()     // Catch: java.lang.Exception -> L1e
                if (r8 == 0) goto L9a
                java.util.Iterator r8 = r8.iterator()     // Catch: java.lang.Exception -> L1e
            L6f:
                boolean r9 = r8.hasNext()     // Catch: java.lang.Exception -> L1e
                if (r9 == 0) goto L9a
                java.lang.Object r9 = r8.next()     // Catch: java.lang.Exception -> L1e
                com.arflix.tv.data.api.MdbWatchedMovieRow r9 = (com.arflix.tv.data.api.MdbWatchedMovieRow) r9     // Catch: java.lang.Exception -> L1e
                com.arflix.tv.data.api.MdbMovieInfo r9 = r9.getMovie()     // Catch: java.lang.Exception -> L1e
                if (r9 == 0) goto L6f
                com.arflix.tv.data.api.MdbIds r9 = r9.getIds()     // Catch: java.lang.Exception -> L1e
                if (r9 == 0) goto L6f
                java.lang.Integer r9 = r9.getTmdb()     // Catch: java.lang.Exception -> L1e
                if (r9 == 0) goto L6f
                int r9 = r9.intValue()     // Catch: java.lang.Exception -> L1e
                java.lang.Integer r10 = new java.lang.Integer     // Catch: java.lang.Exception -> L1e
                r10.<init>(r9)     // Catch: java.lang.Exception -> L1e
                r6.add(r10)     // Catch: java.lang.Exception -> L1e
                goto L6f
            L9a:
                com.arflix.tv.data.api.MdbPagination r12 = r12.getPagination()     // Catch: java.lang.Exception -> L1e
                if (r12 == 0) goto La8
                boolean r12 = r12.getHasMore()     // Catch: java.lang.Exception -> L1e
                if (r12 != r3) goto La8
                int r5 = r5 + r0
                goto L4c
            La8:
                return r6
            La9:
                boolean r0 = r12 instanceof java.util.concurrent.CancellationException
                if (r0 != 0) goto Lae
                return r1
            Lae:
                throw r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MdbListRepository.C08712.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super Set<Integer>> dVar) {
            return ((C08712) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MdbListRepository$getWatchedShowsProgress$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/repository/MdbShowWatchedProgress;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.MdbListRepository$getWatchedShowsProgress$2", f = "MdbListRepository.kt", l = {305, MediaError.DetailedErrorCode.HLS_NETWORK_INVALID_SEGMENT}, m = "invokeSuspend", v = 2)
    public static final class C08722 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends MdbShowWatchedProgress>>, Object> {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.data.repository.MdbListRepository$getWatchedShowsProgress$2$Acc */
        @Metadata(d1 = {"\u0000+\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0010#\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR#\u0010\r\u001a\u0014\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00100\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"com/arflix/tv/data/repository/MdbListRepository$getWatchedShowsProgress$2$Acc", "", LinkHeader.Parameters.Title, "", "year", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "getYear", "setYear", "eps", "", "", "", "getEps", "()Ljava/util/Map;", "lastMs", "", "getLastMs", "()J", "setLastMs", "(J)V", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Acc {
            private final Map<Integer, Set<Integer>> eps = new LinkedHashMap();
            private long lastMs;
            private String title;
            private String year;

            public Acc(String str, String str2) {
                this.title = str;
                this.year = str2;
            }

            public final Map<Integer, Set<Integer>> getEps() {
                return this.eps;
            }

            public final long getLastMs() {
                return this.lastMs;
            }

            public final String getTitle() {
                return this.title;
            }

            public final String getYear() {
                return this.year;
            }

            public final void setLastMs(long j10) {
                this.lastMs = j10;
            }

            public final void setTitle(String str) {
                this.title = str;
            }

            public final void setYear(String str) {
                this.year = str;
            }
        }

        public C08722(d7.d<? super C08722> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return MdbListRepository.this.new C08722(dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:15:0x0040, code lost:
        
            if (r0 == r5) goto L23;
         */
        /* JADX WARN: Code restructure failed: missing block: B:22:0x006e, code lost:
        
            if (r9 != r5) goto L24;
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x0070, code lost:
        
            return r5;
         */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0091  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x006e -> B:24:0x0071). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r22) throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 536
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MdbListRepository.C08722.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super List<MdbShowWatchedProgress>> dVar) {
            return ((C08722) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MdbListRepository$getWatchlist$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/repository/sync/RemoteWatchlistResult;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/repository/sync/RemoteWatchlistResult;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.MdbListRepository$getWatchlist$2", f = "MdbListRepository.kt", l = {119, 121}, m = "invokeSuspend", v = 2)
    public static final class C08732 extends f7.j implements r7.p<ka.k0, d7.d<? super RemoteWatchlistResult>, Object> {
        Object L$0;
        int label;

        public C08732(d7.d<? super C08732> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return MdbListRepository.this.new C08732(dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:22:0x0047, code lost:
        
            if (r10 == r5) goto L23;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) throws java.lang.Exception {
            /*
                r9 = this;
                int r0 = r9.label
                r1 = 2
                r2 = 0
                r3 = 0
                r4 = 1
                e7.a r5 = e7.a.f15033i
                if (r0 == 0) goto L25
                if (r0 == r4) goto L21
                if (r0 != r1) goto L19
                java.lang.Object r0 = r9.L$0
                java.lang.String r0 = (java.lang.String) r0
                k2.c.G(r10)     // Catch: java.lang.Exception -> L16
                goto L4a
            L16:
                r10 = move-exception
                goto L8f
            L19:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r0)
                throw r10
            L21:
                k2.c.G(r10)
                goto L33
            L25:
                k2.c.G(r10)
                com.arflix.tv.data.repository.MdbListRepository r10 = com.arflix.tv.data.repository.MdbListRepository.this
                r9.label = r4
                java.lang.Object r10 = com.arflix.tv.data.repository.MdbListRepository.access$key(r10, r9)
                if (r10 != r5) goto L33
                goto L49
            L33:
                java.lang.String r10 = (java.lang.String) r10
                if (r10 != 0) goto L3d
                com.arflix.tv.data.repository.sync.RemoteWatchlistResult r10 = new com.arflix.tv.data.repository.sync.RemoteWatchlistResult
                r10.<init>(r3, r2, r3)
                return r10
            L3d:
                com.arflix.tv.data.repository.MdbListRepository r0 = com.arflix.tv.data.repository.MdbListRepository.this     // Catch: java.lang.Exception -> L16
                r9.L$0 = r2     // Catch: java.lang.Exception -> L16
                r9.label = r1     // Catch: java.lang.Exception -> L16
                java.lang.Object r10 = com.arflix.tv.data.repository.MdbListRepository.access$fetchAllWatchlistItems(r0, r10, r9)     // Catch: java.lang.Exception -> L16
                if (r10 != r5) goto L4a
            L49:
                return r5
            L4a:
                java.util.List r10 = (java.util.List) r10     // Catch: java.lang.Exception -> L16
                com.arflix.tv.data.repository.MdbListRepository r0 = com.arflix.tv.data.repository.MdbListRepository.this     // Catch: java.lang.Exception -> L16
                java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.lang.Exception -> L16
                r1.<init>()     // Catch: java.lang.Exception -> L16
                java.util.Iterator r5 = r10.iterator()     // Catch: java.lang.Exception -> L16
                r6 = r3
            L58:
                boolean r7 = r5.hasNext()     // Catch: java.lang.Exception -> L16
                if (r7 == 0) goto L77
                java.lang.Object r7 = r5.next()     // Catch: java.lang.Exception -> L16
                int r8 = r6 + 1
                if (r6 < 0) goto L73
                com.arflix.tv.data.api.MdbWatchlistItem r7 = (com.arflix.tv.data.api.MdbWatchlistItem) r7     // Catch: java.lang.Exception -> L16
                com.arflix.tv.data.model.MediaItem r6 = com.arflix.tv.data.repository.MdbListRepository.access$mapWatchlistItem(r0, r7, r6)     // Catch: java.lang.Exception -> L16
                if (r6 == 0) goto L71
                r1.add(r6)     // Catch: java.lang.Exception -> L16
            L71:
                r6 = r8
                goto L58
            L73:
                t7.a.Q()     // Catch: java.lang.Exception -> L16
                throw r2     // Catch: java.lang.Exception -> L16
            L77:
                com.arflix.tv.data.repository.MdbListRepository$getWatchlist$2$invokeSuspend$$inlined$compareBy$1 r0 = new com.arflix.tv.data.repository.MdbListRepository$getWatchlist$2$invokeSuspend$$inlined$compareBy$1     // Catch: java.lang.Exception -> L16
                r0.<init>()     // Catch: java.lang.Exception -> L16
                com.arflix.tv.data.repository.MdbListRepository$getWatchlist$2$invokeSuspend$$inlined$thenByDescending$1 r5 = new com.arflix.tv.data.repository.MdbListRepository$getWatchlist$2$invokeSuspend$$inlined$thenByDescending$1     // Catch: java.lang.Exception -> L16
                r5.<init>()     // Catch: java.lang.Exception -> L16
                java.util.List r0 = kotlin.collections.x.W0(r1, r5)     // Catch: java.lang.Exception -> L16
                com.arflix.tv.data.repository.sync.RemoteWatchlistResult r1 = new com.arflix.tv.data.repository.sync.RemoteWatchlistResult     // Catch: java.lang.Exception -> L16
                int r10 = r10.size()     // Catch: java.lang.Exception -> L16
                r1.<init>(r4, r0, r10)     // Catch: java.lang.Exception -> L16
                return r1
            L8f:
                boolean r0 = r10 instanceof java.util.concurrent.CancellationException
                if (r0 != 0) goto La7
                com.arflix.tv.util.AppLogger r0 = com.arflix.tv.util.AppLogger.INSTANCE
                com.arflix.tv.data.repository.MdbListRepository r1 = com.arflix.tv.data.repository.MdbListRepository.this
                java.lang.String r1 = com.arflix.tv.data.repository.MdbListRepository.access$getTAG$p(r1)
                java.lang.String r5 = "watchlist fetch failed"
                r0.e(r1, r5, r10)
                com.arflix.tv.data.repository.sync.RemoteWatchlistResult r10 = new com.arflix.tv.data.repository.sync.RemoteWatchlistResult
                r10.<init>(r4, r2, r3)
                return r10
            La7:
                throw r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MdbListRepository.C08732.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super RemoteWatchlistResult> dVar) {
            return ((C08732) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MdbListRepository$isConnected$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MdbListRepository", f = "MdbListRepository.kt", l = {72}, m = "isConnected", v = 2)
    public static final class C08741 extends f7.c {
        int label;
        /* synthetic */ Object result;

        public C08741(d7.d<? super C08741> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MdbListRepository.this.isConnected(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MdbListRepository$markEpisodeUnwatched$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MdbListRepository$markEpisodeUnwatched$2", f = "MdbListRepository.kt", l = {234}, m = "invokeSuspend", v = 2)
    public static final class C08752 extends f7.j implements r7.p<String, d7.d<? super Object>, Object> {
        final /* synthetic */ int $episode;
        final /* synthetic */ int $season;
        final /* synthetic */ int $showTmdbId;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C08752(int i10, int i11, int i12, d7.d<? super C08752> dVar) {
            super(2, dVar);
            this.$showTmdbId = i10;
            this.$season = i11;
            this.$episode = i12;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C08752 c08752 = MdbListRepository.this.new C08752(this.$showTmdbId, this.$season, this.$episode, dVar);
            c08752.L$0 = obj;
            return c08752;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            String str = (String) this.L$0;
            int i10 = this.label;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return obj;
            }
            k2.c.G(obj);
            MdbListApi mdbListApi = MdbListRepository.this.api;
            MdbWatchedBody mdbWatchedBodyEpisodeBody = MdbListRepository.this.episodeBody(this.$showTmdbId, this.$season, this.$episode);
            this.L$0 = null;
            this.label = 1;
            Object objRemoveWatched = mdbListApi.removeWatched(str, mdbWatchedBodyEpisodeBody, this);
            e7.a aVar = e7.a.f15033i;
            return objRemoveWatched == aVar ? aVar : objRemoveWatched;
        }

        @Override // r7.p
        public final Object invoke(String str, d7.d<Object> dVar) {
            return ((C08752) create(str, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MdbListRepository$markEpisodeWatched$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MdbListRepository$markEpisodeWatched$2", f = "MdbListRepository.kt", l = {230}, m = "invokeSuspend", v = 2)
    public static final class C08762 extends f7.j implements r7.p<String, d7.d<? super Object>, Object> {
        final /* synthetic */ int $episode;
        final /* synthetic */ int $season;
        final /* synthetic */ int $showTmdbId;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C08762(int i10, int i11, int i12, d7.d<? super C08762> dVar) {
            super(2, dVar);
            this.$showTmdbId = i10;
            this.$season = i11;
            this.$episode = i12;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C08762 c08762 = MdbListRepository.this.new C08762(this.$showTmdbId, this.$season, this.$episode, dVar);
            c08762.L$0 = obj;
            return c08762;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            String str = (String) this.L$0;
            int i10 = this.label;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return obj;
            }
            k2.c.G(obj);
            MdbListApi mdbListApi = MdbListRepository.this.api;
            MdbWatchedBody mdbWatchedBodyEpisodeBody = MdbListRepository.this.episodeBody(this.$showTmdbId, this.$season, this.$episode);
            this.L$0 = null;
            this.label = 1;
            Object objAddWatched = mdbListApi.addWatched(str, mdbWatchedBodyEpisodeBody, this);
            e7.a aVar = e7.a.f15033i;
            return objAddWatched == aVar ? aVar : objAddWatched;
        }

        @Override // r7.p
        public final Object invoke(String str, d7.d<Object> dVar) {
            return ((C08762) create(str, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MdbListRepository$markMovieUnwatched$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MdbListRepository$markMovieUnwatched$2", f = "MdbListRepository.kt", l = {226}, m = "invokeSuspend", v = 2)
    public static final class C08772 extends f7.j implements r7.p<String, d7.d<? super Object>, Object> {
        final /* synthetic */ int $tmdbId;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C08772(int i10, d7.d<? super C08772> dVar) {
            super(2, dVar);
            this.$tmdbId = i10;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C08772 c08772 = MdbListRepository.this.new C08772(this.$tmdbId, dVar);
            c08772.L$0 = obj;
            return c08772;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            String str = (String) this.L$0;
            int i10 = this.label;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return obj;
            }
            k2.c.G(obj);
            MdbListApi mdbListApi = MdbListRepository.this.api;
            MdbWatchedBody mdbWatchedBody = new MdbWatchedBody(Collections.singletonList(new MdbIdsItem(new MdbIds(new Integer(this.$tmdbId), null, null, null, null, 30, null))), null, 2, null);
            this.L$0 = null;
            this.label = 1;
            Object objRemoveWatched = mdbListApi.removeWatched(str, mdbWatchedBody, this);
            e7.a aVar = e7.a.f15033i;
            return objRemoveWatched == aVar ? aVar : objRemoveWatched;
        }

        @Override // r7.p
        public final Object invoke(String str, d7.d<Object> dVar) {
            return ((C08772) create(str, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MdbListRepository$markMovieWatched$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MdbListRepository$markMovieWatched$2", f = "MdbListRepository.kt", l = {222}, m = "invokeSuspend", v = 2)
    public static final class C08782 extends f7.j implements r7.p<String, d7.d<? super Object>, Object> {
        final /* synthetic */ int $tmdbId;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C08782(int i10, d7.d<? super C08782> dVar) {
            super(2, dVar);
            this.$tmdbId = i10;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C08782 c08782 = MdbListRepository.this.new C08782(this.$tmdbId, dVar);
            c08782.L$0 = obj;
            return c08782;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            String str = (String) this.L$0;
            int i10 = this.label;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return obj;
            }
            k2.c.G(obj);
            MdbListApi mdbListApi = MdbListRepository.this.api;
            MdbWatchedBody mdbWatchedBody = new MdbWatchedBody(Collections.singletonList(new MdbIdsItem(new MdbIds(new Integer(this.$tmdbId), null, null, null, null, 30, null))), null, 2, null);
            this.L$0 = null;
            this.label = 1;
            Object objAddWatched = mdbListApi.addWatched(str, mdbWatchedBody, this);
            e7.a aVar = e7.a.f15033i;
            return objAddWatched == aVar ? aVar : objAddWatched;
        }

        @Override // r7.p
        public final Object invoke(String str, d7.d<Object> dVar) {
            return ((C08782) create(str, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MdbListRepository$markSeasonWatched$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MdbListRepository$markSeasonWatched$2", f = "MdbListRepository.kt", l = {239}, m = "invokeSuspend", v = 2)
    public static final class C08792 extends f7.j implements r7.p<String, d7.d<? super Object>, Object> {
        final /* synthetic */ List<Integer> $episodes;
        final /* synthetic */ int $season;
        final /* synthetic */ int $showTmdbId;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C08792(int i10, int i11, List<Integer> list, d7.d<? super C08792> dVar) {
            super(2, dVar);
            this.$showTmdbId = i10;
            this.$season = i11;
            this.$episodes = list;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C08792 c08792 = MdbListRepository.this.new C08792(this.$showTmdbId, this.$season, this.$episodes, dVar);
            c08792.L$0 = obj;
            return c08792;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            String str = (String) this.L$0;
            int i10 = this.label;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return obj;
            }
            k2.c.G(obj);
            MdbListApi mdbListApi = MdbListRepository.this.api;
            MdbIds mdbIds = new MdbIds(new Integer(this.$showTmdbId), null, null, null, null, 30, null);
            int i11 = this.$season;
            List<Integer> list = this.$episodes;
            ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(new MdbWatchedEpisodeRef(((Number) it.next()).intValue()));
            }
            MdbWatchedBody mdbWatchedBody = new MdbWatchedBody(null, Collections.singletonList(new MdbWatchedShowRef(mdbIds, Collections.singletonList(new MdbWatchedSeasonRef(i11, arrayList)))), 1, null);
            this.L$0 = null;
            this.label = 1;
            Object objAddWatched = mdbListApi.addWatched(str, mdbWatchedBody, this);
            e7.a aVar = e7.a.f15033i;
            return objAddWatched == aVar ? aVar : objAddWatched;
        }

        @Override // r7.p
        public final Object invoke(String str, d7.d<Object> dVar) {
            return ((C08792) create(str, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MdbListRepository$modifyWatchlist$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Z"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.MdbListRepository$modifyWatchlist$2", f = "MdbListRepository.kt", l = {MediaError.DetailedErrorCode.MEDIA_DECODE, 109}, m = "invokeSuspend", v = 2)
    public static final class C08802 extends f7.j implements r7.p<ka.k0, d7.d<? super Boolean>, Object> {
        final /* synthetic */ String $action;
        final /* synthetic */ MediaType $mediaType;
        final /* synthetic */ int $tmdbId;
        Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C08802(MediaType mediaType, int i10, String str, d7.d<? super C08802> dVar) {
            super(2, dVar);
            this.$mediaType = mediaType;
            this.$tmdbId = i10;
            this.$action = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return MdbListRepository.this.new C08802(this.$mediaType, this.$tmdbId, this.$action, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:26:0x0076, code lost:
        
            if (r4.modifyWatchlist(r6, r8, r0, r7) == r3) goto L27;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) throws java.lang.Exception {
            /*
                r7 = this;
                int r0 = r7.label
                r1 = 2
                r2 = 1
                e7.a r3 = e7.a.f15033i
                if (r0 == 0) goto L27
                if (r0 == r2) goto L23
                if (r0 != r1) goto L1b
                java.lang.Object r0 = r7.L$1
                com.arflix.tv.data.api.MdbWatchlistModifyBody r0 = (com.arflix.tv.data.api.MdbWatchlistModifyBody) r0
                java.lang.Object r0 = r7.L$0
                java.lang.String r0 = (java.lang.String) r0
                k2.c.G(r8)     // Catch: java.lang.Exception -> L19
                goto L94
            L19:
                r8 = move-exception
                goto L79
            L1b:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r0)
                throw r8
            L23:
                k2.c.G(r8)
                goto L35
            L27:
                k2.c.G(r8)
                com.arflix.tv.data.repository.MdbListRepository r8 = com.arflix.tv.data.repository.MdbListRepository.this
                r7.label = r2
                java.lang.Object r8 = com.arflix.tv.data.repository.MdbListRepository.access$key(r8, r7)
                if (r8 != r3) goto L35
                goto L78
            L35:
                java.lang.String r8 = (java.lang.String) r8
                if (r8 != 0) goto L3c
                java.lang.Boolean r8 = java.lang.Boolean.FALSE
                return r8
            L3c:
                com.arflix.tv.data.model.MediaType r0 = r7.$mediaType     // Catch: java.lang.Exception -> L19
                com.arflix.tv.data.model.MediaType r4 = com.arflix.tv.data.model.MediaType.MOVIE     // Catch: java.lang.Exception -> L19
                r5 = 0
                if (r0 != r4) goto L54
                com.arflix.tv.data.api.MdbWatchlistModifyBody r0 = new com.arflix.tv.data.api.MdbWatchlistModifyBody     // Catch: java.lang.Exception -> L19
                com.arflix.tv.data.api.MdbTmdbRef r4 = new com.arflix.tv.data.api.MdbTmdbRef     // Catch: java.lang.Exception -> L19
                int r6 = r7.$tmdbId     // Catch: java.lang.Exception -> L19
                r4.<init>(r6)     // Catch: java.lang.Exception -> L19
                java.util.List r4 = java.util.Collections.singletonList(r4)     // Catch: java.lang.Exception -> L19
                r0.<init>(r4, r5, r1, r5)     // Catch: java.lang.Exception -> L19
                goto L64
            L54:
                com.arflix.tv.data.api.MdbWatchlistModifyBody r0 = new com.arflix.tv.data.api.MdbWatchlistModifyBody     // Catch: java.lang.Exception -> L19
                com.arflix.tv.data.api.MdbTmdbRef r4 = new com.arflix.tv.data.api.MdbTmdbRef     // Catch: java.lang.Exception -> L19
                int r6 = r7.$tmdbId     // Catch: java.lang.Exception -> L19
                r4.<init>(r6)     // Catch: java.lang.Exception -> L19
                java.util.List r4 = java.util.Collections.singletonList(r4)     // Catch: java.lang.Exception -> L19
                r0.<init>(r5, r4, r2, r5)     // Catch: java.lang.Exception -> L19
            L64:
                com.arflix.tv.data.repository.MdbListRepository r4 = com.arflix.tv.data.repository.MdbListRepository.this     // Catch: java.lang.Exception -> L19
                com.arflix.tv.data.api.MdbListApi r4 = com.arflix.tv.data.repository.MdbListRepository.access$getApi$p(r4)     // Catch: java.lang.Exception -> L19
                java.lang.String r6 = r7.$action     // Catch: java.lang.Exception -> L19
                r7.L$0 = r5     // Catch: java.lang.Exception -> L19
                r7.L$1 = r5     // Catch: java.lang.Exception -> L19
                r7.label = r1     // Catch: java.lang.Exception -> L19
                java.lang.Object r8 = r4.modifyWatchlist(r6, r8, r0, r7)     // Catch: java.lang.Exception -> L19
                if (r8 != r3) goto L94
            L78:
                return r3
            L79:
                boolean r0 = r8 instanceof java.util.concurrent.CancellationException
                if (r0 != 0) goto L99
                com.arflix.tv.util.AppLogger r0 = com.arflix.tv.util.AppLogger.INSTANCE
                com.arflix.tv.data.repository.MdbListRepository r1 = com.arflix.tv.data.repository.MdbListRepository.this
                java.lang.String r1 = com.arflix.tv.data.repository.MdbListRepository.access$getTAG$p(r1)
                java.lang.String r2 = r7.$action
                java.lang.String r3 = "watchlist "
                java.lang.String r4 = " failed"
                java.lang.String r2 = a0.c.l(r3, r2, r4)
                r0.e(r1, r2, r8)
                r2 = 0
            L94:
                java.lang.Boolean r8 = java.lang.Boolean.valueOf(r2)
                return r8
            L99:
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MdbListRepository.C08802.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super Boolean> dVar) {
            return ((C08802) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MdbListRepository$scrobble$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.MdbListRepository$scrobble$2", f = "MdbListRepository.kt", l = {174, PsExtractor.PRIVATE_STREAM_1}, m = "invokeSuspend", v = 2)
    public static final class C08812 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        final /* synthetic */ String $action;
        final /* synthetic */ Integer $episode;
        final /* synthetic */ MediaType $mediaType;
        final /* synthetic */ float $progress;
        final /* synthetic */ Integer $season;
        final /* synthetic */ int $tmdbId;
        int I$0;
        Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C08812(float f10, MediaType mediaType, int i10, Integer num, Integer num2, String str, d7.d<? super C08812> dVar) {
            super(2, dVar);
            this.$progress = f10;
            this.$mediaType = mediaType;
            this.$tmdbId = i10;
            this.$season = num;
            this.$episode = num2;
            this.$action = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return MdbListRepository.this.new C08812(this.$progress, this.$mediaType, this.$tmdbId, this.$season, this.$episode, this.$action, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:30:0x00ce, code lost:
        
            if (r3.scrobble(r6, r0, r8, r20) == r5) goto L31;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r21) throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 237
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MdbListRepository.C08812.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C08812) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MdbListRepository$validateKey$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Z"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.MdbListRepository$validateKey$2", f = "MdbListRepository.kt", l = {65}, m = "invokeSuspend", v = 2)
    public static final class C08822 extends f7.j implements r7.p<ka.k0, d7.d<? super Boolean>, Object> {
        final /* synthetic */ String $apiKey;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C08822(String str, d7.d<? super C08822> dVar) {
            super(2, dVar);
            this.$apiKey = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return MdbListRepository.this.new C08822(this.$apiKey, dVar);
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x0042  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r4) throws java.lang.Exception {
            /*
                r3 = this;
                int r0 = r3.label
                r1 = 0
                r2 = 1
                if (r0 == 0) goto L16
                if (r0 != r2) goto Le
                k2.c.G(r4)     // Catch: java.lang.Exception -> Lc
                goto L34
            Lc:
                r4 = move-exception
                goto L45
            Le:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r4.<init>(r0)
                throw r4
            L16:
                k2.c.G(r4)
                com.arflix.tv.data.repository.MdbListRepository r4 = com.arflix.tv.data.repository.MdbListRepository.this     // Catch: java.lang.Exception -> Lc
                com.arflix.tv.data.api.MdbListApi r4 = com.arflix.tv.data.repository.MdbListRepository.access$getApi$p(r4)     // Catch: java.lang.Exception -> Lc
                java.lang.String r0 = r3.$apiKey     // Catch: java.lang.Exception -> Lc
                java.lang.CharSequence r0 = kotlin.text.o.L0(r0)     // Catch: java.lang.Exception -> Lc
                java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> Lc
                r3.label = r2     // Catch: java.lang.Exception -> Lc
                java.lang.Object r4 = r4.getUser(r0, r3)     // Catch: java.lang.Exception -> Lc
                e7.a r0 = e7.a.f15033i
                if (r4 != r0) goto L34
                return r0
            L34:
                com.arflix.tv.data.api.MdbUser r4 = (com.arflix.tv.data.api.MdbUser) r4     // Catch: java.lang.Exception -> Lc
                java.lang.String r4 = r4.getUsername()     // Catch: java.lang.Exception -> Lc
                if (r4 == 0) goto L42
                boolean r4 = kotlin.text.o.h0(r4)     // Catch: java.lang.Exception -> Lc
                if (r4 == 0) goto L43
            L42:
                r1 = r2
            L43:
                r1 = r1 ^ r2
                goto L49
            L45:
                boolean r0 = r4 instanceof java.util.concurrent.CancellationException
                if (r0 != 0) goto L4e
            L49:
                java.lang.Boolean r4 = java.lang.Boolean.valueOf(r1)
                return r4
            L4e:
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MdbListRepository.C08822.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super Boolean> dVar) {
            return ((C08822) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MdbListRepository$watchedCall$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Z"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.MdbListRepository$watchedCall$2", f = "MdbListRepository.kt", l = {266, 268}, m = "invokeSuspend", v = 2)
    public static final class C08832 extends f7.j implements r7.p<ka.k0, d7.d<? super Boolean>, Object> {
        final /* synthetic */ r7.p<String, d7.d<Object>, Object> $block;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C08832(r7.p<? super String, ? super d7.d<Object>, ? extends Object> pVar, d7.d<? super C08832> dVar) {
            super(2, dVar);
            this.$block = pVar;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return MdbListRepository.this.new C08832(this.$block, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:22:0x0042, code lost:
        
            if (r0.invoke(r6, r5) == r3) goto L23;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r6) throws java.lang.Exception {
            /*
                r5 = this;
                int r0 = r5.label
                r1 = 2
                r2 = 1
                e7.a r3 = e7.a.f15033i
                if (r0 == 0) goto L22
                if (r0 == r2) goto L1e
                if (r0 != r1) goto L16
                java.lang.Object r0 = r5.L$0
                java.lang.String r0 = (java.lang.String) r0
                k2.c.G(r6)     // Catch: java.lang.Exception -> L14
                goto L58
            L14:
                r6 = move-exception
                goto L45
            L16:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            L1e:
                k2.c.G(r6)
                goto L30
            L22:
                k2.c.G(r6)
                com.arflix.tv.data.repository.MdbListRepository r6 = com.arflix.tv.data.repository.MdbListRepository.this
                r5.label = r2
                java.lang.Object r6 = com.arflix.tv.data.repository.MdbListRepository.access$key(r6, r5)
                if (r6 != r3) goto L30
                goto L44
            L30:
                java.lang.String r6 = (java.lang.String) r6
                if (r6 != 0) goto L37
                java.lang.Boolean r6 = java.lang.Boolean.FALSE
                return r6
            L37:
                r7.p<java.lang.String, d7.d<java.lang.Object>, java.lang.Object> r0 = r5.$block     // Catch: java.lang.Exception -> L14
                r4 = 0
                r5.L$0 = r4     // Catch: java.lang.Exception -> L14
                r5.label = r1     // Catch: java.lang.Exception -> L14
                java.lang.Object r6 = r0.invoke(r6, r5)     // Catch: java.lang.Exception -> L14
                if (r6 != r3) goto L58
            L44:
                return r3
            L45:
                boolean r0 = r6 instanceof java.util.concurrent.CancellationException
                if (r0 != 0) goto L5d
                com.arflix.tv.util.AppLogger r0 = com.arflix.tv.util.AppLogger.INSTANCE
                com.arflix.tv.data.repository.MdbListRepository r1 = com.arflix.tv.data.repository.MdbListRepository.this
                java.lang.String r1 = com.arflix.tv.data.repository.MdbListRepository.access$getTAG$p(r1)
                java.lang.String r2 = "watched mirror failed"
                r0.e(r1, r2, r6)
                r2 = 0
            L58:
                java.lang.Boolean r6 = java.lang.Boolean.valueOf(r2)
                return r6
            L5d:
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MdbListRepository.C08832.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super Boolean> dVar) {
            return ((C08832) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    @Inject
    public MdbListRepository(MdbListApi mdbListApi, SyncProviderStore syncProviderStore) {
        this.api = mdbListApi;
        this.store = syncProviderStore;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MdbWatchedBody episodeBody(int showTmdbId, int season, int episode) {
        return new MdbWatchedBody(null, Collections.singletonList(new MdbWatchedShowRef(new MdbIds(Integer.valueOf(showTmdbId), null, null, null, null, 30, null), Collections.singletonList(new MdbWatchedSeasonRef(season, Collections.singletonList(new MdbWatchedEpisodeRef(episode)))))), 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x005e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x005f -> B:19:0x0060). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object fetchAllWatchlistItems(java.lang.String r11, d7.d<? super java.util.List<com.arflix.tv.data.api.MdbWatchlistItem>> r12) {
        /*
            r10 = this;
            boolean r0 = r12 instanceof com.arflix.tv.data.repository.MdbListRepository.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r12
            com.arflix.tv.data.repository.MdbListRepository$fetchAllWatchlistItems$1 r0 = (com.arflix.tv.data.repository.MdbListRepository.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.MdbListRepository$fetchAllWatchlistItems$1 r0 = new com.arflix.tv.data.repository.MdbListRepository$fetchAllWatchlistItems$1
            r0.<init>(r12)
        L18:
            java.lang.Object r12 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L3c
            if (r1 != r2) goto L34
            int r11 = r0.I$1
            int r1 = r0.I$0
            java.lang.Object r3 = r0.L$1
            java.util.List r3 = (java.util.List) r3
            java.lang.Object r4 = r0.L$0
            java.lang.String r4 = (java.lang.String) r4
            k2.c.G(r12)
            r9 = r0
            r6 = r1
            r5 = r4
            goto L60
        L34:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L3c:
            java.util.ArrayList r12 = androidx.compose.foundation.c.w(r12)
            r1 = 1000(0x3e8, float:1.401E-42)
            r3 = 0
            r5 = r11
            r9 = r0
            r6 = r1
            r7 = r3
            r3 = r12
        L48:
            com.arflix.tv.data.api.MdbListApi r4 = r10.api
            r9.L$0 = r5
            r9.L$1 = r3
            r9.I$0 = r6
            r9.I$1 = r7
            r9.label = r2
            java.lang.String r8 = "true"
            java.lang.Object r12 = r4.getWatchlistItems(r5, r6, r7, r8, r9)
            e7.a r11 = e7.a.f15033i
            if (r12 != r11) goto L5f
            return r11
        L5f:
            r11 = r7
        L60:
            java.util.List r12 = (java.util.List) r12
            r3.addAll(r12)
            int r12 = r12.size()
            if (r12 < r6) goto L6e
            int r7 = r11 + r6
            goto L48
        L6e:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MdbListRepository.fetchAllWatchlistItems(java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object key(d7.d<? super String> dVar) {
        return this.store.getMdbListApiKey(dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ContinueWatchingItem mapPlaybackItem(MdbPlaybackItem item) {
        Float fH;
        MdbEpisodeInfo episode;
        MdbIds ids;
        Integer tmdb;
        Integer number;
        String strValueOf;
        MdbIds ids2;
        Integer tmdb2;
        String progress = item.getProgress();
        if (progress == null || (fH = kotlin.text.t.H(progress)) == null) {
            return null;
        }
        int iM = t7.a.M(fH.floatValue());
        if (iM >= 3 && iM < 90) {
            Integer runtime = item.getRuntime();
            long jIntValue = ((long) (runtime != null ? runtime.intValue() : 0)) * 60;
            Long updatedAtTs = item.getUpdatedAtTs();
            long jLongValue = updatedAtTs != null ? updatedAtTs.longValue() * 1000 : parseIsoMillis(item.getUpdatedAt());
            if (kotlin.jvm.internal.p.a(item.getType(), "movie")) {
                MdbMovieInfo movie = item.getMovie();
                if (movie == null || (ids2 = movie.getIds()) == null || (tmdb2 = ids2.getTmdb()) == null) {
                    return null;
                }
                int iIntValue = tmdb2.intValue();
                String title = movie.getTitle();
                String str = title == null ? "" : title;
                MediaType mediaType = MediaType.MOVIE;
                int iN = qb.d.n(iM, 0, 100);
                Integer year = movie.getYear();
                strValueOf = year != null ? String.valueOf(year.intValue()) : null;
                return new ContinueWatchingItem(iIntValue, str, mediaType, iN, 0L, jIntValue, null, null, null, null, null, null, null, null, null, null, strValueOf == null ? "" : strValueOf, null, false, null, null, null, null, null, jLongValue, 0, 0, 117374928, null);
            }
            MdbShowInfo show = item.getShow();
            if (show != null && (episode = item.getEpisode()) != null && (ids = show.getIds()) != null && (tmdb = ids.getTmdb()) != null) {
                int iIntValue2 = tmdb.intValue();
                Integer season = episode.getSeason();
                if (season != null && (number = episode.getNumber()) != null) {
                    String title2 = show.getTitle();
                    if (title2 == null) {
                        title2 = "";
                    }
                    MediaType mediaType2 = MediaType.TV;
                    int iN2 = qb.d.n(iM, 0, 100);
                    String name = episode.getName();
                    Integer year2 = show.getYear();
                    strValueOf = year2 != null ? String.valueOf(year2.intValue()) : null;
                    return new ContinueWatchingItem(iIntValue2, title2, mediaType2, iN2, 0L, jIntValue, season, number, null, null, name, null, null, null, null, null, strValueOf == null ? "" : strValueOf, null, false, null, null, null, null, null, jLongValue, 0, 0, 117373712, null);
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MediaItem mapWatchlistItem(MdbWatchlistItem item, int sourceOrder) {
        Integer id;
        String str;
        String strValueOf;
        MdbIds ids = item.getIds();
        if ((ids == null || (id = ids.getTmdb()) == null) && (id = item.getId()) == null) {
            return null;
        }
        int iIntValue = id.intValue();
        MediaType mediaType = kotlin.text.u.L(item.getMediatype(), "show", true) ? MediaType.TV : MediaType.MOVIE;
        Integer releaseYear = item.getReleaseYear();
        if (releaseYear == null || (strValueOf = String.valueOf(releaseYear.intValue())) == null) {
            String releaseDate = item.getReleaseDate();
            String strI0 = releaseDate != null ? kotlin.text.o.I0(4, releaseDate) : null;
            str = strI0 == null ? "" : strI0;
        } else {
            str = strValueOf;
        }
        String title = item.getTitle();
        return new MediaItem(iIntValue, title == null ? "" : title, null, null, str, item.getReleaseDate(), null, null, null, null, mediaType, null, null, 0, false, null, null, null, null, null, false, null, null, null, null, null, null, null, null, false, null, 0.0f, parseIsoMillis(item.getWatchlistAt()), sourceOrder, false, null, false, false, null, null, null, null, -1076, AnalyticsListener.EVENT_VIDEO_DISABLED, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object modifyWatchlist(MediaType mediaType, int i10, String str, d7.d<? super Boolean> dVar) {
        return ka.m0.y(x0.f19655d, new C08802(mediaType, i10, str, null), dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long parseIsoMillis(String iso) throws Exception {
        if (iso == null || kotlin.text.o.h0(iso)) {
            return 0L;
        }
        try {
            return Instant.parse(iso).toEpochMilli();
        } catch (Exception e5) {
            if (e5 instanceof CancellationException) {
                throw e5;
            }
            return 0L;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object watchedCall(r7.p<? super String, ? super d7.d<Object>, ? extends Object> pVar, d7.d<? super Boolean> dVar) {
        return ka.m0.y(x0.f19655d, new C08832(pVar, null), dVar);
    }

    public final Object addToWatchlist(MediaType mediaType, int i10, d7.d<? super Boolean> dVar) {
        return modifyWatchlist(mediaType, i10, "add", dVar);
    }

    public final Object clearPlayback(MediaType mediaType, int i10, Integer num, Integer num2, d7.d<? super t0> dVar) throws Throwable {
        Object objY = ka.m0.y(x0.f19655d, new AnonymousClass2(mediaType, i10, num, num2, null), dVar);
        return objY == e7.a.f15033i ? objY : t0.f22605a;
    }

    public final Object fetchUsername(d7.d<? super String> dVar) {
        return ka.m0.y(x0.f19655d, new C08682(null), dVar);
    }

    public final Object getContinueWatching(d7.d<? super List<ContinueWatchingItem>> dVar) {
        return ka.m0.y(x0.f19655d, new C08692(null), dVar);
    }

    public final Object getWatchedEpisodes(d7.d<? super Set<String>> dVar) {
        return ka.m0.y(x0.f19655d, new C08702(null), dVar);
    }

    public final Object getWatchedMovies(d7.d<? super Set<Integer>> dVar) {
        return ka.m0.y(x0.f19655d, new C08712(null), dVar);
    }

    public final Object getWatchedShowsProgress(d7.d<? super List<MdbShowWatchedProgress>> dVar) {
        return ka.m0.y(x0.f19655d, new C08722(null), dVar);
    }

    public final Object getWatchlist(d7.d<? super RemoteWatchlistResult> dVar) {
        return ka.m0.y(x0.f19655d, new C08732(null), dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object isConnected(d7.d<? super java.lang.Boolean> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.arflix.tv.data.repository.MdbListRepository.C08741
            if (r0 == 0) goto L13
            r0 = r5
            com.arflix.tv.data.repository.MdbListRepository$isConnected$1 r0 = (com.arflix.tv.data.repository.MdbListRepository.C08741) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.MdbListRepository$isConnected$1 r0 = new com.arflix.tv.data.repository.MdbListRepository$isConnected$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r5)
            goto L3b
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2d:
            k2.c.G(r5)
            r0.label = r2
            java.lang.Object r5 = r4.key(r0)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L3b
            return r0
        L3b:
            if (r5 == 0) goto L3e
            goto L3f
        L3e:
            r2 = 0
        L3f:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r2)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MdbListRepository.isConnected(d7.d):java.lang.Object");
    }

    public final Object markEpisodeUnwatched(int i10, int i11, int i12, d7.d<? super Boolean> dVar) {
        return watchedCall(new C08752(i10, i11, i12, null), dVar);
    }

    public final Object markEpisodeWatched(int i10, int i11, int i12, d7.d<? super Boolean> dVar) {
        return watchedCall(new C08762(i10, i11, i12, null), dVar);
    }

    public final Object markMovieUnwatched(int i10, d7.d<? super Boolean> dVar) {
        return watchedCall(new C08772(i10, null), dVar);
    }

    public final Object markMovieWatched(int i10, d7.d<? super Boolean> dVar) {
        return watchedCall(new C08782(i10, null), dVar);
    }

    public final Object markSeasonWatched(int i10, int i11, List<Integer> list, d7.d<? super Boolean> dVar) {
        return watchedCall(new C08792(i10, i11, list, null), dVar);
    }

    public final Object removeFromWatchlist(MediaType mediaType, int i10, d7.d<? super Boolean> dVar) {
        return modifyWatchlist(mediaType, i10, "remove", dVar);
    }

    public final Object scrobble(String str, MediaType mediaType, int i10, float f10, Integer num, Integer num2, d7.d<? super t0> dVar) throws Throwable {
        Object objY = ka.m0.y(x0.f19655d, new C08812(f10, mediaType, i10, num, num2, str, null), dVar);
        return objY == e7.a.f15033i ? objY : t0.f22605a;
    }

    public final Object validateKey(String str, d7.d<? super Boolean> dVar) {
        return ka.m0.y(x0.f19655d, new C08822(str, null), dVar);
    }
}
