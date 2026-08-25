package a8;

import kotlin.reflect.full.IllegalCallableAccessException;
import kotlin.reflect.i;

/* JADX INFO: loaded from: classes5.dex */
public final class n0 extends p1 implements i.a {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final o0 f228t;

    public n0(o0 o0Var) {
        this.f228t = o0Var;
    }

    @Override // a8.l1
    public final r1 C() {
        return this.f228t;
    }

    @Override // kotlin.reflect.m.a
    public final kotlin.reflect.m c() {
        return this.f228t;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, x6.s] */
    @Override // r7.l
    public final Object invoke(Object obj) throws IllegalCallableAccessException {
        ((n0) this.f228t.z.getValue()).call(obj);
        return x6.t0.f22605a;
    }
}
