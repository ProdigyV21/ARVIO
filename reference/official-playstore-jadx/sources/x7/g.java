package x7;

import i7.n;

/* JADX INFO: loaded from: classes5.dex */
public class g implements Iterable, s7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f22619i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f22620l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f22621m;

    public g(int i10, int i11, int i12) {
        if (i12 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (i12 == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        }
        this.f22619i = i10;
        this.f22620l = n.a(i10, i11, i12);
        this.f22621m = i12;
    }

    @Override // java.lang.Iterable
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final h iterator() {
        return new h(this.f22619i, this.f22620l, this.f22621m);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof g)) {
            return false;
        }
        if (isEmpty() && ((g) obj).isEmpty()) {
            return true;
        }
        g gVar = (g) obj;
        return this.f22619i == gVar.f22619i && this.f22620l == gVar.f22620l && this.f22621m == gVar.f22621m;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (((this.f22619i * 31) + this.f22620l) * 31) + this.f22621m;
    }

    public boolean isEmpty() {
        int i10 = this.f22621m;
        int i11 = this.f22620l;
        int i12 = this.f22619i;
        return i10 > 0 ? i12 > i11 : i12 < i11;
    }

    public String toString() {
        StringBuilder sb2;
        int i10 = this.f22620l;
        int i11 = this.f22619i;
        int i12 = this.f22621m;
        if (i12 > 0) {
            sb2 = new StringBuilder();
            sb2.append(i11);
            sb2.append("..");
            sb2.append(i10);
            sb2.append(" step ");
            sb2.append(i12);
        } else {
            sb2 = new StringBuilder();
            sb2.append(i11);
            sb2.append(" downTo ");
            sb2.append(i10);
            sb2.append(" step ");
            sb2.append(-i12);
        }
        return sb2.toString();
    }
}
