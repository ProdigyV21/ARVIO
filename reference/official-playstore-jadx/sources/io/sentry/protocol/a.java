package io.sentry.protocol;

import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class a implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f17529i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Date f17530l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f17531m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f17532n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public String f17533o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f17534p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String f17535q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public AbstractMap f17536r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public List f17537s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public String f17538t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Boolean f17539u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Boolean f17540v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public List f17541w;
    public ConcurrentHashMap x;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || a.class != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        return ac.b.C(this.f17529i, aVar.f17529i) && ac.b.C(this.f17530l, aVar.f17530l) && ac.b.C(this.f17531m, aVar.f17531m) && ac.b.C(this.f17532n, aVar.f17532n) && ac.b.C(this.f17533o, aVar.f17533o) && ac.b.C(this.f17534p, aVar.f17534p) && ac.b.C(this.f17535q, aVar.f17535q) && ac.b.C(this.f17536r, aVar.f17536r) && ac.b.C(this.f17539u, aVar.f17539u) && ac.b.C(this.f17537s, aVar.f17537s) && ac.b.C(this.f17538t, aVar.f17538t) && ac.b.C(this.f17540v, aVar.f17540v) && ac.b.C(this.f17541w, aVar.f17541w);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f17529i, this.f17530l, this.f17531m, this.f17532n, this.f17533o, this.f17534p, this.f17535q, this.f17536r, this.f17539u, this.f17537s, this.f17538t, this.f17540v, this.f17541w});
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) throws IOException {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        if (this.f17529i != null) {
            fVar.S("app_identifier");
            fVar.value(this.f17529i);
        }
        if (this.f17530l != null) {
            fVar.S("app_start_time");
            fVar.b0(iLogger, this.f17530l);
        }
        if (this.f17531m != null) {
            fVar.S("device_app_hash");
            fVar.value(this.f17531m);
        }
        if (this.f17532n != null) {
            fVar.S("build_type");
            fVar.value(this.f17532n);
        }
        if (this.f17533o != null) {
            fVar.S("app_name");
            fVar.value(this.f17533o);
        }
        if (this.f17534p != null) {
            fVar.S("app_version");
            fVar.value(this.f17534p);
        }
        if (this.f17535q != null) {
            fVar.S("app_build");
            fVar.value(this.f17535q);
        }
        AbstractMap abstractMap = this.f17536r;
        if (abstractMap != null && !abstractMap.isEmpty()) {
            fVar.S("permissions");
            fVar.b0(iLogger, this.f17536r);
        }
        if (this.f17539u != null) {
            fVar.S("in_foreground");
            fVar.c0(this.f17539u);
        }
        if (this.f17537s != null) {
            fVar.S("view_names");
            fVar.b0(iLogger, this.f17537s);
        }
        if (this.f17538t != null) {
            fVar.S("start_type");
            fVar.value(this.f17538t);
        }
        if (this.f17540v != null) {
            fVar.S("is_split_apks");
            fVar.c0(this.f17540v);
        }
        List list = this.f17541w;
        if (list != null && !list.isEmpty()) {
            fVar.S("split_names");
            fVar.b0(iLogger, this.f17541w);
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
