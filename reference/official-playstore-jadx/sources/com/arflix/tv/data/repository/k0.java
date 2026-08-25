package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.MediaType;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class k0 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7659i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f7660l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MediaType f7661m;

    public /* synthetic */ k0(int i10, MediaType mediaType, int i11) {
        this.f7659i = i11;
        this.f7660l = i10;
        this.f7661m = mediaType;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        boolean zSaveLocalContinueWatching$lambda$0;
        switch (this.f7659i) {
            case 0:
                zSaveLocalContinueWatching$lambda$0 = TraktRepository.saveLocalContinueWatching$lambda$0(this.f7660l, this.f7661m, (ContinueWatchingItem) obj);
                break;
            case 1:
                zSaveLocalContinueWatching$lambda$0 = WatchlistRepository.addToWatchlist$lambda$1$0(this.f7660l, this.f7661m, (MediaItem) obj);
                break;
            default:
                zSaveLocalContinueWatching$lambda$0 = WatchlistRepository.removeFromWatchlist$lambda$1$0(this.f7660l, this.f7661m, (MediaItem) obj);
                break;
        }
        return Boolean.valueOf(zSaveLocalContinueWatching$lambda$0);
    }
}
