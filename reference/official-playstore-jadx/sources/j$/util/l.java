package j$.util;

/* JADX INFO: loaded from: classes2.dex */
public final class l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final l f18568c = new l();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f18569a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final double f18570b;

    public l() {
        this.f18569a = false;
        this.f18570b = Double.NaN;
    }

    public l(double d4) {
        this.f18569a = true;
        this.f18570b = d4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof l)) {
            return false;
        }
        l lVar = (l) obj;
        boolean z = lVar.f18569a;
        boolean z5 = this.f18569a;
        return (z5 && z) ? Double.compare(this.f18570b, lVar.f18570b) == 0 : z5 == z;
    }

    public final int hashCode() {
        if (!this.f18569a) {
            return 0;
        }
        long jDoubleToLongBits = Double.doubleToLongBits(this.f18570b);
        return (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
    }

    public final String toString() {
        if (this.f18569a) {
            return "OptionalDouble[" + this.f18570b + "]";
        }
        return "OptionalDouble.empty";
    }
}
