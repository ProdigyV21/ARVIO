package io.sentry;

import io.sentry.exception.ExceptionMechanismException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public abstract class v4 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public io.sentry.protocol.v f17984i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final io.sentry.protocol.c f17985l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public io.sentry.protocol.t f17986m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public io.sentry.protocol.p f17987n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public AbstractMap f17988o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f17989p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String f17990q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public String f17991r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public io.sentry.protocol.i0 f17992s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public transient Throwable f17993t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public String f17994u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public String f17995v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public List f17996w;
    public io.sentry.protocol.d x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public AbstractMap f17997y;

    public v4(io.sentry.protocol.v vVar) {
        this.f17985l = new io.sentry.protocol.c();
        this.f17984i = vVar;
    }

    public final Throwable a() {
        Throwable th = this.f17993t;
        return th instanceof ExceptionMechanismException ? ((ExceptionMechanismException) th).f17240l : th;
    }

    public final void b(String str, String str2) {
        if (this.f17988o == null) {
            this.f17988o = new HashMap();
        }
        if (str == null) {
            return;
        }
        if (str2 != null) {
            this.f17988o.put(str, str2);
            return;
        }
        AbstractMap abstractMap = this.f17988o;
        if (abstractMap != null) {
            abstractMap.remove(str);
        }
    }

    public final void c(HashMap map) {
        this.f17988o = new HashMap(map);
    }

    public v4() {
        this(new io.sentry.protocol.v());
    }
}
