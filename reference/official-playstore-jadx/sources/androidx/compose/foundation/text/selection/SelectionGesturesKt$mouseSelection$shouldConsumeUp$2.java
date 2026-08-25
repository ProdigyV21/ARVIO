package androidx.compose.foundation.text.selection;

import androidx.compose.ui.input.pointer.PointerInputChange;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputChange;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/input/pointer/PointerInputChange;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class SelectionGesturesKt$mouseSelection$shouldConsumeUp$2 extends r implements l<PointerInputChange, t0> {
    final /* synthetic */ MouseSelectionObserver $observer;
    final /* synthetic */ SelectionAdjustment $selectionAdjustment;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SelectionGesturesKt$mouseSelection$shouldConsumeUp$2(MouseSelectionObserver mouseSelectionObserver, SelectionAdjustment selectionAdjustment) {
        super(1);
        this.$observer = mouseSelectionObserver;
        this.$selectionAdjustment = selectionAdjustment;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((PointerInputChange) obj);
        return t0.f22605a;
    }

    public final void invoke(PointerInputChange pointerInputChange) {
        if (this.$observer.mo948onDrag3MmeM6k(pointerInputChange.getPosition(), this.$selectionAdjustment)) {
            pointerInputChange.consume();
        }
    }
}
