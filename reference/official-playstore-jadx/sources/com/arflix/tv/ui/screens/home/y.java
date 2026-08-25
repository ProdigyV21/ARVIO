package com.arflix.tv.ui.screens.home;

import androidx.compose.runtime.MutableState;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class y implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9337i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f9338l;

    public /* synthetic */ y(Object obj, int i10) {
        this.f9337i = i10;
        this.f9338l = obj;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f9337i) {
            case 0:
                return HomeScreenKt.MobileHeroCarousel$lambda$6$0$0$0((r7.a) this.f9338l);
            case 1:
                return HomeScreenKt.MobileHeroCarousel$lambda$6$0$2$0((r7.a) this.f9338l);
            default:
                return HomeScreenKt.HomeScreen$lambda$77$8$0$0((MutableState) this.f9338l);
        }
    }
}
