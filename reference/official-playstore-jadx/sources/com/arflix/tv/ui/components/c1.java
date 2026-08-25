package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class c1 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7861i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f7862l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f7863m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f7864n;

    public /* synthetic */ c1(String str, String str2, int i10, int i11) {
        this.f7861i = i11;
        this.f7862l = str;
        this.f7863m = str2;
        this.f7864n = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        int i10 = this.f7861i;
        Composer composer = (Composer) obj;
        int iIntValue = ((Integer) obj2).intValue();
        switch (i10) {
            case 0:
                return MobileHeroBannerKt.BannerMeta$lambda$0(this.f7862l, this.f7863m, this.f7864n, composer, iIntValue);
            case 1:
                return MobileHeroBannerKt.BannerMeta$lambda$2(this.f7862l, this.f7863m, this.f7864n, composer, iIntValue);
            default:
                return StreamSelectorKt.RailMetric$lambda$1(this.f7862l, this.f7863m, this.f7864n, composer, iIntValue);
        }
    }
}
