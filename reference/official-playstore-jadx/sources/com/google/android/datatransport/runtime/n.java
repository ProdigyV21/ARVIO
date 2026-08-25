package com.google.android.datatransport.runtime;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z2.b f12600a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final byte[] f12601b;

    public n(z2.b bVar, byte[] bArr) {
        if (bVar == null) {
            throw new NullPointerException("encoding is null");
        }
        if (bArr == null) {
            throw new NullPointerException("bytes is null");
        }
        this.f12600a = bVar;
        this.f12601b = bArr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof n)) {
            return false;
        }
        n nVar = (n) obj;
        if (this.f12600a.equals(nVar.f12600a)) {
            return Arrays.equals(this.f12601b, nVar.f12601b);
        }
        return false;
    }

    public final int hashCode() {
        return ((this.f12600a.hashCode() ^ 1000003) * 1000003) ^ Arrays.hashCode(this.f12601b);
    }

    public final String toString() {
        return "EncodedPayload{encoding=" + this.f12600a + ", bytes=[...]}";
    }
}
