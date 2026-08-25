package kotlin.jvm.internal;

import a8.x1;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class g implements kotlin.reflect.d, f {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final Map f19739l;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Class f19740i;

    static {
        int i10 = 0;
        List listE = t7.a.E(r7.a.class, r7.l.class, r7.p.class, r7.q.class, r7.r.class, r7.s.class, r7.t.class, r7.u.class, r7.v.class, r7.w.class, r7.b.class, r7.c.class, r7.d.class, r7.e.class, r7.f.class, r7.g.class, r7.h.class, r7.i.class, r7.j.class, r7.k.class, r7.m.class, r7.n.class, r7.o.class);
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(listE, 10));
        for (Object obj : listE) {
            int i11 = i10 + 1;
            if (i10 < 0) {
                t7.a.Q();
                throw null;
            }
            arrayList.add(new x6.x((Class) obj, Integer.valueOf(i10)));
            i10 = i11;
        }
        f19739l = kotlin.collections.h0.A0(arrayList);
    }

    public g(Class cls) {
        this.f19740i = cls;
    }

    public static void m() {
        throw new x1();
    }

    @Override // kotlin.reflect.d
    public final boolean a() {
        m();
        throw null;
    }

    @Override // kotlin.jvm.internal.f
    public final Class c() {
        return this.f19740i;
    }

    @Override // kotlin.reflect.d
    public final Collection d() {
        m();
        throw null;
    }

    @Override // kotlin.reflect.d
    public final Object e() {
        m();
        throw null;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof g) && com.google.common.util.concurrent.r0.n(this).equals(com.google.common.util.concurrent.r0.n((kotlin.reflect.d) obj));
    }

    @Override // kotlin.reflect.b
    public final List getAnnotations() {
        throw null;
    }

    @Override // kotlin.reflect.d
    public final Collection h() {
        m();
        throw null;
    }

    public final int hashCode() {
        return com.google.common.util.concurrent.r0.n(this).hashCode();
    }

    @Override // kotlin.reflect.d
    public final boolean i() {
        m();
        throw null;
    }

    @Override // kotlin.reflect.d
    public final boolean isAbstract() {
        m();
        throw null;
    }

    @Override // kotlin.reflect.d
    public final boolean l(Object obj) {
        Map map = f19739l;
        Class clsN = this.f19740i;
        Integer num = (Integer) map.get(clsN);
        if (num != null) {
            return q0.f(num.intValue(), obj);
        }
        if (clsN.isPrimitive()) {
            clsN = com.google.common.util.concurrent.r0.n(l0.f19747a.b(clsN));
        }
        return clsN.isInstance(obj);
    }

    @Override // kotlin.reflect.d
    public final Collection n() {
        m();
        throw null;
    }

    @Override // kotlin.reflect.d
    public final String o() {
        String strB;
        Class cls = this.f19740i;
        String strConcat = null;
        if (cls.isAnonymousClass() || cls.isLocalClass()) {
            return null;
        }
        if (!cls.isArray()) {
            String strB2 = p.b(cls.getName());
            return strB2 == null ? cls.getCanonicalName() : strB2;
        }
        Class<?> componentType = cls.getComponentType();
        if (componentType.isPrimitive() && (strB = p.b(componentType.getName())) != null) {
            strConcat = strB.concat("Array");
        }
        return strConcat == null ? "kotlin.Array" : strConcat;
    }

    @Override // kotlin.reflect.d
    public final boolean q() {
        m();
        throw null;
    }

    @Override // kotlin.reflect.d
    public final String r() {
        String strF;
        Class cls = this.f19740i;
        String strConcat = null;
        if (cls.isAnonymousClass()) {
            return null;
        }
        if (!cls.isLocalClass()) {
            if (!cls.isArray()) {
                String strF2 = p.f(cls.getName());
                return strF2 == null ? cls.getSimpleName() : strF2;
            }
            Class<?> componentType = cls.getComponentType();
            if (componentType.isPrimitive() && (strF = p.f(componentType.getName())) != null) {
                strConcat = strF.concat("Array");
            }
            return strConcat == null ? "Array" : strConcat;
        }
        String simpleName = cls.getSimpleName();
        Method enclosingMethod = cls.getEnclosingMethod();
        if (enclosingMethod != null) {
            return kotlin.text.o.D0(simpleName, enclosingMethod.getName() + '$', simpleName);
        }
        Constructor<?> enclosingConstructor = cls.getEnclosingConstructor();
        if (enclosingConstructor == null) {
            return kotlin.text.o.C0('$', simpleName, simpleName);
        }
        return kotlin.text.o.D0(simpleName, enclosingConstructor.getName() + '$', simpleName);
    }

    public final String toString() {
        return this.f19740i.toString() + " (Kotlin reflection is not available)";
    }
}
