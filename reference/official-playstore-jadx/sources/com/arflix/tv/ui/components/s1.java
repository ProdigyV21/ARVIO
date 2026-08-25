package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class s1 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8360i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ float f8361l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Modifier f8362m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f8363n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f8364o;

    public /* synthetic */ s1(float f10, Modifier modifier, int i10, int i11, int i12) {
        this.f8360i = i12;
        this.f8361l = f10;
        this.f8362m = modifier;
        this.f8363n = i10;
        this.f8364o = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8360i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                float f10 = this.f8361l;
                return PremiumEffectsKt.GradientSweepLine_rAjV9yQ$lambda$2(this.f8362m, f10, this.f8363n, this.f8364o, (Composer) obj, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj2).intValue();
                return SkeletonLoaderKt.SkeletonMediaCard__orJrPs$lambda$1(this.f8361l, this.f8362m, this.f8363n, this.f8364o, (Composer) obj, iIntValue2);
            default:
                int iIntValue3 = ((Integer) obj2).intValue();
                return SkeletonLoaderKt.SkeletonPosterCard__orJrPs$lambda$1(this.f8361l, this.f8362m, this.f8363n, this.f8364o, (Composer) obj, iIntValue3);
        }
    }

    public /* synthetic */ s1(Modifier modifier, float f10, int i10, int i11) {
        this.f8360i = 0;
        this.f8362m = modifier;
        this.f8361l = f10;
        this.f8363n = i10;
        this.f8364o = i11;
    }
}
