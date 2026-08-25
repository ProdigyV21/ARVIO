package io.sentry.android.core;

import android.os.FileObserver;
import io.sentry.ILogger;
import io.sentry.m3;
import io.sentry.w5;
import java.io.File;

/* JADX INFO: loaded from: classes5.dex */
public final class x0 extends FileObserver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f16821a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m3 f16822b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ILogger f16823c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f16824d;

    public x0(String str, m3 m3Var, ILogger iLogger, long j10) {
        super(str);
        this.f16821a = str;
        this.f16822b = m3Var;
        ac.b.V(iLogger, "Logger is required.");
        this.f16823c = iLogger;
        this.f16824d = j10;
    }

    @Override // android.os.FileObserver
    public final void onEvent(int i10, String str) {
        if (str == null || i10 != 8) {
            return;
        }
        w5 w5Var = w5.DEBUG;
        String str2 = this.f16821a;
        Object[] objArr = {Integer.valueOf(i10), str2, str};
        ILogger iLogger = this.f16823c;
        iLogger.q(w5Var, "onEvent fired for EnvelopeFileObserver with event type %d on path: %s for file %s.", objArr);
        io.sentry.j0 j0VarK = qb.l.k(new w0(this.f16824d, iLogger));
        StringBuilder sbS = androidx.compose.material3.d.s(str2);
        sbS.append(File.separator);
        sbS.append(str);
        String string = sbS.toString();
        m3 m3Var = this.f16822b;
        m3Var.getClass();
        ac.b.V(string, "Path is required.");
        m3Var.b(new File(string), j0VarK);
    }
}
