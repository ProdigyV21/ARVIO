package com.arflix.tv.ui.screens.settings;

import androidx.compose.runtime.MutableState;
import com.arflix.tv.ui.screens.player.SubtitleAiModel;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class v implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11014i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ SettingsViewModel f11015l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MutableState f11016m;

    public /* synthetic */ v(SettingsViewModel settingsViewModel, MutableState mutableState, int i10) {
        this.f11014i = i10;
        this.f11015l = settingsViewModel;
        this.f11016m = mutableState;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f11014i) {
            case 0:
                return SettingsScreenKt.SettingsScreen$lambda$241$65$0(this.f11015l, this.f11016m, (String) obj);
            case 1:
                return SettingsScreenKt.SettingsScreen$lambda$241$68$0(this.f11015l, this.f11016m, (String) obj);
            case 2:
                return SettingsScreenKt.SettingsScreen$lambda$241$71$0(this.f11015l, this.f11016m, (String) obj);
            case 3:
                return SettingsScreenKt.SettingsScreen$lambda$241$74$0(this.f11015l, this.f11016m, (String) obj);
            case 4:
                return SettingsScreenKt.SettingsScreen$lambda$241$79$0(this.f11015l, this.f11016m, (String) obj);
            case 5:
                return SettingsScreenKt.SettingsScreen$lambda$241$81$0(this.f11015l, this.f11016m, (SubtitleAiModel) obj);
            case 6:
                return SettingsScreenKt.SettingsScreen$lambda$241$83$0(this.f11015l, this.f11016m, (String) obj);
            case 7:
                return SettingsScreenKt.SettingsScreen$lambda$241$85$0(this.f11015l, this.f11016m, (String) obj);
            case 8:
                return SettingsScreenKt.SettingsScreen$lambda$241$17$1$0$36$0(this.f11015l, this.f11016m, (SubtitleAiModel) obj);
            case 9:
                return SettingsScreenKt.SettingsScreen$lambda$241$17$1$0$38$0(this.f11015l, this.f11016m, (String) obj);
            case 10:
                return SettingsScreenKt.SettingsScreen$lambda$241$17$1$0$40$0(this.f11015l, this.f11016m, (String) obj);
            default:
                return SettingsScreenKt.SettingsScreen$lambda$241$92$0(this.f11015l, this.f11016m, (String) obj);
        }
    }
}
