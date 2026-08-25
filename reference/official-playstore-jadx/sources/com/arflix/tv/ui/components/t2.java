package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class t2 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8402i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f8403l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f8404m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f8405n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ r7.a f8406o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f8407p;

    public /* synthetic */ t2(String str, boolean z, boolean z5, r7.a aVar, int i10, int i11) {
        this.f8402i = i11;
        this.f8403l = str;
        this.f8404m = z;
        this.f8405n = z5;
        this.f8406o = aVar;
        this.f8407p = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8402i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return StreamSelectorKt.FilterTab$lambda$2(this.f8403l, this.f8404m, this.f8405n, this.f8406o, this.f8407p, (Composer) obj, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj2).intValue();
                return StreamSelectorKt.AddonRailItem$lambda$3(this.f8403l, this.f8404m, this.f8405n, this.f8406o, this.f8407p, (Composer) obj, iIntValue2);
            default:
                int iIntValue3 = ((Integer) obj2).intValue();
                return StreamSelectorKt.SourceFilterChip$lambda$2(this.f8403l, this.f8404m, this.f8405n, this.f8406o, this.f8407p, (Composer) obj, iIntValue3);
        }
    }
}
