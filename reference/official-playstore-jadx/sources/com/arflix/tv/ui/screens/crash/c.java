package com.arflix.tv.ui.screens.crash;

import androidx.compose.runtime.Composer;
import com.arflix.tv.ui.screens.profile.PinEntryDialogKt;
import r7.p;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class c implements p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8692i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f8693l;

    public /* synthetic */ c(String str, int i10) {
        this.f8692i = i10;
        this.f8693l = str;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        int i10 = this.f8692i;
        Composer composer = (Composer) obj;
        int iIntValue = ((Integer) obj2).intValue();
        switch (i10) {
            case 0:
                return CrashReportActivityKt.CrashReportScreen$lambda$9$0$0(this.f8693l, composer, iIntValue);
            case 1:
                return PinEntryDialogKt.PinActionButton_cf5BqRc$lambda$0(this.f8693l, composer, iIntValue);
            default:
                return PinEntryDialogKt.PinKeyButton$lambda$0(this.f8693l, composer, iIntValue);
        }
    }
}
