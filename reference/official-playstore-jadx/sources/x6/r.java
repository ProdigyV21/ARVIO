package x6;

/* JADX INFO: loaded from: classes5.dex */
public final class r implements Comparable {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final r f22598o = new r(2, 3, 0);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f22599i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f22600l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f22601m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f22602n;

    public r(int i10, int i11, int i12) {
        this.f22599i = i10;
        this.f22600l = i11;
        this.f22601m = i12;
        if (i10 >= 0 && i10 < 256 && i11 >= 0 && i11 < 256 && i12 >= 0 && i12 < 256) {
            this.f22602n = (i10 << 16) + (i11 << 8) + i12;
            return;
        }
        throw new IllegalArgumentException(("Version components are out of range: " + i10 + '.' + i11 + '.' + i12).toString());
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.f22602n - ((r) obj).f22602n;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        r rVar = obj instanceof r ? (r) obj : null;
        return rVar != null && this.f22602n == rVar.f22602n;
    }

    public final int hashCode() {
        return this.f22602n;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.f22599i);
        sb2.append('.');
        sb2.append(this.f22600l);
        sb2.append('.');
        sb2.append(this.f22601m);
        return sb2.toString();
    }
}
