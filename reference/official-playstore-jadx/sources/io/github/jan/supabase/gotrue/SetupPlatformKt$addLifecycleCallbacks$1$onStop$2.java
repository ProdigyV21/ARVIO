package io.github.jan.supabase.gotrue;

import d7.d;
import f7.e;
import f7.j;
import k2.c;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 9, 0})
@e(c = "io.github.jan.supabase.gotrue.SetupPlatformKt$addLifecycleCallbacks$1$onStop$2", f = "setupPlatform.kt", l = {}, m = "invokeSuspend")
public final class SetupPlatformKt$addLifecycleCallbacks$1$onStop$2 extends j implements p<k0, d<? super t0>, Object> {
    final /* synthetic */ Auth $gotrue;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SetupPlatformKt$addLifecycleCallbacks$1$onStop$2(Auth auth, d<? super SetupPlatformKt$addLifecycleCallbacks$1$onStop$2> dVar) {
        super(2, dVar);
        this.$gotrue = auth;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new SetupPlatformKt$addLifecycleCallbacks$1$onStop$2(this.$gotrue, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        c.G(obj);
        this.$gotrue.stopAutoRefreshForCurrentSession();
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super t0> dVar) {
        return ((SetupPlatformKt$addLifecycleCallbacks$1$onStop$2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
