package com.arflix.tv.ui.screens.settings;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final /* synthetic */ class SettingsScreenKt$SettingsScreen$24$18$2$1$92$1 extends kotlin.jvm.internal.m implements r7.l<Boolean, x6.t0> {
    public SettingsScreenKt$SettingsScreen$24$18$2$1$92$1(Object obj) {
        super(1, 0, SettingsViewModel.class, obj, "setDiagnosticsSharingEnabled", "setDiagnosticsSharingEnabled(Z)V");
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke(((Boolean) obj).booleanValue());
        return x6.t0.f22605a;
    }

    public final void invoke(boolean z) {
        ((SettingsViewModel) this.receiver).setDiagnosticsSharingEnabled(z);
    }
}
