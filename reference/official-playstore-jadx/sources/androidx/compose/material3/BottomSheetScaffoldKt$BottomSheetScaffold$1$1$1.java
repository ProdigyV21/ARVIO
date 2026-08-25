package androidx.compose.material3;

import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "Landroidx/compose/material3/DraggableAnchors;", "Landroidx/compose/material3/SheetValue;", "sheetSize", "Landroidx/compose/ui/unit/IntSize;", "invoke-ozmzZPI", "(J)Landroidx/compose/material3/DraggableAnchors;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class BottomSheetScaffoldKt$BottomSheetScaffold$1$1$1 extends r implements l<IntSize, DraggableAnchors<SheetValue>> {
    final /* synthetic */ int $layoutHeight;
    final /* synthetic */ int $peekHeightPx;
    final /* synthetic */ BottomSheetScaffoldState $scaffoldState;

    /* JADX INFO: renamed from: androidx.compose.material3.BottomSheetScaffoldKt$BottomSheetScaffold$1$1$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/material3/DraggableAnchorsConfig;", "Landroidx/compose/material3/SheetValue;", "Lx6/t0;", "invoke", "(Landroidx/compose/material3/DraggableAnchorsConfig;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<DraggableAnchorsConfig<SheetValue>, t0> {
        final /* synthetic */ int $layoutHeight;
        final /* synthetic */ int $peekHeightPx;
        final /* synthetic */ BottomSheetScaffoldState $scaffoldState;
        final /* synthetic */ int $sheetHeight;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(BottomSheetScaffoldState bottomSheetScaffoldState, int i10, int i11, int i12) {
            super(1);
            this.$scaffoldState = bottomSheetScaffoldState;
            this.$layoutHeight = i10;
            this.$peekHeightPx = i11;
            this.$sheetHeight = i12;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((DraggableAnchorsConfig<SheetValue>) obj);
            return t0.f22605a;
        }

        public final void invoke(DraggableAnchorsConfig<SheetValue> draggableAnchorsConfig) {
            if (!this.$scaffoldState.getBottomSheetState().getSkipPartiallyExpanded()) {
                draggableAnchorsConfig.at(SheetValue.PartiallyExpanded, this.$layoutHeight - this.$peekHeightPx);
            }
            if (this.$sheetHeight != this.$peekHeightPx) {
                draggableAnchorsConfig.at(SheetValue.Expanded, Math.max(this.$layoutHeight - r0, 0));
            }
            if (this.$scaffoldState.getBottomSheetState().getSkipHiddenState()) {
                return;
            }
            draggableAnchorsConfig.at(SheetValue.Hidden, this.$layoutHeight);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BottomSheetScaffoldKt$BottomSheetScaffold$1$1$1(BottomSheetScaffoldState bottomSheetScaffoldState, int i10, int i11) {
        super(1);
        this.$scaffoldState = bottomSheetScaffoldState;
        this.$layoutHeight = i10;
        this.$peekHeightPx = i11;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return m1254invokeozmzZPI(((IntSize) obj).getPackedValue());
    }

    /* JADX INFO: renamed from: invoke-ozmzZPI, reason: not valid java name */
    public final DraggableAnchors<SheetValue> m1254invokeozmzZPI(long j10) {
        return AnchoredDraggableKt.DraggableAnchors(new AnonymousClass1(this.$scaffoldState, this.$layoutHeight, this.$peekHeightPx, IntSize.m5843getHeightimpl(j10)));
    }
}
