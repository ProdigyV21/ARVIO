package com.arflix.tv.ui.screens.settings;

import androidx.compose.runtime.MutableState;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class k implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10599i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableState f10600l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MutableState f10601m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ MutableState f10602n;

    public /* synthetic */ k(MutableState mutableState, MutableState mutableState2, MutableState mutableState3, int i10) {
        this.f10599i = i10;
        this.f10600l = mutableState;
        this.f10601m = mutableState2;
        this.f10602n = mutableState3;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f10599i) {
            case 0:
                return SettingsScreenKt.SettingsScreen$lambda$241$12$0(this.f10600l, this.f10601m, this.f10602n);
            case 1:
                return SettingsScreenKt.SettingsScreen$lambda$241$26$0(this.f10600l, this.f10601m, this.f10602n);
            case 2:
                return SettingsScreenKt.SettingsScreen$lambda$241$30$0(this.f10600l, this.f10601m, this.f10602n);
            case 3:
                return SettingsScreenKt.SettingsScreen$lambda$241$17$1$0$66$0(this.f10600l, this.f10601m, this.f10602n);
            case 4:
                return SettingsScreenKt.DiscordWebViewModal$lambda$18$1$0$0$1$0$0(this.f10600l, this.f10601m, this.f10602n);
            default:
                return SettingsScreenKt.DiscordWebViewModal$lambda$18$1$0$0$1$2$0(this.f10600l, this.f10601m, this.f10602n);
        }
    }
}
