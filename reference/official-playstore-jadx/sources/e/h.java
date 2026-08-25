package e;

import kotlin.jvm.internal.f0;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
public final class h extends f7.j implements q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ f0 f14950i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(f0 f0Var, d7.d dVar) {
        super(3, dVar);
        this.f14950i = f0Var;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        h hVar = new h(this.f14950i, (d7.d) obj3);
        t0 t0Var = t0.f22605a;
        hVar.invokeSuspend(t0Var);
        return t0Var;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        k2.c.G(obj);
        this.f14950i.f19738i = true;
        return t0.f22605a;
    }
}
