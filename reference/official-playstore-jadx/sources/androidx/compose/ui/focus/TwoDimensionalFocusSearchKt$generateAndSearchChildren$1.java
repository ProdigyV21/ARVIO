package androidx.compose.ui.focus;

import androidx.compose.ui.layout.BeyondBoundsLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/layout/BeyondBoundsLayout$BeyondBoundsScope;", "invoke", "(Landroidx/compose/ui/layout/BeyondBoundsLayout$BeyondBoundsScope;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class TwoDimensionalFocusSearchKt$generateAndSearchChildren$1 extends r implements l<BeyondBoundsLayout.BeyondBoundsScope, Boolean> {
    final /* synthetic */ int $direction;
    final /* synthetic */ FocusTargetNode $focusedItem;
    final /* synthetic */ l<FocusTargetNode, Boolean> $onFound;
    final /* synthetic */ FocusTargetNode $this_generateAndSearchChildren;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public TwoDimensionalFocusSearchKt$generateAndSearchChildren$1(FocusTargetNode focusTargetNode, FocusTargetNode focusTargetNode2, int i10, l<? super FocusTargetNode, Boolean> lVar) {
        super(1);
        this.$this_generateAndSearchChildren = focusTargetNode;
        this.$focusedItem = focusTargetNode2;
        this.$direction = i10;
        this.$onFound = lVar;
    }

    @Override // r7.l
    public final Boolean invoke(BeyondBoundsLayout.BeyondBoundsScope beyondBoundsScope) {
        boolean zM3200searchChildren4C6V_qg = TwoDimensionalFocusSearchKt.m3200searchChildren4C6V_qg(this.$this_generateAndSearchChildren, this.$focusedItem, this.$direction, this.$onFound);
        Boolean boolValueOf = Boolean.valueOf(zM3200searchChildren4C6V_qg);
        if (zM3200searchChildren4C6V_qg || !beyondBoundsScope.getHasMoreContent()) {
            return boolValueOf;
        }
        return null;
    }
}
