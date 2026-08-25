package io.sentry;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaTrack;
import j$.util.concurrent.ConcurrentHashMap;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class l7 implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final io.sentry.protocol.v f17435i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final o7 f17436l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final o7 f17437m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public transient androidx.core.provider.e f17438n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final String f17439o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f17440p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public q7 f17441q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ConcurrentHashMap f17442r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public String f17443s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Map f17444t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public ConcurrentHashMap f17445u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public r1 f17446v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public d f17447w;
    public final a8.e x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final io.sentry.protocol.v f17448y;

    public l7(io.sentry.protocol.v vVar, o7 o7Var, String str, o7 o7Var2) {
        this(vVar, o7Var, o7Var2, str, null, null, null, "manual");
    }

    public final void a(androidx.core.provider.e eVar) {
        this.f17438n = eVar;
        d dVar = this.f17447w;
        if (dVar == null || eVar == null) {
            return;
        }
        Boolean bool = (Boolean) eVar.f2140l;
        Charset charset = io.sentry.util.s.f17974a;
        dVar.b("sentry-sampled", bool == null ? null : bool.toString());
        Double d4 = (Double) eVar.f2142n;
        if (d4 != null && dVar.f17208e) {
            dVar.f17207d = d4;
        }
        Double d10 = (Double) eVar.f2141m;
        if (d10 != null) {
            dVar.f17206c = d10;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof l7)) {
            return false;
        }
        l7 l7Var = (l7) obj;
        return this.f17435i.equals(l7Var.f17435i) && this.f17436l.equals(l7Var.f17436l) && ac.b.C(this.f17437m, l7Var.f17437m) && this.f17439o.equals(l7Var.f17439o) && ac.b.C(this.f17440p, l7Var.f17440p) && this.f17441q == l7Var.f17441q;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f17435i, this.f17436l, this.f17437m, this.f17439o, this.f17440p, this.f17441q});
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        fVar.S("trace_id");
        this.f17435i.serialize(fVar, iLogger);
        fVar.S("span_id");
        this.f17436l.serialize(fVar, iLogger);
        o7 o7Var = this.f17437m;
        if (o7Var != null) {
            fVar.S("parent_span_id");
            o7Var.serialize(fVar, iLogger);
        }
        fVar.S("op");
        fVar.value(this.f17439o);
        if (this.f17440p != null) {
            fVar.S(MediaTrack.ROLE_DESCRIPTION);
            fVar.value(this.f17440p);
        }
        if (this.f17441q != null) {
            fVar.S("status");
            fVar.b0(iLogger, this.f17441q);
        }
        if (this.f17443s != null) {
            fVar.S(TtmlNode.ATTR_TTS_ORIGIN);
            fVar.b0(iLogger, this.f17443s);
        }
        if (!this.f17442r.isEmpty()) {
            fVar.S("tags");
            fVar.b0(iLogger, this.f17442r);
        }
        if (!this.f17444t.isEmpty()) {
            fVar.S("data");
            fVar.b0(iLogger, this.f17444t);
        }
        ConcurrentHashMap concurrentHashMap = this.f17445u;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.f17445u, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }

    public l7(io.sentry.protocol.v vVar, o7 o7Var, o7 o7Var2, String str, String str2, androidx.core.provider.e eVar, q7 q7Var, String str3) {
        this.f17442r = new ConcurrentHashMap();
        this.f17443s = "manual";
        this.f17444t = new ConcurrentHashMap();
        this.f17446v = r1.SENTRY;
        this.x = new a8.e(28);
        this.f17448y = io.sentry.protocol.v.f17707l;
        ac.b.V(vVar, "traceId is required");
        this.f17435i = vVar;
        ac.b.V(o7Var, "spanId is required");
        this.f17436l = o7Var;
        ac.b.V(str, "operation is required");
        this.f17439o = str;
        this.f17437m = o7Var2;
        this.f17440p = str2;
        this.f17441q = q7Var;
        this.f17443s = str3;
        a(eVar);
        io.sentry.util.thread.a threadChecker = s4.b().getOptions().getThreadChecker();
        this.f17444t.put("thread.id", String.valueOf(threadChecker.c()));
        this.f17444t.put("thread.name", threadChecker.b());
    }

    public l7(l7 l7Var) {
        this.f17442r = new ConcurrentHashMap();
        this.f17443s = "manual";
        this.f17444t = new ConcurrentHashMap();
        this.f17446v = r1.SENTRY;
        this.x = new a8.e(28);
        this.f17448y = io.sentry.protocol.v.f17707l;
        this.f17435i = l7Var.f17435i;
        this.f17436l = l7Var.f17436l;
        this.f17437m = l7Var.f17437m;
        a(l7Var.f17438n);
        this.f17439o = l7Var.f17439o;
        this.f17440p = l7Var.f17440p;
        this.f17441q = l7Var.f17441q;
        ConcurrentHashMap concurrentHashMapW = k2.c.w(l7Var.f17442r);
        if (concurrentHashMapW != null) {
            this.f17442r = concurrentHashMapW;
        }
        ConcurrentHashMap concurrentHashMapW2 = k2.c.w(l7Var.f17445u);
        if (concurrentHashMapW2 != null) {
            this.f17445u = concurrentHashMapW2;
        }
        this.f17447w = l7Var.f17447w;
        ConcurrentHashMap concurrentHashMapW3 = k2.c.w(l7Var.f17444t);
        if (concurrentHashMapW3 != null) {
            this.f17444t = concurrentHashMapW3;
        }
    }
}
