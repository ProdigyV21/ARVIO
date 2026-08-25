package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import com.arflix.tv.ui.screens.home.HomeScreenKt;
import com.arflix.tv.ui.screens.tv.live.MiniPlayerKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class n1 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8200i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Modifier f8201l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f8202m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f8203n;

    public /* synthetic */ n1(Modifier modifier, int i10, int i11, int i12) {
        this.f8200i = i12;
        this.f8201l = modifier;
        this.f8202m = i10;
        this.f8203n = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        int i10 = this.f8200i;
        Composer composer = (Composer) obj;
        int iIntValue = ((Integer) obj2).intValue();
        switch (i10) {
            case 0:
                return PlayerLoadingScreenKt.BufferingIndicator$lambda$2(this.f8201l, this.f8202m, this.f8203n, composer, iIntValue);
            case 1:
                return SkeletonLoaderKt.SkeletonCastCard$lambda$1(this.f8201l, this.f8202m, this.f8203n, composer, iIntValue);
            case 2:
                return SkeletonLoaderKt.SkeletonDetailsHero$lambda$1(this.f8201l, this.f8202m, this.f8203n, composer, iIntValue);
            case 3:
                return SkeletonLoaderKt.SkeletonEpisodeCard$lambda$1(this.f8201l, this.f8202m, this.f8203n, composer, iIntValue);
            case 4:
                return HomeScreenKt.PrimeLogo$lambda$1(this.f8201l, this.f8202m, this.f8203n, composer, iIntValue);
            default:
                return MiniPlayerKt.LiveBug$lambda$2(this.f8201l, this.f8202m, this.f8203n, composer, iIntValue);
        }
    }
}
