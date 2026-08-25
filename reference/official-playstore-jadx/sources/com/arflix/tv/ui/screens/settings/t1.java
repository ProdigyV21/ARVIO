package com.arflix.tv.ui.screens.settings;

import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.runtime.Composer;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class t1 implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10940i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ SettingsUiState f10941l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ SettingsViewModel f10942m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.a f10943n;

    public /* synthetic */ t1(SettingsUiState settingsUiState, SettingsViewModel settingsViewModel, r7.a aVar) {
        this.f10941l = settingsUiState;
        this.f10942m = settingsViewModel;
        this.f10943n = aVar;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        switch (this.f10940i) {
            case 0:
                int iIntValue = ((Integer) obj3).intValue();
                SettingsUiState settingsUiState = this.f10941l;
                SettingsViewModel settingsViewModel = this.f10942m;
                return SettingsScreenKt.MobileSettingsSubPage$lambda$6$5(settingsUiState, this.f10943n, settingsViewModel, (ColumnScope) obj, (Composer) obj2, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj3).intValue();
                return SettingsScreenKt.MobileSettingsSubPage$lambda$6$0(this.f10941l, this.f10942m, this.f10943n, (ColumnScope) obj, (Composer) obj2, iIntValue2);
        }
    }

    public /* synthetic */ t1(SettingsUiState settingsUiState, r7.a aVar, SettingsViewModel settingsViewModel) {
        this.f10941l = settingsUiState;
        this.f10943n = aVar;
        this.f10942m = settingsViewModel;
    }
}
