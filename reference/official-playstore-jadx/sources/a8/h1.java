package a8;

import java.lang.reflect.Member;
import kotlin.reflect.m;
import kotlin.reflect.o;

/* JADX INFO: loaded from: classes5.dex */
public class h1 extends r1 implements kotlin.reflect.o {
    public final Object x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final Object f200y;

    public h1(j0 j0Var, g8.p0 p0Var) {
        super(j0Var, p0Var);
        this.x = f4.f.o(2, new g1(this, 0));
        this.f200y = f4.f.o(2, new g1(this, 1));
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // a8.r1
    public final n1 E() {
        return (f1) this.x.getValue();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // kotlin.reflect.o
    public final Object get(Object obj) {
        return ((f1) this.x.getValue()).call(obj);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // kotlin.reflect.o
    public final Object getDelegate(Object obj) {
        return C((Member) this.f200y.getValue(), obj);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // kotlin.reflect.m
    public final m.b getGetter() {
        return (f1) this.x.getValue();
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        return get(obj);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // kotlin.reflect.m
    public final o.a getGetter() {
        return (f1) this.x.getValue();
    }

    public h1(j0 j0Var, String str, String str2, Object obj) {
        super(j0Var, str, str2, null, obj);
        this.x = f4.f.o(2, new g1(this, 0));
        this.f200y = f4.f.o(2, new g1(this, 1));
    }
}
