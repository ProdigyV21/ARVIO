package com.arflix.tv.ui.screens.profile;

import com.arflix.tv.ui.screens.settings.SettingsScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class s implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10060i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f10061l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.a f10062m;

    public /* synthetic */ s(boolean z, r7.a aVar, int i10) {
        this.f10060i = i10;
        this.f10061l = z;
        this.f10062m = aVar;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f10060i) {
            case 0:
                return ProfileDialogsKt.DialogButton$lambda$4$0(this.f10061l, this.f10062m);
            case 1:
                return ProfileDialogsKt.DialogButton$lambda$6$0(this.f10061l, this.f10062m);
            case 2:
                return SettingsScreenKt.StremioAddonsSettings$lambda$6$0$0$0(this.f10061l, this.f10062m);
            case 3:
                return SettingsScreenKt.HomeServerSettings$lambda$0$1$0$0(this.f10061l, this.f10062m);
            case 4:
                return SettingsScreenKt.HomeServerSettings$lambda$0$1$1$0(this.f10061l, this.f10062m);
            case 5:
                return SettingsScreenKt.HomeServerSettings$lambda$1$1$0(this.f10061l, this.f10062m);
            case 6:
                return SettingsScreenKt.HomeServerSettings$lambda$1$2$0(this.f10061l, this.f10062m);
            case 7:
                return SettingsScreenKt.AccountsSettings$lambda$4$12$0(this.f10061l, this.f10062m);
            case 8:
                return SettingsScreenKt.CatalogDiscoveryResultRow$lambda$8$0(this.f10061l, this.f10062m);
            default:
                return SettingsScreenKt.CatalogDiscoveryResultRow$lambda$12$0(this.f10061l, this.f10062m);
        }
    }
}
