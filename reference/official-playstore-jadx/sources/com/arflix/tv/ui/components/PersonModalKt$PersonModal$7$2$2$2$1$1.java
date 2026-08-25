package com.arflix.tv.ui.components;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.saveable.Saver;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.components.PersonModalKt$PersonModal$7$2$2$2$1$1", f = "PersonModal.kt", l = {352}, m = "invokeSuspend", v = 2)
public final class PersonModalKt$PersonModal$7$2$2$2$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableIntState $focusedKnownForIndex$delegate;
    final /* synthetic */ androidx.tv.foundation.lazy.list.m0 $knownForListState;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PersonModalKt$PersonModal$7$2$2$2$1$1(androidx.tv.foundation.lazy.list.m0 m0Var, MutableIntState mutableIntState, d7.d<? super PersonModalKt$PersonModal$7$2$2$2$1$1> dVar) {
        super(2, dVar);
        this.$knownForListState = m0Var;
        this.$focusedKnownForIndex$delegate = mutableIntState;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new PersonModalKt$PersonModal$7$2$2$2$1$1(this.$knownForListState, this.$focusedKnownForIndex$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        x6.t0 t0Var = x6.t0.f22605a;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return t0Var;
        }
        k2.c.G(obj);
        androidx.tv.foundation.lazy.list.m0 m0Var = this.$knownForListState;
        int intValue = this.$focusedKnownForIndex$delegate.getIntValue();
        this.label = 1;
        Saver saver = androidx.tv.foundation.lazy.list.m0.z;
        a8.e eVar = m0Var.f5430d;
        float f10 = i2.f.f16047a;
        Object objE = androidx.compose.foundation.gestures.f.e((androidx.tv.foundation.lazy.list.m0) eVar.f183l, null, new i2.e(intValue, eVar, null), this, 1, null);
        e7.a aVar = e7.a.f15033i;
        if (objE != aVar) {
            objE = t0Var;
        }
        if (objE != aVar) {
            objE = t0Var;
        }
        if (objE != aVar) {
            objE = t0Var;
        }
        return objE == aVar ? aVar : t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((PersonModalKt$PersonModal$7$2$2$2$1$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
