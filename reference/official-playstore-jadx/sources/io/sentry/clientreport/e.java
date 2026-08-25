package io.sentry.clientreport;

import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import java.io.IOException;
import java.util.HashMap;

/* JADX INFO: loaded from: classes5.dex */
public final class e implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f17193i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f17194l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Long f17195m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public HashMap f17196n;

    public e(String str, String str2, Long l10) {
        this.f17193i = str;
        this.f17194l = str2;
        this.f17195m = l10;
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) throws IOException {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        fVar.S("reason");
        fVar.value(this.f17193i);
        fVar.S("category");
        fVar.value(this.f17194l);
        fVar.S("quantity");
        fVar.d0(this.f17195m);
        HashMap map = this.f17196n;
        if (map != null) {
            for (String str : map.keySet()) {
                g8.b.m(this.f17196n, str, fVar, str, iLogger);
            }
        }
        fVar.K();
    }

    public final String toString() {
        return "DiscardedEvent{reason='" + this.f17193i + "', category='" + this.f17194l + "', quantity=" + this.f17195m + '}';
    }
}
