package x7;

/* JADX INFO: loaded from: classes5.dex */
public final class i extends g implements f, m {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final i f22626n = new i(1, 0, 1);

    public i(int i10) {
        super(1, i10, 1);
    }

    @Override // x7.f
    public final Comparable b() {
        return Integer.valueOf(this.f22620l);
    }

    public final boolean d(int i10) {
        return this.f22619i <= i10 && i10 <= this.f22620l;
    }

    @Override // x7.g
    public final boolean equals(Object obj) {
        if (!(obj instanceof i)) {
            return false;
        }
        if (isEmpty() && ((i) obj).isEmpty()) {
            return true;
        }
        i iVar = (i) obj;
        return this.f22619i == iVar.f22619i && this.f22620l == iVar.f22620l;
    }

    @Override // x7.f
    public final Comparable getStart() {
        return Integer.valueOf(this.f22619i);
    }

    @Override // x7.g
    public final int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (this.f22619i * 31) + this.f22620l;
    }

    @Override // x7.g, x7.f
    public final boolean isEmpty() {
        return this.f22619i > this.f22620l;
    }

    @Override // x7.g
    public final String toString() {
        return this.f22619i + ".." + this.f22620l;
    }
}
