package a8;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import kotlin.reflect.full.IllegalCallableAccessException;

/* JADX INFO: loaded from: classes5.dex */
public abstract class u implements kotlin.reflect.c, w1 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final z1 f272i = m2.f0.B(null, new r(this, 1));

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final z1 f273l = m2.f0.B(null, new r(this, 2));

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final z1 f274m = m2.f0.B(null, new r(this, 4));

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final z1 f275n = m2.f0.B(null, new r(this, 5));

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final z1 f276o = m2.f0.B(null, new r(this, 0));

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Object f277p = f4.f.o(2, new r(this, 6));

    public static Object t(kotlin.reflect.q qVar) {
        Class clsC = ((kotlin.jvm.internal.f) t7.a.v(qVar)).c();
        if (clsC.isArray()) {
            return Array.newInstance(clsC.getComponentType(), 0);
        }
        throw new x1("Cannot instantiate the default empty array of type " + clsC.getSimpleName() + ", because it is not an array type");
    }

    public abstract boolean A();

    @Override // kotlin.reflect.c
    public final Object call(Object... objArr) throws IllegalCallableAccessException {
        try {
            return u().call(objArr);
        } catch (IllegalAccessException e5) {
            throw new IllegalCallableAccessException(e5);
        }
    }

    /* JADX WARN: Type inference failed for: r6v1, types: [java.lang.Object, x6.s] */
    @Override // kotlin.reflect.c
    public final Object callBy(Map map) throws IllegalCallableAccessException {
        Object objT;
        boolean z = false;
        if (z()) {
            List<kotlin.reflect.l> parameters = getParameters();
            ArrayList arrayList = new ArrayList(kotlin.collections.s.U(parameters, 10));
            for (kotlin.reflect.l lVar : parameters) {
                if (map.containsKey(lVar)) {
                    objT = map.get(lVar);
                    if (objT == null) {
                        throw new IllegalArgumentException("Annotation argument value cannot be null (" + lVar + ')');
                    }
                } else if (lVar.s()) {
                    objT = null;
                } else {
                    if (!lVar.f()) {
                        throw new IllegalArgumentException("No argument provided for a required parameter: " + lVar);
                    }
                    objT = t(lVar.getType());
                }
                arrayList.add(objT);
            }
            b8.e eVarW = w();
            if (eVarW != null) {
                try {
                    return eVarW.call(arrayList.toArray(new Object[0]));
                } catch (IllegalAccessException e5) {
                    throw new IllegalCallableAccessException(e5);
                }
            }
            throw new x1("This callable does not support a default call: " + x());
        }
        List<kotlin.reflect.l> parameters2 = getParameters();
        if (parameters2.isEmpty()) {
            try {
                return u().call(isSuspend() ? new d7.d[]{null} : new d7.d[0]);
            } catch (IllegalAccessException e6) {
                throw new IllegalCallableAccessException(e6);
            }
        }
        int size = (isSuspend() ? 1 : 0) + parameters2.size();
        Object[] objArr = (Object[]) ((Object[]) this.f276o.invoke()).clone();
        if (isSuspend()) {
            objArr[parameters2.size()] = null;
        }
        boolean zBooleanValue = ((Boolean) this.f277p.getValue()).booleanValue();
        int i10 = 0;
        for (kotlin.reflect.l lVar2 : parameters2) {
            int iY = zBooleanValue ? y(lVar2) : 1;
            if (map.containsKey(lVar2)) {
                objArr[lVar2.getIndex()] = map.get(lVar2);
            } else if (lVar2.s()) {
                if (zBooleanValue) {
                    int i11 = i10 + iY;
                    for (int i12 = i10; i12 < i11; i12++) {
                        int i13 = (i12 / 32) + size;
                        objArr[i13] = Integer.valueOf(((Integer) objArr[i13]).intValue() | (1 << (i12 % 32)));
                    }
                } else {
                    int i14 = (i10 / 32) + size;
                    objArr[i14] = Integer.valueOf(((Integer) objArr[i14]).intValue() | (1 << (i10 % 32)));
                }
                z = true;
            } else if (!lVar2.f()) {
                throw new IllegalArgumentException("No argument provided for a required parameter: " + lVar2);
            }
            if (lVar2.getKind() == 4) {
                i10 += iY;
            }
        }
        if (!z) {
            try {
                return u().call(Arrays.copyOf(objArr, size));
            } catch (IllegalAccessException e10) {
                throw new IllegalCallableAccessException(e10);
            }
        }
        b8.e eVarW2 = w();
        if (eVarW2 != null) {
            try {
                return eVarW2.call(objArr);
            } catch (IllegalAccessException e11) {
                throw new IllegalCallableAccessException(e11);
            }
        }
        throw new x1("This callable does not support a default call: " + x());
    }

    @Override // kotlin.reflect.b
    public final List getAnnotations() {
        return (List) this.f272i.invoke();
    }

    @Override // kotlin.reflect.c
    public final List getParameters() {
        return (List) this.f273l.invoke();
    }

    @Override // kotlin.reflect.c
    public final kotlin.reflect.q getReturnType() {
        return (kotlin.reflect.q) this.f274m.invoke();
    }

    @Override // kotlin.reflect.c
    public final List getTypeParameters() {
        return (List) this.f275n.invoke();
    }

    @Override // kotlin.reflect.c
    public final kotlin.reflect.u getVisibility() {
        g8.p visibility = x().getVisibility();
        f9.c cVar = e2.f188a;
        if (visibility.equals(g8.q.f15528e)) {
            return kotlin.reflect.u.f19913i;
        }
        if (visibility.equals(g8.q.f15526c)) {
            return kotlin.reflect.u.f19914l;
        }
        if (visibility.equals(g8.q.f15527d)) {
            return kotlin.reflect.u.f19915m;
        }
        if (visibility.equals(g8.q.f15524a) ? true : visibility.equals(g8.q.f15525b)) {
            return kotlin.reflect.u.f19916n;
        }
        return null;
    }

    @Override // kotlin.reflect.c
    public final boolean isAbstract() {
        return x().o() == 4;
    }

    @Override // kotlin.reflect.c
    public final boolean isFinal() {
        return x().o() == 1;
    }

    @Override // kotlin.reflect.c
    public final boolean isOpen() {
        return x().o() == 3;
    }

    public abstract b8.e u();

    public abstract j0 v();

    public abstract b8.e w();

    public abstract g8.c x();

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, x6.s] */
    public final int y(kotlin.reflect.l lVar) {
        if (!((Boolean) this.f277p.getValue()).booleanValue()) {
            throw new IllegalArgumentException("Check if parametersNeedMFVCFlattening is true before");
        }
        u1 type = lVar.getType();
        f9.c cVar = e2.f188a;
        v9.w wVar = type.f282i;
        if (wVar == null || !i9.h.f(wVar)) {
            return 1;
        }
        return m2.f0.r(v9.c.b(lVar.getType().f282i)).size();
    }

    public final boolean z() {
        return kotlin.jvm.internal.p.a(getName(), "<init>") && v().c().isAnnotation();
    }
}
