package w5;

/* JADX INFO: loaded from: classes4.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final i f22460a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final i f22461b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final double f22462c;

    public j(i iVar, i iVar2, double d4) {
        this.f22460a = iVar;
        this.f22461b = iVar2;
        this.f22462c = d4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof j)) {
            return false;
        }
        j jVar = (j) obj;
        return this.f22460a == jVar.f22460a && this.f22461b == jVar.f22461b && Double.valueOf(this.f22462c).equals(Double.valueOf(jVar.f22462c));
    }

    public final int hashCode() {
        int iHashCode = (this.f22461b.hashCode() + (this.f22460a.hashCode() * 31)) * 31;
        long jDoubleToLongBits = Double.doubleToLongBits(this.f22462c);
        return iHashCode + ((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32)));
    }

    public final String toString() {
        return "DataCollectionStatus(performance=" + this.f22460a + ", crashlytics=" + this.f22461b + ", sessionSamplingRate=" + this.f22462c + ')';
    }
}
