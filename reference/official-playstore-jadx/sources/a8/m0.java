package a8;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class m0 extends u implements kotlin.jvm.internal.k, kotlin.reflect.g, g {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final /* synthetic */ kotlin.reflect.m[] f218w;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final j0 f219q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final String f220r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final Object f221s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final z1 f222t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final Object f223u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final Object f224v;

    static {
        kotlin.jvm.internal.m0 m0Var = kotlin.jvm.internal.l0.f19747a;
        f218w = new kotlin.reflect.m[]{m0Var.g(new kotlin.jvm.internal.b0(m0Var.b(m0.class), "descriptor", "getDescriptor()Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;"))};
    }

    public m0(j0 j0Var, String str, String str2, g8.w wVar, Object obj) {
        this.f219q = j0Var;
        this.f220r = str2;
        this.f221s = obj;
        this.f222t = m2.f0.B(wVar, new l0(this, str, 0));
        this.f223u = f4.f.o(2, new k0(this, 0));
        this.f224v = f4.f.o(2, new k0(this, 1));
    }

    public static final b8.u B(m0 m0Var, Constructor constructor, g8.w wVar, boolean z) {
        Object obj = m0Var.f221s;
        Class cls = null;
        if (!z) {
            g8.e eVar = wVar instanceof g8.e ? (g8.e) wVar : null;
            if (eVar != null && !g8.q.e(eVar.getVisibility()) && !i9.h.e(eVar.a0()) && !i9.f.q(eVar.a0())) {
                List listE = eVar.e();
                if (!listE.isEmpty()) {
                    Iterator it = listE.iterator();
                    while (it.hasNext()) {
                        if (a.a.W(((g8.e1) it.next()).getType())) {
                            if (m0Var.A()) {
                                return new b8.f(constructor, m2.f0.g(obj, m0Var.x()), 0);
                            }
                            Class declaringClass = constructor.getDeclaringClass();
                            Type[] genericParameterTypes = constructor.getGenericParameterTypes();
                            return new b8.g(constructor, declaringClass, cls, (Type[]) (genericParameterTypes.length <= 1 ? new Type[0] : kotlin.collections.r.S(genericParameterTypes, 0, genericParameterTypes.length - 1)), 0);
                        }
                    }
                }
            }
        }
        if (m0Var.A()) {
            return new b8.f(constructor, m2.f0.g(obj, m0Var.x()), 1);
        }
        Class declaringClass2 = constructor.getDeclaringClass();
        Class declaringClass3 = constructor.getDeclaringClass();
        Class<?> declaringClass4 = declaringClass3.getDeclaringClass();
        return new b8.g(constructor, declaringClass2, (declaringClass4 == null || Modifier.isStatic(declaringClass3.getModifiers())) ? null : declaringClass4, constructor.getGenericParameterTypes(), 1);
    }

    @Override // a8.u
    public final boolean A() {
        return !kotlin.jvm.internal.p.a(this.f221s, kotlin.jvm.internal.e.NO_RECEIVER);
    }

    @Override // a8.u
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public final g8.w x() {
        kotlin.reflect.m mVar = f218w[0];
        return (g8.w) this.f222t.invoke();
    }

    public final boolean equals(Object obj) {
        m0 m0VarB = e2.b(obj);
        return m0VarB != null && kotlin.jvm.internal.p.a(this.f219q, m0VarB.f219q) && getName().equals(m0VarB.getName()) && kotlin.jvm.internal.p.a(this.f220r, m0VarB.f220r) && kotlin.jvm.internal.p.a(this.f221s, m0VarB.f221s);
    }

    @Override // kotlin.jvm.internal.k
    public final int getArity() {
        return u().a().size();
    }

    @Override // kotlin.reflect.c
    public final String getName() {
        return x().getName().d();
    }

    public final int hashCode() {
        return this.f220r.hashCode() + ((getName().hashCode() + (this.f219q.hashCode() * 31)) * 31);
    }

    @Override // r7.a
    public final Object invoke() {
        return call(new Object[0]);
    }

    @Override // kotlin.reflect.g
    public final boolean isExternal() {
        return x().isExternal();
    }

    @Override // kotlin.reflect.g
    public final boolean isInfix() {
        return x().isInfix();
    }

    @Override // kotlin.reflect.g
    public final boolean isInline() {
        return x().isInline();
    }

    @Override // kotlin.reflect.g
    public final boolean isOperator() {
        return x().isOperator();
    }

    @Override // kotlin.reflect.c, kotlin.reflect.g
    public final boolean isSuspend() {
        return x().isSuspend();
    }

    public final String toString() {
        h9.h hVar = b2.f169a;
        return b2.b(x());
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // a8.u
    public final b8.e u() {
        return (b8.e) this.f223u.getValue();
    }

    @Override // a8.u
    public final j0 v() {
        return this.f219q;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    @Override // a8.u
    public final b8.e w() {
        return (b8.e) this.f224v.getValue();
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        return call(obj);
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        return call(obj, obj2);
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        return call(obj, obj2, obj3);
    }

    @Override // r7.r
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        return call(obj, obj2, obj3, obj4);
    }

    @Override // r7.s
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return call(obj, obj2, obj3, obj4, obj5);
    }

    @Override // r7.t
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        return call(obj, obj2, obj3, obj4, obj5, obj6);
    }

    public m0(j0 j0Var, g8.w wVar) {
        this(j0Var, wVar.getName().d(), c2.c(wVar).c(), wVar, kotlin.jvm.internal.e.NO_RECEIVER);
    }

    @Override // r7.u
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    @Override // r7.v
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    @Override // r7.w
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    @Override // r7.b
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    @Override // r7.c
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11);
    }

    @Override // r7.e
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13);
    }

    @Override // r7.f
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14);
    }

    @Override // r7.g
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15);
    }

    @Override // r7.h
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16);
    }

    @Override // r7.i
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16, Object obj17) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17);
    }

    @Override // r7.j
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16, Object obj17, Object obj18) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17, obj18);
    }

    @Override // r7.k
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16, Object obj17, Object obj18, Object obj19) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17, obj18, obj19);
    }

    @Override // r7.m
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16, Object obj17, Object obj18, Object obj19, Object obj20) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17, obj18, obj19, obj20);
    }

    @Override // r7.n
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16, Object obj17, Object obj18, Object obj19, Object obj20, Object obj21) {
        return call(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17, obj18, obj19, obj20, obj21);
    }
}
