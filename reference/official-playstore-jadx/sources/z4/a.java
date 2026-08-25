package z4;

/* JADX INFO: loaded from: classes4.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f23176a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f23177b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f23178c;

    public a(long j10, long j11, long j12) {
        this.f23176a = j10;
        this.f23177b = j11;
        this.f23178c = j12;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof a) {
            a aVar = (a) obj;
            if (this.f23176a == aVar.f23176a && this.f23177b == aVar.f23177b && this.f23178c == aVar.f23178c) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        long j10 = this.f23176a;
        long j11 = this.f23177b;
        int i10 = (((((int) (j10 ^ (j10 >>> 32))) ^ 1000003) * 1000003) ^ ((int) (j11 ^ (j11 >>> 32)))) * 1000003;
        long j12 = this.f23178c;
        return i10 ^ ((int) ((j12 >>> 32) ^ j12));
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("StartupTime{epochMillis=");
        sb2.append(this.f23176a);
        sb2.append(", elapsedRealtime=");
        sb2.append(this.f23177b);
        sb2.append(", uptimeMillis=");
        return a0.c.j(this.f23178c, "}", sb2);
    }
}
