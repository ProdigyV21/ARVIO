package com.arflix.tv.ui.screens.profile;

import androidx.compose.runtime.MutableState;
import ka.k0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.profile.PinEntryDialogKt$PinEntryDialog$1$1", f = "PinEntryDialog.kt", l = {}, m = "invokeSuspend", v = 2)
public final class PinEntryDialogKt$PinEntryDialog$1$1 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ MutableState<String> $errorMessage$delegate;
    final /* synthetic */ String $pinError;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PinEntryDialogKt$PinEntryDialog$1$1(String str, MutableState<String> mutableState, d7.d<? super PinEntryDialogKt$PinEntryDialog$1$1> dVar) {
        super(2, dVar);
        this.$pinError = str;
        this.$errorMessage$delegate = mutableState;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new PinEntryDialogKt$PinEntryDialog$1$1(this.$pinError, this.$errorMessage$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        this.$errorMessage$delegate.setValue(this.$pinError);
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((PinEntryDialogKt$PinEntryDialog$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
