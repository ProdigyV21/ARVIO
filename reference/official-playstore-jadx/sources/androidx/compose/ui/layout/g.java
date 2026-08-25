package androidx.compose.ui.layout;

import androidx.compose.ui.unit.ConstraintsKt;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class g {
    public static int a(MeasurePolicy measurePolicy, IntrinsicMeasureScope intrinsicMeasureScope, List list, int i10) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i11 = 0; i11 < size; i11++) {
            arrayList.add(new DefaultIntrinsicMeasurable((IntrinsicMeasurable) list.get(i11), IntrinsicMinMax.Max, IntrinsicWidthHeight.Height));
        }
        return measurePolicy.mo5measure3p2s80s(new IntrinsicsMeasureScope(intrinsicMeasureScope, intrinsicMeasureScope.getLayoutDirection()), arrayList, ConstraintsKt.Constraints$default(0, i10, 0, 0, 13, null)).get$h();
    }

    public static int b(MeasurePolicy measurePolicy, IntrinsicMeasureScope intrinsicMeasureScope, List list, int i10) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i11 = 0; i11 < size; i11++) {
            arrayList.add(new DefaultIntrinsicMeasurable((IntrinsicMeasurable) list.get(i11), IntrinsicMinMax.Max, IntrinsicWidthHeight.Width));
        }
        return measurePolicy.mo5measure3p2s80s(new IntrinsicsMeasureScope(intrinsicMeasureScope, intrinsicMeasureScope.getLayoutDirection()), arrayList, ConstraintsKt.Constraints$default(0, 0, 0, i10, 7, null)).get$w();
    }

    public static int c(MeasurePolicy measurePolicy, IntrinsicMeasureScope intrinsicMeasureScope, List list, int i10) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i11 = 0; i11 < size; i11++) {
            arrayList.add(new DefaultIntrinsicMeasurable((IntrinsicMeasurable) list.get(i11), IntrinsicMinMax.Min, IntrinsicWidthHeight.Height));
        }
        return measurePolicy.mo5measure3p2s80s(new IntrinsicsMeasureScope(intrinsicMeasureScope, intrinsicMeasureScope.getLayoutDirection()), arrayList, ConstraintsKt.Constraints$default(0, i10, 0, 0, 13, null)).get$h();
    }

    public static int d(MeasurePolicy measurePolicy, IntrinsicMeasureScope intrinsicMeasureScope, List list, int i10) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i11 = 0; i11 < size; i11++) {
            arrayList.add(new DefaultIntrinsicMeasurable((IntrinsicMeasurable) list.get(i11), IntrinsicMinMax.Min, IntrinsicWidthHeight.Width));
        }
        return measurePolicy.mo5measure3p2s80s(new IntrinsicsMeasureScope(intrinsicMeasureScope, intrinsicMeasureScope.getLayoutDirection()), arrayList, ConstraintsKt.Constraints$default(0, 0, 0, i10, 7, null)).get$w();
    }
}
