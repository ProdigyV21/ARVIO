package io.sentry.android.replay.capture;

import a8.g0;
import io.sentry.android.replay.y;
import io.sentry.c1;
import io.sentry.y6;
import io.sentry.z6;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;
import kotlin.jvm.internal.v;
import x6.i0;

/* JADX INFO: loaded from: classes5.dex */
public abstract class c implements l {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final /* synthetic */ kotlin.reflect.m[] f16888q;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y6 f16889a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c1 f16890b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final io.sentry.transport.f f16891c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ScheduledExecutorService f16892d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final i0 f16893e = new i0(new g0(this, 24));

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final io.sentry.android.core.internal.tombstone.b f16894f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final AtomicBoolean f16895g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public io.sentry.android.replay.m f16896h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final b f16897i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final b f16898j;
    public final AtomicLong k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final b f16899l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final b f16900m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final b f16901n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final b f16902o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final ConcurrentLinkedDeque f16903p;

    static {
        v vVar = new v(c.class, "recorderConfig", "getRecorderConfig$sentry_android_replay_release()Lio/sentry/android/replay/ScreenshotRecorderConfig;", 0);
        m0 m0Var = l0.f19747a;
        f16888q = new kotlin.reflect.m[]{m0Var.e(vVar), androidx.compose.material3.d.v(c.class, "segmentTimestamp", "getSegmentTimestamp()Ljava/util/Date;", 0, m0Var), androidx.compose.material3.d.v(c.class, "screenAtStart", "getScreenAtStart()Ljava/lang/String;", 0, m0Var), androidx.compose.material3.d.v(c.class, "currentReplayId", "getCurrentReplayId()Lio/sentry/protocol/SentryId;", 0, m0Var), androidx.compose.material3.d.v(c.class, "currentSegment", "getCurrentSegment()I", 0, m0Var), androidx.compose.material3.d.v(c.class, "replayType", "getReplayType()Lio/sentry/SentryReplayEvent$ReplayType;", 0, m0Var)};
    }

    public c(y6 y6Var, c1 c1Var, io.sentry.transport.f fVar, ScheduledExecutorService scheduledExecutorService) {
        this.f16889a = y6Var;
        this.f16890b = c1Var;
        this.f16891c = fVar;
        this.f16892d = scheduledExecutorService;
        io.sentry.android.core.internal.tombstone.b bVar = new io.sentry.android.core.internal.tombstone.b();
        bVar.f16575c = fVar;
        bVar.f16576d = new LinkedHashMap(10);
        this.f16894f = bVar;
        this.f16895g = new AtomicBoolean(false);
        this.f16897i = new b(this, this, 3);
        this.f16898j = new b(this, this, 4);
        this.k = new AtomicLong();
        this.f16899l = new b(this, this, 5);
        this.f16900m = new b(io.sentry.protocol.v.f17707l, this, this);
        this.f16901n = new b(this, this, 1);
        this.f16902o = new b(this, this, 2);
        this.f16903p = new ConcurrentLinkedDeque();
    }

    public static k l(c cVar, long j10, Date date, io.sentry.protocol.v vVar, int i10, int i11, int i12, int i13, int i14) {
        b bVar = cVar.f16902o;
        kotlin.reflect.m[] mVarArr = f16888q;
        kotlin.reflect.m mVar = mVarArr[5];
        z6 z6Var = (z6) bVar.f16885b.get();
        io.sentry.android.replay.m mVar2 = cVar.f16896h;
        b bVar2 = cVar.f16899l;
        kotlin.reflect.m mVar3 = mVarArr[2];
        return h.a(cVar.f16890b, cVar.f16889a, j10, date, vVar, i10, i11, i12, z6Var, mVar2, i13, i14, (String) bVar2.f16885b.get(), null, cVar.f16903p);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x018e  */
    @Override // io.sentry.android.replay.capture.l
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(android.view.MotionEvent r21) {
        /*
            Method dump skipped, instruction units count: 474
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.replay.capture.c.a(android.view.MotionEvent):void");
    }

    @Override // io.sentry.android.replay.capture.l
    public final void d() {
        k(k2.c.m());
    }

    @Override // io.sentry.android.replay.capture.l
    public final io.sentry.protocol.v e() {
        kotlin.reflect.m mVar = f16888q[3];
        return (io.sentry.protocol.v) this.f16900m.f16885b.get();
    }

    @Override // io.sentry.android.replay.capture.l
    public final void f(int i10) {
        this.f16901n.setValue(this, f16888q[4], Integer.valueOf(i10));
    }

    @Override // io.sentry.android.replay.capture.l
    public final int h() {
        kotlin.reflect.m mVar = f16888q[4];
        return ((Number) this.f16901n.f16885b.get()).intValue();
    }

    @Override // io.sentry.android.replay.capture.l
    public void j(int i10, io.sentry.protocol.v vVar, z6 z6Var) {
        this.f16896h = new io.sentry.android.replay.m(this.f16889a, vVar);
        kotlin.reflect.m[] mVarArr = f16888q;
        this.f16900m.setValue(this, mVarArr[3], vVar);
        f(i10);
        if (z6Var == null) {
            z6Var = this instanceof o ? z6.SESSION : z6.BUFFER;
        }
        this.f16902o.setValue(this, mVarArr[5], z6Var);
        k(k2.c.m());
        this.k.set(this.f16891c.getCurrentTimeMillis());
    }

    @Override // io.sentry.android.replay.capture.l
    public final void k(Date date) {
        this.f16898j.setValue(this, f16888q[1], date);
    }

    public final y m() {
        kotlin.reflect.m mVar = f16888q[0];
        return (y) this.f16897i.f16885b.get();
    }

    public final void n(y yVar) {
        this.f16897i.setValue(this, f16888q[0], yVar);
    }
}
