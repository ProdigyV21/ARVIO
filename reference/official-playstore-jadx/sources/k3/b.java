package k3;

import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f19474a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f19475b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Set f19476c;

    public b(Set set, long j10, long j11) {
        this.f19474a = j10;
        this.f19475b = j11;
        this.f19476c = set;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof b) {
            b bVar = (b) obj;
            if (this.f19474a == bVar.f19474a && this.f19475b == bVar.f19475b && this.f19476c.equals(bVar.f19476c)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        long j10 = this.f19474a;
        int i10 = (((int) (j10 ^ (j10 >>> 32))) ^ 1000003) * 1000003;
        long j11 = this.f19475b;
        return ((i10 ^ ((int) (j11 ^ (j11 >>> 32)))) * 1000003) ^ this.f19476c.hashCode();
    }

    public final String toString() {
        return "ConfigValue{delta=" + this.f19474a + ", maxAllowedDelay=" + this.f19475b + ", flags=" + this.f19476c + "}";
    }
}
