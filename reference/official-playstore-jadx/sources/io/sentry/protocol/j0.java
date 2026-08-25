package io.sentry.protocol;

import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class j0 implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f17609i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final List f17610l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public HashMap f17611m;

    public j0(String str, List list) {
        this.f17609i = str;
        this.f17610l = list;
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        String str = this.f17609i;
        if (str != null) {
            fVar.S("rendering_system");
            fVar.value(str);
        }
        List list = this.f17610l;
        if (list != null) {
            fVar.S("windows");
            fVar.b0(iLogger, list);
        }
        HashMap map = this.f17611m;
        if (map != null) {
            for (String str2 : map.keySet()) {
                g8.b.m(this.f17611m, str2, fVar, str2, iLogger);
            }
        }
        fVar.K();
    }
}
