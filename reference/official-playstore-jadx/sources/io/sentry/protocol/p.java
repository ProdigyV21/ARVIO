package io.sentry.protocol;

import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class p implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f17660i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f17661l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f17662m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f17663n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public String f17664o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ConcurrentHashMap f17665p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ConcurrentHashMap f17666q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Long f17667r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public ConcurrentHashMap f17668s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public String f17669t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public String f17670u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public ConcurrentHashMap f17671v;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || p.class != obj.getClass()) {
            return false;
        }
        p pVar = (p) obj;
        return ac.b.C(this.f17660i, pVar.f17660i) && ac.b.C(this.f17661l, pVar.f17661l) && ac.b.C(this.f17662m, pVar.f17662m) && ac.b.C(this.f17664o, pVar.f17664o) && ac.b.C(this.f17665p, pVar.f17665p) && ac.b.C(this.f17666q, pVar.f17666q) && ac.b.C(this.f17667r, pVar.f17667r) && ac.b.C(this.f17669t, pVar.f17669t) && ac.b.C(this.f17670u, pVar.f17670u);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f17660i, this.f17661l, this.f17662m, this.f17664o, this.f17665p, this.f17666q, this.f17667r, this.f17669t, this.f17670u});
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        if (this.f17660i != null) {
            fVar.S("url");
            fVar.value(this.f17660i);
        }
        if (this.f17661l != null) {
            fVar.S("method");
            fVar.value(this.f17661l);
        }
        if (this.f17662m != null) {
            fVar.S("query_string");
            fVar.value(this.f17662m);
        }
        if (this.f17663n != null) {
            fVar.S("data");
            fVar.b0(iLogger, this.f17663n);
        }
        if (this.f17664o != null) {
            fVar.S("cookies");
            fVar.value(this.f17664o);
        }
        if (this.f17665p != null) {
            fVar.S("headers");
            fVar.b0(iLogger, this.f17665p);
        }
        if (this.f17666q != null) {
            fVar.S("env");
            fVar.b0(iLogger, this.f17666q);
        }
        if (this.f17668s != null) {
            fVar.S("other");
            fVar.b0(iLogger, this.f17668s);
        }
        if (this.f17669t != null) {
            fVar.S("fragment");
            fVar.b0(iLogger, this.f17669t);
        }
        if (this.f17667r != null) {
            fVar.S("body_size");
            fVar.b0(iLogger, this.f17667r);
        }
        if (this.f17670u != null) {
            fVar.S("api_target");
            fVar.b0(iLogger, this.f17670u);
        }
        ConcurrentHashMap concurrentHashMap = this.f17671v;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.f17671v, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }
}
