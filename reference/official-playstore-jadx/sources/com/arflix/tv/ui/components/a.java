package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import com.arflix.tv.ui.screens.home.HomeScreenKt;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;
import com.arflix.tv.ui.screens.tv.live.EnrichedChannel;
import com.arflix.tv.ui.screens.tv.live.LiveTvEnhancementsKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class a implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7779i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Modifier f7780l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f7781m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f7782n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f7783o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f7784p;

    public /* synthetic */ a(Modifier modifier, String str, Float f10, int i10, int i11) {
        this.f7779i = 1;
        this.f7780l = modifier;
        this.f7781m = str;
        this.f7784p = f10;
        this.f7782n = i10;
        this.f7783o = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f7779i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return AppBottomBarKt.AppBottomBar$lambda$1((String) this.f7781m, (r7.l) this.f7784p, this.f7780l, this.f7782n, this.f7783o, (Composer) obj, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj2).intValue();
                return PlayerLoadingScreenKt.SourceLoadingScreen$lambda$2(this.f7780l, (String) this.f7781m, (Float) this.f7784p, this.f7782n, this.f7783o, (Composer) obj, iIntValue2);
            case 2:
                int iIntValue3 = ((Integer) obj2).intValue();
                return HomeScreenKt.HomeBackdropCrossfade$lambda$14((String) this.f7781m, (x6.x) this.f7784p, this.f7780l, this.f7782n, this.f7783o, (Composer) obj, iIntValue3);
            case 3:
                int iIntValue4 = ((Integer) obj2).intValue();
                return SettingsScreenKt.TrackingStatChip$lambda$1((String) this.f7781m, (String) this.f7784p, this.f7780l, this.f7782n, this.f7783o, (Composer) obj, iIntValue4);
            default:
                int iIntValue5 = ((Integer) obj2).intValue();
                return LiveTvEnhancementsKt.VariantRow$lambda$6((EnrichedChannel) this.f7781m, (r7.a) this.f7784p, this.f7780l, this.f7782n, this.f7783o, (Composer) obj, iIntValue5);
        }
    }

    public /* synthetic */ a(Object obj, Object obj2, Modifier modifier, int i10, int i11, int i12) {
        this.f7779i = i12;
        this.f7781m = obj;
        this.f7784p = obj2;
        this.f7780l = modifier;
        this.f7782n = i10;
        this.f7783o = i11;
    }
}
