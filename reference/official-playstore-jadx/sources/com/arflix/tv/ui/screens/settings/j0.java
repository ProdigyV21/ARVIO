package com.arflix.tv.ui.screens.settings;

import com.arflix.tv.data.model.CatalogConfig;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class j0 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10582i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ SettingsViewModel f10583l;

    public /* synthetic */ j0(SettingsViewModel settingsViewModel, int i10) {
        this.f10582i = i10;
        this.f10583l = settingsViewModel;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f10582i) {
            case 0:
                return SettingsScreenKt.SettingsScreen$lambda$241$17$1$0$8$0(this.f10583l, ((Boolean) obj).booleanValue());
            case 1:
                return SettingsScreenKt.SettingsScreen$lambda$241$17$1$0$9$0(this.f10583l, ((Boolean) obj).booleanValue());
            case 2:
                return SettingsScreenKt.MobileSettingsSubPage$lambda$6$6$0(this.f10583l, (String) obj);
            case 3:
                return SettingsScreenKt.MobileSettingsSubPage$lambda$6$7$0(this.f10583l, (String) obj);
            case 4:
                return SettingsScreenKt.MobileSettingsSubPage$lambda$6$8$0(this.f10583l, (String) obj);
            case 5:
                return SettingsScreenKt.MobileSettingsSubPage$lambda$6$9$0(this.f10583l, (String) obj);
            case 6:
                return SettingsScreenKt.MobileSettingsSubPage$lambda$6$17$0(this.f10583l, (CatalogConfig) obj);
            case 7:
                return SettingsScreenKt.MobileSettingsSubPage$lambda$6$18$0(this.f10583l, (CatalogConfig) obj);
            case 8:
                return SettingsScreenKt.MobileSettingsSubPage$lambda$6$19$0(this.f10583l, (CatalogConfig) obj);
            default:
                return SettingsScreenKt.MobileSettingsSubPage$lambda$6$27$0(this.f10583l, (String) obj);
        }
    }
}
