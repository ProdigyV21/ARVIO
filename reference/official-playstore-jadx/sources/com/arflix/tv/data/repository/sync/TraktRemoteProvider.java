package com.arflix.tv.data.repository.sync;

import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.repository.ContinueWatchingItem;
import com.arflix.tv.data.repository.TraktRepository;
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
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0007\u001a\u00020\u0006H\u0096@¢\u0006\u0004\b\u0007\u0010\bJ(\u0010\u000e\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0006H\u0096@¢\u0006\u0004\b\u000e\u0010\u000fJ(\u0010\u0010\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0006H\u0096@¢\u0006\u0004\b\u0010\u0010\u000fJ\u0010\u0010\u0012\u001a\u00020\u0011H\u0096@¢\u0006\u0004\b\u0012\u0010\bJD\u0010\u0018\u001a\u00020\u00172\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u0006H\u0096@¢\u0006\u0004\b\u0018\u0010\u0019JD\u0010\u001a\u001a\u00020\u00172\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u0006H\u0096@¢\u0006\u0004\b\u001a\u0010\u0019JD\u0010\u001b\u001a\u00020\u00172\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u0006H\u0096@¢\u0006\u0004\b\u001b\u0010\u0019JD\u0010\u001c\u001a\u00020\u00172\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u0006H\u0096@¢\u0006\u0004\b\u001c\u0010\u0019J\u0016\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001dH\u0096@¢\u0006\u0004\b\u001e\u0010\bJ\u0016\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u001dH\u0096@¢\u0006\u0004\b \u0010\bJ\u001e\u0010$\u001a\b\u0012\u0004\u0012\u00020#0\"2\u0006\u0010!\u001a\u00020\u0006H\u0096@¢\u0006\u0004\b$\u0010%J4\u0010&\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u000bH\u0096@¢\u0006\u0004\b&\u0010'R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010(R\u001a\u0010*\u001a\u00020)8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-¨\u0006."}, d2 = {"Lcom/arflix/tv/data/repository/sync/TraktRemoteProvider;", "Lcom/arflix/tv/data/repository/sync/RemoteSyncProvider;", "Lcom/arflix/tv/data/repository/TraktRepository;", "traktRepository", "<init>", "(Lcom/arflix/tv/data/repository/TraktRepository;)V", "", "isConnected", "(Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/model/MediaType;", "mediaType", "", "tmdbId", "isAnime", "addToWatchlist", "(Lcom/arflix/tv/data/model/MediaType;IZLd7/d;)Ljava/lang/Object;", "removeFromWatchlist", "Lcom/arflix/tv/data/repository/sync/RemoteWatchlistResult;", "getWatchlist", "", "progress", "season", "episode", "Lx6/t0;", "scrobbleStart", "(Lcom/arflix/tv/data/model/MediaType;IFLjava/lang/Integer;Ljava/lang/Integer;ZLd7/d;)Ljava/lang/Object;", "scrobblePause", "scrobbleProgress", "scrobbleStop", "", "getWatchedMovies", "", "getWatchedEpisodes", "forceRefresh", "", "Lcom/arflix/tv/data/repository/ContinueWatchingItem;", "getContinueWatching", "(ZLd7/d;)Ljava/lang/Object;", "dismissContinueWatching", "(Lcom/arflix/tv/data/model/MediaType;ILjava/lang/Integer;Ljava/lang/Integer;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/repository/TraktRepository;", "Lcom/arflix/tv/data/repository/sync/SyncProvider;", "provider", "Lcom/arflix/tv/data/repository/sync/SyncProvider;", "getProvider", "()Lcom/arflix/tv/data/repository/sync/SyncProvider;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class TraktRemoteProvider implements RemoteSyncProvider {
    public static final int $stable = 8;
    private final SyncProvider provider = SyncProvider.TRAKT;
    private final TraktRepository traktRepository;

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.TraktRemoteProvider$getWatchlist$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.data.repository.sync.TraktRemoteProvider", f = "TraktRemoteProvider.kt", l = {30}, m = "getWatchlist", v = 2)
    public static final class AnonymousClass1 extends c {
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TraktRemoteProvider.this.getWatchlist(this);
        }
    }

    @Inject
    public TraktRemoteProvider(TraktRepository traktRepository) {
        this.traktRepository = traktRepository;
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object addToWatchlist(MediaType mediaType, int i10, boolean z, d<? super Boolean> dVar) {
        return this.traktRepository.addToWatchlist(mediaType, i10, dVar);
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object dismissContinueWatching(MediaType mediaType, int i10, Integer num, Integer num2, d<? super Boolean> dVar) {
        return (mediaType != MediaType.TV || num == null || num2 == null) ? this.traktRepository.deletePlaybackForContent(i10, mediaType, dVar) : this.traktRepository.deletePlaybackForEpisode(i10, num.intValue(), num2.intValue(), dVar);
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object getContinueWatching(boolean z, d<? super List<ContinueWatchingItem>> dVar) {
        return this.traktRepository.getContinueWatching(z, dVar);
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public SyncProvider getProvider() {
        return this.provider;
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object getWatchedEpisodes(d<? super Set<String>> dVar) {
        return this.traktRepository.getWatchedEpisodes(dVar);
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object getWatchedMovies(d<? super Set<Integer>> dVar) {
        return this.traktRepository.getWatchedMovies(dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getWatchlist(d7.d<? super com.arflix.tv.data.repository.sync.RemoteWatchlistResult> r5) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.arflix.tv.data.repository.sync.TraktRemoteProvider.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r5
            com.arflix.tv.data.repository.sync.TraktRemoteProvider$getWatchlist$1 r0 = (com.arflix.tv.data.repository.sync.TraktRemoteProvider.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.sync.TraktRemoteProvider$getWatchlist$1 r0 = new com.arflix.tv.data.repository.sync.TraktRemoteProvider$getWatchlist$1
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
            com.arflix.tv.data.repository.TraktRepository r5 = r4.traktRepository
            r0.label = r2
            java.lang.Object r5 = r5.getWatchlistSyncResultWithAuthState(r0)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L3d
            return r0
        L3d:
            x6.x r5 = (x6.x) r5
            java.lang.Object r0 = r5.f22608i
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            java.lang.Object r5 = r5.f22609l
            com.arflix.tv.data.repository.TraktRepository$WatchlistSyncResult r5 = (com.arflix.tv.data.repository.TraktRepository.WatchlistSyncResult) r5
            com.arflix.tv.data.repository.sync.RemoteWatchlistResult r1 = new com.arflix.tv.data.repository.sync.RemoteWatchlistResult
            if (r5 == 0) goto L54
            java.util.List r2 = r5.getItems()
            goto L55
        L54:
            r2 = 0
        L55:
            if (r5 == 0) goto L5c
            int r5 = r5.getRawCount()
            goto L5d
        L5c:
            r5 = 0
        L5d:
            r1.<init>(r0, r2, r5)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.sync.TraktRemoteProvider.getWatchlist(d7.d):java.lang.Object");
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object isConnected(d<? super Boolean> dVar) {
        return this.traktRepository.hasTrakt(dVar);
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object removeFromWatchlist(MediaType mediaType, int i10, boolean z, d<? super Boolean> dVar) {
        return this.traktRepository.removeFromWatchlist(mediaType, i10, dVar);
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object scrobblePause(MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d<? super t0> dVar) {
        Object objScrobblePauseImmediate = this.traktRepository.scrobblePauseImmediate(mediaType, i10, f10, num, num2, dVar);
        return objScrobblePauseImmediate == e7.a.f15033i ? objScrobblePauseImmediate : t0.f22605a;
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object scrobbleProgress(MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d<? super t0> dVar) {
        Object objScrobbleStart = this.traktRepository.scrobbleStart(mediaType, i10, f10, num, num2, dVar);
        return objScrobbleStart == e7.a.f15033i ? objScrobbleStart : t0.f22605a;
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object scrobbleStart(MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d<? super t0> dVar) {
        Object objScrobbleStart = this.traktRepository.scrobbleStart(mediaType, i10, f10, num, num2, dVar);
        return objScrobbleStart == e7.a.f15033i ? objScrobbleStart : t0.f22605a;
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object scrobbleStop(MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d<? super t0> dVar) {
        Object objScrobbleStop = this.traktRepository.scrobbleStop(mediaType, i10, f10, num, num2, dVar);
        return objScrobbleStop == e7.a.f15033i ? objScrobbleStop : t0.f22605a;
    }
}
