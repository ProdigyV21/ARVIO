package x7;

import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public class j implements Iterable, s7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f22627i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final long f22628l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f22629m;

    public j(long j10, long j11, long j12) {
        if (j12 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (j12 == Long.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Long.MIN_VALUE to avoid overflow on negation.");
        }
        this.f22627i = j10;
        if (j12 > 0) {
            if (j10 < j11) {
                long j13 = j11 % j12;
                long j14 = j10 % j12;
                long j15 = ((j13 < 0 ? j13 + j12 : j13) - (j14 < 0 ? j14 + j12 : j14)) % j12;
                j11 -= j15 < 0 ? j15 + j12 : j15;
            }
        } else {
            if (j12 >= 0) {
                throw new IllegalArgumentException("Step is zero.");
            }
            if (j10 > j11) {
                long j16 = -j12;
                long j17 = j10 % j16;
                long j18 = j11 % j16;
                long j19 = ((j17 < 0 ? j17 + j16 : j17) - (j18 < 0 ? j18 + j16 : j18)) % j16;
                j11 += j19 < 0 ? j19 + j16 : j19;
            }
        }
        this.f22628l = j11;
        this.f22629m = j12;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof j)) {
            return false;
        }
        if (isEmpty() && ((j) obj).isEmpty()) {
            return true;
        }
        j jVar = (j) obj;
        return this.f22627i == jVar.f22627i && this.f22628l == jVar.f22628l && this.f22629m == jVar.f22629m;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        long j10 = 31;
        long j11 = this.f22627i;
        long j12 = this.f22628l;
        long j13 = (((j11 ^ (j11 >>> 32)) * j10) + (j12 ^ (j12 >>> 32))) * j10;
        long j14 = this.f22629m;
        return (int) (j13 + (j14 ^ (j14 >>> 32)));
    }

    public boolean isEmpty() {
        long j10 = this.f22629m;
        long j11 = this.f22628l;
        long j12 = this.f22627i;
        return j10 > 0 ? j12 > j11 : j12 < j11;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new k(this.f22627i, this.f22628l, this.f22629m);
    }

    public String toString() {
        StringBuilder sb2;
        long j10 = this.f22629m;
        long j11 = this.f22628l;
        long j12 = this.f22627i;
        if (j10 > 0) {
            sb2 = new StringBuilder();
            sb2.append(j12);
            sb2.append("..");
            sb2.append(j11);
            sb2.append(" step ");
            sb2.append(j10);
        } else {
            sb2 = new StringBuilder();
            sb2.append(j12);
            sb2.append(" downTo ");
            sb2.append(j11);
            sb2.append(" step ");
            sb2.append(-j10);
        }
        return sb2.toString();
    }
}
