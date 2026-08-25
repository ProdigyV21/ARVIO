package com.arflix.tv.ui.screens.home;

import androidx.compose.runtime.MutableState;
import coil.compose.AsyncImagePainter;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class a0 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9071i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableState f9072l;

    public /* synthetic */ a0(MutableState mutableState, int i10) {
        this.f9071i = i10;
        this.f9072l = mutableState;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f9071i) {
            case 0:
                return HomeScreenKt.HomeBackdropCrossfade$lambda$13$1$2$0(this.f9072l, (AsyncImagePainter.State.Success) obj);
            default:
                return HomeScreenKt.HomeScreen$lambda$77$6$3$0(this.f9072l, ((Boolean) obj).booleanValue());
        }
    }
}
