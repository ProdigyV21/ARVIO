package io.sentry.android.ndk;

import android.app.job.JobParameters;
import androidx.activity.n;
import androidx.tv.material3.u3;
import androidx.work.impl.l;
import androidx.work.impl.utils.futures.k;
import androidx.work.impl.workers.ConstraintTrackingWorker;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.JobInfoSchedulerService;
import com.google.common.util.concurrent.d1;
import fi.iki.elonen.f;
import io.sentry.ILogger;
import io.sentry.android.replay.util.g;
import io.sentry.l7;
import io.sentry.ndk.NativeScope;
import io.sentry.protocol.i0;
import io.sentry.protocol.v;
import io.sentry.w5;
import io.sentry.y6;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import m2.p;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class b implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f16840i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f16841l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f16842m;

    public /* synthetic */ b(f fVar, retrofit2.f fVar2, Throwable th) {
        this.f16840i = 16;
        this.f16841l = fVar2;
        this.f16842m = th;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f16840i) {
            case 0:
                d dVar = (d) this.f16841l;
                io.sentry.f fVar = (io.sentry.f) this.f16842m;
                y6 y6Var = dVar.f16847a;
                w5 w5Var = fVar.f17251s;
                String strB = null;
                String lowerCase = w5Var != null ? w5Var.name().toLowerCase(Locale.ROOT) : null;
                String strR = k2.c.r(fVar.d());
                try {
                    ConcurrentHashMap concurrentHashMap = fVar.f17248p;
                    if (!concurrentHashMap.isEmpty()) {
                        strB = y6Var.getSerializer().b(concurrentHashMap);
                    }
                    break;
                } catch (Throwable th) {
                    y6Var.getLogger().k(w5.ERROR, th, "Breadcrumb data is not serializable.", new Object[0]);
                }
                String str = strB;
                NativeScope nativeScope = dVar.f16848b;
                String str2 = fVar.f17246n;
                String str3 = fVar.f17249q;
                String str4 = fVar.f17247o;
                nativeScope.getClass();
                NativeScope.nativeAddBreadcrumb(lowerCase, str2, str3, str4, strR, str);
                return;
            case 1:
                d dVar2 = (d) this.f16841l;
                String str5 = (String) this.f16842m;
                dVar2.f16848b.getClass();
                NativeScope.nativeRemoveExtra(str5);
                return;
            case 2:
                d dVar3 = (d) this.f16841l;
                i0 i0Var = (i0) this.f16842m;
                NativeScope nativeScope2 = dVar3.f16848b;
                if (i0Var == null) {
                    nativeScope2.getClass();
                    NativeScope.nativeRemoveUser();
                    return;
                }
                String str6 = i0Var.f17598l;
                String str7 = i0Var.f17597i;
                String str8 = i0Var.f17600n;
                String str9 = i0Var.f17599m;
                nativeScope2.getClass();
                NativeScope.nativeSetUser(str6, str7, str8, str9);
                return;
            case 3:
                d dVar4 = (d) this.f16841l;
                l7 l7Var = (l7) this.f16842m;
                NativeScope nativeScope3 = dVar4.f16848b;
                String string = l7Var.f17435i.toString();
                String string2 = l7Var.f17436l.toString();
                nativeScope3.getClass();
                NativeScope.nativeSetTrace(string, string2);
                return;
            case 4:
                File file = (File) this.f16841l;
                io.sentry.android.replay.capture.f fVar2 = (io.sentry.android.replay.capture.f) this.f16842m;
                qb.d.y(file);
                fVar2.f(-1);
                return;
            case 5:
                n nVar = (n) this.f16841l;
                y6 y6Var2 = (y6) this.f16842m;
                try {
                    nVar.run();
                    return;
                } catch (Throwable th2) {
                    y6Var2.getLogger().l(w5.ERROR, "Failed to execute task ReplayIntegration.finalize_previous_replay", th2);
                    return;
                }
            case 6:
                Runnable runnable = (Runnable) this.f16841l;
                io.sentry.android.replay.util.f fVar3 = (io.sentry.android.replay.util.f) this.f16842m;
                try {
                    runnable.run();
                    return;
                } catch (Throwable th3) {
                    ILogger logger = fVar3.f17051l.getLogger();
                    w5 w5Var2 = w5.ERROR;
                    StringBuilder sb2 = new StringBuilder("Failed to execute task ");
                    sb2.append(runnable instanceof g ? ((g) runnable).f17052i : "");
                    logger.l(w5Var2, sb2.toString(), th3);
                    return;
                }
            case 7:
                io.sentry.cache.g gVar = (io.sentry.cache.g) this.f16841l;
                i0 i0Var2 = (i0) this.f16842m;
                if (i0Var2 == null) {
                    gVar.a("user.json");
                    return;
                } else {
                    gVar.f(i0Var2, "user.json");
                    return;
                }
            case 8:
                io.sentry.cache.g gVar2 = (io.sentry.cache.g) this.f16841l;
                try {
                    ((io.sentry.cache.tape.g) gVar2.f17162b.a()).k((io.sentry.f) this.f16842m);
                    return;
                } catch (IOException e5) {
                    gVar2.f17161a.getLogger().l(w5.ERROR, "Failed to add breadcrumb to file queue", e5);
                    return;
                }
            case 9:
                ((io.sentry.cache.g) this.f16841l).f((v) this.f16842m, "replay.json");
                return;
            case 10:
                io.sentry.cache.g gVar3 = (io.sentry.cache.g) this.f16841l;
                String str10 = (String) this.f16842m;
                if (str10 == null) {
                    gVar3.a("transaction.json");
                    return;
                } else {
                    gVar3.f(str10, "transaction.json");
                    return;
                }
            case 11:
                io.sentry.cache.g gVar4 = (io.sentry.cache.g) this.f16841l;
                try {
                    ((Runnable) this.f16842m).run();
                    return;
                } catch (Throwable th4) {
                    gVar4.f17161a.getLogger().l(w5.ERROR, "Serialization task failed", th4);
                    return;
                }
            case 12:
                ((io.sentry.cache.g) this.f16841l).f((io.sentry.protocol.c) this.f16842m, "contexts.json");
                return;
            case 13:
                JobInfoSchedulerService jobInfoSchedulerService = (JobInfoSchedulerService) this.f16841l;
                JobParameters jobParameters = (JobParameters) this.f16842m;
                int i10 = JobInfoSchedulerService.f12608i;
                jobInfoSchedulerService.jobFinished(jobParameters, false);
                return;
            case 14:
                ((io.sentry.internal.debugmeta.c) ((u3) this.f16841l).f6448c).H((l) this.f16842m, 3);
                return;
            case 15:
                ConstraintTrackingWorker constraintTrackingWorker = (ConstraintTrackingWorker) this.f16841l;
                d1 d1Var = (d1) this.f16842m;
                synchronized (constraintTrackingWorker.f7102l) {
                    try {
                        if (constraintTrackingWorker.f7103m) {
                            k kVar = constraintTrackingWorker.f7104n;
                            String str11 = o2.a.f20723a;
                            kVar.h(new p());
                        } else {
                            constraintTrackingWorker.f7104n.j(d1Var);
                        }
                    } catch (Throwable th5) {
                        throw th5;
                    }
                }
                return;
            default:
                ((retrofit2.f) this.f16841l).onFailure((Throwable) this.f16842m);
                return;
        }
    }

    public /* synthetic */ b(Object obj, Object obj2, int i10) {
        this.f16840i = i10;
        this.f16841l = obj;
        this.f16842m = obj2;
    }
}
