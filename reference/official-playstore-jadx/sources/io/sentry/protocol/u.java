package io.sentry.protocol;

import io.ktor.http.LinkHeader;
import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import java.io.IOException;
import java.util.HashMap;

/* JADX INFO: loaded from: classes5.dex */
public final class u implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f17700i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f17701l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f17702m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Long f17703n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public b0 f17704o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public m f17705p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public HashMap f17706q;

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) throws IOException {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        if (this.f17700i != null) {
            fVar.S(LinkHeader.Parameters.Type);
            fVar.value(this.f17700i);
        }
        if (this.f17701l != null) {
            fVar.S("value");
            fVar.value(this.f17701l);
        }
        if (this.f17702m != null) {
            fVar.S("module");
            fVar.value(this.f17702m);
        }
        if (this.f17703n != null) {
            fVar.S("thread_id");
            fVar.d0(this.f17703n);
        }
        if (this.f17704o != null) {
            fVar.S("stacktrace");
            fVar.b0(iLogger, this.f17704o);
        }
        if (this.f17705p != null) {
            fVar.S("mechanism");
            fVar.b0(iLogger, this.f17705p);
        }
        HashMap map = this.f17706q;
        if (map != null) {
            for (String str : map.keySet()) {
                g8.b.m(this.f17706q, str, fVar, str, iLogger);
            }
        }
        fVar.K();
    }
}
