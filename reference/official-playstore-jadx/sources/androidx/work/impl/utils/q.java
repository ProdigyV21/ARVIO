package androidx.work.impl.utils;

import android.content.Context;
import io.sentry.ILogger;
import io.sentry.android.replay.y;
import io.sentry.w5;
import io.sentry.y6;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes3.dex */
public final class q implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7062i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f7063l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f7064m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f7065n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Object f7066o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Object f7067p;

    public q(y6 y6Var, androidx.work.impl.c cVar) {
        this.f7062i = 1;
        this.f7063l = y6Var;
        this.f7064m = cVar;
        this.f7067p = new AtomicBoolean(true);
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f7062i) {
            case 0:
                try {
                    if (!(((androidx.work.impl.utils.futures.k) this.f7063l).f7037i instanceof androidx.work.impl.utils.futures.a)) {
                        String string = ((UUID) this.f7064m).toString();
                        androidx.work.impl.model.p pVarK = ((r) this.f7067p).f7070c.k(string);
                        if (pVarK == null || g8.b.b(pVarK.f6923b)) {
                            throw new IllegalStateException("Calls to setForegroundAsync() must complete before a ListenableWorker signals completion of work by returning an instance of Result.");
                        }
                        ((r) this.f7067p).f7069b.a(string, (m2.j) this.f7065n);
                        ((Context) this.f7066o).startService(androidx.work.impl.foreground.c.a((Context) this.f7066o, a.a.N(pVarK), (m2.j) this.f7065n));
                    }
                    ((androidx.work.impl.utils.futures.k) this.f7063l).h(null);
                    return;
                } catch (Throwable th) {
                    ((androidx.work.impl.utils.futures.k) this.f7063l).i(th);
                    return;
                }
            default:
                y6 y6Var = (y6) this.f7063l;
                if (!((AtomicBoolean) this.f7067p).get()) {
                    if (y6Var.getSessionReplay().f17226w) {
                        y6Var.getLogger().q(w5.DEBUG, "Not capturing frames, recording is not running.", new Object[0]);
                        return;
                    }
                    return;
                }
                try {
                    if (y6Var.getSessionReplay().f17226w) {
                        y6Var.getLogger().q(w5.DEBUG, "Capturing a frame.", new Object[0]);
                    }
                    io.sentry.android.replay.w wVar = (io.sentry.android.replay.w) this.f7065n;
                    if (wVar != null) {
                        wVar.b();
                    }
                    break;
                } catch (Throwable th2) {
                    y6Var.getLogger().l(w5.ERROR, "Failed to capture a frame", th2);
                }
                if (y6Var.getSessionReplay().f17226w) {
                    ILogger logger = y6Var.getLogger();
                    w5 w5Var = w5.DEBUG;
                    StringBuilder sb2 = new StringBuilder("Posting the capture runnable again, frame rate is ");
                    y yVar = (y) this.f7066o;
                    logger.q(w5Var, androidx.compose.material3.d.j(yVar != null ? yVar.f17093e : 1, " fps.", sb2), new Object[0]);
                }
                androidx.work.impl.c cVar = (androidx.work.impl.c) this.f7064m;
                y yVar2 = (y) this.f7066o;
                if (cVar.f6788a.postDelayed(this, 1000 / ((long) (yVar2 != null ? yVar2.f17093e : 1)))) {
                    return;
                }
                y6Var.getLogger().q(w5.WARNING, "Failed to post the capture runnable, main looper is shutting down.", new Object[0]);
                return;
        }
    }

    public q(r rVar, androidx.work.impl.utils.futures.k kVar, UUID uuid, m2.j jVar, Context context) {
        this.f7062i = 0;
        this.f7067p = rVar;
        this.f7063l = kVar;
        this.f7064m = uuid;
        this.f7065n = jVar;
        this.f7066o = context;
    }
}
