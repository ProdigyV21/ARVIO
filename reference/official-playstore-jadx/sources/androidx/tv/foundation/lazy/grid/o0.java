package androidx.tv.foundation.lazy.grid;

/* JADX INFO: loaded from: classes3.dex */
public final class o0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f5283a;

    public final boolean equals(Object obj) {
        if (obj instanceof o0) {
            return this.f5283a == ((o0) obj).f5283a;
        }
        return false;
    }

    public final int hashCode() {
        long j10 = this.f5283a;
        return (int) (j10 ^ (j10 >>> 32));
    }

    public final String toString() {
        return "TvGridItemSpan(packedValue=" + this.f5283a + ')';
    }
}
