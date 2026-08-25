package io.sentry.protocol;

import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class g implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f17583i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f17584l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ConcurrentHashMap f17585m;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && g.class == obj.getClass()) {
            g gVar = (g) obj;
            if (ac.b.C(this.f17583i, gVar.f17583i) && ac.b.C(Boolean.valueOf(this.f17584l), Boolean.valueOf(gVar.f17584l))) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f17583i, Boolean.valueOf(this.f17584l)});
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) throws IOException {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        fVar.S("flag");
        fVar.value(this.f17583i);
        fVar.S("result");
        fVar.e0(this.f17584l);
        ConcurrentHashMap concurrentHashMap = this.f17585m;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.f17585m, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }
}
