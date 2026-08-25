package androidx.compose.material3;

import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Float;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class SheetState$anchoredDraggableState$2 extends r implements r7.a<Float> {
    final /* synthetic */ SheetState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SheetState$anchoredDraggableState$2(SheetState sheetState) {
        super(0);
        this.this$0 = sheetState;
    }

    @Override // r7.a
    public final Float invoke() {
        return Float.valueOf(this.this$0.requireDensity().mo285toPx0680j_4(Dp.m5678constructorimpl(125)));
    }
}
