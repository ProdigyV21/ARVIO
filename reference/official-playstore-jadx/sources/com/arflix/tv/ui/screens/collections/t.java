package com.arflix.tv.ui.screens.collections;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import com.arflix.tv.ui.screens.profile.PinEntryDialogKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class t implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8678i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f8679l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Modifier f8680m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.a f8681n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f8682o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f8683p;

    public /* synthetic */ t(String str, Modifier modifier, r7.a aVar, int i10, int i11, int i12) {
        this.f8678i = i12;
        this.f8679l = str;
        this.f8680m = modifier;
        this.f8681n = aVar;
        this.f8682o = i10;
        this.f8683p = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8678i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return VideoHeroKt.VideoHero$lambda$6(this.f8679l, this.f8680m, this.f8681n, this.f8682o, this.f8683p, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return PinEntryDialogKt.PinKeyButton$lambda$4(this.f8679l, this.f8680m, this.f8681n, this.f8682o, this.f8683p, (Composer) obj, iIntValue2);
        }
    }
}
