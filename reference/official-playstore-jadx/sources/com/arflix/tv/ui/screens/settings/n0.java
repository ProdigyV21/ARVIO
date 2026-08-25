package com.arflix.tv.ui.screens.settings;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class n0 implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10710i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.l f10711l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f10712m;

    public /* synthetic */ n0(String str, r7.l lVar, int i10) {
        this.f10710i = i10;
        this.f10712m = str;
        this.f10711l = lVar;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f10710i) {
            case 0:
                return SettingsScreenKt.IptvSettings$lambda$8$2$0$0(this.f10712m, this.f10711l);
            case 1:
                return SettingsScreenKt.MobileSettingsMainPage$lambda$2$0$1$0$1$0$0$0(this.f10711l, this.f10712m);
            case 2:
                return SettingsScreenKt.IptvSettings$lambda$9$1$0(this.f10712m, this.f10711l);
            default:
                return SettingsScreenKt.IptvCategoriesSettings$lambda$2$0$0$0$0(this.f10711l, this.f10712m);
        }
    }

    public /* synthetic */ n0(r7.l lVar, String str, int i10) {
        this.f10710i = i10;
        this.f10711l = lVar;
        this.f10712m = str;
    }
}
