package a8;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import kotlin.reflect.full.IllegalPropertyDelegateAccessException;

/* JADX INFO: loaded from: classes5.dex */
public abstract class r1 extends u implements kotlin.reflect.m {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final Object f253w = new Object();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final j0 f254q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final String f255r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final String f256s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final Object f257t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final Object f258u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final z1 f259v;

    public r1(j0 j0Var, String str, String str2, g8.p0 p0Var, Object obj) {
        this.f254q = j0Var;
        this.f255r = str;
        this.f256s = str2;
        this.f257t = obj;
        this.f258u = f4.f.o(2, new q1(this, 1));
        this.f259v = m2.f0.B(p0Var, new q1(this, 0));
    }

    @Override // a8.u
    public final boolean A() {
        return !kotlin.jvm.internal.p.a(this.f257t, kotlin.jvm.internal.e.NO_RECEIVER);
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Object, x6.s] */
    public final Member B() {
        if (!x().z()) {
            return null;
        }
        f9.b bVar = c2.f175a;
        k2.c cVarB = c2.b(x());
        if (cVarB instanceof p) {
            p pVar = (p) cVarB;
            c9.f fVar = pVar.f239e;
            d9.h hVar = pVar.f238d;
            if ((hVar.f14819l & 16) == 16) {
                d9.e eVar = hVar.f14824q;
                int i10 = eVar.f14805l;
                if ((i10 & 1) != 1 || (i10 & 2) != 2) {
                    return null;
                }
                return this.f254q.p(fVar.getString(eVar.f14806m), fVar.getString(eVar.f14807n));
            }
        }
        return (Field) this.f258u.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Object C(Member member, Object obj) throws IllegalPropertyDelegateAccessException {
        try {
            Object obj2 = f253w;
            if (obj == obj2 && x().M() == null) {
                throw new RuntimeException("'" + this + "' is not an extension property and thus getExtensionDelegate() is not going to work, use getDelegate() instead");
            }
            Object objG = A() ? m2.f0.g(this.f257t, x()) : obj;
            if (objG == obj2) {
                objG = null;
            }
            if (!A()) {
                obj = null;
            }
            if (obj == obj2) {
                obj = null;
            }
            AccessibleObject accessibleObject = member instanceof AccessibleObject ? (AccessibleObject) member : null;
            if (accessibleObject != null) {
                accessibleObject.setAccessible(z7.b.a(this));
            }
            if (member == 0) {
                return null;
            }
            if (member instanceof Field) {
                return ((Field) member).get(objG);
            }
            if (!(member instanceof Method)) {
                throw new AssertionError("delegate field/method " + member + " neither field nor method");
            }
            int length = ((Method) member).getParameterTypes().length;
            if (length == 0) {
                return ((Method) member).invoke(null, null);
            }
            if (length == 1) {
                Method method = (Method) member;
                if (objG == null) {
                    objG = e2.e(((Method) member).getParameterTypes()[0]);
                }
                return method.invoke(null, objG);
            }
            if (length == 2) {
                Method method2 = (Method) member;
                if (obj == null) {
                    obj = e2.e(((Method) member).getParameterTypes()[1]);
                }
                return method2.invoke(null, objG, obj);
            }
            throw new AssertionError("delegate method " + member + " should take 0, 1, or 2 parameters");
        } catch (IllegalAccessException e5) {
            throw new IllegalPropertyDelegateAccessException("Cannot obtain the delegate of a non-accessible property. Use \"isAccessible = true\" to make the property accessible", e5);
        }
    }

    @Override // a8.u
    /* JADX INFO: renamed from: D, reason: merged with bridge method [inline-methods] */
    public final g8.p0 x() {
        return (g8.p0) this.f259v.invoke();
    }

    public abstract n1 E();

    public final boolean equals(Object obj) {
        r1 r1VarC = e2.c(obj);
        return r1VarC != null && kotlin.jvm.internal.p.a(this.f254q, r1VarC.f254q) && kotlin.jvm.internal.p.a(this.f255r, r1VarC.f255r) && kotlin.jvm.internal.p.a(this.f256s, r1VarC.f256s) && kotlin.jvm.internal.p.a(this.f257t, r1VarC.f257t);
    }

    @Override // kotlin.reflect.c
    public final String getName() {
        return this.f255r;
    }

    public final int hashCode() {
        return this.f256s.hashCode() + androidx.compose.foundation.c.c(this.f254q.hashCode() * 31, 31, this.f255r);
    }

    @Override // kotlin.reflect.m
    public final boolean isConst() {
        return x().isConst();
    }

    @Override // kotlin.reflect.m
    public final boolean isLateinit() {
        return x().t0();
    }

    @Override // kotlin.reflect.c, kotlin.reflect.g
    public final boolean isSuspend() {
        return false;
    }

    public final String toString() {
        h9.h hVar = b2.f169a;
        return b2.c(x());
    }

    @Override // a8.u
    public final b8.e u() {
        return E().u();
    }

    @Override // a8.u
    public final j0 v() {
        return this.f254q;
    }

    @Override // a8.u
    public final b8.e w() {
        E().getClass();
        return null;
    }

    public r1(j0 j0Var, g8.p0 p0Var) {
        this(j0Var, p0Var.getName().d(), c2.b(p0Var).c(), p0Var, kotlin.jvm.internal.e.NO_RECEIVER);
    }
}
