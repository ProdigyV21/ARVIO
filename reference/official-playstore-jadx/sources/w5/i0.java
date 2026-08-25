package w5;

/* JADX INFO: loaded from: classes4.dex */
public final class i0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f22454a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f22455b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f22456c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f22457d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final j f22458e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f22459f;

    public i0(String str, String str2, int i10, long j10, j jVar, String str3) {
        this.f22454a = str;
        this.f22455b = str2;
        this.f22456c = i10;
        this.f22457d = j10;
        this.f22458e = jVar;
        this.f22459f = str3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof i0)) {
            return false;
        }
        i0 i0Var = (i0) obj;
        return kotlin.jvm.internal.p.a(this.f22454a, i0Var.f22454a) && kotlin.jvm.internal.p.a(this.f22455b, i0Var.f22455b) && this.f22456c == i0Var.f22456c && this.f22457d == i0Var.f22457d && kotlin.jvm.internal.p.a(this.f22458e, i0Var.f22458e) && kotlin.jvm.internal.p.a(this.f22459f, i0Var.f22459f);
    }

    public final int hashCode() {
        int iC = (androidx.compose.foundation.c.c(this.f22454a.hashCode() * 31, 31, this.f22455b) + this.f22456c) * 31;
        long j10 = this.f22457d;
        return this.f22459f.hashCode() + ((this.f22458e.hashCode() + ((iC + ((int) (j10 ^ (j10 >>> 32)))) * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("SessionInfo(sessionId=");
        sb2.append(this.f22454a);
        sb2.append(", firstSessionId=");
        sb2.append(this.f22455b);
        sb2.append(", sessionIndex=");
        sb2.append(this.f22456c);
        sb2.append(", eventTimestampUs=");
        sb2.append(this.f22457d);
        sb2.append(", dataCollectionStatus=");
        sb2.append(this.f22458e);
        sb2.append(", firebaseInstallationId=");
        return androidx.compose.foundation.c.u(sb2, this.f22459f, ')');
    }
}
