package com.arflix.tv.ui.screens.watchlist;

import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.lazy.grid.LazyGridScope;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class e implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f12115i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ float f12116l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f12117m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Map f12118n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f12119o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ r7.l f12120p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ r7.l f12121q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ r7.l f12122r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Object f12123s;

    public /* synthetic */ e(HomeLibraryUiState homeLibraryUiState, r7.l lVar, float f10, boolean z, Map map, int i10, r7.l lVar2, r7.l lVar3) {
        this.f12123s = homeLibraryUiState;
        this.f12120p = lVar;
        this.f12116l = f10;
        this.f12117m = z;
        this.f12118n = map;
        this.f12119o = i10;
        this.f12121q = lVar2;
        this.f12122r = lVar3;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f12115i) {
            case 0:
                return WatchlistScreenKt.LibraryResults_8u0NR3k$lambda$1$1$0((HomeLibraryUiState) this.f12123s, this.f12120p, this.f12116l, this.f12117m, this.f12118n, this.f12119o, this.f12121q, this.f12122r, (LazyGridScope) obj);
            default:
                return WatchlistScreenKt.WatchlistItemsSection_7vwJh_E$lambda$2$1$0((List) this.f12123s, this.f12116l, this.f12117m, this.f12118n, this.f12119o, this.f12120p, this.f12121q, this.f12122r, (LazyListScope) obj);
        }
    }

    public /* synthetic */ e(List list, float f10, boolean z, Map map, int i10, r7.l lVar, r7.l lVar2, r7.l lVar3) {
        this.f12123s = list;
        this.f12116l = f10;
        this.f12117m = z;
        this.f12118n = map;
        this.f12119o = i10;
        this.f12120p = lVar;
        this.f12121q = lVar2;
        this.f12122r = lVar3;
    }
}
