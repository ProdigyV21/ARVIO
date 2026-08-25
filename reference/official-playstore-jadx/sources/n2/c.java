package n2;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import androidx.tv.material3.u3;
import androidx.work.impl.constraints.f;
import androidx.work.impl.constraints.j;
import androidx.work.impl.constraints.trackers.m;
import androidx.work.impl.d;
import androidx.work.impl.g;
import androidx.work.impl.i;
import androidx.work.impl.l;
import androidx.work.impl.model.p;
import androidx.work.impl.utils.k;
import com.google.common.util.concurrent.w0;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import ka.v1;
import m2.d0;
import m2.t;

/* JADX INFO: loaded from: classes3.dex */
public final class c implements i, f, d {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final String f20468y = t.f("GreedyScheduler");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Context f20469i;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final a f20471m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f20472n;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final g f20475q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final io.sentry.internal.debugmeta.c f20476r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final m2.c f20477s;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Boolean f20479u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final j f20480v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final androidx.work.impl.utils.taskexecutor.b f20481w;
    public final u3 x;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final HashMap f20470l = new HashMap();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final Object f20473o = new Object();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final io.sentry.internal.debugmeta.c f20474p = new io.sentry.internal.debugmeta.c(5);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final HashMap f20478t = new HashMap();

    public c(Context context, m2.c cVar, m mVar, g gVar, io.sentry.internal.debugmeta.c cVar2, androidx.work.impl.utils.taskexecutor.b bVar) {
        this.f20469i = context;
        androidx.work.impl.c cVar3 = cVar.f20178f;
        this.f20471m = new a(this, cVar3, cVar.f20175c);
        long millis = TimeUnit.MINUTES.toMillis(90L);
        u3 u3Var = new u3();
        u3Var.f6447b = cVar3;
        u3Var.f6448c = cVar2;
        u3Var.f6446a = millis;
        u3Var.f6449d = new Object();
        u3Var.f6450e = new LinkedHashMap();
        this.x = u3Var;
        this.f20481w = bVar;
        this.f20480v = new j(mVar);
        this.f20477s = cVar;
        this.f20475q = gVar;
        this.f20476r = cVar2;
    }

    @Override // androidx.work.impl.i
    public final void a(String str) {
        Runnable runnable;
        if (this.f20479u == null) {
            this.f20479u = Boolean.valueOf(k.a(this.f20469i));
        }
        boolean zBooleanValue = this.f20479u.booleanValue();
        String str2 = f20468y;
        if (!zBooleanValue) {
            t.d().e(str2, "Ignoring schedule request in non-main process");
            return;
        }
        if (!this.f20472n) {
            this.f20475q.b(this);
            this.f20472n = true;
        }
        t.d().a(str2, "Cancelling work ID " + str);
        a aVar = this.f20471m;
        if (aVar != null && (runnable = (Runnable) aVar.f20465d.remove(str)) != null) {
            aVar.f20463b.a(runnable);
        }
        for (l lVar : this.f20474p.F(str)) {
            this.x.a(lVar);
            this.f20476r.n(lVar);
        }
    }

