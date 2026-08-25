package b8;

import a8.e2;
import a8.j0;
import g8.n0;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import m2.f0;
import v9.b0;

/* JADX INFO: loaded from: classes5.dex */
public final class z implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Method f7253a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Method f7254b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList f7255c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ArrayList f7256d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayList f7257e;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v14, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v15, types: [java.util.ArrayList] */
    public z(g8.w wVar, j0 j0Var, String str, List list) {
        ?? SingletonList;
        this.f7253a = j0Var.p("constructor-impl", str);
        this.f7254b = j0Var.p("box-impl", kotlin.text.o.s0(str, "V") + m8.c.b(j0Var.c()));
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
        Iterator it = list.iterator();
        while (true) {
            List listSingletonList = null;
            if (!it.hasNext()) {
                break;
            }
            b0 b0VarB = v9.c.b(((n0) it.next()).getType());
            ArrayList arrayListR = f0.r(b0VarB);
            if (arrayListR == null) {
                Class clsQ = f0.Q(b0VarB);
                if (clsQ != null) {
                    listSingletonList = Collections.singletonList(f0.q(clsQ, wVar));
                }
            } else {
                listSingletonList = arrayListR;
            }
            arrayList.add(listSingletonList);
        }
        this.f7255c = arrayList;
        ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(list, 10));
        int i10 = 0;
        for (Object obj : list) {
            int i11 = i10 + 1;
            if (i10 < 0) {
                t7.a.Q();
                throw null;
            }
            g8.f fVar = (g8.f) ((n0) obj).getType().I0().e();
            List list2 = (List) this.f7255c.get(i10);
            if (list2 != null) {
                SingletonList = new ArrayList(kotlin.collections.s.U(list2, 10));
                Iterator it2 = list2.iterator();
                while (it2.hasNext()) {
                    SingletonList.add(((Method) it2.next()).getReturnType());
                }
            } else {
                SingletonList = Collections.singletonList(e2.i(fVar));
            }
            arrayList2.add(SingletonList);
            i10 = i11;
        }
        this.f7256d = arrayList2;
        this.f7257e = kotlin.collections.s.V(arrayList2);
    }

    @Override // b8.e
    public final List a() {
        return this.f7257e;
    }

    @Override // b8.e
    public final /* bridge */ /* synthetic */ Member b() {
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r6v2, types: [java.lang.Iterable] */
    /* JADX WARN: Type inference failed for: r6v3, types: [java.util.ArrayList] */
    @Override // b8.e
    public final Object call(Object[] objArr) throws IllegalAccessException, InvocationTargetException {
        ?? SingletonList;
        int length = objArr.length;
        ArrayList arrayList = this.f7255c;
        ArrayList<x6.x> arrayList2 = new ArrayList(Math.min(kotlin.collections.s.U(arrayList, 10), length));
        int i10 = 0;
        for (Object obj : arrayList) {
            if (i10 >= length) {
                break;
            }
            arrayList2.add(new x6.x(objArr[i10], obj));
            i10++;
        }
        ArrayList arrayList3 = new ArrayList();
        for (x6.x xVar : arrayList2) {
            Object obj2 = xVar.f22608i;
            List list = (List) xVar.f22609l;
            if (list != null) {
                SingletonList = new ArrayList(kotlin.collections.s.U(list, 10));
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    SingletonList.add(((Method) it.next()).invoke(obj2, null));
                }
            } else {
                SingletonList = Collections.singletonList(obj2);
            }
            kotlin.collections.x.b0(arrayList3, SingletonList);
        }
        Object[] array = arrayList3.toArray(new Object[0]);
        this.f7253a.invoke(null, Arrays.copyOf(array, array.length));
        return this.f7254b.invoke(null, Arrays.copyOf(array, array.length));
    }

    @Override // b8.e
    public final Type getReturnType() {
        return this.f7254b.getReturnType();
    }
}
