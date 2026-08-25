package io.sentry.protocol;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.ContentDisposition;
import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class d0 implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Long f17556i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Integer f17557l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f17558m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f17559n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Boolean f17560o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Boolean f17561p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Boolean f17562q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Boolean f17563r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public b0 f17564s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Map f17565t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public ConcurrentHashMap f17566u;

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) throws IOException {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        if (this.f17556i != null) {
            fVar.S(TtmlNode.ATTR_ID);
            fVar.d0(this.f17556i);
        }
        if (this.f17557l != null) {
            fVar.S("priority");
            fVar.d0(this.f17557l);
        }
        if (this.f17558m != null) {
            fVar.S(ContentDisposition.Parameters.Name);
            fVar.value(this.f17558m);
        }
        if (this.f17559n != null) {
            fVar.S("state");
            fVar.value(this.f17559n);
        }
        if (this.f17560o != null) {
            fVar.S("crashed");
            fVar.c0(this.f17560o);
        }
        if (this.f17561p != null) {
            fVar.S("current");
            fVar.c0(this.f17561p);
        }
        if (this.f17562q != null) {
            fVar.S("daemon");
            fVar.c0(this.f17562q);
        }
        if (this.f17563r != null) {
            fVar.S(MediaTrack.ROLE_MAIN);
            fVar.c0(this.f17563r);
        }
        if (this.f17564s != null) {
            fVar.S("stacktrace");
            fVar.b0(iLogger, this.f17564s);
        }
        if (this.f17565t != null) {
            fVar.S("held_locks");
            fVar.b0(iLogger, this.f17565t);
        }
        ConcurrentHashMap concurrentHashMap = this.f17566u;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.f17566u, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }
}
