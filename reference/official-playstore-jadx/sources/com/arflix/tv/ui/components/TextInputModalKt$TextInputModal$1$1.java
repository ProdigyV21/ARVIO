package com.arflix.tv.ui.components;

import android.content.Context;
import android.widget.EditText;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusRequester;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.components.TextInputModalKt$TextInputModal$1$1", f = "TextInputModal.kt", l = {124}, m = "invokeSuspend", v = 2)
public final class TextInputModalKt$TextInputModal$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ MutableState<EditText> $editTextRef$delegate;
    final /* synthetic */ MutableIntState $focusedButton$delegate;
    final /* synthetic */ FocusRequester $inputFocusRequester;
    final /* synthetic */ boolean $isVisible;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextInputModalKt$TextInputModal$1$1(boolean z, FocusRequester focusRequester, MutableIntState mutableIntState, Context context, MutableState<EditText> mutableState, d7.d<? super TextInputModalKt$TextInputModal$1$1> dVar) {
        super(2, dVar);
        this.$isVisible = z;
        this.$inputFocusRequester = focusRequester;
        this.$focusedButton$delegate = mutableIntState;
        this.$context = context;
        this.$editTextRef$delegate = mutableState;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new TextInputModalKt$TextInputModal$1$1(this.$isVisible, this.$inputFocusRequester, this.$focusedButton$delegate, this.$context, this.$editTextRef$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            if (!this.$isVisible) {
                TextInputModalKt.TextInputModal$hideKeyboard(this.$context, this.$editTextRef$delegate);
                return x6.t0.f22605a;
            }
            this.$inputFocusRequester.requestFocus();
            this.$focusedButton$delegate.setIntValue(-1);
            this.label = 1;
            Object objA = ka.s0.a(200L, this);
            e7.a aVar = e7.a.f15033i;
            if (objA == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        TextInputModalKt.TextInputModal$showKeyboard(this.$context, this.$editTextRef$delegate);
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((TextInputModalKt$TextInputModal$1$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
