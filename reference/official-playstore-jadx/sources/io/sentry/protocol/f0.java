package io.sentry.protocol;

import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class f0 implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String[] f17581i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ConcurrentHashMap f17582l;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || f0.class != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.f17581i, ((f0) obj).f17581i);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f17581i);
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        if (this.f17581i != null) {
            fVar.S("active_profiles");
            fVar.b0(iLogger, this.f17581i);
        }
        ConcurrentHashMap concurrentHashMap = this.f17582l;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.f17582l, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }
}
