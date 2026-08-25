package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Dp;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\u0080\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0002HÂ\u0003¢\u0006\u0004\b\u0010\u0010\u0011J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u0004HÂ\u0003¢\u0006\u0004\b\u0012\u0010\u0013J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u0006HÂ\u0003¢\u0006\u0004\b\u0014\u0010\u0015J\u0016\u0010\u0018\u001a\u00020\bHÂ\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0019\u001a\u00020\nHÂ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\fHÂ\u0003¢\u0006\u0004\b\u001b\u0010\u001cJ,\u0010&\u001a\u00020#*\u00020\u001d2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\u0006\u0010\"\u001a\u00020!H\u0016ø\u0001\u0001¢\u0006\u0004\b$\u0010%J)\u0010+\u001a\u00020)*\u00020'2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020(0\u001e2\u0006\u0010*\u001a\u00020)H\u0016¢\u0006\u0004\b+\u0010,J)\u0010.\u001a\u00020)*\u00020'2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020(0\u001e2\u0006\u0010-\u001a\u00020)H\u0016¢\u0006\u0004\b.\u0010,J)\u0010/\u001a\u00020)*\u00020'2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020(0\u001e2\u0006\u0010*\u001a\u00020)H\u0016¢\u0006\u0004\b/\u0010,J)\u00100\u001a\u00020)*\u00020'2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020(0\u001e2\u0006\u0010-\u001a\u00020)H\u0016¢\u0006\u0004\b0\u0010,JS\u00103\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\fHÆ\u0001ø\u0001\u0001¢\u0006\u0004\b1\u00102J\u0010\u00105\u001a\u000204HÖ\u0001¢\u0006\u0004\b5\u00106J\u0010\u00107\u001a\u00020)HÖ\u0001¢\u0006\u0004\b7\u00108J\u001a\u0010<\u001a\u00020;2\b\u0010:\u001a\u0004\u0018\u000109HÖ\u0003¢\u0006\u0004\b<\u0010=R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010>R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010?R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010@R\u001a\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\n\u0004\b\t\u0010AR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010BR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010C\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006D"}, d2 = {"Landroidx/compose/foundation/layout/RowColumnMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "Landroidx/compose/foundation/layout/LayoutOrientation;", "orientation", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "verticalArrangement", "Landroidx/compose/ui/unit/Dp;", "arrangementSpacing", "Landroidx/compose/foundation/layout/SizeMode;", "crossAxisSize", "Landroidx/compose/foundation/layout/CrossAxisAlignment;", "crossAxisAlignment", "<init>", "(Landroidx/compose/foundation/layout/LayoutOrientation;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;FLandroidx/compose/foundation/layout/SizeMode;Landroidx/compose/foundation/layout/CrossAxisAlignment;Lkotlin/jvm/internal/h;)V", "component1", "()Landroidx/compose/foundation/layout/LayoutOrientation;", "component2", "()Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "component3", "()Landroidx/compose/foundation/layout/Arrangement$Vertical;", "component4-D9Ej5fM", "()F", "component4", "component5", "()Landroidx/compose/foundation/layout/SizeMode;", "component6", "()Landroidx/compose/foundation/layout/CrossAxisAlignment;", "Landroidx/compose/ui/layout/MeasureScope;", "", "Landroidx/compose/ui/layout/Measurable;", "measurables", "Landroidx/compose/ui/unit/Constraints;", "constraints", "Landroidx/compose/ui/layout/MeasureResult;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "measure", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "", "height", "minIntrinsicWidth", "(Landroidx/compose/ui/layout/IntrinsicMeasureScope;Ljava/util/List;I)I", "width", "minIntrinsicHeight", "maxIntrinsicWidth", "maxIntrinsicHeight", "copy-gwO9Abs", "(Landroidx/compose/foundation/layout/LayoutOrientation;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;FLandroidx/compose/foundation/layout/SizeMode;Landroidx/compose/foundation/layout/CrossAxisAlignment;)Landroidx/compose/foundation/layout/RowColumnMeasurePolicy;", "copy", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroidx/compose/foundation/layout/LayoutOrientation;", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "F", "Landroidx/compose/foundation/layout/SizeMode;", "Landroidx/compose/foundation/layout/CrossAxisAlignment;", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class RowColumnMeasurePolicy implements MeasurePolicy {
    public static final int $stable = 0;
    private final float arrangementSpacing;
    private final CrossAxisAlignment crossAxisAlignment;
    private final SizeMode crossAxisSize;
    private final Arrangement.Horizontal horizontalArrangement;
    private final LayoutOrientation orientation;
    private final Arrangement.Vertical verticalArrangement;

    public /* synthetic */ RowColumnMeasurePolicy(LayoutOrientation layoutOrientation, Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, float f10, SizeMode sizeMode, CrossAxisAlignment crossAxisAlignment, h hVar) {
        this(layoutOrientation, horizontal, vertical, f10, sizeMode, crossAxisAlignment);
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
    private final float getArrangementSpacing() {
        return this.arrangementSpacing;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    private final SizeMode getCrossAxisSize() {
        return this.crossAxisSize;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    private final CrossAxisAlignment getCrossAxisAlignment() {
        return this.crossAxisAlignment;
    }

    /* JADX INFO: renamed from: copy-gwO9Abs$default, reason: not valid java name */
    public static /* synthetic */ RowColumnMeasurePolicy m555copygwO9Abs$default(RowColumnMeasurePolicy rowColumnMeasurePolicy, LayoutOrientation layoutOrientation, Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, float f10, SizeMode sizeMode, CrossAxisAlignment crossAxisAlignment, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            layoutOrientation = rowColumnMeasurePolicy.orientation;
        }
        if ((i10 & 2) != 0) {
            horizontal = rowColumnMeasurePolicy.horizontalArrangement;
        }
        if ((i10 & 4) != 0) {
            vertical = rowColumnMeasurePolicy.verticalArrangement;
        }
        if ((i10 & 8) != 0) {
            f10 = rowColumnMeasurePolicy.arrangementSpacing;
        }
        if ((i10 & 16) != 0) {
            sizeMode = rowColumnMeasurePolicy.crossAxisSize;
        }
        if ((i10 & 32) != 0) {
            crossAxisAlignment = rowColumnMeasurePolicy.crossAxisAlignment;
        }
        SizeMode sizeMode2 = sizeMode;
        CrossAxisAlignment crossAxisAlignment2 = crossAxisAlignment;
        return rowColumnMeasurePolicy.m556copygwO9Abs(layoutOrientation, horizontal, vertical, f10, sizeMode2, crossAxisAlignment2);
    }

    /* JADX INFO: renamed from: copy-gwO9Abs, reason: not valid java name */
    public final RowColumnMeasurePolicy m556copygwO9Abs(LayoutOrientation orientation, Arrangement.Horizontal horizontalArrangement, Arrangement.Vertical verticalArrangement, float arrangementSpacing, SizeMode crossAxisSize, CrossAxisAlignment crossAxisAlignment) {
        return new RowColumnMeasurePolicy(orientation, horizontalArrangement, verticalArrangement, arrangementSpacing, crossAxisSize, crossAxisAlignment, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RowColumnMeasurePolicy)) {
            return false;
        }
        RowColumnMeasurePolicy rowColumnMeasurePolicy = (RowColumnMeasurePolicy) other;
        return this.orientation == rowColumnMeasurePolicy.orientation && p.a(this.horizontalArrangement, rowColumnMeasurePolicy.horizontalArrangement) && p.a(this.verticalArrangement, rowColumnMeasurePolicy.verticalArrangement) && Dp.m5683equalsimpl0(this.arrangementSpacing, rowColumnMeasurePolicy.arrangementSpacing) && this.crossAxisSize == rowColumnMeasurePolicy.crossAxisSize && p.a(this.crossAxisAlignment, rowColumnMeasurePolicy.crossAxisAlignment);
    }

    public int hashCode() {
        int iHashCode = this.orientation.hashCode() * 31;
        Arrangement.Horizontal horizontal = this.horizontalArrangement;
        int iHashCode2 = (iHashCode + (horizontal == null ? 0 : horizontal.hashCode())) * 31;
        Arrangement.Vertical vertical = this.verticalArrangement;
        return this.crossAxisAlignment.hashCode() + ((this.crossAxisSize.hashCode() + a0.c.C(this.arrangementSpacing, (iHashCode2 + (vertical != null ? vertical.hashCode() : 0)) * 31, 31)) * 31);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i10) {
        return ((Number) RowColumnImplKt.MaxIntrinsicHeightMeasureBlock(this.orientation).invoke(list, Integer.valueOf(i10), Integer.valueOf(intrinsicMeasureScope.mo279roundToPx0680j_4(this.arrangementSpacing)))).intValue();
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i10) {
        return ((Number) RowColumnImplKt.MaxIntrinsicWidthMeasureBlock(this.orientation).invoke(list, Integer.valueOf(i10), Integer.valueOf(intrinsicMeasureScope.mo279roundToPx0680j_4(this.arrangementSpacing)))).intValue();
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo5measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j10) throws Throwable {
        int crossAxisSize;
        int mainAxisSize;
        RowColumnMeasurementHelper rowColumnMeasurementHelper = new RowColumnMeasurementHelper(this.orientation, this.horizontalArrangement, this.verticalArrangement, this.arrangementSpacing, this.crossAxisSize, this.crossAxisAlignment, list, new Placeable[list.size()], null);
        RowColumnMeasureHelperResult rowColumnMeasureHelperResultM558measureWithoutPlacing_EkL_Y = rowColumnMeasurementHelper.m558measureWithoutPlacing_EkL_Y(measureScope, j10, 0, list.size());
        if (this.orientation == LayoutOrientation.Horizontal) {
            crossAxisSize = rowColumnMeasureHelperResultM558measureWithoutPlacing_EkL_Y.getMainAxisSize();
            mainAxisSize = rowColumnMeasureHelperResultM558measureWithoutPlacing_EkL_Y.getCrossAxisSize();
        } else {
            crossAxisSize = rowColumnMeasureHelperResultM558measureWithoutPlacing_EkL_Y.getCrossAxisSize();
            mainAxisSize = rowColumnMeasureHelperResultM558measureWithoutPlacing_EkL_Y.getMainAxisSize();
        }
        return MeasureScope.CC.q(measureScope, crossAxisSize, mainAxisSize, null, new RowColumnMeasurePolicy$measure$1(rowColumnMeasurementHelper, rowColumnMeasureHelperResultM558measureWithoutPlacing_EkL_Y, measureScope), 4, null);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i10) {
        return ((Number) RowColumnImplKt.MinIntrinsicHeightMeasureBlock(this.orientation).invoke(list, Integer.valueOf(i10), Integer.valueOf(intrinsicMeasureScope.mo279roundToPx0680j_4(this.arrangementSpacing)))).intValue();
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i10) {
        return ((Number) RowColumnImplKt.MinIntrinsicWidthMeasureBlock(this.orientation).invoke(list, Integer.valueOf(i10), Integer.valueOf(intrinsicMeasureScope.mo279roundToPx0680j_4(this.arrangementSpacing)))).intValue();
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("RowColumnMeasurePolicy(orientation=");
        sb2.append(this.orientation);
        sb2.append(", horizontalArrangement=");
        sb2.append(this.horizontalArrangement);
        sb2.append(", verticalArrangement=");
        sb2.append(this.verticalArrangement);
        sb2.append(", arrangementSpacing=");
        a0.c.v(this.arrangementSpacing, sb2, ", crossAxisSize=");
        sb2.append(this.crossAxisSize);
        sb2.append(", crossAxisAlignment=");
        sb2.append(this.crossAxisAlignment);
        sb2.append(')');
        return sb2.toString();
    }

    private RowColumnMeasurePolicy(LayoutOrientation layoutOrientation, Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, float f10, SizeMode sizeMode, CrossAxisAlignment crossAxisAlignment) {
        this.orientation = layoutOrientation;
        this.horizontalArrangement = horizontal;
        this.verticalArrangement = vertical;
        this.arrangementSpacing = f10;
        this.crossAxisSize = sizeMode;
        this.crossAxisAlignment = crossAxisAlignment;
    }
}
