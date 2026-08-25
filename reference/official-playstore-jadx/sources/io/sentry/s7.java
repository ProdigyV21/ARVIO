package io.sentry;

import j$.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes4.dex */
public final class s7 implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final io.sentry.protocol.v f17857i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f17858l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f17859m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f17860n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final String f17861o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final String f17862p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final String f17863q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final String f17864r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final String f17865s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final io.sentry.protocol.v f17866t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public ConcurrentHashMap f17867u;

    public s7(io.sentry.protocol.v vVar, String str, String str2, String str3, String str4, String str5, String str6, String str7, io.sentry.protocol.v vVar2, String str8) {
        this.f17857i = vVar;
        this.f17858l = str;
        this.f17859m = str2;
        this.f17860n = str3;
        this.f17861o = str4;
        this.f17862p = str5;
        this.f17863q = str6;
        this.f17865s = str7;
        this.f17866t = vVar2;
        this.f17864r = str8;
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        fVar.S("trace_id");
        fVar.b0(iLogger, this.f17857i);
        fVar.S("public_key");
        fVar.value(this.f17858l);
        String str = this.f17859m;
        if (str != null) {
            fVar.S("release");
            fVar.value(str);
        }
        String str2 = this.f17860n;
        if (str2 != null) {
            fVar.S("environment");
            fVar.value(str2);
        }
        String str3 = this.f17861o;
        if (str3 != null) {
            fVar.S("user_id");
            fVar.value(str3);
        }
        String str4 = this.f17862p;
        if (str4 != null) {
            fVar.S("transaction");
            fVar.value(str4);
        }
        String str5 = this.f17863q;
        if (str5 != null) {
            fVar.S("sample_rate");
            fVar.value(str5);
        }
        String str6 = this.f17864r;
        if (str6 != null) {
            fVar.S("sample_rand");
            fVar.value(str6);
        }
        String str7 = this.f17865s;
        if (str7 != null) {
            fVar.S("sampled");
            fVar.value(str7);
        }
        io.sentry.protocol.v vVar = this.f17866t;
        if (vVar != null) {
            fVar.S("replay_id");
            fVar.b0(iLogger, vVar);
        }
        ConcurrentHashMap concurrentHashMap = this.f17867u;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.f17867u, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }
}
