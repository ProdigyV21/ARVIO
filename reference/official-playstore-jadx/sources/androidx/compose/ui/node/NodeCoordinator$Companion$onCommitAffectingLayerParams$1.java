package androidx.compose.ui.node;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/node/NodeCoordinator;", "coordinator", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/node/NodeCoordinator;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class NodeCoordinator$Companion$onCommitAffectingLayerParams$1 extends r implements l<NodeCoordinator, t0> {
    public static final NodeCoordinator$Companion$onCommitAffectingLayerParams$1 INSTANCE = new NodeCoordinator$Companion$onCommitAffectingLayerParams$1();

    public NodeCoordinator$Companion$onCommitAffectingLayerParams$1() {
        super(1);
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((NodeCoordinator) obj);
        return t0.f22605a;
    }

    public final void invoke(NodeCoordinator nodeCoordinator) {
        if (nodeCoordinator.isValidOwnerScope()) {
            LayerPositionalProperties layerPositionalProperties = nodeCoordinator.layerPositionalProperties;
            if (layerPositionalProperties == null) {
                NodeCoordinator.updateLayerParameters$default(nodeCoordinator, false, 1, null);
                return;
            }
            NodeCoordinator.tmpLayerPositionalProperties.copyFrom(layerPositionalProperties);
            NodeCoordinator.updateLayerParameters$default(nodeCoordinator, false, 1, null);
            if (NodeCoordinator.tmpLayerPositionalProperties.hasSameValuesAs(layerPositionalProperties)) {
                return;
            }
            LayoutNode layoutNode = nodeCoordinator.getLayoutNode();
            LayoutNodeLayoutDelegate layoutDelegate = layoutNode.getLayoutDelegate();
            if (layoutDelegate.getChildrenAccessingCoordinatesDuringPlacement() > 0) {
                if (layoutDelegate.getCoordinatesAccessedDuringModifierPlacement() || layoutDelegate.getCoordinatesAccessedDuringPlacement()) {
                    LayoutNode.requestRelayout$ui_release$default(layoutNode, false, 1, null);
                }
                layoutDelegate.getMeasurePassDelegate().notifyChildrenUsingCoordinatesWhilePlacing();
            }
            Owner owner = layoutNode.getOwner();
            if (owner != null) {
                owner.requestOnPositionedCallback(layoutNode);
            }
        }
    }
}
