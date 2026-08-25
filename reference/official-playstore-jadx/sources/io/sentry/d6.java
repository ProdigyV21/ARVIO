package io.sentry;

import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class d6 implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final List f17214i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public HashMap f17215l;

    public d6(List list) {
        this.f17214i = list;
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        fVar.S("items");
        fVar.b0(iLogger, this.f17214i);
        HashMap map = this.f17215l;
        if (map != null) {
            for (String str : map.keySet()) {
                g8.b.m(this.f17215l, str, fVar, str, iLogger);
            }
        }
        fVar.K();
    }
}
