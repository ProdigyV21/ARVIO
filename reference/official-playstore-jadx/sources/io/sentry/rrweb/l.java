package io.sentry.rrweb;

import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.LinkHeader;
import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

/* JADX INFO: loaded from: classes5.dex */
public final class l extends b implements i2, k2 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f17826m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f17827n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public String f17828o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public double f17829p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public double f17830q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ConcurrentHashMap f17831r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public HashMap f17832s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public ConcurrentHashMap f17833t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public ConcurrentHashMap f17834u;

    public l() {
        super(c.Custom);
        this.f17826m = "performanceSpan";
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
        fVar.S("tag");
        fVar.value(this.f17826m);
        fVar.S("payload");
        fVar.j();
        if (this.f17827n != null) {
            fVar.S("op");
            fVar.value(this.f17827n);
        }
        if (this.f17828o != null) {
            fVar.S(MediaTrack.ROLE_DESCRIPTION);
            fVar.value(this.f17828o);
        }
        fVar.S("startTimestamp");
        fVar.b0(iLogger, BigDecimal.valueOf(this.f17829p));
        fVar.S("endTimestamp");
        fVar.b0(iLogger, BigDecimal.valueOf(this.f17830q));
        if (this.f17831r != null) {
            fVar.S("data");
            fVar.b0(iLogger, this.f17831r);
        }
        ConcurrentHashMap concurrentHashMap = this.f17833t;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.f17833t, k, fVar, k, iLogger);
            }
        }
        fVar.K();
        ConcurrentHashMap concurrentHashMap2 = this.f17834u;
        if (concurrentHashMap2 != null) {
            for (K k10 : concurrentHashMap2.keySet()) {
                g8.b.n(this.f17834u, k10, fVar, k10, iLogger);
            }
        }
        fVar.K();
        HashMap map = this.f17832s;
        if (map != null) {
            for (String str : map.keySet()) {
                g8.b.m(this.f17832s, str, fVar, str, iLogger);
            }
        }
        fVar.K();
    }
}
