package com.arflix.tv.ui.screens.profile;

import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.runtime.Composer;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class d implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9929i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.p f9930l;

    public /* synthetic */ d(r7.p pVar, int i10) {
        this.f9929i = i10;
        this.f9930l = pVar;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        int i10 = this.f9929i;
        BoxScope boxScope = (BoxScope) obj;
        Composer composer = (Composer) obj2;
        int iIntValue = ((Integer) obj3).intValue();
        switch (i10) {
            case 0:
                return PinEntryDialogKt.PinActionButton_cf5BqRc$lambda$3(this.f9930l, boxScope, composer, iIntValue);
            case 1:
                return PinEntryDialogKt.PinKeyButton$lambda$3(this.f9930l, boxScope, composer, iIntValue);
            case 2:
                return ProfileDialogsKt.DialogButton$lambda$8(this.f9930l, boxScope, composer, iIntValue);
            case 3:
                return ProfileDialogsKt.AvatarGridItem$lambda$9(this.f9930l, boxScope, composer, iIntValue);
            case 4:
                return ProfileSelectionScreenKt.AddProfileButton_Kz89ssw$lambda$4$3(this.f9930l, boxScope, composer, iIntValue);
            default:
                return ProfileSelectionScreenKt.ProfileAvatar_aA_HZ9I$lambda$5$0$4(this.f9930l, boxScope, composer, iIntValue);
        }
    }
}
