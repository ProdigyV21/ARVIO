package com.arflix.tv.ui.screens.details;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.vector.ImageVector;
import com.arflix.tv.ui.screens.search.SearchScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class x implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9041i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f9042l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f9043m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f9044n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Modifier f9045o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ r7.a f9046p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ boolean f9047q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ int f9048r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ int f9049s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ Object f9050t;

    public /* synthetic */ x(ImageVector imageVector, String str, boolean z, boolean z5, boolean z10, Modifier modifier, r7.a aVar, int i10, int i11) {
        this.f9050t = imageVector;
        this.f9042l = str;
        this.f9043m = z;
        this.f9044n = z5;
        this.f9047q = z10;
        this.f9045o = modifier;
        this.f9046p = aVar;
        this.f9048r = i10;
        this.f9049s = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f9041i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return DetailsScreenKt.MobileActionButton$lambda$1((ImageVector) this.f9050t, this.f9042l, this.f9043m, this.f9044n, this.f9047q, this.f9045o, this.f9046p, this.f9048r, this.f9049s, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return SearchScreenKt.GlowChip$lambda$7(this.f9042l, this.f9043m, this.f9044n, this.f9045o, this.f9046p, this.f9047q, (r7.a) this.f9050t, this.f9048r, this.f9049s, (Composer) obj, iIntValue2);
        }
    }

    public /* synthetic */ x(String str, boolean z, boolean z5, Modifier modifier, r7.a aVar, boolean z10, r7.a aVar2, int i10, int i11) {
        this.f9042l = str;
        this.f9043m = z;
        this.f9044n = z5;
        this.f9045o = modifier;
        this.f9046p = aVar;
        this.f9047q = z10;
        this.f9050t = aVar2;
        this.f9048r = i10;
        this.f9049s = i11;
    }
}
