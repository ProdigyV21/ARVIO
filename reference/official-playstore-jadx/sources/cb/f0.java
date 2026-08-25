package cb;

import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class f0 implements ab.g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f7494a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ab.g f7495b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ab.g f7496c;

    public f0(String str, ab.g gVar, ab.g gVar2) {
        this.f7494a = str;
        this.f7495b = gVar;
        this.f7496c = gVar2;
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
        throw new IllegalArgumentException(str.concat(" is not a valid map index"));
    }

    @Override // ab.g
    public final int d() {
        return 2;
    }

    @Override // ab.g
    public final String e(int i10) {
        return String.valueOf(i10);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f0)) {
            return false;
        }
        f0 f0Var = (f0) obj;
        return kotlin.jvm.internal.p.a(this.f7494a, f0Var.f7494a) && kotlin.jvm.internal.p.a(this.f7495b, f0Var.f7495b) && kotlin.jvm.internal.p.a(this.f7496c, f0Var.f7496c);
    }

    @Override // ab.g
    public final List f(int i10) {
        if (i10 >= 0) {
            return kotlin.collections.z.f19728i;
        }
        throw new IllegalArgumentException(a0.c.p(a0.c.s(i10, "Illegal index ", ", "), this.f7494a, " expects only non-negative indices").toString());
    }

    @Override // ab.g
    public final ab.g g(int i10) {
        if (i10 < 0) {
            throw new IllegalArgumentException(a0.c.p(a0.c.s(i10, "Illegal index ", ", "), this.f7494a, " expects only non-negative indices").toString());
        }
        int i11 = i10 % 2;
        if (i11 == 0) {
            return this.f7495b;
        }
        if (i11 == 1) {
            return this.f7496c;
        }
        throw new IllegalStateException("Unreached");
    }

    @Override // ab.g
    public final List getAnnotations() {
        return kotlin.collections.z.f19728i;
    }

    @Override // ab.g
    public final a.a getKind() {
        return ab.n.f820d;
    }

    @Override // ab.g
    public final String h() {
        return this.f7494a;
    }

    public final int hashCode() {
        return this.f7496c.hashCode() + ((this.f7495b.hashCode() + (this.f7494a.hashCode() * 31)) * 31);
    }

    @Override // ab.g
    public final boolean i(int i10) {
        if (i10 >= 0) {
            return false;
        }
        throw new IllegalArgumentException(a0.c.p(a0.c.s(i10, "Illegal index ", ", "), this.f7494a, " expects only non-negative indices").toString());
    }

    @Override // ab.g
    public final boolean isInline() {
        return false;
    }

    public final String toString() {
        return this.f7494a + '(' + this.f7495b + ", " + this.f7496c + ')';
    }
}
