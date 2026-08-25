package io.sentry.protocol;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import j$.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes5.dex */
public final class j implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f17605i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f17606l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f17607m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ConcurrentHashMap f17608n;

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        if (this.f17605i != null) {
            fVar.S("city");
            fVar.value(this.f17605i);
        }
        if (this.f17606l != null) {
            fVar.S("country_code");
            fVar.value(this.f17606l);
        }
        if (this.f17607m != null) {
            fVar.S(TtmlNode.TAG_REGION);
            fVar.value(this.f17607m);
        }
        ConcurrentHashMap concurrentHashMap = this.f17608n;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.f17608n, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }
}