    @Override // androidx.work.impl.i
    public final void b(p... pVarArr) {
        if (this.f20479u == null) {
            this.f20479u = Boolean.valueOf(k.a(this.f20469i));
        }
        if (!this.f20479u.booleanValue()) {
            t.d().e(f20468y, "Ignoring schedule request in a secondary process");
            return;
        }
        if (!this.f20472n) {
            this.f20475q.b(this);
            this.f20472n = true;
        }
        HashSet<p> hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        for (p pVar : pVarArr) {
            if (!this.f20474p.x(a.a.N(pVar))) {
                long jMax = Math.max(pVar.a(), g(pVar));
                this.f20477s.f20175c.getClass();
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (pVar.f6923b == 1) {
                    if (jCurrentTimeMillis < jMax) {
                        a aVar = this.f20471m;
                        if (aVar != null) {
                            d0 d0Var = aVar.f20463b;
                            HashMap map = aVar.f20465d;
                            Runnable runnable = (Runnable) map.remove(pVar.f6922a);
                            if (runnable != null) {
                                d0Var.a(runnable);
                            }
                            w0 w0Var = new w0(29, aVar, false, pVar);
                            map.put(pVar.f6922a, w0Var);
                            d0Var.b(jMax - aVar.f20464c.currentTimeMillis(), w0Var);
                        }
                    } else if (pVar.b()) {
                        int i10 = Build.VERSION.SDK_INT;
                        m2.f fVar = pVar.f6931j;
                        if (fVar.f20190c) {
                            t.d().a(f20468y, "Ignoring " + pVar + ". Requires device idle.");
                        } else if (i10 < 24 || !fVar.a()) {
                            hashSet.add(pVar);
                            hashSet2.add(pVar.f6922a);
                        } else {
                            t.d().a(f20468y, "Ignoring " + pVar + ". Requires ContentUri triggers.");
                        }
                    } else if (!this.f20474p.x(a.a.N(pVar))) {
                        t.d().a(f20468y, "Starting work for " + pVar.f6922a);
                        l lVarI = this.f20474p.I(a.a.N(pVar));
                        this.x.b(lVarI);
                        this.f20476r.G(lVarI);
                    }
                }
            }
        }
        synchronized (this.f20473o) {
            try {
                if (!hashSet.isEmpty()) {
                    t.d().a(f20468y, "Starting tracking for " + TextUtils.join(",", hashSet2));
                    for (p pVar2 : hashSet) {
                        androidx.work.impl.model.j jVarN = a.a.N(pVar2);
                        if (!this.f20470l.containsKey(jVarN)) {
                            this.f20470l.put(jVarN, androidx.work.impl.constraints.m.a(this.f20480v, pVar2, this.f20481w.b(), this));
                        }
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // androidx.work.impl.d
    public final void c(androidx.work.impl.model.j jVar, boolean z) {
        l lVarE = this.f20474p.E(jVar);
        if (lVarE != null) {
            this.x.a(lVarE);
        }
        f(jVar);
        if (z) {
            return;
        }
        synchronized (this.f20473o) {
            this.f20478t.remove(jVar);
        }
    }

    @Override // androidx.work.impl.i
    public final boolean d() {
        return false;
    }

    @Override // androidx.work.impl.constraints.f
    public final void e(p pVar, androidx.work.impl.constraints.d dVar) {
        androidx.work.impl.model.j jVarN = a.a.N(pVar);
        boolean z = dVar instanceof androidx.work.impl.constraints.b;
        io.sentry.internal.debugmeta.c cVar = this.f20476r;
        u3 u3Var = this.x;
        String str = f20468y;
        io.sentry.internal.debugmeta.c cVar2 = this.f20474p;
        if (z) {
            if (cVar2.x(jVarN)) {
                return;
            }
            t.d().a(str, "Constraints met: Scheduling work ID " + jVarN);
            l lVarI = cVar2.I(jVarN);
            u3Var.b(lVarI);
            cVar.G(lVarI);
            return;
        }
        t.d().a(str, "Constraints not met: Cancelling work ID " + jVarN);
        l lVarE = cVar2.E(jVarN);
        if (lVarE != null) {
            u3Var.a(lVarE);
            cVar.H(lVarE, ((androidx.work.impl.constraints.c) dVar).f6790a);
        }
    }

    public final void f(androidx.work.impl.model.j jVar) {
        v1 v1Var;
        synchronized (this.f20473o) {
            v1Var = (v1) this.f20470l.remove(jVar);
        }
        if (v1Var != null) {
            t.d().a(f20468y, "Stopping tracking for " + jVar);
            v1Var.cancel((CancellationException) null);
        }
    }

    public final long g(p pVar) {
        long jMax;
        synchronized (this.f20473o) {
            try {
                androidx.work.impl.model.j jVarN = a.a.N(pVar);
                b bVar = (b) this.f20478t.get(jVarN);
                if (bVar == null) {
                    int i10 = pVar.k;
                    this.f20477s.f20175c.getClass();
                    bVar = new b(i10, System.currentTimeMillis());
                    this.f20478t.put(jVarN, bVar);
                }
                jMax = (((long) Math.max((pVar.k - bVar.f20466a) - 5, 0)) * 30000) + bVar.f20467b;
            } catch (Throwable th) {
                throw th;
            }
        }
        return jMax;
    }
}
