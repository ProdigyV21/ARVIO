package com.arflix.tv.ui.screens.settings;

import androidx.compose.runtime.Composer;
import com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class j2 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10588i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f10589l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f10590m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f10591n;

    public /* synthetic */ j2(String str, String str2, int i10, int i11) {
        this.f10588i = i11;
        this.f10589l = str;
        this.f10590m = str2;
        this.f10591n = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        int i10 = this.f10588i;
        Composer composer = (Composer) obj;
        int iIntValue = ((Integer) obj2).intValue();
        switch (i10) {
            case 0:
                return SettingsScreenKt.TvSettingsFactRow$lambda$1(this.f10589l, this.f10590m, this.f10591n, composer, iIntValue);
            default:
                return WatchlistScreenKt.LibraryMessage$lambda$1(this.f10589l, this.f10590m, this.f10591n, composer, iIntValue);
        }
    }
}
