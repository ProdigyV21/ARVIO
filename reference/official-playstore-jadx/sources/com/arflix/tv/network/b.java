package com.arflix.tv.network;

import android.content.Context;
import androidx.compose.foundation.pager.PagerState;
import com.arflix.tv.ui.screens.home.HomeScreenKt;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class b implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7771i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f7772l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f7773m;

    public /* synthetic */ b(Object obj, int i10, int i11) {
        this.f7771i = i11;
        this.f7773m = obj;
        this.f7772l = i10;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f7771i) {
            case 0:
                return OkHttpProvider.createCoilImageLoader$lambda$0((Context) this.f7773m, this.f7772l);
            default:
                return Float.valueOf(HomeScreenKt.MobileHeroCarousel$lambda$6$1$4$0((PagerState) this.f7773m, this.f7772l));
        }
    }
}
