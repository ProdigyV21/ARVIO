package androidx.datastore.preferences.protobuf;

/* JADX INFO: loaded from: classes3.dex */
public final class z1 implements o2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final v1 f2571a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f3 f2572b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final e0 f2573c;

    public z1(f3 f3Var, e0 e0Var, v1 v1Var) {
        this.f2572b = f3Var;
        e0Var.getClass();
        this.f2573c = e0Var;
        this.f2571a = v1Var;
    }

    @Override // androidx.datastore.preferences.protobuf.o2
    public final void a(Object obj, n2 n2Var, d0 d0Var) {
        ((h3) this.f2572b).getClass();
        r0 r0Var = (r0) obj;
        if (r0Var.unknownFields == g3.f2439f) {
            r0Var.unknownFields = g3.b();
        }
        ((f0) this.f2573c).getClass();
        obj.getClass();
        throw new ClassCastException();
    }

    @Override // androidx.datastore.preferences.protobuf.o2
    public final void b(Object obj) {
        ((h3) this.f2572b).getClass();
        ((r0) obj).unknownFields.f2444e = false;
        ((f0) this.f2573c).getClass();
        androidx.compose.material3.d.x(obj);
        throw null;
    }

    @Override // androidx.datastore.preferences.protobuf.o2
    public final int c(c cVar) {
        ((h3) this.f2572b).getClass();
        g3 g3Var = ((r0) cVar).unknownFields;
        int i10 = g3Var.f2443d;
        if (i10 != -1) {
            return i10;
        }
        int iM = 0;
        for (int i11 = 0; i11 < g3Var.f2440a; i11++) {
            int i12 = g3Var.f2441b[i11] >>> 3;
            iM += u.m(3, (m) g3Var.f2442c[i11]) + u.u(2, i12) + (u.t(1) * 2);
        }
        g3Var.f2443d = iM;
        return iM;
    }

    @Override // androidx.datastore.preferences.protobuf.o2
    public final boolean d(Object obj) {
        ((f0) this.f2573c).getClass();
        androidx.compose.material3.d.x(obj);
        throw null;
    }

    @Override // androidx.datastore.preferences.protobuf.o2
    public final Object e() {
        return this.f2571a.e().g();
    }

    @Override // androidx.datastore.preferences.protobuf.o2
    public final void f(r0 r0Var, r0 r0Var2) {
        q2.x(this.f2572b, r0Var, r0Var2);
    }

    @Override // androidx.datastore.preferences.protobuf.o2
    public final int g(r0 r0Var) {
        ((h3) this.f2572b).getClass();
        return r0Var.unknownFields.hashCode();
    }

    @Override // androidx.datastore.preferences.protobuf.o2
    public final void h(Object obj, a4 a4Var) {
        ((f0) this.f2573c).getClass();
        androidx.compose.material3.d.x(obj);
        throw null;
    }

    @Override // androidx.datastore.preferences.protobuf.o2
    public final boolean i(r0 r0Var, r0 r0Var2) {
        h3 h3Var = (h3) this.f2572b;
        h3Var.getClass();
        g3 g3Var = r0Var.unknownFields;
        h3Var.getClass();
        return g3Var.equals(r0Var2.unknownFields);
    }
}
