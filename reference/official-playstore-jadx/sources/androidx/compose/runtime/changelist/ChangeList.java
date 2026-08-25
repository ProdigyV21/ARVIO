package androidx.compose.runtime.changelist;

import androidx.compose.material3.d;
import androidx.compose.runtime.Anchor;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposeNodeLifecycleCallback;
import androidx.compose.runtime.Composition;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.ControlledComposition;
import androidx.compose.runtime.MovableContentState;
import androidx.compose.runtime.MovableContentStateReference;
import androidx.compose.runtime.RememberManager;
import androidx.compose.runtime.RememberObserver;
import androidx.compose.runtime.SlotTable;
import androidx.compose.runtime.SlotWriter;
import androidx.compose.runtime.changelist.Operation;
import androidx.compose.runtime.changelist.Operations;
import androidx.compose.runtime.internal.IntRef;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.q0;
import r7.a;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¾\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\u0007\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\u0006J\r\u0010\t\u001a\u00020\b¢\u0006\u0004\b\t\u0010\u0003J)\u0010\u0010\u001a\u00020\b2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\u0015\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0014\u0010\u0015J\u001f\u0010\u0019\u001a\u00020\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u0019\u0010\u001aJ'\u0010\u001d\u001a\u00020\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u001d\u0010\u001eJ\u001f\u0010\u001f\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u001b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0016¢\u0006\u0004\b\u001f\u0010 J\u0015\u0010\"\u001a\u00020\b2\u0006\u0010!\u001a\u00020\u0017¢\u0006\u0004\b\"\u0010#J\r\u0010$\u001a\u00020\b¢\u0006\u0004\b$\u0010\u0003J\r\u0010%\u001a\u00020\b¢\u0006\u0004\b%\u0010\u0003J\u0017\u0010'\u001a\u00020\b2\b\u0010&\u001a\u0004\u0018\u00010\u0016¢\u0006\u0004\b'\u0010(J\r\u0010)\u001a\u00020\b¢\u0006\u0004\b)\u0010\u0003J\u0015\u0010*\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b*\u0010+J\r\u0010,\u001a\u00020\b¢\u0006\u0004\b,\u0010\u0003J\r\u0010-\u001a\u00020\b¢\u0006\u0004\b-\u0010\u0003J\r\u0010.\u001a\u00020\b¢\u0006\u0004\b.\u0010\u0003J\u001d\u00101\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u00100\u001a\u00020/¢\u0006\u0004\b1\u00102J%\u00101\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u00100\u001a\u00020/2\u0006\u00104\u001a\u000203¢\u0006\u0004\b1\u00105J\u0015\u00107\u001a\u00020\b2\u0006\u00106\u001a\u00020\u0017¢\u0006\u0004\b7\u0010#J)\u0010<\u001a\u00020\b2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u000209\u0012\u0004\u0012\u00020\b082\u0006\u0010;\u001a\u000209¢\u0006\u0004\b<\u0010=J\u0017\u0010?\u001a\u00020\b2\b\u0010>\u001a\u0004\u0018\u00010\u0016¢\u0006\u0004\b?\u0010(J@\u0010E\u001a\u00020\b\"\u0004\b\u0000\u0010@\"\u0004\b\u0001\u0010A2\u0006\u0010\u0013\u001a\u00028\u00012\u001d\u0010D\u001a\u0019\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\b0B¢\u0006\u0002\bC¢\u0006\u0004\bE\u0010FJ\u001d\u0010I\u001a\u00020\b2\u0006\u0010G\u001a\u00020\u00172\u0006\u0010H\u001a\u00020\u0017¢\u0006\u0004\bI\u0010JJ%\u0010L\u001a\u00020\b2\u0006\u0010K\u001a\u00020\u00172\u0006\u00100\u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u0017¢\u0006\u0004\bL\u0010MJ\u0015\u0010O\u001a\u00020\b2\u0006\u0010N\u001a\u00020\u0017¢\u0006\u0004\bO\u0010#J\u0015\u0010P\u001a\u00020\b2\u0006\u0010!\u001a\u00020\u0017¢\u0006\u0004\bP\u0010#J\u001d\u0010S\u001a\u00020\b2\u000e\u0010R\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160Q¢\u0006\u0004\bS\u0010TJ\u001b\u0010W\u001a\u00020\b2\f\u0010V\u001a\b\u0012\u0004\u0012\u00020\b0U¢\u0006\u0004\bW\u0010XJ\u001d\u0010[\u001a\u00020\b2\u0006\u0010Z\u001a\u00020Y2\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b[\u0010\\J%\u0010_\u001a\u00020\b2\u000e\u0010R\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160]2\u0006\u0010^\u001a\u00020Y¢\u0006\u0004\b_\u0010`J/\u0010f\u001a\u00020\b2\b\u0010b\u001a\u0004\u0018\u00010a2\u0006\u0010d\u001a\u00020c2\u0006\u00100\u001a\u00020e2\u0006\u0010K\u001a\u00020e¢\u0006\u0004\bf\u0010gJ%\u0010j\u001a\u00020\b2\u0006\u0010;\u001a\u00020h2\u0006\u0010d\u001a\u00020c2\u0006\u0010i\u001a\u00020e¢\u0006\u0004\bj\u0010kJ\r\u0010l\u001a\u00020\b¢\u0006\u0004\bl\u0010\u0003J!\u0010n\u001a\u00020\b2\u0006\u0010m\u001a\u00020\u00002\n\b\u0002\u0010^\u001a\u0004\u0018\u00010Y¢\u0006\u0004\bn\u0010oJ\u0017\u0010r\u001a\u00020p2\u0006\u0010q\u001a\u00020pH\u0016¢\u0006\u0004\br\u0010sR\u0014\u0010u\u001a\u00020t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bu\u0010vR\u0011\u0010y\u001a\u00020\u00178F¢\u0006\u0006\u001a\u0004\bw\u0010x¨\u0006z"}, d2 = {"Landroidx/compose/runtime/changelist/ChangeList;", "Landroidx/compose/runtime/changelist/OperationsDebugStringFormattable;", "<init>", "()V", "", "isEmpty", "()Z", "isNotEmpty", "Lx6/t0;", "clear", "Landroidx/compose/runtime/Applier;", "applier", "Landroidx/compose/runtime/SlotWriter;", "slots", "Landroidx/compose/runtime/RememberManager;", "rememberManager", "executeAndFlushAllPendingChanges", "(Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/SlotWriter;Landroidx/compose/runtime/RememberManager;)V", "Landroidx/compose/runtime/RememberObserver;", "value", "pushRemember", "(Landroidx/compose/runtime/RememberObserver;)V", "", "", "groupSlotIndex", "pushUpdateValue", "(Ljava/lang/Object;I)V", "Landroidx/compose/runtime/Anchor;", LinkHeader.Parameters.Anchor, "pushUpdateAnchoredValue", "(Ljava/lang/Object;Landroidx/compose/runtime/Anchor;I)V", "pushAppendValue", "(Landroidx/compose/runtime/Anchor;Ljava/lang/Object;)V", "count", "pushTrimValues", "(I)V", "pushResetSlots", "pushDeactivateCurrentGroup", "data", "pushUpdateAuxData", "(Ljava/lang/Object;)V", "pushEnsureRootStarted", "pushEnsureGroupStarted", "(Landroidx/compose/runtime/Anchor;)V", "pushEndCurrentGroup", "pushSkipToEndOfCurrentGroup", "pushRemoveCurrentGroup", "Landroidx/compose/runtime/SlotTable;", "from", "pushInsertSlots", "(Landroidx/compose/runtime/Anchor;Landroidx/compose/runtime/SlotTable;)V", "Landroidx/compose/runtime/changelist/FixupList;", "fixups", "(Landroidx/compose/runtime/Anchor;Landroidx/compose/runtime/SlotTable;Landroidx/compose/runtime/changelist/FixupList;)V", "offset", "pushMoveCurrentGroup", "Lkotlin/Function1;", "Landroidx/compose/runtime/Composition;", "action", "composition", "pushEndCompositionScope", "(Lr7/l;Landroidx/compose/runtime/Composition;)V", "node", "pushUseNode", "T", "V", "Lkotlin/Function2;", "Lx6/n;", "block", "pushUpdateNode", "(Ljava/lang/Object;Lr7/p;)V", "removeFrom", "moveCount", "pushRemoveNode", "(II)V", "to", "pushMoveNode", "(III)V", "distance", "pushAdvanceSlotsBy", "pushUps", "", "nodes", "pushDowns", "([Ljava/lang/Object;)V", "Lkotlin/Function0;", "effect", "pushSideEffect", "(Lr7/a;)V", "Landroidx/compose/runtime/internal/IntRef;", "effectiveNodeIndexOut", "pushDetermineMovableContentNodeIndex", "(Landroidx/compose/runtime/internal/IntRef;Landroidx/compose/runtime/Anchor;)V", "", "effectiveNodeIndex", "pushCopyNodesToNewAnchorLocation", "(Ljava/util/List;Landroidx/compose/runtime/internal/IntRef;)V", "Landroidx/compose/runtime/MovableContentState;", "resolvedState", "Landroidx/compose/runtime/CompositionContext;", "parentContext", "Landroidx/compose/runtime/MovableContentStateReference;", "pushCopySlotTableToAnchorLocation", "(Landroidx/compose/runtime/MovableContentState;Landroidx/compose/runtime/CompositionContext;Landroidx/compose/runtime/MovableContentStateReference;Landroidx/compose/runtime/MovableContentStateReference;)V", "Landroidx/compose/runtime/ControlledComposition;", "reference", "pushReleaseMovableGroupAtCurrent", "(Landroidx/compose/runtime/ControlledComposition;Landroidx/compose/runtime/CompositionContext;Landroidx/compose/runtime/MovableContentStateReference;)V", "pushEndMovableContentPlacement", "changeList", "pushExecuteOperationsIn", "(Landroidx/compose/runtime/changelist/ChangeList;Landroidx/compose/runtime/internal/IntRef;)V", "", "linePrefix", "toDebugString", "(Ljava/lang/String;)Ljava/lang/String;", "Landroidx/compose/runtime/changelist/Operations;", "operations", "Landroidx/compose/runtime/changelist/Operations;", "getSize", "()I", ContentDisposition.Parameters.Size, "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ChangeList extends OperationsDebugStringFormattable {
    public static final int $stable = 8;
    private final Operations operations = new Operations();

    public static /* synthetic */ void pushExecuteOperationsIn$default(ChangeList changeList, ChangeList changeList2, IntRef intRef, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            intRef = null;
        }
        changeList.pushExecuteOperationsIn(changeList2, intRef);
    }

    public final void clear() {
        this.operations.clear();
    }

    public final void executeAndFlushAllPendingChanges(Applier<?> applier, SlotWriter slots, RememberManager rememberManager) {
        this.operations.executeAndFlushAllPendingOperations(applier, slots, rememberManager);
    }

    public final int getSize() {
        return this.operations.getOpCodesSize();
    }

    public final boolean isEmpty() {
        return this.operations.isEmpty();
    }

    public final boolean isNotEmpty() {
        return this.operations.isNotEmpty();
    }

    public final void pushAdvanceSlotsBy(int distance) {
        Operations operations = this.operations;
        Operation.AdvanceSlotsBy advanceSlotsBy = Operation.AdvanceSlotsBy.INSTANCE;
        operations.pushOp(advanceSlotsBy);
        Operations.WriteScope.m3079setIntA6tL2VI(Operations.WriteScope.m3074constructorimpl(operations), Operation.IntParameter.m3031constructorimpl(0), distance);
        if (operations.pushedIntMask == operations.createExpectedArgMask(advanceSlotsBy.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(advanceSlotsBy.getObjects())) {
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        int ints = advanceSlotsBy.getInts();
        int i10 = 0;
        for (int i11 = 0; i11 < ints; i11++) {
            if (((1 << i11) & operations.pushedIntMask) != 0) {
                if (i10 > 0) {
                    sb2.append(", ");
                }
                sb2.append(advanceSlotsBy.mo3003intParamNamew8GmfQM(Operation.IntParameter.m3031constructorimpl(i11)));
                i10++;
            }
        }
        String string = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        int objects = advanceSlotsBy.getObjects();
        int i12 = 0;
        for (int i13 = 0; i13 < objects; i13++) {
            if (((1 << i13) & operations.pushedObjectMask) != 0) {
                if (i10 > 0) {
                    sb3.append(", ");
                }
                sb3.append(advanceSlotsBy.mo3004objectParamName31yXWZQ(Operation.ObjectParameter.m3042constructorimpl(i13)));
                i12++;
            }
        }
        String string2 = sb3.toString();
        StringBuilder sb4 = new StringBuilder("Error while pushing ");
        sb4.append(advanceSlotsBy);
        sb4.append(". Not all arguments were provided. Missing ");
        sb4.append(i10);
        sb4.append(" int arguments (");
        d.A(sb4, string, ") and ", i12, " object arguments (");
        d.z(sb4, string2, ").");
    }

    public final void pushAppendValue(Anchor anchor, Object value) {
        Operations operations = this.operations;
        Operation.AppendValue appendValue = Operation.AppendValue.INSTANCE;
        operations.pushOp(appendValue);
        Operations operationsM3074constructorimpl = Operations.WriteScope.m3074constructorimpl(operations);
        Operations.WriteScope.m3080setObjectDKhxnng(operationsM3074constructorimpl, Operation.ObjectParameter.m3042constructorimpl(0), anchor);
        Operations.WriteScope.m3080setObjectDKhxnng(operationsM3074constructorimpl, Operation.ObjectParameter.m3042constructorimpl(1), value);
        if (operations.pushedIntMask == operations.createExpectedArgMask(appendValue.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(appendValue.getObjects())) {
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        int ints = appendValue.getInts();
        int i10 = 0;
        for (int i11 = 0; i11 < ints; i11++) {
            if (((1 << i11) & operations.pushedIntMask) != 0) {
                if (i10 > 0) {
                    sb2.append(", ");
                }
                sb2.append(appendValue.mo3003intParamNamew8GmfQM(Operation.IntParameter.m3031constructorimpl(i11)));
                i10++;
            }
        }
        String string = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        int objects = appendValue.getObjects();
        int i12 = 0;
        for (int i13 = 0; i13 < objects; i13++) {
            if (((1 << i13) & operations.pushedObjectMask) != 0) {
                if (i10 > 0) {
                    sb3.append(", ");
                }
                sb3.append(appendValue.mo3004objectParamName31yXWZQ(Operation.ObjectParameter.m3042constructorimpl(i13)));
                i12++;
            }
        }
        String string2 = sb3.toString();
        StringBuilder sb4 = new StringBuilder("Error while pushing ");
        sb4.append(appendValue);
        sb4.append(". Not all arguments were provided. Missing ");
        sb4.append(i10);
        sb4.append(" int arguments (");
        d.A(sb4, string, ") and ", i12, " object arguments (");
        d.z(sb4, string2, ").");
    }

    public final void pushCopyNodesToNewAnchorLocation(List<? extends Object> nodes, IntRef effectiveNodeIndex) {
        if (nodes.isEmpty()) {
            return;
        }
        Operations operations = this.operations;
        Operation.CopyNodesToNewAnchorLocation copyNodesToNewAnchorLocation = Operation.CopyNodesToNewAnchorLocation.INSTANCE;
        operations.pushOp(copyNodesToNewAnchorLocation);
        Operations operationsM3074constructorimpl = Operations.WriteScope.m3074constructorimpl(operations);
        Operations.WriteScope.m3080setObjectDKhxnng(operationsM3074constructorimpl, Operation.ObjectParameter.m3042constructorimpl(1), nodes);
        Operations.WriteScope.m3080setObjectDKhxnng(operationsM3074constructorimpl, Operation.ObjectParameter.m3042constructorimpl(0), effectiveNodeIndex);
        if (operations.pushedIntMask == operations.createExpectedArgMask(copyNodesToNewAnchorLocation.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(copyNodesToNewAnchorLocation.getObjects())) {
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        int ints = copyNodesToNewAnchorLocation.getInts();
        int i10 = 0;
        for (int i11 = 0; i11 < ints; i11++) {
            if (((1 << i11) & operations.pushedIntMask) != 0) {
                if (i10 > 0) {
                    sb2.append(", ");
                }
                sb2.append(copyNodesToNewAnchorLocation.mo3003intParamNamew8GmfQM(Operation.IntParameter.m3031constructorimpl(i11)));
                i10++;
            }
        }
        String string = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        int objects = copyNodesToNewAnchorLocation.getObjects();
        int i12 = 0;
        for (int i13 = 0; i13 < objects; i13++) {
            if (((1 << i13) & operations.pushedObjectMask) != 0) {
                if (i10 > 0) {
                    sb3.append(", ");
                }
                sb3.append(copyNodesToNewAnchorLocation.mo3004objectParamName31yXWZQ(Operation.ObjectParameter.m3042constructorimpl(i13)));
                i12++;
            }
        }
        String string2 = sb3.toString();
        StringBuilder sb4 = new StringBuilder("Error while pushing ");
        sb4.append(copyNodesToNewAnchorLocation);
        sb4.append(". Not all arguments were provided. Missing ");
        sb4.append(i10);
        sb4.append(" int arguments (");
        d.A(sb4, string, ") and ", i12, " object arguments (");
        d.z(sb4, string2, ").");
    }

    public final void pushCopySlotTableToAnchorLocation(MovableContentState resolvedState, CompositionContext parentContext, MovableContentStateReference from, MovableContentStateReference to) {
        Operations operations = this.operations;
        Operation.CopySlotTableToAnchorLocation copySlotTableToAnchorLocation = Operation.CopySlotTableToAnchorLocation.INSTANCE;
        operations.pushOp(copySlotTableToAnchorLocation);
        Operations operationsM3074constructorimpl = Operations.WriteScope.m3074constructorimpl(operations);
        Operations.WriteScope.m3080setObjectDKhxnng(operationsM3074constructorimpl, Operation.ObjectParameter.m3042constructorimpl(0), resolvedState);
        Operations.WriteScope.m3080setObjectDKhxnng(operationsM3074constructorimpl, Operation.ObjectParameter.m3042constructorimpl(1), parentContext);
        Operations.WriteScope.m3080setObjectDKhxnng(operationsM3074constructorimpl, Operation.ObjectParameter.m3042constructorimpl(3), to);
        Operations.WriteScope.m3080setObjectDKhxnng(operationsM3074constructorimpl, Operation.ObjectParameter.m3042constructorimpl(2), from);
        if (operations.pushedIntMask == operations.createExpectedArgMask(copySlotTableToAnchorLocation.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(copySlotTableToAnchorLocation.getObjects())) {
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        int ints = copySlotTableToAnchorLocation.getInts();
        int i10 = 0;
        for (int i11 = 0; i11 < ints; i11++) {
            if (((1 << i11) & operations.pushedIntMask) != 0) {
                if (i10 > 0) {
                    sb2.append(", ");
                }
                sb2.append(copySlotTableToAnchorLocation.mo3003intParamNamew8GmfQM(Operation.IntParameter.m3031constructorimpl(i11)));
                i10++;
            }
        }
        String string = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        int objects = copySlotTableToAnchorLocation.getObjects();
        int i12 = 0;
        for (int i13 = 0; i13 < objects; i13++) {
            if (((1 << i13) & operations.pushedObjectMask) != 0) {
                if (i10 > 0) {
                    sb3.append(", ");
                }
                sb3.append(copySlotTableToAnchorLocation.mo3004objectParamName31yXWZQ(Operation.ObjectParameter.m3042constructorimpl(i13)));
                i12++;
            }
        }
        String string2 = sb3.toString();
        StringBuilder sb4 = new StringBuilder("Error while pushing ");
        sb4.append(copySlotTableToAnchorLocation);
        sb4.append(". Not all arguments were provided. Missing ");
        sb4.append(i10);
        sb4.append(" int arguments (");
        d.A(sb4, string, ") and ", i12, " object arguments (");
        d.z(sb4, string2, ").");
    }

    public final void pushDeactivateCurrentGroup() {
        this.operations.push(Operation.DeactivateCurrentGroup.INSTANCE);
    }

    public final void pushDetermineMovableContentNodeIndex(IntRef effectiveNodeIndexOut, Anchor anchor) {
        Operations operations = this.operations;
        Operation.DetermineMovableContentNodeIndex determineMovableContentNodeIndex = Operation.DetermineMovableContentNodeIndex.INSTANCE;
        operations.pushOp(determineMovableContentNodeIndex);
        Operations operationsM3074constructorimpl = Operations.WriteScope.m3074constructorimpl(operations);
        Operations.WriteScope.m3080setObjectDKhxnng(operationsM3074constructorimpl, Operation.ObjectParameter.m3042constructorimpl(0), effectiveNodeIndexOut);
        Operations.WriteScope.m3080setObjectDKhxnng(operationsM3074constructorimpl, Operation.ObjectParameter.m3042constructorimpl(1), anchor);
        if (operations.pushedIntMask == operations.createExpectedArgMask(determineMovableContentNodeIndex.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(determineMovableContentNodeIndex.getObjects())) {
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        int ints = determineMovableContentNodeIndex.getInts();
        int i10 = 0;
        for (int i11 = 0; i11 < ints; i11++) {
            if (((1 << i11) & operations.pushedIntMask) != 0) {
                if (i10 > 0) {
                    sb2.append(", ");
                }
                sb2.append(determineMovableContentNodeIndex.mo3003intParamNamew8GmfQM(Operation.IntParameter.m3031constructorimpl(i11)));
                i10++;
            }
        }
        String string = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        int objects = determineMovableContentNodeIndex.getObjects();
        int i12 = 0;
        for (int i13 = 0; i13 < objects; i13++) {
            if (((1 << i13) & operations.pushedObjectMask) != 0) {
                if (i10 > 0) {
                    sb3.append(", ");
                }
                sb3.append(determineMovableContentNodeIndex.mo3004objectParamName31yXWZQ(Operation.ObjectParameter.m3042constructorimpl(i13)));
                i12++;
            }
        }
        String string2 = sb3.toString();
        StringBuilder sb4 = new StringBuilder("Error while pushing ");
        sb4.append(determineMovableContentNodeIndex);
        sb4.append(". Not all arguments were provided. Missing ");
        sb4.append(i10);
        sb4.append(" int arguments (");
        d.A(sb4, string, ") and ", i12, " object arguments (");
        d.z(sb4, string2, ").");
    }

    public final void pushDowns(Object[] nodes) {
        if (nodes.length == 0) {
            return;
        }
        Operations operations = this.operations;
        Operation.Downs downs = Operation.Downs.INSTANCE;
        operations.pushOp(downs);
        Operations.WriteScope.m3080setObjectDKhxnng(Operations.WriteScope.m3074constructorimpl(operations), Operation.ObjectParameter.m3042constructorimpl(0), nodes);
        if (operations.pushedIntMask == operations.createExpectedArgMask(downs.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(downs.getObjects())) {
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        int ints = downs.getInts();
        int i10 = 0;
        for (int i11 = 0; i11 < ints; i11++) {
            if (((1 << i11) & operations.pushedIntMask) != 0) {
                if (i10 > 0) {
                    sb2.append(", ");
                }
                sb2.append(downs.mo3003intParamNamew8GmfQM(Operation.IntParameter.m3031constructorimpl(i11)));
                i10++;
            }
        }
        String string = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        int objects = downs.getObjects();
        int i12 = 0;
        for (int i13 = 0; i13 < objects; i13++) {
            if (((1 << i13) & operations.pushedObjectMask) != 0) {
                if (i10 > 0) {
                    sb3.append(", ");
                }
                sb3.append(downs.mo3004objectParamName31yXWZQ(Operation.ObjectParameter.m3042constructorimpl(i13)));
                i12++;
            }
        }
        String string2 = sb3.toString();
        StringBuilder sb4 = new StringBuilder("Error while pushing ");
        sb4.append(downs);
        sb4.append(". Not all arguments were provided. Missing ");
        sb4.append(i10);
        sb4.append(" int arguments (");
        d.A(sb4, string, ") and ", i12, " object arguments (");
        d.z(sb4, string2, ").");
    }

    public final void pushEndCompositionScope(l<? super Composition, t0> action, Composition composition) {
        Operations operations = this.operations;
        Operation.EndCompositionScope endCompositionScope = Operation.EndCompositionScope.INSTANCE;
        operations.pushOp(endCompositionScope);
        Operations operationsM3074constructorimpl = Operations.WriteScope.m3074constructorimpl(operations);
        Operations.WriteScope.m3080setObjectDKhxnng(operationsM3074constructorimpl, Operation.ObjectParameter.m3042constructorimpl(0), action);
        Operations.WriteScope.m3080setObjectDKhxnng(operationsM3074constructorimpl, Operation.ObjectParameter.m3042constructorimpl(1), composition);
        if (operations.pushedIntMask == operations.createExpectedArgMask(endCompositionScope.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(endCompositionScope.getObjects())) {
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        int ints = endCompositionScope.getInts();
        int i10 = 0;
        for (int i11 = 0; i11 < ints; i11++) {
            if (((1 << i11) & operations.pushedIntMask) != 0) {
                if (i10 > 0) {
                    sb2.append(", ");
                }
                sb2.append(endCompositionScope.mo3003intParamNamew8GmfQM(Operation.IntParameter.m3031constructorimpl(i11)));
                i10++;
            }
        }
        String string = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        int objects = endCompositionScope.getObjects();
        int i12 = 0;
        for (int i13 = 0; i13 < objects; i13++) {
            if (((1 << i13) & operations.pushedObjectMask) != 0) {
                if (i10 > 0) {
                    sb3.append(", ");
                }
                sb3.append(endCompositionScope.mo3004objectParamName31yXWZQ(Operation.ObjectParameter.m3042constructorimpl(i13)));
                i12++;
            }
        }
        String string2 = sb3.toString();
        StringBuilder sb4 = new StringBuilder("Error while pushing ");
        sb4.append(endCompositionScope);
        sb4.append(". Not all arguments were provided. Missing ");
        sb4.append(i10);
        sb4.append(" int arguments (");
        d.A(sb4, string, ") and ", i12, " object arguments (");
        d.z(sb4, string2, ").");
    }

    public final void pushEndCurrentGroup() {
        this.operations.push(Operation.EndCurrentGroup.INSTANCE);
    }

    public final void pushEndMovableContentPlacement() {
        this.operations.push(Operation.EndMovableContentPlacement.INSTANCE);
    }

    public final void pushEnsureGroupStarted(Anchor anchor) {
        Operations operations = this.operations;
        Operation.EnsureGroupStarted ensureGroupStarted = Operation.EnsureGroupStarted.INSTANCE;
        operations.pushOp(ensureGroupStarted);
        Operations.WriteScope.m3080setObjectDKhxnng(Operations.WriteScope.m3074constructorimpl(operations), Operation.ObjectParameter.m3042constructorimpl(0), anchor);
        if (operations.pushedIntMask == operations.createExpectedArgMask(ensureGroupStarted.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(ensureGroupStarted.getObjects())) {
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        int ints = ensureGroupStarted.getInts();
        int i10 = 0;
        for (int i11 = 0; i11 < ints; i11++) {
            if (((1 << i11) & operations.pushedIntMask) != 0) {
                if (i10 > 0) {
                    sb2.append(", ");
                }
                sb2.append(ensureGroupStarted.mo3003intParamNamew8GmfQM(Operation.IntParameter.m3031constructorimpl(i11)));
                i10++;
            }
        }
        String string = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        int objects = ensureGroupStarted.getObjects();
        int i12 = 0;
        for (int i13 = 0; i13 < objects; i13++) {
            if (((1 << i13) & operations.pushedObjectMask) != 0) {
                if (i10 > 0) {
                    sb3.append(", ");
                }
                sb3.append(ensureGroupStarted.mo3004objectParamName31yXWZQ(Operation.ObjectParameter.m3042constructorimpl(i13)));
                i12++;
            }
        }
        String string2 = sb3.toString();
        StringBuilder sb4 = new StringBuilder("Error while pushing ");
        sb4.append(ensureGroupStarted);
        sb4.append(". Not all arguments were provided. Missing ");
        sb4.append(i10);
        sb4.append(" int arguments (");
        d.A(sb4, string, ") and ", i12, " object arguments (");
        d.z(sb4, string2, ").");
    }

    public final void pushEnsureRootStarted() {
        this.operations.push(Operation.EnsureRootGroupStarted.INSTANCE);
    }

    public final void pushExecuteOperationsIn(ChangeList changeList, IntRef effectiveNodeIndex) {
        if (changeList.isNotEmpty()) {
            Operations operations = this.operations;
            Operation.ApplyChangeList applyChangeList = Operation.ApplyChangeList.INSTANCE;
            operations.pushOp(applyChangeList);
            Operations operationsM3074constructorimpl = Operations.WriteScope.m3074constructorimpl(operations);
            Operations.WriteScope.m3080setObjectDKhxnng(operationsM3074constructorimpl, Operation.ObjectParameter.m3042constructorimpl(0), changeList);
            Operations.WriteScope.m3080setObjectDKhxnng(operationsM3074constructorimpl, Operation.ObjectParameter.m3042constructorimpl(1), effectiveNodeIndex);
            if (operations.pushedIntMask == operations.createExpectedArgMask(applyChangeList.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(applyChangeList.getObjects())) {
                return;
            }
            StringBuilder sb2 = new StringBuilder();
            int ints = applyChangeList.getInts();
            int i10 = 0;
            for (int i11 = 0; i11 < ints; i11++) {
                if (((1 << i11) & operations.pushedIntMask) != 0) {
                    if (i10 > 0) {
                        sb2.append(", ");
                    }
                    sb2.append(applyChangeList.mo3003intParamNamew8GmfQM(Operation.IntParameter.m3031constructorimpl(i11)));
                    i10++;
                }
            }
            String string = sb2.toString();
            StringBuilder sb3 = new StringBuilder();
            int objects = applyChangeList.getObjects();
            int i12 = 0;
            for (int i13 = 0; i13 < objects; i13++) {
                if (((1 << i13) & operations.pushedObjectMask) != 0) {
                    if (i10 > 0) {
                        sb3.append(", ");
                    }
                    sb3.append(applyChangeList.mo3004objectParamName31yXWZQ(Operation.ObjectParameter.m3042constructorimpl(i13)));
                    i12++;
                }
            }
            String string2 = sb3.toString();
            StringBuilder sb4 = new StringBuilder("Error while pushing ");
            sb4.append(applyChangeList);
            sb4.append(". Not all arguments were provided. Missing ");
            sb4.append(i10);
            sb4.append(" int arguments (");
            d.A(sb4, string, ") and ", i12, " object arguments (");
            d.z(sb4, string2, ").");
        }
    }

    public final void pushInsertSlots(Anchor anchor, SlotTable from) {
        Operations operations = this.operations;
        Operation.InsertSlots insertSlots = Operation.InsertSlots.INSTANCE;
        operations.pushOp(insertSlots);
        Operations operationsM3074constructorimpl = Operations.WriteScope.m3074constructorimpl(operations);
        Operations.WriteScope.m3080setObjectDKhxnng(operationsM3074constructorimpl, Operation.ObjectParameter.m3042constructorimpl(0), anchor);
        Operations.WriteScope.m3080setObjectDKhxnng(operationsM3074constructorimpl, Operation.ObjectParameter.m3042constructorimpl(1), from);
        if (operations.pushedIntMask == operations.createExpectedArgMask(insertSlots.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(insertSlots.getObjects())) {
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        int ints = insertSlots.getInts();
        int i10 = 0;
        for (int i11 = 0; i11 < ints; i11++) {
            if (((1 << i11) & operations.pushedIntMask) != 0) {
                if (i10 > 0) {
                    sb2.append(", ");
                }
                sb2.append(insertSlots.mo3003intParamNamew8GmfQM(Operation.IntParameter.m3031constructorimpl(i11)));
                i10++;
            }
        }
        String string = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        int objects = insertSlots.getObjects();
        int i12 = 0;
        for (int i13 = 0; i13 < objects; i13++) {
            if (((1 << i13) & operations.pushedObjectMask) != 0) {
                if (i10 > 0) {
                    sb3.append(", ");
                }
                sb3.append(insertSlots.mo3004objectParamName31yXWZQ(Operation.ObjectParameter.m3042constructorimpl(i13)));
                i12++;
            }
        }
        String string2 = sb3.toString();
        StringBuilder sb4 = new StringBuilder("Error while pushing ");
        sb4.append(insertSlots);
        sb4.append(". Not all arguments were provided. Missing ");
        sb4.append(i10);
        sb4.append(" int arguments (");
        d.A(sb4, string, ") and ", i12, " object arguments (");
        d.z(sb4, string2, ").");
    }

    public final void pushMoveCurrentGroup(int offset) {
        Operations operations = this.operations;
        Operation.MoveCurrentGroup moveCurrentGroup = Operation.MoveCurrentGroup.INSTANCE;
        operations.pushOp(moveCurrentGroup);
        Operations.WriteScope.m3079setIntA6tL2VI(Operations.WriteScope.m3074constructorimpl(operations), Operation.IntParameter.m3031constructorimpl(0), offset);
        if (operations.pushedIntMask == operations.createExpectedArgMask(moveCurrentGroup.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(moveCurrentGroup.getObjects())) {
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        int ints = moveCurrentGroup.getInts();
        int i10 = 0;
        for (int i11 = 0; i11 < ints; i11++) {
            if (((1 << i11) & operations.pushedIntMask) != 0) {
                if (i10 > 0) {
                    sb2.append(", ");
                }
                sb2.append(moveCurrentGroup.mo3003intParamNamew8GmfQM(Operation.IntParameter.m3031constructorimpl(i11)));
                i10++;
            }
        }
        String string = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        int objects = moveCurrentGroup.getObjects();
        int i12 = 0;
        for (int i13 = 0; i13 < objects; i13++) {
            if (((1 << i13) & operations.pushedObjectMask) != 0) {
                if (i10 > 0) {
                    sb3.append(", ");
                }
                sb3.append(moveCurrentGroup.mo3004objectParamName31yXWZQ(Operation.ObjectParameter.m3042constructorimpl(i13)));
                i12++;
            }
        }
        String string2 = sb3.toString();
        StringBuilder sb4 = new StringBuilder("Error while pushing ");
        sb4.append(moveCurrentGroup);
        sb4.append(". Not all arguments were provided. Missing ");
        sb4.append(i10);
        sb4.append(" int arguments (");
        d.A(sb4, string, ") and ", i12, " object arguments (");
        d.z(sb4, string2, ").");
    }

    public final void pushMoveNode(int to, int from, int count) {
        Operations operations = this.operations;
        Operation.MoveNode moveNode = Operation.MoveNode.INSTANCE;
        operations.pushOp(moveNode);
        Operations operationsM3074constructorimpl = Operations.WriteScope.m3074constructorimpl(operations);
        Operations.WriteScope.m3079setIntA6tL2VI(operationsM3074constructorimpl, Operation.IntParameter.m3031constructorimpl(1), to);
        Operations.WriteScope.m3079setIntA6tL2VI(operationsM3074constructorimpl, Operation.IntParameter.m3031constructorimpl(0), from);
        Operations.WriteScope.m3079setIntA6tL2VI(operationsM3074constructorimpl, Operation.IntParameter.m3031constructorimpl(2), count);
        if (operations.pushedIntMask == operations.createExpectedArgMask(moveNode.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(moveNode.getObjects())) {
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        int ints = moveNode.getInts();
        int i10 = 0;
        for (int i11 = 0; i11 < ints; i11++) {
            if (((1 << i11) & operations.pushedIntMask) != 0) {
                if (i10 > 0) {
                    sb2.append(", ");
                }
                sb2.append(moveNode.mo3003intParamNamew8GmfQM(Operation.IntParameter.m3031constructorimpl(i11)));
                i10++;
            }
        }
        String string = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        int objects = moveNode.getObjects();
        int i12 = 0;
        for (int i13 = 0; i13 < objects; i13++) {
            if (((1 << i13) & operations.pushedObjectMask) != 0) {
                if (i10 > 0) {
                    sb3.append(", ");
                }
                sb3.append(moveNode.mo3004objectParamName31yXWZQ(Operation.ObjectParameter.m3042constructorimpl(i13)));
                i12++;
            }
        }
        String string2 = sb3.toString();
        StringBuilder sb4 = new StringBuilder("Error while pushing ");
        sb4.append(moveNode);
        sb4.append(". Not all arguments were provided. Missing ");
        sb4.append(i10);
        sb4.append(" int arguments (");
        d.A(sb4, string, ") and ", i12, " object arguments (");
        d.z(sb4, string2, ").");
    }

    public final void pushReleaseMovableGroupAtCurrent(ControlledComposition composition, CompositionContext parentContext, MovableContentStateReference reference) {
        Operations operations = this.operations;
        Operation.ReleaseMovableGroupAtCurrent releaseMovableGroupAtCurrent = Operation.ReleaseMovableGroupAtCurrent.INSTANCE;
        operations.pushOp(releaseMovableGroupAtCurrent);
        Operations operationsM3074constructorimpl = Operations.WriteScope.m3074constructorimpl(operations);
        Operations.WriteScope.m3080setObjectDKhxnng(operationsM3074constructorimpl, Operation.ObjectParameter.m3042constructorimpl(0), composition);
        Operations.WriteScope.m3080setObjectDKhxnng(operationsM3074constructorimpl, Operation.ObjectParameter.m3042constructorimpl(1), parentContext);
        Operations.WriteScope.m3080setObjectDKhxnng(operationsM3074constructorimpl, Operation.ObjectParameter.m3042constructorimpl(2), reference);
        if (operations.pushedIntMask == operations.createExpectedArgMask(releaseMovableGroupAtCurrent.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(releaseMovableGroupAtCurrent.getObjects())) {
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        int ints = releaseMovableGroupAtCurrent.getInts();
        int i10 = 0;
        for (int i11 = 0; i11 < ints; i11++) {
            if (((1 << i11) & operations.pushedIntMask) != 0) {
                if (i10 > 0) {
                    sb2.append(", ");
                }
                sb2.append(releaseMovableGroupAtCurrent.mo3003intParamNamew8GmfQM(Operation.IntParameter.m3031constructorimpl(i11)));
                i10++;
            }
        }
        String string = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        int objects = releaseMovableGroupAtCurrent.getObjects();
        int i12 = 0;
        for (int i13 = 0; i13 < objects; i13++) {
            if (((1 << i13) & operations.pushedObjectMask) != 0) {
                if (i10 > 0) {
                    sb3.append(", ");
                }
                sb3.append(releaseMovableGroupAtCurrent.mo3004objectParamName31yXWZQ(Operation.ObjectParameter.m3042constructorimpl(i13)));
                i12++;
            }
        }
        String string2 = sb3.toString();
        StringBuilder sb4 = new StringBuilder("Error while pushing ");
        sb4.append(releaseMovableGroupAtCurrent);
        sb4.append(". Not all arguments were provided. Missing ");
        sb4.append(i10);
        sb4.append(" int arguments (");
        d.A(sb4, string, ") and ", i12, " object arguments (");
        d.z(sb4, string2, ").");
    }

    public final void pushRemember(RememberObserver value) {
        Operations operations = this.operations;
        Operation.Remember remember = Operation.Remember.INSTANCE;
        operations.pushOp(remember);
        Operations.WriteScope.m3080setObjectDKhxnng(Operations.WriteScope.m3074constructorimpl(operations), Operation.ObjectParameter.m3042constructorimpl(0), value);
        if (operations.pushedIntMask == operations.createExpectedArgMask(remember.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(remember.getObjects())) {
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        int ints = remember.getInts();
        int i10 = 0;
        for (int i11 = 0; i11 < ints; i11++) {
            if (((1 << i11) & operations.pushedIntMask) != 0) {
                if (i10 > 0) {
                    sb2.append(", ");
                }
                sb2.append(remember.mo3003intParamNamew8GmfQM(Operation.IntParameter.m3031constructorimpl(i11)));
                i10++;
            }
        }
        String string = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        int objects = remember.getObjects();
        int i12 = 0;
        for (int i13 = 0; i13 < objects; i13++) {
            if (((1 << i13) & operations.pushedObjectMask) != 0) {
                if (i10 > 0) {
                    sb3.append(", ");
                }
                sb3.append(remember.mo3004objectParamName31yXWZQ(Operation.ObjectParameter.m3042constructorimpl(i13)));
                i12++;
            }
        }
        String string2 = sb3.toString();
        StringBuilder sb4 = new StringBuilder("Error while pushing ");
        sb4.append(remember);
        sb4.append(". Not all arguments were provided. Missing ");
        sb4.append(i10);
        sb4.append(" int arguments (");
        d.A(sb4, string, ") and ", i12, " object arguments (");
        d.z(sb4, string2, ").");
    }

    public final void pushRemoveCurrentGroup() {
        this.operations.push(Operation.RemoveCurrentGroup.INSTANCE);
    }

    public final void pushRemoveNode(int removeFrom, int moveCount) {
        Operations operations = this.operations;
        Operation.RemoveNode removeNode = Operation.RemoveNode.INSTANCE;
        operations.pushOp(removeNode);
        Operations operationsM3074constructorimpl = Operations.WriteScope.m3074constructorimpl(operations);
        Operations.WriteScope.m3079setIntA6tL2VI(operationsM3074constructorimpl, Operation.IntParameter.m3031constructorimpl(0), removeFrom);
        Operations.WriteScope.m3079setIntA6tL2VI(operationsM3074constructorimpl, Operation.IntParameter.m3031constructorimpl(1), moveCount);
        if (operations.pushedIntMask == operations.createExpectedArgMask(removeNode.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(removeNode.getObjects())) {
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        int ints = removeNode.getInts();
        int i10 = 0;
        for (int i11 = 0; i11 < ints; i11++) {
            if (((1 << i11) & operations.pushedIntMask) != 0) {
                if (i10 > 0) {
                    sb2.append(", ");
                }
                sb2.append(removeNode.mo3003intParamNamew8GmfQM(Operation.IntParameter.m3031constructorimpl(i11)));
                i10++;
            }
        }
        String string = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        int objects = removeNode.getObjects();
        int i12 = 0;
        for (int i13 = 0; i13 < objects; i13++) {
            if (((1 << i13) & operations.pushedObjectMask) != 0) {
                if (i10 > 0) {
                    sb3.append(", ");
                }
                sb3.append(removeNode.mo3004objectParamName31yXWZQ(Operation.ObjectParameter.m3042constructorimpl(i13)));
                i12++;
            }
        }
        String string2 = sb3.toString();
        StringBuilder sb4 = new StringBuilder("Error while pushing ");
        sb4.append(removeNode);
        sb4.append(". Not all arguments were provided. Missing ");
        sb4.append(i10);
        sb4.append(" int arguments (");
        d.A(sb4, string, ") and ", i12, " object arguments (");
        d.z(sb4, string2, ").");
    }

    public final void pushResetSlots() {
        this.operations.push(Operation.ResetSlots.INSTANCE);
    }

    public final void pushSideEffect(a<t0> effect) {
        Operations operations = this.operations;
        Operation.SideEffect sideEffect = Operation.SideEffect.INSTANCE;
        operations.pushOp(sideEffect);
        Operations.WriteScope.m3080setObjectDKhxnng(Operations.WriteScope.m3074constructorimpl(operations), Operation.ObjectParameter.m3042constructorimpl(0), effect);
        if (operations.pushedIntMask == operations.createExpectedArgMask(sideEffect.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(sideEffect.getObjects())) {
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        int ints = sideEffect.getInts();
        int i10 = 0;
        for (int i11 = 0; i11 < ints; i11++) {
            if (((1 << i11) & operations.pushedIntMask) != 0) {
                if (i10 > 0) {
                    sb2.append(", ");
                }
                sb2.append(sideEffect.mo3003intParamNamew8GmfQM(Operation.IntParameter.m3031constructorimpl(i11)));
                i10++;
            }
        }
        String string = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        int objects = sideEffect.getObjects();
        int i12 = 0;
        for (int i13 = 0; i13 < objects; i13++) {
            if (((1 << i13) & operations.pushedObjectMask) != 0) {
                if (i10 > 0) {
                    sb3.append(", ");
                }
                sb3.append(sideEffect.mo3004objectParamName31yXWZQ(Operation.ObjectParameter.m3042constructorimpl(i13)));
                i12++;
            }
        }
        String string2 = sb3.toString();
        StringBuilder sb4 = new StringBuilder("Error while pushing ");
        sb4.append(sideEffect);
        sb4.append(". Not all arguments were provided. Missing ");
        sb4.append(i10);
        sb4.append(" int arguments (");
        d.A(sb4, string, ") and ", i12, " object arguments (");
        d.z(sb4, string2, ").");
    }

    public final void pushSkipToEndOfCurrentGroup() {
        this.operations.push(Operation.SkipToEndOfCurrentGroup.INSTANCE);
    }

    public final void pushTrimValues(int count) {
        Operations operations = this.operations;
        Operation.TrimParentValues trimParentValues = Operation.TrimParentValues.INSTANCE;
        operations.pushOp(trimParentValues);
        Operations.WriteScope.m3079setIntA6tL2VI(Operations.WriteScope.m3074constructorimpl(operations), Operation.IntParameter.m3031constructorimpl(0), count);
        if (operations.pushedIntMask == operations.createExpectedArgMask(trimParentValues.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(trimParentValues.getObjects())) {
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        int ints = trimParentValues.getInts();
        int i10 = 0;
        for (int i11 = 0; i11 < ints; i11++) {
            if (((1 << i11) & operations.pushedIntMask) != 0) {
                if (i10 > 0) {
                    sb2.append(", ");
                }
                sb2.append(trimParentValues.mo3003intParamNamew8GmfQM(Operation.IntParameter.m3031constructorimpl(i11)));
                i10++;
            }
        }
        String string = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        int objects = trimParentValues.getObjects();
        int i12 = 0;
        for (int i13 = 0; i13 < objects; i13++) {
            if (((1 << i13) & operations.pushedObjectMask) != 0) {
                if (i10 > 0) {
                    sb3.append(", ");
                }
                sb3.append(trimParentValues.mo3004objectParamName31yXWZQ(Operation.ObjectParameter.m3042constructorimpl(i13)));
                i12++;
            }
        }
        String string2 = sb3.toString();
        StringBuilder sb4 = new StringBuilder("Error while pushing ");
        sb4.append(trimParentValues);
        sb4.append(". Not all arguments were provided. Missing ");
        sb4.append(i10);
        sb4.append(" int arguments (");
        d.A(sb4, string, ") and ", i12, " object arguments (");
        d.z(sb4, string2, ").");
    }

    public final void pushUpdateAnchoredValue(Object value, Anchor anchor, int groupSlotIndex) {
        Operations operations = this.operations;
        Operation.UpdateAnchoredValue updateAnchoredValue = Operation.UpdateAnchoredValue.INSTANCE;
        operations.pushOp(updateAnchoredValue);
        Operations operationsM3074constructorimpl = Operations.WriteScope.m3074constructorimpl(operations);
        Operations.WriteScope.m3080setObjectDKhxnng(operationsM3074constructorimpl, Operation.ObjectParameter.m3042constructorimpl(0), value);
        Operations.WriteScope.m3080setObjectDKhxnng(operationsM3074constructorimpl, Operation.ObjectParameter.m3042constructorimpl(1), anchor);
        Operations.WriteScope.m3079setIntA6tL2VI(operationsM3074constructorimpl, Operation.IntParameter.m3031constructorimpl(0), groupSlotIndex);
        if (operations.pushedIntMask == operations.createExpectedArgMask(updateAnchoredValue.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(updateAnchoredValue.getObjects())) {
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        int ints = updateAnchoredValue.getInts();
        int i10 = 0;
        for (int i11 = 0; i11 < ints; i11++) {
            if (((1 << i11) & operations.pushedIntMask) != 0) {
                if (i10 > 0) {
                    sb2.append(", ");
                }
                sb2.append(updateAnchoredValue.mo3003intParamNamew8GmfQM(Operation.IntParameter.m3031constructorimpl(i11)));
                i10++;
            }
        }
        String string = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        int objects = updateAnchoredValue.getObjects();
        int i12 = 0;
        for (int i13 = 0; i13 < objects; i13++) {
            if (((1 << i13) & operations.pushedObjectMask) != 0) {
                if (i10 > 0) {
                    sb3.append(", ");
                }
                sb3.append(updateAnchoredValue.mo3004objectParamName31yXWZQ(Operation.ObjectParameter.m3042constructorimpl(i13)));
                i12++;
            }
        }
        String string2 = sb3.toString();
        StringBuilder sb4 = new StringBuilder("Error while pushing ");
        sb4.append(updateAnchoredValue);
        sb4.append(". Not all arguments were provided. Missing ");
        sb4.append(i10);
        sb4.append(" int arguments (");
        d.A(sb4, string, ") and ", i12, " object arguments (");
        d.z(sb4, string2, ").");
    }

    public final void pushUpdateAuxData(Object data) {
        Operations operations = this.operations;
        Operation.UpdateAuxData updateAuxData = Operation.UpdateAuxData.INSTANCE;
        operations.pushOp(updateAuxData);
        Operations.WriteScope.m3080setObjectDKhxnng(Operations.WriteScope.m3074constructorimpl(operations), Operation.ObjectParameter.m3042constructorimpl(0), data);
        if (operations.pushedIntMask == operations.createExpectedArgMask(updateAuxData.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(updateAuxData.getObjects())) {
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        int ints = updateAuxData.getInts();
        int i10 = 0;
        for (int i11 = 0; i11 < ints; i11++) {
            if (((1 << i11) & operations.pushedIntMask) != 0) {
                if (i10 > 0) {
                    sb2.append(", ");
                }
                sb2.append(updateAuxData.mo3003intParamNamew8GmfQM(Operation.IntParameter.m3031constructorimpl(i11)));
                i10++;
            }
        }
        String string = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        int objects = updateAuxData.getObjects();
        int i12 = 0;
        for (int i13 = 0; i13 < objects; i13++) {
            if (((1 << i13) & operations.pushedObjectMask) != 0) {
                if (i10 > 0) {
                    sb3.append(", ");
                }
                sb3.append(updateAuxData.mo3004objectParamName31yXWZQ(Operation.ObjectParameter.m3042constructorimpl(i13)));
                i12++;
            }
        }
        String string2 = sb3.toString();
        StringBuilder sb4 = new StringBuilder("Error while pushing ");
        sb4.append(updateAuxData);
        sb4.append(". Not all arguments were provided. Missing ");
        sb4.append(i10);
        sb4.append(" int arguments (");
        d.A(sb4, string, ") and ", i12, " object arguments (");
        d.z(sb4, string2, ").");
    }

    public final <T, V> void pushUpdateNode(V value, p<? super T, ? super V, t0> block) {
        Operations operations = this.operations;
        Operation.UpdateNode updateNode = Operation.UpdateNode.INSTANCE;
        operations.pushOp(updateNode);
        Operations operationsM3074constructorimpl = Operations.WriteScope.m3074constructorimpl(operations);
        Operations.WriteScope.m3080setObjectDKhxnng(operationsM3074constructorimpl, Operation.ObjectParameter.m3042constructorimpl(0), value);
        int iM3042constructorimpl = Operation.ObjectParameter.m3042constructorimpl(1);
        q0.e(2, block);
        Operations.WriteScope.m3080setObjectDKhxnng(operationsM3074constructorimpl, iM3042constructorimpl, block);
        if (operations.pushedIntMask == operations.createExpectedArgMask(updateNode.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(updateNode.getObjects())) {
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        int ints = updateNode.getInts();
        int i10 = 0;
        for (int i11 = 0; i11 < ints; i11++) {
            if (((1 << i11) & operations.pushedIntMask) != 0) {
                if (i10 > 0) {
                    sb2.append(", ");
                }
                sb2.append(updateNode.mo3003intParamNamew8GmfQM(Operation.IntParameter.m3031constructorimpl(i11)));
                i10++;
            }
        }
        String string = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        int objects = updateNode.getObjects();
        int i12 = 0;
        for (int i13 = 0; i13 < objects; i13++) {
            if (((1 << i13) & operations.pushedObjectMask) != 0) {
                if (i10 > 0) {
                    sb3.append(", ");
                }
                sb3.append(updateNode.mo3004objectParamName31yXWZQ(Operation.ObjectParameter.m3042constructorimpl(i13)));
                i12++;
            }
        }
        String string2 = sb3.toString();
        StringBuilder sb4 = new StringBuilder("Error while pushing ");
        sb4.append(updateNode);
        sb4.append(". Not all arguments were provided. Missing ");
        sb4.append(i10);
        sb4.append(" int arguments (");
        d.A(sb4, string, ") and ", i12, " object arguments (");
        d.z(sb4, string2, ").");
    }

    public final void pushUpdateValue(Object value, int groupSlotIndex) {
        Operations operations = this.operations;
        Operation.UpdateValue updateValue = Operation.UpdateValue.INSTANCE;
        operations.pushOp(updateValue);
        Operations operationsM3074constructorimpl = Operations.WriteScope.m3074constructorimpl(operations);
        Operations.WriteScope.m3080setObjectDKhxnng(operationsM3074constructorimpl, Operation.ObjectParameter.m3042constructorimpl(0), value);
        Operations.WriteScope.m3079setIntA6tL2VI(operationsM3074constructorimpl, Operation.IntParameter.m3031constructorimpl(0), groupSlotIndex);
        if (operations.pushedIntMask == operations.createExpectedArgMask(updateValue.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(updateValue.getObjects())) {
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        int ints = updateValue.getInts();
        int i10 = 0;
        for (int i11 = 0; i11 < ints; i11++) {
            if (((1 << i11) & operations.pushedIntMask) != 0) {
                if (i10 > 0) {
                    sb2.append(", ");
                }
                sb2.append(updateValue.mo3003intParamNamew8GmfQM(Operation.IntParameter.m3031constructorimpl(i11)));
                i10++;
            }
        }
        String string = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        int objects = updateValue.getObjects();
        int i12 = 0;
        for (int i13 = 0; i13 < objects; i13++) {
            if (((1 << i13) & operations.pushedObjectMask) != 0) {
                if (i10 > 0) {
                    sb3.append(", ");
                }
                sb3.append(updateValue.mo3004objectParamName31yXWZQ(Operation.ObjectParameter.m3042constructorimpl(i13)));
                i12++;
            }
        }
        String string2 = sb3.toString();
        StringBuilder sb4 = new StringBuilder("Error while pushing ");
        sb4.append(updateValue);
        sb4.append(". Not all arguments were provided. Missing ");
        sb4.append(i10);
        sb4.append(" int arguments (");
        d.A(sb4, string, ") and ", i12, " object arguments (");
        d.z(sb4, string2, ").");
    }

    public final void pushUps(int count) {
        Operations operations = this.operations;
        Operation.Ups ups = Operation.Ups.INSTANCE;
        operations.pushOp(ups);
        Operations.WriteScope.m3079setIntA6tL2VI(Operations.WriteScope.m3074constructorimpl(operations), Operation.IntParameter.m3031constructorimpl(0), count);
        if (operations.pushedIntMask == operations.createExpectedArgMask(ups.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(ups.getObjects())) {
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        int ints = ups.getInts();
        int i10 = 0;
        for (int i11 = 0; i11 < ints; i11++) {
            if (((1 << i11) & operations.pushedIntMask) != 0) {
                if (i10 > 0) {
                    sb2.append(", ");
                }
                sb2.append(ups.mo3003intParamNamew8GmfQM(Operation.IntParameter.m3031constructorimpl(i11)));
                i10++;
            }
        }
        String string = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        int objects = ups.getObjects();
        int i12 = 0;
        for (int i13 = 0; i13 < objects; i13++) {
            if (((1 << i13) & operations.pushedObjectMask) != 0) {
                if (i10 > 0) {
                    sb3.append(", ");
                }
                sb3.append(ups.mo3004objectParamName31yXWZQ(Operation.ObjectParameter.m3042constructorimpl(i13)));
                i12++;
            }
        }
        String string2 = sb3.toString();
        StringBuilder sb4 = new StringBuilder("Error while pushing ");
        sb4.append(ups);
        sb4.append(". Not all arguments were provided. Missing ");
        sb4.append(i10);
        sb4.append(" int arguments (");
        d.A(sb4, string, ") and ", i12, " object arguments (");
        d.z(sb4, string2, ").");
    }

    public final void pushUseNode(Object node) {
        if (node instanceof ComposeNodeLifecycleCallback) {
            this.operations.push(Operation.UseCurrentNode.INSTANCE);
        }
    }

    @Override // androidx.compose.runtime.changelist.OperationsDebugStringFormattable
    public String toDebugString(String linePrefix) {
        StringBuilder sbS = d.s("ChangeList instance containing ");
        sbS.append(getSize());
        sbS.append(" operations");
        if (sbS.length() > 0) {
            sbS.append(":\n");
            sbS.append(this.operations.toDebugString(linePrefix));
        }
        return sbS.toString();
    }

    public final void pushInsertSlots(Anchor anchor, SlotTable from, FixupList fixups) {
        Operations operations = this.operations;
        Operation.InsertSlotsWithFixups insertSlotsWithFixups = Operation.InsertSlotsWithFixups.INSTANCE;
        operations.pushOp(insertSlotsWithFixups);
        Operations operationsM3074constructorimpl = Operations.WriteScope.m3074constructorimpl(operations);
        Operations.WriteScope.m3080setObjectDKhxnng(operationsM3074constructorimpl, Operation.ObjectParameter.m3042constructorimpl(0), anchor);
        Operations.WriteScope.m3080setObjectDKhxnng(operationsM3074constructorimpl, Operation.ObjectParameter.m3042constructorimpl(1), from);
        Operations.WriteScope.m3080setObjectDKhxnng(operationsM3074constructorimpl, Operation.ObjectParameter.m3042constructorimpl(2), fixups);
        if (operations.pushedIntMask == operations.createExpectedArgMask(insertSlotsWithFixups.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(insertSlotsWithFixups.getObjects())) {
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        int ints = insertSlotsWithFixups.getInts();
        int i10 = 0;
        for (int i11 = 0; i11 < ints; i11++) {
            if (((1 << i11) & operations.pushedIntMask) != 0) {
                if (i10 > 0) {
                    sb2.append(", ");
                }
                sb2.append(insertSlotsWithFixups.mo3003intParamNamew8GmfQM(Operation.IntParameter.m3031constructorimpl(i11)));
                i10++;
            }
        }
        String string = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        int objects = insertSlotsWithFixups.getObjects();
        int i12 = 0;
        for (int i13 = 0; i13 < objects; i13++) {
            if (((1 << i13) & operations.pushedObjectMask) != 0) {
                if (i10 > 0) {
                    sb3.append(", ");
                }
                sb3.append(insertSlotsWithFixups.mo3004objectParamName31yXWZQ(Operation.ObjectParameter.m3042constructorimpl(i13)));
                i12++;
            }
        }
        String string2 = sb3.toString();
        StringBuilder sb4 = new StringBuilder("Error while pushing ");
        sb4.append(insertSlotsWithFixups);
        sb4.append(". Not all arguments were provided. Missing ");
        sb4.append(i10);
        sb4.append(" int arguments (");
        d.A(sb4, string, ") and ", i12, " object arguments (");
        d.z(sb4, string2, ").");
    }
}
