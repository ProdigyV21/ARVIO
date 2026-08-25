package com.arflix.tv.ui.screens.settings;

import androidx.compose.runtime.Composer;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class s0 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10911i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f10912l;

    public /* synthetic */ s0(String str, int i10) {
        this.f10911i = i10;
        this.f10912l = str;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f10911i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return SettingsScreenKt.AiApiKeyDialog$lambda$6$0$0$1(this.f10912l, (Composer) obj, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj2).intValue();
                return SettingsScreenKt.CatalogDiscoveryTextInputDialog$lambda$3$0$0$1(this.f10912l, (Composer) obj, iIntValue2);
            default:
                return SettingsScreenKt.IptvCategoriesSettings$lambda$2$1$0$0(this.f10912l, ((Integer) obj).intValue(), (String) obj2);
        }
    }
}
