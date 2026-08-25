package com.arflix.tv.ui.screens.home;

import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.GraphicsLayerScope;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class j implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9161i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ State f9162l;

    public /* synthetic */ j(State state, int i10) {
        this.f9161i = i10;
        this.f9162l = state;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f9161i) {
            case 0:
                return HomeScreenKt.MobileHeroCarousel$lambda$6$1$7$0(this.f9162l, (GraphicsLayerScope) obj);
            default:
                return HomeScreenKt.HomeScreen$lambda$77$6$0$0(this.f9162l, (GraphicsLayerScope) obj);
        }
    }
}
