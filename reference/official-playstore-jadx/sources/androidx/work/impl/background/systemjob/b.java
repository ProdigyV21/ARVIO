package androidx.work.impl.background.systemjob;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.PersistableBundle;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.i;
import androidx.work.impl.model.g;
import androidx.work.impl.model.j;
import androidx.work.impl.model.p;
import androidx.work.impl.model.q;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import m2.t;

/* JADX INFO: loaded from: classes3.dex */
public final class b implements i {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final String f6782p = t.f("SystemJobScheduler");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Context f6783i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final JobScheduler f6784l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final a f6785m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final WorkDatabase f6786n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final m2.c f6787o;

    public b(Context context, WorkDatabase workDatabase, m2.c cVar) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        a aVar = new a(context, cVar.f20175c);
        this.f6783i = context;
        this.f6784l = jobScheduler;
        this.f6785m = aVar;
        this.f6786n = workDatabase;
        this.f6787o = cVar;
    }

    public static void c(JobScheduler jobScheduler, int i10) {
        try {
            jobScheduler.cancel(i10);
        } catch (Throwable th) {
            t.d().c(f6782p, String.format(Locale.getDefault(), "Exception while trying to cancel job (%d)", Integer.valueOf(i10)), th);
        }
    }

    public static ArrayList e(Context context, JobScheduler jobScheduler, String str) {
        ArrayList<JobInfo> arrayListF = f(context, jobScheduler);
        if (arrayListF == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(2);
        for (JobInfo jobInfo : arrayListF) {
            j jVarG = g(jobInfo);
            if (jVarG != null && str.equals(jVarG.f6914a)) {
                arrayList.add(Integer.valueOf(jobInfo.getId()));
            }
        }
        return arrayList;
    }

    public static ArrayList f(Context context, JobScheduler jobScheduler) {
        List<JobInfo> allPendingJobs;
        try {
            allPendingJobs = jobScheduler.getAllPendingJobs();
        } catch (Throwable th) {
            t.d().c(f6782p, "getAllPendingJobs() is not reliable on this device.", th);
            allPendingJobs = null;
        }
        if (allPendingJobs == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(allPendingJobs.size());
        ComponentName componentName = new ComponentName(context, (Class<?>) SystemJobService.class);
        for (JobInfo jobInfo : allPendingJobs) {
            if (componentName.equals(jobInfo.getService())) {
                arrayList.add(jobInfo);
            }
        }
        return arrayList;
    }

    public static j g(JobInfo jobInfo) {
        PersistableBundle extras = jobInfo.getExtras();
        if (extras == null) {
            return null;
        }
        try {
            if (!extras.containsKey("EXTRA_WORK_SPEC_ID")) {
                return null;
            }
            return new j(extras.getString("EXTRA_WORK_SPEC_ID"), extras.getInt("EXTRA_WORK_SPEC_GENERATION", 0));
        } catch (NullPointerException unused) {
            return null;
        }
    }

    @Override // androidx.work.impl.i
    public final void a(String str) {
        Context context = this.f6783i;
        JobScheduler jobScheduler = this.f6784l;
        ArrayList arrayListE = e(context, jobScheduler, str);
        if (arrayListE == null || arrayListE.isEmpty()) {
            return;
        }
        Iterator it = arrayListE.iterator();
        while (it.hasNext()) {
            c(jobScheduler, ((Integer) it.next()).intValue());
        }
        this.f6786n.p().l(str);
    }

    @Override // androidx.work.impl.i
    public final void b(p... pVarArr) {
        int iIntValue;
        ArrayList arrayListE;
        int iIntValue2;
        WorkDatabase workDatabase = this.f6786n;
        final androidx.work.impl.utils.f fVar = new androidx.work.impl.utils.f(workDatabase);
        for (p pVar : pVarArr) {
            workDatabase.c();
            try {
                q qVarT = workDatabase.t();
                String str = pVar.f6922a;
                p pVarK = qVarT.k(str);
                String str2 = f6782p;
                if (pVarK == null) {
                    t.d().g(str2, "Skipping scheduling " + str + " because it's no longer in the DB");
                    workDatabase.o();
                } else if (pVarK.f6923b != 1) {
                    t.d().g(str2, "Skipping scheduling " + str + " because it is no longer enqueued");
                    workDatabase.o();
                } else {
                    j jVarN = a.a.N(pVar);
                    g gVarD = workDatabase.p().d(jVarN);
                    m2.c cVar = this.f6787o;
                    WorkDatabase workDatabase2 = fVar.f7011a;
                    if (gVarD != null) {
                        iIntValue = gVarD.f6912c;
                    } else {
                        cVar.getClass();
                        final int i10 = cVar.f20180h;
                        iIntValue = ((Number) workDatabase2.n(new Callable() { // from class: androidx.work.impl.utils.e
                            @Override // java.util.concurrent.Callable
                            public final Object call() {
                                WorkDatabase workDatabase3 = fVar.f7011a;
                                Long lQ = workDatabase3.l().q("next_job_scheduler_id");
                                int i11 = 0;
                                int iLongValue = lQ != null ? (int) lQ.longValue() : 0;
                                workDatabase3.l().j(new androidx.work.impl.model.d("next_job_scheduler_id", Long.valueOf(iLongValue == Integer.MAX_VALUE ? 0 : iLongValue + 1)));
                                if (iLongValue < 0 || iLongValue > i10) {
                                    workDatabase3.l().j(new androidx.work.impl.model.d("next_job_scheduler_id", Long.valueOf(1)));
                                } else {
                                    i11 = iLongValue;
                                }
                                return Integer.valueOf(i11);
                            }
                        })).intValue();
                    }
                    if (gVarD == null) {
                        workDatabase.p().k(new g(jVarN.f6914a, jVarN.f6915b, iIntValue));
                    }
                    h(pVar, iIntValue);
                    if (Build.VERSION.SDK_INT == 23 && (arrayListE = e(this.f6783i, this.f6784l, str)) != null) {
                        int iIndexOf = arrayListE.indexOf(Integer.valueOf(iIntValue));
                        if (iIndexOf >= 0) {
                            arrayListE.remove(iIndexOf);
                        }
                        if (arrayListE.isEmpty()) {
                            cVar.getClass();
                            final int i11 = cVar.f20180h;
                            iIntValue2 = ((Number) workDatabase2.n(new Callable() { // from class: androidx.work.impl.utils.e
                                @Override // java.util.concurrent.Callable
                                public final Object call() {
                                    WorkDatabase workDatabase3 = fVar.f7011a;
                                    Long lQ = workDatabase3.l().q("next_job_scheduler_id");
                                    int i112 = 0;
                                    int iLongValue = lQ != null ? (int) lQ.longValue() : 0;
                                    workDatabase3.l().j(new androidx.work.impl.model.d("next_job_scheduler_id", Long.valueOf(iLongValue == Integer.MAX_VALUE ? 0 : iLongValue + 1)));
                                    if (iLongValue < 0 || iLongValue > i11) {
                                        workDatabase3.l().j(new androidx.work.impl.model.d("next_job_scheduler_id", Long.valueOf(1)));
                                    } else {
                                        i112 = iLongValue;
                                    }
                                    return Integer.valueOf(i112);
                                }
                            })).intValue();
                        } else {
                            iIntValue2 = ((Integer) arrayListE.get(0)).intValue();
                        }
                        h(pVar, iIntValue2);
                    }
                    workDatabase.o();
                }
            } finally {
                workDatabase.k();
            }
        }
    }

    @Override // androidx.work.impl.i
    public final boolean d() {
        return true;
    }

    public final void h(p pVar, int i10) {
        JobScheduler jobScheduler = this.f6784l;
        JobInfo jobInfoA = this.f6785m.a(pVar, i10);
        t tVarD = t.d();
        StringBuilder sb2 = new StringBuilder("Scheduling work ID ");
        String str = pVar.f6922a;
        sb2.append(str);
        sb2.append("Job ID ");
        sb2.append(i10);
        String string = sb2.toString();
        String str2 = f6782p;
        tVarD.a(str2, string);
        try {
            if (jobScheduler.schedule(jobInfoA) == 0) {
                t.d().g(str2, "Unable to schedule work ID " + str);
                if (pVar.f6937q && pVar.f6938r == 1) {
                    pVar.f6937q = false;
                    t.d().a(str2, "Scheduling a non-expedited job (work ID " + str + ")");
                    h(pVar, i10);
                }
            }
        } catch (IllegalStateException e5) {
            ArrayList arrayListF = f(this.f6783i, jobScheduler);
            String str3 = String.format(Locale.getDefault(), "JobScheduler 100 job limit exceeded.  We count %d WorkManager jobs in JobScheduler; we have %d tracked jobs in our DB; our Configuration limit is %d.", Integer.valueOf(arrayListF != null ? arrayListF.size() : 0), Integer.valueOf(this.f6786n.t().g().size()), Integer.valueOf(this.f6787o.f20182j));
            t.d().b(str2, str3);
            throw new IllegalStateException(str3, e5);
        } catch (Throwable th) {
            t.d().c(str2, "Unable to schedule " + pVar, th);
        }
    }
}
