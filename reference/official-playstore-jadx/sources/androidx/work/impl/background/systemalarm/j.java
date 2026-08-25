package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.os.PowerManager;
import android.text.TextUtils;
import androidx.work.impl.p;
import androidx.work.impl.r;
import androidx.work.impl.utils.n;
import androidx.work.impl.utils.v;
import java.util.ArrayList;
import java.util.Iterator;
import m2.t;

/* JADX INFO: loaded from: classes3.dex */
public final class j implements androidx.work.impl.d {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final String f6763u = t.f("SystemAlarmDispatcher");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Context f6764i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final androidx.work.impl.utils.taskexecutor.b f6765l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final v f6766m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final androidx.work.impl.g f6767n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final r f6768o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final c f6769p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final ArrayList f6770q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Intent f6771r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public SystemAlarmService f6772s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final p f6773t;

    public j(SystemAlarmService systemAlarmService) {
        Context applicationContext = systemAlarmService.getApplicationContext();
        this.f6764i = applicationContext;
        io.sentry.internal.debugmeta.c cVar = new io.sentry.internal.debugmeta.c(5);
        r rVarZ = r.Z(systemAlarmService);
        this.f6768o = rVarZ;
        this.f6769p = new c(applicationContext, rVarZ.f6965b.f20175c, cVar);
        this.f6766m = new v(rVarZ.f6965b.f20178f);
        androidx.work.impl.g gVar = rVarZ.f6969f;
        this.f6767n = gVar;
        androidx.work.impl.utils.taskexecutor.d dVar = rVarZ.f6967d;
        this.f6765l = dVar;
        this.f6773t = new io.sentry.internal.debugmeta.c(6, gVar, false, dVar);
        gVar.b(this);
        this.f6770q = new ArrayList();
        this.f6771r = null;
    }

    public static void b() {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("Needs to be invoked on the main thread.");
        }
    }

    public final void a(Intent intent, int i10) {
        t tVarD = t.d();
        String str = f6763u;
        tVarD.a(str, "Adding command " + intent + " (" + i10 + ")");
        b();
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            t.d().g(str, "Unknown command. Ignoring");
            return;
        }
        if ("ACTION_CONSTRAINTS_CHANGED".equals(action) && d()) {
            return;
        }
        intent.putExtra("KEY_START_ID", i10);
        synchronized (this.f6770q) {
            try {
                boolean zIsEmpty = this.f6770q.isEmpty();
                this.f6770q.add(intent);
                if (zIsEmpty) {
                    e();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // androidx.work.impl.d
    public final void c(androidx.work.impl.model.j jVar, boolean z) {
        androidx.work.impl.utils.taskexecutor.c cVarA = this.f6765l.a();
        String str = c.f6734p;
        Intent intent = new Intent(this.f6764i, (Class<?>) SystemAlarmService.class);
        intent.setAction("ACTION_EXECUTION_COMPLETED");
        intent.putExtra("KEY_NEEDS_RESCHEDULE", z);
        c.e(intent, jVar);
        cVarA.execute(new android.support.v4.os.e(0, 3, this, intent));
    }

    public final boolean d() {
        b();
        synchronized (this.f6770q) {
            try {
                Iterator it = this.f6770q.iterator();
                while (it.hasNext()) {
                    if ("ACTION_CONSTRAINTS_CHANGED".equals(((Intent) it.next()).getAction())) {
                        return true;
                    }
                }
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void e() {
        b();
        PowerManager.WakeLock wakeLockA = n.a(this.f6764i, "ProcessCommand");
        try {
            wakeLockA.acquire();
            this.f6768o.f6967d.d(new h(this, 0));
        } finally {
            wakeLockA.release();
        }
    }
}
