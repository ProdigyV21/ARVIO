package io.sentry.android.core;

import androidx.media3.exoplayer.offline.DownloadService;
import io.sentry.j4;
import io.sentry.w5;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes5.dex */
public final class a1 implements e0 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final long f16397l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public io.sentry.o f16398m;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f16402q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final boolean f16403r;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final AtomicLong f16396i = new AtomicLong(0);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final io.sentry.util.k f16399n = new io.sentry.util.k(new o0(5));

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final io.sentry.util.b f16400o = new io.sentry.util.b();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final j4 f16401p = j4.f17401a;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final io.sentry.transport.d f16404s = io.sentry.transport.d.f17915i;

    public a1(long j10, boolean z, boolean z5) {
        this.f16397l = j10;
        this.f16402q = z;
        this.f16403r = z5;
    }

    public final void a(String str) {
        if (this.f16403r) {
            io.sentry.f fVar = new io.sentry.f();
            fVar.f17247o = "navigation";
            fVar.f(str, "state");
            fVar.f17249q = "app.lifecycle";
            fVar.f17251s = w5.INFO;
            this.f16401p.k(fVar);
        }
    }

    public final void b() {
        io.sentry.util.a aVarA = this.f16400o.a();
        try {
            io.sentry.o oVar = this.f16398m;
            if (oVar != null) {
                oVar.cancel();
                this.f16398m = null;
            }
            aVarA.close();
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // io.sentry.android.core.e0
    public final void i() {
        b();
        this.f16404s.getClass();
        long jCurrentTimeMillis = System.currentTimeMillis();
        a3.b bVar = new a3.b(this, 25);
        j4 j4Var = this.f16401p;
        j4Var.o(bVar);
        AtomicLong atomicLong = this.f16396i;
        long j10 = atomicLong.get();
        if (j10 == 0 || j10 + this.f16397l <= jCurrentTimeMillis) {
            if (this.f16402q) {
                j4Var.q();
            }
            j4Var.getOptions().getReplayController().y();
        }
        j4Var.getOptions().getReplayController().d();
        atomicLong.set(jCurrentTimeMillis);
        a(DownloadService.KEY_FOREGROUND);
    }

    @Override // io.sentry.android.core.e0
    public final void j() {
        this.f16404s.getClass();
        this.f16396i.set(System.currentTimeMillis());
        this.f16401p.getOptions().getReplayController().pause();
        io.sentry.util.a aVarA = this.f16400o.a();
        try {
            b();
            this.f16398m = new io.sentry.o(this, 1);
            ((Timer) this.f16399n.a()).schedule(this.f16398m, this.f16397l);
            aVarA.close();
            a("background");
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }
}
