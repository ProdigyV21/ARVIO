package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.Composer;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class z0 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9836i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f9837l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f9838m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f9839n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ r7.a f9840o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f9841p;

    public /* synthetic */ z0(String str, String str2, boolean z, r7.a aVar, int i10, int i11) {
        this.f9836i = i11;
        this.f9837l = str;
        this.f9838m = str2;
        this.f9839n = z;
        this.f9840o = aVar;
        this.f9841p = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f9836i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return PlayerScreenKt.MobileTrackItem$lambda$3(this.f9837l, this.f9838m, this.f9839n, this.f9840o, this.f9841p, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return SettingsScreenKt.TrackingRoutingRow$lambda$1(this.f9837l, this.f9838m, this.f9839n, this.f9840o, this.f9841p, (Composer) obj, iIntValue2);
        }
    }
}
