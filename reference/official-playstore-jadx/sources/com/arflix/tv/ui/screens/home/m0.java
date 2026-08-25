package com.arflix.tv.ui.screens.home;

import com.arflix.tv.data.model.MediaItem;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class m0 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9206i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ HomeViewModel f9207l;

    public /* synthetic */ m0(HomeViewModel homeViewModel, int i10) {
        this.f9206i = i10;
        this.f9207l = homeViewModel;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f9206i) {
            case 0:
                return HomeScreenKt.HomeScreen$lambda$77$8$3$0(this.f9207l, (String) obj);
            case 1:
                return this.f9207l.getIptvStreamUrl(((Integer) obj).intValue());
            case 2:
                return Boolean.valueOf(this.f9207l.isSportsHomeItem((MediaItem) obj));
            case 3:
                return Boolean.valueOf(this.f9207l.isIptvItem((MediaItem) obj));
            case 4:
                return this.f9207l.getIptvChannelId((MediaItem) obj);
            case 5:
                return this.f9207l.getIptvStreamUrl(((Integer) obj).intValue());
            case 6:
                return Boolean.valueOf(HomeViewModel.prefetchHeroDetailsForFocusWindow$lambda$0(this.f9207l, (MediaItem) obj));
            case 7:
                return Boolean.valueOf(HomeViewModel.prefetchHeroDetailsForFocusWindow$lambda$1(this.f9207l, (MediaItem) obj));
            default:
                return Boolean.valueOf(HomeViewModel.prefetchHeroDetailsForFocusWindow$lambda$2(this.f9207l, (MediaItem) obj));
        }
    }
}
