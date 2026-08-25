package io.sentry.rrweb;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.LinkHeader;
import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import java.io.IOException;
import java.util.HashMap;

/* JADX INFO: loaded from: classes5.dex */
public final class g extends e implements i2, k2 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public f f17803n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f17804o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f17805p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f17806q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f17807r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f17808s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public HashMap f17809t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public HashMap f17810u;

    public g() {
        super(d.MouseInteraction);
        this.f17807r = 2;
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) throws IOException {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        fVar.S(LinkHeader.Parameters.Type);
        fVar.b0(iLogger, this.f17800i);
        fVar.S("timestamp");
        fVar.d(this.f17801l);
        fVar.S("data");
        fVar.j();
        fVar.S("source");
        fVar.b0(iLogger, this.f17802m);
        fVar.S(LinkHeader.Parameters.Type);
        fVar.b0(iLogger, this.f17803n);
        fVar.S(TtmlNode.ATTR_ID);
        fVar.d(this.f17804o);
        fVar.S("x");
        fVar.a0(this.f17805p);
        fVar.S("y");
        fVar.a0(this.f17806q);
        fVar.S("pointerType");
        fVar.d(this.f17807r);
        fVar.S("pointerId");
        fVar.d(this.f17808s);
        HashMap map = this.f17810u;
        if (map != null) {
            for (String str : map.keySet()) {
                g8.b.m(this.f17810u, str, fVar, str, iLogger);
            }
        }
        fVar.K();
        HashMap map2 = this.f17809t;
        if (map2 != null) {
            for (String str2 : map2.keySet()) {
                g8.b.m(this.f17809t, str2, fVar, str2, iLogger);
            }
        }
        fVar.K();
    }
}
