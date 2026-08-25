package com.arflix.tv.ui.screens.search;

import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class g implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10128i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.p f10129l;

    public /* synthetic */ g(r7.p pVar, int i10) {
        this.f10128i = i10;
        this.f10129l = pVar;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f10128i) {
            case 0:
                return SearchScreenKt.SearchScreen$lambda$69$0$8$0(this.f10129l, (MediaItem) obj);
            case 1:
                return SearchScreenKt.SearchScreen$lambda$69$0$12$0(this.f10129l, (MediaItem) obj);
            case 2:
                return WatchlistScreenKt.WatchlistScreen$lambda$74$0$5$0(this.f10129l, (MediaItem) obj);
            default:
                return WatchlistScreenKt.WatchlistScreen$lambda$74$0$8$0(this.f10129l, (MediaItem) obj);
        }
    }
}
