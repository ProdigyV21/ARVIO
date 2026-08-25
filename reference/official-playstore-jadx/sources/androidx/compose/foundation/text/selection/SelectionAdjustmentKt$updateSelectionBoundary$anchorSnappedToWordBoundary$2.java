package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.text.selection.Selection;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import x6.s;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/text/selection/Selection$AnchorInfo;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class SelectionAdjustmentKt$updateSelectionBoundary$anchorSnappedToWordBoundary$2 extends r implements r7.a<Selection.AnchorInfo> {
    final /* synthetic */ s<Integer> $currentRawLine$delegate;
    final /* synthetic */ int $currentRawOffset;
    final /* synthetic */ SelectableInfo $info;
    final /* synthetic */ int $otherRawOffset;
    final /* synthetic */ SelectionLayout $this_updateSelectionBoundary;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SelectionAdjustmentKt$updateSelectionBoundary$anchorSnappedToWordBoundary$2(SelectableInfo selectableInfo, int i10, int i11, SelectionLayout selectionLayout, s<Integer> sVar) {
        super(0);
        this.$info = selectableInfo;
        this.$currentRawOffset = i10;
        this.$otherRawOffset = i11;
        this.$this_updateSelectionBoundary = selectionLayout;
        this.$currentRawLine$delegate = sVar;
    }

    @Override // r7.a
    public final Selection.AnchorInfo invoke() {
        return SelectionAdjustmentKt.snapToWordBoundary(this.$info, SelectionAdjustmentKt.updateSelectionBoundary$lambda$0(this.$currentRawLine$delegate), this.$currentRawOffset, this.$otherRawOffset, this.$this_updateSelectionBoundary.getIsStartHandle(), this.$this_updateSelectionBoundary.getCrossStatus() == CrossStatus.CROSSED);
    }
}
