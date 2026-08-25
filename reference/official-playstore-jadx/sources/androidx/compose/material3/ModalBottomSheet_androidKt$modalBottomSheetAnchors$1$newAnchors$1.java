package androidx.compose.material3;

import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/material3/DraggableAnchorsConfig;", "Landroidx/compose/material3/SheetValue;", "Lx6/t0;", "invoke", "(Landroidx/compose/material3/DraggableAnchorsConfig;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ModalBottomSheet_androidKt$modalBottomSheetAnchors$1$newAnchors$1 extends r implements l<DraggableAnchorsConfig<SheetValue>, t0> {
    final /* synthetic */ float $fullHeight;
    final /* synthetic */ long $sheetSize;
    final /* synthetic */ SheetState $sheetState;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModalBottomSheet_androidKt$modalBottomSheetAnchors$1$newAnchors$1(float f10, long j10, SheetState sheetState) {
        super(1);
        this.$fullHeight = f10;
        this.$sheetSize = j10;
        this.$sheetState = sheetState;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DraggableAnchorsConfig<SheetValue>) obj);
        return t0.f22605a;
    }

    public final void invoke(DraggableAnchorsConfig<SheetValue> draggableAnchorsConfig) {
        draggableAnchorsConfig.at(SheetValue.Hidden, this.$fullHeight);
        if (IntSize.m5843getHeightimpl(this.$sheetSize) > this.$fullHeight / 2 && !this.$sheetState.getSkipPartiallyExpanded()) {
            draggableAnchorsConfig.at(SheetValue.PartiallyExpanded, this.$fullHeight / 2.0f);
        }
        if (IntSize.m5843getHeightimpl(this.$sheetSize) != 0) {
            draggableAnchorsConfig.at(SheetValue.Expanded, Math.max(0.0f, this.$fullHeight - IntSize.m5843getHeightimpl(this.$sheetSize)));
        }
    }
}
