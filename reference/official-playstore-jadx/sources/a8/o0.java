package a8;

import kotlin.reflect.h;
import kotlin.reflect.i;

/* JADX INFO: loaded from: classes5.dex */
public final class o0 extends e1 implements kotlin.reflect.i {
    public final Object z;

    public o0(j0 j0Var, g8.p0 p0Var) {
        super(j0Var, p0Var);
        this.z = f4.f.o(2, new g0(this, 1));
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // kotlin.reflect.h
    public final h.a getSetter() {
        return (n0) this.z.getValue();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // kotlin.reflect.i, kotlin.reflect.h
    public final i.a getSetter() {
        return (n0) this.z.getValue();
    }

    public o0(j0 j0Var, String str, String str2, Object obj) {
        super(j0Var, str, str2, obj);
        this.z = f4.f.o(2, new g0(this, 1));
    }
}
