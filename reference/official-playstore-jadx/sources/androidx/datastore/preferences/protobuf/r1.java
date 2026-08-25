package androidx.datastore.preferences.protobuf;

import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class r1 implements q1 {
    @Override // androidx.datastore.preferences.protobuf.q1
    public final p1 a(Object obj, Object obj2) {
        p1 p1VarE = (p1) obj;
        p1 p1Var = (p1) obj2;
        if (!p1Var.isEmpty()) {
            if (!p1VarE.f2496i) {
                p1VarE = p1VarE.e();
            }
            p1VarE.c();
            if (!p1Var.isEmpty()) {
                p1VarE.putAll(p1Var);
            }
        }
        return p1VarE;
    }

    @Override // androidx.datastore.preferences.protobuf.q1
    public final androidx.appcompat.app.i1 b(Object obj) {
        return ((o1) obj).f2485a;
    }

    @Override // androidx.datastore.preferences.protobuf.q1
    public final p1 c(Object obj) {
        return (p1) obj;
    }

    @Override // androidx.datastore.preferences.protobuf.q1
    public final p1 d(Object obj) {
        return (p1) obj;
    }

    @Override // androidx.datastore.preferences.protobuf.q1
    public final Object e(Object obj) {
        ((p1) obj).f2496i = false;
        return obj;
    }

    @Override // androidx.datastore.preferences.protobuf.q1
    public final p1 f() {
        return p1.f2495l.e();
    }

    @Override // androidx.datastore.preferences.protobuf.q1
    public final int g(int i10, Object obj, Object obj2) {
        p1 p1Var = (p1) obj;
        o1 o1Var = (o1) obj2;
        int iB = 0;
        if (p1Var.isEmpty()) {
            return 0;
        }
        for (Map.Entry entry : p1Var.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            o1Var.getClass();
            int iT = u.t(i10);
            int iA = o1.a(o1Var.f2485a, key, value);
            iB = androidx.compose.material3.d.b(iA, iA, iT, iB);
        }
        return iB;
    }

    @Override // androidx.datastore.preferences.protobuf.q1
    public final boolean h(Object obj) {
        return !((p1) obj).f2496i;
    }
}
