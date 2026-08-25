package io.sentry.protocol.profiling;

import fi.iki.elonen.f;
import io.ktor.http.ContentDisposition;
import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import java.util.HashMap;

/* JADX INFO: loaded from: classes5.dex */
public final class c implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f17681i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f17682l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public HashMap f17683m;

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        f fVar = (f) l3Var;
        fVar.j();
        if (this.f17681i != null) {
            fVar.S(ContentDisposition.Parameters.Name);
            fVar.b0(iLogger, this.f17681i);
        }
        fVar.S("priority");
        fVar.b0(iLogger, Integer.valueOf(this.f17682l));
        HashMap map = this.f17683m;
        if (map != null) {
            for (String str : map.keySet()) {
                g8.b.m(this.f17683m, str, fVar, str, iLogger);
            }
        }
        fVar.K();
    }
}
