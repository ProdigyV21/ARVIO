package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.Composer;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class i implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11503i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f11504l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f11505m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f11506n;

    public /* synthetic */ i(String str, int i10, int i11, boolean z) {
        this.f11503i = i11;
        this.f11504l = str;
        this.f11505m = z;
        this.f11506n = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        int i10 = this.f11503i;
        Composer composer = (Composer) obj;
        int iIntValue = ((Integer) obj2).intValue();
        switch (i10) {
            case 0:
                return CategorySidebarKt.SectionHeader$lambda$0(this.f11504l, this.f11505m, this.f11506n, composer, iIntValue);
            case 1:
                return CategorySidebarKt.SectionHeader$lambda$2(this.f11504l, this.f11505m, this.f11506n, composer, iIntValue);
            default:
                return QuickZapOverlayKt.FocusedCategoryRow$lambda$1(this.f11504l, this.f11505m, this.f11506n, composer, iIntValue);
        }
    }
}
