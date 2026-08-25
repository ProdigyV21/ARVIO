package com.arflix.tv.ui.components;

import androidx.compose.ui.focus.FocusRequester;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.components.PersonModalKt$PersonModal$3$1", f = "PersonModal.kt", l = {122}, m = "invokeSuspend", v = 2)
public final class PersonModalKt$PersonModal$3$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ FocusRequester $focusRequester;
    final /* synthetic */ boolean $isVisible;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PersonModalKt$PersonModal$3$1(boolean z, FocusRequester focusRequester, d7.d<? super PersonModalKt$PersonModal$3$1> dVar) {
        super(2, dVar);
        this.$isVisible = z;
        this.$focusRequester = focusRequester;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new PersonModalKt$PersonModal$3$1(this.$isVisible, this.$focusRequester, dVar);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:0|2|(1:(1:5)(2:6|7))(4:8|(2:10|(1:12))|14|15)|17|13|14|15) */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r3) {
        /*
            r2 = this;
            int r0 = r2.label
            r1 = 1
            if (r0 == 0) goto L13
            if (r0 != r1) goto Lb
            k2.c.G(r3)
            goto L27
        Lb:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r3.<init>(r0)
            throw r3
        L13:
            k2.c.G(r3)
            boolean r3 = r2.$isVisible
            if (r3 == 0) goto L2c
            r2.label = r1
            r0 = 100
            java.lang.Object r3 = ka.s0.a(r0, r2)
            e7.a r0 = e7.a.f15033i
            if (r3 != r0) goto L27
            return r0
        L27:
            androidx.compose.ui.focus.FocusRequester r3 = r2.$focusRequester     // Catch: java.lang.Exception -> L2c
            r3.requestFocus()     // Catch: java.lang.Exception -> L2c
        L2c:
            x6.t0 r3 = x6.t0.f22605a
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.PersonModalKt$PersonModal$3$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((PersonModalKt$PersonModal$3$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
