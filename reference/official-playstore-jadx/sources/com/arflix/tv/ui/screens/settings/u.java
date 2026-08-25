package com.arflix.tv.ui.screens.settings;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.ui.focus.FocusState;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class u implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10986i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableIntState f10987l;

    public /* synthetic */ u(MutableIntState mutableIntState, int i10) {
        this.f10986i = i10;
        this.f10987l = mutableIntState;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f10986i) {
            case 0:
                return SettingsScreenKt.SettingsScreen$lambda$241$64$0(this.f10987l, ((Integer) obj).intValue());
            case 1:
                return SettingsScreenKt.SettingsScreen$lambda$241$67$0(this.f10987l, ((Integer) obj).intValue());
            case 2:
                return SettingsScreenKt.SettingsScreen$lambda$241$70$0(this.f10987l, ((Integer) obj).intValue());
            case 3:
                return SettingsScreenKt.SettingsScreen$lambda$241$73$0(this.f10987l, ((Integer) obj).intValue());
            case 4:
                return SettingsScreenKt.SettingsScreen$lambda$241$78$0(this.f10987l, ((Integer) obj).intValue());
            case 5:
                return SettingsScreenKt.SettingsScreen$lambda$241$17$1$0$83$0(this.f10987l, ((Integer) obj).intValue());
            case 6:
                return SettingsScreenKt.SettingsScreen$lambda$241$17$1$0$84$0(this.f10987l, ((Integer) obj).intValue());
            case 7:
                return SettingsScreenKt.QualityFilterEditorModal$lambda$8$0$1$0$0(this.f10987l, (FocusState) obj);
            default:
                return SettingsScreenKt.QualityFilterEditorModal$lambda$8$0$1$1$0(this.f10987l, (FocusState) obj);
        }
    }
}
