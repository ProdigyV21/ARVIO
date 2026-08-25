package io.sentry.clientreport;

import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/* JADX INFO: loaded from: classes5.dex */
public final class b implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Date f17188i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ArrayList f17189l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public HashMap f17190m;

    public b(Date date, ArrayList arrayList) {
        this.f17188i = date;
        this.f17189l = arrayList;
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        fVar.S("timestamp");
        fVar.value(k2.c.r(this.f17188i));
        fVar.S("discarded_events");
        fVar.b0(iLogger, this.f17189l);
        HashMap map = this.f17190m;
        if (map != null) {
            for (String str : map.keySet()) {
                g8.b.m(this.f17190m, str, fVar, str, iLogger);
            }
        }
        fVar.K();
    }
}
