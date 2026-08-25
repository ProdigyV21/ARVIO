package cb;

import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class n0 implements ab.g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ab.g f7535a;

    public n0(ab.g gVar) {
        this.f7535a = gVar;
    }

    @Override // ab.g
    public final boolean b() {
        return false;
    }

    @Override // ab.g
    public final int c(String str) {
        Integer numR = kotlin.text.u.R(str);
        if (numR != null) {
            return numR.intValue();
        }
        throw new IllegalArgumentException(str.concat(" is not a valid list index"));
    }

    @Override // ab.g
    public final int d() {
        return 1;
    }

    @Override // ab.g
    public final String e(int i10) {
        return String.valueOf(i10);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof n0)) {
            return false;
        }
        n0 n0Var = (n0) obj;
        return kotlin.jvm.internal.p.a(this.f7535a, n0Var.f7535a) && kotlin.jvm.internal.p.a(h(), n0Var.h());
    }

    @Override // ab.g
    public final List f(int i10) {
        if (i10 >= 0) {
            return kotlin.collections.z.f19728i;
        }
        StringBuilder sbS = a0.c.s(i10, "Illegal index ", ", ");
        sbS.append(h());
        sbS.append(" expects only non-negative indices");
        throw new IllegalArgumentException(sbS.toString().toString());
    }

    @Override // ab.g
    public final ab.g g(int i10) {
        if (i10 >= 0) {
            return this.f7535a;
        }
        StringBuilder sbS = a0.c.s(i10, "Illegal index ", ", ");
        sbS.append(h());
        sbS.append(" expects only non-negative indices");
        throw new IllegalArgumentException(sbS.toString().toString());
    }

    @Override // ab.g
    public final List getAnnotations() {
        return kotlin.collections.z.f19728i;
    }

    @Override // ab.g
    public final a.a getKind() {
        return ab.n.f819c;
    }

    public final int hashCode() {
        return h().hashCode() + (this.f7535a.hashCode() * 31);
    }

    @Override // ab.g
    public final boolean i(int i10) {
        if (i10 >= 0) {
            return false;
        }
        StringBuilder sbS = a0.c.s(i10, "Illegal index ", ", ");
        sbS.append(h());
        sbS.append(" expects only non-negative indices");
        throw new IllegalArgumentException(sbS.toString().toString());
    }

    @Override // ab.g
    public final boolean isInline() {
        return false;
    }

    public final String toString() {
        return h() + '(' + this.f7535a + ')';
    }
}
