package a8;

import kotlin.reflect.m;
import kotlin.reflect.p;

/* JADX INFO: loaded from: classes5.dex */
public class k1 extends r1 implements kotlin.reflect.p {
    public final Object x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final Object f210y;

    public k1(j0 j0Var, String str, String str2) {
        super(j0Var, str, str2, null, kotlin.jvm.internal.e.NO_RECEIVER);
        this.x = f4.f.o(2, new j1(this, 0));
        this.f210y = f4.f.o(2, new j1(this, 1));
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // a8.r1
    public final n1 E() {
        return (i1) this.x.getValue();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // kotlin.reflect.m
    public final m.b getGetter() {
        return (i1) this.x.getValue();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        return ((i1) this.x.getValue()).call(obj, obj2);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // kotlin.reflect.m
    public final p.a getGetter() {
        return (i1) this.x.getValue();
    }

    public k1(j0 j0Var, g8.p0 p0Var) {
        super(j0Var, p0Var);
        this.x = f4.f.o(2, new j1(this, 0));
        this.f210y = f4.f.o(2, new j1(this, 1));
    }
}
