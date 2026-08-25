package io.sentry.android.replay;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import io.sentry.c7;
import io.sentry.d7;
import io.sentry.g1;
import io.sentry.j4;
import io.sentry.o0;
import io.sentry.s1;
import io.sentry.u5;
import io.sentry.v3;
import io.sentry.w2;
import io.sentry.w3;
import io.sentry.w5;
import io.sentry.y6;
import java.io.Closeable;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.k0;
import x6.i0;

/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\b:\u0002\t\n¨\u0006\u000b"}, d2 = {"Lio/sentry/android/replay/ReplayIntegration;", "Lio/sentry/s1;", "Ljava/io/Closeable;", "Lio/sentry/android/replay/x;", "Lio/sentry/android/replay/gestures/c;", "Lio/sentry/w3;", "Lio/sentry/o0$b;", "Lio/sentry/transport/o;", "Lio/sentry/android/replay/b0;", "io/sentry/android/replay/p", "io/sentry/l0", "sentry-android-replay_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ReplayIntegration implements s1, Closeable, x, io.sentry.android.replay.gestures.c, w3, o0.b, io.sentry.transport.o, b0 {
    public static final /* synthetic */ int B = 0;
    public final r A;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Context f16850i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final io.sentry.transport.d f16851l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public volatile o0.a f16852m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public y6 f16853n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public j4 f16854o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public i f16855p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public io.sentry.android.replay.gestures.b f16856q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final i0 f16857r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final i0 f16858s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final i0 f16859t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final AtomicBoolean f16860u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final AtomicBoolean f16861v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public io.sentry.android.replay.capture.l f16862w;
    public v3 x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final androidx.work.impl.c f16863y;
    public final io.sentry.util.b z;

    static {
        u5.d().b("maven:io.sentry:sentry-android-replay", "8.40.0");
    }

    public ReplayIntegration(Context context) {
        io.sentry.transport.d dVar = io.sentry.transport.d.f17915i;
        Context applicationContext = context.getApplicationContext();
        this.f16850i = applicationContext != null ? applicationContext : context;
        this.f16851l = dVar;
        this.f16852m = o0.a.UNKNOWN;
        this.f16857r = new i0(a.f16865m);
        this.f16858s = new i0(a.f16866n);
        this.f16859t = new i0(new a8.g0(this, 23));
        this.f16860u = new AtomicBoolean(false);
        this.f16861v = new AtomicBoolean(false);
        this.x = w2.f18036a;
        this.f16863y = new androidx.work.impl.c(1);
        this.z = new io.sentry.util.b();
        r rVar = new r();
        rVar.f16996a = s.INITIAL;
        this.A = rVar;
    }

    @Override // io.sentry.w3
    /* JADX INFO: renamed from: G, reason: from getter */
    public final v3 getX() {
        return this.x;
    }

    public final void O(String str) {
        File[] fileArrListFiles;
        y6 y6Var = this.f16853n;
        if (y6Var == null) {
            kotlin.jvm.internal.p.i("options");
            throw null;
        }
        String cacheDirPath = y6Var.getCacheDirPath();
        if (cacheDirPath == null || (fileArrListFiles = new File(cacheDirPath).listFiles()) == null) {
            return;
        }
        for (File file : fileArrListFiles) {
            String name = file.getName();
            if (kotlin.text.u.P(name, "replay_", false) && !kotlin.text.o.T(name, o().toString(), false) && (kotlin.text.o.h0(str) || !kotlin.text.o.T(name, str, false))) {
                qb.d.y(file);
            }
        }
    }

    public final boolean R() {
        return ((s) this.A.f16996a).compareTo(s.STARTED) >= 0 && ((s) this.A.f16996a).compareTo(s.STOPPED) < 0;
    }

    public final void T(Bitmap bitmap) {
        j4 j4Var;
        j4 j4Var2;
        io.sentry.transport.p pVarF;
        io.sentry.transport.p pVarF2;
        k0 k0Var = new k0();
        j4 j4Var3 = this.f16854o;
        if (j4Var3 != null) {
            j4Var3.o(new o(k0Var, 0));
        }
        io.sentry.android.replay.capture.l lVar = this.f16862w;
        if (lVar != null) {
            lVar.b(new i9.b(bitmap, k0Var, 1));
        }
        if (this.f16862w instanceof io.sentry.android.replay.capture.o) {
            if (this.f16852m == o0.a.DISCONNECTED || !(((j4Var = this.f16854o) == null || (pVarF2 = j4Var.f()) == null || !pVarF2.j(io.sentry.m.All)) && ((j4Var2 = this.f16854o) == null || (pVarF = j4Var2.f()) == null || !pVarF.j(io.sentry.m.Replay)))) {
                b0();
            }
        }
    }

    public final void V(int i10, int i11) {
        i iVar;
        if (this.f16860u.get() && R()) {
            y6 y6Var = this.f16853n;
            if (y6Var == null) {
                kotlin.jvm.internal.p.i("options");
                throw null;
            }
            if (y6Var.getSessionReplay().f17224u) {
                Context context = this.f16850i;
                y6 y6Var2 = this.f16853n;
                if (y6Var2 == null) {
                    kotlin.jvm.internal.p.i("options");
                    throw null;
                }
                d7 sessionReplay = y6Var2.getSessionReplay();
                float f10 = i11;
                float f11 = f10 / context.getResources().getDisplayMetrics().density;
                c7 c7Var = sessionReplay.f17219p;
                int iM = t7.a.M(f11 * c7Var.sizeScale);
                int i12 = iM % 16;
                int iMax = i12 <= 8 ? Math.max(16, iM - i12) : iM + (16 - i12);
                float f12 = i10;
                int iM2 = t7.a.M((f12 / context.getResources().getDisplayMetrics().density) * c7Var.sizeScale);
                int i13 = iM2 % 16;
                int iMax2 = i13 <= 8 ? Math.max(16, iM2 - i13) : iM2 + (16 - i13);
                y yVar = new y(iMax2, iMax, iMax2 / f12, iMax / f10, sessionReplay.f17220q, c7Var.bitRate);
                if (this.f16860u.get() && R()) {
                    io.sentry.android.replay.capture.l lVar = this.f16862w;
                    if (lVar != null) {
                        lVar.g(yVar);
                    }
                    i iVar2 = this.f16855p;
                    if (iVar2 != null) {
                        iVar2.g(yVar);
                    }
                    if (((s) this.A.f16996a) != s.PAUSED || (iVar = this.f16855p) == null) {
                        return;
                    }
                    iVar.pause();
                }
            }
        }
    }

    public final void b0() throws IllegalAccessException, InvocationTargetException {
        io.sentry.util.a aVarA = this.z.a();
        try {
            if (this.f16860u.get()) {
                r rVar = this.A;
                s sVar = s.PAUSED;
                if (rVar.a(sVar)) {
                    i iVar = this.f16855p;
                    if (iVar != null) {
                        iVar.pause();
                    }
                    io.sentry.android.replay.capture.l lVar = this.f16862w;
                    if (lVar != null) {
                        lVar.pause();
                    }
                    this.A.f16996a = sVar;
                    androidx.work.impl.t.h(aVarA, null);
                    return;
                }
            }
            androidx.work.impl.t.h(aVarA, null);
        } finally {
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IllegalAccessException, InvocationTargetException {
        io.sentry.transport.p pVarF;
        io.sentry.util.a aVarA = this.z.a();
        try {
            if (this.f16860u.get()) {
                r rVar = this.A;
                s sVar = s.CLOSED;
                if (rVar.a(sVar)) {
                    y6 y6Var = this.f16853n;
                    if (y6Var == null) {
                        kotlin.jvm.internal.p.i("options");
                        throw null;
                    }
                    y6Var.getConnectionStatusProvider().G0(this);
                    j4 j4Var = this.f16854o;
                    if (j4Var != null && (pVarF = j4Var.f()) != null) {
                        pVarF.f17934n.remove(this);
                    }
                    stop();
                    i iVar = this.f16855p;
                    if (iVar != null) {
                        iVar.close();
                    }
                    this.f16855p = null;
                    ((u) this.f16858s.getValue()).close();
                    ((io.sentry.android.replay.util.f) this.f16859t.getValue()).shutdown();
                    this.A.f16996a = sVar;
                    androidx.work.impl.t.h(aVarA, null);
                    return;
                }
            }
            androidx.work.impl.t.h(aVarA, null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                androidx.work.impl.t.h(aVarA, th);
                throw th2;
            }
        }
    }

    @Override // io.sentry.w3
    public final void d() throws IllegalAccessException, InvocationTargetException {
        this.f16861v.set(false);
        q0();
    }

    @Override // io.sentry.o0.b
    public final void i(o0.a aVar) throws IllegalAccessException, InvocationTargetException {
        this.f16852m = aVar;
        if (this.f16862w instanceof io.sentry.android.replay.capture.o) {
            if (aVar == o0.a.DISCONNECTED) {
                b0();
            } else {
                q0();
            }
        }
    }

    @Override // io.sentry.w3
    public final void j(d dVar) {
        this.x = dVar;
    }

    @Override // io.sentry.transport.o
    public final void k(io.sentry.transport.p pVar) throws IllegalAccessException, InvocationTargetException {
        if (this.f16862w instanceof io.sentry.android.replay.capture.o) {
            if (pVar.j(io.sentry.m.All) || pVar.j(io.sentry.m.Replay)) {
                b0();
            } else {
                q0();
            }
        }
    }

    @Override // io.sentry.s1
    public final void l(y6 y6Var) {
        Double d4;
        this.f16853n = y6Var;
        if (Build.VERSION.SDK_INT < 26) {
            y6Var.getLogger().q(w5.INFO, "Session replay is only supported on API 26 and above", new Object[0]);
            return;
        }
        Double d10 = y6Var.getSessionReplay().f17217n;
        if ((d10 == null || d10.doubleValue() <= 0.0d) && ((d4 = y6Var.getSessionReplay().f17218o) == null || d4.doubleValue() <= 0.0d)) {
            y6Var.getLogger().q(w5.INFO, "Session replay is disabled, no sample rate specified", new Object[0]);
            return;
        }
        j4 j4Var = j4.f17401a;
        this.f16854o = j4Var;
        this.f16855p = new f0(y6Var, this, this, this.f16863y, (io.sentry.android.replay.util.f) this.f16859t.getValue());
        this.f16856q = new io.sentry.android.replay.gestures.b(y6Var, this);
        this.f16860u.set(true);
        y6Var.getConnectionStatusProvider().y0(this);
        io.sentry.transport.p pVarF = j4Var.f();
        if (pVarF != null) {
            pVarF.f17934n.add(this);
        }
        xc.d.H("Replay");
        y6 y6Var2 = this.f16853n;
        if (y6Var2 == null) {
            kotlin.jvm.internal.p.i("options");
            throw null;
        }
        g1 executorService = y6Var2.getExecutorService();
        y6 y6Var3 = this.f16853n;
        if (y6Var3 == null) {
            kotlin.jvm.internal.p.i("options");
            throw null;
        }
        try {
            executorService.submit(new io.sentry.android.ndk.b(new androidx.activity.n(this, 23), y6Var3, 5));
        } catch (Throwable th) {
            y6Var3.getLogger().l(w5.ERROR, "Failed to submit task ReplayIntegration.finalize_previous_replay to executor", th);
        }
    }

    @Override // io.sentry.w3
    public final io.sentry.protocol.v o() {
        io.sentry.protocol.v vVarE;
        io.sentry.android.replay.capture.l lVar = this.f16862w;
        return (lVar == null || (vVarE = lVar.e()) == null) ? io.sentry.protocol.v.f17707l : vVarE;
    }

    @Override // io.sentry.w3
    public final void pause() throws IllegalAccessException, InvocationTargetException {
        this.f16861v.set(true);
        b0();
    }

    public final void q0() throws IllegalAccessException, InvocationTargetException {
        j4 j4Var;
        j4 j4Var2;
        io.sentry.transport.p pVarF;
        io.sentry.transport.p pVarF2;
        io.sentry.util.a aVarA = this.z.a();
        try {
            if (this.f16860u.get()) {
                r rVar = this.A;
                s sVar = s.RESUMED;
                if (rVar.a(sVar)) {
                    if (!this.f16861v.get() && this.f16852m != o0.a.DISCONNECTED && (((j4Var = this.f16854o) == null || (pVarF2 = j4Var.f()) == null || !pVarF2.j(io.sentry.m.All)) && ((j4Var2 = this.f16854o) == null || (pVarF = j4Var2.f()) == null || !pVarF.j(io.sentry.m.Replay)))) {
                        this.A.f16996a = sVar;
                        io.sentry.android.replay.capture.l lVar = this.f16862w;
                        if (lVar != null) {
                            lVar.d();
                        }
                        i iVar = this.f16855p;
                        if (iVar != null) {
                            iVar.d();
                        }
                        androidx.work.impl.t.h(aVarA, null);
                        return;
                    }
                    androidx.work.impl.t.h(aVarA, null);
                    return;
                }
            }
            androidx.work.impl.t.h(aVarA, null);
        } finally {
        }
    }

    @Override // io.sentry.w3
    public final void stop() throws IllegalAccessException, InvocationTargetException {
        io.sentry.util.a aVarA = this.z.a();
        try {
            if (this.f16860u.get()) {
                r rVar = this.A;
                s sVar = s.STOPPED;
                if (rVar.a(sVar)) {
                    if (this.f16855p instanceof h) {
                        ((u) this.f16858s.getValue()).f17034m.remove((h) this.f16855p);
                    }
                    ((u) this.f16858s.getValue()).f17034m.remove(this.f16856q);
                    i iVar = this.f16855p;
                    if (iVar != null) {
                        iVar.reset();
                    }
                    i iVar2 = this.f16855p;
                    if (iVar2 != null) {
                        iVar2.stop();
                    }
                    io.sentry.android.replay.gestures.b bVar = this.f16856q;
                    if (bVar != null) {
                        bVar.b();
                    }
                    io.sentry.android.replay.capture.l lVar = this.f16862w;
                    if (lVar != null) {
                        lVar.stop();
                    }
                    this.f16862w = null;
                    this.A.f16996a = sVar;
                    androidx.work.impl.t.h(aVarA, null);
                    return;
                }
            }
            androidx.work.impl.t.h(aVarA, null);
        } finally {
        }
    }

    @Override // io.sentry.w3
    public final void x(Boolean bool) {
        if (this.f16860u.get() && R()) {
            io.sentry.protocol.v vVar = io.sentry.protocol.v.f17707l;
            io.sentry.android.replay.capture.l lVar = this.f16862w;
            if (vVar.equals(lVar != null ? lVar.e() : null)) {
                y6 y6Var = this.f16853n;
                if (y6Var != null) {
                    y6Var.getLogger().q(w5.DEBUG, "Replay id is not set, not capturing for event", new Object[0]);
                    return;
                } else {
                    kotlin.jvm.internal.p.i("options");
                    throw null;
                }
            }
            io.sentry.android.replay.capture.l lVar2 = this.f16862w;
            if (lVar2 != null) {
                lVar2.c(bool.equals(Boolean.TRUE), new ab.h(this, 17));
            }
            io.sentry.android.replay.capture.l lVar3 = this.f16862w;
            this.f16862w = lVar3 != null ? lVar3.i() : null;
        }
    }

    @Override // io.sentry.w3
    public final void y() throws IllegalAccessException, InvocationTargetException {
        io.sentry.android.replay.capture.l fVar;
        io.sentry.util.a aVarA = this.z.a();
        try {
            if (!this.f16860u.get()) {
                androidx.work.impl.t.h(aVarA, null);
                return;
            }
            r rVar = this.A;
            s sVar = s.STARTED;
            if (!rVar.a(sVar)) {
                y6 y6Var = this.f16853n;
                if (y6Var == null) {
                    kotlin.jvm.internal.p.i("options");
                    throw null;
                }
                y6Var.getLogger().q(w5.DEBUG, "Session replay is already being recorded, not starting a new one", new Object[0]);
                androidx.work.impl.t.h(aVarA, null);
                return;
            }
            io.sentry.util.p pVar = (io.sentry.util.p) this.f16857r.getValue();
            y6 y6Var2 = this.f16853n;
            if (y6Var2 == null) {
                kotlin.jvm.internal.p.i("options");
                throw null;
            }
            Double d4 = y6Var2.getSessionReplay().f17217n;
            boolean z = true;
            boolean z5 = d4 != null && d4.doubleValue() >= pVar.c();
            if (!z5) {
                y6 y6Var3 = this.f16853n;
                if (y6Var3 == null) {
                    kotlin.jvm.internal.p.i("options");
                    throw null;
                }
                Double d10 = y6Var3.getSessionReplay().f17218o;
                if (d10 == null || d10.doubleValue() <= 0.0d) {
                    z = false;
                }
                if (!z) {
                    y6 y6Var4 = this.f16853n;
                    if (y6Var4 == null) {
                        kotlin.jvm.internal.p.i("options");
                        throw null;
                    }
                    y6Var4.getLogger().q(w5.INFO, "Session replay is not started, full session was not sampled and onErrorSampleRate is not specified", new Object[0]);
                    androidx.work.impl.t.h(aVarA, null);
                    return;
                }
            }
            this.A.f16996a = sVar;
            if (z5) {
                y6 y6Var5 = this.f16853n;
                if (y6Var5 == null) {
                    kotlin.jvm.internal.p.i("options");
                    throw null;
                }
                fVar = new io.sentry.android.replay.capture.o(y6Var5, this.f16854o, this.f16851l, (io.sentry.android.replay.util.f) this.f16859t.getValue());
            } else {
                y6 y6Var6 = this.f16853n;
                if (y6Var6 == null) {
                    kotlin.jvm.internal.p.i("options");
                    throw null;
                }
                fVar = new io.sentry.android.replay.capture.f(y6Var6, this.f16854o, this.f16851l, (io.sentry.util.p) this.f16857r.getValue(), (io.sentry.android.replay.util.f) this.f16859t.getValue());
            }
            this.f16862w = fVar;
            i iVar = this.f16855p;
            if (iVar != null) {
                iVar.start();
            }
            io.sentry.android.replay.capture.l lVar = this.f16862w;
            if (lVar != null) {
                lVar.j(0, new io.sentry.protocol.v(), null);
            }
            if (this.f16855p instanceof h) {
                ((u) this.f16858s.getValue()).f17034m.add((h) this.f16855p);
            }
            ((u) this.f16858s.getValue()).f17034m.add(this.f16856q);
            androidx.work.impl.t.h(aVarA, null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                androidx.work.impl.t.h(aVarA, th);
                throw th2;
            }
        }
    }
}
