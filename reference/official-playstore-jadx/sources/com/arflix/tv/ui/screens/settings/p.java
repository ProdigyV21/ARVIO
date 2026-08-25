package com.arflix.tv.ui.screens.settings;

import androidx.compose.runtime.MutableState;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class p implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10777i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableState f10778l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MutableState f10779m;

    public /* synthetic */ p(int i10, MutableState mutableState, MutableState mutableState2) {
        this.f10777i = i10;
        this.f10778l = mutableState;
        this.f10779m = mutableState2;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f10777i) {
            case 0:
                return SettingsScreenKt.SettingsScreen$lambda$241$20$0(this.f10778l, this.f10779m);
            case 1:
                return SettingsScreenKt.SettingsScreen$lambda$241$50$0(this.f10778l, this.f10779m);
            case 2:
                return SettingsScreenKt.CatalogsSettings$lambda$6$0$2$0(this.f10778l, this.f10779m);
            case 3:
                return SettingsScreenKt.CatalogsSettings$lambda$7$0$2$0(this.f10778l, this.f10779m);
            default:
                return SettingsScreenKt.IptvSettings$lambda$8$0$2$0(this.f10778l, this.f10779m);
        }
    }
}
