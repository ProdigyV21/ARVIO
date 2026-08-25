package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class u2 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8423i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ SourcePresentation f8424l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f8425m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f8426n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ r7.a f8427o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f8428p;

    public /* synthetic */ u2(SourcePresentation sourcePresentation, boolean z, boolean z5, r7.a aVar, int i10, int i11) {
        this.f8423i = i11;
        this.f8424l = sourcePresentation;
        this.f8425m = z;
        this.f8426n = z5;
        this.f8427o = aVar;
        this.f8428p = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8423i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return StreamSelectorKt.GlassyStreamCard$lambda$3(this.f8424l, this.f8425m, this.f8426n, this.f8427o, this.f8428p, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return StreamSelectorKt.OledSourceRow$lambda$3(this.f8424l, this.f8425m, this.f8426n, this.f8427o, this.f8428p, (Composer) obj, iIntValue2);
        }
    }
}
