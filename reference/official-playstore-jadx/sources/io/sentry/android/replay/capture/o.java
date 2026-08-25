package io.sentry.android.replay.capture;

import androidx.navigation.p;
import io.sentry.android.core.o0;
import io.sentry.android.replay.y;
import io.sentry.c1;
import io.sentry.protocol.v;
import io.sentry.w5;
import io.sentry.y6;
import io.sentry.z6;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: loaded from: classes5.dex */
public final class o extends c {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final y6 f16927r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final c1 f16928s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final io.sentry.transport.f f16929t;

    public o(y6 y6Var, c1 c1Var, io.sentry.transport.f fVar, ScheduledExecutorService scheduledExecutorService) {
        super(y6Var, c1Var, fVar, scheduledExecutorService);
        this.f16927r = y6Var;
        this.f16928s = c1Var;
        this.f16929t = fVar;
    }

    @Override // io.sentry.android.replay.capture.l
    public final void b(final i9.b bVar) {
        final y yVarM = m();
        final long currentTimeMillis = this.f16929t.getCurrentTimeMillis();
        this.f16892d.submit(new io.sentry.android.replay.util.g("SessionCaptureStrategy.add_frame", new Runnable() { // from class: io.sentry.android.replay.capture.m
            @Override // java.lang.Runnable
            public final void run() throws IOException {
                o oVar = this.f16921i;
                io.sentry.android.replay.m mVar = oVar.f16896h;
                y6 y6Var = oVar.f16927r;
                if (mVar != null) {
                    bVar.invoke(mVar, Long.valueOf(currentTimeMillis));
                }
                b bVar2 = oVar.f16898j;
                kotlin.reflect.m mVar2 = c.f16888q[1];
                Date date = (Date) bVar2.f16885b.get();
                if (date == null) {
                    y6Var.getLogger().q(w5.DEBUG, "Segment timestamp is not set, not recording frame", new Object[0]);
                    return;
                }
                if (oVar.f16895g.get()) {
                    y6Var.getLogger().q(w5.DEBUG, "Not capturing segment, because the app is terminating, will be captured on next launch", new Object[0]);
                    return;
                }
                y yVar = yVarM;
                if (yVar == null) {
                    y6Var.getLogger().q(w5.DEBUG, "Recorder config is not set, not capturing a segment", new Object[0]);
                    return;
                }
                long currentTimeMillis2 = oVar.f16929t.getCurrentTimeMillis();
                if (currentTimeMillis2 - date.getTime() >= y6Var.getSessionReplay().f17222s) {
                    k kVarL = c.l(oVar, y6Var.getSessionReplay().f17222s, date, oVar.e(), oVar.h(), yVar.f17090b, yVar.f17089a, yVar.f17093e, yVar.f17094f);
                    if (kVarL instanceof i) {
                        i iVar = (i) kVarL;
                        i.a(iVar, oVar.f16928s);
                        oVar.f(oVar.h() + 1);
                        oVar.k(iVar.f16918a.E);
                    }
                }
                if (currentTimeMillis2 - oVar.k.get() >= y6Var.getSessionReplay().f17223t) {
                    y6Var.getReplayController().stop();
                    y6Var.getLogger().q(w5.INFO, "Session replay deadline exceeded (1h), stopping recording", new Object[0]);
                }
            }
        }));
    }

    @Override // io.sentry.android.replay.capture.l
    public final void c(boolean z, ab.h hVar) {
        y6 y6Var = this.f16927r;
        if (y6Var.getSessionReplay().f17226w) {
            y6Var.getLogger().q(w5.DEBUG, "Replay is already running in 'session' mode, not capturing for event", new Object[0]);
        }
        this.f16895g.set(z);
    }

    @Override // io.sentry.android.replay.capture.l
    public final void g(y yVar) {
        o("onConfigurationChanged", new n(this, 0));
        n(yVar);
    }

    @Override // io.sentry.android.replay.capture.l
    public final l i() {
        return this;
    }

    @Override // io.sentry.android.replay.capture.c, io.sentry.android.replay.capture.l
    public final void j(int i10, v vVar, z6 z6Var) {
        super.j(i10, vVar, z6Var);
        c1 c1Var = this.f16928s;
        if (c1Var != null) {
            c1Var.s(new a3.b(this, 29));
        }
    }

    public final void o(String str, r7.l lVar) {
        y yVarM = m();
        if (yVarM == null) {
            this.f16927r.getLogger().q(w5.DEBUG, "Recorder config is not set, not creating segment for task: ".concat(str), new Object[0]);
            return;
        }
        long currentTimeMillis = this.f16929t.getCurrentTimeMillis();
        kotlin.reflect.m mVar = c.f16888q[1];
        Date date = (Date) this.f16898j.f16885b.get();
        if (date == null) {
            return;
        }
        this.f16892d.submit(new io.sentry.android.replay.util.g("SessionCaptureStrategy.".concat(str), new d(this, currentTimeMillis - date.getTime(), date, e(), yVarM, lVar)));
    }

    @Override // io.sentry.android.replay.capture.l
    public final void pause() {
        o("pause", new n(this, 1));
    }

    @Override // io.sentry.android.replay.capture.l
    public final void stop() throws IllegalAccessException, InvocationTargetException {
        io.sentry.android.replay.m mVar = this.f16896h;
        o("stop", new p(this, mVar != null ? mVar.k() : null, 9));
        c1 c1Var = this.f16928s;
        if (c1Var != null) {
            c1Var.s(new o0(8));
        }
        io.sentry.android.replay.m mVar2 = this.f16896h;
        if (mVar2 != null) {
            mVar2.close();
        }
        this.k.set(0L);
        k(null);
        this.f16900m.setValue(this, c.f16888q[3], v.f17707l);
    }
}
