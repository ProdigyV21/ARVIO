package io.sentry.protocol;

import io.ktor.http.ContentDisposition;
import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import java.util.AbstractMap;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class i implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f17590i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f17591l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f17592m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public v f17593n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public v f17594o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f17595p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public AbstractMap f17596q;

    public i(String str) {
        if (str.length() > 4096) {
            this.f17590i = str.substring(0, 4096);
        } else {
            this.f17590i = str;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        return ac.b.C(this.f17590i, iVar.f17590i) && ac.b.C(this.f17591l, iVar.f17591l) && ac.b.C(this.f17592m, iVar.f17592m) && ac.b.C(this.f17593n, iVar.f17593n) && ac.b.C(this.f17594o, iVar.f17594o) && ac.b.C(this.f17595p, iVar.f17595p) && ac.b.C(this.f17596q, iVar.f17596q);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f17590i, this.f17591l, this.f17592m, this.f17593n, this.f17594o, this.f17595p, this.f17596q});
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        fVar.S("message");
        fVar.value(this.f17590i);
        if (this.f17591l != null) {
            fVar.S("contact_email");
            fVar.value(this.f17591l);
        }
        if (this.f17592m != null) {
            fVar.S(ContentDisposition.Parameters.Name);
            fVar.value(this.f17592m);
        }
        if (this.f17593n != null) {
            fVar.S("associated_event_id");
            this.f17593n.serialize(fVar, iLogger);
        }
        if (this.f17594o != null) {
            fVar.S("replay_id");
            this.f17594o.serialize(fVar, iLogger);
        }
        if (this.f17595p != null) {
            fVar.S("url");
            fVar.value(this.f17595p);
        }
        AbstractMap abstractMap = this.f17596q;
        if (abstractMap != null) {
            for (String str : abstractMap.keySet()) {
                Object obj = this.f17596q.get(str);
                fVar.S(str);
                fVar.b0(iLogger, obj);
            }
        }
        fVar.K();
    }

    public final String toString() {
        return "Feedback{message='" + this.f17590i + "', contactEmail='" + this.f17591l + "', name='" + this.f17592m + "', associatedEventId=" + this.f17593n + ", replayId=" + this.f17594o + ", url='" + this.f17595p + "', unknown=" + this.f17596q + '}';
    }
}
