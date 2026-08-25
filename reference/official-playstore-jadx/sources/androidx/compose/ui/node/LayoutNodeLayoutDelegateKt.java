package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.layout.Measurable;
import kotlin.Metadata;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u0013\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a@\u0010\u000b\u001a\u00020\n\"\b\b\u0000\u0010\u0005*\u00020\u0004*\u00020\u00002\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u00062\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00028\u00000\bH\u0082\b¢\u0006\u0004\b\u000b\u0010\f\"\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Landroidx/compose/ui/node/LayoutNode;", "", "isOutMostLookaheadRoot", "(Landroidx/compose/ui/node/LayoutNode;)Z", "Landroidx/compose/ui/layout/Measurable;", "T", "Landroidx/compose/runtime/collection/MutableVector;", "destination", "Lkotlin/Function1;", "transform", "Lx6/t0;", "updateChildMeasurables", "(Landroidx/compose/ui/node/LayoutNode;Landroidx/compose/runtime/collection/MutableVector;Lr7/l;)V", "", "MeasuredTwiceErrorMessage", "Ljava/lang/String;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LayoutNodeLayoutDelegateKt {
    private static final String MeasuredTwiceErrorMessage = "measure() may not be called multiple times on the same Measurable. If you want to get the content size of the Measurable before calculating the final constraints, please use methods like minIntrinsicWidth()/maxIntrinsicWidth() and minIntrinsicHeight()/maxIntrinsicHeight()";

    public static final boolean isOutMostLookaheadRoot(LayoutNode layoutNode) {
        if (layoutNode.getLookaheadRoot() == null) {
            return false;
        }
        LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
        return (parent$ui_release != null ? parent$ui_release.getLookaheadRoot() : null) == null || layoutNode.getLayoutDelegate().getDetachedFromParentLookaheadPass();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T extends Measurable> void updateChildMeasurables(LayoutNode layoutNode, MutableVector<T> mutableVector, l<? super LayoutNode, ? extends T> lVar) {
        MutableVector<LayoutNode> mutableVector2 = layoutNode.get_children$ui_release();
        int size = mutableVector2.getSize();
        if (size > 0) {
            LayoutNode[] content = mutableVector2.getContent();
            int i10 = 0;
            do {
                LayoutNode layoutNode2 = content[i10];
                if (mutableVector.getSize() <= i10) {
                    mutableVector.add(lVar.invoke(layoutNode2));
                } else {
                    mutableVector.set(i10, lVar.invoke(layoutNode2));
                }
                i10++;
            } while (i10 < size);
        }
        mutableVector.removeRange(layoutNode.getChildren$ui_release().size(), mutableVector.getSize());
    }
}
