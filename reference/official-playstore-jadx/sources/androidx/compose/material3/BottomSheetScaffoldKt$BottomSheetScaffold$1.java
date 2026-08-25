package androidx.compose.material3;

import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Shape;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "layoutHeight", "Lx6/t0;", "invoke", "(ILandroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class BottomSheetScaffoldKt$BottomSheetScaffold$1 extends r implements q<Integer, Composer, Integer, t0> {
    final /* synthetic */ int $peekHeightPx;
    final /* synthetic */ BottomSheetScaffoldState $scaffoldState;
    final /* synthetic */ long $sheetContainerColor;
    final /* synthetic */ q<ColumnScope, Composer, Integer, t0> $sheetContent;
    final /* synthetic */ long $sheetContentColor;
    final /* synthetic */ p<Composer, Integer, t0> $sheetDragHandle;
    final /* synthetic */ float $sheetMaxWidth;
    final /* synthetic */ float $sheetPeekHeight;
    final /* synthetic */ float $sheetShadowElevation;
    final /* synthetic */ Shape $sheetShape;
    final /* synthetic */ boolean $sheetSwipeEnabled;
    final /* synthetic */ float $sheetTonalElevation;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public BottomSheetScaffoldKt$BottomSheetScaffold$1(BottomSheetScaffoldState bottomSheetScaffoldState, int i10, float f10, float f11, boolean z, Shape shape, long j10, long j11, float f12, float f13, p<? super Composer, ? super Integer, t0> pVar, q<? super ColumnScope, ? super Composer, ? super Integer, t0> qVar) {
        super(3);
        this.$scaffoldState = bottomSheetScaffoldState;
        this.$peekHeightPx = i10;
        this.$sheetPeekHeight = f10;
        this.$sheetMaxWidth = f11;
        this.$sheetSwipeEnabled = z;
        this.$sheetShape = shape;
        this.$sheetContainerColor = j10;
        this.$sheetContentColor = j11;
        this.$sheetTonalElevation = f12;
        this.$sheetShadowElevation = f13;
        this.$sheetDragHandle = pVar;
        this.$sheetContent = qVar;
    }

    @Override // r7.q
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        invoke(((Number) obj).intValue(), (Composer) obj2, ((Number) obj3).intValue());
        return t0.f22605a;
    }

    public final void invoke(int i10, Composer composer, int i11) {
        int i12;
        if ((i11 & 6) == 0) {
            i12 = i11 | (composer.changed(i10) ? 4 : 2);
        } else {
            i12 = i11;
        }
        if ((i12 & 19) == 18 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-680109608, i12, -1, "androidx.compose.material3.BottomSheetScaffold.<anonymous> (BottomSheetScaffold.kt:135)");
        }
        SheetState bottomSheetState = this.$scaffoldState.getBottomSheetState();
        composer.startReplaceableGroup(1237684821);
        boolean zChanged = ((i12 & 14) == 4) | composer.changed(this.$scaffoldState) | composer.changed(this.$peekHeightPx);
        BottomSheetScaffoldState bottomSheetScaffoldState = this.$scaffoldState;
        int i13 = this.$peekHeightPx;
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new BottomSheetScaffoldKt$BottomSheetScaffold$1$1$1(bottomSheetScaffoldState, i10, i13);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        BottomSheetScaffoldKt.m1251StandardBottomSheetXcniZvE(bottomSheetState, (l) objRememberedValue, this.$sheetPeekHeight, this.$sheetMaxWidth, this.$sheetSwipeEnabled, this.$sheetShape, this.$sheetContainerColor, this.$sheetContentColor, this.$sheetTonalElevation, this.$sheetShadowElevation, this.$sheetDragHandle, this.$sheetContent, composer, 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
