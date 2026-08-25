package io.sentry.rrweb;

import io.ktor.http.LinkHeader;
import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import java.io.IOException;
import java.util.HashMap;

/* JADX INFO: loaded from: classes5.dex */
public final class k extends b implements i2, k2 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f17824m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public HashMap f17825n;

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
        fVar.value(this.f17824m);
        fVar.S("payload");
        fVar.j();
        HashMap map = this.f17825n;
        if (map != null) {
            for (String str : map.keySet()) {
                Object obj = map.get(str);
                fVar.S(str);
                fVar.b0(iLogger, obj);
            }
        }
        fVar.K();
        fVar.K();
        fVar.K();
    }
}
