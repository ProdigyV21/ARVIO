package com.arflix.tv.ui.screens.home;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class n0 implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9222i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ HomeViewModel f9223l;

    public /* synthetic */ n0(HomeViewModel homeViewModel, int i10) {
        this.f9222i = i10;
        this.f9223l = homeViewModel;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f9222i) {
            case 0:
                return HomeScreenKt.HomeScreen$lambda$77$11$0$0$0(this.f9223l);
            case 1:
                return HomeScreenKt.HomeScreen$lambda$77$13$0$0(this.f9223l);
            case 2:
                return HomeScreenKt.HomeScreen$lambda$77$14$0(this.f9223l);
            case 3:
                return HomeScreenKt.HomeScreen$lambda$77$15$0(this.f9223l);
            case 4:
                return HomeScreenKt.HomeScreen$lambda$77$16$0(this.f9223l);
            case 5:
                return HomeScreenKt.HomeScreen$lambda$77$17$0(this.f9223l);
            case 6:
                return HomeScreenKt.HomeScreen$lambda$77$18$0(this.f9223l);
            default:
                return HomeViewModel.imageLoader_delegate$lambda$0(this.f9223l);
        }
    }
}
