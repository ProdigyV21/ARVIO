package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.NodeKind;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.p;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000e\n\u0002\b\u0007\u001a4\u0010\b\u001a\u0004\u0018\u00010\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00040\u0003H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a2\u0010\u000b\u001a\u00020\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00040\u0003H\u0000ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a:\u0010\u000f\u001a\u00020\u0004*\u00020\u00002\u0006\u0010\f\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00040\u0003H\u0002ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u001a:\u0010\u0011\u001a\u00020\u0004*\u00020\u00002\u0006\u0010\f\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00040\u0003H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u000e\u001a!\u0010\u0016\u001a\u00020\u0015*\u00020\u00122\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00000\u0013H\u0002¢\u0006\u0004\b\u0016\u0010\u0017\u001a.\u0010\u001c\u001a\u0004\u0018\u00010\u0000*\b\u0012\u0004\u0012\u00020\u00000\u00132\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0002\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001a2\u0010\"\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010\u0002\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0004\b \u0010!\u001a2\u0010'\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u00182\u0006\u0010$\u001a\u00020\u00182\u0006\u0010%\u001a\u00020\u00182\u0006\u0010\u0002\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0004\b&\u0010!\u001a\u0013\u0010(\u001a\u00020\u0018*\u00020\u0018H\u0002¢\u0006\u0004\b(\u0010)\u001a\u0013\u0010*\u001a\u00020\u0018*\u00020\u0018H\u0002¢\u0006\u0004\b*\u0010)\u001a\u0013\u0010+\u001a\u00020\u0000*\u00020\u0000H\u0002¢\u0006\u0004\b+\u0010,\"\u001a\u0010.\u001a\u00020-8\u0002X\u0082T¢\u0006\f\n\u0004\b.\u0010/\u0012\u0004\b0\u00101\"\u001a\u00102\u001a\u00020-8\u0002X\u0082T¢\u0006\f\n\u0004\b2\u0010/\u0012\u0004\b3\u00101\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00064"}, d2 = {"Landroidx/compose/ui/focus/FocusTargetNode;", "Landroidx/compose/ui/focus/FocusDirection;", "direction", "Lkotlin/Function1;", "", "onFound", "twoDimensionalFocusSearch--OM-vw8", "(Landroidx/compose/ui/focus/FocusTargetNode;ILr7/l;)Ljava/lang/Boolean;", "twoDimensionalFocusSearch", "findChildCorrespondingToFocusEnter--OM-vw8", "(Landroidx/compose/ui/focus/FocusTargetNode;ILr7/l;)Z", "findChildCorrespondingToFocusEnter", "focusedItem", "generateAndSearchChildren-4C6V_qg", "(Landroidx/compose/ui/focus/FocusTargetNode;Landroidx/compose/ui/focus/FocusTargetNode;ILr7/l;)Z", "generateAndSearchChildren", "searchChildren-4C6V_qg", "searchChildren", "Landroidx/compose/ui/node/DelegatableNode;", "Landroidx/compose/runtime/collection/MutableVector;", "accessibleChildren", "Lx6/t0;", "collectAccessibleChildren", "(Landroidx/compose/ui/node/DelegatableNode;Landroidx/compose/runtime/collection/MutableVector;)V", "Landroidx/compose/ui/geometry/Rect;", "focusRect", "findBestCandidate-4WY_MpI", "(Landroidx/compose/runtime/collection/MutableVector;Landroidx/compose/ui/geometry/Rect;I)Landroidx/compose/ui/focus/FocusTargetNode;", "findBestCandidate", "proposedCandidate", "currentCandidate", "focusedRect", "isBetterCandidate-I7lrPNg", "(Landroidx/compose/ui/geometry/Rect;Landroidx/compose/ui/geometry/Rect;Landroidx/compose/ui/geometry/Rect;I)Z", "isBetterCandidate", "source", "rect1", "rect2", "beamBeats-I7lrPNg", "beamBeats", "topLeft", "(Landroidx/compose/ui/geometry/Rect;)Landroidx/compose/ui/geometry/Rect;", "bottomRight", "activeNode", "(Landroidx/compose/ui/focus/FocusTargetNode;)Landroidx/compose/ui/focus/FocusTargetNode;", "", "InvalidFocusDirection", "Ljava/lang/String;", "getInvalidFocusDirection$annotations", "()V", "NoActiveChild", "getNoActiveChild$annotations", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TwoDimensionalFocusSearchKt {
    private static final String InvalidFocusDirection = "This function should only be used for 2-D focus search";
    private static final String NoActiveChild = "ActiveParent must have a focusedChild";

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FocusStateImpl.values().length];
            try {
                iArr[FocusStateImpl.ActiveParent.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FocusStateImpl.Active.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[FocusStateImpl.Captured.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[FocusStateImpl.Inactive.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private static final FocusTargetNode activeNode(FocusTargetNode focusTargetNode) {
        if (focusTargetNode.getFocusState() != FocusStateImpl.ActiveParent) {
            throw new IllegalStateException("Searching for active node in inactive hierarchy");
        }
        FocusTargetNode focusTargetNodeFindActiveFocusNode = FocusTraversalKt.findActiveFocusNode(focusTargetNode);
        if (focusTargetNodeFindActiveFocusNode != null) {
            return focusTargetNodeFindActiveFocusNode;
        }
        throw new IllegalStateException(NoActiveChild);
    }

    /* JADX INFO: renamed from: beamBeats-I7lrPNg, reason: not valid java name */
    private static final boolean m3195beamBeatsI7lrPNg(Rect rect, Rect rect2, Rect rect3, int i10) {
        if (beamBeats_I7lrPNg$inSourceBeam(rect3, i10, rect) || !beamBeats_I7lrPNg$inSourceBeam(rect2, i10, rect)) {
            return false;
        }
        if (!beamBeats_I7lrPNg$isInDirectionOfSearch(rect3, i10, rect)) {
            return true;
        }
        FocusDirection.Companion companion = FocusDirection.INSTANCE;
        return FocusDirection.m3153equalsimpl0(i10, companion.m3162getLeftdhqQ8s()) || FocusDirection.m3153equalsimpl0(i10, companion.m3165getRightdhqQ8s()) || beamBeats_I7lrPNg$majorAxisDistance$6(rect2, i10, rect) < beamBeats_I7lrPNg$majorAxisDistanceToFarEdge(rect3, i10, rect);
    }

    private static final boolean beamBeats_I7lrPNg$inSourceBeam(Rect rect, int i10, Rect rect2) {
        FocusDirection.Companion companion = FocusDirection.INSTANCE;
        if (FocusDirection.m3153equalsimpl0(i10, companion.m3162getLeftdhqQ8s()) ? true : FocusDirection.m3153equalsimpl0(i10, companion.m3165getRightdhqQ8s())) {
            return rect.getBottom() > rect2.getTop() && rect.getTop() < rect2.getBottom();
        }
        if (FocusDirection.m3153equalsimpl0(i10, companion.m3166getUpdhqQ8s()) ? true : FocusDirection.m3153equalsimpl0(i10, companion.m3159getDowndhqQ8s())) {
            return rect.getRight() > rect2.getLeft() && rect.getLeft() < rect2.getRight();
        }
        throw new IllegalStateException(InvalidFocusDirection);
    }

    private static final boolean beamBeats_I7lrPNg$isInDirectionOfSearch(Rect rect, int i10, Rect rect2) {
        FocusDirection.Companion companion = FocusDirection.INSTANCE;
        if (FocusDirection.m3153equalsimpl0(i10, companion.m3162getLeftdhqQ8s())) {
            return rect2.getLeft() >= rect.getRight();
        }
        if (FocusDirection.m3153equalsimpl0(i10, companion.m3165getRightdhqQ8s())) {
            return rect2.getRight() <= rect.getLeft();
        }
        if (FocusDirection.m3153equalsimpl0(i10, companion.m3166getUpdhqQ8s())) {
            return rect2.getTop() >= rect.getBottom();
        }
        if (FocusDirection.m3153equalsimpl0(i10, companion.m3159getDowndhqQ8s())) {
            return rect2.getBottom() <= rect.getTop();
        }
        throw new IllegalStateException(InvalidFocusDirection);
    }

    private static final float beamBeats_I7lrPNg$majorAxisDistance$6(Rect rect, int i10, Rect rect2) {
        float top;
        float bottom;
        float top2;
        float bottom2;
        float f10;
        FocusDirection.Companion companion = FocusDirection.INSTANCE;
        if (!FocusDirection.m3153equalsimpl0(i10, companion.m3162getLeftdhqQ8s())) {
            if (FocusDirection.m3153equalsimpl0(i10, companion.m3165getRightdhqQ8s())) {
                top = rect.getLeft();
                bottom = rect2.getRight();
            } else if (FocusDirection.m3153equalsimpl0(i10, companion.m3166getUpdhqQ8s())) {
                top2 = rect2.getTop();
                bottom2 = rect.getBottom();
            } else {
                if (!FocusDirection.m3153equalsimpl0(i10, companion.m3159getDowndhqQ8s())) {
                    throw new IllegalStateException(InvalidFocusDirection);
                }
                top = rect.getTop();
                bottom = rect2.getBottom();
            }
            f10 = top - bottom;
            return Math.max(0.0f, f10);
        }
        top2 = rect2.getLeft();
        bottom2 = rect.getRight();
        f10 = top2 - bottom2;
        return Math.max(0.0f, f10);
    }

    private static final float beamBeats_I7lrPNg$majorAxisDistanceToFarEdge(Rect rect, int i10, Rect rect2) {
        float bottom;
        float bottom2;
        float top;
        float top2;
        float f10;
        FocusDirection.Companion companion = FocusDirection.INSTANCE;
        if (!FocusDirection.m3153equalsimpl0(i10, companion.m3162getLeftdhqQ8s())) {
            if (FocusDirection.m3153equalsimpl0(i10, companion.m3165getRightdhqQ8s())) {
                bottom = rect.getRight();
                bottom2 = rect2.getRight();
            } else if (FocusDirection.m3153equalsimpl0(i10, companion.m3166getUpdhqQ8s())) {
                top = rect2.getTop();
                top2 = rect.getTop();
            } else {
                if (!FocusDirection.m3153equalsimpl0(i10, companion.m3159getDowndhqQ8s())) {
                    throw new IllegalStateException(InvalidFocusDirection);
                }
                bottom = rect.getBottom();
                bottom2 = rect2.getBottom();
            }
            f10 = bottom - bottom2;
            return Math.max(1.0f, f10);
        }
        top = rect2.getLeft();
        top2 = rect.getLeft();
        f10 = top - top2;
        return Math.max(1.0f, f10);
    }

    private static final Rect bottomRight(Rect rect) {
        return new Rect(rect.getRight(), rect.getBottom(), rect.getRight(), rect.getBottom());
    }

    private static final void collectAccessibleChildren(DelegatableNode delegatableNode, MutableVector<FocusTargetNode> mutableVector) {
        int iM4843constructorimpl = NodeKind.m4843constructorimpl(1024);
        if (!delegatableNode.getNode().getIsAttached()) {
            throw new IllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child = delegatableNode.getNode().getChild();
        if (child == null) {
            DelegatableNodeKt.addLayoutNodeChildren(mutableVector2, delegatableNode.getNode());
        } else {
            mutableVector2.add(child);
        }
        while (mutableVector2.isNotEmpty()) {
            Modifier.Node nodePop = (Modifier.Node) androidx.compose.material3.d.h(mutableVector2, 1);
            if ((nodePop.getAggregateChildKindSet() & iM4843constructorimpl) == 0) {
                DelegatableNodeKt.addLayoutNodeChildren(mutableVector2, nodePop);
            } else {
                while (true) {
                    if (nodePop == null) {
                        break;
                    }
                    if ((nodePop.getKindSet() & iM4843constructorimpl) != 0) {
                        MutableVector mutableVector3 = null;
                        while (nodePop != null) {
                            if (nodePop instanceof FocusTargetNode) {
                                FocusTargetNode focusTargetNode = (FocusTargetNode) nodePop;
                                if (focusTargetNode.getIsAttached()) {
                                    if (focusTargetNode.fetchFocusProperties$ui_release().getCanFocus()) {
                                        mutableVector.add(focusTargetNode);
                                    } else {
                                        collectAccessibleChildren(focusTargetNode, mutableVector);
                                    }
                                }
                            } else if ((nodePop.getKindSet() & iM4843constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                int i10 = 0;
                                for (Modifier.Node delegate$ui_release = ((DelegatingNode) nodePop).getDelegate(); delegate$ui_release != null; delegate$ui_release = delegate$ui_release.getChild()) {
                                    if ((delegate$ui_release.getKindSet() & iM4843constructorimpl) != 0) {
                                        i10++;
                                        if (i10 == 1) {
                                            nodePop = delegate$ui_release;
                                        } else {
                                            if (mutableVector3 == null) {
                                                mutableVector3 = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (nodePop != null) {
                                                mutableVector3.add(nodePop);
                                                nodePop = null;
                                            }
                                            mutableVector3.add(delegate$ui_release);
                                        }
                                    }
                                }
                                if (i10 == 1) {
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector3);
                        }
                    } else {
                        nodePop = nodePop.getChild();
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: findBestCandidate-4WY_MpI, reason: not valid java name */
    private static final FocusTargetNode m3196findBestCandidate4WY_MpI(MutableVector<FocusTargetNode> mutableVector, Rect rect, int i10) {
        Rect rectTranslate;
        FocusDirection.Companion companion = FocusDirection.INSTANCE;
        if (FocusDirection.m3153equalsimpl0(i10, companion.m3162getLeftdhqQ8s())) {
            rectTranslate = rect.translate(rect.getWidth() + 1, 0.0f);
        } else if (FocusDirection.m3153equalsimpl0(i10, companion.m3165getRightdhqQ8s())) {
            rectTranslate = rect.translate(-(rect.getWidth() + 1), 0.0f);
        } else if (FocusDirection.m3153equalsimpl0(i10, companion.m3166getUpdhqQ8s())) {
            rectTranslate = rect.translate(0.0f, rect.getHeight() + 1);
        } else {
            if (!FocusDirection.m3153equalsimpl0(i10, companion.m3159getDowndhqQ8s())) {
                throw new IllegalStateException(InvalidFocusDirection);
            }
            rectTranslate = rect.translate(0.0f, -(rect.getHeight() + 1));
        }
        int size = mutableVector.getSize();
        FocusTargetNode focusTargetNode = null;
        if (size > 0) {
            FocusTargetNode[] content = mutableVector.getContent();
            int i11 = 0;
            do {
                FocusTargetNode focusTargetNode2 = content[i11];
                if (FocusTraversalKt.isEligibleForFocusSearch(focusTargetNode2)) {
                    Rect rectFocusRect = FocusTraversalKt.focusRect(focusTargetNode2);
                    if (m3199isBetterCandidateI7lrPNg(rectFocusRect, rectTranslate, rect, i10)) {
                        focusTargetNode = focusTargetNode2;
                        rectTranslate = rectFocusRect;
                    }
                }
                i11++;
            } while (i11 < size);
        }
        return focusTargetNode;
    }

    /* JADX INFO: renamed from: findChildCorrespondingToFocusEnter--OM-vw8, reason: not valid java name */
    public static final boolean m3197findChildCorrespondingToFocusEnterOMvw8(FocusTargetNode focusTargetNode, int i10, l<? super FocusTargetNode, Boolean> lVar) {
        Rect rectBottomRight;
        MutableVector mutableVector = new MutableVector(new FocusTargetNode[16], 0);
        collectAccessibleChildren(focusTargetNode, mutableVector);
        if (mutableVector.getSize() <= 1) {
            FocusTargetNode focusTargetNode2 = (FocusTargetNode) (mutableVector.isEmpty() ? null : mutableVector.getContent()[0]);
            if (focusTargetNode2 != null) {
                return ((Boolean) lVar.invoke(focusTargetNode2)).booleanValue();
            }
            return false;
        }
        FocusDirection.Companion companion = FocusDirection.INSTANCE;
        if (FocusDirection.m3153equalsimpl0(i10, companion.m3160getEnterdhqQ8s())) {
            i10 = companion.m3165getRightdhqQ8s();
        }
        if (FocusDirection.m3153equalsimpl0(i10, companion.m3165getRightdhqQ8s()) ? true : FocusDirection.m3153equalsimpl0(i10, companion.m3159getDowndhqQ8s())) {
            rectBottomRight = topLeft(FocusTraversalKt.focusRect(focusTargetNode));
        } else {
            if (!(FocusDirection.m3153equalsimpl0(i10, companion.m3162getLeftdhqQ8s()) ? true : FocusDirection.m3153equalsimpl0(i10, companion.m3166getUpdhqQ8s()))) {
                throw new IllegalStateException(InvalidFocusDirection);
            }
            rectBottomRight = bottomRight(FocusTraversalKt.focusRect(focusTargetNode));
        }
        FocusTargetNode focusTargetNodeM3196findBestCandidate4WY_MpI = m3196findBestCandidate4WY_MpI(mutableVector, rectBottomRight, i10);
        if (focusTargetNodeM3196findBestCandidate4WY_MpI != null) {
            return ((Boolean) lVar.invoke(focusTargetNodeM3196findBestCandidate4WY_MpI)).booleanValue();
        }
        return false;
    }

    /* JADX INFO: renamed from: generateAndSearchChildren-4C6V_qg, reason: not valid java name */
    private static final boolean m3198generateAndSearchChildren4C6V_qg(FocusTargetNode focusTargetNode, FocusTargetNode focusTargetNode2, int i10, l<? super FocusTargetNode, Boolean> lVar) {
        if (m3200searchChildren4C6V_qg(focusTargetNode, focusTargetNode2, i10, lVar)) {
            return true;
        }
        Boolean bool = (Boolean) BeyondBoundsLayoutKt.m3149searchBeyondBoundsOMvw8(focusTargetNode, i10, new TwoDimensionalFocusSearchKt$generateAndSearchChildren$1(focusTargetNode, focusTargetNode2, i10, lVar));
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    private static /* synthetic */ void getInvalidFocusDirection$annotations() {
    }

    private static /* synthetic */ void getNoActiveChild$annotations() {
    }

    /* JADX INFO: renamed from: isBetterCandidate-I7lrPNg, reason: not valid java name */
    private static final boolean m3199isBetterCandidateI7lrPNg(Rect rect, Rect rect2, Rect rect3, int i10) {
        if (!isBetterCandidate_I7lrPNg$isCandidate(rect, i10, rect3)) {
            return false;
        }
        if (isBetterCandidate_I7lrPNg$isCandidate(rect2, i10, rect3) && !m3195beamBeatsI7lrPNg(rect3, rect, rect2, i10)) {
            return !m3195beamBeatsI7lrPNg(rect3, rect2, rect, i10) && isBetterCandidate_I7lrPNg$weightedDistance(i10, rect3, rect) < isBetterCandidate_I7lrPNg$weightedDistance(i10, rect3, rect2);
        }
        return true;
    }

    private static final boolean isBetterCandidate_I7lrPNg$isCandidate(Rect rect, int i10, Rect rect2) {
        FocusDirection.Companion companion = FocusDirection.INSTANCE;
        if (FocusDirection.m3153equalsimpl0(i10, companion.m3162getLeftdhqQ8s())) {
            return (rect2.getRight() > rect.getRight() || rect2.getLeft() >= rect.getRight()) && rect2.getLeft() > rect.getLeft();
        }
        if (FocusDirection.m3153equalsimpl0(i10, companion.m3165getRightdhqQ8s())) {
            return (rect2.getLeft() < rect.getLeft() || rect2.getRight() <= rect.getLeft()) && rect2.getRight() < rect.getRight();
        }
        if (FocusDirection.m3153equalsimpl0(i10, companion.m3166getUpdhqQ8s())) {
            return (rect2.getBottom() > rect.getBottom() || rect2.getTop() >= rect.getBottom()) && rect2.getTop() > rect.getTop();
        }
        if (FocusDirection.m3153equalsimpl0(i10, companion.m3159getDowndhqQ8s())) {
            return (rect2.getTop() < rect.getTop() || rect2.getBottom() <= rect.getTop()) && rect2.getBottom() < rect.getBottom();
        }
        throw new IllegalStateException(InvalidFocusDirection);
    }

    private static final float isBetterCandidate_I7lrPNg$majorAxisDistance(Rect rect, int i10, Rect rect2) {
        float top;
        float bottom;
        float top2;
        float bottom2;
        float f10;
        FocusDirection.Companion companion = FocusDirection.INSTANCE;
        if (!FocusDirection.m3153equalsimpl0(i10, companion.m3162getLeftdhqQ8s())) {
            if (FocusDirection.m3153equalsimpl0(i10, companion.m3165getRightdhqQ8s())) {
                top = rect.getLeft();
                bottom = rect2.getRight();
            } else if (FocusDirection.m3153equalsimpl0(i10, companion.m3166getUpdhqQ8s())) {
                top2 = rect2.getTop();
                bottom2 = rect.getBottom();
            } else {
                if (!FocusDirection.m3153equalsimpl0(i10, companion.m3159getDowndhqQ8s())) {
                    throw new IllegalStateException(InvalidFocusDirection);
                }
                top = rect.getTop();
                bottom = rect2.getBottom();
            }
            f10 = top - bottom;
            return Math.max(0.0f, f10);
        }
        top2 = rect2.getLeft();
        bottom2 = rect.getRight();
        f10 = top2 - bottom2;
        return Math.max(0.0f, f10);
    }

    private static final float isBetterCandidate_I7lrPNg$minorAxisDistance(Rect rect, int i10, Rect rect2) {
        float f10;
        float width;
        float left;
        float width2;
        FocusDirection.Companion companion = FocusDirection.INSTANCE;
        if (FocusDirection.m3153equalsimpl0(i10, companion.m3162getLeftdhqQ8s()) ? true : FocusDirection.m3153equalsimpl0(i10, companion.m3165getRightdhqQ8s())) {
            f10 = 2;
            width = (rect2.getHeight() / f10) + rect2.getTop();
            left = rect.getTop();
            width2 = rect.getHeight();
        } else {
            if (!(FocusDirection.m3153equalsimpl0(i10, companion.m3166getUpdhqQ8s()) ? true : FocusDirection.m3153equalsimpl0(i10, companion.m3159getDowndhqQ8s()))) {
                throw new IllegalStateException(InvalidFocusDirection);
            }
            f10 = 2;
            width = (rect2.getWidth() / f10) + rect2.getLeft();
            left = rect.getLeft();
            width2 = rect.getWidth();
        }
        return width - ((width2 / f10) + left);
    }

    private static final long isBetterCandidate_I7lrPNg$weightedDistance(int i10, Rect rect, Rect rect2) {
        long jAbs = (long) Math.abs(isBetterCandidate_I7lrPNg$majorAxisDistance(rect2, i10, rect));
        long jAbs2 = (long) Math.abs(isBetterCandidate_I7lrPNg$minorAxisDistance(rect2, i10, rect));
        return (jAbs2 * jAbs2) + (((long) 13) * jAbs * jAbs);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: searchChildren-4C6V_qg, reason: not valid java name */
    public static final boolean m3200searchChildren4C6V_qg(FocusTargetNode focusTargetNode, FocusTargetNode focusTargetNode2, int i10, l<? super FocusTargetNode, Boolean> lVar) {
        FocusTargetNode focusTargetNodeM3196findBestCandidate4WY_MpI;
        MutableVector mutableVector = new MutableVector(new FocusTargetNode[16], 0);
        int iM4843constructorimpl = NodeKind.m4843constructorimpl(1024);
        if (!focusTargetNode.getNode().getIsAttached()) {
            throw new IllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child = focusTargetNode.getNode().getChild();
        if (child == null) {
            DelegatableNodeKt.addLayoutNodeChildren(mutableVector2, focusTargetNode.getNode());
        } else {
            mutableVector2.add(child);
        }
        while (mutableVector2.isNotEmpty()) {
            Modifier.Node nodePop = (Modifier.Node) androidx.compose.material3.d.h(mutableVector2, 1);
            if ((nodePop.getAggregateChildKindSet() & iM4843constructorimpl) == 0) {
                DelegatableNodeKt.addLayoutNodeChildren(mutableVector2, nodePop);
            } else {
                while (true) {
                    if (nodePop == null) {
                        break;
                    }
                    if ((nodePop.getKindSet() & iM4843constructorimpl) != 0) {
                        MutableVector mutableVector3 = null;
                        while (nodePop != null) {
                            if (nodePop instanceof FocusTargetNode) {
                                mutableVector.add((FocusTargetNode) nodePop);
                            } else if ((nodePop.getKindSet() & iM4843constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                                int i11 = 0;
                                for (Modifier.Node delegate$ui_release = ((DelegatingNode) nodePop).getDelegate(); delegate$ui_release != null; delegate$ui_release = delegate$ui_release.getChild()) {
                                    if ((delegate$ui_release.getKindSet() & iM4843constructorimpl) != 0) {
                                        i11++;
                                        if (i11 == 1) {
                                            nodePop = delegate$ui_release;
                                        } else {
                                            if (mutableVector3 == null) {
                                                mutableVector3 = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (nodePop != null) {
                                                mutableVector3.add(nodePop);
                                                nodePop = null;
                                            }
                                            mutableVector3.add(delegate$ui_release);
                                        }
                                    }
                                }
                                if (i11 == 1) {
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector3);
                        }
                    } else {
                        nodePop = nodePop.getChild();
                    }
                }
            }
        }
        while (mutableVector.isNotEmpty() && (focusTargetNodeM3196findBestCandidate4WY_MpI = m3196findBestCandidate4WY_MpI(mutableVector, FocusTraversalKt.focusRect(focusTargetNode2), i10)) != null) {
            if (focusTargetNodeM3196findBestCandidate4WY_MpI.fetchFocusProperties$ui_release().getCanFocus()) {
                return ((Boolean) lVar.invoke(focusTargetNodeM3196findBestCandidate4WY_MpI)).booleanValue();
            }
            if (m3198generateAndSearchChildren4C6V_qg(focusTargetNodeM3196findBestCandidate4WY_MpI, focusTargetNode2, i10, lVar)) {
                return true;
            }
            mutableVector.remove(focusTargetNodeM3196findBestCandidate4WY_MpI);
        }
        return false;
    }

    private static final Rect topLeft(Rect rect) {
        return new Rect(rect.getLeft(), rect.getTop(), rect.getLeft(), rect.getTop());
    }

    /* JADX INFO: renamed from: twoDimensionalFocusSearch--OM-vw8, reason: not valid java name */
    public static final Boolean m3201twoDimensionalFocusSearchOMvw8(FocusTargetNode focusTargetNode, int i10, l<? super FocusTargetNode, Boolean> lVar) {
        FocusStateImpl focusState = focusTargetNode.getFocusState();
        int[] iArr = WhenMappings.$EnumSwitchMapping$0;
        int i11 = iArr[focusState.ordinal()];
        if (i11 != 1) {
            if (i11 == 2 || i11 == 3) {
                return Boolean.valueOf(m3197findChildCorrespondingToFocusEnterOMvw8(focusTargetNode, i10, lVar));
            }
            if (i11 == 4) {
                return focusTargetNode.fetchFocusProperties$ui_release().getCanFocus() ? (Boolean) lVar.invoke(focusTargetNode) : Boolean.FALSE;
            }
            throw new NoWhenBranchMatchedException();
        }
        FocusTargetNode activeChild = FocusTraversalKt.getActiveChild(focusTargetNode);
        if (activeChild == null) {
            throw new IllegalStateException(NoActiveChild);
        }
        int i12 = iArr[activeChild.getFocusState().ordinal()];
        if (i12 == 1) {
            Boolean boolM3201twoDimensionalFocusSearchOMvw8 = m3201twoDimensionalFocusSearchOMvw8(activeChild, i10, lVar);
            return !p.a(boolM3201twoDimensionalFocusSearchOMvw8, Boolean.FALSE) ? boolM3201twoDimensionalFocusSearchOMvw8 : Boolean.valueOf(m3198generateAndSearchChildren4C6V_qg(focusTargetNode, activeNode(activeChild), i10, lVar));
        }
        if (i12 == 2 || i12 == 3) {
            return Boolean.valueOf(m3198generateAndSearchChildren4C6V_qg(focusTargetNode, activeChild, i10, lVar));
        }
        if (i12 != 4) {
            throw new NoWhenBranchMatchedException();
        }
        throw new IllegalStateException(NoActiveChild);
    }
}
