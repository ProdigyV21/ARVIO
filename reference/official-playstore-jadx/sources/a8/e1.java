package a8;

import kotlin.reflect.m;
import kotlin.reflect.n;

/* JADX INFO: loaded from: classes5.dex */
public class e1 extends r1 implements kotlin.reflect.n {
    public final Object x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final Object f187y;

    public e1(j0 j0Var, g8.p0 p0Var) {
        super(j0Var, p0Var);
        this.x = f4.f.o(2, new d1(this, 0));
        this.f187y = f4.f.o(2, new d1(this, 1));
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // a8.r1
    public final n1 E() {
        return (c1) this.x.getValue();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // kotlin.reflect.n
    public final Object get() {
        return ((c1) this.x.getValue()).call(new Object[0]);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // kotlin.reflect.n
    public final Object getDelegate() {
        return this.f187y.getValue();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // kotlin.reflect.m
    public final m.b getGetter() {
        return (c1) this.x.getValue();
    }

    @Override // r7.a
    public final Object invoke() {
        return get();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // kotlin.reflect.m
    public final n.a getGetter() {
        return (c1) this.x.getValue();
    }

    public e1(j0 j0Var, String str, String str2, Object obj) {
        super(j0Var, str, str2, null, obj);
        this.x = f4.f.o(2, new d1(this, 0));
        this.f187y = f4.f.o(2, new d1(this, 1));
    }
}
