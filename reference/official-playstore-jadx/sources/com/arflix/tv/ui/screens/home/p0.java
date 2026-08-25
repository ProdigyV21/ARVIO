package com.arflix.tv.ui.screens.home;

import com.arflix.tv.data.model.MediaItem;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class p0 implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9258i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f9259l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MediaItem f9260m;

    public /* synthetic */ p0(Object obj, MediaItem mediaItem, int i10) {
        this.f9258i = i10;
        this.f9259l = obj;
        this.f9260m = mediaItem;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f9258i) {
            case 0:
                return HomeScreenKt.HomeScreen$lambda$77$12$0$2$0((HomeViewModel) this.f9259l, this.f9260m);
            case 1:
                return HomeScreenKt.HomeScreen$lambda$77$12$0$3$0((HomeViewModel) this.f9259l, this.f9260m);
            case 2:
                return HomeScreenKt.HomeScreen$lambda$77$12$0$4$0((HomeViewModel) this.f9259l, this.f9260m);
            default:
                return HomeScreenKt.MobileHeroCarousel$lambda$6$1$6$0((r7.r) this.f9259l, this.f9260m);
        }
    }
}
