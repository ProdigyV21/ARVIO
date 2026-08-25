package com.arflix.tv.ui.screens.settings;

import androidx.compose.runtime.Composer;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class b0 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10288i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f10289l;

    public /* synthetic */ b0(Object obj, int i10) {
        this.f10288i = i10;
        this.f10289l = obj;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f10288i) {
            case 0:
                return SettingsScreenKt.InputModalLegacy$lambda$6$1$1$0$0$0((InputField) this.f10289l, (Composer) obj, ((Integer) obj2).intValue());
            default:
                return SettingsScreenKt.MdbListConnectDialog$lambda$4((r7.a) this.f10289l, (Composer) obj, ((Integer) obj2).intValue());
        }
    }
}
