package com.arflix.tv.ui.screens.search;

import androidx.compose.runtime.MutableState;
import com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class c implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10104i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableState f10105l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MutableState f10106m;

    public /* synthetic */ c(int i10, MutableState mutableState, MutableState mutableState2) {
        this.f10104i = i10;
        this.f10105l = mutableState;
        this.f10106m = mutableState2;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f10104i) {
            case 0:
                return SearchScreenKt.SearchScreen$lambda$69$0$0$2$0(this.f10105l, this.f10106m);
            case 1:
                return WatchlistScreenKt.WatchlistScreen$lambda$74$3$0(this.f10105l, this.f10106m);
            case 2:
                return WatchlistScreenKt.WatchlistScreen$lambda$74$6$0(this.f10105l, this.f10106m);
            default:
                return WatchlistScreenKt.WatchlistScreen$lambda$60$0(this.f10105l, this.f10106m);
        }
    }
}
