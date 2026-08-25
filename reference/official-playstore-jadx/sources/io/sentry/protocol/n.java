package io.sentry.protocol;

import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class n implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f17649i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f17650l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public List f17651m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ConcurrentHashMap f17652n;

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        if (this.f17649i != null) {
            fVar.S("formatted");
            fVar.value(this.f17649i);
        }
        if (this.f17650l != null) {
            fVar.S("message");
            fVar.value(this.f17650l);
        }
        List list = this.f17651m;
        if (list != null && !list.isEmpty()) {
            fVar.S("params");
            fVar.b0(iLogger, this.f17651m);
        }
        ConcurrentHashMap concurrentHashMap = this.f17652n;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.f17652n, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }
}
