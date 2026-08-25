package com.arflix.tv;

import androidx.compose.runtime.State;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.MainActivity$onCreate$5$2$1", f = "MainActivity.kt", l = {}, m = "invokeSuspend", v = 2)
public final class MainActivity$onCreate$5$2$1 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ State<String> $appLanguage$delegate;
    int label;
    final /* synthetic */ MainActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainActivity$onCreate$5$2$1(MainActivity mainActivity, State<String> state, d7.d<? super MainActivity$onCreate$5$2$1> dVar) {
        super(2, dVar);
        this.this$0 = mainActivity;
        this.$appLanguage$delegate = state;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new MainActivity$onCreate$5$2$1(this.this$0, this.$appLanguage$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        this.this$0.getMediaRepository().get().setContentLanguage(MainActivity.onCreate$lambda$4$13(this.$appLanguage$delegate));
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((MainActivity$onCreate$5$2$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
