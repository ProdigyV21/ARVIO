package androidx.compose.foundation.gestures;

import androidx.compose.runtime.saveable.SaverScope;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002\"\u0004\b\u0001\u0010\u0001*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "T", "", "Landroidx/compose/runtime/saveable/SaverScope;", "it", "Landroidx/compose/foundation/gestures/AnchoredDraggableState;", "invoke", "(Landroidx/compose/runtime/saveable/SaverScope;Landroidx/compose/foundation/gestures/AnchoredDraggableState;)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class AnchoredDraggableState$Companion$Saver$2<T> extends r implements p<SaverScope, AnchoredDraggableState<T>, T> {
    public static final AnchoredDraggableState$Companion$Saver$2 INSTANCE = new AnchoredDraggableState$Companion$Saver$2();

    public AnchoredDraggableState$Companion$Saver$2() {
        super(2);
    }

    @Override // r7.p
    public final T invoke(SaverScope saverScope, AnchoredDraggableState<T> anchoredDraggableState) {
        return anchoredDraggableState.getCurrentValue();
    }
}
