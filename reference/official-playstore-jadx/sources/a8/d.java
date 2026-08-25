package a8;

import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class d implements cb.m1, cb.a1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final kotlin.jvm.internal.r f176a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ConcurrentHashMap f177b;

    /* JADX WARN: Multi-variable type inference failed */
    public d(r7.l lVar, int i10) {
        switch (i10) {
            case 1:
                this.f176a = (kotlin.jvm.internal.r) lVar;
                this.f177b = new ConcurrentHashMap();
                break;
            default:
                this.f176a = (kotlin.jvm.internal.r) lVar;
                this.f177b = new ConcurrentHashMap();
                break;
        }
    }

    /* JADX WARN: Type inference failed for: r2v6, types: [kotlin.jvm.internal.r, r7.p] */
    @Override // cb.a1
    public Object a(kotlin.reflect.d dVar, ArrayList arrayList) {
        Object c0Var;
        Object objPutIfAbsent;
        Class clsC = ((kotlin.jvm.internal.f) dVar).c();
        ConcurrentHashMap concurrentHashMap = this.f177b;
        Object z0Var = concurrentHashMap.get(clsC);
        if (z0Var == null && (objPutIfAbsent = concurrentHashMap.putIfAbsent(clsC, (z0Var = new cb.z0()))) != null) {
            z0Var = objPutIfAbsent;
        }
        cb.z0 z0Var2 = (cb.z0) z0Var;
        ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(new cb.m0((kotlin.reflect.q) it.next()));
        }
        ConcurrentHashMap concurrentHashMap2 = z0Var2.f7598a;
        Object obj = concurrentHashMap2.get(arrayList2);
        if (obj == null) {
            try {
                c0Var = (ya.h) this.f176a.invoke(dVar, arrayList);
            } catch (Throwable th) {
                c0Var = new x6.c0(th);
            }
            x6.d0 d0Var = new x6.d0(c0Var);
            Object objPutIfAbsent2 = concurrentHashMap2.putIfAbsent(arrayList2, d0Var);
            obj = objPutIfAbsent2 == null ? d0Var : objPutIfAbsent2;
        }
        return ((x6.d0) obj).f22580i;
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [kotlin.jvm.internal.r, r7.l] */
    @Override // cb.m1
    public ya.h b(kotlin.reflect.d dVar) {
        Object objPutIfAbsent;
        Class clsC = ((kotlin.jvm.internal.f) dVar).c();
        ConcurrentHashMap concurrentHashMap = this.f177b;
        Object kVar = concurrentHashMap.get(clsC);
        if (kVar == null && (objPutIfAbsent = concurrentHashMap.putIfAbsent(clsC, (kVar = new cb.k((ya.h) this.f176a.invoke(dVar))))) != null) {
            kVar = objPutIfAbsent;
        }
        return ((cb.k) kVar).f7524a;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.jvm.internal.r, r7.l] */
    public Object c(Class cls) {
        ConcurrentHashMap concurrentHashMap = this.f177b;
        Object obj = concurrentHashMap.get(cls);
        if (obj != null) {
            return obj;
        }
        Object objInvoke = this.f176a.invoke(cls);
        Object objPutIfAbsent = concurrentHashMap.putIfAbsent(cls, objInvoke);
        return objPutIfAbsent == null ? objInvoke : objPutIfAbsent;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public d(r7.p pVar) {
        this.f176a = (kotlin.jvm.internal.r) pVar;
        this.f177b = new ConcurrentHashMap();
    }
}
