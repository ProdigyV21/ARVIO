package v9;

import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class w implements h8.a, y9.h {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f22257i;

    public abstract List G0();

    public abstract k0 H0();

    public abstract o0 I0();

    public abstract boolean J0();

    public abstract w K0(w9.e eVar);

    public abstract d1 L0();

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof w)) {
            return false;
        }
        w wVar = (w) obj;
        if (J0() == wVar.J0()) {
            return c.q(w9.n.f22527a, L0(), wVar.L0());
        }
        return false;
    }

    @Override // h8.a
    public final h8.h getAnnotations() {
        h8.h hVar;
        h hVar2 = (h) i.f22216b.getValue(H0(), i.f22215a[0]);
        return (hVar2 == null || (hVar = hVar2.f22212a) == null) ? h8.g.f15863a : hVar;
    }

    public final int hashCode() {
        int iHashCode;
        int i10 = this.f22257i;
        if (i10 != 0) {
            return i10;
        }
        if (c.g(this)) {
            iHashCode = super.hashCode();
        } else {
            iHashCode = (J0() ? 1 : 0) + ((G0().hashCode() + (I0().hashCode() * 31)) * 31);
        }
        this.f22257i = iHashCode;
        return iHashCode;
    }

    public abstract p9.n l();
}
