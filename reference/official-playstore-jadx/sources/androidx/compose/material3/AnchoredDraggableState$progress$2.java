package androidx.compose.material3;

import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "T", "invoke", "()Ljava/lang/Float;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class AnchoredDraggableState$progress$2 extends r implements r7.a<Float> {
    final /* synthetic */ AnchoredDraggableState<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AnchoredDraggableState$progress$2(AnchoredDraggableState<T> anchoredDraggableState) {
        super(0);
        this.this$0 = anchoredDraggableState;
    }

    @Override // r7.a
    public final Float invoke() {
        float fPositionOf = this.this$0.getAnchors().positionOf(this.this$0.getCurrentValue());
        float fPositionOf2 = this.this$0.getAnchors().positionOf(this.this$0.getClosestValue$material3_release()) - fPositionOf;
        float fAbs = Math.abs(fPositionOf2);
        float f10 = 1.0f;
        if (!Float.isNaN(fAbs) && fAbs > 1.0E-6f) {
            float fRequireOffset = (this.this$0.requireOffset() - fPositionOf) / fPositionOf2;
            if (fRequireOffset < 1.0E-6f) {
                f10 = 0.0f;
            } else if (fRequireOffset <= 0.999999f) {
                f10 = fRequireOffset;
            }
        }
        return Float.valueOf(f10);
    }
}
