package io.sentry.android.core;

import android.net.TrafficStats;
import android.util.Log;
import io.sentry.ILogger;
import io.sentry.o5;
import io.sentry.w5;
import io.sentry.y6;

/* JADX INFO: loaded from: classes5.dex */
public final class v implements io.sentry.j1, y0, ILogger, io.sentry.logger.c, io.sentry.metrics.c, o5 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final v f16791l = new v(0);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final v f16792m = new v(1);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f16793i;

    public /* synthetic */ v(int i10) {
        this.f16793i = i10;
    }

    @Override // io.sentry.j1
    public void a() {
        TrafficStats.clearThreadStatsTag();
    }

    @Override // io.sentry.j1
    public void b() {
        TrafficStats.setThreadStatsTag(61441);
    }

    @Override // io.sentry.logger.c
    public io.sentry.logger.b c(y6 y6Var, com.google.android.gms.common.api.internal.g0 g0Var) {
        n nVar = new n(y6Var, g0Var, 0);
        h0.f16496o.i(nVar);
        return nVar;
    }

    @Override // io.sentry.ILogger
    public void k(w5 w5Var, Throwable th, String str, Object... objArr) {
        switch (this.f16793i) {
            case 2:
                if (objArr.length != 0) {
                    l(w5Var, String.format(str, objArr), th);
                } else {
                    l(w5Var, str, th);
                }
                break;
            default:
                if (objArr.length != 0) {
                    l(w5Var, String.format(str, objArr), th);
                } else {
                    l(w5Var, str, th);
                }
                break;
        }
    }

    @Override // io.sentry.ILogger
    public void l(w5 w5Var, String str, Throwable th) {
        switch (this.f16793i) {
            case 2:
                Log.wtf("Sentry", str, th);
                break;
            default:
                int i10 = l.f16664a[w5Var.ordinal()];
                if (i10 == 1) {
                    Log.i("Sentry", str, th);
                } else if (i10 == 2) {
                    Log.w("Sentry", str, th);
                } else if (i10 == 3) {
                    Log.e("Sentry", str, th);
                } else if (i10 == 4) {
                    Log.wtf("Sentry", str, th);
                } else {
                    Log.d("Sentry", str, th);
                }
                break;
        }
    }

    @Override // io.sentry.ILogger
    public void q(w5 w5Var, String str, Object... objArr) {
        switch (this.f16793i) {
            case 2:
                if (objArr.length != 0) {
                    Log.println(7, "Sentry", String.format(str, objArr));
                } else {
                    Log.println(7, "Sentry", str);
                }
                break;
            default:
                int i10 = 5;
                if (objArr.length != 0) {
                    int i11 = l.f16664a[w5Var.ordinal()];
                    if (i11 == 1) {
                        i10 = 4;
                    } else if (i11 != 2) {
                        i10 = i11 != 4 ? 3 : 7;
                    }
                    Log.println(i10, "Sentry", String.format(str, objArr));
                } else {
                    int i12 = l.f16664a[w5Var.ordinal()];
                    if (i12 == 1) {
                        i10 = 4;
                    } else if (i12 != 2) {
                        i10 = i12 != 4 ? 3 : 7;
                    }
                    Log.println(i10, "Sentry", str);
                }
                break;
        }
    }

    @Override // io.sentry.ILogger
    public boolean t(w5 w5Var) {
        switch (this.f16793i) {
        }
        return true;
    }

    @Override // io.sentry.metrics.c
    /* JADX INFO: renamed from: c, reason: collision with other method in class */
    public io.sentry.metrics.b mo7017c(y6 y6Var, com.google.android.gms.common.api.internal.g0 g0Var) {
        p pVar = new p(y6Var, g0Var, 1);
        h0.f16496o.i(pVar);
        return pVar;
    }
}
