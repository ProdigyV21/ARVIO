package io.sentry.android.replay.capture;

import android.view.MotionEvent;
import io.sentry.android.replay.y;
import io.sentry.c1;
import io.sentry.j4;
import io.sentry.protocol.v;
import io.sentry.util.p;
import io.sentry.w5;
import io.sentry.y6;
import io.sentry.z6;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class f extends c {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final y6 f16913r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final c1 f16914s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final io.sentry.transport.f f16915t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final p f16916u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final ArrayList f16917v;

    public f(y6 y6Var, j4 j4Var, io.sentry.transport.d dVar, p pVar, io.sentry.android.replay.util.f fVar) {
        super(y6Var, j4Var, dVar, fVar);
        this.f16913r = y6Var;
        this.f16914s = j4Var;
        this.f16915t = dVar;
        this.f16916u = pVar;
        this.f16917v = new ArrayList();
    }

    @Override // io.sentry.android.replay.capture.c, io.sentry.android.replay.capture.l
    public final void a(MotionEvent motionEvent) {
        super.a(motionEvent);
        long currentTimeMillis = this.f16915t.getCurrentTimeMillis() - this.f16913r.getSessionReplay().f17221r;
        Iterator it = this.f16903p.iterator();
        while (it.hasNext()) {
            if (((io.sentry.rrweb.b) it.next()).f17801l < currentTimeMillis) {
                it.remove();
            }
        }
    }

    @Override // io.sentry.android.replay.capture.l
    public final void b(i9.b bVar) {
        this.f16892d.submit(new io.sentry.android.replay.util.g("BufferCaptureStrategy.add_frame", new androidx.media3.exoplayer.video.l(this, bVar, this.f16915t.getCurrentTimeMillis(), 2)));
    }

    @Override // io.sentry.android.replay.capture.l
    public final void c(boolean z, ab.h hVar) throws IllegalAccessException, InvocationTargetException {
        y6 y6Var = this.f16913r;
        Double d4 = y6Var.getSessionReplay().f17218o;
        if (d4 == null || d4.doubleValue() < this.f16916u.c()) {
            y6Var.getLogger().q(w5.INFO, "Replay wasn't sampled by onErrorSampleRate, not capturing for event", new Object[0]);
            return;
        }
        c1 c1Var = this.f16914s;
        if (c1Var != null) {
            c1Var.s(new a3.b(this, 28));
        }
        if (!z) {
            o("capture_replay", new androidx.navigation.p(this, hVar, 7));
        } else {
            this.f16895g.set(true);
            y6Var.getLogger().q(w5.DEBUG, "Not capturing replay for crashed event, will be captured on next launch", new Object[0]);
        }
    }

    @Override // io.sentry.android.replay.capture.l
    public final void g(y yVar) throws IllegalAccessException, InvocationTargetException {
        o("configuration_changed", new e(this, 0));
        n(yVar);
    }

    @Override // io.sentry.android.replay.capture.l
    public final l i() {
        boolean z = this.f16895g.get();
        y6 y6Var = this.f16913r;
        if (z) {
            y6Var.getLogger().q(w5.DEBUG, "Not converting to session mode, because the process is about to terminate", new Object[0]);
            return this;
        }
        o oVar = new o(y6Var, this.f16914s, this.f16915t, this.f16892d);
        oVar.n(m());
        oVar.j(h(), e(), z6.BUFFER);
        return oVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x005e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void o(java.lang.String r11, r7.l r12) throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
            r10 = this;
            io.sentry.android.replay.y r6 = r10.m()
            io.sentry.y6 r0 = r10.f16913r
            if (r6 != 0) goto L1b
            io.sentry.ILogger r12 = r0.getLogger()
            io.sentry.w5 r0 = io.sentry.w5.DEBUG
            java.lang.String r1 = "Recorder config is not set, not creating segment for task: "
            java.lang.String r11 = r1.concat(r11)
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r12.q(r0, r11, r1)
            return
        L1b:
            io.sentry.d7 r0 = r0.getSessionReplay()
            long r0 = r0.f17221r
            io.sentry.transport.f r2 = r10.f16915t
            long r2 = r2.getCurrentTimeMillis()
            io.sentry.android.replay.m r4 = r10.f16896h
            if (r4 == 0) goto L5e
            io.sentry.util.b r5 = r4.f16984p
            io.sentry.util.a r5 = r5.a()
            java.util.ArrayList r4 = r4.f16987s     // Catch: java.lang.Throwable -> L43
            java.lang.Object r4 = kotlin.collections.x.o0(r4)     // Catch: java.lang.Throwable -> L43
            io.sentry.android.replay.n r4 = (io.sentry.android.replay.n) r4     // Catch: java.lang.Throwable -> L43
            r7 = 0
            if (r4 == 0) goto L46
            long r8 = r4.f16991b     // Catch: java.lang.Throwable -> L43
            java.lang.Long r4 = java.lang.Long.valueOf(r8)     // Catch: java.lang.Throwable -> L43
            goto L47
        L43:
            r0 = move-exception
            r11 = r0
            goto L57
        L46:
            r4 = r7
        L47:
            androidx.work.impl.t.h(r5, r7)
            if (r4 == 0) goto L5e
            long r4 = r4.longValue()
            java.util.Date r4 = k2.c.n(r4)
            if (r4 != 0) goto L64
            goto L5e
        L57:
            throw r11     // Catch: java.lang.Throwable -> L58
        L58:
            r0 = move-exception
            r12 = r0
            androidx.work.impl.t.h(r5, r11)
            throw r12
        L5e:
            long r0 = r2 - r0
            java.util.Date r4 = k2.c.n(r0)
        L64:
            long r0 = r4.getTime()
            long r2 = r2 - r0
            io.sentry.protocol.v r5 = r10.e()
            io.sentry.android.replay.util.g r8 = new io.sentry.android.replay.util.g
            java.lang.String r0 = "BufferCaptureStrategy."
            java.lang.String r11 = r0.concat(r11)
            io.sentry.android.replay.capture.d r0 = new io.sentry.android.replay.capture.d
            r1 = r10
            r7 = r12
            r0.<init>(r1, r2, r4, r5, r6, r7)
            r8.<init>(r11, r0)
            java.util.concurrent.ScheduledExecutorService r11 = r1.f16892d
            r11.submit(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.replay.capture.f.o(java.lang.String, r7.l):void");
    }

    @Override // io.sentry.android.replay.capture.l
    public final void pause() throws IllegalAccessException, InvocationTargetException {
        o("pause", new e(this, 1));
    }

    @Override // io.sentry.android.replay.capture.l
    public final void stop() throws IllegalAccessException, InvocationTargetException {
        io.sentry.android.replay.m mVar = this.f16896h;
        this.f16892d.submit(new io.sentry.android.replay.util.g("BufferCaptureStrategy.stop", new io.sentry.android.ndk.b(mVar != null ? mVar.k() : null, this, 4)));
        io.sentry.android.replay.m mVar2 = this.f16896h;
        if (mVar2 != null) {
            mVar2.close();
        }
        this.k.set(0L);
        k(null);
        this.f16900m.setValue(this, c.f16888q[3], v.f17707l);
    }
}
