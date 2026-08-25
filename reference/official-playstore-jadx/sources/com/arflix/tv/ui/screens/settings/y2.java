package com.arflix.tv.ui.screens.settings;

import androidx.compose.runtime.MutableState;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class y2 implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11131i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ SettingsViewModel f11132l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MutableState f11133m;

    public /* synthetic */ y2(SettingsViewModel settingsViewModel, MutableState mutableState, int i10) {
        this.f11131i = i10;
        this.f11132l = settingsViewModel;
        this.f11133m = mutableState;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f11131i) {
            case 0:
                return SettingsScreenKt.SettingsScreen$lambda$241$88$0(this.f11132l, this.f11133m);
            case 1:
                return SettingsScreenKt.MobileCloudAccountSubPage$lambda$7$0(this.f11132l, this.f11133m);
            case 2:
                return SettingsScreenKt.SettingsScreen$lambda$241$90$0(this.f11132l, this.f11133m);
            case 3:
                return SettingsScreenKt.MobileCloudAccountSubPage$lambda$9$0(this.f11132l, this.f11133m);
            default:
                return SettingsScreenKt.SettingsScreen$lambda$241$94$0(this.f11132l, this.f11133m);
        }
    }
}
