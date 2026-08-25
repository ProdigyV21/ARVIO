package com.arflix.tv.ui.screens.settings;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class g implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10475i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableIntState f10476l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MutableState f10477m;

    public /* synthetic */ g(MutableIntState mutableIntState, MutableState mutableState, int i10) {
        this.f10475i = i10;
        this.f10476l = mutableIntState;
        this.f10477m = mutableState;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f10475i) {
            case 0:
                return SettingsScreenKt.SettingsScreen$lambda$241$5$0(this.f10476l, this.f10477m);
            case 1:
                return SettingsScreenKt.SettingsScreen$lambda$241$17$1$0$47$0(this.f10476l, this.f10477m);
            default:
                return SettingsScreenKt.SettingsScreen$lambda$241$17$1$0$56$0(this.f10476l, this.f10477m);
        }
    }
}
