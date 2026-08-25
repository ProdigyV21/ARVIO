package oa;

import kotlin.jvm.internal.q0;
import kotlin.reflect.b0;
import pa.c0;
import x6.t0;

/* JADX INFO: loaded from: classes5.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d7.d[] f20806a = new d7.d[0];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c2.a f20807b = new c2.a("NULL", 5);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c2.a f20808c = new c2.a("UNINITIALIZED", 5);

    public static final Object a(d7.d dVar, na.k kVar, r7.a aVar, r7.q qVar, na.j[] jVarArr) {
        m mVar = new m(null, kVar, aVar, qVar, jVarArr);
        o oVar = new o(dVar.getContext(), dVar);
        Object objF = b0.F(oVar, oVar, mVar);
        return objF == e7.a.f15033i ? objF : t0.f22605a;
    }

    public static final Object b(d7.j jVar, Object obj, Object obj2, r7.p pVar, d7.d dVar) {
        Object objB = c0.b(jVar, obj2);
        try {
            y yVar = new y(jVar, dVar);
            q0.e(2, pVar);
            return pVar.invoke(obj, yVar);
        } finally {
            c0.a(jVar, objB);
        }
    }
}
