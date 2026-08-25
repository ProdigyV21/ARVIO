package androidx.work.impl.background.systemjob;

import android.app.Application;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.os.PersistableBundle;
import androidx.appcompat.app.i1;
import androidx.core.provider.o;
import androidx.work.impl.g;
import androidx.work.impl.l;
import androidx.work.impl.model.j;
import androidx.work.impl.r;
import java.util.Arrays;
import java.util.HashMap;
import m2.t;

/* JADX INFO: loaded from: classes3.dex */
public class SystemJobService extends JobService implements androidx.work.impl.d {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final String f6774o = t.f("SystemJobService");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public r f6775i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final HashMap f6776l = new HashMap();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final io.sentry.internal.debugmeta.c f6777m = new io.sentry.internal.debugmeta.c(5);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public io.sentry.internal.debugmeta.c f6778n;

    public static j a(JobParameters jobParameters) {
        try {
            PersistableBundle extras = jobParameters.getExtras();
            if (extras == null || !extras.containsKey("EXTRA_WORK_SPEC_ID")) {
                return null;
            }
            return new j(extras.getString("EXTRA_WORK_SPEC_ID"), extras.getInt("EXTRA_WORK_SPEC_GENERATION"));
        } catch (NullPointerException unused) {
            return null;
        }
    }

    @Override // androidx.work.impl.d
    public final void c(j jVar, boolean z) {
        JobParameters jobParameters;
        t.d().a(f6774o, jVar.f6914a + " executed on JobScheduler");
        synchronized (this.f6776l) {
            jobParameters = (JobParameters) this.f6776l.remove(jVar);
        }
        this.f6777m.E(jVar);
        if (jobParameters != null) {
            jobFinished(jobParameters, z);
        }
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        try {
            r rVarZ = r.Z(getApplicationContext());
            this.f6775i = rVarZ;
            g gVar = rVarZ.f6969f;
            this.f6778n = new io.sentry.internal.debugmeta.c(6, gVar, false, rVarZ.f6967d);
            gVar.b(this);
        } catch (IllegalStateException e5) {
            if (!Application.class.equals(getApplication().getClass())) {
                throw new IllegalStateException("WorkManager needs to be initialized via a ContentProvider#onCreate() or an Application#onCreate().", e5);
            }
            t.d().g(f6774o, "Could not find WorkManager instance; this may be because an auto-backup is in progress. Ignoring JobScheduler commands for now. Please make sure that you are initializing WorkManager if you have manually disabled WorkManagerInitializer.");
        }
    }

    @Override // android.app.Service
    public final void onDestroy() {
        super.onDestroy();
        r rVar = this.f6775i;
        if (rVar != null) {
            rVar.f6969f.i(this);
        }
    }

    @Override // android.app.job.JobService
    public final boolean onStartJob(JobParameters jobParameters) {
        i1 i1Var;
        if (this.f6775i == null) {
            t.d().a(f6774o, "WorkManager is not initialized; requesting retry.");
            jobFinished(jobParameters, true);
            return false;
        }
        j jVarA = a(jobParameters);
        if (jVarA == null) {
            t.d().b(f6774o, "WorkSpec id not found!");
            return false;
        }
        synchronized (this.f6776l) {
            try {
                if (this.f6776l.containsKey(jVarA)) {
                    t.d().a(f6774o, "Job is already being executed by SystemJobService: " + jVarA);
                    return false;
                }
                t.d().a(f6774o, "onStartJob for " + jVarA);
                this.f6776l.put(jVarA, jobParameters);
                int i10 = Build.VERSION.SDK_INT;
                if (i10 >= 24) {
                    i1Var = new i1(23);
                    if (c.b(jobParameters) != null) {
                        i1Var.f1060m = Arrays.asList(c.b(jobParameters));
                    }
                    if (c.a(jobParameters) != null) {
                        i1Var.f1059l = Arrays.asList(c.a(jobParameters));
                    }
                    if (i10 >= 28) {
                        i1Var.f1061n = d.a(jobParameters);
                    }
                } else {
                    i1Var = null;
                }
                io.sentry.internal.debugmeta.c cVar = this.f6778n;
                ((androidx.work.impl.utils.taskexecutor.b) cVar.f17368m).d(new o((g) cVar.f17367l, this.f6777m.I(jVarA), i1Var, 2));
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // android.app.job.JobService
    public final boolean onStopJob(JobParameters jobParameters) {
        if (this.f6775i == null) {
            t.d().a(f6774o, "WorkManager is not initialized; requesting retry.");
            return true;
        }
        j jVarA = a(jobParameters);
        if (jVarA == null) {
            t.d().b(f6774o, "WorkSpec id not found!");
            return false;
        }
        t.d().a(f6774o, "onStopJob for " + jVarA);
        synchronized (this.f6776l) {
            this.f6776l.remove(jVarA);
        }
        l lVarE = this.f6777m.E(jVarA);
        if (lVarE != null) {
            this.f6778n.H(lVarE, Build.VERSION.SDK_INT >= 31 ? e.a(jobParameters) : -512);
        }
        return !this.f6775i.f6969f.g(jVarA.f6914a);
    }
}
