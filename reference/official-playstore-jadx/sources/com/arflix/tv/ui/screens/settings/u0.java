package com.arflix.tv.ui.screens.settings;

import androidx.compose.runtime.Composer;
import com.arflix.tv.ui.screens.settings.telegram.TelegramSettingsScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class u0 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10988i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.a f10989l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.a f10990m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f10991n;

    public /* synthetic */ u0(r7.a aVar, r7.a aVar2, int i10, int i11) {
        this.f10988i = i11;
        this.f10989l = aVar;
        this.f10990m = aVar2;
        this.f10991n = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        int i10 = this.f10988i;
        Composer composer = (Composer) obj;
        int iIntValue = ((Integer) obj2).intValue();
        switch (i10) {
            case 0:
                return SettingsScreenKt.UnknownSourcesModal$lambda$6(this.f10989l, this.f10990m, this.f10991n, composer, iIntValue);
            default:
                return TelegramSettingsScreenKt.DisconnectConfirmDialog$lambda$7(this.f10989l, this.f10990m, this.f10991n, composer, iIntValue);
        }
    }
}
