package io.sentry.protocol;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class i0 implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f17597i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f17598l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f17599m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f17600n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public String f17601o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public j f17602p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ConcurrentHashMap f17603q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ConcurrentHashMap f17604r;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && i0.class == obj.getClass()) {
            i0 i0Var = (i0) obj;
            if (ac.b.C(this.f17597i, i0Var.f17597i) && ac.b.C(this.f17598l, i0Var.f17598l) && ac.b.C(this.f17599m, i0Var.f17599m) && ac.b.C(this.f17600n, i0Var.f17600n)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f17597i, this.f17598l, this.f17599m, this.f17600n});
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        if (this.f17597i != null) {
            fVar.S("email");
            fVar.value(this.f17597i);
        }
        if (this.f17598l != null) {
            fVar.S(TtmlNode.ATTR_ID);
            fVar.value(this.f17598l);
        }
        if (this.f17599m != null) {
            fVar.S("username");
            fVar.value(this.f17599m);
        }
        if (this.f17600n != null) {
            fVar.S("ip_address");
            fVar.value(this.f17600n);
        }
        if (this.f17601o != null) {
            fVar.S(ContentDisposition.Parameters.Name);
            fVar.value(this.f17601o);
        }
        if (this.f17602p != null) {
            fVar.S("geo");
            this.f17602p.serialize(fVar, iLogger);
        }
        if (this.f17603q != null) {
            fVar.S("data");
            fVar.b0(iLogger, this.f17603q);
        }
        ConcurrentHashMap concurrentHashMap = this.f17604r;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.f17604r, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }
}
