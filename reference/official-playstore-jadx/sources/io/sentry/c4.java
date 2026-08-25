package io.sentry;

import androidx.media3.extractor.text.ttml.TtmlNode;
import j$.util.DesugarCollections;
import j$.util.concurrent.ConcurrentHashMap;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes4.dex */
public final class c4 implements a1 {
    public final io.sentry.protocol.c A;
    public final CopyOnWriteArrayList B;
    public androidx.appcompat.app.i1 C;
    public io.sentry.protocol.v D;
    public f1 E;
    public final Map F;
    public final io.sentry.featureflags.b G;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public m1 f17118i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final WeakReference f17119l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public io.sentry.protocol.i0 f17120m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f17121n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public io.sentry.protocol.p f17122o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final ArrayList f17123p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public volatile Object f17124q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final ConcurrentHashMap f17125r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final ConcurrentHashMap f17126s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final ConcurrentHashMap f17127t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final CopyOnWriteArrayList f17128u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public volatile y6 f17129v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public volatile j7 f17130w;
    public final io.sentry.util.b x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final io.sentry.util.b f17131y;
    public final io.sentry.util.b z;

    public c4(y6 y6Var) {
        io.sentry.featureflags.b bVar;
        this.f17119l = new WeakReference(null);
        this.f17123p = new ArrayList();
        this.f17125r = new ConcurrentHashMap();
        this.f17126s = new ConcurrentHashMap();
        this.f17127t = new ConcurrentHashMap();
        this.f17128u = new CopyOnWriteArrayList();
        this.x = new io.sentry.util.b();
        this.f17131y = new io.sentry.util.b();
        this.z = new io.sentry.util.b();
        this.A = new io.sentry.protocol.c();
        this.B = new CopyOnWriteArrayList();
        this.D = io.sentry.protocol.v.f17707l;
        this.E = b3.f17099a;
        this.F = DesugarCollections.synchronizedMap(new WeakHashMap());
        ac.b.V(y6Var, "SentryOptions is required.");
        this.f17129v = y6Var;
        this.f17124q = a(this.f17129v.getMaxBreadcrumbs());
        if (y6Var.getMaxFeatureFlags() > 0) {
            io.sentry.featureflags.a aVar = new io.sentry.featureflags.a();
            new io.sentry.util.b();
            aVar.f17286i = new CopyOnWriteArrayList();
            bVar = aVar;
        } else {
            bVar = io.sentry.featureflags.c.f17287i;
        }
        this.G = bVar;
        this.C = new androidx.appcompat.app.i1(18);
    }

    public static Queue a(int i10) {
        return i10 > 0 ? new r7(new h(i10)) : new z();
    }

    @Override // io.sentry.a1
    public final io.sentry.protocol.i0 A() {
        return this.f17120m;
    }

    @Override // io.sentry.a1
    public final String B() {
        m1 m1Var = this.f17118i;
        if (m1Var != null) {
            return m1Var.getName();
        }
        return null;
    }

