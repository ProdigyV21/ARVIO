package com.arflix.tv.ui.screens.settings;

import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.text.KeyboardActionScope;
import androidx.compose.runtime.MutableState;
import com.arflix.tv.ui.screens.settings.telegram.TelegramSettingsScreenKt;
import com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt;
import com.arflix.tv.ui.screens.watchlist.WatchlistViewModel;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class o1 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10767i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f10768l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f10769m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ MutableState f10770n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f10771o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f10772p;

    public /* synthetic */ o1(List list, Set set, r7.l lVar, boolean z, MutableState mutableState) {
        this.f10771o = list;
        this.f10772p = set;
        this.f10769m = lVar;
        this.f10768l = z;
        this.f10770n = mutableState;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f10767i) {
            case 0:
                return SettingsScreenKt.CatalogDiscoveryModal$lambda$7$0$0$0$4$2$0((List) this.f10771o, (Set) this.f10772p, (r7.l) this.f10769m, this.f10768l, this.f10770n, (LazyListScope) obj);
            case 1:
                return TelegramSettingsScreenKt.PhoneContent$lambda$10$0$0(this.f10768l, (r7.l) this.f10769m, this.f10770n, (MutableState) this.f10771o, (MutableState) this.f10772p, (KeyboardActionScope) obj);
            default:
                return WatchlistScreenKt.WatchlistScreen$lambda$74$2$0(this.f10768l, (WatchlistViewModel) this.f10771o, this.f10770n, (MutableState) this.f10772p, (MutableState) this.f10769m, (String) obj);
        }
    }

    public /* synthetic */ o1(boolean z, WatchlistViewModel watchlistViewModel, MutableState mutableState, MutableState mutableState2, MutableState mutableState3) {
        this.f10768l = z;
        this.f10771o = watchlistViewModel;
        this.f10770n = mutableState;
        this.f10772p = mutableState2;
        this.f10769m = mutableState3;
    }

    public /* synthetic */ o1(boolean z, r7.l lVar, MutableState mutableState, MutableState mutableState2, MutableState mutableState3) {
        this.f10768l = z;
        this.f10769m = lVar;
        this.f10770n = mutableState;
        this.f10771o = mutableState2;
        this.f10772p = mutableState3;
    }
}
