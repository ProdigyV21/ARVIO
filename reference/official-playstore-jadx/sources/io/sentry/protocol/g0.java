package io.sentry.protocol;

import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import j$.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes5.dex */
public final class g0 implements i2, k2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f17586i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ConcurrentHashMap f17587l;

    public g0(String str) {
        this.f17586i = str;
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        String str = this.f17586i;
        if (str != null) {
            fVar.S("source");
            fVar.b0(iLogger, str);
        }
        ConcurrentHashMap concurrentHashMap = this.f17587l;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.f17587l, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }
}
