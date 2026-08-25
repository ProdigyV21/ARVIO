package io.sentry.rrweb;

import io.ktor.http.LinkHeader;
import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class i extends e implements i2, k2 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f17816n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public List f17817o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public HashMap f17818p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public HashMap f17819q;

    public i() {
        super(d.TouchMove);
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
        fVar.S("source");
        fVar.b0(iLogger, this.f17802m);
        List list = this.f17817o;
        if (list != null && !list.isEmpty()) {
            fVar.S("positions");
            fVar.b0(iLogger, this.f17817o);
        }
        fVar.S("pointerId");
        fVar.d(this.f17816n);
        HashMap map = this.f17819q;
        if (map != null) {
            for (String str : map.keySet()) {
                g8.b.m(this.f17819q, str, fVar, str, iLogger);
            }
        }
        fVar.K();
        HashMap map2 = this.f17818p;
        if (map2 != null) {
            for (String str2 : map2.keySet()) {
                g8.b.m(this.f17818p, str2, fVar, str2, iLogger);
            }
        }
        fVar.K();
    }
}
