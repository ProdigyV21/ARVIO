package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class v2 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8448i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f8449l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f8450m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f8451n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f8452o;

    public /* synthetic */ v2(Object obj, boolean z, int i10, int i11, int i12) {
        this.f8448i = i12;
        this.f8452o = obj;
        this.f8449l = z;
        this.f8450m = i10;
        this.f8451n = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8448i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return StreamSelectorKt.SourceBadgeView$lambda$0((SourceBadge) this.f8452o, this.f8449l, this.f8450m, this.f8451n, (Composer) obj, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj2).intValue();
                return ArvioLoadingScreenKt.ArvioLoadingScreen$lambda$4((Modifier) this.f8452o, this.f8449l, this.f8450m, this.f8451n, (Composer) obj, iIntValue2);
            default:
                int iIntValue3 = ((Integer) obj2).intValue();
                return StreamSelectorKt.OledTextBadge$lambda$1((String) this.f8452o, this.f8449l, this.f8450m, this.f8451n, (Composer) obj, iIntValue3);
        }
    }
}
