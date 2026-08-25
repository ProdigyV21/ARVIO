package com.arflix.tv.ui.screens.plugin;

import f7.j;
import ka.k0;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.plugin.PluginScreenKt$PluginScreen$6$1", f = "PluginScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class PluginScreenKt$PluginScreen$6$1 extends j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ boolean $modalOpen;
    final /* synthetic */ l<Boolean, t0> $onModalStateChanged;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public PluginScreenKt$PluginScreen$6$1(l<? super Boolean, t0> lVar, boolean z, d7.d<? super PluginScreenKt$PluginScreen$6$1> dVar) {
        super(2, dVar);
        this.$onModalStateChanged = lVar;
        this.$modalOpen = z;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new PluginScreenKt$PluginScreen$6$1(this.$onModalStateChanged, this.$modalOpen, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        this.$onModalStateChanged.invoke(Boolean.valueOf(this.$modalOpen));
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((PluginScreenKt$PluginScreen$6$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
