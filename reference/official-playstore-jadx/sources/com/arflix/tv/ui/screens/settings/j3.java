package com.arflix.tv.ui.screens.settings;

import androidx.compose.runtime.Composer;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class j3 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10592i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f10593l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.a f10594m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.a f10595n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f10596o;

    public /* synthetic */ j3(String str, r7.a aVar, r7.a aVar2, int i10, int i11) {
        this.f10592i = i11;
        this.f10593l = str;
        this.f10594m = aVar;
        this.f10595n = aVar2;
        this.f10596o = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f10592i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return SettingsScreenKt.CatalogPackDeleteConfirmDialog$lambda$6(this.f10593l, this.f10594m, this.f10595n, this.f10596o, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return SettingsScreenKt.UiModeWarningDialog$lambda$6(this.f10593l, this.f10594m, this.f10595n, this.f10596o, (Composer) obj, iIntValue2);
        }
    }
}
