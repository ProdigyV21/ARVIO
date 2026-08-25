package k9;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.collections.z;
import v9.k0;
import v9.o0;
import x6.i0;

/* JADX INFO: loaded from: classes5.dex */
public final class n implements o0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Set f19535a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final i0 f19536b;

    public n(Set set) {
        k0.f22220l.getClass();
        k0 k0Var = k0.f22221m;
        v9.e.s(z.f19728i, x9.k.a(2, true, "unknown integer literal type"), k0Var, this, false);
        this.f19536b = new i0(new l(0));
        this.f19535a = set;
    }

    @Override // v9.o0
    public final Collection d() {
        return (List) this.f19536b.getValue();
    }

    @Override // v9.o0
    public final g8.h e() {
        return null;
    }

    @Override // v9.o0
    public final boolean f() {
        return false;
    }

    @Override // v9.o0
    public final List getParameters() {
        return z.f19728i;
    }

    @Override // v9.o0
    public final d8.k j() {
        throw null;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("IntegerLiteralType");
        sb2.append("[" + kotlin.collections.x.u0(this.f19535a, ",", null, null, m.f19534i, 30) + ']');
        return sb2.toString();
    }
}
