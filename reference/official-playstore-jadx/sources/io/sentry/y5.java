package io.sentry;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class y5 implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public io.sentry.protocol.v f18069i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public o7 f18070l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Double f18071m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f18072n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public a6 f18073o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Integer f18074p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Map f18075q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public HashMap f18076r;

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        fVar.S("timestamp");
        fVar.b0(iLogger, k2.c.j(this.f18071m));
        fVar.S("trace_id");
        fVar.b0(iLogger, this.f18069i);
        if (this.f18070l != null) {
            fVar.S("span_id");
            fVar.b0(iLogger, this.f18070l);
        }
        fVar.S(TtmlNode.TAG_BODY);
        fVar.value(this.f18072n);
        fVar.S("level");
        fVar.b0(iLogger, this.f18073o);
        if (this.f18074p != null) {
            fVar.S("severity_number");
            fVar.b0(iLogger, this.f18074p);
        }
        if (this.f18075q != null) {
            fVar.S("attributes");
            fVar.b0(iLogger, this.f18075q);
        }
        HashMap map = this.f18076r;
        if (map != null) {
            for (String str : map.keySet()) {
                g8.b.m(this.f18076r, str, fVar, str, iLogger);
            }
        }
        fVar.K();
    }
}
