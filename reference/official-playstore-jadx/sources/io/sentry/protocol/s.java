package io.sentry.protocol;

import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import java.io.IOException;
import java.util.HashMap;

/* JADX INFO: loaded from: classes5.dex */
public final class s implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f17690i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Integer f17691l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Integer f17692m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Integer f17693n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public HashMap f17694o;

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) throws IOException {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        if (this.f17690i != null) {
            fVar.S("sdk_name");
            fVar.value(this.f17690i);
        }
        if (this.f17691l != null) {
            fVar.S("version_major");
            fVar.d0(this.f17691l);
        }
        if (this.f17692m != null) {
            fVar.S("version_minor");
            fVar.d0(this.f17692m);
        }
        if (this.f17693n != null) {
            fVar.S("version_patchlevel");
            fVar.d0(this.f17693n);
        }
        HashMap map = this.f17694o;
        if (map != null) {
            for (String str : map.keySet()) {
                g8.b.m(this.f17694o, str, fVar, str, iLogger);
            }
        }
        fVar.K();
    }
}
