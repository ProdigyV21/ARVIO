package v9;

import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class g implements o0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f22208a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final u9.s f22209b;

    public g(u9.v vVar) {
        this.f22209b = vVar.b(new j8.t0(this, 8), new s8.d(this, 9));
    }

    public abstract Collection c();

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof o0) && obj.hashCode() == hashCode()) {
            o0 o0Var = (o0) obj;
            if (o0Var.getParameters().size() == getParameters().size()) {
                g8.h hVarE = e();
                g8.h hVarE2 = o0Var.e();
                if (hVarE2 == null || x9.k.e(hVarE) || i9.f.o(hVarE) || x9.k.e(hVarE2) || i9.f.o(hVarE2)) {
                    return false;
                }
                return k(hVarE2);
            }
        }
        return false;
    }

    public abstract w g();

    public abstract g8.z0 h();

    public final int hashCode() {
        int i10 = this.f22208a;
        if (i10 != 0) {
            return i10;
        }
        g8.h hVarE = e();
        int iIdentityHashCode = (x9.k.e(hVarE) || i9.f.o(hVarE)) ? System.identityHashCode(this) : i9.f.g(hVarE).f15364a.hashCode();
        this.f22208a = iIdentityHashCode;
        return iIdentityHashCode;
    }

    @Override // v9.o0
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public final List d() {
        return ((f) this.f22209b.invoke()).f22206b;
    }

    public abstract boolean k(g8.h hVar);

    public List l(List list) {
        return list;
    }
}
