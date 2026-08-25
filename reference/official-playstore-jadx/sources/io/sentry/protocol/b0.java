package io.sentry.protocol;

import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class b0 implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public List f17545i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public AbstractMap f17546l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Boolean f17547m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public a0 f17548n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public ConcurrentHashMap f17549o;

    public b0(List list) {
        this.f17545i = list;
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) throws IOException {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        if (this.f17545i != null) {
            fVar.S("frames");
            fVar.b0(iLogger, this.f17545i);
        }
        if (this.f17546l != null) {
            fVar.S("registers");
            fVar.b0(iLogger, this.f17546l);
        }
        if (this.f17547m != null) {
            fVar.S("snapshot");
            fVar.c0(this.f17547m);
        }
        if (this.f17548n != null) {
            fVar.S("instruction_addr_adjustment");
            fVar.b0(iLogger, this.f17548n);
        }
        ConcurrentHashMap concurrentHashMap = this.f17549o;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.f17549o, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }
}
