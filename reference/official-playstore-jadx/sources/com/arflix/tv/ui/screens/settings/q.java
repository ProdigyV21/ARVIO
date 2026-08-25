package com.arflix.tv.ui.screens.settings;

import androidx.compose.runtime.MutableState;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class q implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10811i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f10812l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MutableState f10813m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ MutableState f10814n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ MutableState f10815o;

    public /* synthetic */ q(MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4) {
        this.f10811i = 2;
        this.f10813m = mutableState;
        this.f10814n = mutableState2;
        this.f10815o = mutableState3;
        this.f10812l = mutableState4;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f10811i) {
            case 0:
                return SettingsScreenKt.SettingsScreen$lambda$241$29$0((SettingsViewModel) this.f10812l, this.f10813m, this.f10814n, this.f10815o);
            case 1:
                return SettingsScreenKt.SettingsScreen$lambda$241$46$0((SettingsViewModel) this.f10812l, this.f10813m, this.f10814n, this.f10815o);
            default:
                return SettingsScreenKt.SettingsScreen$lambda$241$56$0(this.f10813m, this.f10814n, this.f10815o, (MutableState) this.f10812l);
        }
    }

    public /* synthetic */ q(SettingsViewModel settingsViewModel, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, int i10) {
        this.f10811i = i10;
        this.f10812l = settingsViewModel;
        this.f10813m = mutableState;
        this.f10814n = mutableState2;
        this.f10815o = mutableState3;
    }
}
