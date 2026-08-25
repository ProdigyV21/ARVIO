package io.sentry.rrweb;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: loaded from: classes5.dex */
public final class m extends b implements k2, i2 {
    public ConcurrentHashMap A;
    public ConcurrentHashMap B;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f17835m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f17836n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public long f17837o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f17838p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String f17839q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public String f17840r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f17841s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f17842t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f17843u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public String f17844v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f17845w;
    public int x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f17846y;
    public HashMap z;

    public m() {
        super(c.Custom);
        this.f17839q = "h264";
        this.f17840r = "mp4";
        this.f17844v = "constant";
        this.f17835m = "video";
    }

    @Override // io.sentry.rrweb.b
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || m.class != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        m mVar = (m) obj;
        return this.f17836n == mVar.f17836n && this.f17837o == mVar.f17837o && this.f17838p == mVar.f17838p && this.f17841s == mVar.f17841s && this.f17842t == mVar.f17842t && this.f17843u == mVar.f17843u && this.f17845w == mVar.f17845w && this.x == mVar.x && this.f17846y == mVar.f17846y && ac.b.C(this.f17835m, mVar.f17835m) && ac.b.C(this.f17839q, mVar.f17839q) && ac.b.C(this.f17840r, mVar.f17840r) && ac.b.C(this.f17844v, mVar.f17844v);
    }

    @Override // io.sentry.rrweb.b
    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(super.hashCode()), this.f17835m, Integer.valueOf(this.f17836n), Long.valueOf(this.f17837o), Long.valueOf(this.f17838p), this.f17839q, this.f17840r, Integer.valueOf(this.f17841s), Integer.valueOf(this.f17842t), Integer.valueOf(this.f17843u), this.f17844v, Integer.valueOf(this.f17845w), Integer.valueOf(this.x), Integer.valueOf(this.f17846y)});
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) throws IOException {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        fVar.S(LinkHeader.Parameters.Type);
        fVar.b0(iLogger, this.f17800i);
        fVar.S("timestamp");
        fVar.d(this.f17801l);
        fVar.S("data");
        fVar.j();
        fVar.S("tag");
        fVar.value(this.f17835m);
        fVar.S("payload");
        fVar.j();
        fVar.S("segmentId");
        fVar.d(this.f17836n);
        fVar.S(ContentDisposition.Parameters.Size);
        fVar.d(this.f17837o);
        fVar.S("duration");
        fVar.d(this.f17838p);
        fVar.S("encoding");
        fVar.value(this.f17839q);
        fVar.S(TtmlNode.RUBY_CONTAINER);
        fVar.value(this.f17840r);
        fVar.S("height");
        fVar.d(this.f17841s);
        fVar.S("width");
        fVar.d(this.f17842t);
        fVar.S("frameCount");
        fVar.d(this.f17843u);
        fVar.S("frameRate");
        fVar.d(this.f17845w);
        fVar.S("frameRateType");
        fVar.value(this.f17844v);
        fVar.S(TtmlNode.LEFT);
        fVar.d(this.x);
        fVar.S("top");
        fVar.d(this.f17846y);
        ConcurrentHashMap concurrentHashMap = this.A;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.A, k, fVar, k, iLogger);
            }
        }
        fVar.K();
        ConcurrentHashMap concurrentHashMap2 = this.B;
        if (concurrentHashMap2 != null) {
            for (K k10 : concurrentHashMap2.keySet()) {
                g8.b.n(this.B, k10, fVar, k10, iLogger);
            }
        }
        fVar.K();
        HashMap map = this.z;
        if (map != null) {
            for (String str : map.keySet()) {
                g8.b.m(this.z, str, fVar, str, iLogger);
            }
        }
        fVar.K();
    }
}
