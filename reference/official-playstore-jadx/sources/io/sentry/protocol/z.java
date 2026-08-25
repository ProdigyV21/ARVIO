package io.sentry.protocol;

import io.ktor.http.ContentDisposition;
import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import io.sentry.x5;
import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class z implements k2, i2 {
    public String A;
    public String B;
    public String C;
    public ConcurrentHashMap D;
    public String E;
    public x5 F;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public List f17729i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public List f17730l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Map f17731m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f17732n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public String f17733o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f17734p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Integer f17735q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Integer f17736r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public String f17737s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public String f17738t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Boolean f17739u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public String f17740v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public Boolean f17741w;
    public String x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public String f17742y;
    public String z;

    public final boolean equals(Object obj) {
        if (obj == null || z.class != obj.getClass()) {
            return false;
        }
        z zVar = (z) obj;
        return Objects.equals(this.f17729i, zVar.f17729i) && Objects.equals(this.f17730l, zVar.f17730l) && Objects.equals(this.f17731m, zVar.f17731m) && Objects.equals(this.f17732n, zVar.f17732n) && Objects.equals(this.f17733o, zVar.f17733o) && Objects.equals(this.f17734p, zVar.f17734p) && Objects.equals(this.f17735q, zVar.f17735q) && Objects.equals(this.f17736r, zVar.f17736r) && Objects.equals(this.f17737s, zVar.f17737s) && Objects.equals(this.f17738t, zVar.f17738t) && Objects.equals(this.f17739u, zVar.f17739u) && Objects.equals(this.f17740v, zVar.f17740v) && Objects.equals(this.f17741w, zVar.f17741w) && Objects.equals(this.x, zVar.x) && Objects.equals(this.f17742y, zVar.f17742y) && Objects.equals(this.z, zVar.z) && Objects.equals(this.A, zVar.A) && Objects.equals(this.B, zVar.B) && Objects.equals(this.C, zVar.C) && Objects.equals(this.D, zVar.D) && Objects.equals(this.E, zVar.E) && Objects.equals(this.F, zVar.F);
    }

    public final int hashCode() {
        return Objects.hash(this.f17729i, this.f17730l, this.f17731m, null, this.f17732n, this.f17733o, this.f17734p, this.f17735q, this.f17736r, this.f17737s, this.f17738t, this.f17739u, this.f17740v, this.f17741w, this.x, this.f17742y, this.z, this.A, this.B, this.C, this.D, this.E, this.F);
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) throws IOException {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        if (this.f17732n != null) {
            fVar.S(ContentDisposition.Parameters.FileName);
            fVar.value(this.f17732n);
        }
        if (this.f17733o != null) {
            fVar.S("function");
            fVar.value(this.f17733o);
        }
        if (this.f17734p != null) {
            fVar.S("module");
            fVar.value(this.f17734p);
        }
        if (this.f17735q != null) {
            fVar.S("lineno");
            fVar.d0(this.f17735q);
        }
        if (this.f17736r != null) {
            fVar.S("colno");
            fVar.d0(this.f17736r);
        }
        if (this.f17737s != null) {
            fVar.S("abs_path");
            fVar.value(this.f17737s);
        }
        if (this.f17738t != null) {
            fVar.S("context_line");
            fVar.value(this.f17738t);
        }
        if (this.f17739u != null) {
            fVar.S("in_app");
            fVar.c0(this.f17739u);
        }
        if (this.f17740v != null) {
            fVar.S("package");
            fVar.value(this.f17740v);
        }
        if (this.f17741w != null) {
            fVar.S("native");
            fVar.c0(this.f17741w);
        }
        if (this.x != null) {
            fVar.S("platform");
            fVar.value(this.x);
        }
        if (this.f17742y != null) {
            fVar.S("image_addr");
            fVar.value(this.f17742y);
        }
        if (this.z != null) {
            fVar.S("symbol_addr");
            fVar.value(this.z);
        }
        if (this.A != null) {
            fVar.S("instruction_addr");
            fVar.value(this.A);
        }
        if (this.B != null) {
            fVar.S("addr_mode");
            fVar.value(this.B);
        }
        if (this.E != null) {
            fVar.S("raw_function");
            fVar.value(this.E);
        }
        if (this.C != null) {
            fVar.S("symbol");
            fVar.value(this.C);
        }
        if (this.F != null) {
            fVar.S("lock");
            fVar.b0(iLogger, this.F);
        }
        List list = this.f17729i;
        if (list != null && !list.isEmpty()) {
            fVar.S("pre_context");
            fVar.b0(iLogger, this.f17729i);
        }
        List list2 = this.f17730l;
        if (list2 != null && !list2.isEmpty()) {
            fVar.S("post_context");
            fVar.b0(iLogger, this.f17730l);
        }
        Map map = this.f17731m;
        if (map != null && !map.isEmpty()) {
            fVar.S("vars");
            fVar.b0(iLogger, this.f17731m);
        }
        ConcurrentHashMap concurrentHashMap = this.D;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.D, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }
}
