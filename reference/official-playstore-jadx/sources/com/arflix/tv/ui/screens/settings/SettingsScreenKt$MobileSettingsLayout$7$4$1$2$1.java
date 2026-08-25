package com.arflix.tv.ui.screens.settings;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final /* synthetic */ class SettingsScreenKt$MobileSettingsLayout$7$4$1$2$1 extends kotlin.jvm.internal.m implements r7.l<String, x6.t0> {
    public SettingsScreenKt$MobileSettingsLayout$7$4$1$2$1(Object obj) {
        super(1, 0, SettingsViewModel.class, obj, "connectMdbList", "connectMdbList(Ljava/lang/String;)V");
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((String) obj);
        return x6.t0.f22605a;
    }

    public final void invoke(String str) {
        ((SettingsViewModel) this.receiver).connectMdbList(str);
    }
}
