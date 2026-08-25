package io.sentry.protocol;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes5.dex */
public final class f implements k2, i2 {
    public Long A;
    public Long B;
    public Long C;
    public Long D;
    public Integer E;
    public Integer F;
    public Float G;
    public Integer H;
    public Date I;
    public TimeZone J;
    public String K;
    public String L;
    public String M;
    public Float N;
    public Integer O;
    public Double P;
    public String Q;
    public String R;
    public ConcurrentHashMap S;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f17567i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f17568l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f17569m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f17570n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public String f17571o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f17572p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String[] f17573q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Float f17574r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Boolean f17575s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Boolean f17576t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public e f17577u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Boolean f17578v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public Long f17579w;
    public Long x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public Long f17580y;
    public Boolean z;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && f.class == obj.getClass()) {
            f fVar = (f) obj;
            if (ac.b.C(this.f17567i, fVar.f17567i) && ac.b.C(this.f17568l, fVar.f17568l) && ac.b.C(this.f17569m, fVar.f17569m) && ac.b.C(this.f17570n, fVar.f17570n) && ac.b.C(this.f17571o, fVar.f17571o) && ac.b.C(this.f17572p, fVar.f17572p) && Arrays.equals(this.f17573q, fVar.f17573q) && ac.b.C(this.f17574r, fVar.f17574r) && ac.b.C(this.f17575s, fVar.f17575s) && ac.b.C(this.f17576t, fVar.f17576t) && this.f17577u == fVar.f17577u && ac.b.C(this.f17578v, fVar.f17578v) && ac.b.C(this.f17579w, fVar.f17579w) && ac.b.C(this.x, fVar.x) && ac.b.C(this.f17580y, fVar.f17580y) && ac.b.C(this.z, fVar.z) && ac.b.C(this.A, fVar.A) && ac.b.C(this.B, fVar.B) && ac.b.C(this.C, fVar.C) && ac.b.C(this.D, fVar.D) && ac.b.C(this.E, fVar.E) && ac.b.C(this.F, fVar.F) && ac.b.C(this.G, fVar.G) && ac.b.C(this.H, fVar.H) && ac.b.C(this.I, fVar.I) && ac.b.C(this.K, fVar.K) && ac.b.C(this.L, fVar.L) && ac.b.C(this.M, fVar.M) && ac.b.C(this.N, fVar.N) && ac.b.C(this.O, fVar.O) && ac.b.C(this.P, fVar.P) && ac.b.C(this.Q, fVar.Q) && ac.b.C(this.R, fVar.R)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (Arrays.hashCode(new Object[]{this.f17567i, this.f17568l, this.f17569m, this.f17570n, this.f17571o, this.f17572p, this.f17574r, this.f17575s, this.f17576t, this.f17577u, this.f17578v, this.f17579w, this.x, this.f17580y, this.z, this.A, this.B, this.C, this.D, this.E, this.F, this.G, this.H, this.I, this.J, this.K, this.L, this.M, this.N, this.O, this.P, this.Q, this.R}) * 31) + Arrays.hashCode(this.f17573q);
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) throws IOException {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        if (this.f17567i != null) {
            fVar.S(ContentDisposition.Parameters.Name);
            fVar.value(this.f17567i);
        }
        if (this.f17568l != null) {
            fVar.S("manufacturer");
            fVar.value(this.f17568l);
        }
        if (this.f17569m != null) {
            fVar.S("brand");
            fVar.value(this.f17569m);
        }
        if (this.f17570n != null) {
            fVar.S("family");
            fVar.value(this.f17570n);
        }
        if (this.f17571o != null) {
            fVar.S("model");
            fVar.value(this.f17571o);
        }
        if (this.f17572p != null) {
            fVar.S("model_id");
            fVar.value(this.f17572p);
        }
        if (this.f17573q != null) {
            fVar.S("archs");
            fVar.b0(iLogger, this.f17573q);
        }
        if (this.f17574r != null) {
            fVar.S("battery_level");
            fVar.d0(this.f17574r);
        }
        if (this.f17575s != null) {
            fVar.S("charging");
            fVar.c0(this.f17575s);
        }
        if (this.f17576t != null) {
            fVar.S("online");
            fVar.c0(this.f17576t);
        }
        if (this.f17577u != null) {
            fVar.S("orientation");
            fVar.b0(iLogger, this.f17577u);
        }
        if (this.f17578v != null) {
            fVar.S("simulator");
            fVar.c0(this.f17578v);
        }
        if (this.f17579w != null) {
            fVar.S("memory_size");
            fVar.d0(this.f17579w);
        }
        if (this.x != null) {
            fVar.S("free_memory");
            fVar.d0(this.x);
        }
        if (this.f17580y != null) {
            fVar.S("usable_memory");
            fVar.d0(this.f17580y);
        }
        if (this.z != null) {
            fVar.S("low_memory");
            fVar.c0(this.z);
        }
        if (this.A != null) {
            fVar.S("storage_size");
            fVar.d0(this.A);
        }
        if (this.B != null) {
            fVar.S("free_storage");
            fVar.d0(this.B);
        }
        if (this.C != null) {
            fVar.S("external_storage_size");
            fVar.d0(this.C);
        }
        if (this.D != null) {
            fVar.S("external_free_storage");
            fVar.d0(this.D);
        }
        if (this.E != null) {
            fVar.S("screen_width_pixels");
            fVar.d0(this.E);
        }
        if (this.F != null) {
            fVar.S("screen_height_pixels");
            fVar.d0(this.F);
        }
        if (this.G != null) {
            fVar.S("screen_density");
            fVar.d0(this.G);
        }
        if (this.H != null) {
            fVar.S("screen_dpi");
            fVar.d0(this.H);
        }
        if (this.I != null) {
            fVar.S("boot_time");
            fVar.b0(iLogger, this.I);
        }
        if (this.J != null) {
            fVar.S("timezone");
            fVar.b0(iLogger, this.J);
        }
        if (this.K != null) {
            fVar.S(TtmlNode.ATTR_ID);
            fVar.value(this.K);
        }
        if (this.M != null) {
            fVar.S("connection_type");
            fVar.value(this.M);
        }
        if (this.N != null) {
            fVar.S("battery_temperature");
            fVar.d0(this.N);
        }
        if (this.L != null) {
            fVar.S("locale");
            fVar.value(this.L);
        }
        if (this.O != null) {
            fVar.S("processor_count");
            fVar.d0(this.O);
        }
        if (this.P != null) {
            fVar.S("processor_frequency");
            fVar.d0(this.P);
        }
        if (this.Q != null) {
            fVar.S("cpu_description");
            fVar.value(this.Q);
        }
        if (this.R != null) {
            fVar.S("chipset");
            fVar.value(this.R);
        }
        ConcurrentHashMap concurrentHashMap = this.S;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.S, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }
}
