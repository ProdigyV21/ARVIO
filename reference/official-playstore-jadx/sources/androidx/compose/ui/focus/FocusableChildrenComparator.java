package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutNode;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\b\u001a\u0004\u0018\u00010\u0002H\u0016J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\u000bH\u0002¨\u0006\r"}, d2 = {"Landroidx/compose/ui/focus/FocusableChildrenComparator;", "Ljava/util/Comparator;", "Landroidx/compose/ui/focus/FocusTargetNode;", "Lkotlin/Comparator;", "()V", "compare", "", "focusTarget1", "focusTarget2", "pathFromRoot", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/ui/node/LayoutNode;", "layoutNode", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class FocusableChildrenComparator implements Comparator<FocusTargetNode> {
    public static final FocusableChildrenComparator INSTANCE = new FocusableChildrenComparator();

    private FocusableChildrenComparator() {
    }

    private final MutableVector<LayoutNode> pathFromRoot(LayoutNode layoutNode) {
        MutableVector<LayoutNode> mutableVector = new MutableVector<>(new LayoutNode[16], 0);
        while (layoutNode != null) {
            mutableVector.add(0, layoutNode);
            layoutNode = layoutNode.getParent$ui_release();
        }
        return mutableVector;
    }

    @Override // java.util.Comparator
    public int compare(FocusTargetNode focusTarget1, FocusTargetNode focusTarget2) {
        if (focusTarget1 == null) {
            throw new IllegalArgumentException("compare requires non-null focus targets");
        }
        if (focusTarget2 == null) {
            throw new IllegalArgumentException("compare requires non-null focus targets");
        }
        int i10 = 0;
        if (!FocusTraversalKt.isEligibleForFocusSearch(focusTarget1) || !FocusTraversalKt.isEligibleForFocusSearch(focusTarget2)) {
            if (FocusTraversalKt.isEligibleForFocusSearch(focusTarget1)) {
                return -1;
            }
            return FocusTraversalKt.isEligibleForFocusSearch(focusTarget2) ? 1 : 0;
        }
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTarget1);
        LayoutNode layoutNodeRequireLayoutNode2 = DelegatableNodeKt.requireLayoutNode(focusTarget2);
        if (p.a(layoutNodeRequireLayoutNode, layoutNodeRequireLayoutNode2)) {
            return 0;
        }
        MutableVector<LayoutNode> mutableVectorPathFromRoot = pathFromRoot(layoutNodeRequireLayoutNode);
        MutableVector<LayoutNode> mutableVectorPathFromRoot2 = pathFromRoot(layoutNodeRequireLayoutNode2);
        int iMin = Math.min(mutableVectorPathFromRoot.getSize() - 1, mutableVectorPathFromRoot2.getSize() - 1);
        if (iMin >= 0) {
            while (p.a(mutableVectorPathFromRoot.getContent()[i10], mutableVectorPathFromRoot2.getContent()[i10])) {
                if (i10 != iMin) {
                    i10++;
                }
            }
            return p.c(mutableVectorPathFromRoot.getContent()[i10].getPlaceOrder$ui_release(), mutableVectorPathFromRoot2.getContent()[i10].getPlaceOrder$ui_release());
        }
        throw new IllegalStateException("Could not find a common ancestor between the two FocusModifiers.");
    }
}
