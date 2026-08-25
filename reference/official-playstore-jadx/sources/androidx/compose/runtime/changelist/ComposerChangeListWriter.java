package androidx.compose.runtime.changelist;

import androidx.compose.runtime.Anchor;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.Composition;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.ControlledComposition;
import androidx.compose.runtime.IntStack;
import androidx.compose.runtime.MovableContentState;
import androidx.compose.runtime.MovableContentStateReference;
import androidx.compose.runtime.RememberObserver;
import androidx.compose.runtime.SlotReader;
import androidx.compose.runtime.SlotTable;
import androidx.compose.runtime.Stack;
import androidx.compose.runtime.internal.IntRef;
import io.ktor.http.LinkHeader;
import java.util.List;
import kotlin.Metadata;
import r7.a;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \u009c\u00012\u00020\u0001:\u0002\u009c\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\r\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\r\u0010\fJ\r\u0010\u000e\u001a\u00020\n¢\u0006\u0004\b\u000e\u0010\u000fJ&\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00042\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\n0\u0011H\u0086\b¢\u0006\u0004\b\u0013\u0010\u0014J\u001e\u0010\u0015\u001a\u00020\n2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\n0\u0011H\u0086\b¢\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u0019\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u0019\u0010\u001aJ\u001f\u0010\u001c\u001a\u00020\n2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u001b\u001a\u00020\b¢\u0006\u0004\b\u001c\u0010\u001dJ'\u0010 \u001a\u00020\n2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\b¢\u0006\u0004\b \u0010!J\u001f\u0010\"\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u001e2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\"\u0010#J\u0015\u0010%\u001a\u00020\n2\u0006\u0010$\u001a\u00020\b¢\u0006\u0004\b%\u0010\fJ\r\u0010&\u001a\u00020\n¢\u0006\u0004\b&\u0010\u000fJ\u0017\u0010(\u001a\u00020\n2\b\u0010'\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b(\u0010)J\r\u0010*\u001a\u00020\n¢\u0006\u0004\b*\u0010\u000fJ\r\u0010+\u001a\u00020\n¢\u0006\u0004\b+\u0010\u000fJ\r\u0010,\u001a\u00020\n¢\u0006\u0004\b,\u0010\u000fJ\r\u0010-\u001a\u00020\n¢\u0006\u0004\b-\u0010\u000fJ\u001d\u00100\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010/\u001a\u00020.¢\u0006\u0004\b0\u00101J%\u00100\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010/\u001a\u00020.2\u0006\u00103\u001a\u000202¢\u0006\u0004\b0\u00104J\u0015\u00106\u001a\u00020\n2\u0006\u00105\u001a\u00020\b¢\u0006\u0004\b6\u0010\fJ)\u0010;\u001a\u00020\n2\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020\n072\u0006\u0010:\u001a\u000208¢\u0006\u0004\b;\u0010<J\u0017\u0010>\u001a\u00020\n2\b\u0010=\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b>\u0010)J@\u0010C\u001a\u00020\n\"\u0004\b\u0000\u0010?\"\u0004\b\u0001\u0010@2\u0006\u0010\u0018\u001a\u00028\u00012\u001d\u0010\u0012\u001a\u0019\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\n0A¢\u0006\u0002\bB¢\u0006\u0004\bC\u0010DJ\u001d\u0010F\u001a\u00020\n2\u0006\u0010E\u001a\u00020\b2\u0006\u0010$\u001a\u00020\b¢\u0006\u0004\bF\u0010GJ%\u0010I\u001a\u00020\n2\u0006\u0010/\u001a\u00020\b2\u0006\u0010H\u001a\u00020\b2\u0006\u0010$\u001a\u00020\b¢\u0006\u0004\bI\u0010JJ\r\u0010K\u001a\u00020\n¢\u0006\u0004\bK\u0010\u000fJ\r\u0010L\u001a\u00020\n¢\u0006\u0004\bL\u0010\u000fJ\u001d\u0010N\u001a\u00020\n2\u0006\u0010E\u001a\u00020\b2\u0006\u0010M\u001a\u00020\b¢\u0006\u0004\bN\u0010GJ\r\u0010O\u001a\u00020\n¢\u0006\u0004\bO\u0010\u000fJ\u0017\u0010P\u001a\u00020\n2\b\u0010=\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\bP\u0010)J\u001b\u0010R\u001a\u00020\n2\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020\n0\u0011¢\u0006\u0004\bR\u0010\u0016J\u001d\u0010U\u001a\u00020\n2\u0006\u0010T\u001a\u00020S2\u0006\u0010\u001f\u001a\u00020\u001e¢\u0006\u0004\bU\u0010VJ%\u0010Z\u001a\u00020\n2\u000e\u0010X\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010W2\u0006\u0010Y\u001a\u00020S¢\u0006\u0004\bZ\u0010[J/\u0010a\u001a\u00020\n2\b\u0010]\u001a\u0004\u0018\u00010\\2\u0006\u0010_\u001a\u00020^2\u0006\u0010/\u001a\u00020`2\u0006\u0010H\u001a\u00020`¢\u0006\u0004\ba\u0010bJ%\u0010e\u001a\u00020\n2\u0006\u0010:\u001a\u00020c2\u0006\u0010_\u001a\u00020^2\u0006\u0010d\u001a\u00020`¢\u0006\u0004\be\u0010fJ\r\u0010g\u001a\u00020\n¢\u0006\u0004\bg\u0010\u000fJ!\u0010i\u001a\u00020\n2\u0006\u0010h\u001a\u00020\u00042\n\b\u0002\u0010Y\u001a\u0004\u0018\u00010S¢\u0006\u0004\bi\u0010jJ\r\u0010k\u001a\u00020\n¢\u0006\u0004\bk\u0010\u000fJ\r\u0010l\u001a\u00020\n¢\u0006\u0004\bl\u0010\u000fJ\r\u0010m\u001a\u00020\n¢\u0006\u0004\bm\u0010\u000fJ\u000f\u0010n\u001a\u00020\nH\u0002¢\u0006\u0004\bn\u0010\u000fJ\u000f\u0010o\u001a\u00020\nH\u0002¢\u0006\u0004\bo\u0010\u000fJ\u0019\u0010r\u001a\u00020\n2\b\b\u0002\u0010q\u001a\u00020pH\u0002¢\u0006\u0004\br\u0010sJ\u000f\u0010t\u001a\u00020\nH\u0002¢\u0006\u0004\bt\u0010\u000fJ\u0017\u0010u\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u001eH\u0002¢\u0006\u0004\bu\u0010vJ\u0019\u0010x\u001a\u00020\n2\b\b\u0002\u0010w\u001a\u00020pH\u0002¢\u0006\u0004\bx\u0010sJ\u000f\u0010y\u001a\u00020\nH\u0002¢\u0006\u0004\by\u0010\u000fJ\u001f\u0010|\u001a\u00020\n2\u0006\u0010z\u001a\u00020\b2\u0006\u0010{\u001a\u00020\bH\u0002¢\u0006\u0004\b|\u0010GJ'\u0010}\u001a\u00020\n2\u0006\u0010H\u001a\u00020\b2\u0006\u0010/\u001a\u00020\b2\u0006\u0010$\u001a\u00020\bH\u0002¢\u0006\u0004\b}\u0010JJ\u000f\u0010~\u001a\u00020\nH\u0002¢\u0006\u0004\b~\u0010\u000fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u007fR'\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0017\n\u0005\b\u0005\u0010\u0080\u0001\u001a\u0006\b\u0081\u0001\u0010\u0082\u0001\"\u0006\b\u0083\u0001\u0010\u0084\u0001R\u0019\u0010\u0085\u0001\u001a\u00020p8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0085\u0001\u0010\u0086\u0001R\u0018\u0010\u0088\u0001\u001a\u00030\u0087\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0088\u0001\u0010\u0089\u0001R(\u0010\u008a\u0001\u001a\u00020p8\u0006@\u0006X\u0086\u000e¢\u0006\u0017\n\u0006\b\u008a\u0001\u0010\u0086\u0001\u001a\u0006\b\u008b\u0001\u0010\u008c\u0001\"\u0005\b\u008d\u0001\u0010sR\u0019\u0010\u008e\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u008e\u0001\u0010\u008f\u0001R\u0019\u0010\u0090\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0090\u0001\u0010\u008f\u0001R\"\u0010\u0092\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0091\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0092\u0001\u0010\u0093\u0001R\u0017\u0010z\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\bz\u0010\u008f\u0001R\u0019\u0010\u0094\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0094\u0001\u0010\u008f\u0001R\u0019\u0010\u0095\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0095\u0001\u0010\u008f\u0001R\u0017\u0010{\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b{\u0010\u008f\u0001R\u0014\u0010\u0097\u0001\u001a\u00020p8F¢\u0006\b\u001a\u0006\b\u0096\u0001\u0010\u008c\u0001R\u0018\u0010\u009b\u0001\u001a\u00030\u0098\u00018BX\u0082\u0004¢\u0006\b\u001a\u0006\b\u0099\u0001\u0010\u009a\u0001¨\u0006\u009d\u0001"}, d2 = {"Landroidx/compose/runtime/changelist/ComposerChangeListWriter;", "", "Landroidx/compose/runtime/ComposerImpl;", "composer", "Landroidx/compose/runtime/changelist/ChangeList;", "changeList", "<init>", "(Landroidx/compose/runtime/ComposerImpl;Landroidx/compose/runtime/changelist/ChangeList;)V", "", "location", "Lx6/t0;", "moveReaderRelativeTo", "(I)V", "moveReaderToAbsolute", "recordSlotEditing", "()V", "newChangeList", "Lkotlin/Function0;", "block", "withChangeList", "(Landroidx/compose/runtime/changelist/ChangeList;Lr7/a;)V", "withoutImplicitRootStart", "(Lr7/a;)V", "Landroidx/compose/runtime/RememberObserver;", "value", "remember", "(Landroidx/compose/runtime/RememberObserver;)V", "groupSlotIndex", "updateValue", "(Ljava/lang/Object;I)V", "Landroidx/compose/runtime/Anchor;", LinkHeader.Parameters.Anchor, "updateAnchoredValue", "(Ljava/lang/Object;Landroidx/compose/runtime/Anchor;I)V", "appendValue", "(Landroidx/compose/runtime/Anchor;Ljava/lang/Object;)V", "count", "trimValues", "resetSlots", "data", "updateAuxData", "(Ljava/lang/Object;)V", "endRoot", "endCurrentGroup", "skipToEndOfCurrentGroup", "removeCurrentGroup", "Landroidx/compose/runtime/SlotTable;", "from", "insertSlots", "(Landroidx/compose/runtime/Anchor;Landroidx/compose/runtime/SlotTable;)V", "Landroidx/compose/runtime/changelist/FixupList;", "fixups", "(Landroidx/compose/runtime/Anchor;Landroidx/compose/runtime/SlotTable;Landroidx/compose/runtime/changelist/FixupList;)V", "offset", "moveCurrentGroup", "Lkotlin/Function1;", "Landroidx/compose/runtime/Composition;", "action", "composition", "endCompositionScope", "(Lr7/l;Landroidx/compose/runtime/Composition;)V", "node", "useNode", "T", "V", "Lkotlin/Function2;", "Lx6/n;", "updateNode", "(Ljava/lang/Object;Lr7/p;)V", "nodeIndex", "removeNode", "(II)V", "to", "moveNode", "(III)V", "releaseMovableContent", "endNodeMovement", "group", "endNodeMovementAndDeleteNode", "moveUp", "moveDown", "effect", "sideEffect", "Landroidx/compose/runtime/internal/IntRef;", "effectiveNodeIndexOut", "determineMovableContentNodeIndex", "(Landroidx/compose/runtime/internal/IntRef;Landroidx/compose/runtime/Anchor;)V", "", "nodes", "effectiveNodeIndex", "copyNodesToNewAnchorLocation", "(Ljava/util/List;Landroidx/compose/runtime/internal/IntRef;)V", "Landroidx/compose/runtime/MovableContentState;", "resolvedState", "Landroidx/compose/runtime/CompositionContext;", "parentContext", "Landroidx/compose/runtime/MovableContentStateReference;", "copySlotTableToAnchorLocation", "(Landroidx/compose/runtime/MovableContentState;Landroidx/compose/runtime/CompositionContext;Landroidx/compose/runtime/MovableContentStateReference;Landroidx/compose/runtime/MovableContentStateReference;)V", "Landroidx/compose/runtime/ControlledComposition;", "reference", "releaseMovableGroupAtCurrent", "(Landroidx/compose/runtime/ControlledComposition;Landroidx/compose/runtime/CompositionContext;Landroidx/compose/runtime/MovableContentStateReference;)V", "endMovableContentPlacement", "other", "includeOperationsIn", "(Landroidx/compose/runtime/changelist/ChangeList;Landroidx/compose/runtime/internal/IntRef;)V", "finalizeComposition", "resetTransientState", "deactivateCurrentGroup", "pushApplierOperationPreamble", "pushSlotEditingOperationPreamble", "", "useParentSlot", "pushSlotTableOperationPreamble", "(Z)V", "ensureRootStarted", "ensureGroupStarted", "(Landroidx/compose/runtime/Anchor;)V", "forParent", "realizeOperationLocation", "realizeNodeMovementOperations", "removeFrom", "moveCount", "realizeRemoveNode", "realizeMoveNode", "pushPendingUpsAndDowns", "Landroidx/compose/runtime/ComposerImpl;", "Landroidx/compose/runtime/changelist/ChangeList;", "getChangeList", "()Landroidx/compose/runtime/changelist/ChangeList;", "setChangeList", "(Landroidx/compose/runtime/changelist/ChangeList;)V", "startedGroup", "Z", "Landroidx/compose/runtime/IntStack;", "startedGroups", "Landroidx/compose/runtime/IntStack;", "implicitRootStart", "getImplicitRootStart", "()Z", "setImplicitRootStart", "writersReaderDelta", "I", "pendingUps", "Landroidx/compose/runtime/Stack;", "pendingDownNodes", "Landroidx/compose/runtime/Stack;", "moveFrom", "moveTo", "getPastParent", "pastParent", "Landroidx/compose/runtime/SlotReader;", "getReader", "()Landroidx/compose/runtime/SlotReader;", "reader", "Companion", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ComposerChangeListWriter {
    private static final int invalidGroupLocation = -2;
    private ChangeList changeList;
    private final ComposerImpl composer;
    private int moveCount;
    private int pendingUps;
    private boolean startedGroup;
    private int writersReaderDelta;
    public static final int $stable = 8;
    private final IntStack startedGroups = new IntStack();
    private boolean implicitRootStart = true;
    private Stack<Object> pendingDownNodes = new Stack<>();
    private int removeFrom = -1;
    private int moveFrom = -1;
    private int moveTo = -1;

    public ComposerChangeListWriter(ComposerImpl composerImpl, ChangeList changeList) {
        this.composer = composerImpl;
        this.changeList = changeList;
    }

    private final void ensureGroupStarted(Anchor anchor) {
        pushSlotTableOperationPreamble$default(this, false, 1, null);
        this.changeList.pushEnsureGroupStarted(anchor);
        this.startedGroup = true;
    }

    private final void ensureRootStarted() {
        if (this.startedGroup || !this.implicitRootStart) {
            return;
        }
        pushSlotTableOperationPreamble$default(this, false, 1, null);
        this.changeList.pushEnsureRootStarted();
        this.startedGroup = true;
    }

    private final SlotReader getReader() {
        return this.composer.getReader();
    }

    public static /* synthetic */ void includeOperationsIn$default(ComposerChangeListWriter composerChangeListWriter, ChangeList changeList, IntRef intRef, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            intRef = null;
        }
        composerChangeListWriter.includeOperationsIn(changeList, intRef);
    }

    private final void pushApplierOperationPreamble() {
        pushPendingUpsAndDowns();
    }

    private final void pushPendingUpsAndDowns() {
        int i10 = this.pendingUps;
        if (i10 > 0) {
            this.changeList.pushUps(i10);
            this.pendingUps = 0;
        }
        if (this.pendingDownNodes.isNotEmpty()) {
            this.changeList.pushDowns(this.pendingDownNodes.toArray());
            this.pendingDownNodes.clear();
        }
    }

    private final void pushSlotEditingOperationPreamble() {
        realizeOperationLocation$default(this, false, 1, null);
        recordSlotEditing();
    }

    private final void pushSlotTableOperationPreamble(boolean useParentSlot) {
        realizeOperationLocation(useParentSlot);
    }

    public static /* synthetic */ void pushSlotTableOperationPreamble$default(ComposerChangeListWriter composerChangeListWriter, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        composerChangeListWriter.pushSlotTableOperationPreamble(z);
    }

    private final void realizeMoveNode(int to, int from, int count) {
        pushApplierOperationPreamble();
        this.changeList.pushMoveNode(to, from, count);
    }

    private final void realizeNodeMovementOperations() {
        int i10 = this.moveCount;
        if (i10 > 0) {
            int i11 = this.removeFrom;
            if (i11 >= 0) {
                realizeRemoveNode(i11, i10);
                this.removeFrom = -1;
            } else {
                realizeMoveNode(this.moveTo, this.moveFrom, i10);
                this.moveFrom = -1;
                this.moveTo = -1;
            }
            this.moveCount = 0;
        }
    }

    private final void realizeOperationLocation(boolean forParent) {
        int parent = forParent ? getReader().getParent() : getReader().getCurrentGroup();
        int i10 = parent - this.writersReaderDelta;
        if (!(i10 >= 0)) {
            ComposerKt.composeImmediateRuntimeError("Tried to seek backward");
        }
        if (i10 > 0) {
            this.changeList.pushAdvanceSlotsBy(i10);
            this.writersReaderDelta = parent;
        }
    }

    public static /* synthetic */ void realizeOperationLocation$default(ComposerChangeListWriter composerChangeListWriter, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        composerChangeListWriter.realizeOperationLocation(z);
    }

    private final void realizeRemoveNode(int removeFrom, int moveCount) {
        pushApplierOperationPreamble();
        this.changeList.pushRemoveNode(removeFrom, moveCount);
    }

    public final void appendValue(Anchor anchor, Object value) {
        this.changeList.pushAppendValue(anchor, value);
    }

    public final void copyNodesToNewAnchorLocation(List<? extends Object> nodes, IntRef effectiveNodeIndex) {
        this.changeList.pushCopyNodesToNewAnchorLocation(nodes, effectiveNodeIndex);
    }

    public final void copySlotTableToAnchorLocation(MovableContentState resolvedState, CompositionContext parentContext, MovableContentStateReference from, MovableContentStateReference to) {
        this.changeList.pushCopySlotTableToAnchorLocation(resolvedState, parentContext, from, to);
    }

    public final void deactivateCurrentGroup() {
        pushSlotTableOperationPreamble$default(this, false, 1, null);
        this.changeList.pushDeactivateCurrentGroup();
    }

    public final void determineMovableContentNodeIndex(IntRef effectiveNodeIndexOut, Anchor anchor) {
        pushPendingUpsAndDowns();
        this.changeList.pushDetermineMovableContentNodeIndex(effectiveNodeIndexOut, anchor);
    }

    public final void endCompositionScope(l<? super Composition, t0> action, Composition composition) {
        this.changeList.pushEndCompositionScope(action, composition);
    }

    public final void endCurrentGroup() {
        int parent = getReader().getParent();
        if (!(this.startedGroups.peekOr(-1) <= parent)) {
            ComposerKt.composeImmediateRuntimeError("Missed recording an endGroup");
        }
        if (this.startedGroups.peekOr(-1) == parent) {
            pushSlotTableOperationPreamble$default(this, false, 1, null);
            this.startedGroups.pop();
            this.changeList.pushEndCurrentGroup();
        }
    }

    public final void endMovableContentPlacement() {
        this.changeList.pushEndMovableContentPlacement();
        this.writersReaderDelta = 0;
    }

    public final void endNodeMovement() {
        realizeNodeMovementOperations();
    }

    public final void endNodeMovementAndDeleteNode(int nodeIndex, int group) {
        endNodeMovement();
        pushPendingUpsAndDowns();
        int iNodeCount = getReader().isNode(group) ? 1 : getReader().nodeCount(group);
        if (iNodeCount > 0) {
            removeNode(nodeIndex, iNodeCount);
        }
    }

    public final void endRoot() {
        if (this.startedGroup) {
            pushSlotTableOperationPreamble$default(this, false, 1, null);
            pushSlotTableOperationPreamble$default(this, false, 1, null);
            this.changeList.pushEndCurrentGroup();
            this.startedGroup = false;
        }
    }

    public final void finalizeComposition() {
        pushPendingUpsAndDowns();
        if (this.startedGroups.isEmpty()) {
            return;
        }
        ComposerKt.composeImmediateRuntimeError("Missed recording an endGroup()");
    }

    public final ChangeList getChangeList() {
        return this.changeList;
    }

    public final boolean getImplicitRootStart() {
        return this.implicitRootStart;
    }

    public final boolean getPastParent() {
        return getReader().getParent() - this.writersReaderDelta < 0;
    }

    public final void includeOperationsIn(ChangeList other, IntRef effectiveNodeIndex) {
        this.changeList.pushExecuteOperationsIn(other, effectiveNodeIndex);
    }

    public final void insertSlots(Anchor anchor, SlotTable from) {
        pushPendingUpsAndDowns();
        pushSlotEditingOperationPreamble();
        realizeNodeMovementOperations();
        this.changeList.pushInsertSlots(anchor, from);
    }

    public final void moveCurrentGroup(int offset) {
        pushSlotEditingOperationPreamble();
        this.changeList.pushMoveCurrentGroup(offset);
    }

    public final void moveDown(Object node) {
        realizeNodeMovementOperations();
        this.pendingDownNodes.push(node);
    }

    public final void moveNode(int from, int to, int count) {
        if (count > 0) {
            int i10 = this.moveCount;
            if (i10 > 0 && this.moveFrom == from - i10 && this.moveTo == to - i10) {
                this.moveCount = i10 + count;
                return;
            }
            realizeNodeMovementOperations();
            this.moveFrom = from;
            this.moveTo = to;
            this.moveCount = count;
        }
    }

    public final void moveReaderRelativeTo(int location) {
        this.writersReaderDelta = (location - getReader().getCurrentGroup()) + this.writersReaderDelta;
    }

    public final void moveReaderToAbsolute(int location) {
        this.writersReaderDelta = location;
    }

    public final void moveUp() {
        realizeNodeMovementOperations();
        if (this.pendingDownNodes.isNotEmpty()) {
            this.pendingDownNodes.pop();
        } else {
            this.pendingUps++;
        }
    }

    public final void recordSlotEditing() {
        SlotReader reader;
        int parent;
        if (getReader().getGroupsSize() <= 0 || this.startedGroups.peekOr(-2) == (parent = (reader = getReader()).getParent())) {
            return;
        }
        ensureRootStarted();
        if (parent > 0) {
            Anchor anchor = reader.anchor(parent);
            this.startedGroups.push(parent);
            ensureGroupStarted(anchor);
        }
    }

    public final void releaseMovableContent() {
        pushPendingUpsAndDowns();
        if (this.startedGroup) {
            skipToEndOfCurrentGroup();
            endRoot();
        }
    }

    public final void releaseMovableGroupAtCurrent(ControlledComposition composition, CompositionContext parentContext, MovableContentStateReference reference) {
        this.changeList.pushReleaseMovableGroupAtCurrent(composition, parentContext, reference);
    }

    public final void remember(RememberObserver value) {
        this.changeList.pushRemember(value);
    }

    public final void removeCurrentGroup() {
        pushSlotEditingOperationPreamble();
        this.changeList.pushRemoveCurrentGroup();
        this.writersReaderDelta = getReader().getGroupSize() + this.writersReaderDelta;
    }

    public final void removeNode(int nodeIndex, int count) {
        if (count > 0) {
            if (!(nodeIndex >= 0)) {
                ComposerKt.composeImmediateRuntimeError("Invalid remove index " + nodeIndex);
            }
            if (this.removeFrom == nodeIndex) {
                this.moveCount += count;
                return;
            }
            realizeNodeMovementOperations();
            this.removeFrom = nodeIndex;
            this.moveCount = count;
        }
    }

    public final void resetSlots() {
        this.changeList.pushResetSlots();
    }

    public final void resetTransientState() {
        this.startedGroup = false;
        this.startedGroups.clear();
        this.writersReaderDelta = 0;
    }

    public final void setChangeList(ChangeList changeList) {
        this.changeList = changeList;
    }

    public final void setImplicitRootStart(boolean z) {
        this.implicitRootStart = z;
    }

    public final void sideEffect(a<t0> effect) {
        this.changeList.pushSideEffect(effect);
    }

    public final void skipToEndOfCurrentGroup() {
        this.changeList.pushSkipToEndOfCurrentGroup();
    }

    public final void trimValues(int count) {
        if (count > 0) {
            pushSlotEditingOperationPreamble();
            this.changeList.pushTrimValues(count);
        }
    }

    public final void updateAnchoredValue(Object value, Anchor anchor, int groupSlotIndex) {
        this.changeList.pushUpdateAnchoredValue(value, anchor, groupSlotIndex);
    }

    public final void updateAuxData(Object data) {
        pushSlotTableOperationPreamble$default(this, false, 1, null);
        this.changeList.pushUpdateAuxData(data);
    }

    public final <T, V> void updateNode(V value, p<? super T, ? super V, t0> block) {
        pushApplierOperationPreamble();
        this.changeList.pushUpdateNode(value, block);
    }

    public final void updateValue(Object value, int groupSlotIndex) {
        pushSlotTableOperationPreamble(true);
        this.changeList.pushUpdateValue(value, groupSlotIndex);
    }

    public final void useNode(Object node) {
        pushApplierOperationPreamble();
        this.changeList.pushUseNode(node);
    }

    public final void withChangeList(ChangeList newChangeList, a<t0> block) {
        ChangeList changeList = getChangeList();
        try {
            setChangeList(newChangeList);
            block.invoke();
        } finally {
            setChangeList(changeList);
        }
    }

    public final void withoutImplicitRootStart(a<t0> block) {
        boolean implicitRootStart = getImplicitRootStart();
        try {
            setImplicitRootStart(false);
            block.invoke();
        } finally {
            setImplicitRootStart(implicitRootStart);
        }
    }

    public final void insertSlots(Anchor anchor, SlotTable from, FixupList fixups) {
        pushPendingUpsAndDowns();
        pushSlotEditingOperationPreamble();
        realizeNodeMovementOperations();
        this.changeList.pushInsertSlots(anchor, from, fixups);
    }
}
