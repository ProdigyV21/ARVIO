package com.arflix.tv.ui.screens.profile;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.MutableState;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10057i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f10058l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f10059m;

    public /* synthetic */ r(int i10, Object obj, boolean z) {
        this.f10057i = i10;
        this.f10058l = z;
        this.f10059m = obj;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f10057i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return ProfileDialogsKt.DialogButton$lambda$3(this.f10058l, (String) this.f10059m, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return SettingsScreenKt.MdbListConnectDialog$lambda$5(this.f10058l, (MutableState) this.f10059m, (Composer) obj, iIntValue2);
        }
    }
}
