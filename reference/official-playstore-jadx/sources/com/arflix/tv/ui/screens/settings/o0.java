package com.arflix.tv.ui.screens.settings;

import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.runtime.Composer;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class o0 implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10764i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ SettingsUiState f10765l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ SettingsViewModel f10766m;

    public /* synthetic */ o0(SettingsUiState settingsUiState, SettingsViewModel settingsViewModel, int i10) {
        this.f10764i = i10;
        this.f10765l = settingsUiState;
        this.f10766m = settingsViewModel;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        int i10 = this.f10764i;
        ColumnScope columnScope = (ColumnScope) obj;
        Composer composer = (Composer) obj2;
        int iIntValue = ((Integer) obj3).intValue();
        switch (i10) {
            case 0:
                return SettingsScreenKt.MobilePrivacySubPage$lambda$0(this.f10765l, this.f10766m, columnScope, composer, iIntValue);
            case 1:
                return SettingsScreenKt.MobileSettingsSubPage$lambda$6$4(this.f10765l, this.f10766m, columnScope, composer, iIntValue);
            default:
                return SettingsScreenKt.MobileSettingsSubPage$lambda$6$2(this.f10765l, this.f10766m, columnScope, composer, iIntValue);
        }
    }
}
