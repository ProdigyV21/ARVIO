package com.arflix.tv;

import androidx.lifecycle.d1;
import com.arflix.tv.ui.components.SettingsRowsKt;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;
import com.arflix.tv.ui.screens.settings.SettingsViewModel;
import com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt;
import com.arflix.tv.ui.screens.watchlist.WatchlistViewModel;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class b implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7604i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f7605l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f7606m;

    public /* synthetic */ b(int i10, Object obj, boolean z) {
        this.f7604i = i10;
        this.f7606m = obj;
        this.f7605l = z;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f7604i) {
            case 0:
                return ArflixApplication.newImageLoader$lambda$1((ArflixApplication) this.f7606m, this.f7605l);
            case 1:
                return SettingsRowsKt.SettingsToggleRow$lambda$1$0((r7.l) this.f7606m, this.f7605l);
            case 2:
                return SettingsScreenKt.SettingsScreen$lambda$241$17$1$0$97$0(this.f7605l, (SettingsViewModel) this.f7606m);
            default:
                return WatchlistScreenKt.WatchlistScreen$lambda$74$0$6$0(this.f7605l, (WatchlistViewModel) this.f7606m);
        }
    }

    public /* synthetic */ b(boolean z, d1 d1Var, int i10) {
        this.f7604i = i10;
        this.f7605l = z;
        this.f7606m = d1Var;
    }
}
