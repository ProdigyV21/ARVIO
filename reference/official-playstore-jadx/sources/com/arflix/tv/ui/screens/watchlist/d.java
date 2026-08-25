package com.arflix.tv.ui.screens.watchlist;

import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.foundation.lazy.grid.LazyGridState;
import androidx.compose.runtime.Composer;
import java.util.List;
import java.util.Map;
import r7.p;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class d implements p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f12101i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ ColumnScope f12102l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Map f12103m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f12104n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f12105o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ float f12106p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ boolean f12107q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ boolean f12108r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ r7.l f12109s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ r7.l f12110t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ r7.l f12111u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ int f12112v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ int f12113w;
    public final /* synthetic */ Object x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final /* synthetic */ Object f12114y;
    public final /* synthetic */ Object z;

    public /* synthetic */ d(ColumnScope columnScope, HomeLibraryUiState homeLibraryUiState, Map map, int i10, LazyGridState lazyGridState, int i11, float f10, boolean z, boolean z5, r7.l lVar, r7.l lVar2, r7.l lVar3, r7.a aVar, int i12, int i13) {
        this.f12102l = columnScope;
        this.x = homeLibraryUiState;
        this.f12103m = map;
        this.f12104n = i10;
        this.f12114y = lazyGridState;
        this.f12105o = i11;
        this.f12106p = f10;
        this.f12107q = z;
        this.f12108r = z5;
        this.f12109s = lVar;
        this.f12110t = lVar2;
        this.f12111u = lVar3;
        this.z = aVar;
        this.f12112v = i12;
        this.f12113w = i13;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f12101i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return WatchlistScreenKt.WatchlistContent_05tvjtU$lambda$2(this.f12102l, (WatchlistUiState) this.x, (List) this.f12114y, this.f12103m, this.f12106p, this.f12107q, this.f12108r, this.f12104n, this.f12105o, (LazyListState) this.z, this.f12109s, this.f12110t, this.f12111u, this.f12112v, this.f12113w, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return WatchlistScreenKt.LibraryResults_8u0NR3k$lambda$3(this.f12102l, (HomeLibraryUiState) this.x, this.f12103m, this.f12104n, (LazyGridState) this.f12114y, this.f12105o, this.f12106p, this.f12107q, this.f12108r, this.f12109s, this.f12110t, this.f12111u, (r7.a) this.z, this.f12112v, this.f12113w, (Composer) obj, iIntValue2);
        }
    }

    public /* synthetic */ d(ColumnScope columnScope, WatchlistUiState watchlistUiState, List list, Map map, float f10, boolean z, boolean z5, int i10, int i11, LazyListState lazyListState, r7.l lVar, r7.l lVar2, r7.l lVar3, int i12, int i13) {
        this.f12102l = columnScope;
        this.x = watchlistUiState;
        this.f12114y = list;
        this.f12103m = map;
        this.f12106p = f10;
        this.f12107q = z;
        this.f12108r = z5;
        this.f12104n = i10;
        this.f12105o = i11;
        this.z = lazyListState;
        this.f12109s = lVar;
        this.f12110t = lVar2;
        this.f12111u = lVar3;
        this.f12112v = i12;
        this.f12113w = i13;
    }
}
