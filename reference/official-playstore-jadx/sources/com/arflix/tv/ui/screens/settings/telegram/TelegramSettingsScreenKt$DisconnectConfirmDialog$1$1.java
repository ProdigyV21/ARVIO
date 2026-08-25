package com.arflix.tv.ui.screens.settings.telegram;

import androidx.compose.ui.focus.FocusRequester;
import f7.j;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.settings.telegram.TelegramSettingsScreenKt$DisconnectConfirmDialog$1$1", f = "TelegramSettingsScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class TelegramSettingsScreenKt$DisconnectConfirmDialog$1$1 extends j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ FocusRequester $focusRequester;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TelegramSettingsScreenKt$DisconnectConfirmDialog$1$1(FocusRequester focusRequester, d7.d<? super TelegramSettingsScreenKt$DisconnectConfirmDialog$1$1> dVar) {
        super(2, dVar);
        this.$focusRequester = focusRequester;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        TelegramSettingsScreenKt$DisconnectConfirmDialog$1$1 telegramSettingsScreenKt$DisconnectConfirmDialog$1$1 = new TelegramSettingsScreenKt$DisconnectConfirmDialog$1$1(this.$focusRequester, dVar);
        telegramSettingsScreenKt$DisconnectConfirmDialog$1$1.L$0 = obj;
        return telegramSettingsScreenKt$DisconnectConfirmDialog$1$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        try {
            this.$focusRequester.requestFocus();
        } catch (Throwable unused) {
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((TelegramSettingsScreenKt$DisconnectConfirmDialog$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
