package com.arflix.tv.ui.screens.settings.telegram;

import androidx.compose.foundation.text.KeyboardActionScope;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusState;
import com.arflix.tv.ui.skin.ArvioFocusKt;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class b implements l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10965i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ l f10966l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MutableState f10967m;

    public /* synthetic */ b(int i10, MutableState mutableState, l lVar) {
        this.f10965i = i10;
        this.f10966l = lVar;
        this.f10967m = mutableState;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f10965i) {
            case 0:
                return TelegramSettingsScreenKt.PasswordContent$lambda$3$0$0(this.f10966l, this.f10967m, (KeyboardActionScope) obj);
            case 1:
                return ArvioFocusKt.ArvioFocusableSurface_aewEPWQ$lambda$4$0(this.f10966l, this.f10967m, ((Boolean) obj).booleanValue());
            default:
                return ArvioFocusKt.arvioFocusable_gqPShRU$lambda$1$8$0(this.f10966l, this.f10967m, (FocusState) obj);
        }
    }
}
