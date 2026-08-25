package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.foundation.lazy.LazyListState;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class u implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11788i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f11789l;

    public /* synthetic */ u(Object obj, int i10) {
        this.f11788i = i10;
        this.f11789l = obj;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f11788i) {
            case 0:
                return Float.valueOf(((Float) this.f11789l).floatValue());
            case 1:
                return Float.valueOf(((Float) this.f11789l).floatValue());
            case 2:
                return Float.valueOf(QuickZapOverlayKt.FocusedChannelSlot$lambda$0$2$1$0((Float) this.f11789l));
            default:
                return EpgGridKt$EpgGrid$15$1.invokeSuspend$lambda$0((LazyListState) this.f11789l);
        }
    }
}
