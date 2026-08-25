package p5;

import androidx.appcompat.widget.f0;

/* JADX INFO: loaded from: classes4.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f21058a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f21059b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f21060c;

    public b(String str, long j10, int i10) {
        this.f21058a = str;
        this.f21059b = j10;
        this.f21060c = i10;
    }

    public static f0 a() {
        f0 f0Var = new f0(6);
        f0Var.f1568n = 0L;
        return f0Var;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        int i10 = bVar.f21060c;
        String str = bVar.f21058a;
        String str2 = this.f21058a;
        if (str2 == null) {
            if (str != null) {
                return false;
            }
        } else if (!str2.equals(str)) {
            return false;
        }
        if (this.f21059b != bVar.f21059b) {
            return false;
        }
        int i11 = this.f21060c;
        return i11 == 0 ? i10 == 0 : h.f0.b(i11, i10);
    }

    public final int hashCode() {
        String str = this.f21058a;
        int iHashCode = str == null ? 0 : str.hashCode();
        long j10 = this.f21059b;
        int i10 = (((iHashCode ^ 1000003) * 1000003) ^ ((int) (j10 ^ (j10 >>> 32)))) * 1000003;
        int i11 = this.f21060c;
        return (i11 != 0 ? h.f0.c(i11) : 0) ^ i10;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("TokenResult{token=");
        sb2.append(this.f21058a);
        sb2.append(", tokenExpirationTimestamp=");
        sb2.append(this.f21059b);
        sb2.append(", responseCode=");
        int i10 = this.f21060c;
        sb2.append(i10 != 1 ? i10 != 2 ? i10 != 3 ? "null" : "AUTH_ERROR" : "BAD_CONFIG" : "OK");
        sb2.append("}");
        return sb2.toString();
    }
}
