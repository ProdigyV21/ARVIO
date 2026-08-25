package com.google.firebase.crashlytics.internal.send;

import android.database.SQLException;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.datatransport.runtime.u;
import com.google.android.datatransport.runtime.w;
import com.google.android.gms.tasks.i;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CrashlyticsReportWithSessionId;
import com.google.firebase.crashlytics.internal.common.OnDemandCounter;
import com.google.firebase.crashlytics.internal.common.Utils;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.settings.Settings;
import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import z2.c;
import z2.e;
import z2.g;

/* JADX INFO: loaded from: classes4.dex */
final class ReportQueue {
    private static final int MAX_DELAY_MS = 3600000;
    private static final int MS_PER_MINUTE = 60000;
    private static final int MS_PER_SECOND = 1000;
    private static final int STARTUP_DURATION_MS = 2000;
    private final double base;
    private long lastUpdatedMs;
    private final OnDemandCounter onDemandCounter;
    private final BlockingQueue<Runnable> queue;
    private final int queueCapacity;
    private final double ratePerMinute;
    private final ThreadPoolExecutor singleThreadExecutor;
    private final long startTimeMs;
    private int step;
    private final long stepDurationMs;
    private final e<CrashlyticsReport> transport;

    public final class ReportRunnable implements Runnable {
        private final CrashlyticsReportWithSessionId reportWithSessionId;
        private final i tcs;

        @Override // java.lang.Runnable
        public void run() {
            ReportQueue.this.sendReport(this.reportWithSessionId, this.tcs);
            ReportQueue.this.onDemandCounter.resetDroppedOnDemandExceptions();
            double dCalcDelay = ReportQueue.this.calcDelay();
            Logger.getLogger().d("Delay for: " + String.format(Locale.US, "%.2f", Double.valueOf(dCalcDelay / 1000.0d)) + " s for report: " + this.reportWithSessionId.getSessionId());
            ReportQueue.sleep(dCalcDelay);
        }

        private ReportRunnable(CrashlyticsReportWithSessionId crashlyticsReportWithSessionId, i iVar) {
            this.reportWithSessionId = crashlyticsReportWithSessionId;
            this.tcs = iVar;
        }
    }

    public ReportQueue(e<CrashlyticsReport> eVar, Settings settings, OnDemandCounter onDemandCounter) {
        this(settings.onDemandUploadRatePerMinute, settings.onDemandBackoffBase, ((long) settings.onDemandBackoffStepDurationSeconds) * 1000, eVar, onDemandCounter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public double calcDelay() {
        return Math.min(3600000.0d, Math.pow(this.base, calcStep()) * (60000.0d / this.ratePerMinute));
    }

    private int calcStep() {
        if (this.lastUpdatedMs == 0) {
            this.lastUpdatedMs = now();
        }
        int iNow = (int) ((now() - this.lastUpdatedMs) / this.stepDurationMs);
        int iMin = isQueueFull() ? Math.min(100, this.step + iNow) : Math.max(0, this.step - iNow);
        if (this.step != iMin) {
            this.step = iMin;
            this.lastUpdatedMs = now();
        }
        return iMin;
    }

    private boolean isQueueAvailable() {
        return this.queue.size() < this.queueCapacity;
    }

    private boolean isQueueFull() {
        return this.queue.size() == this.queueCapacity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$flushScheduledReportsIfAble$0(CountDownLatch countDownLatch) {
        try {
            e<CrashlyticsReport> eVar = this.transport;
            c cVar = c.f23166m;
            if (eVar instanceof u) {
                w.a().f12658d.a(((u) eVar).f12649a.a(cVar), 1);
            } else {
                String strI = ac.b.I("ForcedSender");
                if (Log.isLoggable(strI, 5)) {
                    Log.w(strI, String.format("Expected instance of `TransportImpl`, got `%s`.", eVar));
                }
            }
        } catch (SQLException unused) {
        }
        countDownLatch.countDown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$sendReport$1(i iVar, boolean z, CrashlyticsReportWithSessionId crashlyticsReportWithSessionId, Exception exc) {
        if (exc != null) {
            iVar.c(exc);
            return;
        }
        if (z) {
            flushScheduledReportsIfAble();
        }
        iVar.d(crashlyticsReportWithSessionId);
    }

    private long now() {
        return System.currentTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendReport(final CrashlyticsReportWithSessionId crashlyticsReportWithSessionId, final i iVar) {
        Logger.getLogger().d("Sending report through Google DataTransport: " + crashlyticsReportWithSessionId.getSessionId());
        final boolean z = SystemClock.elapsedRealtime() - this.startTimeMs < 2000;
        this.transport.b(new z2.a(null, crashlyticsReportWithSessionId.getReport(), c.f23166m), new g() { // from class: com.google.firebase.crashlytics.internal.send.b
            @Override // z2.g
            public final void a(Exception exc) {
                this.f14363i.lambda$sendReport$1(iVar, z, crashlyticsReportWithSessionId, exc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void sleep(double d4) {
        try {
            Thread.sleep((long) d4);
        } catch (InterruptedException unused) {
        }
    }

    public i enqueueReport(CrashlyticsReportWithSessionId crashlyticsReportWithSessionId, boolean z) {
        synchronized (this.queue) {
            try {
                i iVar = new i();
                if (!z) {
                    sendReport(crashlyticsReportWithSessionId, iVar);
                    return iVar;
                }
                this.onDemandCounter.incrementRecordedOnDemandExceptions();
                if (!isQueueAvailable()) {
                    calcStep();
                    Logger.getLogger().d("Dropping report due to queue being full: " + crashlyticsReportWithSessionId.getSessionId());
                    this.onDemandCounter.incrementDroppedOnDemandExceptions();
                    iVar.d(crashlyticsReportWithSessionId);
                    return iVar;
                }
                Logger.getLogger().d("Enqueueing report: " + crashlyticsReportWithSessionId.getSessionId());
                Logger.getLogger().d("Queue size: " + this.queue.size());
                this.singleThreadExecutor.execute(new ReportRunnable(crashlyticsReportWithSessionId, iVar));
                Logger.getLogger().d("Closing task for report: " + crashlyticsReportWithSessionId.getSessionId());
                iVar.d(crashlyticsReportWithSessionId);
                return iVar;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void flushScheduledReportsIfAble() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new Runnable() { // from class: com.google.firebase.crashlytics.internal.send.a
            @Override // java.lang.Runnable
            public final void run() {
                this.f14361i.lambda$flushScheduledReportsIfAble$0(countDownLatch);
            }
        }).start();
        Utils.awaitUninterruptibly(countDownLatch, 2L, TimeUnit.SECONDS);
    }

    public ReportQueue(double d4, double d10, long j10, e<CrashlyticsReport> eVar, OnDemandCounter onDemandCounter) {
        this.ratePerMinute = d4;
        this.base = d10;
        this.stepDurationMs = j10;
        this.transport = eVar;
        this.onDemandCounter = onDemandCounter;
        this.startTimeMs = SystemClock.elapsedRealtime();
        int i10 = (int) d4;
        this.queueCapacity = i10;
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(i10);
        this.queue = arrayBlockingQueue;
        this.singleThreadExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, arrayBlockingQueue);
        this.step = 0;
        this.lastUpdatedMs = 0L;
    }
}
