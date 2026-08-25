package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import com.arflix.tv.data.model.Profile;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class x3 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8496i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Profile f8497l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Modifier f8498m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f8499n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f8500o;

    public /* synthetic */ x3(Modifier modifier, Profile profile, int i10, int i11) {
        this.f8498m = modifier;
        this.f8497l = profile;
        this.f8499n = i10;
        this.f8500o = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8496i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return TopBarClockKt.TopBarClock$lambda$5(this.f8498m, this.f8497l, this.f8499n, this.f8500o, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                Modifier modifier = this.f8498m;
                return TopBarClockKt.ProfileIndicator$lambda$1(this.f8497l, modifier, this.f8499n, this.f8500o, (Composer) obj, iIntValue2);
        }
    }

    public /* synthetic */ x3(Profile profile, Modifier modifier, int i10, int i11) {
        this.f8497l = profile;
        this.f8498m = modifier;
        this.f8499n = i10;
        this.f8500o = i11;
    }
}
