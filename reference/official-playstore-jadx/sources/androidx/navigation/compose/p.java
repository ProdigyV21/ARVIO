package androidx.navigation.compose;

import androidx.navigation.a1;
import androidx.navigation.d0;
import androidx.navigation.n0;
import androidx.navigation.z0;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@z0("dialog")
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Landroidx/navigation/compose/p;", "Landroidx/navigation/a1;", "Landroidx/navigation/compose/o;", "<init>", "()V", "navigation-compose_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class p extends a1 {
    @Override // androidx.navigation.a1
    public final d0 a() {
        return new o(this, b.f4559a);
    }

    @Override // androidx.navigation.a1
    public final void d(List list, n0 n0Var) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            b().f((androidx.navigation.i) it.next());
        }
    }

    @Override // androidx.navigation.a1
    public final void e(androidx.navigation.i iVar, boolean z) {
        b().e(iVar, z);
        int iQ0 = kotlin.collections.x.q0((Iterable) b().f4711f.f20648i.getValue(), iVar);
        int i10 = 0;
        for (Object obj : (Iterable) b().f4711f.f20648i.getValue()) {
            int i11 = i10 + 1;
            if (i10 < 0) {
                t7.a.Q();
                throw null;
            }
            androidx.navigation.i iVar2 = (androidx.navigation.i) obj;
            if (i10 > iQ0) {
                b().b(iVar2);
            }
            i10 = i11;
        }
    }
}
