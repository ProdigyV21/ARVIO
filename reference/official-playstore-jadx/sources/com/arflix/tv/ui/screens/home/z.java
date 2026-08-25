package com.arflix.tv.ui.screens.home;

import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.foundation.pager.PagerScope;
import androidx.compose.foundation.pager.PagerState;
import androidx.compose.runtime.Composer;
import com.arflix.tv.data.model.Profile;
import com.arflix.tv.navigation.AppNavigationKt;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class z implements r7.r {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9339i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f9340l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f9341m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f9342n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f9343o;

    public /* synthetic */ z(Object obj, Object obj2, Object obj3, Object obj4, int i10) {
        this.f9339i = i10;
        this.f9340l = obj;
        this.f9341m = obj2;
        this.f9342n = obj3;
        this.f9343o = obj4;
    }

    @Override // r7.r
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        switch (this.f9339i) {
            case 0:
                return HomeScreenKt.MobileHeroCarousel$lambda$6$1((List) this.f9340l, (r7.r) this.f9341m, (Map) this.f9342n, (PagerState) this.f9343o, (PagerScope) obj, ((Integer) obj2).intValue(), (Composer) obj3, ((Integer) obj4).intValue());
            default:
                return AppNavigationKt.AppNavigation$lambda$9$0$14((r7.a) this.f9340l, (Profile) this.f9341m, (androidx.navigation.l0) this.f9342n, (r7.l) this.f9343o, (AnimatedContentScope) obj, (androidx.navigation.i) obj2, (Composer) obj3, ((Integer) obj4).intValue());
        }
    }
}
