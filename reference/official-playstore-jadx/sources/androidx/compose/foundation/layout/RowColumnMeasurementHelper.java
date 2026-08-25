package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001d\b\u0000\u0018\u00002\u00020\u0001BY\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u000e\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0011¢\u0006\u0004\b\u0014\u0010\u0015J/\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u001bH\u0002¢\u0006\u0004\b\u001a\u0010\u001dJ9\u0010%\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u00122\b\u0010 \u001a\u0004\u0018\u00010\u001f2\u0006\u0010!\u001a\u00020\u00162\u0006\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020\u0016H\u0002¢\u0006\u0004\b%\u0010&J\u0011\u0010'\u001a\u00020\u0016*\u00020\u0012¢\u0006\u0004\b'\u0010(J\u0011\u0010\u000b\u001a\u00020\u0016*\u00020\u0012¢\u0006\u0004\b\u000b\u0010(J0\u00100\u001a\u00020-2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020\u00162\u0006\u0010,\u001a\u00020\u0016ø\u0001\u0000¢\u0006\u0004\b.\u0010/J-\u00106\u001a\u0002052\u0006\u00102\u001a\u0002012\u0006\u00103\u001a\u00020-2\u0006\u00104\u001a\u00020\u00162\u0006\u0010#\u001a\u00020\"¢\u0006\u0004\b6\u00107R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u00108\u001a\u0004\b9\u0010:R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010;\u001a\u0004\b<\u0010=R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010>\u001a\u0004\b?\u0010@R\u001d\u0010\t\u001a\u00020\b8\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\t\u0010A\u001a\u0004\bB\u0010CR\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010D\u001a\u0004\bE\u0010FR\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u0010G\u001a\u0004\bH\u0010IR\u001d\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006¢\u0006\f\n\u0004\b\u0010\u0010J\u001a\u0004\bK\u0010LR\u001f\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00118\u0006¢\u0006\f\n\u0004\b\u0013\u0010M\u001a\u0004\bN\u0010OR\u001c\u0010P\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bP\u0010Q\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006R"}, d2 = {"Landroidx/compose/foundation/layout/RowColumnMeasurementHelper;", "", "Landroidx/compose/foundation/layout/LayoutOrientation;", "orientation", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "verticalArrangement", "Landroidx/compose/ui/unit/Dp;", "arrangementSpacing", "Landroidx/compose/foundation/layout/SizeMode;", "crossAxisSize", "Landroidx/compose/foundation/layout/CrossAxisAlignment;", "crossAxisAlignment", "", "Landroidx/compose/ui/layout/Measurable;", "measurables", "", "Landroidx/compose/ui/layout/Placeable;", "placeables", "<init>", "(Landroidx/compose/foundation/layout/LayoutOrientation;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;FLandroidx/compose/foundation/layout/SizeMode;Landroidx/compose/foundation/layout/CrossAxisAlignment;Ljava/util/List;[Landroidx/compose/ui/layout/Placeable;Lkotlin/jvm/internal/h;)V", "", "mainAxisLayoutSize", "", "childrenMainAxisSize", "mainAxisPositions", "Landroidx/compose/ui/layout/MeasureScope;", "measureScope", "(I[I[ILandroidx/compose/ui/layout/MeasureScope;)[I", "placeable", "Landroidx/compose/foundation/layout/RowColumnParentData;", "parentData", "crossAxisLayoutSize", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "beforeCrossAxisAlignmentLine", "getCrossAxisPosition", "(Landroidx/compose/ui/layout/Placeable;Landroidx/compose/foundation/layout/RowColumnParentData;ILandroidx/compose/ui/unit/LayoutDirection;I)I", "mainAxisSize", "(Landroidx/compose/ui/layout/Placeable;)I", "Landroidx/compose/ui/unit/Constraints;", "constraints", "startIndex", "endIndex", "Landroidx/compose/foundation/layout/RowColumnMeasureHelperResult;", "measureWithoutPlacing-_EkL_-Y", "(Landroidx/compose/ui/layout/MeasureScope;JII)Landroidx/compose/foundation/layout/RowColumnMeasureHelperResult;", "measureWithoutPlacing", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "placeableScope", "measureResult", "crossAxisOffset", "Lx6/t0;", "placeHelper", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;Landroidx/compose/foundation/layout/RowColumnMeasureHelperResult;ILandroidx/compose/ui/unit/LayoutDirection;)V", "Landroidx/compose/foundation/layout/LayoutOrientation;", "getOrientation", "()Landroidx/compose/foundation/layout/LayoutOrientation;", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "getHorizontalArrangement", "()Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "getVerticalArrangement", "()Landroidx/compose/foundation/layout/Arrangement$Vertical;", "F", "getArrangementSpacing-D9Ej5fM", "()F", "Landroidx/compose/foundation/layout/SizeMode;", "getCrossAxisSize", "()Landroidx/compose/foundation/layout/SizeMode;", "Landroidx/compose/foundation/layout/CrossAxisAlignment;", "getCrossAxisAlignment", "()Landroidx/compose/foundation/layout/CrossAxisAlignment;", "Ljava/util/List;", "getMeasurables", "()Ljava/util/List;", "[Landroidx/compose/ui/layout/Placeable;", "getPlaceables", "()[Landroidx/compose/ui/layout/Placeable;", "rowColumnParentData", "[Landroidx/compose/foundation/layout/RowColumnParentData;", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class RowColumnMeasurementHelper {
    public static final int $stable = 8;
    private final float arrangementSpacing;
    private final CrossAxisAlignment crossAxisAlignment;
    private final SizeMode crossAxisSize;
    private final Arrangement.Horizontal horizontalArrangement;
    private final List<Measurable> measurables;
    private final LayoutOrientation orientation;
    private final Placeable[] placeables;
    private final RowColumnParentData[] rowColumnParentData;
    private final Arrangement.Vertical verticalArrangement;

    public /* synthetic */ RowColumnMeasurementHelper(LayoutOrientation layoutOrientation, Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, float f10, SizeMode sizeMode, CrossAxisAlignment crossAxisAlignment, List list, Placeable[] placeableArr, h hVar) {
        this(layoutOrientation, horizontal, vertical, f10, sizeMode, crossAxisAlignment, list, placeableArr);
    }

    private final int getCrossAxisPosition(Placeable placeable, RowColumnParentData parentData, int crossAxisLayoutSize, LayoutDirection layoutDirection, int beforeCrossAxisAlignmentLine) {
        CrossAxisAlignment crossAxisAlignment;
        if (parentData == null || (crossAxisAlignment = parentData.getCrossAxisAlignment()) == null) {
            crossAxisAlignment = this.crossAxisAlignment;
        }
        int iCrossAxisSize = crossAxisLayoutSize - crossAxisSize(placeable);
        if (this.orientation == LayoutOrientation.Horizontal) {
            layoutDirection = LayoutDirection.Ltr;
        }
        return crossAxisAlignment.align$foundation_layout_release(iCrossAxisSize, layoutDirection, placeable, beforeCrossAxisAlignmentLine);
    }

    private final int[] mainAxisPositions(int mainAxisLayoutSize, int[] childrenMainAxisSize, int[] mainAxisPositions, MeasureScope measureScope) {
        if (this.orientation == LayoutOrientation.Vertical) {
            Arrangement.Vertical vertical = this.verticalArrangement;
            if (vertical == null) {
                throw new IllegalArgumentException("null verticalArrangement in Column");
            }
            vertical.arrange(measureScope, mainAxisLayoutSize, childrenMainAxisSize, mainAxisPositions);
            return mainAxisPositions;
        }
        Arrangement.Horizontal horizontal = this.horizontalArrangement;
        if (horizontal == null) {
            throw new IllegalArgumentException("null horizontalArrangement in Row");
        }
        horizontal.arrange(measureScope, mainAxisLayoutSize, childrenMainAxisSize, measureScope.getLayoutDirection(), mainAxisPositions);
        return mainAxisPositions;
    }

    public final int crossAxisSize(Placeable placeable) {
        return this.orientation == LayoutOrientation.Horizontal ? placeable.getHeight() : placeable.getWidth();
    }

    /* JADX INFO: renamed from: getArrangementSpacing-D9Ej5fM, reason: not valid java name and from getter */
    public final float getArrangementSpacing() {
        return this.arrangementSpacing;
    }

    public final CrossAxisAlignment getCrossAxisAlignment() {
        return this.crossAxisAlignment;
    }

    public final SizeMode getCrossAxisSize() {
        return this.crossAxisSize;
    }

    public final Arrangement.Horizontal getHorizontalArrangement() {
        return this.horizontalArrangement;
    }

    public final List<Measurable> getMeasurables() {
        return this.measurables;
    }

    public final LayoutOrientation getOrientation() {
        return this.orientation;
    }

    public final Placeable[] getPlaceables() {
        return this.placeables;
    }

    public final Arrangement.Vertical getVerticalArrangement() {
        return this.verticalArrangement;
    }

    public final int mainAxisSize(Placeable placeable) {
        return this.orientation == LayoutOrientation.Horizontal ? placeable.getWidth() : placeable.getHeight();
    }

    /* JADX INFO: renamed from: measureWithoutPlacing-_EkL_-Y, reason: not valid java name */
    public final RowColumnMeasureHelperResult m558measureWithoutPlacing_EkL_Y(MeasureScope measureScope, long constraints, int startIndex, int endIndex) throws Throwable {
        long j10;
        float f10;
        int i10;
        String str;
        float f11;
        String str2;
        String str3;
        String str4;
        long j11;
        long j12;
        int iO;
        long j13;
        int i11;
        String str5;
        String str6;
        long j14;
        long j15;
        String str7;
        String str8;
        float f12;
        String str9;
        int i12;
        int iMax;
        int i13;
        int i14;
        int i15;
        int i16;
        float f13;
        long j16;
        long j17;
        int i17;
        RowColumnMeasurementHelper rowColumnMeasurementHelper = this;
        int i18 = endIndex;
        long jM498constructorimpl = OrientationIndependentConstraints.m498constructorimpl(constraints, rowColumnMeasurementHelper.orientation);
        long jMo279roundToPx0680j_4 = measureScope.mo279roundToPx0680j_4(rowColumnMeasurementHelper.arrangementSpacing);
        int i19 = i18 - startIndex;
        int i20 = startIndex;
        int i21 = 0;
        int i22 = 0;
        float f14 = 0.0f;
        long jMainAxisSize = 0;
        int i23 = 0;
        boolean z = false;
        while (i20 < i18) {
            Measurable measurable = rowColumnMeasurementHelper.measurables.get(i20);
            long j18 = jM498constructorimpl;
            RowColumnParentData rowColumnParentData = rowColumnMeasurementHelper.rowColumnParentData[i20];
            float weight = RowColumnImplKt.getWeight(rowColumnParentData);
            if (weight > 0.0f) {
                i14 = i19;
                i15 = i20;
                i16 = i22 + 1;
                f13 = f14 + weight;
                j17 = j18;
            } else {
                int iM5646getMaxWidthimpl = Constraints.m5646getMaxWidthimpl(j18);
                Placeable placeableMo4631measureBRTryo0 = rowColumnMeasurementHelper.placeables[i20];
                if (placeableMo4631measureBRTryo0 == null) {
                    if (iM5646getMaxWidthimpl == Integer.MAX_VALUE) {
                        i17 = Integer.MAX_VALUE;
                    } else {
                        long j19 = ((long) iM5646getMaxWidthimpl) - jMainAxisSize;
                        if (j19 < 0) {
                            j19 = 0;
                        }
                        i17 = (int) j19;
                    }
                    f13 = f14;
                    int i24 = i17;
                    i15 = i20;
                    i14 = i19;
                    j16 = jMainAxisSize;
                    i16 = i22;
                    j17 = j18;
                    placeableMo4631measureBRTryo0 = measurable.mo4631measureBRTryo0(OrientationIndependentConstraints.m511toBoxConstraintsOenEA2s(OrientationIndependentConstraints.m500copyyUG9Ft0$default(j18, 0, i24, 0, 0, 8, null), rowColumnMeasurementHelper.orientation));
                } else {
                    i14 = i19;
                    i15 = i20;
                    i16 = i22;
                    f13 = f14;
                    j16 = jMainAxisSize;
                    j17 = j18;
                }
                int i25 = (int) jMo279roundToPx0680j_4;
                long j20 = j16;
                long jMainAxisSize2 = (((long) iM5646getMaxWidthimpl) - j20) - ((long) rowColumnMeasurementHelper.mainAxisSize(placeableMo4631measureBRTryo0));
                if (jMainAxisSize2 < 0) {
                    jMainAxisSize2 = 0;
                }
                int iMin = Math.min(i25, (int) jMainAxisSize2);
                jMainAxisSize = j20 + ((long) (rowColumnMeasurementHelper.mainAxisSize(placeableMo4631measureBRTryo0) + iMin));
                int iMax2 = Math.max(i23, rowColumnMeasurementHelper.crossAxisSize(placeableMo4631measureBRTryo0));
                boolean z5 = z || RowColumnImplKt.isRelative(rowColumnParentData);
                rowColumnMeasurementHelper.placeables[i15] = placeableMo4631measureBRTryo0;
                i23 = iMax2;
                z = z5;
                i21 = iMin;
            }
            i20 = i15 + 1;
            jM498constructorimpl = j17;
            f14 = f13;
            i22 = i16;
            i19 = i14;
        }
        int i26 = i19;
        int i27 = i22;
        float f15 = f14;
        long j21 = jM498constructorimpl;
        if (i27 == 0) {
            j13 = jMainAxisSize - ((long) i21);
            j10 = j21;
            iO = 0;
        } else {
            int iM5648getMinWidthimpl = (f15 <= 0.0f || Constraints.m5646getMaxWidthimpl(j21) == Integer.MAX_VALUE) ? Constraints.m5648getMinWidthimpl(j21) : Constraints.m5646getMaxWidthimpl(j21);
            long j22 = ((long) (i27 - 1)) * jMo279roundToPx0680j_4;
            long j23 = (((long) iM5648getMinWidthimpl) - jMainAxisSize) - j22;
            if (j23 < 0) {
                j23 = 0;
            }
            if (f15 > 0.0f) {
                j10 = j21;
                f10 = j23 / f15;
            } else {
                j10 = j21;
                f10 = 0.0f;
            }
            int i28 = startIndex;
            long jM = j23;
            while (true) {
                i10 = i23;
                str = "weightedSize ";
                f11 = f10;
                str2 = "remainingToTarget ";
                str3 = "arrangementSpacingTotal ";
                str4 = "fixedSpace ";
                j11 = j23;
                j12 = j22;
                if (i28 >= i18) {
                    break;
                }
                int i29 = i28;
                float weight2 = RowColumnImplKt.getWeight(rowColumnMeasurementHelper.rowColumnParentData[i29]);
                float f16 = f11 * weight2;
                try {
                    jM -= (long) t7.a.M(f16);
                    i28 = i29 + 1;
                    rowColumnMeasurementHelper = this;
                    i18 = endIndex;
                    i23 = i10;
                    f10 = f11;
                    j23 = j11;
                    j22 = j12;
                } catch (IllegalArgumentException e5) {
                    StringBuilder sb2 = new StringBuilder("This log indicates a hard-to-reproduce Compose issue, modified with additional debugging details. Please help us by adding your experiences to the bug link provided. Thank you for helping us improve Compose. https://issuetracker.google.com/issues/297974033 mainAxisMax ");
                    sb2.append(Constraints.m5646getMaxWidthimpl(j10));
                    sb2.append("mainAxisMin ");
                    sb2.append(Constraints.m5648getMinWidthimpl(j10));
                    sb2.append("targetSpace ");
                    sb2.append(iM5648getMinWidthimpl);
                    androidx.compose.foundation.c.z(sb2, "arrangementSpacingPx ", jMo279roundToPx0680j_4, "weightChildrenCount ");
                    sb2.append(i27);
                    sb2.append("fixedSpace ");
                    sb2.append(jMainAxisSize);
                    androidx.compose.foundation.c.z(sb2, "arrangementSpacingTotal ", j12, "remainingToTarget ");
                    sb2.append(j11);
                    sb2.append("totalWeight ");
                    sb2.append(f15);
                    sb2.append("weightUnitSpace ");
                    sb2.append(f11);
                    sb2.append("itemWeight ");
                    sb2.append(weight2);
                    sb2.append(str);
                    sb2.append(f16);
                    throw new IllegalArgumentException(sb2.toString()).initCause(e5);
                }
            }
            int i30 = startIndex;
            int iMax3 = i10;
            String str10 = "totalWeight ";
            long j24 = jM;
            boolean z10 = z;
            int i31 = 0;
            long j25 = j24;
            long j26 = j12;
            int i32 = endIndex;
            while (i30 < i32) {
                String str11 = str2;
                if (this.placeables[i30] == null) {
                    Measurable measurable2 = this.measurables.get(i30);
                    i11 = i30;
                    RowColumnParentData rowColumnParentData2 = this.rowColumnParentData[i11];
                    float weight3 = RowColumnImplKt.getWeight(rowColumnParentData2);
                    if (weight3 <= 0.0f) {
                        throw new IllegalStateException("All weights <= 0 should have placeables");
                    }
                    int iSignum = Long.signum(j25);
                    long j27 = jMainAxisSize;
                    j25 -= (long) iSignum;
                    float f17 = f11 * weight3;
                    int iMax4 = Math.max(0, t7.a.M(f17) + iSignum);
                    try {
                        f12 = f17;
                        try {
                            str8 = str4;
                            str9 = str3;
                            try {
                                Placeable placeableMo4631measureBRTryo02 = measurable2.mo4631measureBRTryo0(OrientationIndependentConstraints.m511toBoxConstraintsOenEA2s(OrientationIndependentConstraints.m496constructorimpl((!RowColumnImplKt.getFill(rowColumnParentData2) || iMax4 == Integer.MAX_VALUE) ? 0 : iMax4, iMax4, 0, Constraints.m5645getMaxHeightimpl(j10)), this.orientation));
                                int iMainAxisSize = mainAxisSize(placeableMo4631measureBRTryo02) + i31;
                                iMax3 = Math.max(iMax3, crossAxisSize(placeableMo4631measureBRTryo02));
                                boolean z11 = z10 || RowColumnImplKt.isRelative(rowColumnParentData2);
                                this.placeables[i11] = placeableMo4631measureBRTryo02;
                                i31 = iMainAxisSize;
                                z10 = z11;
                                str5 = str10;
                                str6 = str;
                                j14 = j27;
                                str3 = str9;
                                str4 = str8;
                                str7 = str11;
                                j15 = j26;
                            } catch (IllegalArgumentException e6) {
                                e = e6;
                                StringBuilder sb3 = new StringBuilder("This log indicates a hard-to-reproduce Compose issue, modified with additional debugging details. Please help us by adding your experiences to the bug link provided. Thank you for helping us improve Compose. https://issuetracker.google.com/issues/300280216 mainAxisMax ");
                                sb3.append(Constraints.m5646getMaxWidthimpl(j10));
                                sb3.append("mainAxisMin ");
                                sb3.append(Constraints.m5648getMinWidthimpl(j10));
                                sb3.append("targetSpace ");
                                sb3.append(iM5648getMinWidthimpl);
                                androidx.compose.foundation.c.z(sb3, "arrangementSpacingPx ", jMo279roundToPx0680j_4, "weightChildrenCount ");
                                sb3.append(i27);
                                sb3.append(str8);
                                sb3.append(j27);
                                androidx.compose.foundation.c.z(sb3, str9, j26, str11);
                                sb3.append(j11);
                                sb3.append(str10);
                                sb3.append(f15);
                                sb3.append("weightUnitSpace ");
                                sb3.append(f11);
                                sb3.append("weight ");
                                sb3.append(weight3);
                                sb3.append(str);
                                sb3.append(f12);
                                sb3.append("remainderUnit ");
                                sb3.append(iSignum);
                                throw new IllegalArgumentException(androidx.compose.foundation.c.p(iMax4, "childMainAxisSize ", sb3)).initCause(e);
                            }
                        } catch (IllegalArgumentException e10) {
                            e = e10;
                            str8 = str4;
                            str9 = str3;
                            StringBuilder sb32 = new StringBuilder("This log indicates a hard-to-reproduce Compose issue, modified with additional debugging details. Please help us by adding your experiences to the bug link provided. Thank you for helping us improve Compose. https://issuetracker.google.com/issues/300280216 mainAxisMax ");
                            sb32.append(Constraints.m5646getMaxWidthimpl(j10));
                            sb32.append("mainAxisMin ");
                            sb32.append(Constraints.m5648getMinWidthimpl(j10));
                            sb32.append("targetSpace ");
                            sb32.append(iM5648getMinWidthimpl);
                            androidx.compose.foundation.c.z(sb32, "arrangementSpacingPx ", jMo279roundToPx0680j_4, "weightChildrenCount ");
                            sb32.append(i27);
                            sb32.append(str8);
                            sb32.append(j27);
                            androidx.compose.foundation.c.z(sb32, str9, j26, str11);
                            sb32.append(j11);
                            sb32.append(str10);
                            sb32.append(f15);
                            sb32.append("weightUnitSpace ");
                            sb32.append(f11);
                            sb32.append("weight ");
                            sb32.append(weight3);
                            sb32.append(str);
                            sb32.append(f12);
                            sb32.append("remainderUnit ");
                            sb32.append(iSignum);
                            throw new IllegalArgumentException(androidx.compose.foundation.c.p(iMax4, "childMainAxisSize ", sb32)).initCause(e);
                        }
                    } catch (IllegalArgumentException e11) {
                        e = e11;
                        str8 = str4;
                        f12 = f17;
                    }
                } else {
                    i11 = i30;
                    str5 = str10;
                    str6 = str;
                    j14 = jMainAxisSize;
                    j15 = j26;
                    str7 = str11;
                }
                long j28 = j14;
                str = str6;
                str10 = str5;
                str2 = str7;
                i30 = i11 + 1;
                jMainAxisSize = j28;
                i32 = endIndex;
                j26 = j15;
            }
            rowColumnMeasurementHelper = this;
            long j29 = jMainAxisSize;
            iO = (int) qb.d.o(((long) i31) + j26, 0L, ((long) Constraints.m5646getMaxWidthimpl(j10)) - j29);
            i23 = iMax3;
            z = z10;
            j13 = j29;
        }
        if (z) {
            i12 = endIndex;
            int iMax5 = 0;
            iMax = 0;
            for (int i33 = startIndex; i33 < i12; i33++) {
                Placeable placeable = rowColumnMeasurementHelper.placeables[i33];
                CrossAxisAlignment crossAxisAlignment = RowColumnImplKt.getCrossAxisAlignment(rowColumnMeasurementHelper.rowColumnParentData[i33]);
                Integer numCalculateAlignmentLinePosition$foundation_layout_release = crossAxisAlignment != null ? crossAxisAlignment.calculateAlignmentLinePosition$foundation_layout_release(placeable) : null;
                if (numCalculateAlignmentLinePosition$foundation_layout_release != null) {
                    int iIntValue = numCalculateAlignmentLinePosition$foundation_layout_release.intValue();
                    if (iIntValue == Integer.MIN_VALUE) {
                        iIntValue = 0;
                    }
                    iMax5 = Math.max(iMax5, iIntValue);
                    int iCrossAxisSize = rowColumnMeasurementHelper.crossAxisSize(placeable);
                    int iIntValue2 = numCalculateAlignmentLinePosition$foundation_layout_release.intValue();
                    if (iIntValue2 == Integer.MIN_VALUE) {
                        iIntValue2 = rowColumnMeasurementHelper.crossAxisSize(placeable);
                    }
                    iMax = Math.max(iMax, iCrossAxisSize - iIntValue2);
                }
            }
            i13 = iMax5;
        } else {
            i12 = endIndex;
            iMax = 0;
            i13 = 0;
        }
        long j30 = j13 + ((long) iO);
        if (j30 < 0) {
            j30 = 0;
        }
        int iMax6 = Math.max((int) j30, Constraints.m5648getMinWidthimpl(j10));
        int iMax7 = (Constraints.m5645getMaxHeightimpl(j10) == Integer.MAX_VALUE || rowColumnMeasurementHelper.crossAxisSize != SizeMode.Expand) ? Math.max(i23, Math.max(Constraints.m5647getMinHeightimpl(j10), iMax + i13)) : Constraints.m5645getMaxHeightimpl(j10);
        int[] iArr = new int[i26];
        for (int i34 = 0; i34 < i26; i34++) {
            iArr[i34] = 0;
        }
        int[] iArr2 = new int[i26];
        for (int i35 = 0; i35 < i26; i35++) {
            iArr2[i35] = rowColumnMeasurementHelper.mainAxisSize(rowColumnMeasurementHelper.placeables[i35 + startIndex]);
        }
        return new RowColumnMeasureHelperResult(iMax7, iMax6, startIndex, i12, i13, rowColumnMeasurementHelper.mainAxisPositions(iMax6, iArr2, iArr, measureScope));
    }

    public final void placeHelper(Placeable.PlacementScope placeableScope, RowColumnMeasureHelperResult measureResult, int crossAxisOffset, LayoutDirection layoutDirection) {
        int endIndex = measureResult.getEndIndex();
        for (int startIndex = measureResult.getStartIndex(); startIndex < endIndex; startIndex++) {
            Placeable placeable = this.placeables[startIndex];
            int[] mainAxisPositions = measureResult.getMainAxisPositions();
            Object parentData = this.measurables.get(startIndex).getParentData();
            int crossAxisPosition = getCrossAxisPosition(placeable, parentData instanceof RowColumnParentData ? (RowColumnParentData) parentData : null, measureResult.getCrossAxisSize(), layoutDirection, measureResult.getBeforeCrossAxisAlignmentLine()) + crossAxisOffset;
            if (this.orientation == LayoutOrientation.Horizontal) {
                Placeable.PlacementScope.place$default(placeableScope, placeable, mainAxisPositions[startIndex - measureResult.getStartIndex()], crossAxisPosition, 0.0f, 4, null);
            } else {
                Placeable.PlacementScope.place$default(placeableScope, placeable, crossAxisPosition, mainAxisPositions[startIndex - measureResult.getStartIndex()], 0.0f, 4, null);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private RowColumnMeasurementHelper(LayoutOrientation layoutOrientation, Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, float f10, SizeMode sizeMode, CrossAxisAlignment crossAxisAlignment, List<? extends Measurable> list, Placeable[] placeableArr) {
        this.orientation = layoutOrientation;
        this.horizontalArrangement = horizontal;
        this.verticalArrangement = vertical;
        this.arrangementSpacing = f10;
        this.crossAxisSize = sizeMode;
        this.crossAxisAlignment = crossAxisAlignment;
        this.measurables = list;
        this.placeables = placeableArr;
        int size = list.size();
        RowColumnParentData[] rowColumnParentDataArr = new RowColumnParentData[size];
        for (int i10 = 0; i10 < size; i10++) {
            rowColumnParentDataArr[i10] = RowColumnImplKt.getRowColumnParentData(this.measurables.get(i10));
        }
        this.rowColumnParentData = rowColumnParentDataArr;
    }
}
