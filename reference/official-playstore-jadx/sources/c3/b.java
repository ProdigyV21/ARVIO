package c3;

import h.f0;

/* JADX INFO: loaded from: classes4.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f7387a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f7388b;

    public b(int i10, long j10) {
        if (i10 == 0) {
            throw new NullPointerException("Null status");
        }
        this.f7387a = i10;
        this.f7388b = j10;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return f0.b(this.f7387a, bVar.f7387a) && this.f7388b == bVar.f7388b;
    }

    public final int hashCode() {
        int iC = (f0.c(this.f7387a) ^ 1000003) * 1000003;
        long j10 = this.f7388b;
        return iC ^ ((int) (j10 ^ (j10 >>> 32)));
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("BackendResponse{status=");
        int i10 = this.f7387a;
        sb2.append(i10 != 1 ? i10 != 2 ? i10 != 3 ? i10 != 4 ? "null" : "INVALID_PAYLOAD" : "FATAL_ERROR" : "TRANSIENT_ERROR" : "OK");
        sb2.append(", nextRequestWaitMillis=");
        return a0.c.j(this.f7388b, "}", sb2);
    }
}
