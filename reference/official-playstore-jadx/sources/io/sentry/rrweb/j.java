package io.sentry.rrweb;

import io.ktor.http.LinkHeader;
import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: loaded from: classes5.dex */
public final class j extends b implements k2, i2 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f17820m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f17821n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f17822o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public HashMap f17823p;

    public j() {
        super(c.Meta);
        this.f17820m = "";
    }

    @Override // io.sentry.rrweb.b
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || j.class != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        j jVar = (j) obj;
        return this.f17821n == jVar.f17821n && this.f17822o == jVar.f17822o && ac.b.C(this.f17820m, jVar.f17820m);
    }

    @Override // io.sentry.rrweb.b
    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(super.hashCode()), this.f17820m, Integer.valueOf(this.f17821n), Integer.valueOf(this.f17822o)});
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
        fVar.S("href");
        fVar.value(this.f17820m);
        fVar.S("height");
        fVar.d(this.f17821n);
        fVar.S("width");
        fVar.d(this.f17822o);
        HashMap map = this.f17823p;
        if (map != null) {
            for (String str : map.keySet()) {
                g8.b.m(this.f17823p, str, fVar, str, iLogger);
            }
        }
        fVar.K();
        fVar.K();
    }
}
