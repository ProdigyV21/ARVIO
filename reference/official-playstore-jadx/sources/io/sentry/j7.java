package io.sentry;

import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes4.dex */
public final class j7 implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Date f17402i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Date f17403l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final AtomicInteger f17404m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f17405n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final String f17406o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Boolean f17407p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public i7 f17408q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Long f17409r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Double f17410s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final String f17411t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public String f17412u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final String f17413v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final String f17414w;
    public String x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final io.sentry.util.b f17415y = new io.sentry.util.b();
    public ConcurrentHashMap z;

    public j7(i7 i7Var, Date date, Date date2, int i10, String str, String str2, Boolean bool, Long l10, Double d4, String str3, String str4, String str5, String str6, String str7) {
        this.f17408q = i7Var;
        this.f17402i = date;
        this.f17403l = date2;
        this.f17404m = new AtomicInteger(i10);
        this.f17405n = str;
        this.f17406o = str2;
        this.f17407p = bool;
        this.f17409r = l10;
        this.f17410s = d4;
        this.f17411t = str3;
        this.f17412u = str4;
        this.f17413v = str5;
        this.f17414w = str6;
        this.x = str7;
    }

    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final j7 clone() {
        return new j7(this.f17408q, this.f17402i, this.f17403l, this.f17404m.get(), this.f17405n, this.f17406o, this.f17407p, this.f17409r, this.f17410s, this.f17411t, this.f17412u, this.f17413v, this.f17414w, this.x);
    }

    public final void b(Date date) {
        io.sentry.util.a aVarA = this.f17415y.a();
        try {
            this.f17407p = null;
            if (this.f17408q == i7.Ok) {
                this.f17408q = i7.Exited;
            }
            if (date != null) {
                this.f17403l = date;
            } else {
                this.f17403l = k2.c.m();
            }
            if (this.f17403l != null) {
                this.f17410s = Double.valueOf(Math.abs(r6.getTime() - this.f17402i.getTime()) / 1000.0d);
                long time = this.f17403l.getTime();
                if (time < 0) {
                    time = Math.abs(time);
                }
                this.f17409r = Long.valueOf(time);
            }
            aVarA.close();
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public final Date c() {
        Date date = this.f17402i;
        if (date == null) {
            return null;
        }
        return (Date) date.clone();
    }

    public final boolean d(i7 i7Var, String str, boolean z, String str2) {
        boolean z5;
        io.sentry.util.a aVarA = this.f17415y.a();
        boolean z10 = true;
        if (i7Var != null) {
            try {
                this.f17408q = i7Var;
                z5 = true;
            } catch (Throwable th) {
                try {
                    aVarA.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } else {
            z5 = false;
        }
        if (str != null) {
            this.f17412u = str;
            z5 = true;
        }
        if (z) {
            this.f17404m.addAndGet(1);
            z5 = true;
        }
        if (str2 != null) {
            this.x = str2;
        } else {
            z10 = z5;
        }
        if (z10) {
            this.f17407p = null;
            Date dateM = k2.c.m();
            this.f17403l = dateM;
            if (dateM != null) {
                long time = dateM.getTime();
                if (time < 0) {
                    time = Math.abs(time);
                }
                this.f17409r = Long.valueOf(time);
            }
        }
        aVarA.close();
        return z10;
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) throws IOException {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        String str = this.f17406o;
        if (str != null) {
            fVar.S(CmcdConfiguration.KEY_SESSION_ID);
            fVar.value(str);
        }
        String str2 = this.f17405n;
        if (str2 != null) {
            fVar.S("did");
            fVar.value(str2);
        }
        if (this.f17407p != null) {
            fVar.S("init");
            fVar.c0(this.f17407p);
        }
        fVar.S("started");
        fVar.b0(iLogger, this.f17402i);
        fVar.S("status");
        fVar.b0(iLogger, this.f17408q.name().toLowerCase(Locale.ROOT));
        if (this.f17409r != null) {
            fVar.S("seq");
            fVar.d0(this.f17409r);
        }
        fVar.S("errors");
        fVar.d(this.f17404m.intValue());
        if (this.f17410s != null) {
            fVar.S("duration");
            fVar.d0(this.f17410s);
        }
        if (this.f17403l != null) {
            fVar.S("timestamp");
            fVar.b0(iLogger, this.f17403l);
        }
        if (this.x != null) {
            fVar.S("abnormal_mechanism");
            fVar.b0(iLogger, this.x);
        }
        fVar.S("attrs");
        fVar.j();
        fVar.S("release");
        fVar.b0(iLogger, this.f17414w);
        String str3 = this.f17413v;
        if (str3 != null) {
            fVar.S("environment");
            fVar.b0(iLogger, str3);
        }
        String str4 = this.f17411t;
        if (str4 != null) {
            fVar.S("ip_address");
            fVar.b0(iLogger, str4);
        }
        if (this.f17412u != null) {
            fVar.S("user_agent");
            fVar.b0(iLogger, this.f17412u);
        }
        fVar.K();
        ConcurrentHashMap concurrentHashMap = this.z;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.z, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }
}
