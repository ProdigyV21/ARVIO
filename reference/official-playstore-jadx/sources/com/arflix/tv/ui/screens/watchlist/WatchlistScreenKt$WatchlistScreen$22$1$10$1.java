package com.arflix.tv.ui.screens.watchlist;

import com.arflix.tv.data.model.MediaItem;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final /* synthetic */ class WatchlistScreenKt$WatchlistScreen$22$1$10$1 extends kotlin.jvm.internal.m implements r7.l<MediaItem, t0> {
    public WatchlistScreenKt$WatchlistScreen$22$1$10$1(Object obj) {
        super(1, 0, WatchlistViewModel.class, obj, "removeFromWatchlist", "removeFromWatchlist(Lcom/arflix/tv/data/model/MediaItem;)V");
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((MediaItem) obj);
        return t0.f22605a;
    }

    public final void invoke(MediaItem mediaItem) {
        ((WatchlistViewModel) this.receiver).removeFromWatchlist(mediaItem);
    }
}
