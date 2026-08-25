package a8;

import androidx.media3.session.MediaUtils;
import j$.util.concurrent.ConcurrentHashMap;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class y1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ConcurrentHashMap f312a = new ConcurrentHashMap();

    public static final l8.h a(Class cls) {
        i8.b bVarK;
        i8.d dVarK;
        List list = m8.c.f20266a;
        ClassLoader classLoader = cls.getClassLoader();
        if (classLoader == null) {
            classLoader = ClassLoader.getSystemClassLoader();
        }
        f2 f2Var = new f2(classLoader);
        ConcurrentHashMap concurrentHashMap = f312a;
        WeakReference weakReference = (WeakReference) concurrentHashMap.get(f2Var);
        if (weakReference != null) {
            l8.h hVar = (l8.h) weakReference.get();
            if (hVar != null) {
                return hVar;
            }
            concurrentHashMap.remove(f2Var, weakReference);
        }
        s9.k kVar = s9.k.f21728f;
        l8.c cVar = new l8.c(classLoader);
        l8.c cVar2 = new l8.c(x6.t0.class.getClassLoader());
        l8.c cVar3 = new l8.c(classLoader);
        l8.g gVar = l8.g.f20066b;
        l8.g gVar2 = l8.g.f20067c;
        u9.p pVar = new u9.p("DeserializationComponentsForJava.ModuleData");
        f8.h hVar2 = new f8.h(pVar);
        j8.d0 d0Var = new j8.d0(f9.f.l("<" + ("runtime module for " + classLoader) + '>'), pVar, hVar2, 56);
        u9.u uVar = pVar.f22090a;
        uVar.lock();
        try {
            if (hVar2.f14706a != null) {
                throw new AssertionError("Built-ins module is already set: " + hVar2.f14706a + " (attempting to reset to " + d0Var + ")");
            }
            hVar2.f14706a = d0Var;
            uVar.unlock();
            hVar2.f15320f = new d8.n(d0Var, 1);
            y8.p pVar2 = new y8.p();
            kb.d dVar = new kb.d(14);
            androidx.emoji2.text.e0 e0Var = new androidx.emoji2.text.e0(pVar, d0Var);
            y8.q qVar = y8.q.f22970c;
            io.sentry.util.l lVar = new io.sentry.util.l(pVar);
            d8.o oVar = new d8.o(d0Var, e0Var);
            p8.x xVar = p8.x.f21189c;
            p8.c cVar4 = new p8.c(xVar);
            s8.b bVar = s8.b.f21667a;
            x8.m mVar = new x8.m();
            w9.k.f22525b.getClass();
            w9.l lVar2 = w9.j.f22524b;
            s8.f fVar = new s8.f(new s8.a(pVar, cVar3, cVar, pVar2, q8.m.f21354b, gVar, q8.h.f21348c, lVar, gVar2, dVar, qVar, g8.y0.f15559l, o8.c.f20792a, d0Var, oVar, cVar4, mVar, p8.o.f21172a, bVar, lVar2, xVar, new y8.q()));
            e9.f fVar2 = e9.f.f15073g;
            vb.f fVar3 = new vb.f(cVar, pVar2);
            y8.n nVar = new y8.n(d0Var, e0Var, pVar, cVar);
            nVar.f22962p = fVar2;
            List listSingletonList = Collections.singletonList(v9.l.f22222a);
            d8.k kVar2 = d0Var.f19253n;
            f8.h hVar3 = kVar2 instanceof f8.h ? (f8.h) kVar2 : null;
            y8.q qVar2 = y8.q.f22969b;
            if (hVar3 == null || (bVarK = hVar3.K()) == null) {
                bVarK = i8.a.f16238b;
            }
            if (hVar3 == null || (dVarK = hVar3.K()) == null) {
                dVarK = i8.a.f16240d;
            }
            f2 f2Var2 = f2Var;
            ConcurrentHashMap concurrentHashMap2 = concurrentHashMap;
            s9.j jVar = new s9.j(pVar, d0Var, fVar3, nVar, fVar, gVar, qVar2, kotlin.collections.z.f19728i, e0Var, bVarK, dVarK, e9.h.f15080a, lVar2, new io.sentry.util.l(pVar), listSingletonList, kVar);
            pVar2.f22968a = jVar;
            dVar.f19673l = new kb.d(fVar, 12);
            f8.n nVarK = hVar2.K();
            f8.n nVarK2 = hVar2.K();
            io.sentry.util.l lVar3 = new io.sentry.util.l(pVar);
            f8.p pVar3 = new f8.p(pVar, cVar2, d0Var);
            kb.d dVar2 = new kb.d(pVar3, 15);
            t9.a aVar = t9.a.f21999m;
            pVar3.f15346c = new s9.j(pVar, d0Var, dVar2, new fi.iki.elonen.f(d0Var, e0Var, aVar), pVar3, t7.a.E(new e8.a(pVar, d0Var), new f8.f(pVar, d0Var)), e0Var, nVarK, nVarK2, aVar.f21393a, lVar2, lVar3, MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES);
            d0Var.f19256q = new androidx.work.impl.constraints.j(kotlin.collections.r.o0(new j8.d0[]{d0Var}));
            d0Var.f19257r = new j8.n(t7.a.E(fVar, pVar3), "CompositeProvider@RuntimeModuleData for " + d0Var);
            l8.h hVar4 = new l8.h(jVar, new androidx.appcompat.app.i1(pVar2, cVar));
            while (true) {
                f2 f2Var3 = f2Var2;
                ConcurrentHashMap concurrentHashMap3 = concurrentHashMap2;
                WeakReference weakReference2 = (WeakReference) concurrentHashMap3.putIfAbsent(f2Var3, new WeakReference(hVar4));
                if (weakReference2 == null) {
                    return hVar4;
                }
                l8.h hVar5 = (l8.h) weakReference2.get();
                if (hVar5 != null) {
                    return hVar5;
                }
                concurrentHashMap3.remove(f2Var3, weakReference2);
                f2Var2 = f2Var3;
                concurrentHashMap2 = concurrentHashMap3;
            }
        } finally {
        }
    }
}
