package com.arflix.tv.ui.screens.player;

import android.app.Activity;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusState;
import androidx.media3.exoplayer.ExoPlayer;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class y0 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9824i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f9825l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f9826m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f9827n;

    public /* synthetic */ y0(Object obj, Object obj2, Object obj3, int i10) {
        this.f9824i = i10;
        this.f9825l = obj;
        this.f9826m = obj2;
        this.f9827n = obj3;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f9824i) {
            case 0:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$9$0$1$1$8$0((r7.a) this.f9825l, (MutableState) this.f9826m, (MutableState) this.f9827n, (FocusState) obj);
            default:
                return PlayerScreenKt.PlayerScreen$lambda$317$0((androidx.lifecycle.y) this.f9825l, (Activity) this.f9826m, (ExoPlayer) this.f9827n, (DisposableEffectScope) obj);
        }
    }
}
