package io.sentry.protocol;

import io.ktor.http.ContentDisposition;
import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import j$.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes5.dex */
public final class x implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f17712i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f17713l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f17714m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ConcurrentHashMap f17715n;

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        if (this.f17712i != null) {
            fVar.S(ContentDisposition.Parameters.Name);
            fVar.value(this.f17712i);
        }
        if (this.f17713l != null) {
            fVar.S("version");
            fVar.value(this.f17713l);
        }
        if (this.f17714m != null) {
            fVar.S("raw_description");
            fVar.value(this.f17714m);
        }
        ConcurrentHashMap concurrentHashMap = this.f17715n;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.f17715n, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }
}
