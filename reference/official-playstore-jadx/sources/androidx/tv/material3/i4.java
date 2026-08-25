package androidx.tv.material3;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.DpRect;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class i4 extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ ArrayList f6113i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ SubcomposeMeasureScope f6114l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ ArrayList f6115m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f6116n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ r7.r f6117o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ MutableState f6118p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f6119q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ int f6120r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i4(ArrayList arrayList, SubcomposeMeasureScope subcomposeMeasureScope, ArrayList arrayList2, int i10, r7.r rVar, MutableState mutableState, int i11, int i12) {
        super(1);
        this.f6113i = arrayList;
        this.f6114l = subcomposeMeasureScope;
        this.f6115m = arrayList2;
        this.f6116n = i10;
        this.f6117o = rVar;
        this.f6118p = mutableState;
        this.f6119q = i11;
        this.f6120r = i12;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        SubcomposeMeasureScope subcomposeMeasureScope;
        Placeable.PlacementScope placementScope = (Placeable.PlacementScope) obj;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = this.f6113i;
        int size = arrayList2.size();
        int i10 = 0;
        int i11 = 0;
        while (true) {
            subcomposeMeasureScope = this.f6114l;
            if (i11 >= size) {
                break;
            }
            Placeable placeable = (Placeable) arrayList2.get(i11);
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable, i10, 0, 0.0f, 4, null);
            arrayList.add(new DpRect(subcomposeMeasureScope.mo282toDpu2uoSUM(i10), subcomposeMeasureScope.mo282toDpu2uoSUM(0), subcomposeMeasureScope.mo282toDpu2uoSUM(placeable.getWidth() + i10), subcomposeMeasureScope.mo282toDpu2uoSUM(placeable.getHeight()), null));
            int width = i10 + placeable.getWidth();
            if (t7.a.w(arrayList2) != i11) {
                Placeable.PlacementScope.placeRelative$default(placementScope, (Placeable) this.f6115m.get(i11), width, 0, 0.0f, 4, null);
            }
            i10 = width + this.f6116n;
            i11++;
        }
        List<Measurable> listSubcompose = subcomposeMeasureScope.subcompose(m4.f6220l, ComposableLambdaKt.composableLambdaInstance(1938511990, true, new TabRowKt$TabRow$3$1$1$1$2(this.f6117o, arrayList, this.f6118p)));
        int size2 = listSubcompose.size();
        for (int i12 = 0; i12 < size2; i12++) {
            Placeable.PlacementScope.placeRelative$default(placementScope, listSubcompose.get(i12).mo4631measureBRTryo0(Constraints.INSTANCE.m5654fixedJhjzzOo(this.f6119q, this.f6120r)), 0, 0, 0.0f, 4, null);
        }
        return x6.t0.f22605a;
    }
}
