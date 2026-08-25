package androidx.compose.ui.platform;

import androidx.compose.ui.draganddrop.DragAndDropTransferData;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public /* synthetic */ class AndroidComposeView$dragAndDropModifierOnDragListener$1 extends kotlin.jvm.internal.m implements r7.q<DragAndDropTransferData, Size, r7.l<? super DrawScope, ? extends t0>, Boolean> {
    public AndroidComposeView$dragAndDropModifierOnDragListener$1(Object obj) {
        super(3, 0, AndroidComposeView.class, obj, "startDrag", "startDrag-12SF9DM(Landroidx/compose/ui/draganddrop/DragAndDropTransferData;JLkotlin/jvm/functions/Function1;)Z");
    }

    @Override // r7.q
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return m4926invoke12SF9DM((DragAndDropTransferData) obj, ((Size) obj2).getPackedValue(), (r7.l) obj3);
    }

    /* JADX INFO: renamed from: invoke-12SF9DM, reason: not valid java name */
    public final Boolean m4926invoke12SF9DM(DragAndDropTransferData dragAndDropTransferData, long j10, r7.l<? super DrawScope, t0> lVar) {
        return Boolean.valueOf(((AndroidComposeView) this.receiver).m4924startDrag12SF9DM(dragAndDropTransferData, j10, lVar));
    }
}
