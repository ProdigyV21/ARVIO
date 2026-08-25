package io.sentry;

import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes4.dex */
public final class k7 implements k1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z4 f17419a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public z4 f17420b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final l7 f17421c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final h7 f17422d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final c1 f17423e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final p7 f17426h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public n7 f17427i;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f17424f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final AtomicBoolean f17425g = new AtomicBoolean(false);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final ConcurrentHashMap f17428j = new ConcurrentHashMap();
    public final ConcurrentHashMap k = new ConcurrentHashMap();

    public k7(h7 h7Var, i4 i4Var, l7 l7Var, p7 p7Var, e7 e7Var) {
        new ConcurrentHashMap();
        new io.sentry.util.b();
        this.f17421c = l7Var;
        l7Var.f17443s = (String) p7Var.f17521d;
        ac.b.V(h7Var, "transaction is required");
        this.f17422d = h7Var;
        ac.b.V(i4Var, "Scopes are required");
        this.f17423e = i4Var;
        this.f17426h = p7Var;
        this.f17427i = e7Var;
        z4 z4Var = (z4) p7Var.f17519b;
        if (z4Var != null) {
            this.f17419a = z4Var;
        } else {
            this.f17419a = i4Var.getOptions().getDateProvider().a();
        }
    }

    @Override // io.sentry.k1
    public final String a() {
        return this.f17421c.f17440p;
    }

    @Override // io.sentry.k1
    public final k1 b(String str, z4 z4Var, r1 r1Var) {
        return l("activity.load", str, z4Var, r1Var, new p7());
    }

    @Override // io.sentry.k1
    public final void d() {
        i(this.f17421c.f17441q);
    }

    @Override // io.sentry.k1
    public final void e(String str) {
        this.f17421c.f17440p = str;
    }

    @Override // io.sentry.k1
    public final void g(String str, Number number) {
        if (this.f17424f) {
            this.f17423e.getOptions().getLogger().q(w5.DEBUG, "The span is already finished. Measurement %s cannot be set", str);
            return;
        }
        this.k.put(str, new io.sentry.protocol.l(null, number));
        h7 h7Var = this.f17422d;
        k7 k7Var = h7Var.f17324b;
        if (k7Var == this || k7Var.k.containsKey(str)) {
            return;
        }
        h7Var.g(str, number);
    }

    @Override // io.sentry.k1
    public final q7 getStatus() {
        return this.f17421c.f17441q;
    }

    @Override // io.sentry.k1
    public final void i(q7 q7Var) {
        q(q7Var, this.f17423e.getOptions().getDateProvider().a());
    }

    @Override // io.sentry.k1
    public final boolean isFinished() {
        return this.f17424f;
    }

    @Override // io.sentry.k1
    public final void j(Object obj, String str) {
        ConcurrentHashMap concurrentHashMap = this.f17428j;
        if (obj == null) {
            concurrentHashMap.remove(str);
        } else {
            concurrentHashMap.put(str, obj);
        }
    }

    @Override // io.sentry.k1
    public final k1 l(String str, String str2, z4 z4Var, r1 r1Var, p7 p7Var) {
        boolean z = this.f17424f;
        f3 f3Var = f3.f17277a;
        if (!z) {
            o7 o7Var = this.f17421c.f17436l;
            h7 h7Var = this.f17422d;
            k7 k7Var = h7Var.f17324b;
            l7 l7Var = k7Var.f17421c;
            l7 l7Var2 = new l7(l7Var.f17435i, new o7(), o7Var, str, null, l7Var.f17438n, null, "manual");
            l7Var2.f17440p = str2;
            l7Var2.f17446v = r1Var;
            p7Var.f17519b = z4Var;
            CopyOnWriteArrayList copyOnWriteArrayList = h7Var.f17325c;
            i4 i4Var = h7Var.f17326d;
            if (!k7Var.f17424f && h7Var.f17336o.equals(r1Var) && !io.sentry.util.r.a((String) p7Var.f17521d, i4Var.getOptions().getIgnoredSpanOrigins())) {
                String str3 = l7Var2.f17440p;
                int size = copyOnWriteArrayList.size();
                int maxSpans = i4Var.getOptions().getMaxSpans();
                String str4 = l7Var2.f17439o;
                if (size >= maxSpans) {
                    i4Var.getOptions().getLogger().q(w5.WARNING, "Span operation: %s, description: %s dropped due to limit reached. Returning NoOpSpan.", str4, str3);
                    return f3Var;
                }
                ac.b.V(l7Var2.f17437m, "parentSpanId is required");
                ac.b.V(str4, "operation is required");
                h7Var.t();
                k7 k7Var2 = new k7(h7Var, h7Var.f17326d, l7Var2, p7Var, new e7(h7Var));
                h7Var.w(k7Var2);
                copyOnWriteArrayList.add(k7Var2);
                l lVar = h7Var.f17338q;
                if (lVar != null) {
                    lVar.c(k7Var2);
                }
                return k7Var2;
            }
        }
        return f3Var;
    }

    @Override // io.sentry.k1
    public final void n(String str, Long l10, m2 m2Var) {
        if (this.f17424f) {
            this.f17423e.getOptions().getLogger().q(w5.DEBUG, "The span is already finished. Measurement %s cannot be set", str);
            return;
        }
        this.k.put(str, new io.sentry.protocol.l(m2Var.apiName(), l10));
        h7 h7Var = this.f17422d;
        k7 k7Var = h7Var.f17324b;
        if (k7Var == this || k7Var.k.containsKey(str)) {
            return;
        }
        h7Var.n(str, l10, m2Var);
    }

    @Override // io.sentry.k1
    public final l7 o() {
        return this.f17421c;
    }

    @Override // io.sentry.k1
    public final z4 p() {
        return this.f17420b;
    }

    @Override // io.sentry.k1
    public final void q(q7 q7Var, z4 z4Var) {
        z4 z4Var2;
        z4 z4Var3;
        if (this.f17424f || !this.f17425g.compareAndSet(false, true)) {
            return;
        }
        l7 l7Var = this.f17421c;
        l7Var.f17441q = q7Var;
        o7 o7Var = l7Var.f17436l;
        if (z4Var == null) {
            z4Var = this.f17423e.getOptions().getDateProvider().a();
        }
        this.f17420b = z4Var;
        p7 p7Var = this.f17426h;
        p7Var.getClass();
        if (p7Var.f17518a) {
            h7 h7Var = this.f17422d;
            k7 k7Var = h7Var.f17324b;
            CopyOnWriteArrayList<k7> copyOnWriteArrayList = h7Var.f17325c;
            List<k7> list = copyOnWriteArrayList;
            if (!k7Var.f17421c.f17436l.equals(o7Var)) {
                ArrayList arrayList = new ArrayList();
                for (k7 k7Var2 : copyOnWriteArrayList) {
                    o7 o7Var2 = k7Var2.f17421c.f17437m;
                    if (o7Var2 != null && o7Var2.equals(o7Var)) {
                        arrayList.add(k7Var2);
                    }
                }
                list = arrayList;
            }
            z4 z4Var4 = null;
            z4 z4Var5 = null;
            for (k7 k7Var3 : list) {
                if (z4Var4 == null || k7Var3.f17419a.d(z4Var4) < 0) {
                    z4Var4 = k7Var3.f17419a;
                }
                if (z4Var5 == null || ((z4Var3 = k7Var3.f17420b) != null && z4Var3.d(z4Var5) > 0)) {
                    z4Var5 = k7Var3.f17420b;
                }
            }
            if (p7Var.f17518a && z4Var5 != null && (((z4Var2 = this.f17420b) == null || z4Var2.d(z4Var5) > 0) && this.f17420b != null)) {
                this.f17420b = z4Var5;
            }
        }
        n7 n7Var = this.f17427i;
        if (n7Var != null) {
            n7Var.b(this);
        }
        this.f17424f = true;
    }

    @Override // io.sentry.k1
    public final z4 r() {
        return this.f17419a;
    }

    public final Boolean s() {
        androidx.core.provider.e eVar = this.f17421c.f17438n;
        if (eVar == null) {
            return null;
        }
        return (Boolean) eVar.f2140l;
    }

    public k7(u7 u7Var, h7 h7Var, i4 i4Var, w7 w7Var) {
        new ConcurrentHashMap();
        new io.sentry.util.b();
        this.f17421c = u7Var;
        u7Var.f17443s = (String) w7Var.f17521d;
        this.f17422d = h7Var;
        this.f17423e = i4Var;
        this.f17427i = null;
        z4 z4Var = (z4) w7Var.f17519b;
        if (z4Var != null) {
            this.f17419a = z4Var;
        } else {
            this.f17419a = i4Var.getOptions().getDateProvider().a();
        }
        this.f17426h = w7Var;
    }
}
