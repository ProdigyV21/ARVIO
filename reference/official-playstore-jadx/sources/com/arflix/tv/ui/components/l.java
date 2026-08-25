package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class l implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8148i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Modifier f8149l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f8150m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f8151n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f8152o;

    public /* synthetic */ l(Modifier modifier, String str, int i10, int i11, int i12) {
        this.f8148i = i12;
        this.f8149l = modifier;
        this.f8150m = str;
        this.f8151n = i10;
        this.f8152o = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8148i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return ArvioLoadingScreenKt.CompactLoadingIndicator$lambda$1(this.f8149l, this.f8150m, this.f8151n, this.f8152o, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return ArvioLoadingScreenKt.LoadingOverlay$lambda$1(this.f8149l, this.f8150m, this.f8151n, this.f8152o, (Composer) obj, iIntValue2);
        }
    }
}
