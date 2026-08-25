package io.sentry;

import java.util.ListIterator;
import java.util.Timer;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes4.dex */
public final class h7 implements m1 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final k7 f17324b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final i4 f17326d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f17327e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public volatile f7 f17329g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public volatile f7 f17330h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public volatile Timer f17331i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final io.sentry.util.b f17332j;
    public final io.sentry.util.b k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final AtomicBoolean f17333l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final AtomicBoolean f17334m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final io.sentry.protocol.h0 f17335n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final r1 f17336o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final io.sentry.protocol.c f17337p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final l f17338q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final w7 f17339r;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final io.sentry.protocol.v f17323a = new io.sentry.protocol.v();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final CopyOnWriteArrayList f17325c = new CopyOnWriteArrayList();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public g7 f17328f = g7.f17305c;

    public h7(u7 u7Var, i4 i4Var, w7 w7Var, l lVar) {
        this.f17331i = null;
        io.sentry.util.b bVar = new io.sentry.util.b();
        this.f17332j = bVar;
        this.k = new io.sentry.util.b();
        this.f17333l = new AtomicBoolean(false);
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.f17334m = atomicBoolean;
        io.sentry.protocol.c cVar = new io.sentry.protocol.c();
        this.f17337p = cVar;
        k7 k7Var = new k7(u7Var, this, i4Var, w7Var);
        this.f17324b = k7Var;
        this.f17327e = u7Var.z;
        this.f17336o = u7Var.f17446v;
        this.f17326d = i4Var;
        Boolean bool = Boolean.TRUE;
        lVar = bool.equals(k7Var.s()) ? lVar : null;
        this.f17338q = lVar;
        this.f17335n = u7Var.A;
        this.f17339r = w7Var;
        w(k7Var);
        io.sentry.protocol.v vVarV = v();
        if (!vVarV.equals(io.sentry.protocol.v.f17707l) && bool.equals(k7Var.s())) {
            cVar.k("profile", new r3(vVarV));
        }
        if (lVar != null) {
            lVar.d(this);
        }
        if (w7Var.f18042g == null && w7Var.f18043h == null) {
            return;
        }
        boolean z = true;
        this.f17331i = new Timer(true);
        Long l10 = w7Var.f18043h;
        if (l10 != null) {
            io.sentry.util.a aVarA = bVar.a();
            try {
                if (this.f17331i != null) {
                    s();
                    atomicBoolean.set(true);
                    this.f17330h = new f7(this, 1);
                    try {
                        this.f17331i.schedule(this.f17330h, l10.longValue());
                    } catch (Throwable th) {
                        this.f17326d.getOptions().getLogger().l(w5.WARNING, "Failed to schedule finish timer", th);
                        q7 status = getStatus();
                        if (status == null) {
                            status = q7.DEADLINE_EXCEEDED;
                        }
                        if (this.f17339r.f18042g == null) {
                            z = false;
                        }
                        c(status, z, null);
                        this.f17334m.set(false);
                    }
                }
                aVarA.close();
            } catch (Throwable th2) {
                try {
                    aVarA.close();
                } catch (Throwable th3) {
                    th2.addSuppressed(th3);
                }
                throw th2;
            }
        }
        m();
    }

    @Override // io.sentry.k1
    public final String a() {
        return this.f17324b.f17421c.f17440p;
    }

    @Override // io.sentry.k1
    public final k1 b(String str, z4 z4Var, r1 r1Var) {
        return l("activity.load", str, z4Var, r1Var, new p7());
    }

    @Override // io.sentry.m1
    public final void c(q7 q7Var, boolean z, j0 j0Var) {
        if (this.f17324b.f17424f) {
            return;
        }
        z4 z4VarA = this.f17326d.getOptions().getDateProvider().a();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList(this.f17325c);
        ListIterator listIterator = copyOnWriteArrayList.listIterator(copyOnWriteArrayList.size());
        while (listIterator.hasPrevious()) {
            k7 k7Var = (k7) listIterator.previous();
            k7Var.f17427i = null;
            k7Var.q(q7Var, z4VarA);
        }
        u(q7Var, z4VarA, z, j0Var);
    }

    @Override // io.sentry.k1
    public final void d() {
        q(getStatus(), null);
    }

    @Override // io.sentry.k1
    public final void e(String str) {
        k7 k7Var = this.f17324b;
        if (k7Var.f17424f) {
            this.f17326d.getOptions().getLogger().q(w5.DEBUG, "The transaction is already finished. Description %s cannot be set", str);
        } else {
            k7Var.f17421c.f17440p = str;
        }
    }

    @Override // io.sentry.m1
    public final io.sentry.protocol.v f() {
        return this.f17323a;
    }

    @Override // io.sentry.k1
    public final void g(String str, Number number) {
        this.f17324b.g(str, number);
    }

    @Override // io.sentry.m1
    public final String getName() {
        return this.f17327e;
    }

    @Override // io.sentry.k1
    public final q7 getStatus() {
        return this.f17324b.f17421c.f17441q;
    }

    @Override // io.sentry.k1
    public final s7 h() {
        i4 i4Var = this.f17326d;
        if (i4Var.getOptions().isTraceSampling()) {
            k7 k7Var = this.f17324b;
            l7 l7Var = k7Var.f17421c;
            l7 l7Var2 = k7Var.f17421c;
            d dVar = l7Var.f17447w;
            if (dVar != null) {
                io.sentry.util.a aVarA = this.k.a();
                try {
                    if (dVar.f17208e) {
                        AtomicReference atomicReference = new AtomicReference();
                        if (i4Var.isEnabled()) {
                            try {
                                atomicReference.set(i4Var.f17351e.X(null).o());
                            } catch (Throwable th) {
                                i4Var.getOptions().getLogger().l(w5.ERROR, "Error in the 'configureScope' callback.", th);
                            }
                        } else {
                            i4Var.getOptions().getLogger().q(w5.WARNING, "Instance is disabled and this 'configureScope' call is a no-op.", new Object[0]);
                        }
                        dVar.c(l7Var2.f17435i, (io.sentry.protocol.v) atomicReference.get(), i4Var.getOptions(), l7Var2.f17438n, this.f17327e, this.f17335n);
                        dVar.f17208e = false;
                    }
                    aVarA.close();
                    return dVar.d();
                } finally {
                }
            }
        }
        return null;
    }

    @Override // io.sentry.k1
    public final void i(q7 q7Var) {
        q(q7Var, null);
    }

    @Override // io.sentry.k1
    public final boolean isFinished() {
        return this.f17324b.f17424f;
    }

    @Override // io.sentry.k1
    public final void j(Object obj, String str) {
        k7 k7Var = this.f17324b;
        if (k7Var.f17424f) {
            this.f17326d.getOptions().getLogger().q(w5.DEBUG, "The transaction is already finished. Data %s cannot be set", str);
        } else {
            k7Var.j(obj, str);
        }
    }

    @Override // io.sentry.m1
    public final k1 k() {
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList(this.f17325c);
        ListIterator listIterator = copyOnWriteArrayList.listIterator(copyOnWriteArrayList.size());
        while (listIterator.hasPrevious()) {
            k7 k7Var = (k7) listIterator.previous();
            if (!k7Var.f17424f) {
                return k7Var;
            }
        }
        return null;
    }

    @Override // io.sentry.k1
    public final k1 l(String str, String str2, z4 z4Var, r1 r1Var, p7 p7Var) {
        boolean z = this.f17324b.f17424f;
        f3 f3Var = f3.f17277a;
        if (z || !this.f17336o.equals(r1Var)) {
            return f3Var;
        }
        int size = this.f17325c.size();
        i4 i4Var = this.f17326d;
        if (size < i4Var.getOptions().getMaxSpans()) {
            return this.f17324b.l(str, str2, z4Var, r1Var, p7Var);
        }
        i4Var.getOptions().getLogger().q(w5.WARNING, "Span operation: %s, description: %s dropped due to limit reached. Returning NoOpSpan.", str, str2);
        return f3Var;
    }

    @Override // io.sentry.m1
    public final void m() {
        Long l10;
        io.sentry.util.a aVarA = this.f17332j.a();
        try {
            if (this.f17331i != null && (l10 = this.f17339r.f18042g) != null) {
                t();
                this.f17333l.set(true);
                this.f17329g = new f7(this, 0);
                try {
                    this.f17331i.schedule(this.f17329g, l10.longValue());
                } catch (Throwable th) {
                    this.f17326d.getOptions().getLogger().l(w5.WARNING, "Failed to schedule finish timer", th);
                    q7 status = getStatus();
                    if (status == null) {
                        status = q7.OK;
                    }
                    q(status, null);
                    this.f17333l.set(false);
                }
            }
            aVarA.close();
        } catch (Throwable th2) {
            try {
                aVarA.close();
            } catch (Throwable th3) {
                th2.addSuppressed(th3);
            }
            throw th2;
        }
    }

    @Override // io.sentry.k1
    public final void makeCurrent() {
        i4 i4Var = this.f17326d;
        if (!i4Var.isEnabled()) {
            i4Var.getOptions().getLogger().q(w5.WARNING, "Instance is disabled and this 'configureScope' call is a no-op.", new Object[0]);
            return;
        }
        try {
            i4Var.f17351e.X(null).y(this);
        } catch (Throwable th) {
            i4Var.getOptions().getLogger().l(w5.ERROR, "Error in the 'configureScope' callback.", th);
        }
    }

    @Override // io.sentry.k1
    public final void n(String str, Long l10, m2 m2Var) {
        this.f17324b.n(str, l10, m2Var);
    }

    @Override // io.sentry.k1
    public final l7 o() {
        return this.f17324b.f17421c;
    }

    @Override // io.sentry.k1
    public final z4 p() {
        return this.f17324b.f17420b;
    }

    @Override // io.sentry.k1
    public final void q(q7 q7Var, z4 z4Var) {
        u(q7Var, z4Var, true, null);
    }

    @Override // io.sentry.k1
    public final z4 r() {
        return this.f17324b.f17419a;
    }

    public final void s() {
        io.sentry.util.a aVarA = this.f17332j.a();
        try {
            if (this.f17330h != null) {
                this.f17330h.cancel();
                this.f17334m.set(false);
                this.f17330h = null;
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

    public final void t() {
        io.sentry.util.a aVarA = this.f17332j.a();
        try {
            if (this.f17329g != null) {
                this.f17329g.cancel();
                this.f17333l.set(false);
                this.f17329g = null;
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

    /* JADX WARN: Removed duplicated region for block: B:33:0x00b4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void u(io.sentry.q7 r7, io.sentry.z4 r8, boolean r9, io.sentry.j0 r10) {
        /*
            Method dump skipped, instruction units count: 414
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.h7.u(io.sentry.q7, io.sentry.z4, boolean, io.sentry.j0):void");
    }

    public final io.sentry.protocol.v v() {
        k7 k7Var = this.f17324b;
        return !k7Var.f17421c.f17448y.equals(io.sentry.protocol.v.f17707l) ? k7Var.f17421c.f17448y : this.f17326d.getOptions().getContinuousProfiler().e();
    }

    public final void w(k7 k7Var) {
        io.sentry.util.thread.a threadChecker = this.f17326d.getOptions().getThreadChecker();
        io.sentry.protocol.v vVarV = v();
        if (!vVarV.equals(io.sentry.protocol.v.f17707l) && Boolean.TRUE.equals(k7Var.s())) {
            k7Var.j(vVarV.toString(), "profiler_id");
        }
        k7Var.j(String.valueOf(threadChecker.c()), "thread.id");
        k7Var.j(threadChecker.b(), "thread.name");
    }
}
