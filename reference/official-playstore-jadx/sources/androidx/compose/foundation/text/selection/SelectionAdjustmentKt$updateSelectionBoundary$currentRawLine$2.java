package androidx.compose.foundation.text.selection;

import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class SelectionAdjustmentKt$updateSelectionBoundary$currentRawLine$2 extends r implements r7.a<Integer> {
    final /* synthetic */ int $currentRawOffset;
    final /* synthetic */ SelectableInfo $info;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SelectionAdjustmentKt$updateSelectionBoundary$currentRawLine$2(SelectableInfo selectableInfo, int i10) {
        super(0);
        this.$info = selectableInfo;
        this.$currentRawOffset = i10;
    }

    @Override // r7.a
    public final Integer invoke() {
        return Integer.valueOf(this.$info.getTextLayoutResult().getLineForOffset(this.$currentRawOffset));
    }
}