    @Override // io.sentry.a1
    public final void C() {
        io.sentry.util.a aVarA = this.f17131y.a();
        try {
            this.f17118i = null;
            aVarA.close();
            for (b1 b1Var : this.f17129v.getScopeObservers()) {
                b1Var.t(null);
                b1Var.n(null, this);
            }
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // io.sentry.a1
    public final io.sentry.featureflags.b D() {
        return this.G;
    }

    @Override // io.sentry.a1
    public final j7 E() {
        return this.f17130w;
    }

    @Override // io.sentry.a1
    public final androidx.appcompat.app.i1 F() {
        return this.C;
    }

    @Override // io.sentry.a1
    public final void G(String str) {
        this.f17121n = str;
        io.sentry.protocol.c cVar = this.A;
        io.sentry.protocol.a aVarD = cVar.d();
        if (aVarD == null) {
            aVarD = new io.sentry.protocol.a();
            cVar.m(aVarD);
        }
        if (str == null) {
            aVarD.f17537s = null;
        } else {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(str);
            aVarD.f17537s = arrayList;
        }
        Iterator<b1> it = this.f17129v.getScopeObservers().iterator();
        while (it.hasNext()) {
            it.next().o(cVar);
        }
    }

    @Override // io.sentry.a1
    public final void H(androidx.appcompat.app.i1 i1Var) {
        this.C = i1Var;
        l7 l7Var = new l7((io.sentry.protocol.v) i1Var.f1059l, (o7) i1Var.f1060m, "default", null);
        l7Var.f17443s = TtmlNode.TEXT_EMPHASIS_AUTO;
        Iterator<b1> it = this.f17129v.getScopeObservers().iterator();
        while (it.hasNext()) {
            it.next().n(l7Var, this);
        }
    }

    @Override // io.sentry.a1
    public final f1 I() {
        return this.E;
    }

    @Override // io.sentry.a1
    public final List J() {
        return new CopyOnWriteArrayList(this.B);
    }

    @Override // io.sentry.a1
    public final void K(j5 j5Var) {
        if (!this.f17129v.isTracingEnabled() || j5Var.a() == null) {
            return;
        }
        Map map = this.F;
        Throwable thA = j5Var.a();
        ac.b.V(thA, "throwable cannot be null");
        while (thA.getCause() != null && thA.getCause() != thA) {
            thA = thA.getCause();
        }
    }

    @Override // io.sentry.a1
    public final androidx.appcompat.app.i1 M(z3 z3Var) {
        io.sentry.util.a aVarA = this.z.a();
        try {
            z3Var.c(this.C);
            androidx.appcompat.app.i1 i1Var = new androidx.appcompat.app.i1(this.C);
            aVarA.close();
            return i1Var;
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // io.sentry.a1
    public final void N(b4 b4Var) {
        io.sentry.util.a aVarA = this.f17131y.a();
        try {
            b4Var.b(this.f17118i);
            aVarA.close();
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // io.sentry.a1
    public final void O(io.sentry.protocol.v vVar) {
    }

    @Override // io.sentry.a1
    public final List P() {
        return kotlin.reflect.b0.K(this.f17128u);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, java.util.Collection] */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Object, java.util.Collection] */
    @Override // io.sentry.a1
    public final void clear() {
        this.f17120m = null;
        this.f17122o = null;
        this.f17121n = null;
        this.f17123p.clear();
        this.f17124q.clear();
        Iterator<b1> it = this.f17129v.getScopeObservers().iterator();
        while (it.hasNext()) {
            it.next().l(this.f17124q);
        }
        this.f17125r.clear();
        this.f17126s.clear();
        this.f17127t.clear();
        this.f17128u.clear();
        C();
        this.B.clear();
        Iterator<b1> it2 = this.f17129v.getScopeObservers().iterator();
        while (it2.hasNext()) {
            it2.next().r();
        }
    }

    @Override // io.sentry.a1
    public final a1 clone() {
        return new c4(this);
    }

    @Override // io.sentry.a1
    public final void d(io.sentry.protocol.i0 i0Var) {
        this.f17120m = i0Var;
        Iterator<b1> it = this.f17129v.getScopeObservers().iterator();
        while (it.hasNext()) {
            it.next().d(i0Var);
        }
    }

    @Override // io.sentry.a1
    public final void e(String str, String str2) {
        this.f17125r.put(str, str2);
        for (b1 b1Var : this.f17129v.getScopeObservers()) {
            b1Var.e(str, str2);
            b1Var.s(this.f17125r);
        }
    }

    @Override // io.sentry.a1
    public final void g(String str, String str2) {
        if (str2 == null) {
            this.f17127t.remove(str);
            for (b1 b1Var : this.f17129v.getScopeObservers()) {
                b1Var.m(str);
                b1Var.q(this.f17127t);
            }
            return;
        }
        this.f17127t.put(str, str2);
        for (b1 b1Var2 : this.f17129v.getScopeObservers()) {
            b1Var2.g(str, str2);
            b1Var2.q(this.f17127t);
        }
    }

    @Override // io.sentry.a1
    public final Map getExtras() {
        return this.f17127t;
    }

    @Override // io.sentry.a1
    public final w5 getLevel() {
        return null;
    }

    @Override // io.sentry.a1
    public final y6 getOptions() {
        return this.f17129v;
    }

    @Override // io.sentry.a1
    public final io.sentry.protocol.p getRequest() {
        return this.f17122o;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.Object, java.util.Collection] */
    /* JADX WARN: Type inference failed for: r5v5, types: [java.lang.Object, java.util.Queue] */
    @Override // io.sentry.a1
    public final void h(f fVar, j0 j0Var) {
        if (fVar == null || (this.f17124q instanceof z)) {
            return;
        }
        if (j0Var == null) {
            j0Var = new j0();
        }
        h6 beforeBreadcrumb = this.f17129v.getBeforeBreadcrumb();
        if (beforeBreadcrumb != null) {
            try {
                fVar = beforeBreadcrumb.s(fVar, j0Var);
            } catch (Throwable th) {
                this.f17129v.getLogger().l(w5.ERROR, "The BeforeBreadcrumbCallback callback threw an exception. Exception details will be added to the breadcrumb.", th);
                if (th.getMessage() != null) {
                    fVar.f(th.getMessage(), "sentry:message");
                }
            }
        }
        if (fVar == null) {
            this.f17129v.getLogger().q(w5.INFO, "Breadcrumb was dropped by beforeBreadcrumb", new Object[0]);
            return;
        }
        this.f17124q.add(fVar);
        for (b1 b1Var : this.f17129v.getScopeObservers()) {
            b1Var.k(fVar);
            b1Var.l(this.f17124q);
        }
    }

    @Override // io.sentry.a1
    public final m1 j() {
        return this.f17118i;
    }

    @Override // io.sentry.a1
    public final j7 m() {
        io.sentry.util.a aVarA = this.x.a();
        try {
            j7 j7Var = null;
            if (this.f17130w != null) {
                j7 j7Var2 = this.f17130w;
                j7Var2.getClass();
                j7Var2.b(k2.c.m());
                this.f17129v.getContinuousProfiler().c();
                j7 j7VarClone = this.f17130w.clone();
                this.f17130w = null;
                j7Var = j7VarClone;
            }
            aVarA.close();
            return j7Var;
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // io.sentry.a1
    public final io.sentry.protocol.h n() {
        return this.G.n();
    }

    @Override // io.sentry.a1
    public final io.sentry.protocol.v o() {
        return this.D;
    }

    @Override // io.sentry.a1
    public final void p(io.sentry.protocol.v vVar) {
        this.D = vVar;
        Iterator<b1> it = this.f17129v.getScopeObservers().iterator();
        while (it.hasNext()) {
            it.next().p(vVar);
        }
    }

    @Override // io.sentry.a1
    public final fi.iki.elonen.f q() {
        io.sentry.util.a aVarA = this.x.a();
        try {
            if (this.f17130w != null) {
                j7 j7Var = this.f17130w;
                j7Var.getClass();
                j7Var.b(k2.c.m());
                this.f17129v.getContinuousProfiler().c();
            }
            j7 j7Var2 = this.f17130w;
            fi.iki.elonen.f fVar = null;
            if (this.f17129v.getRelease() != null) {
                String distinctId = this.f17129v.getDistinctId();
                io.sentry.protocol.i0 i0Var = this.f17120m;
                this.f17130w = new j7(i7.Ok, k2.c.m(), k2.c.m(), 0, distinctId, qb.d.B(), Boolean.TRUE, null, null, i0Var != null ? i0Var.f17600n : null, null, this.f17129v.getEnvironment(), this.f17129v.getRelease(), null);
                fVar = new fi.iki.elonen.f((Object) this.f17130w.clone(), (Object) (j7Var2 != null ? j7Var2.clone() : null), false);
            } else {
                this.f17129v.getLogger().q(w5.WARNING, "Release is not set on SentryOptions. Session could not be started", new Object[0]);
            }
            aVarA.close();
            return fVar;
        } catch (Throwable th) {
            try {
                aVarA.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }

    @Override // io.sentry.a1
    public final k1 r() {
        k1 k1VarK;
        k1 k1Var = (k1) this.f17119l.get();
        if (k1Var != null) {
            return k1Var;
        }
        m1 m1Var = this.f17118i;
        return (m1Var == null || (k1VarK = m1Var.k()) == null) ? m1Var : k1VarK;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, java.util.Queue] */
    @Override // io.sentry.a1
    public final Queue s() {
        return this.f17124q;
    }

    @Override // io.sentry.a1
    public final j7 t(a4 a4Var) {
        io.sentry.util.a aVarA = this.x.a();
        try {
            a4Var.a(this.f17130w);
            j7 j7VarClone = this.f17130w != null ? this.f17130w.clone() : null;
            aVarA.close();
            return j7VarClone;
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // io.sentry.a1
    public final Map u() {
        return k2.c.w(this.f17125r);
    }

    @Override // io.sentry.a1
    public final List v() {
        return this.f17128u;
    }

    @Override // io.sentry.a1
    public final io.sentry.protocol.c w() {
        return this.A;
    }

    @Override // io.sentry.a1
    public final String x() {
        return this.f17121n;
    }

    @Override // io.sentry.a1
    public final void y(m1 m1Var) {
        io.sentry.util.a aVarA = this.f17131y.a();
        try {
            this.f17118i = m1Var;
            for (b1 b1Var : this.f17129v.getScopeObservers()) {
                if (m1Var != null) {
                    b1Var.t(m1Var.getName());
                    b1Var.n(m1Var.o(), this);
                } else {
                    b1Var.t(null);
                    b1Var.n(null, this);
                }
            }
            aVarA.close();
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // io.sentry.a1
    public final List z() {
        return this.f17123p;
    }

    /* JADX INFO: renamed from: clone, reason: collision with other method in class */
    public final Object m7018clone() {
        return new c4(this);
    }

    /* JADX WARN: Type inference failed for: r0v26, types: [java.lang.Object, java.util.Collection] */
    public c4(c4 c4Var) {
        io.sentry.protocol.i0 i0Var;
        io.sentry.protocol.p pVar;
        this.f17119l = new WeakReference(null);
        this.f17123p = new ArrayList();
        this.f17125r = new ConcurrentHashMap();
        this.f17126s = new ConcurrentHashMap();
        this.f17127t = new ConcurrentHashMap();
        this.f17128u = new CopyOnWriteArrayList();
        this.x = new io.sentry.util.b();
        this.f17131y = new io.sentry.util.b();
        this.z = new io.sentry.util.b();
        this.A = new io.sentry.protocol.c();
        this.B = new CopyOnWriteArrayList();
        this.D = io.sentry.protocol.v.f17707l;
        this.E = b3.f17099a;
        this.F = DesugarCollections.synchronizedMap(new WeakHashMap());
        this.f17118i = c4Var.f17118i;
        this.f17119l = c4Var.f17119l;
        this.f17130w = c4Var.f17130w;
        this.f17129v = c4Var.f17129v;
        this.E = c4Var.E;
        io.sentry.protocol.i0 i0Var2 = c4Var.f17120m;
        if (i0Var2 != null) {
            i0Var = new io.sentry.protocol.i0();
            i0Var.f17597i = i0Var2.f17597i;
            i0Var.f17599m = i0Var2.f17599m;
            i0Var.f17598l = i0Var2.f17598l;
            i0Var.f17600n = i0Var2.f17600n;
            i0Var.f17601o = i0Var2.f17601o;
            i0Var.f17602p = i0Var2.f17602p;
            i0Var.f17603q = k2.c.w(i0Var2.f17603q);
            i0Var.f17604r = k2.c.w(i0Var2.f17604r);
        } else {
            i0Var = null;
        }
        this.f17120m = i0Var;
        this.f17121n = c4Var.f17121n;
        this.D = c4Var.D;
        io.sentry.protocol.p pVar2 = c4Var.f17122o;
        if (pVar2 != null) {
            pVar = new io.sentry.protocol.p();
            pVar.f17660i = pVar2.f17660i;
            pVar.f17664o = pVar2.f17664o;
            pVar.f17661l = pVar2.f17661l;
            pVar.f17662m = pVar2.f17662m;
            pVar.f17665p = k2.c.w(pVar2.f17665p);
            pVar.f17666q = k2.c.w(pVar2.f17666q);
            pVar.f17668s = k2.c.w(pVar2.f17668s);
            pVar.f17671v = k2.c.w(pVar2.f17671v);
            pVar.f17663n = pVar2.f17663n;
            pVar.f17669t = pVar2.f17669t;
            pVar.f17667r = pVar2.f17667r;
            pVar.f17670u = pVar2.f17670u;
        } else {
            pVar = null;
        }
        this.f17122o = pVar;
        this.f17123p = new ArrayList(c4Var.f17123p);
        this.f17128u = new CopyOnWriteArrayList(c4Var.f17128u);
        f[] fVarArr = (f[]) c4Var.f17124q.toArray(new f[0]);
        Queue queueA = a(c4Var.f17129v.getMaxBreadcrumbs());
        for (f fVar : fVarArr) {
            queueA.add(new f(fVar));
        }
        this.f17124q = queueA;
        ConcurrentHashMap concurrentHashMap = c4Var.f17125r;
        ConcurrentHashMap concurrentHashMap2 = new ConcurrentHashMap();
        for (Map.Entry entry : concurrentHashMap.entrySet()) {
            if (entry != null) {
                concurrentHashMap2.put((String) entry.getKey(), (String) entry.getValue());
            }
        }
        this.f17125r = concurrentHashMap2;
        ConcurrentHashMap concurrentHashMap3 = c4Var.f17126s;
        ConcurrentHashMap concurrentHashMap4 = new ConcurrentHashMap();
        for (Map.Entry entry2 : concurrentHashMap3.entrySet()) {
            if (entry2 != null) {
                String str = (String) entry2.getKey();
                if (entry2.getValue() != null) {
                    throw new ClassCastException();
                }
                concurrentHashMap4.put(str, null);
            }
        }
        this.f17126s = concurrentHashMap4;
        ConcurrentHashMap concurrentHashMap5 = c4Var.f17127t;
        ConcurrentHashMap concurrentHashMap6 = new ConcurrentHashMap();
        for (Map.Entry entry3 : concurrentHashMap5.entrySet()) {
            if (entry3 != null) {
                concurrentHashMap6.put((String) entry3.getKey(), entry3.getValue());
            }
        }
        this.f17127t = concurrentHashMap6;
        this.A = new io.sentry.protocol.c(c4Var.A);
        this.B = new CopyOnWriteArrayList(c4Var.B);
        this.G = c4Var.G.m7019clone();
        this.C = new androidx.appcompat.app.i1(c4Var.C);
    }
}
