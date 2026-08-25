package com.arflix.tv.ui.screens.home;

import androidx.compose.animation.core.Animatable;
import androidx.compose.ui.graphics.GraphicsLayerScope;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class v implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9324i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Animatable f9325l;

    public /* synthetic */ v(Animatable animatable, int i10) {
        this.f9324i = i10;
        this.f9325l = animatable;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f9324i) {
            case 0:
                return HomeScreenKt.HomeBackdropCrossfade$lambda$13$1$1$0(this.f9325l, (GraphicsLayerScope) obj);
            case 1:
                return HomeScreenKt.HomeScreen$lambda$77$7$0(this.f9325l, (GraphicsLayerScope) obj);
            default:
                return HomeScreenKt.HomeScreen$lambda$77$9$0(this.f9325l, (GraphicsLayerScope) obj);
        }
    }
}
