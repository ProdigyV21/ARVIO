package com.arflix.tv.ui.screens.tv;

import ka.o2;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class e0 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11224i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ TvViewModel f11225l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ o2 f11226m;

    public /* synthetic */ e0(TvViewModel tvViewModel, o2 o2Var, int i10) {
        this.f11224i = i10;
        this.f11225l = tvViewModel;
        this.f11226m = o2Var;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f11224i) {
            case 0:
                return TvViewModel.startFullEpgWarmup$lambda$1$0(this.f11225l, this.f11226m, (Throwable) obj);
            case 1:
                return TvViewModel.startFullEpgWarmup$lambda$4$0(this.f11225l, this.f11226m, (Throwable) obj);
            case 2:
                return TvViewModel.startCompleteEpgBackfill$lambda$4$0(this.f11225l, this.f11226m, (Throwable) obj);
            case 3:
                return TvViewModel.scheduleDeferredCompleteEpgBackfill$lambda$0$0(this.f11225l, this.f11226m, (Throwable) obj);
            default:
                return TvViewModel.startVisibleEpgDrain$lambda$0$0(this.f11225l, this.f11226m, (Throwable) obj);
        }
    }
}
