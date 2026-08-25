package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class t implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8378i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f8379l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f8380m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f8381n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f8382o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f8383p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f8384q;

    public /* synthetic */ t(Modifier modifier, boolean z, boolean z5, r7.q qVar, int i10, int i11) {
        this.f8379l = modifier;
        this.f8380m = z;
        this.f8381n = z5;
        this.f8384q = qVar;
        this.f8382o = i10;
        this.f8383p = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8378i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return CardLayoutModeKt.CatalogueRowLayoutToggleButton$lambda$3((String) this.f8384q, (Modifier) this.f8379l, this.f8380m, this.f8381n, this.f8382o, this.f8383p, (Composer) obj, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj2).intValue();
                return PremiumEffectsKt.GlassmorphicCard$lambda$1((Modifier) this.f8379l, this.f8380m, this.f8381n, (r7.q) this.f8384q, this.f8382o, this.f8383p, (Composer) obj, iIntValue2);
            default:
                int iIntValue3 = ((Integer) obj2).intValue();
                return TextInputModalKt.ActionButton$lambda$1((String) this.f8384q, this.f8380m, this.f8381n, (r7.a) this.f8379l, this.f8382o, this.f8383p, (Composer) obj, iIntValue3);
        }
    }

    public /* synthetic */ t(String str, Modifier modifier, boolean z, boolean z5, int i10, int i11) {
        this.f8384q = str;
        this.f8379l = modifier;
        this.f8380m = z;
        this.f8381n = z5;
        this.f8382o = i10;
        this.f8383p = i11;
    }

    public /* synthetic */ t(String str, boolean z, boolean z5, r7.a aVar, int i10, int i11) {
        this.f8384q = str;
        this.f8380m = z;
        this.f8381n = z5;
        this.f8379l = aVar;
        this.f8382o = i10;
        this.f8383p = i11;
    }
}
