package androidx.compose.ui.semantics;

import androidx.compose.ui.node.LayoutNode;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Landroidx/compose/ui/node/LayoutNode;", "invoke", "(Landroidx/compose/ui/node/LayoutNode;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class SemanticsNode$parent$1 extends r implements l<LayoutNode, Boolean> {
    public static final SemanticsNode$parent$1 INSTANCE = new SemanticsNode$parent$1();

    public SemanticsNode$parent$1() {
        super(1);
    }

    @Override // r7.l
    public final Boolean invoke(LayoutNode layoutNode) {
        SemanticsConfiguration collapsedSemantics$ui_release = layoutNode.getCollapsedSemantics$ui_release();
        boolean z = false;
        if (collapsedSemantics$ui_release != null && collapsedSemantics$ui_release.getIsMergingSemanticsOfDescendants()) {
            z = true;
        }
        return Boolean.valueOf(z);
    }
}
