package io.sentry.android.core;

import android.app.ActivityManager;
import android.app.ApplicationExitInfo;
import android.content.Context;
import io.sentry.j4;
import io.sentry.j5;
import io.sentry.w5;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public final class n0 implements Runnable {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final long f16670p = TimeUnit.DAYS.toMillis(91);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Context f16671i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final j4 f16672l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final SentryAndroidOptions f16673m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final l0 f16674n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final long f16675o;

    public n0(Context context, SentryAndroidOptions sentryAndroidOptions, io.sentry.transport.d dVar, l0 l0Var) {
        Context applicationContext = context.getApplicationContext();
        this.f16671i = applicationContext != null ? applicationContext : context;
        this.f16672l = j4.f17401a;
        this.f16673m = sentryAndroidOptions;
        this.f16674n = l0Var;
        dVar.getClass();
        this.f16675o = System.currentTimeMillis() - f16670p;
    }

    public final void a(ApplicationExitInfo applicationExitInfo, boolean z) {
        l0 l0Var = this.f16674n;
        m0 m0VarD = l0Var.d(applicationExitInfo, z);
        if (m0VarD == null) {
            return;
        }
        j5 j5Var = m0VarD.f16667a;
        if (this.f16672l.t(j5Var, (io.sentry.j0) m0VarD.f16668b).equals(io.sentry.protocol.v.f17707l) || ((io.sentry.hints.d) m0VarD.f16669c).g()) {
            return;
        }
        this.f16673m.getLogger().q(w5.WARNING, "Timed out waiting to flush %s event to disk. Event: %s", l0Var.b(), j5Var.f17984i);
    }

    @Override // java.lang.Runnable
    public final void run() {
        ActivityManager activityManager = (ActivityManager) this.f16671i.getSystemService("activity");
        SentryAndroidOptions sentryAndroidOptions = this.f16673m;
        if (activityManager == null) {
            sentryAndroidOptions.getLogger().q(w5.ERROR, "Failed to retrieve ActivityManager.", new Object[0]);
            return;
        }
        ApplicationExitInfo applicationExitInfo = null;
        List<ApplicationExitInfo> historicalProcessExitReasons = activityManager.getHistoricalProcessExitReasons(null, 0, 0);
        if (historicalProcessExitReasons.isEmpty()) {
            sentryAndroidOptions.getLogger().q(w5.DEBUG, "No records in historical exit reasons.", new Object[0]);
            return;
        }
        io.sentry.cache.d envelopeDiskCache = sentryAndroidOptions.getEnvelopeDiskCache();
        if ((envelopeDiskCache instanceof io.sentry.cache.c) && sentryAndroidOptions.isEnableAutoSessionTracking()) {
            io.sentry.cache.c cVar = (io.sentry.cache.c) envelopeDiskCache;
            if (!cVar.g()) {
                sentryAndroidOptions.getLogger().q(w5.WARNING, "Timed out waiting to flush previous session to its own file.", new Object[0]);
                cVar.f17152o.countDown();
            }
        }
        ArrayList arrayList = new ArrayList(historicalProcessExitReasons);
        l0 l0Var = this.f16674n;
        Long lA = l0Var.a();
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ApplicationExitInfo applicationExitInfoB = androidx.media3.extractor.mp4.b.b(it.next());
            if (applicationExitInfoB.getReason() == l0Var.e()) {
                it.remove();
                applicationExitInfo = applicationExitInfoB;
                break;
            }
        }
        if (applicationExitInfo == null) {
            sentryAndroidOptions.getLogger().q(w5.DEBUG, "No %ss have been found in the historical exit reasons list.", l0Var.b());
            return;
        }
        long timestamp = applicationExitInfo.getTimestamp();
        long j10 = this.f16675o;
        if (timestamp < j10) {
            sentryAndroidOptions.getLogger().q(w5.DEBUG, "Latest %s happened too long ago, returning early.", l0Var.b());
            return;
        }
        if (lA != null && applicationExitInfo.getTimestamp() <= lA.longValue()) {
            sentryAndroidOptions.getLogger().q(w5.DEBUG, "Latest %s has already been reported, returning early.", l0Var.b());
            return;
        }
        if (l0Var.c()) {
            Collections.reverse(arrayList);
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                ApplicationExitInfo applicationExitInfoB2 = androidx.media3.extractor.mp4.b.b(it2.next());
                if (applicationExitInfoB2.getReason() == l0Var.e()) {
                    if (applicationExitInfoB2.getTimestamp() < j10) {
                        sentryAndroidOptions.getLogger().q(w5.DEBUG, "%s happened too long ago %s.", l0Var.b(), applicationExitInfoB2);
                    } else if (lA == null || applicationExitInfoB2.getTimestamp() > lA.longValue()) {
                        a(applicationExitInfoB2, false);
                    } else {
                        sentryAndroidOptions.getLogger().q(w5.DEBUG, "%s has already been reported %s.", l0Var.b(), applicationExitInfoB2);
                    }
                }
            }
        }
        a(applicationExitInfo, true);
    }
}
