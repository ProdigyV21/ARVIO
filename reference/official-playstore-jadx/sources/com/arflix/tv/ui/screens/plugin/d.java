package com.arflix.tv.ui.screens.plugin;

import androidx.compose.runtime.Composer;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;
import com.arflix.tv.ui.screens.settings.SettingsUiState;
import com.arflix.tv.ui.screens.settings.SettingsViewModel;
import r7.l;
import r7.p;
import r7.q;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class d implements p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9862i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f9863l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f9864m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f9865n;

    public /* synthetic */ d(int i10, int i11, Object obj, Object obj2) {
        this.f9862i = i11;
        this.f9864m = obj;
        this.f9865n = obj2;
        this.f9863l = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f9862i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return PluginScreenKt.AddRepoDialog$lambda$8((l) this.f9864m, (r7.a) this.f9865n, this.f9863l, (Composer) obj, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj2).intValue();
                return SettingsScreenKt.MobilePrivacySubPage$lambda$2((SettingsUiState) this.f9864m, (SettingsViewModel) this.f9865n, this.f9863l, (Composer) obj, iIntValue2);
            default:
                int iIntValue3 = ((Integer) obj2).intValue();
                return SettingsScreenKt.ModalScrim$lambda$3((r7.a) this.f9865n, (q) this.f9864m, this.f9863l, (Composer) obj, iIntValue3);
        }
    }

    public /* synthetic */ d(r7.a aVar, q qVar, int i10) {
        this.f9862i = 2;
        this.f9865n = aVar;
        this.f9864m = qVar;
        this.f9863l = i10;
    }
}
