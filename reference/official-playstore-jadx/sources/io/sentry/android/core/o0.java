package io.sentry.android.core;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import io.sentry.e4;
import io.sentry.r4;
import io.sentry.y6;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Collections;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class o0 implements io.sentry.android.core.util.a, io.sentry.util.j, r4, e4 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f16676i;

    public /* synthetic */ o0(int i10) {
        this.f16676i = i10;
    }

    @Override // io.sentry.android.core.util.a
    public Object a(Context context) {
        String string;
        switch (this.f16676i) {
            case 0:
                try {
                    ApplicationInfo applicationInfo = context.getApplicationInfo();
                    int i10 = applicationInfo.labelRes;
                    if (i10 == 0) {
                        CharSequence charSequence = applicationInfo.nonLocalizedLabel;
                        string = charSequence != null ? charSequence.toString() : context.getPackageManager().getApplicationLabel(applicationInfo).toString();
                    } else {
                        string = context.getString(i10);
                    }
                    return string;
                } catch (Throwable unused) {
                    return null;
                }
            case 1:
                return q0.a(context);
            default:
                try {
                    return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                } catch (Throwable unused2) {
                    return null;
                }
        }
    }

    @Override // io.sentry.r4
    public void c(y6 y6Var) {
        io.sentry.util.b bVar = k1.f16663b;
    }

    @Override // io.sentry.e4
    public void e(io.sentry.a1 a1Var) {
        switch (this.f16676i) {
            case 8:
                a1Var.p(io.sentry.protocol.v.f17707l);
                break;
            default:
                a1Var.M(new io.sentry.cache.a(a1Var, 4));
                break;
        }
    }

    @Override // io.sentry.util.j
    public Object g() {
        switch (this.f16676i) {
            case 5:
                return new Timer(true);
            case 9:
                ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
                for (io.sentry.clientreport.d dVar : io.sentry.clientreport.d.values()) {
                    for (io.sentry.m mVar : io.sentry.m.values()) {
                        concurrentHashMap.put(new io.sentry.clientreport.c(dVar.getReason(), mVar.getCategory()), new AtomicLong(0L));
                    }
                }
                return Collections.unmodifiableMap(concurrentHashMap);
            default:
                return qb.d.B();
        }
    }
}
