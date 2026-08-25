package io.sentry;

import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public final class o7 implements i2 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final o7 f17504l = new o7("00000000-0000-0000-0000-000000000000".replace("-", "").substring(0, 16));

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final io.sentry.util.k f17505i;

    public o7(String str) {
        Objects.requireNonNull(str, "value is required");
        this.f17505i = new io.sentry.util.k(new e7(str));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || o7.class != obj.getClass()) {
            return false;
        }
        return ((String) this.f17505i.a()).equals(((o7) obj).f17505i.a());
    }

    public final int hashCode() {
        return ((String) this.f17505i.a()).hashCode();
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        ((fi.iki.elonen.f) l3Var).value((String) this.f17505i.a());
    }

    public final String toString() {
        return (String) this.f17505i.a();
    }

    public o7() {
        this.f17505i = new io.sentry.util.k(new x1(6));
    }
}
