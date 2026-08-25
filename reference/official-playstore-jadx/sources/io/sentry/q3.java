package io.sentry;

import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class q3 implements k2, i2 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public io.sentry.protocol.v f17749l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public io.sentry.protocol.v f17750m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public io.sentry.protocol.t f17751n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final Map f17752o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f17753p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String f17754q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public String f17755r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public String f17756s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public double f17757t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final File f17758u;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public io.sentry.protocol.profiling.a f17760w;
    public ConcurrentHashMap x;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public String f17759v = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public io.sentry.protocol.d f17748i = null;

    public q3(io.sentry.protocol.v vVar, io.sentry.protocol.v vVar2, File file, Map map, Double d4, String str, y6 y6Var) {
        this.f17749l = vVar;
        this.f17750m = vVar2;
        this.f17758u = file;
        this.f17752o = map;
        this.f17751n = y6Var.getSdkVersion();
        this.f17754q = y6Var.getRelease() != null ? y6Var.getRelease() : "";
        this.f17755r = y6Var.getEnvironment();
        this.f17753p = str;
        this.f17756s = "2";
        this.f17757t = d4.doubleValue();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof q3)) {
            return false;
        }
        q3 q3Var = (q3) obj;
        return Objects.equals(this.f17748i, q3Var.f17748i) && Objects.equals(this.f17749l, q3Var.f17749l) && Objects.equals(this.f17750m, q3Var.f17750m) && Objects.equals(this.f17751n, q3Var.f17751n) && Objects.equals(this.f17752o, q3Var.f17752o) && Objects.equals(this.f17753p, q3Var.f17753p) && Objects.equals(this.f17754q, q3Var.f17754q) && Objects.equals(this.f17755r, q3Var.f17755r) && Objects.equals(this.f17756s, q3Var.f17756s) && Objects.equals(this.f17759v, q3Var.f17759v) && Objects.equals(this.x, q3Var.x) && Objects.equals(this.f17760w, q3Var.f17760w);
    }

    public final int hashCode() {
        return Objects.hash(this.f17748i, this.f17749l, this.f17750m, this.f17751n, this.f17752o, this.f17753p, this.f17754q, this.f17755r, this.f17756s, this.f17759v, this.f17760w, this.x);
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        if (this.f17748i != null) {
            fVar.S("debug_meta");
            fVar.b0(iLogger, this.f17748i);
        }
        fVar.S("profiler_id");
        fVar.b0(iLogger, this.f17749l);
        fVar.S("chunk_id");
        fVar.b0(iLogger, this.f17750m);
        if (this.f17751n != null) {
            fVar.S("client_sdk");
            fVar.b0(iLogger, this.f17751n);
        }
        Map map = this.f17752o;
        if (!map.isEmpty()) {
            String str = ((io.sentry.vendor.gson.stream.c) fVar.f15416i).f18022n;
            fVar.Y("");
            fVar.S("measurements");
            fVar.b0(iLogger, map);
            fVar.Y(str);
        }
        fVar.S("platform");
        fVar.b0(iLogger, this.f17753p);
        fVar.S("release");
        fVar.b0(iLogger, this.f17754q);
        if (this.f17755r != null) {
            fVar.S("environment");
            fVar.b0(iLogger, this.f17755r);
        }
        fVar.S("version");
        fVar.b0(iLogger, this.f17756s);
        if (this.f17759v != null) {
            fVar.S("sampled_profile");
            fVar.b0(iLogger, this.f17759v);
        }
        fVar.S("timestamp");
        fVar.b0(iLogger, BigDecimal.valueOf(this.f17757t).setScale(6, RoundingMode.DOWN));
        if (this.f17760w != null) {
            fVar.S("profile");
            fVar.b0(iLogger, this.f17760w);
        }
        ConcurrentHashMap concurrentHashMap = this.x;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.x, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }
}
