package a8;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes5.dex */
public abstract class c2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f9.b f175a = f9.b.j(new f9.c("java.lang.Void"));

    public static m a(g8.w wVar) {
        String strQ = qb.l.q(wVar);
        if (strQ == null) {
            if (wVar instanceof g8.q0) {
                strQ = p8.a0.a(m9.d.i(wVar).getName().d());
            } else if (wVar instanceof g8.r0) {
                String strD = m9.d.i(wVar).getName().d();
                f9.c cVar = p8.a0.f21078a;
                StringBuilder sb2 = new StringBuilder("set");
                sb2.append(p8.a0.b(strD) ? strD.substring(2) : kotlin.reflect.b0.e(strD));
                strQ = sb2.toString();
            } else {
                strQ = wVar.getName().d();
            }
        }
        return new m(new e9.e(strQ, com.google.common.util.concurrent.r0.f(wVar, 1)));
    }

    public static k2.c b(g8.p0 p0Var) {
        g8.p0 p0VarA = ((g8.p0) i9.f.t(p0Var)).a();
        if (p0VarA instanceof kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.u) {
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.u uVar = (kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.u) p0VarA;
            a9.t0 t0Var = uVar.K;
            d9.h hVar = (d9.h) com.google.common.util.concurrent.r0.l(t0Var, d9.q.f14866d);
            if (hVar != null) {
                return new p(p0VarA, t0Var, hVar, uVar.L, uVar.M);
            }
        } else if (p0VarA instanceof r8.h) {
            g8.v0 v0VarF = ((r8.h) p0VarA).f();
            v8.a aVar = v0VarF instanceof v8.a ? (v8.a) v0VarF : null;
            m8.s sVarF = aVar != null ? aVar.f() : null;
            if (sVarF instanceof m8.u) {
                return new n(((m8.u) sVarF).f20294a);
            }
            if (!(sVarF instanceof m8.x)) {
                throw new x1("Incorrect resolution sequence for Java field " + p0VarA + " (source = " + sVarF + ')');
            }
            Method method = ((m8.x) sVarF).f20296a;
            g8.r0 r0Var = ((j8.l0) p0VarA).H;
            g8.v0 v0VarF2 = r0Var != null ? r0Var.f() : null;
            v8.a aVar2 = v0VarF2 instanceof v8.a ? (v8.a) v0VarF2 : null;
            m8.s sVarF2 = aVar2 != null ? aVar2.f() : null;
            m8.x xVar = sVarF2 instanceof m8.x ? (m8.x) sVarF2 : null;
            return new o(method, xVar != null ? xVar.f20296a : null);
        }
        m mVarA = a(p0VarA.getGetter());
        g8.r0 setter = p0VarA.getSetter();
        return new q(mVarA, setter != null ? a(setter) : null);
    }

    public static f4.f c(g8.w wVar) {
        Method method;
        g8.w wVarA = ((g8.w) i9.f.t(wVar)).a();
        if (wVarA instanceof kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.c) {
            kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.c cVar = (kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.c) wVarA;
            kotlin.reflect.jvm.internal.impl.protobuf.c0 c0VarD0 = cVar.d0();
            if (c0VarD0 instanceof a9.i0) {
                kotlin.reflect.jvm.internal.impl.protobuf.l lVar = e9.h.f15080a;
                e9.e eVarC = e9.h.c((a9.i0) c0VarD0, cVar.G(), cVar.D());
                if (eVarC != null) {
                    return new m(eVarC);
                }
            }
            if (c0VarD0 instanceof a9.q) {
                kotlin.reflect.jvm.internal.impl.protobuf.l lVar2 = e9.h.f15080a;
                e9.e eVarA = e9.h.a((a9.q) c0VarD0, cVar.G(), cVar.D());
                if (eVarA != null) {
                    String str = eVarA.f15071b;
                    String str2 = eVarA.f15072c;
                    if (i9.h.b(wVar.d())) {
                        return new m(eVarA);
                    }
                    if (!i9.h.c(wVar.d())) {
                        return new l(eVarA);
                    }
                    g8.j jVar = (g8.j) wVar;
                    if (jVar.Z()) {
                        if (!kotlin.jvm.internal.p.a(str, "constructor-impl") || !kotlin.text.u.K(str2, ")V", false)) {
                            throw new IllegalArgumentException(("Invalid signature: " + eVarA).toString());
                        }
                    } else {
                        if (!kotlin.jvm.internal.p.a(str, "constructor-impl")) {
                            throw new IllegalArgumentException(("Invalid signature: " + eVarA).toString());
                        }
                        String strB = e9.b.b(m9.d.f(jVar.a0()).c());
                        if (kotlin.text.u.K(str2, ")V", false)) {
                            eVarA = new e9.e(str, kotlin.text.o.s0(str2, "V") + strB);
                        } else if (!kotlin.text.u.K(str2, strB, false)) {
                            throw new IllegalArgumentException(("Invalid signature: " + eVarA).toString());
                        }
                    }
                    return new m(eVarA);
                }
            }
            return a(wVarA);
        }
        if (wVarA instanceof r8.g) {
            g8.v0 v0VarF = ((r8.g) wVarA).f();
            v8.a aVar = v0VarF instanceof v8.a ? (v8.a) v0VarF : null;
            m8.s sVarF = aVar != null ? aVar.f() : null;
            m8.x xVar = sVarF instanceof m8.x ? (m8.x) sVarF : null;
            if (xVar != null && (method = xVar.f20296a) != null) {
                return new k(method);
            }
            throw new x1("Incorrect resolution sequence for Java method " + wVarA);
        }
        if (!(wVarA instanceof r8.b)) {
            if ((wVarA.getName().equals(d8.q.f14758c) && i9.q.n(wVarA)) || ((wVarA.getName().equals(d8.q.f14756a) && i9.q.n(wVarA)) || (kotlin.jvm.internal.p.a(wVarA.getName(), f8.a.f15292e) && wVarA.e().isEmpty()))) {
                return a(wVarA);
            }
            throw new x1("Unknown origin of " + wVarA + " (" + wVarA.getClass() + ')');
        }
        g8.v0 v0VarF2 = ((r8.b) wVarA).f();
        v8.a aVar2 = v0VarF2 instanceof v8.a ? (v8.a) v0VarF2 : null;
        m8.s sVarF2 = aVar2 != null ? aVar2.f() : null;
        if (sVarF2 instanceof m8.r) {
            return new j(((m8.r) sVarF2).f20292a);
        }
        if (sVarF2 instanceof m8.o) {
            Class cls = ((m8.o) sVarF2).f20288a;
            if (cls.isAnnotation()) {
                return new i(cls);
            }
        }
        throw new x1("Incorrect resolution sequence for Java constructor " + wVarA + " (" + sVarF2 + ')');
    }
}
