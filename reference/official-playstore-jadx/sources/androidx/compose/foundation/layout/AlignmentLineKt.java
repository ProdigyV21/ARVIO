package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.HorizontalAlignmentLine;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u001a>\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001a2\u0010\u0012\u001a\u00020\u0013*\u00020\u00132\u0006\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\nH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a2\u0010\u0012\u001a\u00020\u0013*\u00020\u00132\u0006\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00162\b\b\u0002\u0010\u000b\u001a\u00020\u0016H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a*\u0010\u0019\u001a\u00020\u0013*\u00020\u00132\b\b\u0002\u0010\u001a\u001a\u00020\n2\b\b\u0002\u0010\u001b\u001a\u00020\nH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a*\u0010\u0019\u001a\u00020\u0013*\u00020\u00132\b\b\u0002\u0010\u001a\u001a\u00020\u00162\b\b\u0002\u0010\u001b\u001a\u00020\u0016H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001f\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006 "}, d2 = {"horizontal", "", "Landroidx/compose/ui/layout/AlignmentLine;", "getHorizontal", "(Landroidx/compose/ui/layout/AlignmentLine;)Z", "alignmentLineOffsetMeasure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "alignmentLine", TtmlNode.ANNOTATION_POSITION_BEFORE, "Landroidx/compose/ui/unit/Dp;", TtmlNode.ANNOTATION_POSITION_AFTER, "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "alignmentLineOffsetMeasure-tjqqzMA", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/AlignmentLine;FFLandroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "paddingFrom", "Landroidx/compose/ui/Modifier;", "paddingFrom-4j6BHR0", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/layout/AlignmentLine;FF)Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/unit/TextUnit;", "paddingFrom-Y_r0B1c", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/layout/AlignmentLine;JJ)Landroidx/compose/ui/Modifier;", "paddingFromBaseline", "top", "bottom", "paddingFromBaseline-VpY3zN4", "(Landroidx/compose/ui/Modifier;FF)Landroidx/compose/ui/Modifier;", "paddingFromBaseline-wCyjxdI", "(Landroidx/compose/ui/Modifier;JJ)Landroidx/compose/ui/Modifier;", "foundation-layout_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AlignmentLineKt {
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: alignmentLineOffsetMeasure-tjqqzMA, reason: not valid java name */
    public static final MeasureResult m410alignmentLineOffsetMeasuretjqqzMA(MeasureScope measureScope, AlignmentLine alignmentLine, float f10, float f11, Measurable measurable, long j10) {
        Placeable placeableMo4631measureBRTryo0 = measurable.mo4631measureBRTryo0(getHorizontal(alignmentLine) ? Constraints.m5637copyZbe2FdA$default(j10, 0, 0, 0, 0, 11, null) : Constraints.m5637copyZbe2FdA$default(j10, 0, 0, 0, 0, 14, null));
        int i10 = placeableMo4631measureBRTryo0.get(alignmentLine);
        if (i10 == Integer.MIN_VALUE) {
            i10 = 0;
        }
        int height = getHorizontal(alignmentLine) ? placeableMo4631measureBRTryo0.getHeight() : placeableMo4631measureBRTryo0.getWidth();
        int iM5645getMaxHeightimpl = getHorizontal(alignmentLine) ? Constraints.m5645getMaxHeightimpl(j10) : Constraints.m5646getMaxWidthimpl(j10);
        Dp.Companion companion = Dp.INSTANCE;
        int i11 = iM5645getMaxHeightimpl - height;
        int iN = qb.d.n((!Dp.m5683equalsimpl0(f10, companion.m5698getUnspecifiedD9Ej5fM()) ? measureScope.mo279roundToPx0680j_4(f10) : 0) - i10, 0, i11);
        int iN2 = qb.d.n(((!Dp.m5683equalsimpl0(f11, companion.m5698getUnspecifiedD9Ej5fM()) ? measureScope.mo279roundToPx0680j_4(f11) : 0) - height) + i10, 0, i11 - iN);
        int width = getHorizontal(alignmentLine) ? placeableMo4631measureBRTryo0.getWidth() : Math.max(placeableMo4631measureBRTryo0.getWidth() + iN + iN2, Constraints.m5648getMinWidthimpl(j10));
        int iMax = getHorizontal(alignmentLine) ? Math.max(placeableMo4631measureBRTryo0.getHeight() + iN + iN2, Constraints.m5647getMinHeightimpl(j10)) : placeableMo4631measureBRTryo0.getHeight();
        int i12 = width;
        return MeasureScope.CC.q(measureScope, i12, iMax, null, new AlignmentLineKt$alignmentLineOffsetMeasure$1(alignmentLine, f10, iN, i12, iN2, placeableMo4631measureBRTryo0, iMax), 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getHorizontal(AlignmentLine alignmentLine) {
        return alignmentLine instanceof HorizontalAlignmentLine;
    }

    /* JADX INFO: renamed from: paddingFrom-4j6BHR0, reason: not valid java name */
    public static final Modifier m411paddingFrom4j6BHR0(Modifier modifier, AlignmentLine alignmentLine, float f10, float f11) {
        return modifier.then(new AlignmentLineOffsetDpElement(alignmentLine, f10, f11, InspectableValueKt.isDebugInspectorInfoEnabled() ? new AlignmentLineKt$paddingFrom4j6BHR0$$inlined$debugInspectorInfo$1(alignmentLine, f10, f11) : InspectableValueKt.getNoInspectorInfo(), null));
    }

    /* JADX INFO: renamed from: paddingFrom-4j6BHR0$default, reason: not valid java name */
    public static /* synthetic */ Modifier m412paddingFrom4j6BHR0$default(Modifier modifier, AlignmentLine alignmentLine, float f10, float f11, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            f10 = Dp.INSTANCE.m5698getUnspecifiedD9Ej5fM();
        }
        if ((i10 & 4) != 0) {
            f11 = Dp.INSTANCE.m5698getUnspecifiedD9Ej5fM();
        }
        return m411paddingFrom4j6BHR0(modifier, alignmentLine, f10, f11);
    }

    /* JADX INFO: renamed from: paddingFrom-Y_r0B1c, reason: not valid java name */
    public static final Modifier m413paddingFromY_r0B1c(Modifier modifier, AlignmentLine alignmentLine, long j10, long j11) {
        AlignmentLine alignmentLine2;
        long j12;
        long j13;
        l noInspectorInfo;
        if (InspectableValueKt.isDebugInspectorInfoEnabled()) {
            j12 = j10;
            j13 = j11;
            noInspectorInfo = new AlignmentLineKt$paddingFromY_r0B1c$$inlined$debugInspectorInfo$1(alignmentLine, j12, j13);
            alignmentLine2 = alignmentLine;
        } else {
            alignmentLine2 = alignmentLine;
            j12 = j10;
            j13 = j11;
            noInspectorInfo = InspectableValueKt.getNoInspectorInfo();
        }
        return modifier.then(new AlignmentLineOffsetTextUnitElement(alignmentLine2, j12, j13, noInspectorInfo, null));
    }

    /* JADX INFO: renamed from: paddingFrom-Y_r0B1c$default, reason: not valid java name */
    public static /* synthetic */ Modifier m414paddingFromY_r0B1c$default(Modifier modifier, AlignmentLine alignmentLine, long j10, long j11, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            j10 = TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE();
        }
        long j12 = j10;
        if ((i10 & 4) != 0) {
            j11 = TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE();
        }
        return m413paddingFromY_r0B1c(modifier, alignmentLine, j12, j11);
    }

    /* JADX INFO: renamed from: paddingFromBaseline-VpY3zN4, reason: not valid java name */
    public static final Modifier m415paddingFromBaselineVpY3zN4(Modifier modifier, float f10, float f11) {
        Dp.Companion companion = Dp.INSTANCE;
        return modifier.then(!Dp.m5683equalsimpl0(f10, companion.m5698getUnspecifiedD9Ej5fM()) ? m412paddingFrom4j6BHR0$default(Modifier.INSTANCE, androidx.compose.ui.layout.AlignmentLineKt.getFirstBaseline(), f10, 0.0f, 4, null) : Modifier.INSTANCE).then(!Dp.m5683equalsimpl0(f11, companion.m5698getUnspecifiedD9Ej5fM()) ? m412paddingFrom4j6BHR0$default(Modifier.INSTANCE, androidx.compose.ui.layout.AlignmentLineKt.getLastBaseline(), 0.0f, f11, 2, null) : Modifier.INSTANCE);
    }

    /* JADX INFO: renamed from: paddingFromBaseline-VpY3zN4$default, reason: not valid java name */
    public static /* synthetic */ Modifier m416paddingFromBaselineVpY3zN4$default(Modifier modifier, float f10, float f11, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            f10 = Dp.INSTANCE.m5698getUnspecifiedD9Ej5fM();
        }
        if ((i10 & 2) != 0) {
            f11 = Dp.INSTANCE.m5698getUnspecifiedD9Ej5fM();
        }
        return m415paddingFromBaselineVpY3zN4(modifier, f10, f11);
    }

    /* JADX INFO: renamed from: paddingFromBaseline-wCyjxdI, reason: not valid java name */
    public static final Modifier m417paddingFromBaselinewCyjxdI(Modifier modifier, long j10, long j11) {
        return modifier.then(!TextUnitKt.m5883isUnspecifiedR2X_6o(j10) ? m414paddingFromY_r0B1c$default(Modifier.INSTANCE, androidx.compose.ui.layout.AlignmentLineKt.getFirstBaseline(), j10, 0L, 4, null) : Modifier.INSTANCE).then(!TextUnitKt.m5883isUnspecifiedR2X_6o(j11) ? m414paddingFromY_r0B1c$default(Modifier.INSTANCE, androidx.compose.ui.layout.AlignmentLineKt.getLastBaseline(), 0L, j11, 2, null) : Modifier.INSTANCE);
    }

    /* JADX INFO: renamed from: paddingFromBaseline-wCyjxdI$default, reason: not valid java name */
    public static /* synthetic */ Modifier m418paddingFromBaselinewCyjxdI$default(Modifier modifier, long j10, long j11, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            j10 = TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE();
        }
        if ((i10 & 2) != 0) {
            j11 = TextUnit.INSTANCE.m5876getUnspecifiedXSAIIZE();
        }
        return m417paddingFromBaselinewCyjxdI(modifier, j10, j11);
    }
}
