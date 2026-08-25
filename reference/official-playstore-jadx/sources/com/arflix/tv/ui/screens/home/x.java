package com.arflix.tv.ui.screens.home;

import com.arflix.tv.ui.screens.tv.live.LivePanesKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class x implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9335i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f9336l;

    public /* synthetic */ x(int i10, int i11) {
        this.f9335i = i11;
        this.f9336l = i10;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f9335i) {
            case 0:
                return Integer.valueOf(HomeScreenKt.MobileHeroCarousel$lambda$4$0(this.f9336l));
            default:
                return Float.valueOf(LivePanesKt.LoadingPane$lambda$0$1$0(this.f9336l));
        }
    }
}
