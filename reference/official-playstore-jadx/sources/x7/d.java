package x7;

/* JADX INFO: loaded from: classes5.dex */
public final class d implements e {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final float f22617i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final float f22618l;

    public d(float f10, float f11) {
        this.f22617i = f10;
        this.f22618l = f11;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // x7.e
    public final boolean a(Comparable comparable, Comparable comparable2) {
        return ((Number) comparable).floatValue() <= ((Number) comparable2).floatValue();
    }

    @Override // x7.f
    public final Comparable b() {
        return Float.valueOf(this.f22618l);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        if (isEmpty() && ((d) obj).isEmpty()) {
            return true;
        }
        d dVar = (d) obj;
        return this.f22617i == dVar.f22617i && this.f22618l == dVar.f22618l;
    }

    @Override // x7.f
    public final Comparable getStart() {
        return Float.valueOf(this.f22617i);
    }

    public final int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return Float.floatToIntBits(this.f22618l) + (Float.floatToIntBits(this.f22617i) * 31);
    }

    @Override // x7.f
    public final boolean isEmpty() {
        return this.f22617i > this.f22618l;
    }

    public final String toString() {
        return this.f22617i + ".." + this.f22618l;
    }
}
