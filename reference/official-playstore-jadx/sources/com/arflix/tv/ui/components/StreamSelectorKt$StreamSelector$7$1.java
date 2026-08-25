package com.arflix.tv.ui.components;

import androidx.compose.runtime.MutableIntState;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.components.StreamSelectorKt$StreamSelector$7$1", f = "StreamSelector.kt", l = {367}, m = "invokeSuspend", v = 2)
public final class StreamSelectorKt$StreamSelector$7$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ androidx.tv.foundation.lazy.list.m0 $addonListState;
    final /* synthetic */ MutableIntState $focusedTabIndex$delegate;
    final /* synthetic */ List<String> $tabLabels;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StreamSelectorKt$StreamSelector$7$1(androidx.tv.foundation.lazy.list.m0 m0Var, List<String> list, MutableIntState mutableIntState, d7.d<? super StreamSelectorKt$StreamSelector$7$1> dVar) {
        super(2, dVar);
        this.$addonListState = m0Var;
        this.$tabLabels = list;
        this.$focusedTabIndex$delegate = mutableIntState;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new StreamSelectorKt$StreamSelector$7$1(this.$addonListState, this.$tabLabels, this.$focusedTabIndex$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            androidx.tv.foundation.lazy.list.m0 m0Var = this.$addonListState;
            int intValue = this.$focusedTabIndex$delegate.getIntValue();
            int size = this.$tabLabels.size();
            this.label = 1;
            Object objScrollToKeepFocusVisible = StreamSelectorKt.scrollToKeepFocusVisible(m0Var, intValue, size, this);
            e7.a aVar = e7.a.f15033i;
            if (objScrollToKeepFocusVisible == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((StreamSelectorKt$StreamSelector$7$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
