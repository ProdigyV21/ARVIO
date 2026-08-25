package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import androidx.fragment.app.a2;
import androidx.media3.session.MediaSessionService;
import androidx.work.impl.constraints.trackers.m;
import androidx.work.impl.l;
import androidx.work.impl.model.p;
import androidx.work.impl.utils.n;
import androidx.work.impl.utils.t;
import androidx.work.impl.utils.u;
import androidx.work.impl.utils.v;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import ka.f0;
import ka.x1;

/* JADX INFO: loaded from: classes3.dex */
public final class g implements androidx.work.impl.constraints.f, t {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final String f6747y = m2.t.f("DelayMetCommandHandler");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Context f6748i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f6749l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final androidx.work.impl.model.j f6750m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final j f6751n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final androidx.work.impl.constraints.j f6752o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Object f6753p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f6754q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final androidx.work.impl.utils.taskexecutor.a f6755r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final Executor f6756s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public PowerManager.WakeLock f6757t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f6758u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final l f6759v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final f0 f6760w;
    public volatile x1 x;

    public g(Context context, int i10, j jVar, l lVar) {
        this.f6748i = context;
        this.f6749l = i10;
        this.f6751n = jVar;
        this.f6750m = lVar.f6896a;
        this.f6759v = lVar;
        m mVar = jVar.f6768o.f6973j;
        androidx.work.impl.utils.taskexecutor.b bVar = jVar.f6765l;
        this.f6755r = bVar.c();
        this.f6756s = bVar.a();
        this.f6760w = bVar.b();
        this.f6752o = new androidx.work.impl.constraints.j(mVar);
        this.f6758u = false;
        this.f6754q = 0;
        this.f6753p = new Object();
    }

    public static void b(g gVar) {
        int i10 = gVar.f6749l;
        Executor executor = gVar.f6756s;
        Context context = gVar.f6748i;
        j jVar = gVar.f6751n;
        androidx.work.impl.model.j jVar2 = gVar.f6750m;
        String str = jVar2.f6914a;
        int i11 = gVar.f6754q;
        String str2 = f6747y;
        if (i11 >= 2) {
            m2.t.d().a(str2, "Already stopped work for " + str);
            return;
        }
        gVar.f6754q = 2;
        m2.t.d().a(str2, "Stopping work for WorkSpec " + str);
        Intent intent = new Intent(context, (Class<?>) SystemAlarmService.class);
        intent.setAction("ACTION_STOP_WORK");
        c.e(intent, jVar2);
        executor.execute(new android.support.v4.os.e(i10, 3, jVar, intent));
        if (!jVar.f6767n.h(str)) {
            m2.t.d().a(str2, "Processor does not have WorkSpec " + str + ". No need to reschedule");
            return;
        }
        m2.t.d().a(str2, "WorkSpec " + str + " needs to be rescheduled");
        Intent intent2 = new Intent(context, (Class<?>) SystemAlarmService.class);
        intent2.setAction("ACTION_SCHEDULE_WORK");
        c.e(intent2, jVar2);
        executor.execute(new android.support.v4.os.e(i10, 3, jVar, intent2));
    }

    public static void c(g gVar) {
        if (gVar.f6754q != 0) {
            m2.t.d().a(f6747y, "Already started work for " + gVar.f6750m);
            return;
        }
        gVar.f6754q = 1;
        m2.t.d().a(f6747y, "onAllConstraintsMet for " + gVar.f6750m);
        if (!gVar.f6751n.f6767n.j(gVar.f6759v, null)) {
            gVar.d();
            return;
        }
        v vVar = gVar.f6751n.f6766m;
        androidx.work.impl.model.j jVar = gVar.f6750m;
        synchronized (vVar.f7086d) {
            m2.t.d().a(v.f7082e, "Starting timer for " + jVar);
            vVar.a(jVar);
            u uVar = new u(vVar, jVar);
            vVar.f7084b.put(jVar, uVar);
            vVar.f7085c.put(jVar, gVar);
            vVar.f7083a.b(MediaSessionService.DEFAULT_FOREGROUND_SERVICE_TIMEOUT_MS, uVar);
        }
    }

    @Override // androidx.work.impl.utils.t
    public final void a(androidx.work.impl.model.j jVar) {
        m2.t.d().a(f6747y, "Exceeded time limits on execution for " + jVar);
        this.f6755r.execute(new f(this, 0));
    }

    public final void d() {
        synchronized (this.f6753p) {
            try {
                if (this.x != null) {
                    this.x.cancel((CancellationException) null);
                }
                this.f6751n.f6766m.a(this.f6750m);
                PowerManager.WakeLock wakeLock = this.f6757t;
                if (wakeLock != null && wakeLock.isHeld()) {
                    m2.t.d().a(f6747y, "Releasing wakelock " + this.f6757t + "for WorkSpec " + this.f6750m);
                    this.f6757t.release();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // androidx.work.impl.constraints.f
    public final void e(p pVar, androidx.work.impl.constraints.d dVar) {
        boolean z = dVar instanceof androidx.work.impl.constraints.b;
        androidx.work.impl.utils.taskexecutor.a aVar = this.f6755r;
        if (z) {
            aVar.execute(new f(this, 1));
        } else {
            aVar.execute(new f(this, 0));
        }
    }

    public final void f() {
        String str = this.f6750m.f6914a;
        Context context = this.f6748i;
        StringBuilder sbP = a2.p(str, " (");
        sbP.append(this.f6749l);
        sbP.append(")");
        this.f6757t = n.a(context, sbP.toString());
        m2.t tVarD = m2.t.d();
        String str2 = f6747y;
        tVarD.a(str2, "Acquiring wakelock " + this.f6757t + "for WorkSpec " + str);
        this.f6757t.acquire();
        p pVarK = this.f6751n.f6768o.f6966c.t().k(str);
        if (pVarK == null) {
            this.f6755r.execute(new f(this, 0));
            return;
        }
        boolean zB = pVarK.b();
        this.f6758u = zB;
        if (zB) {
            this.x = androidx.work.impl.constraints.m.a(this.f6752o, pVarK, this.f6760w, this);
            return;
        }
        m2.t.d().a(str2, "No constraints for " + str);
        this.f6755r.execute(new f(this, 1));
    }

    public final void g(boolean z) {
        m2.t tVarD = m2.t.d();
        StringBuilder sb2 = new StringBuilder("onExecuted ");
        androidx.work.impl.model.j jVar = this.f6750m;
        sb2.append(jVar);
        sb2.append(", ");
        sb2.append(z);
        tVarD.a(f6747y, sb2.toString());
        d();
        int i10 = this.f6749l;
        j jVar2 = this.f6751n;
        Executor executor = this.f6756s;
        Context context = this.f6748i;
        if (z) {
            Intent intent = new Intent(context, (Class<?>) SystemAlarmService.class);
            intent.setAction("ACTION_SCHEDULE_WORK");
            c.e(intent, jVar);
            executor.execute(new android.support.v4.os.e(i10, 3, jVar2, intent));
        }
        if (this.f6758u) {
            Intent intent2 = new Intent(context, (Class<?>) SystemAlarmService.class);
            intent2.setAction("ACTION_CONSTRAINTS_CHANGED");
            executor.execute(new android.support.v4.os.e(i10, 3, jVar2, intent2));
        }
    }
}
