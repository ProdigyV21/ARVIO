package com.arflix.tv.ui.screens.settings;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.ui.focus.FocusRequester;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsScreenKt$QualityFilterEditorModal$2$1", f = "SettingsScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class SettingsScreenKt$QualityFilterEditorModal$2$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ FocusRequester $deviceNameRequester;
    final /* synthetic */ MutableIntState $focusedIndex$delegate;
    final /* synthetic */ FocusRequester $modalFocusRequester;
    final /* synthetic */ FocusRequester $regexPatternRequester;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SettingsScreenKt$QualityFilterEditorModal$2$1(FocusRequester focusRequester, FocusRequester focusRequester2, FocusRequester focusRequester3, MutableIntState mutableIntState, d7.d<? super SettingsScreenKt$QualityFilterEditorModal$2$1> dVar) {
        super(2, dVar);
        this.$deviceNameRequester = focusRequester;
        this.$regexPatternRequester = focusRequester2;
        this.$modalFocusRequester = focusRequester3;
        this.$focusedIndex$delegate = mutableIntState;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new SettingsScreenKt$QualityFilterEditorModal$2$1(this.$deviceNameRequester, this.$regexPatternRequester, this.$modalFocusRequester, this.$focusedIndex$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        int intValue = this.$focusedIndex$delegate.getIntValue();
        if (intValue == 0) {
            this.$deviceNameRequester.requestFocus();
        } else if (intValue != 1) {
            this.$modalFocusRequester.requestFocus();
        } else {
            this.$regexPatternRequester.requestFocus();
        }
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((SettingsScreenKt$QualityFilterEditorModal$2$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
