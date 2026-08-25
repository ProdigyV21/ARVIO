package a8;

import kotlin.reflect.full.IllegalCallableAccessException;
import kotlin.reflect.k;

/* JADX INFO: loaded from: classes5.dex */
public final class r0 extends p1 implements k.a {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final s0 f252t;

    public r0(s0 s0Var) {
        this.f252t = s0Var;
    }

    @Override // a8.l1
    public final r1 C() {
        return this.f252t;
    }

    @Override // kotlin.reflect.m.a
    public final kotlin.reflect.m c() {
        return this.f252t;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, x6.s] */
    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) throws IllegalCallableAccessException {
        ((r0) this.f252t.z.getValue()).call(obj, obj2, obj3);
        return x6.t0.f22605a;
    }
}
