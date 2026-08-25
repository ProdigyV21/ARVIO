package androidx.compose.material3;

import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0004\n\u0002\b\u0004\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "T", "invoke", "()Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class AnchoredDraggableState$targetValue$2<T> extends r implements r7.a<T> {
    final /* synthetic */ AnchoredDraggableState<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AnchoredDraggableState$targetValue$2(AnchoredDraggableState<T> anchoredDraggableState) {
        super(0);
        this.this$0 = anchoredDraggableState;
    }

    @Override // r7.a
    public final T invoke() {
        T t2 = (T) this.this$0.getDragTarget();
        if (t2 != null) {
            return t2;
        }
        AnchoredDraggableState<T> anchoredDraggableState = this.this$0;
        float offset = anchoredDraggableState.getOffset();
        return !Float.isNaN(offset) ? (T) anchoredDraggableState.computeTarget(offset, anchoredDraggableState.getCurrentValue(), 0.0f) : anchoredDraggableState.getCurrentValue();
    }
}
