package com.arflix.tv.ui.components;

import androidx.compose.runtime.MutableState;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class u0 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8415i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.a f8416l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MutableState f8417m;

    public /* synthetic */ u0(r7.a aVar, MutableState mutableState, int i10) {
        this.f8415i = i10;
        this.f8416l = aVar;
        this.f8417m = mutableState;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        int i10 = this.f8415i;
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        switch (i10) {
            case 0:
                return MediaCardKt.MediaCard_L_7BDBA$lambda$15$0$0(this.f8416l, this.f8417m, zBooleanValue);
            default:
                return MediaCardKt.PosterCard_iWtaglI$lambda$8$0$0(this.f8416l, this.f8417m, zBooleanValue);
        }
    }
}
