package cb;

import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class q implements m1, a1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final kotlin.jvm.internal.r f7545a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s f7546b = new s();

    /* JADX WARN: Multi-variable type inference failed */
    public q(r7.l lVar) {
        this.f7545a = (kotlin.jvm.internal.r) lVar;
    }

    /* JADX WARN: Type inference failed for: r2v6, types: [kotlin.jvm.internal.r, r7.p] */
    @Override // cb.a1
    public Object a(kotlin.reflect.d dVar, ArrayList arrayList) {
        Object c0Var;
        u0 u0Var = (u0) this.f7546b.get(((kotlin.jvm.internal.f) dVar).c());
        Object objA = u0Var.reference.get();
        if (objA == null) {
            objA = u0Var.a(new r(0));
        }
        z0 z0Var = (z0) objA;
        ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(new m0((kotlin.reflect.q) it.next()));
        }
        ConcurrentHashMap concurrentHashMap = z0Var.f7598a;
        Object obj = concurrentHashMap.get(arrayList2);
        if (obj == null) {
            try {
                c0Var = (ya.h) this.f7545a.invoke(dVar, arrayList);
            } catch (Throwable th) {
                c0Var = new x6.c0(th);
            }
            x6.d0 d0Var = new x6.d0(c0Var);
            Object objPutIfAbsent = concurrentHashMap.putIfAbsent(arrayList2, d0Var);
            obj = objPutIfAbsent == null ? d0Var : objPutIfAbsent;
        }
        return ((x6.d0) obj).f22580i;
    }

    @Override // cb.m1
    public ya.h b(kotlin.reflect.d dVar) {
        u0 u0Var = (u0) this.f7546b.get(((kotlin.jvm.internal.f) dVar).c());
        Object objA = u0Var.reference.get();
        if (objA == null) {
            objA = u0Var.a(new a8.l0(this, dVar, 7));
        }
        return ((k) objA).f7524a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public q(r7.p pVar) {
        this.f7545a = (kotlin.jvm.internal.r) pVar;
    }
}
