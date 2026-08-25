package io.sentry.android.core;

import android.os.SystemClock;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public final class s implements io.sentry.android.core.internal.util.o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f16736a = 0.0f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ u f16737b;

    public s(u uVar) {
        this.f16737b = uVar;
    }

    @Override // io.sentry.android.core.internal.util.o
    public final void e(long j10, long j11, long j12, long j13, boolean z, boolean z5, float f10) {
        Date dateM = k2.c.m();
        System.nanoTime();
        long time = dateM.getTime() * 1000000;
        long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos() + (j11 - System.nanoTime());
        u uVar = this.f16737b;
        long j14 = jElapsedRealtimeNanos - uVar.f16756a;
        if (j14 < 0) {
            return;
        }
        if (z5) {
            uVar.f16765j.addLast(new io.sentry.profilemeasurements.b(Long.valueOf(j14), Long.valueOf(j12), time));
        } else if (z) {
            uVar.f16764i.addLast(new io.sentry.profilemeasurements.b(Long.valueOf(j14), Long.valueOf(j12), time));
        }
        if (f10 != this.f16736a) {
            this.f16736a = f10;
            uVar.f16763h.addLast(new io.sentry.profilemeasurements.b(Long.valueOf(j14), Float.valueOf(f10), time));
        }
    }
}
