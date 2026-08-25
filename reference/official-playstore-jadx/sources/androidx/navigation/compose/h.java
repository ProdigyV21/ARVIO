package androidx.navigation.compose;

import androidx.compose.animation.ContentTransform;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemProvider;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.core.view.d0;
import androidx.tv.material3.l0;
import androidx.tv.material3.p1;
import androidx.tv.material3.q1;
import java.util.List;
import ka.k0;
import ka.m0;
import v9.o0;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class h extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4568i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f4569l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f4570m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f4571n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ h(Object obj, Object obj2, Object obj3, int i10) {
        super(1);
        this.f4568i = i10;
        this.f4569l = obj;
        this.f4570m = obj2;
        this.f4571n = obj3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f4568i) {
            case 0:
                SnapshotStateList snapshotStateList = (SnapshotStateList) this.f4569l;
                androidx.navigation.i iVar = (androidx.navigation.i) this.f4570m;
                snapshotStateList.add(iVar);
                return new g((p) this.f4571n, iVar, snapshotStateList, 0);
            case 1:
                return ((l0) this.f4569l).f6186c ? (ContentTransform) this.f4570m : (ContentTransform) this.f4571n;
            case 2:
                FocusState focusState = (FocusState) obj;
                ((MutableState) this.f4570m).setValue(focusState);
                if (((Boolean) ((MutableState) this.f4571n).getValue()).booleanValue()) {
                    ((p1) this.f4569l).f6293a.setValue(focusState.getHasFocus() ? q1.f6330l : q1.f6329i);
                }
                return t0.f22605a;
            case 3:
                long packedValue = ((IntSize) obj).getPackedValue();
                MutableState mutableState = (MutableState) this.f4569l;
                if (mutableState.getValue() == null && ((p1) this.f4570m).a() == q1.f6329i) {
                    mutableState.setValue(Dp.m5676boximpl(((Density) this.f4571n).mo282toDpu2uoSUM(IntSize.m5844getWidthimpl(packedValue))));
                }
                return t0.f22605a;
            case 4:
                LayoutCoordinates layoutCoordinates = (LayoutCoordinates) obj;
                Density density = (Density) this.f4569l;
                MutableState mutableState2 = (MutableState) this.f4570m;
                MutableState mutableState3 = (MutableState) this.f4571n;
                mutableState2.setValue(Dp.m5676boximpl(density.mo282toDpu2uoSUM(IntSize.m5844getWidthimpl(layoutCoordinates.mo4638getSizeYbymL2g()))));
                mutableState3.setValue(Dp.m5676boximpl(density.mo282toDpu2uoSUM(IntSize.m5843getHeightimpl(layoutCoordinates.mo4638getSizeYbymL2g()))));
                return t0.f22605a;
            case 5:
                androidx.activity.l0 l0Var = (androidx.activity.l0) this.f4569l;
                androidx.lifecycle.y yVar = (androidx.lifecycle.y) this.f4570m;
                e.e eVar = (e.e) this.f4571n;
                l0Var.a(yVar, eVar);
                return new e.b(eVar, 1);
            case 6:
                androidx.activity.l0 l0Var2 = (androidx.activity.l0) this.f4569l;
                androidx.lifecycle.y yVar2 = (androidx.lifecycle.y) this.f4570m;
                e.i iVar2 = (e.i) this.f4571n;
                l0Var2.a(yVar2, iVar2);
                return new e.b(iVar2, 2);
            case 7:
                int iIntValue = ((Number) obj).intValue();
                LazyLayoutItemProvider lazyLayoutItemProvider = (LazyLayoutItemProvider) ((r7.a) this.f4569l).invoke();
                if (iIntValue >= 0 && iIntValue < lazyLayoutItemProvider.getItemCount()) {
                    m0.p((k0) this.f4570m, null, 0, new androidx.tv.foundation.lazy.list.k0((i2.m) this.f4571n, iIntValue, null), 3);
                    return Boolean.TRUE;
                }
                StringBuilder sbS = a0.c.s(iIntValue, "Can't scroll to index ", ", it is out of bounds [0, ");
                sbS.append(lazyLayoutItemProvider.getItemCount());
                sbS.append(')');
                throw new IllegalArgumentException(sbS.toString().toString());
            case 8:
                androidx.activity.i iVar3 = new androidx.activity.i((androidx.lifecycle.p) this.f4570m, (State) this.f4571n, 4);
                androidx.lifecycle.y yVar3 = (androidx.lifecycle.y) this.f4569l;
                yVar3.getLifecycle().addObserver(iVar3);
                return new k(yVar3, iVar3, 2);
            case 9:
                kotlin.jvm.internal.k0 k0Var = new kotlin.jvm.internal.k0();
                d0 d0Var = new d0((n1.i) this.f4570m, k0Var, (r7.l) this.f4571n, 1);
                androidx.lifecycle.y yVar4 = (androidx.lifecycle.y) this.f4569l;
                yVar4.getLifecycle().addObserver(d0Var);
                return new g(yVar4, d0Var, k0Var, 1);
            case 10:
                kotlin.jvm.internal.k0 k0Var2 = new kotlin.jvm.internal.k0();
                d0 d0Var2 = new d0((n1.j) this.f4570m, k0Var2, (r7.l) this.f4571n, 2);
                androidx.lifecycle.y yVar5 = (androidx.lifecycle.y) this.f4569l;
                yVar5.getLifecycle().addObserver(d0Var2);
                return new g(yVar5, d0Var2, k0Var2, 2);
            default:
                w9.e eVar2 = (w9.e) obj;
                if (((o0) this.f4569l).e() == null) {
                    return null;
                }
                eVar2.getClass();
                return null;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(List list, p9.n nVar, v9.k0 k0Var, o0 o0Var, boolean z) {
        super(1);
        this.f4568i = 11;
        this.f4569l = o0Var;
        this.f4570m = list;
        this.f4571n = nVar;
    }
}
