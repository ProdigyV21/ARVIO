package androidx.compose.material3;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/material3/DraggableAnchorsConfig;", "Landroidx/compose/material3/SwipeToDismissBoxValue;", "Lx6/t0;", "invoke", "(Landroidx/compose/material3/DraggableAnchorsConfig;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class SwipeToDismissAnchorsNode$measure$newAnchors$1 extends r implements l<DraggableAnchorsConfig<SwipeToDismissBoxValue>, t0> {
    final /* synthetic */ float $width;
    final /* synthetic */ SwipeToDismissAnchorsNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SwipeToDismissAnchorsNode$measure$newAnchors$1(SwipeToDismissAnchorsNode swipeToDismissAnchorsNode, float f10) {
        super(1);
        this.this$0 = swipeToDismissAnchorsNode;
        this.$width = f10;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DraggableAnchorsConfig<SwipeToDismissBoxValue>) obj);
        return t0.f22605a;
    }

    public final void invoke(DraggableAnchorsConfig<SwipeToDismissBoxValue> draggableAnchorsConfig) {
        draggableAnchorsConfig.at(SwipeToDismissBoxValue.Settled, 0.0f);
        if (this.this$0.getEnableDismissFromStartToEnd()) {
            draggableAnchorsConfig.at(SwipeToDismissBoxValue.StartToEnd, this.$width);
        }
        if (this.this$0.getEnableDismissFromEndToStart()) {
            draggableAnchorsConfig.at(SwipeToDismissBoxValue.EndToStart, -this.$width);
        }
    }
}
