package androidx.compose.ui.input.pointer;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.pointer.PointerEventType;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.PointerInputModifierNode;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.node.TraversableNodeKt;
import androidx.compose.ui.node.f;
import androidx.compose.ui.platform.CompositionLocalsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.f0;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.k0;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0019\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u000e\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u000f\u0010\rJ\u0011\u0010\u0010\u001a\u0004\u0018\u00010\u0000H\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u0012\u0010\rJ\u0011\u0010\u0013\u001a\u0004\u0018\u00010\u0000H\u0002¢\u0006\u0004\b\u0013\u0010\u0011J\u000f\u0010\u0014\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u0014\u0010\rJ*\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0019H\u0016ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001e\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u001e\u0010\rJ\u000f\u0010\u001f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u001f\u0010\rR\u001a\u0010!\u001a\u00020 8\u0016X\u0096D¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$R*\u0010\u0006\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\u00058\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\u0006\u0010&\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R*\u0010\b\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u00078\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\b\u0010+\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u0016\u00100\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b0\u0010+R\u0016\u00104\u001a\u0004\u0018\u0001018BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b2\u00103\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00065"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerHoverIconModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/TraversableNode;", "Landroidx/compose/ui/node/PointerInputModifierNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/ui/input/pointer/PointerIcon;", "icon", "", "overrideDescendants", "<init>", "(Landroidx/compose/ui/input/pointer/PointerIcon;Z)V", "Lx6/t0;", "displayIcon", "()V", "displayDefaultIcon", "displayIconIfDescendantsDoNotHavePriority", "findDescendantNodeWithCursorInBounds", "()Landroidx/compose/ui/input/pointer/PointerHoverIconModifierNode;", "displayIconFromCurrentNodeOrDescendantsWithCursorInBounds", "findOverridingAncestorNode", "displayIconFromAncestorNodeWithCursorInBoundsOrDefaultIcon", "Landroidx/compose/ui/input/pointer/PointerEvent;", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "pass", "Landroidx/compose/ui/unit/IntSize;", "bounds", "onPointerEvent-H0pRuoY", "(Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEventPass;J)V", "onPointerEvent", "onCancelPointerInput", "onDetach", "", "traverseKey", "Ljava/lang/String;", "getTraverseKey", "()Ljava/lang/String;", "value", "Landroidx/compose/ui/input/pointer/PointerIcon;", "getIcon", "()Landroidx/compose/ui/input/pointer/PointerIcon;", "setIcon", "(Landroidx/compose/ui/input/pointer/PointerIcon;)V", "Z", "getOverrideDescendants", "()Z", "setOverrideDescendants", "(Z)V", "cursorInBoundsOfNode", "Landroidx/compose/ui/input/pointer/PointerIconService;", "getPointerIconService", "()Landroidx/compose/ui/input/pointer/PointerIconService;", "pointerIconService", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PointerHoverIconModifierNode extends Modifier.Node implements TraversableNode, PointerInputModifierNode, CompositionLocalConsumerModifierNode {
    public static final int $stable = 8;
    private boolean cursorInBoundsOfNode;
    private PointerIcon icon;
    private boolean overrideDescendants;
    private final String traverseKey;

    /* JADX INFO: renamed from: androidx.compose.ui.input.pointer.PointerHoverIconModifierNode$displayIconFromAncestorNodeWithCursorInBoundsOrDefaultIcon$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Landroidx/compose/ui/input/pointer/PointerHoverIconModifierNode;", "invoke", "(Landroidx/compose/ui/input/pointer/PointerHoverIconModifierNode;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<PointerHoverIconModifierNode, Boolean> {
        final /* synthetic */ k0 $pointerHoverIconModifierNode;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(k0 k0Var) {
            super(1);
            this.$pointerHoverIconModifierNode = k0Var;
        }

        @Override // r7.l
        public final Boolean invoke(PointerHoverIconModifierNode pointerHoverIconModifierNode) {
            if (this.$pointerHoverIconModifierNode.f19746i == null && pointerHoverIconModifierNode.cursorInBoundsOfNode) {
                this.$pointerHoverIconModifierNode.f19746i = pointerHoverIconModifierNode;
            } else if (this.$pointerHoverIconModifierNode.f19746i != null && pointerHoverIconModifierNode.getOverrideDescendants() && pointerHoverIconModifierNode.cursorInBoundsOfNode) {
                this.$pointerHoverIconModifierNode.f19746i = pointerHoverIconModifierNode;
            }
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.input.pointer.PointerHoverIconModifierNode$displayIconIfDescendantsDoNotHavePriority$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/ui/node/TraversableNode$Companion$TraverseDescendantsAction;", "it", "Landroidx/compose/ui/input/pointer/PointerHoverIconModifierNode;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C06061 extends r implements l<PointerHoverIconModifierNode, TraversableNode.Companion.TraverseDescendantsAction> {
        final /* synthetic */ f0 $hasIconRightsOverDescendants;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06061(f0 f0Var) {
            super(1);
            this.$hasIconRightsOverDescendants = f0Var;
        }

        @Override // r7.l
        public final TraversableNode.Companion.TraverseDescendantsAction invoke(PointerHoverIconModifierNode pointerHoverIconModifierNode) {
            if (!pointerHoverIconModifierNode.cursorInBoundsOfNode) {
                return TraversableNode.Companion.TraverseDescendantsAction.ContinueTraversal;
            }
            this.$hasIconRightsOverDescendants.f19738i = false;
            return TraversableNode.Companion.TraverseDescendantsAction.CancelTraversal;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.input.pointer.PointerHoverIconModifierNode$findDescendantNodeWithCursorInBounds$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/ui/node/TraversableNode$Companion$TraverseDescendantsAction;", "it", "Landroidx/compose/ui/input/pointer/PointerHoverIconModifierNode;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C06071 extends r implements l<PointerHoverIconModifierNode, TraversableNode.Companion.TraverseDescendantsAction> {
        final /* synthetic */ k0 $descendantNodeWithCursorInBounds;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06071(k0 k0Var) {
            super(1);
            this.$descendantNodeWithCursorInBounds = k0Var;
        }

        @Override // r7.l
        public final TraversableNode.Companion.TraverseDescendantsAction invoke(PointerHoverIconModifierNode pointerHoverIconModifierNode) {
            TraversableNode.Companion.TraverseDescendantsAction traverseDescendantsAction = TraversableNode.Companion.TraverseDescendantsAction.ContinueTraversal;
            if (pointerHoverIconModifierNode.cursorInBoundsOfNode) {
                this.$descendantNodeWithCursorInBounds.f19746i = pointerHoverIconModifierNode;
                if (pointerHoverIconModifierNode.getOverrideDescendants()) {
                    return TraversableNode.Companion.TraverseDescendantsAction.SkipSubtreeAndContinueTraversal;
                }
            }
            return traverseDescendantsAction;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.input.pointer.PointerHoverIconModifierNode$findOverridingAncestorNode$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Landroidx/compose/ui/input/pointer/PointerHoverIconModifierNode;", "invoke", "(Landroidx/compose/ui/input/pointer/PointerHoverIconModifierNode;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C06081 extends r implements l<PointerHoverIconModifierNode, Boolean> {
        final /* synthetic */ k0 $pointerHoverIconModifierNode;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06081(k0 k0Var) {
            super(1);
            this.$pointerHoverIconModifierNode = k0Var;
        }

        @Override // r7.l
        public final Boolean invoke(PointerHoverIconModifierNode pointerHoverIconModifierNode) {
            if (pointerHoverIconModifierNode.getOverrideDescendants() && pointerHoverIconModifierNode.cursorInBoundsOfNode) {
                this.$pointerHoverIconModifierNode.f19746i = pointerHoverIconModifierNode;
            }
            return Boolean.TRUE;
        }
    }

    public /* synthetic */ PointerHoverIconModifierNode(PointerIcon pointerIcon, boolean z, int i10, h hVar) {
        this(pointerIcon, (i10 & 2) != 0 ? false : z);
    }

    private final void displayDefaultIcon() {
        PointerIconService pointerIconService = getPointerIconService();
        if (pointerIconService != null) {
            pointerIconService.setIcon(null);
        }
    }

    private final void displayIcon() {
        PointerIcon pointerIcon;
        PointerHoverIconModifierNode pointerHoverIconModifierNodeFindOverridingAncestorNode = findOverridingAncestorNode();
        if (pointerHoverIconModifierNodeFindOverridingAncestorNode == null || (pointerIcon = pointerHoverIconModifierNodeFindOverridingAncestorNode.icon) == null) {
            pointerIcon = this.icon;
        }
        PointerIconService pointerIconService = getPointerIconService();
        if (pointerIconService != null) {
            pointerIconService.setIcon(pointerIcon);
        }
    }

    private final void displayIconFromAncestorNodeWithCursorInBoundsOrDefaultIcon() {
        t0 t0Var;
        k0 k0Var = new k0();
        TraversableNodeKt.traverseAncestors(this, new AnonymousClass1(k0Var));
        PointerHoverIconModifierNode pointerHoverIconModifierNode = (PointerHoverIconModifierNode) k0Var.f19746i;
        if (pointerHoverIconModifierNode != null) {
            pointerHoverIconModifierNode.displayIcon();
            t0Var = t0.f22605a;
        } else {
            t0Var = null;
        }
        if (t0Var == null) {
            displayDefaultIcon();
        }
    }

    private final void displayIconFromCurrentNodeOrDescendantsWithCursorInBounds() {
        PointerHoverIconModifierNode pointerHoverIconModifierNodeFindDescendantNodeWithCursorInBounds;
        if (this.cursorInBoundsOfNode) {
            if (this.overrideDescendants || (pointerHoverIconModifierNodeFindDescendantNodeWithCursorInBounds = findDescendantNodeWithCursorInBounds()) == null) {
                pointerHoverIconModifierNodeFindDescendantNodeWithCursorInBounds = this;
            }
            pointerHoverIconModifierNodeFindDescendantNodeWithCursorInBounds.displayIcon();
        }
    }

    private final void displayIconIfDescendantsDoNotHavePriority() {
        f0 f0Var = new f0();
        f0Var.f19738i = true;
        if (!this.overrideDescendants) {
            TraversableNodeKt.traverseDescendants(this, new C06061(f0Var));
        }
        if (f0Var.f19738i) {
            displayIcon();
        }
    }

    private final PointerHoverIconModifierNode findDescendantNodeWithCursorInBounds() {
        k0 k0Var = new k0();
        TraversableNodeKt.traverseDescendants(this, new C06071(k0Var));
        return (PointerHoverIconModifierNode) k0Var.f19746i;
    }

    private final PointerHoverIconModifierNode findOverridingAncestorNode() {
        k0 k0Var = new k0();
        TraversableNodeKt.traverseAncestors(this, new C06081(k0Var));
        return (PointerHoverIconModifierNode) k0Var.f19746i;
    }

    private final PointerIconService getPointerIconService() {
        return (PointerIconService) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalPointerIconService());
    }

    public final PointerIcon getIcon() {
        return this.icon;
    }

    public final boolean getOverrideDescendants() {
        return this.overrideDescendants;
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final /* synthetic */ boolean interceptOutOfBoundsChildEvents() {
        return f.a(this);
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public void onCancelPointerInput() {
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final /* synthetic */ void onDensityChange() {
        f.b(this);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        this.cursorInBoundsOfNode = false;
        displayIconFromAncestorNodeWithCursorInBoundsOrDefaultIcon();
        super.onDetach();
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    /* JADX INFO: renamed from: onPointerEvent-H0pRuoY */
    public void mo148onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pass, long bounds) {
        if (pass == PointerEventPass.Main) {
            int type = pointerEvent.getType();
            PointerEventType.Companion companion = PointerEventType.INSTANCE;
            if (PointerEventType.m4467equalsimpl0(type, companion.m4471getEnter7fucELk())) {
                this.cursorInBoundsOfNode = true;
                displayIconIfDescendantsDoNotHavePriority();
            } else if (PointerEventType.m4467equalsimpl0(pointerEvent.getType(), companion.m4472getExit7fucELk())) {
                this.cursorInBoundsOfNode = false;
                displayIconFromAncestorNodeWithCursorInBoundsOrDefaultIcon();
            }
        }
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final /* synthetic */ void onViewConfigurationChange() {
        f.c(this);
    }

    public final void setIcon(PointerIcon pointerIcon) {
        if (p.a(this.icon, pointerIcon)) {
            return;
        }
        this.icon = pointerIcon;
        if (this.cursorInBoundsOfNode) {
            displayIconIfDescendantsDoNotHavePriority();
        }
    }

    public final void setOverrideDescendants(boolean z) {
        if (this.overrideDescendants != z) {
            this.overrideDescendants = z;
            if (z) {
                if (this.cursorInBoundsOfNode) {
                    displayIcon();
                }
            } else if (this.cursorInBoundsOfNode) {
                displayIconFromCurrentNodeOrDescendantsWithCursorInBounds();
            }
        }
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final /* synthetic */ boolean sharePointerInputWithSiblings() {
        return f.d(this);
    }

    public PointerHoverIconModifierNode(PointerIcon pointerIcon, boolean z) {
        this.traverseKey = "androidx.compose.ui.input.pointer.PointerHoverIcon";
        this.icon = pointerIcon;
        this.overrideDescendants = z;
    }

    @Override // androidx.compose.ui.node.TraversableNode
    public String getTraverseKey() {
        return this.traverseKey;
    }
}
