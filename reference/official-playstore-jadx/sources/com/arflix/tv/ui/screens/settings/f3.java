package com.arflix.tv.ui.screens.settings;

import androidx.compose.foundation.relocation.BringIntoViewRequester;
import androidx.compose.foundation.text.KeyboardActionScope;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.MutableState;
import com.arflix.tv.ui.screens.settings.telegram.TelegramSettingsScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class f3 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10466i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f10467l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f10468m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f10469n;

    public /* synthetic */ f3(int i10, MutableState mutableState, r7.l lVar) {
        this.f10467l = i10;
        this.f10468m = lVar;
        this.f10469n = mutableState;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f10466i) {
            case 0:
                return SettingsScreenKt.settingsFocusSlot$lambda$1$0((SettingsFocusTracker) this.f10468m, this.f10467l, (BringIntoViewRequester) this.f10469n, (DisposableEffectScope) obj);
            default:
                return TelegramSettingsScreenKt.CodeContent$lambda$3$1$0(this.f10467l, (r7.l) this.f10468m, (MutableState) this.f10469n, (KeyboardActionScope) obj);
        }
    }

    public /* synthetic */ f3(SettingsFocusTracker settingsFocusTracker, int i10, BringIntoViewRequester bringIntoViewRequester) {
        this.f10468m = settingsFocusTracker;
        this.f10467l = i10;
        this.f10469n = bringIntoViewRequester;
    }
}
