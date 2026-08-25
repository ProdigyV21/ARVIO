package androidx.compose.foundation;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.semantics.Role;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0004\b\r\u0010\u000eJB\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\t\u001a\u0004\u0018\u00010\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0013\u001a\u00020\u00128\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0018\u001a\u00020\u00178\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001c"}, d2 = {"Landroidx/compose/foundation/ClickableNode;", "Landroidx/compose/foundation/AbstractClickableNode;", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "", "enabled", "", "onClickLabel", "Landroidx/compose/ui/semantics/Role;", "role", "Lkotlin/Function0;", "Lx6/t0;", "onClick", "<init>", "(Landroidx/compose/foundation/interaction/MutableInteractionSource;ZLjava/lang/String;Landroidx/compose/ui/semantics/Role;Lr7/a;Lkotlin/jvm/internal/h;)V", "update-XHw0xAI", "(Landroidx/compose/foundation/interaction/MutableInteractionSource;ZLjava/lang/String;Landroidx/compose/ui/semantics/Role;Lr7/a;)V", "update", "Landroidx/compose/foundation/ClickableSemanticsNode;", "clickableSemanticsNode", "Landroidx/compose/foundation/ClickableSemanticsNode;", "getClickableSemanticsNode", "()Landroidx/compose/foundation/ClickableSemanticsNode;", "Landroidx/compose/foundation/ClickablePointerInputNode;", "clickablePointerInputNode", "Landroidx/compose/foundation/ClickablePointerInputNode;", "getClickablePointerInputNode", "()Landroidx/compose/foundation/ClickablePointerInputNode;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class ClickableNode extends AbstractClickableNode {
    private final ClickablePointerInputNode clickablePointerInputNode;
    private final ClickableSemanticsNode clickableSemanticsNode;

    public /* synthetic */ ClickableNode(MutableInteractionSource mutableInteractionSource, boolean z, String str, Role role, r7.a aVar, h hVar) {
        this(mutableInteractionSource, z, str, role, aVar);
    }

    @Override // androidx.compose.foundation.AbstractClickableNode
    public ClickableSemanticsNode getClickableSemanticsNode() {
        return this.clickableSemanticsNode;
    }

    /* JADX INFO: renamed from: update-XHw0xAI, reason: not valid java name */
    public final void m223updateXHw0xAI(MutableInteractionSource interactionSource, boolean enabled, String onClickLabel, Role role, r7.a<t0> onClick) {
        m150updateCommonXHw0xAI(interactionSource, enabled, onClickLabel, role, onClick);
        getClickableSemanticsNode().m226updateUMe6uN4(enabled, onClickLabel, role, onClick, null, null);
        getClickablePointerInputNode().update(enabled, interactionSource, onClick);
    }

    private ClickableNode(MutableInteractionSource mutableInteractionSource, boolean z, String str, Role role, r7.a<t0> aVar) {
        super(mutableInteractionSource, z, str, role, aVar, null);
        this.clickableSemanticsNode = (ClickableSemanticsNode) delegate(new ClickableSemanticsNode(z, str, role, aVar, null, null, null));
        this.clickablePointerInputNode = (ClickablePointerInputNode) delegate(new ClickablePointerInputNode(z, mutableInteractionSource, aVar, getInteractionData()));
    }

    @Override // androidx.compose.foundation.AbstractClickableNode
    public ClickablePointerInputNode getClickablePointerInputNode() {
        return this.clickablePointerInputNode;
    }
}
