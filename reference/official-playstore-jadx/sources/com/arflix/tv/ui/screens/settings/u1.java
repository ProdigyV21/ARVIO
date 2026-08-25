package com.arflix.tv.ui.screens.settings;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class u1 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10992i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ SettingsUiState f10993l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ SettingsViewModel f10994m;

    public /* synthetic */ u1(SettingsUiState settingsUiState, SettingsViewModel settingsViewModel, int i10) {
        this.f10992i = i10;
        this.f10993l = settingsUiState;
        this.f10994m = settingsViewModel;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f10992i) {
            case 0:
                return SettingsScreenKt.MobileSettingsSubPage$lambda$6$20$0(this.f10993l, this.f10994m, ((Integer) obj).intValue());
            case 1:
                return SettingsScreenKt.MobileSettingsSubPage$lambda$6$21$0(this.f10993l, this.f10994m, ((Integer) obj).intValue());
            case 2:
                return SettingsScreenKt.MobileSettingsSubPage$lambda$6$22$0(this.f10993l, this.f10994m, ((Integer) obj).intValue());
            case 3:
                return SettingsScreenKt.MobileSettingsSubPage$lambda$6$23$0(this.f10993l, this.f10994m, ((Integer) obj).intValue());
            case 4:
                return SettingsScreenKt.MobileSettingsSubPage$lambda$6$28$0(this.f10994m, this.f10993l, (String) obj);
            case 5:
                return SettingsScreenKt.MobileSettingsSubPage$lambda$6$29$0(this.f10994m, this.f10993l, (String) obj);
            case 6:
                return SettingsScreenKt.MobileSettingsSubPage$lambda$6$30$0(this.f10994m, this.f10993l, (String) obj);
            default:
                return SettingsScreenKt.MobileCloudAccountSubPage$lambda$12$0(this.f10993l, this.f10994m, (r7.a) obj);
        }
    }

    public /* synthetic */ u1(SettingsViewModel settingsViewModel, SettingsUiState settingsUiState, int i10) {
        this.f10992i = i10;
        this.f10994m = settingsViewModel;
        this.f10993l = settingsUiState;
    }
}
