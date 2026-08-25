package x7;

/* JADX INFO: loaded from: classes5.dex */
public final class l extends j implements f, m {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final l f22634n = new l(1, 0);

    public l(long j10, long j11) {
        super(j10, j11, 1L);
    }

    @Override // x7.f
    public final Comparable b() {
        return Long.valueOf(this.f22628l);
    }

    @Override // x7.j
    public final boolean equals(Object obj) {
        if (!(obj instanceof l)) {
            return false;
        }
        if (isEmpty() && ((l) obj).isEmpty()) {
            return true;
        }
        l lVar = (l) obj;
        return this.f22627i == lVar.f22627i && this.f22628l == lVar.f22628l;
    }

    @Override // x7.f
    public final Comparable getStart() {
        return Long.valueOf(this.f22627i);
    }

    @Override // x7.j
    public final int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        long j10 = this.f22627i;
        long j11 = ((long) 31) * (j10 ^ (j10 >>> 32));
        long j12 = this.f22628l;
        return (int) (j11 + (j12 ^ (j12 >>> 32)));
    }

    @Override // x7.j, x7.f
    public final boolean isEmpty() {
        return this.f22627i > this.f22628l;
    }

    @Override // x7.j
    public final String toString() {
        return this.f22627i + ".." + this.f22628l;
    }
}
