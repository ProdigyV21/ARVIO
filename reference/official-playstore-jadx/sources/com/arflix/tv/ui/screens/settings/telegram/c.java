package com.arflix.tv.ui.screens.settings.telegram;

import androidx.compose.runtime.Composer;
import r7.l;
import r7.p;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class c implements p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10968i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ l f10969l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f10970m;

    public /* synthetic */ c(int i10, int i11, l lVar) {
        this.f10968i = i11;
        this.f10969l = lVar;
        this.f10970m = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        int i10 = this.f10968i;
        Composer composer = (Composer) obj;
        int iIntValue = ((Integer) obj2).intValue();
        switch (i10) {
            case 0:
                return TelegramSettingsScreenKt.PasswordContent$lambda$4(this.f10969l, this.f10970m, composer, iIntValue);
            default:
                return TelegramSettingsScreenKt.PhoneContent$lambda$11(this.f10969l, this.f10970m, composer, iIntValue);
        }
    }
}
