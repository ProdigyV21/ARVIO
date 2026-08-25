package com.arflix.tv;

import androidx.compose.runtime.MutableState;
import ka.k0;
import kotlin.Metadata;
import kotlin.text.u;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.MainActivityKt$ArflixApp$7$1", f = "MainActivity.kt", l = {}, m = "invokeSuspend", v = 2)
public final class MainActivityKt$ArflixApp$7$1 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ String $currentRoute;
    final /* synthetic */ MutableState<Boolean> $iptvFullscreen$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainActivityKt$ArflixApp$7$1(String str, MutableState<Boolean> mutableState, d7.d<? super MainActivityKt$ArflixApp$7$1> dVar) {
        super(2, dVar);
        this.$currentRoute = str;
        this.$iptvFullscreen$delegate = mutableState;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new MainActivityKt$ArflixApp$7$1(this.$currentRoute, this.$iptvFullscreen$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        String str = this.$currentRoute;
        if (str == null || !u.P(str, "tv", false)) {
            MainActivityKt.ArflixApp$lambda$18(this.$iptvFullscreen$delegate, false);
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((MainActivityKt$ArflixApp$7$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
