package io.sentry.protocol;

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
public final class l implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f17634i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f17635l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f17636m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public AbstractMap f17637n;

    public /* synthetic */ l() {
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) throws IOException {
        switch (this.f17634i) {
            case 0:
                fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
                fVar.j();
                fVar.S("value");
                fVar.d0((Number) this.f17636m);
                String str = this.f17635l;
                if (str != null) {
                    fVar.S("unit");
                    fVar.value(str);
                }
                ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) this.f17637n;
                if (concurrentHashMap != null) {
                    for (K k : concurrentHashMap.keySet()) {
                        g8.b.n((ConcurrentHashMap) this.f17637n, k, fVar, k, iLogger);
                    }
                }
                fVar.K();
                break;
            default:
                fi.iki.elonen.f fVar2 = (fi.iki.elonen.f) l3Var;
                fVar2.j();
                fVar2.S(LinkHeader.Parameters.Type);
                fVar2.b0(iLogger, this.f17635l);
                fVar2.S("value");
                fVar2.b0(iLogger, this.f17636m);
                HashMap map = (HashMap) this.f17637n;
                if (map != null) {
                    for (String str2 : map.keySet()) {
                        g8.b.m((HashMap) this.f17637n, str2, fVar2, str2, iLogger);
                    }
                }
                fVar2.K();
                break;
        }
    }

    public l(String str, Number number) {
        this.f17636m = number;
        this.f17635l = str;
    }
}
