package a8;

import java.lang.reflect.Constructor;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes5.dex */
public final class k0 extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f208i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ m0 f209l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ k0(m0 m0Var, int i10) {
        super(0);
        this.f208i = i10;
        this.f209l = m0Var;
    }

    @Override // r7.a
    public final Object invoke() {
        b8.e rVar;
        b8.e qVar;
        GenericDeclaration declaredConstructor;
        b8.u uVarB;
        b8.u rVar2;
        int i10 = this.f208i;
        m0 m0Var = this.f209l;
        Object objP = null;
        switch (i10) {
            case 0:
                f9.b bVar = c2.f175a;
                Object obj = m0Var.f221s;
                g8.w wVarX = m0Var.x();
                j0 j0Var = m0Var.f219q;
                f4.f fVarC = c2.c(wVarX);
                if (fVarC instanceof l) {
                    if (m0Var.z()) {
                        Class clsC = j0Var.c();
                        List parameters = m0Var.getParameters();
                        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(parameters, 10));
                        Iterator it = parameters.iterator();
                        while (it.hasNext()) {
                            arrayList.add(((kotlin.reflect.l) it.next()).getName());
                        }
                        return new b8.a(clsC, arrayList, 2);
                    }
                    String str = ((l) fVarC).f211l.f15072c;
                    Class clsC2 = j0Var.c();
                    try {
                        Class[] clsArr = (Class[]) j0Var.z(str).toArray(new Class[0]);
                        objP = clsC2.getDeclaredConstructor((Class[]) Arrays.copyOf(clsArr, clsArr.length));
                        break;
                    } catch (NoSuchMethodException unused) {
                    }
                } else if (fVarC instanceof m) {
                    g8.w wVarX2 = m0Var.x();
                    if (i9.h.c(wVarX2.d()) && (wVarX2 instanceof g8.j) && ((g8.j) wVarX2).Z()) {
                        return new b8.z(m0Var.x(), j0Var, ((m) fVarC).f216l.f15072c, m0Var.x().e());
                    }
                    e9.e eVar = ((m) fVarC).f216l;
                    objP = j0Var.p(eVar.f15071b, eVar.f15072c);
                } else if (fVarC instanceof k) {
                    objP = ((k) fVarC).f207l;
                } else {
                    if (!(fVarC instanceof j)) {
                        if (!(fVarC instanceof i)) {
                            throw new NoWhenBranchMatchedException();
                        }
                        List list = ((i) fVarC).f201l;
                        Class clsC3 = j0Var.c();
                        ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(list, 10));
                        Iterator it2 = list.iterator();
                        while (it2.hasNext()) {
                            arrayList2.add(((Method) it2.next()).getName());
                        }
                        return new b8.a(clsC3, arrayList2, 2, 1, list);
                    }
                    objP = ((j) fVarC).f203l;
                }
                if (objP instanceof Constructor) {
                    qVar = m0.B(m0Var, (Constructor) objP, m0Var.x(), false);
                } else {
                    if (!(objP instanceof Method)) {
                        throw new x1("Could not compute caller for function: " + m0Var.x() + " (member = " + objP + ')');
                    }
                    Method method = (Method) objP;
                    if (!Modifier.isStatic(method.getModifiers())) {
                        rVar = m0Var.A() ? new b8.p(method, m2.f0.g(obj, m0Var.x())) : new b8.t(method, false, 6);
                    } else if (m0Var.x().getAnnotations().j(e2.f188a) != null) {
                        qVar = m0Var.A() ? new b8.q(method) : new b8.t(method);
                    } else {
                        rVar = m0Var.A() ? new b8.r(method, m2.f0.g(obj, m0Var.x())) : new b8.t(method, 2);
                    }
                    qVar = rVar;
                }
                return m2.f0.k(qVar, m0Var.x(), false);
            default:
                f9.b bVar2 = c2.f175a;
                g8.w wVarX3 = m0Var.x();
                j0 j0Var2 = m0Var.f219q;
                f4.f fVarC2 = c2.c(wVarX3);
                if (!(fVarC2 instanceof m)) {
                    if (fVarC2 instanceof l) {
                        if (m0Var.z()) {
                            Class clsC4 = j0Var2.c();
                            List parameters2 = m0Var.getParameters();
                            ArrayList arrayList3 = new ArrayList(kotlin.collections.s.U(parameters2, 10));
                            Iterator it3 = parameters2.iterator();
                            while (it3.hasNext()) {
                                arrayList3.add(((kotlin.reflect.l) it3.next()).getName());
                            }
                            return new b8.a(clsC4, arrayList3, 1);
                        }
                        String str2 = ((l) fVarC2).f211l.f15072c;
                        Class clsC5 = j0Var2.c();
                        ArrayList arrayList4 = new ArrayList();
                        j0Var2.m(str2, arrayList4, true);
                        try {
                            Class[] clsArr2 = (Class[]) arrayList4.toArray(new Class[0]);
                            declaredConstructor = clsC5.getDeclaredConstructor((Class[]) Arrays.copyOf(clsArr2, clsArr2.length));
                        } catch (NoSuchMethodException unused2) {
                            declaredConstructor = null;
                        }
                    } else if (fVarC2 instanceof i) {
                        List list2 = ((i) fVarC2).f201l;
                        Class clsC6 = j0Var2.c();
                        ArrayList arrayList5 = new ArrayList(kotlin.collections.s.U(list2, 10));
                        Iterator it4 = list2.iterator();
                        while (it4.hasNext()) {
                            arrayList5.add(((Method) it4.next()).getName());
                        }
                        return new b8.a(clsC6, arrayList5, 1, 1, list2);
                    }
                    declaredConstructor = null;
                    break;
                } else {
                    g8.w wVarX4 = m0Var.x();
                    if (i9.h.c(wVarX4.d()) && (wVarX4 instanceof g8.j) && ((g8.j) wVarX4).Z()) {
                        throw new x1(m0Var.x().d() + " cannot have default arguments");
                    }
                    e9.e eVar2 = ((m) fVarC2).f216l;
                    String str3 = eVar2.f15071b;
                    String str4 = eVar2.f15072c;
                    boolean zIsStatic = Modifier.isStatic(m0Var.u().b().getModifiers());
                    boolean z = !zIsStatic;
                    j0Var2.getClass();
                    if (str3.equals("<init>")) {
                        declaredConstructor = null;
                    } else {
                        ArrayList arrayList6 = new ArrayList();
                        if (!zIsStatic) {
                            arrayList6.add(j0Var2.c());
                        }
                        j0Var2.m(str4, arrayList6, false);
                        declaredConstructor = j0.A(j0Var2.x(), str3.concat("$default"), (Class[]) arrayList6.toArray(new Class[0]), j0Var2.B(kotlin.text.o.e0(str4, ')', 0, false, 6) + 1, str4.length(), str4), z);
                    }
                }
                if (declaredConstructor instanceof Constructor) {
                    uVarB = m0.B(m0Var, (Constructor) declaredConstructor, m0Var.x(), true);
                } else if (declaredConstructor instanceof Method) {
                    if (m0Var.x().getAnnotations().j(e2.f188a) == null || ((g8.f) m0Var.x().d()).Y()) {
                        Method method2 = (Method) declaredConstructor;
                        rVar2 = m0Var.A() ? new b8.r(method2, m2.f0.g(m0Var.f221s, m0Var.x())) : new b8.t(method2, 2);
                    } else {
                        Method method3 = (Method) declaredConstructor;
                        rVar2 = m0Var.A() ? new b8.q(method3) : new b8.t(method3);
                    }
                    uVarB = rVar2;
                } else {
                    uVarB = null;
                }
                if (uVarB != null) {
                    return m2.f0.k(uVarB, m0Var.x(), true);
                }
                return null;
        }
    }
}
