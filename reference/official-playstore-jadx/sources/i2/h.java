package i2;

/* JADX INFO: loaded from: classes3.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f16050a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f16051b;

    public h(int i10, int i11) {
        this.f16050a = i10;
        this.f16051b = i11;
        if (i10 < 0) {
            throw new IllegalArgumentException("negative start index");
        }
        if (i11 < i10) {
            throw new IllegalArgumentException("end index greater than start");
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        return this.f16050a == hVar.f16050a && this.f16051b == hVar.f16051b;
    }

    public final int hashCode() {
        return (this.f16050a * 31) + this.f16051b;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("Interval(start=");
        sb2.append(this.f16050a);
        sb2.append(", end=");
        return a0.c.o(sb2, this.f16051b, ')');
    }
}
