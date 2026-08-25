package com.arflix.tv.ui.screens.settings;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class y0 implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11122i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f11123l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.a f11124m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.a f11125n;

    public /* synthetic */ y0(boolean z, r7.a aVar, r7.a aVar2, int i10) {
        this.f11122i = i10;
        this.f11123l = z;
        this.f11124m = aVar;
        this.f11125n = aVar2;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f11122i) {
            case 0:
                return SettingsScreenKt.AccountRow$lambda$0$0(this.f11123l, this.f11124m, this.f11125n);
            case 1:
                return SettingsScreenKt.AccountsSettings$lambda$4$1$0(this.f11123l, this.f11124m, this.f11125n);
            case 2:
                return SettingsScreenKt.AccountsSettings$lambda$4$2$0(this.f11123l, this.f11124m, this.f11125n);
            default:
                return SettingsScreenKt.TrackingServiceRow$lambda$0$0$0(this.f11123l, this.f11124m, this.f11125n);
        }
    }
}
