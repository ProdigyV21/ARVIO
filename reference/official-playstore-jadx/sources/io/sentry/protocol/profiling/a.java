package io.sentry.protocol.profiling;

import fi.iki.elonen.f;
import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class a implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public List f17672i = new ArrayList();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public List f17673l = new ArrayList();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public List f17674m = new ArrayList();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Map f17675n = new HashMap();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public ConcurrentHashMap f17676o;

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        f fVar = (f) l3Var;
        fVar.j();
        fVar.S("samples");
        fVar.b0(iLogger, this.f17672i);
        fVar.S("stacks");
        fVar.b0(iLogger, this.f17673l);
        fVar.S("frames");
        fVar.b0(iLogger, this.f17674m);
        fVar.S("thread_metadata");
        fVar.b0(iLogger, this.f17675n);
        ConcurrentHashMap concurrentHashMap = this.f17676o;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.f17676o, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }
}
