package io.sentry.protocol;

import io.ktor.http.ContentDisposition;
import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.k2;
import io.sentry.l3;
import io.sentry.u5;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: loaded from: classes5.dex */
public final class t implements k2, i2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f17695i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f17696l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public CopyOnWriteArraySet f17697m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public CopyOnWriteArraySet f17698n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public HashMap f17699o;

    public t(String str, String str2) {
        this.f17695i = str;
        this.f17696l = str2;
    }

    public final String a() {
        return this.f17695i;
    }

    public final String b() {
        return this.f17696l;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && t.class == obj.getClass()) {
            t tVar = (t) obj;
            if (this.f17695i.equals(tVar.f17695i) && this.f17696l.equals(tVar.f17696l)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f17695i, this.f17696l});
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        fi.iki.elonen.f fVar = (fi.iki.elonen.f) l3Var;
        fVar.j();
        fVar.S(ContentDisposition.Parameters.Name);
        fVar.value(this.f17695i);
        fVar.S("version");
        fVar.value(this.f17696l);
        CopyOnWriteArraySet copyOnWriteArraySet = this.f17697m;
        if (copyOnWriteArraySet == null) {
            copyOnWriteArraySet = u5.d().f17957b;
        }
        CopyOnWriteArraySet copyOnWriteArraySet2 = this.f17698n;
        if (copyOnWriteArraySet2 == null) {
            copyOnWriteArraySet2 = u5.d().f17956a;
        }
        if (!copyOnWriteArraySet.isEmpty()) {
            fVar.S("packages");
            fVar.b0(iLogger, copyOnWriteArraySet);
        }
        if (!copyOnWriteArraySet2.isEmpty()) {
            fVar.S("integrations");
            fVar.b0(iLogger, copyOnWriteArraySet2);
        }
        HashMap map = this.f17699o;
        if (map != null) {
            for (String str : map.keySet()) {
                g8.b.m(this.f17699o, str, fVar, str, iLogger);
            }
        }
        fVar.K();
    }
}
