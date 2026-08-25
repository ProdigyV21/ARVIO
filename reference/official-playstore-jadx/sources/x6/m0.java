package x6;

/* JADX INFO: loaded from: classes5.dex */
public final class m0 implements Comparable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f22592i;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return kotlin.jvm.internal.p.c(this.f22592i ^ Integer.MIN_VALUE, ((m0) obj).f22592i ^ Integer.MIN_VALUE);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof m0) {
            return this.f22592i == ((m0) obj).f22592i;
        }
        return false;
    }

    public final int hashCode() {
        return this.f22592i;
    }

    public final String toString() {
        return String.valueOf(((long) this.f22592i) & 4294967295L);
    }
}
