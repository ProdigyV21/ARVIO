package androidx.compose.foundation.draganddrop;

import androidx.compose.ui.draganddrop.DragAndDropTransferData;
import androidx.compose.ui.input.pointer.PointerInputScope;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/draganddrop/DragAndDropSourceScope;", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "Landroidx/compose/ui/draganddrop/DragAndDropTransferData;", "transferData", "Lx6/t0;", "startTransfer", "(Landroidx/compose/ui/draganddrop/DragAndDropTransferData;)V", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface DragAndDropSourceScope extends PointerInputScope {
    void startTransfer(DragAndDropTransferData transferData);
}
