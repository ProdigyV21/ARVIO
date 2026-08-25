package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusRequester;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.QuickZapOverlayKt$QuickZapOverlay$1$1", f = "QuickZapOverlay.kt", l = {}, m = "invokeSuspend", v = 2)
public final class QuickZapOverlayKt$QuickZapOverlay$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableState<Boolean> $categoryListFocused$delegate;
    final /* synthetic */ FocusRequester $focusRequester;
    final /* synthetic */ MutableState<String> $originalCategoryId$delegate;
    final /* synthetic */ String $selectedCategoryId;
    final /* synthetic */ boolean $visible;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QuickZapOverlayKt$QuickZapOverlay$1$1(boolean z, String str, MutableState<String> mutableState, MutableState<Boolean> mutableState2, FocusRequester focusRequester, d7.d<? super QuickZapOverlayKt$QuickZapOverlay$1$1> dVar) {
        super(2, dVar);
        this.$visible = z;
        this.$selectedCategoryId = str;
        this.$originalCategoryId$delegate = mutableState;
        this.$categoryListFocused$delegate = mutableState2;
        this.$focusRequester = focusRequester;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        QuickZapOverlayKt$QuickZapOverlay$1$1 quickZapOverlayKt$QuickZapOverlay$1$1 = new QuickZapOverlayKt$QuickZapOverlay$1$1(this.$visible, this.$selectedCategoryId, this.$originalCategoryId$delegate, this.$categoryListFocused$delegate, this.$focusRequester, dVar);
        quickZapOverlayKt$QuickZapOverlay$1$1.L$0 = obj;
        return quickZapOverlayKt$QuickZapOverlay$1$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        if (this.$visible) {
            this.$originalCategoryId$delegate.setValue(this.$selectedCategoryId);
            QuickZapOverlayKt.QuickZapOverlay$lambda$9(this.$categoryListFocused$delegate, false);
            try {
                this.$focusRequester.requestFocus();
            } catch (Throwable unused) {
            }
        }
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((QuickZapOverlayKt$QuickZapOverlay$1$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
