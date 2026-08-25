package com.google.android.gms.common.api.internal;

import android.os.SystemClock;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public final class n0 implements com.google.android.gms.tasks.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final h f12770i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f12771l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final a f12772m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final long f12773n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final long f12774o;

    public n0(h hVar, int i10, a aVar, long j10, long j11) {
        this.f12770i = hVar;
        this.f12771l = i10;
        this.f12772m = aVar;
        this.f12773n = j10;
        this.f12774o = j11;
    }

    public static com.google.android.gms.common.internal.i a(e0 e0Var, com.google.android.gms.common.internal.f fVar, int i10) {
        com.google.android.gms.common.internal.i telemetryConfiguration = fVar.getTelemetryConfiguration();
        if (telemetryConfiguration == null || !telemetryConfiguration.f12854l) {
            return null;
        }
        int[] iArr = telemetryConfiguration.f12856n;
        int i11 = 0;
        if (iArr != null) {
            while (i11 < iArr.length) {
                if (iArr[i11] != i10) {
                    i11++;
                }
            }
            return null;
        }
        int[] iArr2 = telemetryConfiguration.f12858p;
        if (iArr2 != null) {
            while (i11 < iArr2.length) {
                if (iArr2[i11] == i10) {
                    return null;
                }
                i11++;
            }
        }
        if (e0Var.f12726n < telemetryConfiguration.f12857o) {
            return telemetryConfiguration;
        }
        return null;
    }

    @Override // com.google.android.gms.tasks.c
    public final void onComplete(com.google.android.gms.tasks.h hVar) {
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        long j10;
        long j11;
        int iElapsedRealtime;
        h hVar2 = this.f12770i;
        if (hVar2.g()) {
            com.google.android.gms.common.internal.v vVar = (com.google.android.gms.common.internal.v) com.google.android.gms.common.internal.u.d().f12919a;
            if (vVar == null || vVar.f12925l) {
                e0 e0Var = (e0) hVar2.f12749t.get(this.f12772m);
                if (e0Var != null) {
                    Object obj = e0Var.f12717d;
                    if (obj instanceof com.google.android.gms.common.internal.f) {
                        com.google.android.gms.common.internal.f fVar = (com.google.android.gms.common.internal.f) obj;
                        long j12 = this.f12773n;
                        boolean z = j12 > 0;
                        int gCoreServiceId = fVar.getGCoreServiceId();
                        if (vVar != null) {
                            z &= vVar.f12926m;
                            int i16 = vVar.f12927n;
                            int i17 = vVar.f12928o;
                            i10 = vVar.f12924i;
                            if (fVar.hasConnectionInfo() && !fVar.isConnecting()) {
                                com.google.android.gms.common.internal.i iVarA = a(e0Var, fVar, this.f12771l);
                                if (iVarA == null) {
                                    return;
                                }
                                boolean z5 = iVarA.f12855m && j12 > 0;
                                i17 = iVarA.f12857o;
                                z = z5;
                            }
                            i12 = i16;
                            i11 = i17;
                        } else {
                            i10 = 0;
                            i11 = 100;
                            i12 = 5000;
                        }
                        if (hVar.h()) {
                            i15 = 0;
                            i14 = 0;
                        } else if (((com.google.android.gms.tasks.t) hVar).f13883d) {
                            i14 = -1;
                            i15 = 100;
                        } else {
                            Exception excE = hVar.e();
                            if (excE instanceof ApiException) {
                                Status status = ((ApiException) excE).getStatus();
                                i13 = status.f12675i;
                                o3.b bVar = status.f12678n;
                                if (bVar != null) {
                                    i14 = bVar.f20729l;
                                }
                                i15 = i13;
                            } else {
                                i13 = 101;
                            }
                            i14 = -1;
                            i15 = i13;
                        }
                        if (z) {
                            long j13 = this.f12774o;
                            long jCurrentTimeMillis = System.currentTimeMillis();
                            j10 = j12;
                            iElapsedRealtime = (int) (SystemClock.elapsedRealtime() - j13);
                            j11 = jCurrentTimeMillis;
                        } else {
                            j10 = 0;
                            j11 = 0;
                            iElapsedRealtime = -1;
                        }
                        o0 o0Var = new o0(new com.google.android.gms.common.internal.r(this.f12771l, i15, i14, j10, j11, null, null, gCoreServiceId, iElapsedRealtime), i10, i12, i11);
                        androidx.loader.content.j jVar = hVar2.x;
                        jVar.sendMessage(jVar.obtainMessage(18, o0Var));
                    }
                }
            }
        }
    }
}
