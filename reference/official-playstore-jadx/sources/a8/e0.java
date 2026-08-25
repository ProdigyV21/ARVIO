package a8;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class e0 extends j0 implements kotlin.reflect.d, f0, w1 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ int f184n = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Class f185l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f186m = f4.f.o(2, new w(this, 8));

    public e0(Class cls) {
        this.f185l = cls;
    }

    public static j8.m D(f9.b bVar, l8.h hVar) {
        s9.j jVar = hVar.f20068a;
        f8.j jVar2 = new f8.j(jVar.f21707b, bVar.g(), 1);
        f9.f fVarI = bVar.i();
        List listSingletonList = Collections.singletonList(jVar.f21707b.j().j("Any").m());
        u9.p pVar = jVar.f21706a;
        j8.m mVar = new j8.m(jVar2, fVarI, 1, 1, listSingletonList, pVar);
        mVar.G0(new c0(pVar, mVar), kotlin.collections.b0.f19686i, null);
        return mVar;
    }

    public final f9.b E() {
        d8.m mVarI;
        f9.b bVar = c2.f175a;
        Class cls = this.f185l;
        if (cls.isArray()) {
            Class<?> componentType = cls.getComponentType();
            mVarI = componentType.isPrimitive() ? n9.c.d(componentType.getSimpleName()).i() : null;
            return mVarI != null ? new f9.b(d8.q.f14766l, mVarI.f14722l) : f9.b.j(d8.p.f14739g.g());
        }
        if (cls.equals(Void.TYPE)) {
            return c2.f175a;
        }
        mVarI = cls.isPrimitive() ? n9.c.d(cls.getSimpleName()).i() : null;
        if (mVarI != null) {
            return new f9.b(d8.q.f14766l, mVarI.f14721i);
        }
        f9.b bVarA = m8.c.a(cls);
        if (!bVarA.f15357c) {
            String str = f8.d.f15297a;
            f9.b bVar2 = (f9.b) f8.d.f15304h.get(bVarA.b().i());
            if (bVar2 != null) {
                return bVar2;
            }
        }
        return bVarA;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // a8.f0
    /* JADX INFO: renamed from: F, reason: merged with bridge method [inline-methods] */
    public final g8.f getDescriptor() {
        return ((a0) this.f186m.getValue()).a();
    }

    @Override // kotlin.reflect.d
    public final boolean a() {
        return getDescriptor().o() == 2;
    }

    @Override // kotlin.jvm.internal.f
    public final Class c() {
        return this.f185l;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // kotlin.reflect.d
    public final Collection d() {
        z1 z1Var = ((a0) this.f186m.getValue()).f139g;
        kotlin.reflect.m mVar = a0.f134p[5];
        return (Collection) z1Var.invoke();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Object, x6.s] */
    @Override // kotlin.reflect.d
    public final Object e() {
        return ((a0) this.f186m.getValue()).f140h.getValue();
    }

    public final boolean equals(Object obj) {
        return (obj instanceof e0) && com.google.common.util.concurrent.r0.n(this).equals(com.google.common.util.concurrent.r0.n((kotlin.reflect.d) obj));
    }

    @Override // kotlin.reflect.b
    public final List getAnnotations() {
        throw null;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // kotlin.reflect.d
    public final Collection h() {
        z1 z1Var = ((a0) this.f186m.getValue()).f138f;
        kotlin.reflect.m mVar = a0.f134p[4];
        return (Collection) z1Var.invoke();
    }

    public final int hashCode() {
        return com.google.common.util.concurrent.r0.n(this).hashCode();
    }

    @Override // kotlin.reflect.d
    public final boolean i() {
        return getDescriptor().i();
    }

    @Override // kotlin.reflect.d
    public final boolean isAbstract() {
        return getDescriptor().o() == 4;
    }

    @Override // kotlin.reflect.d
    public final boolean l(Object obj) {
        Map map = m8.c.f20269d;
        Class cls = this.f185l;
        Integer num = (Integer) map.get(cls);
        if (num != null) {
            return kotlin.jvm.internal.q0.f(num.intValue(), obj);
        }
        Class cls2 = (Class) m8.c.f20268c.get(cls);
        if (cls2 != null) {
            cls = cls2;
        }
        return cls.isInstance(obj);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // kotlin.reflect.d
    public final Collection n() {
        z1 z1Var = ((a0) this.f186m.getValue()).f146o;
        kotlin.reflect.m mVar = a0.f134p[16];
        return (Collection) z1Var.invoke();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // kotlin.reflect.d
    public final String o() {
        z1 z1Var = ((a0) this.f186m.getValue()).f137e;
        kotlin.reflect.m mVar = a0.f134p[3];
        return (String) z1Var.invoke();
    }

    @Override // kotlin.reflect.d
    public final boolean q() {
        return getDescriptor().q();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // kotlin.reflect.d
    public final String r() {
        z1 z1Var = ((a0) this.f186m.getValue()).f136d;
        kotlin.reflect.m mVar = a0.f134p[2];
        return (String) z1Var.invoke();
    }

    @Override // a8.j0
    public final Collection t() {
        g8.f descriptor = getDescriptor();
        return (descriptor.p0() == 2 || descriptor.p0() == 6) ? kotlin.collections.z.f19728i : descriptor.h();
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("class ");
        f9.b bVarE = E();
        f9.c cVarG = bVarE.g();
        String strConcat = cVarG.d() ? "" : cVarG.b().concat(".");
        sb2.append(strConcat + bVarE.h().b().replace('.', '$'));
        return sb2.toString();
    }

    @Override // a8.j0
    public final Collection u(f9.f fVar) {
        p9.n nVarL = getDescriptor().m().l();
        o8.e eVar = o8.e.f20794l;
        return kotlin.collections.x.I0(nVarL.c(fVar, eVar), getDescriptor().j0().c(fVar, eVar));
    }

    @Override // a8.j0
    public final g8.p0 v(int i10) {
        a9.t0 t0Var;
        Class<?> declaringClass;
        Class cls = this.f185l;
        if (cls.getSimpleName().equals("DefaultImpls") && (declaringClass = cls.getDeclaringClass()) != null && declaringClass.isInterface()) {
            return ((e0) kotlin.jvm.internal.l0.f19747a.b(declaringClass)).v(i10);
        }
        g8.f descriptor = getDescriptor();
        kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.k kVar = descriptor instanceof kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.k ? (kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.k) descriptor : null;
        if (kVar == null || (t0Var = (a9.t0) com.google.common.util.concurrent.r0.m(kVar.f19866o, d9.q.f14872j, i10)) == null) {
            return null;
        }
        s9.m mVar = kVar.f19873v;
        return (g8.p0) e2.f(this.f185l, t0Var, mVar.f21732b, mVar.f21734d, kVar.f19867p, d0.f178i);
    }

    @Override // a8.j0
    public final Collection y(f9.f fVar) {
        p9.n nVarL = getDescriptor().m().l();
        o8.e eVar = o8.e.f20794l;
        return kotlin.collections.x.I0(nVarL.g(fVar, eVar), getDescriptor().j0().g(fVar, eVar));
    }
}
