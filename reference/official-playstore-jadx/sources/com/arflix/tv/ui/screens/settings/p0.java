package com.arflix.tv.ui.screens.settings;

import android.content.Context;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.runtime.Composer;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class p0 implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10780i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Context f10781l;

    public /* synthetic */ p0(Context context, int i10) {
        this.f10780i = i10;
        this.f10781l = context;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        int i10 = this.f10780i;
        ColumnScope columnScope = (ColumnScope) obj;
        Composer composer = (Composer) obj2;
        int iIntValue = ((Integer) obj3).intValue();
        switch (i10) {
            case 0:
                return SettingsScreenKt.MobilePrivacySubPage$lambda$1(this.f10781l, columnScope, composer, iIntValue);
            default:
                return SettingsScreenKt.MobileCloudAccountSubPage$lambda$14(this.f10781l, columnScope, composer, iIntValue);
        }
    }
}
