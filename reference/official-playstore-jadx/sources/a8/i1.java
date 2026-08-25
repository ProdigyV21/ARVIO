package a8;

import kotlin.reflect.p;

/* JADX INFO: loaded from: classes5.dex */
public final class i1 extends n1 implements p.a {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final k1 f202t;

    public i1(k1 k1Var) {
        this.f202t = k1Var;
    }

    @Override // a8.l1
    public final r1 C() {
        return this.f202t;
    }

    @Override // kotlin.reflect.m.a
    public final kotlin.reflect.m c() {
        return this.f202t;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, x6.s] */
    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        return ((i1) this.f202t.x.getValue()).call(obj, obj2);
    }
}
