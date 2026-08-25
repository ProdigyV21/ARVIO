package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.CrossAxisAlignment;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;
import x7.h;
import x7.i;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aV\u0010\u000e\u001a\u00020\n2\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\u001c\u0010\r\u001a\u0018\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000b¢\u0006\u0002\b\fH\u0087\b¢\u0006\u0004\b\u000e\u0010\u000f\u001aV\u0010\u0012\u001a\u00020\n2\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u00062\u001c\u0010\r\u001a\u0018\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000b¢\u0006\u0002\b\fH\u0087\b¢\u0006\u0004\b\u0012\u0010\u0013\u001a'\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0006H\u0001¢\u0006\u0004\b\u0016\u0010\u0017\u001a'\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0006H\u0001¢\u0006\u0004\b\u0018\u0010\u0019\u001aZ\u0010!\u001a\u00020\u00062\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2#\u0010\u001e\u001a\u001f\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u001d¢\u0006\u0002\b\f2\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0002¢\u0006\u0004\b!\u0010\"\u001a\u0087\u0001\u0010%\u001a\u00020\u00062\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2#\u0010\u001e\u001a\u001f\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u001d¢\u0006\u0002\b\f2#\u0010#\u001a\u001f\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u001d¢\u0006\u0002\b\f2\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0002¢\u0006\u0004\b%\u0010&\u001aM\u0010+\u001a\u00020\u00062\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020'2\u0006\u0010*\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0002¢\u0006\u0004\b+\u0010,\u001a\u0087\u0001\u0010+\u001a\u00020\u00062\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2#\u0010\u001e\u001a\u001f\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u001d¢\u0006\u0002\b\f2#\u0010#\u001a\u001f\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u001d¢\u0006\u0002\b\f2\u0006\u0010*\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0002¢\u0006\u0004\b+\u0010&\u001a6\u00107\u001a\u000204*\u00020-2\u0006\u0010/\u001a\u00020.2\u0006\u00101\u001a\u0002002\u0006\u00103\u001a\u0002022\u0006\u0010\u0014\u001a\u00020\u0006H\u0000ø\u0001\u0000¢\u0006\u0004\b5\u00106\u001a#\u00109\u001a\u00020\u0006*\u0002082\u0006\u00101\u001a\u0002002\u0006\u0010#\u001a\u00020\u0006H\u0000¢\u0006\u0004\b9\u0010:\u001a#\u0010;\u001a\u00020\u0006*\u0002082\u0006\u00101\u001a\u0002002\u0006\u0010\u001e\u001a\u00020\u0006H\u0000¢\u0006\u0004\b;\u0010:\u001a\u001b\u0010\u001e\u001a\u00020\u0006*\u00020<2\u0006\u00101\u001a\u000200H\u0000¢\u0006\u0004\b\u001e\u0010=\u001a\u001b\u0010#\u001a\u00020\u0006*\u00020<2\u0006\u00101\u001a\u000200H\u0000¢\u0006\u0004\b#\u0010=\u001a<\u0010A\u001a\u00020\u0006*\u0002082\u0006\u00103\u001a\u0002022\u0006\u00101\u001a\u0002002\u0014\u0010>\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010<\u0012\u0004\u0012\u00020\n0\bH\u0002ø\u0001\u0000¢\u0006\u0004\b?\u0010@\"\u0014\u0010C\u001a\u00020B8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bC\u0010D\"\u0014\u0010E\u001a\u00020B8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bE\u0010D\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006F"}, d2 = {"Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "verticalArrangement", "", "maxItemsInEachRow", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/FlowRowScope;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "Lx6/n;", "content", "FlowRow", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;ILr7/q;Landroidx/compose/runtime/Composer;II)V", "maxItemsInEachColumn", "Landroidx/compose/foundation/layout/FlowColumnScope;", "FlowColumn", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;ILr7/q;Landroidx/compose/runtime/Composer;II)V", "maxItemsInMainAxis", "Landroidx/compose/ui/layout/MeasurePolicy;", "rowMeasurementHelper", "(Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;ILandroidx/compose/runtime/Composer;I)Landroidx/compose/ui/layout/MeasurePolicy;", "columnMeasurementHelper", "(Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;ILandroidx/compose/runtime/Composer;I)Landroidx/compose/ui/layout/MeasurePolicy;", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "children", "Lkotlin/Function3;", "mainAxisSize", "crossAxisAvailable", "mainAxisSpacing", "maxIntrinsicMainAxisSize", "(Ljava/util/List;Lr7/q;III)I", "crossAxisSize", "crossAxisSpacing", "minIntrinsicMainAxisSize", "(Ljava/util/List;Lr7/q;Lr7/q;IIII)I", "", "mainAxisSizes", "crossAxisSizes", "mainAxisAvailable", "intrinsicCrossAxisSize", "(Ljava/util/List;[I[IIIII)I", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/foundation/layout/RowColumnMeasurementHelper;", "measureHelper", "Landroidx/compose/foundation/layout/LayoutOrientation;", "orientation", "Landroidx/compose/foundation/layout/OrientationIndependentConstraints;", "constraints", "Landroidx/compose/foundation/layout/FlowResult;", "breakDownItems-w1Onq5I", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/foundation/layout/RowColumnMeasurementHelper;Landroidx/compose/foundation/layout/LayoutOrientation;JI)Landroidx/compose/foundation/layout/FlowResult;", "breakDownItems", "Landroidx/compose/ui/layout/Measurable;", "mainAxisMin", "(Landroidx/compose/ui/layout/Measurable;Landroidx/compose/foundation/layout/LayoutOrientation;I)I", "crossAxisMin", "Landroidx/compose/ui/layout/Placeable;", "(Landroidx/compose/ui/layout/Placeable;Landroidx/compose/foundation/layout/LayoutOrientation;)I", "storePlaceable", "measureAndCache-6m2dt9o", "(Landroidx/compose/ui/layout/Measurable;JLandroidx/compose/foundation/layout/LayoutOrientation;Lr7/l;)I", "measureAndCache", "Landroidx/compose/foundation/layout/CrossAxisAlignment;", "CROSS_AXIS_ALIGNMENT_TOP", "Landroidx/compose/foundation/layout/CrossAxisAlignment;", "CROSS_AXIS_ALIGNMENT_START", "foundation-layout_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FlowLayoutKt {
    private static final CrossAxisAlignment CROSS_AXIS_ALIGNMENT_START;
    private static final CrossAxisAlignment CROSS_AXIS_ALIGNMENT_TOP;

    /* JADX INFO: renamed from: androidx.compose.foundation.layout.FlowLayoutKt$intrinsicCrossAxisSize$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "index", "<anonymous parameter 1>", "invoke", "(Landroidx/compose/ui/layout/IntrinsicMeasurable;II)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements q<IntrinsicMeasurable, Integer, Integer, Integer> {
        final /* synthetic */ int[] $mainAxisSizes;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(int[] iArr) {
            super(3);
            this.$mainAxisSizes = iArr;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((IntrinsicMeasurable) obj, ((Number) obj2).intValue(), ((Number) obj3).intValue());
        }

        public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int i10, int i11) {
            return Integer.valueOf(this.$mainAxisSizes[i10]);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.layout.FlowLayoutKt$intrinsicCrossAxisSize$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "index", "<anonymous parameter 1>", "invoke", "(Landroidx/compose/ui/layout/IntrinsicMeasurable;II)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements q<IntrinsicMeasurable, Integer, Integer, Integer> {
        final /* synthetic */ int[] $crossAxisSizes;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(int[] iArr) {
            super(3);
            this.$crossAxisSizes = iArr;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((IntrinsicMeasurable) obj, ((Number) obj2).intValue(), ((Number) obj3).intValue());
        }

        public final Integer invoke(IntrinsicMeasurable intrinsicMeasurable, int i10, int i11) {
            return Integer.valueOf(this.$crossAxisSizes[i10]);
        }
    }

    static {
        CrossAxisAlignment.Companion companion = CrossAxisAlignment.INSTANCE;
        Alignment.Companion companion2 = Alignment.INSTANCE;
        CROSS_AXIS_ALIGNMENT_TOP = companion.vertical$foundation_layout_release(companion2.getTop());
        CROSS_AXIS_ALIGNMENT_START = companion.horizontal$foundation_layout_release(companion2.getStart());
    }

    public static final void FlowColumn(Modifier modifier, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, int i10, q<? super FlowColumnScope, ? super Composer, ? super Integer, t0> qVar, Composer composer, int i11, int i12) {
        composer.startReplaceableGroup(-310290901);
        if ((i12 & 1) != 0) {
            modifier = Modifier.INSTANCE;
        }
        if ((i12 & 2) != 0) {
            vertical = Arrangement.INSTANCE.getTop();
        }
        if ((i12 & 4) != 0) {
            horizontal = Arrangement.INSTANCE.getStart();
        }
        if ((i12 & 8) != 0) {
            i10 = Integer.MAX_VALUE;
        }
        MeasurePolicy measurePolicyColumnMeasurementHelper = columnMeasurementHelper(vertical, horizontal, i10, composer, (i11 >> 3) & AnalyticsListener.EVENT_DRM_SESSION_ACQUIRED);
        composer.startReplaceableGroup(-1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
        r7.a<ComposeUiNode> constructor = companion.getConstructor();
        q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier);
        if (composer.getApplier() == null) {
            ComposablesKt.invalidApplier();
        }
        composer.startReusableNode();
        if (composer.getInserting()) {
            composer.createNode(constructor);
        } else {
            composer.useNode();
        }
        Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composer);
        p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyColumnMeasurementHelper, composerM2991constructorimpl, currentCompositionLocalMap);
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
        }
        a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
        qVar.invoke(FlowColumnScopeInstance.INSTANCE, composer, Integer.valueOf(((i11 >> 9) & 112) | 6));
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
    }

    public static final void FlowRow(Modifier modifier, Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, int i10, q<? super FlowRowScope, ? super Composer, ? super Integer, t0> qVar, Composer composer, int i11, int i12) {
        composer.startReplaceableGroup(1098475987);
        if ((i12 & 1) != 0) {
            modifier = Modifier.INSTANCE;
        }
        if ((i12 & 2) != 0) {
            horizontal = Arrangement.INSTANCE.getStart();
        }
        if ((i12 & 4) != 0) {
            vertical = Arrangement.INSTANCE.getTop();
        }
        if ((i12 & 8) != 0) {
            i10 = Integer.MAX_VALUE;
        }
        MeasurePolicy measurePolicyRowMeasurementHelper = rowMeasurementHelper(horizontal, vertical, i10, composer, (i11 >> 3) & AnalyticsListener.EVENT_DRM_SESSION_ACQUIRED);
        composer.startReplaceableGroup(-1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
        r7.a<ComposeUiNode> constructor = companion.getConstructor();
        q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier);
        if (composer.getApplier() == null) {
            ComposablesKt.invalidApplier();
        }
        composer.startReusableNode();
        if (composer.getInserting()) {
            composer.createNode(constructor);
        } else {
            composer.useNode();
        }
        Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composer);
        p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyRowMeasurementHelper, composerM2991constructorimpl, currentCompositionLocalMap);
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
        }
        a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
        qVar.invoke(FlowRowScopeInstance.INSTANCE, composer, Integer.valueOf(((i11 >> 9) & 112) | 6));
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00a2  */
    /* JADX INFO: renamed from: breakDownItems-w1Onq5I, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.foundation.layout.FlowResult m473breakDownItemsw1Onq5I(androidx.compose.ui.layout.MeasureScope r20, androidx.compose.foundation.layout.RowColumnMeasurementHelper r21, androidx.compose.foundation.layout.LayoutOrientation r22, long r23, int r25) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 297
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.FlowLayoutKt.m473breakDownItemsw1Onq5I(androidx.compose.ui.layout.MeasureScope, androidx.compose.foundation.layout.RowColumnMeasurementHelper, androidx.compose.foundation.layout.LayoutOrientation, long, int):androidx.compose.foundation.layout.FlowResult");
    }

    public static final MeasurePolicy columnMeasurementHelper(Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, int i10, Composer composer, int i11) {
        composer.startReplaceableGroup(-2013098357);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2013098357, i11, -1, "androidx.compose.foundation.layout.columnMeasurementHelper (FlowLayout.kt:177)");
        }
        Integer numValueOf = Integer.valueOf(i10);
        composer.startReplaceableGroup(1618982084);
        boolean zChanged = composer.changed(numValueOf) | composer.changed(vertical) | composer.changed(horizontal);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            FlowMeasurePolicy flowMeasurePolicy = new FlowMeasurePolicy(LayoutOrientation.Vertical, horizontal, vertical, vertical.getSpacing(), SizeMode.Wrap, CROSS_AXIS_ALIGNMENT_START, horizontal.getSpacing(), i10, null);
            composer.updateRememberedValue(flowMeasurePolicy);
            objRememberedValue = flowMeasurePolicy;
        }
        composer.endReplaceableGroup();
        FlowMeasurePolicy flowMeasurePolicy2 = (FlowMeasurePolicy) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return flowMeasurePolicy2;
    }

    public static final int crossAxisMin(Measurable measurable, LayoutOrientation layoutOrientation, int i10) {
        return layoutOrientation == LayoutOrientation.Horizontal ? measurable.minIntrinsicHeight(i10) : measurable.minIntrinsicWidth(i10);
    }

    public static final int crossAxisSize(Placeable placeable, LayoutOrientation layoutOrientation) {
        return layoutOrientation == LayoutOrientation.Horizontal ? placeable.getHeight() : placeable.getWidth();
    }

    private static final int intrinsicCrossAxisSize(List<? extends IntrinsicMeasurable> list, int[] iArr, int[] iArr2, int i10, int i11, int i12, int i13) {
        return intrinsicCrossAxisSize(list, new AnonymousClass1(iArr), new AnonymousClass2(iArr2), i10, i11, i12, i13);
    }

    public static final int mainAxisMin(Measurable measurable, LayoutOrientation layoutOrientation, int i10) {
        return layoutOrientation == LayoutOrientation.Horizontal ? measurable.minIntrinsicWidth(i10) : measurable.minIntrinsicHeight(i10);
    }

    public static final int mainAxisSize(Placeable placeable, LayoutOrientation layoutOrientation) {
        return layoutOrientation == LayoutOrientation.Horizontal ? placeable.getWidth() : placeable.getHeight();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int maxIntrinsicMainAxisSize(List<? extends IntrinsicMeasurable> list, q<? super IntrinsicMeasurable, ? super Integer, ? super Integer, Integer> qVar, int i10, int i11, int i12) {
        int size = list.size();
        int i13 = 0;
        int iMax = 0;
        int i14 = 0;
        int i15 = 0;
        while (i13 < size) {
            int iIntValue = ((Number) qVar.invoke(list.get(i13), Integer.valueOf(i13), Integer.valueOf(i10))).intValue() + i11;
            int i16 = i13 + 1;
            if (i16 - i14 == i12 || i16 == list.size()) {
                iMax = Math.max(iMax, (i15 + iIntValue) - i11);
                i15 = 0;
                i14 = i13;
            } else {
                i15 += iIntValue;
            }
            i13 = i16;
        }
        return iMax;
    }

    /* JADX INFO: renamed from: measureAndCache-6m2dt9o, reason: not valid java name */
    private static final int m474measureAndCache6m2dt9o(Measurable measurable, long j10, LayoutOrientation layoutOrientation, l<? super Placeable, t0> lVar) {
        if (RowColumnImplKt.getWeight(RowColumnImplKt.getRowColumnParentData(measurable)) != 0.0f) {
            return mainAxisMin(measurable, layoutOrientation, Integer.MAX_VALUE);
        }
        Placeable placeableMo4631measureBRTryo0 = measurable.mo4631measureBRTryo0(OrientationIndependentConstraints.m511toBoxConstraintsOenEA2s(OrientationIndependentConstraints.m500copyyUG9Ft0$default(j10, 0, 0, 0, 0, 14, null), layoutOrientation));
        lVar.invoke(placeableMo4631measureBRTryo0);
        return mainAxisSize(placeableMo4631measureBRTryo0, layoutOrientation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int minIntrinsicMainAxisSize(List<? extends IntrinsicMeasurable> list, q<? super IntrinsicMeasurable, ? super Integer, ? super Integer, Integer> qVar, q<? super IntrinsicMeasurable, ? super Integer, ? super Integer, Integer> qVar2, int i10, int i11, int i12, int i13) {
        int size = list.size();
        int[] iArr = new int[size];
        for (int i14 = 0; i14 < size; i14++) {
            iArr[i14] = 0;
        }
        int size2 = list.size();
        int[] iArr2 = new int[size2];
        for (int i15 = 0; i15 < size2; i15++) {
            iArr2[i15] = 0;
        }
        int size3 = list.size();
        for (int i16 = 0; i16 < size3; i16++) {
            IntrinsicMeasurable intrinsicMeasurable = list.get(i16);
            int iIntValue = ((Number) qVar.invoke(intrinsicMeasurable, Integer.valueOf(i16), Integer.valueOf(i10))).intValue();
            iArr[i16] = iIntValue;
            iArr2[i16] = ((Number) qVar2.invoke(intrinsicMeasurable, Integer.valueOf(i16), Integer.valueOf(iIntValue))).intValue();
        }
        int i17 = 0;
        for (int i18 = 0; i18 < size; i18++) {
            i17 += iArr[i18];
        }
        if (size2 == 0) {
            throw new NoSuchElementException();
        }
        int iIntrinsicCrossAxisSize = iArr2[0];
        h it = new i(1, size2 - 1, 1).iterator();
        while (it.f22624m) {
            int i19 = iArr2[it.nextInt()];
            if (iIntrinsicCrossAxisSize < i19) {
                iIntrinsicCrossAxisSize = i19;
            }
        }
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int i20 = iArr[0];
        h it2 = new i(1, size - 1, 1).iterator();
        while (it2.f22624m) {
            int i21 = iArr[it2.nextInt()];
            if (i20 < i21) {
                i20 = i21;
            }
        }
        int i22 = i17;
        int i23 = i20;
        while (i23 < i17 && iIntrinsicCrossAxisSize != i10) {
            int i24 = (i23 + i17) / 2;
            int[] iArr3 = iArr2;
            iIntrinsicCrossAxisSize = intrinsicCrossAxisSize(list, iArr, iArr3, i24, i11, i12, i13);
            if (iIntrinsicCrossAxisSize == i10) {
                return i24;
            }
            if (iIntrinsicCrossAxisSize > i10) {
                i23 = i24 + 1;
            } else {
                i17 = i24 - 1;
            }
            i22 = i24;
            iArr2 = iArr3;
        }
        return i22;
    }

    public static final MeasurePolicy rowMeasurementHelper(Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, int i10, Composer composer, int i11) {
        composer.startReplaceableGroup(1479255111);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1479255111, i11, -1, "androidx.compose.foundation.layout.rowMeasurementHelper (FlowLayout.kt:156)");
        }
        Integer numValueOf = Integer.valueOf(i10);
        composer.startReplaceableGroup(1618982084);
        boolean zChanged = composer.changed(numValueOf) | composer.changed(horizontal) | composer.changed(vertical);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            FlowMeasurePolicy flowMeasurePolicy = new FlowMeasurePolicy(LayoutOrientation.Horizontal, horizontal, vertical, horizontal.getSpacing(), SizeMode.Wrap, CROSS_AXIS_ALIGNMENT_TOP, vertical.getSpacing(), i10, null);
            composer.updateRememberedValue(flowMeasurePolicy);
            objRememberedValue = flowMeasurePolicy;
        }
        composer.endReplaceableGroup();
        FlowMeasurePolicy flowMeasurePolicy2 = (FlowMeasurePolicy) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return flowMeasurePolicy2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int intrinsicCrossAxisSize(List<? extends IntrinsicMeasurable> list, q<? super IntrinsicMeasurable, ? super Integer, ? super Integer, Integer> qVar, q<? super IntrinsicMeasurable, ? super Integer, ? super Integer, Integer> qVar2, int i10, int i11, int i12, int i13) {
        int i14;
        if (list.isEmpty()) {
            return 0;
        }
        IntrinsicMeasurable intrinsicMeasurable = (IntrinsicMeasurable) x.p0(0, list);
        int iIntValue = intrinsicMeasurable != null ? ((Number) qVar2.invoke(intrinsicMeasurable, 0, Integer.valueOf(i10))).intValue() : 0;
        int iIntValue2 = intrinsicMeasurable != null ? ((Number) qVar.invoke(intrinsicMeasurable, 0, Integer.valueOf(iIntValue))).intValue() : 0;
        int size = list.size();
        int i15 = i10;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        while (i16 < size) {
            list.get(i16);
            i15 -= iIntValue2;
            int iMax = Math.max(i18, iIntValue);
            i16++;
            IntrinsicMeasurable intrinsicMeasurable2 = (IntrinsicMeasurable) x.p0(i16, list);
            int iIntValue3 = intrinsicMeasurable2 != null ? ((Number) qVar2.invoke(intrinsicMeasurable2, Integer.valueOf(i16), Integer.valueOf(i10))).intValue() : 0;
            int iIntValue4 = intrinsicMeasurable2 != null ? ((Number) qVar.invoke(intrinsicMeasurable2, Integer.valueOf(i16), Integer.valueOf(iIntValue3))).intValue() + i11 : 0;
            if (i15 >= 0 && i16 != list.size()) {
                if (i16 - i19 != i13 && i15 - iIntValue4 >= 0) {
                    int i20 = iIntValue4;
                    i14 = iMax;
                    iIntValue2 = i20;
                }
                int i21 = iIntValue3;
                i18 = i14;
                iIntValue = i21;
            }
            i17 += iMax + i12;
            i15 = i10;
            iIntValue2 = iIntValue4 - i11;
            i19 = i16;
            i14 = 0;
            int i212 = iIntValue3;
            i18 = i14;
            iIntValue = i212;
        }
        return i17 - i12;
    }
}
