package io.sentry;

import java.io.File;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class q4 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f17761i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f17762l;

    public /* synthetic */ q4(Object obj, int i10) {
        this.f17761i = i10;
        this.f17762l = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f17761i) {
            case 0:
                File[] fileArrListFiles = ((File) this.f17762l).listFiles();
                if (fileArrListFiles != null) {
                    for (File file : fileArrListFiles) {
                        if (file.lastModified() < s4.f17854f - TimeUnit.MINUTES.toMillis(5L)) {
                            qb.d.y(file);
                        }
                    }
                    break;
                }
                break;
            default:
                n5 n5Var = (n5) this.f17762l;
                ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = n5Var.f17488a;
                for (int i10 = 0; i10 < 40; i10++) {
                    try {
                        scheduledThreadPoolExecutor.schedule(n5Var.f17490c, 365L, TimeUnit.DAYS).cancel(true);
                    } catch (RejectedExecutionException unused) {
                        return;
                    }
                }
                scheduledThreadPoolExecutor.purge();
                break;
        }
    }
}
