package io.sentry.protocol;

import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import j$.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes5.dex */
public final class r implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f17684i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ConcurrentHashMap f17685l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Integer f17686m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Long f17687n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Object f17688o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ConcurrentHashMap f17689p;

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        if (this.f17684i != null) {
            fVar.S("cookies");
            fVar.value(this.f17684i);
        }
        if (this.f17685l != null) {
            fVar.S("headers");
            fVar.b0(iLogger, this.f17685l);
        }
        if (this.f17686m != null) {
            fVar.S("status_code");
            fVar.b0(iLogger, this.f17686m);
        }
        if (this.f17687n != null) {
            fVar.S("body_size");
            fVar.b0(iLogger, this.f17687n);
        }
        if (this.f17688o != null) {
            fVar.S("data");
            fVar.b0(iLogger, this.f17688o);
        }
        ConcurrentHashMap concurrentHashMap = this.f17689p;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.f17689p, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }
}
