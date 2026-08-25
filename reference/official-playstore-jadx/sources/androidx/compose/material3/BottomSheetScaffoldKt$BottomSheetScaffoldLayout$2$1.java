package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.unit.Constraints;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/SubcomposeMeasureScope;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "invoke-0kLqBqw", "(Landroidx/compose/ui/layout/SubcomposeMeasureScope;J)Landroidx/compose/ui/layout/MeasureResult;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class BottomSheetScaffoldKt$BottomSheetScaffoldLayout$2$1 extends r implements p<SubcomposeMeasureScope, Constraints, MeasureResult> {
    final /* synthetic */ q<PaddingValues, Composer, Integer, t0> $body;
    final /* synthetic */ q<Integer, Composer, Integer, t0> $bottomSheet;
    final /* synthetic */ long $containerColor;
    final /* synthetic */ long $contentColor;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ r7.a<Float> $sheetOffset;
    final /* synthetic */ float $sheetPeekHeight;
    final /* synthetic */ SheetState $sheetState;
    final /* synthetic */ p<Composer, Integer, t0> $snackbarHost;
    final /* synthetic */ p<Composer, Integer, t0> $topBar;

    /* JADX INFO: renamed from: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffoldLayout$2$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<Placeable.PlacementScope, t0> {
        final /* synthetic */ Placeable $bodyPlaceable;
        final /* synthetic */ int $layoutHeight;
        final /* synthetic */ int $layoutWidth;
        final /* synthetic */ r7.a<Float> $sheetOffset;
        final /* synthetic */ Placeable $sheetPlaceable;
        final /* synthetic */ SheetState $sheetState;
        final /* synthetic */ Placeable $snackbarPlaceable;
        final /* synthetic */ int $topBarHeight;
        final /* synthetic */ Placeable $topBarPlaceable;

        /* JADX INFO: renamed from: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffoldLayout$2$1$1$WhenMappings */
        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[SheetValue.values().length];
                try {
                    iArr[SheetValue.PartiallyExpanded.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[SheetValue.Expanded.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[SheetValue.Hidden.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(r7.a<Float> aVar, int i10, Placeable placeable, Placeable placeable2, SheetState sheetState, int i11, Placeable placeable3, int i12, Placeable placeable4) {
            super(1);
            this.$sheetOffset = aVar;
            this.$layoutWidth = i10;
            this.$sheetPlaceable = placeable;
            this.$snackbarPlaceable = placeable2;
            this.$sheetState = sheetState;
            this.$layoutHeight = i11;
            this.$bodyPlaceable = placeable3;
            this.$topBarHeight = i12;
            this.$topBarPlaceable = placeable4;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Placeable.PlacementScope) obj);
            return t0.f22605a;
        }

        public final void invoke(Placeable.PlacementScope placementScope) {
            int height;
            int iM = t7.a.M(((Number) this.$sheetOffset.invoke()).floatValue());
            int iMax = Math.max(0, (this.$layoutWidth - this.$sheetPlaceable.getWidth()) / 2);
            int iB = androidx.compose.foundation.c.B(this.$snackbarPlaceable, this.$layoutWidth, 2);
            int i10 = WhenMappings.$EnumSwitchMapping$0[this.$sheetState.getCurrentValue().ordinal()];
            if (i10 == 1) {
                height = iM - this.$snackbarPlaceable.getHeight();
            } else {
                if (i10 != 2 && i10 != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                height = this.$layoutHeight - this.$snackbarPlaceable.getHeight();
            }
            int i11 = height;
            Placeable.PlacementScope.placeRelative$default(placementScope, this.$bodyPlaceable, 0, this.$topBarHeight, 0.0f, 4, null);
            Placeable placeable = this.$topBarPlaceable;
            if (placeable != null) {
                Placeable.PlacementScope.placeRelative$default(placementScope, placeable, 0, 0, 0.0f, 4, null);
            }
            Placeable.PlacementScope.placeRelative$default(placementScope, this.$sheetPlaceable, iMax, iM, 0.0f, 4, null);
            Placeable.PlacementScope.placeRelative$default(placementScope, this.$snackbarPlaceable, iB, i11, 0.0f, 4, null);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public BottomSheetScaffoldKt$BottomSheetScaffoldLayout$2$1(p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, q<? super Integer, ? super Composer, ? super Integer, t0> qVar, Modifier modifier, long j10, long j11, q<? super PaddingValues, ? super Composer, ? super Integer, t0> qVar2, float f10, r7.a<Float> aVar, SheetState sheetState) {
        super(2);
        this.$topBar = pVar;
        this.$snackbarHost = pVar2;
        this.$bottomSheet = qVar;
        this.$modifier = modifier;
        this.$containerColor = j10;
        this.$contentColor = j11;
        this.$body = qVar2;
        this.$sheetPeekHeight = f10;
        this.$sheetOffset = aVar;
        this.$sheetState = sheetState;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return m1256invoke0kLqBqw((SubcomposeMeasureScope) obj, ((Constraints) obj2).getValue());
    }

    /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
    public final MeasureResult m1256invoke0kLqBqw(SubcomposeMeasureScope subcomposeMeasureScope, long j10) {
        int iM5646getMaxWidthimpl = Constraints.m5646getMaxWidthimpl(j10);
        int iM5645getMaxHeightimpl = Constraints.m5645getMaxHeightimpl(j10);
        long jM5637copyZbe2FdA$default = Constraints.m5637copyZbe2FdA$default(j10, 0, 0, 0, 0, 10, null);
        Placeable placeableMo4631measureBRTryo0 = subcomposeMeasureScope.subcompose(BottomSheetScaffoldLayoutSlot.Sheet, ComposableLambdaKt.composableLambdaInstance(-1192048628, true, new BottomSheetScaffoldKt$BottomSheetScaffoldLayout$2$1$sheetPlaceable$1(this.$bottomSheet, iM5645getMaxHeightimpl))).get(0).mo4631measureBRTryo0(jM5637copyZbe2FdA$default);
        p<Composer, Integer, t0> pVar = this.$topBar;
        Placeable placeableMo4631measureBRTryo02 = pVar != null ? subcomposeMeasureScope.subcompose(BottomSheetScaffoldLayoutSlot.TopBar, ComposableLambdaKt.composableLambdaInstance(-873203005, true, new BottomSheetScaffoldKt$BottomSheetScaffoldLayout$2$1$topBarPlaceable$1$1(pVar))).get(0).mo4631measureBRTryo0(jM5637copyZbe2FdA$default) : null;
        int height = placeableMo4631measureBRTryo02 != null ? placeableMo4631measureBRTryo02.getHeight() : 0;
        return MeasureScope.CC.q(subcomposeMeasureScope, iM5646getMaxWidthimpl, iM5645getMaxHeightimpl, null, new AnonymousClass1(this.$sheetOffset, iM5646getMaxWidthimpl, placeableMo4631measureBRTryo0, subcomposeMeasureScope.subcompose(BottomSheetScaffoldLayoutSlot.Snackbar, this.$snackbarHost).get(0).mo4631measureBRTryo0(jM5637copyZbe2FdA$default), this.$sheetState, iM5645getMaxHeightimpl, subcomposeMeasureScope.subcompose(BottomSheetScaffoldLayoutSlot.Body, ComposableLambdaKt.composableLambdaInstance(-1459220575, true, new BottomSheetScaffoldKt$BottomSheetScaffoldLayout$2$1$bodyPlaceable$1(this.$modifier, this.$containerColor, this.$contentColor, this.$body, this.$sheetPeekHeight))).get(0).mo4631measureBRTryo0(Constraints.m5637copyZbe2FdA$default(jM5637copyZbe2FdA$default, 0, 0, 0, iM5645getMaxHeightimpl - height, 7, null)), height, placeableMo4631measureBRTryo02), 4, null);
    }
}
