package io.sentry.profilemeasurements;

import fi.iki.elonen.f;
import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/* JADX INFO: loaded from: classes5.dex */
public final class a implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ConcurrentHashMap f17522i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f17523l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Collection f17524m;

    public a(String str, AbstractCollection abstractCollection) {
        this.f17523l = str;
        this.f17524m = abstractCollection;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || a.class != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        return ac.b.C(this.f17522i, aVar.f17522i) && this.f17523l.equals(aVar.f17523l) && new ArrayList(this.f17524m).equals(new ArrayList(aVar.f17524m));
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f17522i, this.f17523l, this.f17524m});
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        f fVar = (f) l3Var;
        fVar.j();
        fVar.S("unit");
        fVar.b0(iLogger, this.f17523l);
        fVar.S("values");
        fVar.b0(iLogger, this.f17524m);
        ConcurrentHashMap concurrentHashMap = this.f17522i;
        if (concurrentHashMap != null) {
            for (K k : concurrentHashMap.keySet()) {
                g8.b.n(this.f17522i, k, fVar, k, iLogger);
            }
        }
        fVar.K();
    }
}
