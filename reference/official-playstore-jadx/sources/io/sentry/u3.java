package io.sentry;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class u3 implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f17943i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f17944l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f17945m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Long f17946n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Long f17947o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Long f17948p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Long f17949q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ConcurrentHashMap f17950r;

    public u3(m1 m1Var, Long l10, Long l11) {
        this.f17943i = m1Var.f().toString();
        this.f17944l = m1Var.o().f17435i.toString();
        this.f17945m = m1Var.getName().isEmpty() ? "unknown" : m1Var.getName();
        this.f17946n = l10;
        this.f17948p = l11;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || u3.class != obj.getClass()) {
            return false;
        }
        u3 u3Var = (u3) obj;
        return this.f17943i.equals(u3Var.f17943i) && this.f17944l.equals(u3Var.f17944l) && this.f17945m.equals(u3Var.f17945m) && this.f17946n.equals(u3Var.f17946n) && this.f17948p.equals(u3Var.f17948p) && ac.b.C(this.f17949q, u3Var.f17949q) && ac.b.C(this.f17947o, u3Var.f17947o) && ac.b.C(this.f17950r, u3Var.f17950r);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f17943i, this.f17944l, this.f17945m, this.f17946n, this.f17947o, this.f17948p, this.f17949q, this.f17950r});
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        fVar.S(TtmlNode.ATTR_ID);
        fVar.b0(iLogger, this.f17943i);
        fVar.S("trace_id");
        fVar.b0(iLogger, this.f17944l);
        fVar.S(ContentDisposition.Parameters.Name);
        fVar.b0(iLogger, this.f17945m);
        fVar.S("relative_start_ns");
        fVar.b0(iLogger, this.f17946n);
        fVar.S("relative_end_ns");
        fVar.b0(iLogger, this.f17947o);
        fVar.S("relative_cpu_start_ms");
        fVar.b0(iLogger, this.f17948p);
        fVar.S("relative_cpu_end_ms");
        fVar.b0(iLogger, this.f17949q);
        ConcurrentHashMap concurrentHashMap = this.f17950r;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.f17950r, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }
}
