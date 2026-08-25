package androidx.datastore.preferences.protobuf;

/* JADX INFO: loaded from: classes3.dex */
public final class n1 implements u1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public u1[] f2481a;

    @Override // androidx.datastore.preferences.protobuf.u1
    public final t1 a(Class cls) {
        for (u1 u1Var : this.f2481a) {
            if (u1Var.b(cls)) {
                return u1Var.a(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(cls.getName()));
    }

    @Override // androidx.datastore.preferences.protobuf.u1
    public final boolean b(Class cls) {
        for (u1 u1Var : this.f2481a) {
            if (u1Var.b(cls)) {
                return true;
            }
        }
        return false;
    }
}
