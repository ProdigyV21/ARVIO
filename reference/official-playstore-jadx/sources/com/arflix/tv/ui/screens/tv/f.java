package com.arflix.tv.ui.screens.tv;

import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.MutableState;
import androidx.media3.ui.PlayerView;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class f implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11227i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableState f11228l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MutableState f11229m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f11230n;

    public /* synthetic */ f(MutableState mutableState, MutableState mutableState2, MutableState mutableState3) {
        this.f11228l = mutableState;
        this.f11229m = mutableState2;
        this.f11230n = mutableState3;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f11227i) {
            case 0:
                return TvScreenKt.TvScreen$lambda$104$0((androidx.lifecycle.y) this.f11230n, this.f11228l, this.f11229m, (DisposableEffectScope) obj);
            default:
                return TvScreenKt.TvScreen$lambda$118$2$1$0$3$1$0(this.f11228l, this.f11229m, (MutableState) this.f11230n, (PlayerView) obj);
        }
    }

    public /* synthetic */ f(androidx.lifecycle.y yVar, MutableState mutableState, MutableState mutableState2) {
        this.f11230n = yVar;
        this.f11228l = mutableState;
        this.f11229m = mutableState2;
    }
}
