package androidx.compose.ui.focus;

import android.view.KeyEvent;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.input.key.KeyInputModifierNode;
import androidx.compose.ui.input.key.SoftKeyboardInterceptionModifierNode;
import androidx.compose.ui.input.rotary.RotaryInputModifierNode;
import androidx.compose.ui.input.rotary.RotaryScrollEvent;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.LayoutDirection;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.f0;
import kotlin.jvm.internal.p;
import r7.l;
import t.x;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B!\u0012\u0018\u0010\u0005\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0004\u0012\u00020\u00040\u0002¢\u0006\u0004\b\u0006\u0010\u0007JY\u0010\u0010\u001a\u00020\u0004\"\n\b\u0000\u0010\t\u0018\u0001*\u00020\b*\u00020\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\n2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00040\u00022\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00040\u0002H\u0082\bø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0012\u001a\u0004\u0018\u00010\u0011*\u00020\bH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u001a\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0014H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u001a\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001aH\u0002ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001f\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u001f\u0010 J\u000f\u0010!\u001a\u00020\u0004H\u0016¢\u0006\u0004\b!\u0010 J\u0017\u0010#\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u0016H\u0016¢\u0006\u0004\b#\u0010$J\u001f\u0010#\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u00162\u0006\u0010%\u001a\u00020\u0016H\u0016¢\u0006\u0004\b#\u0010&J\u001a\u0010(\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0014H\u0016ø\u0001\u0000¢\u0006\u0004\b'\u0010\u0018J\u001a\u0010*\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001aH\u0016ø\u0001\u0000¢\u0006\u0004\b)\u0010\u001dJ\u001a\u0010,\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001aH\u0016ø\u0001\u0000¢\u0006\u0004\b+\u0010\u001dJ\u0017\u0010/\u001a\u00020\u00162\u0006\u0010.\u001a\u00020-H\u0016¢\u0006\u0004\b/\u00100J\u0017\u00103\u001a\u00020\u00042\u0006\u00102\u001a\u000201H\u0016¢\u0006\u0004\b3\u00104J\u0017\u00103\u001a\u00020\u00042\u0006\u00102\u001a\u000205H\u0016¢\u0006\u0004\b3\u00106J\u0017\u00103\u001a\u00020\u00042\u0006\u00102\u001a\u000207H\u0016¢\u0006\u0004\b3\u00108J\u0011\u0010:\u001a\u0004\u0018\u000109H\u0016¢\u0006\u0004\b:\u0010;R\"\u0010<\u001a\u0002018\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b<\u0010=\u001a\u0004\b>\u0010?\"\u0004\b@\u00104R\u0014\u0010B\u001a\u00020A8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bB\u0010CR\u001a\u0010E\u001a\u00020D8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bE\u0010F\u001a\u0004\bG\u0010HR\u001a\u0010J\u001a\u00020I8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bJ\u0010K\u001a\u0004\bL\u0010MR\"\u0010O\u001a\u00020N8\u0016@\u0016X\u0096.¢\u0006\u0012\n\u0004\bO\u0010P\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TR\u0018\u0010V\u001a\u0004\u0018\u00010U8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bV\u0010W\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006X"}, d2 = {"Landroidx/compose/ui/focus/FocusOwnerImpl;", "Landroidx/compose/ui/focus/FocusOwner;", "Lkotlin/Function1;", "Lkotlin/Function0;", "Lx6/t0;", "onRequestApplyChangesListener", "<init>", "(Lr7/l;)V", "Landroidx/compose/ui/node/DelegatableNode;", "T", "Landroidx/compose/ui/node/NodeKind;", LinkHeader.Parameters.Type, "onPreVisit", "onVisit", "traverseAncestors-Y-YKmho", "(Landroidx/compose/ui/node/DelegatableNode;ILr7/l;Lr7/l;)V", "traverseAncestors", "Landroidx/compose/ui/Modifier$Node;", "lastLocalKeyInputNode", "(Landroidx/compose/ui/node/DelegatableNode;)Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/focus/FocusDirection;", "focusDirection", "", "wrapAroundFocus-3ESFkO8", "(I)Z", "wrapAroundFocus", "Landroidx/compose/ui/input/key/KeyEvent;", "keyEvent", "validateKeyEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "validateKeyEvent", "takeFocus", "()V", "releaseFocus", "force", "clearFocus", "(Z)V", "refreshFocusEvents", "(ZZ)V", "moveFocus-3ESFkO8", "moveFocus", "dispatchKeyEvent-ZmokQxo", "dispatchKeyEvent", "dispatchInterceptedSoftKeyboardEvent-ZmokQxo", "dispatchInterceptedSoftKeyboardEvent", "Landroidx/compose/ui/input/rotary/RotaryScrollEvent;", "event", "dispatchRotaryEvent", "(Landroidx/compose/ui/input/rotary/RotaryScrollEvent;)Z", "Landroidx/compose/ui/focus/FocusTargetNode;", "node", "scheduleInvalidation", "(Landroidx/compose/ui/focus/FocusTargetNode;)V", "Landroidx/compose/ui/focus/FocusEventModifierNode;", "(Landroidx/compose/ui/focus/FocusEventModifierNode;)V", "Landroidx/compose/ui/focus/FocusPropertiesModifierNode;", "(Landroidx/compose/ui/focus/FocusPropertiesModifierNode;)V", "Landroidx/compose/ui/geometry/Rect;", "getFocusRect", "()Landroidx/compose/ui/geometry/Rect;", "rootFocusNode", "Landroidx/compose/ui/focus/FocusTargetNode;", "getRootFocusNode$ui_release", "()Landroidx/compose/ui/focus/FocusTargetNode;", "setRootFocusNode$ui_release", "Landroidx/compose/ui/focus/FocusInvalidationManager;", "focusInvalidationManager", "Landroidx/compose/ui/focus/FocusInvalidationManager;", "Landroidx/compose/ui/focus/FocusTransactionManager;", "focusTransactionManager", "Landroidx/compose/ui/focus/FocusTransactionManager;", "getFocusTransactionManager", "()Landroidx/compose/ui/focus/FocusTransactionManager;", "Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/ui/Modifier;", "getModifier", "()Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "setLayoutDirection", "(Landroidx/compose/ui/unit/LayoutDirection;)V", "Lt/x;", "keysCurrentlyDown", "Lt/x;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class FocusOwnerImpl implements FocusOwner {
    public static final int $stable = 8;
    private final FocusInvalidationManager focusInvalidationManager;
    private x keysCurrentlyDown;
    public LayoutDirection layoutDirection;
    private FocusTargetNode rootFocusNode = new FocusTargetNode();
    private final FocusTransactionManager focusTransactionManager = new FocusTransactionManager();
    private final Modifier modifier = new ModifierNodeElement<FocusTargetNode>() { // from class: androidx.compose.ui.focus.FocusOwnerImpl$modifier$1
        @Override // androidx.compose.ui.node.ModifierNodeElement
        public boolean equals(Object other) {
            return other == this;
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public int hashCode() {
            return this.this$0.getRootFocusNode().hashCode();
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public void inspectableProperties(InspectorInfo inspectorInfo) {
            inspectorInfo.setName("RootFocusTarget");
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public void update(FocusTargetNode node) {
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public FocusTargetNode create() {
            return this.this$0.getRootFocusNode();
        }
    };

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[CustomDestinationResult.values().length];
            try {
                iArr[CustomDestinationResult.Redirected.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CustomDestinationResult.Cancelled.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CustomDestinationResult.RedirectCancelled.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[CustomDestinationResult.None.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[FocusStateImpl.values().length];
            try {
                iArr2[FocusStateImpl.Active.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[FocusStateImpl.ActiveParent.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[FocusStateImpl.Captured.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[FocusStateImpl.Inactive.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public FocusOwnerImpl(l<? super r7.a<t0>, t0> lVar) {
        this.focusInvalidationManager = new FocusInvalidationManager(lVar);
    }

    private final Modifier.Node lastLocalKeyInputNode(DelegatableNode delegatableNode) {
        int iM4843constructorimpl = NodeKind.m4843constructorimpl(1024) | NodeKind.m4843constructorimpl(8192);
        if (!delegatableNode.getNode().getIsAttached()) {
            throw new IllegalStateException("visitLocalDescendants called on an unattached node");
        }
        Modifier.Node node = delegatableNode.getNode();
        Modifier.Node node2 = null;
        if ((node.getAggregateChildKindSet() & iM4843constructorimpl) != 0) {
            for (Modifier.Node child = node.getChild(); child != null; child = child.getChild()) {
                if ((child.getKindSet() & iM4843constructorimpl) != 0) {
                    if ((NodeKind.m4843constructorimpl(1024) & child.getKindSet()) != 0) {
                        return node2;
                    }
                    node2 = child;
                }
            }
        }
        return node2;
    }

    /* JADX INFO: renamed from: traverseAncestors-Y-YKmho, reason: not valid java name */
    private final <T extends DelegatableNode> void m3171traverseAncestorsYYKmho(DelegatableNode delegatableNode, int i10, l<? super T, t0> lVar, l<? super T, t0> lVar2) {
        NodeChain nodes;
        if (!delegatableNode.getNode().getIsAttached()) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node parent = delegatableNode.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(delegatableNode);
        while (layoutNodeRequireLayoutNode != null) {
            if ((androidx.compose.material3.d.d(layoutNodeRequireLayoutNode) & i10) != 0) {
                while (parent != null) {
                    if ((parent.getKindSet() & i10) != 0) {
                        p.h();
                        throw null;
                    }
                    parent = parent.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
            parent = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
        if (delegatableNode.getNode() != null) {
            p.h();
            throw null;
        }
        if (delegatableNode.getNode() == null) {
            return;
        }
        p.h();
        throw null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x00a2, code lost:
    
        r31 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x00ac, code lost:
    
        if (((r9 & ((~r9) << 6)) & (-9187201950435737472L)) == 0) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x00ae, code lost:
    
        r3 = r5.b(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x00b4, code lost:
    
        if (r5.f21879e != 0) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00c5, code lost:
    
        if (((r5.f21875a[r3 >> 3] >> ((r3 & 7) << 3)) & 255) != 254) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00c8, code lost:
    
        r3 = r5.f21877c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00ca, code lost:
    
        if (r3 <= 8) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00de, code lost:
    
        if (java.lang.Long.compare((((long) r5.f21878d) * 32) ^ Long.MIN_VALUE, (((long) r3) * 25) ^ Long.MIN_VALUE) > 0) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00e0, code lost:
    
        r5.d(t.i0.b(r5.f21877c));
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00ea, code lost:
    
        r5.d(t.i0.b(r5.f21877c));
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00f3, code lost:
    
        r3 = r5.b(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00f7, code lost:
    
        r28 = r3;
        r5.f21878d++;
        r3 = r5.f21879e;
        r4 = r5.f21875a;
        r6 = r28 >> 3;
        r9 = r4[r6];
        r7 = (r28 & 7) << 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0112, code lost:
    
        if (((r9 >> r7) & 255) != 128) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0114, code lost:
    
        r13 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0116, code lost:
    
        r13 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0118, code lost:
    
        r5.f21879e = r3 - r13;
        r4[r6] = (r9 & (~(255 << r7))) | (r11 << r7);
        r3 = r5.f21877c;
        r6 = ((r28 - 7) & r3) + (r3 & 7);
        r3 = r6 >> 3;
        r6 = (r6 & 7) << 3;
        r4[r3] = (r11 << r6) | (r4[r3] & (~(255 << r6)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x01c7, code lost:
    
        if (((r6 & ((~r6) << 6)) & (-9187201950435737472L)) == 0) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x01c9, code lost:
    
        r12 = -1;
     */
    /* JADX INFO: renamed from: validateKeyEvent-ZmokQxo, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final boolean m3172validateKeyEventZmokQxo(android.view.KeyEvent r38) {
        /*
            Method dump skipped, instruction units count: 522
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusOwnerImpl.m3172validateKeyEventZmokQxo(android.view.KeyEvent):boolean");
    }

    /* JADX INFO: renamed from: wrapAroundFocus-3ESFkO8, reason: not valid java name */
    private final boolean m3173wrapAroundFocus3ESFkO8(int focusDirection) {
        if (this.rootFocusNode.getFocusState().getHasFocus() && !this.rootFocusNode.getFocusState().isFocused()) {
            FocusDirection.Companion companion = FocusDirection.INSTANCE;
            if (FocusDirection.m3153equalsimpl0(focusDirection, companion.m3163getNextdhqQ8s()) ? true : FocusDirection.m3153equalsimpl0(focusDirection, companion.m3164getPreviousdhqQ8s())) {
                clearFocus(false);
                if (this.rootFocusNode.getFocusState().isFocused()) {
                    return mo3168moveFocus3ESFkO8(focusDirection);
                }
                return false;
            }
        }
        return false;
    }

    @Override // androidx.compose.ui.focus.FocusManager
    public void clearFocus(boolean force) {
        clearFocus(force, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v16 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r1v15, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v23, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r1v24, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v25 */
    /* JADX WARN: Type inference failed for: r1v26 */
    /* JADX WARN: Type inference failed for: r1v27 */
    /* JADX WARN: Type inference failed for: r1v28 */
    /* JADX WARN: Type inference failed for: r1v4, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r1v42 */
    /* JADX WARN: Type inference failed for: r1v43 */
    /* JADX WARN: Type inference failed for: r1v44 */
    /* JADX WARN: Type inference failed for: r1v45 */
    /* JADX WARN: Type inference failed for: r1v5, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r1v6, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r1v7, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r9v13 */
    /* JADX WARN: Type inference failed for: r9v14, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v16, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r9v17, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v19 */
    /* JADX WARN: Type inference failed for: r9v20 */
    /* JADX WARN: Type inference failed for: r9v21 */
    /* JADX WARN: Type inference failed for: r9v22 */
    /* JADX WARN: Type inference failed for: r9v23 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7 */
    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: dispatchInterceptedSoftKeyboardEvent-ZmokQxo */
    public boolean mo3169dispatchInterceptedSoftKeyboardEventZmokQxo(KeyEvent keyEvent) {
        SoftKeyboardInterceptionModifierNode softKeyboardInterceptionModifierNode;
        int size;
        NodeChain nodes;
        ?? Pop;
        NodeChain nodes2;
        FocusTargetNode focusTargetNodeFindActiveFocusNode = FocusTraversalKt.findActiveFocusNode(this.rootFocusNode);
        if (focusTargetNodeFindActiveFocusNode != null) {
            int iM4843constructorimpl = NodeKind.m4843constructorimpl(131072);
            if (!focusTargetNodeFindActiveFocusNode.getNode().getIsAttached()) {
                throw new IllegalStateException("visitAncestors called on an unattached node");
            }
            Modifier.Node parent = focusTargetNodeFindActiveFocusNode.getNode().getParent();
            LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetNodeFindActiveFocusNode);
            loop0: while (true) {
                if (layoutNodeRequireLayoutNode == null) {
                    Pop = 0;
                    break;
                }
                if ((androidx.compose.material3.d.d(layoutNodeRequireLayoutNode) & iM4843constructorimpl) != 0) {
                    while (parent != null) {
                        if ((parent.getKindSet() & iM4843constructorimpl) != 0) {
                            MutableVector mutableVector = null;
                            Pop = parent;
                            while (Pop != 0) {
                                if (Pop instanceof SoftKeyboardInterceptionModifierNode) {
                                    break loop0;
                                }
                                if ((Pop.getKindSet() & iM4843constructorimpl) != 0 && (Pop instanceof DelegatingNode)) {
                                    Modifier.Node delegate = ((DelegatingNode) Pop).getDelegate();
                                    int i10 = 0;
                                    Pop = Pop;
                                    while (delegate != null) {
                                        if ((delegate.getKindSet() & iM4843constructorimpl) != 0) {
                                            i10++;
                                            if (i10 == 1) {
                                                Pop = delegate;
                                            } else {
                                                if (mutableVector == null) {
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                if (Pop != 0) {
                                                    mutableVector.add(Pop);
                                                    Pop = 0;
                                                }
                                                mutableVector.add(delegate);
                                            }
                                        }
                                        delegate = delegate.getChild();
                                        Pop = Pop;
                                    }
                                    if (i10 == 1) {
                                    }
                                }
                                Pop = DelegatableNodeKt.pop(mutableVector);
                            }
                        }
                        parent = parent.getParent();
                    }
                }
                layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
                parent = (layoutNodeRequireLayoutNode == null || (nodes2 = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes2.getTail();
            }
            softKeyboardInterceptionModifierNode = (SoftKeyboardInterceptionModifierNode) Pop;
        } else {
            softKeyboardInterceptionModifierNode = null;
        }
        if (softKeyboardInterceptionModifierNode != null) {
            int iM4843constructorimpl2 = NodeKind.m4843constructorimpl(131072);
            if (!softKeyboardInterceptionModifierNode.getNode().getIsAttached()) {
                throw new IllegalStateException("visitAncestors called on an unattached node");
            }
            Modifier.Node parent2 = softKeyboardInterceptionModifierNode.getNode().getParent();
            LayoutNode layoutNodeRequireLayoutNode2 = DelegatableNodeKt.requireLayoutNode(softKeyboardInterceptionModifierNode);
            ArrayList arrayList = null;
            while (layoutNodeRequireLayoutNode2 != null) {
                if ((androidx.compose.material3.d.d(layoutNodeRequireLayoutNode2) & iM4843constructorimpl2) != 0) {
                    while (parent2 != null) {
                        if ((parent2.getKindSet() & iM4843constructorimpl2) != 0) {
                            Modifier.Node nodePop = parent2;
                            MutableVector mutableVector2 = null;
                            while (nodePop != null) {
                                if (nodePop instanceof SoftKeyboardInterceptionModifierNode) {
                                    if (arrayList == null) {
                                        arrayList = new ArrayList();
                                    }
                                    arrayList.add(nodePop);
                                } else if ((nodePop.getKindSet() & iM4843constructorimpl2) != 0 && (nodePop instanceof DelegatingNode)) {
                                    int i11 = 0;
                                    for (Modifier.Node delegate2 = ((DelegatingNode) nodePop).getDelegate(); delegate2 != null; delegate2 = delegate2.getChild()) {
                                        if ((delegate2.getKindSet() & iM4843constructorimpl2) != 0) {
                                            i11++;
                                            if (i11 == 1) {
                                                nodePop = delegate2;
                                            } else {
                                                if (mutableVector2 == null) {
                                                    mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                if (nodePop != null) {
                                                    mutableVector2.add(nodePop);
                                                    nodePop = null;
                                                }
                                                mutableVector2.add(delegate2);
                                            }
                                        }
                                    }
                                    if (i11 == 1) {
                                    }
                                }
                                nodePop = DelegatableNodeKt.pop(mutableVector2);
                            }
                        }
                        parent2 = parent2.getParent();
                    }
                }
                layoutNodeRequireLayoutNode2 = layoutNodeRequireLayoutNode2.getParent$ui_release();
                parent2 = (layoutNodeRequireLayoutNode2 == null || (nodes = layoutNodeRequireLayoutNode2.getNodes()) == null) ? null : nodes.getTail();
            }
            if (arrayList != null && arrayList.size() - 1 >= 0) {
                while (true) {
                    int i12 = size - 1;
                    if (((SoftKeyboardInterceptionModifierNode) arrayList.get(size)).mo4089onPreInterceptKeyBeforeSoftKeyboardZmokQxo(keyEvent)) {
                        return true;
                    }
                    if (i12 < 0) {
                        break;
                    }
                    size = i12;
                }
            }
            ?? node = softKeyboardInterceptionModifierNode.getNode();
            MutableVector mutableVector3 = null;
            while (node != 0) {
                if (node instanceof SoftKeyboardInterceptionModifierNode) {
                    if (((SoftKeyboardInterceptionModifierNode) node).mo4089onPreInterceptKeyBeforeSoftKeyboardZmokQxo(keyEvent)) {
                        return true;
                    }
                } else if ((node.getKindSet() & iM4843constructorimpl2) != 0 && (node instanceof DelegatingNode)) {
                    Modifier.Node delegate3 = ((DelegatingNode) node).getDelegate();
                    int i13 = 0;
                    node = node;
                    while (delegate3 != null) {
                        if ((delegate3.getKindSet() & iM4843constructorimpl2) != 0) {
                            i13++;
                            if (i13 == 1) {
                                node = delegate3;
                            } else {
                                if (mutableVector3 == null) {
                                    mutableVector3 = new MutableVector(new Modifier.Node[16], 0);
                                }
                                if (node != 0) {
                                    mutableVector3.add(node);
                                    node = 0;
                                }
                                mutableVector3.add(delegate3);
                            }
                        }
                        delegate3 = delegate3.getChild();
                        node = node;
                    }
                    if (i13 == 1) {
                    }
                }
                node = DelegatableNodeKt.pop(mutableVector3);
            }
            ?? node2 = softKeyboardInterceptionModifierNode.getNode();
            MutableVector mutableVector4 = null;
            while (node2 != 0) {
                if (node2 instanceof SoftKeyboardInterceptionModifierNode) {
                    if (((SoftKeyboardInterceptionModifierNode) node2).mo4088onInterceptKeyBeforeSoftKeyboardZmokQxo(keyEvent)) {
                        return true;
                    }
                } else if ((node2.getKindSet() & iM4843constructorimpl2) != 0 && (node2 instanceof DelegatingNode)) {
                    Modifier.Node delegate4 = ((DelegatingNode) node2).getDelegate();
                    int i14 = 0;
                    node2 = node2;
                    while (delegate4 != null) {
                        if ((delegate4.getKindSet() & iM4843constructorimpl2) != 0) {
                            i14++;
                            if (i14 == 1) {
                                node2 = delegate4;
                            } else {
                                if (mutableVector4 == null) {
                                    mutableVector4 = new MutableVector(new Modifier.Node[16], 0);
                                }
                                if (node2 != 0) {
                                    mutableVector4.add(node2);
                                    node2 = 0;
                                }
                                mutableVector4.add(delegate4);
                            }
                        }
                        delegate4 = delegate4.getChild();
                        node2 = node2;
                    }
                    if (i14 == 1) {
                    }
                }
                node2 = DelegatableNodeKt.pop(mutableVector4);
            }
            if (arrayList != null) {
                int size2 = arrayList.size();
                for (int i15 = 0; i15 < size2; i15++) {
                    if (((SoftKeyboardInterceptionModifierNode) arrayList.get(i15)).mo4088onInterceptKeyBeforeSoftKeyboardZmokQxo(keyEvent)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v16 */
    /* JADX WARN: Type inference failed for: r2v10, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v11, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v2, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v3, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v20, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r3v21, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v22 */
    /* JADX WARN: Type inference failed for: r3v23 */
    /* JADX WARN: Type inference failed for: r3v24 */
    /* JADX WARN: Type inference failed for: r3v25 */
    /* JADX WARN: Type inference failed for: r3v39 */
    /* JADX WARN: Type inference failed for: r3v4, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r3v40 */
    /* JADX WARN: Type inference failed for: r3v5, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r9v26 */
    /* JADX WARN: Type inference failed for: r9v27 */
    /* JADX WARN: Type inference failed for: r9v33 */
    /* JADX WARN: Type inference failed for: r9v34, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r9v35 */
    /* JADX WARN: Type inference failed for: r9v36, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r9v37, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v38 */
    /* JADX WARN: Type inference failed for: r9v39 */
    /* JADX WARN: Type inference failed for: r9v40 */
    /* JADX WARN: Type inference failed for: r9v41 */
    /* JADX WARN: Type inference failed for: r9v42 */
    /* JADX WARN: Type inference failed for: r9v43 */
    /* JADX WARN: Type inference failed for: r9v8 */
    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: dispatchKeyEvent-ZmokQxo */
    public boolean mo3170dispatchKeyEventZmokQxo(KeyEvent keyEvent) {
        int size;
        NodeChain nodes;
        ?? Pop;
        NodeChain nodes2;
        if (!m3172validateKeyEventZmokQxo(keyEvent)) {
            return false;
        }
        FocusTargetNode focusTargetNodeFindActiveFocusNode = FocusTraversalKt.findActiveFocusNode(this.rootFocusNode);
        if (focusTargetNodeFindActiveFocusNode == null) {
            throw new IllegalStateException("Event can't be processed because we do not have an active focus target.");
        }
        Modifier.Node nodeLastLocalKeyInputNode = lastLocalKeyInputNode(focusTargetNodeFindActiveFocusNode);
        if (nodeLastLocalKeyInputNode == null) {
            int iM4843constructorimpl = NodeKind.m4843constructorimpl(8192);
            if (!focusTargetNodeFindActiveFocusNode.getNode().getIsAttached()) {
                throw new IllegalStateException("visitAncestors called on an unattached node");
            }
            Modifier.Node parent = focusTargetNodeFindActiveFocusNode.getNode().getParent();
            LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetNodeFindActiveFocusNode);
            loop0: while (true) {
                if (layoutNodeRequireLayoutNode == null) {
                    Pop = 0;
                    break;
                }
                if ((androidx.compose.material3.d.d(layoutNodeRequireLayoutNode) & iM4843constructorimpl) != 0) {
                    while (parent != null) {
                        if ((parent.getKindSet() & iM4843constructorimpl) != 0) {
                            MutableVector mutableVector = null;
                            Pop = parent;
                            while (Pop != 0) {
                                if (Pop instanceof KeyInputModifierNode) {
                                    break loop0;
                                }
                                if ((Pop.getKindSet() & iM4843constructorimpl) != 0 && (Pop instanceof DelegatingNode)) {
                                    Modifier.Node delegate = ((DelegatingNode) Pop).getDelegate();
                                    int i10 = 0;
                                    Pop = Pop;
                                    while (delegate != null) {
                                        if ((delegate.getKindSet() & iM4843constructorimpl) != 0) {
                                            i10++;
                                            if (i10 == 1) {
                                                Pop = delegate;
                                            } else {
                                                if (mutableVector == null) {
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                if (Pop != 0) {
                                                    mutableVector.add(Pop);
                                                    Pop = 0;
                                                }
                                                mutableVector.add(delegate);
                                            }
                                        }
                                        delegate = delegate.getChild();
                                        Pop = Pop;
                                    }
                                    if (i10 == 1) {
                                    }
                                }
                                Pop = DelegatableNodeKt.pop(mutableVector);
                            }
                        }
                        parent = parent.getParent();
                    }
                }
                layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
                parent = (layoutNodeRequireLayoutNode == null || (nodes2 = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes2.getTail();
            }
            KeyInputModifierNode keyInputModifierNode = (KeyInputModifierNode) Pop;
            nodeLastLocalKeyInputNode = keyInputModifierNode != null ? keyInputModifierNode.getNode() : null;
        }
        if (nodeLastLocalKeyInputNode != null) {
            int iM4843constructorimpl2 = NodeKind.m4843constructorimpl(8192);
            if (!nodeLastLocalKeyInputNode.getNode().getIsAttached()) {
                throw new IllegalStateException("visitAncestors called on an unattached node");
            }
            Modifier.Node parent2 = nodeLastLocalKeyInputNode.getNode().getParent();
            LayoutNode layoutNodeRequireLayoutNode2 = DelegatableNodeKt.requireLayoutNode(nodeLastLocalKeyInputNode);
            ArrayList arrayList = null;
            while (layoutNodeRequireLayoutNode2 != null) {
                if ((androidx.compose.material3.d.d(layoutNodeRequireLayoutNode2) & iM4843constructorimpl2) != 0) {
                    while (parent2 != null) {
                        if ((parent2.getKindSet() & iM4843constructorimpl2) != 0) {
                            Modifier.Node nodePop = parent2;
                            MutableVector mutableVector2 = null;
                            while (nodePop != null) {
                                if (nodePop instanceof KeyInputModifierNode) {
                                    if (arrayList == null) {
                                        arrayList = new ArrayList();
                                    }
                                    arrayList.add(nodePop);
                                } else if ((nodePop.getKindSet() & iM4843constructorimpl2) != 0 && (nodePop instanceof DelegatingNode)) {
                                    int i11 = 0;
                                    for (Modifier.Node delegate2 = ((DelegatingNode) nodePop).getDelegate(); delegate2 != null; delegate2 = delegate2.getChild()) {
                                        if ((delegate2.getKindSet() & iM4843constructorimpl2) != 0) {
                                            i11++;
                                            if (i11 == 1) {
                                                nodePop = delegate2;
                                            } else {
                                                if (mutableVector2 == null) {
                                                    mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                if (nodePop != null) {
                                                    mutableVector2.add(nodePop);
                                                    nodePop = null;
                                                }
                                                mutableVector2.add(delegate2);
                                            }
                                        }
                                    }
                                    if (i11 == 1) {
                                    }
                                }
                                nodePop = DelegatableNodeKt.pop(mutableVector2);
                            }
                        }
                        parent2 = parent2.getParent();
                    }
                }
                layoutNodeRequireLayoutNode2 = layoutNodeRequireLayoutNode2.getParent$ui_release();
                parent2 = (layoutNodeRequireLayoutNode2 == null || (nodes = layoutNodeRequireLayoutNode2.getNodes()) == null) ? null : nodes.getTail();
            }
            if (arrayList != null && arrayList.size() - 1 >= 0) {
                while (true) {
                    int i12 = size - 1;
                    if (((KeyInputModifierNode) arrayList.get(size)).mo149onPreKeyEventZmokQxo(keyEvent)) {
                        return true;
                    }
                    if (i12 < 0) {
                        break;
                    }
                    size = i12;
                }
            }
            ?? node = nodeLastLocalKeyInputNode.getNode();
            MutableVector mutableVector3 = null;
            while (node != 0) {
                if (node instanceof KeyInputModifierNode) {
                    if (((KeyInputModifierNode) node).mo149onPreKeyEventZmokQxo(keyEvent)) {
                        return true;
                    }
                } else if ((node.getKindSet() & iM4843constructorimpl2) != 0 && (node instanceof DelegatingNode)) {
                    Modifier.Node delegate3 = ((DelegatingNode) node).getDelegate();
                    int i13 = 0;
                    node = node;
                    while (delegate3 != null) {
                        if ((delegate3.getKindSet() & iM4843constructorimpl2) != 0) {
                            i13++;
                            if (i13 == 1) {
                                node = delegate3;
                            } else {
                                if (mutableVector3 == null) {
                                    mutableVector3 = new MutableVector(new Modifier.Node[16], 0);
                                }
                                if (node != 0) {
                                    mutableVector3.add(node);
                                    node = 0;
                                }
                                mutableVector3.add(delegate3);
                            }
                        }
                        delegate3 = delegate3.getChild();
                        node = node;
                    }
                    if (i13 == 1) {
                    }
                }
                node = DelegatableNodeKt.pop(mutableVector3);
            }
            ?? node2 = nodeLastLocalKeyInputNode.getNode();
            MutableVector mutableVector4 = null;
            while (node2 != 0) {
                if (node2 instanceof KeyInputModifierNode) {
                    if (((KeyInputModifierNode) node2).mo147onKeyEventZmokQxo(keyEvent)) {
                        return true;
                    }
                } else if ((node2.getKindSet() & iM4843constructorimpl2) != 0 && (node2 instanceof DelegatingNode)) {
                    Modifier.Node delegate4 = ((DelegatingNode) node2).getDelegate();
                    int i14 = 0;
                    node2 = node2;
                    while (delegate4 != null) {
                        if ((delegate4.getKindSet() & iM4843constructorimpl2) != 0) {
                            i14++;
                            if (i14 == 1) {
                                node2 = delegate4;
                            } else {
                                if (mutableVector4 == null) {
                                    mutableVector4 = new MutableVector(new Modifier.Node[16], 0);
                                }
                                if (node2 != 0) {
                                    mutableVector4.add(node2);
                                    node2 = 0;
                                }
                                mutableVector4.add(delegate4);
                            }
                        }
                        delegate4 = delegate4.getChild();
                        node2 = node2;
                    }
                    if (i14 == 1) {
                    }
                }
                node2 = DelegatableNodeKt.pop(mutableVector4);
            }
            if (arrayList != null) {
                int size2 = arrayList.size();
                for (int i15 = 0; i15 < size2; i15++) {
                    if (((KeyInputModifierNode) arrayList.get(i15)).mo147onKeyEventZmokQxo(keyEvent)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v16 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r1v15, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v23, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r1v24, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v25 */
    /* JADX WARN: Type inference failed for: r1v26 */
    /* JADX WARN: Type inference failed for: r1v27 */
    /* JADX WARN: Type inference failed for: r1v28 */
    /* JADX WARN: Type inference failed for: r1v4, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r1v42 */
    /* JADX WARN: Type inference failed for: r1v43 */
    /* JADX WARN: Type inference failed for: r1v44 */
    /* JADX WARN: Type inference failed for: r1v45 */
    /* JADX WARN: Type inference failed for: r1v5, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r1v6, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r1v7, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r9v13 */
    /* JADX WARN: Type inference failed for: r9v14, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v16, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r9v17, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v19 */
    /* JADX WARN: Type inference failed for: r9v20 */
    /* JADX WARN: Type inference failed for: r9v21 */
    /* JADX WARN: Type inference failed for: r9v22 */
    /* JADX WARN: Type inference failed for: r9v23 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7 */
    @Override // androidx.compose.ui.focus.FocusOwner
    public boolean dispatchRotaryEvent(RotaryScrollEvent event) {
        RotaryInputModifierNode rotaryInputModifierNode;
        int size;
        NodeChain nodes;
        ?? Pop;
        NodeChain nodes2;
        FocusTargetNode focusTargetNodeFindActiveFocusNode = FocusTraversalKt.findActiveFocusNode(this.rootFocusNode);
        if (focusTargetNodeFindActiveFocusNode != null) {
            int iM4843constructorimpl = NodeKind.m4843constructorimpl(16384);
            if (!focusTargetNodeFindActiveFocusNode.getNode().getIsAttached()) {
                throw new IllegalStateException("visitAncestors called on an unattached node");
            }
            Modifier.Node parent = focusTargetNodeFindActiveFocusNode.getNode().getParent();
            LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetNodeFindActiveFocusNode);
            loop0: while (true) {
                if (layoutNodeRequireLayoutNode == null) {
                    Pop = 0;
                    break;
                }
                if ((androidx.compose.material3.d.d(layoutNodeRequireLayoutNode) & iM4843constructorimpl) != 0) {
                    while (parent != null) {
                        if ((parent.getKindSet() & iM4843constructorimpl) != 0) {
                            MutableVector mutableVector = null;
                            Pop = parent;
                            while (Pop != 0) {
                                if (Pop instanceof RotaryInputModifierNode) {
                                    break loop0;
                                }
                                if ((Pop.getKindSet() & iM4843constructorimpl) != 0 && (Pop instanceof DelegatingNode)) {
                                    Modifier.Node delegate = ((DelegatingNode) Pop).getDelegate();
                                    int i10 = 0;
                                    Pop = Pop;
                                    while (delegate != null) {
                                        if ((delegate.getKindSet() & iM4843constructorimpl) != 0) {
                                            i10++;
                                            if (i10 == 1) {
                                                Pop = delegate;
                                            } else {
                                                if (mutableVector == null) {
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                if (Pop != 0) {
                                                    mutableVector.add(Pop);
                                                    Pop = 0;
                                                }
                                                mutableVector.add(delegate);
                                            }
                                        }
                                        delegate = delegate.getChild();
                                        Pop = Pop;
                                    }
                                    if (i10 == 1) {
                                    }
                                }
                                Pop = DelegatableNodeKt.pop(mutableVector);
                            }
                        }
                        parent = parent.getParent();
                    }
                }
                layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
                parent = (layoutNodeRequireLayoutNode == null || (nodes2 = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes2.getTail();
            }
            rotaryInputModifierNode = (RotaryInputModifierNode) Pop;
        } else {
            rotaryInputModifierNode = null;
        }
        if (rotaryInputModifierNode != null) {
            int iM4843constructorimpl2 = NodeKind.m4843constructorimpl(16384);
            if (!rotaryInputModifierNode.getNode().getIsAttached()) {
                throw new IllegalStateException("visitAncestors called on an unattached node");
            }
            Modifier.Node parent2 = rotaryInputModifierNode.getNode().getParent();
            LayoutNode layoutNodeRequireLayoutNode2 = DelegatableNodeKt.requireLayoutNode(rotaryInputModifierNode);
            ArrayList arrayList = null;
            while (layoutNodeRequireLayoutNode2 != null) {
                if ((androidx.compose.material3.d.d(layoutNodeRequireLayoutNode2) & iM4843constructorimpl2) != 0) {
                    while (parent2 != null) {
                        if ((parent2.getKindSet() & iM4843constructorimpl2) != 0) {
                            Modifier.Node nodePop = parent2;
                            MutableVector mutableVector2 = null;
                            while (nodePop != null) {
                                if (nodePop instanceof RotaryInputModifierNode) {
                                    if (arrayList == null) {
                                        arrayList = new ArrayList();
                                    }
                                    arrayList.add(nodePop);
                                } else if ((nodePop.getKindSet() & iM4843constructorimpl2) != 0 && (nodePop instanceof DelegatingNode)) {
                                    int i11 = 0;
                                    for (Modifier.Node delegate2 = ((DelegatingNode) nodePop).getDelegate(); delegate2 != null; delegate2 = delegate2.getChild()) {
                                        if ((delegate2.getKindSet() & iM4843constructorimpl2) != 0) {
                                            i11++;
                                            if (i11 == 1) {
                                                nodePop = delegate2;
                                            } else {
                                                if (mutableVector2 == null) {
                                                    mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                if (nodePop != null) {
                                                    mutableVector2.add(nodePop);
                                                    nodePop = null;
                                                }
                                                mutableVector2.add(delegate2);
                                            }
                                        }
                                    }
                                    if (i11 == 1) {
                                    }
                                }
                                nodePop = DelegatableNodeKt.pop(mutableVector2);
                            }
                        }
                        parent2 = parent2.getParent();
                    }
                }
                layoutNodeRequireLayoutNode2 = layoutNodeRequireLayoutNode2.getParent$ui_release();
                parent2 = (layoutNodeRequireLayoutNode2 == null || (nodes = layoutNodeRequireLayoutNode2.getNodes()) == null) ? null : nodes.getTail();
            }
            if (arrayList != null && arrayList.size() - 1 >= 0) {
                while (true) {
                    int i12 = size - 1;
                    if (((RotaryInputModifierNode) arrayList.get(size)).onPreRotaryScrollEvent(event)) {
                        return true;
                    }
                    if (i12 < 0) {
                        break;
                    }
                    size = i12;
                }
            }
            ?? node = rotaryInputModifierNode.getNode();
            MutableVector mutableVector3 = null;
            while (node != 0) {
                if (node instanceof RotaryInputModifierNode) {
                    if (((RotaryInputModifierNode) node).onPreRotaryScrollEvent(event)) {
                        return true;
                    }
                } else if ((node.getKindSet() & iM4843constructorimpl2) != 0 && (node instanceof DelegatingNode)) {
                    Modifier.Node delegate3 = ((DelegatingNode) node).getDelegate();
                    int i13 = 0;
                    node = node;
                    while (delegate3 != null) {
                        if ((delegate3.getKindSet() & iM4843constructorimpl2) != 0) {
                            i13++;
                            if (i13 == 1) {
                                node = delegate3;
                            } else {
                                if (mutableVector3 == null) {
                                    mutableVector3 = new MutableVector(new Modifier.Node[16], 0);
                                }
                                if (node != 0) {
                                    mutableVector3.add(node);
                                    node = 0;
                                }
                                mutableVector3.add(delegate3);
                            }
                        }
                        delegate3 = delegate3.getChild();
                        node = node;
                    }
                    if (i13 == 1) {
                    }
                }
                node = DelegatableNodeKt.pop(mutableVector3);
            }
            ?? node2 = rotaryInputModifierNode.getNode();
            MutableVector mutableVector4 = null;
            while (node2 != 0) {
                if (node2 instanceof RotaryInputModifierNode) {
                    if (((RotaryInputModifierNode) node2).onRotaryScrollEvent(event)) {
                        return true;
                    }
                } else if ((node2.getKindSet() & iM4843constructorimpl2) != 0 && (node2 instanceof DelegatingNode)) {
                    Modifier.Node delegate4 = ((DelegatingNode) node2).getDelegate();
                    int i14 = 0;
                    node2 = node2;
                    while (delegate4 != null) {
                        if ((delegate4.getKindSet() & iM4843constructorimpl2) != 0) {
                            i14++;
                            if (i14 == 1) {
                                node2 = delegate4;
                            } else {
                                if (mutableVector4 == null) {
                                    mutableVector4 = new MutableVector(new Modifier.Node[16], 0);
                                }
                                if (node2 != 0) {
                                    mutableVector4.add(node2);
                                    node2 = 0;
                                }
                                mutableVector4.add(delegate4);
                            }
                        }
                        delegate4 = delegate4.getChild();
                        node2 = node2;
                    }
                    if (i14 == 1) {
                    }
                }
                node2 = DelegatableNodeKt.pop(mutableVector4);
            }
            if (arrayList != null) {
                int size2 = arrayList.size();
                for (int i15 = 0; i15 < size2; i15++) {
                    if (((RotaryInputModifierNode) arrayList.get(i15)).onRotaryScrollEvent(event)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public Rect getFocusRect() {
        FocusTargetNode focusTargetNodeFindActiveFocusNode = FocusTraversalKt.findActiveFocusNode(this.rootFocusNode);
        if (focusTargetNodeFindActiveFocusNode != null) {
            return FocusTraversalKt.focusRect(focusTargetNodeFindActiveFocusNode);
        }
        return null;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public FocusTransactionManager getFocusTransactionManager() {
        return this.focusTransactionManager;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public LayoutDirection getLayoutDirection() {
        LayoutDirection layoutDirection = this.layoutDirection;
        if (layoutDirection != null) {
            return layoutDirection;
        }
        p.i("layoutDirection");
        throw null;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public Modifier getModifier() {
        return this.modifier;
    }

    /* JADX INFO: renamed from: getRootFocusNode$ui_release, reason: from getter */
    public final FocusTargetNode getRootFocusNode() {
        return this.rootFocusNode;
    }

    @Override // androidx.compose.ui.focus.FocusManager
    /* JADX INFO: renamed from: moveFocus-3ESFkO8 */
    public boolean mo3168moveFocus3ESFkO8(int focusDirection) {
        FocusTargetNode focusTargetNodeFindActiveFocusNode = FocusTraversalKt.findActiveFocusNode(this.rootFocusNode);
        if (focusTargetNodeFindActiveFocusNode == null) {
            return false;
        }
        FocusRequester focusRequesterM3188customFocusSearchOMvw8 = FocusTraversalKt.m3188customFocusSearchOMvw8(focusTargetNodeFindActiveFocusNode, focusDirection, getLayoutDirection());
        FocusRequester.Companion companion = FocusRequester.INSTANCE;
        if (focusRequesterM3188customFocusSearchOMvw8 != companion.getDefault()) {
            return focusRequesterM3188customFocusSearchOMvw8 != companion.getCancel() && focusRequesterM3188customFocusSearchOMvw8.focus$ui_release();
        }
        f0 f0Var = new f0();
        return !f0Var.f19738i && (FocusTraversalKt.m3189focusSearchsMXa3k8(this.rootFocusNode, focusDirection, getLayoutDirection(), new FocusOwnerImpl$moveFocus$foundNextItem$1(focusTargetNodeFindActiveFocusNode, this, focusDirection, f0Var)) || m3173wrapAroundFocus3ESFkO8(focusDirection));
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void releaseFocus() {
        FocusTransactionsKt.clearFocus(this.rootFocusNode, true, true);
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void scheduleInvalidation(FocusTargetNode node) {
        this.focusInvalidationManager.scheduleInvalidation(node);
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void setLayoutDirection(LayoutDirection layoutDirection) {
        this.layoutDirection = layoutDirection;
    }

    public final void setRootFocusNode$ui_release(FocusTargetNode focusTargetNode) {
        this.rootFocusNode = focusTargetNode;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void takeFocus() {
        if (this.rootFocusNode.getFocusState() == FocusStateImpl.Inactive) {
            this.rootFocusNode.setFocusState(FocusStateImpl.Active);
        }
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void clearFocus(boolean force, boolean refreshFocusEvents) {
        FocusStateImpl focusStateImpl;
        FocusTransactionManager focusTransactionManager = getFocusTransactionManager();
        try {
            if (focusTransactionManager.ongoingTransaction) {
                focusTransactionManager.cancelTransaction();
            }
            focusTransactionManager.beginTransaction();
            if (!force) {
                int i10 = WhenMappings.$EnumSwitchMapping$0[FocusTransactionsKt.m3183performCustomClearFocusMxy_nc0(this.rootFocusNode, FocusDirection.INSTANCE.m3161getExitdhqQ8s()).ordinal()];
                if (i10 == 1 || i10 == 2 || i10 == 3) {
                    focusTransactionManager.commitTransaction();
                    return;
                }
            }
            FocusStateImpl focusState = this.rootFocusNode.getFocusState();
            if (FocusTransactionsKt.clearFocus(this.rootFocusNode, force, refreshFocusEvents)) {
                FocusTargetNode focusTargetNode = this.rootFocusNode;
                int i11 = WhenMappings.$EnumSwitchMapping$1[focusState.ordinal()];
                if (i11 == 1 || i11 == 2 || i11 == 3) {
                    focusStateImpl = FocusStateImpl.Active;
                } else {
                    if (i11 != 4) {
                        throw new NoWhenBranchMatchedException();
                    }
                    focusStateImpl = FocusStateImpl.Inactive;
                }
                focusTargetNode.setFocusState(focusStateImpl);
            }
            focusTransactionManager.commitTransaction();
        } catch (Throwable th) {
            focusTransactionManager.commitTransaction();
            throw th;
        }
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void scheduleInvalidation(FocusEventModifierNode node) {
        this.focusInvalidationManager.scheduleInvalidation(node);
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void scheduleInvalidation(FocusPropertiesModifierNode node) {
        this.focusInvalidationManager.scheduleInvalidation(node);
    }
}
