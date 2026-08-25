package androidx.navigation.compose;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.navigation.a1;
import androidx.navigation.d0;
import androidx.navigation.n0;
import androidx.navigation.z0;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.q0;
import na.j1;
import na.s0;

/* JADX INFO: loaded from: classes3.dex */
@z0("composable")
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Landroidx/navigation/compose/f;", "Landroidx/navigation/a1;", "Landroidx/navigation/compose/e;", "<init>", "()V", "navigation-compose_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class f extends a1 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final MutableState f4563c = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);

    @Override // androidx.navigation.a1
    public final d0 a() {
        return new e(this, a.f4554a);
    }

    @Override // androidx.navigation.a1
    public final void d(List list, n0 n0Var) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            androidx.navigation.i iVar = (androidx.navigation.i) it.next();
            androidx.navigation.k kVarB = b();
            s0 s0Var = kVarB.f4710e;
            j1 j1Var = kVarB.f4708c;
            Iterable iterable = (Iterable) j1Var.getValue();
            if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                Iterator it2 = iterable.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    if (((androidx.navigation.i) it2.next()) == iVar) {
                        Iterable iterable2 = (Iterable) s0Var.f20648i.getValue();
                        if (!(iterable2 instanceof Collection) || !((Collection) iterable2).isEmpty()) {
                            Iterator it3 = iterable2.iterator();
                            while (it3.hasNext()) {
                                if (((androidx.navigation.i) it3.next()) == iVar) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            androidx.navigation.i iVar2 = (androidx.navigation.i) kotlin.collections.x.y0((List) s0Var.f20648i.getValue());
            if (iVar2 != null) {
                j1Var.h(null, q0.c0((Set) j1Var.getValue(), iVar2));
            }
            j1Var.h(null, q0.c0((Set) j1Var.getValue(), iVar));
            kVarB.f(iVar);
        }
        this.f4563c.setValue(Boolean.FALSE);
    }

    @Override // androidx.navigation.a1
    public final void e(androidx.navigation.i iVar, boolean z) {
        b().e(iVar, z);
        this.f4563c.setValue(Boolean.TRUE);
    }
}
