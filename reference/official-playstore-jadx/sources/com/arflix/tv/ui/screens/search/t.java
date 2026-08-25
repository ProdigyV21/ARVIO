package com.arflix.tv.ui.screens.search;

import androidx.compose.runtime.Composer;
import com.arflix.tv.ui.screens.settings.telegram.TelegramSettingsScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class t implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10228i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f10229l;

    public /* synthetic */ t(boolean z, int i10) {
        this.f10228i = i10;
        this.f10229l = z;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        int i10 = this.f10228i;
        Composer composer = (Composer) obj;
        int iIntValue = ((Integer) obj2).intValue();
        switch (i10) {
            case 0:
                return SearchScreenKt.SearchInputBar_yZaabhs$lambda$2(this.f10229l, composer, iIntValue);
            default:
                return TelegramSettingsScreenKt.PhoneContent$lambda$10$2(this.f10229l, composer, iIntValue);
        }
    }
}
