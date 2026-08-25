package com.google.android.datatransport.runtime;

import android.util.Base64;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f12589a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final byte[] f12590b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final z2.c f12591c;

    public i(String str, byte[] bArr, z2.c cVar) {
        this.f12589a = str;
        this.f12590b = bArr;
        this.f12591c = cVar;
    }

    public final i a(z2.c cVar) {
        String str = this.f12589a;
        if (str == null) {
            throw new NullPointerException("Null backendName");
        }
        if (cVar != null) {
            return new i(str, this.f12590b, cVar);
        }
        throw new NullPointerException("Null priority");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof i) {
            i iVar = (i) obj;
            if (this.f12589a.equals(iVar.f12589a) && Arrays.equals(this.f12590b, iVar.f12590b) && this.f12591c.equals(iVar.f12591c)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((this.f12589a.hashCode() ^ 1000003) * 1000003) ^ Arrays.hashCode(this.f12590b)) * 1000003) ^ this.f12591c.hashCode();
    }

    public final String toString() {
        byte[] bArr = this.f12590b;
        String strEncodeToString = bArr == null ? "" : Base64.encodeToString(bArr, 2);
        StringBuilder sb2 = new StringBuilder("TransportContext(");
        sb2.append(this.f12589a);
        sb2.append(", ");
        sb2.append(this.f12591c);
        sb2.append(", ");
        return a0.c.p(sb2, strEncodeToString, ")");
    }
}
