package a8;

import kotlin.reflect.h;
import kotlin.reflect.j;

/* JADX INFO: loaded from: classes5.dex */
public final class q0 extends h1 implements kotlin.reflect.j {
    public final Object z;

    public q0(j0 j0Var, String str, String str2, Object obj) {
        super(j0Var, str, str2, obj);
        this.z = f4.f.o(2, new g0(this, 2));
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // kotlin.reflect.h
    public final h.a getSetter() {
        return (p0) this.z.getValue();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // kotlin.reflect.j
    public final void set(Object obj, Object obj2) {
        ((p0) this.z.getValue()).call(obj, obj2);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // kotlin.reflect.j, kotlin.reflect.h
    public final j.a getSetter() {
        return (p0) this.z.getValue();
    }

    public q0(j0 j0Var, g8.p0 p0Var) {
        super(j0Var, p0Var);
        this.z = f4.f.o(2, new g0(this, 2));
    }
}
