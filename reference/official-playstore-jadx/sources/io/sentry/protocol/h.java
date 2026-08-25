package io.sentry.protocol;

import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class h implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final List f17588i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ConcurrentHashMap f17589l;

    public h(List list) {
        this.f17588i = list;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || h.class != obj.getClass()) {
            return false;
        }
        return ac.b.C(this.f17588i, ((h) obj).f17588i);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f17588i});
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        fVar.S("values");
        fVar.b0(iLogger, this.f17588i);
        ConcurrentHashMap concurrentHashMap = this.f17589l;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.f17589l, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }
}
