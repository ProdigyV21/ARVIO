package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.graphics.vector.ImageVector;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class k implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8119i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f8120l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f8121m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.a f8122n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ boolean f8123o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ boolean f8124p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f8125q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ int f8126r;

    public /* synthetic */ k(ImageVector imageVector, boolean z, boolean z5, boolean z10, r7.a aVar, int i10, int i11) {
        this.f8119i = 2;
        this.f8120l = imageVector;
        this.f8121m = z;
        this.f8123o = z5;
        this.f8124p = z10;
        this.f8122n = aVar;
        this.f8125q = i10;
        this.f8126r = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8119i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return AppUpdateModalKt.UpdateActionButton$lambda$1((String) this.f8120l, this.f8121m, this.f8122n, this.f8123o, this.f8124p, this.f8125q, this.f8126r, (Composer) obj, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj2).intValue();
                return SettingsScreenKt.UpdateActionButton$lambda$2((String) this.f8120l, this.f8121m, this.f8122n, this.f8123o, this.f8124p, this.f8125q, this.f8126r, (Composer) obj, iIntValue2);
            default:
                int iIntValue3 = ((Integer) obj2).intValue();
                return SettingsScreenKt.CatalogActionChip$lambda$6((ImageVector) this.f8120l, this.f8121m, this.f8123o, this.f8124p, this.f8122n, this.f8125q, this.f8126r, (Composer) obj, iIntValue3);
        }
    }

    public /* synthetic */ k(String str, boolean z, r7.a aVar, boolean z5, boolean z10, int i10, int i11, int i12) {
        this.f8119i = i12;
        this.f8120l = str;
        this.f8121m = z;
        this.f8122n = aVar;
        this.f8123o = z5;
        this.f8124p = z10;
        this.f8125q = i10;
        this.f8126r = i11;
    }
}
