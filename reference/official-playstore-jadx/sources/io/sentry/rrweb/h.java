package io.sentry.rrweb;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import java.io.IOException;
import java.util.HashMap;

/* JADX INFO: loaded from: classes5.dex */
public final class h implements i2, k2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f17811i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f17812l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f17813m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f17814n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public HashMap f17815o;

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) throws IOException {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        fVar.S(TtmlNode.ATTR_ID);
        fVar.d(this.f17811i);
        fVar.S("x");
        fVar.a0(this.f17812l);
        fVar.S("y");
        fVar.a0(this.f17813m);
        fVar.S("timeOffset");
        fVar.d(this.f17814n);
        HashMap map = this.f17815o;
        if (map != null) {
            for (String str : map.keySet()) {
                g8.b.m(this.f17815o, str, fVar, str, iLogger);
            }
        }
        fVar.K();
    }
}
