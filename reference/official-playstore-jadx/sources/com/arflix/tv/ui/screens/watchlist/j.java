package com.arflix.tv.ui.screens.watchlist;

import androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope;
import com.arflix.tv.data.model.MediaItem;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class j implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f12154i;

    public /* synthetic */ j(int i10) {
        this.f12154i = i10;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f12154i) {
            case 0:
                return WatchlistScreenKt.WatchlistScreen$lambda$4$0((String) obj);
            case 1:
                return WatchlistScreenKt.LibraryResults_8u0NR3k$lambda$1$1$0$3((LazyGridItemSpanScope) obj);
            case 2:
                return WatchlistScreenKt.WatchlistItemsSection_7vwJh_E$lambda$0$0(((Integer) obj).intValue());
            default:
                return WatchlistScreenKt.WatchlistItemsSection_7vwJh_E$lambda$1$0((MediaItem) obj);
        }
    }
}
