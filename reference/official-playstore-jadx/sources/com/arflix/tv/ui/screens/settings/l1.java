package com.arflix.tv.ui.screens.settings;

import androidx.compose.runtime.Composer;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class l1 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10643i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f10644l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.l f10645m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.a f10646n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f10647o;

    public /* synthetic */ l1(String str, r7.a aVar, r7.l lVar, int i10) {
        this.f10644l = str;
        this.f10646n = aVar;
        this.f10645m = lVar;
        this.f10647o = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f10643i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return SettingsScreenKt.DiscordWebViewModal$lambda$19(this.f10644l, this.f10646n, this.f10645m, this.f10647o, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                String str = this.f10644l;
                r7.a aVar = this.f10646n;
                return SettingsScreenKt.CustomUserAgentDialog$lambda$7(str, this.f10645m, aVar, this.f10647o, (Composer) obj, iIntValue2);
        }
    }

    public /* synthetic */ l1(String str, r7.l lVar, r7.a aVar, int i10) {
        this.f10644l = str;
        this.f10645m = lVar;
        this.f10646n = aVar;
        this.f10647o = i10;
    }
}
