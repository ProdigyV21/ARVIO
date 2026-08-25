package com.arflix.tv.ui.screens.settings;

import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.runtime.Composer;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class c implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10345i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ SettingsUiState f10346l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.a f10347m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.a f10348n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ r7.a f10349o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ r7.a f10350p;

    public /* synthetic */ c(SettingsUiState settingsUiState, r7.a aVar, r7.a aVar2, r7.a aVar3, r7.a aVar4, int i10) {
        this.f10345i = i10;
        this.f10346l = settingsUiState;
        this.f10347m = aVar;
        this.f10348n = aVar2;
        this.f10349o = aVar3;
        this.f10350p = aVar4;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        switch (this.f10345i) {
            case 0:
                int iIntValue = ((Integer) obj3).intValue();
                return SettingsScreenKt.MobileSettingsMainPage$lambda$2$0$0(this.f10346l, this.f10347m, this.f10348n, this.f10349o, this.f10350p, (LazyItemScope) obj, (Composer) obj2, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj3).intValue();
                return SettingsScreenKt.MobileSettingsMainPage$lambda$2$0$0$0(this.f10346l, this.f10347m, this.f10348n, this.f10349o, this.f10350p, (ColumnScope) obj, (Composer) obj2, iIntValue2);
        }
    }
}
