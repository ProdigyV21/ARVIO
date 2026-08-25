package androidx.work.impl;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import androidx.appcompat.app.i1;
import androidx.work.impl.foreground.SystemForegroundService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public final class g implements androidx.work.impl.foreground.a {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final String f6878l = m2.t.f("Processor");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f6880b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final m2.c f6881c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final androidx.work.impl.utils.taskexecutor.d f6882d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final WorkDatabase f6883e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final HashMap f6885g = new HashMap();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final HashMap f6884f = new HashMap();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final HashSet f6887i = new HashSet();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final ArrayList f6888j = new ArrayList();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public PowerManager.WakeLock f6879a = null;
    public final Object k = new Object();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final HashMap f6886h = new HashMap();

    public g(Context context, m2.c cVar, androidx.work.impl.utils.taskexecutor.d dVar, WorkDatabase workDatabase) {
        this.f6880b = context;
        this.f6881c = cVar;
        this.f6882d = dVar;
        this.f6883e = workDatabase;
    }

    public static boolean f(String str, v vVar, int i10) {
        if (vVar == null) {
            m2.t.d().a(f6878l, "WorkerWrapper could not be found for " + str);
            return false;
        }
        vVar.B = i10;
        vVar.h();
        vVar.A.cancel(true);
        if (vVar.f7091o == null || !(vVar.A.f7037i instanceof androidx.work.impl.utils.futures.a)) {
            m2.t.d().a(v.C, "WorkSpec " + vVar.f7090n + " is already done. Not interrupting.");
        } else {
            vVar.f7091o.stop(i10);
        }
        m2.t.d().a(f6878l, "WorkerWrapper interrupted for " + str);
        return true;
    }

    @Override // androidx.work.impl.foreground.a
    public final void a(String str, m2.j jVar) {
        synchronized (this.k) {
            try {
                m2.t.d().e(f6878l, "Moving WorkSpec (" + str + ") to the foreground");
                v vVar = (v) this.f6885g.remove(str);
                if (vVar != null) {
                    if (this.f6879a == null) {
                        PowerManager.WakeLock wakeLockA = androidx.work.impl.utils.n.a(this.f6880b, "ProcessorForegroundLck");
                        this.f6879a = wakeLockA;
                        wakeLockA.acquire();
                    }
                    this.f6884f.put(str, vVar);
                    qb.d.U(this.f6880b, androidx.work.impl.foreground.c.b(this.f6880b, a.a.N(vVar.f7090n), jVar));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void b(d dVar) {
        synchronized (this.k) {
            this.f6888j.add(dVar);
        }
    }

    public final v c(String str) {
        v vVar = (v) this.f6884f.remove(str);
        boolean z = vVar != null;
        if (!z) {
            vVar = (v) this.f6885g.remove(str);
        }
        this.f6886h.remove(str);
        if (z) {
            synchronized (this.k) {
                try {
                    if (this.f6884f.isEmpty()) {
                        Context context = this.f6880b;
                        String str2 = androidx.work.impl.foreground.c.f6864t;
                        Intent intent = new Intent(context, (Class<?>) SystemForegroundService.class);
                        intent.setAction("ACTION_STOP_FOREGROUND");
                        try {
                            this.f6880b.startService(intent);
                        } catch (Throwable th) {
                            m2.t.d().c(f6878l, "Unable to stop foreground service", th);
                        }
                        PowerManager.WakeLock wakeLock = this.f6879a;
                        if (wakeLock != null) {
                            wakeLock.release();
                            this.f6879a = null;
                        }
                    }
                } finally {
                }
            }
        }
        return vVar;
    }

    public final androidx.work.impl.model.p d(String str) {
        synchronized (this.k) {
            try {
                v vVarE = e(str);
                if (vVarE == null) {
                    return null;
                }
                return vVarE.f7090n;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final v e(String str) {
        v vVar = (v) this.f6884f.get(str);
        return vVar == null ? (v) this.f6885g.get(str) : vVar;
    }

    public final boolean g(String str) {
        boolean zContains;
        synchronized (this.k) {
            zContains = this.f6887i.contains(str);
        }
        return zContains;
    }

    public final boolean h(String str) {
        boolean z;
        synchronized (this.k) {
            z = e(str) != null;
        }
        return z;
    }

    public final void i(d dVar) {
        synchronized (this.k) {
            this.f6888j.remove(dVar);
        }
    }

    public final boolean j(l lVar, i1 i1Var) {
        Throwable th;
        androidx.work.impl.model.j jVar = lVar.f6896a;
        String str = jVar.f6914a;
        ArrayList arrayList = new ArrayList();
        androidx.work.impl.model.p pVar = (androidx.work.impl.model.p) this.f6883e.n(new f(this, arrayList, str, 0));
        if (pVar == null) {
            m2.t.d().g(f6878l, "Didn't find WorkSpec for id " + jVar);
            this.f6882d.f7079d.execute(new androidx.activity.s(this, jVar, 11));
            return false;
        }
        synchronized (this.k) {
            try {
                try {
                    try {
                        if (h(str)) {
                            Set set = (Set) this.f6886h.get(str);
                            if (((l) set.iterator().next()).f6896a.f6915b == jVar.f6915b) {
                                set.add(lVar);
                                m2.t.d().a(f6878l, "Work " + jVar + " is already enqueued for processing");
                            } else {
                                this.f6882d.f7079d.execute(new androidx.activity.s(this, jVar, 11));
                            }
                            return false;
                        }
                        if (pVar.f6940t != jVar.f6915b) {
                            this.f6882d.f7079d.execute(new androidx.activity.s(this, jVar, 11));
                            return false;
                        }
                        u uVar = new u(this.f6880b, this.f6881c, this.f6882d, this, this.f6883e, pVar, arrayList);
                        if (i1Var != null) {
                            uVar.f6993h = i1Var;
                        }
                        v vVar = new v(uVar);
                        androidx.work.impl.utils.futures.k kVar = vVar.z;
                        kVar.addListener(new androidx.emoji2.text.q(this, kVar, vVar, 7), this.f6882d.f7079d);
                        this.f6885g.put(str, vVar);
                        HashSet hashSet = new HashSet();
                        hashSet.add(lVar);
                        this.f6886h.put(str, hashSet);
                        this.f6882d.f7076a.execute(vVar);
                        m2.t.d().a(f6878l, g.class.getSimpleName() + ": processing " + jVar);
                        return true;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (Throwable th4) {
                th = th4;
            }
            th = th;
            throw th;
        }
    }

    public final boolean k(l lVar, int i10) {
        String str = lVar.f6896a.f6914a;
        synchronized (this.k) {
            try {
                if (this.f6884f.get(str) == null) {
                    Set set = (Set) this.f6886h.get(str);
                    if (set != null && set.contains(lVar)) {
                        return f(str, c(str), i10);
                    }
                    return false;
                }
                m2.t.d().a(f6878l, "Ignored stopWork. WorkerWrapper " + str + " is in foreground");
                return false;
            } finally {
            }
        }
    }
}
