package io.sentry;

import j$.util.concurrent.ConcurrentHashMap;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class r3 implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public io.sentry.protocol.v f17775i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ConcurrentHashMap f17776l;

    public r3(io.sentry.protocol.v vVar) {
        this.f17775i = vVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof r3) {
            return this.f17775i.equals(((r3) obj).f17775i);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f17775i});
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        fVar.S("profiler_id");
        fVar.b0(iLogger, this.f17775i);
        ConcurrentHashMap concurrentHashMap = this.f17776l;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.f17776l, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }
}
