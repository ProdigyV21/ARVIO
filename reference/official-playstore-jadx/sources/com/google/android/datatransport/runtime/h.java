package com.google.android.datatransport.runtime;

import androidx.appcompat.widget.z;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f12583a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Integer f12584b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final n f12585c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f12586d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f12587e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Map f12588f;

    public h(String str, Integer num, n nVar, long j10, long j11, HashMap map) {
        this.f12583a = str;
        this.f12584b = num;
        this.f12585c = nVar;
        this.f12586d = j10;
        this.f12587e = j11;
        this.f12588f = map;
    }

    public final String a(String str) {
        String str2 = (String) this.f12588f.get(str);
        return str2 == null ? "" : str2;
    }

    public final int b(String str) {
        String str2 = (String) this.f12588f.get(str);
        if (str2 == null) {
            return 0;
        }
        return Integer.valueOf(str2).intValue();
    }

    public final z c() {
        z zVar = new z();
        String str = this.f12583a;
        if (str == null) {
            throw new NullPointerException("Null transportName");
        }
        zVar.f1842a = str;
        zVar.f1843b = this.f12584b;
        n nVar = this.f12585c;
        if (nVar == null) {
            throw new NullPointerException("Null encodedPayload");
        }
        zVar.f1844c = nVar;
        zVar.f1845d = Long.valueOf(this.f12586d);
        zVar.f1846e = Long.valueOf(this.f12587e);
        zVar.f1847f = new HashMap(this.f12588f);
        return zVar;
    }

    public final boolean equals(Object obj) {
        Integer num;
        if (obj == this) {
            return true;
        }
        if (obj instanceof h) {
            h hVar = (h) obj;
            Integer num2 = hVar.f12584b;
            if (this.f12583a.equals(hVar.f12583a) && ((num = this.f12584b) != null ? num.equals(num2) : num2 == null) && this.f12585c.equals(hVar.f12585c) && this.f12586d == hVar.f12586d && this.f12587e == hVar.f12587e && this.f12588f.equals(hVar.f12588f)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = (this.f12583a.hashCode() ^ 1000003) * 1000003;
        Integer num = this.f12584b;
        int iHashCode2 = (((iHashCode ^ (num == null ? 0 : num.hashCode())) * 1000003) ^ this.f12585c.hashCode()) * 1000003;
        long j10 = this.f12586d;
        int i10 = (iHashCode2 ^ ((int) (j10 ^ (j10 >>> 32)))) * 1000003;
        long j11 = this.f12587e;
        return ((i10 ^ ((int) (j11 ^ (j11 >>> 32)))) * 1000003) ^ this.f12588f.hashCode();
    }

    public final String toString() {
        return "EventInternal{transportName=" + this.f12583a + ", code=" + this.f12584b + ", encodedPayload=" + this.f12585c + ", eventMillis=" + this.f12586d + ", uptimeMillis=" + this.f12587e + ", autoMetadata=" + this.f12588f + "}";
    }
}
