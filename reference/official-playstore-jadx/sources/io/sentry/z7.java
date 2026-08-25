package io.sentry;

import io.ktor.http.ContentDisposition;
import java.util.HashMap;

/* JADX INFO: loaded from: classes4.dex */
public final class z7 implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final io.sentry.protocol.v f18081i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f18082l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f18083m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f18084n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public HashMap f18085o;

    public z7(io.sentry.protocol.v vVar, String str, String str2, String str3) {
        this.f18081i = vVar;
        this.f18082l = str;
        this.f18083m = str2;
        this.f18084n = str3;
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        fVar.S("event_id");
        this.f18081i.serialize(fVar, iLogger);
        String str = this.f18082l;
        if (str != null) {
            fVar.S(ContentDisposition.Parameters.Name);
            fVar.value(str);
        }
        String str2 = this.f18083m;
        if (str2 != null) {
            fVar.S("email");
            fVar.value(str2);
        }
        String str3 = this.f18084n;
        if (str3 != null) {
            fVar.S("comments");
            fVar.value(str3);
        }
        HashMap map = this.f18085o;
        if (map != null) {
            for (String str4 : map.keySet()) {
                g8.b.m(this.f18085o, str4, fVar, str4, iLogger);
            }
        }
        fVar.K();
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("UserFeedback{eventId=");
        sb2.append(this.f18081i);
        sb2.append(", name='");
        sb2.append(this.f18082l);
        sb2.append("', email='");
        sb2.append(this.f18083m);
        sb2.append("', comments='");
        return a0.c.p(sb2, this.f18084n, "'}");
    }
}
