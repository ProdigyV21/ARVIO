package com.arflix.tv.data.repository.sync;

import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.repository.ContinueWatchingItem;
import com.arflix.tv.data.repository.simkl.SimklAuthManager;
import com.arflix.tv.data.repository.simkl.SimklScrobbler;
import com.arflix.tv.data.repository.simkl.SimklSyncService;
import d7.d;
import f7.c;
import f7.e;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u000b\u001a\u00020\nH\u0096@¢\u0006\u0004\b\u000b\u0010\fJ(\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\nH\u0096@¢\u0006\u0004\b\u0012\u0010\u0013J(\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\nH\u0096@¢\u0006\u0004\b\u0014\u0010\u0013J\u0010\u0010\u0016\u001a\u00020\u0015H\u0096@¢\u0006\u0004\b\u0016\u0010\fJD\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u000f2\b\u0010\u001a\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0011\u001a\u00020\nH\u0096@¢\u0006\u0004\b\u001c\u0010\u001dJD\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u000f2\b\u0010\u001a\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0011\u001a\u00020\nH\u0096@¢\u0006\u0004\b\u001e\u0010\u001dJD\u0010\u001f\u001a\u00020\u001b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u000f2\b\u0010\u001a\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0011\u001a\u00020\nH\u0096@¢\u0006\u0004\b\u001f\u0010\u001dJD\u0010 \u001a\u00020\u001b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u000f2\b\u0010\u001a\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0011\u001a\u00020\nH\u0096@¢\u0006\u0004\b \u0010\u001dJ\u0016\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000f0!H\u0096@¢\u0006\u0004\b\"\u0010\fJ\u0016\u0010$\u001a\b\u0012\u0004\u0012\u00020#0!H\u0096@¢\u0006\u0004\b$\u0010\fJ\u001e\u0010(\u001a\b\u0012\u0004\u0012\u00020'0&2\u0006\u0010%\u001a\u00020\nH\u0096@¢\u0006\u0004\b(\u0010)J4\u0010*\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u000f2\b\u0010\u001a\u001a\u0004\u0018\u00010\u000fH\u0096@¢\u0006\u0004\b*\u0010+R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010,R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010-R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010.R\u001a\u00100\u001a\u00020/8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b0\u00101\u001a\u0004\b2\u00103¨\u00064"}, d2 = {"Lcom/arflix/tv/data/repository/sync/SimklRemoteProvider;", "Lcom/arflix/tv/data/repository/sync/RemoteSyncProvider;", "Lcom/arflix/tv/data/repository/simkl/SimklAuthManager;", "authManager", "Lcom/arflix/tv/data/repository/simkl/SimklScrobbler;", "scrobbler", "Lcom/arflix/tv/data/repository/simkl/SimklSyncService;", "syncService", "<init>", "(Lcom/arflix/tv/data/repository/simkl/SimklAuthManager;Lcom/arflix/tv/data/repository/simkl/SimklScrobbler;Lcom/arflix/tv/data/repository/simkl/SimklSyncService;)V", "", "isConnected", "(Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/model/MediaType;", "mediaType", "", "tmdbId", "isAnime", "addToWatchlist", "(Lcom/arflix/tv/data/model/MediaType;IZLd7/d;)Ljava/lang/Object;", "removeFromWatchlist", "Lcom/arflix/tv/data/repository/sync/RemoteWatchlistResult;", "getWatchlist", "", "progress", "season", "episode", "Lx6/t0;", "scrobbleStart", "(Lcom/arflix/tv/data/model/MediaType;IFLjava/lang/Integer;Ljava/lang/Integer;ZLd7/d;)Ljava/lang/Object;", "scrobblePause", "scrobbleProgress", "scrobbleStop", "", "getWatchedMovies", "", "getWatchedEpisodes", "forceRefresh", "", "Lcom/arflix/tv/data/repository/ContinueWatchingItem;", "getContinueWatching", "(ZLd7/d;)Ljava/lang/Object;", "dismissContinueWatching", "(Lcom/arflix/tv/data/model/MediaType;ILjava/lang/Integer;Ljava/lang/Integer;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/repository/simkl/SimklAuthManager;", "Lcom/arflix/tv/data/repository/simkl/SimklScrobbler;", "Lcom/arflix/tv/data/repository/simkl/SimklSyncService;", "Lcom/arflix/tv/data/repository/sync/SyncProvider;", "provider", "Lcom/arflix/tv/data/repository/sync/SyncProvider;", "getProvider", "()Lcom/arflix/tv/data/repository/sync/SyncProvider;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SimklRemoteProvider implements RemoteSyncProvider {
    public static final int $stable = 8;
    private final SimklAuthManager authManager;
    private final SyncProvider provider = SyncProvider.SIMKL;
    private final SimklScrobbler scrobbler;
    private final SimklSyncService syncService;

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.SimklRemoteProvider$getWatchlist$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.data.repository.sync.SimklRemoteProvider", f = "SimklRemoteProvider.kt", l = {32, 34}, m = "getWatchlist", v = 2)
    public static final class AnonymousClass1 extends c {
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
            return SimklRemoteProvider.this.getWatchlist(this);
        }
    }

    @Inject
    public SimklRemoteProvider(SimklAuthManager simklAuthManager, SimklScrobbler simklScrobbler, SimklSyncService simklSyncService) {
        this.authManager = simklAuthManager;
        this.scrobbler = simklScrobbler;
        this.syncService = simklSyncService;
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object addToWatchlist(MediaType mediaType, int i10, boolean z, d<? super Boolean> dVar) {
        return this.syncService.addToWatchlist(mediaType, i10, z, dVar);
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object dismissContinueWatching(MediaType mediaType, int i10, Integer num, Integer num2, d<? super Boolean> dVar) {
        return this.syncService.dismissContinueWatching(mediaType, i10, num, num2, dVar);
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object getContinueWatching(boolean z, d<? super List<ContinueWatchingItem>> dVar) {
        return this.syncService.getContinueWatching(z, dVar);
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public SyncProvider getProvider() {
        return this.provider;
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object getWatchedEpisodes(d<? super Set<String>> dVar) {
        return this.syncService.getWatchedEpisodes(dVar);
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object getWatchedMovies(d<? super Set<Integer>> dVar) {
        return this.syncService.getWatchedMovies(dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x005d, code lost:
    
        if (r6 == r4) goto L25;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getWatchlist(d7.d<? super com.arflix.tv.data.repository.sync.RemoteWatchlistResult> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.arflix.tv.data.repository.sync.SimklRemoteProvider.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            com.arflix.tv.data.repository.sync.SimklRemoteProvider$getWatchlist$1 r0 = (com.arflix.tv.data.repository.sync.SimklRemoteProvider.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.sync.SimklRemoteProvider$getWatchlist$1 r0 = new com.arflix.tv.data.repository.sync.SimklRemoteProvider$getWatchlist$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L36
            if (r1 == r3) goto L32
            if (r1 != r2) goto L2a
            k2.c.G(r6)
            goto L60
        L2a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L32:
            k2.c.G(r6)
            goto L42
        L36:
            k2.c.G(r6)
            r0.label = r3
            java.lang.Object r6 = r5.isConnected(r0)
            if (r6 != r4) goto L42
            goto L5f
        L42:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 != 0) goto L53
            com.arflix.tv.data.repository.sync.RemoteWatchlistResult r6 = new com.arflix.tv.data.repository.sync.RemoteWatchlistResult
            kotlin.collections.z r0 = kotlin.collections.z.f19728i
            r1 = 0
            r6.<init>(r1, r0, r1)
            return r6
        L53:
            com.arflix.tv.data.repository.simkl.SimklSyncService r1 = r5.syncService
            r0.Z$0 = r6
            r0.label = r2
            java.lang.Object r6 = r1.getWatchlistItems(r0)
            if (r6 != r4) goto L60
        L5f:
            return r4
        L60:
            java.util.List r6 = (java.util.List) r6
            com.arflix.tv.data.repository.sync.RemoteWatchlistResult r0 = new com.arflix.tv.data.repository.sync.RemoteWatchlistResult
            int r1 = r6.size()
            r0.<init>(r3, r6, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.sync.SimklRemoteProvider.getWatchlist(d7.d):java.lang.Object");
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object isConnected(d<? super Boolean> dVar) {
        return this.authManager.isConnected(dVar);
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object removeFromWatchlist(MediaType mediaType, int i10, boolean z, d<? super Boolean> dVar) {
        return this.syncService.removeFromWatchlist(mediaType, i10, z, dVar);
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object scrobblePause(MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d<? super t0> dVar) {
        Object objScrobblePause = this.scrobbler.scrobblePause(mediaType, i10, f10, num, num2, z, dVar);
        return objScrobblePause == e7.a.f15033i ? objScrobblePause : t0.f22605a;
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object scrobbleProgress(MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d<? super t0> dVar) {
        return t0.f22605a;
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object scrobbleStart(MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d<? super t0> dVar) {
        Object objScrobbleStart = this.scrobbler.scrobbleStart(mediaType, i10, f10, num, num2, z, dVar);
        return objScrobbleStart == e7.a.f15033i ? objScrobbleStart : t0.f22605a;
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object scrobbleStop(MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d<? super t0> dVar) {
        Object objScrobbleStop = this.scrobbler.scrobbleStop(mediaType, i10, f10, num, num2, z, dVar);
        return objScrobbleStop == e7.a.f15033i ? objScrobbleStop : t0.f22605a;
    }
}
