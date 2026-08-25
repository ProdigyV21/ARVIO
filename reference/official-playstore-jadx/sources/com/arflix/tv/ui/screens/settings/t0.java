package com.arflix.tv.ui.screens.settings;

import androidx.compose.runtime.MutableState;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class t0 implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10937i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.l f10938l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MutableState f10939m;

    public /* synthetic */ t0(int i10, MutableState mutableState, r7.l lVar) {
        this.f10937i = i10;
        this.f10938l = lVar;
        this.f10939m = mutableState;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f10937i) {
            case 0:
                return SettingsScreenKt.AiApiKeyDialog$lambda$6$0$0$2$4$0(this.f10938l, this.f10939m);
            case 1:
                return SettingsScreenKt.CatalogDiscoveryTextInputDialog$lambda$3$0$0$2$0$0(this.f10938l, this.f10939m);
            case 2:
                return SettingsScreenKt.CatalogDiscoveryTextInputDialog$lambda$3$0$0$3$0$0(this.f10938l, this.f10939m);
            case 3:
                return SettingsScreenKt.MdbListConnectDialog$lambda$3$0$0(this.f10938l, this.f10939m);
            case 4:
                return SettingsScreenKt.CustomUserAgentDialog$lambda$6$0$0$1$4$0(this.f10938l, this.f10939m);
            default:
                return SettingsScreenKt.MobileCloudAccountSubPage$lambda$13$2$0(this.f10938l, this.f10939m);
        }
    }
}
