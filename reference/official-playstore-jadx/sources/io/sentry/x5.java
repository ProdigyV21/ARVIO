package io.sentry;

import io.ktor.http.LinkHeader;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class x5 implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f18054i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f18055l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f18056m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f18057n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Long f18058o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ConcurrentHashMap f18059p;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || x5.class != obj.getClass()) {
            return false;
        }
        return ac.b.C(this.f18055l, ((x5) obj).f18055l);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f18055l});
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) throws IOException {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        fVar.S(LinkHeader.Parameters.Type);
        fVar.d(this.f18054i);
        if (this.f18055l != null) {
            fVar.S("address");
            fVar.value(this.f18055l);
        }
        if (this.f18056m != null) {
            fVar.S("package_name");
            fVar.value(this.f18056m);
        }
        if (this.f18057n != null) {
            fVar.S("class_name");
            fVar.value(this.f18057n);
        }
        if (this.f18058o != null) {
            fVar.S("thread_id");
            fVar.d0(this.f18058o);
        }
        ConcurrentHashMap concurrentHashMap = this.f18059p;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.f18059p, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }
}
