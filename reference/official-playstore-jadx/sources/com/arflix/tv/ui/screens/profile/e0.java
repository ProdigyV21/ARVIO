package com.arflix.tv.ui.screens.profile;

import androidx.compose.runtime.Composer;
import com.arflix.tv.ui.screens.settings.telegram.TelegramSettingsScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class e0 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9941i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.a f9942l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f9943m;

    public /* synthetic */ e0(int i10, int i11, r7.a aVar) {
        this.f9941i = i11;
        this.f9942l = aVar;
        this.f9943m = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        int i10 = this.f9941i;
        Composer composer = (Composer) obj;
        int iIntValue = ((Integer) obj2).intValue();
        switch (i10) {
            case 0:
                return ProfileSelectionScreenKt.CloudConnectButton$lambda$7(this.f9942l, this.f9943m, composer, iIntValue);
            default:
                return TelegramSettingsScreenKt.IdleContent$lambda$1(this.f9942l, this.f9943m, composer, iIntValue);
        }
    }
}
