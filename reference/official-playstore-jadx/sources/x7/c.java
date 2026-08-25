package x7;

import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes5.dex */
public final class c extends a implements f, m {
    static {
        new c((char) 1, (char) 0);
    }

    @Override // x7.f
    public final Comparable b() {
        return Character.valueOf(this.f22611l);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        if (isEmpty() && ((c) obj).isEmpty()) {
            return true;
        }
        c cVar = (c) obj;
        return this.f22610i == cVar.f22610i && this.f22611l == cVar.f22611l;
    }

    @Override // x7.f
    public final Comparable getStart() {
        return Character.valueOf(this.f22610i);
    }

    public final int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (this.f22610i * 31) + this.f22611l;
    }

    @Override // x7.f
    public final boolean isEmpty() {
        return p.c(this.f22610i, this.f22611l) > 0;
    }

    public final String toString() {
        return this.f22610i + ".." + this.f22611l;
    }
}
