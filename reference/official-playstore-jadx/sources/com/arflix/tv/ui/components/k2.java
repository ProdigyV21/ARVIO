package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import com.arflix.tv.ui.screens.tv.live.QuickZapOverlayKt;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class k2 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8137i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f8138l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f8139m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Modifier f8140n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f8141o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f8142p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f8143q;

    public /* synthetic */ k2(int i10, SkeletonCardType skeletonCardType, boolean z, Modifier modifier, int i11, int i12) {
        this.f8138l = i10;
        this.f8143q = skeletonCardType;
        this.f8139m = z;
        this.f8140n = modifier;
        this.f8141o = i11;
        this.f8142p = i12;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8137i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return SkeletonLoaderKt.SkeletonCategoryRow$lambda$1(this.f8138l, (SkeletonCardType) this.f8143q, this.f8139m, this.f8140n, this.f8141o, this.f8142p, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return QuickZapOverlayKt.CategorySidebarPanel$lambda$1((List) this.f8143q, this.f8138l, this.f8139m, this.f8140n, this.f8141o, this.f8142p, (Composer) obj, iIntValue2);
        }
    }

    public /* synthetic */ k2(List list, int i10, boolean z, Modifier modifier, int i11, int i12) {
        this.f8143q = list;
        this.f8138l = i10;
        this.f8139m = z;
        this.f8140n = modifier;
        this.f8141o = i11;
        this.f8142p = i12;
    }
}
