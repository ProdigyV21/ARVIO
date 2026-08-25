package com.arflix.tv.ui.screens.settings;

import androidx.compose.runtime.MutableState;
import com.arflix.tv.data.model.CatalogConfig;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class j implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10577i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ SettingsViewModel f10578l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MutableState f10579m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ MutableState f10580n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ MutableState f10581o;

    public /* synthetic */ j(SettingsViewModel settingsViewModel, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, int i10) {
        this.f10577i = i10;
        this.f10578l = settingsViewModel;
        this.f10579m = mutableState;
        this.f10580n = mutableState2;
        this.f10581o = mutableState3;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f10577i) {
            case 0:
                return SettingsScreenKt.SettingsScreen$lambda$241$10$0(this.f10578l, this.f10579m, this.f10580n, this.f10581o, (CatalogConfig) obj);
            default:
                return SettingsScreenKt.SettingsScreen$lambda$241$17$1$0$75$0(this.f10578l, this.f10579m, this.f10580n, this.f10581o, (CatalogConfig) obj);
        }
    }
}
