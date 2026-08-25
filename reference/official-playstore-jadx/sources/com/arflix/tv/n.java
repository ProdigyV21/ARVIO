package com.arflix.tv;

import androidx.navigation.l0;
import com.arflix.tv.navigation.AppNavigationKt;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class n implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7767i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ l0 f7768l;

    public /* synthetic */ n(l0 l0Var, int i10) {
        this.f7767i = i10;
        this.f7768l = l0Var;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f7767i) {
            case 0:
                return MainActivityKt.ArflixApp$lambda$20$1$0(this.f7768l, (String) obj);
            case 1:
                return AppNavigationKt.AppNavigation$lambda$9$0$19$4$0(this.f7768l, (String) obj);
            case 2:
                return AppNavigationKt.AppNavigation$lambda$3$0(this.f7768l, (String) obj);
            default:
                return AppNavigationKt.AppNavigation$lambda$9$0$1$1$0(this.f7768l, (String) obj);
        }
    }
}
