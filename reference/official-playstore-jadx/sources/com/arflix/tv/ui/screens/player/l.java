package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class l implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9618i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ State f9619l;

    public /* synthetic */ l(State state, int i10) {
        this.f9618i = i10;
        this.f9619l = state;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f9618i) {
            case 0:
                return PlayerScreenKt.ErrorButton$lambda$2$0(this.f9619l, (GraphicsLayerScope) obj);
            case 1:
                return PlayerScreenKt.PulsingLogo$lambda$3$0$0$0(this.f9619l, (DrawScope) obj);
            case 2:
                return PlayerScreenKt.PulsingLogo$lambda$3$0$1$0(this.f9619l, (GraphicsLayerScope) obj);
            case 3:
                return PlayerScreenKt.PlayerIconButton_xI7PXdU$lambda$12$0(this.f9619l, (GraphicsLayerScope) obj);
            default:
                return SkipIntroButtonKt.SkipIntroButton$lambda$11$4$0(this.f9619l, (GraphicsLayerScope) obj);
        }
    }
}
