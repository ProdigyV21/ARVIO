package com.arflix.tv.ui.screens.home;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class h0 implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9148i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f9149l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ State f9150m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ State f9151n;

    public /* synthetic */ h0(Object obj, State state, State state2, int i10) {
        this.f9148i = i10;
        this.f9149l = obj;
        this.f9150m = state;
        this.f9151n = state2;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f9148i) {
            case 0:
                return HomeScreenKt.HomeScreen$lambda$45$0((MutableState) this.f9149l, (MutableState) this.f9150m, (MutableState) this.f9151n);
            case 1:
                return HomeScreenKt.HomeScreen$lambda$77$12$0$5$0((MutableState) this.f9149l, (MutableState) this.f9150m, (MutableState) this.f9151n);
            default:
                return HomeScreenKt$HomeScreen$17$1.invokeSuspend$lambda$0((HomeFocusState) this.f9149l, this.f9150m, this.f9151n);
        }
    }
}
