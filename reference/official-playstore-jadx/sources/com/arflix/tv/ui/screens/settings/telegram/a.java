package com.arflix.tv.ui.screens.settings.telegram;

import androidx.compose.runtime.Composer;
import r7.p;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class a implements p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10961i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f10962l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.a f10963m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f10964n;

    public /* synthetic */ a(String str, r7.a aVar, int i10, int i11) {
        this.f10961i = i11;
        this.f10962l = str;
        this.f10963m = aVar;
        this.f10964n = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        int i10 = this.f10961i;
        Composer composer = (Composer) obj;
        int iIntValue = ((Integer) obj2).intValue();
        switch (i10) {
            case 0:
                return TelegramSettingsScreenKt.ActionButton$lambda$2(this.f10962l, this.f10963m, this.f10964n, composer, iIntValue);
            default:
                return TelegramSettingsScreenKt.ErrorContent$lambda$1(this.f10962l, this.f10963m, this.f10964n, composer, iIntValue);
        }
    }
}
