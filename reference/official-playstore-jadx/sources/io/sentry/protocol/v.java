package io.sentry.protocol;

import io.sentry.ILogger;
import io.sentry.android.core.o0;
import io.sentry.e7;
import io.sentry.i2;
import io.sentry.l3;

/* JADX INFO: loaded from: classes5.dex */
public final class v implements i2 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final v f17707l = new v("00000000-0000-0000-0000-000000000000".replace("-", ""));

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final io.sentry.util.k f17708i;

    public v() {
        this.f17708i = new io.sentry.util.k(new o0(10));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || v.class != obj.getClass()) {
            return false;
        }
        return ((String) this.f17708i.a()).equals(((v) obj).f17708i.a());
    }

    public final int hashCode() {
        return ((String) this.f17708i.a()).hashCode();
    }

    @Override // io.sentry.i2
    public final void serialize(l3 l3Var, ILogger iLogger) {
        ((fi.iki.elonen.f) l3Var).value(toString());
    }

    public final String toString() {
        return (String) this.f17708i.a();
    }

    public v(String str) {
        String str2 = str.equals("0000-0000") ? "00000000-0000-0000-0000-000000000000" : str;
        if (str2.length() != 32 && str2.length() != 36) {
            throw new IllegalArgumentException("String representation of SentryId has either 32 (UUID no dashes) or 36 characters long (completed UUID). Received: ".concat(str));
        }
        if (str2.length() == 36) {
            this.f17708i = new io.sentry.util.k(new io.sentry.android.core.e(this, str2));
        } else {
            this.f17708i = new io.sentry.util.k(new e7(str2));
        }
    }
}
