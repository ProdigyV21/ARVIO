package io.sentry.protocol;

import io.ktor.http.LinkHeader;
import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class k0 implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f17622i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f17623l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f17624m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f17625n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Double f17626o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Double f17627p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Double f17628q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Double f17629r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public String f17630s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Double f17631t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public List f17632u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public HashMap f17633v;

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) throws IOException {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        if (this.f17622i != null) {
            fVar.S("rendering_system");
            fVar.value(this.f17622i);
        }
        if (this.f17623l != null) {
            fVar.S(LinkHeader.Parameters.Type);
            fVar.value(this.f17623l);
        }
        if (this.f17624m != null) {
            fVar.S("identifier");
            fVar.value(this.f17624m);
        }
        if (this.f17625n != null) {
            fVar.S("tag");
            fVar.value(this.f17625n);
        }
        if (this.f17626o != null) {
            fVar.S("width");
            fVar.d0(this.f17626o);
        }
        if (this.f17627p != null) {
            fVar.S("height");
            fVar.d0(this.f17627p);
        }
        if (this.f17628q != null) {
            fVar.S("x");
            fVar.d0(this.f17628q);
        }
        if (this.f17629r != null) {
            fVar.S("y");
            fVar.d0(this.f17629r);
        }
        if (this.f17630s != null) {
            fVar.S("visibility");
            fVar.value(this.f17630s);
        }
        if (this.f17631t != null) {
            fVar.S("alpha");
            fVar.d0(this.f17631t);
        }
        List list = this.f17632u;
        if (list != null && !list.isEmpty()) {
            fVar.S("children");
            fVar.b0(iLogger, this.f17632u);
        }
        HashMap map = this.f17633v;
        if (map != null) {
            for (String str : map.keySet()) {
                g8.b.m(this.f17633v, str, fVar, str, iLogger);
            }
        }
        fVar.K();
    }
}
