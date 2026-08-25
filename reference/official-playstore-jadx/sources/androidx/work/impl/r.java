package androidx.work.impl;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.Build;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import m2.f0;

/* JADX INFO: loaded from: classes3.dex */
public final class r extends f0 {
    public static r k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static r f6962l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final Object f6963m;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f6964a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m2.c f6965b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final WorkDatabase f6966c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final androidx.work.impl.utils.taskexecutor.d f6967d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List f6968e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final g f6969f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final androidx.work.impl.utils.f f6970g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f6971h = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public BroadcastReceiver.PendingResult f6972i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final androidx.work.impl.constraints.trackers.m f6973j;

    static {
        m2.t.f("WorkManagerImpl");
        k = null;
        f6962l = null;
        f6963m = new Object();
    }

    public r(Context context, final m2.c cVar, androidx.work.impl.utils.taskexecutor.d dVar, final WorkDatabase workDatabase, final List list, g gVar, androidx.work.impl.constraints.trackers.m mVar) {
        Context applicationContext = context.getApplicationContext();
        if (Build.VERSION.SDK_INT >= 24 && q.a(applicationContext)) {
            throw new IllegalStateException("Cannot initialize WorkManager in direct boot mode");
        }
        m2.t tVar = new m2.t(cVar.f20179g);
        synchronized (m2.t.f20214b) {
            m2.t.f20215c = tVar;
        }
        this.f6964a = applicationContext;
        this.f6967d = dVar;
        this.f6966c = workDatabase;
        this.f6969f = gVar;
        this.f6973j = mVar;
        this.f6965b = cVar;
        this.f6968e = list;
        this.f6970g = new androidx.work.impl.utils.f(workDatabase);
        final androidx.work.impl.utils.l lVar = dVar.f7076a;
        String str = k.f6895a;
        gVar.b(new d() { // from class: androidx.work.impl.j
            @Override // androidx.work.impl.d
            public final void c(androidx.work.impl.model.j jVar, boolean z) {
                lVar.execute(new androidx.media3.exoplayer.source.preload.b(list, jVar, cVar, workDatabase, 2));
            }
        });
        dVar.d(new androidx.work.impl.utils.d(applicationContext, this));
    }

    public static r Y() {
        synchronized (f6963m) {
            try {
                r rVar = k;
                if (rVar != null) {
                    return rVar;
                }
                return f6962l;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static r Z(Context context) {
        r rVarY;
        synchronized (f6963m) {
            try {
                rVarY = Y();
                if (rVarY == null) {
                    Context applicationContext = context.getApplicationContext();
                    if (!(applicationContext instanceof m2.b)) {
                        throw new IllegalStateException("WorkManager is not initialized properly.  You have explicitly disabled WorkManagerInitializer in your manifest, have not manually called WorkManager#initialize at this point, and your Application does not implement Configuration.Provider.");
                    }
                    a0(applicationContext, ((m2.b) applicationContext).getWorkManagerConfiguration());
                    rVarY = Z(applicationContext);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return rVarY;
    }

    public static void a0(Context context, m2.c cVar) {
        synchronized (f6963m) {
            try {
                r rVar = k;
                if (rVar != null && f6962l != null) {
                    throw new IllegalStateException("WorkManager is already initialized.  Did you try to initialize it manually without disabling WorkManagerInitializer? See WorkManager#initialize(Context, Configuration) or the class level Javadoc for more information.");
                }
                if (rVar == null) {
                    Context applicationContext = context.getApplicationContext();
                    if (f6962l == null) {
                        f6962l = t.l(applicationContext, cVar);
                    }
                    k = f6962l;
                }
            } finally {
            }
        }
    }

    public final void b0() {
        synchronized (f6963m) {
            try {
                this.f6971h = true;
                BroadcastReceiver.PendingResult pendingResult = this.f6972i;
                if (pendingResult != null) {
                    pendingResult.finish();
                    this.f6972i = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void c0() {
        ArrayList arrayListF;
        String str = androidx.work.impl.background.systemjob.b.f6782p;
        Context context = this.f6964a;
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        if (jobScheduler != null && (arrayListF = androidx.work.impl.background.systemjob.b.f(context, jobScheduler)) != null && !arrayListF.isEmpty()) {
            Iterator it = arrayListF.iterator();
            while (it.hasNext()) {
                androidx.work.impl.background.systemjob.b.c(jobScheduler, ((JobInfo) it.next()).getId());
            }
        }
        WorkDatabase workDatabase = this.f6966c;
        workDatabase.t().n();
        k.b(this.f6965b, workDatabase, this.f6968e);
    }
}
