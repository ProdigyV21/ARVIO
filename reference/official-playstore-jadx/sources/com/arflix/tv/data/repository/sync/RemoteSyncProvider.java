package com.arflix.tv.data.repository.sync;

import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.repository.ContinueWatchingItem;
import d7.d;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u0002H¦@¢\u0006\u0004\b\u0003\u0010\u0004J*\u0010\n\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0002H¦@¢\u0006\u0004\b\n\u0010\u000bJ*\u0010\f\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0002H¦@¢\u0006\u0004\b\f\u0010\u000bJ\u0012\u0010\u000e\u001a\u0004\u0018\u00010\rH¦@¢\u0006\u0004\b\u000e\u0010\u0004JJ\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u000f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\t\u001a\u00020\u0002H¦@¢\u0006\u0004\b\u0014\u0010\u0015JJ\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u000f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\t\u001a\u00020\u0002H¦@¢\u0006\u0004\b\u0016\u0010\u0015JJ\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u000f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\t\u001a\u00020\u0002H¦@¢\u0006\u0004\b\u0017\u0010\u0015JJ\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u000f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\t\u001a\u00020\u0002H¦@¢\u0006\u0004\b\u0018\u0010\u0015J\u0016\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00070\u0019H¦@¢\u0006\u0004\b\u001a\u0010\u0004J\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0019H¦@¢\u0006\u0004\b\u001c\u0010\u0004J \u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\b\b\u0002\u0010\u001d\u001a\u00020\u0002H¦@¢\u0006\u0004\b \u0010!J8\u0010\"\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0007H¦@¢\u0006\u0004\b\"\u0010#R\u0014\u0010'\u001a\u00020$8&X¦\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&¨\u0006(À\u0006\u0003"}, d2 = {"Lcom/arflix/tv/data/repository/sync/RemoteSyncProvider;", "", "", "isConnected", "(Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/model/MediaType;", "mediaType", "", "tmdbId", "isAnime", "addToWatchlist", "(Lcom/arflix/tv/data/model/MediaType;IZLd7/d;)Ljava/lang/Object;", "removeFromWatchlist", "Lcom/arflix/tv/data/repository/sync/RemoteWatchlistResult;", "getWatchlist", "", "progress", "season", "episode", "Lx6/t0;", "scrobbleStart", "(Lcom/arflix/tv/data/model/MediaType;IFLjava/lang/Integer;Ljava/lang/Integer;ZLd7/d;)Ljava/lang/Object;", "scrobbleProgress", "scrobblePause", "scrobbleStop", "", "getWatchedMovies", "", "getWatchedEpisodes", "forceRefresh", "", "Lcom/arflix/tv/data/repository/ContinueWatchingItem;", "getContinueWatching", "(ZLd7/d;)Ljava/lang/Object;", "dismissContinueWatching", "(Lcom/arflix/tv/data/model/MediaType;ILjava/lang/Integer;Ljava/lang/Integer;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/repository/sync/SyncProvider;", "getProvider", "()Lcom/arflix/tv/data/repository/sync/SyncProvider;", "provider", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface RemoteSyncProvider {

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    Object addToWatchlist(MediaType mediaType, int i10, boolean z, d<? super Boolean> dVar);

    Object dismissContinueWatching(MediaType mediaType, int i10, Integer num, Integer num2, d<? super Boolean> dVar);

    Object getContinueWatching(boolean z, d<? super List<ContinueWatchingItem>> dVar);

    SyncProvider getProvider();

    Object getWatchedEpisodes(d<? super Set<String>> dVar);

    Object getWatchedMovies(d<? super Set<Integer>> dVar);

    Object getWatchlist(d<? super RemoteWatchlistResult> dVar);

    Object isConnected(d<? super Boolean> dVar);

    Object removeFromWatchlist(MediaType mediaType, int i10, boolean z, d<? super Boolean> dVar);

    Object scrobblePause(MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d<? super t0> dVar);

    Object scrobbleProgress(MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d<? super t0> dVar);

    Object scrobbleStart(MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d<? super t0> dVar);

    Object scrobbleStop(MediaType mediaType, int i10, float f10, Integer num, Integer num2, boolean z, d<? super t0> dVar);
}
