package io.sentry.protocol;

import io.ktor.http.ContentDisposition;
import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class o implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f17653i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f17654l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f17655m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f17656n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public String f17657o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Boolean f17658p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ConcurrentHashMap f17659q;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && o.class == obj.getClass()) {
            o oVar = (o) obj;
            if (ac.b.C(this.f17653i, oVar.f17653i) && ac.b.C(this.f17654l, oVar.f17654l) && ac.b.C(this.f17655m, oVar.f17655m) && ac.b.C(this.f17656n, oVar.f17656n) && ac.b.C(this.f17657o, oVar.f17657o) && ac.b.C(this.f17658p, oVar.f17658p)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f17653i, this.f17654l, this.f17655m, this.f17656n, this.f17657o, this.f17658p});
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) throws IOException {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        if (this.f17653i != null) {
            fVar.S(ContentDisposition.Parameters.Name);
            fVar.value(this.f17653i);
        }
        if (this.f17654l != null) {
            fVar.S("version");
            fVar.value(this.f17654l);
        }
        if (this.f17655m != null) {
            fVar.S("raw_description");
            fVar.value(this.f17655m);
        }
        if (this.f17656n != null) {
            fVar.S("build");
            fVar.value(this.f17656n);
        }
        if (this.f17657o != null) {
            fVar.S("kernel_version");
            fVar.value(this.f17657o);
        }
        if (this.f17658p != null) {
            fVar.S("rooted");
            fVar.c0(this.f17658p);
        }
        ConcurrentHashMap concurrentHashMap = this.f17659q;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.f17659q, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }
}
