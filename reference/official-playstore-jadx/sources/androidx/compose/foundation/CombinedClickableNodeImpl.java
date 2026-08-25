package androidx.compose.foundation;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.semantics.Role;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002Bc\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u0011\u0010\u0012Jn\u0010\u0015\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0016ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014R\u001e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\b\u0010\u0016R\u001a\u0010\u0018\u001a\u00020\u00178\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001d\u001a\u00020\u001c8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 \u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006!"}, d2 = {"Landroidx/compose/foundation/CombinedClickableNodeImpl;", "Landroidx/compose/foundation/CombinedClickableNode;", "Landroidx/compose/foundation/AbstractClickableNode;", "Lkotlin/Function0;", "Lx6/t0;", "onClick", "", "onLongClickLabel", "onLongClick", "onDoubleClick", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "", "enabled", "onClickLabel", "Landroidx/compose/ui/semantics/Role;", "role", "<init>", "(Lr7/a;Ljava/lang/String;Lr7/a;Lr7/a;Landroidx/compose/foundation/interaction/MutableInteractionSource;ZLjava/lang/String;Landroidx/compose/ui/semantics/Role;Lkotlin/jvm/internal/h;)V", "update-xpl5gLE", "(Lr7/a;Ljava/lang/String;Lr7/a;Lr7/a;Landroidx/compose/foundation/interaction/MutableInteractionSource;ZLjava/lang/String;Landroidx/compose/ui/semantics/Role;)V", "update", "Lr7/a;", "Landroidx/compose/foundation/ClickableSemanticsNode;", "clickableSemanticsNode", "Landroidx/compose/foundation/ClickableSemanticsNode;", "getClickableSemanticsNode", "()Landroidx/compose/foundation/ClickableSemanticsNode;", "Landroidx/compose/foundation/CombinedClickablePointerInputNode;", "clickablePointerInputNode", "Landroidx/compose/foundation/CombinedClickablePointerInputNode;", "getClickablePointerInputNode", "()Landroidx/compose/foundation/CombinedClickablePointerInputNode;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class CombinedClickableNodeImpl extends AbstractClickableNode implements CombinedClickableNode {
    private final CombinedClickablePointerInputNode clickablePointerInputNode;
    private final ClickableSemanticsNode clickableSemanticsNode;
    private r7.a<t0> onLongClick;

    public /* synthetic */ CombinedClickableNodeImpl(r7.a aVar, String str, r7.a aVar2, r7.a aVar3, MutableInteractionSource mutableInteractionSource, boolean z, String str2, Role role, h hVar) {
        this(aVar, str, aVar2, aVar3, mutableInteractionSource, z, str2, role);
    }

    @Override // androidx.compose.foundation.AbstractClickableNode
    public ClickableSemanticsNode getClickableSemanticsNode() {
        return this.clickableSemanticsNode;
    }

    @Override // androidx.compose.foundation.CombinedClickableNode
    /* JADX INFO: renamed from: update-xpl5gLE */
    public void mo231updatexpl5gLE(r7.a<t0> onClick, String onLongClickLabel, r7.a<t0> onLongClick, r7.a<t0> onDoubleClick, MutableInteractionSource interactionSource, boolean enabled, String onClickLabel, Role role) {
        if ((this.onLongClick == null) != (onLongClick == null)) {
            disposeInteractionSource();
        }
        this.onLongClick = onLongClick;
        m150updateCommonXHw0xAI(interactionSource, enabled, onClickLabel, role, onClick);
        getClickableSemanticsNode().m226updateUMe6uN4(enabled, onClickLabel, role, onClick, onLongClickLabel, onLongClick);
        getClickablePointerInputNode().update(enabled, interactionSource, onClick, onLongClick, onDoubleClick);
    }

    private CombinedClickableNodeImpl(r7.a<t0> aVar, String str, r7.a<t0> aVar2, r7.a<t0> aVar3, MutableInteractionSource mutableInteractionSource, boolean z, String str2, Role role) {
        super(mutableInteractionSource, z, str2, role, aVar, null);
        this.onLongClick = aVar2;
        this.clickableSemanticsNode = (ClickableSemanticsNode) delegate(new ClickableSemanticsNode(z, str2, role, aVar, str, aVar2, null));
        this.clickablePointerInputNode = (CombinedClickablePointerInputNode) delegate(new CombinedClickablePointerInputNode(z, mutableInteractionSource, aVar, getInteractionData(), this.onLongClick, aVar3));
    }

    @Override // androidx.compose.foundation.AbstractClickableNode
    public CombinedClickablePointerInputNode getClickablePointerInputNode() {
        return this.clickablePointerInputNode;
    }
}
