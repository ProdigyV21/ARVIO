package io.sentry;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.LinkHeader;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Arrays;
import java.util.Date;

/* JADX INFO: loaded from: classes4.dex */
public final class f implements k2, i2, Comparable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Long f17243i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Date f17244l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Long f17245m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f17246n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public String f17247o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ConcurrentHashMap f17248p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String f17249q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public String f17250r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public w5 f17251s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public ConcurrentHashMap f17252t;

    public f(Date date) {
        this.f17248p = new ConcurrentHashMap();
        this.f17245m = Long.valueOf(System.nanoTime());
        this.f17244l = date;
        this.f17243i = null;
    }

    public static boolean a(f fVar, f fVar2) {
        return fVar.d().getTime() == fVar2.d().getTime() && ac.b.C(fVar.f17246n, fVar2.f17246n) && ac.b.C(fVar.f17247o, fVar2.f17247o) && ac.b.C(fVar.f17249q, fVar2.f17249q) && ac.b.C(fVar.f17250r, fVar2.f17250r) && fVar.f17251s == fVar2.f17251s;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.f17245m.compareTo(((f) obj).f17245m);
    }

    public final Date d() {
        Date date = this.f17244l;
        if (date != null) {
            return (Date) date.clone();
        }
        Long l10 = this.f17243i;
        if (l10 == null) {
            throw new IllegalStateException("No timestamp set for breadcrumb");
        }
        Date dateN = k2.c.n(l10.longValue());
        this.f17244l = dateN;
        return dateN;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || f.class != obj.getClass()) {
            return false;
        }
        f fVar = (f) obj;
        return "http".equals(this.f17247o) ? a(this, fVar) && ac.b.C(this.f17248p.get("status_code"), fVar.f17248p.get("status_code")) && ac.b.C(this.f17248p.get("url"), fVar.f17248p.get("url")) && ac.b.C(this.f17248p.get("method"), fVar.f17248p.get("method")) && ac.b.C(this.f17248p.get("http.fragment"), fVar.f17248p.get("http.fragment")) && ac.b.C(this.f17248p.get("http.query"), fVar.f17248p.get("http.query")) : a(this, fVar);
    }

    public final void f(Object obj, String str) {
        if (obj == null) {
            this.f17248p.remove(str);
        } else {
            this.f17248p.put(str, obj);
        }
    }

    public final int hashCode() {
        return "http".equals(this.f17247o) ? Arrays.hashCode(new Object[]{Long.valueOf(d().getTime()), this.f17246n, this.f17247o, this.f17249q, this.f17250r, this.f17251s, this.f17248p.get("status_code"), this.f17248p.get("url"), this.f17248p.get("method"), this.f17248p.get("http.fragment"), this.f17248p.get("http.query")}) : Arrays.hashCode(new Object[]{Long.valueOf(d().getTime()), this.f17246n, this.f17247o, this.f17249q, this.f17250r, this.f17251s});
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        fVar.S("timestamp");
        fVar.b0(iLogger, d());
        if (this.f17246n != null) {
            fVar.S("message");
            fVar.value(this.f17246n);
        }
        if (this.f17247o != null) {
            fVar.S(LinkHeader.Parameters.Type);
            fVar.value(this.f17247o);
        }
        fVar.S("data");
        fVar.b0(iLogger, this.f17248p);
        if (this.f17249q != null) {
            fVar.S("category");
            fVar.value(this.f17249q);
        }
        if (this.f17250r != null) {
            fVar.S(TtmlNode.ATTR_TTS_ORIGIN);
            fVar.value(this.f17250r);
        }
        if (this.f17251s != null) {
            fVar.S("level");
            fVar.b0(iLogger, this.f17251s);
        }
        ConcurrentHashMap concurrentHashMap = this.f17252t;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.f17252t, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }

    public f(long j10) {
        this.f17248p = new ConcurrentHashMap();
        this.f17245m = Long.valueOf(System.nanoTime());
        this.f17243i = Long.valueOf(j10);
        this.f17244l = null;
    }

    public f(f fVar) {
        this.f17248p = new ConcurrentHashMap();
        this.f17245m = Long.valueOf(System.nanoTime());
        this.f17244l = fVar.f17244l;
        this.f17243i = fVar.f17243i;
        this.f17246n = fVar.f17246n;
        this.f17247o = fVar.f17247o;
        this.f17249q = fVar.f17249q;
        this.f17250r = fVar.f17250r;
        ConcurrentHashMap concurrentHashMapW = k2.c.w(fVar.f17248p);
        if (concurrentHashMapW != null) {
            this.f17248p = concurrentHashMapW;
        }
        this.f17252t = k2.c.w(fVar.f17252t);
        this.f17251s = fVar.f17251s;
    }

    public f() {
        this(System.currentTimeMillis());
    }
}
