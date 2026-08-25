package io.sentry.protocol.profiling;

import fi.iki.elonen.f;
import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

/* JADX INFO: loaded from: classes5.dex */
public final class b implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public double f17677i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f17678l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f17679m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public HashMap f17680n;

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        f fVar = (f) l3Var;
        fVar.j();
        fVar.S("timestamp");
        fVar.b0(iLogger, BigDecimal.valueOf(this.f17677i).setScale(6, RoundingMode.DOWN));
        fVar.S("stack_id");
        fVar.b0(iLogger, Integer.valueOf(this.f17678l));
        if (this.f17679m != null) {
            fVar.S("thread_id");
            fVar.b0(iLogger, this.f17679m);
        }
        HashMap map = this.f17680n;
        if (map != null) {
            for (String str : map.keySet()) {
                g8.b.m(this.f17680n, str, fVar, str, iLogger);
            }
        }
        fVar.K();
    }
}
