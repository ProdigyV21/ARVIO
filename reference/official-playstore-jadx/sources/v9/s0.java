package v9;

/* JADX INFO: loaded from: classes5.dex */
public abstract class s0 implements r0 {
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof r0)) {
            return false;
        }
        r0 r0Var = (r0) obj;
        return b() == r0Var.b() && c() == r0Var.c() && getType().equals(r0Var.getType());
    }

    public final int hashCode() {
        int iC = h.f0.c(c());
        if (b1.l(getType())) {
            return (iC * 31) + 19;
        }
        return (iC * 31) + (b() ? 17 : getType().hashCode());
    }

    public final String toString() {
        if (b()) {
            return "*";
        }
        if (c() == 1) {
            return getType().toString();
        }
        return v.f.B(c()) + " " + getType();
    }
}
