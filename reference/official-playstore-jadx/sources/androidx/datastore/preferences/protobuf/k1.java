package androidx.datastore.preferences.protobuf;

import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class k1 extends l1 {
    @Override // androidx.datastore.preferences.protobuf.l1
    public final void a(long j10, Object obj) {
        ((e1) p3.f2500d.i(j10, obj)).o();
    }

    @Override // androidx.datastore.preferences.protobuf.l1
    public final void b(Object obj, Object obj2, long j10) {
        o3 o3Var = p3.f2500d;
        e1 e1VarV = (e1) o3Var.i(j10, obj);
        e1 e1Var = (e1) o3Var.i(j10, obj2);
        int size = e1VarV.size();
        int size2 = e1Var.size();
        if (size > 0 && size2 > 0) {
            if (!e1VarV.T()) {
                e1VarV = e1VarV.V(size2 + size);
            }
            e1VarV.addAll(e1Var);
        }
        if (size > 0) {
            e1Var = e1VarV;
        }
        p3.o(obj, e1Var, j10);
    }

    @Override // androidx.datastore.preferences.protobuf.l1
    public final List c(long j10, Object obj) {
        e1 e1Var = (e1) p3.f2500d.i(j10, obj);
        if (e1Var.T()) {
            return e1Var;
        }
        int size = e1Var.size();
        e1 e1VarV = e1Var.V(size == 0 ? 10 : size * 2);
        p3.o(obj, e1VarV, j10);
        return e1VarV;
    }
}
