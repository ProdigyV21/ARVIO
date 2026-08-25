package androidx.compose.ui.semantics;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.LayoutInfo;
import androidx.compose.ui.layout.c;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.node.RootForTest;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import androidx.compose.ui.unit.IntSize;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.collections.z;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0015\b\u0007\u0018\u00002\u00020\u0001B)\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00000\u00122\b\b\u0002\u0010\u0011\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\u0011\u0010\u0019\u001a\u0004\u0018\u00010\u0016H\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u001c\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\bH\u0002¢\u0006\u0004\b\u001f\u0010 J!\u0010#\u001a\u00020\u001e*\u00020\u00062\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00000!H\u0002¢\u0006\u0004\b#\u0010$J%\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00000\u00122\u0006\u0010%\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0004H\u0002¢\u0006\u0004\b&\u0010'J%\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00000\u00122\u000e\b\u0002\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00000!H\u0002¢\u0006\u0004\b(\u0010)J\u001d\u0010*\u001a\u00020\u001e2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00000!H\u0002¢\u0006\u0004\b*\u0010+J5\u00104\u001a\u00020\u00002\b\u0010-\u001a\u0004\u0018\u00010,2\u0017\u00101\u001a\u0013\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u00020\u001e0.¢\u0006\u0002\b0H\u0002ø\u0001\u0000¢\u0006\u0004\b2\u00103R\u001a\u0010\u0003\u001a\u00020\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0003\u00105\u001a\u0004\b6\u00107R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u00108\u001a\u0004\b9\u0010:R\u001a\u0010\u0007\u001a\u00020\u00068\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0007\u0010;\u001a\u0004\b<\u0010=R\u001a\u0010\t\u001a\u00020\b8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\t\u0010>\u001a\u0004\b?\u0010@R\"\u0010A\u001a\u00020\u00048\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bA\u00108\u001a\u0004\bB\u0010:\"\u0004\bC\u0010DR\u0018\u0010E\u001a\u0004\u0018\u00010\u00008\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bE\u0010FR\u0017\u0010G\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\bG\u0010H\u001a\u0004\bI\u0010JR\u0014\u0010L\u001a\u00020\u00048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bK\u0010:R\u0011\u0010P\u001a\u00020M8F¢\u0006\u0006\u001a\u0004\bN\u0010OR\u0013\u0010T\u001a\u0004\u0018\u00010Q8F¢\u0006\u0006\u001a\u0004\bR\u0010SR\u0011\u0010X\u001a\u00020U8F¢\u0006\u0006\u001a\u0004\bV\u0010WR\u0017\u0010\\\u001a\u00020Y8Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bZ\u0010[R\u0011\u0010^\u001a\u00020U8F¢\u0006\u0006\u001a\u0004\b]\u0010WR\u0017\u0010a\u001a\u00020_8Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b`\u0010[R\u0011\u0010c\u001a\u00020U8F¢\u0006\u0006\u001a\u0004\bb\u0010WR\u0017\u0010e\u001a\u00020_8Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bd\u0010[R\u0014\u0010g\u001a\u00020U8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bf\u0010WR\u0014\u0010i\u001a\u00020\u00048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bh\u0010:R\u0011\u0010k\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\bj\u0010@R\u0017\u0010m\u001a\b\u0012\u0004\u0012\u00020\u00000\u00128F¢\u0006\u0006\u001a\u0004\b&\u0010lR\u001a\u0010o\u001a\b\u0012\u0004\u0012\u00020\u00000\u00128@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bn\u0010lR\u0011\u0010p\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\bp\u0010:R\u0013\u0010r\u001a\u0004\u0018\u00010\u00008F¢\u0006\u0006\u001a\u0004\bq\u0010\u001bR\u0014\u0010s\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bs\u0010:\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006t"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsNode;", "", "Landroidx/compose/ui/Modifier$Node;", "outerSemanticsNode", "", "mergingEnabled", "Landroidx/compose/ui/node/LayoutNode;", "layoutNode", "Landroidx/compose/ui/semantics/SemanticsConfiguration;", "unmergedConfig", "<init>", "(Landroidx/compose/ui/Modifier$Node;ZLandroidx/compose/ui/node/LayoutNode;Landroidx/compose/ui/semantics/SemanticsConfiguration;)V", "Landroidx/compose/ui/layout/AlignmentLine;", "alignmentLine", "", "getAlignmentLinePosition", "(Landroidx/compose/ui/layout/AlignmentLine;)I", "includeFakeNodes", "", "unmergedChildren$ui_release", "(Z)Ljava/util/List;", "unmergedChildren", "Landroidx/compose/ui/node/NodeCoordinator;", "findCoordinatorToGetBounds$ui_release", "()Landroidx/compose/ui/node/NodeCoordinator;", "findCoordinatorToGetBounds", "copyWithMergingEnabled$ui_release", "()Landroidx/compose/ui/semantics/SemanticsNode;", "copyWithMergingEnabled", "mergedConfig", "Lx6/t0;", "mergeConfig", "(Landroidx/compose/ui/semantics/SemanticsConfiguration;)V", "", "list", "fillOneLayerOfSemanticsWrappers", "(Landroidx/compose/ui/node/LayoutNode;Ljava/util/List;)V", "includeReplacedSemantics", "getChildren", "(ZZ)Ljava/util/List;", "findOneLayerOfMergingSemanticsNodes", "(Ljava/util/List;)Ljava/util/List;", "emitFakeNodes", "(Ljava/util/List;)V", "Landroidx/compose/ui/semantics/Role;", "role", "Lkotlin/Function1;", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lx6/n;", "properties", "fakeSemanticsNode-ypyhhiA", "(Landroidx/compose/ui/semantics/Role;Lr7/l;)Landroidx/compose/ui/semantics/SemanticsNode;", "fakeSemanticsNode", "Landroidx/compose/ui/Modifier$Node;", "getOuterSemanticsNode$ui_release", "()Landroidx/compose/ui/Modifier$Node;", "Z", "getMergingEnabled", "()Z", "Landroidx/compose/ui/node/LayoutNode;", "getLayoutNode$ui_release", "()Landroidx/compose/ui/node/LayoutNode;", "Landroidx/compose/ui/semantics/SemanticsConfiguration;", "getUnmergedConfig$ui_release", "()Landroidx/compose/ui/semantics/SemanticsConfiguration;", "isFake", "isFake$ui_release", "setFake$ui_release", "(Z)V", "fakeNodeParent", "Landroidx/compose/ui/semantics/SemanticsNode;", TtmlNode.ATTR_ID, "I", "getId", "()I", "isUnmergedLeafNode$ui_release", "isUnmergedLeafNode", "Landroidx/compose/ui/layout/LayoutInfo;", "getLayoutInfo", "()Landroidx/compose/ui/layout/LayoutInfo;", "layoutInfo", "Landroidx/compose/ui/node/RootForTest;", "getRoot", "()Landroidx/compose/ui/node/RootForTest;", "root", "Landroidx/compose/ui/geometry/Rect;", "getTouchBoundsInRoot", "()Landroidx/compose/ui/geometry/Rect;", "touchBoundsInRoot", "Landroidx/compose/ui/unit/IntSize;", "getSize-YbymL2g", "()J", ContentDisposition.Parameters.Size, "getBoundsInRoot", "boundsInRoot", "Landroidx/compose/ui/geometry/Offset;", "getPositionInRoot-F1C5BW0", "positionInRoot", "getBoundsInWindow", "boundsInWindow", "getPositionInWindow-F1C5BW0", "positionInWindow", "getBoundsInParent$ui_release", "boundsInParent", "isTransparent$ui_release", "isTransparent", "getConfig", "config", "()Ljava/util/List;", "children", "getReplacedChildren$ui_release", "replacedChildren", "isRoot", "getParent", "parent", "isMergingSemanticsOfDescendants", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SemanticsNode {
    public static final int $stable = 8;
    private SemanticsNode fakeNodeParent;
    private final int id;
    private boolean isFake;
    private final LayoutNode layoutNode;
    private final boolean mergingEnabled;
    private final Modifier.Node outerSemanticsNode;
    private final SemanticsConfiguration unmergedConfig;

    public SemanticsNode(Modifier.Node node, boolean z, LayoutNode layoutNode, SemanticsConfiguration semanticsConfiguration) {
        this.outerSemanticsNode = node;
        this.mergingEnabled = z;
        this.layoutNode = layoutNode;
        this.unmergedConfig = semanticsConfiguration;
        this.id = layoutNode.getSemanticsId();
    }

    private final void emitFakeNodes(List<SemanticsNode> unmergedChildren) {
        Role role = SemanticsNodeKt.getRole(this);
        if (role != null && this.unmergedConfig.getIsMergingSemanticsOfDescendants() && !unmergedChildren.isEmpty()) {
            unmergedChildren.add(m5030fakeSemanticsNodeypyhhiA(role, new SemanticsNode$emitFakeNodes$fakeNode$1(role)));
        }
        SemanticsConfiguration semanticsConfiguration = this.unmergedConfig;
        SemanticsProperties semanticsProperties = SemanticsProperties.INSTANCE;
        if (semanticsConfiguration.contains(semanticsProperties.getContentDescription()) && !unmergedChildren.isEmpty() && this.unmergedConfig.getIsMergingSemanticsOfDescendants()) {
            List list = (List) SemanticsConfigurationKt.getOrNull(this.unmergedConfig, semanticsProperties.getContentDescription());
            String str = list != null ? (String) x.o0(list) : null;
            if (str != null) {
                unmergedChildren.add(0, m5030fakeSemanticsNodeypyhhiA(null, new SemanticsNode$emitFakeNodes$fakeNode$2(str)));
            }
        }
    }

    /* JADX INFO: renamed from: fakeSemanticsNode-ypyhhiA, reason: not valid java name */
    private final SemanticsNode m5030fakeSemanticsNodeypyhhiA(Role role, l<? super SemanticsPropertyReceiver, t0> properties) {
        SemanticsConfiguration semanticsConfiguration = new SemanticsConfiguration();
        semanticsConfiguration.setMergingSemanticsOfDescendants(false);
        semanticsConfiguration.setClearingSemantics(false);
        properties.invoke(semanticsConfiguration);
        SemanticsNode semanticsNode = new SemanticsNode(new SemanticsNode$fakeSemanticsNode$fakeNode$1(properties), false, new LayoutNode(true, role != null ? SemanticsNodeKt.roleFakeNodeId(this) : SemanticsNodeKt.contentDescriptionFakeNodeId(this)), semanticsConfiguration);
        semanticsNode.isFake = true;
        semanticsNode.fakeNodeParent = this;
        return semanticsNode;
    }

    private final void fillOneLayerOfSemanticsWrappers(LayoutNode layoutNode, List<SemanticsNode> list) {
        MutableVector<LayoutNode> zSortedChildren = layoutNode.getZSortedChildren();
        int size = zSortedChildren.getSize();
        if (size > 0) {
            LayoutNode[] content = zSortedChildren.getContent();
            int i10 = 0;
            do {
                LayoutNode layoutNode2 = content[i10];
                if (layoutNode2.isAttached()) {
                    if (layoutNode2.getNodes().m4800hasH91voCI$ui_release(NodeKind.m4843constructorimpl(8))) {
                        list.add(SemanticsNodeKt.SemanticsNode(layoutNode2, this.mergingEnabled));
                    } else {
                        fillOneLayerOfSemanticsWrappers(layoutNode2, list);
                    }
                }
                i10++;
            } while (i10 < size);
        }
    }

    private final List<SemanticsNode> findOneLayerOfMergingSemanticsNodes(List<SemanticsNode> list) {
        List listUnmergedChildren$ui_release$default = unmergedChildren$ui_release$default(this, false, 1, null);
        int size = listUnmergedChildren$ui_release$default.size();
        for (int i10 = 0; i10 < size; i10++) {
            SemanticsNode semanticsNode = (SemanticsNode) listUnmergedChildren$ui_release$default.get(i10);
            if (semanticsNode.isMergingSemanticsOfDescendants()) {
                list.add(semanticsNode);
            } else if (!semanticsNode.unmergedConfig.getIsClearingSemantics()) {
                semanticsNode.findOneLayerOfMergingSemanticsNodes(list);
            }
        }
        return list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ List findOneLayerOfMergingSemanticsNodes$default(SemanticsNode semanticsNode, List list, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            list = new ArrayList();
        }
        return semanticsNode.findOneLayerOfMergingSemanticsNodes(list);
    }

    private final boolean isMergingSemanticsOfDescendants() {
        return this.mergingEnabled && this.unmergedConfig.getIsMergingSemanticsOfDescendants();
    }

    private final void mergeConfig(SemanticsConfiguration mergedConfig) {
        if (this.unmergedConfig.getIsClearingSemantics()) {
            return;
        }
        List listUnmergedChildren$ui_release$default = unmergedChildren$ui_release$default(this, false, 1, null);
        int size = listUnmergedChildren$ui_release$default.size();
        for (int i10 = 0; i10 < size; i10++) {
            SemanticsNode semanticsNode = (SemanticsNode) listUnmergedChildren$ui_release$default.get(i10);
            if (!semanticsNode.isMergingSemanticsOfDescendants()) {
                mergedConfig.mergeChild$ui_release(semanticsNode.unmergedConfig);
                semanticsNode.mergeConfig(mergedConfig);
            }
        }
    }

    public static /* synthetic */ List unmergedChildren$ui_release$default(SemanticsNode semanticsNode, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        return semanticsNode.unmergedChildren$ui_release(z);
    }

    public final SemanticsNode copyWithMergingEnabled$ui_release() {
        return new SemanticsNode(this.outerSemanticsNode, true, this.layoutNode, this.unmergedConfig);
    }

    public final NodeCoordinator findCoordinatorToGetBounds$ui_release() {
        if (this.isFake) {
            SemanticsNode parent = getParent();
            if (parent != null) {
                return parent.findCoordinatorToGetBounds$ui_release();
            }
            return null;
        }
        DelegatableNode outerMergingSemantics = SemanticsNodeKt.getOuterMergingSemantics(this.layoutNode);
        if (outerMergingSemantics == null) {
            outerMergingSemantics = this.outerSemanticsNode;
        }
        return DelegatableNodeKt.m4726requireCoordinator64DMado(outerMergingSemantics, NodeKind.m4843constructorimpl(8));
    }

    public final int getAlignmentLinePosition(AlignmentLine alignmentLine) {
        NodeCoordinator nodeCoordinatorFindCoordinatorToGetBounds$ui_release = findCoordinatorToGetBounds$ui_release();
        if (nodeCoordinatorFindCoordinatorToGetBounds$ui_release != null) {
            return nodeCoordinatorFindCoordinatorToGetBounds$ui_release.get(alignmentLine);
        }
        return Integer.MIN_VALUE;
    }

    public final Rect getBoundsInParent$ui_release() {
        LayoutCoordinates coordinates;
        SemanticsNode parent = getParent();
        if (parent == null) {
            return Rect.INSTANCE.getZero();
        }
        NodeCoordinator nodeCoordinatorFindCoordinatorToGetBounds$ui_release = findCoordinatorToGetBounds$ui_release();
        if (nodeCoordinatorFindCoordinatorToGetBounds$ui_release != null) {
            if (!nodeCoordinatorFindCoordinatorToGetBounds$ui_release.isAttached()) {
                nodeCoordinatorFindCoordinatorToGetBounds$ui_release = null;
            }
            if (nodeCoordinatorFindCoordinatorToGetBounds$ui_release != null && (coordinates = nodeCoordinatorFindCoordinatorToGetBounds$ui_release.getCoordinates()) != null) {
                return c.c(DelegatableNodeKt.m4726requireCoordinator64DMado(parent.outerSemanticsNode, NodeKind.m4843constructorimpl(8)), coordinates, false, 2, null);
            }
        }
        return Rect.INSTANCE.getZero();
    }

    public final Rect getBoundsInRoot() {
        Rect rectBoundsInRoot;
        NodeCoordinator nodeCoordinatorFindCoordinatorToGetBounds$ui_release = findCoordinatorToGetBounds$ui_release();
        if (nodeCoordinatorFindCoordinatorToGetBounds$ui_release != null) {
            if (!nodeCoordinatorFindCoordinatorToGetBounds$ui_release.isAttached()) {
                nodeCoordinatorFindCoordinatorToGetBounds$ui_release = null;
            }
            if (nodeCoordinatorFindCoordinatorToGetBounds$ui_release != null && (rectBoundsInRoot = LayoutCoordinatesKt.boundsInRoot(nodeCoordinatorFindCoordinatorToGetBounds$ui_release)) != null) {
                return rectBoundsInRoot;
            }
        }
        return Rect.INSTANCE.getZero();
    }

    public final Rect getBoundsInWindow() {
        Rect rectBoundsInWindow;
        NodeCoordinator nodeCoordinatorFindCoordinatorToGetBounds$ui_release = findCoordinatorToGetBounds$ui_release();
        if (nodeCoordinatorFindCoordinatorToGetBounds$ui_release != null) {
            if (!nodeCoordinatorFindCoordinatorToGetBounds$ui_release.isAttached()) {
                nodeCoordinatorFindCoordinatorToGetBounds$ui_release = null;
            }
            if (nodeCoordinatorFindCoordinatorToGetBounds$ui_release != null && (rectBoundsInWindow = LayoutCoordinatesKt.boundsInWindow(nodeCoordinatorFindCoordinatorToGetBounds$ui_release)) != null) {
                return rectBoundsInWindow;
            }
        }
        return Rect.INSTANCE.getZero();
    }

    public final List<SemanticsNode> getChildren() {
        return getChildren(!this.mergingEnabled, false);
    }

    public final SemanticsConfiguration getConfig() {
        if (!isMergingSemanticsOfDescendants()) {
            return this.unmergedConfig;
        }
        SemanticsConfiguration semanticsConfigurationCopy = this.unmergedConfig.copy();
        mergeConfig(semanticsConfigurationCopy);
        return semanticsConfigurationCopy;
    }

    public final int getId() {
        return this.id;
    }

    public final LayoutInfo getLayoutInfo() {
        return this.layoutNode;
    }

    /* JADX INFO: renamed from: getLayoutNode$ui_release, reason: from getter */
    public final LayoutNode getLayoutNode() {
        return this.layoutNode;
    }

    public final boolean getMergingEnabled() {
        return this.mergingEnabled;
    }

    /* JADX INFO: renamed from: getOuterSemanticsNode$ui_release, reason: from getter */
    public final Modifier.Node getOuterSemanticsNode() {
        return this.outerSemanticsNode;
    }

    public final SemanticsNode getParent() {
        SemanticsNode semanticsNode = this.fakeNodeParent;
        if (semanticsNode != null) {
            return semanticsNode;
        }
        LayoutNode layoutNodeFindClosestParentNode = this.mergingEnabled ? SemanticsNodeKt.findClosestParentNode(this.layoutNode, SemanticsNode$parent$1.INSTANCE) : null;
        if (layoutNodeFindClosestParentNode == null) {
            layoutNodeFindClosestParentNode = SemanticsNodeKt.findClosestParentNode(this.layoutNode, SemanticsNode$parent$2.INSTANCE);
        }
        if (layoutNodeFindClosestParentNode == null) {
            return null;
        }
        return SemanticsNodeKt.SemanticsNode(layoutNodeFindClosestParentNode, this.mergingEnabled);
    }

    /* JADX INFO: renamed from: getPositionInRoot-F1C5BW0, reason: not valid java name */
    public final long m5031getPositionInRootF1C5BW0() {
        NodeCoordinator nodeCoordinatorFindCoordinatorToGetBounds$ui_release = findCoordinatorToGetBounds$ui_release();
        if (nodeCoordinatorFindCoordinatorToGetBounds$ui_release != null) {
            if (!nodeCoordinatorFindCoordinatorToGetBounds$ui_release.isAttached()) {
                nodeCoordinatorFindCoordinatorToGetBounds$ui_release = null;
            }
            if (nodeCoordinatorFindCoordinatorToGetBounds$ui_release != null) {
                return LayoutCoordinatesKt.positionInRoot(nodeCoordinatorFindCoordinatorToGetBounds$ui_release);
            }
        }
        return Offset.INSTANCE.m3252getZeroF1C5BW0();
    }

    /* JADX INFO: renamed from: getPositionInWindow-F1C5BW0, reason: not valid java name */
    public final long m5032getPositionInWindowF1C5BW0() {
        NodeCoordinator nodeCoordinatorFindCoordinatorToGetBounds$ui_release = findCoordinatorToGetBounds$ui_release();
        if (nodeCoordinatorFindCoordinatorToGetBounds$ui_release != null) {
            if (!nodeCoordinatorFindCoordinatorToGetBounds$ui_release.isAttached()) {
                nodeCoordinatorFindCoordinatorToGetBounds$ui_release = null;
            }
            if (nodeCoordinatorFindCoordinatorToGetBounds$ui_release != null) {
                return LayoutCoordinatesKt.positionInWindow(nodeCoordinatorFindCoordinatorToGetBounds$ui_release);
            }
        }
        return Offset.INSTANCE.m3252getZeroF1C5BW0();
    }

    public final List<SemanticsNode> getReplacedChildren$ui_release() {
        return getChildren(false, true);
    }

    public final RootForTest getRoot() {
        Owner owner = this.layoutNode.getOwner();
        if (owner != null) {
            return owner.getRootForTest();
        }
        return null;
    }

    /* JADX INFO: renamed from: getSize-YbymL2g, reason: not valid java name */
    public final long m5033getSizeYbymL2g() {
        NodeCoordinator nodeCoordinatorFindCoordinatorToGetBounds$ui_release = findCoordinatorToGetBounds$ui_release();
        return nodeCoordinatorFindCoordinatorToGetBounds$ui_release != null ? nodeCoordinatorFindCoordinatorToGetBounds$ui_release.mo4638getSizeYbymL2g() : IntSize.INSTANCE.m5849getZeroYbymL2g();
    }

    public final Rect getTouchBoundsInRoot() {
        DelegatableNode outerMergingSemantics;
        if (!this.unmergedConfig.getIsMergingSemanticsOfDescendants() || (outerMergingSemantics = SemanticsNodeKt.getOuterMergingSemantics(this.layoutNode)) == null) {
            outerMergingSemantics = this.outerSemanticsNode;
        }
        return SemanticsModifierNodeKt.touchBoundsInRoot(outerMergingSemantics.getNode(), SemanticsModifierNodeKt.getUseMinimumTouchTarget(this.unmergedConfig));
    }

    /* JADX INFO: renamed from: getUnmergedConfig$ui_release, reason: from getter */
    public final SemanticsConfiguration getUnmergedConfig() {
        return this.unmergedConfig;
    }

    /* JADX INFO: renamed from: isFake$ui_release, reason: from getter */
    public final boolean getIsFake() {
        return this.isFake;
    }

    public final boolean isRoot() {
        return getParent() == null;
    }

    public final boolean isTransparent$ui_release() {
        NodeCoordinator nodeCoordinatorFindCoordinatorToGetBounds$ui_release = findCoordinatorToGetBounds$ui_release();
        if (nodeCoordinatorFindCoordinatorToGetBounds$ui_release != null) {
            return nodeCoordinatorFindCoordinatorToGetBounds$ui_release.isTransparent();
        }
        return false;
    }

    public final boolean isUnmergedLeafNode$ui_release() {
        return !this.isFake && getReplacedChildren$ui_release().isEmpty() && SemanticsNodeKt.findClosestParentNode(this.layoutNode, SemanticsNode$isUnmergedLeafNode$1.INSTANCE) == null;
    }

    public final void setFake$ui_release(boolean z) {
        this.isFake = z;
    }

    public final List<SemanticsNode> unmergedChildren$ui_release(boolean includeFakeNodes) {
        if (this.isFake) {
            return z.f19728i;
        }
        ArrayList arrayList = new ArrayList();
        fillOneLayerOfSemanticsWrappers(this.layoutNode, arrayList);
        if (includeFakeNodes) {
            emitFakeNodes(arrayList);
        }
        return arrayList;
    }

    private final List<SemanticsNode> getChildren(boolean includeReplacedSemantics, boolean includeFakeNodes) {
        if (!includeReplacedSemantics && this.unmergedConfig.getIsClearingSemantics()) {
            return z.f19728i;
        }
        if (isMergingSemanticsOfDescendants()) {
            return findOneLayerOfMergingSemanticsNodes$default(this, null, 1, null);
        }
        return unmergedChildren$ui_release(includeFakeNodes);
    }
}
