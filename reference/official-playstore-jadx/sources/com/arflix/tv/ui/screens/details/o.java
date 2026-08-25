package com.arflix.tv.ui.screens.details;

import androidx.compose.runtime.Composer;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class o implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8929i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f8930l;

    public /* synthetic */ o(int i10, int i11) {
        this.f8929i = i11;
        this.f8930l = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        int i10 = this.f8929i;
        Composer composer = (Composer) obj;
        int iIntValue = ((Integer) obj2).intValue();
        switch (i10) {
            case 0:
                return DetailsScreenKt.MobileMetadataSeparator$lambda$0(this.f8930l, composer, iIntValue);
            default:
                return DetailsScreenKt.OngoingBadge$lambda$1(this.f8930l, composer, iIntValue);
        }
    }
}
