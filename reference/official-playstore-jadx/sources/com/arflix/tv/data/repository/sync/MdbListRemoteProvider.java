package com.arflix.tv.data.repository.sync;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.repository.ContinueWatchingItem;
import com.arflix.tv.data.repository.MdbListRepository;
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
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0007\u001a\u00020\u0006H\u0096@¢\u0006\u0004\b\u0007\u0010\bJ(\u0010\u000e\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0006H\u0096@¢\u0006\u0004\b\u000e\u0010\u000fJ(\u0010\u0010\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0006H\u0096@¢\u0006\u0004\b\u0010\u0010\u000fJ\u0010\u0010\u0012\u001a\u00020\u0011H\u0096@¢\u0006\u0004\b\u0012\u0010\bJD\u0010\u0018\u001a\u00020\u00172\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u0006H\u0096@¢\u0006\u0004\b\u0018\u0010\u0019JD\u0010\u001a\u001a\u00020\u00172\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u0006H\u0096@¢\u0006\u0004\b\u001a\u0010\u0019JD\u0010\u001b\u001a\u00020\u00172\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u0006H\u0096@¢\u0006\u0004\b\u001b\u0010\u0019JD\u0010\u001c\u001a\u00020\u00172\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u0006H\u0096@¢\u0006\u0004\b\u001c\u0010\u0019J\u0016\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001dH\u0096@¢\u0006\u0004\b\u001e\u0010\bJ\u0016\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u001dH\u0096@¢\u0006\u0004\b \u0010\bJ\u001e\u0010$\u001a\b\u0012\u0004\u0012\u00020#0\"2\u0006\u0010!\u001a\u00020\u0006H\u0096@¢\u0006\u0004\b$\u0010%J4\u0010&\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u000bH\u0096@¢\u0006\u0004\b&\u0010'R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010(R\u001a\u0010*\u001a\u00020)8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-¨\u0006."}, d2 = {"Lcom/arflix/tv/data/repository/sync/MdbListRemoteProvider;", "Lcom/arflix/tv/data/repository/sync/RemoteSyncProvider;", "Lcom/arflix/tv/data/repository/MdbListRepository;", "repository", "<init>", "(Lcom/arflix/tv/data/repository/MdbListRepository;)V", "", "isConnected", "(Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/model/MediaType;", "mediaType", "", "tmdbId", "isAnime", "addToWatchlist", "(Lcom/arflix/tv/data/model/MediaType;IZLd7/d;)Ljava/lang/Object;", "removeFromWatchlist", "Lcom/arflix/tv/data/repository/sync/RemoteWatchlistResult;", "getWatchlist", "", "progress", "season", "episode", "Lx6/t0;", "scrobbleStart", "(Lcom/arflix/tv/data/model/MediaType;IFLjava/lang/Integer;Ljava/lang/Integer;ZLd7/d;)Ljava/lang/Object;", "scrobblePause", "scrobbleProgress", "scrobbleStop", "", "getWatchedMovies", "", "getWatchedEpisodes", "forceRefresh", "", "Lcom/arflix/tv/data/repository/ContinueWatchingItem;", "getContinueWatching", "(ZLd7/d;)Ljava/lang/Object;", "dismissContinueWatching", "(Lcom/arflix/tv/data/model/MediaType;ILjava/lang/Integer;Ljava/lang/Integer;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/repository/MdbListRepository;", "Lcom/arflix/tv/data/repository/sync/SyncProvider;", "provider", "Lcom/arflix/tv/data/repository/sync/SyncProvider;", "getProvider", "()Lcom/arflix/tv/data/repository/sync/SyncProvider;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class MdbListRemoteProvider implements RemoteSyncProvider {
    public static final int $stable = SyncProviderStore.$stable;
    private final SyncProvider provider = SyncProvider.MDBLIST;
    private final MdbListRepository repository;

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.sync.MdbListRemoteProvider$dismissContinueWatching$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.data.repository.sync.MdbListRemoteProvider", f = "MdbListRemoteProvider.kt", l = {60}, m = "dismissContinueWatching", v = 2)
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
            return MdbListRemoteProvider.this.dismissContinueWatching(null, 0, null, null, this);
        }
    }

    @Inject
    public MdbListRemoteProvider(MdbListRepository mdbListRepository) {
        this.repository = mdbListRepository;
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object addToWatchlist(MediaType mediaType, int i10, boolean z, d<? super Boolean> dVar) {
        return this.repository.addToWatchlist(mediaType, i10, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object dismissContinueWatching(com.arflix.tv.data.model.MediaType r8, int r9, java.lang.Integer r10, java.lang.Integer r11, d7.d<? super java.lang.Boolean> r12) throws java.lang.Throwable {
        /*
            r7 = this;
            boolean r0 = r12 instanceof com.arflix.tv.data.repository.sync.MdbListRemoteProvider.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r12
            com.arflix.tv.data.repository.sync.MdbListRemoteProvider$dismissContinueWatching$1 r0 = (com.arflix.tv.data.repository.sync.MdbListRemoteProvider.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r6 = r0
            goto L1a
        L14:
            com.arflix.tv.data.repository.sync.MdbListRemoteProvider$dismissContinueWatching$1 r0 = new com.arflix.tv.data.repository.sync.MdbListRemoteProvider$dismissContinueWatching$1
            r0.<init>(r12)
            goto L12
        L1a:
            java.lang.Object r12 = r6.result
            int r0 = r6.label
            r1 = 1
            if (r0 == 0) goto L3b
            if (r0 != r1) goto L33
            java.lang.Object r8 = r6.L$2
            java.lang.Integer r8 = (java.lang.Integer) r8
            java.lang.Object r8 = r6.L$1
            java.lang.Integer r8 = (java.lang.Integer) r8
            java.lang.Object r8 = r6.L$0
            com.arflix.tv.data.model.MediaType r8 = (com.arflix.tv.data.model.MediaType) r8
            k2.c.G(r12)
            goto L59
        L33:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L3b:
            k2.c.G(r12)
            r12 = r1
            com.arflix.tv.data.repository.MdbListRepository r1 = r7.repository
            r0 = 0
            r6.L$0 = r0
            r6.L$1 = r0
            r6.L$2 = r0
            r6.I$0 = r9
            r6.label = r12
            r2 = r8
            r3 = r9
            r4 = r10
            r5 = r11
            java.lang.Object r8 = r1.clearPlayback(r2, r3, r4, r5, r6)
            e7.a r9 = e7.a.f15033i
            if (r8 != r9) goto L59
            return r9
        L59:
            java.lang.Boolean r8 = java.lang.Boolean.TRUE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.sync.MdbListRemoteProvider.dismissContinueWatching(com.arflix.tv.data.model.MediaType, int, java.lang.Integer, java.lang.Integer, d7.d):java.lang.Object");
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object getContinueWatching(boolean z, d<? super List<ContinueWatchingItem>> dVar) {
        return this.repository.getContinueWatching(dVar);
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public SyncProvider getProvider() {
        return this.provider;
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object getWatchedEpisodes(d<? super Set<String>> dVar) {
        return this.repository.getWatchedEpisodes(dVar);
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object getWatchedMovies(d<? super Set<Integer>> dVar) {
        return this.repository.getWatchedMovies(dVar);
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object getWatchlist(d<? super RemoteWatchlistResult> dVar) {
        return this.repository.getWatchlist(dVar);
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object isConnected(d<? super Boolean> dVar) {
        return this.repository.isConnected(dVar);
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object removeFromWatchlist(MediaType mediaType, int i10, boolean z, d<? super Boolean> dVar) {
        return this.repository.removeFromWatchlist(mediaType, i10, dVar);
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object scrobblePause(MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d<? super t0> dVar) throws Throwable {
        Object objScrobble = this.repository.scrobble("pause", mediaType, i10, f10, num, num2, dVar);
        return objScrobble == e7.a.f15033i ? objScrobble : t0.f22605a;
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object scrobbleProgress(MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d<? super t0> dVar) throws Throwable {
        Object objScrobble = this.repository.scrobble(TtmlNode.START, mediaType, i10, f10, num, num2, dVar);
        return objScrobble == e7.a.f15033i ? objScrobble : t0.f22605a;
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object scrobbleStart(MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d<? super t0> dVar) throws Throwable {
        Object objScrobble = this.repository.scrobble(TtmlNode.START, mediaType, i10, f10, num, num2, dVar);
        return objScrobble == e7.a.f15033i ? objScrobble : t0.f22605a;
    }

    @Override // com.arflix.tv.data.repository.sync.RemoteSyncProvider
    public Object scrobbleStop(MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d<? super t0> dVar) throws Throwable {
        Object objScrobble = this.repository.scrobble("stop", mediaType, i10, f10, num, num2, dVar);
        return objScrobble == e7.a.f15033i ? objScrobble : t0.f22605a;
    }
}
