package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableState;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class p implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11670i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f11671l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f11672m;

    public /* synthetic */ p(Object obj, Object obj2, int i10) {
        this.f11670i = i10;
        this.f11671l = obj;
        this.f11672m = obj2;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f11670i) {
            case 0:
                return CategorySidebarKt.CategorySidebar$lambda$38$0$0((LiveCategoryTree) this.f11671l, (r7.l) this.f11672m);
            case 1:
                return FullscreenHudKt.FullscreenHud$lambda$21$0$5$1$1$2$0((r7.a) this.f11671l, (MutableState) this.f11672m);
            default:
                return LiveTvEnhancementsKt.ProviderSelector$lambda$6$0$4$0((r7.l) this.f11672m, (TvProviderFilter) this.f11671l);
        }
    }

    public /* synthetic */ p(r7.l lVar, TvProviderFilter tvProviderFilter) {
        this.f11670i = 2;
        this.f11672m = lVar;
        this.f11671l = tvProviderFilter;
    }
}
