package io.sentry.profilemeasurements;

import fi.iki.elonen.f;
import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import j$.util.concurrent.ConcurrentHashMap;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class b implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ConcurrentHashMap f17525i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public double f17526l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f17527m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public double f17528n;

    public b(Long l10, Number number, long j10) {
        this.f17527m = l10.toString();
        this.f17528n = number.doubleValue();
        this.f17526l = j10 / 1.0E9d;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return ac.b.C(this.f17525i, bVar.f17525i) && this.f17527m.equals(bVar.f17527m) && this.f17528n == bVar.f17528n && this.f17526l == bVar.f17526l;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f17525i, this.f17527m, Double.valueOf(this.f17528n)});
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        f fVar = (f) l3Var;
        fVar.j();
        fVar.S("value");
        fVar.b0(iLogger, Double.valueOf(this.f17528n));
        fVar.S("elapsed_since_start_ns");
        fVar.b0(iLogger, this.f17527m);
        fVar.S("timestamp");
        fVar.b0(iLogger, BigDecimal.valueOf(this.f17526l).setScale(6, RoundingMode.DOWN));
        ConcurrentHashMap concurrentHashMap = this.f17525i;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.f17525i, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }
}
