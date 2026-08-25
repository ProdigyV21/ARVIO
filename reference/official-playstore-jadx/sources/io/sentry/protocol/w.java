package io.sentry.protocol;

import io.ktor.http.ContentDisposition;
import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import j$.util.Objects;
import java.util.HashMap;

/* JADX INFO: loaded from: classes5.dex */
public final class w implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f17709i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f17710l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public HashMap f17711m;

    public w(String str, String str2) {
        this.f17709i = str;
        this.f17710l = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && w.class == obj.getClass()) {
            w wVar = (w) obj;
            if (Objects.equals(this.f17709i, wVar.f17709i) && Objects.equals(this.f17710l, wVar.f17710l)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(this.f17709i, this.f17710l);
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        fVar.S(ContentDisposition.Parameters.Name);
        fVar.value(this.f17709i);
        fVar.S("version");
        fVar.value(this.f17710l);
        HashMap map = this.f17711m;
        if (map != null) {
            for (String str : map.keySet()) {
                g8.b.m(this.f17711m, str, fVar, str, iLogger);
            }
        }
        fVar.K();
    }
}
