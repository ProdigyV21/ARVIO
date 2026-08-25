package io.sentry.clientreport;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f17191a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f17192b;

    public c(String str, String str2) {
        this.f17191a = str;
        this.f17192b = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return ac.b.C(this.f17191a, cVar.f17191a) && ac.b.C(this.f17192b, cVar.f17192b);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f17191a, this.f17192b});
    }
}
