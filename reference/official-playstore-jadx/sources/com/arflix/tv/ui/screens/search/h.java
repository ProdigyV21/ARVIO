package com.arflix.tv.ui.screens.search;

import androidx.compose.runtime.Composer;
import com.arflix.tv.data.model.Profile;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;
import com.arflix.tv.ui.screens.settings.SettingsUiState;
import com.arflix.tv.ui.screens.settings.SettingsViewModel;
import com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt;
import com.arflix.tv.ui.screens.watchlist.WatchlistViewModel;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class h implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10130i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.a f10131l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.a f10132m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.a f10133n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ r7.a f10134o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ r7.a f10135p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ x6.o f10136q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ int f10137r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ int f10138s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ Object f10139t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ Object f10140u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ x6.o f10141v;

    public /* synthetic */ h(SettingsUiState settingsUiState, r7.a aVar, r7.a aVar2, r7.l lVar, r7.a aVar3, r7.a aVar4, r7.a aVar5, r7.p pVar, r7.p pVar2, int i10, int i11) {
        this.f10130i = 2;
        this.f10139t = settingsUiState;
        this.f10131l = aVar;
        this.f10132m = aVar2;
        this.f10140u = lVar;
        this.f10133n = aVar3;
        this.f10134o = aVar4;
        this.f10135p = aVar5;
        this.f10141v = pVar;
        this.f10136q = pVar2;
        this.f10137r = i10;
        this.f10138s = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f10130i) {
            case 0:
                return SearchScreenKt.SearchScreen$lambda$70((SearchViewModel) this.f10139t, (Profile) this.f10140u, (r7.p) this.f10141v, this.f10131l, this.f10132m, this.f10133n, this.f10134o, this.f10135p, (r7.a) this.f10136q, this.f10137r, this.f10138s, (Composer) obj, ((Integer) obj2).intValue());
            case 1:
                return SettingsScreenKt.MobileSettingsMainPage$lambda$3((SettingsUiState) this.f10139t, (SettingsViewModel) this.f10140u, (r7.l) this.f10141v, this.f10131l, this.f10132m, this.f10133n, this.f10134o, this.f10135p, (r7.a) this.f10136q, this.f10137r, this.f10138s, (Composer) obj, ((Integer) obj2).intValue());
            case 2:
                return SettingsScreenKt.TrackingIntegrationsPage$lambda$21((SettingsUiState) this.f10139t, this.f10131l, this.f10132m, (r7.l) this.f10140u, this.f10133n, this.f10134o, this.f10135p, (r7.p) this.f10141v, (r7.p) this.f10136q, this.f10137r, this.f10138s, (Composer) obj, ((Integer) obj2).intValue());
            default:
                return WatchlistScreenKt.WatchlistScreen$lambda$75((WatchlistViewModel) this.f10139t, (Profile) this.f10140u, (r7.p) this.f10141v, this.f10131l, this.f10132m, this.f10133n, (r7.l) this.f10136q, this.f10134o, this.f10135p, this.f10137r, this.f10138s, (Composer) obj, ((Integer) obj2).intValue());
        }
    }

    public /* synthetic */ h(WatchlistViewModel watchlistViewModel, Profile profile, r7.p pVar, r7.a aVar, r7.a aVar2, r7.a aVar3, r7.l lVar, r7.a aVar4, r7.a aVar5, int i10, int i11) {
        this.f10130i = 3;
        this.f10139t = watchlistViewModel;
        this.f10140u = profile;
        this.f10141v = pVar;
        this.f10131l = aVar;
        this.f10132m = aVar2;
        this.f10133n = aVar3;
        this.f10136q = lVar;
        this.f10134o = aVar4;
        this.f10135p = aVar5;
        this.f10137r = i10;
        this.f10138s = i11;
    }

    public /* synthetic */ h(Object obj, Object obj2, x6.o oVar, r7.a aVar, r7.a aVar2, r7.a aVar3, r7.a aVar4, r7.a aVar5, r7.a aVar6, int i10, int i11, int i12) {
        this.f10130i = i12;
        this.f10139t = obj;
        this.f10140u = obj2;
        this.f10141v = oVar;
        this.f10131l = aVar;
        this.f10132m = aVar2;
        this.f10133n = aVar3;
        this.f10134o = aVar4;
        this.f10135p = aVar5;
        this.f10136q = aVar6;
        this.f10137r = i10;
        this.f10138s = i11;
    }
}
