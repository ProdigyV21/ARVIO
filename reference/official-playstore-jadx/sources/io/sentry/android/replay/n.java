package io.sentry.android.replay;

import java.io.File;

/* JADX INFO: loaded from: classes5.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final File f16990a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f16991b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f16992c;

    public n(File file, String str, long j10) {
        this.f16990a = file;
        this.f16991b = j10;
        this.f16992c = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof n)) {
            return false;
        }
        n nVar = (n) obj;
        return kotlin.jvm.internal.p.a(this.f16990a, nVar.f16990a) && this.f16991b == nVar.f16991b && kotlin.jvm.internal.p.a(this.f16992c, nVar.f16992c);
    }

    public final int hashCode() {
        int iHashCode = this.f16990a.hashCode() * 31;
        long j10 = this.f16991b;
        int i10 = (iHashCode + ((int) (j10 ^ (j10 >>> 32)))) * 31;
        String str = this.f16992c;
        return i10 + (str == null ? 0 : str.hashCode());
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("ReplayFrame(screenshot=");
        sb2.append(this.f16990a);
        sb2.append(", timestamp=");
        sb2.append(this.f16991b);
        sb2.append(", screen=");
        return androidx.compose.foundation.c.u(sb2, this.f16992c, ')');
    }
}
