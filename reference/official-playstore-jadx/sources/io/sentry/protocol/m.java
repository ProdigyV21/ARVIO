package io.sentry.protocol;

import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.LinkHeader;
import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.HashMap;

/* JADX INFO: loaded from: classes5.dex */
public final class m implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f17638i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f17639l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f17640m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Boolean f17641n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public AbstractMap f17642o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ConcurrentHashMap f17643p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Boolean f17644q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Integer f17645r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Integer f17646s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Boolean f17647t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public HashMap f17648u;

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) throws IOException {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        if (this.f17638i != null) {
            fVar.S(LinkHeader.Parameters.Type);
            fVar.value(this.f17638i);
        }
        if (this.f17639l != null) {
            fVar.S(MediaTrack.ROLE_DESCRIPTION);
            fVar.value(this.f17639l);
        }
        if (this.f17640m != null) {
            fVar.S("help_link");
            fVar.value(this.f17640m);
        }
        if (this.f17641n != null) {
            fVar.S("handled");
            fVar.c0(this.f17641n);
        }
        if (this.f17642o != null) {
            fVar.S("meta");
            fVar.b0(iLogger, this.f17642o);
        }
        if (this.f17643p != null) {
            fVar.S("data");
            fVar.b0(iLogger, this.f17643p);
        }
        if (this.f17644q != null) {
            fVar.S("synthetic");
            fVar.c0(this.f17644q);
        }
        if (this.f17645r != null) {
            fVar.S("exception_id");
            fVar.b0(iLogger, this.f17645r);
        }
        if (this.f17646s != null) {
            fVar.S("parent_id");
            fVar.b0(iLogger, this.f17646s);
        }
        if (this.f17647t != null) {
            fVar.S("is_exception_group");
            fVar.c0(this.f17647t);
        }
        HashMap map = this.f17648u;
        if (map != null) {
            for (String str : map.keySet()) {
                g8.b.m(this.f17648u, str, fVar, str, iLogger);
            }
        }
        fVar.K();
    }
}
