package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.DisposableEffectScope;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class u0 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11790i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.l f11791l;

    public /* synthetic */ u0(r7.l lVar, int i10) {
        this.f11790i = i10;
        this.f11791l = lVar;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f11790i) {
            case 0:
                return FullscreenHudKt.FullscreenHud$lambda$6$0(this.f11791l, (DisposableEffectScope) obj);
            default:
                return LiveTvScreenKt.LiveTvScreen$lambda$148$0(this.f11791l, (DisposableEffectScope) obj);
        }
    }
}
