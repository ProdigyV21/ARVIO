package com.arflix.tv.ui.screens.player;

import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class g0 implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9518i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f9519l;

    public /* synthetic */ g0(String str, int i10) {
        this.f9518i = i10;
        this.f9519l = str;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        switch (this.f9518i) {
            case 0:
                int iIntValue = ((Integer) obj3).intValue();
                return PlayerScreenKt.PlayerScreen$lambda$362$2$17(this.f9519l, (AnimatedVisibilityScope) obj, (Composer) obj2, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj3).intValue();
                return PlayerScreenKt.SubtitleMenu$lambda$13$2$2$0$1$0(this.f9519l, (LazyItemScope) obj, (Composer) obj2, iIntValue2);
        }
    }
}
