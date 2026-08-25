package androidx.datastore.preferences.protobuf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class j1 extends l1 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Class f2451c = Collections.unmodifiableList(Collections.EMPTY_LIST).getClass();

    public static List d(int i10, long j10, Object obj) {
        List list = (List) p3.f2500d.i(j10, obj);
        if (list.isEmpty()) {
            List h1Var = list instanceof i1 ? new h1(i10) : ((list instanceof i2) && (list instanceof e1)) ? ((e1) list).V(i10) : new ArrayList(i10);
            p3.o(obj, h1Var, j10);
            return h1Var;
        }
        if (f2451c.isAssignableFrom(list.getClass())) {
            ArrayList arrayList = new ArrayList(list.size() + i10);
            arrayList.addAll(list);
            p3.o(obj, arrayList, j10);
            return arrayList;
        }
        if (list instanceof k3) {
            h1 h1Var2 = new h1(list.size() + i10);
            h1Var2.addAll((k3) list);
            p3.o(obj, h1Var2, j10);
            return h1Var2;
        }
        if ((list instanceof i2) && (list instanceof e1)) {
            e1 e1Var = (e1) list;
            if (!e1Var.T()) {
                e1 e1VarV = e1Var.V(list.size() + i10);
                p3.o(obj, e1VarV, j10);
                return e1VarV;
            }
        }
        return list;
    }

    @Override // androidx.datastore.preferences.protobuf.l1
    public final void a(long j10, Object obj) {
        Object objUnmodifiableList;
        List list = (List) p3.f2500d.i(j10, obj);
        if (list instanceof i1) {
            objUnmodifiableList = ((i1) list).l();
        } else {
            if (f2451c.isAssignableFrom(list.getClass())) {
                return;
            }
            if ((list instanceof i2) && (list instanceof e1)) {
                e1 e1Var = (e1) list;
                if (e1Var.T()) {
                    e1Var.o();
                    return;
                }
                return;
            }
            objUnmodifiableList = Collections.unmodifiableList(list);
        }
        p3.o(obj, objUnmodifiableList, j10);
    }

    @Override // androidx.datastore.preferences.protobuf.l1
    public final void b(Object obj, Object obj2, long j10) {
        List list = (List) p3.f2500d.i(j10, obj2);
        List listD = d(list.size(), j10, obj);
        int size = listD.size();
        int size2 = list.size();
        if (size > 0 && size2 > 0) {
            listD.addAll(list);
        }
        if (size > 0) {
            list = listD;
        }
        p3.o(obj, list, j10);
    }

    @Override // androidx.datastore.preferences.protobuf.l1
    public final List c(long j10, Object obj) {
        return d(10, j10, obj);
    }
}
