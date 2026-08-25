package ea;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes5.dex */
public abstract class o implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final m f15099a = new m();

    public static /* synthetic */ void c(int i10) {
        Object[] objArr = new Object[3];
        switch (i10) {
            case 1:
            case 5:
            case 8:
            case 11:
            case 15:
            case 18:
            case 21:
            case 23:
                objArr[0] = "neighbors";
                break;
            case 2:
            case 12:
            case 16:
            case 19:
            case 24:
                objArr[0] = "visited";
                break;
            case 3:
            case 6:
            case 13:
            case 25:
                objArr[0] = "handler";
                break;
            case 4:
            case 7:
            case 17:
            case 20:
            default:
                objArr[0] = "nodes";
                break;
            case 9:
                objArr[0] = "predicate";
                break;
            case 10:
            case 14:
                objArr[0] = "node";
                break;
            case 22:
                objArr[0] = "current";
                break;
        }
        objArr[1] = "kotlin/reflect/jvm/internal/impl/utils/DFS";
        switch (i10) {
            case 7:
            case 8:
            case 9:
                objArr[2] = "ifAny";
                break;
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                objArr[2] = "dfsFromNode";
                break;
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                objArr[2] = "topologicalOrder";
                break;
            case 22:
            case 23:
            case 24:
            case 25:
                objArr[2] = "doDfs";
                break;
            default:
                objArr[2] = "dfs";
                break;
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public static final void d(AbstractCollection abstractCollection, Object obj) {
        if (obj != null) {
            abstractCollection.add(obj);
        }
    }

    public static final List e(ArrayList arrayList) {
        int size = arrayList.size();
        if (size == 0) {
            return z.f19728i;
        }
        if (size == 1) {
            return Collections.singletonList(x.m0(arrayList));
        }
        arrayList.trimToSize();
        return arrayList;
    }

    public static Object f(Collection collection, b bVar, o oVar) {
        if (collection == null) {
            c(4);
            throw null;
        }
        t5.c cVar = new t5.c(1);
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            g(it.next(), bVar, cVar, oVar);
        }
        return oVar.result();
    }

    public static void g(Object obj, b bVar, t5.c cVar, o oVar) {
        if (obj == null) {
            c(22);
            throw null;
        }
        if (cVar.f21894a.add(obj) && oVar.b(obj)) {
            Iterator it = bVar.r(obj).iterator();
            while (it.hasNext()) {
                g(it.next(), bVar, cVar, oVar);
            }
            oVar.a(obj);
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [boolean[], java.io.Serializable] */
    public static Boolean h(List list, b bVar, r7.l lVar) {
        if (list != null) {
            return (Boolean) f(list, bVar, new a(lVar, new boolean[1], 0));
        }
        c(7);
        throw null;
    }

    public static final boolean i(Throwable th) {
        Class<?> superclass = th.getClass();
        while (!p.a(superclass.getCanonicalName(), "com.intellij.openapi.progress.ProcessCanceledException")) {
            superclass = superclass.getSuperclass();
            if (superclass == null) {
                return false;
            }
        }
        return true;
    }

    public static void j(Object obj) throws Throwable {
        if (obj instanceof n) {
            throw ((n) obj).f15098a;
        }
    }

    @Override // ea.c
    public void a(Object obj) {
    }
}
