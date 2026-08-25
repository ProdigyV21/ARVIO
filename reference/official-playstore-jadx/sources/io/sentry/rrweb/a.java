package io.sentry.rrweb;

import io.ktor.http.LinkHeader;
import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import io.sentry.w5;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

/* JADX INFO: loaded from: classes5.dex */
public final class a extends b implements k2, i2 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f17790m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public double f17791n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public String f17792o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f17793p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String f17794q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public w5 f17795r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public ConcurrentHashMap f17796s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public HashMap f17797t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public ConcurrentHashMap f17798u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public ConcurrentHashMap f17799v;

    public a() {
        super(c.Custom);
        this.f17790m = "breadcrumb";
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
        fVar.value(this.f17790m);
        fVar.S("payload");
        fVar.j();
        if (this.f17792o != null) {
            fVar.S(LinkHeader.Parameters.Type);
            fVar.value(this.f17792o);
        }
        fVar.S("timestamp");
        fVar.b0(iLogger, BigDecimal.valueOf(this.f17791n));
        if (this.f17793p != null) {
            fVar.S("category");
            fVar.value(this.f17793p);
        }
        if (this.f17794q != null) {
            fVar.S("message");
            fVar.value(this.f17794q);
        }
        if (this.f17795r != null) {
            fVar.S("level");
            fVar.b0(iLogger, this.f17795r);
        }
        if (this.f17796s != null) {
            fVar.S("data");
            fVar.b0(iLogger, this.f17796s);
        }
        ConcurrentHashMap concurrentHashMap = this.f17798u;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.f17798u, k, fVar, k, iLogger);
            }
        }
        fVar.K();
        ConcurrentHashMap concurrentHashMap2 = this.f17799v;
        if (concurrentHashMap2 != null) {
            for (K k10 : concurrentHashMap2.keySet()) {
                g8.b.n(this.f17799v, k10, fVar, k10, iLogger);
            }
        }
        fVar.K();
        HashMap map = this.f17797t;
        if (map != null) {
            for (String str : map.keySet()) {
                g8.b.m(this.f17797t, str, fVar, str, iLogger);
            }
        }
        fVar.K();
    }
}
