package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import com.arflix.tv.data.model.Profile;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class r1 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8333i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Modifier f8334l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ long f8335m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ float f8336n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f8337o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f8338p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f8339q;

    public /* synthetic */ r1(Modifier modifier, long j10, float f10, r7.q qVar, int i10, int i11) {
        this.f8334l = modifier;
        this.f8335m = j10;
        this.f8336n = f10;
        this.f8339q = qVar;
        this.f8337o = i10;
        this.f8338p = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8333i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return PremiumEffectsKt.PulsingGlow_sW7UJKQ$lambda$3(this.f8334l, this.f8335m, this.f8336n, (r7.q) this.f8339q, this.f8337o, this.f8338p, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return ProfileAvatarVisualKt.ProfileAvatarVisual_xjFF_98$lambda$6((Profile) this.f8339q, this.f8334l, this.f8335m, this.f8336n, this.f8337o, this.f8338p, (Composer) obj, iIntValue2);
        }
    }

    public /* synthetic */ r1(Profile profile, Modifier modifier, long j10, float f10, int i10, int i11) {
        this.f8339q = profile;
        this.f8334l = modifier;
        this.f8335m = j10;
        this.f8336n = f10;
        this.f8337o = i10;
        this.f8338p = i11;
    }
}
