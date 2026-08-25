package androidx.compose.ui.platform;

import android.graphics.Region;
import android.view.View;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutInfo;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.semantics.AccessibilityAction;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsConfigurationKt;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.semantics.SemanticsOwner;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.util.ListUtilsKt;
import androidx.compose.ui.viewinterop.AndroidViewHolder;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0007\n\u0002\b\u0006\u001a)\u0010\u0004\u001a\u0004\u0018\u00010\u0000*\u00020\u00002\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00020\u0001H\u0002¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0013\u0010\u0007\u001a\u00020\u0002*\u00020\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\b\u001a\u0013\u0010\t\u001a\u00020\u0002*\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\b\u001a\u001b\u0010\f\u001a\u00020\u0002*\u00020\u00062\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\f\u0010\r\u001a\u0013\u0010\u000e\u001a\u00020\u0002*\u00020\u0006H\u0002¢\u0006\u0004\b\u000e\u0010\b\u001a\u0015\u0010\u0010\u001a\u0004\u0018\u00010\u000f*\u00020\u0006H\u0002¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u0013\u0010\u0012\u001a\u00020\u0002*\u00020\u0006H\u0002¢\u0006\u0004\b\u0012\u0010\b\u001a!\u0010\u0016\u001a\u00020\u0002*\u0006\u0012\u0002\b\u00030\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014H\u0002¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u001f\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0\u0019*\u00020\u0018H\u0002¢\u0006\u0004\b\u001c\u0010\u001d\u001a#\u0010!\u001a\u0004\u0018\u00010\u001f*\b\u0012\u0004\u0012\u00020\u001f0\u001e2\u0006\u0010 \u001a\u00020\u001aH\u0002¢\u0006\u0004\b!\u0010\"\u001a\u0018\u0010&\u001a\u0004\u0018\u00010\u000f*\u00020#H\u0002ø\u0001\u0000¢\u0006\u0004\b$\u0010%\u001a\u001d\u0010)\u001a\u0004\u0018\u00010(*\u00020'2\u0006\u0010 \u001a\u00020\u001aH\u0000¢\u0006\u0004\b)\u0010*\u001a\u001b\u0010,\u001a\u00020\u0002*\u00020\u00002\u0006\u0010+\u001a\u00020\u0000H\u0002¢\u0006\u0004\b,\u0010-\"\u0014\u0010/\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u00100\"0\u00102\u001a\u00020\u00022\u0006\u00101\u001a\u00020\u00028G@GX\u0087\u000e¢\u0006\u0018\n\u0004\b2\u00103\u0012\u0004\b8\u00109\u001a\u0004\b4\u00105\"\u0004\b6\u00107\"\u001e\u0010:\u001a\u00020\u0002*\u00020\u00068BX\u0082\u0004¢\u0006\f\u0012\u0004\b;\u0010<\u001a\u0004\b:\u0010\b\"\u0019\u0010=\u001a\u00020\u0002*\u00020\u00068Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b=\u0010\b\"\u0019\u0010>\u001a\u00020\u0002*\u00020\u00068Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b>\u0010\b\"\u0019\u0010?\u001a\u00020\u0002*\u00020\u00068Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b?\u0010\b\"\u0019\u0010@\u001a\u00020\u0002*\u00020\u00068Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b@\u0010\b\"\u0019\u0010D\u001a\u00020A*\u00020\u00068Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\bB\u0010C\"\u001a\u0010F\u001a\u0004\u0018\u00010\u000f*\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bE\u0010\u0011\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006G"}, d2 = {"Landroidx/compose/ui/node/LayoutNode;", "Lkotlin/Function1;", "", "selector", "findClosestParentNode", "(Landroidx/compose/ui/node/LayoutNode;Lr7/l;)Landroidx/compose/ui/node/LayoutNode;", "Landroidx/compose/ui/semantics/SemanticsNode;", "enabled", "(Landroidx/compose/ui/semantics/SemanticsNode;)Z", "isImportantForAccessibility", "Landroidx/compose/ui/semantics/SemanticsConfiguration;", "oldConfig", "propertiesDeleted", "(Landroidx/compose/ui/semantics/SemanticsNode;Landroidx/compose/ui/semantics/SemanticsConfiguration;)Z", "hasPaneTitle", "", "getTextForTranslation", "(Landroidx/compose/ui/semantics/SemanticsNode;)Ljava/lang/String;", "excludeLineAndPageGranularities", "Landroidx/compose/ui/semantics/AccessibilityAction;", "", "other", "accessibilityEquals", "(Landroidx/compose/ui/semantics/AccessibilityAction;Ljava/lang/Object;)Z", "Landroidx/compose/ui/semantics/SemanticsOwner;", "", "", "Landroidx/compose/ui/platform/SemanticsNodeWithAdjustedBounds;", "getAllUncoveredSemanticsNodesToMap", "(Landroidx/compose/ui/semantics/SemanticsOwner;)Ljava/util/Map;", "", "Landroidx/compose/ui/platform/ScrollObservationScope;", TtmlNode.ATTR_ID, "findById", "(Ljava/util/List;I)Landroidx/compose/ui/platform/ScrollObservationScope;", "Landroidx/compose/ui/semantics/Role;", "toLegacyClassName-V4PA4sw", "(I)Ljava/lang/String;", "toLegacyClassName", "Landroidx/compose/ui/platform/AndroidViewsHandler;", "Landroid/view/View;", "semanticsIdToView", "(Landroidx/compose/ui/platform/AndroidViewsHandler;I)Landroid/view/View;", "node", "isAncestorOf", "(Landroidx/compose/ui/node/LayoutNode;Landroidx/compose/ui/node/LayoutNode;)Z", "Landroidx/compose/ui/geometry/Rect;", "DefaultFakeNodeBounds", "Landroidx/compose/ui/geometry/Rect;", "<set-?>", "DisableContentCapture", "Z", "getDisableContentCapture", "()Z", "setDisableContentCapture", "(Z)V", "getDisableContentCapture$annotations", "()V", "isVisible", "isVisible$annotations", "(Landroidx/compose/ui/semantics/SemanticsNode;)V", "isPassword", "isTextField", "isRtl", "isTraversalGroup", "", "getTraversalIndex", "(Landroidx/compose/ui/semantics/SemanticsNode;)F", "traversalIndex", "getInfoContentDescriptionOrNull", "infoContentDescriptionOrNull", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidComposeViewAccessibilityDelegateCompat_androidKt {
    private static final Rect DefaultFakeNodeBounds = new Rect(0.0f, 0.0f, 10.0f, 10.0f);
    private static boolean DisableContentCapture;

    /* JADX INFO: renamed from: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat_androidKt$isTraversalGroup$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends kotlin.jvm.internal.r implements r7.a<Boolean> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(0);
        }

        @Override // r7.a
        public final Boolean invoke() {
            return Boolean.FALSE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean accessibilityEquals(AccessibilityAction<?> accessibilityAction, Object obj) {
        if (accessibilityAction == obj) {
            return true;
        }
        if (!(obj instanceof AccessibilityAction)) {
            return false;
        }
        AccessibilityAction accessibilityAction2 = (AccessibilityAction) obj;
        if (!kotlin.jvm.internal.p.a(accessibilityAction.getLabel(), accessibilityAction2.getLabel())) {
            return false;
        }
        if (accessibilityAction.getAction() != null || accessibilityAction2.getAction() == null) {
            return accessibilityAction.getAction() == null || accessibilityAction2.getAction() != null;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean enabled(SemanticsNode semanticsNode) {
        return SemanticsConfigurationKt.getOrNull(semanticsNode.getConfig(), SemanticsProperties.INSTANCE.getDisabled()) == null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean excludeLineAndPageGranularities(SemanticsNode semanticsNode) {
        if (semanticsNode.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getSetText()) && !kotlin.jvm.internal.p.a(SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getFocused()), Boolean.TRUE)) {
            return true;
        }
        LayoutNode layoutNodeFindClosestParentNode = findClosestParentNode(semanticsNode.getLayoutNode(), AndroidComposeViewAccessibilityDelegateCompat_androidKt$excludeLineAndPageGranularities$ancestor$1.INSTANCE);
        if (layoutNodeFindClosestParentNode != null) {
            SemanticsConfiguration collapsedSemantics$ui_release = layoutNodeFindClosestParentNode.getCollapsedSemantics$ui_release();
            if (!(collapsedSemantics$ui_release != null ? kotlin.jvm.internal.p.a(SemanticsConfigurationKt.getOrNull(collapsedSemantics$ui_release, SemanticsProperties.INSTANCE.getFocused()), Boolean.TRUE) : false)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ScrollObservationScope findById(List<ScrollObservationScope> list, int i10) {
        int size = list.size();
        for (int i11 = 0; i11 < size; i11++) {
            if (list.get(i11).getSemanticsNodeId() == i10) {
                return list.get(i11);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LayoutNode findClosestParentNode(LayoutNode layoutNode, r7.l<? super LayoutNode, Boolean> lVar) {
        for (LayoutNode parent$ui_release = layoutNode.getParent$ui_release(); parent$ui_release != null; parent$ui_release = parent$ui_release.getParent$ui_release()) {
            if (((Boolean) lVar.invoke(parent$ui_release)).booleanValue()) {
                return parent$ui_release;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Map<Integer, SemanticsNodeWithAdjustedBounds> getAllUncoveredSemanticsNodesToMap(SemanticsOwner semanticsOwner) {
        SemanticsNode unmergedRootSemanticsNode = semanticsOwner.getUnmergedRootSemanticsNode();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (unmergedRootSemanticsNode.getLayoutNode().isPlaced() && unmergedRootSemanticsNode.getLayoutNode().isAttached()) {
            Rect boundsInRoot = unmergedRootSemanticsNode.getBoundsInRoot();
            getAllUncoveredSemanticsNodesToMap$findAllSemanticNodesRecursive(new Region(t7.a.M(boundsInRoot.getLeft()), t7.a.M(boundsInRoot.getTop()), t7.a.M(boundsInRoot.getRight()), t7.a.M(boundsInRoot.getBottom())), unmergedRootSemanticsNode, linkedHashMap, unmergedRootSemanticsNode, new Region());
        }
        return linkedHashMap;
    }

    private static final void getAllUncoveredSemanticsNodesToMap$findAllSemanticNodesRecursive(Region region, SemanticsNode semanticsNode, Map<Integer, SemanticsNodeWithAdjustedBounds> map, SemanticsNode semanticsNode2, Region region2) {
        LayoutInfo layoutInfo;
        boolean z = (semanticsNode2.getLayoutNode().isPlaced() && semanticsNode2.getLayoutNode().isAttached()) ? false : true;
        if (!region.isEmpty() || semanticsNode2.getId() == semanticsNode.getId()) {
            if (!z || semanticsNode2.getIsFake()) {
                Rect touchBoundsInRoot = semanticsNode2.getTouchBoundsInRoot();
                int iM = t7.a.M(touchBoundsInRoot.getLeft());
                int iM2 = t7.a.M(touchBoundsInRoot.getTop());
                int iM3 = t7.a.M(touchBoundsInRoot.getRight());
                int iM4 = t7.a.M(touchBoundsInRoot.getBottom());
                region2.set(iM, iM2, iM3, iM4);
                int id = semanticsNode2.getId() == semanticsNode.getId() ? -1 : semanticsNode2.getId();
                if (!region2.op(region, Region.Op.INTERSECT)) {
                    if (semanticsNode2.getIsFake()) {
                        SemanticsNode parent = semanticsNode2.getParent();
                        Rect boundsInRoot = (parent == null || (layoutInfo = parent.getLayoutInfo()) == null || !layoutInfo.isPlaced()) ? DefaultFakeNodeBounds : parent.getBoundsInRoot();
                        map.put(Integer.valueOf(id), new SemanticsNodeWithAdjustedBounds(semanticsNode2, new android.graphics.Rect(t7.a.M(boundsInRoot.getLeft()), t7.a.M(boundsInRoot.getTop()), t7.a.M(boundsInRoot.getRight()), t7.a.M(boundsInRoot.getBottom()))));
                        return;
                    } else {
                        if (id == -1) {
                            map.put(Integer.valueOf(id), new SemanticsNodeWithAdjustedBounds(semanticsNode2, region2.getBounds()));
                            return;
                        }
                        return;
                    }
                }
                map.put(Integer.valueOf(id), new SemanticsNodeWithAdjustedBounds(semanticsNode2, region2.getBounds()));
                List<SemanticsNode> replacedChildren$ui_release = semanticsNode2.getReplacedChildren$ui_release();
                for (int size = replacedChildren$ui_release.size() - 1; -1 < size; size--) {
                    getAllUncoveredSemanticsNodesToMap$findAllSemanticNodesRecursive(region, semanticsNode, map, replacedChildren$ui_release.get(size), region2);
                }
                if (isImportantForAccessibility(semanticsNode2)) {
                    region.op(iM, iM2, iM3, iM4, Region.Op.DIFFERENCE);
                }
            }
        }
    }

    public static final boolean getDisableContentCapture() {
        return DisableContentCapture;
    }

    public static /* synthetic */ void getDisableContentCapture$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String getInfoContentDescriptionOrNull(SemanticsNode semanticsNode) {
        List list = (List) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getContentDescription());
        if (list != null) {
            return (String) x.o0(list);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String getTextForTranslation(SemanticsNode semanticsNode) {
        List list = (List) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getText());
        if (list != null) {
            return ListUtilsKt.fastJoinToString$default(list, "\n", null, null, 0, null, null, 62, null);
        }
        return null;
    }

    private static final float getTraversalIndex(SemanticsNode semanticsNode) {
        return ((Number) semanticsNode.getConfig().getOrElse(SemanticsProperties.INSTANCE.getTraversalIndex(), AndroidComposeViewAccessibilityDelegateCompat_androidKt$traversalIndex$1.INSTANCE)).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean hasPaneTitle(SemanticsNode semanticsNode) {
        return semanticsNode.getConfig().contains(SemanticsProperties.INSTANCE.getPaneTitle());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isAncestorOf(LayoutNode layoutNode, LayoutNode layoutNode2) {
        LayoutNode parent$ui_release = layoutNode2.getParent$ui_release();
        if (parent$ui_release == null) {
            return false;
        }
        return parent$ui_release.equals(layoutNode) || isAncestorOf(layoutNode, parent$ui_release);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isImportantForAccessibility(SemanticsNode semanticsNode) {
        return semanticsNode.getUnmergedConfig().getIsMergingSemanticsOfDescendants() || semanticsNode.getUnmergedConfig().containsImportantForAccessibility$ui_release();
    }

    private static final boolean isPassword(SemanticsNode semanticsNode) {
        return semanticsNode.getConfig().contains(SemanticsProperties.INSTANCE.getPassword());
    }

    private static final boolean isRtl(SemanticsNode semanticsNode) {
        return semanticsNode.getLayoutInfo().getLayoutDirection() == LayoutDirection.Rtl;
    }

    private static final boolean isTextField(SemanticsNode semanticsNode) {
        return semanticsNode.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getSetText());
    }

    private static final boolean isTraversalGroup(SemanticsNode semanticsNode) {
        return ((Boolean) semanticsNode.getConfig().getOrElse(SemanticsProperties.INSTANCE.getIsTraversalGroup(), AnonymousClass1.INSTANCE)).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isVisible(SemanticsNode semanticsNode) {
        return (semanticsNode.isTransparent$ui_release() || semanticsNode.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getInvisibleToUser())) ? false : true;
    }

    private static /* synthetic */ void isVisible$annotations(SemanticsNode semanticsNode) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean propertiesDeleted(SemanticsNode semanticsNode, SemanticsConfiguration semanticsConfiguration) {
        Iterator<Map.Entry<? extends SemanticsPropertyKey<?>, ? extends Object>> it = semanticsConfiguration.iterator();
        while (it.hasNext()) {
            if (!semanticsNode.getConfig().contains(it.next().getKey())) {
                return true;
            }
        }
        return false;
    }

    public static final View semanticsIdToView(AndroidViewsHandler androidViewsHandler, int i10) {
        Object next;
        Iterator<T> it = androidViewsHandler.getLayoutNodeToHolder().entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((LayoutNode) ((Map.Entry) next).getKey()).getSemanticsId() == i10) {
                break;
            }
        }
        Map.Entry entry = (Map.Entry) next;
        if (entry != null) {
            return (AndroidViewHolder) entry.getValue();
        }
        return null;
    }

    public static final void setDisableContentCapture(boolean z) {
        DisableContentCapture = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toLegacyClassName-V4PA4sw, reason: not valid java name */
    public static final String m4935toLegacyClassNameV4PA4sw(int i10) {
        Role.Companion companion = Role.INSTANCE;
        if (Role.m5019equalsimpl0(i10, companion.m5023getButtono7Vup1c())) {
            return "android.widget.Button";
        }
        if (Role.m5019equalsimpl0(i10, companion.m5024getCheckboxo7Vup1c())) {
            return "android.widget.CheckBox";
        }
        if (Role.m5019equalsimpl0(i10, companion.m5027getRadioButtono7Vup1c())) {
            return "android.widget.RadioButton";
        }
        if (Role.m5019equalsimpl0(i10, companion.m5026getImageo7Vup1c())) {
            return "android.widget.ImageView";
        }
        if (Role.m5019equalsimpl0(i10, companion.m5025getDropdownListo7Vup1c())) {
            return "android.widget.Spinner";
        }
        return null;
    }
}
