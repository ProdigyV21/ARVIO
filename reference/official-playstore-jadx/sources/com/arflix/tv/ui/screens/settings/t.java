package com.arflix.tv.ui.screens.settings;

import androidx.compose.runtime.State;
import com.arflix.tv.data.model.CatalogPackManifest;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class t implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10934i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ SettingsViewModel f10935l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ State f10936m;

    public /* synthetic */ t(SettingsViewModel settingsViewModel, State state, int i10) {
        this.f10934i = i10;
        this.f10935l = settingsViewModel;
        this.f10936m = state;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f10934i) {
            case 0:
                return SettingsScreenKt.SettingsScreen$lambda$241$51$0(this.f10935l, this.f10936m, (CatalogPackManifest) obj);
            case 1:
                return SettingsScreenKt.SettingsScreen$lambda$241$17$1$0$43$0(this.f10935l, this.f10936m, (String) obj);
            case 2:
                return SettingsScreenKt.SettingsScreen$lambda$241$17$1$0$44$0(this.f10935l, this.f10936m, (String) obj);
            case 3:
                return SettingsScreenKt.SettingsScreen$lambda$241$17$1$0$45$0(this.f10935l, this.f10936m, (String) obj);
            case 4:
                return SettingsScreenKt.SettingsScreen$lambda$241$17$1$0$49$0(this.f10935l, this.f10936m, ((Integer) obj).intValue());
            case 5:
                return SettingsScreenKt.SettingsScreen$lambda$241$17$1$0$50$0(this.f10935l, this.f10936m, ((Integer) obj).intValue());
            case 6:
                return SettingsScreenKt.SettingsScreen$lambda$241$17$1$0$51$0(this.f10935l, this.f10936m, ((Integer) obj).intValue());
            case 7:
                return SettingsScreenKt.SettingsScreen$lambda$241$17$1$0$52$0(this.f10935l, this.f10936m, ((Integer) obj).intValue());
            case 8:
                return SettingsScreenKt.SettingsScreen$lambda$241$17$1$0$58$0(this.f10935l, this.f10936m, ((Integer) obj).intValue());
            case 9:
                return SettingsScreenKt.SettingsScreen$lambda$241$17$1$0$59$0(this.f10935l, this.f10936m, ((Integer) obj).intValue());
            case 10:
                return SettingsScreenKt.SettingsScreen$lambda$241$17$1$0$60$0(this.f10935l, this.f10936m, ((Integer) obj).intValue());
            default:
                return SettingsScreenKt.SettingsScreen$lambda$241$17$1$0$61$0(this.f10935l, this.f10936m, ((Integer) obj).intValue());
        }
    }
}
