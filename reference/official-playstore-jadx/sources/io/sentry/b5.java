package io.sentry;

import java.util.Date;
import java.util.HashMap;

/* JADX INFO: loaded from: classes4.dex */
public final class b5 implements i2, k2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final io.sentry.protocol.v f17100i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final io.sentry.protocol.t f17101l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final s7 f17102m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Date f17103n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public HashMap f17104o;

    public b5(io.sentry.protocol.v vVar, io.sentry.protocol.t tVar, s7 s7Var) {
        this.f17100i = vVar;
        this.f17101l = tVar;
        this.f17102m = s7Var;
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        io.sentry.protocol.v vVar = this.f17100i;
        if (vVar != null) {
            fVar.S("event_id");
            fVar.b0(iLogger, vVar);
        }
        io.sentry.protocol.t tVar = this.f17101l;
        if (tVar != null) {
            fVar.S("sdk");
            fVar.b0(iLogger, tVar);
        }
        s7 s7Var = this.f17102m;
        if (s7Var != null) {
            fVar.S("trace");
            fVar.b0(iLogger, s7Var);
        }
        if (this.f17103n != null) {
            fVar.S("sent_at");
            fVar.b0(iLogger, k2.c.r(this.f17103n));
        }
        HashMap map = this.f17104o;
        if (map != null) {
            for (String str : map.keySet()) {
                g8.b.m(this.f17104o, str, fVar, str, iLogger);
            }
        }
        fVar.K();
    }
}
