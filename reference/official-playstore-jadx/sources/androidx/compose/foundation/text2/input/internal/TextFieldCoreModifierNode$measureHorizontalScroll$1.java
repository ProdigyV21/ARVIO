package androidx.compose.foundation.text2.input.internal;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class TextFieldCoreModifierNode$measureHorizontalScroll$1 extends r implements l<Placeable.PlacementScope, t0> {
    final /* synthetic */ Placeable $placeable;
    final /* synthetic */ MeasureScope $this_measureHorizontalScroll;
    final /* synthetic */ int $width;
    final /* synthetic */ TextFieldCoreModifierNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextFieldCoreModifierNode$measureHorizontalScroll$1(TextFieldCoreModifierNode textFieldCoreModifierNode, MeasureScope measureScope, Placeable placeable, int i10) {
        super(1);
        this.this$0 = textFieldCoreModifierNode;
        this.$this_measureHorizontalScroll = measureScope;
        this.$placeable = placeable;
        this.$width = i10;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Placeable.PlacementScope) obj);
        return t0.f22605a;
    }

    public final void invoke(Placeable.PlacementScope placementScope) {
        Rect cursorRectInScroller;
        long selectionInChars = this.this$0.textFieldState.getText().getSelectionInChars();
        int iM1105calculateOffsetToFollow5zctL8 = this.this$0.m1105calculateOffsetToFollow5zctL8(selectionInChars);
        if (iM1105calculateOffsetToFollow5zctL8 >= 0) {
            cursorRectInScroller = TextFieldCoreModifierKt.getCursorRectInScroller(this.$this_measureHorizontalScroll, iM1105calculateOffsetToFollow5zctL8, this.this$0.textLayoutState.getLayoutResult(), this.$this_measureHorizontalScroll.getLayoutDirection() == LayoutDirection.Rtl, this.$placeable.getWidth());
        } else {
            cursorRectInScroller = null;
        }
        this.this$0.updateScrollState(cursorRectInScroller, this.$width, this.$placeable.getWidth());
        if (this.this$0.isFocused) {
            this.this$0.previousSelection = TextRange.m5180boximpl(selectionInChars);
        }
        Placeable.PlacementScope.placeRelative$default(placementScope, this.$placeable, -this.this$0.scrollState.getValue(), 0, 0.0f, 4, null);
    }
}
