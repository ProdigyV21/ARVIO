package x6;

/* JADX INFO: loaded from: classes5.dex */
public final class o0 implements Comparable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f22594i;

    public /* synthetic */ o0(long j10) {
        this.f22594i = j10;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return kotlin.jvm.internal.p.d(this.f22594i ^ Long.MIN_VALUE, ((o0) obj).f22594i ^ Long.MIN_VALUE);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof o0) {
            return this.f22594i == ((o0) obj).f22594i;
        }
        return false;
    }

    public final int hashCode() {
        long j10 = this.f22594i;
        return (int) (j10 ^ (j10 >>> 32));
    }

    public final String toString() {
        return m2.f0.V(10, this.f22594i);
    }
}
