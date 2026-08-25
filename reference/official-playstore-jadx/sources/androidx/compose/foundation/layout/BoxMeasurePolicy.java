package androidx.compose.foundation.layout;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.g;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.i0;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u0007\u001a\u00020\u0003HÂ\u0003J\t\u0010\b\u001a\u00020\u0005HÂ\u0003J\u001d\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\n\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J,\u0010\u0011\u001a\u00020\u0012*\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0017\u001a\u00020\u0018H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001b"}, d2 = {"Landroidx/compose/foundation/layout/BoxMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "alignment", "Landroidx/compose/ui/Alignment;", "propagateMinConstraints", "", "(Landroidx/compose/ui/Alignment;Z)V", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class BoxMeasurePolicy implements MeasurePolicy {
    private final Alignment alignment;
    private final boolean propagateMinConstraints;

    public BoxMeasurePolicy(Alignment alignment, boolean z) {
        this.alignment = alignment;
        this.propagateMinConstraints = z;
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    private final Alignment getAlignment() {
        return this.alignment;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    private final boolean getPropagateMinConstraints() {
        return this.propagateMinConstraints;
    }

    public static /* synthetic */ BoxMeasurePolicy copy$default(BoxMeasurePolicy boxMeasurePolicy, Alignment alignment, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            alignment = boxMeasurePolicy.alignment;
        }
        if ((i10 & 2) != 0) {
            z = boxMeasurePolicy.propagateMinConstraints;
        }
        return boxMeasurePolicy.copy(alignment, z);
    }

    public final BoxMeasurePolicy copy(Alignment alignment, boolean propagateMinConstraints) {
        return new BoxMeasurePolicy(alignment, propagateMinConstraints);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BoxMeasurePolicy)) {
            return false;
        }
        BoxMeasurePolicy boxMeasurePolicy = (BoxMeasurePolicy) other;
        return p.a(this.alignment, boxMeasurePolicy.alignment) && this.propagateMinConstraints == boxMeasurePolicy.propagateMinConstraints;
    }

    public int hashCode() {
        return (this.alignment.hashCode() * 31) + (this.propagateMinConstraints ? 1231 : 1237);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public final /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i10) {
        return g.a(this, intrinsicMeasureScope, list, i10);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public final /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i10) {
        return g.b(this, intrinsicMeasureScope, list, i10);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo5measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j10) {
        long j11;
        int iM5648getMinWidthimpl;
        int iM5647getMinHeightimpl;
        Placeable placeableMo4631measureBRTryo0;
        if (list.isEmpty()) {
            return MeasureScope.CC.q(measureScope, Constraints.m5648getMinWidthimpl(j10), Constraints.m5647getMinHeightimpl(j10), null, BoxMeasurePolicy$measure$1.INSTANCE, 4, null);
        }
        if (this.propagateMinConstraints) {
            j11 = j10;
        } else {
            j11 = j10;
            j10 = Constraints.m5637copyZbe2FdA$default(j11, 0, 0, 0, 0, 10, null);
        }
        if (list.size() == 1) {
            Measurable measurable = list.get(0);
            if (BoxKt.getMatchesParentSize(measurable)) {
                iM5648getMinWidthimpl = Constraints.m5648getMinWidthimpl(j11);
                iM5647getMinHeightimpl = Constraints.m5647getMinHeightimpl(j11);
                placeableMo4631measureBRTryo0 = measurable.mo4631measureBRTryo0(Constraints.INSTANCE.m5654fixedJhjzzOo(Constraints.m5648getMinWidthimpl(j11), Constraints.m5647getMinHeightimpl(j11)));
            } else {
                placeableMo4631measureBRTryo0 = measurable.mo4631measureBRTryo0(j10);
                iM5648getMinWidthimpl = Math.max(Constraints.m5648getMinWidthimpl(j11), placeableMo4631measureBRTryo0.getWidth());
                iM5647getMinHeightimpl = Math.max(Constraints.m5647getMinHeightimpl(j11), placeableMo4631measureBRTryo0.getHeight());
            }
            int i10 = iM5648getMinWidthimpl;
            int i11 = iM5647getMinHeightimpl;
            return MeasureScope.CC.q(measureScope, i10, i11, null, new BoxMeasurePolicy$measure$2(placeableMo4631measureBRTryo0, measurable, measureScope, i10, i11, this), 4, null);
        }
        Placeable[] placeableArr = new Placeable[list.size()];
        i0 i0Var = new i0();
        i0Var.f19744i = Constraints.m5648getMinWidthimpl(j11);
        i0 i0Var2 = new i0();
        i0Var2.f19744i = Constraints.m5647getMinHeightimpl(j11);
        int size = list.size();
        boolean z = false;
        for (int i12 = 0; i12 < size; i12++) {
            Measurable measurable2 = list.get(i12);
            if (BoxKt.getMatchesParentSize(measurable2)) {
                z = true;
            } else {
                Placeable placeableMo4631measureBRTryo02 = measurable2.mo4631measureBRTryo0(j10);
                placeableArr[i12] = placeableMo4631measureBRTryo02;
                i0Var.f19744i = Math.max(i0Var.f19744i, placeableMo4631measureBRTryo02.getWidth());
                i0Var2.f19744i = Math.max(i0Var2.f19744i, placeableMo4631measureBRTryo02.getHeight());
            }
        }
        if (z) {
            int i13 = i0Var.f19744i;
            int i14 = i13 != Integer.MAX_VALUE ? i13 : 0;
            int i15 = i0Var2.f19744i;
            long jConstraints = ConstraintsKt.Constraints(i14, i13, i15 != Integer.MAX_VALUE ? i15 : 0, i15);
            int size2 = list.size();
            for (int i16 = 0; i16 < size2; i16++) {
                Measurable measurable3 = list.get(i16);
                if (BoxKt.getMatchesParentSize(measurable3)) {
                    placeableArr[i16] = measurable3.mo4631measureBRTryo0(jConstraints);
                }
            }
        }
        return MeasureScope.CC.q(measureScope, i0Var.f19744i, i0Var2.f19744i, null, new BoxMeasurePolicy$measure$5(placeableArr, list, measureScope, i0Var, i0Var2, this), 4, null);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public final /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i10) {
        return g.c(this, intrinsicMeasureScope, list, i10);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public final /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i10) {
        return g.d(this, intrinsicMeasureScope, list, i10);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("BoxMeasurePolicy(alignment=");
        sb2.append(this.alignment);
        sb2.append(", propagateMinConstraints=");
        return a0.c.r(sb2, this.propagateMinConstraints, ')');
    }
}
