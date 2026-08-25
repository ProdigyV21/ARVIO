package io.sentry.protocol;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaTrack;
import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.k7;
import io.sentry.l3;
import io.sentry.l7;
import io.sentry.o7;
import io.sentry.q7;
import j$.util.concurrent.ConcurrentHashMap;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class y implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Double f17716i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Double f17717l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final v f17718m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final o7 f17719n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final o7 f17720o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final String f17721p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final String f17722q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final q7 f17723r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final String f17724s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final Map f17725t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Map f17726u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final Map f17727v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ConcurrentHashMap f17728w;

    public y(Double d4, Double d10, v vVar, o7 o7Var, o7 o7Var2, String str, String str2, q7 q7Var, String str3, Map map, Map map2, Map map3) {
        this.f17716i = d4;
        this.f17717l = d10;
        this.f17718m = vVar;
        this.f17719n = o7Var;
        this.f17720o = o7Var2;
        this.f17721p = str;
        this.f17722q = str2;
        this.f17723r = q7Var;
        this.f17724s = str3;
        this.f17725t = map;
        this.f17727v = map2;
        this.f17726u = map3;
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        fVar.S("start_timestamp");
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf(this.f17716i.doubleValue());
        RoundingMode roundingMode = RoundingMode.DOWN;
        fVar.b0(iLogger, bigDecimalValueOf.setScale(6, roundingMode));
        Double d4 = this.f17717l;
        if (d4 != null) {
            fVar.S("timestamp");
            fVar.b0(iLogger, BigDecimal.valueOf(d4.doubleValue()).setScale(6, roundingMode));
        }
        fVar.S("trace_id");
        fVar.b0(iLogger, this.f17718m);
        fVar.S("span_id");
        fVar.b0(iLogger, this.f17719n);
        o7 o7Var = this.f17720o;
        if (o7Var != null) {
            fVar.S("parent_span_id");
            fVar.b0(iLogger, o7Var);
        }
        fVar.S("op");
        fVar.value(this.f17721p);
        String str = this.f17722q;
        if (str != null) {
            fVar.S(MediaTrack.ROLE_DESCRIPTION);
            fVar.value(str);
        }
        q7 q7Var = this.f17723r;
        if (q7Var != null) {
            fVar.S("status");
            fVar.b0(iLogger, q7Var);
        }
        String str2 = this.f17724s;
        if (str2 != null) {
            fVar.S(TtmlNode.ATTR_TTS_ORIGIN);
            fVar.b0(iLogger, str2);
        }
        Map map = this.f17725t;
        if (!map.isEmpty()) {
            fVar.S("tags");
            fVar.b0(iLogger, map);
        }
        if (this.f17726u != null) {
            fVar.S("data");
            fVar.b0(iLogger, this.f17726u);
        }
        Map map2 = this.f17727v;
        if (!map2.isEmpty()) {
            fVar.S("measurements");
            fVar.b0(iLogger, map2);
        }
        ConcurrentHashMap concurrentHashMap = this.f17728w;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.f17728w, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }

    public y(k7 k7Var) {
        ConcurrentHashMap concurrentHashMap = k7Var.f17428j;
        l7 l7Var = k7Var.f17421c;
        this.f17722q = l7Var.f17440p;
        this.f17721p = l7Var.f17439o;
        this.f17719n = l7Var.f17436l;
        this.f17720o = l7Var.f17437m;
        this.f17718m = l7Var.f17435i;
        this.f17723r = l7Var.f17441q;
        this.f17724s = l7Var.f17443s;
        ConcurrentHashMap concurrentHashMapW = k2.c.w(l7Var.f17442r);
        this.f17725t = concurrentHashMapW == null ? new ConcurrentHashMap() : concurrentHashMapW;
        ConcurrentHashMap concurrentHashMapW2 = k2.c.w(k7Var.k);
        this.f17727v = concurrentHashMapW2 == null ? new ConcurrentHashMap() : concurrentHashMapW2;
        this.f17717l = k7Var.f17420b == null ? null : Double.valueOf(k7Var.f17419a.f(r2) / 1.0E9d);
        this.f17716i = Double.valueOf(k7Var.f17419a.i() / 1.0E9d);
        this.f17726u = concurrentHashMap;
        l7Var.x.n();
    }
}
