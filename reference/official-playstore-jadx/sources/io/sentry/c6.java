package io.sentry;

import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class c6 implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public io.sentry.protocol.v f17135i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public o7 f17136l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Double f17137m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f17138n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public String f17139o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f17140p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Double f17141q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Map f17142r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public HashMap f17143s;

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) throws IOException {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        fVar.S("timestamp");
        fVar.b0(iLogger, k2.c.j(this.f17137m));
        fVar.S(LinkHeader.Parameters.Type);
        fVar.value(this.f17140p);
        fVar.S(ContentDisposition.Parameters.Name);
        fVar.value(this.f17138n);
        fVar.S("value");
        fVar.d0(this.f17141q);
        fVar.S("trace_id");
        fVar.b0(iLogger, this.f17135i);
        if (this.f17136l != null) {
            fVar.S("span_id");
            fVar.b0(iLogger, this.f17136l);
        }
        if (this.f17139o != null) {
            fVar.S("unit");
            fVar.b0(iLogger, this.f17139o);
        }
        if (this.f17142r != null) {
            fVar.S("attributes");
            fVar.b0(iLogger, this.f17142r);
        }
        HashMap map = this.f17143s;
        if (map != null) {
            for (String str : map.keySet()) {
                g8.b.m(this.f17143s, str, fVar, str, iLogger);
            }
        }
        fVar.K();
    }
}
