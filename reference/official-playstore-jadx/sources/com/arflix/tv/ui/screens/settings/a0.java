package com.arflix.tv.ui.screens.settings;

import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class a0 implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10266i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.l f10267l;

    public /* synthetic */ a0(r7.l lVar, int i10) {
        this.f10266i = i10;
        this.f10267l = lVar;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        switch (this.f10266i) {
            case 0:
                int iIntValue = ((Integer) obj3).intValue();
                return SettingsScreenKt.MobileSettingsMainPage$lambda$2$0$1(this.f10267l, (LazyItemScope) obj, (Composer) obj2, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj3).intValue();
                return SettingsScreenKt.MobileSettingsMainPage$lambda$2$0$1$0(this.f10267l, (ColumnScope) obj, (Composer) obj2, iIntValue2);
        }
    }
}
