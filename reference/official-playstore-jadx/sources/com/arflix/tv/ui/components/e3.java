package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class e3 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7942i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Modifier f7943l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f7944m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f7945n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f7946o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f7947p;

    public /* synthetic */ e3(Modifier modifier, int i10, List list, int i11, int i12) {
        this.f7943l = modifier;
        this.f7944m = i10;
        this.f7947p = list;
        this.f7945n = i11;
        this.f7946o = i12;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f7942i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return StreamSelectorKt.OledBadgeFlow$lambda$1((SourcePresentation) this.f7947p, this.f7944m, this.f7943l, this.f7945n, this.f7946o, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return PremiumEffectsKt.FloatingParticles$lambda$3(this.f7943l, this.f7944m, (List) this.f7947p, this.f7945n, this.f7946o, (Composer) obj, iIntValue2);
        }
    }

    public /* synthetic */ e3(SourcePresentation sourcePresentation, int i10, Modifier modifier, int i11, int i12) {
        this.f7947p = sourcePresentation;
        this.f7944m = i10;
        this.f7943l = modifier;
        this.f7945n = i11;
        this.f7946o = i12;
    }
}
