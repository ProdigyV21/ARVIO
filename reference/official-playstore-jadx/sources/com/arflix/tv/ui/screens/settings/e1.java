package com.arflix.tv.ui.screens.settings;

import com.arflix.tv.data.repository.HomeServerConnection;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class e1 implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10426i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.l f10427l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ HomeServerConnection f10428m;

    public /* synthetic */ e1(r7.l lVar, HomeServerConnection homeServerConnection, int i10) {
        this.f10426i = i10;
        this.f10427l = lVar;
        this.f10428m = homeServerConnection;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f10426i) {
            case 0:
                return SettingsScreenKt.HomeServerSettings$lambda$0$0$0$6$0(this.f10427l, this.f10428m);
            default:
                return SettingsScreenKt.HomeServerSettings$lambda$1$0$6$0(this.f10427l, this.f10428m);
        }
    }
}
