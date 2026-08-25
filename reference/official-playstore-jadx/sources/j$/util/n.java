package j$.util;

/* JADX INFO: loaded from: classes2.dex */
public final class n {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final n f18576c = new n();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f18577a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f18578b;

    public n() {
        this.f18577a = false;
        this.f18578b = 0L;
    }

    public n(long j10) {
        this.f18577a = true;
        this.f18578b = j10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof n)) {
            return false;
        }
        n nVar = (n) obj;
        boolean z = nVar.f18577a;
        boolean z5 = this.f18577a;
        return (z5 && z) ? this.f18578b == nVar.f18578b : z5 == z;
    }

    public final int hashCode() {
        if (!this.f18577a) {
            return 0;
        }
        long j10 = this.f18578b;
        return (int) (j10 ^ (j10 >>> 32));
    }

    public final String toString() {
        if (this.f18577a) {
            return "OptionalLong[" + this.f18578b + "]";
        }
        return "OptionalLong.empty";
    }
}
