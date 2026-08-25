package androidx.tv.material3;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.unit.Constraints;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class j4 extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ MutableState f6138i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.q f6139l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.p f6140m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.r f6141n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j4(MutableState mutableState, r7.p pVar, r7.q qVar, r7.r rVar) {
        super(2);
        this.f6138i = mutableState;
        this.f6139l = qVar;
        this.f6140m = pVar;
        this.f6141n = rVar;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        int iIntValue;
        Integer numValueOf;
        SubcomposeMeasureScope subcomposeMeasureScope = (SubcomposeMeasureScope) obj;
        long value = ((Constraints) obj2).getValue();
        List<Measurable> listSubcompose = subcomposeMeasureScope.subcompose(m4.f6219i, ComposableLambdaKt.composableLambdaInstance(-1565364206, true, new TabRowKt$TabRow$3$1$1$tabMeasurables$1(this.f6138i, this.f6139l)));
        ArrayList arrayList = new ArrayList(listSubcompose.size());
        int size = listSubcompose.size();
        for (int i10 = 0; i10 < size; i10++) {
            arrayList.add(listSubcompose.get(i10).mo4631measureBRTryo0(Constraints.m5637copyZbe2FdA$default(value, 0, 0, 0, 0, 10, null)));
        }
        int size2 = listSubcompose.size() - 1;
        List<Measurable> listSubcompose2 = subcomposeMeasureScope.subcompose(m4.f6221m, ComposableLambdaKt.composableLambdaInstance(489921092, true, new TabRowKt$TabRow$3$1$1$separators$1(size2, this.f6140m)));
        ArrayList arrayList2 = new ArrayList(listSubcompose2.size());
        int size3 = listSubcompose2.size();
        for (int i11 = 0; i11 < size3; i11++) {
            arrayList2.add(listSubcompose2.get(i11).mo4631measureBRTryo0(Constraints.m5637copyZbe2FdA$default(value, 0, 0, 0, 0, 10, null)));
        }
        Placeable placeable = (Placeable) kotlin.collections.x.o0(arrayList2);
        int width = placeable != null ? placeable.getWidth() : 0;
        int size4 = arrayList.size();
        int width2 = 0;
        for (int i12 = 0; i12 < size4; i12++) {
            width2 += ((Placeable) arrayList.get(i12)).getWidth();
        }
        int i13 = (size2 * width) + width2;
        if (arrayList.isEmpty()) {
            numValueOf = null;
            iIntValue = 0;
        } else {
            iIntValue = 0;
            numValueOf = Integer.valueOf(((Placeable) arrayList.get(0)).getHeight());
            int iW = t7.a.w(arrayList);
            if (1 <= iW) {
                int i14 = 1;
                while (true) {
                    Integer numValueOf2 = Integer.valueOf(((Placeable) arrayList.get(i14)).getHeight());
                    if (numValueOf2.compareTo(numValueOf) > 0) {
                        numValueOf = numValueOf2;
                    }
                    if (i14 == iW) {
                        break;
                    }
                    i14++;
                }
            }
        }
        if (numValueOf != null) {
            iIntValue = numValueOf.intValue();
        }
        int i15 = iIntValue;
        return MeasureScope.CC.q(subcomposeMeasureScope, i13, i15, null, new i4(arrayList, subcomposeMeasureScope, arrayList2, width, this.f6141n, this.f6138i, i13, i15), 4, null);
    }
}
