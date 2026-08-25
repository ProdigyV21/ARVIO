package io.sentry.protocol;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class k implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f17612i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Integer f17613l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f17614m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f17615n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Integer f17616o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f17617p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Boolean f17618q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public String f17619r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public String f17620s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public ConcurrentHashMap f17621t;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && k.class == obj.getClass()) {
            k kVar = (k) obj;
            if (ac.b.C(this.f17612i, kVar.f17612i) && ac.b.C(this.f17613l, kVar.f17613l) && ac.b.C(this.f17614m, kVar.f17614m) && ac.b.C(this.f17615n, kVar.f17615n) && ac.b.C(this.f17616o, kVar.f17616o) && ac.b.C(this.f17617p, kVar.f17617p) && ac.b.C(this.f17618q, kVar.f17618q) && ac.b.C(this.f17619r, kVar.f17619r) && ac.b.C(this.f17620s, kVar.f17620s)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f17612i, this.f17613l, this.f17614m, this.f17615n, this.f17616o, this.f17617p, this.f17618q, this.f17619r, this.f17620s});
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) throws IOException {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        if (this.f17612i != null) {
            fVar.S(ContentDisposition.Parameters.Name);
            fVar.value(this.f17612i);
        }
        if (this.f17613l != null) {
            fVar.S(TtmlNode.ATTR_ID);
            fVar.d0(this.f17613l);
        }
        if (this.f17614m != null) {
            fVar.S("vendor_id");
            fVar.value(this.f17614m);
        }
        if (this.f17615n != null) {
            fVar.S("vendor_name");
            fVar.value(this.f17615n);
        }
        if (this.f17616o != null) {
            fVar.S("memory_size");
            fVar.d0(this.f17616o);
        }
        if (this.f17617p != null) {
            fVar.S("api_type");
            fVar.value(this.f17617p);
        }
        if (this.f17618q != null) {
            fVar.S("multi_threaded_rendering");
            fVar.c0(this.f17618q);
        }
        if (this.f17619r != null) {
            fVar.S("version");
            fVar.value(this.f17619r);
        }
        if (this.f17620s != null) {
            fVar.S("npot_support");
            fVar.value(this.f17620s);
        }
        ConcurrentHashMap concurrentHashMap = this.f17621t;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.f17621t, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }
}
