package com.arflix.tv.ui.screens.settings.telegram;

import androidx.compose.runtime.MutableState;
import kotlin.Metadata;
import kotlin.jvm.internal.m;
import kotlin.jvm.internal.o;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final /* synthetic */ class TelegramSettingsScreenKt$PhoneContent$1$4$1 extends m implements r7.a<t0> {
    final /* synthetic */ MutableState<Boolean> $isSubmitting$delegate;
    final /* synthetic */ boolean $isValid;
    final /* synthetic */ l<String, t0> $onSubmit;
    final /* synthetic */ MutableState<String> $phone$delegate;
    final /* synthetic */ MutableState<Boolean> $showValidation$delegate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public TelegramSettingsScreenKt$PhoneContent$1$4$1(boolean z, l<? super String, t0> lVar, MutableState<Boolean> mutableState, MutableState<Boolean> mutableState2, MutableState<String> mutableState3) {
        super(0, o.class, "trySubmit", "PhoneContent$trySubmit(ZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/MutableState;Landroidx/compose/runtime/MutableState;Landroidx/compose/runtime/MutableState;)V", 0);
        this.$isValid = z;
        this.$onSubmit = lVar;
        this.$showValidation$delegate = mutableState;
        this.$isSubmitting$delegate = mutableState2;
        this.$phone$delegate = mutableState3;
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        m6329invoke();
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m6329invoke() {
        TelegramSettingsScreenKt.PhoneContent$trySubmit(this.$isValid, this.$onSubmit, this.$showValidation$delegate, this.$isSubmitting$delegate, this.$phone$delegate);
    }
}
