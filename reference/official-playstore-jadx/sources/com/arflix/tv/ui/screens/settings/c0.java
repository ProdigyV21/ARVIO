package com.arflix.tv.ui.screens.settings;

import android.content.Context;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class c0 implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10351i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Context f10352l;

    public /* synthetic */ c0(Context context, int i10) {
        this.f10351i = i10;
        this.f10352l = context;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f10351i) {
            case 0:
                return SettingsScreenKt.SettingsScreen$lambda$241$17$1$0$105$0(this.f10352l);
            case 1:
                return SettingsScreenKt.SettingsScreen$lambda$241$17$1$0$106$0(this.f10352l);
            case 2:
                return SettingsScreenKt.AccountsSettings$lambda$4$10$0(this.f10352l);
            case 3:
                return SettingsScreenKt.MobileCloudAccountSubPage$lambda$14$0$0(this.f10352l);
            case 4:
                return SettingsScreenKt.MobilePrivacySubPage$lambda$1$0$0(this.f10352l);
            default:
                return SettingsScreenKt.SettingsScreen$lambda$241$101$0(this.f10352l);
        }
    }
}
