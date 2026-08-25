package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import r7.q;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0082\b\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\b\u0012\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0002HÂ\u0003¢\u0006\u0004\b\u0013\u0010\u0014J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u0004HÂ\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u0006HÂ\u0003¢\u0006\u0004\b\u0017\u0010\u0018J\u0016\u0010\u001b\u001a\u00020\bHÂ\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001c\u001a\u00020\nHÂ\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\fHÂ\u0003¢\u0006\u0004\b\u001e\u0010\u001fJ\u0016\u0010!\u001a\u00020\bHÂ\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b \u0010\u001aJ\u0010\u0010\"\u001a\u00020\u000fHÂ\u0003¢\u0006\u0004\b\"\u0010#J,\u0010-\u001a\u00020**\u00020$2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020&0%2\u0006\u0010)\u001a\u00020(H\u0016ø\u0001\u0001¢\u0006\u0004\b+\u0010,J)\u00101\u001a\u00020\u000f*\u00020.2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020/0%2\u0006\u00100\u001a\u00020\u000fH\u0016¢\u0006\u0004\b1\u00102J)\u00104\u001a\u00020\u000f*\u00020.2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020/0%2\u0006\u00103\u001a\u00020\u000fH\u0016¢\u0006\u0004\b4\u00102J)\u00105\u001a\u00020\u000f*\u00020.2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020/0%2\u0006\u00103\u001a\u00020\u000fH\u0016¢\u0006\u0004\b5\u00102J)\u00106\u001a\u00020\u000f*\u00020.2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020/0%2\u0006\u00100\u001a\u00020\u000fH\u0016¢\u0006\u0004\b6\u00102J3\u0010:\u001a\u00020\u000f2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020/0%2\u0006\u00107\u001a\u00020\u000f2\u0006\u00108\u001a\u00020\u000f2\u0006\u00109\u001a\u00020\u000f¢\u0006\u0004\b:\u0010;J+\u0010=\u001a\u00020\u000f2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020/0%2\u0006\u00100\u001a\u00020\u000f2\u0006\u0010<\u001a\u00020\u000f¢\u0006\u0004\b=\u0010>J3\u0010@\u001a\u00020\u000f2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020/0%2\u0006\u0010?\u001a\u00020\u000f2\u0006\u00108\u001a\u00020\u000f2\u0006\u00109\u001a\u00020\u000f¢\u0006\u0004\b@\u0010;Jg\u0010C\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\u000fHÆ\u0001ø\u0001\u0001¢\u0006\u0004\bA\u0010BJ\u0010\u0010E\u001a\u00020DHÖ\u0001¢\u0006\u0004\bE\u0010FJ\u0010\u0010G\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\bG\u0010#J\u001a\u0010K\u001a\u00020J2\b\u0010I\u001a\u0004\u0018\u00010HHÖ\u0003¢\u0006\u0004\bK\u0010LR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010MR\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010NR\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010OR\u001a\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\n\u0004\b\t\u0010PR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010QR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010RR\u001a\u0010\u000e\u001a\u00020\b8\u0002X\u0082\u0004ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\n\u0004\b\u000e\u0010PR\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010SR4\u0010V\u001a\u001f\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0T¢\u0006\u0002\bU8\u0006¢\u0006\f\n\u0004\bV\u0010W\u001a\u0004\bX\u0010YR4\u0010Z\u001a\u001f\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0T¢\u0006\u0002\bU8\u0006¢\u0006\f\n\u0004\bZ\u0010W\u001a\u0004\b[\u0010YR4\u0010\\\u001a\u001f\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0T¢\u0006\u0002\bU8\u0006¢\u0006\f\n\u0004\b\\\u0010W\u001a\u0004\b]\u0010YR4\u0010^\u001a\u001f\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0T¢\u0006\u0002\bU8\u0006¢\u0006\f\n\u0004\b^\u0010W\u001a\u0004\b_\u0010Y\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006`"}, d2 = {"Landroidx/compose/foundation/layout/FlowMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "Landroidx/compose/foundation/layout/LayoutOrientation;", "orientation", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "verticalArrangement", "Landroidx/compose/ui/unit/Dp;", "mainAxisArrangementSpacing", "Landroidx/compose/foundation/layout/SizeMode;", "crossAxisSize", "Landroidx/compose/foundation/layout/CrossAxisAlignment;", "crossAxisAlignment", "crossAxisArrangementSpacing", "", "maxItemsInMainAxis", "<init>", "(Landroidx/compose/foundation/layout/LayoutOrientation;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;FLandroidx/compose/foundation/layout/SizeMode;Landroidx/compose/foundation/layout/CrossAxisAlignment;FILkotlin/jvm/internal/h;)V", "component1", "()Landroidx/compose/foundation/layout/LayoutOrientation;", "component2", "()Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "component3", "()Landroidx/compose/foundation/layout/Arrangement$Vertical;", "component4-D9Ej5fM", "()F", "component4", "component5", "()Landroidx/compose/foundation/layout/SizeMode;", "component6", "()Landroidx/compose/foundation/layout/CrossAxisAlignment;", "component7-D9Ej5fM", "component7", "component8", "()I", "Landroidx/compose/ui/layout/MeasureScope;", "", "Landroidx/compose/ui/layout/Measurable;", "measurables", "Landroidx/compose/ui/unit/Constraints;", "constraints", "Landroidx/compose/ui/layout/MeasureResult;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "measure", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "minIntrinsicWidth", "(Landroidx/compose/ui/layout/IntrinsicMeasureScope;Ljava/util/List;I)I", "width", "minIntrinsicHeight", "maxIntrinsicHeight", "maxIntrinsicWidth", "crossAxisAvailable", "mainAxisSpacing", "crossAxisSpacing", "minIntrinsicMainAxisSize", "(Ljava/util/List;III)I", "arrangementSpacing", "maxIntrinsicMainAxisSize", "(Ljava/util/List;II)I", "mainAxisAvailable", "intrinsicCrossAxisSize", "copy-cBR-a5Y", "(Landroidx/compose/foundation/layout/LayoutOrientation;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;FLandroidx/compose/foundation/layout/SizeMode;Landroidx/compose/foundation/layout/CrossAxisAlignment;FI)Landroidx/compose/foundation/layout/FlowMeasurePolicy;", "copy", "", "toString", "()Ljava/lang/String;", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroidx/compose/foundation/layout/LayoutOrientation;", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "F", "Landroidx/compose/foundation/layout/SizeMode;", "Landroidx/compose/foundation/layout/CrossAxisAlignment;", "I", "Lkotlin/Function3;", "Lx6/n;", "maxMainAxisIntrinsicItemSize", "Lr7/q;", "getMaxMainAxisIntrinsicItemSize", "()Lr7/q;", "maxCrossAxisIntrinsicItemSize", "getMaxCrossAxisIntrinsicItemSize", "minCrossAxisIntrinsicItemSize", "getMinCrossAxisIntrinsicItemSize", "minMainAxisIntrinsicItemSize", "getMinMainAxisIntrinsicItemSize", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class FlowMeasurePolicy implements MeasurePolicy {
    private final CrossAxisAlignment crossAxisAlignment;
    private final float crossAxisArrangementSpacing;
    private final SizeMode crossAxisSize;
    private final Arrangement.Horizontal horizontalArrangement;
    private final float mainAxisArrangementSpacing;
    private final q<IntrinsicMeasurable, Integer, Integer, Integer> maxCrossAxisIntrinsicItemSize;
    private final int maxItemsInMainAxis;
    private final q<IntrinsicMeasurable, Integer, Integer, Integer> maxMainAxisIntrinsicItemSize;
    private final q<IntrinsicMeasurable, Integer, Integer, Integer> minCrossAxisIntrinsicItemSize;
    private final q<IntrinsicMeasurable, Integer, Integer, Integer> minMainAxisIntrinsicItemSize;
    private final LayoutOrientation orientation;
    private final Arrangement.Vertical verticalArrangement;

    public /* synthetic */ FlowMeasurePolicy(LayoutOrientation layoutOrientation, Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, float f10, SizeMode sizeMode, CrossAxisAlignment crossAxisAlignment, float f11, int i10, h hVar) {
        this(layoutOrientation, horizontal, vertical, f10, sizeMode, crossAxisAlignment, f11, i10);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    private final LayoutOrientation getOrientation() {
        return this.orientation;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    private final Arrangement.Horizontal getHorizontalArrangement() {
        return this.horizontalArrangement;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    private final Arrangement.Vertical getVerticalArrangement() {
        return this.verticalArrangement;
    }

    /* JADX INFO: renamed from: component4-D9Ej5fM, reason: not valid java name and from getter */
    private final float getMainAxisArrangementSpacing() {
        return this.mainAxisArrangementSpacing;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    private final SizeMode getCrossAxisSize() {
        return this.crossAxisSize;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    private final CrossAxisAlignment getCrossAxisAlignment() {
        return this.crossAxisAlignment;
    }

    /* JADX INFO: renamed from: component7-D9Ej5fM, reason: not valid java name and from getter */
    private final float getCrossAxisArrangementSpacing() {
        return this.crossAxisArrangementSpacing;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    private final int getMaxItemsInMainAxis() {
        return this.maxItemsInMainAxis;
    }

    /* JADX INFO: renamed from: copy-cBR-a5Y$default, reason: not valid java name */
    public static /* synthetic */ FlowMeasurePolicy m477copycBRa5Y$default(FlowMeasurePolicy flowMeasurePolicy, LayoutOrientation layoutOrientation, Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, float f10, SizeMode sizeMode, CrossAxisAlignment crossAxisAlignment, float f11, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            layoutOrientation = flowMeasurePolicy.orientation;
        }
        if ((i11 & 2) != 0) {
            horizontal = flowMeasurePolicy.horizontalArrangement;
        }
        if ((i11 & 4) != 0) {
            vertical = flowMeasurePolicy.verticalArrangement;
        }
        if ((i11 & 8) != 0) {
            f10 = flowMeasurePolicy.mainAxisArrangementSpacing;
        }
        if ((i11 & 16) != 0) {
            sizeMode = flowMeasurePolicy.crossAxisSize;
        }
        if ((i11 & 32) != 0) {
            crossAxisAlignment = flowMeasurePolicy.crossAxisAlignment;
        }
        if ((i11 & 64) != 0) {
            f11 = flowMeasurePolicy.crossAxisArrangementSpacing;
        }
        if ((i11 & 128) != 0) {
            i10 = flowMeasurePolicy.maxItemsInMainAxis;
        }
        float f12 = f11;
        int i12 = i10;
        SizeMode sizeMode2 = sizeMode;
        CrossAxisAlignment crossAxisAlignment2 = crossAxisAlignment;
        return flowMeasurePolicy.m478copycBRa5Y(layoutOrientation, horizontal, vertical, f10, sizeMode2, crossAxisAlignment2, f12, i12);
    }

    /* JADX INFO: renamed from: copy-cBR-a5Y, reason: not valid java name */
    public final FlowMeasurePolicy m478copycBRa5Y(LayoutOrientation orientation, Arrangement.Horizontal horizontalArrangement, Arrangement.Vertical verticalArrangement, float mainAxisArrangementSpacing, SizeMode crossAxisSize, CrossAxisAlignment crossAxisAlignment, float crossAxisArrangementSpacing, int maxItemsInMainAxis) {
        return new FlowMeasurePolicy(orientation, horizontalArrangement, verticalArrangement, mainAxisArrangementSpacing, crossAxisSize, crossAxisAlignment, crossAxisArrangementSpacing, maxItemsInMainAxis, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FlowMeasurePolicy)) {
            return false;
        }
        FlowMeasurePolicy flowMeasurePolicy = (FlowMeasurePolicy) other;
        return this.orientation == flowMeasurePolicy.orientation && p.a(this.horizontalArrangement, flowMeasurePolicy.horizontalArrangement) && p.a(this.verticalArrangement, flowMeasurePolicy.verticalArrangement) && Dp.m5683equalsimpl0(this.mainAxisArrangementSpacing, flowMeasurePolicy.mainAxisArrangementSpacing) && this.crossAxisSize == flowMeasurePolicy.crossAxisSize && p.a(this.crossAxisAlignment, flowMeasurePolicy.crossAxisAlignment) && Dp.m5683equalsimpl0(this.crossAxisArrangementSpacing, flowMeasurePolicy.crossAxisArrangementSpacing) && this.maxItemsInMainAxis == flowMeasurePolicy.maxItemsInMainAxis;
    }

    public final q<IntrinsicMeasurable, Integer, Integer, Integer> getMaxCrossAxisIntrinsicItemSize() {
        return this.maxCrossAxisIntrinsicItemSize;
    }

    public final q<IntrinsicMeasurable, Integer, Integer, Integer> getMaxMainAxisIntrinsicItemSize() {
        return this.maxMainAxisIntrinsicItemSize;
    }

    public final q<IntrinsicMeasurable, Integer, Integer, Integer> getMinCrossAxisIntrinsicItemSize() {
        return this.minCrossAxisIntrinsicItemSize;
    }

    public final q<IntrinsicMeasurable, Integer, Integer, Integer> getMinMainAxisIntrinsicItemSize() {
        return this.minMainAxisIntrinsicItemSize;
    }

    public int hashCode() {
        int iHashCode = this.orientation.hashCode() * 31;
        Arrangement.Horizontal horizontal = this.horizontalArrangement;
        int iHashCode2 = (iHashCode + (horizontal == null ? 0 : horizontal.hashCode())) * 31;
        Arrangement.Vertical vertical = this.verticalArrangement;
        return a0.c.C(this.crossAxisArrangementSpacing, (this.crossAxisAlignment.hashCode() + ((this.crossAxisSize.hashCode() + a0.c.C(this.mainAxisArrangementSpacing, (iHashCode2 + (vertical != null ? vertical.hashCode() : 0)) * 31, 31)) * 31)) * 31, 31) + this.maxItemsInMainAxis;
    }

    public final int intrinsicCrossAxisSize(List<? extends IntrinsicMeasurable> measurables, int mainAxisAvailable, int mainAxisSpacing, int crossAxisSpacing) {
        return FlowLayoutKt.intrinsicCrossAxisSize((List<? extends IntrinsicMeasurable>) measurables, (q<? super IntrinsicMeasurable, ? super Integer, ? super Integer, Integer>) this.minMainAxisIntrinsicItemSize, (q<? super IntrinsicMeasurable, ? super Integer, ? super Integer, Integer>) this.minCrossAxisIntrinsicItemSize, mainAxisAvailable, mainAxisSpacing, crossAxisSpacing, this.maxItemsInMainAxis);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i10) {
        return this.orientation == LayoutOrientation.Horizontal ? intrinsicCrossAxisSize(list, i10, intrinsicMeasureScope.mo279roundToPx0680j_4(this.mainAxisArrangementSpacing), intrinsicMeasureScope.mo279roundToPx0680j_4(this.crossAxisArrangementSpacing)) : maxIntrinsicMainAxisSize(list, i10, intrinsicMeasureScope.mo279roundToPx0680j_4(this.mainAxisArrangementSpacing));
    }

    public final int maxIntrinsicMainAxisSize(List<? extends IntrinsicMeasurable> measurables, int height, int arrangementSpacing) {
        return FlowLayoutKt.maxIntrinsicMainAxisSize(measurables, this.maxMainAxisIntrinsicItemSize, height, arrangementSpacing, this.maxItemsInMainAxis);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i10) {
        return this.orientation == LayoutOrientation.Horizontal ? maxIntrinsicMainAxisSize(list, i10, intrinsicMeasureScope.mo279roundToPx0680j_4(this.mainAxisArrangementSpacing)) : intrinsicCrossAxisSize(list, i10, intrinsicMeasureScope.mo279roundToPx0680j_4(this.mainAxisArrangementSpacing), intrinsicMeasureScope.mo279roundToPx0680j_4(this.crossAxisArrangementSpacing));
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo5measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j10) throws Throwable {
        int mainAxisTotalSize;
        if (list.isEmpty()) {
            return MeasureScope.CC.q(measureScope, 0, 0, null, FlowMeasurePolicy$measure$1.INSTANCE, 4, null);
        }
        RowColumnMeasurementHelper rowColumnMeasurementHelper = new RowColumnMeasurementHelper(this.orientation, this.horizontalArrangement, this.verticalArrangement, this.mainAxisArrangementSpacing, this.crossAxisSize, this.crossAxisAlignment, list, new Placeable[list.size()], null);
        MeasureScope measureScope2 = measureScope;
        FlowResult flowResultM473breakDownItemsw1Onq5I = FlowLayoutKt.m473breakDownItemsw1Onq5I(measureScope2, rowColumnMeasurementHelper, this.orientation, OrientationIndependentConstraints.m498constructorimpl(j10, this.orientation), this.maxItemsInMainAxis);
        MutableVector<RowColumnMeasureHelperResult> items = flowResultM473breakDownItemsw1Onq5I.getItems();
        int size = items.getSize();
        int[] iArr = new int[size];
        for (int i10 = 0; i10 < size; i10++) {
            iArr[i10] = items.getContent()[i10].getCrossAxisSize();
        }
        int[] iArr2 = new int[size];
        int crossAxisTotalSize = flowResultM473breakDownItemsw1Onq5I.getCrossAxisTotalSize() + ((items.getSize() - 1) * measureScope2.mo279roundToPx0680j_4(this.crossAxisArrangementSpacing));
        LayoutOrientation layoutOrientation = this.orientation;
        LayoutOrientation layoutOrientation2 = LayoutOrientation.Horizontal;
        if (layoutOrientation == layoutOrientation2) {
            Arrangement.Vertical vertical = this.verticalArrangement;
            if (vertical == null) {
                throw new IllegalArgumentException("null verticalArrangement");
            }
            vertical.arrange(measureScope2, crossAxisTotalSize, iArr, iArr2);
        } else {
            Arrangement.Horizontal horizontal = this.horizontalArrangement;
            if (horizontal == null) {
                throw new IllegalArgumentException("null horizontalArrangement");
            }
            horizontal.arrange(measureScope, crossAxisTotalSize, iArr, measureScope.getLayoutDirection(), iArr2);
            measureScope2 = measureScope;
        }
        if (this.orientation == layoutOrientation2) {
            crossAxisTotalSize = flowResultM473breakDownItemsw1Onq5I.getMainAxisTotalSize();
            mainAxisTotalSize = crossAxisTotalSize;
        } else {
            mainAxisTotalSize = flowResultM473breakDownItemsw1Onq5I.getMainAxisTotalSize();
        }
        return MeasureScope.CC.q(measureScope2, ConstraintsKt.m5660constrainWidthK40F9xA(j10, crossAxisTotalSize), ConstraintsKt.m5659constrainHeightK40F9xA(j10, mainAxisTotalSize), null, new FlowMeasurePolicy$measure$6(flowResultM473breakDownItemsw1Onq5I, rowColumnMeasurementHelper, iArr2, measureScope2), 4, null);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i10) {
        return this.orientation == LayoutOrientation.Horizontal ? intrinsicCrossAxisSize(list, i10, intrinsicMeasureScope.mo279roundToPx0680j_4(this.mainAxisArrangementSpacing), intrinsicMeasureScope.mo279roundToPx0680j_4(this.crossAxisArrangementSpacing)) : minIntrinsicMainAxisSize(list, i10, intrinsicMeasureScope.mo279roundToPx0680j_4(this.mainAxisArrangementSpacing), intrinsicMeasureScope.mo279roundToPx0680j_4(this.crossAxisArrangementSpacing));
    }

    public final int minIntrinsicMainAxisSize(List<? extends IntrinsicMeasurable> measurables, int crossAxisAvailable, int mainAxisSpacing, int crossAxisSpacing) {
        return FlowLayoutKt.minIntrinsicMainAxisSize(measurables, this.minMainAxisIntrinsicItemSize, this.minCrossAxisIntrinsicItemSize, crossAxisAvailable, mainAxisSpacing, crossAxisSpacing, this.maxItemsInMainAxis);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i10) {
        return this.orientation == LayoutOrientation.Horizontal ? minIntrinsicMainAxisSize(list, i10, intrinsicMeasureScope.mo279roundToPx0680j_4(this.mainAxisArrangementSpacing), intrinsicMeasureScope.mo279roundToPx0680j_4(this.crossAxisArrangementSpacing)) : intrinsicCrossAxisSize(list, i10, intrinsicMeasureScope.mo279roundToPx0680j_4(this.mainAxisArrangementSpacing), intrinsicMeasureScope.mo279roundToPx0680j_4(this.crossAxisArrangementSpacing));
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("FlowMeasurePolicy(orientation=");
        sb2.append(this.orientation);
        sb2.append(", horizontalArrangement=");
        sb2.append(this.horizontalArrangement);
        sb2.append(", verticalArrangement=");
        sb2.append(this.verticalArrangement);
        sb2.append(", mainAxisArrangementSpacing=");
        a0.c.v(this.mainAxisArrangementSpacing, sb2, ", crossAxisSize=");
        sb2.append(this.crossAxisSize);
        sb2.append(", crossAxisAlignment=");
        sb2.append(this.crossAxisAlignment);
        sb2.append(", crossAxisArrangementSpacing=");
        a0.c.v(this.crossAxisArrangementSpacing, sb2, ", maxItemsInMainAxis=");
        return a0.c.o(sb2, this.maxItemsInMainAxis, ')');
    }

    private FlowMeasurePolicy(LayoutOrientation layoutOrientation, Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, float f10, SizeMode sizeMode, CrossAxisAlignment crossAxisAlignment, float f11, int i10) {
        this.orientation = layoutOrientation;
        this.horizontalArrangement = horizontal;
        this.verticalArrangement = vertical;
        this.mainAxisArrangementSpacing = f10;
        this.crossAxisSize = sizeMode;
        this.crossAxisAlignment = crossAxisAlignment;
        this.crossAxisArrangementSpacing = f11;
        this.maxItemsInMainAxis = i10;
        LayoutOrientation layoutOrientation2 = LayoutOrientation.Horizontal;
        this.maxMainAxisIntrinsicItemSize = layoutOrientation == layoutOrientation2 ? FlowMeasurePolicy$maxMainAxisIntrinsicItemSize$1.INSTANCE : FlowMeasurePolicy$maxMainAxisIntrinsicItemSize$2.INSTANCE;
        this.maxCrossAxisIntrinsicItemSize = layoutOrientation == layoutOrientation2 ? FlowMeasurePolicy$maxCrossAxisIntrinsicItemSize$1.INSTANCE : FlowMeasurePolicy$maxCrossAxisIntrinsicItemSize$2.INSTANCE;
        this.minCrossAxisIntrinsicItemSize = layoutOrientation == layoutOrientation2 ? FlowMeasurePolicy$minCrossAxisIntrinsicItemSize$1.INSTANCE : FlowMeasurePolicy$minCrossAxisIntrinsicItemSize$2.INSTANCE;
        this.minMainAxisIntrinsicItemSize = layoutOrientation == layoutOrientation2 ? FlowMeasurePolicy$minMainAxisIntrinsicItemSize$1.INSTANCE : FlowMeasurePolicy$minMainAxisIntrinsicItemSize$2.INSTANCE;
    }
}
