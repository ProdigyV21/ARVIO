package io.sentry.protocol;

import io.ktor.http.ContentDisposition;
import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class b implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f17542i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f17543l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ConcurrentHashMap f17544m;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && b.class == obj.getClass()) {
            b bVar = (b) obj;
            if (ac.b.C(this.f17542i, bVar.f17542i) && ac.b.C(this.f17543l, bVar.f17543l)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f17542i, this.f17543l});
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        if (this.f17542i != null) {
            fVar.S(ContentDisposition.Parameters.Name);
            fVar.value(this.f17542i);
        }
        if (this.f17543l != null) {
            fVar.S("version");
            fVar.value(this.f17543l);
        }
        ConcurrentHashMap concurrentHashMap = this.f17544m;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.f17544m, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }
}
