package com.arflix.tv.data.repository.sync;

import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.extractor.ts.PsExtractor;
import androidx.media3.extractor.ts.TsExtractor;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.repository.ContinueWatchingItem;
import d7.d;
import f7.c;
import f7.e;
import f7.j;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import ka.k0;
import ka.l0;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B)\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fH\u0086@¢\u0006\u0004\b\r\u0010\u000eJ\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0086@¢\u0006\u0004\b\u0010\u0010\u000eJ\u001a\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0012\u001a\u00020\u0011H\u0086@¢\u0006\u0004\b\u0014\u0010\u0015J*\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u0013H\u0086@¢\u0006\u0004\b\u001b\u0010\u001cJ*\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u0013H\u0086@¢\u0006\u0004\b\u001d\u0010\u001cJ\u0012\u0010\u001f\u001a\u0004\u0018\u00010\u001eH\u0086@¢\u0006\u0004\b\u001f\u0010\u000eJJ\u0010%\u001a\u00020$2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010!\u001a\u00020 2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u0013H\u0086@¢\u0006\u0004\b%\u0010&JJ\u0010'\u001a\u00020$2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010!\u001a\u00020 2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u0013H\u0086@¢\u0006\u0004\b'\u0010&JJ\u0010(\u001a\u00020$2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010!\u001a\u00020 2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u0013H\u0086@¢\u0006\u0004\b(\u0010&JJ\u0010)\u001a\u00020$2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010!\u001a\u00020 2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u0013H\u0086@¢\u0006\u0004\b)\u0010&J\u0016\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00180*H\u0086@¢\u0006\u0004\b+\u0010\u000eJ\u0016\u0010-\u001a\b\u0012\u0004\u0012\u00020,0*H\u0086@¢\u0006\u0004\b-\u0010\u000eJ \u00101\u001a\b\u0012\u0004\u0012\u0002000/2\b\b\u0002\u0010.\u001a\u00020\u0013H\u0086@¢\u0006\u0004\b1\u00102J8\u00103\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u0018H\u0086@¢\u0006\u0004\b3\u00104J\u0019\u00106\u001a\u0004\u0018\u00010\u000f2\u0006\u00105\u001a\u00020\fH\u0002¢\u0006\u0004\b6\u00107J$\u00109\u001a\b\u0012\u0004\u0012\u00020\u000f0/2\f\u00108\u001a\b\u0012\u0004\u0012\u00020\f0*H\u0082@¢\u0006\u0004\b9\u0010:J4\u0010>\u001a\u00020\u00132\"\u0010=\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130<\u0012\u0006\u0012\u0004\u0018\u00010\u00010;H\u0082@¢\u0006\u0004\b>\u0010?J4\u0010@\u001a\u00020$2\"\u0010=\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0<\u0012\u0006\u0012\u0004\u0018\u00010\u00010;H\u0082@¢\u0006\u0004\b@\u0010?JF\u0010B\u001a\b\u0012\u0004\u0012\u00028\u00000*\"\u0004\b\u0000\u0010A2(\u0010=\u001a$\b\u0001\u0012\u0004\u0012\u00020\u000f\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000*0<\u0012\u0006\u0012\u0004\u0018\u00010\u00010;H\u0082@¢\u0006\u0004\bB\u0010?R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010CR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010DR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010ER\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010F¨\u0006G"}, d2 = {"Lcom/arflix/tv/data/repository/sync/RemoteSyncManager;", "", "Lcom/arflix/tv/data/repository/sync/SyncProviderStore;", "store", "Lcom/arflix/tv/data/repository/sync/TraktRemoteProvider;", "traktProvider", "Lcom/arflix/tv/data/repository/sync/MdbListRemoteProvider;", "mdbListProvider", "Lcom/arflix/tv/data/repository/sync/SimklRemoteProvider;", "simklProvider", "<init>", "(Lcom/arflix/tv/data/repository/sync/SyncProviderStore;Lcom/arflix/tv/data/repository/sync/TraktRemoteProvider;Lcom/arflix/tv/data/repository/sync/MdbListRemoteProvider;Lcom/arflix/tv/data/repository/sync/SimklRemoteProvider;)V", "Lcom/arflix/tv/data/repository/sync/SyncProvider;", "selectedProvider", "(Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/repository/sync/RemoteSyncProvider;", "active", "Lcom/arflix/tv/data/repository/sync/TrackingFeature;", "feature", "", "isRemoteConnected", "(Lcom/arflix/tv/data/repository/sync/TrackingFeature;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/model/MediaType;", "mediaType", "", "tmdbId", "isAnime", "addToWatchlist", "(Lcom/arflix/tv/data/model/MediaType;IZLd7/d;)Ljava/lang/Object;", "removeFromWatchlist", "Lcom/arflix/tv/data/repository/sync/RemoteWatchlistResult;", "getWatchlist", "", "progress", "season", "episode", "Lx6/t0;", "scrobbleStart", "(Lcom/arflix/tv/data/model/MediaType;IFLjava/lang/Integer;Ljava/lang/Integer;ZLd7/d;)Ljava/lang/Object;", "scrobblePause", "scrobbleProgress", "scrobbleStop", "", "getWatchedMovies", "", "getWatchedEpisodes", "forceRefresh", "", "Lcom/arflix/tv/data/repository/ContinueWatchingItem;", "getContinueWatching", "(ZLd7/d;)Ljava/lang/Object;", "dismissContinueWatching", "(Lcom/arflix/tv/data/model/MediaType;ILjava/lang/Integer;Ljava/lang/Integer;Ld7/d;)Ljava/lang/Object;", "provider", "providerFor", "(Lcom/arflix/tv/data/repository/sync/SyncProvider;)Lcom/arflix/tv/data/repository/sync/RemoteSyncProvider;", "providers", "connected", "(Ljava/util/Set;Ld7/d;)Ljava/lang/Object;", "Lkotlin/Function2;", "Ld7/d;", "block", "writeResults", "(Lr7/p;Ld7/d;)Ljava/lang/Object;", "writeAll", "T", "readSets", "Lcom/arflix/tv/data/repository/sync/SyncProviderStore;", "Lcom/arflix/tv/data/repository/sync/TraktRemoteProvider;", "Lcom/arflix/tv/data/repository/sync/MdbListRemoteProvider;", "Lcom/arflix/tv/data/repository/sync/SimklRemoteProvider;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class RemoteSyncManager {
    public static final int $stable = 8;
    private final MdbListRemoteProvider mdbListProvider;
    private final SimklRemoteProvider simklProvider;
    private final SyncProviderStore store;
    private final TraktRemoteProvider traktProvider;

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SyncProvider.values().length];
            try {
                iArr[SyncProvider.TRAKT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[SyncProvider.MDBLIST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[SyncProvider.SIMKL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[SyncProvider.NONE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.RemoteSyncManager$active$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.data.repository.sync.RemoteSyncManager", f = "RemoteSyncManager.kt", l = {47, 48, 49}, m = "active", v = 2)
    public static final class AnonymousClass1 extends c {
        int I$0;
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
            return RemoteSyncManager.this.active(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.RemoteSyncManager$addToWatchlist$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/arflix/tv/data/repository/sync/RemoteSyncProvider;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.data.repository.sync.RemoteSyncManager$addToWatchlist$2", f = "RemoteSyncManager.kt", l = {MdtaMetadataEntry.TYPE_INDICATOR_INT32}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends j implements p<RemoteSyncProvider, d<? super Boolean>, Object> {
        final /* synthetic */ boolean $isAnime;
        final /* synthetic */ MediaType $mediaType;
        final /* synthetic */ int $tmdbId;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(MediaType mediaType, int i10, boolean z, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$mediaType = mediaType;
            this.$tmdbId = i10;
            this.$isAnime = z;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$mediaType, this.$tmdbId, this.$isAnime, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // r7.p
        public final Object invoke(RemoteSyncProvider remoteSyncProvider, d<? super Boolean> dVar) {
            return ((AnonymousClass2) create(remoteSyncProvider, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            RemoteSyncProvider remoteSyncProvider = (RemoteSyncProvider) this.L$0;
            int i10 = this.label;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return obj;
            }
            k2.c.G(obj);
            MediaType mediaType = this.$mediaType;
            int i11 = this.$tmdbId;
            boolean z = this.$isAnime;
            this.L$0 = null;
            this.label = 1;
            Object objAddToWatchlist = remoteSyncProvider.addToWatchlist(mediaType, i11, z, this);
            e7.a aVar = e7.a.f15033i;
            return objAddToWatchlist == aVar ? aVar : objAddToWatchlist;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.RemoteSyncManager$connected$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.data.repository.sync.RemoteSyncManager", f = "RemoteSyncManager.kt", l = {37}, m = "connected", v = 2)
    public static final class C12641 extends c {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        public C12641(d<? super C12641> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RemoteSyncManager.this.connected(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.RemoteSyncManager$dismissContinueWatching$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/arflix/tv/data/repository/sync/RemoteSyncProvider;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.data.repository.sync.RemoteSyncManager$dismissContinueWatching$2", f = "RemoteSyncManager.kt", l = {223}, m = "invokeSuspend", v = 2)
    public static final class C12652 extends j implements p<RemoteSyncProvider, d<? super Boolean>, Object> {
        final /* synthetic */ Integer $episode;
        final /* synthetic */ MediaType $mediaType;
        final /* synthetic */ Integer $season;
        final /* synthetic */ int $tmdbId;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C12652(MediaType mediaType, int i10, Integer num, Integer num2, d<? super C12652> dVar) {
            super(2, dVar);
            this.$mediaType = mediaType;
            this.$tmdbId = i10;
            this.$season = num;
            this.$episode = num2;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C12652 c12652 = new C12652(this.$mediaType, this.$tmdbId, this.$season, this.$episode, dVar);
            c12652.L$0 = obj;
            return c12652;
        }

        @Override // r7.p
        public final Object invoke(RemoteSyncProvider remoteSyncProvider, d<? super Boolean> dVar) {
            return ((C12652) create(remoteSyncProvider, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            RemoteSyncProvider remoteSyncProvider = (RemoteSyncProvider) this.L$0;
            int i10 = this.label;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return obj;
            }
            k2.c.G(obj);
            MediaType mediaType = this.$mediaType;
            int i11 = this.$tmdbId;
            Integer num = this.$season;
            Integer num2 = this.$episode;
            this.L$0 = null;
            this.label = 1;
            Object objDismissContinueWatching = remoteSyncProvider.dismissContinueWatching(mediaType, i11, num, num2, this);
            e7.a aVar = e7.a.f15033i;
            return objDismissContinueWatching == aVar ? aVar : objDismissContinueWatching;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.RemoteSyncManager$getContinueWatching$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/repository/ContinueWatchingItem;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.data.repository.sync.RemoteSyncManager$getContinueWatching$2", f = "RemoteSyncManager.kt", l = {195, 195, 205}, m = "invokeSuspend", v = 2)
    public static final class C12662 extends j implements p<k0, d<? super List<? extends ContinueWatchingItem>>, Object> {
        final /* synthetic */ boolean $forceRefresh;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C12662(boolean z, d<? super C12662> dVar) {
            super(2, dVar);
            this.$forceRefresh = z;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C12662 c12662 = RemoteSyncManager.this.new C12662(this.$forceRefresh, dVar);
            c12662.L$0 = obj;
            return c12662;
        }

        /* JADX WARN: Code restructure failed: missing block: B:23:0x0088, code lost:
        
            if (r9 != r6) goto L25;
         */
        /* JADX WARN: Removed duplicated region for block: B:21:0x006d A[LOOP:2: B:19:0x0067->B:21:0x006d, LOOP_END] */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                Method dump skipped, instruction units count: 284
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.sync.RemoteSyncManager.C12662.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super List<ContinueWatchingItem>> dVar) {
            return ((C12662) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.RemoteSyncManager$getWatchedEpisodes$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "", "it", "Lcom/arflix/tv/data/repository/sync/RemoteSyncProvider;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.data.repository.sync.RemoteSyncManager$getWatchedEpisodes$2", f = "RemoteSyncManager.kt", l = {176}, m = "invokeSuspend", v = 2)
    public static final class C12672 extends j implements p<RemoteSyncProvider, d<? super Set<? extends String>>, Object> {
        /* synthetic */ Object L$0;
        int label;

        public C12672(d<? super C12672> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C12672 c12672 = new C12672(dVar);
            c12672.L$0 = obj;
            return c12672;
        }

        @Override // r7.p
        public final Object invoke(RemoteSyncProvider remoteSyncProvider, d<? super Set<String>> dVar) {
            return ((C12672) create(remoteSyncProvider, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            RemoteSyncProvider remoteSyncProvider = (RemoteSyncProvider) this.L$0;
            int i10 = this.label;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return obj;
            }
            k2.c.G(obj);
            this.L$0 = null;
            this.label = 1;
            Object watchedEpisodes = remoteSyncProvider.getWatchedEpisodes(this);
            e7.a aVar = e7.a.f15033i;
            return watchedEpisodes == aVar ? aVar : watchedEpisodes;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.RemoteSyncManager$getWatchedMovies$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\"\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "", "it", "Lcom/arflix/tv/data/repository/sync/RemoteSyncProvider;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.data.repository.sync.RemoteSyncManager$getWatchedMovies$2", f = "RemoteSyncManager.kt", l = {174}, m = "invokeSuspend", v = 2)
    public static final class C12682 extends j implements p<RemoteSyncProvider, d<? super Set<? extends Integer>>, Object> {
        /* synthetic */ Object L$0;
        int label;

        public C12682(d<? super C12682> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C12682 c12682 = new C12682(dVar);
            c12682.L$0 = obj;
            return c12682;
        }

        @Override // r7.p
        public final Object invoke(RemoteSyncProvider remoteSyncProvider, d<? super Set<Integer>> dVar) {
            return ((C12682) create(remoteSyncProvider, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            RemoteSyncProvider remoteSyncProvider = (RemoteSyncProvider) this.L$0;
            int i10 = this.label;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return obj;
            }
            k2.c.G(obj);
            this.L$0 = null;
            this.label = 1;
            Object watchedMovies = remoteSyncProvider.getWatchedMovies(this);
            e7.a aVar = e7.a.f15033i;
            return watchedMovies == aVar ? aVar : watchedMovies;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.RemoteSyncManager$getWatchlist$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/repository/sync/RemoteWatchlistResult;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/repository/sync/RemoteWatchlistResult;"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.data.repository.sync.RemoteSyncManager$getWatchlist$2", f = "RemoteSyncManager.kt", l = {73, 73, 85}, m = "invokeSuspend", v = 2)
    public static final class C12692 extends j implements p<k0, d<? super RemoteWatchlistResult>, Object> {
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        public C12692(d<? super C12692> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C12692 c12692 = RemoteSyncManager.this.new C12692(dVar);
            c12692.L$0 = obj;
            return c12692;
        }

        /* JADX WARN: Code restructure failed: missing block: B:26:0x0093, code lost:
        
            if (r10 == r6) goto L27;
         */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0060 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0061  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) {
            /*
                Method dump skipped, instruction units count: 315
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.sync.RemoteSyncManager.C12692.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super RemoteWatchlistResult> dVar) {
            return ((C12692) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.RemoteSyncManager$isRemoteConnected$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.data.repository.sync.RemoteSyncManager", f = "RemoteSyncManager.kt", l = {55, 57, 61}, m = "isRemoteConnected", v = 2)
    public static final class C12701 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C12701(d<? super C12701> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RemoteSyncManager.this.isRemoteConnected(null, this);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.RemoteSyncManager$readSets$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"T", "Lka/k0;", "", "<anonymous>", "(Lka/k0;)Ljava/util/Set;"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.data.repository.sync.RemoteSyncManager$readSets$2", f = "RemoteSyncManager.kt", l = {179, 179, PsExtractor.PRIVATE_STREAM_1}, m = "invokeSuspend", v = 2)
    public static final class C12712<T> extends j implements p<k0, d<? super Set<? extends T>>, Object> {
        final /* synthetic */ p<RemoteSyncProvider, d<? super Set<? extends T>>, Object> $block;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C12712(p<? super RemoteSyncProvider, ? super d<? super Set<? extends T>>, ? extends Object> pVar, d<? super C12712> dVar) {
            super(2, dVar);
            this.$block = pVar;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C12712 c12712 = RemoteSyncManager.this.new C12712(this.$block, dVar);
            c12712.L$0 = obj;
            return c12712;
        }

        /* JADX WARN: Code restructure failed: missing block: B:23:0x0088, code lost:
        
            if (r9 != r6) goto L25;
         */
        /* JADX WARN: Removed duplicated region for block: B:21:0x006d A[LOOP:0: B:19:0x0067->B:21:0x006d, LOOP_END] */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                r8 = this;
                java.lang.Object r0 = r8.L$0
                ka.k0 r0 = (ka.k0) r0
                int r1 = r8.label
                r2 = 3
                r3 = 2
                r4 = 1
                r5 = 0
                e7.a r6 = e7.a.f15033i
                if (r1 == 0) goto L2d
                if (r1 == r4) goto L25
                if (r1 == r3) goto L21
                if (r1 != r2) goto L19
                k2.c.G(r9)
                goto L8b
            L19:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L21:
                k2.c.G(r9)
                goto L54
            L25:
                java.lang.Object r1 = r8.L$1
                com.arflix.tv.data.repository.sync.RemoteSyncManager r1 = (com.arflix.tv.data.repository.sync.RemoteSyncManager) r1
                k2.c.G(r9)
                goto L45
            L2d:
                k2.c.G(r9)
                com.arflix.tv.data.repository.sync.RemoteSyncManager r1 = com.arflix.tv.data.repository.sync.RemoteSyncManager.this
                com.arflix.tv.data.repository.sync.SyncProviderStore r9 = com.arflix.tv.data.repository.sync.RemoteSyncManager.access$getStore$p(r1)
                com.arflix.tv.data.repository.sync.TrackingFeature r7 = com.arflix.tv.data.repository.sync.TrackingFeature.WATCHED
                r8.L$0 = r0
                r8.L$1 = r1
                r8.label = r4
                java.lang.Object r9 = r9.readProviders(r7, r8)
                if (r9 != r6) goto L45
                goto L8a
            L45:
                java.util.Set r9 = (java.util.Set) r9
                r8.L$0 = r0
                r8.L$1 = r5
                r8.label = r3
                java.lang.Object r9 = com.arflix.tv.data.repository.sync.RemoteSyncManager.access$connected(r1, r9, r8)
                if (r9 != r6) goto L54
                goto L8a
            L54:
                java.lang.Iterable r9 = (java.lang.Iterable) r9
                r7.p<com.arflix.tv.data.repository.sync.RemoteSyncProvider, d7.d<? super java.util.Set<? extends T>>, java.lang.Object> r1 = r8.$block
                java.util.ArrayList r3 = new java.util.ArrayList
                r4 = 10
                int r4 = kotlin.collections.s.U(r9, r4)
                r3.<init>(r4)
                java.util.Iterator r9 = r9.iterator()
            L67:
                boolean r4 = r9.hasNext()
                if (r4 == 0) goto L80
                java.lang.Object r4 = r9.next()
                com.arflix.tv.data.repository.sync.RemoteSyncProvider r4 = (com.arflix.tv.data.repository.sync.RemoteSyncProvider) r4
                com.arflix.tv.data.repository.sync.RemoteSyncManager$readSets$2$1$1 r7 = new com.arflix.tv.data.repository.sync.RemoteSyncManager$readSets$2$1$1
                r7.<init>(r1, r4, r5)
                ka.q0 r4 = ka.m0.e(r2, r5, r0, r7)
                r3.add(r4)
                goto L67
            L80:
                r8.L$0 = r5
                r8.label = r2
                java.lang.Object r9 = ka.m0.f(r3, r8)
                if (r9 != r6) goto L8b
            L8a:
                return r6
            L8b:
                java.lang.Iterable r9 = (java.lang.Iterable) r9
                java.util.ArrayList r9 = kotlin.collections.s.V(r9)
                java.util.Set r9 = kotlin.collections.x.g1(r9)
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.sync.RemoteSyncManager.C12712.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super Set<? extends T>> dVar) {
            return ((C12712) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.RemoteSyncManager$removeFromWatchlist$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/arflix/tv/data/repository/sync/RemoteSyncProvider;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.data.repository.sync.RemoteSyncManager$removeFromWatchlist$2", f = "RemoteSyncManager.kt", l = {70}, m = "invokeSuspend", v = 2)
    public static final class C12722 extends j implements p<RemoteSyncProvider, d<? super Boolean>, Object> {
        final /* synthetic */ boolean $isAnime;
        final /* synthetic */ MediaType $mediaType;
        final /* synthetic */ int $tmdbId;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C12722(MediaType mediaType, int i10, boolean z, d<? super C12722> dVar) {
            super(2, dVar);
            this.$mediaType = mediaType;
            this.$tmdbId = i10;
            this.$isAnime = z;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C12722 c12722 = new C12722(this.$mediaType, this.$tmdbId, this.$isAnime, dVar);
            c12722.L$0 = obj;
            return c12722;
        }

        @Override // r7.p
        public final Object invoke(RemoteSyncProvider remoteSyncProvider, d<? super Boolean> dVar) {
            return ((C12722) create(remoteSyncProvider, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            RemoteSyncProvider remoteSyncProvider = (RemoteSyncProvider) this.L$0;
            int i10 = this.label;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return obj;
            }
            k2.c.G(obj);
            MediaType mediaType = this.$mediaType;
            int i11 = this.$tmdbId;
            boolean z = this.$isAnime;
            this.L$0 = null;
            this.label = 1;
            Object objRemoveFromWatchlist = remoteSyncProvider.removeFromWatchlist(mediaType, i11, z, this);
            e7.a aVar = e7.a.f15033i;
            return objRemoveFromWatchlist == aVar ? aVar : objRemoveFromWatchlist;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.RemoteSyncManager$scrobblePause$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/arflix/tv/data/repository/sync/RemoteSyncProvider;", "it", "Lx6/t0;", "<anonymous>", "(Lcom/arflix/tv/data/repository/sync/RemoteSyncProvider;)V"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.data.repository.sync.RemoteSyncManager$scrobblePause$2", f = "RemoteSyncManager.kt", l = {147}, m = "invokeSuspend", v = 2)
    public static final class C12732 extends j implements p<RemoteSyncProvider, d<? super t0>, Object> {
        final /* synthetic */ Integer $episode;
        final /* synthetic */ boolean $isAnime;
        final /* synthetic */ MediaType $mediaType;
        final /* synthetic */ float $progress;
        final /* synthetic */ Integer $season;
        final /* synthetic */ int $tmdbId;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C12732(MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d<? super C12732> dVar) {
            super(2, dVar);
            this.$mediaType = mediaType;
            this.$tmdbId = i10;
            this.$progress = f10;
            this.$season = num;
            this.$episode = num2;
            this.$isAnime = z;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C12732 c12732 = new C12732(this.$mediaType, this.$tmdbId, this.$progress, this.$season, this.$episode, this.$isAnime, dVar);
            c12732.L$0 = obj;
            return c12732;
        }

        @Override // r7.p
        public final Object invoke(RemoteSyncProvider remoteSyncProvider, d<? super t0> dVar) {
            return ((C12732) create(remoteSyncProvider, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            RemoteSyncProvider remoteSyncProvider = (RemoteSyncProvider) this.L$0;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                MediaType mediaType = this.$mediaType;
                int i11 = this.$tmdbId;
                float f10 = this.$progress;
                Integer num = this.$season;
                Integer num2 = this.$episode;
                boolean z = this.$isAnime;
                this.L$0 = null;
                this.label = 1;
                Object objScrobblePause = remoteSyncProvider.scrobblePause(mediaType, i11, f10, num, num2, z, this);
                e7.a aVar = e7.a.f15033i;
                if (objScrobblePause == aVar) {
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
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.RemoteSyncManager$scrobbleProgress$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/arflix/tv/data/repository/sync/RemoteSyncProvider;", "it", "Lx6/t0;", "<anonymous>", "(Lcom/arflix/tv/data/repository/sync/RemoteSyncProvider;)V"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.data.repository.sync.RemoteSyncManager$scrobbleProgress$2", f = "RemoteSyncManager.kt", l = {158}, m = "invokeSuspend", v = 2)
    public static final class C12742 extends j implements p<RemoteSyncProvider, d<? super t0>, Object> {
        final /* synthetic */ Integer $episode;
        final /* synthetic */ boolean $isAnime;
        final /* synthetic */ MediaType $mediaType;
        final /* synthetic */ float $progress;
        final /* synthetic */ Integer $season;
        final /* synthetic */ int $tmdbId;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C12742(MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d<? super C12742> dVar) {
            super(2, dVar);
            this.$mediaType = mediaType;
            this.$tmdbId = i10;
            this.$progress = f10;
            this.$season = num;
            this.$episode = num2;
            this.$isAnime = z;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C12742 c12742 = new C12742(this.$mediaType, this.$tmdbId, this.$progress, this.$season, this.$episode, this.$isAnime, dVar);
            c12742.L$0 = obj;
            return c12742;
        }

        @Override // r7.p
        public final Object invoke(RemoteSyncProvider remoteSyncProvider, d<? super t0> dVar) {
            return ((C12742) create(remoteSyncProvider, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            RemoteSyncProvider remoteSyncProvider = (RemoteSyncProvider) this.L$0;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                MediaType mediaType = this.$mediaType;
                int i11 = this.$tmdbId;
                float f10 = this.$progress;
                Integer num = this.$season;
                Integer num2 = this.$episode;
                boolean z = this.$isAnime;
                this.L$0 = null;
                this.label = 1;
                Object objScrobbleProgress = remoteSyncProvider.scrobbleProgress(mediaType, i11, f10, num, num2, z, this);
                e7.a aVar = e7.a.f15033i;
                if (objScrobbleProgress == aVar) {
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
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.RemoteSyncManager$scrobbleStart$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/arflix/tv/data/repository/sync/RemoteSyncProvider;", "it", "Lx6/t0;", "<anonymous>", "(Lcom/arflix/tv/data/repository/sync/RemoteSyncProvider;)V"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.data.repository.sync.RemoteSyncManager$scrobbleStart$2", f = "RemoteSyncManager.kt", l = {TsExtractor.TS_STREAM_TYPE_DTS_HD}, m = "invokeSuspend", v = 2)
    public static final class C12752 extends j implements p<RemoteSyncProvider, d<? super t0>, Object> {
        final /* synthetic */ Integer $episode;
        final /* synthetic */ boolean $isAnime;
        final /* synthetic */ MediaType $mediaType;
        final /* synthetic */ float $progress;
        final /* synthetic */ Integer $season;
        final /* synthetic */ int $tmdbId;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C12752(MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d<? super C12752> dVar) {
            super(2, dVar);
            this.$mediaType = mediaType;
            this.$tmdbId = i10;
            this.$progress = f10;
            this.$season = num;
            this.$episode = num2;
            this.$isAnime = z;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C12752 c12752 = new C12752(this.$mediaType, this.$tmdbId, this.$progress, this.$season, this.$episode, this.$isAnime, dVar);
            c12752.L$0 = obj;
            return c12752;
        }

        @Override // r7.p
        public final Object invoke(RemoteSyncProvider remoteSyncProvider, d<? super t0> dVar) {
            return ((C12752) create(remoteSyncProvider, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            RemoteSyncProvider remoteSyncProvider = (RemoteSyncProvider) this.L$0;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                MediaType mediaType = this.$mediaType;
                int i11 = this.$tmdbId;
                float f10 = this.$progress;
                Integer num = this.$season;
                Integer num2 = this.$episode;
                boolean z = this.$isAnime;
                this.L$0 = null;
                this.label = 1;
                Object objScrobbleStart = remoteSyncProvider.scrobbleStart(mediaType, i11, f10, num, num2, z, this);
                e7.a aVar = e7.a.f15033i;
                if (objScrobbleStart == aVar) {
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
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.RemoteSyncManager$scrobbleStop$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/arflix/tv/data/repository/sync/RemoteSyncProvider;", "it", "Lx6/t0;", "<anonymous>", "(Lcom/arflix/tv/data/repository/sync/RemoteSyncProvider;)V"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.data.repository.sync.RemoteSyncManager$scrobbleStop$2", f = "RemoteSyncManager.kt", l = {169}, m = "invokeSuspend", v = 2)
    public static final class C12762 extends j implements p<RemoteSyncProvider, d<? super t0>, Object> {
        final /* synthetic */ Integer $episode;
        final /* synthetic */ boolean $isAnime;
        final /* synthetic */ MediaType $mediaType;
        final /* synthetic */ float $progress;
        final /* synthetic */ Integer $season;
        final /* synthetic */ int $tmdbId;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C12762(MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d<? super C12762> dVar) {
            super(2, dVar);
            this.$mediaType = mediaType;
            this.$tmdbId = i10;
            this.$progress = f10;
            this.$season = num;
            this.$episode = num2;
            this.$isAnime = z;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C12762 c12762 = new C12762(this.$mediaType, this.$tmdbId, this.$progress, this.$season, this.$episode, this.$isAnime, dVar);
            c12762.L$0 = obj;
            return c12762;
        }

        @Override // r7.p
        public final Object invoke(RemoteSyncProvider remoteSyncProvider, d<? super t0> dVar) {
            return ((C12762) create(remoteSyncProvider, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            RemoteSyncProvider remoteSyncProvider = (RemoteSyncProvider) this.L$0;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                MediaType mediaType = this.$mediaType;
                int i11 = this.$tmdbId;
                float f10 = this.$progress;
                Integer num = this.$season;
                Integer num2 = this.$episode;
                boolean z = this.$isAnime;
                this.L$0 = null;
                this.label = 1;
                Object objScrobbleStop = remoteSyncProvider.scrobbleStop(mediaType, i11, f10, num, num2, z, this);
                e7.a aVar = e7.a.f15033i;
                if (objScrobbleStop == aVar) {
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
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.RemoteSyncManager$writeAll$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.data.repository.sync.RemoteSyncManager$writeAll$2", f = "RemoteSyncManager.kt", l = {112, 112, 122}, m = "invokeSuspend", v = 2)
    public static final class C12772 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ p<RemoteSyncProvider, d<? super t0>, Object> $block;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C12772(p<? super RemoteSyncProvider, ? super d<? super t0>, ? extends Object> pVar, d<? super C12772> dVar) {
            super(2, dVar);
            this.$block = pVar;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C12772 c12772 = RemoteSyncManager.this.new C12772(this.$block, dVar);
            c12772.L$0 = obj;
            return c12772;
        }

        /* JADX WARN: Code restructure failed: missing block: B:23:0x0086, code lost:
        
            if (ka.m0.f(r3, r8) != r6) goto L25;
         */
        /* JADX WARN: Removed duplicated region for block: B:21:0x006b A[LOOP:0: B:19:0x0065->B:21:0x006b, LOOP_END] */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                r8 = this;
                java.lang.Object r0 = r8.L$0
                ka.k0 r0 = (ka.k0) r0
                int r1 = r8.label
                r2 = 3
                r3 = 2
                r4 = 1
                r5 = 0
                e7.a r6 = e7.a.f15033i
                if (r1 == 0) goto L2d
                if (r1 == r4) goto L25
                if (r1 == r3) goto L21
                if (r1 != r2) goto L19
                k2.c.G(r9)
                goto L89
            L19:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L21:
                k2.c.G(r9)
                goto L52
            L25:
                java.lang.Object r1 = r8.L$1
                com.arflix.tv.data.repository.sync.RemoteSyncManager r1 = (com.arflix.tv.data.repository.sync.RemoteSyncManager) r1
                k2.c.G(r9)
                goto L43
            L2d:
                k2.c.G(r9)
                com.arflix.tv.data.repository.sync.RemoteSyncManager r1 = com.arflix.tv.data.repository.sync.RemoteSyncManager.this
                com.arflix.tv.data.repository.sync.SyncProviderStore r9 = com.arflix.tv.data.repository.sync.RemoteSyncManager.access$getStore$p(r1)
                r8.L$0 = r0
                r8.L$1 = r1
                r8.label = r4
                java.lang.Object r9 = r9.writeProviders(r8)
                if (r9 != r6) goto L43
                goto L88
            L43:
                java.util.Set r9 = (java.util.Set) r9
                r8.L$0 = r0
                r8.L$1 = r5
                r8.label = r3
                java.lang.Object r9 = com.arflix.tv.data.repository.sync.RemoteSyncManager.access$connected(r1, r9, r8)
                if (r9 != r6) goto L52
                goto L88
            L52:
                java.lang.Iterable r9 = (java.lang.Iterable) r9
                r7.p<com.arflix.tv.data.repository.sync.RemoteSyncProvider, d7.d<? super x6.t0>, java.lang.Object> r1 = r8.$block
                java.util.ArrayList r3 = new java.util.ArrayList
                r4 = 10
                int r4 = kotlin.collections.s.U(r9, r4)
                r3.<init>(r4)
                java.util.Iterator r9 = r9.iterator()
            L65:
                boolean r4 = r9.hasNext()
                if (r4 == 0) goto L7e
                java.lang.Object r4 = r9.next()
                com.arflix.tv.data.repository.sync.RemoteSyncProvider r4 = (com.arflix.tv.data.repository.sync.RemoteSyncProvider) r4
                com.arflix.tv.data.repository.sync.RemoteSyncManager$writeAll$2$1$1 r7 = new com.arflix.tv.data.repository.sync.RemoteSyncManager$writeAll$2$1$1
                r7.<init>(r1, r4, r5)
                ka.q0 r4 = ka.m0.e(r2, r5, r0, r7)
                r3.add(r4)
                goto L65
            L7e:
                r8.L$0 = r5
                r8.label = r2
                java.lang.Object r9 = ka.m0.f(r3, r8)
                if (r9 != r6) goto L89
            L88:
                return r6
            L89:
                x6.t0 r9 = x6.t0.f22605a
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.sync.RemoteSyncManager.C12772.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((C12772) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.RemoteSyncManager$writeResults$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Z"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.data.repository.sync.RemoteSyncManager$writeResults$2", f = "RemoteSyncManager.kt", l = {97, 97, 108}, m = "invokeSuspend", v = 2)
    public static final class C12782 extends j implements p<k0, d<? super Boolean>, Object> {
        final /* synthetic */ p<RemoteSyncProvider, d<? super Boolean>, Object> $block;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C12782(p<? super RemoteSyncProvider, ? super d<? super Boolean>, ? extends Object> pVar, d<? super C12782> dVar) {
            super(2, dVar);
            this.$block = pVar;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C12782 c12782 = RemoteSyncManager.this.new C12782(this.$block, dVar);
            c12782.L$0 = obj;
            return c12782;
        }

        /* JADX WARN: Code restructure failed: missing block: B:23:0x008c, code lost:
        
            if (r10 != r6) goto L25;
         */
        /* JADX WARN: Removed duplicated region for block: B:21:0x006f A[LOOP:1: B:19:0x0069->B:21:0x006f, LOOP_END] */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) {
            /*
                r9 = this;
                java.lang.Object r0 = r9.L$0
                ka.k0 r0 = (ka.k0) r0
                int r1 = r9.label
                r2 = 3
                r3 = 2
                r4 = 1
                r5 = 0
                e7.a r6 = e7.a.f15033i
                if (r1 == 0) goto L31
                if (r1 == r4) goto L29
                if (r1 == r3) goto L25
                if (r1 != r2) goto L1d
                java.lang.Object r0 = r9.L$1
                java.util.List r0 = (java.util.List) r0
                k2.c.G(r10)
                goto L8f
            L1d:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r0)
                throw r10
            L25:
                k2.c.G(r10)
                goto L56
            L29:
                java.lang.Object r1 = r9.L$1
                com.arflix.tv.data.repository.sync.RemoteSyncManager r1 = (com.arflix.tv.data.repository.sync.RemoteSyncManager) r1
                k2.c.G(r10)
                goto L47
            L31:
                k2.c.G(r10)
                com.arflix.tv.data.repository.sync.RemoteSyncManager r1 = com.arflix.tv.data.repository.sync.RemoteSyncManager.this
                com.arflix.tv.data.repository.sync.SyncProviderStore r10 = com.arflix.tv.data.repository.sync.RemoteSyncManager.access$getStore$p(r1)
                r9.L$0 = r0
                r9.L$1 = r1
                r9.label = r4
                java.lang.Object r10 = r10.writeProviders(r9)
                if (r10 != r6) goto L47
                goto L8e
            L47:
                java.util.Set r10 = (java.util.Set) r10
                r9.L$0 = r0
                r9.L$1 = r5
                r9.label = r3
                java.lang.Object r10 = com.arflix.tv.data.repository.sync.RemoteSyncManager.access$connected(r1, r10, r9)
                if (r10 != r6) goto L56
                goto L8e
            L56:
                java.util.List r10 = (java.util.List) r10
                r7.p<com.arflix.tv.data.repository.sync.RemoteSyncProvider, d7.d<? super java.lang.Boolean>, java.lang.Object> r1 = r9.$block
                java.util.ArrayList r3 = new java.util.ArrayList
                r7 = 10
                int r7 = kotlin.collections.s.U(r10, r7)
                r3.<init>(r7)
                java.util.Iterator r10 = r10.iterator()
            L69:
                boolean r7 = r10.hasNext()
                if (r7 == 0) goto L82
                java.lang.Object r7 = r10.next()
                com.arflix.tv.data.repository.sync.RemoteSyncProvider r7 = (com.arflix.tv.data.repository.sync.RemoteSyncProvider) r7
                com.arflix.tv.data.repository.sync.RemoteSyncManager$writeResults$2$1$1 r8 = new com.arflix.tv.data.repository.sync.RemoteSyncManager$writeResults$2$1$1
                r8.<init>(r1, r7, r5)
                ka.q0 r7 = ka.m0.e(r2, r5, r0, r8)
                r3.add(r7)
                goto L69
            L82:
                r9.L$0 = r5
                r9.L$1 = r5
                r9.label = r2
                java.lang.Object r10 = ka.m0.f(r3, r9)
                if (r10 != r6) goto L8f
            L8e:
                return r6
            L8f:
                java.lang.Iterable r10 = (java.lang.Iterable) r10
                boolean r0 = r10 instanceof java.util.Collection
                r1 = 0
                if (r0 == 0) goto La1
                r0 = r10
                java.util.Collection r0 = (java.util.Collection) r0
                boolean r0 = r0.isEmpty()
                if (r0 == 0) goto La1
            L9f:
                r4 = r1
                goto Lb7
            La1:
                java.util.Iterator r10 = r10.iterator()
            La5:
                boolean r0 = r10.hasNext()
                if (r0 == 0) goto L9f
                java.lang.Object r0 = r10.next()
                java.lang.Boolean r0 = (java.lang.Boolean) r0
                boolean r0 = r0.booleanValue()
                if (r0 == 0) goto La5
            Lb7:
                java.lang.Boolean r10 = java.lang.Boolean.valueOf(r4)
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.sync.RemoteSyncManager.C12782.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super Boolean> dVar) {
            return ((C12782) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    @Inject
    public RemoteSyncManager(SyncProviderStore syncProviderStore, TraktRemoteProvider traktRemoteProvider, MdbListRemoteProvider mdbListRemoteProvider, SimklRemoteProvider simklRemoteProvider) {
        this.store = syncProviderStore;
        this.traktProvider = traktRemoteProvider;
        this.mdbListProvider = mdbListRemoteProvider;
        this.simklProvider = simklRemoteProvider;
    }

    public static /* synthetic */ Object addToWatchlist$default(RemoteSyncManager remoteSyncManager, MediaType mediaType, int i10, boolean z, d dVar, int i11, Object obj) {
        if ((i11 & 4) != 0) {
            z = false;
        }
        return remoteSyncManager.addToWatchlist(mediaType, i10, z, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x00a7 -> B:26:0x00aa). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object connected(java.util.Set<? extends com.arflix.tv.data.repository.sync.SyncProvider> r9, d7.d<? super java.util.List<? extends com.arflix.tv.data.repository.sync.RemoteSyncProvider>> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.arflix.tv.data.repository.sync.RemoteSyncManager.C12641
            if (r0 == 0) goto L13
            r0 = r10
            com.arflix.tv.data.repository.sync.RemoteSyncManager$connected$1 r0 = (com.arflix.tv.data.repository.sync.RemoteSyncManager.C12641) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.sync.RemoteSyncManager$connected$1 r0 = new com.arflix.tv.data.repository.sync.RemoteSyncManager$connected$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            int r1 = r0.label
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L4c
            if (r1 != r2) goto L44
            int r9 = r0.I$1
            int r1 = r0.I$0
            java.lang.Object r4 = r0.L$6
            com.arflix.tv.data.repository.sync.RemoteSyncProvider r4 = (com.arflix.tv.data.repository.sync.RemoteSyncProvider) r4
            java.lang.Object r4 = r0.L$5
            java.lang.Object r5 = r0.L$4
            java.util.Iterator r5 = (java.util.Iterator) r5
            java.lang.Object r6 = r0.L$3
            java.util.Collection r6 = (java.util.Collection) r6
            java.lang.Object r7 = r0.L$2
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.lang.Object r7 = r0.L$1
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.lang.Object r7 = r0.L$0
            java.util.Set r7 = (java.util.Set) r7
            k2.c.G(r10)
            goto Laa
        L44:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L4c:
            k2.c.G(r10)
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.util.Iterator r9 = r9.iterator()
        L5a:
            boolean r1 = r9.hasNext()
            if (r1 == 0) goto L70
            java.lang.Object r1 = r9.next()
            com.arflix.tv.data.repository.sync.SyncProvider r1 = (com.arflix.tv.data.repository.sync.SyncProvider) r1
            com.arflix.tv.data.repository.sync.RemoteSyncProvider r1 = r8.providerFor(r1)
            if (r1 == 0) goto L5a
            r10.add(r1)
            goto L5a
        L70:
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.Iterator r10 = r10.iterator()
            r6 = r9
            r5 = r10
            r9 = r3
            r1 = r9
        L7d:
            boolean r10 = r5.hasNext()
            if (r10 == 0) goto Lb6
            java.lang.Object r4 = r5.next()
            r10 = r4
            com.arflix.tv.data.repository.sync.RemoteSyncProvider r10 = (com.arflix.tv.data.repository.sync.RemoteSyncProvider) r10
            r7 = 0
            r0.L$0 = r7
            r0.L$1 = r7
            r0.L$2 = r7
            r0.L$3 = r6
            r0.L$4 = r5
            r0.L$5 = r4
            r0.L$6 = r7
            r0.I$0 = r1
            r0.I$1 = r9
            r0.I$2 = r3
            r0.label = r2
            java.lang.Object r10 = r10.isConnected(r0)
            e7.a r7 = e7.a.f15033i
            if (r10 != r7) goto Laa
            return r7
        Laa:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L7d
            r6.add(r4)
            goto L7d
        Lb6:
            java.util.List r6 = (java.util.List) r6
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.sync.RemoteSyncManager.connected(java.util.Set, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object dismissContinueWatching$default(RemoteSyncManager remoteSyncManager, MediaType mediaType, int i10, Integer num, Integer num2, d dVar, int i11, Object obj) {
        if ((i11 & 4) != 0) {
            num = null;
        }
        if ((i11 & 8) != 0) {
            num2 = null;
        }
        return remoteSyncManager.dismissContinueWatching(mediaType, i10, num, num2, dVar);
    }

    public static /* synthetic */ Object getContinueWatching$default(RemoteSyncManager remoteSyncManager, boolean z, d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        return remoteSyncManager.getContinueWatching(z, dVar);
    }

    public static /* synthetic */ Object isRemoteConnected$default(RemoteSyncManager remoteSyncManager, TrackingFeature trackingFeature, d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            trackingFeature = TrackingFeature.WATCHLIST;
        }
        return remoteSyncManager.isRemoteConnected(trackingFeature, dVar);
    }

    private final RemoteSyncProvider providerFor(SyncProvider provider) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[provider.ordinal()];
        if (i10 == 1) {
            return this.traktProvider;
        }
        if (i10 == 2) {
            return this.mdbListProvider;
        }
        if (i10 == 3) {
            return this.simklProvider;
        }
        if (i10 == 4) {
            return null;
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <T> Object readSets(p<? super RemoteSyncProvider, ? super d<? super Set<? extends T>>, ? extends Object> pVar, d<? super Set<? extends T>> dVar) {
        return l0.c(new C12712(pVar, null), dVar);
    }

    public static /* synthetic */ Object removeFromWatchlist$default(RemoteSyncManager remoteSyncManager, MediaType mediaType, int i10, boolean z, d dVar, int i11, Object obj) {
        if ((i11 & 4) != 0) {
            z = false;
        }
        return remoteSyncManager.removeFromWatchlist(mediaType, i10, z, dVar);
    }

    public static /* synthetic */ Object scrobblePause$default(RemoteSyncManager remoteSyncManager, MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d dVar, int i11, Object obj) {
        if ((i11 & 8) != 0) {
            num = null;
        }
        if ((i11 & 16) != 0) {
            num2 = null;
        }
        if ((i11 & 32) != 0) {
            z = false;
        }
        return remoteSyncManager.scrobblePause(mediaType, i10, f10, num, num2, z, dVar);
    }

    public static /* synthetic */ Object scrobbleProgress$default(RemoteSyncManager remoteSyncManager, MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d dVar, int i11, Object obj) {
        if ((i11 & 8) != 0) {
            num = null;
        }
        if ((i11 & 16) != 0) {
            num2 = null;
        }
        if ((i11 & 32) != 0) {
            z = false;
        }
        return remoteSyncManager.scrobbleProgress(mediaType, i10, f10, num, num2, z, dVar);
    }

    public static /* synthetic */ Object scrobbleStart$default(RemoteSyncManager remoteSyncManager, MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d dVar, int i11, Object obj) {
        if ((i11 & 8) != 0) {
            num = null;
        }
        if ((i11 & 16) != 0) {
            num2 = null;
        }
        if ((i11 & 32) != 0) {
            z = false;
        }
        return remoteSyncManager.scrobbleStart(mediaType, i10, f10, num, num2, z, dVar);
    }

    public static /* synthetic */ Object scrobbleStop$default(RemoteSyncManager remoteSyncManager, MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d dVar, int i11, Object obj) {
        if ((i11 & 8) != 0) {
            num = null;
        }
        if ((i11 & 16) != 0) {
            num2 = null;
        }
        if ((i11 & 32) != 0) {
            z = false;
        }
        return remoteSyncManager.scrobbleStop(mediaType, i10, f10, num, num2, z, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object writeAll(p<? super RemoteSyncProvider, ? super d<? super t0>, ? extends Object> pVar, d<? super t0> dVar) {
        Object objC = l0.c(new C12772(pVar, null), dVar);
        return objC == e7.a.f15033i ? objC : t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object writeResults(p<? super RemoteSyncProvider, ? super d<? super Boolean>, ? extends Object> pVar, d<? super Boolean> dVar) {
        return l0.c(new C12782(pVar, null), dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0086, code lost:
    
        if (r8 == r6) goto L28;
     */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0093 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object active(d7.d<? super com.arflix.tv.data.repository.sync.RemoteSyncProvider> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.arflix.tv.data.repository.sync.RemoteSyncManager.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            com.arflix.tv.data.repository.sync.RemoteSyncManager$active$1 r0 = (com.arflix.tv.data.repository.sync.RemoteSyncManager.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.sync.RemoteSyncManager$active$1 r0 = new com.arflix.tv.data.repository.sync.RemoteSyncManager$active$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 3
            r3 = 2
            r4 = 1
            r5 = 0
            e7.a r6 = e7.a.f15033i
            if (r1 == 0) goto L4e
            if (r1 == r4) goto L4a
            if (r1 == r3) goto L42
            if (r1 != r2) goto L3a
            java.lang.Object r1 = r0.L$2
            com.arflix.tv.data.repository.sync.TraktRemoteProvider r1 = (com.arflix.tv.data.repository.sync.TraktRemoteProvider) r1
            java.lang.Object r1 = r0.L$1
            com.arflix.tv.data.repository.sync.TraktRemoteProvider r1 = (com.arflix.tv.data.repository.sync.TraktRemoteProvider) r1
            java.lang.Object r0 = r0.L$0
            java.util.Set r0 = (java.util.Set) r0
            k2.c.G(r8)
            goto L89
        L3a:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L42:
            java.lang.Object r1 = r0.L$0
            java.util.Set r1 = (java.util.Set) r1
            k2.c.G(r8)
            goto L6b
        L4a:
            k2.c.G(r8)
            goto L5e
        L4e:
            k2.c.G(r8)
            com.arflix.tv.data.repository.sync.SyncProviderStore r8 = r7.store
            com.arflix.tv.data.repository.sync.TrackingFeature r1 = com.arflix.tv.data.repository.sync.TrackingFeature.WATCHLIST
            r0.label = r4
            java.lang.Object r8 = r8.readProviders(r1, r0)
            if (r8 != r6) goto L5e
            goto L88
        L5e:
            java.util.Set r8 = (java.util.Set) r8
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r8 = r7.connected(r8, r0)
            if (r8 != r6) goto L6b
            goto L88
        L6b:
            java.util.List r8 = (java.util.List) r8
            java.lang.Object r8 = kotlin.collections.x.o0(r8)
            com.arflix.tv.data.repository.sync.RemoteSyncProvider r8 = (com.arflix.tv.data.repository.sync.RemoteSyncProvider) r8
            if (r8 != 0) goto L93
            com.arflix.tv.data.repository.sync.TraktRemoteProvider r1 = r7.traktProvider
            r0.L$0 = r5
            r0.L$1 = r1
            r0.L$2 = r5
            r8 = 0
            r0.I$0 = r8
            r0.label = r2
            java.lang.Object r8 = r1.isConnected(r0)
            if (r8 != r6) goto L89
        L88:
            return r6
        L89:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L92
            return r1
        L92:
            return r5
        L93:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.sync.RemoteSyncManager.active(d7.d):java.lang.Object");
    }

    public final Object addToWatchlist(MediaType mediaType, int i10, boolean z, d<? super Boolean> dVar) {
        return writeResults(new AnonymousClass2(mediaType, i10, z, null), dVar);
    }

    public final Object dismissContinueWatching(MediaType mediaType, int i10, Integer num, Integer num2, d<? super Boolean> dVar) {
        return writeResults(new C12652(mediaType, i10, num, num2, null), dVar);
    }

    public final Object getContinueWatching(boolean z, d<? super List<ContinueWatchingItem>> dVar) {
        return l0.c(new C12662(z, null), dVar);
    }

    public final Object getWatchedEpisodes(d<? super Set<String>> dVar) {
        return readSets(new C12672(null), dVar);
    }

    public final Object getWatchedMovies(d<? super Set<Integer>> dVar) {
        return readSets(new C12682(null), dVar);
    }

    public final Object getWatchlist(d<? super RemoteWatchlistResult> dVar) {
        return l0.c(new C12692(null), dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0096, code lost:
    
        if (r10 == r6) goto L30;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object isRemoteConnected(com.arflix.tv.data.repository.sync.TrackingFeature r9, d7.d<? super java.lang.Boolean> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.arflix.tv.data.repository.sync.RemoteSyncManager.C12701
            if (r0 == 0) goto L13
            r0 = r10
            com.arflix.tv.data.repository.sync.RemoteSyncManager$isRemoteConnected$1 r0 = (com.arflix.tv.data.repository.sync.RemoteSyncManager.C12701) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.sync.RemoteSyncManager$isRemoteConnected$1 r0 = new com.arflix.tv.data.repository.sync.RemoteSyncManager$isRemoteConnected$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            int r1 = r0.label
            r2 = 3
            r3 = 2
            r4 = 1
            r5 = 0
            e7.a r6 = e7.a.f15033i
            if (r1 == 0) goto L5a
            if (r1 == r4) goto L52
            if (r1 == r3) goto L42
            if (r1 != r2) goto L3a
            java.lang.Object r9 = r0.L$2
            java.util.Set r9 = (java.util.Set) r9
            java.lang.Object r9 = r0.L$1
            java.util.Set r9 = (java.util.Set) r9
            java.lang.Object r9 = r0.L$0
            com.arflix.tv.data.repository.sync.TrackingFeature r9 = (com.arflix.tv.data.repository.sync.TrackingFeature) r9
            k2.c.G(r10)
            goto L99
        L3a:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L42:
            java.lang.Object r9 = r0.L$2
            java.util.Set r9 = (java.util.Set) r9
            java.lang.Object r1 = r0.L$1
            java.util.Set r1 = (java.util.Set) r1
            java.lang.Object r1 = r0.L$0
            com.arflix.tv.data.repository.sync.TrackingFeature r1 = (com.arflix.tv.data.repository.sync.TrackingFeature) r1
            k2.c.G(r10)
            goto L84
        L52:
            java.lang.Object r9 = r0.L$0
            com.arflix.tv.data.repository.sync.TrackingFeature r9 = (com.arflix.tv.data.repository.sync.TrackingFeature) r9
            k2.c.G(r10)
            goto L6a
        L5a:
            k2.c.G(r10)
            com.arflix.tv.data.repository.sync.SyncProviderStore r10 = r8.store
            r0.L$0 = r9
            r0.label = r4
            java.lang.Object r10 = r10.readProviders(r9, r0)
            if (r10 != r6) goto L6a
            goto L98
        L6a:
            java.util.Set r10 = (java.util.Set) r10
            com.arflix.tv.data.repository.sync.TrackingFeature r1 = com.arflix.tv.data.repository.sync.TrackingFeature.WATCHLIST
            if (r9 != r1) goto L8a
            com.arflix.tv.data.repository.sync.SyncProviderStore r9 = r8.store
            r0.L$0 = r5
            r0.L$1 = r5
            r0.L$2 = r10
            r0.label = r3
            java.lang.Object r9 = r9.writeProviders(r0)
            if (r9 != r6) goto L81
            goto L98
        L81:
            r7 = r10
            r10 = r9
            r9 = r7
        L84:
            java.lang.Iterable r10 = (java.lang.Iterable) r10
            java.util.LinkedHashSet r10 = kotlin.collections.q0.b0(r9, r10)
        L8a:
            r0.L$0 = r5
            r0.L$1 = r5
            r0.L$2 = r5
            r0.label = r2
            java.lang.Object r10 = r8.connected(r10, r0)
            if (r10 != r6) goto L99
        L98:
            return r6
        L99:
            java.util.Collection r10 = (java.util.Collection) r10
            boolean r9 = r10.isEmpty()
            r9 = r9 ^ r4
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.sync.RemoteSyncManager.isRemoteConnected(com.arflix.tv.data.repository.sync.TrackingFeature, d7.d):java.lang.Object");
    }

    public final Object removeFromWatchlist(MediaType mediaType, int i10, boolean z, d<? super Boolean> dVar) {
        return writeResults(new C12722(mediaType, i10, z, null), dVar);
    }

    public final Object scrobblePause(MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d<? super t0> dVar) {
        Object objWriteAll = writeAll(new C12732(mediaType, i10, f10, num, num2, z, null), dVar);
        return objWriteAll == e7.a.f15033i ? objWriteAll : t0.f22605a;
    }

    public final Object scrobbleProgress(MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d<? super t0> dVar) {
        Object objWriteAll = writeAll(new C12742(mediaType, i10, f10, num, num2, z, null), dVar);
        return objWriteAll == e7.a.f15033i ? objWriteAll : t0.f22605a;
    }

    public final Object scrobbleStart(MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d<? super t0> dVar) {
        Object objWriteAll = writeAll(new C12752(mediaType, i10, f10, num, num2, z, null), dVar);
        return objWriteAll == e7.a.f15033i ? objWriteAll : t0.f22605a;
    }

    public final Object scrobbleStop(MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d<? super t0> dVar) {
        Object objWriteAll = writeAll(new C12762(mediaType, i10, f10, num, num2, z, null), dVar);
        return objWriteAll == e7.a.f15033i ? objWriteAll : t0.f22605a;
    }

    public final Object selectedProvider(d<? super SyncProvider> dVar) {
        return this.store.getProvider(dVar);
    }
}
