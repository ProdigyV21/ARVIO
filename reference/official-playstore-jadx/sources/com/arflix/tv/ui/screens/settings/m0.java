package com.arflix.tv.ui.screens.settings;

import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class m0 implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10676i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ SettingsUiState f10677l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.l f10678m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.a f10679n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ SettingsViewModel f10680o;

    public /* synthetic */ m0(SettingsUiState settingsUiState, r7.l lVar, r7.a aVar, SettingsViewModel settingsViewModel, int i10) {
        this.f10676i = i10;
        this.f10677l = settingsUiState;
        this.f10678m = lVar;
        this.f10679n = aVar;
        this.f10680o = settingsViewModel;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        switch (this.f10676i) {
            case 0:
                int iIntValue = ((Integer) obj3).intValue();
                return SettingsScreenKt.MobileSettingsMainPage$lambda$2$0$2(this.f10677l, this.f10678m, this.f10679n, this.f10680o, (LazyItemScope) obj, (Composer) obj2, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj3).intValue();
                return SettingsScreenKt.MobileSettingsMainPage$lambda$2$0$2$0(this.f10677l, this.f10678m, this.f10679n, this.f10680o, (ColumnScope) obj, (Composer) obj2, iIntValue2);
        }
    }
}
