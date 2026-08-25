package com.arflix.tv.ui.screens.settings;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class h implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10509i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableIntState f10510l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MutableState f10511m;

    public /* synthetic */ h(MutableIntState mutableIntState, MutableState mutableState, int i10) {
        this.f10509i = i10;
        this.f10510l = mutableIntState;
        this.f10511m = mutableState;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        int i10 = this.f10509i;
        int iIntValue = ((Integer) obj).intValue();
        switch (i10) {
            case 0:
                return SettingsScreenKt.SettingsScreen$lambda$241$6$0(this.f10510l, this.f10511m, iIntValue);
            case 1:
                return SettingsScreenKt.SettingsScreen$lambda$241$17$1$0$48$0(this.f10510l, this.f10511m, iIntValue);
            default:
                return SettingsScreenKt.SettingsScreen$lambda$241$17$1$0$57$0(this.f10510l, this.f10511m, iIntValue);
        }
    }
}
